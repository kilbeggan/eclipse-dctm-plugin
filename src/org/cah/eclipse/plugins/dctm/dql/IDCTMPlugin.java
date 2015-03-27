/*-
 * $Log: IDCTMPlugin.java,v $
 * Revision 1.4  2005/12/04 22:14:39  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.3  2005/12/04 20:32:42  madcook
 * Version 3.0.0 work started.
 * 
 * Revision 1.2 2005/11/21 13:04:31 madcook 
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 * 
 * Revision 1.1 2005/04/01 11:06:49 harpechr 
 * Added create TBO resources and moved static final variables to interfaces.
 */

package org.cah.eclipse.plugins.dctm.dql;

import java.util.ResourceBundle;


/**
 * <H4>Container for static plugin strings and methods.</H4>
 * <DL>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>01-Apr-2005 12:47:58.</DD>
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
 * href="http://www.gnu.org/licenses/gpl.html">GNU General Public License</a>
 * for more details.
 * </p>
 * 
 * @author Christopher Harper account: HARPEC
 * @version 3.0.0
 * @since 2.0.0
 */
public interface IDCTMPlugin
{

	/**
	 * Browse for file resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_BROWSE_FOR_FILE						= "MSG_BROWSE_FOR_FILE";					//$NON-NLS-1$

	/**
	 * Browse for file tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_BROWSE_FOR_FILE_TOOLTIP				= "MSG_BROWSE_FOR_FILE_TOOLTIP";			//$NON-NLS-1$

	/**
	 * Cabinets resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_CABINETS							= "MSG_CABINETS";							//$NON-NLS-1$

	/**
	 * Cancel check out key.
	 * 
	 * @since 1.0.5
	 */

	public static final String	MSG_CANCEL_CHECKOUT						= "MSG_CANCEL_CHECKOUT";					//$NON-NLS-1$

	/**
	 * Cancel check out tool tip key.
	 * 
	 * @since 1.0.5
	 */

	public static final String	MSG_CANCEL_CHECKOUT_TOOLTIP				= "MSG_CANCEL_CHECKOUT_TOOLTIP";			//$NON-NLS-1$

	/**
	 * Checkin properties key.
	 * 
	 * @since 1.0.5
	 */
	public static final String	MSG_CHECKIN								= "MSG_CHECKIN";							//$NON-NLS-1$

	/**
	 * Check in as major version resource string key.
	 * 
	 * @since 1.0.5
	 */
	public static final String	MSG_CHECKIN_MAJOR						= "MSG_CHECKIN_MAJOR";						//$NON-NLS-1$

	/**
	 * Check in as major version tooltip resource string key.
	 * 
	 * @since 1.0.5
	 */
	public static final String	MSG_CHECKIN_MAJOR_TOOLTIP				= "MSG_CHECKIN_MAJOR_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Check in as minor version resource string key.
	 * 
	 * @since 1.0.5
	 */
	public static final String	MSG_CHECKIN_MINOR						= "MSG_CHECKIN_MINOR";						//$NON-NLS-1$

	/**
	 * Check in as minor version tooltip resource string key.
	 * 
	 * @since 1.0.5
	 */
	public static final String	MSG_CHECKIN_MINOR_TOOLTIP				= "MSG_CHECKIN_MINOR_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Checkin tooltip properties key.
	 * 
	 * @since 1.0.5
	 */
	public static final String	MSG_CHECKIN_TOOLTIP						= "MSG_CHECKIN_TOOLTIP";					//$NON-NLS-1$

	/**
	 * Checkout properties key.
	 * 
	 * @since 1.0.5
	 */
	public static final String	MSG_CHECKOUT							= "MSG_CHECKOUT";							//$NON-NLS-1$

	/**
	 * Checkout tooltip properties key.
	 * 
	 * @since 1.0.5
	 */
	public static final String	MSG_CHECKOUT_TOOLTIP					= "MSG_CHECKOUT_TOOLTIP";					//$NON-NLS-1$

	/**
	 * Class prefix key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_CLASS_PREFIX						= "MSG_CLASS_PREFIX";						//$NON-NLS-1$

	/**
	 * Connect resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_CONNECT								= "MSG_CONNECT";							//$NON-NLS-1$

	/**
	 * Connect tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_CONNECT_TOOLTIP						= "MSG_CONNECT_TOOLTIP";					//$NON-NLS-1$

	/**
	 * Copyright key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_COPYRIGHT_VALUE						= "MSG_COPYRIGHT_VALUE";					//$NON-NLS-1$

	/**
	 * Count content distribution key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_COUNT_CONTENT_DISTRIBUTION			= "MSG_COUNT_CONTENT_DISTRIBUTION";		//$NON-NLS-1$

	/**
	 * Count content distribution tooltip key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_COUNT_CONTENT_DISTRIBUTION_TOOLTIP	= "MSG_COUNT_CONTENT_DISTRIBUTION_TOOLTIP"; //$NON-NLS-1$

	/**
	 * Count indexed objects query key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_COUNT_INDEXED						= "MSG_COUNT_INDEXED";						//$NON-NLS-1$

	/**
	 * Count indexed objects query tooltip key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_COUNT_INDEXED_TOOLTIP				= "MSG_COUNT_INDEXED_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Create count type frequency DQL statement key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_COUNT_TYPE_FREQUENCY				= "MSG_COUNT_TYPE_FREQUENCY";				//$NON-NLS-1$

	/**
	 * Create count type frequency DQL statement tooltip key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_COUNT_TYPE_FREQUENCY_TOOLTIP		= "MSG_COUNT_TYPE_FREQUENCY_TOOLTIP";		//$NON-NLS-1$

	/**
	 * Count users documents key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_COUNT_USERS_DOCUMENTS				= "MSG_COUNT_USERS_DOCUMENTS";				//$NON-NLS-1$

	/**
	 * Count users document tooltip key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_COUNT_USERS_DOCUMENTS_TOOLTIP		= "MSG_COUNT_USERS_DOCUMENTS_TOOLTIP";		//$NON-NLS-1$

	/**
	 * Change storage key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_CREATE_CHANGE_STORAGE				= "MSG_CREATE_CHANGE_STORAGE";				//$NON-NLS-1$

	/**
	 * Change storage tooltip key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_CREATE_CHANGE_STORAGE_TOOLTIP		= "MSG_CREATE_CHANGE_STORAGE_TOOLTIP";		//$NON-NLS-1$

	/**
	 * Create classes key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_CREATE_CLASSES						= "MSG_CREATE_CLASSES";					//$NON-NLS-1$

	/**
	 * Create custom attribute methods key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_CREATE_CUSTOM_ATTRIBUTE_METHODS		= "MSG_CREATE_CUSTOM_ATTRIBUTE_METHODS";	//$NON-NLS-1$

	/**
	 * Create DQL buffer resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_CREATE_DQL							= "MSG_CREATE_DQL";						//$NON-NLS-1$

	/**
	 * Create DQL buffer tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_CREATE_DQL_TOOLTIP					= "MSG_CREATE_DQL_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Create group key.
	 * 
	 * @since 1.0.2
	 */
	public static final String	MSG_CREATE_GROUP						= "MSG_CREATE_GROUP";						//$NON-NLS-1$

	/**
	 * Create group tool tip key.
	 * 
	 * @since 1.0.2
	 */
	public static final String	MSG_CREATE_GROUP_TOOLTIP				= "MSG_CREATE_GROUP_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Create packages key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_CREATE_PACKAGES						= "MSG_CREATE_PACKAGES";					//$NON-NLS-1$

	/**
	 * Create relation key.
	 * 
	 * @since 1.0.3
	 */
	public static final String	MSG_CREATE_RELATION_TYPE				= "MSG_CREATE_RELATION_TYPE";				//$NON-NLS-1$

	/**
	 * Create relation tooltip key.
	 * 
	 * @since 1.0.3
	 */
	public static final String	MSG_CREATE_RELATION_TYPE_TOOLTIP		= "MSG_CREATE_RELATION_TYPE_TOOLTIP";		//$NON-NLS-1$

	/**
	 * Create new TBO module key. 02-Dec-2005 <code>MSG_CREATE_TBO_MODULE</code>.
	 * 
	 * @since 3.0.0
	 */
	public static final String	MSG_CREATE_TBO_MODULE					= "MSG_CREATE_TBO_MODULE";					//$NON-NLS-1$

	/**
	 * Create new TBO module key tooltip. 02-Dec-2005
	 * <code>MSG_CREATE_TBO_MODULE_TOOLTIP</code>
	 * 
	 * @since 3.0.0
	 */
	public static final String	MSG_CREATE_TBO_MODULE_TOOLTIP			= "MSG_CREATE_TBO_MODULE_TOOLTIP";			//$NON-NLS-1$

	/**
	 * Create TBO's resource key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_CREATE_TBOS							= "MSG_CREATE_TBOS";						//$NON-NLS-1$

	/**
	 * Create TBO's tooltip resource key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_CREATE_TBOS_TOOLTIP					= "MSG_CREATE_TBOS_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Create type DQL key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_CREATE_TYPE							= "MSG_CREATE_TYPE";						//$NON-NLS-1$

	/**
	 * Create type DQL tooltip key.
	 * 
	 * @since 1.0.2
	 */
	public static final String	MSG_CREATE_TYPE_TOOLTIP					= "MSG_CREATE_TYPE_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Create user key.
	 * 
	 * @since 1.0.2
	 */
	public static final String	MSG_CREATE_USER							= "MSG_CREATE_USER";						//$NON-NLS-1$

	/**
	 * Create user tool tip key.
	 * 
	 * @since 1.0.2
	 */
	public static final String	MSG_CREATE_USER_TOOLTIP					= "MSG_CREATE_USER_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Plugin resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_DCTM								= "MSG_DCTM";								//$NON-NLS-1$

	/**
	 * Debug the creation prosess key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_DEBUG_CREATION						= "MSG_DEBUG_CREATION";					//$NON-NLS-1$

	/**
	 * DFC shared folder key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_DFC_SHARED							= "MSG_DFC_SHARED";						//$NON-NLS-1$

	/**
	 * Disconnect resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_DISCONNECT							= "MSG_DISCONNECT";						//$NON-NLS-1$

	/**
	 * Disconnect tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_DISCONNECT_TOOLTIP					= "MSG_DISCONNECT_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Docbase resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_DOCBASE								= "MSG_DOCBASE";							//$NON-NLS-1$

	/**
	 * Docbase tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_DOMAIN								= "MSG_DOMAIN";							//$NON-NLS-1$

	/**
	 * Dump object resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_DUMP_OBJECT							= "MSG_DUMP_OBJECT";						//$NON-NLS-1$

	/**
	 * Dump object tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_DUMP_OBJECT_TOOLTIP					= "MSG_DUMP_OBJECT_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Edit connection resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_EDIT_CONNECTION						= "MSG_EDIT_CONNECTION";					//$NON-NLS-1$

	/**
	 * Edit connection tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_EDIT_CONNECTION_TOOLTIP				= "MSG_EDIT_CONNECTION_TOOLTIP";			//$NON-NLS-1$

	/**
	 * Execute resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_EXECUTE								= "MSG_EXECUTE";							//$NON-NLS-1$

	/**
	 * Execute as new resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_EXECUTE_NEW							= "MSG_EXECUTE_NEW";						//$NON-NLS-1$

	/**
	 * Execute as new tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_EXECUTE_NEW_TOOLTIP					= "MSG_EXECUTE_NEW_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Execute tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_EXECUTE_TOOLTIP						= "MSG_EXECUTE_TOOLTIP";					//$NON-NLS-1$

	/**
	 * Export results resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_EXPORT_RESULTS						= "MSG_EXPORT_RESULTS";					//$NON-NLS-1$

	/**
	 * Export results tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_EXPORT_RESULTS_TOOLTIP				= "MSG_EXPORT_RESULTS_TOOLTIP";			//$NON-NLS-1$

	/**
	 * Export selected resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_EXPORT_SELECTED						= "MSG_EXPORT_SELECTED";					//$NON-NLS-1$

	/**
	 * Export selected tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_EXPORT_SELECTED_TOOLTIP				= "MSG_EXPORT_SELECTED_TOOLTIP";			//$NON-NLS-1$

	/**
	 * Export success resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_EXPORT_SUCCESS						= "MSG_EXPORT_SUCCESS";					//$NON-NLS-1$

	/**
	 * Fetch selected resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_FETCH_SELECTED						= "MSG_FETCH_SELECTED";					//$NON-NLS-1$

	/**
	 * Fetch selected tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_FETCH_SELECTED_TOOLTIP				= "MSG_FETCH_SELECTED_TOOLTIP";			//$NON-NLS-1$

	/**
	 * Fiel separator instructions resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_FIELD_SEPARATOR_ISTRUCTIONS			= "MSG_FIELD_SEPARATOR_ISTRUCTIONS";		//$NON-NLS-1$

	/**
	 * Format statistics key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_FORMAT_STATISTICS					= "MSG_FORMAT_STATISTICS";					//$NON-NLS-1$

	/**
	 * Format statistics tooltip key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_FORMAT_STATISTICS_TOOLTIP			= "MSG_FORMAT_STATISTICS_TOOLTIP";			//$NON-NLS-1$

	/**
	 * Get content action resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_GET_CONTENT							= "MSG_GET_CONTENT";						//$NON-NLS-1$

	/**
	 * Get content action tooltip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_GET_CONTENT_TOOLTIP					= "MSG_GET_CONTENT_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Groups model name.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_GROUPS								= "MSG_GROUPS";							//$NON-NLS-1$

	/**
	 * Key for inbox string.
	 * 
	 * @since 1.0.5
	 */
	public static final String	MSG_INBOX								= "MSG_INBOX";								//$NON-NLS-1$

	/**
	 * Inbox messages key.
	 * 
	 * @since 1.0.5
	 */
	public static final String	MSG_INBOX_MESSAGES						= "MSG_INBOX_MESSAGES";					//$NON-NLS-1$

	/**
	 * Inbox messages tool tip key.
	 * 
	 * @since 1.0.5
	 */
	public static final String	MSG_INBOX_MESSAGES_TOOLTIP				= "MSG_INBOX_MESSAGES_TOOLTIP";			//$NON-NLS-1$

	/**
	 * Include column names resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_INCLUDE_COLUMN_NAMES				= "MSG_INCLUDE_COLUMN_NAMES";				//$NON-NLS-1$

	/**
	 * Full text indexed query key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_INDEXED								= "MSG_INDEXED";							//$NON-NLS-1$

	/**
	 * Full text indexed query tooltip key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_INDEXED_TOOLTIP						= "MSG_INDEXED_TOOLTIP";					//$NON-NLS-1$

	/**
	 * Inherit from DFC classes key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_INHERIT_FROM_DFC					= "MSG_INHERIT_FROM_DFC";					//$NON-NLS-1$

	/**
	 * Maximun result row count resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_MAX_RESULT_ROWS						= "MSG_MAX_RESULT_ROWS";					//$NON-NLS-1$

	/**
	 * New API editor resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_NEW_API								= "MSG_NEW_API";							//$NON-NLS-1$

	/**
	 * New API editor tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_NEW_API_TOOLTIP						= "MSG_NEW_API_TOOLTIP";					//$NON-NLS-1$

	/**
	 * New connection resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_NEW_CONNECTION						= "MSG_NEW_CONNECTION";					//$NON-NLS-1$

	/**
	 * New connection tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_NEW_CONNECTION_TOOLTIP				= "MSG_NEW_CONNECTION_TOOLTIP";			//$NON-NLS-1$

	/**
	 * New DQL editor resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_NEW_DQL								= "MSG_NEW_DQL";							//$NON-NLS-1$

	/**
	 * New DQL editor tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_NEW_DQL_TOOLTIP						= "MSG_NEW_DQL_TOOLTIP";					//$NON-NLS-1$

	/**
	 * Root package name key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_PACKAGE_NAME						= "MSG_PACKAGE_NAME";						//$NON-NLS-1$

	/**
	 * Password resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_PASSWORD							= "MSG_PASSWORD";							//$NON-NLS-1$

	/**
	 * Refresh resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_REFRESH								= "MSG_REFRESH";							//$NON-NLS-1$

	/**
	 * Refresh Docbases resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_REFRESH_DOCBASES					= "MSG_REFRESH_DOCBASES";					//$NON-NLS-1$

	/**
	 * Refresh tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_REFRESH_TOOLTIP						= "MSG_REFRESH_TOOLTIP";					//$NON-NLS-1$

	/**
	 * Remove connection resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_REMOVE_CONNECTION					= "MSG_REMOVE_CONNECTION";					//$NON-NLS-1$

	/**
	 * Remove connection tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_REMOVE_CONNECTION_TOOLTIP			= "MSG_REMOVE_CONNECTION_TOOLTIP";			//$NON-NLS-1$

	/**
	 * Return all query rows key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_RETURN_ALL_ROWS						= "MSG_RETURN_ALL_ROWS";					//$NON-NLS-1$

	/**
	 * Return all query rows tooltip key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_RETURN_ALL_ROWS_TOOLTIP				= "MSG_RETURN_ALL_ROWS_TOOLTIP";			//$NON-NLS-1$

	/**
	 * Return all version resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_RETURN_VERSIONS						= "MSG_RETURN_VERSIONS";					//$NON-NLS-1$

	/**
	 * Return all version tooltip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_RETURN_VERSIONS_TOOLTIP				= "MSG_RETURN_VERSIONS_TOOLTIP";			//$NON-NLS-1$

	/**
	 * Select virtual document content resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_SELECT_DOCUMENT_CONTENT				= "MSG_SELECT_DOCUMENT_CONTENT";			//$NON-NLS-1$

	/**
	 * Select virtual document content tooltip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_SELECT_DOCUMENT_CONTENT_TOOLTIP		= "MSG_SELECT_DOCUMENT_CONTENT_TOOLTIP";	//$NON-NLS-1$

	/**
	 * Return folder content resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_SELECT_FOLDER_CONTENT				= "MSG_SELECT_FOLDER_CONTENT";				//$NON-NLS-1$

	/**
	 * Select folder content tooltip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_SELECT_FOLDER_CONTENT_TOOLTIP		= "MSG_SELECT_FOLDER_CONTENT_TOOLTIP";		//$NON-NLS-1$

	/**
	 * Select grups action name key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_SELECT_GROUPS						= "MSG_SELECT_GROUPS";						//$NON-NLS-1$

	/**
	 * Select groups tool tip key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_SELECT_GROUPS_TOOLTIP				= "MSG_SELECT_GROUPS_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Select relation type key.
	 * 
	 * @since 1.0.3
	 */
	public static final String	MSG_SELECT_RELATION_TYPE				= "MSG_SELECT_RELATION_TYPE";				//$NON-NLS-1$

	/**
	 * Select relation type tooltip key.
	 * 
	 * @since 1.0.3
	 */
	public static final String	MSG_SELECT_RELATION_TYPE_TOOLTIP		= "MSG_SELECT_RELATION_TYPE_TOOLTIP";		//$NON-NLS-1$

	/**
	 * Select table attributes resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_SELECT_TABLE_ATTRIBUTES				= "MSG_SELECT_TABLE_ATTRIBUTES";			//$NON-NLS-1$

	/**
	 * Selcect table attributes tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_SELECT_TABLE_ATTRIBUTES_TOOLTIP		= "MSG_SELECT_TABLE_ATTRIBUTES_TOOLTIP";	//$NON-NLS-1$

	/**
	 * Select type attributes resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_SELECT_TYPE_ATTRIBUTES				= "MSG_SELECT_TYPE_ATTRIBUTES";			//$NON-NLS-1$

	/**
	 * Select type attributes tool tip resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_SELECT_TYPE_ATTRIBUTES_TOOLTIP		= "MSG_SELECT_TYPE_ATTRIBUTES_TOOLTIP";	//$NON-NLS-1$

	/**
	 * Select users action label key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_SELECT_USERS						= "MSG_SELECT_USERS";						//$NON-NLS-1$

	/**
	 * Select users tool tip key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_SELECT_USERS_TOOLTIP				= "MSG_SELECT_USERS_TOOLTIP";				//$NON-NLS-1$

	/**
	 * Javadoc since value key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_SINCE_VALUE							= "MSG_SINCE_VALUE";						//$NON-NLS-1$

	/**
	 * Tables resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_TABLES								= "MSG_TABLES";							//$NON-NLS-1$

	/**
	 * Target folder key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_TARGET_FOLDER						= "MSG_TARGET_FOLDER";						//$NON-NLS-1$

	/**
	 * Types resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_TYPES								= "MSG_TYPES";								//$NON-NLS-1$

	/**
	 * User name resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_USER_NAME							= "MSG_USER_NAME";							//$NON-NLS-1$

	/**
	 * Use creation threads key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_USER_THREADS						= "MSG_USER_THREADS";						//$NON-NLS-1$

	/**
	 * Users resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	MSG_USERS								= "MSG_USERS";								//$NON-NLS-1$

	/**
	 * Javadoc version value key.
	 * 
	 * @since 2.0.0
	 */
	public static final String	MSG_VERSION_VALUE						= "MSG_VERSION_VALUE";						//$NON-NLS-1$

	/**
	 * Waiting for fulltext indexing key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_WAITING_FOR_INDEXING				= "MSG_WAITING_FOR_INDEXING";				//$NON-NLS-1$

	/**
	 * Waiting for fulltext indexing tooltip key.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_WAITING_FOR_INDEXING_TOOLTIP		= "MSG_WAITING_FOR_INDEXING_TOOLTIP";		//$NON-NLS-1$

	/**
	 * Systems new line characters resource key.
	 * 
	 * @since 1.0
	 */
	public static final String	NEWLINE									= System
																			.getProperty("line.separator");		//$NON-NLS-1$

	/**
	 * Returns the plugin's resource bundle.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 10:34:10 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the bundle.
	 */
	public abstract ResourceBundle getResourceBundle();

}
