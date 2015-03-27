/*-
 * $Log: IPolicy.java,v $
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
 * Revision 1.4  2005/02/09 14:01:43  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:59  harpechr
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

package org.cah.dctm.bof.tbo.persistent.sysobject.policy;

import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;


/**
 * <H4>dm_policy defined.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>May 31, 2004 1:49:07 PM.</DD>
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
public abstract interface IPolicy
									extends
										ISysObject
{

	/**
	 * action_object_id ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ACTION_OBJECT_ID				= "action_object_id";										//$NON-NLS-1$

	/**
	 * alias_set_ids ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALIAS_SET_IDS					= "alias_set_ids";											//$NON-NLS-1$

	/**
	 * allow_attach BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALLOW_ATTACH					= "allow_attach";											//$NON-NLS-1$

	/**
	 * allow_demote BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALLOW_DEMOTE					= "allow_demote";											//$NON-NLS-1$

	/**
	 * allow_schedule BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALLOW_SCHEDULE					= "allow_schedule";										//$NON-NLS-1$

	/**
	 * app_validation_id ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	APP_VALIDATION_ID				= "app_validation_id";										//$NON-NLS-1$

	/**
	 * app_validation_ver CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	APP_VALIDATION_VER				= "app_validation_ver";									//$NON-NLS-1$

	/**
	 * Policy name setting.
	 * 
	 * @since 1.0.3
	 */
	public static final String	CONFIG_POLICY_CONTROLLED_STATE	= "controlled_state_name";									//$NON-NLS-1$

	/**
	 * Policy name setting.
	 * 
	 * @since 1.0.3
	 */
	public static final String	CONFIG_POLICY_NAME_KEY			= "name";													//$NON-NLS-1$

	/**
	 * Policy related configuration settings.
	 * 
	 * @since 1.0.3
	 */
	public static final String	CONFIG_POLICY_SECTION			= "policy";												//$NON-NLS-1$

	/**
	 * Resource used to configure archiving.
	 * 
	 * @since 1.0.3
	 */
	public static final String	CONFIG_RESOURCE_NAME			= "com.te.dctm.bof.tbo.persistent.sysobject.policy.config"; //$NON-NLS-1$

	/**
	 * entry_criteria_id ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ENTRY_CRITERIA_ID				= "entry_criteria_id";										//$NON-NLS-1$

	/**
	 * exception_state CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	EXCEPTION_STATE					= "exception_state";										//$NON-NLS-1$

	/**
	 * extension_type CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	EXTENSION_TYPE					= "extension_type";										//$NON-NLS-1$

	/**
	 * i_state_no INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	I_STATE_NO						= "i_state_no";											//$NON-NLS-1$

	/**
	 * include_subtypes BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	INCLUDE_SUBTYPES				= "include_subtypes";										//$NON-NLS-1$

	/**
	 * included_type CHAR(27) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	INCLUDED_TYPE					= "included_type";											//$NON-NLS-1$

	/**
	 * r_definition_state INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	R_DEFINITION_STATE				= "r_definition_state";									//$NON-NLS-1$

	/**
	 * return_condition INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	RETURN_CONDITION				= "return_condition";										//$NON-NLS-1$

	/**
	 * return_to_base BOOLEAN REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	RETURN_TO_BASE					= "return_to_base";										//$NON-NLS-1$

	/**
	 * state_class INTEGER REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	STATE_CLASS						= "state_class";											//$NON-NLS-1$

	/**
	 * state_description CHAR(128) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	STATE_DESCRIPTION				= "state_description";										//$NON-NLS-1$

	/**
	 * state_name CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	STATE_NAME						= "state_name";											//$NON-NLS-1$

	/**
	 * state_type CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	STATE_TYPE						= "state_type";											//$NON-NLS-1$

	/**
	 * dm_policy
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_POLICY					= "dm_policy";												//$NON-NLS-1$

	/**
	 * type_override_id ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_OVERRIDE_ID				= "type_override_id";										//$NON-NLS-1$

	/**
	 * user_action_id ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_ACTION_ID					= "user_action_id";										//$NON-NLS-1$

	/**
	 * user_action_ver CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_ACTION_VER					= "user_action_ver";										//$NON-NLS-1$

	/**
	 * user_criteria_id ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_CRITERIA_ID				= "user_criteria_id";										//$NON-NLS-1$

	/**
	 * user_criteria_ver CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_CRITERIA_VER				= "user_criteria_ver";										//$NON-NLS-1$

	/**
	 * user_postproc_id ID REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_POSTPROC_ID				= "user_postproc_id";										//$NON-NLS-1$

	/**
	 * user_postproc_ver CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	USER_POSTPROC_VER				= "user_postproc_ver";										//$NON-NLS-1$
}
