/*-
 * $Log: GetContentAction.java,v $
 * Revision 1.5  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.4  2005/12/04 20:25:44  madcook
 * Version 3.0.0 work started.
 * 
 * Revision 1.3 2005/11/21 13:04:32 madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5
 * settings. 
 * 
 * Revision 1.2 2005/03/25 09:19:27 harpechr 
 * Version 2.0.0 code that has implemented the new java 1.5 features. 
 * 
 * Revision 1.1 2005/02/28 07:35:21 harpechr 
 * Refactored actions so that they are in view spesific packages.
 * 
 * Revision 1.7 2005/02/09 14:01:15 harpechr 
 * Version 1.0.5 work started.
 * 
 * Revision 1.6 2005/01/24 12:34:53 harpechr 
 * Version 1.0.4 work started.
 * 
 * Revision 1.5 2005/01/11 14:02:00 harpechr 
 * Changed version number from 1.0.2 to 1.0.3. 
 * 
 * Revision 1.4 2005/01/11 13:47:25 harpechr 
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof). 
 * 
 * Revision 1.3 2005/01/10 08:45:12 harpechr 
 * Changed the copyright statement from my work standard to my name and the 
 * licence to GNU.
 * 
 * Revision 1.2 2005/01/09 10:46:54 harpechr 
 * Renamed classes that were abstract by prefixing the class name with 
 * 'Abstract'. 
 * 
 * Revision 1.1 2005/01/07 12:37:48
 * harpschr Version 1.0.2 code. First CVS commit!
 */

package org.cah.eclipse.plugins.dctm.dql.actions.results;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;
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
 * <H4>Action to return the content of a document.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Dec 23, 2004 4:54:07 PM.</DD>
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
public class GetContentAction
								extends
									AbstractResultAction
{

	/**
	 * Path to the returned file
	 * 
	 * @since 1.0.5
	 */

	private String	fileNameAndPath	= null;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 4:54:07 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.1
	 */
	public GetContentAction()
	{

		super();
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
					path.substring(path.lastIndexOf('\\'))};
			}
		}
		return new String[] {
			DCTMPlugin.getClientX().getClientRegistry().getCheckoutDirectory(),
			getFileName(aSession, anObjectId)};
	}

	/**
	 * Get the location and filename where to check out a file.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 13:56:36</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @param anObjectId
	 *            the id of the file.
	 * @param aSession
	 *            the session to use.
	 * @since 1.0.5
	 * @return 0 = folder path, 1 = object name.
	 * @throws DfException
	 *             if the name can't be returned.
	 */
	protected String[] getCheckOutLocation(final IDfSession aSession,
											final String anObjectId) throws DfException
	{

		return new String[] {
			DCTMPlugin.getClientX().getClientRegistry().getCheckoutDirectory(),
			getFileName(aSession, anObjectId)};
	}

	/**
	 * Get the file name and path.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 17:13:35</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return path to the file.
	 */

	protected String getFileNameAndPath()
	{

		return this.fileNameAndPath;
	}

	/**
	 * Can the action be executed.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 10:11:24</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return true if it's OK to execute the action.
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
	 * Get the content from the content server.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 13:29:39</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param tableItem
	 *            the row to work with.
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
			final FileDialog dialog = new FileDialog(getView().getSite()
				.getShell(), SWT.SAVE);
			if(dialog != null)
			{
				final String[] location = new String[] {null,
					getFileName(session, getRObjectId())};
				dialog.setFilterPath(location[0]);
				dialog.setFileName(location[1]);
				final String fileName = dialog.open();
				final String tag = ConnectionView.getInstance()
					.getSelectedConnection().getTag();
				if((fileName != null) && (!fileName.equals(""))) //$NON-NLS-1$
				{
					final String value = getRObjectId() + ',' + fileName;
					MessageView.getInstance().addMessage(
						tag + " getfile,c," + value); //$NON-NLS-1$
					setFileNameAndPath(session.apiGet("getfile", value)); //$NON-NLS-1$
					MessageView.getInstance().addMessage(
						tag + ' ' + getFileNameAndPath());
					return true;
				}
				MessageView.getInstance().addMessage(
					tag + " No filename given!"); //$NON-NLS-1$
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

	/**
	 * Set the file name and path.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 17:13:39</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param aFileName
	 *            path to the file.
	 */

	protected void setFileNameAndPath(final String aFileName)
	{

		this.fileNameAndPath = aFileName;
	}
}
