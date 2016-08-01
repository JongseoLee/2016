package com.js.ens.leveller.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.js.ens.leveller.core.Mediator;



public class HandlerButton implements Listener {
	private Mediator med = Mediator.getInstance();
	
	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getBtnCalcElementNum()){	
			med.getCustomBtnCalcElementNum().execute();
		}else if(event.widget == med.getBtnNone()){
			med.getCustomBtnNone().execute();
		}else if(event.widget == med.getBtnUpper()){
			med.getCustomBtnUpper().execute();
		}else if(event.widget == med.getBtnLower()){
			med.getCustomBtnLower().execute();
		}else if(event.widget == med.getBtnShowRollTable()){
			med.getCustomBtnShowRollTable().execute();
		}else if(event.widget == med.getBtnRadioNone_RC()){
			med.getCustomBtnRadioNone_RC().execute();
		}else if(event.widget == med.getBtnRadioApply_RC()){
			med.getCustomBtnRadioApply_RC().execute();
		}else if(event.widget == med.getBtnRadioRigid_MS()){
			med.getCustomBtnRadioRigid_MS().execute();
		}else if(event.widget == med.getBtnRadioSpring_MS()){
			med.getCustomBtnRadioSpring_MS().execute();
		}else if(event.widget == med.getBtnCreateRoll()){
			med.getCustomBtnCreateRoll().execute();
		}
		else if(event.widget == med.getBtnSaveRoll()){
			med.getcustomBtnSaveRoll().execute();
		}
		else if(event.widget == med.getBtnExplorerYoungsModulus()){
			med.getCustomBtnExplorerYoungsModulus().execute();
		}else if(event.widget == med.getBtnExplorerFlowStress()){
			med.getCustomBtnExplorerFlowStress().execute();
		}else if(event.widget == med.getBtnExplorerThermalExpansionCoefficient()){
			med.getCustomBtnExplorerThermalExpansionCoefficient().execute();
		}else if(event.widget == med.getBtnExplorerPoissonsRatio()){
			med.getCustomBtnExplorerPoissonsRatio().execute();
		}else if(event.widget == med.getBtnRadioConstant_YM()){
			med.getCustomBtnRadioConstant_YM().execute();
		}else if(event.widget == med.getBtnRadioTable_YM()){
			med.getCustomBtnRadioTable_YM().execute();
		}else if(event.widget == med.getBtnRadioConstant_FS()){
			med.getCustomBtnRadioConstant_FS().execute();
		}else if(event.widget == med.getBtnRadioTable_FS()){
			med.getCustomBtnRadioTable_FS().execute();
		}else if(event.widget == med.getBtnRadioConstant_TEC()){
			med.getCustomBtnRadioConstant_TEC().execute();
		}else if(event.widget == med.getBtnRadioTable_TEC()){
			med.getCustomBtnRadioTable_TEC().execute();
		}else if(event.widget == med.getBtnRadioConstant_PR()){
			med.getCustomBtnRadioConstant_PR().execute();
		}else if(event.widget == med.getBtnRadioTable_PR()){
			med.getCustomBtnRadioTable_PR().execute();
		}else if(event.widget == med.getBtnParallelDDMUse()){
			med.getCustomBtnParallelDDMUse().execute();
		}else if(event.widget == med.getBtnParallelMultiThreadUse()){
			med.getCustomBtnParallelMultiThreadUse().execute();
		}else if(event.widget == med.getBtnApply()){
			med.getCustomBtnApply().execute();
		}
		
		
		//2D
		
		if(event.widget == med.getBtnCalcElementNum_2D()){	
			med.getCustomBtnCalcElementNum_2D().execute();
		}else if(event.widget == med.getBtnNone_2D()){
			med.getCustomBtnNone_2D().execute();
		}else if(event.widget == med.getBtnUpper_2D()){
			med.getCustomBtnUpper_2D().execute();
		}else if(event.widget == med.getBtnLower_2D()){
			med.getCustomBtnLower_2D().execute();
		}else if(event.widget == med.getBtnShowRollTable_2D()){
			med.getCustomBtnShowRollTable_2D().execute();
		}else if(event.widget == med.getBtnRadioNone_RC_2D()){
			med.getCustomBtnRadioNone_RC_2D().execute();
		}else if(event.widget == med.getBtnRadioApply_RC_2D()){
			med.getCustomBtnRadioApply_RC_2D().execute();
		}else if(event.widget == med.getBtnRadioRigid_MS_2D()){
			med.getCustomBtnRadioRigid_MS_2D().execute();
		}else if(event.widget == med.getBtnRadioSpring_MS_2D()){
			med.getCustomBtnRadioSpring_MS_2D().execute();
		}else if(event.widget == med.getBtnCreateRoll_2D()){
			med.getCustomBtnCreateRoll_2D().execute();
		}
		else if(event.widget == med.getBtnSaveRoll_2D()){
			med.getCustomBtnSaveRoll_2D().execute();
		}
		else if(event.widget == med.getBtnExplorerYoungsModulus_2D()){
			med.getCustomBtnExplorerYoungsModulus_2D().execute();
		}else if(event.widget == med.getBtnExplorerFlowStress_2D()){
			med.getCustomBtnExplorerFlowStress_2D().execute();
		}else if(event.widget == med.getBtnExplorerThermalExpansionCoefficient_2D()){
			med.getCustomBtnExplorerThermalExpansionCoefficient_2D().execute();
		}else if(event.widget == med.getBtnExplorerPoissonsRatio_2D()){
			med.getCustomBtnExplorerPoissonsRatio_2D().execute();
		}else if(event.widget == med.getBtnRadioConstant_YM_2D()){
			med.getCustomBtnRadioConstant_YM_2D().execute();
		}else if(event.widget == med.getBtnRadioTable_YM_2D()){
			med.getCustomBtnRadioTable_YM_2D().execute();
		}else if(event.widget == med.getBtnRadioConstant_FS_2D()){
			med.getCustomBtnRadioConstant_FS_2D().execute();
		}else if(event.widget == med.getBtnRadioTable_FS_2D()){
			med.getCustomBtnRadioTable_FS_2D().execute();
		}else if(event.widget == med.getBtnRadioConstant_TEC_2D()){
			med.getCustomBtnRadioConstant_TEC_2D().execute();
		}else if(event.widget == med.getBtnRadioTable_TEC_2D()){
			med.getCustomBtnRadioTable_TEC_2D().execute();
		}else if(event.widget == med.getBtnRadioConstant_PR_2D()){
			med.getCustomBtnRadioConstant_PR_2D().execute();
		}else if(event.widget == med.getBtnRadioTable_PR_2D()){
			med.getCustomBtnRadioTable_PR_2D().execute();
		}else if(event.widget == med.getBtnParallelDDMUse_2D()){
			med.getCustomBtnParallelDDMUse_2D().execute();
		}else if(event.widget == med.getBtnParallelMultiThreadUse_2D()){
			med.getCustomBtnParallelMultiThreadUse_2D().execute();
		}else if(event.widget == med.getBtnApply_2D()){
			med.getCustomBtnApply_2D().execute();
		}
		
		
	}

}
