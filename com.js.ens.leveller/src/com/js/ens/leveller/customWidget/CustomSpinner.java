package com.js.ens.leveller.customWidget;

import org.eclipse.swt.widgets.Spinner;

import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.core.Mediator;
import com.js.ens.leveller.handler.ICommand;

public class CustomSpinner implements ICommand {
	private Mediator med;
	private LevellerMain LMain;
	private String widgetName;
	private Spinner spinner;
	
	public CustomSpinner(String widgetName, Mediator med){
		this.widgetName = widgetName;
		this.med = med;
		this.LMain = LevellerMain.getInstatnce();
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		if(widgetName.equals(Mediator.SPINNER_UpperRollNum)){
			String num = med.getSpinnerUpperRollNum().getText();
			//System.out.println("=> Upper Spinner : "+num);
			LMain.setSpinnerUpperRollNum(num);
		}
		
		if(widgetName.equals(Mediator.SPINNER_LowerRollNum)){
			String num = med.getSpinnerLowerRollNum().getText();
			//System.out.println("=>Lower Spinner : "+num);
			LMain.setSpinnerLowerRollNum(num);
		}
		
		if(widgetName.equals(Mediator.SPINNER_Domain)){
			String num = med.getSpinnerDomain().getText();
			//System.out.println("Domain Spinner : "+num);
			LMain.setSpinnerDomain(num);
		}
		
		if(widgetName.equals(Mediator.SPINNER_Thread)){
			String num = med.getSpinnerThread().getText();
			LMain.setSpinnerThread(num);
		}
	}
	
	public void setCustomWidget_spinnerUpperRollNum(){
		this.spinner = med.getSpinnerUpperRollNum();
	}
	
	public void setCustomWidget_spinnerLowerRollNum(){
		this.spinner = med.getSpinnerLowerRollNum();
	}
	
	public void setCustomWidget_spinnerDomain(){
		this.spinner = med.getSpinnerDomain();
	}
	
	public void setCustomWidget_spinnerThread(){
		this.spinner = med.getSpinnerThread();
	}
}
