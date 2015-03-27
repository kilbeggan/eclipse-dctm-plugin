/*-
 * $Log: IImageCache.java,v $
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

/**
 * <H4>Container for static image cache strings.</H4>
 * <DL>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>01-Apr-2005 12:47:17.</DD>
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
public interface IImageCache
{

	/**
	 * Icon for a BOF aspect module. 21-Nov-2005.
	 * 
	 * @since 3.0.0
	 */
	public static final String	ASPECT_ICON							= "aspect.gif";						//$NON-NLS-1$

	/**
	 * Cabinet icon.
	 * 
	 * @since 1.0.1
	 */
	public static final String	CABINET_ICON						= "cabinet.gif";						//$NON-NLS-1$

	/**
	 * Cabinets container icon.
	 * 
	 * @since 1.0.1
	 */
	public static final String	CABINETS_ICON						= "cabinets.gif";						//$NON-NLS-1$

	/**
	 * Cancel check out icon.
	 * 
	 * @since 1.0.5
	 */
	public static final String	CANCEL_CHECKOUT_ICON				= "cancel_checkout.gif";				//$NON-NLS-1$

	/**
	 * Content srorage icon.
	 * 
	 * @since 1.0.4
	 */
	public static final String	CHANGE_STORAGE_ICON					= "store.gif";							//$NON-NLS-1$

	/**
	 * Check in icon.
	 * 
	 * @since 1.0.5
	 */
	public static final String	CHECKIN_ICON						= "checkin.gif";						//$NON-NLS-1$

	/**
	 * Check in major version icon.
	 * 
	 * @since 1.0.5
	 */
	public static final String	CHECKIN_MAJOR_ICON					= "checkin_major.gif";					//$NON-NLS-1$

	/**
	 * Check in minor version icon.
	 * 
	 * @since 1.0.5
	 */
	public static final String	CHECKIN_MINOR_ICON					= "checkin_minor.gif";					//$NON-NLS-1$

	/**
	 * Check out icon.
	 * 
	 * @since 1.0.5
	 */
	public static final String	CHECKOUT_ICON						= "checkout.gif";						//$NON-NLS-1$

	/**
	 * Connect icon.
	 * 
	 * @since 2.0.0
	 */
	public static final String	CONNECT_ICON						= "connect.gif";						//$NON-NLS-1$

	/**
	 * Connected icon.
	 * 
	 * @since 1.0
	 */
	public static final String	CONNECTED_ICON						= "connected_icon.gif";				//$NON-NLS-1$

	/**
	 * Create API statements icon.
	 * 
	 * @since 1.0
	 */
	public static final String	CREATE_API_ICON						= "create_api.gif";					//$NON-NLS-1$

	/**
	 * Content distribution DQL create icon.
	 * 
	 * @since 1.0.4
	 */
	public static final String	CREATE_CONTENT_DISTRIBUTION_ICON	= "create_content_distribution.gif";	//$NON-NLS-1$

	/**
	 * Create count query icon.
	 * 
	 * @since 1.0.4
	 */
	public static final String	CREATE_COUNT_ICON					= "create_count.gif";					//$NON-NLS-1$

	/**
	 * Create DQL statements icon.
	 * 
	 * @since 1.0
	 */
	public static final String	CREATE_DQL_ICON						= "create_dql.gif";					//$NON-NLS-1$

	/**
	 * Create waiting for full text indexing DQL statement icon.
	 * 
	 * @since 1.0.4
	 */
	public static final String	CREATE_FULLTEXT_ICON				= "fulltext.gif";						//$NON-NLS-1$

	/**
	 * Create grop icon.
	 * 
	 * @since 1.0.2
	 */
	public static final String	CREATE_GROUP						= "create_group.gif";					//$NON-NLS-1$

	/**
	 * Create group icon.
	 * 
	 * @since 1.0.2
	 */
	public static final String	CREATE_GROUP_ICON					= "create_group.gif";					//$NON-NLS-1$

	/**
	 * Create relation type icon.
	 * 
	 * @since 1.0.3
	 */
	public static final String	CREATE_RELATION_ICON				= "create_relation.gif";				//$NON-NLS-1$

	/**
	 * Create type icon.
	 * 
	 * @since 1.0.2
	 */
	public static final String	CREATE_TYPE_ICON					= "create_type.gif";					//$NON-NLS-1$

	/**
	 * Create user icon.
	 * 
	 * @since 1.0.2
	 */
	public static final String	CREATE_USER							= "create_user.gif";					//$NON-NLS-1$

	/**
	 * Create user icon.
	 * 
	 * @since 1.0.2
	 */
	public static final String	CREATE_USER_ICON					= "create_user.gif";					//$NON-NLS-1$

	/**
	 * Disconnected connection icon.
	 * 
	 * @since 1.0
	 */
	public static final String	DISCONNECTED_ICON					= "disconnected_icon.gif";				//$NON-NLS-1$

	/**
	 * Docbase icon.
	 * 
	 * @since 1.0
	 */
	public static final String	DOCBASE_ICON						= "documentum.gif";					//$NON-NLS-1$

	/**
	 * Dump object icon.
	 * 
	 * @since 1.0
	 */
	public static final String	DUMP_ICON							= "dump.gif";							//$NON-NLS-1$

	/**
	 * Execute icon.
	 * 
	 * @since 1.0
	 */
	public static final String	EXECUTE_ICON						= "execute_icon.gif";					//$NON-NLS-1$

	/**
	 * BOF / TBO generaion icon.
	 * 
	 * @since 2.0.0
	 */

	public static final String	EXPORT_BOF_ICON						= "export_bof.gif";					//$NON-NLS-1$

	/**
	 * Export results incon.
	 * 
	 * @since 1.0
	 */
	public static final String	EXPORT_ICON							= "export.gif";						//$NON-NLS-1$

	/**
	 * Fetch icon.
	 * 
	 * @since 1.0
	 */
	public static final String	FETCH_ICON							= "fetch.gif";							//$NON-NLS-1$

	/**
	 * Folder icon.
	 * 
	 * @since 1.0.1
	 */
	public static final String	FOLDER_ICON							= "folder.gif";						//$NON-NLS-1$

	/**
	 * Format icon.
	 * 
	 * @since 1.0.4
	 */
	public static final String	FORMAT_ICON							= "format.gif";						//$NON-NLS-1$

	/**
	 * Generate TBO files icon.
	 * 
	 * @since 2.0.0
	 */
	public static final String	GENERATE_TBO_ICON					= "generate_tbo.gif";					//$NON-NLS-1$

	/**
	 * Get content icon.
	 * 
	 * @since 1.0
	 */
	public static final String	GET_CONTENT_ICON					= "getcontent.gif";					//$NON-NLS-1$

	/**
	 * Group icom.
	 * 
	 * @since 1.0.2
	 */
	public static final String	GROUP_ICON							= "group.gif";							//$NON-NLS-1$

	/**
	 * Groups container icon.
	 * 
	 * @since 1.0.2
	 */
	public static final String	GROUPS_ICON							= "groups.gif";						//$NON-NLS-1$

	/**
	 * The Docbase inbox icon.
	 * 
	 * @since 1.0.5
	 */
	public static final String	INBOX_ICON							= "inbox.gif";							//$NON-NLS-1$

	/**
	 * Icon for module dependencies 03-Dec-2005.
	 * 
	 * @since 3.0.0
	 */
	public static final String	MODULE_DEPENDENCIES					= "module_dependencies.gif";			//$NON-NLS-1$

	/**
	 * Icon for module description 03-Dec-2005.
	 * 
	 * @since 3.0.0
	 */
	public static final String	MODULE_DESCRIPTION					= "module_description.gif";			//$NON-NLS-1$

	/**
	 * Icon for module general info 03-Dec-2005.
	 * 
	 * @since 3.0.0
	 */
	public static final String	MODULE_GENERAL						= "module_general.gif";				//$NON-NLS-1$

	/**
	 * Icon for a BOF module. 21-Nov-2005.
	 * 
	 * @since 3.0.0
	 */
	public static final String	MODULE_ICON							= "module.gif";						//$NON-NLS-1$

	/**
	 * Icon for module impementation 03-Dec-2005.
	 * 
	 * @since 3.0.0
	 */
	public static final String	MODULE_IMPLEMENTATION				= "module_implementation.gif";			//$NON-NLS-1$

	/**
	 * Icon for module runtime environment. 03-Dec-2005.
	 * 
	 * @since 3.0.0
	 */
	public static final String	MODULE_RUNTIME_ENVIRONMENT			= "module_runtime_environment.gif";	//$NON-NLS-1$

	/**
	 * New API editor icon.
	 * 
	 * @since 1.0
	 */
	public static final String	NEW_API_ICON						= "api.gif";							//$NON-NLS-1$

	/**
	 * New DQL editor icon.
	 * 
	 * @since 1.0
	 */
	public static final String	NEW_DQL_ICON						= "dql.gif";							//$NON-NLS-1$

	/**
	 * Refresh icon.
	 * 
	 * @since 1.0
	 */
	public static final String	REFRESH_ICON						= "refresh.gif";						//$NON-NLS-1$

	/**
	 * Relation icon.
	 * 
	 * @since 1.0.3
	 */
	public static final String	RELATION_ICON						= "relation.gif";						//$NON-NLS-1$

	/**
	 * Remove connection icon.
	 * 
	 * @since 1.0
	 */
	public static final String	REMOVE_ICON							= "remove_icon.gif";					//$NON-NLS-1$

	/**
	 * Return all query rows icon.
	 * 
	 * @since 2.0.0
	 */
	public static final String	RETURN_ALL_ROWS_ICON				= "return_all_rows.gif";				//$NON-NLS-1$

	/**
	 * Role icon.
	 * 
	 * @since 1.0.2
	 */
	public static final String	ROLE_ICON							= "role.gif";							//$NON-NLS-1$

	/**
	 * Icon for a BOF SBO module. 21-Nov-2005.
	 * 
	 * @since 3.0.0
	 */
	public static final String	SBO_ICON							= "sbo.gif";							//$NON-NLS-1$

	/**
	 * Selected connection icon.
	 * 
	 * @since 1.0
	 */
	public static final String	SELECTED_CONNECTED_ICON				= "selected_connected_icon.gif";		//$NON-NLS-1$

	/**
	 * Table icon.
	 * 
	 * @since 1.0
	 */
	public static final String	TABLE_ICON							= "table_icon.gif";					//$NON-NLS-1$

	/**
	 * Tables icon.
	 * 
	 * @since 1.0
	 */
	public static final String	TABLES_ICON							= "tables.gif";						//$NON-NLS-1$

	/**
	 * Icon for a BOF TBO module. 21-Nov-2005.
	 * 
	 * @since 3.0.0
	 */
	public static final String	TBO_ICON							= "tbo.gif";							//$NON-NLS-1$

	/**
	 * Type icon.
	 * 
	 * @since 1.0
	 */
	public static final String	TYPE_ICON							= "type.gif";							//$NON-NLS-1$

	/**
	 * Types icon
	 * 
	 * @since 1.0
	 */
	public static final String	TYPES_ICON							= "types.gif";							//$NON-NLS-1$

	/**
	 * Users icon.
	 * 
	 * @since 1.0.2
	 */
	public static final String	USERS_ICON							= "users.gif";							//$NON-NLS-1$

	/**
	 * Return versiona action icon.
	 * 
	 * @since 1.0.1
	 */
	public static final String	VERSIONS_ICON						= "versions.gif";						//$NON-NLS-1$

	/**
	 * Virtual Docuement icon.
	 * 
	 * @since 1.0.1
	 */
	public static final String	VIRTUAL_DOCUMENT_ICON				= "virtual_document.gif";				//$NON-NLS-1$
}
