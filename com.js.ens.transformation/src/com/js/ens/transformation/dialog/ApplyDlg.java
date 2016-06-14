package com.js.ens.transformation.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ApplyDlg extends Dialog {
	private boolean isCheckedAll = true;
	private boolean isCheckedIndividual = false;
	private boolean isCheckedF1 = false;
	private boolean isCheckedF2 = false;
	private boolean isCheckedF3 = false;
	private boolean isCheckedF4 = false;
	private boolean isCheckedF5 = false;
	private boolean isCheckedF6 = false;
	private boolean isCheckedF7 = false;
	
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ApplyDlg(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FormLayout());
		
		Label lblApply = new Label(container, SWT.NONE);
		lblApply.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblApply = new FormData();
		fd_lblApply.top = new FormAttachment(0, 10);
		fd_lblApply.left = new FormAttachment(0, 10);
		lblApply.setLayoutData(fd_lblApply);
		lblApply.setText("Apply");
		
		Label lblComment = new Label(container, SWT.NONE);
		FormData fd_lblComment = new FormData();
		fd_lblComment.top = new FormAttachment(lblApply, 15);
		fd_lblComment.left = new FormAttachment(lblApply, 0, SWT.LEFT);
		lblComment.setLayoutData(fd_lblComment);
		lblComment.setText("Please select the type.");
		
		final Button btnAll = new Button(container, SWT.RADIO);
		FormData fd_btnAll = new FormData();
		fd_btnAll.top = new FormAttachment(lblComment, 10);
		fd_btnAll.left = new FormAttachment(lblComment, 10, SWT.LEFT);
		btnAll.setLayoutData(fd_btnAll);
		btnAll.setText("All selected");
		btnAll.setSelection(true);
		
		final Button btnIndividual = new Button(container, SWT.RADIO);
		FormData fd_btnIndividual = new FormData();
		fd_btnIndividual.top = new FormAttachment(btnAll, 10);
		fd_btnIndividual.left = new FormAttachment(btnAll, 0, SWT.LEFT);
		btnIndividual.setLayoutData(fd_btnIndividual);
		btnIndividual.setText("Individually selected");
		btnAll.setSelection(false);
		
		Composite composite = new Composite(container, SWT.BORDER);
		composite.setLayout(new FormLayout());
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(btnIndividual, 5);
		fd_composite.left = new FormAttachment(btnIndividual, 10,SWT.LEFT);
		fd_composite.right = new FormAttachment(100, -20);
		fd_composite.bottom = new FormAttachment(100, -10);		
		composite.setLayoutData(fd_composite);
		
		final Button btnF1 = new Button(composite, SWT.CHECK);
		FormData fd_btnF1 = new FormData();
		fd_btnF1.top = new FormAttachment(0, 10);
		fd_btnF1.left = new FormAttachment(0, 10);
		btnF1.setLayoutData(fd_btnF1);
		btnF1.setText("F1");
		btnF1.setSelection(false);
		
		final Button btnF2 = new Button(composite, SWT.CHECK);
		FormData fd_btnF2 = new FormData();
		fd_btnF2.top = new FormAttachment(btnF1, 0, SWT.TOP);
		fd_btnF2.left = new FormAttachment(btnF1, 10);
		btnF2.setLayoutData(fd_btnF2);
		btnF2.setText("F2");
		btnF2.setSelection(false);

		final Button btnF3 = new Button(composite, SWT.CHECK);
		FormData fd_btnF3 = new FormData();
		fd_btnF3.top = new FormAttachment(btnF1, 0, SWT.TOP);
		fd_btnF3.left = new FormAttachment(btnF2, 10);
		btnF3.setLayoutData(fd_btnF3);
		btnF3.setText("F3");
		btnF3.setSelection(false);
		
		final Button btnF4 = new Button(composite, SWT.CHECK);
		FormData fd_btnF4 = new FormData();
		fd_btnF4.top = new FormAttachment(btnF1, 0, SWT.TOP);
		fd_btnF4.left = new FormAttachment(btnF3, 10);
		btnF4.setLayoutData(fd_btnF4);
		btnF4.setText("F4");
		btnF4.setSelection(false);
		
		final Button btnF5 = new Button(composite, SWT.CHECK);
		FormData fd_btnF5 = new FormData();
		fd_btnF5.top = new FormAttachment(btnF1, 0, SWT.TOP);
		fd_btnF5.left = new FormAttachment(btnF4, 10);
		btnF5.setLayoutData(fd_btnF5);
		btnF5.setText("F5");
		btnF5.setSelection(false);
		
		final Button btnF6 = new Button(composite, SWT.CHECK);
		FormData fd_btnF6 = new FormData();
		fd_btnF6.top = new FormAttachment(btnF1, 0, SWT.TOP);
		fd_btnF6.left = new FormAttachment(btnF5, 10);
		btnF6.setLayoutData(fd_btnF6);
		btnF6.setText("F6");
		btnF6.setSelection(false);
		
		final Button btnF7 = new Button(composite, SWT.CHECK);
		FormData fd_btnF7 = new FormData();
		fd_btnF7.top = new FormAttachment(btnF1, 0, SWT.TOP);
		fd_btnF7.left = new FormAttachment(btnF6, 10);
		btnF7.setLayoutData(fd_btnF7);
		btnF7.setText("F7");
		btnF7.setSelection(false);
		
		//////////////////////////////////////////////////////////////////////
		btnAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedAll = btnAll.getSelection();
				if(isCheckedAll){
					isCheckedIndividual = false;
				}else{
					isCheckedIndividual = true;
				}
			}
		});
		
		btnIndividual.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedIndividual = btnIndividual.getSelection();
				if(isCheckedIndividual){
					isCheckedAll = false;
				}else{
					isCheckedAll = true;
				}
			}
		});
		
		btnF1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF1 = btnF1.getSelection();
			}
		});
		
		btnF2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF2 = btnF2.getSelection();
			}
		});
		
		btnF3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF3 = btnF3.getSelection();
			}
		});
		
		btnF4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF4 = btnF4.getSelection();
			}
		});
		
		btnF5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF5 = btnF5.getSelection();
			}
		});
		
		btnF6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF6 = btnF6.getSelection();
			}
		});
		
		btnF7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF7 = btnF7.getSelection();
			}
		});
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button btnOk = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isCheckedAll)		System.out.println("All Checked");
				if(isCheckedIndividual)	System.out.println("Individual Checked");
				if(isCheckedF1)			System.out.println("F1 Checked");
				if(isCheckedF2)			System.out.println("F2 Checked");
				if(isCheckedF3)			System.out.println("F3 Checked");
				if(isCheckedF4)			System.out.println("F4 Checked");
				if(isCheckedF5)			System.out.println("F5 Checked");
				if(isCheckedF6)			System.out.println("F6 Checked");
				if(isCheckedF7)			System.out.println("F7 Checked");
			}
		});
		
		Button btnCancel = createButton(parent, IDialogConstants.CANCEL_ID,	IDialogConstants.CANCEL_LABEL, false);
		
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(402, 260);
	}
}
