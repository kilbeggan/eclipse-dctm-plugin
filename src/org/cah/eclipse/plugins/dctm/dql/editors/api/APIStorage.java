/*-
 * $Log: APIStorage.java,v $
 * Revision 1.10  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.9  2005/12/04 20:27:46  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.8  2005/11/22 13:27:07  madcook
 * Eclipse 3.2 compiler check warnings modified.
 * 
 * Revision 1.7 2005/11/21 13:04:32 madcook 
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 * 
 * Revision 1.6 2005/03/25 09:21:17 harpechr 
 * Version 2.0.0 code that has implemented the new java 1.5 features. 
 * 
 * Revision 1.5 2005/02/09 14:01:40 harpechr 
 * Version 1.0.5 work started. 
 * 
 * Revision 1.4 2005/01/24 12:34:57 harpechr 
 * Version 1.0.4 work started. 
 * 
 * Revision 1.3 2005/01/11 14:02:18 harpechr 
 * Changed version number from 1.0.2 to 1.0.3. 
 * 
 * Revision 1.2 2005/01/11 13:47:23 harpechr 
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof). 
 * 
 * Revision 1.1 2005/01/07 12:37:50 harpechr 
 * Version 1.0.2 code. First CVS commit!
 */

package org.cah.eclipse.plugins.dctm.dql.editors.api;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IStorageEditorInput;


/**
 * <H4>Set of bytes which can be accessed.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 28, 2004 12:46:21 PM.</DD>
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
public class APIStorage
						implements
							IStorage
{

	/**
	 * The editor input.
	 * 
	 * @since 1.0
	 */
	private IStorageEditorInput	editorInput	= null;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:51:13 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anEditorInput
	 *            editor input.
	 */
	public APIStorage(final APIEditorInput anEditorInput)
	{

		setEditorInput(anEditorInput);
	}

	/**
	 * Returns an object which is an instance of the given class associated with
	 * this object. Returns <code>null</code> if no such object can be found.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:55:27 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param adapter
	 *            the adapter class to look up
	 * @return a object castable to the given class, or <code>null</code> if
	 *         this object does not have an adapter for the given class. This
	 *         implementation returns <code>null</code>.
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(final Class adapter)
	{

		return null;
	}

	/**
	 * Returns an open input stream on the contents of this storage. The caller
	 * is responsible for closing the stream when finished.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:55:33 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return an input stream containing the contents of this storage. The
	 *         stream in this case will be empty.
	 * @exception CoreException
	 *                if the contents of this storage could not be accessed. See
	 *                any refinements for more information.
	 * @see org.eclipse.core.resources.IStorage#getContents()
	 */
	public InputStream getContents() throws CoreException
	{

		return new ByteArrayInputStream("".getBytes()); //$NON-NLS-1$
	}

	/**
	 * Returns the full path of this storage. The returned value depends on the
	 * implementor/extender. A storage need not have a path.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:55:40 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the path related to the data represented by this storage or
	 *         <code>null</code> if none. This implementation returns
	 *         <code>null</code>.
	 * @see org.eclipse.core.resources.IStorage#getFullPath()
	 */
	public IPath getFullPath()
	{

		return null;
	}

	/**
	 * Get the name of the editor input.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:56:02 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the name.
	 * @see org.eclipse.core.resources.IStorage#getName()
	 */
	public String getName()
	{

		return getEditorInput().getName();
	}

	/**
	 * Returns whether this storage is read-only.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:56:27 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return <code>true</code> if this storage is read-only. This
	 *         implementation returns false always.
	 * @see org.eclipse.core.resources.IStorage#isReadOnly()
	 */
	public boolean isReadOnly()
	{

		return false;
	}

	/**
	 * Get the editor input.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:54:41 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the parent editor input.
	 */
	protected IStorageEditorInput getEditorInput()
	{

		return this.editorInput;
	}

	/**
	 * Set the editor input.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:53:06 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anEditorInput
	 *            the input.
	 */
	private void setEditorInput(final IStorageEditorInput anEditorInput)
	{

		this.editorInput = anEditorInput;

	}
}
