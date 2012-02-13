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
package net.sf.jasperreports.engine;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRPrintElement.java 4648 2011-10-10 12:50:53Z lucianc $
 */
public interface JRPrintElement extends JRCommonElement, JRPropertiesHolder
{

	/**
	 * A value used when no source element Id information is available.
	 * 
	 * @see #getSourceElementId()
	 */
	int UNSET_SOURCE_ELEMENT_ID = 0;
	
	/**
	 *
	 */
	public JROrigin getOrigin();
	
	/**
	 *
	 */
	public void setStyle(JRStyle style);
	
	/**
	 *
	 */
	public int getX();
	
	/**
	 *
	 */
	public void setX(int x);
	
	/**
	 *
	 */
	public int getY();
	
	/**
	 *
	 */
	public void setY(int y);
	
	/**
	 *
	 */
	public int getWidth();
	
	/**
	 *
	 */
	public void setWidth(int width);
	
	/**
	 *
	 */
	public int getHeight();
	
	/**
	 *
	 */
	public void setHeight(int height);
	
	
	/**
	 * Returns a string key that can be used to identify the element.
	 * <p>
	 * When filling a report, the {@link JRElement#getKey() report element key}
	 * is copied to all print elements generated by a report element.
	 * Note that this means that multiple elements having the same key can
	 * appear on the same page/filled report.
	 * </p>
	 */
	public String getKey();
	
	/**
	 * Accepts a print element visitor.
	 * 
	 * <p>
	 * The element calls the method that corresponds to the element type on the visitor. 
	 * </p>
	 * 
	 * @param <T> the type of the argument passed to the visit operation
	 * @param visitor the visitor objects
	 * @param arg the argument passed to the visit operation
	 */
	public <T> void accept(PrintElementVisitor<T> visitor, T arg);

	/**
	 * Returns a numerical Id associated to the fill element that generated 
	 * this print element.
	 * 
	 * @return
	 */
	public int getSourceElementId();
	
}
