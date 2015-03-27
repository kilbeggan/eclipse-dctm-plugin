/*-
 * $Log: DumpObjectAction.java,v $
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
 * Revision 1.7  2005/02/10 11:33:33  harpechr
 * Safety commit!
 *
 * Revision 1.6  2005/02/09 14:01:15  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.5  2005/01/24 12:34:53  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.4  2005/01/11 14:02:00  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.3  2005/01/11 13:47:24  harpechr
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

package org.cah.eclipse.plugins.dctm.dql.actions.results;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.eclipse.swt.widgets.TableItem;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;


/**
 * <H4>Dump the selected result rows object into the DCTM Messages view.</H4>
 * <DL>
 * <DT><B>Description : </B>
 * <DD>Dump the selected result rows object into the DCTM Messages view. For
 * this action to work the selected row must contain a <code>r_object_id</code>
 * column.</DD>
 * </DT>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 30, 2004 12:12:15 PM.</DD>
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
public class DumpObjectAction
								extends
									AbstractResultAction
{

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 12:12:15 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public DumpObjectAction()
	{

		super();
	}

	/**
	 * Can the action be executed.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 10:39:59</DD>
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
	 * Can the row be executed.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 10:41:13</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param tableItem
	 *            the row to be executed.
	 * @return true if the row is OK to execute.
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
	 * Run the single row.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 10:38:11</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param tableItem
	 * @return true if the object was dumped.
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
			final String tag = ConnectionView.getInstance()
				.getSelectedConnection().getTag();
			MessageView.getInstance().addMessage(
				tag + " dump,c," + getRObjectId()); //$NON-NLS-1$
			MessageView.getInstance().addMessage(
				tag + ' ' + session.apiGet("dump", getRObjectId())); //$NON-NLS-1$
			return true;
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
