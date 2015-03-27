/*-
 * $Log: AbstractBaseAction.java,v $
 * Revision 1.8  2005/12/04 22:17:22  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:25:44  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.6  2005/03/25 09:18:59  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/28 07:36:05  harpechr
 * Refactored actions so that they are in view spesific packages.
 *
 * Revision 1.4  2005/02/09 14:01:29  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:53  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:00  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/09 10:46:54  harpechr
 * Renamed classes that were abstract by prefixing the class name with 
 * 'Abstract'.
 *
 * Revision 1.1  2005/01/07 12:37:48  harpechr
 * Version 1.0.2 code. First CVS commit!
 */

package org.cah.eclipse.plugins.dctm.dql.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;


/**
 * <H4>Class to implement all those methods inherited from interfaces that we
 * never use.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Dec 8, 2004 2:28:35 PM.</DD>
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
public abstract class AbstractBaseAction
										extends
											Action
													implements
														IWorkbenchWindowActionDelegate,
														IViewActionDelegate
{

	/**
	 * The view that contains this action.
	 * 
	 * @since 1.0
	 */
	private IViewPart			view	= null;

	/**
	 * The workbench window.
	 * 
	 * @since 1.0
	 */
	private IWorkbenchWindow	window	= null;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 8, 2004 2:34:35 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public AbstractBaseAction()
	{

		super();
	}

	/**
	 * Release resources.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 8:23:43 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose()
	{

		/* No work done here. */
	}

	/**
	 * Initializes this action delegate with the view it will work in.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 8, 2004 2:33:25 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aView
	 *            the view that provides the context for this delegate.
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(final IViewPart aView)
	{

		setView(aView);
	}

	/**
	 * Initialize the action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 8:24:19 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aWindow
	 *            the calling window.
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public void init(final IWorkbenchWindow aWindow)
	{

		setWindow(aWindow);
	}

	/**
	 * Just call <code>run()</code>.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 8, 2004 2:28:35 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param action
	 *            the action proxy that handles the presentation portion of the
	 *            action
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(final IAction action)
	{

		run();

	}

	/**
	 * Do nothing.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 8, 2004 2:28:35 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param action
	 *            the action proxy that handles presentation portion of the
	 *            action
	 * @param selection
	 *            the current selection, or <code>null</code> if there is no
	 *            selection.
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(final IAction action,
									final ISelection selection)
	{

		/* Do nothing. */
	}

	/**
	 * Get the view context.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 8, 2004 2:31:04 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the view part.
	 */
	protected IViewPart getView()
	{

		return this.view;
	}

	/**
	 * Get the workbench window
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 8:24:47 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the window
	 */
	protected IWorkbenchWindow getWindow()
	{

		return this.window;
	}

	/**
	 * Set the view context.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 8, 2004 2:30:49 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aView
	 *            the view part.
	 */
	protected void setView(final IViewPart aView)
	{

		this.view = aView;
	}

	/**
	 * Set the workbench window.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 8:24:47 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param window
	 *            The window to set
	 */
	protected void setWindow(final IWorkbenchWindow window)
	{

		this.window = window;
	}
}
