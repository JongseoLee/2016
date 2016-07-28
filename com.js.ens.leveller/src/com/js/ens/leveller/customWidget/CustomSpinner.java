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
		
		
		
		
		if(widgetName.equals(Mediator.SPINNER_UpperRollNum_2D)){
			String num = med.getSpinnerUpperRollNum_2D().getText();
			//LMain.setSpinnerUpperRollNum(num);
		}
		
		if(widgetName.equals(Mediator.SPINNER_LowerRollNum_2D)){
			String num = med.getSpinnerLowerRollNum_2D().getText();
			//LMain.setSpinnerLowerRollNum(num);
		}
		                              
		if(widgetName.equals(Mediator.SPINNER_Domain_2D)){
			String num = med.getSpinnerDomain_2D().getText();
			//LMain.setSpinnerDomain(num);
		}
		
		if(widgetName.equals(Mediator.SPINNER_Thread_2D)){
			String num = med.getSpinnerThread_2D().getText();
			//LMain.setSpinnerThread(num);
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
	
	
	
	
	public void setCustomWidget_spinnerUpperRollNum_2D(){
		this.spinner = med.getSpinnerUpperRollNum_2D();
	}
	
	public void setCustomWidget_spinnerLowerRollNum_2D(){
		this.spinner = med.getSpinnerLowerRollNum_2D();
	}
	
	public void setCustomWidget_spinnerDomain_2D(){
		this.spinner = med.getSpinnerDomain_2D();
	}
	
	public void setCustomWidget_spinnerThread_2D(){
		this.spinner = med.getSpinnerThread_2D();
	}
	
	
	
}
