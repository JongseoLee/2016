package com.js.ens.leveller.customWidget;

import java.util.Map;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.InitValue;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.core.Mediator;
import com.js.ens.leveller.dialog.ApplyConfirmDlg;
import com.js.ens.leveller.dialog.ApplyConfirm_2DDlg;
import com.js.ens.leveller.dialog.ExportProcedureDlg;
import com.js.ens.leveller.dialog.ShowRollTableDlg;
import com.js.ens.leveller.dialog.ShowRollTable_2DDlg;
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
			//System.out.println("selected "+ med.getBtnNone().getText() +" : "+med.getBtnNone().getSelection());
			if(med.getBtnNone().getSelection()){
				med.getTextFrontHDRollDia().setEnabled(false);
				med.getTextFrontHDRollPitch().setEnabled(false);
				med.getTextFrontHDRollVericalPos().setEnabled(false);
				med.getTextRearHDRollDia().setEnabled(false);
				med.getTextRearHDRollPitch().setEnabled(false);
				med.getTextRearHDRollVerticalPos().setEnabled(false);
			}else {
				med.getTextFrontHDRollDia().setEnabled(true);
				med.getTextFrontHDRollPitch().setEnabled(true);
				med.getTextFrontHDRollVericalPos().setEnabled(true);
				med.getTextRearHDRollDia().setEnabled(true);
				med.getTextRearHDRollPitch().setEnabled(true);
				med.getTextRearHDRollVerticalPos().setEnabled(true);
			}
		}
		
		if(widgetName.equals(Mediator.BUTTON_btnUpper)){
			//System.out.println("selected "+ med.getBtnUpper().getText() +" : "+med.getBtnUpper().getSelection());
			if(med.getBtnUpper().getSelection()){
				med.getTextFrontHDRollDia().setEnabled(true);
				med.getTextFrontHDRollPitch().setEnabled(true);
				med.getTextFrontHDRollVericalPos().setEnabled(true);
				med.getTextRearHDRollDia().setEnabled(true);
				med.getTextRearHDRollPitch().setEnabled(true);
				med.getTextRearHDRollVerticalPos().setEnabled(true);
			}
		}
		
		if(widgetName.equals(Mediator.BUTTON_btnLower)){
			//System.out.println("selected "+ med.getBtnLower().getText() +" : "+med.getBtnLower().getSelection());
			if(med.getBtnLower().getSelection()){
				med.getTextFrontHDRollDia().setEnabled(true);
				med.getTextFrontHDRollPitch().setEnabled(true);
				med.getTextFrontHDRollVericalPos().setEnabled(true);
				med.getTextRearHDRollDia().setEnabled(true);
				med.getTextRearHDRollPitch().setEnabled(true);
				med.getTextRearHDRollVerticalPos().setEnabled(true);
			}
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
		
		if(widgetName.equals(Mediator.BUTTON_SaveRoll)){
			LMain.SaveRoll();
		}
	
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
			//LMain.Export();
			ApplyConfirmDlg applyConfirmDlg = new ApplyConfirmDlg(Display.getCurrent().getActiveShell());
			applyConfirmDlg.open();
		}
		
		
		
		
		
		
		
		
		
		
		
		if(widgetName.equals(Mediator.BUTTON_CalcElementNum_2D)){
			LMain.CalcElementNumber_2D();
		}
		
		if(widgetName.equals(Mediator.BUTTON_btnNone_2D)){
			//System.out.println("selected "+ med.getBtnNone().getText() +" : "+med.getBtnNone().getSelection());
			if(med.getBtnNone_2D().getSelection()){
				med.getTextFrontHDRollDia_2D().setEnabled(false);
				med.getTextFrontHDRollPitch_2D().setEnabled(false);
				med.getTextFrontHDRollVericalPos_2D().setEnabled(false);
				med.getTextRearHDRollDia_2D().setEnabled(false);
				med.getTextRearHDRollPitch_2D().setEnabled(false);
				med.getTextRearHDRollVerticalPos_2D().setEnabled(false);
			}else {
				med.getTextFrontHDRollDia_2D().setEnabled(true);
				med.getTextFrontHDRollPitch_2D().setEnabled(true);
				med.getTextFrontHDRollVericalPos_2D().setEnabled(true);
				med.getTextRearHDRollDia_2D().setEnabled(true);
				med.getTextRearHDRollPitch_2D().setEnabled(true);
				med.getTextRearHDRollVerticalPos_2D().setEnabled(true);
			}
		}
		
		if(widgetName.equals(Mediator.BUTTON_btnUpper_2D)){
			//System.out.println("selected "+ med.getBtnUpper().getText() +" : "+med.getBtnUpper().getSelection());
			if(med.getBtnUpper_2D().getSelection()){
				med.getTextFrontHDRollDia_2D().setEnabled(true);
				med.getTextFrontHDRollPitch_2D().setEnabled(true);
				med.getTextFrontHDRollVericalPos_2D().setEnabled(true);
				med.getTextRearHDRollDia_2D().setEnabled(true);
				med.getTextRearHDRollPitch_2D().setEnabled(true);
				med.getTextRearHDRollVerticalPos_2D().setEnabled(true);
			}
		}
		
		if(widgetName.equals(Mediator.BUTTON_btnLower_2D)){
			//System.out.println("selected "+ med.getBtnLower().getText() +" : "+med.getBtnLower().getSelection());
			if(med.getBtnLower_2D().getSelection()){
				med.getTextFrontHDRollDia_2D().setEnabled(true);
				med.getTextFrontHDRollPitch_2D().setEnabled(true);
				med.getTextFrontHDRollVericalPos_2D().setEnabled(true);
				med.getTextRearHDRollDia_2D().setEnabled(true);
				med.getTextRearHDRollPitch_2D().setEnabled(true);
				med.getTextRearHDRollVerticalPos_2D().setEnabled(true);
			}
		}
		
		if(widgetName.equals(Mediator.BUTTON_btnShowRollTable_2D)){
			ShowRollTable_2DDlg showRollTable_2DDlg = new ShowRollTable_2DDlg(Display.getCurrent().getActiveShell());
			showRollTable_2DDlg.open();
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioNone_RC_2D)){
			med.getTextRollCrown_2D().setEnabled(false);
			med.getTextRollCrown_2D().setText(InitValueMap.get("RollCrown_2D"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioApply_RC_2D)){
			med.getTextRollCrown_2D().setEnabled(true);
			med.getTextRollCrown_2D().setText(InitValueMap.get("RollCrown_2D"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioRigid_MS)){
			med.getTextMillStiffness_2D().setEnabled(false);
			med.getTextMillStiffness_2D().setText(InitValueMap.get("MillStiffness_2D"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioSpring_MS)){
			med.getTextMillStiffness_2D().setEnabled(true);
			med.getTextMillStiffness_2D().setText(InitValueMap.get("MillStiffness_2D"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_CreateRoll_2D)){
			// Action
			LMain.CreateRoll_2D();
		}
		
		if(widgetName.equals(Mediator.BUTTON_SaveRoll_2D)){
			LMain.SaveRoll_2D();
		}
	
		if(widgetName.equals(Mediator.BUTTON_ExplorerYoungsModulus_2D)){
			LMain.Explorer_YoungsModulus_2D();
		}
		
		if(widgetName.equals(Mediator.BUTTON_ExplorerFlowStress_2D)){
			LMain.Explorer_FlowStress_2D();
		}
		
		if(widgetName.equals(Mediator.BUTTON_ExplorerThermalExpansionCoefficient_2D)){
			LMain.Explorer_ThermalExpansionCoefficient_2D();
		}
		
		if(widgetName.equals(Mediator.BUTTON_ExplorerPoissonsRatio_2D)){
			LMain.Explorer_PoissonsRatio_2D();
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioConstant_YM_2D)){
			//System.out.println("=>btn Radio Constant_YM");
			med.getBtnExplorerYoungsModulus_2D().setEnabled(false);
			med.getTextYoungsModulus_2D().setText(InitValueMap.get("YoungsModulus_2D"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioTable_YM_2D)){
			//System.out.println("=>btn Radio Table_YM");
			String matPath = myUtil.setPath(System.getProperty("user.dir"), "materialData");
			String YoungsModulus					= myUtil.setPath(matPath, "elastic_modulus.txt");
			
			med.getBtnExplorerYoungsModulus_2D().setEnabled(true);
			med.getTextYoungsModulus_2D().setText(YoungsModulus);
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioConstant_FS_2D)){
			//System.out.println("=>btn Radio Constant_FS");
			//update version2 2016.01.27
			med.getTextFlowStress_2D().setEnabled(false);
			med.getTextFlowStress_2D().setText("");
			med.getTextYieldStrength_2D().setEnabled(true);
			med.getTextYieldStrength_2D().setText(InitValueMap.get("YieldStrength_2D"));
			med.getTextTensileStrength_2D().setEnabled(true);
			med.getTextTensileStrength_2D().setText(InitValueMap.get("TensileStrength_2D"));
			med.getTextElongation_2D().setEnabled(true);
			med.getTextElongation_2D().setText(InitValueMap.get("Elongation_2D"));
			
			
			med.getBtnExplorerFlowStress_2D().setEnabled(false);
			
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioTable_FS_2D)){
			//System.out.println("=>btn Radio Table_FS");
			String matPath = myUtil.setPath(System.getProperty("user.dir"), "materialData");
			String FlowStress						= myUtil.setPath(matPath, "flow_stress.txt");
			
			//update version 2016.01.27
			med.getTextFlowStress_2D().setEnabled(true);
			med.getTextFlowStress_2D().setText(FlowStress);
			med.getBtnExplorerFlowStress_2D().setEnabled(true);
			med.getTextYieldStrength_2D().setEnabled(false);
			med.getTextYieldStrength_2D().setText("");
			med.getTextTensileStrength_2D().setEnabled(false);
			med.getTextTensileStrength_2D().setText("");
			med.getTextElongation_2D().setEnabled(false);
			med.getTextElongation_2D().setText("");
			
			
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioConstant_TEC_2D)){
			//System.out.println("=>btn Radio Constant_TEC");
			med.getBtnExplorerThermalExpansionCoefficient_2D().setEnabled(false);
			med.getTextThermalExpansionCoefficient_2D().setText(InitValueMap.get("ThermalExpansionCoefficient_2D"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioTable_TEC_2D)){
			//System.out.println("=>btn Radio Table_TEC");
			String matPath = myUtil.setPath(System.getProperty("user.dir"), "materialData");
			String ThermalExpansionCoefficient		= myUtil.setPath(matPath, "expansion.txt");
			
			med.getBtnExplorerThermalExpansionCoefficient_2D().setEnabled(true);
			med.getTextThermalExpansionCoefficient_2D().setText(ThermalExpansionCoefficient);
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioConstant_PR_2D)){
			//System.out.println("=>btn Radio Constant_PR");
			med.getBtnExplorerPoissonsRatio_2D().setEnabled(false);
			med.getTextPoissonsRatio_2D().setText(InitValueMap.get("PoissonsRatio_2D"));
		}
		
		if(widgetName.equals(Mediator.BUTTON_RadioTable_PR_2D)){
			//System.out.println("=>btn Radio Table_PR");
			String matPath = myUtil.setPath(System.getProperty("user.dir"), "materialData");
			String PoissonsRatio					= myUtil.setPath(matPath, "poisson.txt");
			
			med.getBtnExplorerPoissonsRatio_2D().setEnabled(true);
			med.getTextPoissonsRatio_2D().setText(PoissonsRatio);
		}
		
		if(widgetName.equals(Mediator.BUTTON_ParallelDDMUse_2D)){
			//System.out.println("=>Btn Parallel");
			if(med.getBtnParallelDDMUse_2D().getSelection() == true){
				med.getSpinnerDomain_2D().setEnabled(true);
			}else{
				med.getSpinnerDomain_2D().setEnabled(false);
			}
		}
		
		if(widgetName.equals(Mediator.BUTTON_ParallelMultiThreadUse_2D)){
			if(med.getBtnParallelMultiThreadUse_2D().getSelection() == true){
				med.getSpinnerThread_2D().setEnabled(true);
			}else{
				med.getSpinnerThread_2D().setEnabled(false);
			}
		}
		
		if(widgetName.equals(Mediator.BUTTON_Apply_2D)){
			ApplyConfirm_2DDlg applyConfirm_2DDlg = new ApplyConfirm_2DDlg(Display.getCurrent().getActiveShell());
			applyConfirm_2DDlg.open();
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setCustomWidget_btnCalcElementNum_2D(){
		this.button = med.getBtnCalcElementNum_2D();
	}
	public void setCustomWidget_btnNone_2D(){
		this.button = med.getBtnNone_2D();
	}
	public void setCustomWidget_btnUpper_2D(){
		this.button = med.getBtnUpper_2D();
	}
	public void setCustomWidget_btnLower_2D(){
		this.button = med.getBtnLower_2D();
	}
	public void setCustomWidget_btnShowRollTable_2D(){
		this.button = med.getBtnShowRollTable_2D();
	}
	
	public void setCustomWidget_btnCreateRoll_2D(){
		this.button = med.getBtnCreateRoll_2D();
	}
	public void setCustomWidget_btnUpperEdit_2D(){
		this.button = med.getBtnUpperEdit_2D();
	}
	
	public void setCustomWidget_btnUpperSave_2D(){
		this.button = med.getBtnUpperSave_2D();
	}
	
	public void setCustomWidget_btnLowerEdit_2D(){
		this.button = med.getBtnLowerEdit_2D();
	}
	
	public void setCustomWidget_btnLowerSave_2D(){
		this.button = med.getBtnLowerSave_2D();
	}
	
	public void setCustomWidget_btnSaveRoll_2D(){
		this.button = med.getBtnSaveRoll_2D();
	}
	
	public void setCustomWidget_btnRadioNone_RC_2D(){
		this.button = med.getBtnRadioNone_RC_2D();
	}
	
	public void setCustomWidget_btnRadioApply_RC_2D(){
		this.button = med.getBtnRadioApply_RC_2D();
	}
	
	public void setCustomWidget_btnRadioRigid_MS_2D(){
		this.button = med.getBtnRadioRigid_MS_2D();
	}
	
	public void setCustomWidget_btnRadioSpring_MS_2D(){
		this.button = med.getBtnRadioSpring_MS_2D();
	}
	
	public void setCustomWidget_btnExplorerYoungsModulus_2D(){
		this.button = med.getBtnExplorerYoungsModulus_2D();
	}
	
	public void setCustomWidget_btnExplorerFlowStress_2D(){
		this.button = med.getBtnExplorerFlowStress_2D();
	}
	
	public void setCustomWidget_btnExplorerThermalExpansionCoefficient_2D(){
		this.button = med.getBtnExplorerThermalExpansionCoefficient_2D();
	}
	
	public void setCustomWidget_btnExplorerPoissonsRatio_2D(){
		this.button = med.getBtnExplorerPoissonsRatio_2D();
	}
	
	public void setCustomWidget_btnRadioConstant_YM_2D(){
		this.button = med.getBtnRadioConstant_YM_2D();
	}
	
	public void setCustomWidget_btnRadioTable_YM_2D(){
		this.button = med.getBtnRadioTable_YM_2D();
	}
	
	public void setCustomWidget_btnRadioConstant_FS_2D(){
		this.button = med.getBtnRadioConstant_FS_2D();
	}
	
	public void setCustomWidget_btnRadioTable_FS_2D(){
		this.button = med.getBtnRadioTable_FS_2D();
	}
	
	public void setCustomWidget_btnRadioConstant_TEC_2D(){
		this.button = med.getBtnRadioConstant_TEC_2D();
	}
	
	public void setCustomWidget_btnRadioTable_TEC_2D(){
		this.button = med.getBtnRadioTable_TEC_2D();
	}
	
	public void setCustomWidget_btnRadioConstant_PR_2D(){
		this.button = med.getBtnRadioConstant_PR_2D();
	}
	
	public void setCustomWidget_btnRadioTable_PR_2D(){
		this.button = med.getBtnRadioTable_PR_2D();
	}
	
	public void setCustomWidget_btnParallelDDMUse_2D(){
		this.button = med.getBtnParallelDDMUse_2D();
	}
	
	public void setCustomWidget_btnParallelMultiThreadUse_2D(){
		this.button = med.getBtnParallelMultiThreadUse_2D();
	}
	
	public void setCustomWidget_btnApply_2D(){
		this.button = med.getBtnApply_2D();
	}
	
	


}
