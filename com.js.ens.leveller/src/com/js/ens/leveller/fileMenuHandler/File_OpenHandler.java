package com.js.ens.leveller.fileMenuHandler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.dialog.OpenLevellerDlg;

public class File_OpenHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		OpenLevellerDlg openLevellerDlg = new OpenLevellerDlg(Display.getCurrent().getActiveShell());
		openLevellerDlg.open();
		return null;
	}

}
