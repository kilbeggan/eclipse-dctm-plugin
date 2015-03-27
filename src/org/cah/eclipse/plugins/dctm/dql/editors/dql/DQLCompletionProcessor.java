/*-
 * $Log: DQLCompletionProcessor.java,v $
 * Revision 1.10  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.9  2005/12/04 20:28:23  madcook
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

import java.util.Arrays;
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
 * <H4>DQL completion processor.</H4>
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
public class DQLCompletionProcessor
									implements
										IContentAssistProcessor
{

	/**
	 * Characters to activate the completion processor. Characters are: '.',
	 * ',', 10, 13, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
	 * 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'x', 'y', 'x', '_', ';', ' '
	 * 
	 * @since 1.0
	 */
	private static final char[]	ACTIVATION_CHARS	= new char[] {'.', ',', 10,
		13, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'x', 'y', 'x', '_', ';', ' '};

	/**
	 * Get the completion proposals.
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
	 *            the open DQL editor.
	 * @param documentOffset
	 *            the cursor posiotion in the document.
	 * @return array of proposals.
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer,
	 *      int)
	 */
	public ICompletionProposal[] computeCompletionProposals(
															final ITextViewer viewer,
															final int documentOffset)
	{

		try
		{
			final int statementStart = getStatementStart(viewer, documentOffset);
			final int statementEnd = getStatementEnd(viewer, documentOffset);
			final String text = viewer.getDocument().get();
			final String currentStatement = text.substring(statementStart,
				statementEnd);
			int stetementOffset = documentOffset - statementStart;
			if(stetementOffset > currentStatement.length())
			{
				stetementOffset = currentStatement.length();
			}
			final String lowerCurrentStatement = currentStatement.toLowerCase();
			final String trimmedLowerCurrentStatement = lowerCurrentStatement
				.trim();
			if(trimmedLowerCurrentStatement.startsWith("abort")) //$NON-NLS-1$
			{
				return computeAbortBeginCommitCompletionProposals(
					currentStatement, stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("alter")) //$NON-NLS-1$
			{
				return computeAlterCompletionProposals(currentStatement,
					stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("begin")) //$NON-NLS-1$
			{
				return computeAbortBeginCommitCompletionProposals(
					currentStatement, stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("change")) //$NON-NLS-1$
			{
				return computeChangeCompletionProposals(currentStatement,
					stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("commit")) //$NON-NLS-1$
			{
				return computeAbortBeginCommitCompletionProposals(
					currentStatement, stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("create")) //$NON-NLS-1$
			{
				return computeCreateCompletionProposals(currentStatement,
					stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("delete")) //$NON-NLS-1$
			{
				return computeDeleteCompletionProposals(currentStatement,
					stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("drop")) //$NON-NLS-1$
			{
				return computeDropCompletionProposals(currentStatement,
					stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("grant")) //$NON-NLS-1$
			{
				return computeGrantRevokeCompletionProposals(currentStatement,
					stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("insert")) //$NON-NLS-1$
			{
				return computeInsertCompletionProposals(currentStatement,
					stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("register")) //$NON-NLS-1$
			{
				return computeRegisterCompletionProposals(currentStatement,
					stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("revoke")) //$NON-NLS-1$
			{
				return computeGrantRevokeCompletionProposals(currentStatement,
					stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("select")) //$NON-NLS-1$
			{
				return computeSelectCompletionProposals(lowerCurrentStatement,
					stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("unregister")) //$NON-NLS-1$
			{
				return computeUnregisterCompletionProposals(currentStatement,
					stetementOffset, documentOffset);
			} else if(trimmedLowerCurrentStatement.startsWith("update")) //$NON-NLS-1$
			{
				return computeUpdateCompletionProposals(currentStatement,
					stetementOffset, documentOffset);
			}
		} catch(Throwable t)
		{
			System.out.print(t.getMessage());
			t.printStackTrace(System.out);
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
	 *            contexts
	 * @param documentOffset
	 *            an offset within the document for which context information
	 *            should be computed
	 * @return an array of context information objects or <code>null</code> if
	 *         no context could be found. This implementation returns
	 *         <code>null</null>.
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeContextInformation(org.eclipse.jface.text.ITextViewer,
	 *      int)
	 */
	public IContextInformation[] computeContextInformation(
															final ITextViewer viewer,
															final int documentOffset)
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
	 *         <code>null</code> if no auto activation is desired. This
	 *         implementation returns characters: '.', ',', 10, 13, 'a', 'b',
	 *         'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
	 *         'p', 'q', 'r', 's', 't', 'u', 'x', 'y', 'x', '_', ';', ' '.
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getCompletionProposalAutoActivationCharacters()
	 */
	public char[] getCompletionProposalAutoActivationCharacters()
	{

		return DQLCompletionProcessor.ACTIVATION_CHARS;
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
	 *         or <code>null</code> if no auto activation is desired. This
	 *         implementation returns <code>null</code>.
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
	 *         processor is incapable of computing context information. This
	 *         implementation returns <code>null</code>.
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
	 * @return an error message or <code>null</code> if no error occurred.
	 *         This implementation returns <code>null</code>.
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage()
	 */
	public String getErrorMessage()
	{

		return null;
	}

	/**
	 * Get abort, begin & commit DQL statement proposals.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 4:04:56 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param currentStatement
	 *            the current DQL statement.
	 * @param statementOffset
	 *            cursor position inside the statement.
	 * @param documentOffset
	 *            cursor position in the DQL editor.
	 * @return the proposals.
	 */
	protected ICompletionProposal[] computeAbortBeginCommitCompletionProposals(
																				final String currentStatement,
																				final int statementOffset,
																				final int documentOffset)
	{

		if(currentStatement.indexOf("tran") == -1) //$NON-NLS-1$
		{
			Vector<String> keywords = new Vector<String>(Arrays
				.asList(new String[] {"TRAN", "TRANSACTION"})); //$NON-NLS-1$ //$NON-NLS-2$
			String alreadyTyped = null;
			final char[] chars = currentStatement.toCharArray();
			for(int charIndex = statementOffset - 1; charIndex > -1; charIndex--)
			{
				if((Character.isWhitespace(chars[charIndex]))
					|| (charIndex == 0))
				{
					alreadyTyped = new String(chars, charIndex, statementOffset
						- charIndex).trim();
					break;
				}
			}
			return getProposals(keywords, documentOffset, alreadyTyped);
		}
		return null;
	}

	/**
	 * Get alter DQL statement proposals.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 4:04:59 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param currentStatement
	 *            the current DQL statement.
	 * @param statementOffset
	 *            cursor position inside the statement.
	 * @param documentOffset
	 *            cursor position in the DQL editor.
	 * @return valid alter proposals.
	 */
	protected ICompletionProposal[] computeAlterCompletionProposals(
																	final String currentStatement,
																	final int statementOffset,
																	final int documentOffset)
	{

		Vector<String> keywords = null;
		final int groupIndex = currentStatement.indexOf("group"); //$NON-NLS-1$

		if((groupIndex != -1) && (documentOffset > groupIndex))
		{
			keywords = new Vector<String>(Arrays.asList(new String[] {
				"ADD", "DROP", "SET ADDRESS"})); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else
		{
			final int typeIndex = currentStatement.indexOf("type"); //$NON-NLS-1$
			if((typeIndex != -1) && (statementOffset > typeIndex))
			{
				if((currentStatement.indexOf("modify", statementOffset) > -1) //$NON-NLS-1$
					|| (currentStatement.indexOf("drop", statementOffset) > -1) //$NON-NLS-1$
					|| (currentStatement
						.indexOf("add_ftindex", statementOffset) > -1) //$NON-NLS-1$
					|| (currentStatement.indexOf(
						"drop_ftindex", statementOffset) > -1)) //$NON-NLS-1$
				{
					keywords = DQLKeywordScanner.TYPE_ATTRIBUTES;
				} else
				{
					keywords = DQLKeywordScanner.DOCBASE_TYPES;
				}
			} else
			{
				keywords = new Vector<String>(Arrays.asList(new String[] {
					"GROUP", "DOCBASE_TYPE"})); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		String alreadyTyped = null;
		final char[] chars = currentStatement.toCharArray();
		for(int charIndex = statementOffset - 1; charIndex > -1; charIndex--)
		{
			if((Character.isWhitespace(chars[charIndex])) || (charIndex == 0))
			{
				alreadyTyped = new String(chars, charIndex, statementOffset
					- charIndex).trim();
				break;
			}
		}
		return getProposals(keywords, documentOffset, alreadyTyped);
	}

	/**
	 * Get change DQL statement proposals.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 4:05:09 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param currentStatement
	 *            the current DQL statement.
	 * @param statementOffset
	 *            cursor position inside the statement.
	 * @param documentOffset
	 *            cursor position in the DQL editor.
	 * @return change type proposals.
	 */
	protected ICompletionProposal[] computeChangeCompletionProposals(
																		final String currentStatement,
																		final int statementOffset,
																		final int documentOffset)
	{

		Vector<String> keywords = null;
		final int whereIndex = currentStatement.indexOf("where"); //$NON-NLS-1$
		if((whereIndex != -1) && (statementOffset > whereIndex))
		{
			keywords = DQLKeywordScanner.TYPE_ATTRIBUTES;
		} else
		{
			final int searchIndex = currentStatement.indexOf("search"); //$NON-NLS-1$
			final int versionIndex = currentStatement.indexOf("version"); //$NON-NLS-1$
			final int inAssemblyIndex = currentStatement.indexOf("in assembly"); //$NON-NLS-1$
			final int descendIndex = currentStatement.indexOf("descend"); //$NON-NLS-1$
			if(((searchIndex > -1) && (statementOffset > searchIndex))
				|| ((versionIndex > -1) && (statementOffset > versionIndex))
				|| ((inAssemblyIndex > -1) && (statementOffset > inAssemblyIndex))
				|| ((descendIndex > -1) && (statementOffset > descendIndex)))
			{
				return null;
			}
			keywords = DQLKeywordScanner.DOCBASE_TYPES;
		}
		String alreadyTyped = null;
		final char[] chars = currentStatement.toCharArray();
		for(int charIndex = statementOffset - 1; charIndex > -1; charIndex--)
		{
			if((Character.isWhitespace(chars[charIndex])) || (charIndex == 0))
			{
				alreadyTyped = new String(chars, charIndex, statementOffset
					- charIndex).trim();
				break;
			}
		}
		return getProposals(keywords, documentOffset, alreadyTyped);
	}

	/**
	 * Get crate DQL statement proposals.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 4:05:15 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param currentStatement
	 *            the current DQL statement.
	 * @param statementOffset
	 *            cursor position inside the statement.
	 * @param documentOffset
	 *            cursor position in the DQL editor.
	 * @return create statement completion proposals.
	 */
	protected ICompletionProposal[] computeCreateCompletionProposals(
																		final String currentStatement,
																		final int statementOffset,
																		final int documentOffset)
	{

		Vector<String> keywords = null;
		final int supertypeIndex = currentStatement.indexOf("supertype"); //$NON-NLS-1$
		if((supertypeIndex != -1) && (statementOffset > supertypeIndex))
		{
			keywords = DQLKeywordScanner.DOCBASE_TYPES;
		} else
		{
			final int searchIndex = currentStatement.indexOf("object"); //$NON-NLS-1$
			if((searchIndex != -1) && (statementOffset > searchIndex))
			{
				keywords = DQLKeywordScanner.TYPE_ATTRIBUTES;
			} else
			{
				keywords = DQLKeywordScanner.DOCBASE_TYPES;
			}
		}
		String alreadyTyped = null;
		final char[] chars = currentStatement.toCharArray();
		for(int charIndex = statementOffset - 1; charIndex > -1; charIndex--)
		{
			if((Character.isWhitespace(chars[charIndex])) || (charIndex == 0))
			{
				alreadyTyped = new String(chars, charIndex, statementOffset
					- charIndex).trim();
				break;
			}
		}
		return getProposals(keywords, documentOffset, alreadyTyped);
	}

	/**
	 * Get delete DQL statement proposals.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 4:05:18 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param currentStatement
	 *            the current DQL statement.
	 * @param statementOffset
	 *            cursor position inside the statement.
	 * @param documentOffset
	 *            cursor position in the DQL editor.
	 * @return proposals for delete DQL statements.
	 */
	protected ICompletionProposal[] computeDeleteCompletionProposals(
																		final String currentStatement,
																		final int statementOffset,
																		final int documentOffset)
	{

		Vector<String> keywords = null;
		final int fromIndex = currentStatement.indexOf("from"); //$NON-NLS-1$
		if(fromIndex != -1)
		{
			final int whereIndex = currentStatement.indexOf("where"); //$NON-NLS-1$
			if((whereIndex != -1) && (statementOffset > whereIndex))
			{
				keywords = DQLKeywordScanner.TABLE_COLUMNS;
			} else if(statementOffset > fromIndex)
			{
				keywords = DQLKeywordScanner.REGISTERED_TABLES;
			} else
			{
				return null;
			}
		} else
		{
			final int whereIndex = currentStatement.indexOf("where"); //$NON-NLS-1$
			if((whereIndex != -1) && (statementOffset > whereIndex))
			{
				keywords = DQLKeywordScanner.TYPE_ATTRIBUTES;
			} else
			{
				keywords = DQLKeywordScanner.DOCBASE_TYPES;
			}
		}
		String alreadyTyped = null;
		final char[] chars = currentStatement.toCharArray();
		for(int charIndex = statementOffset - 1; charIndex > -1; charIndex--)
		{
			if((Character.isWhitespace(chars[charIndex])) || (charIndex == 0))
			{
				alreadyTyped = new String(chars, charIndex, statementOffset
					- charIndex).trim();
				break;
			}
		}
		return getProposals(keywords, documentOffset, alreadyTyped);
	}

	/**
	 * Get delete DQL statement proposals.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 4:05:23 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param currentStatement
	 *            the current DQL statement.
	 * @param statementOffset
	 *            cursor position inside the statement.
	 * @param documentOffset
	 *            cursor position in the DQL editor.
	 * @return drop statement proposals
	 */
	protected ICompletionProposal[] computeDropCompletionProposals(
																	final String currentStatement,
																	final int statementOffset,
																	final int documentOffset)
	{

		Vector<String> keywords = null;
		final int typeIndex = currentStatement.indexOf("type"); //$NON-NLS-1$
		if((typeIndex != -1) && (statementOffset > typeIndex))
		{
			keywords = DQLKeywordScanner.DOCBASE_TYPES;
		} else
		{
			final int groupIndex = currentStatement.indexOf("group"); //$NON-NLS-1$
			if((groupIndex != -1) && (statementOffset > groupIndex))
			{
				return null;
			}
			keywords = new Vector<String>(Arrays.asList(new String[] {
				"GROUP", "TYPE"})); //$NON-NLS-1$ //$NON-NLS-2$
		}
		String alreadyTyped = null;
		final char[] chars = currentStatement.toCharArray();
		for(int charIndex = statementOffset - 1; charIndex > -1; charIndex--)
		{
			if((Character.isWhitespace(chars[charIndex])) || (charIndex == 0))
			{
				alreadyTyped = new String(chars, charIndex, statementOffset
					- charIndex).trim();
				break;
			}
		}
		return getProposals(keywords, documentOffset, alreadyTyped);
	}

	/**
	 * Get grant & revoke DQL statement proposals.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 4:05:28 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param currentStatement
	 *            the current DQL statement.
	 * @param statementOffset
	 *            cursor position inside the statement.
	 * @param documentOffset
	 *            cursor position in the DQL editor.
	 * @return grant statement completion proposals.
	 */
	protected ICompletionProposal[] computeGrantRevokeCompletionProposals(
																			final String currentStatement,
																			final int statementOffset,
																			final int documentOffset)
	{

		Vector<String> keywords = null;
		final int toFromIndex = currentStatement.indexOf("to") > currentStatement.indexOf("from") ? currentStatement //$NON-NLS-1$ //$NON-NLS-2$
				.indexOf("to") : currentStatement.indexOf("from"); //$NON-NLS-1$ //$NON-NLS-2$
		if((toFromIndex != -1) && (statementOffset > toFromIndex))
		{
			return null;
		}
		keywords = new Vector<String>(Arrays.asList(new String[] {
			"SUPERUSER", "SYSADMIN", "CREATE TYPE", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"CREATE CABINET", "CREATE GROUP"})); //$NON-NLS-1$ //$NON-NLS-2$
		String alreadyTyped = null;
		final char[] chars = currentStatement.toCharArray();
		for(int charIndex = statementOffset - 1; charIndex > -1; charIndex--)
		{
			if((Character.isWhitespace(chars[charIndex])) || (charIndex == 0))
			{
				alreadyTyped = new String(chars, charIndex, statementOffset
					- charIndex).trim();
				break;
			}
		}
		return getProposals(keywords, documentOffset, alreadyTyped);
	}

	/**
	 * Get insert DQL statement proposals.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 4:05:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param currentStatement
	 *            the current DQL statement.
	 * @param statementOffset
	 *            cursor position inside the statement.
	 * @param documentOffset
	 *            cursor position in the DQL editor.
	 * @return insert statement completion proposals.
	 */
	protected ICompletionProposal[] computeInsertCompletionProposals(
																		final String currentStatement,
																		final int statementOffset,
																		final int documentOffset)
	{

		Vector<String> keywords = null;
		final int valuesIndex = currentStatement.indexOf("values"); //$NON-NLS-1$
		if((valuesIndex != -1) && (statementOffset > valuesIndex))
		{
			return null;
		}
		final int openIndex = currentStatement.indexOf('(');
		final int closeIndex = currentStatement.indexOf(')');
		if((statementOffset > openIndex)
			&& ((statementOffset < closeIndex) || (closeIndex == -1)))
		{
			keywords = DQLKeywordScanner.TABLE_COLUMNS;
		} else
		{
			keywords = DQLKeywordScanner.REGISTERED_TABLES;
		}
		String alreadyTyped = null;
		final char[] chars = currentStatement.toCharArray();
		for(int charIndex = statementOffset - 1; charIndex > -1; charIndex--)
		{
			if((Character.isWhitespace(chars[charIndex])) || (charIndex == 0))
			{
				alreadyTyped = new String(chars, charIndex, statementOffset
					- charIndex).trim();
				break;
			}
		}
		return getProposals(keywords, documentOffset, alreadyTyped);
	}

	/**
	 * Get register DQL statement proposals which are none.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 4:05:38 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param currentStatement
	 *            the current DQL statement.
	 * @param statementOffset
	 *            cursor position inside the statement.
	 * @param documentOffset
	 *            cursor position in the DQL editor.
	 * @return register statement copletion proposals. Current implementation
	 *         doesn't return anything.s
	 */
	protected ICompletionProposal[] computeRegisterCompletionProposals(
																		final String currentStatement,
																		final int statementOffset,
																		final int documentOffset)
	{

		if((statementOffset != -1) && (documentOffset != -1)
			&& (currentStatement != null))
		{
			/*
			 * Use all of the parameters just to get rid of the warning about
			 * unused parameters.
			 */
		}
		return null;
	}

	/**
	 * Get select DQL statement proposals.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 4:05:45 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param currentStatement
	 *            the current DQL statement.
	 * @param statementOffset
	 *            cursor position inside the statement.
	 * @param documentOffset
	 *            cursor position in the DQL editor.
	 * @return select statement proposals.
	 */
	protected ICompletionProposal[] computeSelectCompletionProposals(
																		final String currentStatement,
																		final int statementOffset,
																		final int documentOffset)
	{

		Vector<String> keywords = null;
		final int enableIndex = currentStatement.indexOf("enable"); //$NON-NLS-1$
		if((enableIndex != -1) && (statementOffset > enableIndex))
		{
			keywords = new Vector<String>(Arrays.asList(new String[] {
				"SQL_DEF_RESULT_SET", "FORCE_ORDER", //$NON-NLS-1$ //$NON-NLS-2$
				"RETURN_TOP", "OPTIMIZE_TOP", "FETCH_ALL_RESULTS"})); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else
		{
			final int objectIndex = currentStatement.indexOf("object") > currentStatement.indexOf("all") //$NON-NLS-1$ //$NON-NLS-2$
				? currentStatement.indexOf("object") : currentStatement.indexOf("all"); //$NON-NLS-1$ //$NON-NLS-2$
			final int orderByIndex = currentStatement.indexOf("order by"); //$NON-NLS-1$
			final int groupByIndex = currentStatement.indexOf("group by"); //$NON-NLS-1$
			final int whereIndex = currentStatement.indexOf("where"); //$NON-NLS-1$
			if(((orderByIndex != -1) && (statementOffset > orderByIndex))
				|| ((groupByIndex != -1) && (statementOffset > groupByIndex))
				|| ((whereIndex != -1) && (statementOffset > whereIndex)))
			{
				if(objectIndex != -1)
				{
					keywords = DQLKeywordScanner.TYPE_ATTRIBUTES;
				} else
				{
					keywords = DQLKeywordScanner.ALL_ATTRIBUTES;
				}
			} else
			{
				final int fromIndex = currentStatement.indexOf("from"); //$NON-NLS-1$
				if((fromIndex != -1) && (statementOffset > fromIndex))
				{
					if(objectIndex != -1)
					{
						keywords = DQLKeywordScanner.DOCBASE_TYPES;
					} else
					{
						keywords = DQLKeywordScanner.TYPES_AND_TABLES;
					}
				} else
				{
					if(objectIndex != -1)
					{
						keywords = DQLKeywordScanner.TYPE_ATTRIBUTES;
					} else
					{
						keywords = DQLKeywordScanner.ALL_ATTRIBUTES;
					}
				}
			}
		}
		String alreadyTyped = null;
		final char[] chars = currentStatement.toCharArray();
		for(int charIndex = statementOffset - 1; charIndex > -1; charIndex--)
		{
			if((Character.isWhitespace(chars[charIndex])) || (charIndex == 0))
			{
				alreadyTyped = new String(chars, charIndex, statementOffset
					- charIndex).trim();
				break;
			}
		}
		return getProposals(keywords, documentOffset, alreadyTyped);
	}

	/**
	 * Get unregister DQL statement proposals.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 4:05:49 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param currentStatement
	 *            the current DQL statement.
	 * @param statementOffset
	 *            cursor position inside the statement.
	 * @param documentOffset
	 *            cursor position in the DQL editor.
	 * @return registered table names.
	 */
	protected ICompletionProposal[] computeUnregisterCompletionProposals(
																			final String currentStatement,
																			final int statementOffset,
																			final int documentOffset)
	{

		final Vector<String> keywords = DQLKeywordScanner.REGISTERED_TABLES;
		String alreadyTyped = null;
		final char[] chars = currentStatement.toCharArray();
		for(int charIndex = statementOffset - 1; charIndex > -1; charIndex--)
		{
			if((Character.isWhitespace(chars[charIndex])) || (charIndex == 0))
			{
				alreadyTyped = new String(chars, charIndex, statementOffset
					- charIndex).trim();
				break;
			}
		}
		return getProposals(keywords, documentOffset, alreadyTyped);

	}

	/**
	 * Get update DQL statement proposals.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 4:05:54 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param currentStatement
	 *            the current DQL statement.
	 * @param statementOffset
	 *            cursor position inside the statement.
	 * @param documentOffset
	 *            cursor position in the DQL editor.
	 * @return the update proposals.
	 */
	protected ICompletionProposal[] computeUpdateCompletionProposals(
																		final String currentStatement,
																		final int statementOffset,
																		final int documentOffset)
	{

		Vector<String> keywords = null;
		final int objectIndex = currentStatement.indexOf("object") > currentStatement.indexOf("all") ? currentStatement //$NON-NLS-1$ //$NON-NLS-2$
				.indexOf("object") : currentStatement.indexOf("all"); //$NON-NLS-1$ //$NON-NLS-2$
		final int whereIndex = currentStatement.indexOf("where"); //$NON-NLS-1$
		if((whereIndex != -1) && (statementOffset > whereIndex))
		{
			if(objectIndex != -1)
			{
				keywords = DQLKeywordScanner.TYPE_ATTRIBUTES;
			} else
			{
				keywords = DQLKeywordScanner.ALL_ATTRIBUTES;
			}
		} else
		{
			final int setIndex = currentStatement.indexOf("set"); //$NON-NLS-1$
			if((setIndex != -1) && (statementOffset > setIndex))
			{
				if(objectIndex != -1)
				{
					keywords = DQLKeywordScanner.TYPE_ATTRIBUTES;
				} else
				{
					keywords = DQLKeywordScanner.ALL_ATTRIBUTES;
				}
			} else
			{
				final int appendIndex = currentStatement.indexOf("append"); //$NON-NLS-1$
				final int insertIndex = currentStatement.indexOf("insert"); //$NON-NLS-1$
				final int removeIndex = currentStatement.indexOf("remove"); //$NON-NLS-1$
				final int truncateIndex = currentStatement.indexOf("truncate"); //$NON-NLS-1$
				if(((appendIndex != -1) && (statementOffset > appendIndex))
					|| ((insertIndex != -1) && (statementOffset > insertIndex))
					|| ((removeIndex != -1) && (statementOffset > removeIndex))
					|| ((truncateIndex != -1) && (statementOffset > truncateIndex)))
				{
					keywords = DQLKeywordScanner.TYPE_ATTRIBUTES;
				} else
				{
					if(objectIndex != -1)
					{
						keywords = DQLKeywordScanner.DOCBASE_TYPES;
					} else
					{
						keywords = DQLKeywordScanner.TYPES_AND_TABLES;
					}
				}
			}
		}
		String alreadyTyped = null;
		final char[] chars = currentStatement.toCharArray();
		for(int charIndex = statementOffset - 1; charIndex > -1; charIndex--)
		{
			if((Character.isWhitespace(chars[charIndex])) || (charIndex == 0))
			{
				alreadyTyped = new String(chars, charIndex, statementOffset
					- charIndex).trim();
				break;
			}
		}
		return getProposals(keywords, documentOffset, alreadyTyped);
	}

	/**
	 * Get the actual proposals from a initial list.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 4:40:11 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param proposals
	 *            suggested proposals.
	 * @param documentOffset
	 *            proposal locatoin in the document.
	 * @param alreadyTyped
	 *            the portion that the user has already typed.
	 * @return value proposals.
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
				proposal = proposal.toLowerCase();
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
					documentOffset - replacementLength, replacementLength,
					proposal.length());
			}
			return results;
		} catch(Throwable t)
		{
			MessageView.getInstance().addMessage(t);
		}
		return null;
	}

	/**
	 * Get the end point of the current statement.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 3:45:31 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param viewer
	 *            the document viewer.
	 * @param documentOffset
	 *            carret position.
	 * @return the statement end index.
	 */
	protected int getStatementEnd(final ITextViewer viewer,
									final int documentOffset)
	{

		int returnValue = 0;
		if(documentOffset > 0)
		{
			final String text = viewer.getDocument().get().toLowerCase();
			returnValue = text.length();
			for(String startWord: DQLKeywordScanner.STARTWORDS)
			{
				startWord = startWord.toLowerCase();
				int foundIndex = text.indexOf(startWord, documentOffset);
				if((foundIndex != -1) && (returnValue > foundIndex))
				{
					returnValue = foundIndex;
				}
			}
			int foundIndex = text.indexOf(';', documentOffset);
			if((foundIndex != -1) && (returnValue > foundIndex))
			{
				returnValue = foundIndex;
			}
			foundIndex = text.indexOf("\ngo", documentOffset); //$NON-NLS-1$
			if((foundIndex != -1) && (returnValue > foundIndex))
			{
				returnValue = foundIndex;
			}
		}
		return returnValue;
	}

	/**
	 * Get the start point of the current statement.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 9, 2004 3:45:27 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param viewer
	 *            the document viewer.
	 * @param documentOffset
	 *            carret position.
	 * @return the statement start index.
	 */
	protected int getStatementStart(final ITextViewer viewer,
									final int documentOffset)
	{

		int returnValue = 0;
		if(documentOffset > 0)
		{
			final String text = viewer.getDocument().get().toLowerCase();
			for(String startWord: DQLKeywordScanner.STARTWORDS)
			{
				startWord = startWord.toLowerCase();
				int foundIndex = text.lastIndexOf(startWord, documentOffset);
				if(returnValue < foundIndex)
				{
					returnValue = foundIndex;
				}
			}
		}
		return returnValue;
	}
}
