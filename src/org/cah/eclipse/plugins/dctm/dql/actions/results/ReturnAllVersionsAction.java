/*-
 * $Log: ReturnAllVersionsAction.java,v $
 * Revision 1.5  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.4  2005/12/04 20:25:44  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.3  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.2  2005/03/25 09:19:27  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.1  2005/02/28 07:35:21  harpechr
 * Refactored actions so that they are in view spesific packages.
 *
 * Revision 1.7  2005/02/09 13:55:02  harpechr
 * Added previous DQL queries functionality and modified the message view so 
 * that its real time.
 *
 * Revision 1.6  2005/01/24 12:34:53  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.5  2005/01/11 14:02:00  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.4  2005/01/11 13:47:25  harpechr
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof).
 *
 * Revision 1.3  2005/01/10 08:45:12  harpechr
 * Changed the copyright statement from my work standard to my name and the 
 * licence to GNU.
 *
 * Revision 1.2  2005/01/09 10:46:54  harpechr
 * Renamed classes that were abstract by prefixing the class name with 
 * 'Abstract'.
 *
 * Revision 1.1  2005/01/07 12:37:48  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.results;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;
import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;
import org.cah.dctm.bof.tbo.persistent.sysobject.document.IDocument;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.ResultView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.DocumentModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.Model;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;


/**
 * <H4>Return all versions of a result that has the i_chronicle_id.</H4>
 * <DL>
 * </DT>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Dec 23, 2004 4:09:47 PM.</DD>
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
 * @author Christopher Harper account : harpechr
 * @version 3.0.0
 * @since 1.0.1
 */
public class ReturnAllVersionsAction
									extends
										AbstractResultAction
{

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 4:09:47 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.1
	 */
	public ReturnAllVersionsAction()
	{

		super();
	}

	/**
	 * Can the row be executed.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 13:33:05</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param tableItem
	 *            the row to be executed.
	 * @return true if the row can be run.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.results.AbstractResultAction#isRowOkToExecute(org.eclipse.swt.widgets.TableItem)
	 */
	@Override
	protected boolean isRowOkToExecute(final TableItem tableItem)
	{

		if(super.isRowOkToExecute(tableItem))
		{
			setRObjectId(tableItem.getText(getAttributes().indexOf(
				IPersistentObject.R_OBJECT_ID) + 1));
			if(isValidIdValue(getRObjectId(), true))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Can the action be executed.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 11:22:58</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return true if it's OK to execute the action.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.results.AbstractResultAction#isOkToExecute()
	 */
	@Override
	protected boolean isOkToExecute()
	{

		if((super.isOkToExecute())
			&& isColumAvailable(IPersistentObject.R_OBJECT_ID))
		{
			return true;
		}
		MessageView.getInstance().addMessage(
			ConnectionView.getInstance().getSelectedConnection().getTag()
				+ " No '" + IPersistentObject.R_OBJECT_ID //$NON-NLS-1$
				+ "' column found."); //$NON-NLS-1$
		return false;
	}

	/**
	 * Execute the select all versions query.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 4:30:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.1
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run()
	{

		if(getView() instanceof ResultView)
		{
			final ResultView resultView = (ResultView) getView();
			if(resultView.getSelectedTable() != null)
			{
				final TableItem[] selected = resultView.getSelectedTable()
					.getSelection();
				if((selected != null) && (selected.length > 0))
				{
					final TableColumn[] tableColumns = ResultView.getInstance()
						.getSelectedTable().getColumns();
					final TableItem tableItem = selected[0];
					String objectId = null;
					boolean isChronicleId = false;
					for(int columnIndex = 0; columnIndex < tableColumns.length; columnIndex++)
					{
						if(tableColumns[columnIndex].getData().equals(
							ISysObject.I_CHRONICLE_ID)
							|| tableColumns[columnIndex].getData().equals(
								IPersistentObject.R_OBJECT_ID))
						{
							objectId = tableItem.getText(columnIndex);
							if((objectId != null) && (objectId.length() == 16))
							{
								try
								{
									Long.parseLong(objectId, 16);
									if(tableColumns[columnIndex].getData()
										.equals(ISysObject.I_CHRONICLE_ID))
									{
										isChronicleId = true;
										break;
									}
								} catch(final NumberFormatException swallow)
								{
									objectId = null;
									/*
									 * This was just a check if the value was an
									 * id so it's ok to swallow the exception.
									 */
								}
							}
						}
					}
					if(objectId != null)
					{

						final ConnectionModel connectionModel = ConnectionView
							.getInstance().getSelectedConnection();
						IDfSession connection = null;
						try
						{
							connection = connectionModel.getSession();
							final StringBuffer versionsDQL = new StringBuffer(
								"select distinct * from ") //$NON-NLS-1$
								.append(ISysObject.TYPE_DM_SYSOBJECT).append(
									" ( all ) where ") //$NON-NLS-1$
								.append(ISysObject.I_CHRONICLE_ID);
							if(isChronicleId)
							{
								versionsDQL
									.append(" = '").append(objectId).append('\''); //$NON-NLS-1$
							} else
							{
								versionsDQL
									.append(" in ( select distinct ").append(ISysObject.I_CHRONICLE_ID) //$NON-NLS-1$
									.append(" from ").append(ISysObject.TYPE_DM_SYSOBJECT).append(" where ") //$NON-NLS-1$ //$NON-NLS-2$
									.append(IPersistentObject.R_OBJECT_ID)
									.append(" = '").append(objectId) //$NON-NLS-1$
									.append("' )"); //$NON-NLS-1$
							}
							versionsDQL.append(" order by 2"); //$NON-NLS-1$
							final IDfQuery query = new DfQuery();
							query.setDQL(versionsDQL.toString());
							MessageView.getInstance().addMessage(
								connection.getLoginInfo().getDomain() + '\\'
									+ connection.getLoginUserName() + '@'
									+ connection.getDocbaseName() + ' '
									+ query.getDQL());
							ResultView.getInstance().addResultSet(
								query.execute(connection,
									IDfQuery.DF_EXECREAD_QUERY),
								"Versions", false, //$NON-NLS-1$
								query.getDQL(), true);
						} catch(DfException dex)
						{
							MessageView.getInstance().addMessage(dex);
						} finally
						{
							connectionModel.releaceConnection(connection);
						}

					} else
					{
						MessageView.getInstance().addMessage(
							"No r_object_id or i_chronicle_id value found."); //$NON-NLS-1$
					}
				} else
				{
					MessageView.getInstance().addMessage(
						"No result row selected."); //$NON-NLS-1$
				}
			} else
			{
				MessageView.getInstance().addMessage(
					"No results table selected."); //$NON-NLS-1$
			}
		} else
		{
			MessageView.getInstance().addMessage("Result view not selected."); //$NON-NLS-1$
		}

		final Model selectedModel = ConnectionView.getInstance()
			.getSelectedModel();
		if(selectedModel instanceof DocumentModel)
		{
			final DocumentModel documentModel = (DocumentModel) selectedModel;
			final ConnectionModel connectionModel = ConnectionView
				.getInstance().getSelectedConnection();
			IDfSession connection = null;
			try
			{
				connection = connectionModel.getSession();
				final StringBuffer attributeDQL = new StringBuffer(
					"select distinct * from ") //$NON-NLS-1$
					.append(IDocument.TYPE_DM_DOCUMENT).append(
						" in document id ( '") //$NON-NLS-1$
					.append(documentModel.getObjectId().getId()).append(
						"' order by 2"); //$NON-NLS-1$
				final IDfQuery query = new DfQuery();
				query.setDQL(attributeDQL.toString());
				MessageView.getInstance().addMessage(
					connection.getLoginInfo().getDomain() + '\\'
						+ connection.getLoginUserName() + '@'
						+ connection.getDocbaseName() + ' ' + query.getDQL());
				ResultView.getInstance().addResultSet(
					query.execute(connection, IDfQuery.DF_EXECREAD_QUERY),
					"Document content", false, query.getDQL(), true); //$NON-NLS-1$
				documentModel.getName();
			} catch(DfException dex)
			{
				MessageView.getInstance().addMessage(dex);
			} finally
			{
				connectionModel.releaceConnection(connection);
			}
		}
	}

	/**
	 * Execute a single result row.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 11:20:58</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param tableItem
	 *            the row to execute.
	 * @return true if the row was executed ok.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.results.AbstractResultAction#runRow(org.eclipse.swt.widgets.TableItem)
	 */
	@Override
	protected boolean runRow(final TableItem tableItem)
	{

		final StringBuffer versionsDQL = new StringBuffer(
			"select distinct * from ") //$NON-NLS-1$
			.append(ISysObject.TYPE_DM_SYSOBJECT)
			.append(" ( all ) where ").append(ISysObject.I_CHRONICLE_ID); //$NON-NLS-1$
		String usedID = null;
		if(isColumAvailable(ISysObject.I_CHRONICLE_ID))
		{
			usedID = tableItem.getText(getAttributes().indexOf(
				ISysObject.I_CHRONICLE_ID) + 1);
			versionsDQL.append(" = '").append(usedID).append('\''); //$NON-NLS-1$
		} else
		{
			usedID = getRObjectId();
			versionsDQL
				.append(" in ( select distinct ").append(ISysObject.I_CHRONICLE_ID).append(" from ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(ISysObject.TYPE_DM_SYSOBJECT)
				.append(" where ").append(IPersistentObject.R_OBJECT_ID) //$NON-NLS-1$
				.append(" = '").append(usedID).append("' )"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		versionsDQL.append(" order by 2"); //$NON-NLS-1$
		final IDfQuery query = new DfQuery();
		query.setDQL(versionsDQL.toString());
		IDfSession session = null;
		try
		{
			session = ConnectionView.getInstance().getSelectedConnection()
				.getSession();
			MessageView.getInstance().addMessage(
				ConnectionView.getInstance().getSelectedConnection().getTag()
					+ ' ' + query.getDQL());
			ResultView.getInstance().addResultSet(
				query.execute(session, IDfQuery.DF_EXECREAD_QUERY),
				usedID + " versions", true, query.getDQL(), true); //$NON-NLS-1$
		} catch(final DfException dex)
		{
			MessageView.getInstance().addMessage(
				ConnectionView.getInstance().getSelectedConnection().getTag()
					+ ' ' + dex.getMessage());
		} finally
		{
			ConnectionView.getInstance().getSelectedConnection()
				.releaceConnection(session);
		}
		return false;
	}
}
