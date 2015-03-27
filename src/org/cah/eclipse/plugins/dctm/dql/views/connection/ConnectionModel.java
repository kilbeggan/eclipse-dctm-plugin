/*-
 * $Log: ConnectionModel.java,v $
 * Revision 1.9  2005/12/04 22:14:40  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.8  2005/12/04 20:29:12  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.7  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.6  2005/03/25 09:21:38  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/10 11:33:38  harpechr
 * Safety commit!
 *
 * Revision 1.4  2005/02/09 14:01:33  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:54  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:01  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:49  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.views.connection;

import java.util.Hashtable;
import java.util.Vector;

import org.cah.eclipse.plugins.dctm.dql.DCTMPlugin;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfLoginInfo;


/**
 * <H4>Model containing connection information.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 25, 2004 1:44:32 PM.</DD>
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
public class ConnectionModel
							extends
								Model
{

	/**
	 * Checked out documents.
	 * 
	 * @since 1.0.5
	 */

	public static final String									CHECKED_OUT_NODE				= "checked_out";								//$NON-NLS-1$

	/**
	 * XML node name 'docbase'.
	 * 
	 * @since 1.0
	 */
	public static final String									DOCBASE_NODE					= "docbase";									//$NON-NLS-1$

	/**
	 * XML node name 'domain'.
	 * 
	 * @since 1.0
	 */
	public static final String									DOMAIN_NODE						= "domain";									//$NON-NLS-1$

	/**
	 * XML node name 'password'.
	 * 
	 * @since 1.0
	 */
	public static final String									PASSWORD_NODE					= "password";									//$NON-NLS-1$

	/**
	 * Container element for previous statemtns.
	 * 
	 * @since 1.0.5
	 */

	public static final String									PREVIOUS_DQL_STATEMENTS_NODE	= "previous_statements";						//$NON-NLS-1$

	/**
	 * Previous statement node.
	 * 
	 * @since 1.0.5
	 */

	public static final String									STATEMENT_NODE					= "statement_";								//$NON-NLS-1$

	/**
	 * XML node name 'user_name'.
	 * 
	 * @since 1.0
	 */
	public static final String									USERNAME_NODE					= "user_name";									//$NON-NLS-1$

	/**
	 * It's too expensive to test if the session is null. So we will have a
	 * vector of connected users. Value is a combination of user os name and
	 * docbase name.
	 * 
	 * @since 1.0.5
	 */
	private static Vector<String>								connectedUsesrs					= new Vector<String>();

	/**
	 * Container for session managers.
	 * 
	 * @since 1.0.5
	 */

	private static final Hashtable<String, IDfSessionManager>	sessionManagers					= new Hashtable<String, IDfSessionManager>();

	/**
	 * Docbase name.
	 * 
	 * @since 1.0
	 */
	private String												docbase							= null;

	/**
	 * Domain name.
	 * 
	 * @since 1.0
	 */
	private String												domain							= null;

	/**
	 * User password.
	 * 
	 * @since 1.0
	 */
	private String												password						= null;

	/**
	 * Tag to use in messages in the message view.
	 * 
	 * @since 1.0.5
	 */
	private String												tag								= null;

	/**
	 * User name.
	 * 
	 * @since 1.0
	 */
	private String												userName						= null;

	/**
	 * Constructor with no model name.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:44:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public ConnectionModel()
	{

		super();
	}

	/**
	 * Constructor with model name.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:44:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aName
	 *            the name of this model
	 */
	public ConnectionModel(final String aName)
	{

		super(aName);
	}

	/**
	 * Get the connected users.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>18.3.2005 11:54:48</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return the connected users.
	 */
	protected static Vector<String> getConnectedUsesrs()
	{

		return ConnectionModel.connectedUsesrs;
	}

	/**
	 * Get the session managers.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>14.3.2005 16:57:20</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return Returns the sessionManagers
	 */

	protected static Hashtable<String, IDfSessionManager> getSessionManagers()
	{

		return ConnectionModel.sessionManagers;
	}

	/**
	 * Disconnect the Docbase connection by clearing identies.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 11:08:15 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public void disconnect()
	{

		if(getSessionManagers().containsKey(getUserName()))
		{
			(getSessionManagers().get(getUserName()))
				.clearIdentity(getDocbase());
		}
		setTag(""); //$NON-NLS-1$
		getConnectedUsesrs().removeElement(
			getUserName() + getDocbase() + getDomain() + getPassword());
	}

	/**
	 * Get the Docbase name.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:53:26 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the docbase name.
	 */
	public String getDocbase()
	{

		return this.docbase;
	}

	/**
	 * Get the possible domain.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:53:23 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the users domain.
	 */
	public String getDomain()
	{

		return this.domain;
	}

	/**
	 * Get the users password.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:53:20 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the users password.
	 */
	public String getPassword()
	{

		return this.password;
	}

	/**
	 * Get a Docbse session.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 2:53:28 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return a session.
	 * @throws DfException
	 *             if the session can't be returned.
	 */
	public IDfSession getSession() throws DfException
	{

		try
		{
			if(getSessionManagers().containsKey(getUserName()))
			{
				final IDfSessionManager sessionManager = getSessionManagers()
					.get(getUserName());
				if((sessionManager != null)
					&& (sessionManager.hasIdentity(getDocbase())))
				{
					final IDfSession session = sessionManager
						.getSession(getDocbase());
					session.getMessage(1);
					return session;
				}
			}
		} catch(DfException dex)
		{
			getConnectedUsesrs().removeElement(
				getUserName() + getDocbase() + getDomain() + getPassword());
			throw dex;
		}
		return null;
	}

	/**
	 * Get the message tag.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 14:16:22</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return Returns the tag
	 */

	public String getTag()
	{

		return this.tag;
	}

	/**
	 * Get the user name.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:53:16 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the users login name.
	 */
	public String getUserName()
	{

		return this.userName;
	}

	/**
	 * Check if this model is connected.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>18.3.2005 11:57:39</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return true if a connection is present.
	 */
	public boolean isConnected()
	{

		return getConnectedUsesrs().contains(
			getUserName() + getDocbase() + getDomain() + getPassword());
	}

	/**
	 * Initialize the connection by setting the identity into the manager.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 9:46:01 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @throws DfException
	 *             if the logininfo can't be set.
	 */
	public void prepareConnection() throws DfException
	{

		if(!getSessionManagers().containsKey(getUserName()))
		{
			getSessionManagers().put(getUserName(),
				DCTMPlugin.getClient().newSessionManager());
		}
		final IDfSessionManager sessionManager = getSessionManagers().get(
			getUserName());
		if(!sessionManager.hasIdentity(getDocbase()))
		{
			sessionManager.setIdentity(getDocbase(), getLoginInfo());
			IDfSession session = null;
			try
			{
				session = sessionManager.getSession(getDocbase());
				getConnectedUsesrs().addElement(
					getUserName() + getDocbase() + getDomain() + getPassword());
				if((getDomain() == null) || (getDomain().equals(""))) //$NON-NLS-1$
				{
					setTag("empty\\" + getUserName() + '@' + getDocbase()); //$NON-NLS-1$
				} else
				{
					setTag(getDomain() + '\\' + getUserName() + '@'
						+ getDocbase());
				}
			} finally
			{
				sessionManager.release(session);
			}
		}
	}

	/**
	 * Releace the connection back to the manager.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 2:54:51 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param session
	 *            the session returned from the manager.
	 */
	public void releaceConnection(final IDfSession session)
	{

		if((session != null)
			&& (getSessionManagers().containsKey(getUserName())))
		{
			getSessionManagers().get(getUserName()).release(session);
		}
	}

	/**
	 * Set the Docbase name.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:53:13 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDocbase
	 *            name of a Docbase.
	 */
	public void setDocbase(final String aDocbase)
	{

		this.docbase = aDocbase;
	}

	/**
	 * Set a users domain.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:53:10 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDomain
	 *            the domain name.
	 */
	public void setDomain(final String aDomain)
	{

		getLoginInfo().setDomain(aDomain);
		this.domain = aDomain;
	}

	/**
	 * Set the users password.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:53:04 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aPassword
	 *            the password.
	 */
	public void setPassword(final String aPassword)
	{

		getLoginInfo().setPassword(aPassword);
		this.password = aPassword;
	}

	/**
	 * Set the users name.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:53:02 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anUserName
	 *            the name.
	 */
	public void setUserName(final String anUserName)
	{

		getLoginInfo().setUser(anUserName);
		this.userName = anUserName;
	}

	/**
	 * Get the required information to log into a Docbase.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>14.3.2005 17:43:23</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return the newly created login info object.
	 */

	protected IDfLoginInfo getLoginInfo()
	{

		final IDfLoginInfo loginInfo = new DfLoginInfo();
		loginInfo.setDomain(getDomain());
		loginInfo.setPassword(getPassword());
		loginInfo.setUser(getUserName());
		return loginInfo;
	}

	/**
	 * Set the message tag.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 14:16:22</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param aTag
	 *            The tag to set
	 */

	protected void setTag(final String aTag)
	{

		this.tag = aTag;
	}
}
