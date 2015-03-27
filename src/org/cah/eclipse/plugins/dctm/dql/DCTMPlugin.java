/*-
 * $Log: DCTMPlugin.java,v $
 * Revision 1.20  2005/12/04 22:14:39  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.19  2005/12/04 20:32:42  madcook
 * Version 3.0.0 work started.
 * 
 * Revision 1.18 2005/11/21 13:04:31 madcook 
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 * 
 * Revision 1.17 2005/04/01 11:06:49 harpechr 
 * Added create TBO resources and moved static final variables to interfaces. 
 * 
 * Revision 1.16 2005/03/25 09:18:10 harpechr 
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 * 
 * Revision 1.15 2005/02/09 14:01:30 harpechr 
 * Version 1.0.5 work started.
 * 
 * Revision 1.14 2005/02/07 13:51:39 harpechr 
 * Create count type frequency DQL action added. 
 * 
 * Revision 1.13 2005/02/07 13:14:52 harpechr 
 * Create count fulltext indexed (& failed) DQL action added. 
 * 
 * Revision 1.12 2005/02/07 12:37:36 harpechr 
 * Create fulltext indexed DQL action added. 
 * 
 * Revision 1.11 2005/02/07 11:03:46 harpechr 
 * Create waiting for fulltext indexing DQL action added. 
 * 
 * Revision 1.10 2005/02/07 10:12:00 harpechr 
 * Create count content distribution DQL action added. 
 * 
 * Revision 1.9 2005/02/02 08:27:59 harpechr
 * Create count users documents DQL action defined. 
 * 
 * Revision 1.8 2005/01/25 20:18:43 harpechr 
 * Create content format statistics DQL statement action defined. 
 * 
 * Revision 1.7 2005/01/24 22:23:35 harpechr 
 * Create change default storage DQL statement action defined. 
 * 
 * Revision 1.6 2005/01/24 12:34:53 harpechr 
 * Version 1.0.4 work started. 
 * 
 * Revision 1.5 2005/01/18 07:24:48 harpechr 
 * Version 1.0.3 features added. Mainly relation related modifications.
 * 
 * Revision 1.4 2005/01/11 14:02:00 harpechr 
 * Changed version number from 1.0.2 to 1.0.3. 
 * 
 * Revision 1.3 2005/01/11 13:47:24 harpechr 
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof). 
 * 
 * Revision 1.2 2005/01/07 14:17:29 harpechr 
 * Version 1.0.2 code. First CVS commit!
 */

package org.cah.eclipse.plugins.dctm.dql;

import java.io.File;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.ResultView;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionContentProvider;
import org.cah.eclipse.plugins.dctm.dql.views.connection.ConnectionSaveParticipant;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.resources.ISavedState;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.IDfClient;


/**
 * <H4>The main plugin class to be used in the desktop.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 25, 2004 10:27:30 AM.</DD>
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
public class DCTMPlugin
						extends
							AbstractUIPlugin
											implements
												IDCTMPlugin
{

	/**
	 * ID of this plugin.
	 * 
	 * @since 2.0.0
	 */
	public static String			PLUGIN_ID			= null;

	/**
	 * Documentum clientX.
	 * 
	 * @since 1.0
	 */
	private static final IDfClientX	clientX				= new DfClientX();

	/**
	 * The shared instance of the <code>plugin</code>.
	 * 
	 * @since 1.0
	 */
	private static DCTMPlugin		pluginInstance		= null;

	/**
	 * Resource bundle name
	 * <code>TE_RESOUCE_NAME = " org.cah.eclipse.plugins.dctm.DctmPluginResources";</code>
	 * 
	 * @since 1.0
	 */
	private static final String		TE_RESOUCE_NAME		= DCTMPlugin.class
															.getName()
															+ "Resources";	//$NON-NLS-1$

	/**
	 * Are the views been activated.
	 * 
	 * @since 1.0
	 */
	private static boolean			viewsNotActivated	= true;

	/**
	 * Resource bundle <code>resourceBundle</code>.
	 * 
	 * @since 1.0
	 */
	private ResourceBundle			resourceBundle		= null;

	/**
	 * Initialize DFC.
	 * 
	 * @since 1.0
	 */
	static
	{
		DCTMPlugin.getClient();
	}

	/**
	 * The sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 10:29:01 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public DCTMPlugin()
	{

		super();
		DCTMPlugin.pluginInstance = this;
		try
		{
			DCTMPlugin.getInstance().setResourceBundle(
				ResourceBundle.getBundle(DCTMPlugin.TE_RESOUCE_NAME));
			final String className = DCTMPlugin.getInstance().getClass()
				.getName();
			DCTMPlugin.PLUGIN_ID = className.substring(0, className
				.lastIndexOf('.'));
		} catch(MissingResourceException mrex)
		{
			DCTMPlugin.getInstance().setResourceBundle(null);
		}
	}

	/**
	 * Activate DCTM plug-in views.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 4:16:47 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public static void activateViews()
	{

		try
		{
			IWorkbenchWindow[] windows = DCTMPlugin.getInstance()
				.getWorkbench().getWorkbenchWindows();
			for(int windowIndex = 0; windowIndex < windows.length; windowIndex++)
			{
				IWorkbenchPage[] pages = windows[windowIndex].getPages();
				for(int pageIndex = 0; pageIndex < pages.length; pageIndex++)
				{
					IViewPart message = pages[pageIndex]
						.findView(MessageView.ID);
					if(message != null)
					{
						pages[pageIndex].activate(message);
					} else
					{
						pages[pageIndex].showView(MessageView.ID, null,
							IWorkbenchPage.VIEW_CREATE);
					}
					IViewPart result = pages[pageIndex].findView(ResultView.ID);
					if(result != null)
					{
						pages[pageIndex].activate(result);
					} else
					{
						pages[pageIndex].showView(ResultView.ID, null,
							IWorkbenchPage.VIEW_CREATE);
					}
				}
			}
		} catch(PartInitException ex)
		{
			System.out.println(ex.getMessage());
			ex.printStackTrace(System.out);
		}
		DCTMPlugin.viewsActivated();
	}

	/**
	 * Get the client.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 14:36:39</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return Returns the client
	 */
	public static IDfClient getClient()
	{

		return DfClient.getLocalClientEx();
	}

	/**
	 * Get the clientX.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 14:36:39</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return Returns the clientX
	 */

	public static IDfClientX getClientX()
	{

		return DCTMPlugin.clientX;
	}

	/**
	 * Returns the shared instance.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 10:32:14 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the shared instance.
	 */
	public static DCTMPlugin getDefault()
	{

		if(DCTMPlugin.pluginInstance == null)
		{
			new DCTMPlugin();
		}
		return DCTMPlugin.pluginInstance;
	}

	/**
	 * Get the first created instance of the plugin.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 27, 2004 2:22:29 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the first created plugin instance.
	 */
	public static DCTMPlugin getInstance()
	{

		return getDefault();
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 10:32:42 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param key
	 *            resource bundle key
	 * @return the founc value or key if the value storage not found.
	 */
	public static String getResourceString(final String key)
	{

		final ResourceBundle bundle = DCTMPlugin.getDefault()
			.getResourceBundle();
		try
		{
			return (bundle != null) ? bundle.getString(key) : key;
		} catch(MissingResourceException mrex)
		{
			return key;
		}
	}

	/**
	 * Returns the workspace instance.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 10:20:28 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the workspace.
	 */
	public static IWorkspace getWorkspace()
	{

		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Check if the views have been initialized.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 3:59:45 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return true if the views haven't been activated.
	 */
	public static boolean isViewsNotActivated()
	{

		return DCTMPlugin.viewsNotActivated;
	}

	/**
	 * Set the views activated to true.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 4:00:31 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public static void viewsActivated()
	{

		DCTMPlugin.viewsNotActivated = false;
	}

	/**
	 * Get the resource bundle.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 01-Apr-2005 12:46:07</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return the plugins resource bundle.
	 * @see org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin#getResourceBundle()
	 */

	public ResourceBundle getResourceBundle()
	{

		return this.resourceBundle;
	}

	/**
	 * This method storage called upon plug-in activation.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 10:34:42 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * <p>
	 * This method should be overridden in subclasses that need to do something
	 * when this plug-in storage started. Implementors should call the inherited
	 * method at the first possible point to ensure that any system requirements
	 * can be met.
	 * </p>
	 * <p>
	 * If this method throws an exception, it storage taken as an indication
	 * that plug-in initialization has failed; as a result, the plug-in will not
	 * be activated; moreover, the plug-in will be marked as disabled and
	 * ineligible for activation for the duration.
	 * </p>
	 * <p>
	 * Plug-in startup code should be robust. In the event of a startup failure,
	 * the plug-in's <code>shutdown</code> method will be invoked
	 * automatically, in an attempt to close open files, etc.
	 * </p>
	 * <p>
	 * Note 1: This method storage automatically invoked by the platform the
	 * first time any code in the plug-in storage executed.
	 * </p>
	 * <p>
	 * Note 2: This method storage intended to perform simple initialization of
	 * the plug-in environment. The platform may terminate initializers that do
	 * not complete in a timely fashion.
	 * </p>
	 * <p>
	 * Note 3: The class loader typically has monitors acquired during
	 * invocation of this method. It storage strongly recommended that this
	 * method avoid synchronized blocks or other thread locking mechanisms, as
	 * this would lead to deadlock vulnerability.
	 * </p>
	 * <p>
	 * Note 4: The supplied bundle context represents the plug-in to the OSGi
	 * framework. For security reasons, it storage strongly recommended that
	 * this object should not be divulged.
	 * </p>
	 * <b>Clients must never explicitly call this method. </b>
	 * 
	 * @param context
	 *            The bundle context for this plug-in.
	 * @exception Exception
	 *                If this plug-in did not start up properly.
	 * @since 1.0
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(final BundleContext context) throws Exception
	{

		super.start(context);
		final ISaveParticipant saveParticipant = new ConnectionSaveParticipant();
		final ISavedState lastState = ResourcesPlugin.getWorkspace()
			.addSaveParticipant(this, saveParticipant);
		if(lastState == null)
		{
			return;
		}
		final IPath location = lastState.lookup(new Path(
			ConnectionSaveParticipant.SAVE_FILE));
		if(location == null)
		{
			return;
		}
		final File settingsFile = getStateLocation().append(location).toFile();
		try
		{
			ConnectionContentProvider.getInstance().load(settingsFile);
		} catch(Exception e)
		{
			settingsFile.delete();
			MessageDialog
				.openError(
					getWorkbench().getActiveWorkbenchWindow().getShell(),
					"Error Loading Connections", //$NON-NLS-1$
					"Saved connections are corrupt, connections deleted, please add new connections" + e.getMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * This method storage called when the plug-in storage stopped.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 10:38:38 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param context
	 *            The bundle context for this plug-in.
	 * @exception Exception
	 *                If this plug-in did not stop properly.
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext context) throws Exception
	{

		super.stop(context);
	}

	/**
	 * Set the resource bundle to use.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 10:40:00 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aResourceBundle
	 *            the new bundle.
	 */
	protected void setResourceBundle(final ResourceBundle aResourceBundle)
	{

		this.resourceBundle = aResourceBundle;
	}
}
