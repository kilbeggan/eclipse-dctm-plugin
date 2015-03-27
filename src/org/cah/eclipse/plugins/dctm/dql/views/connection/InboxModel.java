/*-
 * $Log: InboxModel.java,v $
 * Revision 1.4  2005/12/04 22:14:40  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.3  2005/12/04 20:29:12  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.2  2005/03/25 09:21:38  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.1  2005/03/25 08:39:11  harpechr
 * Inbox model defined.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.views.connection;

/**
 * <H4>Model used to list inbox items.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>27.2.2005 16:27:28.</DD>
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
public class InboxModel
						extends
							Model
{

	/**
	 * Sole constructor
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 16:27:28</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param aName
	 *            name of the inbox node.
	 */
	public InboxModel(final String aName)
	{

		super(aName);
	}
}
