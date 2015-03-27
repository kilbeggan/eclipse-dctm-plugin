/*-
 * $Log: IDmiTypeInfo.java,v $
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
 * Revision 1.4  2005/02/09 14:01:45  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:35:00  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:58:12  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:11  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.dmitypeinfo;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;


/**
 * <H4>Attributes of a dmi_type_info object.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Apr 26, 2004 10:12:44 AM.</DD>
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
public abstract interface IDmiTypeInfo
										extends
											IPersistentObject
{

	/**
	 * acl_domain CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	ACL_DOMAIN				= "acl_domain";			//$NON-NLS-1$

	/**
	 * acl_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	ACL_NAME				= "acl_name";				//$NON-NLS-1$

	/**
	 * default_group CHAR(27)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_GROUP			= "default_group";			//$NON-NLS-1$

	/**
	 * default_group_permit INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_GROUP_PERMIT	= "default_group_permit";	//$NON-NLS-1$

	/**
	 * default_owner_permit INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_OWNER_PERMIT	= "default_owner_permit";	//$NON-NLS-1$

	/**
	 * default_storage ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_STORAGE			= "default_storage";		//$NON-NLS-1$

	/**
	 * default_world_permit INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_WORLD_PERMIT	= "default_world_permit";	//$NON-NLS-1$

	/**
	 * ftindex_attrs CHAR(27) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	FTINDEX_ATTRS			= "ftindex_attrs";			//$NON-NLS-1$

	/**
	 * locally_managed BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	LOCALLY_MANAGED			= "locally_managed";		//$NON-NLS-1$

	/**
	 * r_orig_declaration BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_ORIG_DECLARATION		= "r_orig_declaration";	//$NON-NLS-1$

	/**
	 * r_supertype CHAR(27) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_SUPERTYPE				= "r_supertype";			//$NON-NLS-1$

	/**
	 * r_type_id ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_TYPE_ID				= "r_type_id";				//$NON-NLS-1$

	/**
	 * r_type_name CHAR(27)
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_TYPE_NAME				= "r_type_name";			//$NON-NLS-1$

	/**
	 * dmi_type_info
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DMI_TYPE_INFO		= "dmi_type_info";			//$NON-NLS-1$

	/**
	 * type_override ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_OVERRIDE			= "type_override";			//$NON-NLS-1$

}
