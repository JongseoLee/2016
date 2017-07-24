package com.msc.adams.automation.provider.content;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.msc.adams.automation.datas.factory.WindshieldFactory;

public class WindshieldDataContentProvider implements IStructuredContentProvider {

	public WindshieldDataContentProvider() {
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
		return ((WindshieldFactory) inputElement).getList().toArray();
	}

}
