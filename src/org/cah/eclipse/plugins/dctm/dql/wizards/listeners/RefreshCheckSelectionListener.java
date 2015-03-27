/**
 * $Log: RefreshCheckSelectionListener.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:31:44  madcook
 * Version 3.0.0 work started and moved wizards to their own packages.
 *
 * Revision 1.6  2005/03/25 09:22:07  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/10 11:33:38  harpechr
 * Safety commit!
 *
 * Revision 1.4  2005/02/09 14:01:41  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:58  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:20  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:51  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.wizards.listeners;

import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.wizards.connection.ConnectionPage;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

/**
 * <H4>Export query results functionality.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Dec 3, 2004 2:11:43 PM.</DD>
 * </DT>
 * </DL>
 * <p>
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * </p>
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the <a
 * href="http://www.gnu.org/licenses/gpl.html">GNU General Public License </a> for more details.
 * </p>
 * 
 * @author Christopher Harper account : HARPECHR
 * @version 3.0.0
 * @since 1.0
 */
public class RefreshCheckSelectionListener
											implements
												SelectionListener
{

	/**
	 * The connection page containing the refresh check box.
	 * 
	 * @since 1.0
	 */
	private ConnectionPage	connectionPage	= null;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 2:24:51 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aConnectionPage
	 *            the calling connection page.
	 */
	public RefreshCheckSelectionListener(final ConnectionPage aConnectionPage)
	{

		super();
		setConnectionPage(aConnectionPage);
	}

	/**
	 * Do nothing.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 2:12:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param selectionEvent
	 *            an event containing information about the selection
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(final SelectionEvent selectionEvent)
	{

		/* Do nothing. */

	}

	/**
	 * Event that is triggered when the check box is selected.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 2:12:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param selectionEvent
	 *            an event containing information about the selection
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(final SelectionEvent selectionEvent)
	{

		ConnectionView.refreshDocbases();
		getConnectionPage().initDocbases();
		if (selectionEvent.getSource() instanceof Button)
		{
			Button refreshButton = (Button) selectionEvent.getSource();
			refreshButton.setSelection(false);
		}
	}

	/**
	 * ge the connection page.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 2:21:15 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the connection page.
	 */
	protected ConnectionPage getConnectionPage()
	{

		return this.connectionPage;
	}

	/**
	 * Set the connection page.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 2:21:06 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aConnectionPage
	 *            the connection page to set.
	 */
	protected void setConnectionPage(final ConnectionPage aConnectionPage)
	{

		this.connectionPage = aConnectionPage;
	}

}