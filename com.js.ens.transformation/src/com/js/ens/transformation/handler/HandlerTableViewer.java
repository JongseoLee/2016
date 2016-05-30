package com.js.ens.transformation.handler;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;

import com.js.ens.transformation.core.Mediator;

public class HandlerTableViewer implements ISelectionChangedListener {
	private Mediator med = Mediator.getInstance();
	private TableViewer tableViewer;
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		try{
			tableViewer = (TableViewer) event.getSource();
			
			if(tableViewer == med.getTableViewerSlabPlateInfo()){
				med.getC_tableViewerSlabPlateInfo().execute();
			}else if(tableViewer == med.getTableViewerVariable()){
				med.getC_tableViewerVariable().execute();
			}else if(tableViewer == med.getTableViewerPLog()){
				med.getC_tableViewerPLog().execute();
			}
		}catch(Exception e){
			
		}
	}

}
