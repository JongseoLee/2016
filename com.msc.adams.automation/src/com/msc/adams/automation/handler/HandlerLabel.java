package com.msc.adams.automation.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.msc.adams.automation.core.Mediator;

public class HandlerLabel implements Listener {
	private Mediator med = Mediator.getInstance();
	
	public HandlerLabel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getLblStep1()){
			med.getC_lblStep1().execute();
		}else if(event.widget == med.getLblStep2()){
			med.getC_lblStep2().execute();
		}else if(event.widget == med.getLblStep3()){
			med.getC_lblStep3().execute();
		}else if(event.widget == med.getLblStep4()){
			med.getC_lblStep4().execute();
		}else if(event.widget == med.getLblStep5()){
			med.getC_lblStep5().execute();
		}
		
	}

}
