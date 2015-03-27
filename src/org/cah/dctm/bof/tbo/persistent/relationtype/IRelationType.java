/*-
 * $Log: IRelationType.java,v $
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
 * Revision 1.5  2005/03/25 09:26:05  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 14:01:45  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:59  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:58:15  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:15  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.relationtype;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;

import com.documentum.fc.client.IDfRelationType;


/**
 * <H4>Ralation type container for attribute names, type name and public
 * methods.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Aug 20, 2004 11:07:38 AM.</DD>
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
public interface IRelationType
								extends
									IPersistentObject,
									IDfRelationType
{

	/**
	 * child_parent_label CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	CHILD_PARENT_LABEL		= "child_parent_label"; //$NON-NLS-1$

	/**
	 * child_type CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	CHILD_TYPE				= "child_type";		//$NON-NLS-1$

	/**
	 * description CHAR(250)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DESCRIPTION				= "description";		//$NON-NLS-1$

	/**
	 * direction_kind INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	DIRECTION_KIND			= "direction_kind";	//$NON-NLS-1$

	/**
	 * integrity_kind INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	INTEGRITY_KIND			= "integrity_kind";	//$NON-NLS-1$

	/**
	 * parent_child_label CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	PARENT_CHILD_LABEL		= "parent_child_label"; //$NON-NLS-1$

	/**
	 * parent_type CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	PARENT_TYPE				= "parent_type";		//$NON-NLS-1$

	/**
	 * relation_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	RELATION_NAME			= "relation_name";		//$NON-NLS-1$

	/**
	 * security_type CHAR(10)
	 * 
	 * @since 1.0.3
	 */
	public static final String	SECURITY_TYPE			= "security_type";		//$NON-NLS-1$

	/**
	 * Name of the type.
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_RELATION_TYPE	= "dm_relation_type";	//$NON-NLS-1$
}
