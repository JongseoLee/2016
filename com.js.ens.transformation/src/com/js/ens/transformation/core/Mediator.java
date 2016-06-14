package com.js.ens.transformation.core;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;

import com.js.ens.transformation.customWidget.ICommand;

public class Mediator {
	private static Mediator instance = new Mediator();
	public static Mediator getInstance(){
		return instance;
	}
	
	
	private TabFolder tabFolder;
	public ICommand C_tabFolder;
	public static String TABFOLDER_tabFolder = "tabFolder";
	
	

	//
	// Tab 1
	//
	private TableViewer tableViewerSlabPlateInfo;
	public ICommand C_tableViewerSlabPlateInfo;
	public static String TABLEVIEWER_tableViewerSlabPlateInfo = "tableViewerSlabPlateInfo";
	
	private TableViewer tableViewerVariable;
	public ICommand C_tableViewerVariable;
	public static String TABLEVIEWER_tableViewerVariable = "tableViewerVariable";
	
	private TableViewer tableViewerPLog;
	public ICommand C_tableViewerPLog;
	public static String TABLEVIEWER_tableViewerPLog = "tableViewerPLog";
	
	private Button btnImportPLog;
	public ICommand C_btnImportPLog;
	public static String BUTTON_btnImportPLog = "btnImportPLog";
	
	//
	// Tab2
	//
	
	
	
	
	//
	// Common
	//
	private Label lblModelNameValue;
	private Label lblWorkspacePath;
	
	private Button btnApply;
	public ICommand C_btnApply;
	public static String BUTTON_btnApply = "btnApply";
	
	
	
	
	//
	// set get method
	//
	public TabFolder getTabFolder() {
		return tabFolder;
	}
	public void setTabFolder(TabFolder tabFolder) {
		this.tabFolder = tabFolder;
	}
	public ICommand getC_tabFolder() {
		return C_tabFolder;
	}
	public void setC_tabFolder(ICommand c_tabFolder) {
		C_tabFolder = c_tabFolder;
	}
	
	
	public TableViewer getTableViewerSlabPlateInfo() {
		return tableViewerSlabPlateInfo;
	}
	public void setTableViewerSlabPlateInfo(TableViewer tableViewerSlabPlateInfo) {
		this.tableViewerSlabPlateInfo = tableViewerSlabPlateInfo;
	}
	public ICommand getC_tableViewerSlabPlateInfo() {
		return C_tableViewerSlabPlateInfo;
	}
	public void setC_tableViewerSlabPlateInfo(ICommand c_tableViewerSlabPlateInfo) {
		C_tableViewerSlabPlateInfo = c_tableViewerSlabPlateInfo;
	}
	
	
	public TableViewer getTableViewerVariable() {
		return tableViewerVariable;
	}
	public void setTableViewerVariable(TableViewer tableViewerVariable) {
		this.tableViewerVariable = tableViewerVariable;
	}
	public ICommand getC_tableViewerVariable() {
		return C_tableViewerVariable;
	}
	public void setC_tableViewerVariable(ICommand c_tableViewerVariable) {
		C_tableViewerVariable = c_tableViewerVariable;
	}
	
	
	public TableViewer getTableViewerPLog() {
		return tableViewerPLog;
	}
	public void setTableViewerPLog(TableViewer tableViewerPLog) {
		this.tableViewerPLog = tableViewerPLog;
	}
	public ICommand getC_tableViewerPLog() {
		return C_tableViewerPLog;
	}
	public void setC_tableViewerPLog(ICommand c_tableViewerPLog) {
		C_tableViewerPLog = c_tableViewerPLog;
	}
	
	
	public Button getBtnImportPLog() {
		return btnImportPLog;
	}
	public void setBtnImportPLog(Button btnImportPLog) {
		this.btnImportPLog = btnImportPLog;
	}
	public ICommand getC_btnImportPLog() {
		return C_btnImportPLog;
	}
	public void setC_btnImportPLog(ICommand c_btnImportPLog) {
		C_btnImportPLog = c_btnImportPLog;
	}
	
	
	public Button getBtnApply() {
		return btnApply;
	}
	public void setBtnApply(Button btnApply) {
		this.btnApply = btnApply;
	}
	public ICommand getC_btnApply() {
		return C_btnApply;
	}
	public void setC_btnApply(ICommand c_btnApply) {
		C_btnApply = c_btnApply;
	}
	
	
	public Label getLblModelNameValue() {
		return lblModelNameValue;
	}
	public void setLblModelNameValue(Label lblModelNameValue) {
		this.lblModelNameValue = lblModelNameValue;
	}
	
	
	public Label getLblWorkspacePath() {
		return lblWorkspacePath;
	}
	public void setLblWorkspacePath(Label lblWorkspacePath) {
		this.lblWorkspacePath = lblWorkspacePath;
	}
	
}
