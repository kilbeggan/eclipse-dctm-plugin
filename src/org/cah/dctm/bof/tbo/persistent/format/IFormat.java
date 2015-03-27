/*-
 * $Log: IFormat.java,v $
 * Revision 1.8  2005/12/04 22:17:22  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:24:54  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.6  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.5  2005/03/25 09:26:05  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 14:01:46  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:35:14  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:58:14  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:14  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.format;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;


/**
 * <H4>Attributes and type for the dm_format type..</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Apr 25, 2004 10:03:08 PM.</DD>
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
 * @since 1.0.3
 */
public abstract interface IFormat
									extends
										IPersistentObject
{

	/**
	 * asset_class CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	ASSET_CLASS			= "asset_class";		//$NON-NLS-1$

	/**
	 * can_index BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	CAN_INDEX			= "can_index";			//$NON-NLS-1$

	/**
	 * com_class_id CHAR(38)
	 * 
	 * @since 1.0.3
	 */
	public static final String	COM_CLASS_ID		= "com_class_id";		//$NON-NLS-1$

	/**
	 * default_storage ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_STORAGE		= "default_storage";	//$NON-NLS-1$

	/**
	 * description CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DESCRIPTION			= "description";		//$NON-NLS-1$

	/**
	 * dos_extension CHAR(10)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DOS_EXTENSION		= "dos_extension";		//$NON-NLS-1$

	/**
	 * filename_modifier CHAR(16)
	 * 
	 * @since 1.0.3
	 */
	public static final String	FILENAME_MODIFIER	= "filename_modifier";	//$NON-NLS-1$

	/**
	 * format_class CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	FORMAT_CLASS		= "format_class";		//$NON-NLS-1$

	/**
	 * icon_index INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	ICON_INDEX			= "icon_index";		//$NON-NLS-1$

	/**
	 * is_hidden BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	IS_HIDDEN			= "is_hidden";			//$NON-NLS-1$

	/**
	 * mac_creator CHAR(4)
	 * 
	 * @since 1.0.3
	 */
	public static final String	MAC_CREATOR			= "mac_creator";		//$NON-NLS-1$

	/**
	 * mac_type CHAR(4)
	 * 
	 * @since 1.0.3
	 */
	public static final String	MAC_TYPE			= "mac_type";			//$NON-NLS-1$

	/**
	 * mime_type CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	MIME_TYPE			= "mime_type";			//$NON-NLS-1$

	/**
	 * name CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	NAME				= "name";				//$NON-NLS-1$

	/**
	 * richmedia_enabled BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	RICHMEDIA_ENABLED	= "richmedia_enabled";	//$NON-NLS-1$

	/**
	 * topic_filter CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	TOPIC_FILTER		= "topic_filter";		//$NON-NLS-1$

	/**
	 * topic_format ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	TOPIC_FORMAT		= "topic_format";		//$NON-NLS-1$

	/**
	 * topic_format_name CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	TOPIC_FORMAT_NAME	= "topic_format_name";	//$NON-NLS-1$

	/**
	 * topic_transform BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	TOPIC_TRANSFORM		= "topic_transform";	//$NON-NLS-1$

	/**
	 * dm_format
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_FORMAT		= "dm_format";			//$NON-NLS-1$

	/**
	 * win31_app CHAR(12)
	 * 
	 * @since 1.0.3
	 */
	public static final String	WIN31_APP			= "win31_app";			//$NON-NLS-1$

}
