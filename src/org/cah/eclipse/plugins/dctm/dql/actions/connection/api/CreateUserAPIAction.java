/*-
 * $Log: CreateUserAPIAction.java,v $
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
 * Revision 1.7  2005/02/09 14:01:15  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.6  2005/01/24 12:34:53  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.5  2005/01/18 07:24:47  harpechr
 * Version 1.0.3 features added. Mainly relation related modifications.
 *
 * Revision 1.4  2005/01/11 14:01:59  harpechr
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


/**
 * <H4>Create user action.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Jan 2, 2005 10:17:55 PM.</DD>
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
public class CreateUserAPIAction
								extends
									AbstractCreateAPIAction
{

	/**
	 * Create user API statements key.
	 * 
	 * @since 1.0
	 */
	public static final String		MSG_CREATE_USER			= "MSG_CREATE_USER";			//$NON-NLS-1$

	/**
	 * Create user comment key.
	 * 
	 * @since 1.0
	 */
	public static final String		MSG_CREATE_USER_COMMENT	= "MSG_CREATE_USER_COMMENT";	//$NON-NLS-1$

	/**
	 * The resource bundle name.
	 * 
	 * @since 1.0.3
	 */
	public static final String		RESOURCE_NAME			= CreateUserAPIAction.class
																.getName()
																+ "Resources";				//$NON-NLS-1$

	/**
	 * Create statements bundle.
	 * 
	 * @since 1.0
	 */
	private static ResourceBundle	resourceBundle			= null;

	/**
	 * Initialize the resourcebundle.
	 * 
	 * @since 1.0
	 */
	static
	{
		setResourceBundle(ResourceBundle
			.getBundle(CreateUserAPIAction.RESOURCE_NAME));
	}

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 10:17:55 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.2
	 */
	public CreateUserAPIAction()
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
	 * @since 1.0
	 * @return Returns the resourceBundle.
	 */
	public static ResourceBundle getResourceBundle()
	{

		return CreateUserAPIAction.resourceBundle;
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
	 * @since 1.0
	 * @param aResourceBundle
	 *            The resourceBundle to set.
	 */
	protected static void setResourceBundle(final ResourceBundle aResourceBundle)
	{

		CreateUserAPIAction.resourceBundle = aResourceBundle;
	}

	/**
	 * Create the create user API statements.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 10:22:11 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.2
	 * @return the create user api statements.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.connection.api.AbstractCreateAPIAction#getCreateAPI()
	 */
	@Override
	protected String getCreateAPI()
	{

		final StringBuffer createUserAPI = new StringBuffer(CreateUserAPIAction
			.getResourceBundle().getString(
				CreateUserAPIAction.MSG_CREATE_USER_COMMENT))
			.append(CreateUserAPIAction.getResourceBundle().getString(
				CreateUserAPIAction.MSG_CREATE_USER));
		return createUserAPI.toString();
	}

	// protected String getCreateAPI()
	// {
	//
	// final StringBuffer createUserAPI = new
	// StringBuffer("/*").append(IDCTMPlugin.NEWLINE).append(
	// " * Create the user and set it's attributes
	// like:").append(IDCTMPlugin.NEWLINE).append(" * create,c,")
	// .append(IUser.TYPE_DM_USER).append(IDCTMPlugin.NEWLINE).append(" *
	// set,c,l,").append(IUser.USER_NAME)
	// .append(IDCTMPlugin.NEWLINE).append(" * Plugin
	// User").append(IDCTMPlugin.NEWLINE).append(" * set,c,l,")
	// .append(IUser.USER_OS_NAME).append(IDCTMPlugin.NEWLINE).append(" *
	// plugiuse").append(IDCTMPlugin.NEWLINE)
	// .append(" *
	// set,c,l,").append(IUser.USER_ADDRESS).append(IDCTMPlugin.NEWLINE).append(
	// " * plug-in.user@dctm.fi").append(IDCTMPlugin.NEWLINE).append(" *
	// set,c,l,").append(
	// IUser.USER_GROUP_NAME).append(IDCTMPlugin.NEWLINE).append(" *
	// docu").append(IDCTMPlugin.NEWLINE).append(
	// " *
	// set,c,l,").append(IUser.USER_PRIVILEGES).append(IDCTMPlugin.NEWLINE).append("
	// * 16").append(
	// IDCTMPlugin.NEWLINE).append(" *
	// set,c,l,").append(IUser.DEFAULT_FOLDER).append(IDCTMPlugin.NEWLINE)
	// .append(" * /Temp").append(IDCTMPlugin.NEWLINE).append(" *
	// set,c,l,").append(IUser.DESCRIPTION).append(
	// IDCTMPlugin.NEWLINE).append(" * Yet another
	// superuser.").append(IDCTMPlugin.NEWLINE)
	// .append(" *
	// set,c,l,").append(IUser.ACL_DOMAIN).append(IDCTMPlugin.NEWLINE).append("
	// * dm_dbo").append(
	// IDCTMPlugin.NEWLINE).append(" *
	// set,c,l,").append(IUser.ACL_NAME).append(IDCTMPlugin.NEWLINE).append(
	// " * dm_acl_superusers").append(IDCTMPlugin.NEWLINE).append(" *
	// set,c,l,").append(IUser.USER_OS_DOMAIN)
	// .append(IDCTMPlugin.NEWLINE).append(" *
	// api").append(IDCTMPlugin.NEWLINE).append(" * set,c,l,").append(
	// IUser.CLIENT_CAPABILITY).append(IDCTMPlugin.NEWLINE).append(" *
	// 8").append(IDCTMPlugin.NEWLINE).append(
	// " * save,c,l").append(IDCTMPlugin.NEWLINE).append("
	// *").append(IDCTMPlugin.NEWLINE).append(
	// " * Replace the '<>' values with real
	// ones.").append(IDCTMPlugin.NEWLINE).append(" */").append(
	// IDCTMPlugin.NEWLINE).append("create,c,").append(IUser.TYPE_DM_USER).append(IDCTMPlugin.NEWLINE).append(
	// "set,c,l,").append(IUser.USER_NAME).append(IDCTMPlugin.NEWLINE).append("<Your
	// user name>").append(
	// IDCTMPlugin.NEWLINE).append("set,c,l,").append(IUser.USER_OS_NAME).append(IDCTMPlugin.NEWLINE).append(
	// "<Your user os
	// name>").append(IDCTMPlugin.NEWLINE).append("set,c,l,").append(IUser.USER_ADDRESS).append(
	// IDCTMPlugin.NEWLINE).append("<Your user
	// address>").append(IDCTMPlugin.NEWLINE).append("set,c,l,").append(
	// IUser.USER_GROUP_NAME).append(IDCTMPlugin.NEWLINE).append("<Your user
	// default group>").append(
	// IDCTMPlugin.NEWLINE).append("set,c,l,").append(IUser.USER_PRIVILEGES).append(IDCTMPlugin.NEWLINE).append(
	// "<Your user
	// privileges>").append(IDCTMPlugin.NEWLINE).append("set,c,l,").append(IUser.DEFAULT_FOLDER)
	// .append(IDCTMPlugin.NEWLINE).append("<Your user default
	// folder>").append(IDCTMPlugin.NEWLINE).append(
	// "set,c,l,").append(IUser.DESCRIPTION).append(IDCTMPlugin.NEWLINE).append("<Your
	// user description>")
	// .append(IDCTMPlugin.NEWLINE).append("set,c,l,").append(IUser.ACL_DOMAIN).append(IDCTMPlugin.NEWLINE).append(
	// "<Your ACL
	// owner>").append(IDCTMPlugin.NEWLINE).append("set,c,l,").append(IUser.ACL_NAME).append(
	// IDCTMPlugin.NEWLINE).append("<Your ACL
	// name>").append(IDCTMPlugin.NEWLINE).append("set,c,l,").append(
	// IUser.USER_OS_DOMAIN).append(IDCTMPlugin.NEWLINE).append("<Your os
	// domain>").append(IDCTMPlugin.NEWLINE)
	// .append("set,c,l,").append(IUser.CLIENT_CAPABILITY).append(IDCTMPlugin.NEWLINE).append(
	// "<Your client
	// capability>").append(IDCTMPlugin.NEWLINE).append("save,c,l");
	// return createUserAPI.toString();
	// }
}
