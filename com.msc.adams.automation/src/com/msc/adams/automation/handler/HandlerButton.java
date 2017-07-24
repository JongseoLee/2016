package com.msc.adams.automation.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.msc.adams.automation.core.Mediator;

public class HandlerButton implements Listener {
	private Mediator med = Mediator.getInstance();
	
	public HandlerButton() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getBtnPrevious()){
			med.getC_btnPrevious().execute();
		}else if(event.widget == med.getBtnNext()){
			med.getC_btnNext().execute();
		}else if(event.widget == med.getBtnEditAllData()){
			med.getC_btnEditAllData().execute();
		}else if(event.widget == med.getBtnSaveAllData()){
			med.getC_btnSaveAllData().execute();
		}else if(event.widget == med.getBtnReloadDb()){
			med.getC_btnReloadDb().execute();
		}else if(event.widget == med.getBtnNone()){
			med.getC_btnNone().execute();
		}else if(event.widget == med.getBtnAdd()){
			med.getC_btnAdd().execute();
		}else if(event.widget == med.getBtnDel()){
			med.getC_btnDel().execute();
		}else if(event.widget == med.getBtnExplorerStep3()){
			med.getC_btnExplorerStep3().execute();
		}else if(event.widget == med.getBtnStartSolving()){
			med.getC_btnStartSolving().execute();
		}/*else if(event.widget == med.getBtnStopSolving()){
			med.getC_btnStopSolving().execute();
		}// */
		else if(event.widget == med.getBtnStartAnimation()){
			med.getC_btnStartAnimation().execute();
		}else if(event.widget == med.getBtnGSTIFF()){
			med.getC_btnGSTIFF().execute();
		}else if(event.widget == med.getBtnWSTIFF()){
			med.getC_btnWSTIFF().execute();
		}else if(event.widget == med.getBtnI3()){
			med.getC_btnI3().execute();
		}else if(event.widget == med.getBtnSI2()){
			med.getC_btnSI2().execute();
		}else if(event.widget == med.getBtnOriginal()){
			med.getC_btnOriginal().execute();
		}else if(event.widget == med.getBtnExtraMassOn()){
			med.getC_btnExtraMassOn().execute();
		}else if(event.widget == med.getBtnExtraMassOff()){
			med.getC_btnExtraMassOff().execute();
		}else if(event.widget == med.getBtnModified()){
			med.getC_btnModified().execute();
		}else if(event.widget == med.getBtnModelData()){
			med.getC_btnModelData().execute();
		}else if(event.widget == med.getBtnDACFile()){
			med.getC_btnDACFile().execute();
		}else if(event.widget == med.getBtnModelDataBin()){
			med.getC_btnModelDataBin().execute();
		}else if(event.widget == med.getBtnForceFile()){
			med.getC_btnForceFile().execute();
		}else if(event.widget == med.getBtnExportResult()){
			med.getC_btnExportResult().execute();
		}else if(event.widget == med.getBtnSolvingAndCreate()){
			med.getC_btnSolvingAndCreate().execute();
		}
	}

}
