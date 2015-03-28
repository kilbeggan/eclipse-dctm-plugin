package org.cah.eclipse.plugins.dctm.dql.actions.results;

import org.cah.eclipse.plugins.dctm.dql.views.ResultView;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

public class CopyCellValueToClipboardAction extends AbstractResultAction {
	@Override
	protected boolean runRow(final TableItem tableItem) {
		final ResultView view = (ResultView) getView();
		final String cellValue = view.getMouseClickCellValue();
		
		if (cellValue != null && cellValue.length() > 0) {
			final Clipboard cb = new Clipboard(Display.getCurrent());
			TextTransfer textTransfer = TextTransfer.getInstance();
			cb.setContents(new Object[] { cellValue }, new Transfer[] { textTransfer });
		}

		return true;
	}
}
