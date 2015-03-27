/*-
 * $Log: NewTBOModuleWizard.java,v $
 * Revision 1.1  2005/12/04 22:14:40  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.wizards.modules.tbo;

import org.cah.eclipse.plugins.dctm.dql.IImageCache;
import org.cah.eclipse.plugins.dctm.dql.ImageCache;
import org.cah.eclipse.plugins.dctm.dql.wizards.modules.AbstractModuleImplementationPage;
import org.eclipse.jface.wizard.Wizard;


/**
 * <H4>New TBO module wizard.</H4>
 * <DL>
 * <DT><B>Description :</B>
 * <DD>Container for holding pages defining a TBO module.</DD>
 * </DT>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Mad Cook</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>25-Nov-2005 14:50:14.</DD>
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
public class NewTBOModuleWizard
								extends
									Wizard
{

	/**
	 * A TBO module wizards sole constructor.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25-Nov-2005 14:50:14</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 */
	public NewTBOModuleWizard()
	{

		super();
	}

	/**
	 * Add pages to the wizard.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 03-Dec-2005 15:56:58</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages()
	{

		final ModuleImplementationPage mip = new ModuleImplementationPage(
			AbstractModuleImplementationPage
				.getString("NewTBOModuleWizard.IMPLEMENTATION_PAGE_NAME"), //$NON-NLS-1$
			AbstractModuleImplementationPage
				.getString("NewTBOModuleWizard.IMPLEMENTATION_PAGE_TITLE"), //$NON-NLS-1$
			ImageCache.getImageDescriptor(IImageCache.MODULE_IMPLEMENTATION));
		addPage(mip);

		final ModuleGeneralPage mgp = new ModuleGeneralPage(
			AbstractModuleImplementationPage
				.getString("NewTBOModuleWizard.GENERAL_PAGE_NAME"), //$NON-NLS-1$
			AbstractModuleImplementationPage
				.getString("NewTBOModuleWizard.GENERAL_PAGE_TITLE"), ImageCache //$NON-NLS-1$
				.getImageDescriptor(IImageCache.MODULE_GENERAL));
		mip.setModuleGeneralPage(mgp);
		mgp.setModuleImplementationPage(mip);
		addPage(mgp);

	}

	/**
	 * Store the changes.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25-Nov-2005 14:50:41</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return true if the module was successfully created.
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish()
	{

		return true;
	}

}
