/*-
 * $Log: ConnectionView.java,v $
 * Revision 1.17  2005/12/04 20:29:56  madcook
 * Version 3.0.0 work started.
 * 
 * 
 * Revision 1.16 2005/11/21 13:04:32 madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5
 * settings. 
 * Revision 1.15 2005/04/01 11:28:07 harpechr 
 * Reference to a static string changed to an interface. 
 * 
 * Revision 1.14 2005/03/25 09:18:59 harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features. 
 * 
 * Revision 1.13 2005/02/09 14:01:43 harpechr 
 * Version 1.0.5 work started. 
 * 
 * Revision 1.12 2005/02/07 13:51:32 harpechr 
 * Create count type frequency DQL action added.
 * 
 * Revision 1.11 2005/02/07 13:14:51 harpechr 
 * Create count fulltext indexed (& failed) DQL action added. 
 * 
 * Revision 1.10 2005/02/07 12:37:31 harpechr 
 * Create fulltext indexed DQL action added. 
 * 
 * Revision 1.9 2005/02/07 11:03:53 harpechr
 * Create waiting for fulltext indexing DQL action added. 
 * 
 * Revision 1.8 2005/02/07 10:12:08 harpechr
 * Create count content distribution DQL action added. 
 * 
 * Revision 1.7 2005/02/02 08:27:59 harpechr
 * Create count users documents DQL action defined. 
 * 
 * Revision 1.6 2005/01/25 20:18:54 harpechr 
 * Create content format statistics DQL statement action defined. 
 * 
 * Revision 1.5 2005/01/24 22:23:36 harpechr 
 * Create change default storage DQL statement action defined.
 * 
 * Revision 1.4 2005/01/24 12:34:57 harpechr 
 * Version 1.0.4 work started.
 * 
 * Revision 1.3 2005/01/18 07:24:48 harpechr
 * Version 1.0.3 features added. Mainly relation related modifications. 
 * 
 * Revision 1.2 2005/01/11 14:02:19 harpechr 
 * Changed version number from 1.0.2 to 1.0.3. 
 * 
 * Revision 1.1 2005/01/07 12:37:51 harpechr 
 * Version 1.0.2 code. First CVS commit!
 */

package org.cah.eclipse.plugins.dctm.dql.views;

import java.util.Vector;

import org.cah.eclipse.plugins.dctm.dql.DCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.IImageCache;
import org.cah.eclipse.plugins.dctm.dql.ImageCache;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.ConnectAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.CreateTBOsAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.DisconnectAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.EditConnectionAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.ExecuteAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.ExecuteNewAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.NewApiEditorAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.NewConnectionAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.NewDqlEditorAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.RefreshAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.RemoveConnectionAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.SelectDocumentAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.SelectFolderAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.SelectGroupsAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.SelectInboxAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.SelectRelationTypeModelAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.SelectTableAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.SelectTypeAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.SelectUsersAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.api.CreateGroupAPIAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.api.CreateRelationAPIAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.api.CreateUserAPIAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.ChangeDefaultStorageStatementAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.CreateCountContentDistributionAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.CreateCountIndexedAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.CreateCountObjectTypeFrequencyAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.CreateCountUsersDocumentsAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.CreateFormatStatisticsAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.CreateIndexedAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.CreateReturnAllDqlAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.CreateTypeDQLAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.CreateWaitingForIndexingAction;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.module.NewTBOAction;
import org.cah.eclipse.plugins.dctm.dql.editors.APIEditor;
import org.cah.eclipse.plugins.dctm.dql.editors.DQLEditor;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionContentProvider;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionLabelProvider;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.DocbaseModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.Model;
import org.cah.eclipse.plugins.dctm.dql.views.listeners.ConnectionViewMenuListener;
import org.cah.eclipse.plugins.dctm.dql.views.listeners.DoubleClickListener;
import org.cah.eclipse.plugins.dctm.dql.views.listeners.SelectionChangedListener;
import org.cah.eclipse.plugins.dctm.dql.views.listeners.TreeViewListener;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.part.ViewPart;

import com.documentum.fc.client.IDfDocbaseMap;
import com.documentum.fc.common.DfException;


/**
 * <H4>A workbech view holding the connection model and it's sub nodes.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 24, 2004 3:37:37 PM.</DD>
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
public class ConnectionView
							extends
								ViewPart
{

	/**
	 * Names of available Docbases.
	 * 
	 * @since 1.0
	 */
	public static final Vector<String>				DOCBASE_NAMES							= new Vector<String>();

	/**
	 * ID of the view.
	 * 
	 * @since 1.0
	 */
	public static final String						ID										= ConnectionView.class
																								.getName();

	/**
	 * Menu ID of the view.
	 * 
	 * @since 1.0
	 */
	public static String							MENU_ID									= '#' + ConnectionView.ID;

	/**
	 * First created instance of this view.
	 * 
	 * @since 1.0
	 */
	private static ConnectionView					instance								= null;

	/**
	 * synchronization helper.
	 * 
	 * @since 1.0
	 */
	private static final Object						SEMAPHORE								= new Object();

	/**
	 * Create change storage statement action.
	 * 
	 * @since 1.0.4
	 */
	private ChangeDefaultStorageStatementAction		changeDefaultStorageStatementAction		= null;

	/**
	 * The connect action.
	 * 
	 * @since 1.0
	 */
	private ConnectAction							connectAction							= null;

	/**
	 * The create count content distribution action.
	 * 
	 * @since 1.0.4
	 */
	private CreateCountContentDistributionAction	createCountContentDistributionAction	= null;

	/**
	 * The create count indexed DQL statement action.
	 * 
	 * @since 1.0.4
	 */
	private CreateCountIndexedAction				createCountIndexedAction;

	/**
	 * The create count object type frequency DQL statement action.
	 * 
	 * @since 1.0.4
	 */
	private CreateCountObjectTypeFrequencyAction	createCountObjectTypeFrequencyAction	= null;

	/**
	 * Create count users document action.
	 * 
	 * @since 1.0.4
	 */
	private CreateCountUsersDocumentsAction			createCountUsersDocumentsAction			= null;

	/**
	 * Create format statistics action.
	 * 
	 * @since 1.0.4
	 */
	private CreateFormatStatisticsAction			createFormatStatisticsAction			= null;

	/**
	 * Create group action.
	 * 
	 * @since 1.0.2
	 */
	private CreateGroupAPIAction					createGroupAPIAction					= null;

	/**
	 * The create select fulltext indexed DQL query action.
	 * 
	 * @since 1.0.4
	 */
	private CreateIndexedAction						createIndexedAction						= null;

	/**
	 * Create relation type action.
	 * 
	 * @since 1.0.3
	 */
	private CreateRelationAPIAction					createRelationAPIAction					= null;

	/**
	 * The create return all DQL statement action.
	 * 
	 * @since 1.0
	 */
	private CreateReturnAllDqlAction				createReturnAllDqlAction				= null;

	/**
	 * The create TBO's action.
	 * 
	 * @since 2.0.0
	 */

	private CreateTBOsAction						createTBOsAction						= null;

	/**
	 * The create new type DQL action.
	 * 
	 * @since 1.0.2
	 */
	private CreateTypeDQLAction						createTypeDQLAction						= null;

	/**
	 * Create user action.
	 * 
	 * @since 1.0.2
	 */
	private CreateUserAPIAction						createUserAPIAction						= null;

	/**
	 * The create waiting for fulltext indexing DQL statement action.
	 * 
	 * @since 1.0.4
	 */
	private CreateWaitingForIndexingAction			createWaitingForIndexingAction;

	/**
	 * Disconnect action.
	 * 
	 * @since 1.0
	 */
	private DisconnectAction						disconnectAction						= null;

	/**
	 * Edit connection action.
	 * 
	 * @since 1.0
	 */
	private EditConnectionAction					editConnectionAction					= null;

	/**
	 * Execute DQL / API action.
	 * 
	 * @since 1.0
	 */
	private ExecuteAction							executeAction							= null;

	/**
	 * Execute DQL into new result tables action.
	 * 
	 * @since 1.0
	 */
	private ExecuteNewAction						executeNewAction						= null;

	/**
	 * New API editor action.
	 * 
	 * @since 1.0
	 */
	private NewApiEditorAction						newApiEditorAction						= null;

	/**
	 * New connection action.
	 * 
	 * @since 1.0
	 */
	private NewConnectionAction						newConnectionAction						= null;

	/**
	 * New DQL editor action.
	 * 
	 * @since 1.0
	 */
	private NewDqlEditorAction						newDqlEditorAction						= null;

	/**
	 * Action to add a new TBO to the system. 25-Nov-2005
	 * 
	 * @since 3.0.0
	 */
	private NewTBOAction							newTBOAction							= null;

	/**
	 * Refresh model action.
	 * 
	 * @since 1.0
	 */
	private RefreshAction							refreshAction							= null;

	/**
	 * Remove connection action.
	 * 
	 * @since 1.0
	 */
	private RemoveConnectionAction					removeConnectionAction					= null;

	/**
	 * Select document action.
	 * 
	 * @since 1.0.1
	 */
	private SelectDocumentAction					selectDocumentAction					= null;

	/**
	 * Select folder action.
	 * 
	 * @since 1.0.1
	 */
	private SelectFolderAction						selectFolderAction						= null;

	/**
	 * Select groups content action.
	 * 
	 * @since 1.0.2
	 */
	private SelectGroupsAction						selectGroupsAction						= null;

	/**
	 * The action that selects the contents of a users inbox.
	 * 
	 * @since 1.0.5
	 */
	private SelectInboxAction						selectInboxAction						= null;

	/**
	 * Select relation type action.
	 * 
	 * @since 1.0
	 */
	private SelectRelationTypeModelAction			selectRelationTypeModelAction;

	/**
	 * Select table action.
	 * 
	 * @since 1.0
	 */
	private SelectTableAction						selectTableAction						= null;

	/**
	 * Select type action.
	 * 
	 * @since 1.0
	 */
	private SelectTypeAction						selectTypeAction						= null;

	/**
	 * Select users action.
	 * 
	 * @since 1.0.2
	 */
	private SelectUsersAction						selectUsersAction						= null;

	/**
	 * The model tree.
	 * 
	 * @since 1.0
	 */
	private TreeViewer								treeViewer								= null;

	/**
	 * The model tree listener.
	 * 
	 * @since 1.0
	 */
	private TreeViewListener						treeViewListener						= null;

	/**
	 * Always returns the first created instance of this class.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 24, 2004 4:11:00 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return instance of this class.
	 */
	public static ConnectionView getInstance()
	{

		synchronized(SEMAPHORE)
		{
			if(ConnectionView.instance == null)
			{
				new ConnectionView();
			}
		}
		return ConnectionView.instance;
	}

	/**
	 * Refresh Docbase information.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 2:10:19 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public static void refreshDocbases()
	{

		try
		{
			ConnectionView.DOCBASE_NAMES.clear();
			final IDfDocbaseMap docbaseMap = DCTMPlugin.getClient()
				.getDocbaseMap();
			for(int docbaseIndex = 0; docbaseIndex < docbaseMap
				.getDocbaseCount(); docbaseIndex++)
			{
				ConnectionView.DOCBASE_NAMES.addElement(docbaseMap
					.getDocbaseName(docbaseIndex));
			}
		} catch(DfException dex)
		{
			MessageView.getInstance().addMessage(
				"Failure returning Docbases.\n" + dex.getMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 24, 2004 3:37:37 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public ConnectionView()
	{

		super();
		ConnectionView.instance = this;
	}

	/**
	 * Add connection to the tree.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 5:22:23 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aConnectionModel
	 *            connection to add.
	 */
	public void addConnection(final ConnectionModel aConnectionModel)
	{

		ConnectionContentProvider.getInstance().getInvisibleRootModel()
			.addChild(aConnectionModel);
		changedConnection();
	}

	/**
	 * Set that a connection has changed.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 5:23:17 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public void changedConnection()
	{

		ConnectionContentProvider.getInstance().setHasChanged(true);
		if(getTreeViewer() != null)
		{
			getTreeViewer().refresh();
		}
	}

	/**
	 * Connect to a Docbase.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 9:18:02 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aConnectionModel
	 *            where to connect
	 */
	public void connect(final ConnectionModel aConnectionModel)
	{

		ConnectionContentProvider.getInstance().connect(aConnectionModel);
		getTreeViewListener().expandConnectionModel(aConnectionModel);
	}

	/**
	 * <p>
	 * Initialize the view part and creates the SWT controls for this workbench
	 * part.
	 * </p>
	 * <p>
	 * Clients should not call this method (the workbench calls this method when
	 * it needs to, which may be never).
	 * </p>
	 * <p>
	 * For implementors this is a multi-step process:
	 * <ol>
	 * <li>Create one or more controls within the parent.</li>
	 * <li>Set the parent layout as needed.</li>
	 * <li>Register any global actions with the <code>IActionService</code>.</li>
	 * <li>Register any popup menus with the <code>IActionService</code>.</li>
	 * <li>Register a selection provider with the
	 * <code>ISelectionService</code> (optional).</li>
	 * </ol>
	 * </p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 24, 2004 3:37:37 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aParent
	 *            the parent control
	 * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(final Composite aParent)
	{

		initActions();
		setTreeViewer(new TreeViewer(aParent));
		getTreeViewer().setContentProvider(
			ConnectionContentProvider.getInstance());
		getTreeViewer().setLabelProvider(ConnectionLabelProvider.getInstance());
		getTreeViewer().setInput(
			ConnectionContentProvider.getInstance().getInvisibleRootModel());

		setTreeViewListener(new TreeViewListener(getTreeViewer()));
		getTreeViewer().addTreeListener(getTreeViewListener());
		getTreeViewer().addSelectionChangedListener(
			new SelectionChangedListener(this));

		final MenuManager menuManager = new MenuManager(
			"ActionsMenu", ConnectionView.MENU_ID); //$NON-NLS-1$
		menuManager.setRemoveAllWhenShown(true);
		final Menu contextMenu = menuManager.createContextMenu(getTreeViewer()
			.getControl());
		getTreeViewer().getControl().setMenu(contextMenu);
		menuManager.addMenuListener(new ConnectionViewMenuListener(this));

		getTreeViewer().addDoubleClickListener(new DoubleClickListener(this));

	}

	/**
	 * Disconnect the Docbase session
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 11:03:22 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param connectionModel
	 *            connection to disconnect.
	 */
	public void disconnect(final ConnectionModel connectionModel)
	{

		ConnectionContentProvider.getInstance().disconnect(connectionModel);
		getTreeViewer().refresh();
	}

	/**
	 * Execute API statements.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 9:21:56 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param editor
	 *            the API editor whose content should be executed.
	 */
	public void executeAPI(final APIEditor editor)
	{

		ConnectionContentProvider.getInstance().executeAPI(editor);
	}

	/**
	 * Execute DQL statements.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 8:27:39 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param editor
	 *            the DQL editor whose content should be executed.
	 * @param newTab
	 *            should new result tables be created.
	 */
	public void executeDQL(final DQLEditor editor, final boolean newTab)
	{

		ConnectionContentProvider.getInstance().executeDQL(editor, newTab);
	}

	/**
	 * Open the connection model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:24:07 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aConnectionModel
	 *            the model to open.
	 */
	public void expandConnectionModel(final ConnectionModel aConnectionModel)
	{

		getTreeViewer().collapseToLevel(aConnectionModel,
			AbstractTreeViewer.ALL_LEVELS);
		getTreeViewer().refresh();
		getTreeViewer().expandToLevel(aConnectionModel, 1);
	}

	/**
	 * Open the Docbase model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:35:04 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDocbaseModel
	 *            the model to open.
	 */
	public void expandDocbaseModel(final DocbaseModel aDocbaseModel)
	{

		ConnectionContentProvider.getInstance().expandDocbaseModel(
			aDocbaseModel, false);
		getTreeViewer().collapseToLevel(aDocbaseModel,
			AbstractTreeViewer.ALL_LEVELS);
		getTreeViewer().refresh();
		getTreeViewer().expandToLevel(aDocbaseModel, 1);
	}

	/**
	 * Get the default storage statement action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 24, 2005 8:48:56 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @return the default storage action.
	 */
	public ChangeDefaultStorageStatementAction getChangeDefaultStorageStatementAction()
	{

		return this.changeDefaultStorageStatementAction;
	}

	/**
	 * Get the connect action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 10:53:54 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the connect action.
	 */
	public ConnectAction getConnectAction()
	{

		return this.connectAction;
	}

	/**
	 * Get the create count content distribution action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 7, 2005 10:53:07 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @return the action.
	 */
	public CreateCountContentDistributionAction getCreateCountContentDistributionAction()
	{

		return this.createCountContentDistributionAction;
	}

	/**
	 * Get the create count indexed DQL statement action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 7, 2005 2:55:56 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @return the action.
	 */
	public CreateCountIndexedAction getCreateCountIndexedAction()
	{

		return this.createCountIndexedAction;
	}

	/**
	 * Get the create count object type frequency DQL statement action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 7, 2005 3:30:34 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @return the action.
	 */
	public CreateCountObjectTypeFrequencyAction getCreateCountObjectTypeFrequencyAction()
	{

		return this.createCountObjectTypeFrequencyAction;
	}

	/**
	 * Get create count users document action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 25, 2005 10:30:25 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @return the action.
	 */
	public CreateCountUsersDocumentsAction getCreateCountUsersDocumentsAction()
	{

		return this.createCountUsersDocumentsAction;
	}

	/**
	 * Get the format statistics action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 25, 2005 3:07:30 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @return the action
	 */
	public CreateFormatStatisticsAction getCreateFormatStatisticsAction()
	{

		return this.createFormatStatisticsAction;
	}

	/**
	 * Get the create group action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 3, 2005 12:48:51 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the action.
	 */
	public CreateGroupAPIAction getCreateGroupAPIAction()
	{

		return this.createGroupAPIAction;
	}

	/**
	 * Get the create select fulltext indexed DQL query action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 7, 2005 2:14:26 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @return the action.
	 */
	public CreateIndexedAction getCreateIndexedAction()
	{

		return this.createIndexedAction;
	}

	/**
	 * Return the create relation type action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 17, 2005 1:46:29 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.3
	 * @return the relation type creation action.
	 */
	public CreateRelationAPIAction getCreateRelationAPIAction()
	{

		return this.createRelationAPIAction;
	}

	/**
	 * Get the create DQL action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 4:43:37 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the create dql action.
	 */
	public CreateReturnAllDqlAction getCreateReturnAllDqlAction()
	{

		return this.createReturnAllDqlAction;
	}

	/**
	 * Get the create TBO's action.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 17:34:37</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the createTBOsAction
	 */
	public CreateTBOsAction getCreateTBOsAction()
	{

		return this.createTBOsAction;
	}

	/**
	 * Get the create type DQL statement action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 7:37:00 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the action.
	 */
	public CreateTypeDQLAction getCreateTypeDQLAction()
	{

		return this.createTypeDQLAction;
	}

	/**
	 * Get the create user action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 3, 2005 12:59:56 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the create user action.
	 */
	public CreateUserAPIAction getCreateUserAPIAction()
	{

		return this.createUserAPIAction;
	}

	/**
	 * Get the create waiting for fulltext indexing DQL statement action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 7, 2005 12:50:49 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @return the action.
	 */
	public CreateWaitingForIndexingAction getCreateWaitingForIndexingAction()
	{

		return this.createWaitingForIndexingAction;
	}

	/**
	 * Get the disconnect action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 11:21:38 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the disconnect action.
	 */
	public DisconnectAction getDisconnectAction()
	{

		return this.disconnectAction;
	}

	/**
	 * Get the edit connection action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 2:57:44 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the edit connection action.
	 */
	public EditConnectionAction getEditConnectionAction()
	{

		return this.editConnectionAction;
	}

	/**
	 * Get the execute action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 10:57:34 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the execute query action.
	 */
	public ExecuteAction getExecuteAction()
	{

		return this.executeAction;

	}

	/**
	 * Get the execute new action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 4:04:06 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the execute as new action.
	 */
	public ExecuteNewAction getExecuteNewAction()
	{

		return this.executeNewAction;
	}

	/**
	 * Get the new API editor action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 11:34:10 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the new api editor action.
	 */
	public NewApiEditorAction getNewApiEditorAction()
	{

		return this.newApiEditorAction;
	}

	/**
	 * Get the new connection action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:04:40 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return conect action.
	 */
	public NewConnectionAction getNewConnectionAction()
	{

		return this.newConnectionAction;
	}

	/**
	 * Get the new DQL editor action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 9:20:06 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the new DQL editor action.
	 */
	public NewDqlEditorAction getNewDqlEditorAction()
	{

		return this.newDqlEditorAction;
	}

	/**
	 * Get the new TBO action.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25-Nov-2005 14:17:02</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the new TBO action.
	 */
	public NewTBOAction getNewTBOAction()
	{

		return this.newTBOAction;
	}

	/**
	 * Get the refresh action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 10:12:19 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the refresh action.
	 */
	public RefreshAction getRefreshAction()
	{

		return this.refreshAction;
	}

	/**
	 * Get the remove connection action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 11:07:22 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the remove connection action.
	 */
	public RemoveConnectionAction getRemoveConnectionAction()
	{

		return this.removeConnectionAction;
	}

	/**
	 * Select document content action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 1:59:36 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the select document action.
	 */
	public SelectDocumentAction getSelectDocumentAction()
	{

		return this.selectDocumentAction;
	}

	/**
	 * Get the selected connection.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 2:55:54 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the selected connection model.
	 */
	public ConnectionModel getSelectedConnection()
	{

		return ConnectionContentProvider.getInstance()
			.getSelectedConnectionModel();
	}

	/**
	 * Get the selected Docbase model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:08:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the selected docbase.
	 */
	public DocbaseModel getSelectedDocbase()
	{

		return ConnectionContentProvider.getInstance()
			.getSelectedDocbaseModel();
	}

	/**
	 * Get the selected model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 3:49:43 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return The selected model.
	 */
	public Model getSelectedModel()
	{

		return (Model) ((StructuredSelection) getTreeViewer().getSelection())
			.getFirstElement();
	}

	/**
	 * Get the select folder content action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 1:59:29 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the select folder content action.
	 */
	public SelectFolderAction getSelectFolderAction()
	{

		return this.selectFolderAction;
	}

	/**
	 * Get the select groups action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 5:04:34 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the select groups action.
	 */
	public SelectGroupsAction getSelectGroupsAction()
	{

		return this.selectGroupsAction;
	}

	/**
	 * Get the select inbox content action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 17:10:19</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return the select inbox action.
	 */

	public SelectInboxAction getSelectInboxAction()
	{

		return this.selectInboxAction;
	}

	/**
	 * Get the select relation type action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 17, 2005 9:42:23 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.3
	 * @return the relation type selection action.
	 */
	public SelectRelationTypeModelAction getSelectRelationTypeModelAction()
	{

		return this.selectRelationTypeModelAction;
	}

	/**
	 * Get the select table action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 9:57:16 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the selected table action.
	 */
	public SelectTableAction getSelectTableAction()
	{

		return this.selectTableAction;
	}

	/**
	 * Get the select type action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 27, 2004 2:16:40 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper accsount : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the select types attributes action.
	 */
	public SelectTypeAction getSelectTypeAction()
	{

		return this.selectTypeAction;
	}

	/**
	 * Get the select users action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 5:04:46 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the select users action.
	 */
	public SelectUsersAction getSelectUsersAction()
	{

		return this.selectUsersAction;
	}

	/**
	 * Refresh the selected model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 10:01:20 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public void refreshSelectedModel()
	{

		Model model = getSelectedModel();
		ConnectionContentProvider.getInstance().refreshModel(model);
		getTreeViewer().collapseToLevel(model, 1);
		getTreeViewer().refresh();
		getTreeViewer().expandToLevel(model, 1);

	}

	/**
	 * Remove a connection.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 11:04:48 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aConnectionModel
	 *            the connection to remove.
	 */
	public void removeConnection(final ConnectionModel aConnectionModel)
	{

		ConnectionContentProvider.getInstance().getInvisibleRootModel()
			.removeChild(aConnectionModel);
		changedConnection();
	}

	/**
	 * <p>
	 * <b>Does nothing! </b>
	 * </p>
	 * <p>
	 * Asks this part to take focus within the workbench.
	 * </p>
	 * <p>
	 * Clients should not call this method (the workbench calls this method at
	 * appropriate times). To have the workbench activate a part, use
	 * <code>IWorkbenchPage.activate(IWorkbenchPart) instead</code>.
	 * </p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 24, 2004 3:37:37 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @see org.eclipse.ui.IWorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus()
	{

		/* Do nothing. */
	}

	/**
	 * Set the select document action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 2:33:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aSelectDocumentAction
	 *            the select document action.
	 */
	public void setSelectDocumentAction(
										final SelectDocumentAction aSelectDocumentAction)
	{

		this.selectDocumentAction = aSelectDocumentAction;
	}

	/**
	 * Set the selected connection.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:13:45 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aConnectionModel
	 *            selected connection.
	 */
	public void setSelectedConnection(final ConnectionModel aConnectionModel)
	{

		ConnectionContentProvider.getInstance().setSelectedConnectionModel(
			aConnectionModel);
		ConnectionContentProvider.getInstance().setSelectedDocbaseModel(null);
		expandConnectionModel(aConnectionModel);
	}

	/**
	 * Set the selected Docbase.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:16:26 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDocbaseModel
	 *            the seleced Docbase
	 */
	public void setSelectedDocbase(final DocbaseModel aDocbaseModel)
	{

		ConnectionContentProvider.getInstance().setSelectedDocbaseModel(
			aDocbaseModel);
		ConnectionModel connectionModel = ConnectionContentProvider
			.getInstance().getParentConnectionModel(aDocbaseModel);
		ConnectionContentProvider.getInstance().setSelectedConnectionModel(
			connectionModel);
		expandDocbaseModel(aDocbaseModel);
	}

	/**
	 * Set the select folder content action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 2:40:27 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aSelectFolderAction
	 *            the initialized action.
	 */
	public void setSelectFolderAction(
										final SelectFolderAction aSelectFolderAction)
	{

		this.selectFolderAction = aSelectFolderAction;
	}

	/**
	 * Get the tree viewer.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 10:51:42 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the tree view.
	 */
	protected TreeViewer getTreeViewer()
	{

		return this.treeViewer;

	}

	/**
	 * Get the tree view listener.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 9:34:14 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the tree listener.
	 */
	protected TreeViewListener getTreeViewListener()
	{

		return this.treeViewListener;
	}

	/**
	 * Initialize all actions of this view.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:01:34 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	protected void initActions()
	{

		setNewConnectionAction(new NewConnectionAction());
		getNewConnectionAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_NEW_CONNECTION));
		getNewConnectionAction().setToolTipText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_NEW_CONNECTION_TOOLTIP));
		getNewConnectionAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.DISCONNECTED_ICON));
		getNewConnectionAction().init(this);

		setConnectAction(new ConnectAction());
		getConnectAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CONNECT));
		getConnectAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CONNECT_TOOLTIP));
		getConnectAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CONNECTED_ICON));
		getConnectAction().init(this);

		setSelectTypeAction(new SelectTypeAction());
		getSelectTypeAction().setText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_SELECT_TYPE_ATTRIBUTES));
		getSelectTypeAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_SELECT_TYPE_ATTRIBUTES_TOOLTIP));
		getSelectTypeAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.TYPE_ICON));
		getSelectTypeAction().init(this);

		setSelectTableAction(new SelectTableAction());
		getSelectTableAction().setText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_SELECT_TABLE_ATTRIBUTES));
		getSelectTableAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_SELECT_TABLE_ATTRIBUTES_TOOLTIP));
		getSelectTableAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.TABLE_ICON));
		getSelectTableAction().init(this);

		setDisconnectAction(new DisconnectAction());
		getDisconnectAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_DISCONNECT));
		getDisconnectAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_DISCONNECT_TOOLTIP));
		getDisconnectAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.DISCONNECTED_ICON));
		getDisconnectAction().init(this);

		setNewDqlEditorAction(new NewDqlEditorAction());
		getNewDqlEditorAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_NEW_DQL));
		getNewDqlEditorAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_NEW_DQL_TOOLTIP));
		getNewDqlEditorAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.NEW_DQL_ICON));
		getNewDqlEditorAction().init(this);

		setNewApiEditorAction(new NewApiEditorAction());
		getNewApiEditorAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_NEW_API));
		getNewApiEditorAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_NEW_API_TOOLTIP));
		getNewApiEditorAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.NEW_API_ICON));
		getNewApiEditorAction().init(this);

		setRefreshAction(new RefreshAction());
		getRefreshAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_REFRESH));
		getRefreshAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_REFRESH_TOOLTIP));
		getRefreshAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.REFRESH_ICON));
		getRefreshAction().init(this);

		setExecuteAction(new ExecuteAction());
		getExecuteAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_EXECUTE));
		getExecuteAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_EXECUTE_TOOLTIP));
		getExecuteAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.EXECUTE_ICON));
		getExecuteAction().init(this);

		setExecuteNewAction(new ExecuteNewAction());
		getExecuteNewAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_EXECUTE_NEW));
		getExecuteNewAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_EXECUTE_NEW_TOOLTIP));
		getExecuteNewAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.EXECUTE_ICON));
		getExecuteNewAction().init(this);

		setRemoveConnectionAction(new RemoveConnectionAction());
		getRemoveConnectionAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_REMOVE_CONNECTION));
		getRemoveConnectionAction().setToolTipText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_REMOVE_CONNECTION_TOOLTIP));
		getRemoveConnectionAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.REMOVE_ICON));
		getRemoveConnectionAction().init(this);

		setEditConnectionAction(new EditConnectionAction());
		getEditConnectionAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_EDIT_CONNECTION));
		getEditConnectionAction().setToolTipText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_EDIT_CONNECTION_TOOLTIP));
		getEditConnectionAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.DISCONNECTED_ICON));
		getEditConnectionAction().init(this);

		setCreateReturnAllDqlAction(new CreateReturnAllDqlAction());
		getCreateReturnAllDqlAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CREATE_DQL));
		getCreateReturnAllDqlAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CREATE_DQL_TOOLTIP));
		getCreateReturnAllDqlAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CREATE_DQL_ICON));
		getCreateReturnAllDqlAction().init(this);

		setSelectFolderAction(new SelectFolderAction());
		getSelectFolderAction()
			.setText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_SELECT_FOLDER_CONTENT));
		getSelectFolderAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_SELECT_FOLDER_CONTENT_TOOLTIP));
		getSelectFolderAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.FOLDER_ICON));
		getSelectFolderAction().init(this);

		setSelectDocumentAction(new SelectDocumentAction());
		getSelectDocumentAction().setText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_SELECT_DOCUMENT_CONTENT));
		getSelectDocumentAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_SELECT_DOCUMENT_CONTENT_TOOLTIP));
		getSelectDocumentAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.VIRTUAL_DOCUMENT_ICON));
		getSelectDocumentAction().init(this);

		setSelectGroupsAction(new SelectGroupsAction());
		getSelectGroupsAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_SELECT_GROUPS));
		getSelectGroupsAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_SELECT_GROUPS_TOOLTIP));
		getSelectGroupsAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.GROUP_ICON));
		getSelectGroupsAction().init(this);

		setSelectUsersAction(new SelectUsersAction());
		getSelectUsersAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_SELECT_USERS));
		getSelectUsersAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_SELECT_USERS_TOOLTIP));
		getSelectUsersAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.USERS_ICON));
		getSelectUsersAction().init(this);

		setCreateTypeDQLAction(new CreateTypeDQLAction());
		getCreateTypeDQLAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CREATE_TYPE));
		getCreateTypeDQLAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CREATE_TYPE_TOOLTIP));
		getCreateTypeDQLAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CREATE_TYPE_ICON));
		getCreateTypeDQLAction().init(this);

		setCreateGroupAPIAction(new CreateGroupAPIAction());
		getCreateGroupAPIAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CREATE_GROUP));
		getCreateGroupAPIAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CREATE_GROUP_TOOLTIP));
		getCreateGroupAPIAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CREATE_GROUP_ICON));
		getCreateGroupAPIAction().init(this);

		setCreateUserAPIAction(new CreateUserAPIAction());
		getCreateUserAPIAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CREATE_USER));
		getCreateUserAPIAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CREATE_USER_TOOLTIP));
		getCreateUserAPIAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CREATE_USER_ICON));
		getCreateUserAPIAction().init(this);

		setSelectRelationTypeModelAction(new SelectRelationTypeModelAction());
		getSelectRelationTypeModelAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_SELECT_RELATION_TYPE));
		getSelectRelationTypeModelAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_SELECT_RELATION_TYPE_TOOLTIP));
		getSelectRelationTypeModelAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.RELATION_ICON));
		getSelectRelationTypeModelAction().init(this);

		setCreateRelationAPIAction(new CreateRelationAPIAction());
		getCreateRelationAPIAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CREATE_RELATION_TYPE));
		getCreateRelationAPIAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_CREATE_RELATION_TYPE_TOOLTIP));
		getCreateRelationAPIAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CREATE_RELATION_ICON));
		getCreateRelationAPIAction().init(this);

		setChangeDefaultStorageStatementAction(new ChangeDefaultStorageStatementAction());
		getChangeDefaultStorageStatementAction()
			.setText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_CREATE_CHANGE_STORAGE));
		getChangeDefaultStorageStatementAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_CREATE_CHANGE_STORAGE_TOOLTIP));
		getChangeDefaultStorageStatementAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CHANGE_STORAGE_ICON));
		getChangeDefaultStorageStatementAction().init(this);

		setCreateFormatStatisticsAction(new CreateFormatStatisticsAction());
		getCreateFormatStatisticsAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_FORMAT_STATISTICS));
		getCreateFormatStatisticsAction().setToolTipText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_FORMAT_STATISTICS_TOOLTIP));
		getCreateFormatStatisticsAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.FORMAT_ICON));
		getCreateFormatStatisticsAction().init(this);

		setCreateCountUsersDocumentsAction(new CreateCountUsersDocumentsAction());
		getCreateCountUsersDocumentsAction()
			.setText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_COUNT_USERS_DOCUMENTS));
		getCreateCountUsersDocumentsAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_COUNT_USERS_DOCUMENTS_TOOLTIP));
		getCreateCountUsersDocumentsAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CREATE_COUNT_ICON));
		getCreateCountUsersDocumentsAction().init(this);

		setCreateCountContentDistributionAction(new CreateCountContentDistributionAction());
		getCreateCountContentDistributionAction().setText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_COUNT_CONTENT_DISTRIBUTION));
		getCreateCountContentDistributionAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_COUNT_CONTENT_DISTRIBUTION_TOOLTIP));
		getCreateCountContentDistributionAction()
			.setImageDescriptor(
				ImageCache
					.getImageDescriptor(IImageCache.CREATE_CONTENT_DISTRIBUTION_ICON));
		getCreateCountContentDistributionAction().init(this);

		setCreateWaitingForIndexingAction(new CreateWaitingForIndexingAction());
		getCreateWaitingForIndexingAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_WAITING_FOR_INDEXING));
		getCreateWaitingForIndexingAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_WAITING_FOR_INDEXING_TOOLTIP));
		getCreateWaitingForIndexingAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CREATE_FULLTEXT_ICON));
		getCreateWaitingForIndexingAction().init(this);

		setCreateIndexedAction(new CreateIndexedAction());
		getCreateIndexedAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_INDEXED));
		getCreateIndexedAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_INDEXED_TOOLTIP));
		getCreateIndexedAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CREATE_FULLTEXT_ICON));
		getCreateIndexedAction().init(this);

		setCreateCountIndexedAction(new CreateCountIndexedAction());
		getCreateCountIndexedAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_COUNT_INDEXED));
		getCreateCountIndexedAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_COUNT_INDEXED_TOOLTIP));
		getCreateCountIndexedAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CREATE_FULLTEXT_ICON));
		getCreateCountIndexedAction().init(this);

		setCreateCountObjectTypeFrequencyAction(new CreateCountObjectTypeFrequencyAction());
		getCreateCountObjectTypeFrequencyAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_COUNT_TYPE_FREQUENCY));
		getCreateCountObjectTypeFrequencyAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_COUNT_TYPE_FREQUENCY_TOOLTIP));
		getCreateCountObjectTypeFrequencyAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.TYPE_ICON));
		getCreateCountObjectTypeFrequencyAction().init(this);

		setSelectInboxAction(new SelectInboxAction());
		getSelectInboxAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_INBOX_MESSAGES));
		getSelectInboxAction().setToolTipText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_INBOX_MESSAGES_TOOLTIP));
		getSelectInboxAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.INBOX_ICON));
		getSelectInboxAction().init(this);

		setCreateTBOsAction(new CreateTBOsAction());
		getCreateTBOsAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CREATE_TBOS));
		getCreateTBOsAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CREATE_TBOS_TOOLTIP));
		getCreateTBOsAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.GENERATE_TBO_ICON));
		getCreateTBOsAction().init(this);

		setNewTBOAction(new NewTBOAction());
		getNewTBOAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CREATE_TBO_MODULE));
		getNewTBOAction().setToolTipText(
			IDCTMPlugin.MSG_CREATE_TBO_MODULE_TOOLTIP);
		getNewTBOAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.TBO_ICON));
		getNewTBOAction().init(this);

	}

	/**
	 * Set the
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 24, 2005 8:50:27 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @param aChangeDefaultStorageStatementAction
	 *            the new action.
	 */
	protected void setChangeDefaultStorageStatementAction(
															final ChangeDefaultStorageStatementAction aChangeDefaultStorageStatementAction)
	{

		this.changeDefaultStorageStatementAction = aChangeDefaultStorageStatementAction;
	}

	/**
	 * Set the connect action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 11:05:40 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aConnectAction
	 *            the action to set.
	 */
	protected void setConnectAction(final ConnectAction aConnectAction)
	{

		this.connectAction = aConnectAction;
	}

	/**
	 * Set the create count content distribution action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 7, 2005 10:54:18 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @param aCreateCountContentDistributionAction
	 *            the new action
	 */
	protected void setCreateCountContentDistributionAction(
															final CreateCountContentDistributionAction aCreateCountContentDistributionAction)
	{

		this.createCountContentDistributionAction = aCreateCountContentDistributionAction;
	}

	/**
	 * Set the create count indexed DQL statement action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 7, 2005 2:57:50 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @param aCreateCountIndexedAction
	 *            the new action instance.
	 */
	protected void setCreateCountIndexedAction(
												final CreateCountIndexedAction aCreateCountIndexedAction)
	{

		this.createCountIndexedAction = aCreateCountIndexedAction;
	}

	/**
	 * Set the create count object type frequency DQL statement action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 7, 2005 3:31:26 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @param aCreateCountObjectTypeFrequencyAction
	 *            the new action instance.
	 */
	protected void setCreateCountObjectTypeFrequencyAction(
															final CreateCountObjectTypeFrequencyAction aCreateCountObjectTypeFrequencyAction)
	{

		this.createCountObjectTypeFrequencyAction = aCreateCountObjectTypeFrequencyAction;
	}

	/**
	 * Set create count users document action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 25, 2005 10:30:28 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @param aCreateCountUsersDocumentsAction
	 *            the new action.
	 */
	protected void setCreateCountUsersDocumentsAction(
														final CreateCountUsersDocumentsAction aCreateCountUsersDocumentsAction)
	{

		this.createCountUsersDocumentsAction = aCreateCountUsersDocumentsAction;
	}

	/**
	 * Set the action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 25, 2005 3:30:11 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @param aCreateFormatStatisticsAction
	 *            the new action to set.
	 */
	protected void setCreateFormatStatisticsAction(
													final CreateFormatStatisticsAction aCreateFormatStatisticsAction)
	{

		this.createFormatStatisticsAction = aCreateFormatStatisticsAction;
	}

	/**
	 * set create group action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 3, 2005 12:47:08 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aCreateGroupAPIAction
	 *            the new action.
	 */
	protected void setCreateGroupAPIAction(
											final CreateGroupAPIAction aCreateGroupAPIAction)
	{

		this.createGroupAPIAction = aCreateGroupAPIAction;
	}

	/**
	 * Set the create select fulltext indexed DQL query action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 7, 2005 2:15:56 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @param aCreateIndexedAction
	 *            the new action.
	 */
	protected void setCreateIndexedAction(
											final CreateIndexedAction aCreateIndexedAction)
	{

		this.createIndexedAction = aCreateIndexedAction;
	}

	/**
	 * Set the create relation api action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 17, 2005 1:46:07 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.3
	 * @param aCreateRelationAPIAction
	 *            the new action.
	 */
	protected void setCreateRelationAPIAction(
												final CreateRelationAPIAction aCreateRelationAPIAction)
	{

		this.createRelationAPIAction = aCreateRelationAPIAction;
	}

	/**
	 * Set the create return all DQL action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 4:41:23 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aCreateReturnAllDqlAction
	 *            the action to set.
	 */
	protected void setCreateReturnAllDqlAction(
												final CreateReturnAllDqlAction aCreateReturnAllDqlAction)
	{

		this.createReturnAllDqlAction = aCreateReturnAllDqlAction;

	}

	/**
	 * Set the create TBO's action.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 17:34:37</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aCreateTBOsAction
	 *            The createTBOsAction to set
	 */
	protected void setCreateTBOsAction(final CreateTBOsAction aCreateTBOsAction)
	{

		this.createTBOsAction = aCreateTBOsAction;
	}

	/**
	 * Set the create type action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 7:38:37 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aCreateTypeDQLAction
	 *            the new action
	 */
	protected void setCreateTypeDQLAction(
											final CreateTypeDQLAction aCreateTypeDQLAction)
	{

		this.createTypeDQLAction = aCreateTypeDQLAction;
	}

	/**
	 * Set the create user action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 3, 2005 1:00:01 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aCreateUserAPIAction
	 *            the new action.
	 */
	protected void setCreateUserAPIAction(
											final CreateUserAPIAction aCreateUserAPIAction)
	{

		this.createUserAPIAction = aCreateUserAPIAction;
	}

	/**
	 * Set the create waiting for fulltext indexing DQL statement action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 7, 2005 12:52:29 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @param aCreateWaitingForIndexingAction
	 *            the new action instance.
	 */
	protected void setCreateWaitingForIndexingAction(
														final CreateWaitingForIndexingAction aCreateWaitingForIndexingAction)
	{

		this.createWaitingForIndexingAction = aCreateWaitingForIndexingAction;
	}

	/**
	 * Set the disconnect action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 11:24:20 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDisconnectAction
	 *            the action to set.
	 */
	protected void setDisconnectAction(final DisconnectAction aDisconnectAction)
	{

		this.disconnectAction = aDisconnectAction;
	}

	/**
	 * Set the edit connect action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 2:57:40 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anEditConnectionAction
	 *            the action to set.
	 */
	protected void setEditConnectionAction(
											final EditConnectionAction anEditConnectionAction)
	{

		this.editConnectionAction = anEditConnectionAction;
	}

	/**
	 * Set the execute action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 10:53:35 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anExecuteAction
	 *            the action to set.
	 */
	protected void setExecuteAction(final ExecuteAction anExecuteAction)
	{

		this.executeAction = anExecuteAction;

	}

	/**
	 * Set the execute new action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 4:04:09 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anExecuteNewAction
	 *            the action to set.
	 */
	protected void setExecuteNewAction(final ExecuteNewAction anExecuteNewAction)
	{

		this.executeNewAction = anExecuteNewAction;

	}

	/**
	 * Set the new API editor action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 11:34:13 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aNewApiEditorAction
	 *            the action to set.
	 */
	protected void setNewApiEditorAction(
											final NewApiEditorAction aNewApiEditorAction)
	{

		this.newApiEditorAction = aNewApiEditorAction;
	}

	/**
	 * Set the new connection action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:04:29 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aNewConnectionAction
	 *            the action to set.
	 */
	protected void setNewConnectionAction(
											final NewConnectionAction aNewConnectionAction)
	{

		this.newConnectionAction = aNewConnectionAction;
	}

	/**
	 * Set the new DQL editor action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 9:19:53 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aNewDqlEditorAction
	 *            the action to set.
	 */
	protected void setNewDqlEditorAction(
											final NewDqlEditorAction aNewDqlEditorAction)
	{

		this.newDqlEditorAction = aNewDqlEditorAction;

	}

	/**
	 * Set the new TBO acton.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25-Nov-2005 14:15:59</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aNewTBOAction
	 *            the new TBO action.
	 */
	protected void setNewTBOAction(final NewTBOAction aNewTBOAction)
	{

		this.newTBOAction = aNewTBOAction;
	}

	/**
	 * Set the refresh action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 10:12:17 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aRefreshAction
	 *            the action to set.
	 */
	protected void setRefreshAction(final RefreshAction aRefreshAction)
	{

		this.refreshAction = aRefreshAction;
	}

	/**
	 * Set the remove connection action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 11:07:27 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aRemoveConnectionAction
	 *            the action to set.
	 */
	protected void setRemoveConnectionAction(
												final RemoveConnectionAction aRemoveConnectionAction)
	{

		this.removeConnectionAction = aRemoveConnectionAction;
	}

	/**
	 * Set the select groups action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 5:09:33 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aSelectGroupsAction
	 *            the new action.
	 */
	protected void setSelectGroupsAction(
											final SelectGroupsAction aSelectGroupsAction)
	{

		this.selectGroupsAction = aSelectGroupsAction;
	}

	/**
	 * Set the select relation type action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 17, 2005 10:00:02 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.3
	 * @param aSelectRelationTypeModelAction
	 *            the new action.
	 */
	protected void setSelectRelationTypeModelAction(
													final SelectRelationTypeModelAction aSelectRelationTypeModelAction)
	{

		this.selectRelationTypeModelAction = aSelectRelationTypeModelAction;
	}

	/**
	 * Set the select table action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 9:58:56 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aSelectTableAction
	 *            the action to set.
	 */
	protected void setSelectTableAction(
										final SelectTableAction aSelectTableAction)
	{

		this.selectTableAction = aSelectTableAction;
	}

	/**
	 * Set the select type action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 27, 2004 2:17:27 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param selectTypeAction
	 *            the action to set.
	 */
	protected void setSelectTypeAction(SelectTypeAction selectTypeAction)
	{

		this.selectTypeAction = selectTypeAction;
	}

	/**
	 * Sets the select users action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 5:21:11 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aSelectUsersAction
	 *            the new select users action.
	 */
	protected void setSelectUsersAction(
										final SelectUsersAction aSelectUsersAction)
	{

		this.selectUsersAction = aSelectUsersAction;
	}

	/**
	 * Set the tree viewer.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:32:11 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aTreeViewer
	 *            the viewer to set.
	 */
	protected void setTreeViewer(final TreeViewer aTreeViewer)
	{

		this.treeViewer = aTreeViewer;

	}

	/**
	 * Set the tree view listener.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 9:36:24 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aTreeViewListener
	 *            a listener to set.
	 */
	protected void setTreeViewListener(final TreeViewListener aTreeViewListener)
	{

		this.treeViewListener = aTreeViewListener;
	}

	/**
	 * Set the select inbox content action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 17:13:25</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param aSelectInboxAction
	 *            the action to set.
	 */
	private void setSelectInboxAction(final SelectInboxAction aSelectInboxAction)
	{

		this.selectInboxAction = aSelectInboxAction;
	}

}
