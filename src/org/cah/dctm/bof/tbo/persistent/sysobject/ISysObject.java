/*-
 * $Log: ISysObject.java,v $
 * Revision 1.10  2005/12/04 22:17:22  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.9  2005/12/04 20:24:54  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.8  2005/11/22 13:27:07  madcook
 * Eclipse 3.2 compiler check warnings modified.
 * 
 * Revision 1.7 2005/11/21 13:04:33 madcook 
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 * 
 * Revision 1.6 2005/03/25 09:26:05 harpechr 
 * Version 2.0.0 code that has implemented the new java 1.5 features. 
 * 
 * Revision 1.5 2005/02/09 14:01:46 harpechr 
 * Version 1.0.5 work started. 
 * 
 * Revision 1.4 2005/01/24 12:35:15 harpechr 
 * Version 1.0.4 work started. 
 * 
 * Revision 1.3 2005/01/11 13:47:26 harpechr 
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof). 
 * 
 * Revision 1.2 2005/01/10 11:58:15 harpechr 
 * Changed version numbers and since information to 1.0.3. 
 * 
 * Revision 1.1 2005/01/10 10:41:15 harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 */

package org.cah.dctm.bof.tbo.persistent.sysobject;

import java.util.Arrays;
import java.util.Vector;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;

import com.documentum.fc.client.IDfSysObject;


/**
 * <h4>Attributes and type of the dm_sysobject type.</h4>
 * <DL>
 * <DT><B>Copyright: </B>
 * <DD>2004 (C) Christopher Harper</DD>
 * </DT>
 * <DT><B>Created: </B>
 * <DD>Apr 7, 2004 5:43:15 PM</DD>
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
public abstract interface ISysObject
									extends
										IPersistentObject,
										IDfSysObject
{

	/**
	 * Currently unused 'a_application_type' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_APPLICATION_TYPE				= "a_application_type";										//$NON-NLS-1$

	/**
	 * Used internally 'a_archive' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_ARCHIVE						= "a_archive";													//$NON-NLS-1$

	/**
	 * Used internally to manage the application 'a_category' CHAR(64).
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_CATEGORY						= "a_category";												//$NON-NLS-1$

	/**
	 * Used by Virtual Document Manager to determine if the object can be
	 * structurally changed through VDM. An empty string indicates that the
	 * object can be changed 'a_compound_architecture' CHAR(16).
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_COMPOUND_ARCHITECTURE			= "a_compound_architecture";									//$NON-NLS-1$

	/**
	 * File format of the object's content 'a_content_type' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_CONTENT_TYPE					= "a_content_type";											//$NON-NLS-1$

	/**
	 * 'a_controlling_app' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_CONTROLLING_APP				= "a_controlling_app";											//$NON-NLS-1$

	/**
	 * Contains status information provided by the application or program run by
	 * the job 'a_current_status'.
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_CURRENT_STATUS				= "a_current_status";											//$NON-NLS-1$

	/**
	 * The date on which the document can be published to the Web site. The
	 * value at a particular index position applies to the Web site named at the
	 * corresponding index position in a_effective_label 'a_effective_date' TIME
	 * REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_EFFECTIVE_DATE				= "a_effective_date";											//$NON-NLS-1$

	/**
	 * Indicates whether a pending expiration notice was sent to the
	 * documentâ€™s owner. The value at a particular index position applies
	 * to the Web site named at the corresponding index position in
	 * a_effective_label 'a_effective_flag' CHAR(8) REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_EFFECTIVE_FLAG				= "a_effective_flag";											//$NON-NLS-1$

	/**
	 * User-defined, typically a symbolic label. The WebCache export operation
	 * examines the values in the a_effective_date and a_expiration_date
	 * attributes of all documents whose a_effective_label attribute value
	 * matches the webc config effective_label attribute. If unspecified,
	 * effective labels are not enforced 'a_effective_label' CHAR(32) REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_EFFECTIVE_LABEL				= "a_effective_label";											//$NON-NLS-1$

	/**
	 * The date at which the document is to be removed from the Web site. The
	 * value at a particular index position applies to the Web site named at the
	 * corresponding index position in a_effective_label 'a_expiration_date'
	 * TIME REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_EXPIRATION_DATE				= "a_expiration_date";											//$NON-NLS-1$

	/**
	 * 'a_extended_properties' CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_EXTENDED_PROPERTIES			= "a_extended_properties";										//$NON-NLS-1$

	/**
	 * Indicates whether the document is marked for full-text indexing
	 * 'a_full_text' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_FULL_TEXT						= "a_full_text";												//$NON-NLS-1$

	/**
	 * Indicates if this object is visible to end users 'a_is_hidden' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_IS_HIDDEN						= "a_is_hidden";												//$NON-NLS-1$

	/**
	 * 'a_is_signed' BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_IS_SIGNED						= "a_is_signed";												//$NON-NLS-1$

	/**
	 * a_is_template BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_IS_TEMPLATE					= "a_is_template";												//$NON-NLS-1$

	/**
	 * a_last_review_date TIME
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_LAST_REVIEW_DATE				= "a_last_review_date";										//$NON-NLS-1$

	/**
	 * Used internally 'a_link_resolved' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_LINK_RESOLVED					= "a_link_resolved";											//$NON-NLS-1$

	/**
	 * Lists the object's renditions that are to be exported to the WebCache
	 * repository. The system will export these renditions plus those defined in
	 * the source_formats attribute of the webc config object. This is an
	 * optional attribute. If unspecified, only renditions defined in the webc
	 * config object are published 'a_publish_formats' CHAR(32) REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_PUBLISH_FORMATS				= "a_publish_formats";											//$NON-NLS-1$

	/**
	 * Used internally 'a_retention_date' TIME.
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_RETENTION_DATE				= "a_retention_date";											//$NON-NLS-1$

	/**
	 * Reserved for use by Documentum products 'a_special_app' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_SPECIAL_APP					= "a_special_app";												//$NON-NLS-1$

	/**
	 * Not currently used 'a_status' CHAR(16).
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_STATUS						= "a_status";													//$NON-NLS-1$

	/**
	 * Specifies the default storage area for content files associated with the
	 * object 'a_storage_type' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			A_STORAGE_TYPE					= "a_storage_type";											//$NON-NLS-1$

	/**
	 * Identifies the domain of the ACL associated with the object. The value
	 * will be either the user who created the ACL or, for system-level ACLs,
	 * the name of the Docbase owner 'acl_domain' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			ACL_DOMAIN						= "acl_domain";												//$NON-NLS-1$

	/**
	 * The object name of the associated ACL 'acl_name' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			ACL_NAME						= "acl_name";													//$NON-NLS-1$

	/**
	 * List of attributes that should not be copied.
	 * 
	 * @since 1.0.3
	 */
	public static final Vector<String>	ATTRIBUTES_WE_CANT_COPY			= new Vector<String>(
																			Arrays
																				.asList(ISysObject.ATTRIBUTES_WE_CANT_COPY_ARRAY));

	/**
	 * Array of attributes that should not be updated throught the standard api
	 * (a_compound_architecture, a_content_type, a_link_resolvede,
	 * a_storage_type, group_name, group_permit, i_antecedent_id, i_branch_cnt,
	 * i_cabinet_id, i_chronicle_id, i_contents_id, i_direct_dsc, i_folder_id,
	 * i_has_folder, i_is_deleted, i_is_reference, i_is_replica, i_latest_flag,
	 * i_reference_cnt, i_vstamp, owner_permit, r_access_date, r_alias_set_id,
	 * r_assembled_from_id, r_component_label, r_composite_id, r_content_size,
	 * r_creation_date, r_creator_name, r_current_state, r_frozen_flag,
	 * r_frzn_assembly_cnt, r_has_events, r_has_frzn_assembly, r_immutable_flag,
	 * r_is_public, r_is_virtual_doc, r_link_cnt, r_link_high_cnt, r_lock_date,
	 * r_lock_machine, r_lock_owner, r_modifier, r_modify_date, r_object_id,
	 * r_object_type, r_order_no, r_page_cnt, r_policy_id, r_resumen_state,
	 * r_version_label, world_permit, acl_domain, acl_name a_application_type,
	 * a_status, r_composite_label, owner_name).
	 * 
	 * @since 1.0.3
	 */
	public static final String[]		ATTRIBUTES_WE_CANT_COPY_ARRAY	= new String[] {
		ISysObject.A_COMPOUND_ARCHITECTURE, ISysObject.A_CONTENT_TYPE,
		ISysObject.A_LINK_RESOLVED, ISysObject.A_STORAGE_TYPE,
		ISysObject.GROUP_NAME, ISysObject.GROUP_PERMIT,
		ISysObject.I_ANTECEDENT_ID, ISysObject.I_BRANCH_CNT,
		ISysObject.I_CABINET_ID, ISysObject.I_CHRONICLE_ID,
		ISysObject.I_CONTENTS_ID, ISysObject.I_DIRECT_DSC,
		ISysObject.I_FOLDER_ID, ISysObject.I_HAS_FOLDER,
		ISysObject.I_IS_DELETED, ISysObject.I_IS_REFERENCE,
		IPersistentObject.I_IS_REPLICA, ISysObject.I_LATEST_FLAG,
		ISysObject.I_REFERENCE_CNT, IPersistentObject.I_VSTAMP,
		ISysObject.OWNER_PERMIT, ISysObject.R_ACCESS_DATE,
		ISysObject.R_ALIAS_SET_ID, ISysObject.R_ASSEMBLED_FROM_ID,
		ISysObject.R_COMPONENT_LABEL, ISysObject.R_COMPOSITE_ID,
		ISysObject.R_CONTENT_SIZE, ISysObject.R_CREATION_DATE,
		ISysObject.R_CREATOR_NAME, ISysObject.R_CURRENT_STATE,
		ISysObject.R_FROZEN_FLAG, ISysObject.R_FRZN_ASSEMBLY_CNT,
		ISysObject.R_HAS_EVENTS, ISysObject.R_HAS_FRZN_ASSEMBLY,
		ISysObject.R_IMMUTABLE_FLAG, ISysObject.R_IS_PUBLIC,
		ISysObject.R_IS_VIRTUAL_DOC, ISysObject.R_LINK_CNT,
		ISysObject.R_LINK_HIGH_CNT, ISysObject.R_LOCK_DATE,
		ISysObject.R_LOCK_MACHINE, ISysObject.R_LOCK_OWNER,
		ISysObject.R_MODIFIER, ISysObject.R_MODIFY_DATE,
		IPersistentObject.R_OBJECT_ID, ISysObject.R_OBJECT_TYPE,
		ISysObject.R_ORDER_NO, ISysObject.R_PAGE_CNT, ISysObject.R_POLICY_ID,
		ISysObject.R_RESUME_STATE, ISysObject.R_VERSION_LABEL,
		ISysObject.WORLD_PERMIT, ISysObject.ACL_DOMAIN, ISysObject.ACL_NAME,
		ISysObject.A_APPLICATION_TYPE, ISysObject.A_STATUS,
		ISysObject.R_COMPONENT_LABEL, ISysObject.OWNER_NAME				};

	/**
	 * List of attributes that should not be updated through the standard api.
	 * 
	 * @since 1.0.3
	 */
	public static final Vector<String>	ATTRIBUTES_WE_CANT_TOUCH		= new Vector<String>(
																			Arrays
																				.asList(ISysObject.ATTRIBUTES_WE_CANT_TOUCH_ARRAY));

	/**
	 * Array of attributes that should not be updated throught the standard api
	 * (a_compound_architecture, a_content_type, a_link_resolvede,
	 * a_storage_type, group_name, group_permit, i_antecedent_id, i_branch_cnt,
	 * i_cabinet_id, i_chronicle_id, i_contents_id, i_direct_dsc, i_folder_id,
	 * i_has_folder, i_is_deleted, i_is_reference, i_is_replica, i_latest_flag,
	 * i_reference_cnt, i_vstamp, owner_permit, r_access_date, r_alias_set_id,
	 * r_assembled_from_id, r_component_label, r_composite_id, r_content_size,
	 * r_creation_date, r_creator_name, r_current_state, r_frozen_flag,
	 * r_frzn_assembly_cnt, r_has_events, r_has_frzn_assembly, r_immutable_flag,
	 * r_is_public, r_is_virtual_doc, r_link_cnt, r_link_high_cnt, r_lock_date,
	 * r_lock_machine, r_lock_owner, r_modifier, r_modify_date, r_object_id,
	 * r_object_type, r_order_no, r_page_cnt, r_policy_id, r_resumen_state,
	 * r_version_label, world_permit).
	 * 
	 * @since 1.0.3
	 */
	public static final String[]		ATTRIBUTES_WE_CANT_TOUCH_ARRAY	= new String[] {
		ISysObject.A_COMPOUND_ARCHITECTURE, ISysObject.A_CONTENT_TYPE,
		ISysObject.A_LINK_RESOLVED, ISysObject.A_STORAGE_TYPE,
		ISysObject.GROUP_NAME, ISysObject.GROUP_PERMIT,
		ISysObject.I_ANTECEDENT_ID, ISysObject.I_BRANCH_CNT,
		ISysObject.I_CABINET_ID, ISysObject.I_CHRONICLE_ID,
		ISysObject.I_CONTENTS_ID, ISysObject.I_DIRECT_DSC,
		ISysObject.I_FOLDER_ID, ISysObject.I_HAS_FOLDER,
		ISysObject.I_IS_DELETED, ISysObject.I_IS_REFERENCE,
		IPersistentObject.I_IS_REPLICA, ISysObject.I_LATEST_FLAG,
		ISysObject.I_REFERENCE_CNT, IPersistentObject.I_VSTAMP,
		ISysObject.OWNER_PERMIT, ISysObject.R_ACCESS_DATE,
		ISysObject.R_ALIAS_SET_ID, ISysObject.R_ASSEMBLED_FROM_ID,
		ISysObject.R_COMPONENT_LABEL, ISysObject.R_COMPOSITE_ID,
		ISysObject.R_CONTENT_SIZE, ISysObject.R_CREATION_DATE,
		ISysObject.R_CREATOR_NAME, ISysObject.R_CURRENT_STATE,
		ISysObject.R_FROZEN_FLAG, ISysObject.R_FRZN_ASSEMBLY_CNT,
		ISysObject.R_HAS_EVENTS, ISysObject.R_HAS_FRZN_ASSEMBLY,
		ISysObject.R_IMMUTABLE_FLAG, ISysObject.R_IS_PUBLIC,
		ISysObject.R_IS_VIRTUAL_DOC, ISysObject.R_LINK_CNT,
		ISysObject.R_LINK_HIGH_CNT, ISysObject.R_LOCK_DATE,
		ISysObject.R_LOCK_MACHINE, ISysObject.R_LOCK_OWNER,
		ISysObject.R_MODIFIER, ISysObject.R_MODIFY_DATE,
		IPersistentObject.R_OBJECT_ID, ISysObject.R_OBJECT_TYPE,
		ISysObject.R_ORDER_NO, ISysObject.R_PAGE_CNT, ISysObject.R_POLICY_ID,
		ISysObject.R_RESUME_STATE, ISysObject.R_VERSION_LABEL,
		ISysObject.WORLD_PERMIT											};

	/**
	 * List of the authors for the object. This is user-defined 'authors'
	 * CHAR(48) REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String			AUTHORS							= "authors";													//$NON-NLS-1$

	/**
	 * Name of the group. If group_class is set to role, then group_name is the
	 * name of the role. If group_class is set to domain, then group_name is set
	 * the name of the domain. The name must consist of characters compatible
	 * with the server os codepage of the Content Server 'group_name' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			GROUP_NAME						= "group_name";												//$NON-NLS-1$

	/**
	 * Object-level permission assigned to the objectâ€™s group for this
	 * object 'group_permit' INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			GROUP_PERMIT					= "group_permit";												//$NON-NLS-1$

	/**
	 * Object ID of the object's parent version 'i_antecedent_id' ID.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_ANTECEDENT_ID					= "i_antecedent_id";											//$NON-NLS-1$

	/**
	 * Number of branches on the version tree that contains the object
	 * 'i_branch_cnt' INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_BRANCH_CNT					= "i_branch_cnt";												//$NON-NLS-1$

	/**
	 * Object ID of the cabinet that is the objectâ€™s primary storage
	 * location 'i_cabinet_id' ID.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_CABINET_ID					= "i_cabinet_id";												//$NON-NLS-1$

	/**
	 * Object ID of the root object of the version tree that contains this
	 * object 'i_chronicle_id' ID.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_CHRONICLE_ID					= "i_chronicle_id";											//$NON-NLS-1$

	/**
	 * Object ID of the content object for an object that has only one content.
	 * This is not used if an object has multiple content. In such cases, the
	 * Content Facility manages the relationships between the object and its
	 * multiple content 'i_contents_id' ID.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_CONTENTS_ID					= "i_contents_id";												//$NON-NLS-1$

	/**
	 * Indicates whether the object has any direct descendants (versions derived
	 * directly from this object). The default is FALSE 'i_direct_dsc' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_DIRECT_DSC					= "i_direct_dsc";												//$NON-NLS-1$

	/**
	 * Object IDs of all folders linked to the object 'i_folder_id' ID
	 * REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_FOLDER_ID						= "i_folder_id";												//$NON-NLS-1$

	/**
	 * Flag indicating whether this object is the CURRENT object in the version
	 * tree 'i_has_folder' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_HAS_FOLDER					= "i_has_folder";												//$NON-NLS-1$

	/**
	 * If the object is the root version of a version tree, deleting the object
	 * sets this attribute to TRUE 'i_is_deleted' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_IS_DELETED					= "i_is_deleted";												//$NON-NLS-1$

	/**
	 * Indicates whether the object is a mirror object for a reference link to a
	 * remote object. TRUE means that the object is a mirror object
	 * 'i_is_reference' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_IS_REFERENCE					= "i_is_reference";											//$NON-NLS-1$

	/**
	 * Indicates whether this version is the most recent version of the object
	 * on a particular branch in the version tree 'i_latest_flag' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_LATEST_FLAG					= "i_latest_flag";												//$NON-NLS-1$

	/**
	 * Number of folder references made to this object 'i_reference_cnt'
	 * INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			I_REFERENCE_CNT					= "i_reference_cnt";											//$NON-NLS-1$

	/**
	 * List of user-defined keywords for the object 'keywords' CHAR(48)
	 * REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String			KEYWORDS						= "keywords";													//$NON-NLS-1$

	/**
	 * Five-character code, in the format xx_yy, indicating the language in
	 * which the document is written and the country of origin. The first two
	 * characters (xx) contain the language code and the final two (yy), the
	 * country code 'language_code' CHAR(5).
	 * 
	 * @since 1.0.3
	 */
	public static final String			LANGUAGE_CODE					= "language_code";												//$NON-NLS-1$

	/**
	 * Version comments specified by the user 'log_entry' CHAR(120).
	 * 
	 * @since 1.0.3
	 */
	public static final String			LOG_ENTRY						= "log_entry";													//$NON-NLS-1$

	/**
	 * Name of the object. This is user-defined. Note: If you are using a
	 * Documentum client, this is the name that appears under the
	 * objectâ€™s icon 'object_name' CHAR(255).
	 * 
	 * @since 1.0.3
	 */
	public static final String			OBJECT_NAME						= "object_name";												//$NON-NLS-1$

	/**
	 * Name of the objectâ€™s owner. This can be a user or group name
	 * 'owner_name' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			OWNER_NAME						= "owner_name";												//$NON-NLS-1$

	/**
	 * Object-level permission assigned to the owner for this object
	 * 'owner_permit' INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			OWNER_PERMIT					= "owner_permit";												//$NON-NLS-1$

	/**
	 * Contains the date and time when this object was last accessed by a
	 * Getfile or Print method 'r_access_date' TIME.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_ACCESS_DATE					= "r_access_date";												//$NON-NLS-1$

	/**
	 * Object ID of the alias set associated with the sysobject 'r_alias_set_id'
	 * ID.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_ALIAS_SET_ID					= "r_alias_set_id";											//$NON-NLS-1$

	/**
	 * Object ID of the virtual document that was the source of the assembly
	 * associated with a document 'r_assembled_from_id' ID.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_ASSEMBLED_FROM_ID				= "r_assembled_from_id";										//$NON-NLS-1$

	/**
	 * No longer used. Preserved for backwards compatibility 'r_component_label'
	 * CHAR(32) REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_COMPONENT_LABEL				= "r_component_label";											//$NON-NLS-1$

	/**
	 * No longer used. Preserved for backwards compatibility 'r_composite_id' ID
	 * REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_COMPOSITE_ID					= "r_composite_id";											//$NON-NLS-1$

	/**
	 * No longer used. Preserved for backwards compatibility 'r_composite_label'
	 * CHAR(32) REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_COMPOSITE_LABEL				= "r_composite_label";											//$NON-NLS-1$

	/**
	 * Size, in bytes, of the first content file associated with the document.
	 * This attribute cannot record content sizes greater than 2GB. Examine
	 * r_full_content_size to obtain the size of content larger than 2GB
	 * 'r_content_size' INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_CONTENT_SIZE					= "r_content_size";											//$NON-NLS-1$

	/**
	 * Date the object was created. This is set by the server. || Records date
	 * and time of when a work item is generated 'r_creation_date' TIME.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_CREATION_DATE					= "r_creation_date";											//$NON-NLS-1$

	/**
	 * Indicates the creator. Automatically set by the server 'r_creator_name'
	 * CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_CREATOR_NAME					= "r_creator_name";											//$NON-NLS-1$

	/**
	 * State number of the current state of the object in the lifecycle
	 * 'r_current_state' INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_CURRENT_STATE					= "r_current_state";											//$NON-NLS-1$

	/**
	 * Indicates whether the object is unchangeable because it was specifically
	 * frozen. The default is FALSE 'r_frozen_flag' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_FROZEN_FLAG					= "r_frozen_flag";												//$NON-NLS-1$

	/**
	 * Contains a count of the number of frozen assemblies that contain this
	 * object 'r_frzn_assembly_cnt' INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_FRZN_ASSEMBLY_CNT				= "r_frzn_assembly_cnt";										//$NON-NLS-1$

	/**
	 * 'r_full_content_size' DOUBLE
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_FULL_CONTENT_SIZE				= "r_full_content_size";										//$NON-NLS-1$

	/**
	 * Indicates whether any users have registered to receive events for this
	 * object. The default is FALSE 'r_has_events' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_HAS_EVENTS					= "r_has_events";												//$NON-NLS-1$

	/**
	 * Indicates that the documentâ€™s assembly is frozen
	 * 'r_has_frzn_assembly' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_HAS_FRZN_ASSEMBLY				= "r_has_frzn_assembly";										//$NON-NLS-1$

	/**
	 * Indicates whether the object can be changed. The default is FALSE
	 * 'r_immutable_flag' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_IMMUTABLE_FLAG				= "r_immutable_flag";											//$NON-NLS-1$

	/**
	 * TRUE means the object is public. FALSE indicates that the object is not
	 * public. If this is TRUE, the object's attributes and content are open to
	 * the publicâ€�?there is no security checking on the object.
	 * 'r_is_public' BOOLEAN.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_IS_PUBLIC						= "r_is_public";												//$NON-NLS-1$

	/**
	 * Indicates whether the SysObject is a virtual document. If the value is 1,
	 * the object is a virtual document. If the value is 0, the object is not a
	 * virtual document unless the r_link_cnt is greater than 0
	 * 'r_is_virtual_doc' INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_IS_VIRTUAL_DOC				= "r_is_virtual_doc";											//$NON-NLS-1$

	/**
	 * Number of components in the virtual document 'r_link_cnt' INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_LINK_CNT						= "r_link_cnt";												//$NON-NLS-1$

	/**
	 * Records the current maximum order number assigned to a component
	 * 'r_link_high_cnt' INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_LINK_HIGH_CNT					= "r_link_high_cnt";											//$NON-NLS-1$

	/**
	 * Date that this object was locked 'r_lock_date' TIME.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_LOCK_DATE						= "r_lock_date";												//$NON-NLS-1$

	/**
	 * Name of the client machine on which a user is working when he or she
	 * locks an object (by a checkout or branch operation). If the server is
	 * unable to resolve the name, r_lock_attribute is set to the client
	 * machineâ€™s IP address instead 'r_lock_machine' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_LOCK_MACHINE					= "r_lock_machine";											//$NON-NLS-1$

	/**
	 * Name of the user who locked the object 'r_lock_owner' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_LOCK_OWNER					= "r_lock_owner";												//$NON-NLS-1$

	/**
	 * Name of the user who made the last modification 'r_modifier' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_MODIFIER						= "r_modifier";												//$NON-NLS-1$

	/**
	 * Date the object was last modified. This is set by the server.
	 * 'r_modify_date' TIME.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_MODIFY_DATE					= "r_modify_date";												//$NON-NLS-1$

	/**
	 * The object's type, for example, dm_folder or dm_document, set when the
	 * object is created 'r_object_type' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_OBJECT_TYPE					= "r_object_type";												//$NON-NLS-1$

	/**
	 * No longer used. Preserved for backwards compatibility 'r_order_no'
	 * INTEGER REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_ORDER_NO						= "r_order_no";												//$NON-NLS-1$

	/**
	 * Number of content files associated with the object 'r_page_cnt' INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_PAGE_CNT						= "r_page_cnt";												//$NON-NLS-1$

	/**
	 * Object ID of the associated policy object 'r_policy_id' ID.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_POLICY_ID						= "r_policy_id";												//$NON-NLS-1$

	/**
	 * The state number to which the object is resumed if r_current_state
	 * identifies an exception state 'r_resume_state' INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_RESUME_STATE					= "r_resume_state";											//$NON-NLS-1$

	/**
	 * List of the version labels associated with the object. The first position
	 * in this attribute holds the object's implicit version label. The
	 * remaining rows contain the objectâ€™s symbolic version labels
	 * 'r_version_label' CHAR(32) REPEATING.
	 * 
	 * @since 1.0.3
	 */
	public static final String			R_VERSION_LABEL					= "r_version_label";											//$NON-NLS-1$

	/**
	 * Contains the default version label used to resolve late-bound nodes of a
	 * virtual document 'resolution_label' CHAR(32).
	 * 
	 * @since 1.0.3
	 */
	public static final String			RESOLUTION_LABEL				= "resolution_label";											//$NON-NLS-1$

	/**
	 * Subject of the object. This is user-defined 'subject' CHAR(192).
	 * 
	 * @since 1.0.3
	 */
	public static final String			SUBJECT							= "subject";													//$NON-NLS-1$

	/**
	 * Title of the object. This is user-defined 'title' CHAR(400).
	 * 
	 * @since 1.0.3
	 */
	public static final String			TITLE							= "title";														//$NON-NLS-1$

	/**
	 * dm_sysobject
	 * 
	 * @since 1.0.3
	 */
	public static final String			TYPE_DM_SYSOBJECT				= "dm_sysobject";												//$NON-NLS-1$

	/**
	 * Object-level permission assigned to the world (all users except owner and
	 * group members) for this object 'world_permit' INTEGER.
	 * 
	 * @since 1.0.3
	 */
	public static final String			WORLD_PERMIT					= "world_permit";												//$NON-NLS-1$

}
