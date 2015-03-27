/*-
 * $Log: IFolder.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:24:31  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.6  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.5  2005/03/25 09:26:03  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 14:01:45  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:35:00  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:58:14  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:14  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.sysobject.folder;

import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;


/**
 * <h4>Attributes and type of the dm_folder type.</h4>
 * <DL>
 * <DT><B>Copyright: </B>
 * <DD>2004 (C) Christopher Harper</DD>
 * </DT>
 * <DT><B>Created: </B>
 * <DD>Apr 7, 2004 5:52:19 PM</DD>
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
public abstract interface IFolder
									extends
										ISysObject
{

	/**
	 * Object ID of the folders or cabinets that contain this folder directly or
	 * indirectly 'i_ancestor_id' ID REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_ANCESTOR_ID			= "i_ancestor_id";	//$NON-NLS-1$

	/**
	 * Folder paths for all locations to which the folder is linked. The length
	 * of this attribute will vary for SQL Server customers: For a new SQL
	 * Server Docbase, the length is 450. For an upgraded SQL Server Docbase not
	 * using Unicode, the length is 765 'r_folder_path' CHAR(450) REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_FOLDER_PATH			= "r_folder_path";	//$NON-NLS-1$

	/**
	 * dm_folder
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_FOLDER			= "dm_folder";		//$NON-NLS-1$

	/**
	 * Folder identifying id start.
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_FOLDER_PREFIX	= "0b";			//$NON-NLS-1$
}
