package com.msc.adams.automation.provider.content;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.msc.adams.automation.datas.factory.PartFactory;
import com.msc.adams.automation.datas.factory.SwappingPartFactory;

public class SwappingPartDataContentProvider implements IStructuredContentProvider {

	public SwappingPartDataContentProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		
		return ((SwappingPartFactory) inputElement).getList().toArray();
	}

}
