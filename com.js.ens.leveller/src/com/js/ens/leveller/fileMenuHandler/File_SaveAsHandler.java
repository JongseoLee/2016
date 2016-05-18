package com.js.ens.leveller.fileMenuHandler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.dialog.SaveAsLevellerDlg;

public class File_SaveAsHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		SaveAsLevellerDlg saveLevellerDlg = new SaveAsLevellerDlg(Display.getCurrent().getActiveShell());
		saveLevellerDlg.open();
		return null;
	}

}
