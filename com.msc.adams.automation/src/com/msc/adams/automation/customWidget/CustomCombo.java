package com.msc.adams.automation.customWidget;


import org.eclipse.swt.widgets.Combo;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;

public class CustomCombo implements ICommand {
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private Combo combo;
	
	public CustomCombo(String widgetName,Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(this.widgetName.equals(Mediator.COMBO_comboBladeType)){
			// TODO Call MC method
			MC.ComboBladeType();
		}else if(this.widgetName.equals(Mediator.COMBO_comboLinkageType)){
			// TODO Call MC method
			MC.ComboLinkageType();
		}else if(this.widgetName.equals(Mediator.COMBO_comboWindshieldType)){
			MC.ComboWindshieldType();
			//System.out.println("Custom~");
		}
		
	}

	
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	//
	//
	public void setCustomWidget_comboBladeType(){
		this.combo = med.getComboBladeType();
	}
	
	public void setCustomWidget_comboLinkageType(){
		this.combo = med.getComboLinkageType();
	}
	
	public void setCustomWidget_comboWindshieldType(){
		this.combo = med.getComboWindshieldType();
	}
	//
	//
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	
}
