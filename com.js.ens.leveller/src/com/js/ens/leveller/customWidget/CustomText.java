package com.js.ens.leveller.customWidget;

import org.eclipse.swt.widgets.Text;

import com.js.ens.leveller.core.Mediator;
import com.js.ens.leveller.handler.ICommand;


public class CustomText implements ICommand {
	private Mediator med;
	private String widgetName;
	private Text text;
	
	public CustomText(String widgetName, Mediator med){
		this.widgetName = widgetName;
		this.med = med;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		/*
		if(widgetName.equals(Mediator.TEXT_COMMON_DATA)){
			
		}
		*/
	}
	
	/*
	public void setCustomWidget_textCommonData(){
		this.text = med.getTextCommonData(); 
	}
	*/
}
