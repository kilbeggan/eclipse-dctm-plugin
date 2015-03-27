/*-
 * $Log: SelectGroupsAction.java,v $
 * Revision 1.5  2005/12/04 22:14:39  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.4  2005/12/04 20:26:59  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.3  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.2  2005/03/25 09:19:27  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.1  2005/02/28 07:37:14  harpechr
 * Refactored actions so that they are in view spesific packages.
 *
 * Revision 1.7  2005/02/09 13:55:02  harpechr
 * Added previous DQL queries functionality and modified the message view so 
 * that its real time.
 *
 * Revision 1.6  2005/01/24 12:34:53  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.5  2005/01/11 14:02:00  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.4  2005/01/11 13:47:25  harpechr
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof).
 *
 * Revision 1.3  2005/01/10 08:45:12  harpechr
 * Changed the copyright statement from my work standard to my name and the 
 * licence to GNU.
 *
 * Revision 1.2  2005/01/09 10:46:54  harpechr
 * Renamed classes that were abstract by prefixing the class name with 
 * 'Abstract'.
 *
 * Revision 1.1  2005/01/07 12:37:48  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.connection;

import org.cah.dctm.bof.tbo.persistent.group.IGroup;
import org.cah.dctm.bof.tbo.persistent.user.IUser;
import org.cah.eclipse.plugins.dctm.dql.actions.AbstractBaseAction;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.ResultView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionContentProvider;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.GroupModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.GroupsModel;
import org.cah.eclipse.plugins.dctm.dql.views.connection.Model;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;


/**
 * <H4>Select groups by super grup of all of them.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Jan 2, 2005 5:08:40 PM.</DD>
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
public class SelectGroupsAction
								extends
									AbstractBaseAction
{

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 5:08:40 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.2
	 */
	public SelectGroupsAction()
	{

		super();
	}

	/**
	 * Run the folder content query.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 2:10:58 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.2
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run()
	{

		final Model selectedModel = ConnectionView.getInstance()
			.getSelectedModel();
		if((selectedModel instanceof GroupsModel)
			|| (selectedModel instanceof GroupModel))
		{
			final ConnectionModel connectionModel = ConnectionView
				.getInstance().getSelectedConnection();
			IDfSession connection = null;
			try
			{
				connection = connectionModel.getSession();
				StringBuffer groupDQL = new StringBuffer(
					"select distinct * from ").append(IGroup.TYPE_DM_GROUP); //$NON-NLS-1$
				if(selectedModel instanceof GroupModel)
				{
					groupDQL
						.append(" where any ").append(IGroup.GROUPS_NAMES).append(" = '") //$NON-NLS-1$ //$NON-NLS-2$
						.append(
							ConnectionContentProvider
								.escapeSingleQuotes(selectedModel.getName()))
						.append('\'');
				}
				groupDQL.append(" order by 2"); //$NON-NLS-1$
				final IDfQuery query = new DfQuery();
				query.setDQL(groupDQL.toString());
				MessageView.getInstance().addMessage(
					connection.getLoginInfo().getDomain() + '\\'
						+ connection.getLoginUserName() + '@'
						+ connection.getDocbaseName() + ' ' + query.getDQL());
				ResultView.getInstance().addResultSet(
					query.execute(connection, IDfQuery.DF_EXECREAD_QUERY),
					"Groups", //$NON-NLS-1$
					false, query.getDQL(), true);
				if(selectedModel instanceof GroupModel)
				{
					groupDQL = new StringBuffer("select distinct * from ").append(IUser.TYPE_DM_USER).append(" where ") //$NON-NLS-1$ //$NON-NLS-2$
						.append(IUser.R_IS_GROUP)
						.append(" = 0 and ").append(IUser.USER_NAME).append(" in ( select ") //$NON-NLS-1$ //$NON-NLS-2$
						.append(IGroup.USERS_NAMES)
						.append(" from ").append(IGroup.TYPE_DM_GROUP).append(" where ") //$NON-NLS-1$ //$NON-NLS-2$
						.append(IGroup.GROUP_NAME).append(" = '") //$NON-NLS-1$
						.append(
							ConnectionContentProvider
								.escapeSingleQuotes(selectedModel.getName()))
						.append("' ) order by 2"); //$NON-NLS-1$
					query.setDQL(groupDQL.toString());
					MessageView.getInstance().addMessage(
						connection.getLoginInfo().getDomain() + '\\'
							+ connection.getLoginUserName() + '@'
							+ connection.getDocbaseName() + ' '
							+ query.getDQL());
					ResultView.getInstance().addResultSet(
						query.execute(connection, IDfQuery.DF_EXECREAD_QUERY),
						"Users", true, query.getDQL(), true); //$NON-NLS-1$
					groupDQL = new StringBuffer("select distinct * from ").append(IUser.TYPE_DM_USER).append(" where ") //$NON-NLS-1$ //$NON-NLS-2$
						.append(IUser.R_IS_GROUP)
						.append(" = 0 and ").append(IUser.USER_NAME).append(" in ( select ") //$NON-NLS-1$ //$NON-NLS-2$
						.append(IGroup.I_ALL_USERS_NAMES)
						.append(" from ").append(IGroup.TYPE_DM_GROUP) //$NON-NLS-1$
						.append(" where ").append(IGroup.GROUP_NAME).append(" = '") //$NON-NLS-1$ //$NON-NLS-2$
						.append(
							ConnectionContentProvider
								.escapeSingleQuotes(selectedModel.getName()))
						.append("' ) order by 2"); //$NON-NLS-1$
					query.setDQL(groupDQL.toString());
					MessageView.getInstance().addMessage(
						connection.getLoginInfo().getDomain() + '\\'
							+ connection.getLoginUserName() + '@'
							+ connection.getDocbaseName() + ' '
							+ query.getDQL());
					ResultView.getInstance().addResultSet(
						query.execute(connection, IDfQuery.DF_EXECREAD_QUERY),
						"All users", true, query.getDQL(), true); //$NON-NLS-1$

				}
			} catch(DfException dex)
			{
				MessageView.getInstance().addMessage(dex);
			} finally
			{
				connectionModel.releaceConnection(connection);
			}
		}
	}
}
