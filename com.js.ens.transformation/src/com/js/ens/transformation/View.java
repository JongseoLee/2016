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

import org.eclipse.swt.widgets.TableColumn;

import com.js.ens.transformation.core.TableColumnLabel;
import com.js.ens.transformation.core.UILabel;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

public class View extends ViewPart {
	
	
	public View() {
	}
	
	private TableColumnLabel tclObj;
	private UILabel ulObj;
	
	public static final String ID = "com.js.ens.transformation.view";
	private Table tableSlabPlateInfo;
	private Table tableVariable;
	private Table tablePLog;
	
	private Text textDiameterInWRRoll1;
	private Text textDiameterInWRRoll2;
	private Text textDiameterInWRRoll3;
	private Text textLengthInWRRoll1;
	private Text textLengthInWRRoll2;
	private Text textLengthInWRRoll3;

	private Text textDiameterInBURRoll1;
	private Text textDiameterInBURRoll2;
	private Text textDiameterInBURRoll3;
	private Text textLengthInBURRoll1;
	private Text textLengthInBURRoll2;
	private Text textLengthInBURRoll3;

	private Text textLengthOfPlate;
	private Text textWidthOfPlate;
	private Text textThicknessOfPlate;
	private Text textInitialPositionOfPlate;
	private Text textRollGap;
	
	private Text textArcLengthOfWR;
	private Text textArcLengthOfBUR;

	public void createPartControl(Composite parent) {
		//--------------------------------------------------------------------------
		tclObj = new TableColumnLabel();
		tclObj.readTableColumnLabelFile();
		
		ulObj = new UILabel();
		ulObj.readUILabelFile();
		//--------------------------------------------------------------------------
		parent.setLayout(new FormLayout());
		
		TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.top = new FormAttachment(0,50);
		fd_tabFolder.left = new FormAttachment(0,0);
		fd_tabFolder.right = new FormAttachment(100,0);
		fd_tabFolder.bottom = new FormAttachment(100,-50);
		tabFolder.setLayoutData(fd_tabFolder);
		
		TabItem tbtmTotal = new TabItem(tabFolder, SWT.NONE);
		tbtmTotal.setText(ulObj.getUILabelValue(UILabel.Tab1Label));
		
		Composite compositeTotal = new Composite(tabFolder, SWT.NONE);
		compositeTotal.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tbtmTotal.setControl(compositeTotal);
		compositeTotal.setLayout(new FormLayout());
		
		Label lblSlabAndPlate = new Label(compositeTotal, SWT.NONE);
		FormData fd_lblSlabAndPlate = new FormData();
		fd_lblSlabAndPlate.top = new FormAttachment(0, 10);
		fd_lblSlabAndPlate.left = new FormAttachment(0, 10);
		lblSlabAndPlate.setLayoutData(fd_lblSlabAndPlate);
		lblSlabAndPlate.setText(ulObj.getUILabelValue(UILabel.Table1Label));
		
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
		lblVariable.setText(ulObj.getUILabelValue(UILabel.Table2Label));
		
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
		lblPLog.setText(ulObj.getUILabelValue(UILabel.Table3Label));
		
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
		tbtmDetail.setText(ulObj.getUILabelValue(UILabel.Tab2Label));
		
		Composite compositeDetail = new Composite(tabFolder, SWT.NONE);
		compositeDetail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tbtmDetail.setControl(compositeDetail);
		compositeDetail.setLayout(new FormLayout());
		
		Group groupSTAND = new Group(compositeDetail, SWT.NONE);
		groupSTAND.setText(ulObj.getUILabelValue(UILabel.STAND));
		groupSTAND.setLayout(new FormLayout());
		FormData fd_groupSTAND = new FormData();
		fd_groupSTAND.top = new FormAttachment(0, 10);
		fd_groupSTAND.left = new FormAttachment(0, 10);		
		fd_groupSTAND.right = new FormAttachment(0, 400);
		groupSTAND.setLayoutData(fd_groupSTAND);
		
		Button btnF1 = new Button(groupSTAND, SWT.RADIO);
		btnF1.setSelection(true);
		FormData fd_btnF1 = new FormData();
		fd_btnF1.top = new FormAttachment(0, 5);
		fd_btnF1.left = new FormAttachment(0, 5);
		fd_btnF1.bottom = new FormAttachment(100,-5);
		btnF1.setLayoutData(fd_btnF1);
		btnF1.setText(ulObj.getUILabelValue(UILabel.F1));
		
		Button btnF2 = new Button(groupSTAND, SWT.RADIO);
		FormData fd_btnF2 = new FormData();
		fd_btnF2.top = new FormAttachment(btnF1, 0,SWT.TOP);
		fd_btnF2.left = new FormAttachment(btnF1, 20);
		fd_btnF2.bottom = new FormAttachment(btnF1,0,SWT.BOTTOM);
		btnF2.setLayoutData(fd_btnF2);
		btnF2.setText(ulObj.getUILabelValue(UILabel.F2));

		Button btnF3 = new Button(groupSTAND, SWT.RADIO);
		FormData fd_btnF3 = new FormData();
		fd_btnF3.top = new FormAttachment(btnF1, 0,SWT.TOP);
		fd_btnF3.left = new FormAttachment(btnF2, 20);
		fd_btnF3.bottom = new FormAttachment(btnF1,0,SWT.BOTTOM);
		btnF3.setLayoutData(fd_btnF3);
		btnF3.setText(ulObj.getUILabelValue(UILabel.F3));
		
		Button btnF4 = new Button(groupSTAND, SWT.RADIO);
		FormData fd_btnF4 = new FormData();
		fd_btnF4.top = new FormAttachment(btnF1, 0,SWT.TOP);
		fd_btnF4.left = new FormAttachment(btnF3, 20);
		fd_btnF4.bottom = new FormAttachment(btnF1,0,SWT.BOTTOM);
		btnF4.setLayoutData(fd_btnF4);
		btnF4.setText(ulObj.getUILabelValue(UILabel.F4));
		
		Button btnF5 = new Button(groupSTAND, SWT.RADIO);
		FormData fd_btnF5 = new FormData();
		fd_btnF5.top = new FormAttachment(btnF1, 0,SWT.TOP);
		fd_btnF5.left = new FormAttachment(btnF4, 20);
		fd_btnF5.bottom = new FormAttachment(btnF1,0,SWT.BOTTOM);
		btnF5.setLayoutData(fd_btnF5);
		btnF5.setText(ulObj.getUILabelValue(UILabel.F5));
		
		Button btnF6 = new Button(groupSTAND, SWT.RADIO);
		FormData fd_btnF6 = new FormData();
		fd_btnF6.top = new FormAttachment(btnF1, 0,SWT.TOP);
		fd_btnF6.left = new FormAttachment(btnF5, 20);
		fd_btnF6.bottom = new FormAttachment(btnF1,0,SWT.BOTTOM);
		btnF6.setLayoutData(fd_btnF6);
		btnF6.setText(ulObj.getUILabelValue(UILabel.F6));
		
		Button btnF7 = new Button(groupSTAND, SWT.RADIO);
		FormData fd_btnF7 = new FormData();
		fd_btnF7.top = new FormAttachment(btnF1, 0,SWT.TOP);
		fd_btnF7.left = new FormAttachment(btnF6, 20);
		fd_btnF7.bottom = new FormAttachment(btnF1,0,SWT.BOTTOM);
		btnF7.setLayoutData(fd_btnF7);
		btnF7.setText(ulObj.getUILabelValue(UILabel.F7));
		
		
		//=============
		// Group1 start
		//
		Group grpWorkRollwrShapeParameter = new Group(compositeDetail, SWT.NONE);
		grpWorkRollwrShapeParameter.setText(ulObj.getUILabelValue(UILabel.Work_Roll_shape_parameter));
		grpWorkRollwrShapeParameter.setLayout(new FormLayout());
		FormData fd_grpWorkRollwrShapeParameter = new FormData();
		fd_grpWorkRollwrShapeParameter.top = new FormAttachment(groupSTAND, 5);
		fd_grpWorkRollwrShapeParameter.left = new FormAttachment(0,10);
		fd_grpWorkRollwrShapeParameter.right = new FormAttachment(0, 400);
		fd_grpWorkRollwrShapeParameter.bottom = new FormAttachment(groupSTAND, 210, SWT.BOTTOM);
		grpWorkRollwrShapeParameter.setLayoutData(fd_grpWorkRollwrShapeParameter);
		
		Label lblDiameterInWRRoll1 = new Label(grpWorkRollwrShapeParameter, SWT.NONE);
		FormData fd_lblDiameterInWRRoll1 = new FormData();
		fd_lblDiameterInWRRoll1.top = new FormAttachment(0, 10);
		fd_lblDiameterInWRRoll1.left = new FormAttachment(0, 10);
		lblDiameterInWRRoll1.setLayoutData(fd_lblDiameterInWRRoll1);
		lblDiameterInWRRoll1.setText(ulObj.getUILabelValue(UILabel._1st_diameter_in_WR_roll));
		
		textDiameterInWRRoll1 = new Text(grpWorkRollwrShapeParameter, SWT.BORDER);
		FormData fd_textDiameterInWRRoll1 = new FormData();
		fd_textDiameterInWRRoll1.top = new FormAttachment(lblDiameterInWRRoll1,-2,SWT.TOP);
		fd_textDiameterInWRRoll1.left = new FormAttachment(lblDiameterInWRRoll1,30,SWT.RIGHT);
		fd_textDiameterInWRRoll1.right = new FormAttachment(100, -10);
		textDiameterInWRRoll1.setLayoutData(fd_textDiameterInWRRoll1);
		
		Label lblDiameterInWRRoll2 = new Label(grpWorkRollwrShapeParameter, SWT.NONE);
		FormData fd_lblDiameterInWRRoll2 = new FormData();
		fd_lblDiameterInWRRoll2.top = new FormAttachment(lblDiameterInWRRoll1, 10);
		fd_lblDiameterInWRRoll2.left = new FormAttachment(lblDiameterInWRRoll1, 0,SWT.LEFT);
		lblDiameterInWRRoll2.setLayoutData(fd_lblDiameterInWRRoll2);
		lblDiameterInWRRoll2.setText(ulObj.getUILabelValue(UILabel._2nd_diameter_in_WR_roll));
		
		textDiameterInWRRoll2 = new Text(grpWorkRollwrShapeParameter, SWT.BORDER);
		FormData fd_textDiameterInWRRoll2 = new FormData();
		fd_textDiameterInWRRoll2.top = new FormAttachment(lblDiameterInWRRoll2,-2,SWT.TOP);
		fd_textDiameterInWRRoll2.left = new FormAttachment(textDiameterInWRRoll1, 0,SWT.LEFT);
		fd_textDiameterInWRRoll2.right = new FormAttachment(100, -10);
		textDiameterInWRRoll2.setLayoutData(fd_textDiameterInWRRoll2);
		
		Label lblDiameterInWRRoll3 = new Label(grpWorkRollwrShapeParameter, SWT.NONE);
		FormData fd_lblDiameterInWRRoll3 = new FormData();
		fd_lblDiameterInWRRoll3.top = new FormAttachment(lblDiameterInWRRoll2, 10);
		fd_lblDiameterInWRRoll3.left = new FormAttachment(lblDiameterInWRRoll1, 0,SWT.LEFT);
		lblDiameterInWRRoll3.setLayoutData(fd_lblDiameterInWRRoll3);
		lblDiameterInWRRoll3.setText(ulObj.getUILabelValue(UILabel._3rd_diameter_in_WR_roll));
		
		textDiameterInWRRoll3 = new Text(grpWorkRollwrShapeParameter, SWT.BORDER);
		FormData fd_textDiameterInWRRoll3 = new FormData();
		fd_textDiameterInWRRoll3.top = new FormAttachment(lblDiameterInWRRoll3,-2,SWT.TOP);
		fd_textDiameterInWRRoll3.left = new FormAttachment(textDiameterInWRRoll1, 0,SWT.LEFT);
		fd_textDiameterInWRRoll3.right = new FormAttachment(100, -10);
		textDiameterInWRRoll3.setLayoutData(fd_textDiameterInWRRoll3);
		
		Label lblLengthInWRRoll1 = new Label(grpWorkRollwrShapeParameter, SWT.NONE);
		FormData fd_lblLengthInWRRoll1 = new FormData();
		fd_lblLengthInWRRoll1.top = new FormAttachment(lblDiameterInWRRoll3, 30);
		fd_lblLengthInWRRoll1.left = new FormAttachment(lblDiameterInWRRoll1, 0, SWT.LEFT);
		lblLengthInWRRoll1.setLayoutData(fd_lblLengthInWRRoll1);
		lblLengthInWRRoll1.setText(ulObj.getUILabelValue(UILabel._1st_length_in_WR_roll));
		
		textLengthInWRRoll1 = new Text(grpWorkRollwrShapeParameter, SWT.BORDER);
		FormData fd_textLengthInWRRoll1 = new FormData();
		fd_textLengthInWRRoll1.top = new FormAttachment(lblLengthInWRRoll1,-2,SWT.TOP);
		fd_textLengthInWRRoll1.left = new FormAttachment(textDiameterInWRRoll1, 0,SWT.LEFT);
		fd_textLengthInWRRoll1.right = new FormAttachment(100, -10);
		textLengthInWRRoll1.setLayoutData(fd_textLengthInWRRoll1);
		
		Label lblLengthInWRRoll2 = new Label(grpWorkRollwrShapeParameter, SWT.NONE);
		FormData fd_lblLengthInWRRoll2 = new FormData();
		fd_lblLengthInWRRoll2.top = new FormAttachment(lblLengthInWRRoll1, 10);
		fd_lblLengthInWRRoll2.left = new FormAttachment(lblDiameterInWRRoll1, 0,SWT.LEFT);
		lblLengthInWRRoll2.setLayoutData(fd_lblLengthInWRRoll2);
		lblLengthInWRRoll2.setText(ulObj.getUILabelValue(UILabel._2nd_length_in_WR_roll));
		
		textLengthInWRRoll2 = new Text(grpWorkRollwrShapeParameter, SWT.BORDER);
		FormData fd_textLengthInWRRoll2 = new FormData();
		fd_textLengthInWRRoll2.top = new FormAttachment(lblLengthInWRRoll2,-2,SWT.TOP);
		fd_textLengthInWRRoll2.left = new FormAttachment(textDiameterInWRRoll1, 0,SWT.LEFT);
		fd_textLengthInWRRoll2.right = new FormAttachment(100, -10);
		textLengthInWRRoll2.setLayoutData(fd_textLengthInWRRoll2);
		
		Label lblLengthInWRRoll3 = new Label(grpWorkRollwrShapeParameter, SWT.NONE);
		FormData fd_lblLengthInWRRoll3 = new FormData();
		fd_lblLengthInWRRoll3.top = new FormAttachment(lblLengthInWRRoll2, 10);
		fd_lblLengthInWRRoll3.left = new FormAttachment(lblDiameterInWRRoll1, 0,SWT.LEFT);
		lblLengthInWRRoll3.setLayoutData(fd_lblLengthInWRRoll3);
		lblLengthInWRRoll3.setText(ulObj.getUILabelValue(UILabel._3rd_length_in_WR_roll));
		
		textLengthInWRRoll3 = new Text(grpWorkRollwrShapeParameter, SWT.BORDER);
		FormData fd_textLengthInWRRoll3 = new FormData();
		fd_textLengthInWRRoll3.top = new FormAttachment(lblLengthInWRRoll3,-2,SWT.TOP);
		fd_textLengthInWRRoll3.left = new FormAttachment(textDiameterInWRRoll1, 0,SWT.LEFT);
		fd_textLengthInWRRoll3.right = new FormAttachment(100, -10);
		textLengthInWRRoll3.setLayoutData(fd_textLengthInWRRoll3);
		//
		// Group1 End
		//=============
		
		//=============
		// Group2 Strat
		//
		Group grpBackUpRollburShapeParameter = new Group(compositeDetail, SWT.NONE);
		grpBackUpRollburShapeParameter.setText(ulObj.getUILabelValue(UILabel.Back_Up_Roll_shape_parameter));
		grpBackUpRollburShapeParameter.setLayout(new FormLayout());
		FormData fd_grpBackUpRollburShapeParameter = new FormData();
		fd_grpBackUpRollburShapeParameter.top = new FormAttachment(grpWorkRollwrShapeParameter, 5);
		fd_grpBackUpRollburShapeParameter.left = new FormAttachment(grpWorkRollwrShapeParameter, 0,SWT.LEFT);
		fd_grpBackUpRollburShapeParameter.right = new FormAttachment(grpWorkRollwrShapeParameter, 0,SWT.RIGHT);
		fd_grpBackUpRollburShapeParameter.bottom = new FormAttachment(grpWorkRollwrShapeParameter, 210, SWT.BOTTOM);
		grpBackUpRollburShapeParameter.setLayoutData(fd_grpBackUpRollburShapeParameter);
		
		Label lblDiameterInBURRoll1 = new Label(grpBackUpRollburShapeParameter, SWT.NONE);
		FormData fd_lblDiameterInBURRoll1 = new FormData();
		fd_lblDiameterInBURRoll1.top = new FormAttachment(0, 10);
		fd_lblDiameterInBURRoll1.left = new FormAttachment(0, 10);
		lblDiameterInBURRoll1.setLayoutData(fd_lblDiameterInBURRoll1);
		lblDiameterInBURRoll1.setText(ulObj.getUILabelValue(UILabel._1st_diameter_in_BUR_roll));
		
		textDiameterInBURRoll1 = new Text(grpBackUpRollburShapeParameter, SWT.BORDER);
		FormData fd_textDiameterInBURRoll1 = new FormData();
		fd_textDiameterInBURRoll1.top = new FormAttachment(lblDiameterInBURRoll1,-2,SWT.TOP);
		fd_textDiameterInBURRoll1.left = new FormAttachment(lblDiameterInBURRoll1,30,SWT.RIGHT);
		fd_textDiameterInBURRoll1.right = new FormAttachment(100, -10);
		textDiameterInBURRoll1.setLayoutData(fd_textDiameterInBURRoll1);
		
		Label lblDiameterInBURRoll2 = new Label(grpBackUpRollburShapeParameter, SWT.NONE);
		FormData fd_lblDiameterInBURRoll2 = new FormData();
		fd_lblDiameterInBURRoll2.top = new FormAttachment(lblDiameterInBURRoll1, 10);
		fd_lblDiameterInBURRoll2.left = new FormAttachment(lblDiameterInBURRoll1, 0,SWT.LEFT);
		lblDiameterInBURRoll2.setLayoutData(fd_lblDiameterInBURRoll2);
		lblDiameterInBURRoll2.setText(ulObj.getUILabelValue(UILabel._2nd_diameter_in_BUR_roll));
		
		textDiameterInBURRoll2 = new Text(grpBackUpRollburShapeParameter, SWT.BORDER);
		FormData fd_textDiameterInBURRoll2 = new FormData();
		fd_textDiameterInBURRoll2.top = new FormAttachment(lblDiameterInBURRoll2,-2,SWT.TOP);
		fd_textDiameterInBURRoll2.left = new FormAttachment(textDiameterInBURRoll1, 0,SWT.LEFT);
		fd_textDiameterInBURRoll2.right = new FormAttachment(100, -10);
		textDiameterInBURRoll2.setLayoutData(fd_textDiameterInBURRoll2);
		
		Label lblDiameterInBURRoll3 = new Label(grpBackUpRollburShapeParameter, SWT.NONE);
		FormData fd_lblDiameterInBURRoll3 = new FormData();
		fd_lblDiameterInBURRoll3.top = new FormAttachment(lblDiameterInBURRoll2, 10);
		fd_lblDiameterInBURRoll3.left = new FormAttachment(lblDiameterInBURRoll1, 0,SWT.LEFT);
		lblDiameterInBURRoll3.setLayoutData(fd_lblDiameterInBURRoll3);
		lblDiameterInBURRoll3.setText(ulObj.getUILabelValue(UILabel._3rd_diameter_in_BUR_roll));
		
		textDiameterInBURRoll3 = new Text(grpBackUpRollburShapeParameter, SWT.BORDER);
		FormData fd_textDiameterInBURRoll3 = new FormData();
		fd_textDiameterInBURRoll3.top = new FormAttachment(lblDiameterInBURRoll3,-2,SWT.TOP);
		fd_textDiameterInBURRoll3.left = new FormAttachment(textDiameterInBURRoll1, 0,SWT.LEFT);
		fd_textDiameterInBURRoll3.right = new FormAttachment(100, -10);
		textDiameterInBURRoll3.setLayoutData(fd_textDiameterInBURRoll3);
		
		Label lblLengthInBURRoll1 = new Label(grpBackUpRollburShapeParameter, SWT.NONE);
		FormData fd_lblLengthInBURRoll1 = new FormData();
		fd_lblLengthInBURRoll1.top = new FormAttachment(lblDiameterInBURRoll3, 30);
		fd_lblLengthInBURRoll1.left = new FormAttachment(lblDiameterInBURRoll1, 0, SWT.LEFT);
		lblLengthInBURRoll1.setLayoutData(fd_lblLengthInBURRoll1);
		lblLengthInBURRoll1.setText(ulObj.getUILabelValue(UILabel._1st_length_in_BUR_roll));
		
		textLengthInBURRoll1 = new Text(grpBackUpRollburShapeParameter, SWT.BORDER);
		FormData fd_textLengthInBURRoll1 = new FormData();
		fd_textLengthInBURRoll1.top = new FormAttachment(lblLengthInBURRoll1,-2,SWT.TOP);
		fd_textLengthInBURRoll1.left = new FormAttachment(textDiameterInBURRoll1, 0,SWT.LEFT);
		fd_textLengthInBURRoll1.right = new FormAttachment(100, -10);
		textLengthInBURRoll1.setLayoutData(fd_textLengthInBURRoll1);
		
		Label lblLengthInBURRoll2 = new Label(grpBackUpRollburShapeParameter, SWT.NONE);
		FormData fd_lblLengthInBURRoll2 = new FormData();
		fd_lblLengthInBURRoll2.top = new FormAttachment(lblLengthInBURRoll1, 10);
		fd_lblLengthInBURRoll2.left = new FormAttachment(lblDiameterInBURRoll1, 0,SWT.LEFT);
		lblLengthInBURRoll2.setLayoutData(fd_lblLengthInBURRoll2);
		lblLengthInBURRoll2.setText(ulObj.getUILabelValue(UILabel._2nd_length_in_BUR_roll));
		
		textLengthInBURRoll2 = new Text(grpBackUpRollburShapeParameter, SWT.BORDER);
		FormData fd_textLengthInBURRoll2 = new FormData();
		fd_textLengthInBURRoll2.top = new FormAttachment(lblLengthInBURRoll2,-2,SWT.TOP);
		fd_textLengthInBURRoll2.left = new FormAttachment(textDiameterInBURRoll1, 0,SWT.LEFT);
		fd_textLengthInBURRoll2.right = new FormAttachment(100, -10);
		textLengthInBURRoll2.setLayoutData(fd_textLengthInBURRoll2);
		
		Label lblLengthInBURRoll3 = new Label(grpBackUpRollburShapeParameter, SWT.NONE);
		FormData fd_lblLengthInBURRoll3 = new FormData();
		fd_lblLengthInBURRoll3.top = new FormAttachment(lblLengthInBURRoll2, 10);
		fd_lblLengthInBURRoll3.left = new FormAttachment(lblDiameterInBURRoll1, 0,SWT.LEFT);
		lblLengthInBURRoll3.setLayoutData(fd_lblLengthInBURRoll3);
		lblLengthInBURRoll3.setText(ulObj.getUILabelValue(UILabel._3rd_length_in_BUR_roll));
		
		textLengthInBURRoll3 = new Text(grpBackUpRollburShapeParameter, SWT.BORDER);
		FormData fd_textLengthInBURRoll3 = new FormData();
		fd_textLengthInBURRoll3.top = new FormAttachment(lblLengthInBURRoll3,-2,SWT.TOP);
		fd_textLengthInBURRoll3.left = new FormAttachment(textDiameterInBURRoll1, 0,SWT.LEFT);
		fd_textLengthInBURRoll3.right = new FormAttachment(100, -10);
		textLengthInBURRoll3.setLayoutData(fd_textLengthInBURRoll3);
		//
		// Group2 End
		//=============
		
		//=============		
		// Group3 Start
		// 
		Group grpPlateShapeParameter = new Group(compositeDetail, SWT.NONE);
		grpPlateShapeParameter.setText(ulObj.getUILabelValue(UILabel.Plate_shape_parameter));
		grpPlateShapeParameter.setLayout(new FormLayout());
		FormData fd_grpPlateShapeParameter = new FormData();
		fd_grpPlateShapeParameter.top = new FormAttachment(grpBackUpRollburShapeParameter, 5);
		fd_grpPlateShapeParameter.left = new FormAttachment(grpWorkRollwrShapeParameter, 0,SWT.LEFT);
		fd_grpPlateShapeParameter.right = new FormAttachment(grpWorkRollwrShapeParameter, 0,SWT.RIGHT);
		fd_grpPlateShapeParameter.bottom = new FormAttachment(grpBackUpRollburShapeParameter, 180, SWT.BOTTOM);
		grpPlateShapeParameter.setLayoutData(fd_grpPlateShapeParameter);
		
		Label lblLengthOfPlate = new Label(grpPlateShapeParameter, SWT.NONE);
		FormData fd_lblLengthOfPlate = new FormData();
		fd_lblLengthOfPlate.top = new FormAttachment(0, 10);
		fd_lblLengthOfPlate.left = new FormAttachment(0, 10);
		lblLengthOfPlate.setLayoutData(fd_lblLengthOfPlate);
		lblLengthOfPlate.setText(ulObj.getUILabelValue(UILabel.length_of_plate));
		
		textLengthOfPlate = new Text(grpPlateShapeParameter, SWT.BORDER);
		FormData fd_textLengthOfPlate = new FormData();
		fd_textLengthOfPlate.top = new FormAttachment(lblLengthOfPlate,-2,SWT.TOP);
		fd_textLengthOfPlate.left = new FormAttachment(lblLengthOfPlate,80,SWT.RIGHT);
		fd_textLengthOfPlate.right = new FormAttachment(100, -10);
		textLengthOfPlate.setLayoutData(fd_textLengthOfPlate);
		
		Label lblWidthOfPlate = new Label(grpPlateShapeParameter, SWT.NONE);
		FormData fd_lblWidthOfPlate = new FormData();
		fd_lblWidthOfPlate.top = new FormAttachment(lblLengthOfPlate, 10);
		fd_lblWidthOfPlate.left = new FormAttachment(lblLengthOfPlate, 0,SWT.LEFT);
		lblWidthOfPlate.setLayoutData(fd_lblWidthOfPlate);
		lblWidthOfPlate.setText(ulObj.getUILabelValue(UILabel.width_of_plate));
		
		textWidthOfPlate = new Text(grpPlateShapeParameter, SWT.BORDER);
		FormData fd_textWidthOfPlate = new FormData();
		fd_textWidthOfPlate.top = new FormAttachment(lblWidthOfPlate,-2,SWT.TOP);
		fd_textWidthOfPlate.left = new FormAttachment(textLengthOfPlate, 0,SWT.LEFT);
		fd_textWidthOfPlate.right = new FormAttachment(100, -10);
		textWidthOfPlate.setLayoutData(fd_textWidthOfPlate);
		
		Label lblThicknessOfPlate = new Label(grpPlateShapeParameter, SWT.NONE);
		FormData fd_lblThicknessOfPlate = new FormData();
		fd_lblThicknessOfPlate.top = new FormAttachment(lblWidthOfPlate, 10);
		fd_lblThicknessOfPlate.left = new FormAttachment(lblLengthOfPlate, 0,SWT.LEFT);
		lblThicknessOfPlate.setLayoutData(fd_lblThicknessOfPlate);
		lblThicknessOfPlate.setText(ulObj.getUILabelValue(UILabel.thickness_of_plate));
		
		textThicknessOfPlate = new Text(grpPlateShapeParameter, SWT.BORDER);
		FormData fd_textThicknessOfPlate = new FormData();
		fd_textThicknessOfPlate.top = new FormAttachment(lblThicknessOfPlate,-2,SWT.TOP);
		fd_textThicknessOfPlate.left = new FormAttachment(textLengthOfPlate, 0,SWT.LEFT);
		fd_textThicknessOfPlate.right = new FormAttachment(100, -10);
		textThicknessOfPlate.setLayoutData(fd_textThicknessOfPlate);
		
		Label lblInitialPositionOfPlate = new Label(grpPlateShapeParameter, SWT.NONE);
		FormData fd_lblInitialPositionOfPlate = new FormData();
		fd_lblInitialPositionOfPlate.top = new FormAttachment(lblThicknessOfPlate, 30);
		fd_lblInitialPositionOfPlate.left = new FormAttachment(lblLengthOfPlate, 0, SWT.LEFT);
		lblInitialPositionOfPlate.setLayoutData(fd_lblInitialPositionOfPlate);
		lblInitialPositionOfPlate.setText(ulObj.getUILabelValue(UILabel.initial_position_of_plate));
		
		textInitialPositionOfPlate = new Text(grpPlateShapeParameter, SWT.BORDER);
		FormData fd_textInitialPositionOfPlate = new FormData();
		fd_textInitialPositionOfPlate.top = new FormAttachment(lblInitialPositionOfPlate,-2,SWT.TOP);
		fd_textInitialPositionOfPlate.left = new FormAttachment(textLengthOfPlate, 0,SWT.LEFT);
		fd_textInitialPositionOfPlate.right = new FormAttachment(100, -10);
		textInitialPositionOfPlate.setLayoutData(fd_textInitialPositionOfPlate);
		
		Label lblRollGap = new Label(grpPlateShapeParameter, SWT.NONE);
		FormData fd_lblRollGap = new FormData();
		fd_lblRollGap.top = new FormAttachment(lblInitialPositionOfPlate, 10);
		fd_lblRollGap.left = new FormAttachment(lblLengthOfPlate, 0,SWT.LEFT);
		lblRollGap.setLayoutData(fd_lblRollGap);
		lblRollGap.setText(ulObj.getUILabelValue(UILabel.roll_gap));
		
		textRollGap = new Text(grpPlateShapeParameter, SWT.BORDER);
		FormData fd_textRollGap = new FormData();
		fd_textRollGap.top = new FormAttachment(lblRollGap,-2,SWT.TOP);
		fd_textRollGap.left = new FormAttachment(textLengthOfPlate, 0,SWT.LEFT);
		fd_textRollGap.right = new FormAttachment(100, -10);
		textRollGap.setLayoutData(fd_textRollGap);
		
		//
		// Group3 End
		//=============
		
		//=============
		// Group4 End
		// 
		Group grpRollMeshParameter = new Group(compositeDetail, SWT.NONE);
		grpRollMeshParameter.setText(ulObj.getUILabelValue(UILabel.Roll_mesh_parameter));
		grpRollMeshParameter.setLayout(new FormLayout());
		FormData fd_grpRollMeshParameter = new FormData();
		fd_grpRollMeshParameter.top = new FormAttachment(grpWorkRollwrShapeParameter, 0, SWT.TOP);
		fd_grpRollMeshParameter.left = new FormAttachment(grpWorkRollwrShapeParameter, 10, SWT.RIGHT);
		fd_grpRollMeshParameter.right = new FormAttachment(grpWorkRollwrShapeParameter, 410,SWT.RIGHT);
		fd_grpRollMeshParameter.bottom = new FormAttachment(groupSTAND, 150, SWT.BOTTOM);
		grpRollMeshParameter.setLayoutData(fd_grpRollMeshParameter);
		
		Label lblArcLengthOfWR = new Label(grpRollMeshParameter, SWT.NONE);
		FormData fd_lblArcLengthOfWR = new FormData();
		fd_lblArcLengthOfWR.top = new FormAttachment(0, 10);
		fd_lblArcLengthOfWR.left = new FormAttachment(0, 10);
		lblArcLengthOfWR.setLayoutData(fd_lblArcLengthOfWR);
		lblArcLengthOfWR.setText(ulObj.getUILabelValue(UILabel.arc_length_of_WR));
		
		textArcLengthOfWR = new Text(grpRollMeshParameter, SWT.BORDER);
		FormData fd_textArcLengthOfWR = new FormData();
		fd_textArcLengthOfWR.top = new FormAttachment(lblArcLengthOfWR,-2,SWT.TOP);
		fd_textArcLengthOfWR.left = new FormAttachment(lblArcLengthOfWR,60,SWT.RIGHT);
		fd_textArcLengthOfWR.right = new FormAttachment(100, -10);
		textArcLengthOfWR.setLayoutData(fd_textArcLengthOfWR);

		Label lblArcLengthOfBUR = new Label(grpRollMeshParameter, SWT.NONE);
		FormData fd_lblArcLengthOfBUR = new FormData();
		fd_lblArcLengthOfBUR.top = new FormAttachment(lblArcLengthOfWR, 10);
		fd_lblArcLengthOfBUR.left = new FormAttachment(lblArcLengthOfWR, 0,SWT.LEFT);
		lblArcLengthOfBUR.setLayoutData(fd_lblArcLengthOfBUR);
		lblArcLengthOfBUR.setText(ulObj.getUILabelValue(UILabel.arc_length_of_BUR));

		textArcLengthOfBUR = new Text(grpRollMeshParameter, SWT.BORDER);
		FormData fd_textArcLengthOfBUR = new FormData();
		fd_textArcLengthOfBUR.top = new FormAttachment(lblArcLengthOfBUR,-2,SWT.TOP);
		fd_textArcLengthOfBUR.left = new FormAttachment(textArcLengthOfWR, 0,SWT.LEFT);
		fd_textArcLengthOfBUR.right = new FormAttachment(100, -10);
		textArcLengthOfBUR.setLayoutData(fd_textArcLengthOfBUR);

		//
		// Group4 End
		//=============
	}

	
	public void setFocus() {}
	
	public void init_TableColunm(){
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
