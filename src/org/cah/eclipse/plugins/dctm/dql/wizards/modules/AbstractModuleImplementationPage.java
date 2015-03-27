/*-
 * $Log: AbstractModuleImplementationPage.java,v $
 * Revision 1.1  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.wizards.modules;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;
import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;
import org.cah.dctm.bof.tbo.persistent.sysobject.document.jar.IJar;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionContentProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;


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
public class AbstractModuleImplementationPage
												extends
													WizardPage
{

	/**
	 * <H4>Listener for the filesystem button.</H4>
	 * <DL>
	 * <DT><B>Description :</B>
	 * <DD> Open up a filesystem dialog to select a jar file from the
	 * filesystem. </DD>
	 * </DT>
	 * <DT><B>Copyright :</B>
	 * <DD>(c) 2005 Mad Cook</DD>
	 * </DT>
	 * <DT><B>Created :</B>
	 * <DD>03-Dec-2005 12:42:58.</DD>
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
	public class FileSystemButtonListener
											extends
												Object
														implements
															Listener
	{

		/**
		 * A select file dialog. 03-Dec-2005 <code>selectDialog</code>
		 * 
		 * @since 3.0.0
		 */
		private FileDialog	selectDialog	= null;

		/**
		 * The list containing the selected files. 03-Dec-2005
		 * <code>targetList</code>
		 * 
		 * @since 3.0.0
		 */
		private Control		targetList		= null;

		/**
		 * Sole constructor.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:53:51</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param aTargetList
		 *            the target list.
		 * @param aSelectDialog
		 *            the selection dialog.
		 */
		protected FileSystemButtonListener(final Control aTargetList,
											final FileDialog aSelectDialog)
		{

			setTargetList(aTargetList);
			setSelectDialog(aSelectDialog);
		}

		/**
		 * Handle the button click event.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:56:06</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param selectEvent
		 *            the click event.
		 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
		 */
		public void handleEvent(final Event selectEvent)
		{

			final String fileName = getSelectDialog().open();
			if(fileName != null)
			{

				if(getTargetList() instanceof List)
				{
					final List targetedList = (List) getTargetList();
					if(targetedList.getData() == null)
					{
						targetedList.removeAll();
						targetedList.setData(new Object());
					}

					final Vector<String> items = new Vector<String>(Arrays
						.asList(targetedList.getItems()));
					if(!items.contains(fileName))
					{
						targetedList.add(fileName);
						if(targetedList
							.getData(AbstractModuleImplementationPage.LIST_TYPE)
							.equals(
								AbstractModuleImplementationPage.LIST_TYPE_IMPLEMENTATION))
						{
							populateImplementationClasses();
						} else
						{
							populateInterfaceClasses();
						}
						setPageFlipStatus();
					}
				} else if(getTargetList() instanceof Text)
				{
					((Text) getTargetList()).setText(fileName);
				}
			}
		}

		/**
		 * Get the select file dialog.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:45:58</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @return the select dialog.
		 */
		protected FileDialog getSelectDialog()
		{

			return this.selectDialog;
		}

		/**
		 * Get the target list.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:53:09</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @return the target list.
		 */
		protected Control getTargetList()
		{

			return this.targetList;
		}

		/**
		 * Set the select file dialog.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:46:22</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param aSelectDialog
		 *            the select dialog.
		 */
		protected void setSelectDialog(final FileDialog aSelectDialog)
		{

			this.selectDialog = aSelectDialog;
		}

		/**
		 * Set the target list.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:52:52</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param aTargetList
		 *            the target list.
		 */
		protected void setTargetList(final Control aTargetList)
		{

			this.targetList = aTargetList;
		}

	}

	/**
	 * <H4>Listener for Docbase jar selection.</H4>
	 * <DL>
	 * <DT><B>Description :</B>
	 * <DD> Listener for the select jar from Docbase buttons. </DD>
	 * </DT>
	 * <DT><B>Copyright :</B>
	 * <DD>(c) 2005 Mad Cook</DD>
	 * </DT>
	 * <DT><B>Created :</B>
	 * <DD>02-Dec-2005 18:33:37.</DD>
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
	class DocbaseButtonListener
								extends
									Object
											implements
												Listener
	{

		/**
		 * <H4>Listener to close the Docbases Jar list.</H4>
		 * <DL>
		 * <DT><B>Description :</B>
		 * <DD>Listener that runs on the OK button and adds the selected jar
		 * into the list of selected jars and colses the dialog. If nothing is
		 * selected the dialog is just closed.</DD>
		 * </DT>
		 * <DT><B>Copyright :</B>
		 * <DD>(c) 2005 Mad Cook</DD>
		 * </DT>
		 * <DT><B>Created :</B>
		 * <DD>03-Dec-2005 12:16:34.</DD>
		 * </DT>
		 * </DL>
		 * <p>
		 * This program is free software; you can redistribute it and/or modify
		 * it under the terms of the GNU General Public License as published by
		 * the Free Software Foundation; either version 2 of the License, or (at
		 * your option) any later version.
		 * </p>
		 * <p>
		 * This program is distributed in the hope that it will be useful, but
		 * WITHOUT ANY WARRANTY; without even the implied warranty of
		 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the <a
		 * href="http://www.gnu.org/licenses/gpl.html">GNU General Public
		 * License</a> for more details.
		 * </p>
		 * 
		 * @author Mad Cook account: dmadmin
		 * @version 3.0.0
		 * @since 3.0.0
		 */
		class OKListener
						extends
							Object
									implements
										Listener
		{

			/**
			 * Default public default constructor.
			 * <DL>
			 * <DT><B>Created :</B>
			 * <DD> 03-Dec-2005 12:19:49</DD>
			 * </DT>
			 * <DT><B>Author :</B>
			 * <DD>Mad Cook account : dmadmin</DD>
			 * </DT>
			 * </DL>
			 * 
			 * @since 3.0.0
			 */
			public OKListener()
			{

				super();
			}

			/**
			 * Handle the click event.
			 * <DL>
			 * <DT><B>Created :</B>
			 * <DD> 03-Dec-2005 12:11:59</DD>
			 * </DT>
			 * <DT><B>Author :</B>
			 * <DD>Mad Cook account : dmadmin</DD>
			 * </DT>
			 * </DL>
			 * 
			 * @since 3.0.0
			 * @param okEvent
			 *            the click OK event.
			 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
			 */
			public void handleEvent(final Event okEvent)
			{

				if(getJarList().getSelectionIndex() != -1)
				{
					final String item = getJarList().getItem(
						getJarList().getSelectionIndex());
					if(getTargetList() instanceof List)
					{
						final List targetedList = (List) getTargetList();
						if(getTargetList().getData() == null)
						{

							targetedList.removeAll();
							targetedList.setData(new Object());
						}
						final Vector<String> items = new Vector<String>(Arrays
							.asList(targetedList.getItems()));
						if(!items.contains(item))
						{
							targetedList.add(item);
							if(getTargetList()
								.getData(
									AbstractModuleImplementationPage.LIST_TYPE)
								.equals(
									AbstractModuleImplementationPage.LIST_TYPE_IMPLEMENTATION))
							{
								populateImplementationClasses();
							} else
							{
								populateInterfaceClasses();
							}
							setPageFlipStatus();
						}
					} else if(getTargetList() instanceof Text)
					{
						((Text) getTargetList()).setText(item);
					}
				}
				getDialog().close();
			}
		}

		/**
		 * Inicator of an impementation jar. 02-Dec-2005
		 * <code>IMPLEMENTATION</code>
		 * 
		 * @since 3.0.0
		 */
		public static final int	IMPLEMENTATION	= 2;

		/**
		 * Indicator of an interface jar. 03-Dec-2005 <code>INTERFACE</code>
		 * 
		 * @since 3.0.0
		 */
		public static final int	INTERFACE		= 1;

		/**
		 * The Docbase jar list dialog. 03-Dec-2005 <code>dialog</code>
		 * 
		 * @since 3.0.0
		 */
		private Shell			dialog			= null;

		/**
		 * List of available jars in the Docbase. 03-Dec-2005
		 * <code>jarList</code>
		 * 
		 * @since 3.0.0
		 */
		private List			jarList			= null;

		/**
		 * Type of the jar to select. 03-Dec-2005 <code>jarType</code>
		 * 
		 * @since 3.0.0
		 */
		private int				jarType			= DocbaseButtonListener.IMPLEMENTATION;

		/**
		 * The target list where the selected jar will be placed. 03-Dec-2005
		 * <code>targetList</code>
		 * 
		 * @since 3.0.0
		 */
		private Control			targetList		= null;

		/**
		 * Create a button listener.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:08:52</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param aTargetList
		 *            the list to be populated.
		 * @param aJarType
		 *            type of the jar to select.
		 */
		DocbaseButtonListener(final Control aTargetList, final int aJarType)
		{

			setTargetList(aTargetList);
			setJarType(aJarType);
		}

		/**
		 * Handle the button click event.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:09:44</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param docbaeSelect
		 *            the button select event.
		 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
		 */
		public void handleEvent(final Event docbaeSelect)
		{

			setDialog(new Shell(getContainer().getShell(), SWT.DIALOG_TRIM
				| SWT.APPLICATION_MODAL));

			final GridLayout singleColumnLayout = new GridLayout(1, false);
			getDialog().setLayout(singleColumnLayout);

			final Group mainGroup = new Group(getDialog(), SWT.SHADOW_ETCHED_IN);
			mainGroup.setLayout(singleColumnLayout);
			mainGroup.setLayoutData(new GridData(GridData.FILL_BOTH));

			final Label jarTypeLabel = new Label(mainGroup, SWT.NULL);
			if(getJarType() == DocbaseButtonListener.IMPLEMENTATION)
			{
				jarTypeLabel
					.setText(AbstractModuleImplementationPage
						.getString("AbstractModuleImplementationPage.IMPLEMENTATION_JARS")); //$NON-NLS-1$
			} else
			{
				jarTypeLabel
					.setText(AbstractModuleImplementationPage
						.getString("AbstractModuleImplementationPage.INTERFACE_JARS")); //$NON-NLS-1$
			}
			jarTypeLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			setJarList(new List(mainGroup, SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.SINGLE));
			getJarList().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			populateJarList();

			Button button = new Button(mainGroup, SWT.PUSH);
			button.setText(AbstractModuleImplementationPage
				.getString("AbstractModuleImplementationPage.OK")); //$NON-NLS-1$
			button.addListener(SWT.Selection, new OKListener());

			getDialog().pack();
			getDialog().open();
		}

		/**
		 * Get the docbase jar list dialog.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:30:02</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @return the jar list dialog.
		 */
		protected Shell getDialog()
		{

			return this.dialog;
		}

		/**
		 * Get the list of Docbase jars.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:38:58</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @return the Docbase jar list.
		 */
		protected List getJarList()
		{

			return this.jarList;
		}

		/**
		 * Set the jar type.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:35:13</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @return the jar type.
		 */
		protected int getJarType()
		{

			return this.jarType;
		}

		/**
		 * Get the target list.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:39:34</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @return the target list.
		 */
		protected Control getTargetList()
		{

			return this.targetList;
		}

		/**
		 * Query for all the JAR(s) of the selected type and place them into the
		 * jarlist.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:40:16</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 */
		protected void populateJarList()
		{

			final StringBuffer typeQuery = new StringBuffer("select distinct ") //$NON-NLS-1$
				.append(IPersistentObject.R_OBJECT_ID).append(" , ").append( //$NON-NLS-1$
					ISysObject.OBJECT_NAME).append(" from ").append( //$NON-NLS-1$
					IJar.TYPE_DMC_JAR).append(" where ").append(IJar.JAR_TYPE) //$NON-NLS-1$
				.append(" = ").append(this.jarType).append(" order by 2"); //$NON-NLS-1$ //$NON-NLS-2$
			IDfSession session = null;
			IDfCollection results = null;
			try
			{
				session = ConnectionContentProvider.getInstance()
					.getSelectedConnectionModel().getSession();
				IDfQuery query = new DfQuery();
				query.setDQL(typeQuery.toString());
				MessageView.getInstance().addMessage(
					session.getLoginInfo().getDomain() + '\\'
						+ session.getLoginUserName() + '@'
						+ session.getDocbaseName() + ' ' + query.getDQL());
				for(results = query
					.execute(session, IDfQuery.DF_EXECREAD_QUERY); results
					.next();)
				{
					getJarList().add(
						results.getString(ISysObject.OBJECT_NAME)
							+ " (" //$NON-NLS-1$
							+ results.getString(IPersistentObject.R_OBJECT_ID)
							+ ')');
				}

			} catch(final DfException dex)
			{
				System.out.println(dex.getMessage());
			} finally
			{
				if((results != null)
					&& (results.getState() != IDfCollection.DF_CLOSED_STATE))
				{
					try
					{
						results.close();
					} catch(final DfException swallow)
					{
						System.out.println(swallow.getMessage());
					}
				}
				ConnectionContentProvider.getInstance()
					.getSelectedConnectionModel().releaceConnection(session);
			}
		}

		/**
		 * Set the docbase jar list dialog.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:30:41</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param aDialog
		 *            the docbase jar list dialog.
		 */
		protected void setDialog(final Shell aDialog)
		{

			this.dialog = aDialog;
		}

		/**
		 * Set the jar list.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:41:14</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param aJarList
		 *            the list of Docbase jars.
		 */
		protected void setJarList(final List aJarList)
		{

			this.jarList = aJarList;
		}

		/**
		 * Set the jar type.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:34:56</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param aJarType
		 *            the jar type.
		 */
		protected void setJarType(final int aJarType)
		{

			this.jarType = aJarType;
		}

		/**
		 * Set the target list.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:41:44</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param aTargetList
		 *            the target list.
		 */
		protected void setTargetList(final Control aTargetList)
		{

			this.targetList = aTargetList;
		}
	}

	/**
	 * <H4>A class loader to load the jar classes.</H4>
	 * <DL>
	 * <DT><B>Description :</B>
	 * <DD> Just make the protected ClassLoader methods accessible. </DD>
	 * </DT>
	 * <DT><B>Copyright :</B>
	 * <DD>(c) 2005 Mad Cook</DD>
	 * </DT>
	 * <DT><B>Created :</B>
	 * <DD>04-Dec-2005 22:08:08.</DD>
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
	class JarClassLoader
						extends
							ClassLoader
	{

		/**
		 * Sole constructor.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 04-Dec-2005 22:09:01</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 */
		JarClassLoader()
		{

			super();
		}

		/**
		 * Get a class from a byte array.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 04-Dec-2005 22:09:16</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param classData
		 *            the class data.
		 * @return a class instance.
		 */
		@SuppressWarnings("unchecked")
		protected Class getClass(final byte[] classData)
		{

			return super.defineClass(null, classData, 0, classData.length);
		}
	}

	/**
	 * <H4>The remove button listener.</H4>
	 * <DL>
	 * <DT><B>Description :</B>
	 * <DD> Handle the remove events. </DD>
	 * </DT>
	 * <DT><B>Copyright :</B>
	 * <DD>(c) 2005 Mad Cook</DD>
	 * </DT>
	 * <DT><B>Created :</B>
	 * <DD>03-Dec-2005 13:00:16.</DD>
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
	class RemoveButtonListener
								extends
									Object
											implements
												Listener
	{

		/**
		 * The list from where the objets should be removed. 03-Dec-2005
		 * <code>targetList</code>
		 * 
		 * @since 3.0.0
		 */
		private Control	targetList	= null;

		/**
		 * Sole constructor.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 13:02:40</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param aTargetList
		 *            the list from whitch the values are removed from.
		 */
		RemoveButtonListener(final Control aTargetList)
		{

			setTargetList(aTargetList);
		}

		/**
		 * Handle the click event.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 13:03:13</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param remomveEvent
		 *            the remove event.
		 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
		 */
		public void handleEvent(final Event remomveEvent)
		{

			if(getTargetList() instanceof List)
			{
				final List targetedList = (List) getTargetList();
				if(targetedList.getSelectionIndex() > -1)
				{
					targetedList.remove(targetedList.getSelectionIndex());
					if(targetedList
						.getData(AbstractModuleImplementationPage.LIST_TYPE)
						.equals(
							AbstractModuleImplementationPage.LIST_TYPE_IMPLEMENTATION))
					{
						populateImplementationClasses();
					} else
					{
						populateInterfaceClasses();
					}
					setPageFlipStatus();
				}
			} else if(getTargetList() instanceof Text)
			{
				((Text) getTargetList()).setText(""); //$NON-NLS-1$
			}
		}

		/**
		 * Get the target list.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:53:09</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @return the target list.
		 */
		protected Control getTargetList()
		{

			return this.targetList;
		}

		/**
		 * Set the target list.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 12:52:52</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param aTargetList
		 *            the target list.
		 */
		protected void setTargetList(final Control aTargetList)
		{

			this.targetList = aTargetList;
		}
	}

	/**
	 * Type of the list 04-Dec-2005 <code>LIST_TYPE</code>.
	 * 
	 * @since 3.0.0
	 */
	public static final String			LIST_TYPE					= "list_type";													//$NON-NLS-1$

	/**
	 * Implementation list type 04-Dec-2005
	 * <code>LIST_TYPE_IMPLEMENTATION</code>.
	 * 
	 * @since 3.0.0
	 */
	public static final String			LIST_TYPE_IMPLEMENTATION	= "list_type_implementation";									//$NON-NLS-1$

	/**
	 * Interface list type 04-Dec-2005 <code>LIST_TYPE_INTERFACE</code>
	 * 
	 * @since 3.0.0
	 */
	public static final String			LIST_TYPE_INTERFACE			= "list_type_interface";										//$NON-NLS-1$

	/**
	 * Name of the bundle to use
	 * 'com.documentum.consulting.web.component.messages'
	 * 
	 * @since 1.0.0.0
	 */
	private static final String			BUNDLE_NAME					= AbstractModuleImplementationPage.class
																		.getName();

	/**
	 * The resource bundle static instance.
	 * 
	 * @since 1.0.0.0
	 */
	private static final ResourceBundle	RESOURCE_BUNDLE				= ResourceBundle
																		.getBundle(AbstractModuleImplementationPage.BUNDLE_NAME);

	/**
	 * List of BOF implementation classes.
	 * 
	 * @since 3.0.0
	 */
	private List						implementationJARsList;

	/**
	 * List of BOF interfaces.
	 * 
	 * @since 3.0.0
	 */
	private List						interfaceJARsList			= null;

	/**
	 * Group for the implementation and interface jar lists. 03-Dec-2005
	 * <code>listGroup</code>
	 * 
	 * @since 3.0.0
	 */
	private Group						listGroup					= null;

	/**
	 * The module general page <code>moduleGeneralPage</code> 04-Dec-2005.
	 * 
	 * @since 3.0.0
	 */
	private AbstractModuleGeneralPage	moduleGeneralPage			= null;

	/**
	 * Get a string from the bundle. Created: 19-May-2005 15:10:17 Author:
	 * HARPEC
	 * 
	 * @since 1.0.0.0
	 * @param key
	 *            the properties file key.
	 * @return the value corresponding to the key
	 */
	public static String getString(final String key)
	{

		try
		{
			return RESOURCE_BUNDLE.getString(key);
		} catch(final MissingResourceException swallow)
		{
			DfLogger
				.warn(
					AbstractModuleImplementationPage.class,
					"Missing resource ''{0}'' exception with message ''{1}'' in bundle {2}.", //$NON-NLS-1$
					new String[] {key, swallow.getMessage(),
						AbstractModuleImplementationPage.BUNDLE_NAME}, swallow);
		}
		return '*' + key + '*';
	}

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
	public AbstractModuleImplementationPage(final String pageName)
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
	public AbstractModuleImplementationPage(final String pageName,
											final String title,
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

		setListGroup(new Group(parent, SWT.SHADOW_ETCHED_IN));
		getListGroup().setText(
			AbstractModuleImplementationPage
				.getString("AbstractModuleImplementationPage.CORE_JARS")); //$NON-NLS-1$
		final GridLayout twoColumnLayout = new GridLayout(2, false);
		getListGroup().setLayout(twoColumnLayout);
		getListGroup().setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(getListGroup(), SWT.NULL);
		label.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleImplementationPage.INTERFACE_JARS")); //$NON-NLS-1$
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.LEFT);

		label = new Label(getListGroup(), SWT.NULL);
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		setInterfaceJARsList(new List(getListGroup(), SWT.BORDER | SWT.H_SCROLL
			| SWT.V_SCROLL | SWT.SINGLE));
		getInterfaceJARsList().setLayoutData(new GridData(GridData.FILL_BOTH));
		getInterfaceJARsList().setData(
			AbstractModuleImplementationPage.LIST_TYPE,
			AbstractModuleImplementationPage.LIST_TYPE_INTERFACE);

		Group buttonGroup = new Group(getListGroup(), SWT.SHADOW_NONE);
		final GridLayout singleColumnLayout = new GridLayout(1, false);
		buttonGroup.setLayout(singleColumnLayout);
		buttonGroup.setLayoutData(new GridData(GridData.FILL_BOTH));

		Button button = new Button(buttonGroup, SWT.PUSH);
		button.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleImplementationPage.FILESYSTEM_ADD")); //$NON-NLS-1$
		final FileDialog fileDialog = new FileDialog(getContainer().getShell(),
			SWT.OPEN);
		fileDialog.setFilterExtensions(new String[] {
			AbstractModuleImplementationPage
				.getString("AbstractModuleImplementationPage.EXTENSION_JAR"), //$NON-NLS-1$
			AbstractModuleImplementationPage
				.getString("AbstractModuleImplementationPage.EXTENSION_ZIP")}); //$NON-NLS-1$
		button.addListener(SWT.Selection, new FileSystemButtonListener(
			getInterfaceJARsList(), fileDialog));

		button = new Button(buttonGroup, SWT.PUSH);
		button.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleImplementationPage.DOCBASE_ADD")); //$NON-NLS-1$
		button.addListener(SWT.Selection, new DocbaseButtonListener(
			getInterfaceJARsList(), DocbaseButtonListener.INTERFACE));

		button = new Button(buttonGroup, SWT.PUSH);
		button.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleImplementationPage.REMOVE")); //$NON-NLS-1$
		button.addListener(SWT.Selection, new RemoveButtonListener(
			getInterfaceJARsList()));

		label = new Label(getListGroup(), SWT.NULL);
		label.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleImplementationPage.IMPLEMENTATION_JARS")); //$NON-NLS-1$
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setAlignment(SWT.LEFT);

		label = new Label(getListGroup(), SWT.NULL);
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		setImplementationJARsList(new List(getListGroup(), SWT.BORDER
			| SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE));
		getImplementationJARsList().setLayoutData(
			new GridData(GridData.FILL_BOTH));
		getImplementationJARsList().setData(
			AbstractModuleImplementationPage.LIST_TYPE,
			AbstractModuleImplementationPage.LIST_TYPE_IMPLEMENTATION);

		buttonGroup = new Group(getListGroup(), SWT.SHADOW_NONE);
		buttonGroup.setLayout(singleColumnLayout);
		buttonGroup.setLayoutData(new GridData(GridData.FILL_BOTH));

		button = new Button(buttonGroup, SWT.PUSH);
		button.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleImplementationPage.FILESYSTEM_ADD")); //$NON-NLS-1$
		button.addListener(SWT.Selection, new FileSystemButtonListener(
			getImplementationJARsList(), fileDialog));

		button = new Button(buttonGroup, SWT.PUSH);
		button.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleImplementationPage.DOCBASE_ADD")); //$NON-NLS-1$
		button.addListener(SWT.Selection, new DocbaseButtonListener(
			getImplementationJARsList(), DocbaseButtonListener.IMPLEMENTATION));

		button = new Button(buttonGroup, SWT.PUSH);
		button.setText(AbstractModuleImplementationPage
			.getString("AbstractModuleImplementationPage.REMOVE")); //$NON-NLS-1$
		button.addListener(SWT.Selection, new RemoveButtonListener(
			getImplementationJARsList()));

		getListGroup().setEnabled(false);

	}

	/**
	 * Returns the wizard container for this wizard page. Implemented just to
	 * improve performance.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 02-Dec-2005 17:05:16</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the wizard container, or <code>null</code> if this wizard page
	 *         has yet to be added to a wizard, or the wizard has yet to be
	 *         added to a container
	 * @see org.eclipse.jface.wizard.WizardPage#getContainer()
	 */
	@Override
	public IWizardContainer getContainer()
	{

		return super.getContainer();
	}

	/**
	 * Get the list group
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 03-Dec-2005 13:09:22</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the list group
	 */
	public Group getListGroup()
	{

		return this.listGroup;
	}

	/**
	 * Set the module general page..
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:14:01</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aModuleGeneralPage
	 *            the module general page.
	 */
	public void setModuleGeneralPage(
										final AbstractModuleGeneralPage aModuleGeneralPage)
	{

		this.moduleGeneralPage = aModuleGeneralPage;
	}

	/**
	 * Get the implementation jar list.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29-Nov-2005 08:42:10</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the implementation jar list.
	 */
	protected List getImplementationJARsList()
	{

		return this.implementationJARsList;
	}

	/**
	 * Get the interface jar list.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29-Nov-2005 08:42:17</DD>
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
	 * Get a jarfile. If it's a local file just create a File object and if it's
	 * a Docabse file download it and return a File pointing to the dowloaded
	 * file.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:10:21</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param jarName
	 *            name of the file as in the selection list.
	 * @return a File pointing to the jar.
	 */
	protected File getJarFile(final String jarName)
	{

		File jarFile = new File(jarName);
		if(!jarFile.exists())
		{
			final int openIndex = jarName.indexOf('(');
			final int closeIndex = jarName.indexOf(')');
			if((openIndex > -1) && ((openIndex + 16) == closeIndex))
			{
				final String objectId = jarName
					.substring(openIndex, closeIndex);
				IDfSession session = null;
				try
				{
					session = ConnectionContentProvider.getInstance()
						.getSelectedConnectionModel().getSession();
					final String tag = ConnectionView.getInstance()
						.getSelectedConnection().getTag();
					MessageView.getInstance().addMessage(
						tag + " getfile,c," + objectId); //$NON-NLS-1$
					jarFile = new File(session.apiGet("getfile", objectId)); //$NON-NLS-1$
					MessageView.getInstance().addMessage(
						tag + ' ' + jarFile.getAbsolutePath());
				} catch(final DfException dex)
				{
					System.out.println(dex.getMessage());
				} finally
				{
					ConnectionContentProvider.getInstance()
						.getSelectedConnectionModel()
						.releaceConnection(session);
				}
			}
		}
		return jarFile;
	}

	/**
	 * Get the module general page.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:13:17</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the module general page.
	 */
	protected AbstractModuleGeneralPage getModuleGeneralPage()
	{

		return this.moduleGeneralPage;
	}

	/**
	 * Get classes or interfaces from a jar.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 21:55:11</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param jarList
	 * @param getInterfaces
	 *            should the method return interfaces or classes.
	 * @return names of the classes found.
	 */
	@SuppressWarnings("unchecked")
	protected String[] populateClasses(final List jarList,
										final boolean getInterfaces)
	{

		final java.util.List<String> classes = new Vector<String>();
		for(final String jarName: jarList.getItems())
		{
			if(jarName != null)
			{
				try
				{
					final File jarLocation = getJarFile(jarName);
					final JarFile jarFile = new JarFile(jarLocation);
					for(final Enumeration<JarEntry> entries = jarFile.entries(); entries
						.hasMoreElements();)
					{
						final JarEntry jarEntry = entries.nextElement();
						if(!jarEntry.isDirectory())
						{
							final InputStream is = jarFile
								.getInputStream(jarEntry);
							final byte[] content = new byte[is.available()];
							is.read(content);
							try
							{
								final Class clazz = (new JarClassLoader())
									.getClass(content);
								if(((getInterfaces) && (clazz.isInterface()))
									|| ((!getInterfaces) && (!clazz
										.isInterface())))
								{
									classes.add(clazz.getName());
								}
							} catch(final Throwable swallow)
							{
								// Not much we can do with this.
							}
						}
					}
				} catch(final Throwable ex)
				{
					System.out.println(ex.getMessage());
				}

			}
		}
		return classes.toArray(new String[classes.size()]);
	}

	/**
	 * Populate the implementation classes combo box on the general page.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:14:23</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 */
	protected void populateImplementationClasses()
	{

		getModuleGeneralPage().getClassNameCombo().removeAll();
		getModuleGeneralPage().getClassNameCombo().setItems(
			populateClasses(getImplementationJARsList(), false));
	}

	/**
	 * Populate the interface classes list box on the general page.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 04-Dec-2005 22:15:13</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 */
	protected void populateInterfaceClasses()
	{

		getModuleGeneralPage().getInterfaceJARsList().removeAll();
		getModuleGeneralPage().getInterfaceJARsList().setItems(
			populateClasses(getInterfaceJARsList(), true));
	}

	/**
	 * Set the implementation jar list.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29-Nov-2005 08:40:58</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aImplementationJARsList
	 *            the implementation jar list.
	 */
	protected void setImplementationJARsList(final List aImplementationJARsList)
	{

		this.implementationJARsList = aImplementationJARsList;
	}

	/**
	 * Set the interface jar list.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 29-Nov-2005 08:42:14</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aInterfaceJARsList
	 *            the interface jar list.
	 */
	protected void setInterfaceJARsList(final List aInterfaceJARsList)
	{

		this.interfaceJARsList = aInterfaceJARsList;
	}

	/**
	 * Set the list group.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 03-Dec-2005 13:10:07</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aListGroup
	 *            the list group
	 */
	protected void setListGroup(final Group aListGroup)
	{

		this.listGroup = aListGroup;
	}

	/**
	 * Check if the page can be changed.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 03-Dec-2005 13:10:50</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 */
	protected void setPageFlipStatus()
	{

		final String[] interfaceJars = getInterfaceJARsList().getItems();
		if((interfaceJars != null) && (interfaceJars.length > 0))
		{
			final String[] implementaionJars = getImplementationJARsList()
				.getItems();
			if((implementaionJars != null) && (implementaionJars.length > 0))
			{
				setPageComplete(true);
				return;
			}
		}
		setPageComplete(false);
	}
}
