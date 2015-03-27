/*-
 * $Log: IJar.java,v $
 * Revision 1.1  2005/12/04 20:51:40  madcook
 * jar type defined.
 *
 */

package org.cah.dctm.bof.tbo.persistent.sysobject.document.jar;

import org.cah.dctm.bof.tbo.persistent.sysobject.document.IDocument;


/**
 * <H4>Interface for dm_jar attributes..</H4>
 * <DL>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Mad Cook</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>02-Dec-2005 17:36:25.</DD>
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
public interface IJar
						extends
							IDocument
{

	/**
	 * JAR_TYPE INTEGER
	 * 
	 * @since 3.0.0
	 */
	public static final String	JAR_TYPE			= "jar_type";		//$NON-NLS-1$

	/**
	 * MIN_VM_VERSION CHAR(32)
	 * 
	 * @since 3.0.0
	 */
	public static final String	MIN_VM_VERSION		= "min_vm_version"; //$NON-NLS-1$

	/**
	 * dmc_module
	 * 
	 * @since 3.0.0
	 */
	public static final String	TYPE_DMC_JAR		= "dmc_jar";		//$NON-NLS-1$

	/**
	 * Jar identifying id start.
	 * 
	 * @since 3.0.0
	 */
	public static final String	TYPE_DMC_JAR_PREFIX	= "09";			//$NON-NLS-1$

}
