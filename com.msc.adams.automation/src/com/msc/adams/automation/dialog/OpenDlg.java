package com.msc.adams.automation.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.wb.swt.SWTResourceManager;

import com.msc.adams.automation.core.MainController;

import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class OpenDlg extends Dialog {
	private MainController MC = MainController.getInstance();
	
	private Text textDBPath;
	private String DBPath;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public OpenDlg(Shell parentShell) {
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
		fl_container.spacing = 10;
		fl_container.marginWidth = 10;
		fl_container.marginTop = 10;
		fl_container.marginRight = 10;
		fl_container.marginLeft = 10;
		fl_container.marginHeight = 10;
		fl_container.marginBottom = 10;
		container.setLayout(fl_container);
		
		Label lblOpen = new Label(container, SWT.NONE);
		lblOpen.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblOpen = new FormData();
		fd_lblOpen.top = new FormAttachment(0);
		fd_lblOpen.left = new FormAttachment(0);
		lblOpen.setLayoutData(fd_lblOpen);
		lblOpen.setText("Open Project");
		
		Label lblDesp = new Label(container, SWT.NONE);
		FormData fd_lblDesp = new FormData();
		fd_lblDesp.top = new FormAttachment(lblOpen, 20);
		fd_lblDesp.left = new FormAttachment(lblOpen, 0, SWT.LEFT);
		lblDesp.setLayoutData(fd_lblDesp);
		lblDesp.setText("Select DB(*.msck) File.");
		
		textDBPath = new Text(container, SWT.BORDER);
		textDBPath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				DBPath = text.getText().toString().trim();
			}
		});
		FormData fd_textDBPath = new FormData();
		fd_textDBPath.top = new FormAttachment(lblDesp, 12);
		fd_textDBPath.left = new FormAttachment(lblOpen, 0, SWT.LEFT);
		fd_textDBPath.right = new FormAttachment(100,-30);
		textDBPath.setLayoutData(fd_textDBPath);
		
		final Button btnExplorer = new Button(container, SWT.NONE);
		btnExplorer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog  dlg = new FileDialog (btnExplorer.getShell(),SWT.OPEN);
				dlg.setText("Select Modulus of elasticity File");
				
				String [] extNames = {"MSCK DB(*.msck)"};
				String [] extType = {"*.msck"};
				
				dlg.setFilterNames(extNames);
				dlg.setFilterExtensions(extType);
				
				dlg.setFilterNames(extNames);
				String path = dlg.open();
				if (path == null){
					return;
				}else {
					textDBPath.setText(path);
				}
			}
		});
		btnExplorer.setText("...");
		FormData fd_btnExplorer = new FormData();
		fd_btnExplorer.top = new FormAttachment(textDBPath, -2, SWT.TOP);
		fd_btnExplorer.left = new FormAttachment(textDBPath, 5);
		fd_btnExplorer.right = new FormAttachment(textDBPath, 45,SWT.RIGHT);
		btnExplorer.setLayoutData(fd_btnExplorer);
		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button btnOK = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		btnOK.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(DBPath != null){
					RunOpenProject();
				}
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 250);
	}
	
	private void RunOpenProject(){
		MC.executeFileOpenImpl(DBPath);
	}
	
}
