/*-
 * $Log: RelationTypeModel.java,v $
 * Revision 1.6  2005/12/04 22:14:40  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.5  2005/12/04 20:29:12  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.4  2005/03/25 09:21:39  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.3  2005/02/09 14:01:32  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.2  2005/01/24 12:34:54  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.1  2005/01/17 07:21:52  harpechr
 * Relation type plugin model
 *
 */

package org.cah.eclipse.plugins.dctm.dql.views.connection;

import com.documentum.fc.common.IDfId;


/**
 * <H4>Model used for relation types in the connection view structure.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Jan 17, 2005 9:11:41 AM.</DD>
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
 * @since 1.0.3
 */
public class RelationTypeModel
								extends
									AbstractPersistentObjectModel
{

	/**
	 * Relation type sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 17, 2005 9:11:41 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.3
	 * @param aName
	 *            relation name.
	 * @param anObjectId
	 *            the relation id.
	 */
	public RelationTypeModel(final String aName, final IDfId anObjectId)
	{

		super(aName, anObjectId);
	}

}
