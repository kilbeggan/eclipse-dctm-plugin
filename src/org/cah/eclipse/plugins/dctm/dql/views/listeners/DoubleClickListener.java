/*-
 * $Log: DoubleClickListener.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:29:56  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.6  2005/03/25 09:21:39  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/10 11:33:38  harpechr
 * Safety commit!
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

import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.Model;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;


/**
 * <H4>A listener which is notified of double-click events in the connection
 * view.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 25, 2004 3:43:37 PM.</DD>
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
public class DoubleClickListener
								implements
									IDoubleClickListener
{

	/**
	 * The connectin view.
	 * 
	 * @since 1.0
	 */
	private ConnectionView	connectionView	= null;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 3:43:37 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aConnectionView
	 *            the connection view.
	 */
	public DoubleClickListener(final ConnectionView aConnectionView)
	{

		super();
		setConnectionView(aConnectionView);
	}

	/**
	 * Notifies of a double click and if the selected model is a connection
	 * model a connection to the Docbase will be opened.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 3:43:37 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param event
	 *            event object describing the double-click
	 * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
	 */
	public void doubleClick(final DoubleClickEvent event)
	{

		final Model model = getConnectionView().getSelectedModel();
		if(model instanceof ConnectionModel)
		{
			getConnectionView().getConnectAction().run();
		}
	}

	/**
	 * Get the connection view.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 3:45:39 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the connection view.
	 */
	protected ConnectionView getConnectionView()
	{

		return this.connectionView;
	}

	/**
	 * Set the connection view.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 3:45:36 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aConnectionView
	 *            the view to set.
	 */
	protected void setConnectionView(final ConnectionView aConnectionView)
	{

		this.connectionView = aConnectionView;
	}
}
