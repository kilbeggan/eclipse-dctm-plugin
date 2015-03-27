/*-
 * $Log: ConnectionContentProvider.java,v $
 * Revision 1.16  2005/12/04 20:29:12  madcook
 * Version 3.0.0 work started.
 * 
 * 
 * Revision 1.15 2005/11/21 14:53:38 madcook
 * Old 1.4 style code removed. 
 * 
 * Revision 1.14 2005/11/21 13:04:31 madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5
 * settings. 
 * 
 * Revision 1.13 2005/04/01 11:28:26 harpechr
 * Reference to a static string changed to an interface. 
 * 
 * Revision 1.12 2005/03/25 09:21:38 harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 * 
 * Revision 1.11 2005/03/14 12:16:08 harpechr
 * Type & relation structure sort changed.
 * 
 * Revision 1.10 2005/02/22 15:46:55 harpechr
 * Changed the code so that all saved connection information is base 64 encoded
 * to get round the name restrictions.
 * 
 * Revision 1.9 2005/02/09 13:55:18 harpechr
 * Added previous DQL queries functionality and modified the message view so 
 * that its real time. 
 * 
 * Revision 1.8 2005/01/24 12:34:54 harpechr
 * Version 1.0.4 work started. 
 * 
 * Revision 1.7 2005/01/18 07:24:48 harpechr
 * Version 1.0.3 features added. Mainly relation related modifications. 
 * 
 * Revision 1.6 2005/01/11 14:15:35 harpechr
 * Moved the table model in the structure and modified the table model creation 
 * in connection content provider. 
 * 
 * Revision 1.5 2005/01/11 14:02:01 harpechr
 * Changed version number from 1.0.2 to 1.0.3. 
 * 
 * Revision 1.4 2005/01/11 13:47:22 harpechr
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof). 
 * 
 * Revision 1.3 2005/01/10 08:22:13 harpechr
 * Changed the copyright statement from my work standard to my name and the 
 * licence to GNU.
 * 
 * Revision 1.2 2005/01/09 10:46:55 harpechr
 * Renamed classes that were abstract by prefixing the class name with 
 * 'Abstract'.
 * 
 * Revision 1.1 2005/01/07 12:37:49 harpechr
 * Version 1.0.2 code. First CVS commit!
 */

package org.cah.eclipse.plugins.dctm.dql.views.connection;

import java.io.File;
import java.io.FileWriter;
import java.security.Key;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.cah.dctm.bof.tbo.persistent.IPersistentObject;
import org.cah.dctm.bof.tbo.persistent.group.IGroup;
import org.cah.dctm.bof.tbo.persistent.relationtype.IRelationType;
import org.cah.dctm.bof.tbo.persistent.sysobject.ISysObject;
import org.cah.dctm.bof.tbo.persistent.sysobject.document.IDocument;
import org.cah.dctm.bof.tbo.persistent.sysobject.folder.IFolder;
import org.cah.dctm.bof.tbo.persistent.sysobject.folder.cabinet.ICabinet;
import org.cah.dctm.bof.tbo.persistent.sysobject.registered.IRegistered;
import org.cah.dctm.bof.tbo.persistent.type.IType;
import org.cah.eclipse.plugins.dctm.dql.DCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.IImageCache;
import org.cah.eclipse.plugins.dctm.dql.ImageCache;
import org.cah.eclipse.plugins.dctm.dql.actions.connection.dql.PreviousDQLStatementAction;
import org.cah.eclipse.plugins.dctm.dql.editors.APIEditor;
import org.cah.eclipse.plugins.dctm.dql.editors.DQLEditor;
import org.cah.eclipse.plugins.dctm.dql.editors.api.APIEditorInput;
import org.cah.eclipse.plugins.dctm.dql.editors.api.APIKeywordScanner;
import org.cah.eclipse.plugins.dctm.dql.editors.api.APIParser;
import org.cah.eclipse.plugins.dctm.dql.editors.dql.DQLEditorInput;
import org.cah.eclipse.plugins.dctm.dql.editors.dql.DQLParser;
import org.cah.eclipse.plugins.dctm.dql.views.ConnectionView;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.ResultView;
import org.cah.util.crypto.Crypto;
import org.cah.util.crypto.ICrypto;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.IDfId;
import com.documentum.xerces_2_6_2.xml.serialize.OutputFormat;
import com.documentum.xerces_2_6_2.xml.serialize.XMLSerializer;


/**
 * <H4>Provides a given connection with data.</H4>
 * <DL>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 25, 2004 12:45:06 PM.</DD>
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
public class ConnectionContentProvider
										implements
											ITreeContentProvider
{

	/**
	 * Container for documents checked out with the plugin.
	 * 
	 * @since 1.0.5
	 */
	private static final Hashtable<String, String>			checkedOutDocuments		= new Hashtable<String, String>();

	/**
	 * DQL statements executed during the session.
	 * 
	 * @since 1.0.5
	 */
	private static final Vector<PreviousDQLStatementAction>	executedDQLStatements	= new Vector<PreviousDQLStatementAction>();

	/**
	 * Sole instance of this class.
	 * 
	 * @since 1.0
	 */
	private static ConnectionContentProvider				instance				= null;

	/**
	 * Invisible model.
	 * 
	 * @since 1.0
	 */
	private static InvisibleModel							invisibleRootModel		= new InvisibleModel(
																						DCTMPlugin
																							.getResourceString(IDCTMPlugin.MSG_DCTM));

	/**
	 * Object used for sychronization.
	 * 
	 * @since 1.0
	 */
	private static Object									SEMAPHORE				= new Object();

	/**
	 * Current docbase information.
	 * 
	 * @since 1.0
	 */
	private DocbaseModel									docbaseModel			= null;

	/**
	 * Has the connection information changed.
	 * 
	 * @since 1.0
	 */
	private boolean											hasChanged				= false;

	/**
	 * The selected connection.
	 * 
	 * @since 1.0
	 */
	private ConnectionModel									selectedConnectionModel	= null;

	/**
	 * DQL doesn't like single Quotes so they need to be escaped. O'Brial
	 * O''Brian.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>Mar 17, 2003 5:23:30 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>CAH account:dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param original
	 *            String to escape.
	 * @return the escaped string.
	 */
	public static String escapeSingleQuotes(final String original)
	{

		return original.replaceAll("'", "''"); //$NON-NLS-1$ //$NON-NLS-2$

	}

	/**
	 * Get container for documents checked out with this plug-in. Key =
	 * r_object_id, Value = path to the document.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 13:46:19</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return Returns the checkedOutDocuments
	 */

	public static Hashtable<String, String> getCheckedOutDocuments()
	{

		return ConnectionContentProvider.checkedOutDocuments;
	}

	/**
	 * Get an instance of this class.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:55:08 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return return the first created instance.
	 */
	public static ConnectionContentProvider getInstance()
	{

		synchronized(SEMAPHORE)
		{
			if(ConnectionContentProvider.instance == null)
			{
				new ConnectionContentProvider();
			}
		}
		return ConnectionContentProvider.instance;
	}

	/**
	 * Set the shared instance.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:55:05 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anInstance
	 *            the shared instance.
	 */
	protected static void setInstance(final ConnectionContentProvider anInstance)
	{

		ConnectionContentProvider.instance = anInstance;
	}

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:45:06 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public ConnectionContentProvider()
	{

		super();
		setInstance(this);
	}

	/**
	 * Connect to a Docbase.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:38:50 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param connectionModel
	 *            the connection information.
	 */
	public void connect(final ConnectionModel connectionModel)
	{

		IDfSession connection = null;
		try
		{
			if(!connectionModel.isConnected())
			{
				connectionModel.prepareConnection();
			}
			connection = connectionModel.getSession();
			if(connection == null)
			{
				throw new DfException(-1, "Connection information invalid"); //$NON-NLS-1$
			}
			/* Add Docbase information. */
			populateConnectionModel(connectionModel);
			/* Set selected connection. */
			setSelectedConnectionModel(connectionModel);

			/* Find buffer editor. */
			IEditorPart bufferEditor = null;
			final IEditorReference[] editorRefs = ConnectionView.getInstance()
				.getViewSite().getPage().getEditorReferences();
			for(int editorIndex = 0; editorIndex < editorRefs.length; editorIndex++)
			{
				final IEditorPart editorPart = editorRefs[editorIndex]
					.getEditor(false);
				if((editorPart != null)
					&& ((editorPart.getEditorInput() instanceof DQLEditorInput) || (editorPart
						.getEditorInput() instanceof APIEditorInput)))
				{
					bufferEditor = editorPart;
					break;
				}
			}
			/* If editor exists, setFocus. */
			if(bufferEditor != null)
			{
				bufferEditor.setFocus();
			}

		} catch(DfException dex)
		{
			MessageView.getInstance().addMessage(
				"Failure return a Docbase session.\n" + dex.getMessage()); //$NON-NLS-1$
		} catch(Throwable t)
		{
			MessageView.getInstance().addMessage(t);
		} finally
		{
			connectionModel.releaceConnection(connection);
		}
	}

	/**
	 * Read the connection information from the saved XML settings.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:38:39 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param document
	 *            the settings xml document.
	 * @return the connection models.
	 */
	public ConnectionModel[] createConnectionModelsFromXml(
															final Document document)
	{

		final ArrayList<ConnectionModel> connectionModelList = new ArrayList<ConnectionModel>();
		try
		{
			final Node rootNode = document.getLastChild();
			final NodeList connectionNodeList = rootNode.getChildNodes();
			for(int connectionIndex = 0; connectionIndex < connectionNodeList
				.getLength(); connectionIndex++)
			{
				final Node connectionNode = connectionNodeList
					.item(connectionIndex);
				if((connectionNode == null)
					|| (connectionNode.getNodeType() != Node.ELEMENT_NODE))
				{
					continue;
				}
				if(connectionNode.getNodeName().equals(
					ConnectionModel.PREVIOUS_DQL_STATEMENTS_NODE))
				{
					final NodeList statementNodeList = connectionNode
						.getChildNodes();
					for(int statementIndex = 0; statementIndex < statementNodeList
						.getLength(); statementIndex++)
					{
						final Node statement = statementNodeList
							.item(statementIndex);
						if(statement.getNodeType() == Node.ELEMENT_NODE)
						{
							final String DQLStatement = new String(Crypto
								.getDecoder().decodeBuffer(
									statement.getFirstChild().getNodeValue()));
							final PreviousDQLStatementAction executed = new PreviousDQLStatementAction(
								DQLStatement);
							executed.setText((DQLStatement.length() > 40)
								? DQLStatement.substring(0, 40) + " ..." //$NON-NLS-1$
								: DQLStatement);
							executed.setToolTipText(DQLStatement);
							executed.setImageDescriptor(ImageCache
								.getImageDescriptor(IImageCache.EXECUTE_ICON));
							executed.init(ConnectionView.getInstance());
							ConnectionContentProvider.getInstance()
								.getExecutedDQLStatements().add(executed);
						}
					}
				} else if(connectionNode.getNodeName().equals(
					ConnectionModel.CHECKED_OUT_NODE))
				{
					final NodeList checkedOutNodeList = connectionNode
						.getChildNodes();
					for(int statementIndex = 0; statementIndex < checkedOutNodeList
						.getLength(); statementIndex++)
					{
						final Node statement = checkedOutNodeList
							.item(statementIndex);
						if(statement.getNodeType() == Node.ELEMENT_NODE)
						{
							final String objectId = statement.getNodeName()
								.substring(3);
							final String checkOutPath = new String(Crypto
								.getDecoder().decodeBuffer(
									statement.getFirstChild().getNodeValue()));
							ConnectionContentProvider.getCheckedOutDocuments()
								.put(objectId, checkOutPath);
						}
					}

				} else
				{
					final ConnectionModel connectionModel = new ConnectionModel();
					final String connectionName = connectionNode.getNodeName();
					connectionModel.setName(connectionName);
					final NodeList dataNodeList = connectionNode
						.getChildNodes();
					String passwordValue = null;
					for(int dataIndex = 0; dataIndex < dataNodeList.getLength(); dataIndex++)
					{
						final Node dataNode = dataNodeList.item(dataIndex);
						if(dataNode.getNodeType() == Node.ELEMENT_NODE)
						{
							final String nodeName = dataNode.getNodeName();
							final Node firstChild = dataNode.getFirstChild();
							final String nodeValue;
							if(firstChild != null)
							{
								nodeValue = firstChild.getNodeValue();
							} else
							{
								nodeValue = ""; //$NON-NLS-1$
							}
							if(nodeName.equals(ConnectionModel.USERNAME_NODE))
							{
								connectionModel.setUserName(new String(Crypto
									.getDecoder().decodeBuffer(nodeValue)));
							} else if(nodeName
								.equals(ConnectionModel.PASSWORD_NODE))
							{
								passwordValue = nodeValue;
							} else if(nodeName
								.equals(ConnectionModel.DOMAIN_NODE))
							{
								connectionModel.setDomain(new String(Crypto
									.getDecoder().decodeBuffer(nodeValue)));
							} else if(nodeName
								.equals(ConnectionModel.DOCBASE_NODE))
							{
								connectionModel.setDocbase(new String(Crypto
									.getDecoder().decodeBuffer(nodeValue)));
							}

						}
					}
					final String buildKeyFrom = new StringBuffer(
						connectionModel.getName()).append(
						connectionModel.getUserName()).append(
						connectionModel.getDomain()).append(
						connectionModel.getDocbase())
						.append("01234567").toString(); //$NON-NLS-1$
					final char[] aPassPhrase = buildKeyFrom.toCharArray();
					final byte[] temp = buildKeyFrom.getBytes();
					final byte[] aSalt = {temp[0], temp[1], temp[2], temp[3],
						temp[4], temp[5], temp[6], temp[7]};
					final ICrypto crypt = new Crypto(
						ICrypto.ALGORITHM_PBEMD5DES_CBC, aSalt, aPassPhrase);
					final Key aKey = crypt.getNewKey();
					crypt.setKey(aKey);
					try
					{
						connectionModel.setPassword(crypt.decryptBase64(
							passwordValue, ICrypto.CHARACTER_SET_UTF_8));
					} catch(Exception ex)
					{
						/*
						 * Swallow this so that it works with previous versions
						 * that have password values in clear text.
						 */
						connectionModel.setPassword(passwordValue);
					}
					connectionModelList.add(connectionModel);
				}
			}
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return connectionModelList
			.toArray(new ConnectionModel[connectionModelList.size()]);
	}

	/**
	 * Disconnect the Docbase session.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:38:58 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param connectionModel
	 *            the connection information.
	 */
	public void disconnect(final ConnectionModel connectionModel)
	{

		/* Disconnect */
		connectionModel.disconnect();
		/* If connectionModel was selected, deselect it. */
		if(connectionModel.equals(getSelectedConnectionModel()))
		{
			setSelectedConnectionModel(null);
			setSelectedDocbaseModel(null);
		}
		/* Remove children from tree. */
		Model[] model = connectionModel.getChildren();
		for(int childIndex = 0; childIndex < model.length; childIndex++)
		{
			connectionModel.removeChild(model[childIndex]);
		}
	}

	/**
	 * Do nothing.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:45:42 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose()
	{

		/* Nothing done here. */
	}

	/**
	 * Execute API statements.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 9:23:53 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param editor
	 *            the API editor.
	 */
	public void executeAPI(final APIEditor editor)
	{

		/* Execute selected text. */
		String apiText = editor.getSelectedText();
		/* If none selected execute all. */
		if(apiText.equals("")) //$NON-NLS-1$
		{
			apiText = editor.getText();
		}
		final Vector<String[]> statements = APIParser.prepareAPI(apiText);
		/* If no statements, don't execute. */
		if((statements == null) || (statements.size() == 0))
		{
			return;
		}
		IDfSession connection = null;
		try
		{
			connection = getInstance().getSelectedConnectionModel()
				.getSession();
			try
			{
				for(final String[] api: statements)
				{
					if(APIKeywordScanner.APIEXEC.contains(api[3]))
					{
						apiExec(connection, api);
					} else if(APIKeywordScanner.APISET.contains(api[3]))
					{
						apiSet(connection, api);
					} else if(APIKeywordScanner.APIGET.contains(api[3]))
					{
						apiGet(connection, api);
					}
				}
			} catch(DfException dex)
			{
				MessageView.getInstance().addMessage(dex);
			}
		} catch(DfException dex)
		{
			MessageView.getInstance().addMessage(
				"Failed to aquire a session.\n" + dex.getMessage()); //$NON-NLS-1$
		} finally
		{
			getInstance().getSelectedConnectionModel().releaceConnection(
				connection);
		}
	}

	/**
	 * Execute DQL statements.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 8:29:59 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param editor
	 *            the DQL editor
	 * @param newTab
	 *            true if results need to go onto new results tables.
	 */
	public void executeDQL(final DQLEditor editor, boolean newTab)
	{

		/* Execute selected text. */
		String dqlText = editor.getSelectedText();
		/* If none selected execute all. */
		if(dqlText.equals("")) //$NON-NLS-1$
		{
			dqlText = editor.getText();
		}
		final String[] statements = DQLParser.prepareDQL(dqlText);
		/* If no statements, don't execute. */
		if((statements == null) || (statements.length == 0))
		{
			return;
		}
		IDfSession connection = null;
		try
		{
			connection = getInstance().getSelectedConnectionModel()
				.getSession();
			final IDfQuery query = new DfQuery();
			for(int statementIndex = 0; statementIndex < statements.length; statementIndex++)
			{
				if(statementIndex > 0)
				{
					newTab = true;
				}
				/* Execute query. */
				try
				{
					query.setDQL(statements[statementIndex]);
					MessageView.getInstance().addMessage(
						connection.getLoginInfo().getDomain() + '\\'
							+ connection.getLoginUserName() + '@'
							+ connection.getDocbaseName() + ' '
							+ query.getDQL());
					ResultView.getInstance().addResultSet(
						query.execute(connection, IDfQuery.DF_EXEC_QUERY),
						"Query results", newTab, query.getDQL(), true); //$NON-NLS-1$

					final PreviousDQLStatementAction executed = new PreviousDQLStatementAction(
						query.getDQL());
					executed
						.setText((statements[statementIndex].length() > 40)
							? statements[statementIndex].substring(0, 40)
								+ " ..." : statements[statementIndex]); //$NON-NLS-1$
					executed.setToolTipText(statements[statementIndex]);
					executed.setImageDescriptor(ImageCache
						.getImageDescriptor(IImageCache.EXECUTE_ICON));
					executed.init(ConnectionView.getInstance());
					for(int index = 0; index < getExecutedDQLStatements()
						.size(); index++)
					{
						final PreviousDQLStatementAction statement = getExecutedDQLStatements()
							.get(index);
						if(executed.equals(statement))
						{
							getExecutedDQLStatements().remove(statement);
							index--;
						}
					}
					getExecutedDQLStatements().add(0, executed);
					while(getExecutedDQLStatements().size() > 20)
					{
						getExecutedDQLStatements().remove(
							getExecutedDQLStatements().size() - 1);
					}
					setHasChanged(true);
				} catch(final DfException dex)
				{
					MessageView.getInstance().addMessage(dex.getMessage());
				}
			}
		} catch(final DfException dex)
		{
			MessageView.getInstance().addMessage(
				"Failed to aquire a session.\n" + dex.getMessage()); //$NON-NLS-1$
		} finally
		{
			getInstance().getSelectedConnectionModel().releaceConnection(
				connection);
		}
	}

	/**
	 * Open up a cabinet.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 10:03:39 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aCabinetModel
	 *            the cabinet to open.
	 * @param refresh
	 *            re-query.
	 */
	public void expandCabinetModel(final CabinetModel aCabinetModel,
									final boolean refresh)
	{

		expandFolderModel(aCabinetModel, refresh);
	}

	/**
	 * Open docbase model
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:58:57 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDocbaseModel
	 *            the model to open.
	 * @param refresh
	 *            should data be refreshed.
	 */
	public void expandDocbaseModel(final DocbaseModel aDocbaseModel,
									boolean refresh)
	{

		/* If expanded once and no refresh, don't repopulate. */
		final InvisibleModel invisibleModel = getInvisibleRootModel(aDocbaseModel);
		if(invisibleModel == null)
		{
			if(!refresh)
			{
				return;
			}
		} else
		{
			refresh = true;
		}
		if(refresh)
		{
			aDocbaseModel.removeChildren();
			populateDocbaseModel(aDocbaseModel);
		}
	}

	/**
	 * Expand a virtual document.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 10:03:19 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDocumentModel
	 *            the virtual document.
	 * @param refresh
	 *            should the content be re-queried.
	 */
	public void expandDocumentModel(final DocumentModel aDocumentModel,
									final boolean refresh)
	{

		/* If expanded once and no refresh, don't repopulate. */
		final Model[] children = aDocumentModel.getChildren();
		if((refresh) || (children == null) || (children.length == 1))
		{
			final ConnectionModel connectionModel = getParentConnectionModel(aDocumentModel);
			IDfSession session = null;
			try
			{
				session = connectionModel.getSession();
				aDocumentModel.removeChildren();
				populateDocument(session, aDocumentModel);
			} catch(DfException dex)
			{
				MessageView.getInstance().addMessage(
					"Failed to get session.\n" + dex.getMessage()); //$NON-NLS-1$
			} finally
			{
				connectionModel.releaceConnection(session);
			}
		}
	}

	/**
	 * Expand a folder model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 10:03:28 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aFolderModel
	 *            the folder to expand.
	 * @param refresh
	 *            should query be re-executed.
	 */
	public void expandFolderModel(final AbstractSysObjectModel aFolderModel,
									final boolean refresh)
	{

		/* If expanded once and no refresh, don't repopulate. */
		final Model[] children = aFolderModel.getChildren();
		if((refresh) || (children == null) || (children.length == 1))
		{
			final ConnectionModel connectionModel = getParentConnectionModel(aFolderModel);
			IDfSession session = null;
			try
			{
				session = connectionModel.getSession();
				aFolderModel.removeChildren();
				populateFolder(session, aFolderModel);
			} catch(DfException dex)
			{
				MessageView.getInstance().addMessage(
					"Failed to get session.\n" + dex.getMessage()); //$NON-NLS-1$
			} finally
			{
				connectionModel.releaceConnection(session);
			}
		}
	}

	/**
	 * Expand groups model
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 3:14:44 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aGroupModel
	 *            the group model
	 * @param refresh
	 *            should the values be re-queried.
	 */
	public void expandGroupModel(GroupModel aGroupModel, final boolean refresh)
	{

		expandGroup(aGroupModel, refresh);
	}

	/**
	 * Expand groups model
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 3:14:31 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aGroupsModel
	 *            the groups container.
	 * @param refresh
	 *            should values be re queried.
	 */
	public void expandGroupsModel(final GroupsModel aGroupsModel,
									final boolean refresh)
	{

		expandGroup(aGroupsModel, refresh);
	}

	/**
	 * Expand table model (Since tables don't have inheritance nothing is
	 * done.).
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 3:11:27 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param tableModel
	 *            the table model.
	 * @param refresh
	 *            should data be refreshed.
	 */
	public void expandTableModel(final TableModel tableModel,
									final boolean refresh)
	{

		if((tableModel != null) && (refresh))
		{
			/* Just to get rid of the warning. */
		}
		/* Do notnig for now. */
	}

	/**
	 * Expand a type model returning the sub types.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 3:11:35 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param typeModel
	 *            the current type model.
	 * @param refresh
	 *            should the data be refreshed.
	 */
	public void expandTypeModel(final TypeModel typeModel, final boolean refresh)
	{

		/* If expanded once and no refresh, don't repopulate. */
		final Model[] children = typeModel.getChildren();
		if((refresh) || (children == null) || (children.length == 1))
		{
			final ConnectionModel connectionModel = getParentConnectionModel(typeModel);
			IDfSession session = null;
			try
			{
				session = connectionModel.getSession();
				typeModel.removeChildren();
				populateTypes(session, typeModel, typeModel.getName());
			} catch(DfException dex)
			{
				MessageView.getInstance().addMessage(
					"Failed to get session.\n" + dex.getMessage()); //$NON-NLS-1$
			} finally
			{
				connectionModel.releaceConnection(session);
			}
		}
	}

	/**
	 * Get a models children.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:45:42 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param parentElement
	 *            the parent.
	 * @return the child models.
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(final Object parentElement)
	{

		if(parentElement instanceof Model)
		{
			return ((Model) parentElement).getChildren();
		}
		return new Object[0];
	}

	/**
	 * Get connectin models
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:36:53 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the connections.
	 */
	public ConnectionModel[] getConnections()
	{

		final ArrayList<Model> children = invisibleRootModel
			.getChildrenArrayList();
		final ConnectionModel[] connectionModels = new ConnectionModel[children
			.size()];
		children.toArray(connectionModels);
		return connectionModels;
	}

	/**
	 * Same as getChildren.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:45:42 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param inputElement
	 *            the parent.
	 * @return the child models.
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(final Object inputElement)
	{

		return getChildren(inputElement);
	}

	/**
	 * Get the executed dql statements.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Feb 9, 2005 9:33:05 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return the statement container.
	 */
	public Vector<PreviousDQLStatementAction> getExecutedDQLStatements()
	{

		return ConnectionContentProvider.executedDQLStatements;
	}

	/**
	 * Get the invisible root model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 1:27:25 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the root model.
	 */
	public InvisibleModel getInvisibleRootModel()
	{

		return ConnectionContentProvider.invisibleRootModel;
	}

	/**
	 * Get the invisible child model from a model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:36:59 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param model
	 *            parent model
	 * @return the invisible child model.
	 */
	public InvisibleModel getInvisibleRootModel(final Model model)
	{

		final Model[] children = model.getChildren();
		if(children.length == 1 && (children[0] instanceof InvisibleModel))
		{
			return (InvisibleModel) children[0];
		}
		return null;
	}

	/**
	 * Get the parent model
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:45:42 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param element
	 *            the child model
	 * @return the parent model.
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(final Object element)
	{

		if(element instanceof Model)
		{
			return ((Model) element).getParent();
		}
		return null;
	}

	/**
	 * Get the connection model from any selected model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 2:50:50 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param model
	 *            any child of the connection model.
	 * @return the connection model.
	 */
	public ConnectionModel getParentConnectionModel(final Model model)
	{

		Model returnModel = model;
		while(!(returnModel instanceof ConnectionModel))
		{
			returnModel = getParentConnectionModel(returnModel.getParent());
		}
		return (ConnectionModel) returnModel;
	}

	/**
	 * Get the selected connection model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 2:57:43 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the selected connection model.
	 */
	public ConnectionModel getSelectedConnectionModel()
	{

		return this.selectedConnectionModel;
	}

	/**
	 * Get the selected Docbase model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:09:13 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the selected Docbase model.
	 */
	public DocbaseModel getSelectedDocbaseModel()
	{

		return this.docbaseModel;
	}

	/**
	 * Returns has the connection changed.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:38:14 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return has connection information changed.
	 */
	public boolean hasChanged()
	{

		return this.hasChanged;
	}

	/**
	 * Check if a model has children.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:45:42 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param element
	 *            the parent model.
	 * @return true if the model has child models.
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(final Object element)
	{

		if(element instanceof Model)
		{
			return ((Model) element).hasChildren();
		}
		return false;
	}

	/**
	 * <p>
	 * <b>Do nothing. </b>
	 * </p>
	 * <p>
	 * Notifies this content provider that the given viewer's input has been
	 * switched to a different element.
	 * </p>
	 * <p>
	 * A typical use for this method is registering the content provider as a
	 * listener to changes on the new input (using model-specific means), and
	 * deregistering the viewer from the old input. In response to these change
	 * notifications, the content provider should update the viewer (see the
	 * add, remove, update and refresh methods on the viewers).
	 * </p>
	 * <p>
	 * The viewer should not be updated during this call, as it might be in the
	 * process of being disposed.
	 * </p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:45:42 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param viewer
	 *            the viewer
	 * @param oldInput
	 *            the old input element, or <code>null</code> if the viewer
	 *            did not previously have an input
	 * @param newInput
	 *            the new input element, or <code>null</code> if the viewer
	 *            does not have an input
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(final Viewer viewer, final Object oldInput,
								final Object newInput)
	{

		/* Nothing done here. */
	}

	/**
	 * Load the connection information.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:36:15 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param file
	 *            the settings file.
	 * @throws Exception
	 *             if the file can't be read.
	 */
	public void load(final File file) throws Exception
	{

		final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
			.newInstance();
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		documentBuilderFactory.setIgnoringComments(true);
		final DocumentBuilder documentBuilder = documentBuilderFactory
			.newDocumentBuilder();
		final Document document = documentBuilder.parse(file);
		final ConnectionModel[] connectionModels = createConnectionModelsFromXml(document);
		for(int connectionIndex = 0; connectionIndex < connectionModels.length; connectionIndex++)
		{
			invisibleRootModel.addChild(connectionModels[connectionIndex]);
		}
	}

	/**
	 * Populate the cabinets.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 11:17:56 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param session
	 *            a valid Docbase session.
	 * @param parentCabinetsModel
	 *            the cabinets container node.
	 */
	public void populateCabinets(final IDfSession session,
									final CabinetsModel parentCabinetsModel)
	{

		StringBuffer folderQuery = new StringBuffer("select distinct ").append(IPersistentObject.R_OBJECT_ID) //$NON-NLS-1$
			.append(" , ").append(ISysObject.OBJECT_NAME).append(" from ").append(ICabinet.TYPE_DM_CABINET) //$NON-NLS-1$ //$NON-NLS-2$
			.append(" order by 2 , 1"); //$NON-NLS-1$
		final IDfQuery query = new DfQuery();
		IDfCollection results = null;
		query.setDQL(folderQuery.toString());
		try
		{
			MessageView.getInstance().addMessage(
				session.getLoginInfo().getDomain() + '\\'
					+ session.getLoginUserName() + '@'
					+ session.getDocbaseName() + ' ' + query.getDQL());
			results = query.execute(session, IDfQuery.DF_EXECREAD_QUERY);
			while(results.next())
			{
				final CabinetModel childCabinetModel = new CabinetModel(results
					.getString(ISysObject.OBJECT_NAME), results
					.getId(IPersistentObject.R_OBJECT_ID));
				childCabinetModel.addChild(new InvisibleModel("NONE")); //$NON-NLS-1$
				parentCabinetsModel.addChild(childCabinetModel);
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
							+ " Failed to run cabinet query " + folderQuery.toString() + ".\n" //$NON-NLS-1$ //$NON-NLS-2$
							+ dex.getMessage());
			} catch(DfException swallow)
			{/* Do nothing. */
			}
		} finally
		{
			if(results != null)
			{
				try
				{
					results.close();
				} catch(final Exception swallow)
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
									+ " Failed to close collection.\n" + swallow.getMessage()); //$NON-NLS-1$
					} catch(DfException swallow2)
					{/* Do nothing. */
					}
				}
			}
		}
	}

	/**
	 * Populate the connection model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:40:51 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param connectionModel
	 *            the model to populate.
	 */
	public void populateConnectionModel(final ConnectionModel connectionModel)
	{

		String name = null;
		if((connectionModel.getDomain() == null)
			|| (connectionModel.getDomain().equals(""))) //$NON-NLS-1$
		{
			name = connectionModel.getUserName() + '@'
				+ connectionModel.getDocbase();
		} else
		{
			name = connectionModel.getDomain() + '\\'
				+ connectionModel.getUserName() + '@'
				+ connectionModel.getDocbase();
		}
		DocbaseModel aDocbaseModel = new DocbaseModel(name);
		connectionModel.addChild(aDocbaseModel);
		aDocbaseModel.addChild(new InvisibleModel("NONE")); //$NON-NLS-1$
	}

	/**
	 * Populate the Docbase model
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:41:05 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @param aDocbaseModel
	 *            the model to populate.
	 * @since 1.0
	 */
	public void populateDocbaseModel(final DocbaseModel aDocbaseModel)
	{

		final ConnectionModel connectionModel = getParentConnectionModel(aDocbaseModel);
		IDfSession session = null;
		try
		{
			session = connectionModel.getSession();
			final TypesModel typesModel = new TypesModel(DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_TYPES));
			aDocbaseModel.addChild(typesModel);
			populateTypes(session, typesModel, null);
			final TablesModel tablesModel = new TablesModel(DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_TABLES));
			aDocbaseModel.addChild(tablesModel);
			populateTables(session, tablesModel);
			final CabinetsModel cabinetsModel = new CabinetsModel(DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_CABINETS));
			aDocbaseModel.addChild(cabinetsModel);
			populateCabinets(session, cabinetsModel);
			final GroupsModel groupsModel = new GroupsModel(DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_GROUPS));
			aDocbaseModel.addChild(groupsModel);
			populateGroup(session, groupsModel);
			final UsersModel usersModel = new UsersModel(DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_USERS));
			aDocbaseModel.addChild(usersModel);
			final InboxModel inboxModel = new InboxModel(DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_INBOX));
			aDocbaseModel.addChild(inboxModel);

			populateModules(session, aDocbaseModel);
		} catch(DfException dex)
		{
			MessageView.getInstance().addMessage(
				"Failed to get session.\n" + dex.getMessage()); //$NON-NLS-1$
		} finally
		{
			connectionModel.releaceConnection(session);
		}
	}

	/**
	 * The modules model instance. 25-Nov-2005
	 * 
	 * @since 3.0.0
	 */
	private ModulesModel	modulesModel	= null;

	/**
	 * Get the modules model.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25-Nov-2005 12:58:01</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @return the BOF modules model.
	 */
	public ModulesModel getModulesModel()
	{

		return this.modulesModel;
	}

	/**
	 * Set the modules model.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 25-Nov-2005 12:58:30</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param aModulesModel
	 *            the BOF modules model.
	 */
	public void setModulesModel(final ModulesModel aModulesModel)
	{

		this.modulesModel = aModulesModel;
	}

	/**
	 * Populate a virtual document content.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 11:06:53 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param session
	 *            a valid Docbase session.
	 * @param parentDocumentModel
	 *            the paren virtual document.
	 */
	public void populateDocument(final IDfSession session,
									final DocumentModel parentDocumentModel)
	{

		final StringBuffer folderQuery = new StringBuffer("select distinct ").append(IPersistentObject.R_OBJECT_ID) //$NON-NLS-1$
			.append(" , ").append(ISysObject.OBJECT_NAME).append(" from ").append(IDocument.TYPE_DM_DOCUMENT) //$NON-NLS-1$ //$NON-NLS-2$
			.append(" in document id ( '").append(parentDocumentModel.getObjectId().getId()).append("' )"); //$NON-NLS-1$ //$NON-NLS-2$
		final IDfQuery query = new DfQuery();
		IDfCollection results = null;
		query.setDQL(folderQuery.toString());
		try
		{
			MessageView.getInstance().addMessage(
				session.getLoginInfo().getDomain() + '\\'
					+ session.getLoginUserName() + '@'
					+ session.getDocbaseName() + ' ' + query.getDQL());
			results = query.execute(session, IDfQuery.DF_EXECREAD_QUERY);
			while(results.next())
			{
				final DocumentModel childDocumentModel = new DocumentModel(
					results.getString(ISysObject.OBJECT_NAME), results
						.getId(IPersistentObject.R_OBJECT_ID));
				childDocumentModel.addChild(new InvisibleModel("NONE")); //$NON-NLS-1$
				parentDocumentModel.addChild(childDocumentModel);
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
							+ " Failed to run document query " + folderQuery.toString() + ".\n" //$NON-NLS-1$ //$NON-NLS-2$
							+ dex.getMessage());
			} catch(DfException swallow)
			{/* Do nothing. */
			}
		} finally
		{
			if(results != null)
			{
				try
				{
					results.close();
				} catch(final Exception swallow)
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
									+ " Failed to close collection.\n" + swallow.getMessage()); //$NON-NLS-1$
					} catch(DfException swallow2)
					{
						/* Do nothing. */
					}
				}
			}
		}
	}

	/**
	 * Populate the folders.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 10:17:03 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param session
	 *            a Docbase session.
	 * @param parentFolderModel
	 *            the folders whose content needs to be returned.
	 */
	public void populateFolder(final IDfSession session,
								final AbstractSysObjectModel parentFolderModel)
	{

		StringBuffer folderQuery = new StringBuffer(200)
			.append("select distinct ") //$NON-NLS-1$
			.append(IPersistentObject.R_OBJECT_ID)
			.append(" , ").append(ISysObject.OBJECT_NAME).append(" , ") //$NON-NLS-1$ //$NON-NLS-2$
			.append(ISysObject.R_OBJECT_TYPE)
			.append(" from ").append(IDocument.TYPE_DM_DOCUMENT) //$NON-NLS-1$
			.append(" where folder ( id ( '").append(parentFolderModel.getObjectId().getId()).append("' ) ) and ") //$NON-NLS-1$ //$NON-NLS-2$
			.append(ISysObject.R_IS_VIRTUAL_DOC)
			.append(" = 1 union select distinct ") //$NON-NLS-1$
			.append(IPersistentObject.R_OBJECT_ID)
			.append(" , ").append(ISysObject.OBJECT_NAME).append(" , ") //$NON-NLS-1$ //$NON-NLS-2$
			.append(ISysObject.R_OBJECT_TYPE)
			.append(" from ").append(IFolder.TYPE_DM_FOLDER) //$NON-NLS-1$
			.append(" where folder ( id ( '").append(parentFolderModel.getObjectId().getId()) //$NON-NLS-1$
			.append("' ) ) order by 3 , 2 , 1"); //$NON-NLS-1$
		final IDfQuery query = new DfQuery();
		IDfCollection results = null;
		query.setDQL(folderQuery.toString());
		try
		{
			MessageView.getInstance().addMessage(
				session.getLoginInfo().getDomain() + '\\'
					+ session.getLoginUserName() + '@'
					+ session.getDocbaseName() + ' ' + query.getDQL());
			results = query.execute(session, IDfQuery.DF_EXECREAD_QUERY);
			while(results.next())
			{
				final IDfId objectId = results
					.getId(IPersistentObject.R_OBJECT_ID);
				final AbstractSysObjectModel childModel;

				if(objectId.equals(getModulesModel().getObjectId()))
				{
					childModel = getModulesModel();
				} else if(objectId.getId().startsWith(
					IDocument.TYPE_DM_DOCUMENT_PREFIX))
				{
					childModel = new DocumentModel(results
						.getString(ISysObject.OBJECT_NAME), objectId);
					childModel.addChild(new InvisibleModel("NONE")); //$NON-NLS-1$
				} else
				{
					childModel = new FolderModel(results
						.getString(ISysObject.OBJECT_NAME), objectId);
					childModel.addChild(new InvisibleModel("NONE")); //$NON-NLS-1$
				}
				parentFolderModel.addChild(childModel);
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
							+ " Failed to run folder query " + folderQuery.toString() + ".\n" //$NON-NLS-1$ //$NON-NLS-2$
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
				} catch(final Exception swallow)
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
									+ " Failed to close collection.\n" + swallow.getMessage()); //$NON-NLS-1$
					} catch(DfException swallow2)
					{
						/* Do nothing. */
					}
				}
			}
		}
	}

	/**
	 * Populate group content.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 3:21:46 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.2
	 * @param session
	 *            a valid Docbase session.
	 * @param parentModel
	 *            the parent group.
	 */
	public void populateGroup(final IDfSession session, final Model parentModel)
	{

		final StringBuffer groupQuery = new StringBuffer("select distinct ").append(IPersistentObject.R_OBJECT_ID) //$NON-NLS-1$
			.append(" , ").append(IGroup.GROUP_NAME).append(" , ").append(IGroup.GROUP_CLASS).append(" from ") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			.append(IGroup.TYPE_DM_GROUP);
		if(parentModel instanceof GroupModel)
		{
			groupQuery
				.append(" where any ").append(IGroup.GROUPS_NAMES).append(" = '") //$NON-NLS-1$ //$NON-NLS-2$
				.append(escapeSingleQuotes(parentModel.getName())).append('\'');
		}
		groupQuery.append(" order by 3 , 2 , 1"); //$NON-NLS-1$
		final IDfQuery query = new DfQuery();
		IDfCollection results = null;
		query.setDQL(groupQuery.toString());
		try
		{
			MessageView.getInstance().addMessage(
				session.getLoginInfo().getDomain() + '\\'
					+ session.getLoginUserName() + '@'
					+ session.getDocbaseName() + ' ' + query.getDQL());
			results = query.execute(session, IDfQuery.DF_EXECREAD_QUERY);
			while(results.next())
			{
				final GroupModel childModel = new GroupModel(results
					.getString(IGroup.GROUP_NAME), results
					.getId(IPersistentObject.R_OBJECT_ID), results
					.getString(IGroup.GROUP_CLASS));
				childModel.addChild(new InvisibleModel("NONE")); //$NON-NLS-1$
				parentModel.addChild(childModel);
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
							+ " Failed to run group query " + groupQuery.toString() + ".\n" //$NON-NLS-1$ //$NON-NLS-2$
							+ dex.getMessage());
			} catch(DfException swallow)
			{/* Do nothing. */
			}
		} finally
		{
			if(results != null)
			{
				try
				{
					results.close();
				} catch(final Exception swallow)
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
									+ " Failed to close collection.\n" + swallow.getMessage()); //$NON-NLS-1$
					} catch(final DfException swallow2)
					{
						/* Do nothing. */
					}
				}
			}
		}
	}

	/**
	 * Populate the modules.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 24-Nov-2005 16:56:35</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Mad Cook account : dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 3.0.0
	 * @param session
	 *            a valid Docbase session.
	 * @param aDocbaseModel
	 * @throws DfException
	 */
	public void populateModules(final IDfSession session,
								final DocbaseModel aDocbaseModel) throws DfException
	{

		StringBuffer modulesQuery = new StringBuffer(IFolder.TYPE_DM_FOLDER)
			.append(" where any ").append(IFolder.R_FOLDER_PATH).append(" = '") //$NON-NLS-1$ //$NON-NLS-2$
			.append(ModulesModel.MODULES_LOCATION).append('\'');

		MessageView.getInstance().addMessage("id,c," + modulesQuery.toString()); //$NON-NLS-1$
		final IDfId modulesId = new DfId(session.apiGet("id", modulesQuery //$NON-NLS-1$
			.toString()));
		MessageView.getInstance().addMessage(
			"get,c," + modulesId.getId() + ',' + ISysObject.OBJECT_NAME); //$NON-NLS-1$

		final String modulesName = session.apiGet("get", modulesId.getId() //$NON-NLS-1$
			+ ',' + ISysObject.OBJECT_NAME);
		setModulesModel(new ModulesModel(modulesName, modulesId));
		aDocbaseModel.addChild(getModulesModel());

		modulesQuery = new StringBuffer("select distinct ") //$NON-NLS-1$
			.append(IPersistentObject.R_OBJECT_ID).append(" , ") //$NON-NLS-1$
			.append(ISysObject.OBJECT_NAME).append(" from ") //$NON-NLS-1$
			.append(IFolder.TYPE_DM_FOLDER).append(" where folder ( ID ( '") //$NON-NLS-1$
			.append(modulesId).append("' ) ) order by 2"); //$NON-NLS-1$
		final IDfQuery query = new DfQuery();
		IDfCollection results = null;
		query.setDQL(modulesQuery.toString());
		try
		{
			MessageView.getInstance().addMessage(
				session.getLoginInfo().getDomain() + '\\'
					+ session.getLoginUserName() + '@'
					+ session.getDocbaseName() + ' ' + query.getDQL());
			results = query.execute(session, IDfQuery.DF_EXECREAD_QUERY);
			while(results.next())
			{
				final String objectName = results
					.getString(ISysObject.OBJECT_NAME);
				Model moduleModel = null;
				if(objectName.equals(ModulesModel.ASPECT_MODULE))
				{
					moduleModel = new AspectModuleModel(objectName, results
						.getId(IPersistentObject.R_OBJECT_ID));
				} else if(objectName.equals(ModulesModel.SBO_MODULE))
				{
					moduleModel = new SBOModuleModel(objectName, results
						.getId(IPersistentObject.R_OBJECT_ID));
				} else if(objectName.equals(ModulesModel.TBO_MODULE))
				{
					moduleModel = new TBOModuleModel(objectName, results
						.getId(IPersistentObject.R_OBJECT_ID));
				} else
				{
					moduleModel = new OtherModuleModel(objectName, results
						.getId(IPersistentObject.R_OBJECT_ID));
				}
				moduleModel.addChild(new InvisibleModel("NONE")); //$NON-NLS-1$
				getModulesModel().addChild(moduleModel);
			}
		} catch(final DfException dex)
		{
			try
			{
				MessageView.getInstance().addMessage(
					session.getLoginInfo().getDomain() + '\\'
						+ session.getLoginUserName() + '@'
						+ session.getDocbaseName()
						+ " Failed to run document query " //$NON-NLS-1$
						+ modulesQuery.toString() + ".\n" + dex.getMessage()); //$NON-NLS-1$
			} catch(DfException swallow)
			{/* Do nothing. */
			}
		} finally
		{
			if(results != null)
			{
				try
				{
					results.close();
				} catch(final Exception swallow)
				{
					try
					{
						MessageView.getInstance().addMessage(
							session.getLoginInfo().getDomain() + '\\'
								+ session.getLoginUserName() + '@'
								+ session.getDocbaseName()
								+ " Failed to close collection.\n" //$NON-NLS-1$
								+ swallow.getMessage());
					} catch(DfException swallow2)
					{
						/* Do nothing. */
					}
				}
			}
		}
	}

	/**
	 * Populate registered tables.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 3:08:47 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param session
	 *            a Docbase session.
	 * @param parent
	 *            the parent model.
	 */
	public void populateTables(final IDfSession session, final Model parent)
	{

		final StringBuffer tableQuery = new StringBuffer("select distinct ").append(IPersistentObject.R_OBJECT_ID) //$NON-NLS-1$
			.append(" , ").append(ISysObject.OWNER_NAME).append(" , ").append(IRegistered.TABLE_NAME).append(" from ") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			.append(IRegistered.TYPE_DM_REGISTERED).append(" order by 2"); //$NON-NLS-1$
		final IDfQuery query = new DfQuery();
		IDfCollection results = null;
		query.setDQL(tableQuery.toString());
		try
		{
			MessageView.getInstance().addMessage(
				session.getLoginInfo().getDomain() + '\\'
					+ session.getLoginUserName() + '@'
					+ session.getDocbaseName() + ' ' + query.getDQL());
			results = query.execute(session, IDfQuery.DF_EXECREAD_QUERY);
			while(results.next())
			{
				final TableModel tableModel = new TableModel((new StringBuffer(
					results.getString(ISysObject.OWNER_NAME)).append('.')
					.append(results.getString(IRegistered.TABLE_NAME)))
					.toString(), results.getId(IPersistentObject.R_OBJECT_ID));
				parent.addChild(tableModel);
			}
		} catch(DfException dex)
		{
			MessageView
				.getInstance()
				.addMessage(
					"Failed to run table query " + tableQuery + ".\n" + dex.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
		} finally
		{
			if(results != null)
			{
				try
				{
					results.close();
				} catch(Exception swallow)
				{
					MessageView.getInstance().addMessage(
						"Failed to close collection.\n" + swallow.getMessage()); //$NON-NLS-1$
				}
			}
		}
	}

	/**
	 * Populate the Docbase type models and the relation types that have the
	 * given type as their parent type.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 2:09:41 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.3
	 * @param session
	 *            a valid Docbase session.
	 * @param parent
	 *            the parent model.
	 * @param superTypeName
	 *            the docbase types super type. Can be null.
	 */
	public void populateTypes(final IDfSession session, final Model parent,
								final String superTypeName)
	{

		final StringBuffer typeQuery = new StringBuffer("select distinct ").append(IPersistentObject.R_OBJECT_ID) //$NON-NLS-1$
			.append(" , ").append(IType.NAME).append(" from ").append(IType.TYPE_DM_TYPE).append(" where ") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			.append(IType.SUPER_NAME);
		if(superTypeName != null)
		{
			typeQuery
				.append(" = '").append(superTypeName).append("' union select distinct ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(IPersistentObject.R_OBJECT_ID)
				.append(" , ").append(IRelationType.RELATION_NAME).append(" as ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(IType.NAME)
				.append(" from ").append(IRelationType.TYPE_DM_RELATION_TYPE).append(" where ") //$NON-NLS-1$ //$NON-NLS-2$
				.append(IRelationType.PARENT_TYPE)
				.append(" = '").append(superTypeName).append('\''); //$NON-NLS-1$

		} else
		{
			typeQuery.append(" is nullstring"); //$NON-NLS-1$
		}
		typeQuery.append(" order by 2"); //$NON-NLS-1$
		final IDfQuery query = new DfQuery();
		IDfCollection results = null;
		query.setDQL(typeQuery.toString());
		try
		{
			MessageView.getInstance().addMessage(
				session.getLoginInfo().getDomain() + '\\'
					+ session.getLoginUserName() + '@'
					+ session.getDocbaseName() + ' ' + query.getDQL());
			results = query.execute(session, IDfQuery.DF_EXECREAD_QUERY);
			while(results.next())
			{
				final IDfId objectId = results
					.getId(IPersistentObject.R_OBJECT_ID);
				Model childModel = null;
				if((objectId != null)
					&& (objectId.getId().startsWith(IType.TYPE_DM_TYPE_PREFIX)))
				{
					childModel = new TypeModel(results.getString(IType.NAME),
						objectId);
					childModel.addChild(new InvisibleModel("NONE")); //$NON-NLS-1$
				} else
				{
					childModel = new RelationTypeModel(results
						.getString(IType.NAME), objectId);
				}
				parent.addChild(childModel);
			}
		} catch(DfException dex)
		{
			MessageView
				.getInstance()
				.addMessage(
					"Failed to run type query " + typeQuery.toString() + ".\n" + dex.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
		} finally
		{
			if(results != null)
			{
				try
				{
					results.close();
				} catch(Exception swallow)
				{
					MessageView.getInstance().addMessage(
						"Failed to close collection.\n" + swallow.getMessage()); //$NON-NLS-1$
				}
			}
		}
	}

	/**
	 * Refresh a model
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 10:02:26 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param model
	 *            model to refresh.
	 */
	public void refreshModel(final Model model)
	{

		/* Remove children from tree. */
		IDfSession connection = null;
		try
		{

			model.removeChildren();
			if(model instanceof ConnectionModel)
			{
				final ConnectionModel connectionModel = (ConnectionModel) model;
				populateConnectionModel(connectionModel);
			} else if(model instanceof DocbaseModel)
			{
				final DocbaseModel aDocbaseModel = (DocbaseModel) model;
				populateDocbaseModel(aDocbaseModel);
			} else if(model instanceof TablesModel)
			{
				populateTables((connection = ConnectionView.getInstance()
					.getSelectedConnection().getSession()), model);
			} else if(model instanceof TypesModel)
			{
				populateTypes((connection = ConnectionView.getInstance()
					.getSelectedConnection().getSession()), model, null);
			} else if(model instanceof TypeModel)
			{
				populateTypes((connection = ConnectionView.getInstance()
					.getSelectedConnection().getSession()), model, model
					.getName());
			} else if(model instanceof CabinetsModel)
			{
				populateCabinets((connection = ConnectionView.getInstance()
					.getSelectedConnection().getSession()),
					(CabinetsModel) model);
			} else if((model instanceof CabinetModel)
				|| (model instanceof FolderModel))
			{
				populateFolder((connection = ConnectionView.getInstance()
					.getSelectedConnection().getSession()), (FolderModel) model);
			} else if(model instanceof DocumentModel)
			{
				populateDocument((connection = ConnectionView.getInstance()
					.getSelectedConnection().getSession()),
					(DocumentModel) model);
			} else if((model instanceof GroupsModel)
				|| (model instanceof GroupModel))
			{
				populateGroup((connection = ConnectionView.getInstance()
					.getSelectedConnection().getSession()), model);
			}
		} catch(DfException dex)
		{
			MessageView.getInstance().addMessage(
				"Failure refreshing.\n" + dex.getMessage()); //$NON-NLS-1$
		} finally
		{
			ConnectionView.getInstance().getSelectedConnection()
				.releaceConnection(connection);
		}
	}

	/**
	 * Save the connection settings.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 10:29:26 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param file
	 *            the target file.
	 */
	public void save(final File file)
	{

		try
		{
			final Document document = serializeConnectionModelToXML();
			final OutputFormat format = new OutputFormat(document);
			format.setIndenting(true);
			final FileWriter fileOutput = new FileWriter(file);
			final XMLSerializer serial = new XMLSerializer(fileOutput, format);
			serial.asDOMSerializer(); // As a DOM
			serial.serialize(document);
		} catch(Throwable e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the XML document.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:38:21 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the connection settings as a xml document.
	 */
	public Document serializeConnectionModelToXML()
	{

		Document document = null;
		try
		{
			final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
			documentBuilderFactory.setIgnoringElementContentWhitespace(true);
			final DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
			document = documentBuilder.newDocument();
			final Element rootNode = document.createElement(invisibleRootModel
				.getName());
			final ConnectionModel[] connectionModels = getConnections();
			for(int connectionIndex = 0; connectionIndex < connectionModels.length; connectionIndex++)
			{
				final Element connectionNode = document
					.createElement(connectionModels[connectionIndex].getName());
				connectionNode.appendChild(createNode(document,
					ConnectionModel.USERNAME_NODE, Crypto.getEncoder().encode(
						connectionModels[connectionIndex].getUserName()
							.getBytes())));
				connectionNode.appendChild(createNode(document,
					ConnectionModel.DOMAIN_NODE, Crypto.getEncoder().encode(
						connectionModels[connectionIndex].getDomain()
							.getBytes())));
				connectionNode.appendChild(createNode(document,
					ConnectionModel.DOCBASE_NODE, Crypto.getEncoder().encode(
						connectionModels[connectionIndex].getDocbase()
							.getBytes())));
				final String buildKeyFrom = new StringBuffer(
					connectionModels[connectionIndex].getName()).append(
					connectionModels[connectionIndex].getUserName()).append(
					connectionModels[connectionIndex].getDomain()).append(
					connectionModels[connectionIndex].getDocbase()).append(
					"01234567").toString(); //$NON-NLS-1$
				final char[] aPassPhrase = buildKeyFrom.toCharArray();
				final byte[] temp = buildKeyFrom.getBytes();
				final byte[] aSalt = {temp[0], temp[1], temp[2], temp[3],
					temp[4], temp[5], temp[6], temp[7]};
				final ICrypto crypt = new Crypto(
					ICrypto.ALGORITHM_PBEMD5DES_CBC, aSalt, aPassPhrase);
				final Key aKey = crypt.getNewKey();
				crypt.setKey(aKey);
				connectionNode.appendChild(createNode(document,
					ConnectionModel.PASSWORD_NODE, crypt.encryptBase64(
						connectionModels[connectionIndex].getPassword(),
						ICrypto.CHARACTER_SET_UTF_8)));
				rootNode.appendChild(connectionNode);
			}
			int index = 1;
			final Element statementsNode = document
				.createElement(ConnectionModel.PREVIOUS_DQL_STATEMENTS_NODE);
			for(final PreviousDQLStatementAction statement: ConnectionContentProvider
				.getInstance().getExecutedDQLStatements())
			{
				statementsNode.appendChild(createNode(document,
					ConnectionModel.STATEMENT_NODE + index++, Crypto
						.getEncoder().encode(
							statement.getDQLStatement().getBytes())));
			}
			rootNode.appendChild(statementsNode);
			final Element checkedOutNode = document
				.createElement(ConnectionModel.CHECKED_OUT_NODE);
			for(final String rObjectId: ConnectionContentProvider
				.getCheckedOutDocuments().keySet())
			{
				checkedOutNode.appendChild(createNode(document,
					"id_" + rObjectId, Crypto.getEncoder() //$NON-NLS-1$
						.encode(
							(ConnectionContentProvider.getCheckedOutDocuments()
								.get(rObjectId)).getBytes())));
			}
			rootNode.appendChild(checkedOutNode);
			document.appendChild(rootNode);
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return document;
	}

	/**
	 * Set has the connection settings changed.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 5:23:59 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aHasChanged
	 *            has the connection information changed.
	 */
	public void setHasChanged(final boolean aHasChanged)
	{

		this.hasChanged = aHasChanged;

	}

	/**
	 * Set the selected connection.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 10:09:53 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aSelectedConnectionModel
	 *            the selected connection model.
	 */
	public void setSelectedConnectionModel(
											final ConnectionModel aSelectedConnectionModel)
	{

		this.selectedConnectionModel = aSelectedConnectionModel;
	}

	/**
	 * Set the selected Docbase model.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 28, 2004 12:10:59 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDocbaseModel
	 *            the selected model.
	 */
	public void setSelectedDocbaseModel(final DocbaseModel aDocbaseModel)
	{

		this.docbaseModel = aDocbaseModel;
	}

	/**
	 * Execute a api exec statement.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 10:58:07 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param connection
	 *            a valid Docbase connection.
	 * @param api
	 *            the API. 0 command, 1 arguments.
	 * @throws DfException
	 *             if the command couldn't be executed.
	 */
	protected void apiExec(final IDfSession connection, final String[] api) throws DfException
	{

		if((connection != null) && (api.length >= 2))
		{
			MessageView.getInstance().addMessage(api[0] + ",c," + api[1]); //$NON-NLS-1$
			if(connection.apiExec(api[0], api[1]))
			{
				MessageView.getInstance().addMessage("OK"); //$NON-NLS-1$
			} else
			{
				MessageView.getInstance().addMessage(connection.getMessage(1));
			}
		} else
		{
			MessageView.getInstance().addMessage("Invalid arguments."); //$NON-NLS-1$
		}
	}

	/**
	 * Execute a API get statement.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 10:59:40 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param connection
	 *            a valid Docbase connection.
	 * @param api
	 *            the API. 0 command, 1 arguments.
	 * @throws DfException
	 *             if the command couldn't be executed.
	 */
	protected void apiGet(final IDfSession connection, final String[] api) throws DfException
	{

		if((connection != null) && (api.length >= 2))
		{
			MessageView.getInstance().addMessage(api[0] + ",c," + api[1]); //$NON-NLS-1$
			MessageView.getInstance().addMessage(
				connection.apiGet(api[0], api[1]));
		} else
		{
			MessageView.getInstance().addMessage("Invalid arguments."); //$NON-NLS-1$
		}
	}

	/**
	 * Execute API set statement.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 3, 2004 10:58:13 AM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param connection
	 *            a valid Docbase connection.
	 * @param api
	 *            the API. 0 command, 1 arguments, 2 value.
	 * @throws DfException
	 *             if the command couldn't be executed.
	 */
	protected void apiSet(final IDfSession connection, String[] api) throws DfException
	{

		if((connection != null) && (api.length >= 3))
		{
			MessageView.getInstance().addMessage(api[0] + ",c," + api[1]); //$NON-NLS-1$
			MessageView.getInstance().addMessage(api[2]);
			if(connection.apiSet(api[0], api[1], api[2]))
			{
				MessageView.getInstance().addMessage("OK"); //$NON-NLS-1$
			} else
			{
				MessageView.getInstance().addMessage(connection.getMessage(1));
			}
		} else
		{
			MessageView.getInstance().addMessage("Invalid arguments."); //$NON-NLS-1$
		}
	}

	/**
	 * XML Helper method.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 25, 2004 12:38:28 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param document
	 *            the XML document.
	 * @param elementName
	 *            naem of the new element
	 * @param elementValue
	 *            the element value.
	 * @return the created node.
	 */
	protected Element createNode(final Document document,
									final String elementName,
									final String elementValue)
	{

		final Element childNode = document.createElement(elementName);
		final Text textNode = document.createTextNode(elementValue);
		childNode.appendChild(textNode);
		return childNode;
	}

	/**
	 * Expand a group or group container.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Jan 2, 2005 3:45:00 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aGroupModel
	 *            group model or container.
	 * @param refresh
	 *            should values be re-queried.
	 */
	protected void expandGroup(Model aGroupModel, final boolean refresh)
	{

		/* If expanded once and no refresh, don't repopulate. */
		final Model[] children = aGroupModel.getChildren();
		if((refresh) || (children == null) || (children.length == 1))
		{
			final ConnectionModel connectionModel = getParentConnectionModel(aGroupModel);
			IDfSession session = null;
			try
			{
				session = connectionModel.getSession();
				aGroupModel.removeChildren();
				populateGroup(session, aGroupModel);
			} catch(DfException dex)
			{
				MessageView.getInstance().addMessage(
					"Failed to get session.\n" + dex.getMessage()); //$NON-NLS-1$
			} finally
			{
				connectionModel.releaceConnection(session);
			}
		}
	}
}
