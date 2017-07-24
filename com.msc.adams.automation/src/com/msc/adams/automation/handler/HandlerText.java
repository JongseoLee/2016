package com.msc.adams.automation.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.msc.adams.automation.core.Mediator;

public class HandlerText implements Listener {
	private Mediator med = Mediator.getInstance();
	
	public HandlerText() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getTextMnfFilePath()){
			med.getC_textMnfFilePath().execute();
		}else if(event.widget == med.getTextRadiusValue()){
			med.getC_textRadiusValue().execute();
		}else if(event.widget == med.getTextXValue()){
			med.getC_textXValue().execute();
		}else if(event.widget == med.getTextYValue()){
			med.getC_textYValue().execute();
		}else if(event.widget == med.getTextZValue()){
			med.getC_textZValue().execute();
		}else if(event.widget == med.getTextError()){
			med.getC_textError().execute();
		}else if(event.widget == med.getTextHmax()){
			med.getC_textHmax().execute();
		}else if(event.widget == med.getTextNumberOfStep()){
			med.getC_textNumberOfStep().execute();
		}else if(event.widget == med.getTextEndTime()){
			med.getC_textEndTime().execute();
		}else if(event.widget == med.getTextExtraMassRatio()){
			med.getC_textExtraMassRatio().execute();
		}else if(event.widget == med.getTextNumberOfCycles()){
			med.getC_textNumberOfCycles().execute();
		}else if(event.widget == med.getTextStartTimeRange()){
			med.getC_textStartTimeRange().execute();
		}else if(event.widget == med.getTextEndTimeRange()){
			med.getC_textEndTimeRange().execute();
		}else if(event.widget == med.getTextIncrementFrame()){
			med.getC_textIncrementFrame().execute();
		}else if(event.widget == med.getTextResultName()){
			med.getC_textResultName().execute();
		}else if(event.widget == med.getTextCycleNumber()){
			med.getC_textCycleNumber().execute();
		}
	}

}
