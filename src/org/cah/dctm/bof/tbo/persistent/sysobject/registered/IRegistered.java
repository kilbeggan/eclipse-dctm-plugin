/*-
 * $Log: IRegistered.java,v $
 * Revision 1.7  2005/12/04 22:14:39  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.6  2005/12/04 20:24:31  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.5  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.4  2005/03/25 09:26:03  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.3  2005/02/09 14:01:15  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.2  2005/01/24 12:34:53  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.1  2005/01/10 11:57:08  harpechr
 * dm_registered object information defined.
 *
 */

package org.cah.dctm.bof.tbo.persistent.sysobject.registered;

import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;


/**
 * <H4>Attributes for dm_registered.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Jan 10, 2005 1:43:56 PM.</DD>
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
public interface IRegistered
							extends
								ISysObject
{

	/**
	 * column_count INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	COLUMN_COUNT		= "column_count";		//$NON-NLS-1$

	/**
	 * column_datatype CHAR(64) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	COLUMN_DATATYPE		= "column_datatype";	//$NON-NLS-1$

	/**
	 * column_length INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	COLUMN_LENGTH		= "column_length";		//$NON-NLS-1$

	/**
	 * column_name CHAR(64) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	COLUMN_NAME			= "column_name";		//$NON-NLS-1$

	/**
	 * group_table_permit INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	GROUP_TABLE_PERMIT	= "group_table_permit"; //$NON-NLS-1$

	/**
	 * is_key BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	IS_KEY				= "is_key";			//$NON-NLS-1$

	/**
	 * owner_table_permit INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	OWNER_TABLE_PERMIT	= "owner_table_permit"; //$NON-NLS-1$

	/**
	 * synonym_for CHAR(254)
	 * 
	 * @since 1.0.3
	 */
	public static final String	SYNONYM_FOR			= "synonym_for";		//$NON-NLS-1$

	/**
	 * table_name CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	TABLE_NAME			= "table_name";		//$NON-NLS-1$

	/**
	 * table_owner CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	TABLE_OWNER			= "table_owner";		//$NON-NLS-1$

	/**
	 * Name of the registered type 'dm_registered'.
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_REGISTERED	= "dm_registered";		//$NON-NLS-1$

	/**
	 * world_table_permit INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	WORLD_TABLE_PERMIT	= "world_table_permit"; //$NON-NLS-1$

}
