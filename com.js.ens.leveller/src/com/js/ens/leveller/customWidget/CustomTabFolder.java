package com.js.ens.leveller.customWidget;

import org.eclipse.swt.widgets.TabFolder;

import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.core.Mediator;
import com.js.ens.leveller.handler.ICommand;


public class CustomTabFolder implements ICommand {
	private Mediator med;
	private LevellerMain LMain;
	private String widgetName;
	private TabFolder tabFolder;
	
	public CustomTabFolder(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.LMain = LevellerMain.getInstatnce();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(widgetName.equals(Mediator.TABFOLDER_tabFolder)){
			// tabItem id P Log , F1~F7
			//System.out.println(tabFolder.getSelection()[0].getText());
			// tabItem no 0 , 1
			//System.out.println(tabFolder.getSelectionIndex());
			if(tabFolder.getSelectionIndex() == 0){
				LMain.ChangedTabFolder(0);
			}else if(tabFolder.getSelectionIndex() == 1){
				LMain.ChangedTabFolder(1);
			}
		}
	}
	
	public void setCustomWidget_tabFolder(){
		this.tabFolder = med.getTabFolder();
		
	}

}
