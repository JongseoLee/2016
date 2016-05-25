package com.js.ens.transformation;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Label;
//import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.widgets.TableColumn;

import com.js.ens.transformation.core.TableColumnLabel;
import org.eclipse.jface.viewers.TableViewerColumn;

public class View extends ViewPart {
	private TableColumnLabel tclObj;
	
	public View() {
	}
	public static final String ID = "com.js.ens.transformation.view";
	private Table tableSlabPlateInfo;
	private Table tableVariable;
	private Table tablePLog;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());
		
		TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.top = new FormAttachment(0,50);
		fd_tabFolder.left = new FormAttachment(0,0);
		fd_tabFolder.right = new FormAttachment(100,0);
		fd_tabFolder.bottom = new FormAttachment(100,-50);
		tabFolder.setLayoutData(fd_tabFolder);
		
		TabItem tbtmTotal = new TabItem(tabFolder, SWT.NONE);
		tbtmTotal.setText("P Log");
		
		Composite compositeTotal = new Composite(tabFolder, SWT.NONE);
		tbtmTotal.setControl(compositeTotal);
		compositeTotal.setLayout(new FormLayout());
		
		Label lblSlabAndPlate = new Label(compositeTotal, SWT.NONE);
		FormData fd_lblSlabAndPlate = new FormData();
		fd_lblSlabAndPlate.top = new FormAttachment(0, 10);
		fd_lblSlabAndPlate.left = new FormAttachment(0, 10);
		lblSlabAndPlate.setLayoutData(fd_lblSlabAndPlate);
		lblSlabAndPlate.setText("Slab and Plate Data");
		
		TableViewer tableViewerSlabPlateInfo = new TableViewer(compositeTotal, SWT.BORDER | SWT.FULL_SELECTION);
		tableSlabPlateInfo = tableViewerSlabPlateInfo.getTable();
		tableSlabPlateInfo.setLinesVisible(true);
		tableSlabPlateInfo.setHeaderVisible(true);
		FormData fd_tableSlabPlateInfo = new FormData();
		fd_tableSlabPlateInfo.top = new FormAttachment(lblSlabAndPlate,10);
		fd_tableSlabPlateInfo.left = new FormAttachment(0,10);
		fd_tableSlabPlateInfo.right = new FormAttachment(0,745);
		fd_tableSlabPlateInfo.bottom = new FormAttachment(0,90);
		tableSlabPlateInfo.setLayoutData(fd_tableSlabPlateInfo);
		
		Label lblVariable = new Label(compositeTotal, SWT.NONE);
		FormData fd_lblVariable = new FormData();
		fd_lblVariable.top = new FormAttachment(tableSlabPlateInfo, 20);
		fd_lblVariable.left = new FormAttachment(lblSlabAndPlate, 0, SWT.LEFT);
		lblVariable.setLayoutData(fd_lblVariable);
		lblVariable.setText("Variable");
		
		TableViewer tableViewerVariable = new TableViewer(compositeTotal, SWT.BORDER | SWT.FULL_SELECTION);
		tableVariable = tableViewerVariable.getTable();
		tableVariable.setLinesVisible(true);
		tableVariable.setHeaderVisible(true);
		FormData fd_tableVariable = new FormData();
		fd_tableVariable.top = new FormAttachment(lblVariable, 10);
		fd_tableVariable.left = new FormAttachment(lblSlabAndPlate, 0, SWT.LEFT);
		fd_tableVariable.right = new FormAttachment(0,980);
		fd_tableVariable.bottom = new FormAttachment(0,190);
		tableVariable.setLayoutData(fd_tableVariable);
		
		Label lblPLog = new Label(compositeTotal, SWT.NONE);
		FormData fd_lblPLog = new FormData();
		fd_lblPLog.top = new FormAttachment(tableVariable, 20);
		fd_lblPLog.left = new FormAttachment(lblSlabAndPlate, 0, SWT.LEFT);
		lblPLog.setLayoutData(fd_lblPLog);
		lblPLog.setText("P Log");
		
		TableViewer tableViewerPLog = new TableViewer(compositeTotal, SWT.BORDER | SWT.FULL_SELECTION);
		tablePLog = tableViewerPLog.getTable();
		tablePLog.setLinesVisible(true);
		tablePLog.setHeaderVisible(true);
		FormData fd_tablePLog = new FormData();
		fd_tablePLog.top = new FormAttachment(lblPLog, 10);
		fd_tablePLog.left = new FormAttachment(lblSlabAndPlate, 0, SWT.LEFT);
		fd_tablePLog.right = new FormAttachment(0,680);
		fd_tablePLog.bottom = new FormAttachment(100,-10);
		tablePLog.setLayoutData(fd_tablePLog);
		
		init_TableColunm();
		
		//----------------------------------------------------------------------
		//----------------------------------------------------------------------
		//----------------------------------------------------------------------
		TabItem tbtmDetail = new TabItem(tabFolder, SWT.NONE);
		tbtmDetail.setText("F1~F7");
		
		Composite compositeDetail = new Composite(tabFolder, SWT.NONE);
		tbtmDetail.setControl(compositeDetail);
		compositeDetail.setLayout(new FormLayout());
		
	
		
		
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
	
	public void init_TableColunm(){
		tclObj = new TableColumnLabel();
		tclObj.readTableColumnLabelFile();
		
		String [] ColumnName_SlabPlateInfo = new String[]{
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_0),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_1),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_2),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_3),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_4),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_5),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_6),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_7),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_8)			
		};
		int [] ColumnWidth_SlabPlateInfo = new int[]{
				80,80,80,80,80,80,80,80,80
		};
		int [] ColumnAligments_SlabPlateInfo = new int []{
				SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER};
		String [] ColumnProperty_SlabPlateInfo ={
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_0),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_1),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_2),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_3),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_4),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_5),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_6),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_7),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_8)	
		};
		
		for(int i=0;i<ColumnName_SlabPlateInfo.length;i++){
			TableColumn tableColumn_SlabPlateInfo = new TableColumn(this.tableSlabPlateInfo,ColumnAligments_SlabPlateInfo[i]);
			tableColumn_SlabPlateInfo.setText(ColumnName_SlabPlateInfo[i]);
			tableColumn_SlabPlateInfo.setWidth(ColumnWidth_SlabPlateInfo[i]);			
		}
		//med.getTableViewerUpperRoll().setColumnProperties(ColumnProperty_SlabPlateInfo);
		
		String [] ColumnName_Variable = new String[]{
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_0),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_1),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_2),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_3),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_4),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_5),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_6),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_7),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_8),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_9),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_10),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_11),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_12),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_13),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_14),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_15)					
		};
		int [] ColumnWidth_Variable  = new int[]{
				60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60
		};
		int [] ColumnAligments_Variable  = new int []{
				SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER
				,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER};
		String [] ColumnProperty_Variable  ={
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_0),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_1),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_2),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_3),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_4),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_5),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_6),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_7),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_8),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_9),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_10),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_11),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_12),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_13),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_14),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_15)					
		};
		
		for(int i=0;i<ColumnName_Variable .length;i++){
			TableColumn tableColumn_Variable = new TableColumn(this.tableVariable,ColumnAligments_Variable[i]);
			tableColumn_Variable.setText(ColumnName_Variable[i]);
			tableColumn_Variable.setWidth(ColumnWidth_Variable[i]);			
		}
		//med.getTableViewerUpperRoll().setColumnProperties(ColumnProperty_Variable);
		
		String [] ColumnName_PLog = new String[]{
				tclObj.getTableColumnLabel(TableColumnLabel.CL3_0),
				"F1","F2","F3","F4","F5","F6","F7"									
		};
		int [] ColumnWidth_PLog  = new int[]{
				80,80,80,80,80,80,80,80
		};
		int [] ColumnAligments_PLog  = new int []{
				SWT.CENTER,SWT.RIGHT,SWT.RIGHT,SWT.RIGHT,SWT.RIGHT,SWT.RIGHT,SWT.RIGHT,SWT.RIGHT};
		String [] ColumnProperty_PLog  ={
				tclObj.getTableColumnLabel(TableColumnLabel.CL3_0),
				"F1","F2","F3","F4","F5","F6","F7"				
		};
		
		for(int i=0;i<ColumnName_PLog.length;i++){
			TableColumn tableColumn_PLog = new TableColumn(this.tablePLog,ColumnAligments_PLog[i]);
			tableColumn_PLog.setText(ColumnName_PLog[i]);
			tableColumn_PLog.setWidth(ColumnWidth_PLog[i]);			
		}
		//med.getTableViewerUpperRoll().setColumnProperties(ColumnProperty_PLog);
	}
}