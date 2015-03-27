/*-
 * $Log: CreateTBOsAction.java,v $
 * Revision 1.4  2005/12/04 22:14:39  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.3  2005/12/04 20:26:59  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.2  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.1  2005/04/01 11:04:16  harpechr
 * The create BOF / TBO interfaces and classes functionality.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.connection;

import org.cah.eclipse.plugins.dctm.dql.actions.AbstractBaseAction;
import org.cah.eclipse.plugins.dctm.dql.wizards.tbo.TBOGenerationWizard;
import org.eclipse.jface.wizard.WizardDialog;


/**
 * <H4>Launch the TBO creation wizard action.</H4>
 * <DL>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>29.3.2005 17:26:30.</DD>
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
 * @author Christopher Harper account: HARPEC
 * @version 3.0.0
 * @since 2.0.0
 */

public class CreateTBOsAction
								extends
									AbstractBaseAction
{

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 17:26:34</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 */
	public CreateTBOsAction()
	{

		super();
	}

	/**
	 * Run the create TBO action.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 17:30:36</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run()
	{

		final TBOGenerationWizard wizard = new TBOGenerationWizard();
		final WizardDialog dialog = new WizardDialog(getView().getSite()
			.getShell(), wizard);
		dialog.open();
	}

}
