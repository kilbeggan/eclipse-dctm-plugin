/*-
 * $Log: CreateReturnAllDqlAction.java,v $
 * Revision 1.6  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.5  2005/12/04 20:26:26  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.4  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.3  2005/04/01 11:27:30  harpechr
 * Reference to a static string changed to an interface.
 *
 * Revision 1.2  2005/03/25 09:19:59  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.1  2005/02/28 07:38:25  harpechr
 * Refactored actions so that they are in view and type spesific packages.
 *
 * Revision 1.6  2005/02/09 14:01:29  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.5  2005/01/24 12:34:53  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.4  2005/01/11 14:02:00  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.3  2005/01/11 13:47:25  harpechr
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof).
 *
 * Revision 1.2  2005/01/09 10:46:54  harpechr
 * Renamed classes that were abstract by prefixing the class name with 
 * 'Abstract'.
 *
 * Revision 1.1  2005/01/07 12:37:48  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.connection.dql;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;
import org.cah.dctm.bof.tbo.persistent.type.IType;

import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.actions.AbstractBaseAction;
import org.cah.eclipse.plugins.dctm.dql.editors.DQLEditor;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.Model;
import org.cah.eclipse.plugins.dctm.dql.views.connection.TableModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.TypeModel;

import org.eclipse.ui.IEditorPart;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;


/**
 * <H4>Generate a DQL statement that will return all columns / attributes.</H4>
 * <DL>
 * <DT><B>Description : </B>
 * <DD>Generate a DQL statement that will return all columns / attributes of a
 * selected table / type. A DQL editor must be open and active for this action
 * to work. If a editor is not open and active a message will be printed into
 * the DCTM Message view.</DD>
 * </DT>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Dec 2, 2004 3:45:32 PM.</DD>
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
public class CreateReturnAllDqlAction
										extends
											AbstractBaseAction
{

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 3:45:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public CreateReturnAllDqlAction()
	{

		super();
	}

	/**
	 * Find the active editor and print the DQL statement into it if it's a DQL
	 * editor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 3:49:53 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	@Override
	public void run()
	{

		IEditorPart editor = null;
		if(getView() != null)
		{
			editor = getView().getSite().getWorkbenchWindow().getActivePage()
				.getActiveEditor();
		}
		DQLEditor dqlEditor = null;
		if((editor != null) && (editor instanceof DQLEditor))
		{
			dqlEditor = (DQLEditor) editor;
		}
		if(dqlEditor == null)
		{
			MessageView.getInstance().addMessage("No active DQL editor"); //$NON-NLS-1$
			return;
		}
		dqlEditor.setText(dqlEditor.getText() + IDCTMPlugin.NEWLINE
			+ getReturnAllDql());
	}

	/**
	 * Get the return all DQL statement.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 3:58:28 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return current types return all attributes query and tables just a
	 *         simple * query.
	 */
	protected String getReturnAllDql()
	{

		IDfSession session = null;
		try
		{
			session = ConnectionView.getInstance().getSelectedConnection()
				.getSession();
			if(session != null)
			{
				final Model model = ConnectionView.getInstance()
					.getSelectedModel();
				if(model instanceof TypeModel)
				{
					return getReturnAllTypeDql(session, (TypeModel) model);
				} else if(model instanceof TableModel)
				{
					return getReturnAllTableDql((TableModel) model);
				}
			}
		} catch(DfException ex)
		{
			MessageView.getInstance().addMessage(ex);
		} finally
		{
			ConnectionView.getInstance().getSelectedConnection()
				.releaceConnection(session);
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * Create a DQL statement that returns all columns from a registered table
	 * with a comment.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 4:38:30 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param tableModel
	 * @return query for all attributes of a registered table.
	 */
	protected String getReturnAllTableDql(final TableModel tableModel)
	{

		final String tableName = tableModel.getName();
		StringBuffer stringBuffer = new StringBuffer(
			"/* Return all attributes from table ").append(tableName) //$NON-NLS-1$
			.append(". */").append(IDCTMPlugin.NEWLINE).append("select").append(IDCTMPlugin.NEWLINE) //$NON-NLS-1$ //$NON-NLS-2$
			.append("\tdistinct *").append(IDCTMPlugin.NEWLINE).append("from").append(IDCTMPlugin.NEWLINE).append('\t') //$NON-NLS-1$ //$NON-NLS-2$
			.append(tableName).append(';');
		return stringBuffer.toString();
	}

	/**
	 * Create a commented DQL statement that returns all attributes of a given
	 * type.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 4:04:36 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param session
	 * @param typeModel
	 * @return query for all attributes of a type.
	 */
	protected String getReturnAllTypeDql(final IDfSession session,
											final TypeModel typeModel)
	{

		StringBuffer stringBuffer = new StringBuffer("select distinct ").append(IType.ATTR_NAME).append(" from ") //$NON-NLS-1$ //$NON-NLS-2$
			.append(IType.TYPE_DM_TYPE)
			.append(" where ").append(IType.NAME).append(" = '").append(typeModel.getName()) //$NON-NLS-1$ //$NON-NLS-2$
			.append('\'');
		final IDfQuery query = new DfQuery();
		query.setDQL(stringBuffer.toString());
		IDfCollection result = null;
		try
		{
			stringBuffer = new StringBuffer(
				"/* Return all attributes from type ").append(typeModel.getName()) //$NON-NLS-1$
				.append(". */").append(IDCTMPlugin.NEWLINE).append("select distinct").append(IDCTMPlugin.NEWLINE) //$NON-NLS-1$ //$NON-NLS-2$
				.append('\t').append(IPersistentObject.R_OBJECT_ID);
			MessageView.getInstance().addMessage(
				session.getLoginInfo().getDomain() + '\\'
					+ session.getLoginUserName() + '@'
					+ session.getDocbaseName() + ' ' + query.getDQL());
			result = query.execute(session, IDfQuery.DF_EXECREAD_QUERY);
			while(result.next())
			{
				final String attributeName = result.getString(IType.ATTR_NAME);
				if(!attributeName
					.equalsIgnoreCase(IPersistentObject.R_OBJECT_ID))
				{
					stringBuffer
						.append(" ,").append(IDCTMPlugin.NEWLINE).append('\t').append(attributeName); //$NON-NLS-1$
				}
			}
			stringBuffer.append(IDCTMPlugin.NEWLINE)
				.append("from").append(IDCTMPlugin.NEWLINE).append('\t') //$NON-NLS-1$
				.append(typeModel.getName()).append(';');
			return stringBuffer.toString();
		} catch(DfException dex)
		{
			MessageView.getInstance().addMessage(dex);
		} finally
		{
			if(result != null)
			{
				try
				{
					result.close();
				} catch(DfException dex)
				{
					MessageView.getInstance().addMessage(dex);
				}
			}
		}
		return ""; //$NON-NLS-1$
	}

}
