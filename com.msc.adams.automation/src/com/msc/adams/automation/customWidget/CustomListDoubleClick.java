package com.msc.adams.automation.customWidget;

import org.eclipse.swt.widgets.List;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;

public class CustomListDoubleClick implements ICommand {

	private Mediator med;
	private MainController MC;
	private String widgetName;
	private List list; 
	
	public CustomListDoubleClick(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(this.widgetName.equals(Mediator.LIST_listBladeDatabase)){
			//TODO Call MC method
			MC.ListDoubleClickBladeDatabase();
		}else if(this.widgetName.equals(Mediator.LIST_listLinkageDatabase)){
			//TODO Call MC method
			MC.ListDoubleClickLinkageDatabase();
		}
	}
	
	public void setCustomWidget_listBladeDatabase(){
		this.list = med.getListBladeDatabase();
	}
	
	public void setCustomWidget_listLinkageDatabase(){
		this.list = med.getListLinkageDatabase();
	}

}
