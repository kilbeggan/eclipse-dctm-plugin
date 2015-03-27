/*-
 * $Log: ConnectAction.java,v $
 * Revision 1.5  2005/12/04 22:14:39  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.4  2005/12/04 20:26:59  madcook
 * Version 3.0.0 work started.
 * 
 * Revision 1.3 2005/11/21 13:04:31 madcook 
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 * 
 * Revision 1.2 2005/03/25 09:19:27 harpechr 
 * Version 2.0.0 code that has implemented the new java 1.5 features. 
 * 
 * Revision 1.1 2005/02/28 07:37:15 harpechr 
 * Refactored actions so that they are in view spesific packages.
 * 
 * Revision 1.5 2005/02/09 14:01:15 harpechr 
 * Version 1.0.5 work started.
 * 
 * Revision 1.4 2005/01/24 12:34:53 harpechr 
 * Version 1.0.4 work started.
 * 
 * Revision 1.3 2005/01/11 14:02:00 harpechr 
 * Changed version number from 1.0.2 to 1.0.3. 
 * 
 * Revision 1.2 2005/01/09 10:46:54 harpechr 
 * Renamed classes that were abstract by prefixing the class name with 
 * 'Abstract'. 
 * 
 * Revision 1.1 2005/01/07 12:37:48 harpechr 
 * Version 1.0.2 code. First CVS commit!
 */

package org.cah.eclipse.plugins.dctm.dql.actions.connection;

import org.cah.eclipse.plugins.dctm.dql.actions.AbstractBaseAction;
import org.cah.eclipse.plugins.dctm.dql.editors.api.APIEditorInput;
import org.cah.eclipse.plugins.dctm.dql.editors.api.APIKeywordScanner;
import org.cah.eclipse.plugins.dctm.dql.editors.dql.DQLEditorInput;
import org.cah.eclipse.plugins.dctm.dql.editors.dql.DQLKeywordScanner;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionModel;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;


/**
 * <H4>Use existing connection details and connect to a Docbase.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 26, 2004 9:10:48 AM.</DD>
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
public class ConnectAction
							extends
								AbstractBaseAction
{

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 9:10:48 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public ConnectAction()
	{

		super();
	}

	/**
	 * Conect to the docbase and populate the type, table, attribute and column
	 * keywords for colour coding and then open up a DQL editor if one isn't
	 * already open.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 10:52:38 AM</DD>
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

		final ConnectionModel connectionModel = (ConnectionModel) ConnectionView
			.getInstance().getSelectedModel();
		if(!connectionModel.isConnected())
		{
			ConnectionView.getInstance().connect(connectionModel);
		}
		DQLKeywordScanner.populateTypesAndAttributes();
		APIKeywordScanner.populateTypesAndAttributes();
		/* Find buffer editor. */
		IEditorPart bufferEditor = null;
		final IEditorReference[] editorRefs = ConnectionView.getInstance()
			.getViewSite().getPage().getEditorReferences();
		for(int editorIndex = 0; editorIndex < editorRefs.length; editorIndex++)
		{
			final IEditorPart editorPart = editorRefs[editorIndex]
				.getEditor(false);
			if((editorPart != null)
				&& ((editorPart.getEditorInput() instanceof DQLEditorInput) || (editorPart
					.getEditorInput() instanceof APIEditorInput)))
			{
				bufferEditor = editorPart;
				break;
			}
		}
		/* If editor exists, setFocus. */
		if(bufferEditor != null)
		{
			bufferEditor.setFocus();
		} else
		{
			ConnectionView.getInstance().getNewDqlEditorAction().run();
		}
	}
}
