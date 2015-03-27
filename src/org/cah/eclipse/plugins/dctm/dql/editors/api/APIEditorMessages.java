/*-
 * $Log: APIEditorMessages.java,v $
 * Revision 1.9  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.8  2005/12/04 20:27:46  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.7  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.6  2005/03/25 09:21:17  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/09 14:01:40  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.4  2005/01/24 12:34:57  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.3  2005/01/11 14:02:18  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.2  2005/01/11 13:47:23  harpechr
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof).
 *
 * Revision 1.1  2005/01/07 12:37:50  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.editors.api;

import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * <H4>API editor resource bundle.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 28, 2004 1:37:52 PM.</DD>
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
public class APIEditorMessages
{

	/**
	 * Resource bundle identificator.
	 * 
	 * @since 1.0
	 */
	private static final String		RESOURCE_BUNDLE	= APIEditorMessages.class
														.getName()
														+ "Properties"; //$NON-NLS-1$

	/**
	 * Resource bundle instance.
	 * 
	 * @since 1.0
	 */
	private static ResourceBundle	resourceBundle	= null;

	/**
	 * Sole constructors.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:38:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	private APIEditorMessages()
	{

		super();
	}

	/**
	 * Get the bundle.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:40:01 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the resource bundle.
	 */
	public static ResourceBundle getResourceBundle()
	{

		if(APIEditorMessages.resourceBundle == null)
		{
			try
			{
				APIEditorMessages.resourceBundle = ResourceBundle
					.getBundle(RESOURCE_BUNDLE);
			} catch(MissingResourceException swallow)
			{
				/* Nothing to do. */
			}
		}
		return APIEditorMessages.resourceBundle;
	}

	/**
	 * Get a string from the bundle.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:38:07 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param key
	 *            resource key.
	 * @return string from the bundle.
	 */
	public static String getString(final String key)
	{

		try
		{
			return APIEditorMessages.resourceBundle.getString(key);
		} catch(MissingResourceException e)
		{
			return key;
		}
	}
}
