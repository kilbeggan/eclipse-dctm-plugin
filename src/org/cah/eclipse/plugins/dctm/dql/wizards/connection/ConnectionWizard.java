/*-
 * $Log: ConnectionWizard.java,v $
 * Revision 1.2  2005/12/04 22:17:22  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.1  2005/12/04 20:31:44  madcook
 * Version 3.0.0 work started and moved wizards to their own packages.
 *
 * Revision 1.8  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.7  2005/04/01 11:05:11  harpechr
 * Added images to dialogs.
 *
 * Revision 1.6  2005/03/25 09:18:58  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/10 11:33:38  harpechr
 * Safety commit!
 *
 * Revision 1.4  2005/02/09 14:01:38  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:56  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:17  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:49  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.wizards.connection;

import org.cah.eclipse.plugins.dctm.dql.DCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.IImageCache;
import org.cah.eclipse.plugins.dctm.dql.ImageCache;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionModel;
import org.eclipse.jface.wizard.Wizard;


/**
 * <H4>Wizard containing the connection page.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 25, 2004 10:48:52 AM.</DD>
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
public class ConnectionWizard
								extends
									Wizard

{

	/**
	 * The connection model.
	 * 
	 * @since 1.0
	 */
	private ConnectionModel	connectionModel	= null;

	/**
	 * The connection page.
	 * 
	 * @since 1.0
	 */
	private ConnectionPage	connectionPage	= null;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 10:48:52 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public ConnectionWizard()
	{

		super();
	}

	/**
	 * Add the connection page.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 4:44:42 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	@Override
	public void addPages()
	{

		setConnectionPage(new ConnectionPage(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_NEW_CONNECTION), DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_NEW_CONNECTION), ImageCache
			.getImageDescriptor(IImageCache.CONNECT_ICON), getConnectionModel()));
		addPage(getConnectionPage());
	}

	/**
	 * Initialize the connection wizard.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 10:53:36 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public void init()
	{

		setWindowTitle("New Connection"); //$NON-NLS-1$
		setConnectionModel(null);
	}

	/**
	 * Initialize the connection wizard with ready connection information.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 2:46:37 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aConnectionModel
	 *            curren connection information.
	 */
	public void init(final ConnectionModel aConnectionModel)
	{

		setWindowTitle("Edit Connection"); //$NON-NLS-1$
		setConnectionModel(aConnectionModel);

	}

	/**
	 * Save the user information.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 10:51:56 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return true if the save goes well.
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish()
	{

		return getConnectionPage().performFinish();
	}

	/**
	 * Get the connection model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 4:28:25 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the connectino model.
	 */
	protected ConnectionModel getConnectionModel()
	{

		return this.connectionModel;
	}

	/**
	 * Get the connectin page.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 5:05:40 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the connection page.
	 */
	protected ConnectionPage getConnectionPage()
	{

		return this.connectionPage;
	}

	/**
	 * Set the connection model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 2:49:54 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aConnectionModel
	 *            the new model to set.
	 */
	protected void setConnectionModel(final ConnectionModel aConnectionModel)
	{

		this.connectionModel = aConnectionModel;

	}

	/**
	 * Set the connection page.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 5:05:43 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param connectionPage
	 *            the new page.
	 */
	protected void setConnectionPage(ConnectionPage connectionPage)
	{

		this.connectionPage = connectionPage;
	}
}
