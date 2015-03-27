/*-
 * $Log: AbstractCreateDQLAction.java,v $
 * Revision 1.6  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.5  2005/12/04 20:26:26  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.4  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.3  2005/04/01 11:27:30  harpechr
 * Reference to a static string changed to an interface.
 *
 * Revision 1.2  2005/03/25 09:19:59  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.1  2005/02/28 07:38:25  harpechr
 * Refactored actions so that they are in view and type spesific packages.
 *
 * Revision 1.2  2005/02/09 13:55:02  harpechr
 * Added previous DQL queries functionality and modified the message view so 
 * that its real time.
 *
 * Revision 1.1  2005/01/24 22:24:05  harpechr
 * Create change default storage DQL statement action defined.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.connection.dql;

import java.util.ResourceBundle;

import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.actions.AbstractBaseAction;
import org.cah.eclipse.plugins.dctm.dql.editors.DQLEditor;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.eclipse.ui.IEditorPart;


/**
 * <H4>Base action for created DQL statements.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Jan 24, 2005 8:52:29 PM.</DD>
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
 * @since 1.0.4
 */
public abstract class AbstractCreateDQLAction
												extends
													AbstractBaseAction
{

	/**
	 * The resource bundle name.
	 * 
	 * @since 1.0.4
	 */
	public static final String		RESOURCE_NAME	= AbstractCreateDQLAction.class
														.getName()
														+ "Resources";	//$NON-NLS-1$

	/**
	 * Create group statements bundle.
	 * 
	 * @since 1.0.4
	 */
	private static ResourceBundle	resourceBundle	= null;

	/**
	 * Initialize the resourcebundle.
	 * 
	 * @since 1.0.3
	 */
	static
	{
		setResourceBundle(ResourceBundle
			.getBundle(AbstractCreateDQLAction.RESOURCE_NAME));
	}

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 24, 2005 8:52:29 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 */
	public AbstractCreateDQLAction()
	{

		super();
	}

	/**
	 * Get the resource bundle.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 24, 2005 9:03:18 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @return Returns the resourceBundle.
	 */
	protected static ResourceBundle getResourceBundle()
	{

		return AbstractCreateDQLAction.resourceBundle;
	}

	/**
	 * Set the resource bundle.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 24, 2005 9:03:18 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @param aResourceBundle
	 *            The resourceBundle to set
	 */
	protected static void setResourceBundle(final ResourceBundle aResourceBundle)
	{

		AbstractCreateDQLAction.resourceBundle = aResourceBundle;
	}

	/**
	 * Check if the two statements are the same.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 9, 2005 10:19:36 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param createDQL
	 *            statement to compare this instance to.
	 * @return true if the underlying DQL statement is the same.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(final AbstractCreateDQLAction createDQL)
	{

		return getCreateDQL().equals(createDQL.getCreateDQL());
	}

	/**
	 * Create the DQL statement.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 7:29:08 PM</DD>
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

		try
		{
			IEditorPart editor = null;
			if(getView() != null)
			{
				if(getView().getSite() == null)
				{
					init(ConnectionView.getInstance());
				}
				editor = getView().getSite().getWorkbenchWindow()
					.getActivePage().getActiveEditor();
			}
			DQLEditor dqlEditor = null;
			if((editor != null) && (editor instanceof DQLEditor))
			{
				dqlEditor = (DQLEditor) editor;
			}
			if(dqlEditor == null)
			{
				MessageView.getInstance().addMessage("No active DQL editor"); //$NON-NLS-1$
				return;
			}
			dqlEditor.setText(dqlEditor.getText() + IDCTMPlugin.NEWLINE
				+ getCreateDQL());
		} catch(final Exception ex)
		{
			System.err.println(ex.getMessage());
			ex.printStackTrace(System.err);
		}
	}

	/**
	 * Return the DQL statement to be created.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 9, 2005 10:27:39 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return the DQL statement.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{

		return getCreateDQL();
	}

	/**
	 * Create the DQL statements.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 24, 2005 8:57:59 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @return the statements.
	 */
	protected abstract String getCreateDQL();
}
