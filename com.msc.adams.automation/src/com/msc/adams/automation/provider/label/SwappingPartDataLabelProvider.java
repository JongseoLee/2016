package com.msc.adams.automation.provider.label;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import com.msc.adams.automation.datas.SwappingPart;

public class SwappingPartDataLabelProvider implements ILabelProvider {

	public SwappingPartDataLabelProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		// Update
		//System.out.println("Label : " +((PartData) element).getPartName());
		String label = "["+((SwappingPart) element).getType()+"]"+((SwappingPart) element).getPartName();
		return label;
	}

}
