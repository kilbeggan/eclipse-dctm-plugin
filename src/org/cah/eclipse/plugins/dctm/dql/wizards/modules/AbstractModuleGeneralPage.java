
/*-
 * $Log: AbstractModuleGeneralPage.java,v $
 * Revision 1.1  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.wizards.modules;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
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
public class AbstractModuleGeneralPage
										extends
											WizardPage
{

	/**
	 * <H4>Listener for class selection.</H4>
	 * <DL>
	 * <DT><B>Description :</B>
	 * <DD> Enable the next button when a class is selected. </DD>
	 * </DT>
	 * <DT><B>Copyright :</B>
	 * <DD>(c) 2005 Mad Cook</DD>
	 * </DT>
	 * <DT><B>Created :</B>
	 * <DD>04-Dec-2005 21:55:45.</DD>
	 * </DT>
	 * </DL>
	 * <p>
	 * This program is free software; you can redistribute it and/or modify it
	 * under the terms of the GNU General Public License as published by the
	 * Free Software Foundation; either version 2 of the License, or (at your
	 * option) any later version.
	 * </p>
	 * <p>
	 * This program is distributed in the hope that it will be useful, but
	 * WITHOUT ANY WARRANTY; without even the implied warranty of
	 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the <a
	 * href="http://www.gnu.org/licenses/gpl.html">GNU General Public License</a>
	 * for more details.
	 * </p>
	 * 
	 * @author Mad Cook account: dmadmin
	 * @version 3.0.0
	 * @since 3.0.0
	 */
	class SelectionListener
							extends
								Object
										implements
											Listener
	{

		/**
		 * Sole constructor.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 04-Dec-2005 21:56:20</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 */
		SelectionListener()
		{

			super();
		}

		/**
		 * Handle the selection event.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 04-Dec-2005 21:56:36</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param selectionEvent
		 *            the selection event.
		 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
		 */
		public void handleEvent(final Event selectionEvent)
		{

			setClassName(getClassNameCombo().getText());
			if((getClassName() != null) && (getClassName().length() > 1))
			{
				setPageComplete(true);
			}
		}
	}

	/**
	 * The BOF version text box. 04-Dec-2005 <code>bofVersion</code>
	 * 
	 * @since 3.0.0
	 */
	private Text								bofVersion					= null;

	/**
	 * Name of the class that is populated during the select event
	 * <code>className</code>. 04-Dec-2005.
	 * 
	 * @since 3.0.0
	 */
	private String								className					= null;

	/**
	 * The class name combo box <code>classNameCombo</code> 04-Dec-2005.
	 * 
	 * @since 3.0.0
	 */
	private Combo								classNameCombo				= null;

	/**
	 * Minimun DFC version text box <code>dfcVersion</code> 04-Dec-2005.
	 * 
	 * @since 3.0.0
	 */
	private Text								dfcVersion					= null;

	/**
	 * Interface list <code>interfaceJARsList</code> 04-Dec-2005.
	 * 
	 * @since 3.0.0
	 */
	private List								interfaceJARsList			= null;

	/**
	 * Java documentation zip file <code>javaDoc</code> 04-Dec-2005.
	 * 
	 * @since 3.0.0
	 */
	private Text								javaDoc						= null;

	/**
	 * Java virtual machine version <code>jvmVersion</code> 04-Dec-2005.
	 * 
	 * @since 3.0.0
	 */
	private Text								jvmVersion					= null;

	/**
	 * The abstract module implementation page
	 * <code>moduleImplementationPage</code> 04-Dec-2005.
	 * 
	 * @since 3.0.0
	 */
	private AbstractModuleImplementationPage	moduleImplementationPage	= null;

	/**
	 * New module page with a name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29-Nov-2005 08:43:28</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param pageName
	 *            name of the page.
	 */
	public AbstractModuleGeneralPage(final String pageName)
	{

		super(pageName);
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
	 * @since 2.0.0
	 * @param pageName
	 *            name of the page.
	 * @param title
	 *            title of the page.
	 * @param titleImage
	 *            title image of th page.
	 */
	public AbstractModuleGeneralPage(final String pageName, final String title,
										final ImageDescriptor titleImage)
	{

		super(pageName, title, titleImage);
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
	 * @since 2.0.0
	 * @param parent
	 *            the parent control.
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(final Composite parent)
	{

		final Composite container = new Composite(parent, SWT.NULL);
		final GridLayout singleColumnLayout = new GridLayout(1, false);
		container.setLayout(singleColumnLayout);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		final Group mainGroup = new Group(container, SWT.SHADOW_ETCHED_IN);
		mainGroup.setLayout(singleColumnLayout);
		mainGroup.setLayoutData(new GridData(GridData.FILL_BOTH));

		final Group codeGroup = new Group(mainGroup, SWT.SHADOW_ETCHED_IN);
		final GridLayout twoColumnLayout = new GridLayout(2, false);
		codeGroup.setLayout(twoColumnLayout);
		codeGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		codeGroup.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleGeneralPage.GENERAL_INFO")); //$NON-NLS-1$

		Label label = new Label(codeGroup, SWT.NULL);
		label.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleGeneralPage.CLASS_NAME")); //$NON-NLS-1$
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);

		setClassNameCombo(new Combo(codeGroup, SWT.BORDER));
		getClassNameCombo().setLayoutData(
			new GridData(GridData.FILL_HORIZONTAL));
		getClassNameCombo().addListener(SWT.Selection, new SelectionListener());

		label = new Label(codeGroup, SWT.NULL);
		label.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleGeneralPage.BOF_VERSION")); //$NON-NLS-1$
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);

		setBofVersion(new Text(codeGroup, SWT.BORDER));
		getBofVersion().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		label = new Label(codeGroup, SWT.NULL);
		label.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleGeneralPage.INTERFACES")); //$NON-NLS-1$
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);

		setInterfaceJARsList(new List(codeGroup, SWT.BORDER | SWT.H_SCROLL
			| SWT.V_SCROLL | SWT.SINGLE));
		getInterfaceJARsList().setLayoutData(new GridData(GridData.FILL_BOTH));

		label = new Label(codeGroup, SWT.NULL);
		label.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleGeneralPage.MIN_DFC_VERSION")); //$NON-NLS-1$
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);

		setDfcVersion(new Text(codeGroup, SWT.BORDER));
		getDfcVersion().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		label = new Label(codeGroup, SWT.NULL);
		label.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleGeneralPage.MIN_JVM_VERSION")); //$NON-NLS-1$
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);

		setJvmVersion(new Text(codeGroup, SWT.BORDER));
		getJvmVersion().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Group javaDocGroup = new Group(mainGroup, SWT.SHADOW_ETCHED_IN);
		javaDocGroup.setLayout(twoColumnLayout);
		javaDocGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		javaDocGroup.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleGeneralPage.JAVA_DOC_ZIP")); //$NON-NLS-1$

		label = new Label(javaDocGroup, SWT.NULL);
		label.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleGeneralPage.JAVA_DOC")); //$NON-NLS-1$
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);

		setJavaDoc(new Text(javaDocGroup, SWT.BORDER));
		getJavaDoc().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		getJavaDoc().setEditable(false);

		label = new Label(javaDocGroup, SWT.NULL);
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Composite buttonContainer = new Composite(javaDocGroup, SWT.NULL);
		buttonContainer.setLayout(new GridLayout(3, false));
		buttonContainer.setLayoutData(new GridData(GridData.FILL_BOTH));

		final FileDialog fileDialog = new FileDialog(getContainer().getShell(),
			SWT.OPEN);
		fileDialog.setFilterExtensions(new String[] {
			AbstractModuleImplementationPage
				.getString("AbstractModuleImplementationPage.EXTENSION_JAR"), //$NON-NLS-1$
			AbstractModuleImplementationPage
				.getString("AbstractModuleImplementationPage.EXTENSION_ZIP")}); //$NON-NLS-1$

		Button button = new Button(buttonContainer, SWT.PUSH);
		button.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleImplementationPage.FILESYSTEM_ADD")); //$NON-NLS-1$
		button.addListener(SWT.Selection,
			getModuleImplementationPage().new FileSystemButtonListener(
				getJavaDoc(), fileDialog));

		button = new Button(buttonContainer, SWT.PUSH);
		button.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleImplementationPage.DOCBASE_ADD")); //$NON-NLS-1$
		button
			.addListener(
				SWT.Selection,
				getModuleImplementationPage().new DocbaseButtonListener(
					getJavaDoc(),
					AbstractModuleImplementationPage.DocbaseButtonListener.IMPLEMENTATION));

		button = new Button(buttonContainer, SWT.PUSH);
		button.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleImplementationPage.REMOVE")); //$NON-NLS-1$
		button
			.addListener(SWT.Selection,
				getModuleImplementationPage().new RemoveButtonListener(
					getJavaDoc()));

		setPageComplete(false);

		setControl(container);
	}

	/**
	 * Get the module implementation page.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:02:43</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the module implementation page.
	 */
	public AbstractModuleImplementationPage getModuleImplementationPage()
	{

		return this.moduleImplementationPage;
	}

	/**
	 * Set a module implementation page.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 21:06:51</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aModuleImplementationPage
	 *            the module implementation page.
	 */
	public void setModuleImplementationPage(
											final AbstractModuleImplementationPage aModuleImplementationPage)
	{

		this.moduleImplementationPage = aModuleImplementationPage;
	}

	/**
	 * Get the bof version.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:03:13</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the bof version.
	 */
	protected Text getBofVersion()
	{

		return this.bofVersion;
	}

	/**
	 * Get the class name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:03:49</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the class name.
	 */
	protected String getClassName()
	{

		return this.className;
	}

	/**
	 * Get the class name combo box.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:04:28</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the class name combo box.
	 */
	protected Combo getClassNameCombo()
	{

		return this.classNameCombo;
	}

	/**
	 * Get the DFC version.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:05:09</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the DFC version.
	 */
	protected Text getDfcVersion()
	{

		return this.dfcVersion;
	}

	/**
	 * Get the interface jar list.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 03-Dec-2005 21:16:20</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the interface jar list.
	 */
	protected List getInterfaceJARsList()
	{

		return this.interfaceJARsList;
	}

	/**
	 * Get the java documentation text box.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:06:29</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the java documentation text box.
	 */
	protected Text getJavaDoc()
	{

		return this.javaDoc;
	}

	/**
	 * Get the java virtual machine minimum version text box.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:07:15</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the java virtual machine minimum version text box.
	 */
	protected Text getJvmVersion()
	{

		return this.jvmVersion;
	}

	/**
	 * Set the bof version.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:03:33</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aBofVersion
	 *            the bof version.
	 */
	protected void setBofVersion(final Text aBofVersion)
	{

		this.bofVersion = aBofVersion;
	}

	/**
	 * Set the class name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:04:10</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aClassName
	 *            the class name.
	 */
	protected void setClassName(final String aClassName)
	{

		this.className = aClassName;
	}

	/**
	 * Set the class name combo box.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:04:51</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aClassNameCombo
	 *            the class name combo box.
	 */
	protected void setClassNameCombo(final Combo aClassNameCombo)
	{

		this.classNameCombo = aClassNameCombo;
	}

	/**
	 * Set the DFC version.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:05:29</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aDfcVersion
	 *            the DFC version.
	 */
	protected void setDfcVersion(final Text aDfcVersion)
	{

		this.dfcVersion = aDfcVersion;
	}

	/**
	 * Set the interface jar list.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 03-Dec-2005 21:15:25</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param anInterfaceJARsList
	 *            the interface jar list.
	 */
	protected void setInterfaceJARsList(final List anInterfaceJARsList)
	{

		this.interfaceJARsList = anInterfaceJARsList;
	}

	/**
	 * Set the java documentation text box.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:06:59</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aJavaDoc
	 *            the java documentation text box.
	 */
	protected void setJavaDoc(final Text aJavaDoc)
	{

		this.javaDoc = aJavaDoc;
	}

	/**
	 * Set the java virtual machine minimum version text box.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:07:50</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aJvmVersion
	 *            the java virtual machine minimum version text box.
	 */
	protected void setJvmVersion(final Text aJvmVersion)
	{

		this.jvmVersion = aJvmVersion;
	}

}
