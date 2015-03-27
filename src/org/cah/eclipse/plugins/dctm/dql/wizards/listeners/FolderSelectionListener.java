/*-
 * $Log: FolderSelectionListener.java,v $
 * Revision 1.4  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.3  2005/12/04 20:31:44  madcook
 * Version 3.0.0 work started and moved wizards to their own packages.
 *
 * Revision 1.2  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.1  2005/04/01 11:04:24  harpechr
 * The create BOF / TBO interfaces and classes functionality.
 *
 * Revision 1.6  2005/03/25 09:22:07  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/10 11:33:38  harpechr
 * Safety commit!
 *
 * Revision 1.4  2005/02/09 14:01:41  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:58  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:20  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:51  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.wizards.listeners;

import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Text;


/**
 * <H4>Change text box folder path value.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Dec 3, 2004 2:11:43 PM.</DD>
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
 * @since 2.0.0
 */
public class FolderSelectionListener
									implements
										SelectionListener
{

	/**
	 * The text box to alter.
	 * 
	 * @since 2.0.0
	 */
	private Text	text	= null;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 2:24:51 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param targetTextBox
	 *            the text box whose walues is about to change.
	 */
	public FolderSelectionListener(final Text targetTextBox)
	{

		super();
		setText(targetTextBox);
	}

	/**
	 * Do nothing.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 2:12:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param selectionEvent
	 *            an event containing information about the selection
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(final SelectionEvent selectionEvent)
	{

		/* Do nothing. */

	}

	/**
	 * Event that is triggered when the check box is selected.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 2:12:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param selectionEvent
	 *            an event containing information about the selection
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(final SelectionEvent selectionEvent)
	{

		final DirectoryDialog dialog = new DirectoryDialog(ConnectionView
			.getInstance().getSite().getShell());
		dialog.setFilterPath(getText().getText());
		final String targetFolder = dialog.open();
		if((targetFolder != null) && (!targetFolder.equals(""))) //$NON-NLS-1$
		{
			getText().setText(targetFolder);
		}
	}

	/**
	 * Get the text box.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 30-Mar-2005 11:58:12</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return The text box.
	 */
	private Text getText()
	{

		return this.text;
	}

	/**
	 * Set the text box.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 30-Mar-2005 11:57:17</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aText
	 *            The text box.
	 */
	private void setText(final Text aText)
	{

		this.text = aText;
	}
}
