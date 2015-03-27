/*-
 * $Log: IType.java,v $
 * Revision 1.9  2005/12/04 22:14:39  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.8  2005/12/04 20:24:31  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.7  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.6  2005/03/25 09:26:03  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/09 14:01:15  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.4  2005/01/24 12:34:53  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.3  2005/01/17 07:07:23  harpechr
 * Added the r_object_id prefix.
 *
 * Revision 1.2  2005/01/10 11:58:10  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:09  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.type;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;

import com.documentum.fc.client.IDfType;


/**
 * <h4>Attributes and type of the dm_type type.</h4>
 * <DL>
 * <DT><B>Copyright: </B>
 * <DD>2004 (C) Christopher Harper</DD>
 * </DT>
 * <DT><B>Created: </B>
 * <DD>Apr 7, 2004 6:22:50 PM</DD>
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
 * @author Christopher Andrew Harper (CAH) account: dmadmin
 * @version 3.0.0
 * @since 1.0.3
 */
public abstract interface IType
								extends
									IPersistentObject,
									IDfType
{

	/**
	 * attr_count INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	ATTR_COUNT			= "attr_count";	//$NON-NLS-1$

	/**
	 * attr_length INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ATTR_LENGTH			= "attr_length";	//$NON-NLS-1$

	/**
	 * attr_name CHAR(40) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ATTR_NAME			= "attr_name";		//$NON-NLS-1$

	/**
	 * attr_repeating BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ATTR_REPEATING		= "attr_repeating"; //$NON-NLS-1$

	/**
	 * attr_type INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ATTR_TYPE			= "attr_type";		//$NON-NLS-1$

	/**
	 * info ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	INFO				= "info";			//$NON-NLS-1$

	/**
	 * name CHAR(27)
	 * 
	 * @since 1.0.3
	 */
	public static final String	NAME				= "name";			//$NON-NLS-1$

	/**
	 * owner CHAR(40)
	 * 
	 * @since 1.0.3
	 */
	public static final String	OWNER				= "owner";			//$NON-NLS-1$

	/**
	 * r_index_attr ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_INDEX_ATTR		= "r_index_attr";	//$NON-NLS-1$

	/**
	 * s_index_attr ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	S_INDEX_ATTR		= "s_index_attr";	//$NON-NLS-1$

	/**
	 * start_pos INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	START_POS			= "start_pos";		//$NON-NLS-1$

	/**
	 * super_name CHAR(40)
	 * 
	 * @since 1.0.3
	 */
	public static final String	SUPER_NAME			= "super_name";	//$NON-NLS-1$

	/**
	 * dm_type
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_TYPE		= "dm_type";		//$NON-NLS-1$

	/**
	 * r_object_id prefix for types '03'.
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_TYPE_PREFIX	= "03";			//$NON-NLS-1$

	/**
	 * views_valid BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	VIEWS_VALID			= "views_valid";	//$NON-NLS-1$

}
