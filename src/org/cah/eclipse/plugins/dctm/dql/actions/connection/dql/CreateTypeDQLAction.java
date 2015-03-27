/*-
 * $Log: CreateTypeDQLAction.java,v $
 * Revision 1.6  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.5  2005/12/04 20:26:26  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.4  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.3  2005/04/01 11:27:30  harpechr
 * Reference to a static string changed to an interface.
 *
 * Revision 1.2  2005/03/25 09:19:59  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.1  2005/02/28 07:38:25  harpechr
 * Refactored actions so that they are in view and type spesific packages.
 *
 * Revision 1.6  2005/02/09 14:01:15  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.5  2005/01/24 12:34:53  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.4  2005/01/11 14:02:00  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.3  2005/01/11 13:44:38  harpechr
 * Moved the create statements to resource bundles.
 *
 * Revision 1.2  2005/01/09 10:46:54  harpechr
 * Renamed classes that were abstract by prefixing the class name with 
 * 'Abstract'.
 *
 * Revision 1.1  2005/01/07 12:37:48  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.connection.dql;

import java.util.ResourceBundle;

import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.actions.AbstractBaseAction;
import org.cah.eclipse.plugins.dctm.dql.editors.DQLEditor;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.TypeModel;
import org.eclipse.ui.IEditorPart;

import com.documentum.fc.common.DfLogger;


/**
 * <H4>Add a create type DQL statement to a active DQL editor.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Jan 2, 2005 6:22:44 PM.</DD>
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
 * @since 1.0.2
 */
public class CreateTypeDQLAction
								extends
									AbstractBaseAction
{

	/**
	 * Alter attribute DQL key.
	 * 
	 * @since 1.0.3
	 */
	public static final String		MSG_ALTER_ATTRIBUTE			= "MSG_ALTER_ATTRIBUTE";			//$NON-NLS-1$

	/**
	 * Alter attribute comment key.
	 * 
	 * @since 1.0.3
	 */
	public static final String		MSG_ALTER_ATTRIBUTE_COMMENT	= "MSG_ALTER_ATTRIBUTE_COMMENT";	//$NON-NLS-1$

	/**
	 * Alter type DQL key.
	 * 
	 * @since 1.0.3
	 */
	public static final String		MSG_ALTER_TYPE				= "MSG_ALTER_TYPE";				//$NON-NLS-1$

	/**
	 * Alter type comment key.
	 * 
	 * @since 1.0.3
	 */
	public static final String		MSG_ALTER_TYPE_COMMENT		= "MSG_ALTER_TYPE_COMMENT";		//$NON-NLS-1$

	/**
	 * Create type DQL key.
	 * 
	 * @since 1.0.3
	 */
	public static final String		MSG_CREATE_TYPE				= "MSG_CREATE_TYPE";				//$NON-NLS-1$

	/**
	 * Create type comment key.
	 * 
	 * @since 1.0.3
	 */
	public static final String		MSG_CREATE_TYPE_COMMENT		= "MSG_CREATE_TYPE_COMMENT";		//$NON-NLS-1$

	/**
	 * The resource bundle name.
	 * 
	 * @since 1.0.3
	 */
	public static final String		RESOURCE_NAME				= CreateTypeDQLAction.class
																	.getName()
																	+ "Resources";					//$NON-NLS-1$

	/**
	 * Create statements bundle.
	 * 
	 * @since 1.0
	 */
	private static ResourceBundle	resourceBundle				= null;

	/**
	 * Initialize the resourcebundle.
	 * 
	 * @since 1.0
	 */
	static
	{
		setResourceBundle(ResourceBundle
			.getBundle(CreateTypeDQLAction.RESOURCE_NAME));
	}

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 6:22:44 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.2
	 */
	public CreateTypeDQLAction()
	{

		super();
	}

	/**
	 * Create the create type DQL statement.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 7:29:08 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.2
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run()
	{

		IEditorPart editor = null;
		if(getView() != null)
		{
			editor = getView().getSite().getWorkbenchWindow().getActivePage()
				.getActiveEditor();
		}
		DQLEditor dqlEditor = null;
		if((editor != null) && (editor instanceof DQLEditor))
		{
			dqlEditor = (DQLEditor) editor;
		}
		if(dqlEditor == null)
		{
			MessageView.getInstance().addMessage("No active DQL editor"); //$NON-NLS-1$
			return;
		}
		dqlEditor.setText(dqlEditor.getText() + IDCTMPlugin.NEWLINE
			+ getCreateTypeDQL());
	}

	/**
	 * Create the create type DQL statement.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 11, 2005 1:17:09 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the create and alter type statements.
	 */
	protected String getCreateTypeDQL()
	{

		final StringBuffer createTypeDQL = new StringBuffer(
			(String) CreateTypeDQLAction.getResourceBundle().getObject(
				CreateTypeDQLAction.MSG_CREATE_TYPE_COMMENT));
		if(ConnectionView.getInstance().getSelectedModel() instanceof TypeModel)
		{
			createTypeDQL.append(DfLogger.getFullMessage(
				(String) CreateTypeDQLAction.getResourceBundle().getObject(
					CreateTypeDQLAction.MSG_CREATE_TYPE),
				new String[] {ConnectionView.getInstance().getSelectedModel()
					.getName()}));
		} else
		{
			createTypeDQL.append(DfLogger
				.getFullMessage((String) CreateTypeDQLAction
					.getResourceBundle().getObject(
						CreateTypeDQLAction.MSG_CREATE_TYPE),
					new String[] {"null"})); //$NON-NLS-1$
		}
		createTypeDQL.append(
			CreateTypeDQLAction.getResourceBundle().getObject(
				CreateTypeDQLAction.MSG_ALTER_TYPE_COMMENT)).append(
			CreateTypeDQLAction.getResourceBundle().getObject(
				CreateTypeDQLAction.MSG_ALTER_TYPE)).append(
			CreateTypeDQLAction.getResourceBundle().getObject(
				CreateTypeDQLAction.MSG_ALTER_ATTRIBUTE_COMMENT)).append(
			CreateTypeDQLAction.getResourceBundle().getObject(
				CreateTypeDQLAction.MSG_ALTER_ATTRIBUTE));
		return createTypeDQL.toString();
	}

	/**
	 * Get the resourcebundle.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 11, 2005 1:20:08 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the resourceBundle.
	 */
	public static ResourceBundle getResourceBundle()
	{

		return CreateTypeDQLAction.resourceBundle;
	}

	/**
	 * Set the resourcebundle.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 11, 2005 1:20:08 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aResourceBundle
	 *            The resourceBundle to set.
	 */
	protected static void setResourceBundle(final ResourceBundle aResourceBundle)
	{

		CreateTypeDQLAction.resourceBundle = aResourceBundle;
	}
}
