package com.msc.adams.automation.dialog;

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
import org.eclipse.wb.swt.SWTResourceManager;

import com.msc.adams.automation.core.MainController;

import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SaveDlg extends Dialog {
	private MainController MC = MainController.getInstance();
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public SaveDlg(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.BORDER);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		FormLayout fl_container = new FormLayout();
		fl_container.marginWidth = 10;
		fl_container.spacing = 10;
		fl_container.marginTop = 10;
		fl_container.marginRight = 10;
		fl_container.marginLeft = 10;
		fl_container.marginHeight = 10;
		fl_container.marginBottom = 10;
		container.setLayout(fl_container);
		
		Label lblSave = new Label(container, SWT.NONE);
		lblSave.setText("Save Project");
		lblSave.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblSave = new FormData();
		fd_lblSave.top = new FormAttachment(0);
		fd_lblSave.left = new FormAttachment(0);
		lblSave.setLayoutData(fd_lblSave);
		
		Label lblDesp = new Label(container, SWT.NONE);
		FormData fd_lblDesp = new FormData();
		fd_lblDesp.top = new FormAttachment(lblSave, 32);
		fd_lblDesp.left = new FormAttachment(0);
		lblDesp.setLayoutData(fd_lblDesp);
		lblDesp.setText("Do you save all data?");

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
				MC.executeFileSaveImpl();
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 241);
	}

}
