/*-
 * $Log: ModuleImplementationPage.java,v $
 * Revision 1.1  2005/12/04 22:14:40  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.wizards.modules.tbo;

import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;
import org.cah.dctm.bof.tbo.persistent.sysobject.folder.module.IModule;
import org.cah.dctm.bof.tbo.persistent.type.IType;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionContentProvider;
import org.cah.eclipse.plugins.dctm.dql.wizards.modules.AbstractModuleImplementationPage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;


/**
 * <H4>The module implementation page.</H4>
 * <DL>
 * <DT><B>Description :</B>
 * <DD>The page for defining the type and jar files for a TBO module.</DD>
 * </DT>
 * <DT><B>Copyright :</B>
 * <DD>(c) 2005 Mad Cook</DD>
 * </DT>
 * <DT><B>Created :</B>
 * <DD>25-Nov-2005 15:53:47.</DD>
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
 * href="http://www.gnu.org/licenses/gpl.html">GNU General Public License</a>
 * for more details.
 * </p>
 * 
 * @author Mad Cook account: dmadmin
 * @version 3.0.0
 * @since 3.0.0
 */
public class ModuleImplementationPage
										extends
											AbstractModuleImplementationPage
{

	/**
	 * <H4>Selection listener.</H4>
	 * <DL>
	 * <DT><B>Description :</B>
	 * <DD> Event triggered when the type selection changes. </DD>
	 * </DT>
	 * <DT><B>Copyright :</B>
	 * <DD>(c) 2005 Mad Cook</DD>
	 * </DT>
	 * <DT><B>Created :</B>
	 * <DD>03-Dec-2005 13:35:55.</DD>
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
		 * <DD> 03-Dec-2005 16:01:04</DD>
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
		 * Handle the combo selection event.
		 * <DL>
		 * <DT><B>Created :</B>
		 * <DD> 03-Dec-2005 16:01:21</DD>
		 * </DT>
		 * <DT><B>Author :</B>
		 * <DD>Mad Cook account : dmadmin</DD>
		 * </DT>
		 * </DL>
		 * 
		 * @since 3.0.0
		 * @param selectionChangeEvent
		 *            selection chage event.
		 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
		 */
		public void handleEvent(final Event selectionChangeEvent)
		{

			setTypeName(getTypeCombo().getText());
			if((getTypeName() != null) && (getTypeName().length() > 1))
			{
				getListGroup().setEnabled(true);
				setPageFlipStatus();
			} else
			{
				getListGroup().setEnabled(false);
			}
		}
	}

	/**
	 * The combo containing all those types that dont already have a TBO module.
	 * 25-Nov-2005 <code>typeCombo</code>
	 * 
	 * @since 3.0.0
	 */
	private Combo	typeCombo	= null;

	/**
	 * Name of the selected type. 03-Dec-2005 <code>typeName</code>
	 * 
	 * @since 3.0.0
	 */
	private String	typeName	= null;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25-Nov-2005 15:54:40</DD>
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
	public ModuleImplementationPage(final String pageName)
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
	 *            title image of the page.
	 */
	public ModuleImplementationPage(final String pageName, final String title,
									final ImageDescriptor titleImage)
	{

		super(pageName, title, titleImage);
	}

	/**
	 * Create the controls for the module implementation page.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25-Nov-2005 15:56:24</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param parent
	 *            the parent container.
	 * @see org.cah.eclipse.plugins.dctm.dql.wizards.modules.AbstractModuleImplementationPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(final Composite parent)
	{

		final Composite container = new Composite(parent, SWT.NULL);
		final GridLayout singleColumnLayout = new GridLayout(1, false);
		container.setLayout(singleColumnLayout);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		final Group mainGroup = new Group(container, SWT.SHADOW_ETCHED_IN);
		mainGroup.setLayout(singleColumnLayout);
		mainGroup.setLayoutData(new GridData(GridData.FILL_BOTH));

		final Group typeGroup = new Group(mainGroup, SWT.SHADOW_ETCHED_IN);
		final GridLayout twoColumnLayout = new GridLayout(2, false);
		typeGroup.setLayout(twoColumnLayout);
		typeGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		typeGroup.setText(AbstractModuleImplementationPage
			.getString("ModuleImplementationPage.TBO_TYPE")); //$NON-NLS-1$

		final Label typeNameLabel = new Label(typeGroup, SWT.NULL);
		typeNameLabel.setText(AbstractModuleImplementationPage
			.getString("ModuleImplementationPage.TYPE_NAME")); //$NON-NLS-1$
		typeNameLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		setTypeCombo(new Combo(typeGroup, SWT.BORDER));
		getTypeCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		getTypeCombo().addListener(SWT.Selection, new SelectionListener());

		super.createControl(mainGroup);

		populateTypes();

		setControl(container);

		setPageComplete(false);
	}

	/**
	 * Get the type combobox.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25-Nov-2005 15:55:55</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the type combobox.
	 */
	protected Combo getTypeCombo()
	{

		return this.typeCombo;
	}

	/**
	 * Get the type name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 03-Dec-2005 16:05:48</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the type name.
	 */
	protected String getTypeName()
	{

		return this.typeName;
	}

	/**
	 * Populate types into the type combo box.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25-Nov-2005 15:55:45</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 */
	protected void populateTypes()
	{

		final StringBuffer typeQuery = new StringBuffer("select distinct ") //$NON-NLS-1$
			.append(IType.NAME).append(" from ").append(IType.TYPE_DM_TYPE) //$NON-NLS-1$
			.append(" where ").append(IType.NAME).append(" not in ( select ") //$NON-NLS-1$ //$NON-NLS-2$
			.append(ISysObject.OBJECT_NAME).append(" from ") //$NON-NLS-1$
			.append(IModule.TYPE_DMC_MODULE).append(" ) order by 1"); //$NON-NLS-1$
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
			for(results = query.execute(session, IDfQuery.DF_EXECREAD_QUERY); results
				.next();)
			{
				getTypeCombo().add(results.getString(IType.NAME));
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
	 * Check if the page can be changed.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 03-Dec-2005 13:27:03</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @see org.cah.eclipse.plugins.dctm.dql.wizards.modules.AbstractModuleImplementationPage#setPageFlipStatus()
	 */
	@Override
	protected void setPageFlipStatus()
	{

		if((getTypeName() != null) && (getTypeName().length() > 1))
		{
			super.setPageFlipStatus();
		}
	}

	/**
	 * Set the type combo box.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25-Nov-2005 15:55:49</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aTypeCombo
	 *            the type combo box.
	 */
	protected void setTypeCombo(final Combo aTypeCombo)
	{

		this.typeCombo = aTypeCombo;
	}

	/**
	 * Set the type name.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 03-Dec-2005 16:06:09</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aTypeName
	 *            the type name.
	 */
	protected void setTypeName(final String aTypeName)
	{

		this.typeName = aTypeName;
	}
}
