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
package net.sf.jasperreports.engine.export.ooxml;

import java.io.Writer;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: XlsxContentTypesHelper.java 4595 2011-09-08 15:55:10Z teodord $
 */
public class XlsxContentTypesHelper extends BaseHelper
{
	/**
	 * 
	 */
	public XlsxContentTypesHelper(Writer writer)
	{
		super(writer);
	}

	/**
	 *
	 */
	public void exportHeader()
	{
		write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		write("<Types xmlns=\"http://schemas.openxmlformats.org/package/2006/content-types\">\n");
		write("  <Default Extension=\"gif\" ContentType=\"image/gif\"/>\n");
		write("  <Default Extension=\"jpeg\" ContentType=\"image/jpeg\"/>\n");
		write("  <Default Extension=\"png\" ContentType=\"image/png\"/>\n");
		write("  <Default Extension=\"tiff\" ContentType=\"image/tiff\"/>\n");
		write("  <Default Extension=\"rels\" ContentType=\"application/vnd.openxmlformats-package.relationships+xml\"/>\n");
		write("  <Default Extension=\"xml\" ContentType=\"application/xml\"/>\n");
		write("  <Override PartName=\"/xl/styles.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.styles+xml\"/>\n");
		write("  <Override PartName=\"/xl/workbook.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml\"/>\n");
	}
	

	/**
	 * 
	 */
	public void exportSheet(int index)
	{
		write("  <Override PartName=\"/xl/worksheets/sheet" + index + ".xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml\"/>\n");
		write("  <Override PartName=\"/xl/drawings/drawing" + index + ".xml\" ContentType=\"application/vnd.openxmlformats-officedocument.drawing+xml\"/>\n");
	}
	

	/**
	 *
	 */
	public void exportFooter()
	{
		write("</Types>\n");
	}

}
