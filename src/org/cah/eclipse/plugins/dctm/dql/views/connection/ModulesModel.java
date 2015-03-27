/*-
 * $Log: ModulesModel.java,v $
 * Revision 1.1  2005/12/04 21:49:34  madcook
 * A model for modules.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.views.connection;

import com.documentum.fc.common.IDfId;


/**
 * <H4>Container for all the BOF Modules.</H4>
 * <DL>
 * <DT><B>Description :</B>
 * <DD> BOF modules container. </DD>
 * </DT>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Mad Cook</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>24-Nov-2005 16:38:01.</DD>
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
public class ModulesModel
							extends
								AbstractSysObjectModel
{

	/**
	 * Aspect modules folder name <code>ASPECT_MODULE = "Aspect"</code>.
	 * 24-Nov-2005
	 * 
	 * @since 3.0.0
	 */
	public static final String	ASPECT_MODULE		= "Aspect";			//$NON-NLS-1$

	/**
	 * Location of modules in the Docbase
	 * <code>MODULES_LOCATION = "/System/Modules"</code>. 24-Nov-2005
	 * 
	 * @since 3.0.0
	 */
	public static final String	MODULES_LOCATION	= "/System/Modules";	//$NON-NLS-1$

	/**
	 * SBO modules folder name <code>SBO_MODULE = "SBO"</code>. 24-Nov-2005
	 * 
	 * @since 3.0.0
	 */
	public static final Object	SBO_MODULE			= "SBO";				//$NON-NLS-1$

	/**
	 * TBO modules folder name <code>TBO_MODULE = "TBO"</code>. 24-Nov-2005
	 * 
	 * @since 3.0.0
	 */
	public static Object		TBO_MODULE			= "TBO";				//$NON-NLS-1$

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 24-Nov-2005 16:39:06</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aName
	 *            the container name.
	 * @param aModuleId
	 *            the modules folders id
	 */
	public ModulesModel(final String aName, final IDfId aModuleId)
	{

		super(aName, aModuleId);
	}

}
