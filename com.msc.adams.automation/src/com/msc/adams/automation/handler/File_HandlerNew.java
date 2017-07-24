package com.msc.adams.automation.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;

import com.msc.adams.automation.core.MainController;

public class File_HandlerNew extends AbstractHandler implements IHandler {
	private MainController MC = MainController.getInstance();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		MC.executeFileNew();
		return null;
	}

}
