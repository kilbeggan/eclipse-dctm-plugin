/*-
 * $Log: ResultView.java,v $
 * Revision 1.10  2005/12/04 22:17:21  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.9  2005/12/04 20:29:56  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.8  2005/11/21 13:04:32  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.7  2005/04/01 11:28:07  harpechr
 * Reference to a static string changed to an interface.
 *
 * Revision 1.6  2005/03/25 09:18:59  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/09 13:55:19  harpechr
 * Added previous DQL queries functionality and modified the message view so 
 * that its real time.
 *
 * Revision 1.4  2005/01/24 12:34:57  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.3  2005/01/11 14:02:19  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.2  2005/01/11 13:47:25  harpechr
 * Changed hard coded attribute names and type names to references from the bof 
 * structure (org.cah.dctm.bof).
 *
 * Revision 1.1  2005/01/07 12:37:51  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.views;

import java.util.Vector;

import org.cah.dctm.bof.tbo.GenerateTBOs;
import org.cah.dctm.bof.tbo.persistent.sysobject.registered.IRegistered;
import org.cah.dctm.bof.tbo.persistent.type.IType;
import org.cah.eclipse.plugins.dctm.dql.DCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.IImageCache;
import org.cah.eclipse.plugins.dctm.dql.ImageCache;
import org.cah.eclipse.plugins.dctm.dql.actions.results.CancelCheckOutAction;
import org.cah.eclipse.plugins.dctm.dql.actions.results.CheckInAction;
import org.cah.eclipse.plugins.dctm.dql.actions.results.CheckInMajorAction;
import org.cah.eclipse.plugins.dctm.dql.actions.results.CheckInMinorAction;
import org.cah.eclipse.plugins.dctm.dql.actions.results.CheckOutAction;
import org.cah.eclipse.plugins.dctm.dql.actions.results.DumpObjectAction;
import org.cah.eclipse.plugins.dctm.dql.actions.results.ExportResultsAction;
import org.cah.eclipse.plugins.dctm.dql.actions.results.ExportSelectedAction;
import org.cah.eclipse.plugins.dctm.dql.actions.results.FetchApiAction;
import org.cah.eclipse.plugins.dctm.dql.actions.results.GetContentAction;
import org.cah.eclipse.plugins.dctm.dql.actions.results.ReturnAllRowsAction;
import org.cah.eclipse.plugins.dctm.dql.actions.results.ReturnAllVersionsAction;
import org.cah.eclipse.plugins.dctm.dql.views.listeners.ResultViewMenuListener;
import org.cah.eclipse.plugins.dctm.dql.views.listeners.TabFolderListener;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfAttr;


/**
 * <H4>Workbench view for DQL query results..</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>(c) Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Nov 24, 2004 3:39:32 PM.</DD>
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
public class ResultView
						extends
							ViewPart
{

	/**
	 * The count column name 'count'.
	 * 
	 * @since 1.0
	 */
	public static final String		COLUMN_COUNT			= "count";				//$NON-NLS-1$

	/**
	 * Id of the view.
	 * 
	 * @since 1.0
	 */

	public static final String		ID						= ResultView.class
																.getName();

	/**
	 * The first created instance of this view.
	 * 
	 * @since 1.0
	 */
	private static ResultView		instance				= null;

	/**
	 * Maximum row count.
	 * 
	 * @since 1.0
	 */
	private static long				maxRowCount				= 1000;

	/**
	 * Views menu id.
	 * 
	 * @since 1.0
	 */
	private static final String		MENU_ID					= '#' + ResultView.ID;

	/**
	 * synchronization helper.
	 * 
	 * @since 1.0
	 */
	private static Object			SEMAPHORE				= new Object();

	/**
	 * The cancel check out action.
	 * 
	 * @since 1.0.5
	 */
	private CancelCheckOutAction	cancelCheckOutAction	= null;

	/**
	 * The Check in action.
	 * 
	 * @since 1.0.5
	 */

	private CheckInAction			checkInAction			= null;

	/**
	 * The check in as major version action.
	 * 
	 * @since 1.0.5
	 */

	private CheckInMajorAction		checkInMajorAction		= null;

	/**
	 * The check in as minor version action.
	 * 
	 * @since 1.0.5
	 */

	private CheckInMinorAction		checkInMinorAction		= null;

	/**
	 * The check out action.
	 * 
	 * @since 1.0.5
	 */

	private CheckOutAction			checkOutAction			= null;

	/**
	 * The dump object action.
	 * 
	 * @since 1.0
	 */
	private DumpObjectAction		dumpObjectAction		= null;

	/**
	 * The export results action.
	 * 
	 * @since 1.0
	 */
	private ExportResultsAction		exportResultsAction		= null;

	/**
	 * The export selected action.
	 * 
	 * @since 1.0
	 */
	private ExportSelectedAction	exportSelectedAction	= null;

	/**
	 * The fetch to api action.
	 * 
	 * @since 1.0
	 */
	private FetchApiAction			fetchApiAction			= null;

	/**
	 * The get content action.
	 * 
	 * @since 1.0
	 */
	private GetContentAction		getContentAction		= null;

	/**
	 * The parent control.
	 * 
	 * @since 1.0
	 */
	private Composite				parent					= null;

	/**
	 * Return all rows from a query.
	 * 
	 * @since 2.0.0
	 */
	private ReturnAllRowsAction		returnAllRowsAction		= null;

	/**
	 * Return all document versions action.
	 * 
	 * @since 1.0
	 */
	private ReturnAllVersionsAction	returnAllVersionsAction	= null;

	/**
	 * The result tables tab folder.
	 * 
	 * @since 1.0
	 */
	private CTabFolder				tablesTabFolder			= null;

	/**
	 * initialize the max rows.
	 * 
	 * @since 1.0
	 */
	static
	{
		try
		{
			ResultView.setMaxRowCount(Long.valueOf(
				DCTMPlugin.getResourceString(IDCTMPlugin.MSG_MAX_RESULT_ROWS))
				.longValue());
			MessageView.getInstance().addMessage(
				"Max rows " + ResultView.getMaxRowCount() + "."); //$NON-NLS-1$ //$NON-NLS-2$
		} catch(Exception ex)
		{
			MessageView.getInstance().addMessage(ex);
		}
	}

	/**
	 * Sole constructor.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 24, 2004 3:39:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	public ResultView()
	{

		super();
		ResultView.instance = this;
	}

	/**
	 * Always returns the first created instance of this class.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 4:45:01 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return first created instance of this view.
	 */
	public static ResultView getInstance()
	{

		synchronized(SEMAPHORE)
		{
			if(ResultView.instance == null)
			{
				new ResultView();
			}
		}
		return ResultView.instance;
	}

	/**
	 * Get the maximum row count.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 3:17:24 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the maxRowCount
	 */
	public static long getMaxRowCount()
	{

		return ResultView.maxRowCount;
	}

	/**
	 * Set the maximum row count.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 3:17:24 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param maxRowCount
	 *            The maxRowCount to set
	 */
	protected static void setMaxRowCount(long maxRowCount)
	{

		ResultView.maxRowCount = maxRowCount;
	}

	/**
	 * Add a result set to the view.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 3:58:24 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param results
	 *            the results.
	 * @param tip
	 *            the tab name.
	 * @param newTab
	 *            should a new tab be created.
	 * @param dqlQuery
	 *            the query that generated this result.
	 * @param restrictionsAply
	 *            does the maximum row count aply.
	 * @throws DfException
	 *             if the query can't be handled.
	 */
	public void addResultSet(final IDfCollection results, final String tip,
								final boolean newTab, final String dqlQuery,
								final boolean restrictionsAply) throws DfException
	{

		if(!newTab)
		{
			try
			{
				while(getTablesTabFolder().getItemCount() != 0)
				{
					getTablesTabFolder().getSelection().dispose();
					if(getTablesTabFolder().getItemCount() > 0)
					{
						getTablesTabFolder().setSelection(0);
					}
				}
			} catch(Exception ex)
			{
				MessageView.getInstance().addMessage(ex);
			}
		}
		final Table resultSetTable = new Table(getTablesTabFolder(), SWT.BORDER
			| SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		resultSetTable.setLayoutData(new GridData(GridData.FILL_BOTH));
		resultSetTable.setLinesVisible(true);
		resultSetTable.setHeaderVisible(true);
		final MenuManager menuManager = new MenuManager(
			"ResultsMenu", ResultView.MENU_ID); //$NON-NLS-1$
		menuManager.setRemoveAllWhenShown(true);
		final Menu contextMenu = menuManager.createContextMenu(resultSetTable);
		resultSetTable.setMenu(contextMenu);
		menuManager.addMenuListener(new ResultViewMenuListener(this,
			resultSetTable));

		CTabItem tabItem = new CTabItem(getTablesTabFolder(), SWT.NONE);
		int length = (tip.length() <= 25) ? tip.length() : 25;
		tabItem.setText(tip.substring(0, length));
		tabItem.setToolTipText(tip);
		tabItem.setControl(resultSetTable);
		bindResultSet(results, resultSetTable, dqlQuery, restrictionsAply);
		/* Set focus on new tab. */
		getTablesTabFolder().setSelection(
			getTablesTabFolder().getItemCount() - 1);
		/* Bring to front. */
		getSite().getPage().activate(instance);
	}

	/**
	 * Bind a collection to a table.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 4:51:08 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param results
	 *            the query results.
	 * @param aTable
	 *            the table where to place the results.
	 * @param dqlQuery
	 *            the query that generated this result.
	 * @param restrictionsAply
	 *            does the maximum row count aply.
	 * @throws DfException
	 *             if the collection cant be closed.
	 */
	public void bindResultSet(final IDfCollection results, final Table aTable,
								final String dqlQuery,
								final boolean restrictionsAply) throws DfException
	{

		try
		{

			TableColumn column = new TableColumn(aTable, SWT.NONE);
			column.setData(COLUMN_COUNT);
			final int attributeCount = results.getAttrCount();
			final Vector<String> attributeNames = new Vector<String>(
				attributeCount);
			aTable.setData(attributeNames);
			/* Add columns. */
			int attributeIndex;
			for(attributeIndex = 0; attributeIndex < attributeCount; attributeIndex++)
			{
				column = new TableColumn(aTable, SWT.NONE);
				final String attributeName = results.getAttr(attributeIndex)
					.getName();
				column.setData(attributeName);
				attributeNames.add(attributeIndex, attributeName);
				column.setText(GenerateTBOs.makeTitleCase(attributeName, true));
			}
			attributeNames.add(attributeIndex, dqlQuery);
			int count = 1;
			/* Add data. */
			while(results.next())
			{
				try
				{
					Thread.sleep(1);
				} catch(InterruptedException ex)
				{
					MessageView.getInstance().addMessage(ex);
					break;
				}
				TableItem item = new TableItem(aTable, SWT.NONE);
				if(restrictionsAply && (count > ResultView.getMaxRowCount()))
				{
					item.setText(0, String.valueOf(count));
					item.setText(1, "MAX rows returned."); //$NON-NLS-1$
					break;
				}
				for(attributeIndex = 0; attributeIndex < attributeCount; attributeIndex++)
				{
					final IDfAttr attribute = results.getAttr(attributeIndex);
					final String attributeName = attribute.getName();
					String value = null;
					if(results.isAttrRepeating(attributeName))
					{
						if(attributeName.equals(IType.ATTR_TYPE))
						{
							value = this.getAttributeDatatypeString(results
								.getInt(attributeName));
						} else if(attributeName.equals(IType.ATTR_REPEATING))
						{
							value = getBooleanString(results
								.getString(attributeName));
						} else if((attributeName.equals(IType.ATTR_LENGTH))
							|| (attributeName.equals(IRegistered.COLUMN_LENGTH)))
						{
							try
							{
								final int length = results
									.getInt(attributeName);
								if(length > 0)
								{
									value = String.valueOf(length);
								} else
								{
									value = ""; //$NON-NLS-1$
								}
							} catch(final Exception swallow)
							{
								value = ""; //$NON-NLS-1$
							}
						} else if(attribute.getDataType() == IDfAttr.DM_BOOLEAN)
						{
							value = String.valueOf(results
								.getBoolean(attributeName));
						} else if(attribute.getDataType() == IDfAttr.DM_INTEGER)
						{
							if(attributeName.equals(IType.ATTR_TYPE))
							{
								value = this.getAttributeDatatypeString(results
									.getInt(attributeName));
							} else
							{
								value = results.getAllRepeatingStrings(
									attributeName, ", "); //$NON-NLS-1$
							}
						} else
						{
							value = results.getAllRepeatingStrings(
								attributeName, ", "); //$NON-NLS-1$
						}
					} else
					{
						if(attribute.getDataType() == IDfAttr.DM_BOOLEAN)
						{
							value = String.valueOf(results
								.getBoolean(attributeName));
						} else if(attribute.getDataType() == IDfAttr.DM_INTEGER)
						{
							if(attributeName.equals(IType.ATTR_TYPE))
							{
								value = getAttributeDatatypeString(results
									.getInt(attributeName));
							} else
							{
								value = results.getString(attributeName);
							}
						} else
						{
							value = results.getString(attributeName);
						}
					}
					if(value == null)
					{
						value = "null"; //$NON-NLS-1$
					}
					if(attributeIndex == 0)
					{
						item.setText(attributeIndex, String.valueOf(count++));
					}
					item.setText(attributeIndex + 1, value);
				}
			}
			/* Resize columns. */
			for(attributeIndex = 0; attributeIndex <= attributeCount; attributeIndex++)
			{
				aTable.getColumn(attributeIndex).pack();
			}
		} catch(DfException dex)
		{
			MessageView.getInstance().addMessage(dex);
		} finally
		{
			results.close();
		}
	}

	/**
	 * <p>
	 * Initialize the view part and creates the SWT controls for this workbench
	 * part.
	 * </p>
	 * <p>
	 * Clients should not call this method (the workbench calls this method when
	 * it needs to, which may be never).
	 * </p>
	 * <p>
	 * For implementors this is a multi-step process:
	 * <ol>
	 * <li>Create one or more controls within the parent.</li>
	 * <li>Set the parent layout as needed.</li>
	 * <li>Register any global actions with the <code>IActionService</code>.</li>
	 * <li>Register any popup menus with the <code>IActionService</code>.</li>
	 * <li>Register a selection provider with the
	 * <code>ISelectionService</code> (optional).</li>
	 * </ol>
	 * </p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 24, 2004 3:39:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aParent
	 *            the parent control
	 * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(final Composite aParent)
	{

		setParent(aParent);
		setTablesTabFolder(new CTabFolder(aParent, SWT.BORDER));
		getTablesTabFolder().addCTabFolder2Listener(new TabFolderListener());
		initActions();
	}

	/**
	 * Set the cancel check out action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 14:52:24</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return the cancel check out action.
	 */
	public CancelCheckOutAction getCancelCheckOutAction()
	{

		return this.cancelCheckOutAction;
	}

	/**
	 * Get the check in action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 15:07:00</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return Returns the checkInAction.
	 */

	public CheckInAction getCheckInAction()
	{

		return this.checkInAction;
	}

	/**
	 * Get the check in as major version action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>18.3.2005 11:17:52</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return The check in as major version action.
	 */

	public CheckInMajorAction getCheckInMajorAction()
	{

		return this.checkInMajorAction;
	}

	/**
	 * Get the check in as minor version action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>18.3.2005 11:18:08</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return The check in as minor version action.
	 */

	public CheckInMinorAction getCheckInMinorAction()
	{

		return this.checkInMinorAction;
	}

	/**
	 * Get the check out action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 10:06:50</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @return the check out action.
	 */
	public CheckOutAction getCheckOutAction()
	{

		return this.checkOutAction;
	}

	/**
	 * Get the dump object action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 12:21:19 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the dump objects attributes action.
	 */
	public DumpObjectAction getDumpObjectAction()
	{

		return this.dumpObjectAction;
	}

	/**
	 * Get the export results action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 12:11:29 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the export results action.
	 */
	public ExportResultsAction getExportResultsAction()
	{

		return this.exportResultsAction;
	}

	/**
	 * Get the export selected action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 12:21:12 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return export selected values action.
	 */
	public ExportSelectedAction getExportSelectedAction()
	{

		return this.exportSelectedAction;
	}

	/**
	 * Get the fetch action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 12:21:25 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the fetch api action.
	 */
	public FetchApiAction getFetchApiAction()
	{

		return this.fetchApiAction;
	}

	/**
	 * Get the get content action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 5:20:45 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the action that returns the content of a selected result.
	 */
	public GetContentAction getGetContentAction()
	{

		return this.getContentAction;
	}

	/**
	 * Set the return all query rows action.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 12:05:41</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @return The return all query rows action.
	 */
	public ReturnAllRowsAction getReturnAllRowsAction()
	{

		return this.returnAllRowsAction;
	}

	/**
	 * Get the all versions action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 4:08:05 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the all versions action.
	 */
	public ReturnAllVersionsAction getReturnAllVersionsAction()
	{

		return this.returnAllVersionsAction;
	}

	/**
	 * Get the selected table.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 26, 2004 4:51:21 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the selected table.
	 */
	public Table getSelectedTable()
	{

		return (Table) getTablesTabFolder().getSelection().getControl();
	}

	/**
	 * <p>
	 * <b>Does nothing! </b>
	 * </p>
	 * <p>
	 * Asks this part to take focus within the workbench.
	 * </p>
	 * <p>
	 * Clients should not call this method (the workbench calls this method at
	 * appropriate times). To have the workbench activate a part, use
	 * <code>IWorkbenchPage.activate(IWorkbenchPart) instead</code>.
	 * </p>
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 24, 2004 3:39:32 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @see org.eclipse.ui.IWorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus()
	{

		/* Nothing to do here. */
	}

	/**
	 * Get attributes data type as string.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 27, 2004 3:12:49 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param dataType
	 *            the data type.
	 * @return data type as an string.
	 */
	protected String getAttributeDatatypeString(final int dataType)
	{

		switch(dataType)
		{
			case IDfAttr.DM_BOOLEAN:
				return "boolean"; //$NON-NLS-1$
			case IDfAttr.DM_DOUBLE:
				return "double"; //$NON-NLS-1$
			case IDfAttr.DM_ID:
				return "id"; //$NON-NLS-1$
			case IDfAttr.DM_INTEGER:
				return "integer"; //$NON-NLS-1$
			case IDfAttr.DM_STRING:
				return "string"; //$NON-NLS-1$
			case IDfAttr.DM_TIME:
				return "time"; //$NON-NLS-1$
			default:
				return "undefined"; //$NON-NLS-1$
		}

	}

	/**
	 * Get the boolean string
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 27, 2004 3:25:55 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param value
	 *            't', 'T' & '1' return the string 'true'. All other values
	 *            return 'false'.
	 * @return a boolean string 'true' or 'false'
	 */
	protected String getBooleanString(final String value)
	{

		if((value.equalsIgnoreCase("t")) || (value.equals("1"))) //$NON-NLS-1$ //$NON-NLS-2$
		{
			return "true"; //$NON-NLS-1$
		}
		return "false"; //$NON-NLS-1$
	}

	/**
	 * Get the parent
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 4:14:50 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the parent.
	 */
	protected Composite getParent()
	{

		return this.parent;
	}

	/**
	 * Get the tables folder.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 4:15:07 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the tab folder.
	 */
	protected CTabFolder getTablesTabFolder()
	{

		return this.tablesTabFolder;
	}

	/**
	 * Initialize actions.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 12:54:55 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 */
	protected void initActions()
	{

		setDumpObjectAction(new DumpObjectAction());
		getDumpObjectAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_DUMP_OBJECT));
		getDumpObjectAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_DUMP_OBJECT_TOOLTIP));
		getDumpObjectAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.DUMP_ICON));
		getDumpObjectAction().init(this);

		setExportResultsAction(new ExportResultsAction());
		getExportResultsAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_EXPORT_RESULTS));
		getExportResultsAction().setToolTipText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_EXPORT_RESULTS_TOOLTIP));
		getExportResultsAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.EXPORT_ICON));
		getExportResultsAction().init(this);

		setExportSelectedAction(new ExportSelectedAction());
		getExportSelectedAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_EXPORT_SELECTED));
		getExportSelectedAction().setToolTipText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_EXPORT_SELECTED_TOOLTIP));
		getExportSelectedAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.EXPORT_ICON));
		getExportSelectedAction().init(this);

		setFetchApiAction(new FetchApiAction());
		getFetchApiAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_FETCH_SELECTED));
		getFetchApiAction().setToolTipText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_FETCH_SELECTED_TOOLTIP));
		getFetchApiAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.FETCH_ICON));
		getFetchApiAction().init(this);

		setReturnAllVersionsAction(new ReturnAllVersionsAction());
		getReturnAllVersionsAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_RETURN_VERSIONS));
		getReturnAllVersionsAction().setToolTipText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_RETURN_VERSIONS_TOOLTIP));
		getReturnAllVersionsAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.VERSIONS_ICON));
		getReturnAllVersionsAction().init(this);

		setReturnAllVersionsAction(new ReturnAllVersionsAction());
		getReturnAllVersionsAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_RETURN_VERSIONS));
		getReturnAllVersionsAction().setToolTipText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_RETURN_VERSIONS_TOOLTIP));
		getReturnAllVersionsAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.VERSIONS_ICON));
		getReturnAllVersionsAction().init(this);

		setGetContentAction(new GetContentAction());
		getGetContentAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_GET_CONTENT));
		getGetContentAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_GET_CONTENT_TOOLTIP));
		getGetContentAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.GET_CONTENT_ICON));
		getGetContentAction().init(this);

		setCheckOutAction(new CheckOutAction());
		getCheckOutAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CHECKOUT));
		getCheckOutAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CHECKOUT_TOOLTIP));
		getCheckOutAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CHECKOUT_ICON));
		getCheckOutAction().init(this);

		setCheckInAction(new CheckInAction());
		getCheckInAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CHECKIN));
		getCheckInAction().setToolTipText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CHECKIN_TOOLTIP));
		getCheckInAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CHECKIN_ICON));
		getCheckInAction().init(this);

		setCancelCheckOutAction(new CancelCheckOutAction());
		getCancelCheckOutAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CANCEL_CHECKOUT));
		getCancelCheckOutAction().setToolTipText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_CANCEL_CHECKOUT_TOOLTIP));
		getCancelCheckOutAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CANCEL_CHECKOUT_ICON));
		getCancelCheckOutAction().init(this);

		setCheckInMajorAction(new CheckInMajorAction());
		getCheckInMajorAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CHECKIN_MAJOR));
		getCheckInMajorAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_CHECKIN_MAJOR_TOOLTIP));
		getCheckInMajorAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CHECKIN_MAJOR_ICON));
		getCheckInMajorAction().init(this);

		setCheckInMinorAction(new CheckInMinorAction());
		getCheckInMinorAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_CHECKIN_MINOR));
		getCheckInMinorAction()
			.setToolTipText(
				DCTMPlugin
					.getResourceString(IDCTMPlugin.MSG_CHECKIN_MINOR_TOOLTIP));
		getCheckInMinorAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.CHECKIN_MINOR_ICON));
		getCheckInMinorAction().init(this);

		setReturnAllRowsAction(new ReturnAllRowsAction());
		getReturnAllRowsAction().setText(
			DCTMPlugin.getResourceString(IDCTMPlugin.MSG_RETURN_ALL_ROWS));
		getReturnAllRowsAction().setToolTipText(
			DCTMPlugin
				.getResourceString(IDCTMPlugin.MSG_RETURN_ALL_ROWS_TOOLTIP));
		getReturnAllRowsAction().setImageDescriptor(
			ImageCache.getImageDescriptor(IImageCache.RETURN_ALL_ROWS_ICON));
		getReturnAllRowsAction().init(this);

	}

	/**
	 * Get the cancel check out action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 14:51:50</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param aCancelCheckOutAction
	 *            the cancel check out action.
	 */
	protected void setCancelCheckOutAction(
											final CancelCheckOutAction aCancelCheckOutAction)
	{

		this.cancelCheckOutAction = aCancelCheckOutAction;
	}

	/**
	 * Set the check in action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 15:07:00</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param aCheckInAction
	 *            The checkInAction to set.
	 */
	protected void setCheckInAction(final CheckInAction aCheckInAction)
	{

		this.checkInAction = aCheckInAction;
	}

	/**
	 * Set the check in as major version action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>18.3.2005 11:17:48</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param aCheckInMajorAction
	 *            The check in as major version action.
	 */

	protected void setCheckInMajorAction(
											final CheckInMajorAction aCheckInMajorAction)
	{

		this.checkInMajorAction = aCheckInMajorAction;
	}

	/**
	 * Set the check in as minor version action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>18.3.2005 11:18:04</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param aCheckInMinorAction
	 *            The check in as minor version action.
	 */

	protected void setCheckInMinorAction(
											final CheckInMinorAction aCheckInMinorAction)
	{

		this.checkInMinorAction = aCheckInMinorAction;
	}

	/**
	 * Set the check out action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>27.2.2005 10:07:16</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0.5
	 * @param aCheckOutAction
	 *            the check out action to set.
	 */
	protected void setCheckOutAction(final CheckOutAction aCheckOutAction)
	{

		this.checkOutAction = aCheckOutAction;
	}

	/**
	 * Set the dump object action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 12:25:36 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDumpObjectAction
	 *            action to set.
	 */
	protected void setDumpObjectAction(final DumpObjectAction aDumpObjectAction)
	{

		this.dumpObjectAction = aDumpObjectAction;
	}

	/**
	 * Set the export results action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 12:20:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anExportResultsAction
	 *            action to set.
	 */
	protected void setExportResultsAction(
											final ExportResultsAction anExportResultsAction)
	{

		this.exportResultsAction = anExportResultsAction;
	}

	/**
	 * Set the export selected action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 12:25:38 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anExportSelectedAction
	 *            action to set.
	 */
	protected void setExportSelectedAction(
											final ExportSelectedAction anExportSelectedAction)
	{

		this.exportSelectedAction = anExportSelectedAction;
	}

	/**
	 * Set the fetch API action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 30, 2004 12:25:33 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aFetchApiAction
	 *            action to set.
	 */
	protected void setFetchApiAction(final FetchApiAction aFetchApiAction)
	{

		this.fetchApiAction = aFetchApiAction;
	}

	/**
	 * Set the get content action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 5:18:41 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aGetContentAction
	 *            the initialized action
	 */
	protected void setGetContentAction(final GetContentAction aGetContentAction)
	{

		this.getContentAction = aGetContentAction;
	}

	/**
	 * Set the parent.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 4:15:19 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aParent
	 *            the parent.
	 */
	protected void setParent(final Composite aParent)
	{

		this.parent = aParent;
	}

	/**
	 * Get the return all query rows action.
	 * <DL>
	 * <DT><B>Created :</B>
	 * <DD> 26.3.2005 12:05:18</DD>
	 * </DT>
	 * <DT><B>Author :</B>
	 * <DD>Christopher Harper account : HARPEC</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 2.0.0
	 * @param aReturnAllRowsAction
	 *            The new return all query rows action.
	 */
	protected void setReturnAllRowsAction(
											final ReturnAllRowsAction aReturnAllRowsAction)
	{

		this.returnAllRowsAction = aReturnAllRowsAction;
	}

	/**
	 * Set the return all versions action.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 23, 2004 4:11:18 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : harpechr</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aReturnAllVersionsAction
	 *            the initialized action.
	 */
	protected void setReturnAllVersionsAction(
												final ReturnAllVersionsAction aReturnAllVersionsAction)
	{

		this.returnAllVersionsAction = aReturnAllVersionsAction;
	}

	/**
	 * Set the tables folder.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Nov 29, 2004 4:15:23 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aTablesTabFolder
	 *            a tables folder
	 */
	protected void setTablesTabFolder(final CTabFolder aTablesTabFolder)
	{

		this.tablesTabFolder = aTablesTabFolder;
	}

}
