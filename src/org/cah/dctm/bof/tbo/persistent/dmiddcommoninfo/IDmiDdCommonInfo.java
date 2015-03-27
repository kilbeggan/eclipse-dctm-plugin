/*-
 * $Log: IDmiDdCommonInfo.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:24:54  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.6  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.5  2005/03/25 09:26:04  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 14:01:38  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:56  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:58:11  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:11  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 */

package org.cah.dctm.bof.tbo.persistent.dmiddcommoninfo;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;


/**
 * <H4>Attributes of a dmi_dd_common_info object.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Apr 26, 2004 10:04:31 AM.</DD>
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
public abstract interface IDmiDdCommonInfo
											extends
												IPersistentObject
{

	/**
	 * business_policy_id ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	BUSINESS_POLICY_ID		= "business_policy_id";	//$NON-NLS-1$

	/**
	 * comment_text CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	COMMENT_TEXT			= "comment_text";			//$NON-NLS-1$

	/**
	 * constraint_dep_usr BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	CONSTRAINT_DEP_USR		= "constraint_dep_usr";	//$NON-NLS-1$

	/**
	 * foreign_key_enfs INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	FOREIGN_KEY_ENFS		= "foreign_key_enfs";		//$NON-NLS-1$

	/**
	 * foreign_key_msgs CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	FOREIGN_KEY_MSGS		= "foreign_key_msgs";		//$NON-NLS-1$

	/**
	 * foreign_keys ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	FOREIGN_KEYS			= "foreign_keys";			//$NON-NLS-1$

	/**
	 * help_text CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	HELP_TEXT				= "help_text";				//$NON-NLS-1$

	/**
	 * i_comment_text BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_COMMENT_TEXT			= "i_comment_text";		//$NON-NLS-1$

	/**
	 * i_constraint_dep_usr BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_CONSTRAINT_DEP_USR	= "i_constraint_dep_usr";	//$NON-NLS-1$

	/**
	 * i_dd_flags INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_DD_FLAGS				= "i_dd_flags";			//$NON-NLS-1$

	/**
	 * i_foreign_key_enfs INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_FOREIGN_KEY_ENFS		= "i_foreign_key_enfs";	//$NON-NLS-1$

	/**
	 * i_foreign_key_msgs INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_FOREIGN_KEY_MSGS		= "i_foreign_key_msgs";	//$NON-NLS-1$

	/**
	 * i_foreign_keys INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_FOREIGN_KEYS			= "i_foreign_keys";		//$NON-NLS-1$

	/**
	 * i_help_text BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_HELP_TEXT				= "i_help_text";			//$NON-NLS-1$

	/**
	 * i_ignore_constraints BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_IGNORE_CONSTRAINTS	= "i_ignore_constraints";	//$NON-NLS-1$

	/**
	 * i_is_searchable BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_IS_SEARCHABLE			= "i_is_searchable";		//$NON-NLS-1$

	/**
	 * i_label_text BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_LABEL_TEXT			= "i_label_text";			//$NON-NLS-1$

	/**
	 * i_life_cycle BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_LIFE_CYCLE			= "i_life_cycle";			//$NON-NLS-1$

	/**
	 * i_map_data_string INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_MAP_DATA_STRING		= "i_map_data_string";		//$NON-NLS-1$

	/**
	 * i_map_description INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_MAP_DESCRIPTION		= "i_map_description";		//$NON-NLS-1$

	/**
	 * i_map_display_string INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_MAP_DISPLAY_STRING	= "i_map_display_string";	//$NON-NLS-1$

	/**
	 * i_primary_key BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_PRIMARY_KEY			= "i_primary_key";			//$NON-NLS-1$

	/**
	 * i_primary_key_enf BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_PRIMARY_KEY_ENF		= "i_primary_key_enf";		//$NON-NLS-1$

	/**
	 * i_primary_key_msg BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_PRIMARY_KEY_MSG		= "i_primary_key_msg";		//$NON-NLS-1$

	/**
	 * i_unique_key_enfs INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_UNIQUE_KEY_ENFS		= "i_unique_key_enfs";		//$NON-NLS-1$

	/**
	 * i_unique_key_msgs INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_UNIQUE_KEY_MSGS		= "i_unique_key_msgs";		//$NON-NLS-1$

	/**
	 * i_unique_keys INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_UNIQUE_KEYS			= "i_unique_keys";			//$NON-NLS-1$

	/**
	 * i_val_constraint INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_VAL_CONSTRAINT		= "i_val_constraint";		//$NON-NLS-1$

	/**
	 * i_val_constraint_dep INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_VAL_CONSTRAINT_DEP	= "i_val_constraint_dep";	//$NON-NLS-1$

	/**
	 * i_val_constraint_enf INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_VAL_CONSTRAINT_ENF	= "i_val_constraint_enf";	//$NON-NLS-1$

	/**
	 * i_val_constraint_msg INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_VAL_CONSTRAINT_MSG	= "i_val_constraint_msg";	//$NON-NLS-1$

	/**
	 * ignore_constraints BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	IGNORE_CONSTRAINTS		= "ignore_constraints";	//$NON-NLS-1$

	/**
	 * is_searchable BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	IS_SEARCHABLE			= "is_searchable";			//$NON-NLS-1$

	/**
	 * label_text CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	LABEL_TEXT				= "label_text";			//$NON-NLS-1$

	/**
	 * life_cycle INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	LIFE_CYCLE				= "life_cycle";			//$NON-NLS-1$

	/**
	 * map_data_string CHAR(128) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	MAP_DATA_STRING			= "map_data_string";		//$NON-NLS-1$

	/**
	 * map_description CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	MAP_DESCRIPTION			= "map_description";		//$NON-NLS-1$

	/**
	 * map_display_string CHAR(128) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	MAP_DISPLAY_STRING		= "map_display_string";	//$NON-NLS-1$

	/**
	 * nls_key CHAR(5)
	 * 
	 * @since 1.0.3
	 */
	public static final String	NLS_KEY					= "nls_key";				//$NON-NLS-1$

	/**
	 * primary_key ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	PRIMARY_KEY				= "primary_key";			//$NON-NLS-1$

	/**
	 * primary_key_enf INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	PRIMARY_KEY_ENF			= "primary_key_enf";		//$NON-NLS-1$

	/**
	 * primary_key_msg CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	PRIMARY_KEY_MSG			= "primary_key_msg";		//$NON-NLS-1$

	/**
	 * resync_needed BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	RESYNC_NEEDED			= "resync_needed";			//$NON-NLS-1$

	/**
	 * state_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	STATE_NAME				= "state_name";			//$NON-NLS-1$

	/**
	 * dmi_dd_common_info
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DMI_DD_COMMON_INFO	= "dmi_dd_common_info";	//$NON-NLS-1$

	/**
	 * type_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_NAME				= "type_name";				//$NON-NLS-1$

	/**
	 * unique_key_enfs INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	UNIQUE_KEY_ENFS			= "unique_key_enfs";		//$NON-NLS-1$

	/**
	 * unique_key_msgs CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	UNIQUE_KEY_MSGS			= "unique_key_msgs";		//$NON-NLS-1$

	/**
	 * unique_keys ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	UNIQUE_KEYS				= "unique_keys";			//$NON-NLS-1$

	/**
	 * val_constraint ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	VAL_CONSTRAINT			= "val_constraint";		//$NON-NLS-1$

	/**
	 * val_constraint_dep CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	VAL_CONSTRAINT_DEP		= "val_constraint_dep";	//$NON-NLS-1$

	/**
	 * val_constraint_enf INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	VAL_CONSTRAINT_ENF		= "val_constraint_enf";	//$NON-NLS-1$

	/**
	 * val_constraint_msg CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	VAL_CONSTRAINT_MSG		= "val_constraint_msg";	//$NON-NLS-1$

}
