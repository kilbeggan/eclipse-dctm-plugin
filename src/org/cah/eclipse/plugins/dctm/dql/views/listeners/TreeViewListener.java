/*-
 * $Log: TreeViewListener.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:29:56  madcook
 * Version 3.0.0 work started.
 * 
 * Revision 1.6 2005/11/21 13:04:32 madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5
 * settings. 
 * 
 * Revision 1.5 2005/03/25 09:21:39 harpechr 
 * Version 2.0.0 code that has implemented the new java 1.5 features. 
 * 
 * Revision 1.4 2005/02/09 14:01:44 harpechr
 * Version 1.0.5 work started. 
 * 
 * Revision 1.3 2005/01/24 12:34:57 harpechr 
 * Version 1.0.4 work started. 
 * 
 * Revision 1.2 2005/01/11 14:02:19 harpechr 
 * Changed version number from 1.0.2 to 1.0.3. 
 * 
 * Revision 1.1 2005/01/07 12:37:51 harpechr 
 * Version 1.0.2 code. First CVS commit!
 */

package org.cah.eclipse.plugins.dctm.dql.views.listeners;

import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.AbstractSysObjectModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.CabinetModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionContentProvider;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.DocbaseModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.DocumentModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.FolderModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.GroupModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.GroupsModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.Model;
import org.cah.eclipse.plugins.dctm.dql.views.connection.OtherModuleModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.TableModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.TypeModel;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;


/**
 * <H4>A listener which is notified when a tree viewer expands or collapses a
 * node.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) Christopher Harper</DD>
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
public class TreeViewListener
								implements
									ITreeViewerListener
{

	/**
	 * The connection view tree.
	 * 
	 * @since 1.0
	 */
	private TreeViewer	treeViewer	= null;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:36:57 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @param aTreeViewer
	 *            the tree view.
	 * @since 1.0
	 */
	public TreeViewListener(final TreeViewer aTreeViewer)
	{

		super();
		setTreeViewer(aTreeViewer);
	}

	/**
	 * Cabinet model expanded.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:56:37 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aCabinetModel
	 *            the cabinet model been expanded.
	 */
	public void expandCabinetModel(final CabinetModel aCabinetModel)
	{
		getTreeViewer().getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				ConnectionContentProvider.getInstance().expandCabinetModel(aCabinetModel, false);
				getTreeViewer().refresh();
			}
		});
	}

	/**
	 * Connection model expanded.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 9:37:33 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param connectionModel
	 *            the model opened.
	 */
	public void expandConnectionModel(final ConnectionModel connectionModel)
	{
		getTreeViewer().getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				getTreeViewer().refresh();
			}
		});
	}

	/**
	 * Docbasemodel expanded.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:43:12 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param docbaseModel
	 *            the model expanded.
	 */
	public void expandDocbaseModel(final DocbaseModel docbaseModel)
	{
		getTreeViewer().getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				ConnectionContentProvider.getInstance().expandDocbaseModel(docbaseModel, false);
				getTreeViewer().refresh();
			}
		});
	}

	/**
	 * Document model expanded.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:56:37 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDocumentModel
	 *            the document model expanded.
	 */
	public void expandDocumentModel(final DocumentModel aDocumentModel)
	{
		getTreeViewer().getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				ConnectionContentProvider.getInstance().expandDocumentModel(aDocumentModel, false);
				getTreeViewer().refresh();
			}
		});
	}

	/**
	 * Folder model expanded.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:56:37 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aFolderModel
	 *            the folder model expanded.
	 */
	public void expandFolderModel(final AbstractSysObjectModel aFolderModel)
	{
		getTreeViewer().getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				ConnectionContentProvider.getInstance().expandFolderModel(aFolderModel, false);
				getTreeViewer().refresh();
			}
		});
	}

	/**
	 * Expand a Docbase group.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 3:09:16 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.2
	 * @param aGroupModel
	 *            the group to expand.
	 */
	public void expandGroupModel(final GroupModel aGroupModel)
	{
		getTreeViewer().getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				ConnectionContentProvider.getInstance().expandGroupModel(aGroupModel, false);
				getTreeViewer().refresh();
			}
		});
	}

	/**
	 * Expand the groups container.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 3:09:09 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.2
	 * @param aGroupsModel
	 *            the groups container.
	 */
	public void expandGroupsModel(final GroupsModel aGroupsModel)
	{
		getTreeViewer().getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				ConnectionContentProvider.getInstance().expandGroupsModel(aGroupsModel, false);
				getTreeViewer().refresh();
			}
		});
	}

	/**
	 * Table model expanded.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:56:37 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param tableModel
	 *            the table model expanded.
	 */
	public void expandTableModel(final TableModel tableModel)
	{
		getTreeViewer().getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				ConnectionContentProvider.getInstance().expandTableModel(tableModel, false);
				getTreeViewer().refresh();
			}
		});
	}

	/**
	 * Type model expanded.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:56:31 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param typeModel
	 *            the model expanded.
	 */
	public void expandTypeModel(final TypeModel typeModel)
	{
		getTreeViewer().getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				ConnectionContentProvider.getInstance().expandTypeModel(typeModel, false);
				getTreeViewer().refresh();
			}
		});
	}

	/**
	 * <p>
	 * <b>Nothing done!!! </b>
	 * </p>
	 * Notifies that a node in the tree has been collapsed.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:36:57 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param event
	 *            event object describing details
	 * @see org.eclipse.jface.viewers.ITreeViewerListener#treeCollapsed(org.eclipse.jface.viewers.TreeExpansionEvent)
	 */
	public void treeCollapsed(final TreeExpansionEvent event)
	{

		/* Nothing done here. */
	}

	/**
	 * Notifies that a node in the tree has been expanded and based on the
	 * selected model calls different expand functions.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:36:57 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param event
	 *            event object describing details
	 * @see org.eclipse.jface.viewers.ITreeViewerListener#treeExpanded(org.eclipse.jface.viewers.TreeExpansionEvent)
	 */
	public void treeExpanded(final TreeExpansionEvent event)
	{

		Model model = (Model) event.getElement();
		/*
		 * Deselect everything, otherwise tree will refresh to top of selected
		 * item.
		 */
		getTreeViewer().setSelection(null);
		if(model == null)
		{
			MessageView.getInstance().addMessage(
				"Selected model storage null!!!"); //$NON-NLS-1$
		} else if(model instanceof ConnectionModel)
		{
			/* Nothing here. */
		} else if(model instanceof DocbaseModel)
		{
			expandDocbaseModel((DocbaseModel) model);
		} else if(model instanceof TypeModel)
		{
			expandTypeModel((TypeModel) model);
		} else if(model instanceof TableModel)
		{
			expandTableModel((TableModel) model);
		} else if(model instanceof CabinetModel)
		{
			expandCabinetModel((CabinetModel) model);
		} else if(model instanceof FolderModel)
		{
			expandFolderModel((FolderModel) model);
		} else if(model instanceof DocumentModel)
		{
			expandDocumentModel((DocumentModel) model);
		} else if(model instanceof GroupsModel)
		{
			expandGroupsModel((GroupsModel) model);
		} else if(model instanceof GroupModel)
		{
			expandGroupModel((GroupModel) model);
		} else if(model instanceof OtherModuleModel)
		{
			expandFolderModel((OtherModuleModel) model);
		}
	}

	/**
	 * Get the tree view.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:41:35 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the tree view (connection view).
	 */
	protected TreeViewer getTreeViewer()
	{

		return this.treeViewer;
	}

	/**
	 * Set the tree view.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:41:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aTreeViewer
	 *            tree view (connection view).
	 */
	protected void setTreeViewer(final TreeViewer aTreeViewer)
	{

		this.treeViewer = aTreeViewer;
	}
}
