/*-
 * $Log: IAliasSet.java,v $
 * Revision 1.8  2005/12/04 22:14:39  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:24:28  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.6  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.5  2005/03/25 09:26:02  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 14:01:13  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:49  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:57:55  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:09  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.aliasset;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;

import com.documentum.fc.client.IDfAliasSet;


/**
 * <H4>Attributes and type of alias sets.</H4>
 * <DL>
 * <DT><B>Description : </B>
 * <DD>Container for all attributes found in a dm_alias_set.</DD>
 * </DT>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Apr 7, 2004 7:15:55 PM.</DD>
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
 * @author Christopher Harper account : dmadmin
 * @version 3.0.0
 * @since 1.0.3
 */
public abstract interface IAliasSet
									extends
										IDfAliasSet,
										IPersistentObject
{

	/**
	 * alias_category INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALIAS_CATEGORY		= "alias_category";	//$NON-NLS-1$

	/**
	 * alias_description CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALIAS_DESCRIPTION	= "alias_description";	//$NON-NLS-1$

	/**
	 * alias_name CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALIAS_NAME			= "alias_name";		//$NON-NLS-1$

	/**
	 * alias_usr_category INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALIAS_USR_CATEGORY	= "alias_usr_category"; //$NON-NLS-1$

	/**
	 * alias_value CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALIAS_VALUE			= "alias_value";		//$NON-NLS-1$

	/**
	 * object_description CHAR(128)
	 * 
	 * @since 1.0.3
	 */
	public static final String	OBJECT_DESCRIPTION	= "object_description"; //$NON-NLS-1$

	/**
	 * object_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	OBJECT_NAME			= "object_name";		//$NON-NLS-1$

	/**
	 * owner_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	OWNER_NAME			= "owner_name";		//$NON-NLS-1$

	/**
	 * dm_alias_set
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_ALIAS_SET	= "dm_alias_set";		//$NON-NLS-1$

}
