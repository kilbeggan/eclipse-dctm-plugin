/*-
 * $Log: FetchApiAction.java,v $
 * Revision 1.6  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.5  2005/12/04 20:25:44  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.4  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.3  2005/04/01 11:27:59  harpechr
 * Reference to a static string changed to an interface.
 *
 * Revision 1.2  2005/03/25 09:19:27  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.1  2005/02/28 07:35:21  harpechr
 * Refactored actions so that they are in view spesific packages.
 *
 * Revision 1.6  2005/02/10 11:33:33  harpechr
 * Safety commit!
 *
 * Revision 1.5  2005/02/09 14:01:29  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.4  2005/01/24 12:34:53  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.3  2005/01/11 14:02:00  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
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

import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.editors.APIEditor;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.ResultView;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorPart;


/**
 * <H4>Create a fetch statement from each ID on a row.</H4>
 * <DL>
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
public class FetchApiAction
							extends
								AbstractResultAction
{

	/**
	 * Fetch statement buffer.
	 * 
	 * @since 2.0.0
	 */
	private StringBuffer	results	= new StringBuffer();

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
	public FetchApiAction()
	{

		super();
	}

	/**
	 * Go through the rows columns and create a fetch statement from each id
	 * value (Id is 16 chars in length and can be converted to a long using
	 * radix '16').
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 12:13:45 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run()
	{

		setResults(new StringBuffer());
		super.run();
		final IEditorPart editor = getView().getSite().getWorkbenchWindow()
			.getActivePage().getActiveEditor();
		if((editor instanceof APIEditor) && (getResults().length() > 0))
		{
			final APIEditor apiEditor = (APIEditor) editor;
			apiEditor.setText(apiEditor.getText() + getResults().toString());
		}
	}

	/**
	 * Get the result buffer.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 11:09:18</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the fetch statement buffer.
	 */
	protected StringBuffer getResults()
	{

		return this.results;
	}

	/**
	 * Can the action be executed.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 11:09:51</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return true if the action can be run.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.results.AbstractResultAction#isOkToExecute()
	 */
	@Override
	protected boolean isOkToExecute()
	{

		if(super.isOkToExecute())
		{
			final IEditorPart editor = getView().getSite().getWorkbenchWindow()
				.getActivePage().getActiveEditor();
			if(editor instanceof APIEditor)
			{
				return true;
			}
			MessageView.getInstance().addMessage(
				ConnectionView.getInstance().getSelectedConnection().getTag()
					+ " Activate the API editor before calling."); //$NON-NLS-1$
		}
		return false;
	}

	/**
	 * Execute a single row from the results.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 10:54:41</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param tableItem
	 *            the row to execute.
	 * @return true if the row executed ok.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.results.AbstractResultAction#runRow(org.eclipse.swt.widgets.TableItem)
	 */
	@Override
	protected boolean runRow(final TableItem tableItem)
	{

		getResults()
			.append("/*").append(IDCTMPlugin.NEWLINE).append(" * New object.").append(IDCTMPlugin.NEWLINE) //$NON-NLS-1$ //$NON-NLS-2$
			.append(" */"); //$NON-NLS-1$
		final TableColumn[] tableColumns = ResultView.getInstance()
			.getSelectedTable().getColumns();
		for(int columnIndex = 0; columnIndex < tableColumns.length; columnIndex++)
		{
			final String columnValue = tableItem.getText(columnIndex);
			if(isValidIdValue(columnValue, false))
			{
				getResults()
					.append(IDCTMPlugin.NEWLINE)
					.append(
						"/* Fetch api after wich 'l' can be used. Value returned from '" //$NON-NLS-1$
							+ tableColumns[columnIndex].getData() + "'.*/").append(IDCTMPlugin.NEWLINE).append("fetch,c,") //$NON-NLS-1$ //$NON-NLS-2$
					.append(columnValue);
			}
		}
		return false;

	}

	/**
	 * Set the fetch statement buffer.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 11:11:13</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aResults
	 *            new buffer.
	 */
	protected void setResults(final StringBuffer aResults)
	{

		this.results = aResults;
	}
}
