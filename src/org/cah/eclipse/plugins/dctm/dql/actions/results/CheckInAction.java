/*-
 * $Log: CheckInAction.java,v $
 * Revision 1.4  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.3  2005/12/04 20:25:44  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.2  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.1  2005/03/25 09:10:45  harpechr
 * Check a document in.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.results;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;
import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;
import org.cah.eclipse.plugins.dctm.dql.DCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.TableItem;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;


/**
 * <H4>Check a document in if it was checked out by the connected user. If the
 * document was checked out using this plugin the local copy of the file is
 * destroyed after it has been checked in.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>26.2.2005 22:57:21.</DD>
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
 * @since 1.0
 */
public class CheckInAction
							extends
								AbstractResultAction
{

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>26.2.2005 22:57:21</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.3
	 */

	public CheckInAction()
	{

		super();
	}

	/**
	 * Check the document in as same version.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 17:58:07</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param session
	 *            a valid Docbase connection.
	 * @throws DfException
	 *             if the checkin fails.
	 */
	protected void checkInCommand(final IDfSession session) throws DfException
	{

		MessageView.getInstance().addMessage(
			ConnectionView.getInstance().getSelectedConnection().getTag()
				+ " save,c," + getRObjectId()); //$NON-NLS-1$
		MessageView.getInstance().addMessage(
			(new Boolean(session.apiExec("save", getRObjectId()))).toString()); //$NON-NLS-1$

	}

	/**
	 * Get the checkin location.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 13:56:27</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @param anObjectId
	 *            the id of the file.
	 * @param aSession
	 *            a valid Docbase session
	 * @since 1.0.5
	 * @return 0 = checkout folder, 1 = file name.
	 * @throws DfException
	 *             if the name information can't be returned.
	 */
	protected String[] getCheckInLocation(final IDfSession aSession,
											final String anObjectId) throws DfException
	{

		if(ConnectionContentProvider.getCheckedOutDocuments().containsKey(
			anObjectId))
		{
			final String path = ConnectionContentProvider
				.getCheckedOutDocuments().get(anObjectId);
			if(path.lastIndexOf('\\') > -1)
			{
				return new String[] {path.substring(0, path.lastIndexOf('\\')),
					path.substring(path.lastIndexOf('\\') + 1)};
			}
		}
		return new String[] {
			DCTMPlugin.getClientX().getClientRegistry().getCheckoutDirectory(),
			getFileName(aSession, anObjectId)};
	}

	/**
	 * Check can the checkout action be run.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 11:56:34</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return true if it's ok to check the object out.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.results.GetContentAction#isOkToExecute()
	 */
	@Override
	protected boolean isOkToExecute()
	{

		if((super.isOkToExecute())
			&& (isColumAvailable(IPersistentObject.R_OBJECT_ID)))
		{
			if(isColumAvailable(ISysObject.R_LOCK_OWNER))
			{
				return true;
			}
			MessageView.getInstance().addMessage(
				ConnectionView.getInstance().getSelectedConnection().getTag()
					+ " No '" + ISysObject.R_LOCK_OWNER //$NON-NLS-1$
					+ "' column found."); //$NON-NLS-1$
		} else
		{
			MessageView.getInstance().addMessage(
				ConnectionView.getInstance().getSelectedConnection().getTag()
					+ " No '" + IPersistentObject.R_OBJECT_ID //$NON-NLS-1$
					+ "' column found."); //$NON-NLS-1$
		}
		return false;
	}

	/**
	 * Can the row be executed.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 17:35:04</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param tableItem
	 * @return true if logged in user is the same as the lock owner.
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
				final String lockOwner = tableItem.getText(getAttributes()
					.indexOf(ISysObject.R_LOCK_OWNER) + 1);
				String loggedInUser = ConnectionView.getInstance()
					.getSelectedConnection().getUserName();
				if(!lockOwner.equals("")) //$NON-NLS-1$
				{
					IDfSession session = null;
					try
					{
						session = ConnectionView.getInstance()
							.getSelectedConnection().getSession();
						loggedInUser = session.getLoginUserName();
						if(loggedInUser.equals(lockOwner))
						{
							return true;
						}
					} catch(final DfException dex)
					{
						MessageView.getInstance().addMessage(
							ConnectionView.getInstance()
								.getSelectedConnection().getTag()
								+ ' ' + dex.getMessage());
					} finally
					{
						ConnectionView.getInstance().getSelectedConnection()
							.releaceConnection(session);
					}
				}
				MessageView
					.getInstance()
					.addMessage(
						ConnectionView.getInstance().getSelectedConnection()
							.getTag()
							+ " Document was not checked out by '" + loggedInUser + "' lock owner field had value '" //$NON-NLS-1$ //$NON-NLS-2$
							+ lockOwner + "' in it."); //$NON-NLS-1$
			}
		}
		return false;
	}

	/**
	 * Execute the checkin functionality for a single row.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 17:35:40</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param tableItem
	 *            the table row to handle.
	 * @return true if the object was checked in.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.results.AbstractResultAction#runRow(org.eclipse.swt.widgets.TableItem)
	 */
	@Override
	protected boolean runRow(final TableItem tableItem)
	{

		IDfSession session = null;
		try
		{
			final String tag = ConnectionView.getInstance()
				.getSelectedConnection().getTag();
			session = ConnectionView.getInstance().getSelectedConnection()
				.getSession();
			MessageView.getInstance().addMessage(
				tag + " get,c," + ISysObject.R_LOCK_OWNER); //$NON-NLS-1$
			final String lockOwner = session.apiGet(
				"get", ISysObject.R_LOCK_OWNER); //$NON-NLS-1$
			MessageView.getInstance().addMessage(tag + lockOwner);
			if((lockOwner != null)
				&& (lockOwner.equals(session.getLoginUserName())))
			{
				final FileDialog dialog = new FileDialog(getView().getSite()
					.getShell(), SWT.OPEN);
				final String[] location = getCheckInLocation(session,
					getRObjectId());
				dialog.setFilterPath(location[0]);
				dialog.setFileName(location[1]);
				final String fileName = dialog.open();
				if((fileName != null) && (!fileName.equals(""))) //$NON-NLS-1$
				{
					final String value = getRObjectId() + ',' + fileName;
					MessageView.getInstance().addMessage(
						tag + " setfile,c," + value); //$NON-NLS-1$
					MessageView
						.getInstance()
						.addMessage(
							(new Boolean(session.apiExec("setfile", value))).toString()); //$NON-NLS-1$
					checkInCommand(session);
					if(ConnectionContentProvider.getCheckedOutDocuments()
						.containsKey(getRObjectId()))
					{
						final String filePath = ConnectionContentProvider
							.getCheckedOutDocuments().remove(getRObjectId());
						removeFile(filePath);
						ConnectionContentProvider.getInstance().setHasChanged(
							true);
					}
					return true;
				}
				MessageView.getInstance().addMessage(
					tag + " No filename given!"); //$NON-NLS-1$
			} else
			{
				MessageView
					.getInstance()
					.addMessage(
						tag
							+ " The lock owner '" + lockOwner + "' is not the same as the logged in user '" //$NON-NLS-1$ //$NON-NLS-2$
							+ session.getLoginUserName() + "'."); //$NON-NLS-1$
			}
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
