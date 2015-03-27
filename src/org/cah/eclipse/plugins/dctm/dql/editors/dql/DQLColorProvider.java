/*-
 * $Log: DQLColorProvider.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:28:23  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.6  2005/11/22 13:27:07  madcook
 * Eclipse 3.2 compiler check warnings modified.
 * 
 * Revision 1.5 2005/03/25 09:21:16 harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features. 
 * 
 * Revision 1.4 2005/02/09 14:01:35 harpechr 
 * Version 1.0.5 work started. 
 * 
 * Revision 1.3 2005/01/24 12:34:55 harpechr 
 * Version 1.0.4 work started. 
 * 
 * Revision 1.2 2005/01/11 14:02:01 harpechr 
 * Changed version number from 1.0.2 to 1.0.3.
 * 
 * Revision 1.1 2005/01/07 12:37:49 harpechr 
 * Version 1.0.2 code. First CVS commit!
 */

package org.cah.eclipse.plugins.dctm.dql.editors.dql;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;


/**
 * <H4>Manager for colors used in the DQL editor.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Dec 2, 2004 10:44:12 AM.</DD>
 * </DT>
 * </DL>
 * <p>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * </p>
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the <a
 * href="http://www.gnu.org/licenses/gpl.html">GNU General Public License </a>
 * for more details.
 * </p>
 * 
 * @author Christopher Harper account : HARPECHR
 * @version 3.0.0
 * @since 1.0
 */
public class DQLColorProvider
{

	/**
	 * Colour of Docbase type attributes. Purple RGB(200, 0, 200).
	 * 
	 * @since 1.0
	 */
	public static final RGB		ATTRIBUTE			= new RGB(200, 0, 200);

	/**
	 * Background colour. White RGB(255, 255, 255)
	 * 
	 * @since 1.0
	 */
	public static final RGB		BACKGROUND			= new RGB(255, 255, 255);

	/**
	 * Registered table column colour. Pink RGB(250, 0, 100).
	 * 
	 * @since 1.0
	 */
	public static final RGB		COLUMN				= new RGB(250, 0, 100);

	/**
	 * Default colour. Black RGB(0, 0, 0).
	 * 
	 * @since 1.0
	 */
	public static final RGB		DEFAULT				= new RGB(0, 0, 0);

	/**
	 * Type colour. Red RGB(250, 50, 50).
	 * 
	 * @since 1.0
	 */
	public static final RGB		DOCBASE_TYPE		= new RGB(250, 50, 50);

	/**
	 * keyword colour. Blue RGB(50, 100, 200).
	 * 
	 * @since 1.0
	 */
	public static final RGB		KEYWORD				= new RGB(50, 100, 200);

	/**
	 * Multi line comment colour. Green RGB(50, 250, 50).
	 * 
	 * @since 1.0
	 */
	public static final RGB		MULTI_LINE_COMMENT	= new RGB(50, 250, 50);

	/**
	 * Colour for registered tables. Brown RGB(150, 0, 0).
	 * 
	 * @since 1.0
	 */
	public static final RGB		REGISTERED_TABLE	= new RGB(150, 0, 0);

	/**
	 * Single line comment colour. Dark green RGB(0, 150, 0).
	 * 
	 * @since 1.0
	 */
	public static final RGB		SINGLE_LINE_COMMENT	= new RGB(0, 150, 0);

	/**
	 * Colour of DQL statement start words. Orange RGB(255, 100, 0).
	 * 
	 * @since 1.0
	 */
	public static final RGB		STARTWORD			= new RGB(255, 100, 0);

	/**
	 * String colour. Turquoise RGB(50, 200, 200).
	 * 
	 * @since 1.0
	 */
	public static final RGB		STRING				= new RGB(50, 200, 200);

	/**
	 * Table of colours.
	 * 
	 * @since 1.0
	 */
	protected Map<RGB, Color>	colorTable			= new HashMap<RGB, Color>(8);

	/**
	 * Release all of the color resources held onto by the receiver.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 11:40:23 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public void dispose()
	{

		final Iterator<Color> colour = getColorTable().values().iterator();
		while(colour.hasNext())
		{
			colour.next().dispose();
		}
	}

	/**
	 * Return the Color that storage stored in the Color table as rgb.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 11:42:02 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param rgb
	 *            the desired colour
	 * @return the RGB value converted to Color.
	 */
	public Color getColor(final RGB rgb)
	{

		Color color = getColorTable().get(rgb);
		if(color == null)
		{
			color = new Color(Display.getCurrent(), rgb);
			getColorTable().put(rgb, color);
		}
		return color;
	}

	/**
	 * Get the colour table.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 11:43:04 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the color table.
	 */
	protected Map<RGB, Color> getColorTable()
	{

		return this.colorTable;
	}
}
