/*-
 * $Log: ChangeDefaultStorageStatementAction.java,v $
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
 * Revision 1.1  2005/01/24 22:23:57  harpechr
 * Create change default storage DQL statement action defined.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.connection.dql;

import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.TypeModel;

import com.documentum.fc.common.DfLogger;


/**
 * <H4>Action for creating the change default srorage statement..</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Jan 24, 2005 8:54:43 PM.</DD>
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
public class ChangeDefaultStorageStatementAction
												extends
													AbstractCreateDQLAction
{

	/**
	 * Key for change default storage statement.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_CHANGE_DEFAULT_STORAGE			= "MSG_CHANGE_DEFAULT_STORAGE";		//$NON-NLS-1$

	/**
	 * Key for change default storage statement comment.
	 * 
	 * @since 1.0.4
	 */
	public static final String	MSG_CHANGE_DEFAULT_STORAGE_COMMENT	= "MSG_CHANGE_DEFAULT_STORAGE_COMMENT"; //$NON-NLS-1$

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 24, 2005 8:54:43 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 */
	public ChangeDefaultStorageStatementAction()
	{

		super();
	}

	/**
	 * Get the DQL statemtn to change the default storage.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 24, 2005 8:58:36 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.4
	 * @return the change storage dql.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.AbstractCreateDQLAction#getCreateDQL()
	 */
	@Override
	protected String getCreateDQL()
	{

		final StringBuffer changeDefaultStorage = new StringBuffer(
			AbstractCreateDQLAction
				.getResourceBundle()
				.getString(
					ChangeDefaultStorageStatementAction.MSG_CHANGE_DEFAULT_STORAGE_COMMENT));
		if(ConnectionView.getInstance().getSelectedModel() instanceof TypeModel)
		{
			changeDefaultStorage
				.append(DfLogger
					.getFullMessage(
						AbstractCreateDQLAction
							.getResourceBundle()
							.getString(
								ChangeDefaultStorageStatementAction.MSG_CHANGE_DEFAULT_STORAGE),
						new String[] {ConnectionView.getInstance()
							.getSelectedModel().getName()}));
		} else
		{
			changeDefaultStorage
				.append(DfLogger
					.getFullMessage(
						AbstractCreateDQLAction
							.getResourceBundle()
							.getString(
								ChangeDefaultStorageStatementAction.MSG_CHANGE_DEFAULT_STORAGE),
						new String[] {"<type_name>"})); //$NON-NLS-1$
		}
		return changeDefaultStorage.toString();
	}
}
