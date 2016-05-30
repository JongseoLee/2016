package com.js.ens.transformation.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.js.ens.transformation.core.Mediator;

public class HandlerButton implements Listener {
	private Mediator med = Mediator.getInstance();
	
	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getBtnImportPLog()){
			med.getC_btnImportPLog().execute();
		}else if(event.widget == med.getBtnApply()){
			med.getC_btnApply().execute();
		}
	}

}
