/*-
 * $Log: ICategory.java,v $
 * Revision 1.8  2005/12/04 22:17:22  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:24:54  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.6  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.5  2005/03/25 09:26:04  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 14:01:46  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:35:14  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/10 11:58:14  harpechr
 * Changed version numbers and since information to 1.0.3.
 *
 * Revision 1.1  2005/01/10 10:41:14  harpechr
 * TBO interfaces for most commonly used types (No implementations since were 
 * trying to do a light client.).
 *
 */

package org.cah.dctm.bof.tbo.persistent.sysobject.folder.category;

import org.cah.dctm.bof.tbo.persistent.sysobject.folder.IFolder;


/**
 * <H4>Category folder attribute interface.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2004 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 6, 2004 9:29:12 AM.</DD>
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
public interface ICategory
							extends
								IFolder
{

	/**
	 * active_doc_count INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	ACTIVE_DOC_COUNT	= "active_doc_count";		//$NON-NLS-1$

	/**
	 * allowed_operations CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	ALLOWED_OPERATIONS	= "allowed_operations";	//$NON-NLS-1$

	/**
	 * candidate_threshold INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	CANDIDATE_THRESHOLD	= "candidate_threshold";	//$NON-NLS-1$

	/**
	 * category_evidence CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	CATEGORY_EVIDENCE	= "category_evidence";		//$NON-NLS-1$

	/**
	 * category_owner CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	CATEGORY_OWNER		= "category_owner";		//$NON-NLS-1$

	/**
	 * child_count INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	CHILD_COUNT			= "child_count";			//$NON-NLS-1$

	/**
	 * class_id ID
	 * 
	 * @since 1.0.3
	 */
	public static final String	CLASS_ID			= "class_id";				//$NON-NLS-1$

	/**
	 * definition_type CHAR(32)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DEFINITION_TYPE		= "definition_type";		//$NON-NLS-1$

	/**
	 * description CHAR(255)
	 * 
	 * @since 1.0.3
	 */
	public static final String	DESCRIPTION			= "description";			//$NON-NLS-1$

	/**
	 * keyword_evidence CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	KEYWORD_EVIDENCE	= "keyword_evidence";		//$NON-NLS-1$

	/**
	 * on_target_threshold INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	ON_TARGET_THRESHOLD	= "on_target_threshold";	//$NON-NLS-1$

	/**
	 * qualifiers CHAR(255) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	QUALIFIERS			= "qualifiers";			//$NON-NLS-1$

	/**
	 * supported_language CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	SUPPORTED_LANGUAGE	= "supported_language";	//$NON-NLS-1$

	/**
	 * test_doc_count INTEGER
	 * 
	 * @since 1.0.3
	 */
	public static final String	TEST_DOC_COUNT		= "test_doc_count";		//$NON-NLS-1$

	/**
	 * translated_name CHAR(32) REPEATING
	 * 
	 * @since 1.0.3
	 */
	public static final String	TRANSLATED_NAME		= "translated_name";		//$NON-NLS-1$

	/**
	 * Type name 'dm_category'.
	 * 
	 * @since 1.0.3
	 */
	public static final String	TYPE_DM_CATEGORY	= "dm_category";			//$NON-NLS-1$
}
