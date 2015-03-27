/*-
 * $Log: IUser.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
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
 * Revision 1.4  2005/02/09 14:01:43  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:59  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:58:13  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:13  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.user;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;

import com.documentum.fc.client.IDfUser;


/**
 * <h4>Attributes and type for the type dm_user.</h4>
 * <DL>
 * <DT><B>Copyright: </B>
 * <DD>2004 (C) Christopher Harper</DD>
 * </DT>
 * <DT><B>Created: </B>
 * <DD>Apr 7, 2004 6:18:50 PM</DD>
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
public abstract interface IUser
								extends
									IPersistentObject,
									IDfUser
{

	/**
	 * acl_domain CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	ACL_DOMAIN			= "acl_domain";			//$NON-NLS-1$

	/**
	 * acl_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	ACL_NAME			= "acl_name";				//$NON-NLS-1$

	/**
	 * alias_set_id ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALIAS_SET_ID		= "alias_set_id";			//$NON-NLS-1$

	/**
	 * client_capability INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	CLIENT_CAPABILITY	= "client_capability";		//$NON-NLS-1$

	/**
	 * default_folder CHAR(200)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_FOLDER		= "default_folder";		//$NON-NLS-1$

	/**
	 * description CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DESCRIPTION			= "description";			//$NON-NLS-1$

	/**
	 * failed_auth_attempt INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	FAILED_AUTH_ATTEMPT	= "failed_auth_attempt";	//$NON-NLS-1$

	/**
	 * globally_managed BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	GLOBALLY_MANAGED	= "globally_managed";		//$NON-NLS-1$

	/**
	 * group_def_permit INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	GROUP_DEF_PERMIT	= "group_def_permit";		//$NON-NLS-1$

	/**
	 * home_docbase CHAR(120)
	 * 
	 * @since 1.0.3
	 */
	public static final String	HOME_DOCBASE		= "home_docbase";			//$NON-NLS-1$

	/**
	 * owner_def_permit INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	OWNER_DEF_PERMIT	= "owner_def_permit";		//$NON-NLS-1$

	/**
	 * r_has_events BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HAS_EVENTS		= "r_has_events";			//$NON-NLS-1$

	/**
	 * r_is_group BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_IS_GROUP			= "r_is_group";			//$NON-NLS-1$

	/**
	 * r_modify_date TIME
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_MODIFY_DATE		= "r_modify_date";			//$NON-NLS-1$

	/**
	 * dm_user
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_USER		= "dm_user";				//$NON-NLS-1$

	/**
	 * user_address CHAR(80)
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_ADDRESS		= "user_address";			//$NON-NLS-1$

	/**
	 * user_db_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_DB_NAME		= "user_db_name";			//$NON-NLS-1$

	/**
	 * user_delegation CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_DELEGATION		= "user_delegation";		//$NON-NLS-1$

	/**
	 * user_group_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_GROUP_NAME		= "user_group_name";		//$NON-NLS-1$

	/**
	 * user_ldap_dn CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_LDAP_DN		= "user_ldap_dn";			//$NON-NLS-1$

	/**
	 * user_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_NAME			= "user_name";				//$NON-NLS-1$

	/**
	 * user_os_domain CHAR(16)
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_OS_DOMAIN		= "user_os_domain";		//$NON-NLS-1$

	/**
	 * user_os_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_OS_NAME		= "user_os_name";			//$NON-NLS-1$

	/**
	 * user_privileges INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_PRIVILEGES		= "user_privileges";		//$NON-NLS-1$

	/**
	 * user_source CHAR(16)
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_SOURCE			= "user_source";			//$NON-NLS-1$

	/**
	 * user_state INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_STATE			= "user_state";			//$NON-NLS-1$

	/**
	 * user_xprivileges INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_XPRIVILEGES	= "user_xprivileges";		//$NON-NLS-1$

	/**
	 * workflow_disabled BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	WORKFLOW_DISABLED	= "workflow_disabled";		//$NON-NLS-1$

	/**
	 * world_def_permit INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	WORLD_DEF_PERMIT	= "world_def_permit";		//$NON-NLS-1$
}
