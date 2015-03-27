/*-
 * $Log: APICompletionProcessor.java,v $
 * Revision 1.10  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.9  2005/12/04 20:27:46  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.8  2005/11/21 14:53:38  madcook
 * Old 1.4 style code removed.
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

import java.util.Collections;
import java.util.Vector;

import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;


/**
 * <H4>API doc completion processor for key words, types and attribute names.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Dec 2, 2004 10:44:12 AM.</DD>
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
public class APICompletionProcessor
									implements
										IContentAssistProcessor
{

	/**
	 * Characters to activate the completion processor.
	 * 
	 * @since 1.0
	 */
	private static final char[]	ACTIVATION_CHARS	= new char[] {',', 10, 13,
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
		'o', 'p', 'q', 'r', 's', 't', 'u', 'x', 'y', 'x', '_'};

	/**
	 * Returns a list of completion proposals based on the specified location
	 * within the document that corresponds to the current cursor position
	 * within the text viewer.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:08:12 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param viewer
	 *            the viewer whose document is used to compute the proposals.
	 * @param documentOffset
	 *            an offset within the document for which completions should be
	 *            computed.
	 * @return an array of completion proposals or <code>null</code> if no
	 *         proposals are possible.
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer,
	 *      int)
	 */
	public ICompletionProposal[] computeCompletionProposals(
															final ITextViewer viewer,
															final int documentOffset)
	{

		final String text = viewer.getDocument().get();
		final int previousNewLineIndex = text.lastIndexOf('\n',
			documentOffset - 1) > -1 ? text.lastIndexOf('\n',
			documentOffset - 1) : 0;
		final int nextNewLineIndex = text.indexOf('\n', documentOffset) > previousNewLineIndex
			? text.indexOf('\n', documentOffset) : text.length();
		final String line = text.substring(previousNewLineIndex + 1,
			nextNewLineIndex);
		String last = null;
		String first = line;
		int lastCommaIndex = line.lastIndexOf(',') + 1;
		if(lastCommaIndex > 0)
		{
			last = line.substring(lastCommaIndex);
			first = line.substring(0, lastCommaIndex);
		}
		if(first.endsWith("describe,c,type,")) //$NON-NLS-1$
		{
			return getProposals(APIKeywordScanner.TYPES, previousNewLineIndex
				+ lastCommaIndex + 1, last);
		} else if(first.endsWith("create,c,")) //$NON-NLS-1$
		{
			return getProposals(APIKeywordScanner.TYPES, previousNewLineIndex
				+ lastCommaIndex + 1, last);
		} else if(first.endsWith(",c,l,")) //$NON-NLS-1$
		{
			return getProposals(APIKeywordScanner.ATTRIBUTES,
				previousNewLineIndex + lastCommaIndex + 1, last);
		} else if(last == null)
		{
			return getProposals(APIKeywordScanner.COMBINED_KEYWORDS,
				previousNewLineIndex + 1, first);
		}
		return null;
	}

	/**
	 * Returns information about possible contexts based on the specified
	 * location within the document that corresponds to the current cursor
	 * position within the text viewer.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:12:38 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param viewer
	 *            the viewer whose document is used to compute the possible
	 *            contexts.
	 * @param documentOffset
	 *            an offset within the document for which context information
	 *            should be computed.
	 * @return an array of context information objects or <code>null</code> if
	 *         no context could be found.
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeContextInformation(org.eclipse.jface.text.ITextViewer,
	 *      int)
	 */
	public IContextInformation[] computeContextInformation(
															final ITextViewer viewer,
															int documentOffset)
	{

		return null;
	}

	/**
	 * Returns the characters which when entered by the user should
	 * automatically trigger the presentation of possible completions.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:12:47 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the auto activation characters for completion proposal or
	 *         <code>null</code> if no auto activation is desired
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getCompletionProposalAutoActivationCharacters()
	 */
	public char[] getCompletionProposalAutoActivationCharacters()
	{

		return APICompletionProcessor.ACTIVATION_CHARS;
	}

	/**
	 * Returns the characters which when entered by the user should
	 * automatically trigger the presentation of context information.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:12:55 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the auto activation characters for presenting context information
	 *         or <code>null</code> if no auto activation is desired
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationAutoActivationCharacters()
	 */
	public char[] getContextInformationAutoActivationCharacters()
	{

		return null;
	}

	/**
	 * Returns a validator used to determine when displayed context information
	 * should be dismissed. May only return <code>null</code> if the processor
	 * is incapable of computing context information.
	 * <p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:13:01 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return a context information validator, or <code>null</code> if the
	 *         processor is incapable of computing context information
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationValidator()
	 */
	public IContextInformationValidator getContextInformationValidator()
	{

		return null;
	}

	/**
	 * Returns the reason why this content assist processor was unable to
	 * produce any completion proposals or context information.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:13:10 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return an error message or <code>null</code> if no error occurred
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage()
	 */
	public String getErrorMessage()
	{

		return null;
	}

	/**
	 * Convert strings to proposals.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 1:11:43 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param proposals
	 *            values to convert.
	 * @param documentOffset
	 *            an offset within the document for which context information
	 *            should be computed.
	 * @param alreadyTyped
	 *            value already typed.
	 * @return the converted proposals.
	 */
	protected ICompletionProposal[] getProposals(
													final Vector<String> proposals,
													int documentOffset,
													final String alreadyTyped)
	{

		try
		{
			final Vector<String> tempResults = new Vector<String>();

			for(String proposal: proposals)
			{
				if(((proposal != null) && (!(proposal = proposal.trim())
					.equals(""))) //$NON-NLS-1$
					&& ((alreadyTyped == null) || (alreadyTyped.equals("")) || (proposal.startsWith(alreadyTyped))) //$NON-NLS-1$
					&& (!proposal.equals(alreadyTyped)))
				{
					tempResults.addElement(proposal);
				}
			}
			Collections.sort(tempResults);
			ICompletionProposal[] results = new ICompletionProposal[tempResults
				.size()];
			int replacementLength = 0;
			if(alreadyTyped != null)
			{
				replacementLength = alreadyTyped.length();
			}
			int proposalIndex = 0;
			for(String proposal: tempResults)
			{
				results[proposalIndex++] = new CompletionProposal(proposal,
					documentOffset, replacementLength, proposal.length());
			}
			return results;
		} catch(Throwable t)
		{
			MessageView.getInstance().addMessage(t);
		}
		return null;
	}
}
