package com.msc.adams.automation.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.msc.adams.automation.core.MainController;
import com.msc.util.myUtil;

import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.wb.swt.SWTResourceManager;


public class NewDlg extends Dialog {
	private MainController MC = MainController.getInstance();
	

	private Text textProjectName;
	private Text textWorkspace;
	
	public String ProjectName = null;
	public String Workspace = null;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public NewDlg(Shell parentShell) {
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
		
		Label lblNew = new Label(container, SWT.NONE);
		lblNew.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblNew = new FormData();
		fd_lblNew.top = new FormAttachment(0);
		fd_lblNew.left = new FormAttachment(0);
		lblNew.setLayoutData(fd_lblNew);
		lblNew.setText("New Project");
		
		Label lblProjectName = new Label(container, SWT.NONE);
		FormData fd_lblProjectName = new FormData();
		fd_lblProjectName.top = new FormAttachment(lblNew, 10);
		fd_lblProjectName.left = new FormAttachment(0, 10);
		lblProjectName.setLayoutData(fd_lblProjectName);
		lblProjectName.setText("Project Name");
		
		textProjectName = new Text(container, SWT.BORDER);
		textProjectName.addModifyListener(new ModifyListener() {	
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				Text text = (Text)e.getSource();
				ProjectName = text.getText().toString().trim();
			}
		});
		FormData fd_textProjectName = new FormData();
		fd_textProjectName.top = new FormAttachment(lblProjectName, -2, SWT.TOP);
		fd_textProjectName.left = new FormAttachment(lblProjectName, 20);
		fd_textProjectName.right = new FormAttachment(lblProjectName, 180,SWT.RIGHT);
		textProjectName.setLayoutData(fd_textProjectName);
		
		Label lblWorkspacePath = new Label(container, SWT.NONE);
		FormData fd_lblWorkspacePath = new FormData();
		fd_lblWorkspacePath.top = new FormAttachment(lblProjectName, 12);
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
		String path = myUtil.setPath(System.getProperty("user.dir"),"msck_Workspace");
		textWorkspace.setText(path);
		if(!myUtil.checkPath(path))	myUtil.makeDir(path);
		
		final Button buttonExplorer = new Button(container, SWT.NONE);
		buttonExplorer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog  dlg = new DirectoryDialog(buttonExplorer.getShell());
				dlg.setFilterPath(myUtil.setPath(System.getProperty("user.dir"),"msck_Workspace"));
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
		Button btnOK = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		btnOK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(ProjectName != null && Workspace != null){
					RunNewProject();					
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
		return new Point(450, 300);
	}
	
	private void RunNewProject(){
		String userWorkspace = myUtil.setPath(Workspace, ProjectName);
		MC.executeFileNewImpl(ProjectName, userWorkspace);
	}
}
