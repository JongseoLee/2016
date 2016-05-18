package com.js.ens.leveller.dialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

import com.js.ens.leveller.core.LevellerMain;
import com.js.util.myUtil;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class NewLevellerDlg extends Dialog {
	private LevellerMain LMain = LevellerMain.getInstatnce();
	
	private Text textModelName;
	private Text textWorkspace;
	
	public String ModelName = null;
	public String Workspace = null;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public NewLevellerDlg(Shell parentShell) {
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
		fl_container.marginHeight = 10;
		fl_container.marginLeft = 10;
		fl_container.marginBottom = 10;
		container.setLayout(fl_container);
		
		Label lblNew = new Label(container, SWT.NONE);
		lblNew.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblNew = new FormData();
		fd_lblNew.top = new FormAttachment(0);
		fd_lblNew.left = new FormAttachment(0);
		lblNew.setLayoutData(fd_lblNew);
		lblNew.setText("New");
		
		Label lblModelName = new Label(container, SWT.NONE);
		FormData fd_lblModelName = new FormData();
		fd_lblModelName.top = new FormAttachment(lblNew, 10);
		fd_lblModelName.left = new FormAttachment(0, 10);
		lblModelName.setLayoutData(fd_lblModelName);
		lblModelName.setText("Model Name");
		
		textModelName = new Text(container, SWT.BORDER);
		textModelName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				ModelName = text.getText().toString().trim();
				
			}
		});
		FormData fd_textModelName = new FormData();
		fd_textModelName.top = new FormAttachment(lblModelName, -2, SWT.TOP);
		fd_textModelName.left = new FormAttachment(lblModelName, 20);
		fd_textModelName.right = new FormAttachment(lblModelName, 180,SWT.RIGHT);
		textModelName.setLayoutData(fd_textModelName);
		
		Label lblWorkspacePath = new Label(container, SWT.NONE);
		FormData fd_lblWorkspacePath = new FormData();
		fd_lblWorkspacePath.top = new FormAttachment(lblModelName, 12);
		fd_lblWorkspacePath.left = new FormAttachment(0, 10);
		lblWorkspacePath.setLayoutData(fd_lblWorkspacePath);
		lblWorkspacePath.setText("Workspace Path");
		
		textWorkspace = new Text(container, SWT.BORDER);
		textWorkspace.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				Workspace = text.getText().toString().trim();
			}
		});
		FormData fd_textWorkspace = new FormData();
		fd_textWorkspace.top = new FormAttachment(lblWorkspacePath, 5);
		fd_textWorkspace.left = new FormAttachment(0, 10);
		fd_textWorkspace.right = new FormAttachment(100,-35);
		textWorkspace.setLayoutData(fd_textWorkspace);
		textWorkspace.setText(myUtil.setPath(System.getProperty("user.dir"),"userData"));
		
		final Button buttonExplorer = new Button(container, SWT.NONE);
		buttonExplorer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog  dlg = new DirectoryDialog(buttonExplorer.getShell());
				dlg.setFilterPath(myUtil.setPath(System.getProperty("user.dir"),"userData"));
				dlg.setMessage("Select a workspace");
				String path = dlg.open();
				if (path != null){
					textWorkspace.setText(path);
				}
			}
		});
		FormData fd_buttonExplorer = new FormData();
		fd_buttonExplorer.top = new FormAttachment(textWorkspace, -2,SWT.TOP);
		fd_buttonExplorer.left = new FormAttachment(textWorkspace, 5);
		fd_buttonExplorer.right = new FormAttachment(textWorkspace,45,SWT.RIGHT);
		buttonExplorer.setLayoutData(fd_buttonExplorer);
		buttonExplorer.setText("...");

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button btn_OK = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,true);
		btn_OK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(ModelName != null && Workspace != null){
					NewLeveller();
				}
			}
		});
		
		Button cancel = createButton(parent, IDialogConstants.CANCEL_ID,IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	
	public void NewLeveller(){
		
		if(ModelName.equals(null)){
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			ModelName = sdf.format(dt).toString();
		}
		
		if(!Workspace.equals(null)){
			//System.out.println(ModelName);
			//System.out.println(Workspace);
			LMain.NewLeveller(ModelName,Workspace);
		}
	}
}
