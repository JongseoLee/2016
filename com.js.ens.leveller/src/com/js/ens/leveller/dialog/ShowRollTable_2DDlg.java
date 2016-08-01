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

public class ShowRollTable_2DDlg extends Dialog {
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private Mediator med = Mediator.getInstance();
	private Table tableUpperRoll_2D;
	private Table tableLowerRoll_2D;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ShowRollTable_2DDlg(Shell parentShell) {
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
		
		
		Label lblUpperRoll_2D = new Label(container, SWT.NONE);
		FormData fd_lblUpperRoll_2D = new FormData();
		fd_lblUpperRoll_2D.top = new FormAttachment(0, 10);
		fd_lblUpperRoll_2D.left = new FormAttachment(0, 5);
		lblUpperRoll_2D.setLayoutData(fd_lblUpperRoll_2D);
		lblUpperRoll_2D.setText(TextLabel_UI.lblUpperRoll_2D);
		
		TableViewer tableViewerUpperRoll_2D = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		med.setTableViewerUpperRoll_2D(tableViewerUpperRoll_2D);
		CustomTableViewer customTableViewerUpperRoll_2D = new CustomTableViewer(Mediator.TABLEVIEWER_UpperRoll_2D,med);
		med.setCustomTableViewerUpperRoll_2D(customTableViewerUpperRoll_2D);
		customTableViewerUpperRoll_2D.setCustomWidget_tableViewerUpperRoll_2D();
		tableUpperRoll_2D = tableViewerUpperRoll_2D.getTable();
		tableUpperRoll_2D.setLinesVisible(true);
		tableUpperRoll_2D.setHeaderVisible(true);
		FormData fd_tableUpperRoll_2D = new FormData();
		fd_tableUpperRoll_2D.top = new FormAttachment(lblUpperRoll_2D, 5);
		fd_tableUpperRoll_2D.left = new FormAttachment(lblUpperRoll_2D, 0, SWT.LEFT);
		fd_tableUpperRoll_2D.right = new FormAttachment(100,-5);
		fd_tableUpperRoll_2D.bottom = new FormAttachment(lblUpperRoll_2D, 150, SWT.BOTTOM);
		tableUpperRoll_2D.setLayoutData(fd_tableUpperRoll_2D);
		//tableUpperRoll.setEnabled(false);
		
		Label lblLowerRoll_2D = new Label(container, SWT.NONE);
		FormData fd_lblLowerRoll_2D = new FormData();
		fd_lblLowerRoll_2D.top = new FormAttachment(tableUpperRoll_2D, 5);
		fd_lblLowerRoll_2D.left = new FormAttachment(lblUpperRoll_2D, 0, SWT.LEFT);
		lblLowerRoll_2D.setLayoutData(fd_lblLowerRoll_2D);
		lblLowerRoll_2D.setText(TextLabel_UI.lblLowerRoll_2D);
		
		TableViewer tableViewerLowerRoll_2D = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		med.setTableViewerLowerRoll_2D(tableViewerLowerRoll_2D);
		CustomTableViewer customTableViewerLowerRoll_2D = new CustomTableViewer(Mediator.TABLEVIEWER_LowerRoll_2D,med);
		med.setCustomTableViewerLowerRoll_2D(customTableViewerLowerRoll_2D);
		customTableViewerLowerRoll_2D.setCustomWidget_tableViewerLowerRoll_2D();
		tableLowerRoll_2D = tableViewerLowerRoll_2D.getTable();
		tableLowerRoll_2D.setLinesVisible(true);
		tableLowerRoll_2D.setHeaderVisible(true);
		FormData fd_tableLowerRoll_2D = new FormData();
		fd_tableLowerRoll_2D.top = new FormAttachment(lblLowerRoll_2D, 5);
		fd_tableLowerRoll_2D.left = new FormAttachment(lblUpperRoll_2D, 0, SWT.LEFT);
		fd_tableLowerRoll_2D.right = new FormAttachment(100,-5);
		fd_tableLowerRoll_2D.bottom = new FormAttachment(lblLowerRoll_2D, 150, SWT.BOTTOM);
		tableLowerRoll_2D.setLayoutData(fd_tableLowerRoll_2D);
		//tableLowerRoll.setEnabled(false);

		init_TableColunmn();
		
		Button btnSaveRoll_2D = new Button(container, SWT.NONE);
		med.setBtnSaveRoll_2D(btnSaveRoll_2D);
		CustomButton customBtnSaveRoll_2D = new CustomButton(Mediator.BUTTON_SaveRoll,med);
		med.setCustomBtnSaveRoll_2D(customBtnSaveRoll_2D);
		customBtnSaveRoll_2D.setCustomWidget_btnSaveRoll_2D();
		FormData fd_btnSaveRoll_2D = new FormData();
		fd_btnSaveRoll_2D.top = new FormAttachment(tableLowerRoll_2D, 5);
		fd_btnSaveRoll_2D.left = new FormAttachment(tableLowerRoll_2D, 0, SWT.LEFT);
		fd_btnSaveRoll_2D.right = new FormAttachment(tableLowerRoll_2D, 0, SWT.RIGHT);
		btnSaveRoll_2D.setLayoutData(fd_btnSaveRoll_2D);
		btnSaveRoll_2D.setText("Save Roll");
		// */ 
		
		LMain.showRollTable_2D();
		
		HandlerTableViewer handlerTableViewer = new HandlerTableViewer();
		med.getTableViewerUpperRoll_2D().addSelectionChangedListener(handlerTableViewer);
		med.getTableViewerLowerRoll_2D().addSelectionChangedListener(handlerTableViewer);
		HandlerButton handlerButton = new HandlerButton();
		med.getBtnSaveRoll_2D().addListener(SWT.Selection, handlerButton);
		
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
		return new Point(473, 517);
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
			TableColumn tableColumn_up = new TableColumn(this.tableUpperRoll_2D,ColumnAligments[i]);
			tableColumn_up.setText(ColumnName[i]);
			tableColumn_up.setWidth(ColumnWidth[i]);

			TableColumn tableColumn_down = new TableColumn(this.tableLowerRoll_2D,ColumnAligments[i]);
			tableColumn_down.setText(ColumnName[i]);
			tableColumn_down.setWidth(ColumnWidth[i]);
		}
		//med.tableViewerUp.setColumnProperties(ColumnProperty);
		//med.tableViewerDown.setColumnProperties(ColumnProperty);
		med.getTableViewerUpperRoll_2D().setColumnProperties(ColumnProperty);
		med.getTableViewerLowerRoll_2D().setColumnProperties(ColumnProperty);
	}
}
