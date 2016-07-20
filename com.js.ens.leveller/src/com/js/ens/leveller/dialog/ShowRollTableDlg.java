package com.js.ens.leveller.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.core.Mediator;
import com.js.ens.leveller.core.TableColumnLabel;
import com.js.ens.leveller.core.TextLabel_UI;
import com.js.ens.leveller.customWidget.CustomButton;
import com.js.ens.leveller.customWidget.CustomTableViewer;
import com.js.ens.leveller.handler.HandlerButton;
import com.js.ens.leveller.handler.HandlerTableViewer;

import org.eclipse.swt.layout.FormLayout;

public class ShowRollTableDlg extends Dialog {
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private Mediator med = Mediator.getInstance();
	private Table tableUpperRoll;
	private Table tableLowerRoll;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ShowRollTableDlg(Shell parentShell) {
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
		container.setLayout(new FormLayout());
		
		
		Label lblUpperRoll = new Label(container, SWT.NONE);
		FormData fd_lblUpperRoll = new FormData();
		fd_lblUpperRoll.top = new FormAttachment(0, 10);
		fd_lblUpperRoll.left = new FormAttachment(0, 5);
		lblUpperRoll.setLayoutData(fd_lblUpperRoll);
		lblUpperRoll.setText(TextLabel_UI.lblUpperRoll);
		
		TableViewer tableViewerUpperRoll = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		med.setTableViewerUpperRoll(tableViewerUpperRoll);
		CustomTableViewer customTableViewerUpperRoll = new CustomTableViewer(Mediator.TABLEVIEWER_UpperRoll,med);
		med.setCustomTableViewerUpperRoll(customTableViewerUpperRoll);
		customTableViewerUpperRoll.setCustomWidget_tableViewerUpperRoll();
		tableUpperRoll = tableViewerUpperRoll.getTable();
		tableUpperRoll.setLinesVisible(true);
		tableUpperRoll.setHeaderVisible(true);
		FormData fd_tableUpperRoll = new FormData();
		fd_tableUpperRoll.top = new FormAttachment(lblUpperRoll, 5);
		fd_tableUpperRoll.left = new FormAttachment(lblUpperRoll, 0, SWT.LEFT);
		fd_tableUpperRoll.right = new FormAttachment(100,-5);
		fd_tableUpperRoll.bottom = new FormAttachment(lblUpperRoll, 100, SWT.BOTTOM);
		tableUpperRoll.setLayoutData(fd_tableUpperRoll);
		//tableUpperRoll.setEnabled(false);
		
		Label lblLowerRoll = new Label(container, SWT.NONE);
		FormData fd_lblLowerRoll = new FormData();
		fd_lblLowerRoll.top = new FormAttachment(tableUpperRoll, 5);
		fd_lblLowerRoll.left = new FormAttachment(lblUpperRoll, 0, SWT.LEFT);
		lblLowerRoll.setLayoutData(fd_lblLowerRoll);
		lblLowerRoll.setText(TextLabel_UI.lblLowerRoll);
		
		TableViewer tableViewerLowerRoll = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		med.setTableViewerLowerRoll(tableViewerLowerRoll);
		CustomTableViewer customTableViewerLowerRoll = new CustomTableViewer(Mediator.TABLEVIEWER_LowerRoll,med);
		med.setCustomTableViewerLowerRoll(customTableViewerLowerRoll);
		customTableViewerLowerRoll.setCustomWidget_tableViewerLowerRoll();
		tableLowerRoll = tableViewerLowerRoll.getTable();
		tableLowerRoll.setLinesVisible(true);
		tableLowerRoll.setHeaderVisible(true);
		FormData fd_tableLowerRoll = new FormData();
		fd_tableLowerRoll.top = new FormAttachment(lblLowerRoll, 5);
		fd_tableLowerRoll.left = new FormAttachment(lblUpperRoll, 0, SWT.LEFT);
		fd_tableLowerRoll.right = new FormAttachment(100,-5);
		fd_tableLowerRoll.bottom = new FormAttachment(lblLowerRoll, 100, SWT.BOTTOM);
		tableLowerRoll.setLayoutData(fd_tableLowerRoll);
		//tableLowerRoll.setEnabled(false);

		init_TableColunmn();
		
		Button btnSaveRoll = new Button(container, SWT.NONE);
		med.setBtnSaveRoll(btnSaveRoll);
		CustomButton customBtnSaveRoll = new CustomButton(Mediator.BUTTON_SaveRoll,med);
		med.setcustomBtnSaveRoll(customBtnSaveRoll);
		customBtnSaveRoll.setCustomWidget_btnSaveRoll();
		FormData fd_btnSaveRoll = new FormData();
		fd_btnSaveRoll.top = new FormAttachment(tableLowerRoll, 5);
		fd_btnSaveRoll.left = new FormAttachment(tableLowerRoll, 0, SWT.LEFT);
		fd_btnSaveRoll.right = new FormAttachment(tableLowerRoll, 0, SWT.RIGHT);
		btnSaveRoll.setLayoutData(fd_btnSaveRoll);
		btnSaveRoll.setText("Save Roll");
		// */ 
		
		LMain.showRollTable();
		
		HandlerTableViewer handlerTableViewer = new HandlerTableViewer();
		med.getTableViewerUpperRoll().addSelectionChangedListener(handlerTableViewer);
		med.getTableViewerLowerRoll().addSelectionChangedListener(handlerTableViewer);
		HandlerButton handlerButton = new HandlerButton();
		med.getBtnSaveRoll().addListener(SWT.Selection, handlerButton);
		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(555, 413);
	}
	
	public void init_TableColunmn(){
		String [] ColumnName = new String[]{  
				TableColumnLabel.COL_0,TableColumnLabel.COL_1,TableColumnLabel.COL_2,TableColumnLabel.COL_3};
		int [] ColumnWidth = new int []{
				100,145,95,95	};
		int [] ColumnAligments = new int []{
				SWT.LEFT,SWT.RIGHT,SWT.RIGHT,SWT.RIGHT,};
		String [] ColumnProperty = {
				TableColumnLabel.COL_0,TableColumnLabel.COL_1,TableColumnLabel.COL_2,TableColumnLabel.COL_3};
		for(int i=0;i<ColumnName.length;i++){
			TableColumn tableColumn_up = new TableColumn(this.tableUpperRoll,ColumnAligments[i]);
			tableColumn_up.setText(ColumnName[i]);
			tableColumn_up.setWidth(ColumnWidth[i]);

			TableColumn tableColumn_down = new TableColumn(this.tableLowerRoll,ColumnAligments[i]);
			tableColumn_down.setText(ColumnName[i]);
			tableColumn_down.setWidth(ColumnWidth[i]);
		}
		//med.tableViewerUp.setColumnProperties(ColumnProperty);
		//med.tableViewerDown.setColumnProperties(ColumnProperty);
		med.getTableViewerUpperRoll().setColumnProperties(ColumnProperty);
		med.getTableViewerLowerRoll().setColumnProperties(ColumnProperty);
	}
}
