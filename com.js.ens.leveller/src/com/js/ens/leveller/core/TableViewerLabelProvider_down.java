package com.js.ens.leveller.core;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableViewerLabelProvider_down extends LabelProvider implements ITableLabelProvider {
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		DownTableDataContent DTDCObj = (DownTableDataContent) element;
		
		switch(columnIndex){
		case 0:
			return DTDCObj.getNo();
		case 1:
			return DTDCObj.getGap();
		case 2:
			return DTDCObj.getFriction();
		case 3:
			return DTDCObj.getDiameter();
		}
		
		return "";
	}

}
