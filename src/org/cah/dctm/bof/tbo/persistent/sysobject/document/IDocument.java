/*-
 * $Log: IDocument.java,v $
 * Revision 1.9  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.8  2005/12/04 20:24:54  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.7  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.6  2005/03/25 09:26:04  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/10 11:33:54  harpechr
 * Safety commit!
 *
 * Revision 1.4  2005/02/09 14:01:42  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:58  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:57:54  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:08  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.sysobject.document;

import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;

import com.documentum.fc.client.IDfDocument;


/**
 * <h4>Attributes and type of the dm_document type.</h4>
 * <DL>
 * <DT><B>Description: </B>
 * <DD>Hold static variables for attribute names and extend the interfaces that
 * enable BOF/TBO usage. NOTE This cant be registered for dm_document.</DD>
 * </DT>
 * <DT><B>Copyright: </B>
 * <DD>2004 (C) Christopher Harper</DD>
 * </DT>
 * <DT><B>Created: </B>
 * <DD>Apr 7, 2004 2:43:15 PM</DD>
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
 * @author Christopher Andrew Harper (CAH) account: dmadmin
 * @version 3.0.0
 * @since 1.0.3
 */
public abstract interface IDocument
									extends
										ISysObject,
										IDfDocument
{

	/**
	 * r_varsion_label given to new objects that haven't checked in.
	 * 
	 * @since 1.0.3
	 */
	public static final String	NEW_LABEL				= "_NEW_";			//$NON-NLS-1$

	/**
	 * dm_document
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_DOCUMENT		= "dm_document";	//$NON-NLS-1$

	/**
	 * r_object_id prefix for documents and it's subtypes '09'.
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_DOCUMENT_PREFIX	= "09";			//$NON-NLS-1$

}
