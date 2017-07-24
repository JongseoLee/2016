package com.msc.adams.automation.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.msc.adams.automation.core.Mediator;

public class HandlerTable implements Listener {
	private Mediator med = Mediator.getInstance();
	
	public HandlerTable() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getTableSwappingPart()){
			med.getC_tableSwappingPart().execute();
		}else if(event.widget == med.getTableFatSolving()){
			med.getC_tableFatSolving().execute();
		}
	}

}
