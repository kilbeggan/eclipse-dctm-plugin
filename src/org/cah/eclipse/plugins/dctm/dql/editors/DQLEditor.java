/*-
 * $Log: DQLEditor.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:28:23  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.6  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.5  2005/03/25 09:18:59  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 14:01:37  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:55  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:16  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:49  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.editors;

import org.cah.eclipse.plugins.dctm.dql.editors.dql.DQLColorProvider;
import org.cah.eclipse.plugins.dctm.dql.editors.dql.DQLDocumentProvider;
import org.cah.eclipse.plugins.dctm.dql.editors.dql.DQLSourceVieweConfiguration;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.FileEditorInput;


/**
 * <H4>DQL statement editor.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 24, 2004 3:58:21 PM.</DD>
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
public class DQLEditor
						extends
							TextEditor
{

	/**
	 * The editor ID.
	 * 
	 * @since 1.0
	 */
	public static String		ID		= DQLEditor.class.getName();

	/**
	 * The editor menu ID.
	 * 
	 * @since 1.0
	 */
	public static String		MENU_ID	= "#" + DQLEditor.ID;			//$NON-NLS-1$

	/**
	 * The edotor colour provider.
	 * 
	 * @since 1.0
	 */
	private DQLColorProvider	colorProvider;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 24, 2004 3:58:22 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public DQLEditor()
	{

		super();
		setColorProvider(new DQLColorProvider());
		setSourceViewerConfiguration(new DQLSourceVieweConfiguration(
			getColorProvider()));
		setDocumentProvider(new DQLDocumentProvider());
		setEditorContextMenuId(MENU_ID);
	}

	/**
	 * Return the DQL welcome message, GNU license and copyright.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 3:09:35 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the welcome message placed in new editors.
	 */
	public static String getWelcomemessage()
	{

		return "/*\n * Simple Documentum DQL editor. Copyright (c): Christopher Harper\n *\n" //$NON-NLS-1$
			+ " * This program is free software; you can redistribute it and/or modify it under the terms of the GNU\n" //$NON-NLS-1$
			+ " * General Public License as published by the Free Software Foundation; either version 2 of the \n" //$NON-NLS-1$
			+ " * License, or (at your option) any later version.\n * \n" //$NON-NLS-1$
			+ " * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without\n" //$NON-NLS-1$
			+ " * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See GNU General\n" //$NON-NLS-1$
			+ " * Public License (http://www.gnu.org/licenses/gpl.html) for more details.\n * \n * Enjoy!\n" //$NON-NLS-1$
			+ " */"; //$NON-NLS-1$
	}

	/**
	 * Release used resources.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:37:29 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @see org.eclipse.ui.IWorkbenchPart#dispose()
	 */
	@Override
	public void dispose()
	{

		super.dispose();
		getColorProvider().dispose();
	}

	/**
	 * Get the selected text.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:37:55 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the selected text.
	 */
	public String getSelectedText()
	{

		return getSourceViewer().getTextWidget().getSelectionText();
	}

	/**
	 * Get all editor text.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:38:15 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return all of the editor text.
	 */
	public String getText()
	{

		return getSourceViewer().getTextWidget().getText();
	}

	/**
	 * Check if the editor has modifications if it's a file editor input.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:39:14 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return true if a file was modified.
	 * @see org.eclipse.ui.ISaveablePart#isDirty()
	 */
	@Override
	public boolean isDirty()
	{

		/* Only ask opened dirty files to save, not buffers. */
		if(getEditorInput() instanceof FileEditorInput)
		{
			return super.isDirty();
		}
		return false;
	}

	/**
	 * Can the editor be saved.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:40:05 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return <code>true</code>.
	 * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed()
	{

		return true;
	}

	/**
	 * Set the editor text.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 3:59:07 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param dqlText
	 *            the text to set.
	 */
	public void setText(final String dqlText)
	{

		getSourceViewer().getTextWidget().setText(dqlText);

	}

	/**
	 * Get the colour provider.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:42:13 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the color provider.
	 */
	protected DQLColorProvider getColorProvider()
	{

		return this.colorProvider;
	}

	/**
	 * Set the colour provider.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:42:20 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aColorProvider
	 *            the colour provider.
	 */
	protected void setColorProvider(final DQLColorProvider aColorProvider)
	{

		this.colorProvider = aColorProvider;
	}
}
