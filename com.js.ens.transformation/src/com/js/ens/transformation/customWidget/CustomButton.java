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
		}else if(widgetName.equals(Mediator.BUTTON_btnApply)){
			MC.Apply();
		}else if(widgetName.equals(Mediator.BUTTON_btnF1)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnF2)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnF3)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnF4)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnF5)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnF6)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnF7)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnExplorerYoungsModulus)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.Button_btnExplorerThermalExpansionCoefficient)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnExplorerPoissonsRatio)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			//MC.메소드 호출()
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			//MC.메소드 호출()
		}
		
	}
	
	
	public void setCustomWidget_btnImportPLog(){
		this.button = med.getBtnImportPLog();
	}
	
	public void setCustomWidget_btnApply(){
		this.button = med.getBtnApply();
	}
	
	public void setCustomWidget_btnF1(){
		this.button = med.getBtnF1();
	}

	public void setCustomWidget_btnF2(){
		this.button = med.getBtnF2();
	}
	
	public void setCustomWidget_btnF3(){
		this.button = med.getBtnF3();
	}
	
	public void setCustomWidget_btnF4(){
		this.button = med.getBtnF4();
	}
	
	public void setCustomWidget_btnF5(){
		this.button = med.getBtnF5();
	}
	
	public void setCustomWidget_btnF6(){
		this.button = med.getBtnF6();
	}
	
	public void setCustomWidget_btnF7(){
		this.button = med.getBtnF7();
	}
	
	public void setCustomWidget_btnConstant1_YM(){
		this.button = med.getBtnConstant1_YM();
	}
	
	public void setCustomWidget_btnTable1_YM(){
		this.button = med.getBtnTable1_YM();
	}
	
	public void setCustomWidget_btnExplorerYoungsModulus(){
		this.button = med.getBtnExplorerYoungsModulus();
	}
	
	public void setCustomWidget_btnConstant2_TEC(){
		this.button = med.getBtnConstant2_TEC();
	}
	
	public void setCustomWidget_btnTable2_TEC(){
		this.button = med.getBtnTable2_TEC();
	}
	
	public void setCustomWidget_btnExplorerThermalExpansionCoefficient(){
		this.button = med.getBtnExplorerThermalExpansionCoefficient();
	}
	
	public void setCustomWidget_btnConstant3_PR(){
		this.button = med.getBtnConstant3_PR();
	}
	
	public void setCustomWidget_btnTable3_PR(){
		this.button = med.getBtnTable3_PR();
	}
	
	public void setCustomWidget_btnExplorerPoissonsRatio(){
		this.button = med.getBtnExplorerPoissonsRatio();
	}
	
	public void setCustomWidget_btnParallelDDM(){
		this.button = med.getBtnParallelDDM();
	}
	
	public void setCustomWidget_btnParallelMultiThread(){
		this.button = med.getBtnParallelMultiThread();
	}
	
}
