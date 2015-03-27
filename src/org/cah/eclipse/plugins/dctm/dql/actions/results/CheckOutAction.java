/*-
 * $Log: CheckOutAction.java,v $
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
 * Revision 1.1  2005/03/25 09:12:08  harpechr
 * Check a document out.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.results;

import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionContentProvider;
import org.eclipse.swt.widgets.TableItem;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;


/**
 * <H4>If the document is not checked out anybody do a checkout and copy the
 * content to a local file area.</H4>
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
public class CheckOutAction
							extends
								GetContentAction
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
	public CheckOutAction()
	{

		super();
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
			&& (isColumAvailable(ISysObject.R_LOCK_OWNER)))
		{
			return true;
		}
		MessageView.getInstance().addMessage(
			ConnectionView.getInstance().getSelectedConnection().getTag()
				+ " No '" + ISysObject.R_LOCK_OWNER //$NON-NLS-1$
				+ "' column found."); //$NON-NLS-1$
		return false;
	}

	/**
	 * Check if the current row has an empty r_lock_owner.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 12:05:54</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param tableItem
	 *            the table row to check.
	 * @return true if the r_lock_owner is empty.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.results.GetContentAction#isRowOkToExecute(org.eclipse.swt.widgets.TableItem)
	 */
	@Override
	protected boolean isRowOkToExecute(final TableItem tableItem)
	{

		if(super.isRowOkToExecute(tableItem))
		{
			final String lockOwner = tableItem.getText(getAttributes().indexOf(
				ISysObject.R_LOCK_OWNER) + 1);
			if((lockOwner == null) || (lockOwner.equals(""))) //$NON-NLS-1$
			{
				return true;
			}
			MessageView
				.getInstance()
				.addMessage(
					ConnectionView.getInstance().getSelectedConnection()
						.getTag()
						+ " Document was already checked out by '" + lockOwner + "'."); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return false;
	}

	/**
	 * In adition to getting the content we place a lock on the object.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 14:52:57</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param tableItem
	 *            the row to hadle.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.results.AbstractResultAction#runRow(org.eclipse.swt.widgets.TableItem)
	 */
	@Override
	protected boolean runRow(final TableItem tableItem)
	{

		if(super.runRow(tableItem))
		{
			IDfSession session = null;
			final String tag = ConnectionView.getInstance()
				.getSelectedConnection().getTag();
			try
			{
				session = ConnectionView.getInstance().getSelectedConnection()
					.getSession();
				MessageView.getInstance().addMessage(
					tag + " checkout,c," + getRObjectId()); //$NON-NLS-1$
				MessageView.getInstance().addMessage(
					session.apiGet("checkout", getRObjectId())); //$NON-NLS-1$
				ConnectionContentProvider.getCheckedOutDocuments().put(
					getRObjectId(), getFileNameAndPath());
				ConnectionContentProvider.getInstance().setHasChanged(true);
				return true;
			} catch(final DfException dex)
			{
				MessageView.getInstance().addMessage(
					tag + ' ' + dex.getMessage());
				removeFile(getFileNameAndPath());
			} finally
			{
				ConnectionView.getInstance().getSelectedConnection()
					.releaceConnection(session);
			}
		}
		return false;
	}

}
