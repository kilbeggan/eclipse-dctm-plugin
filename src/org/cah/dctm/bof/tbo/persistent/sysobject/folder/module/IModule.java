/*-
 * $Log: IModule.java,v $
 * Revision 1.1  2005/12/04 20:54:03  madcook
 * module type defined.
 *
 */

package org.cah.dctm.bof.tbo.persistent.sysobject.folder.module;

import org.cah.dctm.bof.tbo.persistent.sysobject.folder.IFolder;


/**
 * <H4>Interface for module type attributes..</H4>
 * <DL>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Mad Cook</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>02-Dec-2005 14:32:24.</DD>
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
public interface IModule
						extends
							IFolder
{

	/**
	 * a_bof_version CHAR(32)
	 * 
	 * @since 3.0.0
	 */
	public static final String	A_BOF_VERSION				= "a_bof_version";				//$NON-NLS-1$

	/**
	 * a_interfaces CHAR(255) REPEATING
	 * 
	 * @since 3.0.0
	 */
	public static final String	A_INTERFACES				= "a_interfaces";				//$NON-NLS-1$

	/**
	 * a_module_type CHAR(64)
	 * 
	 * @since 3.0.0
	 */
	public static final String	A_MODULE_TYPE				= "a_module_type";				//$NON-NLS-1$

	/**
	 * a_req_module_interfaces CHAR(255) REPEATING
	 * 
	 * @since 3.0.0
	 */
	public static final String	A_REQ_MODULE_INTERFACES		= "a_req_module_interfaces";	//$NON-NLS-1$

	/**
	 * contact_info CHAR(255)
	 * 
	 * @since 3.0.0
	 */
	public static final String	CONTACT_INFO				= "contact_info";				//$NON-NLS-1$

	/**
	 * implementation_technology CHAR(32)
	 * 
	 * @since 3.0.0
	 */
	public static final String	IMPLEMENTATION_TECHNOLOGY	= "implementation_technology";	//$NON-NLS-1$

	/**
	 * min_dfc_version CHAR(32)
	 * 
	 * @since 3.0.0
	 */
	public static final String	MIN_DFC_VERSION				= "min_dfc_version";			//$NON-NLS-1$

	/**
	 * module_description CHAR(255)
	 * 
	 * @since 3.0.0
	 */
	public static final String	MODULE_DESCRIPTION			= "module_description";		//$NON-NLS-1$

	/**
	 * primary_class CHAR(255)
	 * 
	 * @since 3.0.0
	 */
	public static final String	PRIMARY_CLASS				= "primary_class";				//$NON-NLS-1$

	/**
	 * req_module_names CHAR(255) REPEATING
	 * 
	 * @since 3.0.0
	 */
	public static final String	REQ_MODULE_NAMES			= "req_module_names";			//$NON-NLS-1$

	/**
	 * dmc_module
	 * 
	 * @since 3.0.0
	 */
	public static final String	TYPE_DMC_MODULE				= "dmc_module";				//$NON-NLS-1$

	/**
	 * Module identifying id start.
	 * 
	 * @since 3.0.0
	 */
	public static final String	TYPE_DMC_MODULE_PREFIX		= "0b";						//$NON-NLS-1$
}
