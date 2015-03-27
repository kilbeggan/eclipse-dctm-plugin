/*-
 * $Log: CreateRelationAPIAction.java,v $
 * Revision 1.5  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.4  2005/12/04 20:26:26  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.3  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.2  2005/03/25 09:19:59  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.1  2005/02/28 07:38:25  harpechr
 * Refactored actions so that they are in view and type spesific packages.
 *
 * Revision 1.4  2005/02/09 14:01:29  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 18:51:47  harpechr
 * Fixed resource bundle problem.
 *
 * Revision 1.2  2005/01/24 12:34:53  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.1  2005/01/18 07:24:47  harpechr
 * Version 1.0.3 features added. Mainly relation related modifications.
 *
 */

package org.cah.eclipse.plugins.dctm.dql.actions.connection.api;

import java.util.ResourceBundle;

import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.TypeModel;

import com.documentum.fc.common.DfLogger;


/**
 * <H4>Create relation api statement generator.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Jan 17, 2005 1:00:34 PM.</DD>
 * </DT>
 * </DL>
 * 
 * @author Christopher Harper account : HARPECHR
 * @version 3.0.0
 * @since 1.0.3
 */
public class CreateRelationAPIAction
									extends
										AbstractCreateAPIAction
{

	/**
	 * The resource bundle name.
	 * 
	 * @since 1.0.3
	 */
	public static final String		RESOURCE_NAME				= CreateRelationAPIAction.class
																	.getName()
																	+ "Resources";					//$NON-NLS-1$

	/**
	 * Create group statements bundle.
	 * 
	 * @since 1.0.3
	 */
	private static ResourceBundle	resourceBundle				= null;

	/**
	 * Create relation comment key.
	 * 
	 * @since 1.0.3
	 */
	private static final String		MSG_CREATE_RELATION_COMMENT	= "MSG_CREATE_RELATION_COMMENT";	//$NON-NLS-1$

	/**
	 * Create relation api statements key
	 * 
	 * @since 1.0.3
	 */
	private static final String		MSG_CREATE_RELATION			= "MSG_CREATE_RELATION";			//$NON-NLS-1$
	/**
	 * Initialize the resourcebundle.
	 * 
	 * @since 1.0.3
	 */
	static
	{
		setResourceBundle(ResourceBundle
			.getBundle(CreateRelationAPIAction.RESOURCE_NAME));
	}

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 17, 2005 1:00:34 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.3
	 */
	public CreateRelationAPIAction()
	{

		super();
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

		return CreateRelationAPIAction.resourceBundle;
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

		CreateRelationAPIAction.resourceBundle = aResourceBundle;
	}

	/**
	 * Get the api statements.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 17, 2005 1:00:34 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.3
	 * @return the create API statements.
	 * @see org.cah.eclipse.plugins.dctm.dql.actions.connection.api.AbstractCreateAPIAction#getCreateAPI()
	 */
	@Override
	protected String getCreateAPI()
	{

		final StringBuffer createRelationAPI = new StringBuffer(
			CreateRelationAPIAction.getResourceBundle().getString(
				CreateRelationAPIAction.MSG_CREATE_RELATION_COMMENT));
		if(ConnectionView.getInstance().getSelectedModel() instanceof TypeModel)
		{
			createRelationAPI.append(DfLogger.getFullMessage(
				CreateRelationAPIAction.getResourceBundle().getString(
					CreateRelationAPIAction.MSG_CREATE_RELATION),
				new String[] {ConnectionView.getInstance().getSelectedModel()
					.getName()}));

		} else
		{
			createRelationAPI.append(DfLogger.getFullMessage(
				CreateRelationAPIAction.getResourceBundle().getString(
					CreateRelationAPIAction.MSG_CREATE_RELATION),
				new String[] {"<parent_type_name>"})); //$NON-NLS-1$
		}
		return createRelationAPI.toString();
	}
}
