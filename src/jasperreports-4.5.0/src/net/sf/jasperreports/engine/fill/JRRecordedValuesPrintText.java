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
package net.sf.jasperreports.engine.fill;

import java.util.Set;

import net.sf.jasperreports.engine.JRConstants;

/**
 * Print text implementation that supports recorded values.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRRecordedValuesPrintText.java 4648 2011-10-10 12:50:53Z lucianc $
 */
public class JRRecordedValuesPrintText extends JRTemplatePrintText implements JRRecordedValuesPrintElement
{
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private JRRecordedValues recordedValues;

	/**
	 * 
	 * @param text
	 * @deprecated provide a source Id via {@link #JRTemplatePrintText(JRTemplateText, int)}
	 */
	public JRRecordedValuesPrintText(JRTemplateText text)
	{
		super(text);
	}

	/**
	 * 
	 * @param text
	 * @param sourceElementId the Id of the source element
	 */
	public JRRecordedValuesPrintText(JRTemplateText text, int sourceElementId)
	{
		super(text, sourceElementId);
	}

	public JRRecordedValues getRecordedValues()
	{
		return recordedValues;
	}

	public void deleteRecordedValues()
	{
		recordedValues = null;
	}

	public void initRecordedValues(Set<JREvaluationTime> evaluationTimes)
	{
		recordedValues = new JRRecordedValues(evaluationTimes);
	}
}
