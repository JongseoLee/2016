package com.js.ens.leveller.handler;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;

import com.js.ens.leveller.core.Mediator;


public class HandlerTableViewer implements ISelectionChangedListener {
	private Mediator med = Mediator.getInstance();
	private TableViewer tableViewer;

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		
		try{
			tableViewer = (TableViewer) event.getSource();
			
			if(tableViewer == med.getTableViewerUpperRoll()){
				med.getCustomTableViewerUpperRoll().execute();
				Table table= tableViewer.getTable();
				//System.out.println("Select index : "+table.getSelectionIndex());
			}else if(tableViewer == med.getTableViewerLowerRoll()){
				med.getCustomTableViewerLowerRoll().execute();
				Table table= tableViewer.getTable();
				//System.out.println("Select index : "+table.getSelectionIndex());
			}
		}catch(Exception e){
			
		}
		
	}

}
