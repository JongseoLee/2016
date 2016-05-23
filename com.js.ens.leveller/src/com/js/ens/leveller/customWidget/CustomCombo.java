package com.js.ens.leveller.customWidget;

import org.eclipse.swt.widgets.Combo;

import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.core.Mediator;
import com.js.ens.leveller.handler.ICommand;

public class CustomCombo implements ICommand{
	private Mediator med;
	private LevellerMain LMain;
	private String widgetName;
	private Combo combo;
	
	public CustomCombo(String widgetName, Mediator med){
		this.widgetName = widgetName;
		this.med = med;
		this.LMain = LevellerMain.getInstatnce();
		
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(widgetName.equals(Mediator.COMBO_Type)){
			String type = med.getComboType().getText();
			//System.out.println("=>Combo Type : "+type);
			LMain.ChangePlateType();
		}
	}
	
	public void setCustomWidget_comboType(){
		this.combo = med.getComboType();
	}
	
}