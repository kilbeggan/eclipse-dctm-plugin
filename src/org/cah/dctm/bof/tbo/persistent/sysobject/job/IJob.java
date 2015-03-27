/*-
 * $Log: IJob.java,v $
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
 * Revision 1.3  2005/01/24 12:34:56  harpechr
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

package org.cah.dctm.bof.tbo.persistent.sysobject.job;

import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;


/**
 * <H4>Attributes and type of the dm_job type.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Apr 25, 2004 8:44:39 PM.</DD>
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
public abstract interface IJob
								extends
									ISysObject
{

	/**
	 * a_continuation_interval INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	A_CONTINUATION_INTERVAL		= "a_continuation_interval";	//$NON-NLS-1$

	/**
	 * a_current_status CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	A_CURRENT_STATUS			= "a_current_status";			//$NON-NLS-1$

	/**
	 * a_is_continued BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	A_IS_CONTINUED				= "a_is_continued";			//$NON-NLS-1$

	/**
	 * a_iterations INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	A_ITERATIONS				= "a_iterations";				//$NON-NLS-1$

	/**
	 * a_last_completion TIME
	 * 
	 * @since 1.0.3
	 */
	public static final String	A_LAST_COMPLETION			= "a_last_completion";			//$NON-NLS-1$

	/**
	 * a_last_document_id ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	A_LAST_DOCUMENT_ID			= "a_last_document_id";		//$NON-NLS-1$

	/**
	 * a_last_invocation TIME
	 * 
	 * @since 1.0.3
	 */
	public static final String	A_LAST_INVOCATION			= "a_last_invocation";			//$NON-NLS-1$

	/**
	 * a_last_process_id INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	A_LAST_PROCESS_ID			= "a_last_process_id";			//$NON-NLS-1$

	/**
	 * a_last_return_code INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	A_LAST_RETURN_CODE			= "a_last_return_code";		//$NON-NLS-1$

	/**
	 * a_next_continuation TIME
	 * 
	 * @since 1.0.3
	 */
	public static final String	A_NEXT_CONTINUATION			= "a_next_continuation";		//$NON-NLS-1$

	/**
	 * a_next_invocation TIME
	 * 
	 * @since 1.0.3
	 */
	public static final String	A_NEXT_INVOCATION			= "a_next_invocation";			//$NON-NLS-1$

	/**
	 * expiration_date TIME
	 * 
	 * @since 1.0.3
	 */
	public static final String	EXPIRATION_DATE				= "expiration_date";			//$NON-NLS-1$

	/**
	 * inactivate_after_failure BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	INACTIVATE_AFTER_FAILURE	= "inactivate_after_failure";	//$NON-NLS-1$

	/**
	 * is_inactive BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	IS_INACTIVE					= "is_inactive";				//$NON-NLS-1$

	/**
	 * max_iterations INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	MAX_ITERATIONS				= "max_iterations";			//$NON-NLS-1$

	/**
	 * method_arguments CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	METHOD_ARGUMENTS			= "method_arguments";			//$NON-NLS-1$

	/**
	 * method_data CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	METHOD_DATA					= "method_data";				//$NON-NLS-1$

	/**
	 * method_name CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	METHOD_NAME					= "method_name";				//$NON-NLS-1$

	/**
	 * method_trace_level INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	METHOD_TRACE_LEVEL			= "method_trace_level";		//$NON-NLS-1$

	/**
	 * pass_standard_arguments BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	PASS_STANDARD_ARGUMENTS		= "pass_standard_arguments";	//$NON-NLS-1$

	/**
	 * run_interval INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	RUN_INTERVAL				= "run_interval";				//$NON-NLS-1$

	/**
	 * run_mode INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	RUN_MODE					= "run_mode";					//$NON-NLS-1$

	/**
	 * run_now BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	RUN_NOW						= "run_now";					//$NON-NLS-1$

	/**
	 * start_date TIME
	 * 
	 * @since 1.0.3
	 */
	public static final String	START_DATE					= "start_date";				//$NON-NLS-1$

	/**
	 * target_server CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	TARGET_SERVER				= "target_server";				//$NON-NLS-1$

	/**
	 * 'dm_job'.
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_JOB					= "dm_job";					//$NON-NLS-1$

}
