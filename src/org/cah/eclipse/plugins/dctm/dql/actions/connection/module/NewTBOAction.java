/*-
 * $Log: NewTBOAction.java,v $
 * Revision 1.1  2005/12/04 21:20:10  madcook
 * Create new TBO action created.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.connection.module;

import org.cah.eclipse.plugins.dctm.dql.actions.AbstractBaseAction;
import org.cah.eclipse.plugins.dctm.dql.wizards.modules.tbo.NewTBOModuleWizard;
import org.eclipse.jface.wizard.WizardDialog;


/**
 * <H4>Open up the create TBO module dialog.</H4>
 * <DL>
 * <DT><B>Description :</B>
 * <DD> Open up a dialog that enables the creation of TBO modules into the
 * Docbase. </DD>
 * </DT>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Mad Cook</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>25-Nov-2005 14:27:12.</DD>
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
 * href="http://www.gnu.org/licenses/gpl.html">GNU General Public License</a>
 * for more details.
 * </p>
 * 
 * @author Mad Cook account: dmadmin
 * @version 3.0.0
 * @since 3.0.0
 */
public class NewTBOAction
							extends
								AbstractBaseAction
{

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25-Nov-2005 14:47:11</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 */
	public NewTBOAction()
	{

		super();
	}

	/**
	 * Create a new TBO module creation dialog.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25-Nov-2005 14:47:56</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run()
	{

		final NewTBOModuleWizard wizard = new NewTBOModuleWizard();
		final WizardDialog dialog = new WizardDialog(getView().getSite()
			.getShell(), wizard);
		dialog.open();
	}
}
