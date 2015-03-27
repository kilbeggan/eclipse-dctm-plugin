/*-
 * $Log: ExecuteAction.java,v $
 * Revision 1.5  2005/12/04 22:14:39  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.4  2005/12/04 20:26:59  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.3  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.2  2005/03/25 09:19:27  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.1  2005/02/28 07:37:15  harpechr
 * Refactored actions so that they are in view spesific packages.
 *
 * Revision 1.6  2005/02/10 11:33:33  harpechr
 * Safety commit!
 *
 * Revision 1.5  2005/02/09 14:01:15  harpechr
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

package org.cah.eclipse.plugins.dctm.dql.actions.connection;

import org.cah.eclipse.plugins.dctm.dql.actions.AbstractBaseAction;
import org.cah.eclipse.plugins.dctm.dql.editors.APIEditor;
import org.cah.eclipse.plugins.dctm.dql.editors.DQLEditor;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionModel;
import org.eclipse.ui.IEditorPart;


/**
 * <H4>Execute DQL or API statements.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 28, 2004 8:22:18 PM.</DD>
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
public class ExecuteAction
							extends
								AbstractBaseAction
{

	/**
	 * The id of this action.
	 * 
	 * @since 1.0
	 */
	public final static String	ID	= ExecuteAction.class.getName();

	/**
	 * Execute the data from the selected editor if it's an instance of API or
	 * DQL editor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 8:25:25 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param newTab
	 *            open a new tab.
	 */
	public void execute(final boolean newTab)
	{

		final ConnectionModel connectionModel = ConnectionView.getInstance()
			.getSelectedConnection();
		if(connectionModel == null)
		{
			MessageView.getInstance().addMessage(
				"Please select a Docbase connection."); //$NON-NLS-1$
			return;
		}
		IEditorPart editor = null;
		if(getWindow() != null)
		{
			editor = getWindow().getActivePage().getActiveEditor();
		} else
		{
			editor = getView().getSite().getWorkbenchWindow().getActivePage()
				.getActiveEditor();
		}
		if(editor != null)
		{
			if(editor instanceof DQLEditor)
			{
				ConnectionView.getInstance().executeDQL((DQLEditor) editor,
					newTab);
			} else if(editor instanceof APIEditor)
			{
				if(newTab)
				{
					MessageView.getInstance().addMessage(
						"Invalid execute command for API's."); //$NON-NLS-1$
				} else
				{
					ConnectionView.getInstance().executeAPI((APIEditor) editor);
				}
			} else
			{
				MessageView.getInstance().addMessage(
					"Active editor is not for DQL or API."); //$NON-NLS-1$
			}
		} else
		{
			MessageView.getInstance().addMessage("No active editor."); //$NON-NLS-1$
		}
	}

	/**
	 * Just call <code>execute</code> with argument value <code>false</false>.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 10:26:52 AM</DD>
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

		execute(false);
	}

}
