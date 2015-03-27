/*-
 * $Log: IStore.java,v $
 * Revision 1.6  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.5  2005/12/04 20:24:55  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.4  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.3  2005/03/25 09:26:05  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.2  2005/02/09 14:01:45  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.1  2005/01/24 21:07:46  harpechr
 * Storage attributes and type defined.
 *
 */

package org.cah.dctm.bof.tbo.persistent.store;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;


/**
 * <H4>Store type defined.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Jan 24, 2005 8:41:02 PM.</DD>
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
 * @since 1.0.4
 */
public interface IStore
						extends
							IPersistentObject
{

	/**
	 * base_url CHAR(255)
	 * 
	 * @since 1.0.4
	 */
	public static final String	BASE_URL			= "base_url";			//$NON-NLS-1$

	/**
	 * capacity INTEGER
	 * 
	 * @since 1.0.4
	 */
	public static final String	CAPACITY			= "capacity";			//$NON-NLS-1$

	/**
	 * component CHAR(64) REPEATING
	 * 
	 * @since 1.0.4
	 */
	public static final String	COMPONENT			= "component";			//$NON-NLS-1$

	/**
	 * crypto_key CHAR(255)
	 * 
	 * @since 1.0.4
	 */
	public static final String	CRYPTO_KEY			= "crypto_key";		//$NON-NLS-1$

	/**
	 * crypto_mode INTEGER
	 * 
	 * @since 1.0.4
	 */
	public static final String	CRYPTO_MODE			= "crypto_mode";		//$NON-NLS-1$

	/**
	 * current_use INTEGER
	 * 
	 * @since 1.0.4
	 */
	public static final String	CURRENT_USE			= "current_use";		//$NON-NLS-1$

	/**
	 * get_method CHAR(32)
	 * 
	 * @since 1.0.4
	 */
	public static final String	GET_METHOD			= "get_method";		//$NON-NLS-1$

	/**
	 * i_use_mask_after INTEGER
	 * 
	 * @since 1.0.4
	 */
	public static final String	I_USE_MASK_AFTER	= "i_use_mask_after";	//$NON-NLS-1$

	/**
	 * media_type INTEGER
	 * 
	 * @since 1.0.4
	 */
	public static final String	MEDIA_TYPE			= "media_type";		//$NON-NLS-1$

	/**
	 * name CHAR(64)
	 * 
	 * @since 1.0.4
	 */
	public static final String	NAME				= "name";				//$NON-NLS-1$

	/**
	 * offline_get_method BOOLEAN
	 * 
	 * @since 1.0.4
	 */
	public static final String	OFFLINE_GET_METHOD	= "offline_get_method"; //$NON-NLS-1$

	/**
	 * r_component ID REPEATING
	 * 
	 * @since 1.0.4
	 */
	public static final String	R_COMPONENT			= "r_component";		//$NON-NLS-1$

	/**
	 * r_component_count INTEGER
	 * 
	 * @since 1.0.4
	 */
	public static final String	R_COMPONENT_COUNT	= "r_component_count";	//$NON-NLS-1$

	/**
	 * r_status INTEGER
	 * 
	 * @since 1.0.4
	 */
	public static final String	R_STATUS			= "r_status";			//$NON-NLS-1$

	/**
	 * require_ticket BOOLEAN
	 * 
	 * @since 1.0.4
	 */
	public static final String	REQUIRE_TICKET		= "require_ticket";	//$NON-NLS-1$

	/**
	 * store_type INTEGER
	 * 
	 * @since 1.0.4
	 */
	public static final String	STORE_TYPE			= "store_type";		//$NON-NLS-1$

	/**
	 * Type name 'dm_store'.
	 * 
	 * @since 1.0.4
	 */
	public static final String	TYPE_DM_STORE		= "dm_store";			//$NON-NLS-1$
}
