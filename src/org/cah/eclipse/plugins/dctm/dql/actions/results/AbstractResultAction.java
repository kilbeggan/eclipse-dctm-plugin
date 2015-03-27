/*-
 * $Log: AbstractResultAction.java,v $
 * Revision 1.6  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.5  2005/12/04 20:25:44  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.4  2005/11/22 13:27:07  madcook
 * Eclipse 3.2 compiler check warnings modified.
 * 
 * Revision 1.3 2005/11/21 13:04:32 madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5
 * settings. 
 * 
 * Revision 1.2 2005/04/01 11:27:59 harpechr 
 * Reference to a static string changed to an interface. 
 * 
 * Revision 1.1 2005/03/25 09:09:35 harpechr
 * Abstract results action for actions that require the presence of r_object_id
 * in the results.
 */

package org.cah.eclipse.plugins.dctm.dql.actions.results;

import java.io.File;
import java.util.HashMap;
import java.util.Vector;

import org.cah.dctm.bof.tbo.persistent.format.IFormat;
import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;
import org.cah.dctm.bof.tbo.persistent.sysobject.document.IDocument;
import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.actions.AbstractBaseAction;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.ResultView;
import org.eclipse.swt.widgets.TableItem;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;


/**
 * <H4>Basic action functionality for actions that require the usage of
 * r_object_id in the results table.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) 2005 Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>17.3.2005 13:15:15.</DD>
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
 * @author Christopher Harper account: HARPEC
 * @version 3.0.0
 * @since 1.0.5
 */

public abstract class AbstractResultAction
											extends
												AbstractBaseAction
{

	/**
	 * Storege for dosextensions with the content type as the key.
	 * 
	 * @since 1.0.1
	 */
	private static HashMap<String, String>	dosExtensions	= null;

	/**
	 * Attribute names in the current result table.
	 * 
	 * @since 1.0.5
	 */

	private Vector<String>					attributes		= null;

	/**
	 * the r_object_id value.
	 * 
	 * @since 1.0.5
	 */

	private String							rObjectId		= null;

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 13:15:15</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 */
	public AbstractResultAction()
	{

		super();
	}

	/**
	 * Get the dos extension container.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 6:20:05 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.1
	 * @return the extensions.
	 */
	public static HashMap<String, String> getDosExtensions()
	{

		return AbstractResultAction.dosExtensions;
	}

	/**
	 * Remove unwanted characters from a string.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>Mar 17, 2003 5:26:18 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>Christopher Harper account: dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.1
	 * @param originalString
	 *            string to check.
	 * @return the string that can be used on any file system as folder or file
	 *         names.
	 */
	public static String makeFileSystemCompliant(final String originalString)
	{

		StringBuffer retVal = new StringBuffer(originalString.length());
		char[] chars = originalString.toCharArray();

		for(int ind = 0; ind < chars.length; ind++)
		{

			char ch = chars[ind];

			if(((ch >= 1) && (ch <= 31)) || (ch == '"') || (ch == '*')
				|| (ch == '/') || (ch == ':') || (ch == '<') || (ch == '>')
				|| (ch == '?') || (ch == '|') || (ch == '\\') || (ch > 127))
			{

				retVal.append('_');
			} else
			{

				retVal.append(ch);
			}
		}
		return(retVal.toString());
	}

	/**
	 * Set the dos extensions storage.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 6:20:08 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.1
	 * @param aDosExtensions
	 *            the new extensions.
	 */
	private static void setDosExtensions(
											final HashMap<String, String> aDosExtensions)
	{

		AbstractResultAction.dosExtensions = aDosExtensions;
	}

	/**
	 * Run the get file action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 5:13:47 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.1
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run()
	{

		if(isOkToExecute())
		{
			final TableItem[] selected = ((ResultView) getView())
				.getSelectedTable().getSelection();
			for(int selectionIndex = 0; selectionIndex < selected.length; selectionIndex++)
			{
				final TableItem tableRow = selected[selectionIndex];
				if(isRowOkToExecute(tableRow))
				{
					runRow(tableRow);
				}
			}
		}
	}

	/**
	 * Get the current attribute names.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 16:33:57</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return the attribute names in the current result table.
	 */

	protected Vector<String> getAttributes()
	{

		return this.attributes;
	}

	/**
	 * Get the name of the file.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 6:02:46 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.1
	 * @param session
	 *            a valid Docbase session.
	 * @param objectId
	 *            id of the object.
	 * @return the filesystem compliant naem.
	 * @throws DfException
	 *             if the name can't be returned.
	 */
	protected String getFileName(final IDfSession session, final String objectId) throws DfException
	{

		if(getDosExtensions() == null)
		{
			populateDosExtensions(session);
		}
		final String tag = ConnectionView.getInstance().getSelectedConnection()
			.getTag();
		MessageView.getInstance().addMessage(
			tag + " get,c," + objectId + ',' + ISysObject.OBJECT_NAME); //$NON-NLS-1$
		final String objectName = makeFileSystemCompliant(session.apiGet(
			"get", objectId + ',' + ISysObject.OBJECT_NAME)); //$NON-NLS-1$
		MessageView.getInstance().addMessage(tag + objectName);
		MessageView.getInstance().addMessage(
			tag + " get,c," + objectId + ',' + ISysObject.A_CONTENT_TYPE); //$NON-NLS-1$
		final String contentType = session.apiGet(
			"get", objectId + ',' + ISysObject.A_CONTENT_TYPE); //$NON-NLS-1$
		MessageView.getInstance().addMessage(tag + contentType);
		if(getDosExtensions().containsKey(contentType))
		{
			final String dosExtension = getDosExtensions().get(contentType);
			if(!objectName.endsWith(dosExtension))
			{
				return objectName + '.' + dosExtension;
			}
		}
		return objectName;
	}

	/**
	 * Get the object id value
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 16:56:13</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return the id value.
	 */
	protected String getRObjectId()
	{

		return this.rObjectId;
	}

	/**
	 * Check if a column is available.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 13:02:51</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param requiredColumn
	 * @return true if the column is present.
	 */
	protected boolean isColumAvailable(final String requiredColumn)
	{

		if((getAttributes() != null)
			&& (getAttributes().contains(requiredColumn)))
		{
			return true;
		}
		return false;
	}

	/**
	 * Can the action be executed.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 12:57:55</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return true if the action can be run.
	 */
	@SuppressWarnings("unchecked")
	protected boolean isOkToExecute()
	{

		if(getView() instanceof ResultView)
		{
			final ResultView resultView = (ResultView) getView();
			if(resultView.getSelectedTable() != null)
			{
				final TableItem[] selected = resultView.getSelectedTable()
					.getSelection();
				if((selected != null) && (selected.length > 0))
				{
					setAttributes((Vector<String>) ((ResultView) getView())
						.getSelectedTable().getData());
					return true;
				}
				MessageView.getInstance().addMessage("No result row selected."); //$NON-NLS-1$
			} else
			{
				MessageView.getInstance().addMessage(
					"No results table selected."); //$NON-NLS-1$
			}
		} else
		{
			MessageView.getInstance().addMessage("Result view not selected."); //$NON-NLS-1$
		}
		return false;
	}

	/**
	 * Check if it's ok to execute the action on a table row.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 12:02:50</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param tableItem
	 *            the row to check.
	 * @return true if it's ok to execute the action on the given table row.
	 */
	protected boolean isRowOkToExecute(final TableItem tableItem)
	{

		if(tableItem != null)
		{
			return true;
		}
		return false;
	}

	/**
	 * Check it the r_object_id is of valid format.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 17:04:21</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @param anIdValue
	 *            an id value to evaluate.
	 * @param writeLog
	 *            Should an error message about an invalid ID be printed into
	 *            the log.
	 * @since 1.0.5
	 * @return true if the id length is 16 and its a hex string that can be
	 *         converted to long.
	 */
	protected boolean isValidIdValue(final String anIdValue,
										final boolean writeLog)
	{

		try
		{
			if((anIdValue != null) && (anIdValue.length() == 16)
				&& (Long.parseLong(anIdValue, 16) > 0)
				&& (anIdValue.startsWith(IDocument.TYPE_DM_DOCUMENT_PREFIX))
				&& (!anIdValue.equals(DfId.DF_NULLID_STR)))
			{
				return true;
			}
		} catch(final NumberFormatException swallow)
		{
			/*
			 * Just a check to werify that the object id was a proper hex value.
			 * If the conversion to long throws an exception we just return
			 * false.
			 */
		}
		if(writeLog)
		{
			MessageView
				.getInstance()
				.addMessage(
					(new StringBuffer(ConnectionView.getInstance()
						.getSelectedConnection().getTag())
						.append(" '").append(anIdValue) //$NON-NLS-1$
						.append(
							"' is not a valid ID. A valid ID is 16 characters long, can be converted to long from hex using the radix 16 and the ID starts with '") //$NON-NLS-1$
						.append(IDocument.TYPE_DM_DOCUMENT_PREFIX).append("'.")).toString()); //$NON-NLS-1$

		}
		return false;
	}

	/**
	 * Populate the dos extensions and content type names.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 6:07:13 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.1
	 * @param session
	 *            a valid Docbase session.
	 */
	protected void populateDosExtensions(final IDfSession session)
	{

		setDosExtensions(new HashMap<String, String>());
		final StringBuffer formatQuery = new StringBuffer("select distinct ").append(IFormat.NAME).append(" , ") //$NON-NLS-1$ //$NON-NLS-2$
			.append(IFormat.DOS_EXTENSION)
			.append(" from ").append(IFormat.TYPE_DM_FORMAT).append(" where ") //$NON-NLS-1$ //$NON-NLS-2$
			.append(IFormat.NAME)
			.append(" is not nullstring and ").append(IFormat.DOS_EXTENSION) //$NON-NLS-1$
			.append(" is not nullstring"); //$NON-NLS-1$
		IDfQuery query = new DfQuery();
		query.setDQL(formatQuery.toString());
		IDfCollection results = null;
		try
		{
			MessageView.getInstance().addMessage(
				session.getLoginInfo().getDomain() + '\\'
					+ session.getLoginUserName() + '@'
					+ session.getDocbaseName() + ' ' + query.getDQL());
			results = query.execute(session, IDfQuery.DF_EXECREAD_QUERY);
			while(results.next())
			{
				getDosExtensions().put(results.getString(IFormat.NAME),
					results.getString(IFormat.DOS_EXTENSION));
			}
		} catch(final DfException dex)
		{
			try
			{
				MessageView
					.getInstance()
					.addMessage(
						session.getLoginInfo().getDomain()
							+ '\\'
							+ session.getLoginUserName()
							+ '@'
							+ session.getDocbaseName()
							+ " Failure running format query." + IDCTMPlugin.NEWLINE //$NON-NLS-1$
							+ dex.getMessage());
			} catch(DfException swallow)
			{
				/* Do nothing. */
			}
		} finally
		{
			if(results != null)
			{

				try
				{
					results.close();
				} catch(DfException swallow)
				{
					try
					{
						MessageView
							.getInstance()
							.addMessage(
								session.getLoginInfo().getDomain()
									+ '\\'
									+ session.getLoginUserName()
									+ '@'
									+ session.getDocbaseName()
									+ " Failure closing collection.\n" + swallow.getMessage()); //$NON-NLS-1$
					} catch(DfException swallow2)
					{
						/* Do nothing. */
					}
				}
			}
		}
	}

	/**
	 * Remove a given file from the file system.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 15:51:03</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param aFileName
	 *            name of the file to remove.
	 */
	protected void removeFile(final String aFileName)
	{

		final File fileToRemove = new File(aFileName);
		if(fileToRemove.exists())
		{
			fileToRemove.delete();
		}
	}

	/**
	 * Execute actions for a single row.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 13:14:10</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param tableItem
	 *            the row to handle
	 * @return true if the action was OK.
	 */
	protected abstract boolean runRow(final TableItem tableItem);

	/**
	 * Set the object id string.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 16:53:34</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param anRObjectId
	 *            the rows object id.
	 */
	protected void setRObjectId(final String anRObjectId)
	{

		this.rObjectId = anRObjectId;
	}

	/**
	 * Set the attributes of the current results.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>17.3.2005 16:37:05</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param anAttributes
	 *            the attributes.
	 */

	private void setAttributes(final Vector<String> anAttributes)
	{

		this.attributes = anAttributes;
	}

}
