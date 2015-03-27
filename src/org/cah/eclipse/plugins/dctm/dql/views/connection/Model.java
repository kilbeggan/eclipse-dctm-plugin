/*-
 * $Log: Model.java,v $
 * Revision 1.9  2005/12/04 22:14:40  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.8  2005/12/04 20:29:12  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.7  2005/11/22 13:27:07  madcook
 * Eclipse 3.2 compiler check warnings modified.
 * 
 * Revision 1.6 2005/11/21 13:04:31 madcook 
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings. 
 * 
 * Revision 1.5 2005/03/25 09:21:39 harpechr 
 * Version 2.0.0 code that has implemented the new java 1.5 features. 
 * 
 * Revision 1.4 2005/02/09 14:01:33 harpechr 
 * Version 1.0.5 work started. 
 * 
 * Revision 1.3 2005/01/24 12:34:54 harpechr 
 * Version 1.0.4 work started. 
 * 
 * Revision 1.2 2005/01/11 14:02:01 harpechr 
 * Changed version number from 1.0.2 to 1.0.3. 
 * 
 * Revision 1.1 2005/01/07 12:37:49 harpechr 
 * Version 1.0.2 code. First CVS commit!
 */

package org.cah.eclipse.plugins.dctm.dql.views.connection;

import java.util.ArrayList;

import org.eclipse.core.runtime.IAdaptable;


/**
 * <H4>The root model.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 25, 2004 12:48:20 PM.</DD>
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
public class Model
					implements
						IAdaptable
{

	/**
	 * List of model children.
	 * 
	 * @since 1.0
	 */
	protected ArrayList<Model>	children	= new ArrayList<Model>();

	/**
	 * Model name.
	 * 
	 * @since 1.0
	 */
	private String				name;

	/**
	 * The parent model.
	 * 
	 * @since 1.0
	 */
	private Model				parent;

	/**
	 * Constructor without a name.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:48:20 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public Model()
	{

		super();
	}

	/**
	 * Constructor where the model name is given.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:22:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aName
	 *            the model name.
	 */
	public Model(final String aName)
	{

		this();
		setName(aName);
	}

	/**
	 * Add a child model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 2:32:31 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aChild
	 *            the child model.
	 */
	public void addChild(Model aChild)
	{

		this.children.add(aChild);
		aChild.setParent(this);
	}

	/**
	 * Returns an object which is an instance of the given class associated with
	 * this object. Returns <code>null</code> if no such object can be found.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:48:20 PM</DD>
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
	 * Get the children of this model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:50:51 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the children.
	 */
	public Model[] getChildren()
	{

		return this.children.toArray(new Model[this.children.size()]);
	}

	/**
	 * Get the children of this model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 10:44:46 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the child models.
	 */
	public ArrayList<Model> getChildrenArrayList()
	{

		return this.children;
	}

	/**
	 * Get the name of this model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:23:58 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return name of the model
	 */
	public String getName()
	{

		return this.name;
	}

	/**
	 * Get the parent model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:56:41 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the paren model.
	 */
	public Model getParent()
	{

		return this.parent;
	}

	/**
	 * Has this model got children?
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:00:11 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return true if this node has children.
	 */
	public boolean hasChildren()
	{

		return this.children.size() > 0;
	}

	/**
	 * Remove a child model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 11:12:23 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param child
	 *            to be removed.
	 */
	public void removeChild(final Model child)
	{

		this.children.remove(child);
		child.setParent(null);
	}

	/**
	 * Remove all children.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 2:02:20 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public void removeChildren()
	{

		setChildren(new ArrayList<Model>());
	}

	/**
	 * Set the parent model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 2:35:54 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param parent
	 *            the parent.
	 */
	public void setParent(final Model parent)
	{

		this.parent = parent;
	}

	/**
	 * Get the name of this model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 10:50:48 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the model name.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{

		return getName();
	}

	/**
	 * Set the child models.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 2:03:16 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aChildren
	 *            the child models.
	 */
	protected void setChildren(final ArrayList<Model> aChildren)
	{

		this.children = aChildren;
	}

	/**
	 * Set the model name.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:24:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aName
	 *            model name.
	 */
	protected void setName(final String aName)
	{

		this.name = aName;
	}
}
