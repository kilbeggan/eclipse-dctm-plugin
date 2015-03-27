/*-
 * $Log: ConnectionPage.java,v $
 * Revision 1.2  2005/12/04 22:17:22  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.1  2005/12/04 20:31:44  madcook
 * Version 3.0.0 work started and moved wizards to their own packages.
 *
 * Revision 1.10  2005/11/21 14:53:38  madcook
 * Old 1.4 style code removed.
 *
 * Revision 1.9  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.8  2005/04/01 11:05:11  harpechr
 * Added images to dialogs.
 *
 * Revision 1.7  2005/03/25 09:18:58  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.6  2005/02/22 15:46:29  harpechr
 * Changed the code so that all saved connection information is base 64 encoded 
 * to get round the name restrictions.
 *
 * Revision 1.5  2005/02/10 11:33:38  harpechr
 * Safety commit!
 *
 * Revision 1.4  2005/02/09 14:01:38  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:56  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:17  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:49  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.wizards.connection;

import org.cah.eclipse.plugins.dctm.dql.DCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionModel;
import org.cah.eclipse.plugins.dctm.dql.wizards.listeners.RefreshCheckSelectionListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * <H4>Page providing controls to enter Docbase connection information.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 25, 2004 10:59:32 AM.</DD>
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
public class ConnectionPage
							extends
								WizardPage
{

	/**
	 * The Docbase connection model that holds the actual connection
	 * information.
	 * 
	 * @since 1.0
	 */
	private ConnectionModel	connectionModel			= null;

	/**
	 * Docbase names container.
	 * 
	 * @since 1.0
	 */
	private Combo			docbases				= null;

	/**
	 * Domain text box.
	 * 
	 * @since 1.0
	 */
	private Text			domain					= null;

	/**
	 * Password text box.
	 * 
	 * @since 1.0
	 */
	private Text			password				= null;

	/**
	 * Refresh check box.
	 * 
	 * @since 1.0
	 */
	private Button			refreshDocbasesButton	= null;

	/**
	 * User name text box.
	 * 
	 * @since 1.0
	 */
	private Text			userName				= null;

	/**
	 * New connection page with a name.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 10:59:32 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param pageName
	 *            name of the page.
	 */
	public ConnectionPage(final String pageName)
	{

		super(pageName);
		setConnectionModel(null);
	}

	/**
	 * A new connection page with a name and connection information.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 5:18:38 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param pageName
	 *            name of the connection page.
	 * @param aConnectionModel
	 *            the connection information.
	 */
	public ConnectionPage(final String pageName,
							final ConnectionModel aConnectionModel)
	{

		this(pageName);
		setConnectionModel(aConnectionModel);
	}

	/**
	 * A new connection paeg with name, title and an image.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 10:59:32 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param pageName
	 *            name of the page.
	 * @param title
	 *            title of the page.
	 * @param titleImage
	 *            title image of th page.
	 */
	public ConnectionPage(final String pageName, final String title,
							final ImageDescriptor titleImage)
	{

		super(pageName, title, titleImage);
	}

	/**
	 * A new connection paeg with name, title, image and connection information.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 01-Apr-2005 12:41:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param pageName
	 *            name of the page.
	 * @param title
	 *            title of the page.
	 * @param titleImage
	 *            title image of th page.
	 * @param aConnectionModel
	 *            the connection information.
	 */

	public ConnectionPage(final String pageName, final String title,
							final ImageDescriptor titleImage,
							final ConnectionModel aConnectionModel)
	{

		super(pageName, title, titleImage);
		setConnectionModel(aConnectionModel);
	}

	/**
	 * Initialize all the controls.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 10:59:32 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param parent
	 *            the parent control.
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(final Composite parent)
	{

		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		layout.verticalSpacing = 9;

		Label label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin.getResourceString(IDCTMPlugin.MSG_DOCBASE));
		setDocbases(new Combo(container, SWT.READ_ONLY));
		getDocbases().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		initDocbases();

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin.getResourceString(IDCTMPlugin.MSG_USER_NAME));
		setUserName(new Text(container, SWT.BORDER | SWT.SINGLE));
		getUserName().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin.getResourceString(IDCTMPlugin.MSG_PASSWORD));
		setPassword(new Text(container, SWT.BORDER | SWT.SINGLE));
		getPassword().setEchoChar('*');
		getPassword().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin.getResourceString(IDCTMPlugin.MSG_DOMAIN));
		setDomain(new Text(container, SWT.BORDER | SWT.SINGLE));
		getDomain().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_REFRESH_DOCBASES));
		setRefreshDocbasesButton(new Button(container, SWT.CHECK | SWT.RIGHT));
		getRefreshDocbasesButton().setLayoutData(
			new GridData(GridData.HORIZONTAL_ALIGN_END));
		getRefreshDocbasesButton().addSelectionListener(
			new RefreshCheckSelectionListener(this));

		setControl(container);

		if(getConnectionModel() != null)
		{
			getDocbases().setText(getConnectionModel().getDocbase());
			getUserName().setText(getConnectionModel().getUserName());
			getPassword().setText(getConnectionModel().getPassword());
			getDomain().setText(getConnectionModel().getDomain());
		}
		setPageComplete(true);
	}

	/**
	 * Initialize the Docbase list.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 9:56:35 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public void initDocbases()
	{

		try
		{
			if(ConnectionView.DOCBASE_NAMES.size() == 0)
			{
				ConnectionView.refreshDocbases();
			}
			for(final String docbaseName: ConnectionView.DOCBASE_NAMES)
			{
				getDocbases().add(docbaseName);
			}
			getDocbases().select(0);
		} catch(Exception ex)
		{
			MessageDialog
				.openError(
					getShell(),
					"Failed to return Docbases", "Docbases missing!\n\n" + ex.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * Save the values the user entered.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 5:08:45 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return true if everything goes fine.
	 */
	public boolean performFinish()
	{

		final String docbaseText = getDocbases().getText().trim();
		final String userNameText = getUserName().getText().trim();
		final String passwordText = getPassword().getText(); /*
																 * Don't trim
																 * password!
																 */
		final String domainText = getDomain().getText().trim();
		if((docbaseText.equals("")) || (userNameText.equals("")) || (passwordText.equals(""))) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		{
			MessageDialog.openError(getShell(),
				"Required Fields", "Required field missing"); //$NON-NLS-1$ //$NON-NLS-2$
			return false;
		}
		boolean newFlag = false;
		if(getConnectionModel() == null)
		{
			newFlag = true;
			setConnectionModel(new ConnectionModel(docbaseText));
		}
		getConnectionModel().setDocbase(docbaseText);
		getConnectionModel().setDomain(domainText);
		getConnectionModel().setPassword(passwordText);
		getConnectionModel().setUserName(userNameText);
		if(newFlag)
		{
			ConnectionView.getInstance().addConnection(getConnectionModel());
		} else
		{
			ConnectionView.getInstance().changedConnection();
		}
		return true;
	}

	/**
	 * Ge the connection model
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 5:16:59 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the used connection model.
	 */
	protected ConnectionModel getConnectionModel()
	{

		return this.connectionModel;
	}

	/**
	 * Get the Docbase combo.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 11:24:11 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the docbase control.
	 */
	protected Combo getDocbases()
	{

		return this.docbases;
	}

	/**
	 * Return the domain text control.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 11:38:41 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the domain control.
	 */
	protected Text getDomain()
	{

		return this.domain;
	}

	/**
	 * Return the password text control.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 11:36:04 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the password control.
	 */
	protected Text getPassword()
	{

		return this.password;
	}

	/**
	 * Get the refresh buttot(check box).
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 2:07:19 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the refresh check box.
	 */
	protected Button getRefreshDocbasesButton()
	{

		return this.refreshDocbasesButton;
	}

	/**
	 * Get the user name text control.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 11:32:47 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the user name text control
	 */
	protected Text getUserName()
	{

		return this.userName;
	}

	/**
	 * Set the connection model and populate the controls with the information
	 * that it provides.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 5:17:02 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param connectionModel
	 *            the connection model.
	 */
	protected void setConnectionModel(final ConnectionModel connectionModel)
	{

		this.connectionModel = connectionModel;
		try
		{
			if(getConnectionModel() != null)
			{
				if(getDocbases() != null)
				{
					String docbase = getConnectionModel().getDocbase();
					if(docbase != null)
					{
						for(int docbaseIndex = 0; docbaseIndex < getDocbases()
							.getItemCount(); docbaseIndex++)
						{
							final String option = getDocbases().getItem(
								docbaseIndex);
							if((option != null) && docbase.equals(option))
							{
								getDocbases().select(docbaseIndex);
								docbase = null;
								break;
							}
						}
					}
					if(docbase != null)
					{
						getDocbases().setText(docbase);
					}
				}
				if((getUserName() != null)
					&& (getConnectionModel().getUserName() != null))
				{
					getUserName().setText(getConnectionModel().getUserName());
				}
				if((getPassword() != null)
					&& (getConnectionModel().getPassword() != null))
				{
					getPassword().setText(getConnectionModel().getPassword());
				}
				if((getDomain() != null)
					&& (getConnectionModel().getDomain() != null))
				{
					getDomain().setText(getConnectionModel().getDomain());
				}
			}
		} catch(Exception ex)
		{
			MessageView.getInstance().addMessage(ex);
		}
	}

	/**
	 * Set the Docbases combo.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 11:24:19 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDocbases
	 *            a new Docbases combo.
	 */
	protected void setDocbases(final Combo aDocbases)
	{

		this.docbases = aDocbases;
	}

	/**
	 * Set the domain text.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 11:38:37 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDomain
	 *            a new domain text.
	 */
	protected void setDomain(final Text aDomain)
	{

		this.domain = aDomain;
	}

	/**
	 * Set the password field.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 11:35:59 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aPassword
	 *            new password control
	 */
	protected void setPassword(final Text aPassword)
	{

		this.password = aPassword;

	}

	/**
	 * Set the refresh Docbase list control.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 2:06:10 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aRefreshDocbasesButton
	 *            a new refresh checkbox.
	 */
	protected void setRefreshDocbasesButton(final Button aRefreshDocbasesButton)
	{

		this.refreshDocbasesButton = aRefreshDocbasesButton;
	}

	/**
	 * Set the user name control.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 11:32:39 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aUserName
	 *            a new user name control
	 */
	protected void setUserName(final Text aUserName)
	{

		this.userName = aUserName;

	}
}
