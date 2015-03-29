/*-
 * $Log: NewApiEditorAction.java,v $
 * Revision 1.6  2005/12/04 22:14:39  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.5  2005/12/04 20:26:59  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.4  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.3  2005/04/01 11:26:57  harpechr
 * Reference to a static string changed to an interface.
 *
 * Revision 1.2  2005/03/25 09:19:27  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.1  2005/02/28 07:37:14  harpechr
 * Refactored actions so that they are in view spesific packages.
 *
 * Revision 1.7  2005/02/10 11:33:33  harpechr
 * Safety commit!
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

package org.cah.eclipse.plugins.dctm.dql.actions.connection;

import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.actions.AbstractBaseAction;
import org.cah.eclipse.plugins.dctm.dql.editors.APIEditor;
import org.cah.eclipse.plugins.dctm.dql.editors.api.APIEditorInput;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;


/**
 * <H4>Create a new API statement buffer and place the GNU comment in it.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 28, 2004 11:34:35 AM.</DD>
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
public class NewApiEditorAction
								extends
									AbstractBaseAction
{

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 11:34:36 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public NewApiEditorAction()
	{

		super();
	}

	/**
	 * Create a new API editor buffer.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 9:16:31 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	@Override
	public void run()
	{

		try
		{
			ConnectionView.getInstance().getViewSite().getPage().openEditor(
				new APIEditorInput(), APIEditor.ID);
			final IEditorPart editor = getView().getSite().getWorkbenchWindow()
				.getActivePage().getActiveEditor();
			if(editor instanceof APIEditor)
			{
				((APIEditor) editor).setText(((APIEditor) editor).getText()
					+ IDCTMPlugin.NEWLINE + (hasTwoOrMoreApiEditors() ? "" : APIEditor.getWelcomemessage()));
			}
		} catch(PartInitException piex)
		{
			MessageView.getInstance().addMessage(piex);
		}
	}
	
	private boolean hasTwoOrMoreApiEditors() {
		int count = 0;
		for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
			for (IWorkbenchPage page : window.getPages()) {
				for (IEditorReference ref : page.getEditorReferences()) {
					if (ref.getEditor(false) instanceof APIEditor) {
						if (++count >= 2) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
}
