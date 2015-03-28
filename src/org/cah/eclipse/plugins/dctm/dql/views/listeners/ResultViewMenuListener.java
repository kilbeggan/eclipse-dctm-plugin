/*-
 * $Log: ResultViewMenuListener.java,v $
 * Revision 1.9  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.8  2005/12/04 20:29:56  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.7  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.6  2005/03/25 09:21:39  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/10 11:33:38  harpechr
 * Safety commit!
 *
 * Revision 1.4  2005/02/09 14:01:44  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:57  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:19  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:51  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.views.listeners;

import org.cah.eclipse.plugins.dctm.dql.views.ResultView;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;


/**
 * <H4>A result view menu listener that gets informed when a menu is about to
 * show.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 25, 2004 4:06:54 PM.</DD>
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
public class ResultViewMenuListener
									implements
										IMenuListener
{

	/**
	 * The result view.
	 * 
	 * @since 1.0
	 */
	private ResultView			resultView		= null;

	/**
	 * The result table.
	 * 
	 * @since 1.0
	 */
	private Table				table			= null;

	/**
	 * Sub menu for content related operations.
	 * 
	 * @since 1.0.5
	 */

	private static final String	SUBMENU_CONTENT	= "Content";	//$NON-NLS-1$

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 11:56:18 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aResultView
	 *            the result view.
	 * @param aTable
	 *            the result table.
	 */
	public ResultViewMenuListener(final ResultView aResultView,
									final Table aTable)
	{

		super();
		setResultView(aResultView);
		setTable(aTable);
	}

	/**
	 * Notifies this listener that the menu is about to be shown by the given
	 * menu manager and given the number of selected rows different menu items
	 * are displayed.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 4:10:08 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param manager
	 *            the menu manager
	 * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
	public void menuAboutToShow(final IMenuManager manager)
	{

		final IMenuManager contentSubmenu = new MenuManager(
			ResultViewMenuListener.SUBMENU_CONTENT);
		final TableItem[] selectedItems = getTable().getSelection();
		manager.add(getResultView().getExportResultsAction());
		if(selectedItems != null)
		{
			if(selectedItems.length == 1)
			{
				if(selectedItems[0].getText(0).equals(
					String.valueOf(ResultView.getMaxRowCount() + 1)))
				{
					manager.add(getResultView().getReturnAllRowsAction());
				} else
				{
					String cellValue = getResultView().getMouseClickCellValue();
					if (cellValue != null && cellValue.length() > 0) {
						manager.add(new Separator());
						manager.add(getResultView().getCopyCellValueToClipboardAction());
						manager.add(new Separator());	
					}
					
					manager.add(getResultView().getDumpObjectAction());
					manager.add(getResultView().getFetchApiAction());
					manager.add(getResultView().getReturnAllVersionsAction());
					contentSubmenu.add(getResultView().getGetContentAction());
					contentSubmenu.add(getResultView().getCheckOutAction());
					contentSubmenu.add(getResultView().getCancelCheckOutAction());
					contentSubmenu.add(getResultView().getCheckInAction());
					contentSubmenu.add(getResultView().getCheckInMinorAction());
					contentSubmenu.add(getResultView().getCheckInMajorAction());
					manager.add(new Separator(ResultViewMenuListener.SUBMENU_CONTENT));
					manager.appendToGroup(ResultViewMenuListener.SUBMENU_CONTENT, contentSubmenu);
				}
			} else
			{
				manager.add(getResultView().getExportSelectedAction());
			}
		}
	}

	/**
	 * Get the result view.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 12:07:12 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the parent view.
	 */
	protected ResultView getResultView()
	{

		return this.resultView;
	}

	/**
	 * Get the result table.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 11:57:44 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the table
	 */
	protected Table getTable()
	{

		return this.table;
	}

	/**
	 * Set the result view.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 12:07:33 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aResultView
	 *            the result view.
	 */
	protected void setResultView(final ResultView aResultView)
	{

		this.resultView = aResultView;
	}

	/**
	 * Set the result table.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 11:57:44 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aTable
	 *            The table to set
	 */
	protected void setTable(final Table aTable)
	{

		this.table = aTable;
	}
}
