/*-
 * $Log: MessageView.java,v $
 * Revision 1.9  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.8  2005/12/04 20:29:56  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.7  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.6  2005/04/01 11:28:07  harpechr
 * Reference to a static string changed to an interface.
 *
 * Revision 1.5  2005/03/25 09:18:59  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.4  2005/02/09 13:55:19  harpechr
 * Added previous DQL queries functionality and modified the message view so 
 * that its real time.
 *
 * Revision 1.3  2005/01/24 12:34:57  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:19  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:51  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.views;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.cah.eclipse.plugins.dctm.dql.DCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.IImageCache;
import org.cah.eclipse.plugins.dctm.dql.ImageCache;
import org.cah.eclipse.plugins.dctm.dql.actions.messages.ClearMessagesAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;


/**
 * <H4>Workbench view for all API / DQL statements and messages.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 24, 2004 3:40:32 PM.</DD>
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
public class MessageView
						extends
							ViewPart
{

	/**
	 * Id of the view.
	 * 
	 * @since 1.0
	 */
	public static final String				ID				= MessageView.class
																.getName();

	/**
	 * The views content.
	 * 
	 * @since 1.0
	 */
	public static Text						text			= null;

	/**
	 * single instance of the view.
	 * 
	 * @since 1.0
	 */
	private static MessageView				instance		= null;

	/**
	 * Buffer used when the view hasen't been initialized but messages are
	 * logged.
	 * 
	 * @since 1.0
	 */
	private static final LinkedList<String>	messageBuffer	= new LinkedList<String>();

	/**
	 * synchronization helper.
	 * 
	 * @since 1.0
	 */
	private static Object					SEMAPHORE		= new Object();

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 24, 2004 3:40:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public MessageView()
	{

		super();
		setInstance(this);
	}

	/**
	 * Get the first created instance.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 2:48:59 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return first created instance of this view.
	 */
	public static MessageView getInstance()
	{

		synchronized(MessageView.SEMAPHORE)
		{
			if(MessageView.instance == null)
			{
				new MessageView();
			}
		}
		return MessageView.instance;
	}

	/**
	 * Get the welcome, copyright and license message.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 7, 2004 9:47:54 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the copyright statement.
	 */
	public static String getWelcomemessage()
	{

		return "Simple Documentum DQL/API editor. Copyright (c): Christopher Harper\n\n" //$NON-NLS-1$
			+ "This program is free software; you can redistribute it and/or modify it under the terms of the GNU\n" //$NON-NLS-1$
			+ "General Public License as published by the Free Software Foundation; either version 2 of the\n" //$NON-NLS-1$
			+ "License, or (at your option) any later version.\n\n" //$NON-NLS-1$
			+ "This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without\n" //$NON-NLS-1$
			+ "even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See GNU General\n" //$NON-NLS-1$
			+ "Public License (http://www.gnu.org/licenses/gpl.html) for more details.\n\nEnjoy!\n"; //$NON-NLS-1$
	}

	/**
	 * Get the view text.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 4:10:36 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the text control inside this view.
	 */
	protected static Text getText()
	{

		return MessageView.text;
	}

	/**
	 * Set the first instance.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 4:11:14 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anInstance
	 *            the first instance.
	 */
	protected static void setInstance(final MessageView anInstance)
	{

		MessageView.instance = anInstance;
	}

	/**
	 * Set the view text.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 4:11:27 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param text
	 *            the view text.
	 */
	protected static void setText(final Text text)
	{

		MessageView.text = text;
	}

	/**
	 * Add a message to the view.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 10:30:15 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param message
	 *            the message to add.
	 */
	public void addMessage(final String message)
	{

		StringBuffer tempMessage = new StringBuffer(DateFormat.getTimeInstance(
			DateFormat.SHORT).format(new Date(System.currentTimeMillis())))
			.append(": ").append(message).append(IDCTMPlugin.NEWLINE); //$NON-NLS-1$
		if(getText() != null)
		{
			while(MessageView.messageBuffer.size() > 0)
			{
				getText().setText(
					(MessageView.messageBuffer.removeFirst())
						+ getText().getText());
			}
			getText().setText(tempMessage.toString() + getText().getText());
			getText().update();
			getSite().getPage().activate(getInstance());
		} else
		{
			MessageView.messageBuffer.addLast(message.toString());
			if(MessageView.messageBuffer.size() > 200)
			{
				MessageView.messageBuffer.removeFirst();
			}
		}
	}

	/**
	 * Add Throwables information to the view.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 10:30:25 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param throwable
	 *            the exception to add.
	 */
	public void addMessage(final Throwable throwable)
	{

		addMessage(throwable.getClass().getName() + '.'
			+ throwable.getMessage());
	}

	/**
	 * Clear all messages.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 1:24:00 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public void clearMessages()
	{

		getText().setText(""); //$NON-NLS-1$
	}

	/**
	 * <p>
	 * Initialize the view part and creates the SWT controls for this workbench
	 * part.
	 * </p>
	 * <p>
	 * Clients should not call this method (the workbench calls this method when
	 * it needs to, which may be never).
	 * </p>
	 * <p>
	 * For implementors this is a multi-step process:
	 * <ol>
	 * <li>Create one or more controls within the parent.</li>
	 * <li>Set the parent layout as needed.</li>
	 * <li>Register any global actions with the <code>IActionService</code>.</li>
	 * <li>Register any popup menus with the <code>IActionService</code>.</li>
	 * <li>Register a selection provider with the
	 * <code>ISelectionService</code> (optional).</li>
	 * </ol>
	 * </p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 2:30:49 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aParent
	 *            the parent control
	 * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(final Composite aParent)
	{

		createToolbar();
		setText(new Text(aParent, SWT.BORDER | SWT.MULTI | SWT.WRAP
			| SWT.V_SCROLL | SWT.READ_ONLY));
		getText().setLayoutData(new GridData(GridData.FILL_BOTH));
		final FontData fontData = new FontData("Courier New", 8, SWT.NORMAL); //$NON-NLS-1$
		final Font messageFont = new Font(DCTMPlugin.getInstance()
			.getWorkbench().getDisplay(), fontData);
		getText().setFont(messageFont);
		addMessage(MessageView.getWelcomemessage());
	}

	/**
	 * Set the focust to the text.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 24, 2004 3:40:55 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus()
	{

		getText().setFocus();
	}

	/**
	 * Create the clear messges action button.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 1:15:39 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	protected void createToolbar()
	{

		IToolBarManager toolbarManager = getViewSite().getActionBars()
			.getToolBarManager();
		ClearMessagesAction clearMessagesAction = new ClearMessagesAction();
		clearMessagesAction.setImageDescriptor(ImageCache
			.getImageDescriptor(IImageCache.REMOVE_ICON));
		toolbarManager.add(clearMessagesAction);
	}
}
