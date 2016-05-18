package com.js.ens.leveller.core;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableViewerLabelProvider_up extends LabelProvider implements ITableLabelProvider {
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		UpTableDataContent UTDCObj = (UpTableDataContent) element;
		
		switch(columnIndex){
		case 0:
			return UTDCObj.getNo();
		case 1:
			return UTDCObj.getGap();
		case 2:
			return UTDCObj.getFriction();
		case 3:
			return UTDCObj.getDiameter();
		}
		
		return "";
	}

}
