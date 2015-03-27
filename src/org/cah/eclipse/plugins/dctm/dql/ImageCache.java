/*-
 * $Log: ImageCache.java,v $
 * Revision 1.18  2005/12/04 20:32:42  madcook
 * Version 3.0.0 work started.
 * 
 * 
 * Revision 1.17 2005/11/21 14:53:38 madcook 
 * Old 1.4 style code removed. 
 * 
 * Revision 1.16 2005/11/21 13:04:31 madcook 
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings. 
 * 
 * Revision 1.15 2005/04/01 11:06:49 harpechr 
 * Added create TBO resources and moved static final variables to interfaces. 
 * 
 * Revision 1.14 2005/03/25 09:18:09 harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features. 
 * 
 * Revision 1.13 2005/02/09 14:01:30 harpechr
 * Version 1.0.5 work started. 
 * 
 * Revision 1.12 2005/02/07 11:03:46 harpechr 
 * Create waiting for fulltext indexing DQL action added. 
 * 
 * Revision 1.11 2005/02/07 10:11:58 harpechr 
 * Create count content distribution DQL action added. 
 * 
 * Revision 1.10 2005/02/02 08:27:58 harpechr
 * Create count users documents DQL action defined. 
 * 
 * Revision 1.9 2005/01/25 20:18:34 harpechr 
 * Create content format statistics DQL statement action defined. 
 * 
 * Revision 1.8 2005/01/24 22:23:34 harpechr 
 * Create change default storage DQL statement action defined. 
 * 
 * Revision 1.7 2005/01/24 12:34:53 harpechr
 * Version 1.0.4 work started.
 * 
 * Revision 1.6 2005/01/18 07:24:47 harpechr 
 * Version 1.0.3 features added. Mainly relation related modifications.
 * 
 * Revision 1.5 2005/01/17 07:21:01 harpechr 
 * Relation icon added. 
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;


/**
 * <H4>Cache for used images.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) Christopher Harper</DD>
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
public class ImageCache
						implements
							IImageCache
{

	/**
	 * The image cache.
	 * 
	 * @since 1.0
	 */
	private static final Map<String, Image>	cache		= new HashMap<String, Image>(
															40);

	/**
	 * Folder used for images/icons. 25-Nov-2005
	 * 
	 * @since 3.0.0
	 */
	private static final String				ICON_PATH	= "icons/"; //$NON-NLS-1$

	/**
	 * The location where the plugin is installed. 25-Nov-2005
	 * 
	 * @since 3.0.0
	 */
	private static URL						INSTALL_URL	= null;

	/**
	 * Load and cache images
	 * 
	 * @since 1.0
	 */
	static
	{
		ImageDescriptor descriptor = getImageDescriptor(IImageCache.DOCBASE_ICON);
		ImageCache.cache
			.put(IImageCache.DOCBASE_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.CREATE_API_ICON);
		ImageCache.cache.put(IImageCache.CREATE_API_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.NEW_API_ICON);
		ImageCache.cache
			.put(IImageCache.NEW_API_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.CREATE_DQL_ICON);
		ImageCache.cache.put(IImageCache.CREATE_DQL_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.FETCH_ICON);
		ImageCache.cache.put(IImageCache.FETCH_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.EXPORT_ICON);
		ImageCache.cache.put(IImageCache.EXPORT_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.DUMP_ICON);
		ImageCache.cache.put(IImageCache.DUMP_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.TYPE_ICON);
		ImageCache.cache.put(IImageCache.TYPE_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.NEW_DQL_ICON);
		ImageCache.cache
			.put(IImageCache.NEW_DQL_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.TYPES_ICON);
		ImageCache.cache.put(IImageCache.TYPES_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.CONNECTED_ICON);
		ImageCache.cache.put(IImageCache.CONNECTED_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.SELECTED_CONNECTED_ICON);
		ImageCache.cache.put(IImageCache.SELECTED_CONNECTED_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.TABLES_ICON);
		ImageCache.cache.put(IImageCache.TABLES_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.TABLE_ICON);
		ImageCache.cache.put(IImageCache.TABLE_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.EXECUTE_ICON);
		ImageCache.cache
			.put(IImageCache.EXECUTE_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.REMOVE_ICON);
		ImageCache.cache.put(IImageCache.REMOVE_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.DISCONNECTED_ICON);
		ImageCache.cache.put(IImageCache.DISCONNECTED_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.CABINETS_ICON);
		ImageCache.cache.put(IImageCache.CABINETS_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.CABINET_ICON);
		ImageCache.cache
			.put(IImageCache.CABINET_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.FOLDER_ICON);
		ImageCache.cache.put(IImageCache.FOLDER_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.VIRTUAL_DOCUMENT_ICON);
		ImageCache.cache.put(IImageCache.VIRTUAL_DOCUMENT_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.VERSIONS_ICON);
		ImageCache.cache.put(IImageCache.VERSIONS_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.GET_CONTENT_ICON);
		ImageCache.cache.put(IImageCache.GET_CONTENT_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.GROUPS_ICON);
		ImageCache.cache.put(IImageCache.GROUPS_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.GROUP_ICON);
		ImageCache.cache.put(IImageCache.GROUP_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.USERS_ICON);
		ImageCache.cache.put(IImageCache.USERS_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.ROLE_ICON);
		ImageCache.cache.put(IImageCache.ROLE_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.CREATE_TYPE_ICON);
		ImageCache.cache.put(IImageCache.CREATE_TYPE_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.CREATE_GROUP_ICON);
		ImageCache.cache.put(IImageCache.CREATE_GROUP_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.CREATE_USER_ICON);
		ImageCache.cache.put(IImageCache.CREATE_USER_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.RELATION_ICON);
		ImageCache.cache.put(IImageCache.RELATION_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.CREATE_RELATION_ICON);
		ImageCache.cache.put(IImageCache.CREATE_RELATION_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.CHANGE_STORAGE_ICON);
		ImageCache.cache.put(IImageCache.CHANGE_STORAGE_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.FORMAT_ICON);
		ImageCache.cache.put(IImageCache.FORMAT_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.CREATE_COUNT_ICON);
		ImageCache.cache.put(IImageCache.CREATE_COUNT_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.CREATE_CONTENT_DISTRIBUTION_ICON);
		ImageCache.cache.put(IImageCache.CREATE_CONTENT_DISTRIBUTION_ICON,
			descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.CHECKIN_ICON);
		ImageCache.cache
			.put(IImageCache.CHECKIN_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.CHECKOUT_ICON);
		ImageCache.cache.put(IImageCache.CHECKOUT_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.CANCEL_CHECKOUT_ICON);
		ImageCache.cache.put(IImageCache.CANCEL_CHECKOUT_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.INBOX_ICON);
		ImageCache.cache.put(IImageCache.INBOX_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.CHECKIN_MAJOR_ICON);
		ImageCache.cache.put(IImageCache.CHECKIN_MAJOR_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.CHECKIN_MINOR_ICON);
		ImageCache.cache.put(IImageCache.CHECKIN_MINOR_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.RETURN_ALL_ROWS_ICON);
		ImageCache.cache.put(IImageCache.RETURN_ALL_ROWS_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.GENERATE_TBO_ICON);
		ImageCache.cache.put(IImageCache.GENERATE_TBO_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.EXPORT_BOF_ICON);
		ImageCache.cache.put(IImageCache.EXPORT_BOF_ICON, descriptor
			.createImage());

		descriptor = getImageDescriptor(IImageCache.MODULE_ICON);
		ImageCache.cache.put(IImageCache.MODULE_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.ASPECT_ICON);
		ImageCache.cache.put(IImageCache.ASPECT_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.SBO_ICON);
		ImageCache.cache.put(IImageCache.SBO_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.TBO_ICON);
		ImageCache.cache.put(IImageCache.TBO_ICON, descriptor.createImage());

		descriptor = getImageDescriptor(IImageCache.MODULE_IMPLEMENTATION);
		ImageCache.cache.put(IImageCache.MODULE_IMPLEMENTATION, descriptor
			.createImage());
		descriptor = getImageDescriptor(IImageCache.MODULE_RUNTIME_ENVIRONMENT);
		ImageCache.cache.put(IImageCache.MODULE_RUNTIME_ENVIRONMENT, descriptor
			.createImage());
		descriptor = getImageDescriptor(IImageCache.MODULE_GENERAL);
		ImageCache.cache.put(IImageCache.MODULE_GENERAL, descriptor
			.createImage());
		descriptor = getImageDescriptor(IImageCache.MODULE_DESCRIPTION);
		ImageCache.cache.put(IImageCache.MODULE_DESCRIPTION, descriptor
			.createImage());
		descriptor = getImageDescriptor(IImageCache.MODULE_DEPENDENCIES);
		ImageCache.cache.put(IImageCache.MODULE_DEPENDENCIES, descriptor
			.createImage());

	}

	/**
	 * Get an image.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 1:41:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param imageCacheKey
	 *            the image key.
	 * @return the found image or null.
	 */
	public static Image getImage(final String imageCacheKey)
	{

		if(ImageCache.cache.containsKey(imageCacheKey))
		{
			return ImageCache.cache.get(imageCacheKey);
		}
		return null;
	}

	/**
	 * Get an image descriptor relative to <code>icons/</code>.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 1:41:07 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param imageName
	 *            partial path to the image.
	 * @return the image descriptior.
	 */
	public static ImageDescriptor getImageDescriptor(final String imageName)
	{

		try
		{
			if(ImageCache.INSTALL_URL == null)
			{
				ImageCache.INSTALL_URL = DCTMPlugin.getInstance().getBundle()
					.getEntry("/"); //$NON-NLS-1$
			}
			final URL url = new URL(ImageCache.INSTALL_URL, ICON_PATH
				+ imageName);
			return ImageDescriptor.createFromURL(url);
		} catch(MalformedURLException e)
		{
			e.printStackTrace();
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}
}
