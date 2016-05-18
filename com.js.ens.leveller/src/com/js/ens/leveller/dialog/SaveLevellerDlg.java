package com.js.ens.leveller.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;

import com.js.ens.leveller.core.LevellerMain;
import com.js.util.myUtil;
import org.eclipse.swt.widgets.Text;

public class SaveLevellerDlg extends Dialog {
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private String dbPath = null;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public SaveLevellerDlg(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.BORDER);
		if(LMain.getWorkspace() != null){
			dbPath = myUtil.setPath(LMain.getWorkspace(),LMain.getTextModelName()+".lvdb");
		}else{
			dbPath = "Data Path : ";
		}
		
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
		
		Label lblSave = new Label(container, SWT.NONE);
		lblSave.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblSave = new FormData();
		fd_lblSave.top = new FormAttachment(0);
		fd_lblSave.left = new FormAttachment(0);
		lblSave.setLayoutData(fd_lblSave);
		lblSave.setText("Save");
		
		Label lblDoYouSave = new Label(container, SWT.NONE);
		FormData fd_lblDoYouSave = new FormData();
		fd_lblDoYouSave.top = new FormAttachment(lblSave, 15);
		fd_lblDoYouSave.left = new FormAttachment(10);
		lblDoYouSave.setLayoutData(fd_lblDoYouSave);
		lblDoYouSave.setText("Do you save leveller data?");
		
		Label lbllvdb = new Label(container, SWT.NONE);
		FormData fd_lbllvdb = new FormData();
		fd_lbllvdb.top = new FormAttachment(lblDoYouSave,10);
		fd_lbllvdb.left = new FormAttachment(0,0);
		fd_lbllvdb.right = new FormAttachment(100,0);
		lbllvdb.setLayoutData(fd_lbllvdb);
		lbllvdb.setText(dbPath);

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
				LMain.SaveLeveller();
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID,IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(700, 315);
	}
}
