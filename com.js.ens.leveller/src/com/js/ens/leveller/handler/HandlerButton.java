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
		/*
		else if(event.widget == med.getBtnUpperEdit()){
			med.getCustomBtnUpperEdit().execute();
		}else if(event.widget == med.getBtnUpperSave()){
			med.getCustomBtnUpperSave().execute();
		}else if(event.widget == med.getBtnLowerEdit()){
			med.getCustomBtnLowerEdit().execute();
		}else if(event.widget == med.getBtnLowerSave()){
			med.getCustomBtnLowerSave().execute();
		}
		*/
		else if(event.widget == med.getBtnSaveRoll()){
			med.getcustomBtnSaveRoll().execute();
		}
		/*
		else if(event.widget == med.getBtnExplorer()){
			med.getCustomBtnExplorer().execute();
		}
		*/
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
		
	}

}
