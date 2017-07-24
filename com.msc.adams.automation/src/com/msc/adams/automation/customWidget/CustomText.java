package com.msc.adams.automation.customWidget;

import org.eclipse.swt.widgets.Text;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;

public class CustomText implements ICommand {

	private Mediator med;
	private MainController MC;
	private String widgetName;
	private Text text;
	
	public CustomText(String widgetName,Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(this.widgetName.equals(Mediator.TEXT_textMnfFilePath)){
			// TODO Call MC method
			MC.TextChangeMnfFilePath();
		}else if(this.widgetName.equals(Mediator.TEXT_textRadiusValue)){
			MC.TextChangeRadiusValue();
		}else if(this.widgetName.equals(Mediator.TEXT_textXValue)){
			MC.TextChangeXValue();
		}else if(this.widgetName.equals(Mediator.TEXT_textYValue)){
			MC.TextChangeYValue();
		}else if(this.widgetName.equals(Mediator.TEXT_textZValue)){
			MC.TextChangeZValue();
		}else if(this.widgetName.equals(Mediator.TEXT_textError)){
			MC.TextChangeError();
		}else if(this.widgetName.equals(Mediator.TEXT_textHmax)){
			MC.TextChangeHmax();
		}else if(this.widgetName.equals(Mediator.TEXT_textNumberOfStep)){
			MC.TextChangeNumberOfStep();
		}else if(this.widgetName.equals(Mediator.TEXT_textEndTime)){
			MC.TextChangeEndTime();
		}else if(this.widgetName.equals(Mediator.TEXT_textExtraMassRatio)){
			MC.TextChangedExtraMassRatio();
		}else if(this.widgetName.equals(Mediator.TEXT_textNumberOfCycles)){
			MC.TextChangedNumberOfCycles();
		}else if(this.widgetName.equals(Mediator.TEXT_textStartTimeRange)){
			MC.TextChangedStartTimeRange();
		}else if(this.widgetName.equals(Mediator.TEXT_textEndTimeRange)){
			MC.TextChangedEndTimeRange();
		}else if(this.widgetName.equals(Mediator.TEXT_textIncrementFrame)){
			MC.TextChangedIncrementFrame();
		}else if(this.widgetName.equals(Mediator.TEXT_textResultName)){
			MC.TextChangeResultName();
		}else if(this.widgetName.equals(Mediator.TEXT_textCycleNumber)){
			MC.TextChangeCycleNumber();
		}
	}
	
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	//
	//
	public void setCustomWidget_textMnfFilePath(){
		this.text = med.getTextMnfFilePath();
	}
	
	public void setCustomWidget_textRadiusValue(){
		this.text = med.getTextRadiusValue();
	}
	
	public void setCustomWidget_textXValue(){
		this.text = med.getTextXValue();
	}

	public void setCustomWidget_textYValue(){
		this.text = med.getTextYValue();
	}
	
	public void setCustomWidget_textZValue(){
		this.text = med.getTextZValue();
	}
	
	public void setCustomWidget_textError(){
		this.text = med.getTextError();
	}
	
	public void setCustomWidget_textHmax(){
		this.text = med.getTextHmax();
	}
	
	public void setCustomWidget_textNumberOfStep(){
		this.text = med.getTextNumberOfStep();
	}
	
	public void setCustomWidget_textEndTime(){
		this.text = med.getTextEndTime();
	}
	
	public void setCustomWidget_textExtraMassRatio(){
		this.text = med.getTextExtraMassRatio();
	}
	
	public void setCustomWidget_textNumberOfCycles(){
		this.text = med.getTextNumberOfCycles();
	}
	
	public void setCustomWidget_textStartTimeRange(){
		this.text = med.getTextStartTimeRange();
	}
	
	public void setCustomWidget_textEndTimeRange(){
		this.text = med.getTextEndTimeRange();
	}
	
	public void setCustomWidget_textIncrementFrame(){
		this.text = med.getTextIncrementFrame();
	}
	
	public void setCustomWidget_textResultName(){
		this.text = med.getTextResultName();
	}
	
	public void setCustomWidget_textCycleNumber(){
		this.text = med.getTextCycleNumber();
	}
	//
	//
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	
}
