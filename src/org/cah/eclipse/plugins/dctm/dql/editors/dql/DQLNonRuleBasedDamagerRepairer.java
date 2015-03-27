/*-
 * $Log: DQLNonRuleBasedDamagerRepairer.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:28:23  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.6  2005/03/25 09:21:17  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/10 11:33:38  harpechr
 * Safety commit!
 *
 * Revision 1.4  2005/02/09 14:01:36  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:55  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:13  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:49  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.editors.dql;

import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.presentation.IPresentationDamager;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.jface.util.Assert;
import org.eclipse.swt.custom.StyleRange;


/**
 * <H4>Detect damages to the document and repair them.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 28, 2004 2:54:57 PM.</DD>
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
public class DQLNonRuleBasedDamagerRepairer
											implements
												IPresentationDamager,
												IPresentationRepairer
{

	/**
	 * The default text attribute if non storage returned as data by the current
	 * token <code>defaultTextAttribute</code>.
	 * 
	 * @since 1.0
	 */
	private TextAttribute	defaultTextAttribute	= null;

	/**
	 * The document this object works on <code>document</code>.
	 * 
	 * @since 1.0
	 */
	private IDocument		document				= null;

	/**
	 * Sole contructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 2:58:09 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDefaultTextAttribute
	 *            the default text attribute.
	 */
	public DQLNonRuleBasedDamagerRepairer(
											final TextAttribute aDefaultTextAttribute)
	{

		Assert.isNotNull(aDefaultTextAttribute);
		setDefaultTextAttribute(aDefaultTextAttribute);
	}

	/**
	 * Fills the given presentation with the style ranges which when applied to
	 * the presentation reconciler's text viewer repair the presentational
	 * damage described by the given region.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 3:04:51 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param presentation
	 *            the text presentation to be filled by this repairer
	 * @param damage
	 *            the damage to be repaired
	 * @see org.eclipse.jface.text.presentation.IPresentationRepairer#createPresentation(org.eclipse.jface.text.TextPresentation,
	 *      org.eclipse.jface.text.ITypedRegion)
	 */
	public void createPresentation(final TextPresentation presentation,
									final ITypedRegion damage)
	{

		addRange(presentation, damage.getOffset(), damage.getLength(),
			getDefaultTextAttribute());
	}

	/**
	 * Returns the damage in the document's presentation caused by the given
	 * document change. The damage is restricted to the specified partition for
	 * which the presentation damager is responsible. The damage may also depend
	 * on whether the document change also caused changes of the document's
	 * partitioning.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 3:02:47 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param partition
	 *            the partition inside which the damage must be determined
	 * @param event
	 *            the event describing the change whose damage must be
	 *            determined
	 * @param documentPartitioningChanged
	 *            indicates whether the given change changed the document's
	 *            partitioning
	 * @return the computed damage
	 * @see org.eclipse.jface.text.presentation.IPresentationDamager#getDamageRegion(org.eclipse.jface.text.ITypedRegion,
	 *      org.eclipse.jface.text.DocumentEvent, boolean)
	 */
	public IRegion getDamageRegion(final ITypedRegion partition,
									final DocumentEvent event,
									final boolean documentPartitioningChanged)
	{

		if(!documentPartitioningChanged)
		{
			try
			{
				IRegion info = getDocument().getLineInformationOfOffset(
					event.getOffset());
				int start = Math.max(partition.getOffset(), info.getOffset());
				int end = event.getOffset()
					+ (event.getText() == null ? event.getLength() : event
						.getText().length());
				if(info.getOffset() <= end
					&& end <= info.getOffset() + info.getLength())
				{
					/* Optimize the case of the same line. */
					end = info.getOffset() + info.getLength();
				} else
				{
					end = endOfLineOf(end);
				}
				end = Math.min(partition.getOffset() + partition.getLength(),
					end);
				return new Region(start, end - start);
			} catch(BadLocationException blex)
			{
				MessageView.getInstance().addMessage(blex);
			}
		}
		return partition;
	}

	/**
	 * Tells the presentation damager on which document it will work.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 3:00:40 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDocument
	 *            the damager's working document
	 * @see org.eclipse.jface.text.presentation.IPresentationRepairer#setDocument(org.eclipse.jface.text.IDocument)
	 */
	public void setDocument(final IDocument aDocument)
	{

		this.document = aDocument;
	}

	/**
	 * Adds style information to the given text presentation.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 3:05:23 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param presentation
	 *            the text presentation to be extended
	 * @param offset
	 *            the offset of the range to be styled
	 * @param length
	 *            the length of the range to be styled
	 * @param attr
	 *            the attribute describing the style of the range to be styled
	 */
	protected void addRange(TextPresentation presentation, int offset,
							int length, TextAttribute attr)
	{

		if(attr != null)
		{
			presentation.addStyleRange(new StyleRange(offset, length, attr
				.getForeground(), attr.getBackground(), attr.getStyle()));
		}
	}

	/**
	 * Returns the end offset of the line that contains the specified offset or
	 * if the offset storage inside a line delimiter, the end offset of the next
	 * line.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 3:00:53 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param offset
	 *            the offset whose line end offset must be computed
	 * @return the line end offset for the given offset
	 * @throws BadLocationException
	 *             if offset storage invalid in the current document
	 */
	protected int endOfLineOf(final int offset) throws BadLocationException
	{

		IRegion info = getDocument().getLineInformationOfOffset(offset);
		if(offset <= info.getOffset() + info.getLength())
		{
			return info.getOffset() + info.getLength();
		}
		int line = getDocument().getLineOfOffset(offset);
		try
		{
			info = getDocument().getLineInformation(line + 1);
			return info.getOffset() + info.getLength();
		} catch(BadLocationException x)
		{
			return getDocument().getLength();
		}
	}

	/**
	 * Get the text attribute.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 3:00:11 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the defaultTextAttribute
	 */
	protected TextAttribute getDefaultTextAttribute()
	{

		return this.defaultTextAttribute;
	}

	/**
	 * Get the document.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 3:00:11 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the document
	 */
	protected IDocument getDocument()
	{

		return this.document;
	}

	/**
	 * Set the text attribute.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 3:00:11 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDefaultTextAttribute
	 *            The defaultTextAttribute to set
	 */
	protected void setDefaultTextAttribute(
											final TextAttribute aDefaultTextAttribute)
	{

		this.defaultTextAttribute = aDefaultTextAttribute;
	}
}
