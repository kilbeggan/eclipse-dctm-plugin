/*-
 * $Log: ExportSelectionListener.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:31:44  madcook
 * Version 3.0.0 work started and moved wizards to their own packages.
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

import org.cah.eclipse.plugins.dctm.dql.wizards.export.ExportDataPage;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;


/**
 * <H4>The listener for the export file selector.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Dec 2, 2004 12:51:51 PM.</DD>
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
public class ExportSelectionListener
									implements
										SelectionListener
{

	/**
	 * The export data page.
	 * 
	 * @since 1.0
	 */
	private ExportDataPage	exportDataPage	= null;

	/**
	 * Constructor with the data page given.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:51:51 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @param anExportDataPage
	 *            the export data page.
	 * @since 1.0
	 */
	public ExportSelectionListener(final ExportDataPage anExportDataPage)
	{

		super();
		setExportDataPage(anExportDataPage);
	}

	/**
	 * Set the export data page.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:53:50 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anExportDataPage
	 *            the data page to set.
	 */
	protected void setExportDataPage(final ExportDataPage anExportDataPage)
	{

		this.exportDataPage = anExportDataPage;
	}

	/**
	 * Get the export data page.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:53:53 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the export data page.
	 */
	protected ExportDataPage getExportDataPage()
	{

		return this.exportDataPage;
	}

	/**
	 * Event triggered when the button is clicked that opens the file selection
	 * dialog.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:51:51 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param selectionEvent
	 *            information about this event.
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(final SelectionEvent selectionEvent)
	{

		final String filename = getExportDataPage().getDialog().open();
		if(filename != null)
		{
			getExportDataPage().getFilenameText().setText(filename);
		}
	}

	/**
	 * Do nothing
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:51:51 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param selectionEvent
	 *            information about this event.
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(final SelectionEvent selectionEvent)
	{

		/* Do nothing. */
	}

}
