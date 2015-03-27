/*-
 * $Log: APIDoubleClickStrategy.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:27:46  madcook
 * Version 3.0.0 work started.
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

import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.ITextViewer;


/**
 * <H4>Functionality triggered from a double click.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 28, 2004 1:27:02 PM.</DD>
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
public class APIDoubleClickStrategy
									implements
										ITextDoubleClickStrategy
{

	/**
	 * The text viewer.
	 * 
	 * @since 1.0
	 */
	protected ITextViewer	text;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:27:13 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public APIDoubleClickStrategy()
	{

		super();
	}

	/**
	 * The mouse has been double clicked on the given text viewer.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:27:16 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param part
	 *            the viewer into which has been double clicked.
	 * @see org.eclipse.jface.text.ITextDoubleClickStrategy#doubleClicked(org.eclipse.jface.text.ITextViewer)
	 */
	public void doubleClicked(final ITextViewer part)
	{

		int xPosition = part.getSelectedRange().x;
		if(xPosition < 0)
		{
			return;
		}
		setText(part);
		if(!selectComment(xPosition))
		{
			selectWord(xPosition);
		}
	}

	/**
	 * Get the text viewer.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:28:12 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the text
	 */
	protected ITextViewer getText()
	{

		return this.text;
	}

	/**
	 * Select the whole comment.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:29:30 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param caretPos
	 * @return true if the method sucseeds.
	 */
	protected boolean selectComment(final int caretPos)
	{

		final IDocument document = getText().getDocument();
		int startPosition;
		int endPosition;
		try
		{
			int position = caretPos;
			char character = ' ';
			while(position >= 0)
			{
				character = document.getChar(position);
				if(character == '\\')
				{
					position -= 2;
					continue;
				}
				if(character == Character.LINE_SEPARATOR || character == '\"')
				{
					break;
				}
				--position;
			}
			if(character != '\"')
			{
				return false;
			}
			startPosition = position;
			position = caretPos;
			int length = document.getLength();
			character = ' ';
			while(position < length)
			{
				character = document.getChar(position);
				if(character == Character.LINE_SEPARATOR || character == '\"')
				{
					break;
				}
				++position;
			}
			if(character != '\"')
			{
				return false;
			}
			endPosition = position;
			int offset = startPosition + 1;
			int len = endPosition - offset;
			getText().setSelectedRange(offset, len);
			return true;
		} catch(BadLocationException blex)
		{
			MessageView.getInstance().addMessage(blex);
		}
		return false;
	}

	/**
	 * Select the word that was clicked.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:34:58 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param caretPos
	 * @return true;
	 */
	protected boolean selectWord(final int caretPos)
	{

		final IDocument document = getText().getDocument();
		int startPosition;
		int endPosition;
		try
		{
			int position = caretPos;
			char character;
			while(position >= 0)
			{
				character = document.getChar(position);
				if(!Character.isJavaIdentifierPart(character))
				{
					break;
				}
				--position;
			}
			startPosition = position;
			position = caretPos;
			int length = document.getLength();
			while(position < length)
			{
				character = document.getChar(position);
				if(!Character.isJavaIdentifierPart(character))
				{
					break;
				}
				++position;
			}
			endPosition = position;
			selectRange(startPosition, endPosition);
			return true;
		} catch(BadLocationException blex)
		{
			MessageView.getInstance().addMessage(blex);
		}
		return false;
	}

	/**
	 * Set the text viewer.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:28:12 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aText
	 *            The text to set
	 */
	protected void setText(final ITextViewer aText)
	{

		this.text = aText;
	}

	/**
	 * Select a text range.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:36:55 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param startPosition
	 *            start offset of the selection.
	 * @param stopPosition
	 *            end offset of the selection.
	 */
	protected void selectRange(final int startPosition, final int stopPosition)
	{

		int offset = startPosition + 1;
		int length = stopPosition - offset;
		getText().setSelectedRange(offset, length);
	}
}
