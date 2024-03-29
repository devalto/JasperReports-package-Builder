/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Contributors:
 * Peter Severin - peter_p_s@users.sourceforge.net 
 */
package net.sf.jasperreports.engine.design;

import java.io.Serializable;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.fill.JREvaluator;
import net.sf.jasperreports.engine.util.JRClassLoader;
import net.sf.jasperreports.engine.util.JRProperties;

import org.apache.commons.collections.ReferenceMap;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRAbstractJavaCompiler.java 4595 2011-09-08 15:55:10Z teodord $
 */
public abstract class JRAbstractJavaCompiler extends JRAbstractCompiler
{

	/**
	 * Property that indicates whether a legacy fix for a JVM issue related to
	 * evaluator classes generated by JasperReports is enabled.  The fix is
	 * enabled by default.
	 * 
	 * Due to the fix, the garbage collector might not be able to collect
	 * a classloader that loaded JasperReports classes. This would be
	 * inconvenient in scenarios in which JasperReports classes are repeatedly
	 * loaded by different classloaders, e.g. when JasperReports is part of
	 * the classpath of a web application which is often reloaded.  In such
	 * scenarios, set this property to false.
	 */
	public static final String PROPERTY_EVALUATOR_CLASS_REFERENCE_FIX_ENABLED = JRProperties.PROPERTY_PREFIX + 
			"evaluator.class.reference.fix.enabled";
	
	// @JVM Crash workaround
	// Reference to the loaded class class in a per thread map
	private static ThreadLocal<Class<?>> classFromBytesRef = new ThreadLocal<Class<?>>();


	private static final Object CLASS_CACHE_NULL_KEY = new Object();
	private static Map<Object,Map<String,Class<?>>> classCache = new ReferenceMap(ReferenceMap.WEAK, ReferenceMap.SOFT);

	
	protected JRAbstractJavaCompiler(boolean needsSourceFiles)
	{
		super(needsSourceFiles);
	}


	protected JREvaluator loadEvaluator(Serializable compileData, String className) throws JRException
	{
		JREvaluator evaluator = null;

		try
		{
			Class<?> clazz = getClassFromCache(className);
			if (clazz == null)
			{
				clazz = JRClassLoader.loadClassFromBytes(className, (byte[]) compileData);
				putClassInCache(className, clazz);
			}
			
			if (JRProperties.getBooleanProperty(PROPERTY_EVALUATOR_CLASS_REFERENCE_FIX_ENABLED))
			{
				//FIXME multiple classes per thread?
				classFromBytesRef.set(clazz);
			}
		
			evaluator = (JREvaluator) clazz.newInstance();
		}
		catch (Exception e)
		{
			throw new JRException("Error loading expression class : " + className, e);
		}
		
		return evaluator;
	}
	
	
	protected static Object classCacheKey()
	{
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		return contextClassLoader == null ? CLASS_CACHE_NULL_KEY : contextClassLoader;
	}

	
	protected static synchronized Class<?> getClassFromCache(String className)
	{
		Object key = classCacheKey();
		Map<String,Class<?>> contextMap = classCache.get(key);
		Class<?> cachedClass = null;
		if (contextMap != null)
		{
			cachedClass = contextMap.get(className);
		}
		return cachedClass;
	}


	protected static synchronized void putClassInCache(String className, Class<?> loadedClass)
	{
		Object key = classCacheKey();
		Map<String,Class<?>> contextMap = classCache.get(key);
		if (contextMap == null)
		{
			contextMap = new ReferenceMap(ReferenceMap.HARD, ReferenceMap.SOFT);
			classCache.put(key, contextMap);
		}
		contextMap.put(className, loadedClass);
	}
}
