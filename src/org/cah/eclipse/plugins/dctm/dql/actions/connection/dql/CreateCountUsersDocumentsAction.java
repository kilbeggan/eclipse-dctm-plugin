/*-
 * $Log: CreateCountUsersDocumentsAction.java,v $
 * Revision 1.5  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.4  2005/12/04 20:26:26  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.3  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.2  2005/03/25 09:19:59  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.1  2005/02/28 07:38:25  harpechr
 * Refactored actions so that they are in view and type spesific packages.
 *
 * Revision 1.2  2005/02/09 14:01:15  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.1  2005/02/02 08:28:07  harpechr
 * Create count users documents DQL action defined.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.connection.dql;

/**
 * <H4>Create count users documents query.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Jan 25, 2005 10:24:59 PM.</DD>
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
public class CreateCountUsersDocumentsAction
											extends
												AbstractCreateDQLAction
{

	/**
	 * Key to return user document count DQL.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_COUNT_USERS_DOCUMENTS			= "MSG_COUNT_USERS_DOCUMENTS";			//$NON-NLS-1$

	/**
	 * Key to return user document count DQL comment.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_COUNT_USERS_DOCUMENTS_COMMENT	= "MSG_COUNT_USERS_DOCUMENTS_COMMENT";	//$NON-NLS-1$

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 25, 2005 2:56:13 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 */
	public CreateCountUsersDocumentsAction()
	{

		super();
	}

	/**
	 * Get the statistics query.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 25, 2005 2:56:13 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @return the query.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.AbstractCreateDQLAction#getCreateDQL()
	 */
	@Override
	protected String getCreateDQL()
	{

		final StringBuffer createCountUsersDocumentsQuery = new StringBuffer(
			AbstractCreateDQLAction
				.getResourceBundle()
				.getString(
					CreateCountUsersDocumentsAction.MSG_COUNT_USERS_DOCUMENTS_COMMENT))
			.append(AbstractCreateDQLAction.getResourceBundle().getString(
				CreateCountUsersDocumentsAction.MSG_COUNT_USERS_DOCUMENTS));
		return createCountUsersDocumentsQuery.toString();
	}
}
