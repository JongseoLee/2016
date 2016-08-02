package com.js.ens.leveller.dialog;

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
import org.eclipse.swt.widgets.Shell;

import com.js.ens.leveller.core.LevellerMain;
import com.js.util.myUtil;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

public class SaveAsLevellerDlg extends Dialog {
	private LevellerMain LMain = LevellerMain.getInstatnce();

	private String newPath = null;
	private String newModelName = null;
	private Text textNewModelName;
	private Text textNewWorkspacePath;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public SaveAsLevellerDlg(Shell parentShell) {
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
		
		Label lblSaveAs = new Label(container, SWT.NONE);
		lblSaveAs.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblSaveAs = new FormData();
		fd_lblSaveAs.top = new FormAttachment(0);
		fd_lblSaveAs.left = new FormAttachment(0);
		lblSaveAs.setLayoutData(fd_lblSaveAs);
		lblSaveAs.setText("Save as");
		
		Label lblDoYouSave = new Label(container, SWT.NONE);
		FormData fd_lblDoYouSave = new FormData();
		fd_lblDoYouSave.top = new FormAttachment(lblSaveAs, 5);
		fd_lblDoYouSave.left = new FormAttachment(lblSaveAs, 10, SWT.LEFT);
		lblDoYouSave.setLayoutData(fd_lblDoYouSave);
		lblDoYouSave.setText("Do you save as leveller data?");
		
		Label lblNewModelName = new Label(container, SWT.NONE);
		FormData fd_lblNewModelName = new FormData();
		fd_lblNewModelName.top = new FormAttachment(lblDoYouSave, 17);
		fd_lblNewModelName.left = new FormAttachment(lblDoYouSave, 0, SWT.LEFT);
		lblNewModelName.setLayoutData(fd_lblNewModelName);
		lblNewModelName.setText("New Model Name");
		
		textNewModelName = new Text(container, SWT.BORDER);
		textNewModelName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				newModelName = text.getText().toString().trim();
				
			}
		});
		FormData fd_textNewModelName = new FormData();
		fd_textNewModelName.top = new FormAttachment(lblNewModelName, -2, SWT.TOP);
		fd_textNewModelName.left = new FormAttachment(lblNewModelName, 30);
		fd_textNewModelName.right = new FormAttachment(lblNewModelName,150,SWT.RIGHT);
		textNewModelName.setLayoutData(fd_textNewModelName);
		
		
		Label lblNewWorkspacePath = new Label(container, SWT.NONE);
		FormData fd_lblNewWorkspacePath = new FormData();
		fd_lblNewWorkspacePath.top = new FormAttachment(lblNewModelName, 5);
		fd_lblNewWorkspacePath.left = new FormAttachment(lblDoYouSave,0,SWT.LEFT);
		lblNewWorkspacePath.setLayoutData(fd_lblNewWorkspacePath);
		lblNewWorkspacePath.setText("New Workspace path");
		
		textNewWorkspacePath = new Text(container, SWT.BORDER);
		textNewWorkspacePath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				newPath = text.getText().toString().trim();
			}
		});
		FormData fd_textNewWorkspacePath = new FormData();
		fd_textNewWorkspacePath.top = new FormAttachment(lblNewWorkspacePath, 5);
		fd_textNewWorkspacePath.left = new FormAttachment(lblDoYouSave, 0, SWT.LEFT);
		fd_textNewWorkspacePath.right = new FormAttachment(100,-35);
		textNewWorkspacePath.setLayoutData(fd_textNewWorkspacePath);
		if(LMain.getWorkspace() != null){
			textNewWorkspacePath.setText(myUtil.getParrentPath(LMain.getWorkspace()));
		}else{
			textNewWorkspacePath.setText(System.getProperty("user.dir"));
		}
		
		final Button buttonExplorer = new Button(container, SWT.NONE);
		buttonExplorer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog  dlg = new DirectoryDialog(buttonExplorer.getShell());
				dlg.setFilterPath(System.getProperty("user.dir"));
				dlg.setMessage("Select a workspace");
				String path = dlg.open();
				if (path != null){
					textNewWorkspacePath.setText(path);
				}
			}
		});
		FormData fd_buttonExplorer = new FormData();
		fd_buttonExplorer.top = new FormAttachment(textNewWorkspacePath, -2, SWT.TOP);
		fd_buttonExplorer.left = new FormAttachment(textNewWorkspacePath, 5);
		fd_buttonExplorer.right = new FormAttachment(textNewWorkspacePath,45,SWT.RIGHT);
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
		Button btn_OK = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,	true);
		btn_OK.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(newModelName != null && newPath != null)
				saveAsLeveller();
				
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(454, 341);
	}
	
	public void saveAsLeveller(){
		if(LMain.getLevellerType().equals("2D")){
			LMain.SaveAsLeveller_2D(newPath, newModelName);
		}else{
			LMain.SaveAsLeveller(newPath, newModelName);
		}
			
		
		
	}
}
