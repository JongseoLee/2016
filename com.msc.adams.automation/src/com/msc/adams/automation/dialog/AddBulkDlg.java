package com.msc.adams.automation.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.datas.Part;
import com.msc.util.myUtil;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class AddBulkDlg extends Dialog {
	private MainController MC = MainController.getInstance();
	
	private Text textBulkData;
	public String textBulkDataPath = null;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public AddBulkDlg(Shell parentShell) {
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
		
		Label lblAddBulkData = new Label(container, SWT.NONE);
		lblAddBulkData.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblAddBulkData = new FormData();
		fd_lblAddBulkData.top = new FormAttachment(0);
		fd_lblAddBulkData.left = new FormAttachment(0);
		lblAddBulkData.setLayoutData(fd_lblAddBulkData);
		lblAddBulkData.setText("Add Bulk data for Durablility/Fatigue Analysis");
		
		Label lblComment1 = new Label(container, SWT.NONE);
		lblComment1.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		lblComment1.setText("If you want to use Durability/Fatigue Analysis in this part, you must add bulk data.");
		FormData fd_lblComment1 = new FormData();
		fd_lblComment1.top = new FormAttachment(lblAddBulkData,25);
		fd_lblComment1.left = new FormAttachment(lblAddBulkData, 0, SWT.LEFT);
		lblComment1.setLayoutData(fd_lblComment1);
		
		Label lblBulkDataFile = new Label(container, SWT.NONE);
		FormData fd_lblBulkDataFile = new FormData();
		fd_lblBulkDataFile.top = new FormAttachment(lblComment1, 30);
		fd_lblBulkDataFile.left = new FormAttachment(lblAddBulkData, 10, SWT.LEFT);
		lblBulkDataFile.setLayoutData(fd_lblBulkDataFile);
		lblBulkDataFile.setText("Bulk data file path");
		
		textBulkData = new Text(container, SWT.BORDER);
		textBulkData.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				textBulkDataPath = text.getText().toString().trim();
			}
		});
		FormData fd_textBulkData = new FormData();
		fd_textBulkData.top = new FormAttachment(lblBulkDataFile, 0);
		fd_textBulkData.left = new FormAttachment(lblBulkDataFile, 0, SWT.LEFT);
		fd_textBulkData.right = new FormAttachment(100, -35);
		textBulkData.setLayoutData(fd_textBulkData);

		final Button buttonExplorer = new Button(container, SWT.NONE);
		buttonExplorer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				FileDialog dlg = new FileDialog(buttonExplorer.getShell());
				dlg.setText("Select BDF file for Nastran.");
				
				String [] extNames = {"All(*.*)"};
				String [] extType = {"*.*"};
				
				dlg.setFilterNames(extNames);
				dlg.setFilterExtensions(extType);
				
				dlg.setFilterNames(extNames);
				String path = dlg.open();
				
				if (path != null){
					textBulkData.setText(path);
				}
			}
		});
		FormData fd_buttonExplorer = new FormData();
		fd_buttonExplorer.top = new FormAttachment(textBulkData, -2,SWT.TOP);
		fd_buttonExplorer.left = new FormAttachment(textBulkData, 5);
		fd_buttonExplorer.right = new FormAttachment(textBulkData,45,SWT.RIGHT);
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
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RunAddBulkData();
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(508, 300);
	}
	
	private void RunAddBulkData(){
		MC.executeAddBulkDlgImpl(textBulkDataPath);
	}
}
