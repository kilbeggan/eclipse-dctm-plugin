/*-
 * $Log: IPersistentObject.java,v $
 * Revision 1.10  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * 
 * Revision 1.9  2005/12/04 20:24:54  madcook
 * Version 3.0.0 work started.
 * 
 * Revision 1.8 2005/11/21 13:04:32 madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5
 * settings. 
 * 
 * Revision 1.7 2005/04/01 11:19:20 harpechr 
 * Sort 
 * 
 * Revision 1.6 2005/03/25 09:26:04 harpechr 
 * Version 2.0.0 code that has implemented the new java 1.5 features. 
 * 
 * Revision 1.5 2005/02/09 14:01:38 harpechr 
 * Version 1.0.5 work started. 
 * 
 * Revision 1.4 2005/01/24 12:34:56 harpechr 
 * Version 1.0.4 work started. 
 * 
 * Revision 1.3 2005/01/11 13:47:25 harpechr 
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof). 
 * 
 * Revision 1.2 2005/01/10 11:58:11 harpechr 
 * Changed version numbers and since information to 1.0.3. 
 * 
 * Revision 1.1 2005/01/10 10:41:11 harpechr TBO interfaces for most commonly 
 * used types (No implementations since were trying to do a light client.).
 */

package org.cah.dctm.bof.tbo.persistent;

import java.util.Arrays;
import java.util.Vector;

import com.documentum.fc.client.IDfBusinessObject;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.common.IDfDynamicInheritance;


/**
 * <h4>Attributes found in a persistent object.</h4>
 * <DL>
 * <DT><B>Description: </B>
 * <DD>Container for all attributes found in a persistent object.</DD>
 * </DT>
 * <DT><B>Copyright: </B>
 * <DD>2004 (C) Christopher Harper</DD>
 * </DT>
 * <DT><B>Created: </B>
 * <DD>Apr 7, 2004 6:07:25 PM</DD>
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
public abstract interface IPersistentObject
											extends
												IDfBusinessObject,
												IDfDynamicInheritance,
												IDfPersistentObject
{

	/**
	 * Attribute prefixes that shouldn't be modified.
	 * 
	 * @since 1.0.3
	 */
	public static final Vector<String>	ATTRIBUTE_NAME_PREFIX_WE_SHOULDNT_TOUCH	= new Vector<String>(
																					Arrays
																						.asList(new String[] {
		IPersistentObject.ATTRIBUTE_PREFIX_APPLICATION,
		IPersistentObject.ATTRIBUTE_PREFIX_INTERNAL,
		IPersistentObject.ATTRIBUTE_PREFIX_READONLY										}));

	/*
	 * Arrays.asList(new String[] {
	 * IPersistentObject.ATTRIBUTE_PREFIX_APPLICATION,
	 * IPersistentObject.ATTRIBUTE_PREFIX_INTERNAL,
	 * IPersistentObject.ATTRIBUTE_PREFIX_READONLY })
	 */

	/**
	 * Attribute prefix for application attributes.
	 * 
	 * @since 1.0.3
	 */
	public static final String			ATTRIBUTE_PREFIX_APPLICATION			= "a_";				//$NON-NLS-1$

	/**
	 * Attribute prefix for internal attributes.
	 * 
	 * @since 1.0.3
	 */
	public static final String			ATTRIBUTE_PREFIX_INTERNAL				= "i_";				//$NON-NLS-1$

	/**
	 * Attribute prefix for read only attributes.
	 * 
	 * @since 1.0.3
	 */
	public static final String			ATTRIBUTE_PREFIX_READONLY				= "r_";				//$NON-NLS-1$

	/**
	 * The i_is_replica attribute indicates whether the object is a local
	 * replica of an object in a remote Docbase 'i_is_replica' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_IS_REPLICA							= "i_is_replica";		//$NON-NLS-1$

	/**
	 * The i_vstamp attribute contains an integer value that represents the
	 * number of committed transactions that have changed an object. This value
	 * is used for versioning, as part of the locking mechanism, to ensure that
	 * one user does not overwrite the changes made by another 'i_vstamp'
	 * INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_VSTAMP								= "i_vstamp";			//$NON-NLS-1$

	/**
	 * The r_object_id attribute contains a 16-character hexadecimal string that
	 * is assigned by the system when an object is created. This value uniquely
	 * identifies the object in the Docbase 'r_object_id'.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_OBJECT_ID								= "r_object_id";		//$NON-NLS-1$

	/**
	 * Value to return all non repeating, non internal and non read only
	 * attributes.
	 * 
	 * @since 1.0.5
	 */
	public static final String			STAR									= "*";					//$NON-NLS-1$

	/**
	 * Table suffix for repeating table.
	 * 
	 * @since 1.0.3
	 */
	public static final String			TABLE_PREFIX_REPEATING					= "_r";				//$NON-NLS-1$

	/**
	 * Table suffix for single table.
	 * 
	 * @since 1.0.3
	 */
	public static final String			TABLE_PREFIX_SINGLE						= "_s";				//$NON-NLS-1$

	/**
	 * Type name prefix.
	 * 
	 * @since 1.0
	 */
	public static final String			TYPE_PREFIX								= "dm_";				//$NON-NLS-1$

	/**
	 * Content type name prefix.
	 * 
	 * @since 1.0
	 */
	public static final String			TYPE_PREFIX_CONTENT						= "dmr_";				//$NON-NLS-1$

	/**
	 * Internal type name prefix.
	 * 
	 * @since 1.0
	 */
	public static final String			TYPE_PREFIX_INTERNAL					= "dmi_";				//$NON-NLS-1$

	/**
	 * Creator of the BOF / TBO.
	 * 
	 * @since 1.0.3
	 */
	public static final String			VENDOR									= "Christopher Harper"; //$NON-NLS-1$

	/**
	 * Version of the BOF / TBO.
	 * 
	 * @since 1.0.3
	 */
	public static final String			VERSION									= "1.0";				//$NON-NLS-1$

}
