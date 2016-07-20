package com.js.ens.leveller.customWidget;

import java.util.Map;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.InitValue;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.core.Mediator;
import com.js.ens.leveller.dialog.ApplyConfirmDlg;
import com.js.ens.leveller.dialog.ExportProcedureDlg;
import com.js.ens.leveller.dialog.ShowRollTableDlg;
import com.js.ens.leveller.handler.ICommand;
import com.js.util.myUtil;





public class CustomButton implements ICommand {
	private Mediator med;
	private LevellerMain LMain;
	private String widgetName;
	private Button button;
	private Map<String,String> InitValueMap; 
	
	public CustomButton(String widgetName, Mediator med){
		this.widgetName = widgetName;
		this.med = med;
		this.LMain = LevellerMain.getInstatnce();
		this.InitValueMap = LMain.getInitValueMap();
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		if(widgetName.equals(Mediator.BUTTON_CalcElementNum)){
			//System.out.println("=>Btn Calc Number of Elements");
			LMain.CalcElementNumber();
		}
		
		if(widgetName.equals(Mediator.BUTTON_btnNone)){
			System.out.println("selected "+ med.getBtnNone().getText() +" : "+med.getBtnNone().getSelection());
		}
		
		if(widgetName.equals(Mediator.BUTTON_btnUpper)){
			System.out.println("selected "+ med.getBtnUpper().getText() +" : "+med.getBtnUpper().getSelection());
		}
		
		if(widgetName.equals(Mediator.BUTTON_btnUpper)){
			System.out.println("selected "+ med.getBtnLower().getText() +" : "+med.getBtnLower().getSelection());
		}
		
		if(widgetName.equals(Mediator.BUTTON_btnShowRollTable)){
			ShowRollTableDlg showRollTableDlg = new ShowRollTableDlg(Display.getCurrent().getActiveShell());
			showRollTableDlg.open();
			
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioNone_RC)){
			med.getTextRollCrown().setEnabled(false);
			med.getTextRollCrown().setText(InitValueMap.get("RollCrown"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioApply_RC)){
			med.getTextRollCrown().setEnabled(true);
			med.getTextRollCrown().setText(InitValueMap.get("RollCrown"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioRigid_MS)){
			med.getTextMillStiffness().setEnabled(false);
			med.getTextMillStiffness().setText(InitValueMap.get("MillStiffness"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioSpring_MS)){
			med.getTextMillStiffness().setEnabled(true);
			med.getTextMillStiffness().setText(InitValueMap.get("MillStiffness"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_CreateRoll)){
			// Action
			//System.out.println("=>Btn Create Roll");
			LMain.CreateRoll();
		}
		
		/*
		if(widgetName.equals(Mediator.BUTTON_UpperEdit)){
			// Action		
			//System.out.println("=>Btn Upper Edit");
			LMain.UpperRollEdit();
		}
		
		if(widgetName.equals(Mediator.BUTTON_UpperSave)){
			// Action
			//System.out.println("=>Btn Upper Save");
			LMain.UpperRollSave();
		}
		
		if(widgetName.equals(Mediator.BUTTON_LowerEdit)){
			// Action
			//System.out.println("=>Btn Lower Edit");
			LMain.LowerRollEdit();
		}
		
		if(widgetName.equals(Mediator.BUTTON_LowerSave)){
			// Action
			//System.out.println("=>Btn Lower Save");
			LMain.LowerRollSave();
		}
		*/
		if(widgetName.equals(Mediator.BUTTON_SaveRoll)){
			LMain.SaveRoll();
		}
		/*
		if(widgetName.equals(Mediator.BUTTON_Explorer)){
			System.out.println("=>Btn Explorer");
			LMain.Explorer();
		}
		*/
		if(widgetName.equals(Mediator.BUTTON_ExplorerYoungsModulus)){
			//System.out.println("=>Btn Explorer YoungsModulus");
			LMain.Explorer_YoungsModulus();
		}
		
		if(widgetName.equals(Mediator.BUTTON_ExplorerFlowStress)){
			//System.out.println("=>Btn Explorer FlowStress");
			LMain.Explorer_FlowStress();
		}
		
		if(widgetName.equals(Mediator.BUTTON_ExplorerThermalExpansionCoefficient)){
			//System.out.println("=>Btn Explorer ThermalExpansionCoefficient");
			LMain.Explorer_ThermalExpansionCoefficient();
		}
		
		if(widgetName.equals(Mediator.BUTTON_ExplorerPoissonsRatio)){
			//System.out.println("=>Btn Explorer PoissonsRatio");
			LMain.Explorer_PoissonsRatio();
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioConstant_YM)){
			//System.out.println("=>btn Radio Constant_YM");
			med.getBtnExplorerYoungsModulus().setEnabled(false);
			med.getTextYoungsModulus().setText(InitValueMap.get("YoungsModulus"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioTable_YM)){
			//System.out.println("=>btn Radio Table_YM");
			String matPath = myUtil.setPath(System.getProperty("user.dir"), "materialData");
			String YoungsModulus					= myUtil.setPath(matPath, "elastic_modulus.txt");
			
			med.getBtnExplorerYoungsModulus().setEnabled(true);
			med.getTextYoungsModulus().setText(YoungsModulus);
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioConstant_FS)){
			//System.out.println("=>btn Radio Constant_FS");
			//update version2 2016.01.27
			med.getTextFlowStress().setEnabled(false);
			med.getTextFlowStress().setText("");
			med.getTextYieldStrength().setEnabled(true);
			med.getTextYieldStrength().setText(InitValueMap.get("YieldStrength"));
			med.getTextTensileStrength().setEnabled(true);
			med.getTextTensileStrength().setText(InitValueMap.get("TensileStrength"));
			med.getTextElongation().setEnabled(true);
			med.getTextElongation().setText(InitValueMap.get("Elongation"));
			
			
			med.getBtnExplorerFlowStress().setEnabled(false);
			
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioTable_FS)){
			//System.out.println("=>btn Radio Table_FS");
			String matPath = myUtil.setPath(System.getProperty("user.dir"), "materialData");
			String FlowStress						= myUtil.setPath(matPath, "flow_stress.txt");
			
			//update version 2016.01.27
			med.getTextFlowStress().setEnabled(true);
			med.getTextFlowStress().setText(FlowStress);
			med.getBtnExplorerFlowStress().setEnabled(true);
			med.getTextYieldStrength().setEnabled(false);
			med.getTextYieldStrength().setText("");
			med.getTextTensileStrength().setEnabled(false);
			med.getTextTensileStrength().setText("");
			med.getTextElongation().setEnabled(false);
			med.getTextElongation().setText("");
			
			
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioConstant_TEC)){
			//System.out.println("=>btn Radio Constant_TEC");
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(false);
			med.getTextThermalExpansionCoefficient().setText(InitValueMap.get("ThermalExpansionCoefficient"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioTable_TEC)){
			//System.out.println("=>btn Radio Table_TEC");
			String matPath = myUtil.setPath(System.getProperty("user.dir"), "materialData");
			String ThermalExpansionCoefficient		= myUtil.setPath(matPath, "expansion.txt");
			
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(true);
			med.getTextThermalExpansionCoefficient().setText(ThermalExpansionCoefficient);
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioConstant_PR)){
			//System.out.println("=>btn Radio Constant_PR");
			med.getBtnExplorerPoissonsRatio().setEnabled(false);
			med.getTextPoissonsRatio().setText(InitValueMap.get("PoissonsRatio"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioTable_PR)){
			//System.out.println("=>btn Radio Table_PR");
			String matPath = myUtil.setPath(System.getProperty("user.dir"), "materialData");
			String PoissonsRatio					= myUtil.setPath(matPath, "poisson.txt");
			
			med.getBtnExplorerPoissonsRatio().setEnabled(true);
			med.getTextPoissonsRatio().setText(PoissonsRatio);
		}
		
		if(widgetName.equals(Mediator.BUTTON_ParallelDDMUse)){
			//System.out.println("=>Btn Parallel");
			if(med.getBtnParallelDDMUse().getSelection() == true){
				med.getSpinnerDomain().setEnabled(true);
			}else{
				med.getSpinnerDomain().setEnabled(false);
			}
		}
		
		if(widgetName.equals(Mediator.BUTTON_ParallelMultiThreadUse)){
			if(med.getBtnParallelMultiThreadUse().getSelection() == true){
				med.getSpinnerThread().setEnabled(true);
			}else{
				med.getSpinnerThread().setEnabled(false);
			}
		}
		
		if(widgetName.equals(Mediator.BUTTON_Apply)){
			/*
			ExportProcedureDlg exportProcedureDlg = new ExportProcedureDlg(Display.getCurrent().getActiveShell());
			exportProcedureDlg.open();
			 */
			//LMain.Export();
			ApplyConfirmDlg applyConfirmDlg = new ApplyConfirmDlg(Display.getCurrent().getActiveShell());
			applyConfirmDlg.open();
		}
		
	}
	public void setCustomWidget_btnCalcElementNum(){
		this.button = med.getBtnCalcElementNum();
	}
	public void setCustomWidget_btnNone(){
		this.button = med.getBtnNone();
	}
	public void setCustomWidget_btnUpper(){
		this.button = med.getBtnUpper();
	}
	public void setCustomWidget_btnLower(){
		this.button = med.getBtnLower();
	}
	public void setCustomWidget_btnShowRollTable(){
		this.button = med.getBtnShowRollTable();
	}
	
	public void setCustomWidget_btnCreateRoll(){
		this.button = med.getBtnCreateRoll();
	}
	public void setCustomWidget_btnUpperEdit(){
		this.button = med.getBtnUpperEdit();
	}
	
	public void setCustomWidget_btnUpperSave(){
		this.button = med.getBtnUpperSave();
	}
	
	public void setCustomWidget_btnLowerEdit(){
		this.button = med.getBtnLowerEdit();
	}
	
	public void setCustomWidget_btnLowerSave(){
		this.button = med.getBtnLowerSave();
	}
	
	public void setCustomWidget_btnSaveRoll(){
		this.button = med.getBtnSaveRoll();
	}
	
	public void setCustomWidget_btnRadioNone_RC(){
		this.button = med.getBtnRadioNone_RC();
	}
	
	public void setCustomWidget_btnRadioApply_RC(){
		this.button = med.getBtnRadioApply_RC();
	}
	
	public void setCustomWidget_btnRadioRigid_MS(){
		this.button = med.getBtnRadioRigid_MS();
	}
	
	public void setCustomWidget_btnRadioSpring_MS(){
		this.button = med.getBtnRadioSpring_MS();
	}
	
	/*
	public void setCustomWidget_btnExplorer(){
		this.button = med.getBtnExplorer();
	}
	*/
	public void setCustomWidget_btnExplorerYoungsModulus(){
		this.button = med.getBtnExplorerYoungsModulus();
	}
	
	public void setCustomWidget_btnExplorerFlowStress(){
		this.button = med.getBtnExplorerFlowStress();
	}
	
	public void setCustomWidget_btnExplorerThermalExpansionCoefficient(){
		this.button = med.getBtnExplorerThermalExpansionCoefficient();
	}
	
	public void setCustomWidget_btnExplorerPoissonsRatio(){
		this.button = med.getBtnExplorerPoissonsRatio();
	}
	
	public void setCustomWidget_btnRadioConstant_YM(){
		this.button = med.getBtnRadioConstant_YM();
	}
	
	public void setCustomWidget_btnRadioTable_YM(){
		this.button = med.getBtnRadioTable_YM();
	}
	
	public void setCustomWidget_btnRadioConstant_FS(){
		this.button = med.getBtnRadioConstant_FS();
	}
	
	public void setCustomWidget_btnRadioTable_FS(){
		this.button = med.getBtnRadioTable_FS();
	}
	
	public void setCustomWidget_btnRadioConstant_TEC(){
		this.button = med.getBtnRadioConstant_TEC();
	}
	
	public void setCustomWidget_btnRadioTable_TEC(){
		this.button = med.getBtnRadioTable_TEC();
	}
	
	public void setCustomWidget_btnRadioConstant_PR(){
		this.button = med.getBtnRadioConstant_PR();
	}
	
	public void setCustomWidget_btnRadioTable_PR(){
		this.button = med.getBtnRadioTable_PR();
	}
	
	public void setCustomWidget_btnParallelDDMUse(){
		this.button = med.getBtnParallelDDMUse();
	}
	
	public void setCustomWidget_btnParallelMultiThreadUse(){
		this.button = med.getBtnParallelMultiThreadUse();
	}
	
	public void setCustomWidget_btnApply(){
		this.button = med.getBtnApply();
	}
	
	
	


}
