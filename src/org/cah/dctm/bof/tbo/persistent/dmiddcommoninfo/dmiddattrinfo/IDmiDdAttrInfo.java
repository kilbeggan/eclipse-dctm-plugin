/*-
 * $Log: IDmiDdAttrInfo.java,v $
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
 * Revision 1.4  2005/02/09 14:01:39  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:57  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:58:13  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:13  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.dmiddcommoninfo.dmiddattrinfo;

import org.cah.dctm.bof.tbo.persistent.dmiddcommoninfo.IDmiDdCommonInfo;


/**
 * <H4>Data dictionary attribute information.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Oct 3, 2004 11:31:10 PM.</DD>
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
public interface IDmiDdAttrInfo
								extends
									IDmiDdCommonInfo
{

	/**
	 * allowed_search_ops INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALLOWED_SEARCH_OPS		= "allowed_search_ops";	//$NON-NLS-1$

	/**
	 * attr_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	ATTR_NAME				= "attr_name";				//$NON-NLS-1$

	/**
	 * category_name CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	CATEGORY_NAME			= "category_name";			//$NON-NLS-1$

	/**
	 * computed_dep_usr BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	COMPUTED_DEP_USR		= "computed_dep_usr";		//$NON-NLS-1$

	/**
	 * computed_expr_dep CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	COMPUTED_EXPR_DEP		= "computed_expr_dep";		//$NON-NLS-1$

	/**
	 * cond_computed_expr ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	COND_COMPUTED_EXPR		= "cond_computed_expr";	//$NON-NLS-1$

	/**
	 * cond_value_assist ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	COND_VALUE_ASSIST		= "cond_value_assist";		//$NON-NLS-1$

	/**
	 * def_value_length INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEF_VALUE_LENGTH		= "def_value_length";		//$NON-NLS-1$

	/**
	 * default_expr_builtin INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_EXPR_BUILTIN	= "default_expr_builtin";	//$NON-NLS-1$

	/**
	 * default_expr_kind INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_EXPR_KIND		= "default_expr_kind";		//$NON-NLS-1$

	/**
	 * default_expr_value CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_EXPR_VALUE		= "default_expr_value";	//$NON-NLS-1$

	/**
	 * default_search_arg CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_SEARCH_ARG		= "default_search_arg";	//$NON-NLS-1$

	/**
	 * default_search_op INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_SEARCH_OP		= "default_search_op";		//$NON-NLS-1$

	/**
	 * default_value ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_VALUE			= "default_value";			//$NON-NLS-1$

	/**
	 * domain_length INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	DOMAIN_LENGTH			= "domain_length";			//$NON-NLS-1$

	/**
	 * domain_type INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	DOMAIN_TYPE				= "domain_type";			//$NON-NLS-1$

	/**
	 * format_pattern CHAR(64)
	 * 
	 * @since 1.0.3
	 */
	public static final String	FORMAT_PATTERN			= "format_pattern";		//$NON-NLS-1$

	/**
	 * format_pattern_tag INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	FORMAT_PATTERN_TAG		= "format_pattern_tag";	//$NON-NLS-1$

	/**
	 * i_allowed_search_ops INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_ALLOWED_SEARCH_OPS	= "i_allowed_search_ops";	//$NON-NLS-1$

	/**
	 * i_category_name BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_CATEGORY_NAME			= "i_category_name";		//$NON-NLS-1$

	/**
	 * i_computed_dep_usr BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_COMPUTED_DEP_USR		= "i_computed_dep_usr";	//$NON-NLS-1$

	/**
	 * i_computed_expr_dep INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_COMPUTED_EXPR_DEP		= "i_computed_expr_dep";	//$NON-NLS-1$

	/**
	 * i_cond_computed_expr BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_COND_COMPUTED_EXPR	= "i_cond_computed_expr";	//$NON-NLS-1$

	/**
	 * i_cond_value_assist BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_COND_VALUE_ASSIST		= "i_cond_value_assist";	//$NON-NLS-1$

	/**
	 * i_def_value_length BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_DEF_VALUE_LENGTH		= "i_def_value_length";	//$NON-NLS-1$

	/**
	 * i_default_expr_builtin INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_DEFAULT_EXPR_BUILTIN	= "i_default_expr_builtin"; //$NON-NLS-1$

	/**
	 * i_default_expr_kind INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_DEFAULT_EXPR_KIND		= "i_default_expr_kind";	//$NON-NLS-1$

	/**
	 * i_default_expr_value INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_DEFAULT_EXPR_VALUE	= "i_default_expr_value";	//$NON-NLS-1$

	/**
	 * i_default_search_arg BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_DEFAULT_SEARCH_ARG	= "i_default_search_arg";	//$NON-NLS-1$

	/**
	 * i_default_search_op BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_DEFAULT_SEARCH_OP		= "i_default_search_op";	//$NON-NLS-1$

	/**
	 * i_default_value INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_DEFAULT_VALUE			= "i_default_value";		//$NON-NLS-1$

	/**
	 * i_domain_length BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_DOMAIN_LENGTH			= "i_domain_length";		//$NON-NLS-1$

	/**
	 * i_domain_type BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_DOMAIN_TYPE			= "i_domain_type";			//$NON-NLS-1$

	/**
	 * i_format_pattern BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_FORMAT_PATTERN		= "i_format_pattern";		//$NON-NLS-1$

	/**
	 * i_format_pattern_tag BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_FORMAT_PATTERN_TAG	= "i_format_pattern_tag";	//$NON-NLS-1$

	/**
	 * i_ignore_immutable BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_IGNORE_IMMUTABLE		= "i_ignore_immutable";	//$NON-NLS-1$

	/**
	 * i_is_hidden BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_IS_HIDDEN				= "i_is_hidden";			//$NON-NLS-1$

	/**
	 * i_is_required BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_IS_REQUIRED			= "i_is_required";			//$NON-NLS-1$

	/**
	 * i_not_null BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_NOT_NULL				= "i_not_null";			//$NON-NLS-1$

	/**
	 * i_not_null_enf BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_NOT_NULL_ENF			= "i_not_null_enf";		//$NON-NLS-1$

	/**
	 * i_not_null_msg BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_NOT_NULL_MSG			= "i_not_null_msg";		//$NON-NLS-1$

	/**
	 * i_read_only BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_READ_ONLY				= "i_read_only";			//$NON-NLS-1$

	/**
	 * i_reference_kind BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_REFERENCE_KIND		= "i_reference_kind";		//$NON-NLS-1$

	/**
	 * i_super_domain_id BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_SUPER_DOMAIN_ID		= "i_super_domain_id";		//$NON-NLS-1$

	/**
	 * i_super_domain_name BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_SUPER_DOMAIN_NAME		= "i_super_domain_name";	//$NON-NLS-1$

	/**
	 * i_value_assist_dep INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_VALUE_ASSIST_DEP		= "i_value_assist_dep";	//$NON-NLS-1$

	/**
	 * i_value_assist_dep_usr BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_VALUE_ASSIST_DEP_USR	= "i_value_assist_dep_usr"; //$NON-NLS-1$

	/**
	 * ignore_immutable BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	IGNORE_IMMUTABLE		= "ignore_immutable";		//$NON-NLS-1$

	/**
	 * is_hidden BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	IS_HIDDEN				= "is_hidden";				//$NON-NLS-1$

	/**
	 * is_required BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	IS_REQUIRED				= "is_required";			//$NON-NLS-1$

	/**
	 * not_null BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	NOT_NULL				= "not_null";				//$NON-NLS-1$

	/**
	 * not_null_enf INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	NOT_NULL_ENF			= "not_null_enf";			//$NON-NLS-1$

	/**
	 * not_null_msg CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	NOT_NULL_MSG			= "not_null_msg";			//$NON-NLS-1$

	/**
	 * read_only BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	READ_ONLY				= "read_only";				//$NON-NLS-1$

	/**
	 * reference_kind INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	REFERENCE_KIND			= "reference_kind";		//$NON-NLS-1$

	/**
	 * super_domain_id ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	SUPER_DOMAIN_ID			= "super_domain_id";		//$NON-NLS-1$

	/**
	 * super_domain_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	SUPER_DOMAIN_NAME		= "super_domain_name";		//$NON-NLS-1$

	/**
	 * dmi_dd_attr_info
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DMI_DD_ATTR_INFO	= "dmi_dd_attr_info";		//$NON-NLS-1$

	/**
	 * value_assist_dep CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	VALUE_ASSIST_DEP		= "value_assist_dep";		//$NON-NLS-1$

	/**
	 * value_assist_dep_usr BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	VALUE_ASSIST_DEP_USR	= "value_assist_dep_usr";	//$NON-NLS-1$

}
