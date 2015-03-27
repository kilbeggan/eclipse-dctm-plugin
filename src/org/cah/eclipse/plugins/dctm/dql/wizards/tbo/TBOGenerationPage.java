/*-
 * $Log: TBOGenerationPage.java,v $
 * Revision 1.2  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.1  2005/12/04 20:32:16  madcook
 * Version 3.0.0 work started and moved wizards to their own packages.
 *
 * Revision 1.2  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.1  2005/04/01 11:04:23  harpechr
 * The create BOF / TBO interfaces and classes functionality.
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

package org.cah.eclipse.plugins.dctm.dql.wizards.tbo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

import org.cah.dctm.bof.tbo.GenerateTBOs;
import org.cah.eclipse.plugins.dctm.dql.DCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.wizards.listeners.FolderSelectionListener;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
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
public class TBOGenerationPage
								extends
									WizardPage
{

	/**
	 * Class and interface prefix.
	 * 
	 * @since 2.0.0
	 */
	private Text	classPrefix						= null;

	/**
	 * Copyinght holder value.
	 * 
	 * @since 2.0.0
	 */
	private Text	copyrightValue					= null;

	/**
	 * Create class files in adition to interface files.
	 * 
	 * @since 2.0.0
	 */
	private Button	createClasses					= null;

	/**
	 * Create custom attributes.
	 * 
	 * @since 2.0.0
	 */
	private Button	createCustomAttributeMethods	= null;

	/**
	 * Create package structure.
	 * 
	 * @since 2.0.0
	 */
	private Button	createPackages					= null;

	/**
	 * Debug the class creation.
	 * 
	 * @since 2.0.0
	 */
	private Button	debugCreation					= null;

	/**
	 * The DFC shared folder.
	 * 
	 * @since 2.0.0
	 */
	private Text	dFCShared						= null;

	/**
	 * Target folder name.
	 * 
	 * @since 2.0.0
	 */
	private Text	folderName						= null;

	/**
	 * Inherit TBO's from existing Documentum DFC classes.
	 * 
	 * @since 2.0.0
	 */
	private Button	inheritFromDFC					= null;

	/**
	 * Password text box.
	 * 
	 * @since 2.0.0
	 */
	private Text	packageName						= null;

	/**
	 * Select the target folder button.
	 * 
	 * @since 2.0.0
	 */
	private Button	selectFolder					= null;

	/**
	 * The since value.
	 * 
	 * @since 2.0.0
	 */
	private Text	sinceValue						= null;

	/**
	 * Use threads when creating.
	 * 
	 * @since 2.0.0
	 */
	private Button	useThreads						= null;

	/**
	 * The version string.
	 * 
	 * @since 2.0.0
	 */
	private Text	versionValue					= null;

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
	 * @since 2.0.0
	 * @param pageName
	 *            name of the page.
	 */
	public TBOGenerationPage(final String pageName)
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
	public TBOGenerationPage(final String pageName, final String title,
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

		final GenerateTBOs generateTBOs = new GenerateTBOs();
		final Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;

		Label label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_CLASS_PREFIX));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);
		setClassPrefix(new Text(container, SWT.BORDER | SWT.SINGLE));
		getClassPrefix().setText(generateTBOs.getPrefix());
		getClassPrefix().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label = new Label(container, SWT.NULL);
		label.setText(""); //$NON-NLS-1$

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_COPYRIGHT_VALUE));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);
		setCopyrightValue(new Text(container, SWT.BORDER | SWT.SINGLE));
		getCopyrightValue().setText(generateTBOs.getCopyright());
		getCopyrightValue().setLayoutData(
			new GridData(GridData.FILL_HORIZONTAL));
		label = new Label(container, SWT.NULL);
		label.setText(""); //$NON-NLS-1$

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_CREATE_CLASSES));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);
		setCreateClasses(new Button(container, SWT.CHECK | SWT.RIGHT));
		getCreateClasses().setLayoutData(
			new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		getCreateClasses().setSelection(generateTBOs.isClass());
		label = new Label(container, SWT.NULL);
		label.setText(""); //$NON-NLS-1$

		label = new Label(container, SWT.NULL);
		label
			.setText(DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_CREATE_CUSTOM_ATTRIBUTE_METHODS));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);
		setCreateCustomAttributeMethods(new Button(container, SWT.CHECK
			| SWT.RIGHT));
		getCreateCustomAttributeMethods().setLayoutData(
			new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		getCreateCustomAttributeMethods().setSelection(
			generateTBOs.isCustomAttributeMethods());
		label = new Label(container, SWT.NULL);
		label.setText(""); //$NON-NLS-1$

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_CREATE_PACKAGES));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);
		setCreatePackages(new Button(container, SWT.CHECK | SWT.RIGHT));
		getCreatePackages().setLayoutData(
			new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		getCreatePackages().setSelection(generateTBOs.isDeepPackages());
		label = new Label(container, SWT.NULL);
		label.setText(""); //$NON-NLS-1$

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_DEBUG_CREATION));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);
		setDebugCreation(new Button(container, SWT.CHECK | SWT.RIGHT));
		getDebugCreation().setLayoutData(
			new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		getDebugCreation().setSelection(generateTBOs.isDebug());
		label = new Label(container, SWT.NULL);
		label.setText(""); //$NON-NLS-1$

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_TARGET_FOLDER));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);
		setFolderName(new Text(container, SWT.BORDER | SWT.SINGLE));
		getFolderName().setText(generateTBOs.getFolder().getAbsolutePath());
		getFolderName().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		setSelectFolder(new Button(container, SWT.PUSH | SWT.RIGHT));
		getSelectFolder().setLayoutData(
			new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		getSelectFolder().setText("Select"); //$NON-NLS-1$
		getSelectFolder().addSelectionListener(
			new FolderSelectionListener(getFolderName()));

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_INHERIT_FROM_DFC));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);
		setInheritFromDFC(new Button(container, SWT.CHECK | SWT.RIGHT));
		getInheritFromDFC().setLayoutData(
			new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		getInheritFromDFC().setSelection(generateTBOs.getInherit());
		label = new Label(container, SWT.NULL);
		label.setText(""); //$NON-NLS-1$

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_PACKAGE_NAME));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);
		setPackageName(new Text(container, SWT.BORDER | SWT.SINGLE));
		getPackageName().setText(generateTBOs.getPackage());
		getPackageName().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label = new Label(container, SWT.NULL);
		label.setText(""); //$NON-NLS-1$

		label = new Label(container, SWT.NULL);
		label
			.setText(DCTMPlugin.getResourceString(IDCTMPlugin.MSG_SINCE_VALUE));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);
		setSinceValue(new Text(container, SWT.BORDER | SWT.SINGLE));
		getSinceValue().setText(generateTBOs.getSince());
		getSinceValue().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label = new Label(container, SWT.NULL);
		label.setText(""); //$NON-NLS-1$

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_USER_THREADS));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);
		setUseThreads(new Button(container, SWT.CHECK | SWT.RIGHT));
		getUseThreads().setLayoutData(
			new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		getUseThreads().setSelection(generateTBOs.isThread());
		label = new Label(container, SWT.NULL);
		label.setText(""); //$NON-NLS-1$

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_VERSION_VALUE));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);
		setVersionValue(new Text(container, SWT.BORDER | SWT.SINGLE));
		getVersionValue().setText(generateTBOs.getVersion());
		getVersionValue().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label = new Label(container, SWT.NULL);
		label.setText(""); //$NON-NLS-1$

		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin.getResourceString(IDCTMPlugin.MSG_DFC_SHARED));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.RIGHT);
		setDFCShared(new Text(container, SWT.BORDER | SWT.SINGLE));
		getDFCShared().setText(File.listRoots()[0].getAbsolutePath());
		getDFCShared().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		setSelectFolder(new Button(container, SWT.PUSH | SWT.RIGHT));
		getSelectFolder().setLayoutData(
			new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		getSelectFolder().setText("Select"); //$NON-NLS-1$
		getSelectFolder().addSelectionListener(
			new FolderSelectionListener(getDFCShared()));

		setControl(container);

		setPageComplete(true);
	}

	/**
	 * Get the target folder.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the folderName
	 */
	public Text getFolderName()
	{

		return this.folderName;
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
	 * @since 2.0.0
	 * @return true if everything goes fine.
	 */
	public boolean performFinish()
	{

		final Vector<String> arguments = new Vector<String>();
		if(addTextArgument(arguments, GenerateTBOs.PREFIX[0], getClassPrefix())
			&& addTextArgument(arguments, GenerateTBOs.COPYRIGHT[0],
				getCopyrightValue())
			&& addTextArgument(arguments, GenerateTBOs.FOLDER[0],
				getFolderName())
			&& addTextArgument(arguments, GenerateTBOs.PACKAGE[0],
				getPackageName())
			&& addTextArgument(arguments, GenerateTBOs.SINCE[0],
				getSinceValue())
			&& addTextArgument(arguments, GenerateTBOs.VERSION[0],
				getVersionValue()))
		{
			arguments.addElement(GenerateTBOs.CLASSES[0]);
			arguments.addElement(String.valueOf(getCreateClasses()
				.getSelection()));
			arguments.addElement(GenerateTBOs.METHODS[0]);
			arguments.addElement(String
				.valueOf(getCreateCustomAttributeMethods().getSelection()));
			arguments.addElement(GenerateTBOs.DEEP[0]);
			arguments.addElement(String.valueOf(getCreatePackages()
				.getSelection()));
			arguments.addElement(GenerateTBOs.DEBUG[0]);
			arguments.addElement(String.valueOf(getDebugCreation()
				.getSelection()));
			arguments.addElement(GenerateTBOs.INHERIT[0]);
			arguments.addElement(String.valueOf(getInheritFromDFC()
				.getSelection()));
			arguments.addElement(GenerateTBOs.THREAD[0]);
			arguments
				.addElement(String.valueOf(getUseThreads().getSelection()));
			arguments.addElement(GenerateTBOs.DOCBASE[0]);
			arguments.addElement(ConnectionView.getInstance()
				.getSelectedConnection().getDocbase());
			arguments.addElement(GenerateTBOs.PASSWORD[0]);
			arguments.addElement(ConnectionView.getInstance()
				.getSelectedConnection().getPassword());
			arguments.addElement(GenerateTBOs.USER[0]);
			arguments.addElement(ConnectionView.getInstance()
				.getSelectedConnection().getUserName());
			if((ConnectionView.getInstance().getSelectedConnection()
				.getDomain() != null)
				&& (!ConnectionView.getInstance().getSelectedConnection()
					.getDomain().equals(""))) //$NON-NLS-1$
			{
				arguments.addElement(GenerateTBOs.DOMAIN[0]);
				arguments.addElement(ConnectionView.getInstance()
					.getSelectedConnection().getDomain());
			}
			final StringBuffer startCommand = new StringBuffer();
			final String[] argumentsArray = new String[arguments.size() + 5];
			argumentsArray[0] = System.getProperty("java.home") + "\\bin\\java.exe"; //$NON-NLS-1$ //$NON-NLS-2$
			startCommand.append(argumentsArray[0]).append(' ');
			argumentsArray[1] = "-cp"; //$NON-NLS-1$
			startCommand.append(argumentsArray[1]).append(' ');
			final StringBuffer classPath = new StringBuffer();
			classPath.append('"');
			final File[] sharedFiles = (new File(getDFCShared().getText()))
				.listFiles();
			for(int fileIndex = 0; fileIndex < sharedFiles.length; fileIndex++)
			{
				final String absolutePath = sharedFiles[fileIndex]
					.getAbsolutePath();
				if((absolutePath.endsWith(".jar")) || (absolutePath.endsWith(".zip"))) //$NON-NLS-1$ //$NON-NLS-2$
				{
					classPath.append(absolutePath).append(
						File.pathSeparatorChar);
				}
			}
			try
			{
				final URL pluginUrl = Platform.resolve(Platform.find(Platform
					.getBundle(DCTMPlugin.PLUGIN_ID), new Path("/"))); //$NON-NLS-1$
				final String path = pluginUrl.getFile();
				if((path != null) && (path.length() > 1))
				{
					final File dqlJar = new File(path.substring(1), "dql.jar"); //$NON-NLS-1$
					classPath.append(dqlJar.getAbsolutePath()).append(
						File.pathSeparatorChar);
					/* Just to support debug. */
					final File classesFolder = new File(dqlJar.getParentFile(),
						"classes"); //$NON-NLS-1$
					classPath.append(classesFolder.getAbsolutePath()).append(
						File.pathSeparatorChar);
				}
			} catch(IOException ioex)
			{
				System.out.println(ioex.getMessage());
			}
			classPath.append(System.getProperty("java.class.path")); //$NON-NLS-1$
			classPath.append('"');
			argumentsArray[2] = classPath.toString();
			startCommand.append(argumentsArray[2]).append(' ');
			argumentsArray[3] = "-Ddfc.properties.file=\"" + System.getProperty("dfc.properties.file") + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			startCommand.append(argumentsArray[3]).append(' ');
			argumentsArray[4] = GenerateTBOs.class.getName();
			startCommand.append(argumentsArray[4]).append(' ');

			for(int argumentIndex = 0; argumentIndex < arguments.size(); argumentIndex++)
			{
				String previous = ""; //$NON-NLS-1$
				if(argumentIndex > 0)
				{
					previous = arguments.get(argumentIndex - 1);
				}
				final String argument = arguments.get(argumentIndex);
				argumentsArray[argumentIndex + 5] = argument;
				if((previous.equals(GenerateTBOs.PASSWORD[0]))
					|| (previous.equals(GenerateTBOs.PASSWORD[1])))
				{
					startCommand.append("******** "); //$NON-NLS-1$
				} else
				{
					startCommand.append(argument).append(' ');
				}
			}
			try
			{
				MessageView.getInstance().addMessage(startCommand.toString());
				Runtime.getRuntime().exec(argumentsArray);
			} catch(IOException e)
			{
				System.out.println(e.getMessage());
			}
			return true;
		}
		return false;
	}

	/**
	 * Get the class prefix.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the classPrefix
	 */
	protected Text getClassPrefix()
	{

		return this.classPrefix;
	}

	/**
	 * Get the copyright value.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the copyrightValue
	 */
	protected Text getCopyrightValue()
	{

		return this.copyrightValue;
	}

	/**
	 * Get the create class.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the createClasses
	 */
	protected Button getCreateClasses()
	{

		return this.createClasses;
	}

	/**
	 * Get the create custom attribute methos.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the createCustomAttributeMethods
	 */
	protected Button getCreateCustomAttributeMethods()
	{

		return this.createCustomAttributeMethods;
	}

	/**
	 * Get the create package.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the createPackages
	 */
	protected Button getCreatePackages()
	{

		return this.createPackages;
	}

	/**
	 * Get the debug creation prosess.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the debugCreation
	 */
	protected Button getDebugCreation()
	{

		return this.debugCreation;
	}

	/**
	 * Get the inherit from DFC.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the inheritFromDFC
	 */
	protected Button getInheritFromDFC()
	{

		return this.inheritFromDFC;
	}

	/**
	 * Get package name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the packageName
	 */
	protected Text getPackageName()
	{

		return this.packageName;
	}

	/**
	 * Get the select folder button.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the selectFolder
	 */

	protected Button getSelectFolder()
	{

		return this.selectFolder;
	}

	/**
	 * Get the since value.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the sinceValue
	 */
	protected Text getSinceValue()
	{

		return this.sinceValue;
	}

	/**
	 * Get the use threads.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the useThreads
	 */
	protected Button getUseThreads()
	{

		return this.useThreads;
	}

	/**
	 * Get version value.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return Returns the versionValue
	 */
	protected Text getVersionValue()
	{

		return this.versionValue;
	}

	/**
	 * Set the class prefix.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aClassPrefix
	 *            The classPrefix to set
	 */
	protected void setClassPrefix(final Text aClassPrefix)
	{

		this.classPrefix = aClassPrefix;
	}

	/**
	 * Set the copyright value.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aCopyrightValue
	 *            The copyrightValue to set
	 */

	protected void setCopyrightValue(final Text aCopyrightValue)
	{

		this.copyrightValue = aCopyrightValue;
	}

	/**
	 * Set the create class.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aCreateClasses
	 *            The createClasses to set
	 */
	protected void setCreateClasses(final Button aCreateClasses)
	{

		this.createClasses = aCreateClasses;
	}

	/**
	 * Set the create custom attribute methos.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aCreateCustomAttributeMethods
	 *            The createCustomAttributeMethods to set
	 */
	protected void setCreateCustomAttributeMethods(
													final Button aCreateCustomAttributeMethods)
	{

		this.createCustomAttributeMethods = aCreateCustomAttributeMethods;
	}

	/**
	 * Set the create package.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aCreatePackages
	 *            The createPackages to set
	 */
	protected void setCreatePackages(final Button aCreatePackages)
	{

		this.createPackages = aCreatePackages;
	}

	/**
	 * Set the debug creation prosess.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aDebugCreation
	 *            The debugCreation to set
	 */
	protected void setDebugCreation(final Button aDebugCreation)
	{

		this.debugCreation = aDebugCreation;
	}

	/**
	 * Set the target folder.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aFolderName
	 *            The folderName to set
	 */

	protected void setFolderName(final Text aFolderName)
	{

		this.folderName = aFolderName;
	}

	/**
	 * Set the inherit from DFC.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param anInheritFromDFC
	 *            The inheritFromDFC to set
	 */

	protected void setInheritFromDFC(final Button anInheritFromDFC)
	{

		this.inheritFromDFC = anInheritFromDFC;
	}

	/**
	 * Set package name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aPackageName
	 *            The packageName to set
	 */
	protected void setPackageName(final Text aPackageName)
	{

		this.packageName = aPackageName;
	}

	/**
	 * Set the select folder button.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aSelectFolder
	 *            The selectFolder to set
	 */

	protected void setSelectFolder(final Button aSelectFolder)
	{

		this.selectFolder = aSelectFolder;
	}

	/**
	 * Set the since value.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aSinceValue
	 *            The sinceValue to set
	 */
	protected void setSinceValue(final Text aSinceValue)
	{

		this.sinceValue = aSinceValue;
	}

	/**
	 * Set the use threads.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param anUseThreads
	 *            The useThreads to set
	 */

	protected void setUseThreads(final Button anUseThreads)
	{

		this.useThreads = anUseThreads;
	}

	/**
	 * Set version value.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 14:33:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aVersionValue
	 *            The versionValue to set
	 */
	protected void setVersionValue(final Text aVersionValue)
	{

		this.versionValue = aVersionValue;
	}

	/**
	 * Add an argument into the arguments array.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29.3.2005 17:03:22</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param arguments
	 *            the arguments array.
	 * @param argumentName
	 *            the name of the argument.
	 * @param argumentValue
	 *            the user given value for this argument.
	 * @return true if the argument was not empty.
	 */
	private boolean addTextArgument(final Vector<String> arguments,
									final String argumentName,
									final Text argumentValue)
	{

		final String argumentValueString = argumentValue.getText().trim();
		if(argumentValueString.equals("")) //$NON-NLS-1$
		{
			return false;
		}
		arguments.addElement(argumentName);
		arguments.addElement(argumentValueString);
		return true;
	}

	/**
	 * Get the DFC shared folder.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 30-Mar-2005 12:48:22</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return The DFC shared folder.
	 */
	private Text getDFCShared()
	{

		return this.dFCShared;
	}

	/**
	 * Set the DFC shared folder.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 30-Mar-2005 12:47:15</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aDFCShared
	 *            The DFC shared folder.
	 */
	private void setDFCShared(final Text aDFCShared)
	{

		this.dFCShared = aDFCShared;
	}

}
