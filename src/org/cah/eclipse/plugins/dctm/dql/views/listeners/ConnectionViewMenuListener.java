/*-
 * $Log: ConnectionViewMenuListener.java,v $
 * Revision 1.20  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.19  2005/12/04 20:29:56  madcook
 * Version 3.0.0 work started.
 * 
 * Revision 1.18 2005/11/21 14:53:38 madcook 
 * Old 1.4 style code removed. 
 * 
 * Revision 1.17 2005/11/21 13:04:32 madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5
 * settings. 
 * 
 * Revision 1.16 2005/04/01 11:28:26 harpechr 
 * Reference to a static string changed to an interface. 
 * 
 * Revision 1.15 2005/03/25 09:21:39 harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features. 
 * 
 * Revision 1.14 2005/02/09 13:55:19 harpechr 
 * Added previous DQL queries functionality and modified the message view so 
 * that its real time. 
 * 
 * Revision 1.13 2005/02/08 08:40:47 harpechr 
 * Moved format statistics from the connection model to the folder and document 
 * model. 
 * 
 * Revision 1.12 2005/02/07 13:51:38 harpechr 
 * Create count type frequency DQL action added. 
 * 
 * Revision 1.11 2005/02/07 13:14:52 harpechr 
 * Create count fulltext indexed (& failed) DQL action added. 
 * 
 * Revision 1.10 2005/02/07 12:37:35 harpechr 
 * Create fulltext indexed DQL action added.
 * 
 * Revision 1.9 2005/02/07 11:03:53 harpechr 
 * Create waiting for fulltext indexing DQL action added. 
 * 
 * Revision 1.8 2005/02/07 10:12:09 harpechr 
 * Create count content distribution DQL action added. 
 * 
 * Revision 1.7 2005/02/02 08:28:00 harpechr
 * Create count users documents DQL action defined. 
 * 
 * Revision 1.6 2005/01/25 20:18:54 harpechr 
 * Create content format statistics DQL statement action defined. 
 * 
 * Revision 1.5 2005/01/24 22:23:40 harpechr
 * Create change default storage DQL statement action defined. 
 * 
 * Revision 1.4 2005/01/24 12:34:57 harpechr 
 * Version 1.0.4 work started. 
 * 
 * Revision 1.3 2005/01/18 07:24:49 harpechr 
 * Version 1.0.3 features added. Mainly relation related modifications. 
 * 
 * Revision 1.2 2005/01/11 14:02:18 harpechr
 * Changed version number from 1.0.2 to 1.0.3. 
 * 
 * Revision 1.1 2005/01/07 12:37:51 harpechr 
 * Version 1.0.2 code. First CVS commit!
 */

package org.cah.eclipse.plugins.dctm.dql.views.listeners;

import org.cah.eclipse.plugins.dctm.dql.DCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.PreviousDQLStatementAction;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.AspectModuleModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionContentProvider;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.DocumentModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.FolderModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.GroupModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.GroupsModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.InboxModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.Model;
import org.cah.eclipse.plugins.dctm.dql.views.connection.OtherModuleModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.RelationTypeModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.SBOModuleModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.TBOModuleModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.TableModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.TypeModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.TypesModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.UsersModel;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;


/**
 * <H4>Connectino view menu listener that gets informed when a menu is about to
 * show.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 25, 2004 4:06:54 PM.</DD>
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
public class ConnectionViewMenuListener
										implements
											IMenuListener
{

	/**
	 * Sub menu for API statements.
	 * 
	 * @since 1.0.4
	 */
	public static final String	SUBMENU_API			= "API";		//$NON-NLS-1$

	/**
	 * Sub menu for DQL statements.
	 * 
	 * @since 1.0.4
	 */
	public static final String	SUBMENU_DQL			= "DQL";		//$NON-NLS-1$

	/**
	 * Sub menu for previous statements.
	 * 
	 * @since 1.0.5
	 */
	private static final String	SUBMENU_PREVIOUS	= "Previous";	//$NON-NLS-1$

	/**
	 * Sub menu for BOF related actions 24-Nov-2005.
	 * 
	 * @since 3.0.0
	 */
	private static final String	SUBMENU_BOF			= "BOF";		//$NON-NLS-1$

	/**
	 * The connection view instance.
	 * 
	 * @since 1.0
	 */
	private ConnectionView		connectionView		= null;

	/**
	 * Sole constructor
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 4:06:54 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @param aConnectionView
	 *            the connection view instance.
	 * @since 1.0
	 */
	public ConnectionViewMenuListener(final ConnectionView aConnectionView)
	{

		super();
		setConnectionView(aConnectionView);
	}

	/**
	 * Notifies this listener that the menu is about to be shown by the given
	 * menu manager. Based on the tree selection different actions will be
	 * placed into the menu that is about to show.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 4:10:08 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param manager
	 *            the menu manager.
	 * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
	public void menuAboutToShow(final IMenuManager manager)
	{

		final IMenuManager dqlSubmenu = new MenuManager(
			ConnectionViewMenuListener.SUBMENU_DQL);
		final IMenuManager apiSubmenu = new MenuManager(
			ConnectionViewMenuListener.SUBMENU_API);
		final IMenuManager previousSubmenu = new MenuManager(
			ConnectionViewMenuListener.SUBMENU_PREVIOUS);
		final IMenuManager modulesSubMenu = new MenuManager(
			ConnectionViewMenuListener.SUBMENU_BOF);
		if(DCTMPlugin.isViewsNotActivated())
		{
			DCTMPlugin.activateViews();
		}
		final Model model = getConnectionView().getSelectedModel();
		if(model == null)
		{
			manager.add(getConnectionView().getNewConnectionAction());
		} else
		{
			if(getConnectionView().getSelectedConnection() != null)
			{
				if(getConnectionView().getSelectedConnection().isConnected())
				{
					for(final PreviousDQLStatementAction statement: ConnectionContentProvider
						.getInstance().getExecutedDQLStatements())
					{
						previousSubmenu.add(statement);
					}
				}
			}
			if(model instanceof ConnectionModel)
			{
				final ConnectionModel connectionModel = (ConnectionModel) model;
				if(!connectionModel.isConnected())
				{
					manager.add(getConnectionView().getConnectAction());
					manager.add(getConnectionView().getEditConnectionAction());
				} else
				{
					manager.add(getConnectionView().getDisconnectAction());
					manager.add(getConnectionView().getExecuteAction());
					manager.add(getConnectionView().getExecuteNewAction());
				}
				manager.add(getConnectionView().getRemoveConnectionAction());
				manager.add(getConnectionView().getNewConnectionAction());
			} else if(model instanceof TypesModel)
			{
				dqlSubmenu.add(getConnectionView().getCreateTypeDQLAction());
				manager.add(getConnectionView().getCreateTBOsAction());
				manager.add(getConnectionView().getNewDqlEditorAction());
				manager.add(getConnectionView().getNewApiEditorAction());
			} else if(model instanceof TypeModel)
			{
				manager.add(getConnectionView().getSelectTypeAction());
				manager.add(getConnectionView().getNewDqlEditorAction());
				manager.add(getConnectionView().getNewApiEditorAction());
				apiSubmenu
					.add(getConnectionView().getCreateRelationAPIAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateReturnAllDqlAction());
				dqlSubmenu.add(getConnectionView().getCreateTypeDQLAction());
				dqlSubmenu.add(getConnectionView()
					.getChangeDefaultStorageStatementAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateCountObjectTypeFrequencyAction());
			} else if(model instanceof TableModel)
			{
				manager.add(getConnectionView().getSelectTableAction());
				manager.add(getConnectionView().getNewDqlEditorAction());
				manager.add(getConnectionView().getNewApiEditorAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateReturnAllDqlAction());
			} else if(model instanceof FolderModel)
			{
				manager.add(getConnectionView().getSelectFolderAction());
				manager.add(getConnectionView().getNewDqlEditorAction());
				manager.add(getConnectionView().getNewApiEditorAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateCountUsersDocumentsAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateCountContentDistributionAction());
				dqlSubmenu.add(getConnectionView().getCreateIndexedAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateWaitingForIndexingAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateCountIndexedAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateFormatStatisticsAction());
			} else if(model instanceof DocumentModel)
			{
				manager.add(getConnectionView().getSelectDocumentAction());
				manager.add(getConnectionView().getNewDqlEditorAction());
				manager.add(getConnectionView().getNewApiEditorAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateCountUsersDocumentsAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateCountContentDistributionAction());
				dqlSubmenu.add(getConnectionView().getCreateIndexedAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateWaitingForIndexingAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateCountIndexedAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateFormatStatisticsAction());
			} else if((model instanceof GroupsModel)
				|| (model instanceof GroupModel))
			{
				manager.add(getConnectionView().getSelectGroupsAction());
				manager.add(getConnectionView().getNewDqlEditorAction());
				manager.add(getConnectionView().getNewApiEditorAction());
				apiSubmenu.add(getConnectionView().getCreateGroupAPIAction());
			} else if(model instanceof UsersModel)
			{
				manager.add(getConnectionView().getSelectUsersAction());
				manager.add(getConnectionView().getNewDqlEditorAction());
				manager.add(getConnectionView().getNewApiEditorAction());
				apiSubmenu.add(getConnectionView().getCreateUserAPIAction());
				dqlSubmenu.add(getConnectionView()
					.getCreateCountUsersDocumentsAction());
			} else if(model instanceof RelationTypeModel)
			{
				manager.add(getConnectionView()
					.getSelectRelationTypeModelAction());
				manager.add(getConnectionView().getNewDqlEditorAction());
				manager.add(getConnectionView().getNewApiEditorAction());
				apiSubmenu
					.add(getConnectionView().getCreateRelationAPIAction());
			} else if(model instanceof InboxModel)
			{
				manager.add(getConnectionView().getSelectInboxAction());
				manager.add(getConnectionView().getNewDqlEditorAction());
				manager.add(getConnectionView().getNewApiEditorAction());
			} else if(model instanceof AspectModuleModel)
			{
				modulesSubMenu.add(getConnectionView().getSelectInboxAction());
			} else if(model instanceof SBOModuleModel)
			{
				modulesSubMenu.add(getConnectionView().getSelectInboxAction());
			} else if(model instanceof TBOModuleModel)
			{
				modulesSubMenu.add(getConnectionView().getNewTBOAction());
			} else if(model instanceof OtherModuleModel)
			{
				modulesSubMenu.add(getConnectionView().getSelectInboxAction());
			}
			manager.add(getConnectionView().getRefreshAction());
		}
		if(!dqlSubmenu.isEmpty())
		{
			manager.add(new Separator(ConnectionViewMenuListener.SUBMENU_DQL));
			manager.appendToGroup(ConnectionViewMenuListener.SUBMENU_DQL,
				dqlSubmenu);
		}
		if(!apiSubmenu.isEmpty())
		{
			manager.add(new Separator(ConnectionViewMenuListener.SUBMENU_API));
			manager.appendToGroup(ConnectionViewMenuListener.SUBMENU_API,
				apiSubmenu);
		}
		if(!previousSubmenu.isEmpty())
		{
			manager.add(new Separator(
				ConnectionViewMenuListener.SUBMENU_PREVIOUS));
			manager.appendToGroup(ConnectionViewMenuListener.SUBMENU_PREVIOUS,
				previousSubmenu);
		}
		if(!modulesSubMenu.isEmpty())
		{
			manager.add(new Separator(ConnectionViewMenuListener.SUBMENU_BOF));
			manager.appendToGroup(ConnectionViewMenuListener.SUBMENU_BOF,
				modulesSubMenu);
		}
	}

	/**
	 * Get the connection view.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 4:14:01 PM</DD>
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
	 * <DD>Nov 25, 2004 4:12:36 PM</DD>
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
