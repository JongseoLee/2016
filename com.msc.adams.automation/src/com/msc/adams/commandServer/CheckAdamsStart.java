package com.msc.adams.commandServer;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;
import com.msc.adams.automation.core.MessageWindow;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.util.myUtil;

public class CheckAdamsStart implements Runnable {
	
	private MainController MC = MainController.getInstance();
	private Mediator med = Mediator.getInstance();
	private String myLogFilePath;
	
	public CheckAdamsStart() {
		// TODO Auto-generated constructor stub
		this.myLogFilePath = myUtil.setPath(myUtil.setPath(MC.getWorkspace(),DatabaseFolderName.JobSpace),"myLog.log");
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(myUtil.checkPath(this.myLogFilePath)){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				med.getParentView().getDisplay().asyncExec(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						/*
						String allMsg = MC.getMsgWindow().addMessage(msg, MessageWindow.Info);
						med.getTextMessageWindow().setText(allMsg);
						med.getTextMessageWindow().setSelection(allMsg.length());
						// */
						med.getBtnSaveAllData().setEnabled(true);
						String allMsg = MC.getMsgWindow().addMessage("Loading Adams is complete.", MessageWindow.Info);
						med.getTextMessageWindow().setText(allMsg);
						med.getTextMessageWindow().setSelection(allMsg.length());
					}
					
				});
				break;
			}
		}
	}

}
