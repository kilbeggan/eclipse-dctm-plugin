/*-
 * $Log: IServerConfig.java,v $
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
 * Revision 1.4  2005/02/09 14:01:41  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:58  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:58:13  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:12  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.sysobject.serverconfig;

import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;


/**
 * <H4>Server configuration object spesific attributes.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>May 21, 2004 10:51:33 AM.</DD>
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
public abstract interface IServerConfig
										extends
											ISysObject
{

	/**
	 * a_silent_login BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	A_SILENT_LOGIN				= "a_silent_login";				//$NON-NLS-1$

	/**
	 * agent_launcher CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	AGENT_LAUNCHER				= "agent_launcher";				//$NON-NLS-1$

	/**
	 * alias_set_id ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALIAS_SET_ID				= "alias_set_id";					//$NON-NLS-1$

	/**
	 * app_server_name CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	APP_SERVER_NAME				= "app_server_name";				//$NON-NLS-1$

	/**
	 * app_server_uri CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	APP_SERVER_URI				= "app_server_uri";				//$NON-NLS-1$

	/**
	 * assume_user_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	ASSUME_USER_LOCATION		= "assume_user_location";			//$NON-NLS-1$

	/**
	 * auth_plugin_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	AUTH_PLUGIN_LOCATION		= "auth_plugin_location";			//$NON-NLS-1$

	/**
	 * cached_types CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	CACHED_TYPES				= "cached_types";					//$NON-NLS-1$

	/**
	 * change_password_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	CHANGE_PASSWORD_LOCATION	= "change_password_location";		//$NON-NLS-1$

	/**
	 * checkpoint_interval INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	CHECKPOINT_INTERVAL			= "checkpoint_interval";			//$NON-NLS-1$

	/**
	 * client_cache_size INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	CLIENT_CACHE_SIZE			= "client_cache_size";				//$NON-NLS-1$

	/**
	 * common_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	COMMON_LOCATION				= "common_location";				//$NON-NLS-1$

	/**
	 * compound_integrity BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	COMPOUND_INTEGRITY			= "compound_integrity";			//$NON-NLS-1$

	/**
	 * concurrent_sessions INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	CONCURRENT_SESSIONS			= "concurrent_sessions";			//$NON-NLS-1$

	/**
	 * dba_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DBA_LOCATION				= "dba_location";					//$NON-NLS-1$

	/**
	 * default_acl INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_ACL					= "default_acl";					//$NON-NLS-1$

	/**
	 * default_client_codepage CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_CLIENT_CODEPAGE		= "default_client_codepage";		//$NON-NLS-1$

	/**
	 * events_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	EVENTS_LOCATION				= "events_location";				//$NON-NLS-1$

	/**
	 * far_stores CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	FAR_STORES					= "far_stores";					//$NON-NLS-1$

	/**
	 * keep_entry_interval INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	KEEP_ENTRY_INTERVAL			= "keep_entry_interval";			//$NON-NLS-1$

	/**
	 * ldap_config_id ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	LDAP_CONFIG_ID				= "ldap_config_id";				//$NON-NLS-1$

	/**
	 * locale_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	LOCALE_NAME					= "locale_name";					//$NON-NLS-1$

	/**
	 * log_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	LOG_LOCATION				= "log_location";					//$NON-NLS-1$

	/**
	 * login_ticket_timeout INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	LOGIN_TICKET_TIMEOUT		= "login_ticket_timeout";			//$NON-NLS-1$

	/**
	 * mail_method CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	MAIL_METHOD					= "mail_method";					//$NON-NLS-1$

	/**
	 * netstor_bin_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	NETSTOR_BIN_LOCATION		= "netstor_bin_location";			//$NON-NLS-1$

	/**
	 * nfs_enabled BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	NFS_ENABLED					= "nfs_enabled";					//$NON-NLS-1$

	/**
	 * nls_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	NLS_LOCATION				= "nls_location";					//$NON-NLS-1$

	/**
	 * operator_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	OPERATOR_NAME				= "operator_name";					//$NON-NLS-1$

	/**
	 * projection_enable BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	PROJECTION_ENABLE			= "projection_enable";				//$NON-NLS-1$

	/**
	 * projection_notes CHAR(80) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	PROJECTION_NOTES			= "projection_notes";				//$NON-NLS-1$

	/**
	 * projection_ports INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	PROJECTION_PORTS			= "projection_ports";				//$NON-NLS-1$

	/**
	 * projection_proxval INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	PROJECTION_PROXVAL			= "projection_proxval";			//$NON-NLS-1$

	/**
	 * projection_targets CHAR(80) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	PROJECTION_TARGETS			= "projection_targets";			//$NON-NLS-1$

	/**
	 * r_castore_enabled INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_CASTORE_ENABLED			= "r_castore_enabled";				//$NON-NLS-1$

	/**
	 * r_host_name CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HOST_NAME					= "r_host_name";					//$NON-NLS-1$

	/**
	 * r_install_domain CHAR(16)
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_INSTALL_DOMAIN			= "r_install_domain";				//$NON-NLS-1$

	/**
	 * r_install_owner CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_INSTALL_OWNER				= "r_install_owner";				//$NON-NLS-1$

	/**
	 * r_process_id INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_PROCESS_ID				= "r_process_id";					//$NON-NLS-1$

	/**
	 * r_server_version CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_SERVER_VERSION			= "r_server_version";				//$NON-NLS-1$

	/**
	 * r_trusted_mode INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_TRUSTED_MODE				= "r_trusted_mode";				//$NON-NLS-1$

	/**
	 * rend_backing_store CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	REND_BACKING_STORE			= "rend_backing_store";			//$NON-NLS-1$

	/**
	 * rightsite_image CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	RIGHTSITE_IMAGE				= "rightsite_image";				//$NON-NLS-1$

	/**
	 * secure_connect_mode CHAR(16)
	 * 
	 * @since 1.0.3
	 */
	public static final String	SECURE_CONNECT_MODE			= "secure_connect_mode";			//$NON-NLS-1$

	/**
	 * secure_writer_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	SECURE_WRITER_LOCATION		= "secure_writer_location";		//$NON-NLS-1$

	/**
	 * server_cache_size INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	SERVER_CACHE_SIZE			= "server_cache_size";				//$NON-NLS-1$

	/**
	 * server_os_codepage CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	SERVER_OS_CODEPAGE			= "server_os_codepage";			//$NON-NLS-1$

	/**
	 * sibling_checkpoint_interval INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	SIBLING_CHECKPOINT_INTERVAL	= "sibling_checkpoint_interval";	//$NON-NLS-1$

	/**
	 * sibling_export_enabled BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	SIBLING_EXPORT_ENABLED		= "sibling_export_enabled";		//$NON-NLS-1$

	/**
	 * signature_chk_loc CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	SIGNATURE_CHK_LOC			= "signature_chk_loc";				//$NON-NLS-1$

	/**
	 * smtp_server CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	SMTP_SERVER					= "smtp_server";					//$NON-NLS-1$

	/**
	 * stage_destroyer_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	STAGE_DESTROYER_LOCATION	= "stage_destroyer_location";		//$NON-NLS-1$

	/**
	 * system_converter_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	SYSTEM_CONVERTER_LOCATION	= "system_converter_location";		//$NON-NLS-1$

	/**
	 * temp_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	TEMP_LOCATION				= "temp_location";					//$NON-NLS-1$

	/**
	 * turbo_backing_store CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	TURBO_BACKING_STORE			= "turbo_backing_store";			//$NON-NLS-1$

	/**
	 * dm_server_config
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_SERVER_CONFIG		= "dm_server_config";				//$NON-NLS-1$

	/**
	 * user_converter_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_CONVERTER_LOCATION		= "user_converter_location";		//$NON-NLS-1$

	/**
	 * user_validation_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_VALIDATION_LOCATION	= "user_validation_location";		//$NON-NLS-1$

	/**
	 * verity_location CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	VERITY_LOCATION				= "verity_location";				//$NON-NLS-1$

	/**
	 * web_server_loc CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	WEB_SERVER_LOC				= "web_server_loc";				//$NON-NLS-1$

	/**
	 * web_server_port INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	WEB_SERVER_PORT				= "web_server_port";				//$NON-NLS-1$

	/**
	 * wf_agent_worker_threads INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	WF_AGENT_WORKER_THREADS		= "wf_agent_worker_threads";		//$NON-NLS-1$

	/**
	 * wf_sleep_interval INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	WF_SLEEP_INTERVAL			= "wf_sleep_interval";				//$NON-NLS-1$

}
