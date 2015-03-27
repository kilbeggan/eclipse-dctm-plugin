/*-
 * $Log: ExportSelectedAction.java,v $
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

import org.cah.eclipse.plugins.dctm.dql.actions.AbstractBaseAction;
import org.cah.eclipse.plugins.dctm.dql.views.ResultView;
import org.cah.eclipse.plugins.dctm.dql.wizards.export.ExportDataWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.TableItem;


/**
 * <H4>Export the selected rows into a file.</H4>
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
public class ExportSelectedAction
									extends
										AbstractBaseAction
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
	public ExportSelectedAction()
	{

		super();
	}

	/**
	 * If no rows are selected select them all. Make sure that something is
	 * selected and launch the export dialog.
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

		if(getView() instanceof ResultView)
		{
			final ResultView resultView = (ResultView) getView();
			if(resultView.getSelectedTable() != null)
			{
				TableItem[] selected = resultView.getSelectedTable()
					.getSelection();
				if((selected == null) || (selected.length < 1))
				{
					resultView.getSelectedTable().select(0,
						resultView.getSelectedTable().getItemCount() - 1);
					selected = resultView.getSelectedTable().getSelection();
				}
				if((selected != null) && (selected.length > 0))
				{
					final ExportDataWizard wizard = new ExportDataWizard();
					final WizardDialog dialog = new WizardDialog(resultView
						.getSite().getShell(), wizard);
					dialog.open();
				}
			}
		}
	}
}
