package com.js.ens.transformation;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;

public class View extends ViewPart {
	public View() {
	}
	public static final String ID = "com.js.ens.transformation.view";

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());
		
		TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.top = new FormAttachment(0,50);
		fd_tabFolder.left = new FormAttachment(0,0);
		fd_tabFolder.right = new FormAttachment(100,0);
		fd_tabFolder.bottom = new FormAttachment(100,-50);
		tabFolder.setLayoutData(fd_tabFolder);
		
		TabItem tbtm_Total = new TabItem(tabFolder, SWT.NONE);
		tbtm_Total.setText("P Log");
		
		Composite composite_Total = new Composite(tabFolder, SWT.NONE);
		tbtm_Total.setControl(composite_Total);
		composite_Total.setLayout(new FormLayout());
		
		
		
		//----------------------------------------------------------------------
		//----------------------------------------------------------------------
		//----------------------------------------------------------------------
		TabItem tbtm_Detail = new TabItem(tabFolder, SWT.NONE);
		tbtm_Detail.setText("F1~F7");
		
		Composite composite_Detail = new Composite(tabFolder, SWT.NONE);
		tbtm_Detail.setControl(composite_Detail);
		composite_Detail.setLayout(new FormLayout());
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}