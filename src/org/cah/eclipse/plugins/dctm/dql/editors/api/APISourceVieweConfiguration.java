/*-
 * $Log: APISourceVieweConfiguration.java,v $
 * Revision 1.8  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.7  2005/12/04 20:27:46  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.6  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.5  2005/03/25 09:21:17  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 14:01:40  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:57  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:18  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:50  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.editors.api;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;


/**
 * <H4>This class bundles the configuration space of a source viewer.</H4>
 * <DL>
 * <DT><B>Description : </B>
 * <DD>
 * <p>
 * This class bundles the configuration space of a source viewer. Instances of
 * this class are passed to the <code>configure</code> method of
 * <code>ISourceViewer</code>.
 * </p>
 * <p>
 * Each method in this class get as argument the source viewer for which it
 * should provide a particular configuration setting such as a presentation
 * reconciler. Based on its specific knowledge about the returned object, the
 * configuration might share such objects or compute them according to some
 * rules.
 * </p>
 * <p>
 * Clients should subclass and override just those methods which must be
 * specific to their needs.
 * </p>
 * </DD>
 * </DT>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 28, 2004 6:04:55 PM.</DD>
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
public class APISourceVieweConfiguration
										extends
											SourceViewerConfiguration
{

	/**
	 * Colour provider.
	 * 
	 * @since 1.0
	 */
	private APIColorProvider		colorManager		= null;

	/**
	 * Double click strategy.
	 * 
	 * @since 1.0
	 */
	private APIDoubleClickStrategy	doubleClickStrategy	= null;

	/**
	 * Key word scanner.
	 * 
	 * @since 1.0
	 */
	private APIKeywordScanner		tagScanner			= null;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:05:08 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aColorManager
	 *            the colour manager.
	 */
	public APISourceVieweConfiguration(final APIColorProvider aColorManager)
	{

		setColorManager(aColorManager);
	}

	/**
	 * Returns all configured content types for the given source viewer. This
	 * list tells the caller which content types must be configured for the
	 * given source viewer, i.e. for which content types the given source
	 * viewer's functionalities must be specified.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:09:20 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param sourceViewer
	 *            the source viewer to be configured by this configuration
	 * @return the configured content types for the given viewer
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getConfiguredContentTypes(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public String[] getConfiguredContentTypes(final ISourceViewer sourceViewer)
	{

		return new String[] {IDocument.DEFAULT_CONTENT_TYPE,
			APIPartitionScanner.API_COMMENT, APIPartitionScanner.API_KEYWORD};
	}

	/**
	 * Returns the content assistant ready to be used with the given source
	 * viewer. This implementation always returns <code>null</code>.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:09:53 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param sourceViewer
	 *            the source viewer to be configured by this configuration.
	 * @return a content assistant or <code>null</code> if content assist
	 *         should not be supported.
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getContentAssistant(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IContentAssistant getContentAssistant(
													final ISourceViewer sourceViewer)
	{

		final ContentAssistant assistant = new ContentAssistant();
		assistant.setContentAssistProcessor(new APICompletionProcessor(),
			IDocument.DEFAULT_CONTENT_TYPE);
		assistant.enableAutoActivation(true);
		assistant.setAutoActivationDelay(500);
		assistant
			.setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);
		assistant
			.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
		return assistant;
	}

	/**
	 * Returns the double-click strategy ready to be used in this viewer when
	 * double clicking onto text of the given content type. This implementation
	 * always returns a new instance of
	 * <code>DefaultTextDoubleClickStrategy</code>.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:11:40 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param sourceViewer
	 *            the source viewer to be configured by this configuration
	 * @param contentType
	 *            the content type for which the strategy is applicable
	 * @return a double-click strategy or <code>null</code> if double clicking
	 *         should not be supported
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getDoubleClickStrategy(org.eclipse.jface.text.source.ISourceViewer,
	 *      java.lang.String)
	 */
	@Override
	public ITextDoubleClickStrategy getDoubleClickStrategy(
															final ISourceViewer sourceViewer,
															final String contentType)
	{

		if(getDoubleClickStrategy() == null)
		{
			setDoubleClickStrategy(new APIDoubleClickStrategy());
		}
		return getDoubleClickStrategy();
	}

	/**
	 * Returns the presentation reconciler ready to be used with the given
	 * source viewer. This implementation always returns <code>null</code>.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:12:34 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param sourceViewer
	 *            the source viewer
	 * @return the presentation reconciler or <code>null</code> if
	 *         presentation reconciling should not be supported
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getPresentationReconciler(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IPresentationReconciler getPresentationReconciler(
																final ISourceViewer sourceViewer)
	{

		final PresentationReconciler reconciler = new PresentationReconciler();
		final DefaultDamagerRepairer damagerRepairer = new DefaultDamagerRepairer(
			getTagScanner());
		reconciler.setDamager(damagerRepairer, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(damagerRepairer, IDocument.DEFAULT_CONTENT_TYPE);
		APINonRuleBasedDamagerRepairer nonRuleBasedDamagerRepairer = new APINonRuleBasedDamagerRepairer(
			new TextAttribute(getColorManager().getColor(
				APIColorProvider.SINGLE_LINE_COMMENT)));
		reconciler.setDamager(nonRuleBasedDamagerRepairer,
			APIPartitionScanner.API_COMMENT);
		reconciler.setRepairer(nonRuleBasedDamagerRepairer,
			APIPartitionScanner.API_COMMENT);
		return reconciler;
	}

	/**
	 * Get the colour manager.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:07:57 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the colorManager
	 */
	protected APIColorProvider getColorManager()
	{

		return this.colorManager;
	}

	/**
	 * Get the double click strategy.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:07:57 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the doubleClickStrategy
	 */
	protected APIDoubleClickStrategy getDoubleClickStrategy()
	{

		return this.doubleClickStrategy;
	}

	/**
	 * Get the key word scanner.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:15:11 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return keyword scanner.
	 */
	protected APIKeywordScanner getTagScanner()
	{

		if(this.tagScanner == null)
		{
			setTagScanner(new APIKeywordScanner(getColorManager()));
			/*
			 * tagScanner.setDefaultReturnToken(new Token(new
			 * TextAttribute(colorManager.getColor(APIColorProvider.DEFAULT))));
			 */
		}
		return this.tagScanner;
	}

	/**
	 * Set the colour manager.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:07:57 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aColorManager
	 *            The colorManager to set
	 */
	protected void setColorManager(final APIColorProvider aColorManager)
	{

		this.colorManager = aColorManager;
	}

	/**
	 * Set the double click strategy.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:07:57 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDoubleClickStrategy
	 *            The doubleClickStrategy to set
	 */
	protected void setDoubleClickStrategy(
											final APIDoubleClickStrategy aDoubleClickStrategy)
	{

		this.doubleClickStrategy = aDoubleClickStrategy;
	}

	/**
	 * Set the key word scanner.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:07:57 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aTagScanner
	 *            The tagScanner to set
	 */
	protected void setTagScanner(final APIKeywordScanner aTagScanner)
	{

		this.tagScanner = aTagScanner;
	}
}
