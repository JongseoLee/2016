package com.msc.adams.automation.customWidget;

import org.eclipse.swt.widgets.Label;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;

public class CustomLabel implements ICommand {
	
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private Label label;
	
	public CustomLabel(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(this.widgetName.equals(Mediator.LABEL_lblStep1)){
			// TODO Call MC Method 
			//System.out.println("DoubleClick Step1");
			MC.LabelDClickStep1();
		}else if(this.widgetName.equals(Mediator.LABEL_lblStep2)){
			// TODO Call MC Method 
			//System.out.println("DoubleClick Step2");
			MC.LabelDClickStep2();
		}else if(this.widgetName.equals(Mediator.LABEL_lblStep3)){
			// TODO Call MC Method 
			//System.out.println("DoubleClick Step3");
			MC.LabelDClickStep3();
		}else if(this.widgetName.equals(Mediator.LABEL_lblStep4)){
			// TODO Call MC Method 
			//System.out.println("DoubleClick Step4");
			MC.LabelDClickStep4();
		}else if(this.widgetName.equals(Mediator.LABEL_lblStep5)){
			// TODO Call MC Method 
			//System.out.println("DoubleClick Step5");
			MC.LabelDClickStep5();
		}
	}
	
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	//
	//
	public void setCustomWidget_lblStep1(){
		this.label = med.getLblStep1();
	}

	public void setCustomWidget_lblStep2(){
		this.label = med.getLblStep2();
	}
	
	public void setCustomWidget_lblStep3(){
		this.label = med.getLblStep3();
	}
	
	public void setCustomWidget_lblStep4(){
		this.label = med.getLblStep4();
	}
	
	public void setCustomWidget_lblStep5(){
		this.label = med.getLblStep5();
	}
	
	//
	//
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	
}
