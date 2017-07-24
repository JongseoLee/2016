package com.msc.adams.automation.customWidget;

import org.eclipse.swt.widgets.List;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;

public class CustomList implements ICommand {
	
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private List list;
	
	public CustomList(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(this.widgetName.equals(Mediator.LIST_listBladeDatabase)){
			// TODO Call MC method
			//System.out.println(this.list.getItem(this.list.getSelectionIndex()));
			MC.ListSelectBladeDatabase();
		}else if(this.widgetName.equals(Mediator.LIST_listLinkageDatabase)){
			// TODO Call MC method
			//System.out.println(this.list.getItem(this.list.getSelectionIndex()));
			MC.ListSelectLinkageDatabase();
		}else if(this.widgetName.equals(Mediator.LIST_listWindShieldDatabase)){
			MC.ListSelectWindshieldDatabase();
		}else if(this.widgetName.equals(Mediator.LIST_listPart)){
			// TODO Call MC method
			MC.ListSelectPartDatabase();
		}else if(this.widgetName.equals(Mediator.LIST_listSwappingPart)){
			// TODO Call MC method
			MC.ListSelectSwappingPartDatabase();
		}else {
			//System.out.println("CustomList");
		}
	}
	
	
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	//
	//
	public void setCustomWidget_listBladeDatabase(){
		this.list = med.getListBladeDatabase();
	}
	
	public void setCustomWidget_listLinkageDatabase(){
		this.list = med.getListLinkageDatabase();
	}
	
	public void setCustomWidget_listWindshieldDatabase(){
		this.list = med.getListWindShieldDatabase();
	}
	
	public void setCustomWidget_listPart(){
		this.list = med.getListPart();
	}
	
	public void setCustomWidget_listSwappingPart(){
		this.list = med.getListSwappingPart();
	}
	//
	//
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	

}
