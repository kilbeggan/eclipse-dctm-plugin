/*-
 * $Log: ReturnAllRowsAction.java,v $
 * Revision 1.5  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.4  2005/12/04 20:25:44  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.3  2005/11/22 13:27:07  madcook
 * Eclipse 3.2 compiler check warnings modified.
 * 
 * Revision 1.2 2005/11/21 13:04:32 madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5
 * settings. 
 * 
 * Revision 1.1 2005/03/25 09:14:20 harpechr 
 * 
 * Return all rows from a previously run query that stoped at the max rows 
 * amount. NOTE: this will cause a out of memory exception on large queries.
 */

package org.cah.eclipse.plugins.dctm.dql.actions.results;

import java.util.Vector;

import org.cah.eclipse.plugins.dctm.dql.actions.AbstractBaseAction;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.ResultView;
import org.eclipse.swt.widgets.Table;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;


/**
 * <H4>Re-run the query but this time get every single row.</H4>
 * <DL>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>26.3.2005 11:51:58.</DD>
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
public class ReturnAllRowsAction
								extends
									AbstractBaseAction
{

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 11:51:37</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 */
	public ReturnAllRowsAction()
	{

		super();
	}

	/**
	 * Re-run the query.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 11:52:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void run()
	{

		final Table resultsTable = ResultView.getInstance().getSelectedTable();
		final Vector<String> columns = (Vector<String>) resultsTable.getData();
		final IDfQuery query = new DfQuery();
		query.setDQL(columns.get(columns.size() - 1));
		IDfSession session = null;
		try
		{
			session = ConnectionView.getInstance().getSelectedConnection()
				.getSession();
			resultsTable.clearAll();
			MessageView.getInstance().addMessage(
				ConnectionView.getInstance().getSelectedConnection().getTag()
					+ ' ' + query.getDQL());
			ResultView.getInstance().bindResultSet(
				query.execute(session, IDfQuery.EXECREAD_QUERY),
				ResultView.getInstance().getSelectedTable(), query.getDQL(),
				false);
		} catch(DfException dex)
		{
			MessageView.getInstance().addMessage(
				ConnectionView.getInstance().getSelectedConnection().getTag()
					+ ' ' + dex.getMessage());
		} finally
		{
			ConnectionView.getInstance().getSelectedConnection()
				.releaceConnection(session);
		}
	}
}
