/*-
 * $Log: IGroup.java,v $
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
 * Revision 1.5  2005/03/25 09:26:04  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 14:01:46  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:35:15  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:58:10  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:10  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.group;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;


/**
 * <h4>Attributes and type for dm_group.</h4>
 * <DL>
 * <DT><B>Copyright: </B>
 * <DD>2004 (C) Christopher Harper</DD>
 * </DT>
 * <DT><B>Created: </B>
 * <DD>Apr 7, 2004 6:20:49 PM</DD>
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
public abstract interface IGroup
								extends
									IPersistentObject
{

	/**
	 * alias_set_id ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALIAS_SET_ID			= "alias_set_id";			//$NON-NLS-1$

	/**
	 * description CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DESCRIPTION				= "description";			//$NON-NLS-1$

	/**
	 * globally_managed BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	GLOBALLY_MANAGED		= "globally_managed";		//$NON-NLS-1$

	/**
	 * group_address CHAR(80)
	 * 
	 * @since 1.0.3
	 */
	public static final String	GROUP_ADDRESS			= "group_address";			//$NON-NLS-1$

	/**
	 * group_admin CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	GROUP_ADMIN				= "group_admin";			//$NON-NLS-1$

	/**
	 * group_class CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	GROUP_CLASS				= "group_class";			//$NON-NLS-1$

	/**
	 * Group class 'group'.
	 * 
	 * @since 1.0.3
	 */
	public static final String	GROUP_CLASS_GROUP		= "group";					//$NON-NLS-1$

	/**
	 * Group class 'role'
	 * 
	 * @since 1.0.3
	 */
	public static final String	GROUP_CLASS_ROLE		= "role";					//$NON-NLS-1$

	/**
	 * group_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	GROUP_NAME				= "group_name";			//$NON-NLS-1$

	/**
	 * group_source CHAR(16)
	 * 
	 * @since 1.0.3
	 */
	public static final String	GROUP_SOURCE			= "group_source";			//$NON-NLS-1$

	/**
	 * groups_names CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	GROUPS_NAMES			= "groups_names";			//$NON-NLS-1$

	/**
	 * i_all_users_names CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_ALL_USERS_NAMES		= "i_all_users_names";		//$NON-NLS-1$

	/**
	 * i_supergroups_names CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_SUPERGROUPS_NAMES		= "i_supergroups_names";	//$NON-NLS-1$

	/**
	 * is_private BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	IS_PRIVATE				= "is_private";			//$NON-NLS-1$

	/**
	 * owner_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	OWNER_NAME				= "owner_name";			//$NON-NLS-1$

	/**
	 * r_has_events BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HAS_EVENTS			= "r_has_events";			//$NON-NLS-1$

	/**
	 * r_modify_date TIME
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_MODIFY_DATE			= "r_modify_date";			//$NON-NLS-1$

	/**
	 * dm_group
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_GROUP			= "dm_group";				//$NON-NLS-1$

	/**
	 * Group r_object_id prefix for groups and it's subtypes '12'.
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_GROUP_PREFIX	= "12";					//$NON-NLS-1$

	/**
	 * users_names CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	USERS_NAMES				= "users_names";			//$NON-NLS-1$

}
