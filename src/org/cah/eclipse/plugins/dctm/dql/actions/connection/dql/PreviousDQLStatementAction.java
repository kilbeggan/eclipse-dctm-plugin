/*-
 * $Log: PreviousDQLStatementAction.java,v $
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
 * Revision 1.1  2005/02/09 13:55:02  harpechr
 * Added previous DQL queries functionality and modified the message view so 
 * that its real time.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.connection.dql;

/**
 * <H4>Action to store previously executed DQL statements.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Feb 9, 2005 10:00:25 AM.</DD>
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
 * @author Christopher Harper account : harpechr
 * @version 3.0.0
 * @since 1.0.5
 */
public class PreviousDQLStatementAction
										extends
											AbstractCreateDQLAction
{

	/**
	 * The previous DQL statement.
	 * 
	 * @since 1.0.5
	 */
	private String	DQLStatement	= null;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 9, 2005 10:00:25 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @param aDQLStatement
	 *            the previous DQL statement.
	 * @since 1.0.5
	 */
	public PreviousDQLStatementAction(final String aDQLStatement)
	{

		super();
		setDQLStatement(aDQLStatement);
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
	 * @param previousStatement
	 *            statement to compare this instance to.
	 * @return true if the underlying DQL statement is the same.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(final PreviousDQLStatementAction previousStatement)
	{

		return getDQLStatement().equals(previousStatement.getDQLStatement());
	}

	/**
	 * Return the previous DQL statement.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 9, 2005 10:00:25 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return the dql statement + ;.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.AbstractCreateDQLAction#getCreateDQL()
	 */
	@Override
	protected String getCreateDQL()
	{

		return getDQLStatement() + " ;"; //$NON-NLS-1$
	}

	/**
	 * Get the previous DQL statement.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 9, 2005 10:01:55 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return Returns the DQLStatement
	 */
	public String getDQLStatement()
	{

		return this.DQLStatement;
	}

	/**
	 * Set the previous DQL statement.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 9, 2005 10:01:55 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param aDQLStatement
	 *            The DQLStatement to set
	 */
	protected void setDQLStatement(final String aDQLStatement)
	{

		this.DQLStatement = aDQLStatement;
	}
}
