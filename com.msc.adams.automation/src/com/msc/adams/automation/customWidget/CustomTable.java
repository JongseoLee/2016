package com.msc.adams.automation.customWidget;

import org.eclipse.swt.widgets.Table;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;

public class CustomTable implements ICommand {

	private Mediator med;
	private MainController MC;
	private String widgetName;
	private Table table;
	
	public CustomTable(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(this.widgetName.equals(Mediator.TABLE_tableSwappingPart)){
			// TODO Call MC method
			med.getTableSwappingPart().deselectAll();
		}else if(this.widgetName.equals(Mediator.TABLE_tableFatSolving)){
			med.getTableFatSolving().deselectAll();
		}
	}
	
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	//
	//
	public void setCustomWidget_tableSwappingPart(){
		this.table = med.getTableSwappingPart();
	}
	
	public void setCustomWidget_tableFatSolving(){
		this.table = med.getTableFatSolving();
	}
	//
	//
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	
}
