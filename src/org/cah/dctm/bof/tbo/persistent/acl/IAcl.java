/*-
 * $Log: IAcl.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:24:54  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.6  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.5  2005/03/25 09:26:04  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 14:01:35  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:55  harpechr
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

package org.cah.dctm.bof.tbo.persistent.acl;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;

import com.documentum.fc.client.IDfACL;


/**
 * <H4>Acl attributes and type.</H4>
 * <DL>
 * <DT><B>Description : </B>
 * <DD>All attributes and type name that relate to a dm_acl object.</DD>
 * </DT>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Apr 7, 2004 6:44:33 PM.</DD>
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
public abstract interface IAcl
								extends
									IPersistentObject,
									IDfACL
{

	/**
	 * acl_class INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	ACL_CLASS			= "acl_class";			//$NON-NLS-1$

	/**
	 * description CHAR(128)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DESCRIPTION			= "description";		//$NON-NLS-1$

	/**
	 * globally_managed BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	GLOBALLY_MANAGED	= "globally_managed";	//$NON-NLS-1$

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
	 * r_accessor_name CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_ACCESSOR_NAME		= "r_accessor_name";	//$NON-NLS-1$

	/**
	 * r_accessor_permit INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_ACCESSOR_PERMIT	= "r_accessor_permit";	//$NON-NLS-1$

	/**
	 * r_accessor_xpermit INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_ACCESSOR_XPERMIT	= "r_accessor_xpermit"; //$NON-NLS-1$

	/**
	 * r_has_events BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HAS_EVENTS		= "r_has_events";		//$NON-NLS-1$

	/**
	 * r_is_group BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_IS_GROUP			= "r_is_group";		//$NON-NLS-1$

	/**
	 * r_is_internal BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_IS_INTERNAL		= "r_is_internal";		//$NON-NLS-1$

	/**
	 * dm_acl
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_ACL			= "dm_acl";			//$NON-NLS-1$

}
