/*-
 * $Log: APIEditorInput.java,v $
 * Revision 1.10  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.9  2005/12/04 20:27:46  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.8  2005/11/22 13:27:07  madcook
 * Eclipse 3.2 compiler check warnings modified.
 *
 * Revision 1.7  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.6  2005/03/25 09:21:17  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/09 14:01:40  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.4  2005/01/24 12:34:57  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.3  2005/01/11 14:02:18  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.2  2005/01/11 13:47:23  harpechr
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof).
 *
 * Revision 1.1  2005/01/07 12:37:50  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.editors.api;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;


/**
 * <H4>API editor input.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 26, 2004 10:13:58 AM.</DD>
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
public class APIEditorInput
							implements
								IStorageEditorInput

{

	/**
	 * The API editor name.
	 * 
	 * @since 1.0
	 */
	String		name	= "API buffer"; //$NON-NLS-1$

	/**
	 * A API storage.
	 * 
	 * @since 1.0
	 */
	IStorage	storage	= null;

	/**
	 * Constructor that creates a new storage.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:57:40 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public APIEditorInput()
	{

		super();
		setStorage(new APIStorage(this));
	}

	/**
	 * Constructor for ready storage
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:57:57 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aStorage
	 *            the srorage.
	 */
	public APIEditorInput(final IStorage aStorage)
	{

		super();
		setStorage(aStorage);
	}

	/**
	 * Returns whether the editor input exists.
	 * <p>
	 * This method is primarily used to determine if an editor input should
	 * appear in the "File Most Recently Used" menu. An editor input will appear
	 * in the list until the return value of <code>exists</code> becomes
	 * <code>false</code> or it drops off the bottom of the list.
	 * </p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:58:06 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return <code>true</code> if the editor input exists;
	 *         <code>false</code> otherwise. This instance returns
	 *         <code>true</code> always.
	 * @see org.eclipse.ui.IEditorInput#exists()
	 */
	public boolean exists()
	{

		return true;
	}

	/**
	 * Returns an object which is an instance of the given class associated with
	 * this object. Returns <code>null</code> if no such object can be found.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:59:27 PM</DD>
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
	 * Returns the image descriptor for this input.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:58:19 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the image descriptor for this input. Always null.
	 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
	 */
	public ImageDescriptor getImageDescriptor()
	{

		return null;
	}

	/**
	 * Returns the editor name.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:58:47 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the name.
	 * @see org.eclipse.ui.IEditorInput#getName()
	 */
	public String getName()
	{

		return this.name;
	}

	/**
	 * Returns an object that can be used to save the state of this editor
	 * input.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:59:04 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the persistable element, or <code>null</code> if this editor
	 *         input cannot be persisted. Null is returned from this
	 *         implementation.
	 * @see org.eclipse.ui.IEditorInput#getPersistable()
	 */
	public IPersistableElement getPersistable()
	{

		return null;
	}

	/**
	 * Returns the underlying IStorage object.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:59:39 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return an IStorage object.
	 * @exception CoreException
	 *                if this method fails
	 * @see org.eclipse.ui.IStorageEditorInput#getStorage()
	 */
	public IStorage getStorage() throws CoreException
	{

		return this.storage;
	}

	/**
	 * Returns the tool tip text for this editor input. This text is used to
	 * differentiate between two input with the same name. For instance,
	 * MyClass.java in folder X and MyClass.java in folder Y. The format of the
	 * text varies between input types.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:59:14 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the tool tip text
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 */
	public String getToolTipText()
	{

		return "Execute API"; //$NON-NLS-1$
	}

	/**
	 * Set the editor name
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:58:27 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aName
	 *            the name.
	 */
	public void setName(final String aName)
	{

		this.name = aName;
	}

	/**
	 * Set the storage to use.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:57:53 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aStorage
	 *            the storage.
	 */
	protected void setStorage(final IStorage aStorage)
	{

		this.storage = aStorage;
	}

}
