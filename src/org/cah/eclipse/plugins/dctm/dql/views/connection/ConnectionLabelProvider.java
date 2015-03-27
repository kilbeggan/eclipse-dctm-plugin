/*-
 * $Log: ConnectionLabelProvider.java,v $
 * Revision 1.11  2005/12/04 22:14:40  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.10  2005/12/04 20:29:12  madcook
 * Version 3.0.0 work started.
 * 
 * Revision 1.9 2005/11/21 13:04:31
 * madcook Plugin version 3.0.0 work started and made code to comply with all
 * java 5 settings. 
 * 
 * Revision 1.8 2005/04/01 11:21:01 harpechr 
 * Moved method make title case to Create TBO class so that this class wich has 
 * references to eclipse doesn't have to be instantiated. 
 * 
 * Revision 1.7 2005/03/25 09:21:39 harpechr 
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 * 
 * Revision 1.6 2005/02/09 14:01:33 harpechr 
 * Version 1.0.5 work started.
 * 
 * Revision 1.5 2005/01/24 12:34:54 harpechr 
 * Version 1.0.4 work started.
 * 
 * Revision 1.4 2005/01/17 07:23:59 harpechr 
 * Relation image added to the getImage method. 
 * 
 * Revision 1.3 2005/01/11 14:02:01 harpechr 
 * Changed version number from 1.0.2 to 1.0.3. 
 * 
 * Revision 1.2 2005/01/11 13:47:23 harpechr 
 * Changed hard coded attribute names and type names to references from the bof
 * structure (org.cah.dctm.bof). 
 * 
 * Revision 1.1 2005/01/07 12:37:49 harpechr
 * Version 1.0.2 code. First CVS commit!
 */

package org.cah.eclipse.plugins.dctm.dql.views.connection;

import org.cah.dctm.bof.tbo.GenerateTBOs;
import org.cah.eclipse.plugins.dctm.dql.IImageCache;
import org.cah.eclipse.plugins.dctm.dql.ImageCache;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * <H4>Provider of labels and images for the models.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 25, 2004 1:06:28 PM.</DD>
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
public class ConnectionLabelProvider
									extends
										LabelProvider
{

	/**
	 * First created instance.
	 * 
	 * @since 1.0
	 */
	private static ConnectionLabelProvider	instance	= null;

	/**
	 * synchronization helper.
	 * 
	 * @since 1.0
	 */
	private static Object					SEMAPHORE	= new Object();

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 10, 2004 4:00:16 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public ConnectionLabelProvider()
	{

		setInstance(this);
	}

	/**
	 * Always returns the first created instance of this class.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:33:25 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return first created instance.
	 */
	public static ConnectionLabelProvider getInstance()
	{

		synchronized(SEMAPHORE)
		{
			if(ConnectionLabelProvider.instance == null)
			{
				new ConnectionLabelProvider();
			}
		}
		return ConnectionLabelProvider.instance;
	}

	/**
	 * Set the instance.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 10:17:04 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anInstance
	 *            the first created instance.
	 */
	protected static void setInstance(final ConnectionLabelProvider anInstance)
	{

		if(ConnectionLabelProvider.instance == null)
		{
			ConnectionLabelProvider.instance = anInstance;
		}
	}

	/**
	 * Get a image for a model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:55:59 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param model
	 *            the model that requires an image.
	 * @return a image for the given model.
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(final Object model)
	{

		Image image = null;
		if(model instanceof ConnectionModel)
		{
			ConnectionModel connectionModel = (ConnectionModel) model;
			if(model == ConnectionView.getInstance().getSelectedConnection())
			{
				image = ImageCache
					.getImage(IImageCache.SELECTED_CONNECTED_ICON);
			} else if(connectionModel.isConnected())
			{
				image = ImageCache.getImage(IImageCache.CONNECTED_ICON);
			} else
			{
				image = ImageCache.getImage(IImageCache.DISCONNECTED_ICON);
			}
		} else if(model instanceof DocbaseModel)
		{
			image = ImageCache.getImage(IImageCache.DOCBASE_ICON);
		} else if(model instanceof TypesModel)
		{
			image = ImageCache.getImage(IImageCache.TYPES_ICON);
		} else if(model instanceof TypeModel)
		{
			image = ImageCache.getImage(IImageCache.TYPE_ICON);
		} else if(model instanceof TablesModel)
		{
			image = ImageCache.getImage(IImageCache.TABLES_ICON);
		} else if(model instanceof TableModel)
		{
			image = ImageCache.getImage(IImageCache.TABLE_ICON);
		} else if(model instanceof CabinetsModel)
		{
			image = ImageCache.getImage(IImageCache.CABINETS_ICON);
		} else if(model instanceof CabinetModel)
		{
			image = ImageCache.getImage(IImageCache.CABINET_ICON);
		} else if(model instanceof FolderModel)
		{
			image = ImageCache.getImage(IImageCache.FOLDER_ICON);
		} else if(model instanceof DocumentModel)
		{
			image = ImageCache.getImage(IImageCache.VIRTUAL_DOCUMENT_ICON);
		} else if(model instanceof GroupsModel)
		{
			image = ImageCache.getImage(IImageCache.GROUPS_ICON);
		} else if(model instanceof GroupModel)
		{
			if(((GroupModel) model).isRole())
			{
				image = ImageCache.getImage(IImageCache.ROLE_ICON);
			} else
			{
				image = ImageCache.getImage(IImageCache.GROUP_ICON);
			}
		} else if(model instanceof UsersModel)
		{
			image = ImageCache.getImage(IImageCache.USERS_ICON);
		} else if(model instanceof RelationTypeModel)
		{
			image = ImageCache.getImage(IImageCache.RELATION_ICON);
		} else if(model instanceof InboxModel)
		{
			image = ImageCache.getImage(IImageCache.INBOX_ICON);
		} else if(model instanceof ModulesModel)
		{
			image = ImageCache.getImage(IImageCache.MODULE_ICON);
		} else if(model instanceof AspectModuleModel)
		{
			image = ImageCache.getImage(IImageCache.ASPECT_ICON);
		} else if(model instanceof SBOModuleModel)
		{
			image = ImageCache.getImage(IImageCache.SBO_ICON);
		} else if(model instanceof TBOModuleModel)
		{
			image = ImageCache.getImage(IImageCache.TBO_ICON);
		} else if(model instanceof OtherModuleModel)
		{
			image = ImageCache.getImage(IImageCache.MODULE_ICON);
		}
		return image;
	}

	/**
	 * Get the label text for a model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 6:56:13 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param model
	 *            model that requires a label value.
	 * @return the created label.
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(final Object model)
	{

		String text = null;
		if(model instanceof Model)
		{
			final Model ourModel = (Model) model;
			text = ourModel.getName();
			if(model instanceof TypeModel)
			{
				text = GenerateTBOs.makeTitleCase(text, true)
					+ " (" + text + ')'; //$NON-NLS-1$
			} else if(model instanceof TableModel)
			{
				if(text.indexOf('.') > -1)
				{
					text = GenerateTBOs.makeTitleCase(text.substring(text
						.indexOf('.') + 1), true)
						+ " (" + text + ')'; //$NON-NLS-1$
				} else
				{
					text = GenerateTBOs.makeTitleCase(text, true)
						+ " (" + text + ')'; //$NON-NLS-1$
				}
			}
		} else
		{
			text = model.toString();
		}
		return text;
	}
}
