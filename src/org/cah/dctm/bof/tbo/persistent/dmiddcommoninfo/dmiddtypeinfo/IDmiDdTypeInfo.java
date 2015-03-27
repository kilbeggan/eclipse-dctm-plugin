/*-
 * $Log: IDmiDdTypeInfo.java,v $
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
 * Revision 1.5  2005/03/25 09:26:03  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 14:01:41  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:58  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:57:55  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:09  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.dmiddcommoninfo.dmiddtypeinfo;

import org.cah.dctm.bof.tbo.persistent.dmiddcommoninfo.IDmiDdCommonInfo;


/**
 * <H4>Attributes of a dmi_dd_type_info object.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Apr 26, 2004 10:08:45 AM.</DD>
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
public abstract interface IDmiDdTypeInfo
										extends
											IDmiDdCommonInfo
{

	/**
	 * attr_domain_id ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ATTR_DOMAIN_ID			= "attr_domain_id";		//$NON-NLS-1$

	/**
	 * attr_domain_name CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ATTR_DOMAIN_NAME		= "attr_domain_name";		//$NON-NLS-1$

	/**
	 * auditable_appevents CHAR(64) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	AUDITABLE_APPEVENTS		= "auditable_appevents";	//$NON-NLS-1$

	/**
	 * auditable_sysevents CHAR(64) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	AUDITABLE_SYSEVENTS		= "auditable_sysevents";	//$NON-NLS-1$

	/**
	 * comp_classifier CHAR(128) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	COMP_CLASSIFIER			= "comp_classifier";		//$NON-NLS-1$

	/**
	 * default_policy_id ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFAULT_POLICY_ID		= "default_policy_id";		//$NON-NLS-1$

	/**
	 * i_attr_domain_id INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_ATTR_DOMAIN_ID		= "i_attr_domain_id";		//$NON-NLS-1$

	/**
	 * i_attr_domain_name INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_ATTR_DOMAIN_NAME		= "i_attr_domain_name";	//$NON-NLS-1$

	/**
	 * i_auditable_appevents INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_AUDITABLE_APPEVENTS	= "i_auditable_appevents";	//$NON-NLS-1$

	/**
	 * i_auditable_sysevents INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_AUDITABLE_SYSEVENTS	= "i_auditable_sysevents";	//$NON-NLS-1$

	/**
	 * i_comp_classifier INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_COMP_CLASSIFIER		= "i_comp_classifier";		//$NON-NLS-1$

	/**
	 * i_default_policy_id BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_DEFAULT_POLICY_ID		= "i_default_policy_id";	//$NON-NLS-1$

	/**
	 * i_icon_index BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_ICON_INDEX			= "i_icon_index";			//$NON-NLS-1$

	/**
	 * i_policy_ver_label BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_POLICY_VER_LABEL		= "i_policy_ver_label";	//$NON-NLS-1$

	/**
	 * i_qual_comp_id INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_QUAL_COMP_ID			= "i_qual_comp_id";		//$NON-NLS-1$

	/**
	 * i_scope_config INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_SCOPE_CONFIG			= "i_scope_config";		//$NON-NLS-1$

	/**
	 * icon_index INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	ICON_INDEX				= "icon_index";			//$NON-NLS-1$

	/**
	 * policy_ver_label CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	POLICY_VER_LABEL		= "policy_ver_label";		//$NON-NLS-1$

	/**
	 * qual_comp_id ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	QUAL_COMP_ID			= "qual_comp_id";			//$NON-NLS-1$

	/**
	 * r_has_check BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HAS_CHECK				= "r_has_check";			//$NON-NLS-1$

	/**
	 * r_has_constraint BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HAS_CONSTRAINT		= "r_has_constraint";		//$NON-NLS-1$

	/**
	 * r_has_default BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HAS_DEFAULT			= "r_has_default";			//$NON-NLS-1$

	/**
	 * r_has_dependency BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HAS_DEPENDENCY		= "r_has_dependency";		//$NON-NLS-1$

	/**
	 * r_has_foreign_key BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HAS_FOREIGN_KEY		= "r_has_foreign_key";		//$NON-NLS-1$

	/**
	 * r_has_ignore_immutable BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HAS_IGNORE_IMMUTABLE	= "r_has_ignore_immutable"; //$NON-NLS-1$

	/**
	 * r_has_not_null BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HAS_NOT_NULL			= "r_has_not_null";		//$NON-NLS-1$

	/**
	 * r_has_primary_key BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HAS_PRIMARY_KEY		= "r_has_primary_key";		//$NON-NLS-1$

	/**
	 * r_has_unique_key BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HAS_UNIQUE_KEY		= "r_has_unique_key";		//$NON-NLS-1$

	/**
	 * r_has_value_assist BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_HAS_VALUE_ASSIST		= "r_has_value_assist";	//$NON-NLS-1$

	/**
	 * scope_config ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	SCOPE_CONFIG			= "scope_config";			//$NON-NLS-1$

	/**
	 * dmi_dd_type_info
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DMI_DD_TYPE_INFO	= "dmi_dd_type_info";		//$NON-NLS-1$

}
