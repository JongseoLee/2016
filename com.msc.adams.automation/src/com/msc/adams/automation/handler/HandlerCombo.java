package com.msc.adams.automation.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.msc.adams.automation.core.Mediator;

public class HandlerCombo implements Listener {
	private Mediator med = Mediator.getInstance();
	
	public HandlerCombo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getComboBladeType()){
			//System.out.println("aaa");
			med.getC_comboBladeType().execute();
		}else if(event.widget == med.getComboLinkageType()){
			//System.out.println("bbb");
			med.getC_comboLinkageType().execute();
		}else if(event.widget == med.getComboWindshieldType()){
			//System.out.println("ws combo");
			med.getC_comboWindshieldType().execute();
		}
	}

}
