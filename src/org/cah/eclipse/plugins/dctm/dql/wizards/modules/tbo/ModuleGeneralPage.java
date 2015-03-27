/*-
 * $Log: ModuleGeneralPage.java,v $
 * Revision 1.1  2005/12/04 22:14:40  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.wizards.modules.tbo;

import org.cah.eclipse.plugins.dctm.dql.wizards.modules.AbstractModuleGeneralPage;
import org.eclipse.jface.resource.ImageDescriptor;


/**
 * <H4>Page for general module settings.</H4>
 * <DL>
 * <DT><B>Description :</B>
 * <DD>Page for defining general settings about a module.</DD>
 * </DT>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Mad Cook</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>01-Dec-2005 13:41:14.</DD>
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
public class ModuleGeneralPage
								extends
									AbstractModuleGeneralPage
{

	/**
	 * Constructor for a page with a name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 01-Dec-2005 13:41:14</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aPageName
	 *            name of the page
	 */
	public ModuleGeneralPage(final String aPageName)
	{

		super(aPageName);
	}

	/**
	 * Constructor for a general module page with a page name, page title and a
	 * page image.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 01-Dec-2005 13:41:14</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param pageName
	 *            name of the page.
	 * @param title
	 *            title of the page.
	 * @param titleImage
	 *            an image for the page.
	 */
	public ModuleGeneralPage(final String pageName, final String title,
								final ImageDescriptor titleImage)
	{

		super(pageName, title, titleImage);
	}
}
