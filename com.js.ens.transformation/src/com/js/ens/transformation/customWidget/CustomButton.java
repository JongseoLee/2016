package com.js.ens.transformation.customWidget;

import org.eclipse.swt.widgets.Button;

import com.js.ens.transformation.core.MainController;
import com.js.ens.transformation.core.Mediator;

public class CustomButton implements ICommand {
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private Button button;
	
	
	public CustomButton(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(widgetName.equals(Mediator.BUTTON_btnImportPLog)){
			MC.ImportPLog();
		}
		if(widgetName.equals(Mediator.BUTTON_btnApply)){
			MC.Apply();
		}
		
	}
	
	
	public void setCustomWidget_btnImportPLog(){
		this.button = med.getBtnImportPLog();
	}
	
	public void setCustomWidget_btnApply(){
		this.button = med.getBtnApply();
	}
}
