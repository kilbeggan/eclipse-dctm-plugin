/*-
 * $Log: SelectionChangedListener.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:29:56  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.6  2005/03/25 09:21:39  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/09 14:01:44  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.4  2005/01/24 12:34:57  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.3  2005/01/18 07:24:49  harpechr
 * Version 1.0.3 features added. Mainly relation related modifications.
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
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionContentProvider;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.DocumentModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.FolderModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.GroupModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.GroupsModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.InboxModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.Model;
import org.cah.eclipse.plugins.dctm.dql.views.connection.RelationTypeModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.TableModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.TypeModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.UsersModel;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;


/**
 * <H4>A listener which is notified when the connection view tree selection
 * changes.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 26, 2004 4:07:03 PM.</DD>
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
public class SelectionChangedListener
										implements
											ISelectionChangedListener
{

	/**
	 * The connection view.
	 * 
	 * @since 1.0
	 */
	private ConnectionView	connectionView	= null;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 4:07:03 PM</DD>
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
	public SelectionChangedListener(final ConnectionView aConnectionView)
	{

		super();
		setConnectionView(aConnectionView);
	}

	/**
	 * Notifies that the selection has changed from one tree node to another.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 4:07:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param event
	 *            object describing the change.
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(final SelectionChangedEvent event)
	{

		IStructuredSelection structuredSelection = (IStructuredSelection) event
			.getSelection();
		if(structuredSelection.size() > 0)
		{
			Object selected = structuredSelection.getFirstElement();
			if((selected != null) && (selected instanceof Model))
			{
				final Model selectedModel = (Model) selected;
				final ConnectionModel selectedConnectionModel = ConnectionContentProvider
					.getInstance().getParentConnectionModel(selectedModel);
				if((selectedConnectionModel != null)
					&& (ConnectionView.getInstance().getSelectedConnection() != null)
					&& (!ConnectionView.getInstance().getSelectedConnection()
						.equals(selectedConnectionModel)))
				{
					ConnectionView.getInstance().setSelectedConnection(
						selectedConnectionModel);
				}
				if(selectedModel instanceof TypeModel)
				{
					getConnectionView().getSelectTypeAction().run();
				} else if(selectedModel instanceof TableModel)
				{
					getConnectionView().getSelectTableAction().run();
				} else if(selectedModel instanceof FolderModel)
				{
					getConnectionView().getSelectFolderAction().run();
				} else if(selectedModel instanceof DocumentModel)
				{
					getConnectionView().getSelectDocumentAction().run();
				} else if((selectedModel instanceof GroupsModel)
					|| (selectedModel instanceof GroupModel))
				{
					getConnectionView().getSelectGroupsAction().run();
				} else if(selectedModel instanceof UsersModel)
				{
					getConnectionView().getSelectUsersAction().run();
				} else if(selectedModel instanceof RelationTypeModel)
				{
					getConnectionView().getSelectRelationTypeModelAction()
						.run();
				} else if(selectedModel instanceof InboxModel)
				{
					getConnectionView().getSelectInboxAction().run();
				}
			}
		}
	}

	/**
	 * Get the connection view.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 4:19:59 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the tree viewer.
	 */
	protected ConnectionView getConnectionView()
	{

		return this.connectionView;
	}

	/**
	 * Set the connection view.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 4:17:41 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aConnectionView
	 */
	protected void setConnectionView(final ConnectionView aConnectionView)
	{

		this.connectionView = aConnectionView;
	}
}
