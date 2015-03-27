/*-
 * $Log: IDocbaseConfig.java,v $
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
 * Revision 1.2  2005/01/10 11:58:11  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:10  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.sysobject.docbaseconfig;

import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;


/**
 * <H4>dm_docbae_config object type defined..</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>May 31, 2004 4:36:08 PM.</DD>
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
public abstract interface IDocbaseConfig
										extends
											ISysObject
{

	/**
	 * a_bpaction_run_as CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	A_BPACTION_RUN_AS			= "a_bpaction_run_as";			//$NON-NLS-1$

	/**
	 * auth_protocol CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	AUTH_PROTOCOL				= "auth_protocol";				//$NON-NLS-1$

	/**
	 * client_pcaching_change INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	CLIENT_PCACHING_CHANGE		= "client_pcaching_change";	//$NON-NLS-1$

	/**
	 * client_pcaching_disabled BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	CLIENT_PCACHING_DISABLED	= "client_pcaching_disabled";	//$NON-NLS-1$

	/**
	 * dd_locales CHAR(5) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	DD_LOCALES					= "dd_locales";				//$NON-NLS-1$

	/**
	 * default_app_permit INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_APP_PERMIT			= "default_app_permit";		//$NON-NLS-1$

	/**
	 * effective_date TIME
	 * 
	 * @since 1.0.3
	 */
	public static final String	EFFECTIVE_DATE				= "effective_date";			//$NON-NLS-1$

	/**
	 * folder_security BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	FOLDER_SECURITY				= "folder_security";			//$NON-NLS-1$

	/**
	 * fulltext_install_locs CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	FULLTEXT_INSTALL_LOCS		= "fulltext_install_locs";		//$NON-NLS-1$

	/**
	 * i_crypto_key CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_CRYPTO_KEY				= "i_crypto_key";				//$NON-NLS-1$

	/**
	 * index_store CHAR(80)
	 * 
	 * @since 1.0.3
	 */
	public static final String	INDEX_STORE					= "index_store";				//$NON-NLS-1$

	/**
	 * mac_access_protocol CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	MAC_ACCESS_PROTOCOL			= "mac_access_protocol";		//$NON-NLS-1$

	/**
	 * max_auth_attempt INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	MAX_AUTH_ATTEMPT			= "max_auth_attempt";			//$NON-NLS-1$

	/**
	 * offline_checkin_flag INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	OFFLINE_CHECKIN_FLAG		= "offline_checkin_flag";		//$NON-NLS-1$

	/**
	 * offline_sync_level INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	OFFLINE_SYNC_LEVEL			= "offline_sync_level";		//$NON-NLS-1$

	/**
	 * oldest_client_version CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	OLDEST_CLIENT_VERSION		= "oldest_client_version";		//$NON-NLS-1$

	/**
	 * r_address_partitions INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_ADDRESS_PARTITIONS		= "r_address_partitions";		//$NON-NLS-1$

	/**
	 * r_dbms_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_DBMS_NAME					= "r_dbms_name";				//$NON-NLS-1$

	/**
	 * r_docbase_id INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_DOCBASE_ID				= "r_docbase_id";				//$NON-NLS-1$

	/**
	 * r_ending_partition INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_ENDING_PARTITION			= "r_ending_partition";		//$NON-NLS-1$

	/**
	 * r_federation_name CHAR(120)
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_FEDERATION_NAME			= "r_federation_name";			//$NON-NLS-1$

	/**
	 * r_starting_partition INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_STARTING_PARTITION		= "r_starting_partition";		//$NON-NLS-1$

	/**
	 * richmedia_enabled BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	RICHMEDIA_ENABLED			= "richmedia_enabled";			//$NON-NLS-1$

	/**
	 * security_mode CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	SECURITY_MODE				= "security_mode";				//$NON-NLS-1$

	/**
	 * dm_docbase_config
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_DOCBASE_CONFIG		= "dm_docbase_config";			//$NON-NLS-1$

	/**
	 * wf_package_control_enabled BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	WF_PACKAGE_CONTROL_ENABLED	= "wf_package_control_enabled"; //$NON-NLS-1$
}
