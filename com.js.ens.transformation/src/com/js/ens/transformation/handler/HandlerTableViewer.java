package com.js.ens.transformation.handler;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;

public class HandlerTableViewer implements ISelectionChangedListener {
	private TableViewer tableViewer;
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		try{
			tableViewer = (TableViewer) event.getSource();
		}catch(Exception e){
			
		}
	}

}
