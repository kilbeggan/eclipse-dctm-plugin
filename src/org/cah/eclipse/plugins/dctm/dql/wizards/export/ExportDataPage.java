/*-
 * $Log: ExportDataPage.java,v $
 * Revision 1.2  2005/12/04 22:17:22  madcook
 * New code formatting settings and all 3.0.0 resources to date added.
 *
 * Revision 1.1  2005/12/04 20:31:44  madcook
 * Version 3.0.0 work started and moved wizards to their own packages.
 *
 * Revision 1.8  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 
 * settings.
 *
 * Revision 1.7  2005/04/01 11:05:11  harpechr
 * Added images to dialogs.
 *
 * Revision 1.6  2005/03/25 09:18:58  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.5  2005/02/10 11:33:38  harpechr
 * Safety commit!
 *
 * Revision 1.4  2005/02/09 14:01:37  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:56  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:02:17  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:49  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.eclipse.plugins.dctm.dql.wizards.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.cah.eclipse.plugins.dctm.dql.DCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.IDCTMPlugin;
import org.cah.eclipse.plugins.dctm.dql.views.MessageView;
import org.cah.eclipse.plugins.dctm.dql.views.ResultView;
import org.cah.eclipse.plugins.dctm.dql.wizards.listeners.ExportSelectionListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;


/**
 * <H4>Export query results functionality.</H4>
 * <DL>
 * <DT><B>Copyright : </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created : </B>
 * <DD>Dec 2, 2004 12:39:00 PM.</DD>
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
public class ExportDataPage
							extends
								WizardPage
{

	/**
	 * The file save as dialog.
	 * 
	 * @since 1.0
	 */
	private FileDialog	dialog					= null;

	/**
	 * The filename text box.
	 * 
	 * @since 1.0
	 */
	private Text		filenameText			= null;

	/**
	 * Include headers(column names) in the exported file.
	 * 
	 * @since 1.0
	 */
	private Button		includeFileHeaderButton	= null;

	/**
	 * Column separator text.
	 * 
	 * @since 1.0
	 */
	private Text		separatorText			= null;

	/**
	 * Constructor withe the page name given.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:39:00 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param pageName
	 *            name of the page.
	 */
	public ExportDataPage(final String pageName)
	{

		super(pageName);
	}

	/**
	 * Constructor with the page name, title and image given.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:39:00 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param pageName
	 *            name of the export page.
	 * @param title
	 *            title of the export page.
	 * @param titleImage
	 *            image of the export page.
	 */
	public ExportDataPage(final String pageName, final String title,
							final ImageDescriptor titleImage)
	{

		super(pageName, title, titleImage);
	}

	/**
	 * Initialize the controls on the export page.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:39:00 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param parent
	 *            the parent control.
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(final Composite parent)
	{

		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		layout.verticalSpacing = 9;

		setIncludeFileHeaderButton(new Button(container, SWT.CHECK | SWT.RIGHT));
		getIncludeFileHeaderButton().setSelection(true);
		getIncludeFileHeaderButton().setLayoutData(
			new GridData(GridData.HORIZONTAL_ALIGN_END));

		Label label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_INCLUDE_COLUMN_NAMES));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		setSeparatorText(new Text(container, SWT.BORDER | SWT.SINGLE));
		getSeparatorText().setText(","); //$NON-NLS-1$
		getSeparatorText()
			.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label = new Label(container, SWT.NULL);
		label.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_FIELD_SEPARATOR_ISTRUCTIONS));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		setFilenameText(new Text(container, SWT.BORDER | SWT.SINGLE));
		getFilenameText().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		final Button button = new Button(container, SWT.PUSH);
		button.setText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_BROWSE_FOR_FILE));
		button.setToolTipText(DCTMPlugin
			.getResourceString(IDCTMPlugin.MSG_BROWSE_FOR_FILE_TOOLTIP));
		button.addSelectionListener(new ExportSelectionListener(this));

		setDialog(new FileDialog(getContainer().getShell(), SWT.SAVE));

		setControl(container);
		setPageComplete(true);
	}

	/**
	 * Get the file dialog.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:46:00 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the dialog
	 */
	public FileDialog getDialog()
	{

		return this.dialog;
	}

	/**
	 * Get the file name text box.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:46:01 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the filenameText
	 */
	public Text getFilenameText()
	{

		return this.filenameText;
	}

	/**
	 * Do the actual export.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:56:16 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return true if the save is ok.
	 */
	public boolean performFinish()
	{

		final boolean includeFileHeader = getIncludeFileHeaderButton()
			.getSelection();
		/* Don't trim separator or filename */
		String separator = getSeparatorText().getText();
		final String fileName = getFilenameText().getText();
		/* Don't check if separator is blank, cuz it can be! */
		if(fileName.equals("")) //$NON-NLS-1$
		{
			MessageDialog.openError(getShell(),
				"Required Fields", "Filename is required"); //$NON-NLS-1$ //$NON-NLS-2$
			return false;
		}
		/* convert "\t" to tab. */
		if(separator.equals("\\t")) //$NON-NLS-1$
		{
			separator = "\t"; //$NON-NLS-1$
		}
		/* Export Data. */
		final File file = new File(fileName);
		// If exists, delete
		if(file.exists())
		{
			file.delete();
		}
		FileOutputStream fileOutputStream = null;
		try
		{
			file.createNewFile();
			fileOutputStream = new FileOutputStream(file);
		} catch(Exception e)
		{
			MessageView.getInstance().addMessage(e);
		}
		final Table selectedTable = ResultView.getInstance().getSelectedTable();
		final TableColumn[] tableColumns = selectedTable.getColumns();
		/* Construct header. */
		StringBuffer outputBuffer = null;
		if(includeFileHeader)
		{
			outputBuffer = new StringBuffer();
			for(int tableColumnIndex = 0; tableColumnIndex < tableColumns.length; tableColumnIndex++)
			{
				if(tableColumnIndex > 0)
				{
					outputBuffer.append(separator);
				}
				outputBuffer.append(tableColumns[tableColumnIndex].getData());
			}
			try
			{
				outputBuffer.append(IDCTMPlugin.NEWLINE);
				fileOutputStream.write(outputBuffer.toString().getBytes());
			} catch(IOException e)
			{
				MessageView.getInstance().addMessage(e);
			}
		}
		/* Construct data, each row. */
		final TableItem[] tableItems = selectedTable.getSelection();
		for(int tableItemIndex = 0; tableItemIndex < tableItems.length; tableItemIndex++)
		{
			outputBuffer = new StringBuffer();
			/* Each column. */
			for(int tableColumnIndex = 0; tableColumnIndex < tableColumns.length; tableColumnIndex++)
			{
				if(tableColumnIndex > 0)
				{
					outputBuffer.append(separator);
				}
				final String text = tableItems[tableItemIndex]
					.getText(tableColumnIndex);
				if((text != null) && (!text.equals("null"))) //$NON-NLS-1$
				{
					outputBuffer.append(text);
				}
				if(tableColumnIndex + 1 == tableColumns.length)
				{
					outputBuffer.append(separator);
				}
			}
			try
			{
				outputBuffer.append(IDCTMPlugin.NEWLINE);
				fileOutputStream.write(outputBuffer.toString().getBytes());
			} catch(IOException e)
			{
				MessageView.getInstance().addMessage(e);
			}
		}
		try
		{
			fileOutputStream.flush();
			fileOutputStream.close();
			MessageView.getInstance().addMessage(
				DCTMPlugin.getResourceString(IDCTMPlugin.MSG_EXPORT_SUCCESS)
					+ fileName + '.');
		} catch(IOException e)
		{
			MessageView.getInstance().addMessage(e);
		}
		return true;
	}

	/**
	 * Get the include column names check box.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:46:01 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the includeFileHeaderButton
	 */
	protected Button getIncludeFileHeaderButton()
	{

		return this.includeFileHeaderButton;
	}

	/**
	 * Get the column separator text box.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:46:01 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return Returns the separatorText
	 */
	protected Text getSeparatorText()
	{

		return this.separatorText;
	}

	/**
	 * Set the file dialog.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:46:00 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDialog
	 *            The dialog to set
	 */
	protected void setDialog(final FileDialog aDialog)
	{

		this.dialog = aDialog;
	}

	/**
	 * Set the file name text box.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:46:01 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aFilenameText
	 *            The filenameText to set
	 */
	protected void setFilenameText(final Text aFilenameText)
	{

		this.filenameText = aFilenameText;
	}

	/**
	 * Set include column names check box.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:46:01 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anIncludeFileHeaderButton
	 *            The includeFileHeaderButton to set
	 */
	protected void setIncludeFileHeaderButton(
												final Button anIncludeFileHeaderButton)
	{

		this.includeFileHeaderButton = anIncludeFileHeaderButton;
	}

	/**
	 * Set the separator text box.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 2, 2004 12:46:01 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aSeparatorText
	 *            The separatorText to set
	 */
	protected void setSeparatorText(final Text aSeparatorText)
	{

		this.separatorText = aSeparatorText;
	}

}
