package com.js.ens.transformation.fileMenuHandler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;

import com.js.ens.transformation.core.MainController;

public class File_ResultHandler extends AbstractHandler implements IHandler {
	private MainController MC = MainController.getInstance();
	public File_ResultHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		MC.ResultProject();
		return null;
	}

}
