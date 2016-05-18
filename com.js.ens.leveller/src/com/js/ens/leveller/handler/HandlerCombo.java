package com.js.ens.leveller.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.js.ens.leveller.core.Mediator;

public class HandlerCombo implements Listener {
	private Mediator med = Mediator.getInstance();
	
	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getComboType()){
			med.getCustomComboType().execute();
		}
	}

}
