package com.js.ens.leveller.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.js.ens.leveller.core.Mediator;


public class HandlerSpinner implements Listener {
	private Mediator med = Mediator.getInstance();
	
	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		
		if(event.widget == med.getSpinnerUpperRollNum()){
			med.getCustomSpinnerUpperRollNum().execute();
		}else if(event.widget == med.getSpinnerLowerRollNum()){
			med.getCustomSpinnerLowerRollNum().execute();
		}else if(event.widget == med.getSpinnerDomain()){
			med.getCustomSpinnerDomain().execute();
		}else if(event.widget == med.getSpinnerThread()){
			med.getCustomSpinnerThread().execute();
		}
		
	}

}
