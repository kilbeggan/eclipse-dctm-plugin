/*-
 * $Log: TabFolderListener.java,v $
 * Revision 1.7  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.6  2005/12/04 20:29:56  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.5  2005/03/25 09:21:39  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 14:01:44  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:57  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:19  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:51  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.views.listeners;

import org.eclipse.swt.custom.CTabFolder2Listener;
import org.eclipse.swt.custom.CTabFolderEvent;


/**
 * <H4>Remove all tab folder events from the result view..</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 26, 2004 4:49:42 PM.</DD>
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
 * @since 1.0
 */
public class TabFolderListener
								implements
									CTabFolder2Listener
{

	/**
	 * Sole constructor
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 4:49:42 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public TabFolderListener()
	{

		super();
	}

	/**
	 * <p>
	 * <b>Does nothing!!! </b>
	 * </p>
	 * <p>
	 * Sent when the user clicks on the close button of an item in the
	 * CTabFolder. The item being closed is specified in the event.item field.
	 * Setting the event.doit field to false will stop the CTabItem from
	 * closing. When the CTabItem is closed, it is disposed. The contents of the
	 * CTabItem (see CTabItem.setControl) will be made not visible when the
	 * CTabItem is closed.
	 * </p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 4:58:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param event
	 *            an event indicating the item being closed.
	 * @see org.eclipse.swt.custom.CTabFolder2Listener#close(org.eclipse.swt.custom.CTabFolderEvent)
	 */
	public void close(CTabFolderEvent event)
	{

		/* Disable all actions. */
	}

	/**
	 * <p>
	 * <b>Does nothing!!! </b>
	 * </p>
	 * Sent when the user clicks on the minimize button of a CTabFolder. The
	 * state of the CTabFolder does not change automatically - it is up to the
	 * application to change the state of the CTabFolder in response to this
	 * event using CTabFolder.setMinimized(true).
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 4:58:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param event
	 *            an event containing information about the minimize.
	 * @see org.eclipse.swt.custom.CTabFolder2Listener#maximize(org.eclipse.swt.custom.CTabFolderEvent)
	 */
	public void maximize(CTabFolderEvent event)
	{

		/* Disable all actions. */
	}

	/**
	 * <p>
	 * <b>Does nothing!!! </b>
	 * </p>
	 * Sent when the user clicks on the maximize button of a CTabFolder. The
	 * state of the CTabFolder does not change automatically - it is up to the
	 * application to change the state of the CTabFolder in response to this
	 * event using CTabFolder.setMaximized(true).
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 4:58:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param event
	 *            an event containing information about the maximize.
	 * @see org.eclipse.swt.custom.CTabFolder2Listener#minimize(org.eclipse.swt.custom.CTabFolderEvent)
	 */
	public void minimize(CTabFolderEvent event)
	{

		/* Disable all actions. */
	}

	/**
	 * <p>
	 * <b>Does nothing!!! </b>
	 * </p>
	 * Sent when the user clicks on the restore button of a CTabFolder. This
	 * event is sent either to restore the CTabFolder from the minimized state
	 * or from the maximized state. To determine which restore is requested, use
	 * CTabFolder.getMinimized() or CTabFolder.getMaximized() to determine the
	 * current state. The state of the CTabFolder does not change automatically -
	 * it is up to the application to change the state of the CTabFolder in
	 * response to this event using CTabFolder.setMaximized(false) or
	 * CTabFolder.setMinimized(false).
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 4:58:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param event
	 *            an event containing information about the restore.
	 * @see org.eclipse.swt.custom.CTabFolder2Listener#restore(org.eclipse.swt.custom.CTabFolderEvent)
	 */
	public void restore(CTabFolderEvent event)
	{

		/* Disable all actions. */
	}

	/**
	 * <p>
	 * <b>Does nothing!!! </b>
	 * </p>
	 * Sent when the user clicks on the chevron button of the CTabFolder. A
	 * chevron appears in the CTabFolder when there are more tabs than can be
	 * displayed at the current widget size. To select a tab that is not
	 * currently visible, the user clicks on the chevron and selects a tab item
	 * from a list. By default, the CTabFolder provides a list of all the items
	 * that are not currently visible, however, the application can provide its
	 * own list by setting the event.doit field to <code>false</code> and
	 * displaying a selection list.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 4:58:04 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param event
	 *            an event containing information about the show list.
	 * @see org.eclipse.swt.custom.CTabFolder2Listener#showList(org.eclipse.swt.custom.CTabFolderEvent)
	 */
	public void showList(CTabFolderEvent event)
	{

		/* Disable all actions. */
	}
}
