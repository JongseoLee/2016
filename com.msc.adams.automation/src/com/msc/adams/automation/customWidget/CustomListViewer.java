package com.msc.adams.automation.customWidget;

import org.eclipse.jface.viewers.ListViewer;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;

public class CustomListViewer implements ICommand {
	
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private ListViewer listViewer;
	
	
	public CustomListViewer(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(this.widgetName.equals(Mediator.LISTVIEWER_listViewerBladeDatabase)){
			// TODO Call MC method
		}else if(this.widgetName.equals(Mediator.LISTVIEWER_listViewerLinkageDatabase)){
			// TODO Call MC method
		}else if(this.widgetName.equals(Mediator.LISTVIEWER_listViewerWindShieldDatabase)){
		
		}else if(this.widgetName.equals(Mediator.LISTVIEWER_listViewerPart)){
			// TODO Call MC method
		}else if(this.widgetName.equals(Mediator.LISTVIEWER_listViewerSwappingPart)){
			// TODO Call MC method
		}
	}

}
