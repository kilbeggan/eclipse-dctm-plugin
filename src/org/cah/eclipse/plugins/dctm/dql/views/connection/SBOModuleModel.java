/*-
 * $Log: SBOModuleModel.java,v $
 * Revision 1.1  2005/12/04 20:29:12  madcook
 * Version 3.0.0 work started.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.views.connection;

import com.documentum.fc.common.IDfId;


/**
 * <H4>Model for SBO modules.</H4>
 * <DL>
 * <DT><B>Description :</B>
 * <DD> Model to hold all the SBO's in the docbase. </DD>
 * </DT>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Mad Cook</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>24-Nov-2005 17:38:14.</DD>
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
 * @author Mad Cook account: dmadmin
 * @version 3.0.0
 * @since 3.0.0
 */
public class SBOModuleModel
							extends
								OtherModuleModel
{

	/**
	 * Constructor with the module name and id.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 24-Nov-2005 17:37:01</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aName
	 *            the module name.
	 * @param aModuleId
	 *            the module id.
	 */
	public SBOModuleModel(final String aName, final IDfId aModuleId)
	{

		super(aName, aModuleId);
	}
}
