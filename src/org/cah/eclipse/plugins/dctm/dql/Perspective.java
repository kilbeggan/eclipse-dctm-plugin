/*-
 * $Log: Perspective.java,v $
 * Revision 1.9  2005/12/04 22:14:39  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.8  2005/12/04 20:32:42  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.7  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.6  2005/03/25 09:18:09  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/10 11:33:54  harpechr
 * Safety commit!
 *
 * Revision 1.4  2005/02/09 14:01:31  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:53  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:00  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:48  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql;

import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.ResultView;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;


/**
 * <H4>Perspective that generates the initial page layout and visible action
 * set for a page.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 24, 2004 3:35:46 PM.</DD>
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
public class Perspective
						implements
							IPerspectiveFactory
{

	/**
	 * View location bottom right.
	 * 
	 * @since 1.0
	 */
	private static final String	BOTTOM_RIGHT	= "bottomRight";	//$NON-NLS-1$

	/**
	 * View location left.
	 * 
	 * @since 1.0
	 */
	private static final String	LEFT			= "left";			//$NON-NLS-1$

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 24, 2004 3:35:46 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public Perspective()
	{

		super();
	}

	/**
	 * Creates the initial layout for a page.
	 * <p>
	 * Implementors of this method may add additional views to a perspective.
	 * The perspective already contains an editor folder identified by the
	 * result of <code>IPageLayout.getEditorArea()</code>. Additional views
	 * should be added to the layout using this value as the initial point of
	 * reference.
	 * </p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 24, 2004 3:36:08 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param pageLayout
	 *            the page layout
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	public void createInitialLayout(final IPageLayout pageLayout)
	{

		final String editorArea = pageLayout.getEditorArea();
		/* Left folder. */
		final IFolderLayout left = pageLayout.createFolder(Perspective.LEFT,
			IPageLayout.LEFT, 0.25f, editorArea);
		left.addView(ConnectionView.ID);
		left.addView(IPageLayout.ID_RES_NAV);
		/* Botom folder. */
		final IFolderLayout bottomRight = pageLayout.createFolder(
			Perspective.BOTTOM_RIGHT, IPageLayout.BOTTOM, 0.50f, editorArea);
		bottomRight.addView(ResultView.ID);
		bottomRight.addView(MessageView.ID);
	}

}
