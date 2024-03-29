/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package net.sf.jasperreports.charts.base;

import net.sf.jasperreports.charts.JRXyzDataset;
import net.sf.jasperreports.charts.JRXyzSeries;
import net.sf.jasperreports.engine.JRChartDataset;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.base.JRBaseChartDataset;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.design.JRVerifier;

/**
 * @author Flavius Sana (flavius_sana@users.sourceforge.net)
 * @version $Id: JRBaseXyzDataset.java 3938 2010-08-19 14:59:36Z teodord $
 */
public class JRBaseXyzDataset extends JRBaseChartDataset implements JRXyzDataset {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	protected JRXyzSeries[] xyzSeries;
	

	public JRBaseXyzDataset( JRChartDataset dataset){
		super( dataset);
	}

	public JRBaseXyzDataset( JRXyzDataset dataset, JRBaseObjectFactory factory ){
		super( dataset, factory );
		
		JRXyzSeries[] srcXyzSeries = dataset.getSeries();
		
		if( srcXyzSeries != null && srcXyzSeries.length > 0 ){
			
			xyzSeries = new JRXyzSeries[ srcXyzSeries.length ];
			for( int i = 0; i < srcXyzSeries.length; i++ ){
				xyzSeries[i] = factory.getXyzSeries( srcXyzSeries[i] );
			}
		}
	}
	
	public JRXyzSeries[] getSeries(){
		return xyzSeries;
	}

	/**
	 * 
	 */
	public byte getDatasetType() {
		return JRChartDataset.XYZ_DATASET;
	}

	/**
	 *
	 */
	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}


	public void validate(JRVerifier verifier)
	{
		verifier.verify(this);
	}

	/**
	 * 
	 */
	public Object clone() 
	{
		JRBaseXyzDataset clone = (JRBaseXyzDataset)super.clone();
		
		if (xyzSeries != null)
		{
			clone.xyzSeries = new JRXyzSeries[xyzSeries.length];
			for(int i = 0; i < xyzSeries.length; i++)
			{
				clone.xyzSeries[i] = (JRXyzSeries)xyzSeries[i].clone();
			}
		}
		
		return clone;
	}
}
