/*-
 * $Log: IRelation.java,v $
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
 * Revision 1.5  2005/02/10 11:33:54  harpechr
 * Safety commit!
 *
 * Revision 1.4  2005/02/09 14:01:31  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:54  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:58:15  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:14  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.relation;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;

import com.documentum.fc.client.IDfRelation;


/**
 * <H4>Object representation of a relation two docbase objects..</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Aug 20, 2004 10:58:06 AM.</DD>
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
public interface IRelation
							extends
								IPersistentObject,
								IDfRelation
{

	/**
	 * child_id ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	CHILD_ID			= "child_id";			//$NON-NLS-1$

	/**
	 * child_label CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	CHILD_LABEL			= "child_label";		//$NON-NLS-1$

	/**
	 * description CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DESCRIPTION			= "description";		//$NON-NLS-1$

	/**
	 * effective_date TIME
	 * 
	 * @since 1.0.3
	 */
	public static final String	EFFECTIVE_DATE		= "effective_date";	//$NON-NLS-1$

	/**
	 * expiration_date TIME
	 * 
	 * @since 1.0.3
	 */
	public static final String	EXPIRATION_DATE		= "expiration_date";	//$NON-NLS-1$

	/**
	 * order_no INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	ORDER_NO			= "order_no";			//$NON-NLS-1$

	/**
	 * parent_id ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	PARENT_ID			= "parent_id";			//$NON-NLS-1$

	/**
	 * permanent_link BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	PERMANENT_LINK		= "permanent_link";	//$NON-NLS-1$

	/**
	 * relation_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	RELATION_NAME		= "relation_name";		//$NON-NLS-1$

	/**
	 * Name of the relation type.
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_RELATION	= "dm_relation";		//$NON-NLS-1$

}
