/*-
 * $Log: IDmiQueueItem.java,v $
 * Revision 1.4  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.3  2005/12/04 20:24:54  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.2  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.1  2005/03/25 09:24:29  harpechr
 * Queue item attributes defined.
 *
 */

package org.cah.dctm.bof.tbo.persistent.dmiqueueitem;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;


/**
 * <H4>Inbox queue type.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>27.2.2005 16:57:55.</DD>
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
 * @author Christopher Harper account: HARPEC
 * @version 3.0.0
 * @since 1.0.5
 */
public interface IDmiQueueItem
								extends
									IPersistentObject
{

	/**
	 * a_content_type CHAR(32)
	 * 
	 * @since 1.0.5
	 */
	public static final String	A_CONTENT_TYPE		= "a_content_type";	//$NON-NLS-1$

	/**
	 * a_operations CHAR(16)
	 * 
	 * @since 1.0.5
	 */
	public static final String	A_OPERATIONS		= "a_operations";		//$NON-NLS-1$

	/**
	 * actual_start_date TIME
	 * 
	 * @since 1.0.5
	 */
	public static final String	ACTUAL_START_DATE	= "actual_start_date";	//$NON-NLS-1$

	/**
	 * content_type CHAR(32)
	 * 
	 * @since 1.0.5
	 */
	public static final String	CONTENT_TYPE		= "content_type";		//$NON-NLS-1$

	/**
	 * date_sent TIME
	 * 
	 * @since 1.0.5
	 */
	public static final String	DATE_SENT			= "date_sent";			//$NON-NLS-1$

	/**
	 * delete_flag BOOLEAN
	 * 
	 * @since 1.0.5
	 */
	public static final String	DELETE_FLAG			= "delete_flag";		//$NON-NLS-1$

	/**
	 * dependency_type CHAR(10)
	 * 
	 * @since 1.0.5
	 */
	public static final String	DEPENDENCY_TYPE		= "dependency_type";	//$NON-NLS-1$

	/**
	 * dequeued_by CHAR(32)
	 * 
	 * @since 1.0.5
	 */
	public static final String	DEQUEUED_BY			= "dequeued_by";		//$NON-NLS-1$

	/**
	 * dequeued_date TIME
	 * 
	 * @since 1.0.5
	 */
	public static final String	DEQUEUED_DATE		= "dequeued_date";		//$NON-NLS-1$

	/**
	 * due_date TIME
	 * 
	 * @since 1.0.5
	 */
	public static final String	DUE_DATE			= "due_date";			//$NON-NLS-1$

	/**
	 * event CHAR(64)
	 * 
	 * @since 1.0.5
	 */
	public static final String	EVENT				= "event";				//$NON-NLS-1$

	/**
	 * instruction_page INTEGER
	 * 
	 * @since 1.0.5
	 */
	public static final String	INSTRUCTION_PAGE	= "instruction_page";	//$NON-NLS-1$

	/**
	 * item_id ID
	 * 
	 * @since 1.0.5
	 */
	public static final String	ITEM_ID				= "item_id";			//$NON-NLS-1$

	/**
	 * item_name CHAR(255)
	 * 
	 * @since 1.0.5
	 */
	public static final String	ITEM_NAME			= "item_name";			//$NON-NLS-1$

	/**
	 * item_type CHAR(32)
	 * 
	 * @since 1.0.5
	 */
	public static final String	ITEM_TYPE			= "item_type";			//$NON-NLS-1$

	/**
	 * message CHAR(255)
	 * 
	 * @since 1.0.5
	 */
	public static final String	MESSAGE				= "message";			//$NON-NLS-1$

	/**
	 * name CHAR(32)
	 * 
	 * @since 1.0.5
	 */
	public static final String	NAME				= "name";				//$NON-NLS-1$

	/**
	 * next_tasks_type CHAR(10)
	 * 
	 * @since 1.0.5
	 */
	public static final String	NEXT_TASKS_TYPE		= "next_tasks_type";	//$NON-NLS-1$

	/**
	 * plan_start_date TIME
	 * 
	 * @since 1.0.5
	 */
	public static final String	PLAN_START_DATE		= "plan_start_date";	//$NON-NLS-1$

	/**
	 * position DOUBLE
	 * 
	 * @since 1.0.5
	 */
	public static final String	POSITION			= "position";			//$NON-NLS-1$

	/**
	 * priority INTEGER
	 * 
	 * @since 1.0.5
	 */
	public static final String	PRIORITY			= "priority";			//$NON-NLS-1$

	/**
	 * read_flag BOOLEAN
	 * 
	 * @since 1.0.5
	 */
	public static final String	READ_FLAG			= "read_flag";			//$NON-NLS-1$

	/**
	 * remote_pending BOOLEAN
	 * 
	 * @since 1.0.5
	 */
	public static final String	REMOTE_PENDING		= "remote_pending";	//$NON-NLS-1$

	/**
	 * router_id ID
	 * 
	 * @since 1.0.5
	 */
	public static final String	ROUTER_ID			= "router_id";			//$NON-NLS-1$

	/**
	 * sent_by CHAR(32)
	 * 
	 * @since 1.0.5
	 */
	public static final String	SENT_BY				= "sent_by";			//$NON-NLS-1$

	/**
	 * sign_off_date TIME
	 * 
	 * @since 1.0.5
	 */
	public static final String	SIGN_OFF_DATE		= "sign_off_date";		//$NON-NLS-1$

	/**
	 * sign_off_required BOOLEAN
	 * 
	 * @since 1.0.5
	 */
	public static final String	SIGN_OFF_REQUIRED	= "sign_off_required";	//$NON-NLS-1$

	/**
	 * sign_off_user CHAR(32)
	 * 
	 * @since 1.0.5
	 */
	public static final String	SIGN_OFF_USER		= "sign_off_user";		//$NON-NLS-1$

	/**
	 * source_docbase CHAR(120)
	 * 
	 * @since 1.0.5
	 */
	public static final String	SOURCE_DOCBASE		= "source_docbase";	//$NON-NLS-1$

	/**
	 * source_event ID
	 * 
	 * @since 1.0.5
	 */
	public static final String	SOURCE_EVENT		= "source_event";		//$NON-NLS-1$

	/**
	 * source_stamp INTEGER
	 * 
	 * @since 1.0.5
	 */
	public static final String	SOURCE_STAMP		= "source_stamp";		//$NON-NLS-1$

	/**
	 * stamp ID
	 * 
	 * @since 1.0.5
	 */
	public static final String	STAMP				= "stamp";				//$NON-NLS-1$

	/**
	 * supervisor_name CHAR(32)
	 * 
	 * @since 1.0.5
	 */
	public static final String	SUPERVISOR_NAME		= "supervisor_name";	//$NON-NLS-1$

	/**
	 * target_docbase CHAR(120)
	 * 
	 * @since 1.0.5
	 */
	public static final String	TARGET_DOCBASE		= "target_docbase";	//$NON-NLS-1$

	/**
	 * task_name CHAR(32)
	 * 
	 * @since 1.0.5
	 */
	public static final String	TASK_NAME			= "task_name";			//$NON-NLS-1$

	/**
	 * task_number CHAR(5)
	 * 
	 * @since 1.0.5
	 */
	public static final String	TASK_NUMBER			= "task_number";		//$NON-NLS-1$

	/**
	 * task_state CHAR(10)
	 * 
	 * @since 1.0.5
	 */
	public static final String	TASK_STATE			= "task_state";		//$NON-NLS-1$

	/**
	 * task_type CHAR(10)
	 * 
	 * @since 1.0.5
	 */
	public static final String	TASK_TYPE			= "task_type";			//$NON-NLS-1$

	/**
	 * Name of the type.
	 * 
	 * @since 1.0.5
	 */
	public static final String	TYPE_DMI_QUEUE_ITEM	= "dmi_queue_item";	//$NON-NLS-1$

}
