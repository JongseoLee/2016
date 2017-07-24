package com.msc.adams.automation.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.msc.adams.automation.core.Mediator;

public class HandlerList implements Listener {
	private Mediator med = Mediator.getInstance();
	
	public HandlerList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getListBladeDatabase()){
			med.getC_listBladeDatabase().execute();
		}else if(event.widget == med.getListLinkageDatabase()){
			med.getC_listLinkageDatabase().execute();
		}else if(event.widget == med.getListWindShieldDatabase()){
			med.getC_listWindShieldDatabase().execute();
		}else if(event.widget == med.getListPart()){
			med.getC_listPart().execute();
		}else if(event.widget == med.getListSwappingPart()){
			med.getC_listSwappingPart().execute();
		}else{
			//System.out.println("HandlerList");
		}
		
	}

}
