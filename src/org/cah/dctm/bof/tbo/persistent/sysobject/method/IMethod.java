/*-
 * $Log: IMethod.java,v $
 * Revision 1.8  2005/12/04 22:17:22  madcook
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
 * Revision 1.4  2005/02/09 14:01:46  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:35:15  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:58:14  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:13  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.sysobject.method;

import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;


/**
 * <H4>Attributes and type of the dm_method type.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Apr 25, 2004 8:26:17 PM.</DD>
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
public abstract interface IMethod
									extends
										ISysObject
{

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_ARGUMENTS			= "ARGUMENTS";			//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_CAN_FETCH			= "CAN_FETCH";			//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_CHECK_FTINDEX		= "CHECK_FTINDEX";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_CLEAN_FTINDEX		= "CLEAN_FTINDEX";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_CLEAN_LINKS		= "CLEAN_LINKS";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_CLEAR_PENDING		= "CLEAR_PENDING";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_DB_STATS			= "DB_STATS";			//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_DELETE_REPLICA		= "DELETE_REPLICA";	//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_DESTROY_CONTENT	= "DESTROY_CONTENT";	//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_DO_METHOD			= "DO_METHOD";			//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_DROP_INDEX			= "DROP_INDEX";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_DUMP_FTINDEX		= "DUMP_FTINDEX";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_EXEC_SQL			= "EXEC_SQL";			//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_FINISH_INDEX_MOVES	= "FINISH_INDEX_MOVES"; //$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_GET_FTINDEX_SIZE	= "GET_FTINDEX_SIZE";	//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_GET_PATH			= "GET_PATH";			//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_GETSTYLE_FTINDEX	= "GETSTYLE_FTINDEX";	//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_IMPORT_REPLICA		= "IMPORT_REPLICA";	//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_INDEX				= "MOVE_INDEX";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_LAUNCH_ASYNC		= "LAUNCH_ASYNC";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_LAUNCH_DIRECT		= "LAUNCH_DIRECT";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_LIST_RECOURCES		= "LIST_RESOURCES";	//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_LIST_SESSIONS		= "LIST_SESSIONS";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_LIST_TARGETS		= "LIST_TARGETS";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_LOAD_FTINDEX		= "LOAD_FTINDEX";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_LOG_OFF			= "LOG_OFF";			//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_LOG_ON				= "LOG_ON";			//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_MAKE_INDEX			= "MAKE_INDEX";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_MARK_ALL			= "MARK_ALL";			//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_MARK_FOR_RETRY		= "MARK_FOR_RETRY";	//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_METHOD				= "METHOD";			//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_PING				= "PING";				//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_PURGE_CONTENT		= "PURGE_CONTENT";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_REAPPLY_PERMISSION	= "REAPPLY_PERMISSION"; //$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_REPLICATE			= "REPLICATE";			//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_RESET_FTINDEX		= "RESET_FTINDEX";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_RESTORE_CONTENT	= "RESTORE_CONTENT";	//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_RMSTYLE_FTINDEX	= "RMSTYLE_FTINDEX";	//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_RUN_AS_SERVER		= "RUN_AS_SERVER";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_SAVE_RESULTS		= "SAVE_RESULTS";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_SET_OPTIONS		= "SET_OPTIONS";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_SET_STORAGE_STATE	= "SET_STORAGE_STATE";	//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_SETSTYLE_FTINDEX	= "SETSTYLE_FTINDEX";	//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_SHOW_SESSIONS		= "SHOW_SESSIONS";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_TIME_OUT			= "TIME_OUT";			//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_TRACE				= "MODIFY_TRACE";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_TRACE_LAUNCH		= "TRACE_LAUNCH";		//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_TYPE_BOOLEAN		= "B";					//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_TYPE_DOUBLE		= "D";					//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_TYPE_INTEGER		= "I";					//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_TYPE_STRING		= "S";					//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_TYPE_TIME			= "T";					//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_UPDATE_FTINDEX		= "UPDATE_FTINDEX";	//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_VALUE_FALSE		= "F";					//$NON-NLS-1$

	/**
	 * @since 1.0.3
	 */
	public static final String	ARGUMENT_VALUE_TRUE			= "T";					//$NON-NLS-1$

	/**
	 * launch_async BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	LAUNCH_ASYNC				= "launch_async";		//$NON-NLS-1$

	/**
	 * launch_direct BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	LAUNCH_DIRECT				= "launch_direct";		//$NON-NLS-1$

	/**
	 * method_args CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	METHOD_ARGS					= "method_args";		//$NON-NLS-1$

	/**
	 * method_type CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	METHOD_TYPE					= "method_type";		//$NON-NLS-1$

	/**
	 * method_verb CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	METHOD_VERB					= "method_verb";		//$NON-NLS-1$

	/**
	 * run_as_server BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	RUN_AS_SERVER				= "run_as_server";		//$NON-NLS-1$

	/**
	 * timeout_default INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	TIMEOUT_DEFAULT				= "timeout_default";	//$NON-NLS-1$

	/**
	 * timeout_max INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	TIMEOUT_MAX					= "timeout_max";		//$NON-NLS-1$

	/**
	 * timeout_min INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	TIMEOUT_MIN					= "timeout_min";		//$NON-NLS-1$

	/**
	 * trace_launch BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	TRACE_LAUNCH				= "trace_launch";		//$NON-NLS-1$

	/**
	 * dm_method
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_METHOD				= "dm_method";			//$NON-NLS-1$

	/**
	 * use_method_content BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	USE_METHOD_CONTENT			= "use_method_content"; //$NON-NLS-1$

	/**
	 * use_method_server BOOLEAN
	 * 
	 * @since 1.0.3
	 */
	public static final String	USE_METHOD_SERVER			= "use_method_server";	//$NON-NLS-1$

}
