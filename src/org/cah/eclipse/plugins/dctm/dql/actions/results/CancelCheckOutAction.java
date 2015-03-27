/*-
 * $Log: CancelCheckOutAction.java,v $
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
 * Revision 1.1  2005/03/25 09:10:17  harpechr
 * Cancel the checkout of a document checked out by the connected user.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.results;

import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionContentProvider;
import org.eclipse.swt.widgets.TableItem;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;


/**
 * <H4>Cancel the checkout of a document if it's checked out by the user. If
 * the document was checked out using this plugin the local copy will be
 * destroyed.</H4>
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
 * @since 1.0.0
 */
public class CancelCheckOutAction
									extends
										CheckInAction
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
	public CancelCheckOutAction()
	{

		super();
	}

	/**
	 * Execute the result row.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>18.3.2005 11:33:19</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param tableItem
	 *            the result row.
	 * @return true if the cancel checkout worked.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.results.AbstractResultAction#runRow(org.eclipse.swt.widgets.TableItem)
	 */
	@Override
	protected boolean runRow(final TableItem tableItem)
	{

		IDfSession session = null;
		try
		{
			session = ConnectionView.getInstance().getSelectedConnection()
				.getSession();
			MessageView.getInstance().addMessage(
				ConnectionView.getInstance().getSelectedConnection().getTag()
					+ " unlock,c," + getRObjectId()); //$NON-NLS-1$
			MessageView
				.getInstance()
				.addMessage(
					(new Boolean(session.apiExec("unlock", getRObjectId()))).toString()); //$NON-NLS-1$
			if(ConnectionContentProvider.getCheckedOutDocuments().containsKey(
				getRObjectId()))
			{
				removeFile(ConnectionContentProvider.getCheckedOutDocuments()
					.remove(getRObjectId()));
			}
			return true;
		} catch(DfException dex)
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
