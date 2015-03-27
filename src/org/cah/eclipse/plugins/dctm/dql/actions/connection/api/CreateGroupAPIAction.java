/*-
 * $Log: CreateGroupAPIAction.java,v $
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
 * Revision 1.3  2005/04/01 11:27:12  harpechr
 * Reference to a static string changed to an interface.
 *
 * Revision 1.2  2005/03/25 09:19:59  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.1  2005/02/28 07:38:25  harpechr
 * Refactored actions so that they are in view and type spesific packages.
 *
 * Revision 1.7  2005/02/09 14:01:29  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.6  2005/01/24 22:19:49  harpechr
 * Formating.
 *
 * Revision 1.5  2005/01/24 12:34:53  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.4  2005/01/11 14:02:00  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.3  2005/01/11 13:44:38  harpechr
 * Moved the create statements to resource bundles.
 *
 * Revision 1.2  2005/01/09 10:46:54  harpechr
 * Renamed classes that were abstract by prefixing the class name with 
 * 'Abstract'.
 *
 * Revision 1.1  2005/01/07 12:37:48  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.connection.api;

import java.util.ResourceBundle;

import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.GroupModel;

import com.documentum.fc.common.DfLogger;


/**
 * <H4>Create create group statements.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Jan 2, 2005 11:00:59 PM.</DD>
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
 * @since 1.0.2
 */
public class CreateGroupAPIAction
									extends
										AbstractCreateAPIAction
{

	/**
	 * Create group API statements key.
	 * 
	 * @since 1.0.3
	 */
	public static final String		MSG_CREATE_GROUP			= "MSG_CREATE_GROUP";			//$NON-NLS-1$

	/**
	 * Create group comment key.
	 * 
	 * @since 1.0.3
	 */
	public static final String		MSG_CREATE_GROUP_COMMENT	= "MSG_CREATE_GROUP_COMMENT";	//$NON-NLS-1$

	/**
	 * The resource bundle name.
	 * 
	 * @since 1.0.3
	 */
	public static final String		RESOURCE_NAME				= CreateGroupAPIAction.class
																	.getName()
																	+ "Resources";				//$NON-NLS-1$

	/**
	 * Create group statements bundle.
	 * 
	 * @since 1.0.3
	 */
	private static ResourceBundle	resourceBundle				= null;

	/**
	 * Initialize the resourcebundle.
	 * 
	 * @since 1.0.3
	 */
	static
	{
		setResourceBundle(ResourceBundle
			.getBundle(CreateGroupAPIAction.RESOURCE_NAME));
	}

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 11:00:59 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.1
	 */
	public CreateGroupAPIAction()
	{

		super();
	}

	/**
	 * Get the resourcebundle.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 11, 2005 1:20:08 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.3
	 * @return Returns the resourceBundle.
	 */
	public static ResourceBundle getResourceBundle()
	{

		return CreateGroupAPIAction.resourceBundle;
	}

	/**
	 * Set the resourcebundle.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 11, 2005 1:20:08 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.3
	 * @param aResourceBundle
	 *            The resourceBundle to set.
	 */
	protected static void setResourceBundle(final ResourceBundle aResourceBundle)
	{

		CreateGroupAPIAction.resourceBundle = aResourceBundle;
	}

	/**
	 * Get the create group statements.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 11:00:59 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.1
	 * @return the create statements.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.connection.api.AbstractCreateAPIAction#getCreateAPI()
	 */
	@Override
	protected String getCreateAPI()
	{

		final StringBuffer createGroup = new StringBuffer(CreateGroupAPIAction
			.getResourceBundle().getString(
				CreateGroupAPIAction.MSG_CREATE_GROUP_COMMENT));
		if(ConnectionView.getInstance().getSelectedModel() instanceof GroupModel)
		{
			createGroup.append(DfLogger.getFullMessage(CreateGroupAPIAction
				.getResourceBundle().getString(
					CreateGroupAPIAction.MSG_CREATE_GROUP),
				new String[] {ConnectionView.getInstance().getSelectedModel()
					.getName()}));
		} else
		{
			createGroup.append(DfLogger.getFullMessage(CreateGroupAPIAction
				.getResourceBundle().getString(
					CreateGroupAPIAction.MSG_CREATE_GROUP),
				new String[] {"<Add your groups>"})); //$NON-NLS-1$
		}
		return createGroup.toString();
	}
	// protected String getCreateAPI()
	// {
	//
	// final StringBuffer createGroup = new
	// StringBuffer("/*").append(IDCTMPlugin.NEWLINE).append(
	// " * Create the group and populate its attributes
	// like:").append(IDCTMPlugin.NEWLINE).append(" * create,c,")
	// .append(IGroup.TYPE_DM_GROUP).append(IDCTMPlugin.NEWLINE).append(" *
	// set,c,l,").append(IGroup.GROUP_NAME)
	// .append(IDCTMPlugin.NEWLINE).append(" * Plugin
	// group").append(IDCTMPlugin.NEWLINE).append(" * set,c,l,")
	// .append(IGroup.GROUP_ADDRESS).append(IDCTMPlugin.NEWLINE).append(" *
	// plugin.group@dctm.fi").append(
	// IDCTMPlugin.NEWLINE).append(" *
	// append,c,l,").append(IGroup.USERS_NAMES).append(IDCTMPlugin.NEWLINE)
	// .append(" * Plugin User").append(IDCTMPlugin.NEWLINE).append(" *
	// append,c,l,").append(IGroup.GROUPS_NAMES)
	// .append(IDCTMPlugin.NEWLINE).append(" *
	// docu").append(IDCTMPlugin.NEWLINE).append(" * set,c,l,").append(
	// IGroup.IS_PRIVATE).append(IDCTMPlugin.NEWLINE).append(" *
	// F").append(IDCTMPlugin.NEWLINE).append(
	// " * set,c,l,").append(IGroup.DESCRIPTION).append(IDCTMPlugin.NEWLINE)
	// .append(" * The plugin users group").append(IDCTMPlugin.NEWLINE).append("
	// * set,c,l,").append(
	// IGroup.GROUP_CLASS).append(IDCTMPlugin.NEWLINE).append(" *
	// ").append(IGroup.GROUP_CLASS_GROUP).append(
	// IDCTMPlugin.NEWLINE).append(" *
	// save,c,l").append(IDCTMPlugin.NEWLINE).append(" *").append(
	// IDCTMPlugin.NEWLINE).append(" * Replace '<>' values with real
	// ones.").append(IDCTMPlugin.NEWLINE).append(
	// "
	// */").append(IDCTMPlugin.NEWLINE).append("create,c,").append(IGroup.TYPE_DM_GROUP).append(
	// IDCTMPlugin.NEWLINE).append("set,c,l,").append(IGroup.GROUP_NAME).append(IDCTMPlugin.NEWLINE).append(
	// "<Your group
	// name>").append(IDCTMPlugin.NEWLINE).append("set,c,l,").append(IGroup.GROUP_ADDRESS).append(
	// IDCTMPlugin.NEWLINE).append("<Your group
	// address>").append(IDCTMPlugin.NEWLINE).append("append,c,l,")
	// .append(IGroup.USERS_NAMES).append(IDCTMPlugin.NEWLINE).append("<Add your
	// users>")
	// .append(IDCTMPlugin.NEWLINE).append("append,c,l,").append(IGroup.GROUPS_NAMES).append(IDCTMPlugin.NEWLINE);
	// if (ConnectionView.getInstance().getSelectedModel() instanceof
	// GroupModel)
	// {
	// createGroup.append(ConnectionView.getInstance().getSelectedModel().getName());
	// } else
	// {
	// createGroup.append("<Add your groups>");
	// }
	// createGroup.append(IDCTMPlugin.NEWLINE).append("# Use T or F for is
	// private.").append(IDCTMPlugin.NEWLINE)
	// .append("set,c,l,").append(IGroup.IS_PRIVATE).append(IDCTMPlugin.NEWLINE).append("<Your
	// privacy>").append(
	// IDCTMPlugin.NEWLINE).append("set,c,l,").append(IGroup.DESCRIPTION).append(IDCTMPlugin.NEWLINE).append(
	// "<Your description>").append(IDCTMPlugin.NEWLINE).append("# Use group or
	// role for class.").append(
	// IDCTMPlugin.NEWLINE).append("set,c,l,").append(IGroup.GROUP_CLASS).append(IDCTMPlugin.NEWLINE).append(
	// "<Your class>").append(IDCTMPlugin.NEWLINE).append("save,c,l");
	// return createGroup.toString();
	// }
}
