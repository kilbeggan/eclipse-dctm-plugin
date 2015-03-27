/*-
 * $Log: CheckInMinorAction.java,v $
 * Revision 1.4  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.3  2005/12/04 20:25:44  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.2  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.1  2005/03/25 09:11:49  harpechr
 * Check a document in with a minor version change.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.results;

import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;


/**
 * <H4>Checkin as minor version functionality.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>17.3.2005 18:04:15.</DD>
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
 * @author Christopher Harper account: HARPEC
 * @version 3.0.0
 * @since 1.0.5
 */

public class CheckInMinorAction
								extends
									CheckInAction
{

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 18:04:15</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 */
	public CheckInMinorAction()
	{

		super();
	}

	/**
	 * Checkin as minor version.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 17:58:07</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param session
	 *            a valid Docbase session.
	 * @throws DfException
	 *             if the checkin fails.
	 */
	@Override
	protected void checkInCommand(final IDfSession session) throws DfException
	{

		MessageView.getInstance().addMessage(
			ConnectionView.getInstance().getSelectedConnection().getTag()
				+ " checkin,c," + getRObjectId()); //$NON-NLS-1$
		MessageView.getInstance().addMessage(
			session.apiGet("checkin", getRObjectId())); //$NON-NLS-1$
	}
}
