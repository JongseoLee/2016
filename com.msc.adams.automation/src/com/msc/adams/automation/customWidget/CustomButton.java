package com.msc.adams.automation.customWidget;

import org.eclipse.swt.widgets.Button;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;

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
		if(this.widgetName.equals(Mediator.BUTTON_btnPrevious)){
			// TODO Call MC method
			MC.ButtonDoPrevious();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnNext)){
			// TODO Call MC method
			MC.ButtonDoNext();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnEditAllData)){
			// TODO Call MC method
			MC.ButtonEditAllData();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnSaveAllData)){
			// TODO Call MC method
			MC.ButtonSaveAllData();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnReloadDb)){
			// TODO Call MC method
			MC.ButtonReloadDB();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnNone)){
			MC.ButtonNone();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnAdd)){
			// TODO Call MC method
			MC.ButtonAddPart();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnDel)){
			// TODO Call MC method
			MC.ButtonRemovePart();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnExplorerStep3)){
			// TODO Call MC method
			MC.ButtonExplorerStep3();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnStartSolving)){
			// TODO Call MC method
			MC.ButtonStartSolving();
		}/*else if(this.widgetName.equals(Mediator.BUTTON_btnStopSolving)){
			MC.ButtonStopSolving();
		}// */
		else if(this.widgetName.equals(Mediator.BUTTON_btnStartAnimation)){
			MC.ButtonStartAnimation();
		}
		else if(this.widgetName.equals(Mediator.BUTTON_btnGSTIFF)){
			MC.ButtonGSTIFF();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnWSTIFF)){
			MC.ButtonWSTIFF();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnI3)){
			MC.ButtonI3();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnSI2)){
			MC.ButtonSI2();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnOriginal)){
			MC.ButtonOriginal();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnModified)){
			MC.ButtonModified();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnExtraMassOn)){
			MC.ButtonExtraMassOn();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnExtraMassOff)){
			MC.ButtonExtraMassOff();
		}
		
		else if(this.widgetName.equals(Mediator.BUTTON_btnModelData)){
			// TODO Call MC method
			MC.ButtonModelData();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnDACFile)){
			// TODO Call MC method
			MC.ButtonDACFile();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnModelDataBin)){
			MC.ButtonModelDataBin();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnForceFile)){
			MC.ButtonForceFile();
		}
		else if(this.widgetName.equals(Mediator.BUTTON_btnExportResult)){
			// TODO Call MC method
			MC.ButtonExportResult();
		}
		
		else if(this.widgetName.equals(Mediator.BUTTON_btnSolvingAndCreate)){
			MC.ButtonSolvingAndCreate();
		}
	}
	
	
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	//
	//
	public void setCustomWidget_btnPrevious(){
		this.button = med.getBtnPrevious();
	}
	
	public void setCustomWidget_btnNext(){
		this.button = med.getBtnNext();
	}
	
	public void setCustomWidget_btnEditAllData(){
		this.button = med.getBtnEditAllData();
	}
	
	public void setCustomWidget_btnSaveAllData(){
		this.button = med.getBtnSaveAllData();
	}
	
	public void setCustomWidget_btnReloadDB(){
		this.button = med.getBtnReloadDb();
	}
	
	public void setCustomWidget_btnNone(){
		this.button = med.getBtnNone();
	}
	
	public void setCustomWidget_btnAdd(){
		this.button = med.getBtnAdd();
	}
	
	public void setCustemWidget_btnDel(){
		this.button = med.getBtnDel();
	}
	
	public void setCustomWidget_btnExplorerStep3(){
		this.button = med.getBtnExplorerStep3();
	}
	
	public void setCustomWidget_btnStartSolving(){
		this.button = med.getBtnStartSolving();
	}
	
	/*
	public void setCustomWidget_btnStopSolving(){
		this.button = med.getBtnStopSolving();
	}// */
	
	public void setCustomWidget_btnStartAnimation(){
		this.button = med.getBtnStartAnimation();
	}
	
	public void setCustomWidget_btnGSTIFF(){
		this.button = med.getBtnGSTIFF();
	}
	
	public void setCustomWidget_btnWSTIFF(){
		this.button = med.getBtnWSTIFF();
	}
	
	public void setCustomWidget_btnI3(){
		this.button = med.getBtnI3();
	}
	
	public void setCustomWidget_btnSI2(){
		this.button = med.getBtnSI2();
	}
	
	public void setCustomWidget_btnOriginal(){
		this.button = med.getBtnOriginal();
	}
	
	public void setCustomWidget_btnModified(){
		this.button = med.getBtnModified();
	}
	
	public void setCustomWidget_btnExtraMassOn(){
		this.button = med.getBtnExtraMassOn();
	}
	
	public void setCustomWidget_btnExtraMassOff(){
		this.button = med.getBtnExtraMassOff();
	}
	
	public void setCustomWidget_btnModelData(){
		this.button = med.getBtnModelData();
	}
	
	public void setCustomWidget_btnDACFile(){
		this.button = med.getBtnDACFile();
	}
	
	public void setCustomWidget_btnModelDataBin(){
		this.button = med.getBtnModelDataBin();
	}
	
	public void setCustomWidget_btnForceFile(){
		this.button = med.getBtnForceFile();
	}
	
	public void setCustomWidget_btnExportResult(){
		this.button = med.getBtnExportResult();
	}
	
	public void setCustomWidget_btnSolvingAndCreate(){
		this.button = med.getBtnSolvingAndCreate();
	}
	
	//
	//
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	
}
