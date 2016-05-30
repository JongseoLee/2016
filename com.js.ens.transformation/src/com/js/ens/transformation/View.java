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

import com.js.ens.transformation.core.MainController;
import com.js.ens.transformation.core.Mediator;
import com.js.ens.transformation.core.TableColumnLabel;
import com.js.ens.transformation.core.UILabel;
import com.js.ens.transformation.customWidget.CustomButton;
import com.js.ens.transformation.customWidget.CustomTableViewer;
import com.js.ens.transformation.handler.HandlerButton;
import com.js.ens.transformation.handler.HandlerTableViewer;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Spinner;

public class View extends ViewPart {
	private Mediator med = Mediator.getInstance();
	private MainController MC = MainController.getInstance();
	
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

	private Text textElementNumberOfThicknessDirection;
	private Text textAspectRatioOfWidthDirection;
	private Text textAspectRatioOfLengthDirection;
	
	private Text textAngularVelocityOfWR;
	private Text textAngularVelocityOFBUR;
	private Text textPlateVelocity;
	private Text textInitialTemperatureOfPlate;
	
	private Text textYoungsModulus;
	private Text textThermalExpansionCoefficient;
	private Text textPoissonsRatio;
	private Text textMassDensity;
	
	private Text textSolvingTime;
	private Text textIncrementTime;
	
	
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
		med.setTableViewerSlabPlateInfo(tableViewerSlabPlateInfo);
		CustomTableViewer C_tableViewerSlabPlateInfo = new CustomTableViewer(Mediator.TABLEVIEWER_tableViewerSlabPlateInfo,med);
		med.setC_tableViewerSlabPlateInfo(C_tableViewerSlabPlateInfo);
		C_tableViewerSlabPlateInfo.setCustomWidget_tableViewerSlabPlateInfo();
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
		med.setTableViewerVariable(tableViewerVariable);
		CustomTableViewer C_tableViewerVariable = new CustomTableViewer(Mediator.TABLEVIEWER_tableViewerVariable,med);
		med.setC_tableViewerVariable(C_tableViewerVariable);
		C_tableViewerVariable.setCustomWidget_tableViewerVariable();
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
		med.setTableViewerPLog(tableViewerPLog);
		CustomTableViewer C_tableViewerPLog = new CustomTableViewer(Mediator.TABLEVIEWER_tableViewerPLog,med);
		med.setC_tableViewerPLog(C_tableViewerPLog);
		C_tableViewerVariable.setCustomWidget_tableViewerPLog();
		tablePLog = tableViewerPLog.getTable();
		tablePLog.setLinesVisible(true);
		tablePLog.setHeaderVisible(true);
		FormData fd_tablePLog = new FormData();
		fd_tablePLog.top = new FormAttachment(lblPLog, 10);
		fd_tablePLog.left = new FormAttachment(lblSlabAndPlate, 0, SWT.LEFT);
		fd_tablePLog.right = new FormAttachment(0,680);
		fd_tablePLog.bottom = new FormAttachment(100,-10);
		tablePLog.setLayoutData(fd_tablePLog);
		
		Button btnImportPLog = new Button(compositeTotal, SWT.NONE);
		med.setBtnImportPLog(btnImportPLog);
		CustomButton C_btnImportPLog = new CustomButton(Mediator.BUTTON_btnImportPLog,med);
		med.setC_btnImportPLog(C_btnImportPLog);
		C_btnImportPLog.setCustomWidget_btnImportPLog();
		FormData fd_btnImportPLog = new FormData();
		fd_btnImportPLog.bottom = new FormAttachment(tablePLog, 0, SWT.BOTTOM);
		fd_btnImportPLog.right = new FormAttachment(100, -10);
		btnImportPLog.setLayoutData(fd_btnImportPLog);
		btnImportPLog.setText(ulObj.getUILabelValue(UILabel.Import_P_Log));
		
		
		
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
		grpWorkRollwrShapeParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
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
		grpBackUpRollburShapeParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
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
		grpPlateShapeParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
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
		grpRollMeshParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpRollMeshParameter.setText(ulObj.getUILabelValue(UILabel.Roll_mesh_parameter));
		grpRollMeshParameter.setLayout(new FormLayout());
		FormData fd_grpRollMeshParameter = new FormData();
		fd_grpRollMeshParameter.top = new FormAttachment(grpWorkRollwrShapeParameter, 0, SWT.TOP);
		fd_grpRollMeshParameter.left = new FormAttachment(grpWorkRollwrShapeParameter, 10, SWT.RIGHT);
		fd_grpRollMeshParameter.right = new FormAttachment(grpWorkRollwrShapeParameter, 410,SWT.RIGHT);
		fd_grpRollMeshParameter.bottom = new FormAttachment(groupSTAND, 120, SWT.BOTTOM);
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

		//=============
		// Group6 Start
		//
		Group grpPlateMeshParameter = new Group(compositeDetail, SWT.NONE);
		grpPlateMeshParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpPlateMeshParameter.setText(ulObj.getUILabelValue(UILabel.Plate_mesh_parameter));
		grpPlateMeshParameter.setLayout(new FormLayout());
		FormData fd_grpPlateMeshParameter = new FormData();
		fd_grpPlateMeshParameter.top = new FormAttachment(grpRollMeshParameter, 5);
		fd_grpPlateMeshParameter.left = new FormAttachment(grpRollMeshParameter, 0, SWT.LEFT);
		fd_grpPlateMeshParameter.right = new FormAttachment(grpRollMeshParameter, 0,SWT.RIGHT);
		fd_grpPlateMeshParameter.bottom = new FormAttachment(grpRollMeshParameter, 110, SWT.BOTTOM);
		grpPlateMeshParameter.setLayoutData(fd_grpPlateMeshParameter);
		
		Label lblElementNumberOfThicknessDirection = new Label(grpPlateMeshParameter, SWT.NONE);
		FormData fd_lblElementNumberOfThicknessDirection = new FormData();
		fd_lblElementNumberOfThicknessDirection.top = new FormAttachment(0, 10);
		fd_lblElementNumberOfThicknessDirection.left = new FormAttachment(0, 10);
		lblElementNumberOfThicknessDirection.setLayoutData(fd_lblElementNumberOfThicknessDirection);
		lblElementNumberOfThicknessDirection.setText(ulObj.getUILabelValue(UILabel.Element_number_of_thickness_direction));
		
		textElementNumberOfThicknessDirection = new Text(grpPlateMeshParameter, SWT.BORDER);
		FormData fd_textElementNumberOfThicknessDirection = new FormData();
		fd_textElementNumberOfThicknessDirection.top = new FormAttachment(lblElementNumberOfThicknessDirection,-2,SWT.TOP);
		fd_textElementNumberOfThicknessDirection.left = new FormAttachment(lblElementNumberOfThicknessDirection,30,SWT.RIGHT);
		fd_textElementNumberOfThicknessDirection.right = new FormAttachment(100, -10);
		textElementNumberOfThicknessDirection.setLayoutData(fd_textElementNumberOfThicknessDirection);
		
		Label lblAspectRatioOfWidthDirection = new Label(grpPlateMeshParameter, SWT.NONE);
		FormData fd_lblAspectRatioOfWidthDirection = new FormData();
		fd_lblAspectRatioOfWidthDirection.top = new FormAttachment(lblElementNumberOfThicknessDirection, 10);
		fd_lblAspectRatioOfWidthDirection.left = new FormAttachment(lblElementNumberOfThicknessDirection, 0,SWT.LEFT);
		lblAspectRatioOfWidthDirection.setLayoutData(fd_lblAspectRatioOfWidthDirection);
		lblAspectRatioOfWidthDirection.setText(ulObj.getUILabelValue(UILabel.Aspect_ratio_of_width_direction));
		
		textAspectRatioOfWidthDirection = new Text(grpPlateMeshParameter, SWT.BORDER);
		FormData fd_textAspectRatioOfWidthDirection = new FormData();
		fd_textAspectRatioOfWidthDirection.top = new FormAttachment(lblAspectRatioOfWidthDirection,-2,SWT.TOP);
		fd_textAspectRatioOfWidthDirection.left = new FormAttachment(textElementNumberOfThicknessDirection, 0,SWT.LEFT);
		fd_textAspectRatioOfWidthDirection.right = new FormAttachment(100, -10);
		textAspectRatioOfWidthDirection.setLayoutData(fd_textAspectRatioOfWidthDirection);
		
		Label lblAspectRatioOfLengthDirection = new Label(grpPlateMeshParameter, SWT.NONE);
		FormData fd_lblAspectRatioOfLengthDirection = new FormData();
		fd_lblAspectRatioOfLengthDirection.top = new FormAttachment(lblAspectRatioOfWidthDirection, 10);
		fd_lblAspectRatioOfLengthDirection.left = new FormAttachment(lblElementNumberOfThicknessDirection, 0,SWT.LEFT);
		lblAspectRatioOfLengthDirection.setLayoutData(fd_lblAspectRatioOfLengthDirection);
		lblAspectRatioOfLengthDirection.setText(ulObj.getUILabelValue(UILabel.Aspect_ratio_of_length_direction));
		
		textAspectRatioOfLengthDirection = new Text(grpPlateMeshParameter, SWT.BORDER);
		FormData fd_textAspectRatioOfLengthDirection = new FormData();
		fd_textAspectRatioOfLengthDirection.top = new FormAttachment(lblAspectRatioOfLengthDirection,-2,SWT.TOP);
		fd_textAspectRatioOfLengthDirection.left = new FormAttachment(textElementNumberOfThicknessDirection, 0,SWT.LEFT);
		fd_textAspectRatioOfLengthDirection.right = new FormAttachment(100, -10);
		textAspectRatioOfLengthDirection.setLayoutData(fd_textAspectRatioOfLengthDirection);
		
		//
		// Group5 End
		//=============
		
		//=============
		// Group6 Start
		//
		Group grpRollPlateInfromation = new Group(compositeDetail, SWT.NONE);
		grpRollPlateInfromation.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpRollPlateInfromation.setText(ulObj.getUILabelValue(UILabel.Roll_Plate_information));
		grpRollPlateInfromation.setLayout(new FormLayout());
		FormData fd_grpRollPlateInfromation = new FormData();
		fd_grpRollPlateInfromation.top = new FormAttachment(grpPlateMeshParameter, 5);
		fd_grpRollPlateInfromation.left = new FormAttachment(grpRollMeshParameter, 0, SWT.LEFT);
		fd_grpRollPlateInfromation.right = new FormAttachment(grpRollMeshParameter, 0,SWT.RIGHT);
		fd_grpRollPlateInfromation.bottom = new FormAttachment(grpPlateMeshParameter, 130, SWT.BOTTOM);
		grpRollPlateInfromation.setLayoutData(fd_grpRollPlateInfromation);
		
		Label lblAngularVelocityOfWR = new Label(grpRollPlateInfromation, SWT.NONE);
		FormData fd_lblAngularVelocityOfWR = new FormData();
		fd_lblAngularVelocityOfWR.top = new FormAttachment(0, 10);
		fd_lblAngularVelocityOfWR.left = new FormAttachment(0, 10);
		lblAngularVelocityOfWR.setLayoutData(fd_lblAngularVelocityOfWR);
		lblAngularVelocityOfWR.setText(ulObj.getUILabelValue(UILabel.Angular_velocity_of_WR));
		
		textAngularVelocityOfWR = new Text(grpRollPlateInfromation, SWT.BORDER);
		FormData fd_textAngularVelocityOfWR = new FormData();
		fd_textAngularVelocityOfWR.top = new FormAttachment(lblAngularVelocityOfWR,-2,SWT.TOP);
		fd_textAngularVelocityOfWR.left = new FormAttachment(lblAngularVelocityOfWR,30,SWT.RIGHT);
		fd_textAngularVelocityOfWR.right = new FormAttachment(100, -10);
		textAngularVelocityOfWR.setLayoutData(fd_textAngularVelocityOfWR);
		
		Label lblAngularVelocityOfBUR = new Label(grpRollPlateInfromation, SWT.NONE);
		FormData fd_lblAngularVelocityOfBUR = new FormData();
		fd_lblAngularVelocityOfBUR.top = new FormAttachment(lblAngularVelocityOfWR, 10);
		fd_lblAngularVelocityOfBUR.left = new FormAttachment(lblAngularVelocityOfWR, 0,SWT.LEFT);
		lblAngularVelocityOfBUR.setLayoutData(fd_lblAngularVelocityOfBUR);
		lblAngularVelocityOfBUR.setText(ulObj.getUILabelValue(UILabel.Angular_velocity_of_BUR));
		
		textAngularVelocityOFBUR = new Text(grpRollPlateInfromation, SWT.BORDER);
		FormData fd_textAngularVelocityOFBUR = new FormData();
		fd_textAngularVelocityOFBUR.top = new FormAttachment(lblAngularVelocityOfBUR,-2,SWT.TOP);
		fd_textAngularVelocityOFBUR.left = new FormAttachment(textAngularVelocityOfWR, 0,SWT.LEFT);
		fd_textAngularVelocityOFBUR.right = new FormAttachment(100, -10);
		textAngularVelocityOFBUR.setLayoutData(fd_textAngularVelocityOFBUR);
		
		Label lblPlateVelocity = new Label(grpRollPlateInfromation, SWT.NONE);
		FormData fd_lblPlateVelocity = new FormData();
		fd_lblPlateVelocity.top = new FormAttachment(lblAngularVelocityOfBUR, 10);
		fd_lblPlateVelocity.left = new FormAttachment(lblAngularVelocityOfWR, 0,SWT.LEFT);
		lblPlateVelocity.setLayoutData(fd_lblPlateVelocity);
		lblPlateVelocity.setText(ulObj.getUILabelValue(UILabel.Plate_velocity));
		
		textPlateVelocity = new Text(grpRollPlateInfromation, SWT.BORDER);
		FormData fd_textPlateVelocity = new FormData();
		fd_textPlateVelocity.top = new FormAttachment(lblPlateVelocity,-2,SWT.TOP);
		fd_textPlateVelocity.left = new FormAttachment(textAngularVelocityOfWR, 0,SWT.LEFT);
		fd_textPlateVelocity.right = new FormAttachment(100, -10);
		textPlateVelocity.setLayoutData(fd_textPlateVelocity);
		
		Label lblInitialTemperatureOfPlate = new Label(grpRollPlateInfromation, SWT.NONE);
		FormData fd_lblInitialTemperatureOfPlate = new FormData();
		fd_lblInitialTemperatureOfPlate.top = new FormAttachment(lblPlateVelocity, 10);
		fd_lblInitialTemperatureOfPlate.left = new FormAttachment(lblAngularVelocityOfWR, 0,SWT.LEFT);
		lblInitialTemperatureOfPlate.setLayoutData(fd_lblInitialTemperatureOfPlate);
		lblInitialTemperatureOfPlate.setText(ulObj.getUILabelValue(UILabel.initial_temperature_of_plate));
		
		textInitialTemperatureOfPlate = new Text(grpRollPlateInfromation, SWT.BORDER);
		FormData fd_textInitialTemperatureOfPlate = new FormData();
		fd_textInitialTemperatureOfPlate.top = new FormAttachment(lblInitialTemperatureOfPlate,-2,SWT.TOP);
		fd_textInitialTemperatureOfPlate.left = new FormAttachment(textAngularVelocityOfWR, 0,SWT.LEFT);
		fd_textInitialTemperatureOfPlate.right = new FormAttachment(100, -10);
		textInitialTemperatureOfPlate.setLayoutData(fd_textInitialTemperatureOfPlate);
		//
		// Group6 End
		//=============
		
		//=============
		// Group7 Start
		//
		Group grpMaterialParameter = new Group(compositeDetail, SWT.NONE);
		grpMaterialParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpMaterialParameter.setText(ulObj.getUILabelValue(UILabel.Material_parameter));
		grpMaterialParameter.setLayout(new FormLayout());
		FormData fd_grpMaterialParameter = new FormData();
		fd_grpMaterialParameter.top = new FormAttachment(grpWorkRollwrShapeParameter, 0, SWT.TOP);
		fd_grpMaterialParameter.left = new FormAttachment(grpRollMeshParameter, 10, SWT.RIGHT);
		fd_grpMaterialParameter.right = new FormAttachment(grpRollMeshParameter, 410,SWT.RIGHT);
		fd_grpMaterialParameter.bottom = new FormAttachment(groupSTAND, 290, SWT.BOTTOM);
		grpMaterialParameter.setLayoutData(fd_grpMaterialParameter);

		Label lblYoungsModulus = new Label(grpMaterialParameter, SWT.NONE);
		FormData fd_lblYoungsModulus = new FormData();
		fd_lblYoungsModulus.top = new FormAttachment(0, 10);
		fd_lblYoungsModulus.left = new FormAttachment(0, 10);
		lblYoungsModulus.setLayoutData(fd_lblYoungsModulus);
		lblYoungsModulus.setText(ulObj.getUILabelValue(UILabel.Youngs_Modulus));
		
		Composite compositeYoungsModulus = new Composite(grpMaterialParameter, SWT.NONE);
		compositeYoungsModulus.setLayout(new FormLayout());
		FormData fd_compositeYoungsModulus = new FormData();
		fd_compositeYoungsModulus.top = new FormAttachment(lblYoungsModulus, 5);
		fd_compositeYoungsModulus.left = new FormAttachment(lblYoungsModulus, 10, SWT.LEFT);
		fd_compositeYoungsModulus.right = new FormAttachment(lblYoungsModulus,100,SWT.RIGHT);
		fd_compositeYoungsModulus.bottom = new FormAttachment(lblYoungsModulus,20,SWT.BOTTOM);
		compositeYoungsModulus.setLayoutData(fd_compositeYoungsModulus);
		
		Button btnConstant1 = new Button(compositeYoungsModulus, SWT.RADIO);
		FormData fd_btnConstant = new FormData();
		fd_btnConstant.top = new FormAttachment(0, 0);
		fd_btnConstant.left = new FormAttachment(0, 0);
		btnConstant1.setLayoutData(fd_btnConstant);
		btnConstant1.setText(ulObj.getUILabelValue(UILabel.Constatnt));
		
		Button btnTable1 = new Button(compositeYoungsModulus, SWT.RADIO);
		FormData fd_btnTable = new FormData();
		fd_btnTable.top = new FormAttachment(0, 0);
		fd_btnTable.left = new FormAttachment(btnConstant1,10,SWT.RIGHT);
		btnTable1.setLayoutData(fd_btnTable);
		btnTable1.setText(ulObj.getUILabelValue(UILabel.Table));
		
		textYoungsModulus = new Text(grpMaterialParameter, SWT.BORDER);
		FormData fd_textYoungsModulus = new FormData();
		fd_textYoungsModulus.top = new FormAttachment(compositeYoungsModulus, 10);
		fd_textYoungsModulus.left = new FormAttachment(compositeYoungsModulus, 0, SWT.LEFT);
		fd_textYoungsModulus.right = new FormAttachment(100,-40);
		textYoungsModulus.setLayoutData(fd_textYoungsModulus);
		
		Button btnExplorerYoungsModulus = new Button(grpMaterialParameter, SWT.NONE);
		FormData fd_btnExplorerYoungsModulus = new FormData();
		fd_btnExplorerYoungsModulus.top = new FormAttachment(textYoungsModulus, -2, SWT.TOP);
		fd_btnExplorerYoungsModulus.left = new FormAttachment(textYoungsModulus, 5);
		btnExplorerYoungsModulus.setLayoutData(fd_btnExplorerYoungsModulus);
		btnExplorerYoungsModulus.setText("...");
		
		Label lblThermalExpansionCoefficient = new Label(grpMaterialParameter, SWT.NONE);
		FormData fd_lblThermalExpansionCoefficient = new FormData();
		fd_lblThermalExpansionCoefficient.top = new FormAttachment(textYoungsModulus, 10);
		fd_lblThermalExpansionCoefficient.left = new FormAttachment(lblYoungsModulus, 0, SWT.LEFT);
		lblThermalExpansionCoefficient.setLayoutData(fd_lblThermalExpansionCoefficient);
		lblThermalExpansionCoefficient.setText(ulObj.getUILabelValue(UILabel.Thermal_Expansion_Coefficient));
		
		Composite compositeThermalExpansionCoefficient = new Composite(grpMaterialParameter, SWT.NONE);
		compositeThermalExpansionCoefficient.setLayout(new FormLayout());
		FormData fd_compositeThermalExpansionCoefficient = new FormData();
		fd_compositeThermalExpansionCoefficient.top = new FormAttachment(lblThermalExpansionCoefficient, 5);
		fd_compositeThermalExpansionCoefficient.left = new FormAttachment(lblThermalExpansionCoefficient, 10, SWT.LEFT);
		fd_compositeThermalExpansionCoefficient.right = new FormAttachment(lblThermalExpansionCoefficient,100,SWT.RIGHT);
		fd_compositeThermalExpansionCoefficient.bottom = new FormAttachment(lblThermalExpansionCoefficient,20,SWT.BOTTOM);
		compositeThermalExpansionCoefficient.setLayoutData(fd_compositeThermalExpansionCoefficient);
		
		Button btnConstant2 = new Button(compositeThermalExpansionCoefficient, SWT.RADIO);
		FormData fd_btnConstant2 = new FormData();
		fd_btnConstant2.top = new FormAttachment(0, 0);
		fd_btnConstant2.left = new FormAttachment(0, 0);
		btnConstant2.setLayoutData(fd_btnConstant2);
		btnConstant2.setText(ulObj.getUILabelValue(UILabel.Constatnt));
		
		Button btnTable2 = new Button(compositeThermalExpansionCoefficient, SWT.RADIO);
		FormData fd_btnTable2 = new FormData();
		fd_btnTable2.top = new FormAttachment(0, 0);
		fd_btnTable2.left = new FormAttachment(btnConstant2,10,SWT.RIGHT);
		btnTable2.setLayoutData(fd_btnTable2);
		btnTable2.setText(ulObj.getUILabelValue(UILabel.Table));
		
		textThermalExpansionCoefficient = new Text(grpMaterialParameter, SWT.BORDER);
		FormData fd_textThermalExpansionCoefficient = new FormData();
		fd_textThermalExpansionCoefficient.top = new FormAttachment(compositeThermalExpansionCoefficient, 10);
		fd_textThermalExpansionCoefficient.left = new FormAttachment(compositeThermalExpansionCoefficient, 0, SWT.LEFT);
		fd_textThermalExpansionCoefficient.right = new FormAttachment(100,-40);
		textThermalExpansionCoefficient.setLayoutData(fd_textThermalExpansionCoefficient);
		
		Button btnExplorerThermalExpansionCoefficient = new Button(grpMaterialParameter, SWT.NONE);
		FormData fd_btnExplorerThermalExpansionCoefficient = new FormData();
		fd_btnExplorerThermalExpansionCoefficient.top = new FormAttachment(textThermalExpansionCoefficient, -2, SWT.TOP);
		fd_btnExplorerThermalExpansionCoefficient.left = new FormAttachment(textThermalExpansionCoefficient, 5);
		btnExplorerThermalExpansionCoefficient.setLayoutData(fd_btnExplorerThermalExpansionCoefficient);
		btnExplorerThermalExpansionCoefficient.setText("...");
		
		Label lblPoissonsRatio = new Label(grpMaterialParameter, SWT.NONE);
		FormData fd_lblPoissonsRatio = new FormData();
		fd_lblPoissonsRatio.top = new FormAttachment(textThermalExpansionCoefficient, 10);
		fd_lblPoissonsRatio.left = new FormAttachment(lblYoungsModulus, 0, SWT.LEFT);
		lblPoissonsRatio.setLayoutData(fd_lblPoissonsRatio);
		lblPoissonsRatio.setText(ulObj.getUILabelValue(UILabel.Poissons_Ratio));
		
		Composite compositePoissonsRatio = new Composite(grpMaterialParameter, SWT.NONE);
		compositePoissonsRatio.setLayout(new FormLayout());
		FormData fd_compositePoissonsRatio = new FormData();
		fd_compositePoissonsRatio.top = new FormAttachment(lblPoissonsRatio, 5);
		fd_compositePoissonsRatio.left = new FormAttachment(lblPoissonsRatio, 10, SWT.LEFT);
		fd_compositePoissonsRatio.right = new FormAttachment(lblPoissonsRatio,100,SWT.RIGHT);
		fd_compositePoissonsRatio.bottom = new FormAttachment(lblPoissonsRatio,20,SWT.BOTTOM);
		compositePoissonsRatio.setLayoutData(fd_compositePoissonsRatio);
		
		Button btnConstant3 = new Button(compositePoissonsRatio, SWT.RADIO);
		FormData fd_btnConstant3 = new FormData();
		fd_btnConstant3.top = new FormAttachment(0, 0);
		fd_btnConstant3.left = new FormAttachment(0, 0);
		btnConstant3.setLayoutData(fd_btnConstant3);
		btnConstant3.setText(ulObj.getUILabelValue(UILabel.Constatnt));
		
		Button btnTable3 = new Button(compositePoissonsRatio, SWT.RADIO);
		FormData fd_btnTable3 = new FormData();
		fd_btnTable3.top = new FormAttachment(0, 0);
		fd_btnTable3.left = new FormAttachment(btnConstant3,10,SWT.RIGHT);
		btnTable3.setLayoutData(fd_btnTable3);
		btnTable3.setText(ulObj.getUILabelValue(UILabel.Table));
		
		textPoissonsRatio = new Text(grpMaterialParameter, SWT.BORDER);
		FormData fd_textPoissonsRatio = new FormData();
		fd_textPoissonsRatio.top = new FormAttachment(compositePoissonsRatio, 10);
		fd_textPoissonsRatio.left = new FormAttachment(compositePoissonsRatio, 0, SWT.LEFT);
		fd_textPoissonsRatio.right = new FormAttachment(100,-40);
		textPoissonsRatio.setLayoutData(fd_textPoissonsRatio);
		
		Button btnExplorerPoissonsRatio = new Button(grpMaterialParameter, SWT.NONE);
		FormData fd_btnExplorerPoissonsRatio = new FormData();
		fd_btnExplorerPoissonsRatio.top = new FormAttachment(textPoissonsRatio, -2, SWT.TOP);
		fd_btnExplorerPoissonsRatio.left = new FormAttachment(textPoissonsRatio, 5);
		btnExplorerPoissonsRatio.setLayoutData(fd_btnExplorerPoissonsRatio);
		btnExplorerPoissonsRatio.setText("...");
		
		Label lblMassDensity = new Label(grpMaterialParameter, SWT.NONE);
		FormData fd_lblMassDensity = new FormData();
		fd_lblMassDensity.top = new FormAttachment(textPoissonsRatio, 10);
		fd_lblMassDensity.left = new FormAttachment(lblYoungsModulus, 0, SWT.LEFT);
		lblMassDensity.setLayoutData(fd_lblMassDensity);
		lblMassDensity.setText(ulObj.getUILabelValue(UILabel.Mass_Density));
		
		textMassDensity = new Text(grpMaterialParameter, SWT.BORDER);
		FormData fd_textMassDensity = new FormData();
		fd_textMassDensity.top = new FormAttachment(lblMassDensity, -2,SWT.TOP);
		fd_textMassDensity.left = new FormAttachment(lblMassDensity, 50, SWT.RIGHT);
		fd_textMassDensity.right = new FormAttachment(100,-10);
		textMassDensity.setLayoutData(fd_textMassDensity);
		//
		// Group7 End
		//=============
		
		/* */
		//=============
		// Group8 Start
		//
		Group grpSolvingOption = new Group(compositeDetail, SWT.NONE);
		grpSolvingOption.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpSolvingOption.setText(ulObj.getUILabelValue(UILabel.Solving_Option));
		grpSolvingOption.setLayout(new FormLayout());
		FormData fd_grpSolvingOption = new FormData();
		fd_grpSolvingOption.top = new FormAttachment(grpMaterialParameter, 5);
		fd_grpSolvingOption.left = new FormAttachment(grpMaterialParameter, 0, SWT.LEFT);
		fd_grpSolvingOption.right = new FormAttachment(grpMaterialParameter, 0,SWT.RIGHT);
		fd_grpSolvingOption.bottom = new FormAttachment(grpMaterialParameter, 190, SWT.BOTTOM);
		grpSolvingOption.setLayoutData(fd_grpSolvingOption);
		
		Label lblSolvingTime = new Label(grpSolvingOption, SWT.NONE);
		FormData fd_lblSolvingTime = new FormData();
		fd_lblSolvingTime.top = new FormAttachment(0, 10);
		fd_lblSolvingTime.left = new FormAttachment(0, 10);
		lblSolvingTime.setLayoutData(fd_lblSolvingTime);
		lblSolvingTime.setText(ulObj.getUILabelValue(UILabel.Solving_time));
		
		textSolvingTime = new Text(grpSolvingOption, SWT.BORDER);
		FormData fd_textSolvingTime = new FormData();
		fd_textSolvingTime.top = new FormAttachment(lblSolvingTime, -2,SWT.TOP);
		fd_textSolvingTime.left = new FormAttachment(lblSolvingTime, 80, SWT.RIGHT);
		fd_textSolvingTime.right = new FormAttachment(100,-10);
		textSolvingTime.setLayoutData(fd_textSolvingTime);
		
		Label lblIncrementTime = new Label(grpSolvingOption, SWT.NONE);
		FormData fd_lblIncrementTime = new FormData();
		fd_lblIncrementTime.top = new FormAttachment(lblSolvingTime, 10);
		fd_lblIncrementTime.left = new FormAttachment(lblSolvingTime, 0,SWT.LEFT);
		lblIncrementTime.setLayoutData(fd_lblIncrementTime);
		lblIncrementTime.setText(ulObj.getUILabelValue(UILabel.Increment_time));
		
		textIncrementTime = new Text(grpSolvingOption, SWT.BORDER);
		FormData fd_textIncrementTime = new FormData();
		fd_textIncrementTime.top = new FormAttachment(lblIncrementTime, -2,SWT.TOP);
		fd_textIncrementTime.left = new FormAttachment(textSolvingTime, 0, SWT.LEFT);
		fd_textIncrementTime.right = new FormAttachment(100,-10);
		textIncrementTime.setLayoutData(fd_textIncrementTime);
		
		Label lblParallelDDM = new Label(grpSolvingOption, SWT.NONE);
		FormData fd_lblParallelDDM = new FormData();
		fd_lblParallelDDM.top = new FormAttachment(lblIncrementTime, 10);
		fd_lblParallelDDM.left = new FormAttachment(lblSolvingTime, 0,SWT.LEFT);
		lblParallelDDM.setLayoutData(fd_lblParallelDDM);
		lblParallelDDM.setText(ulObj.getUILabelValue(UILabel.Parallel_DDM));
		
		Button btnUseParallelDDM = new Button(grpSolvingOption, SWT.CHECK);
		FormData fd_btnParallelDDM = new FormData();
		fd_btnParallelDDM.top = new FormAttachment(lblParallelDDM, 0, SWT.TOP);
		fd_btnParallelDDM.left = new FormAttachment(textSolvingTime, 0, SWT.LEFT);
		btnUseParallelDDM.setLayoutData(fd_btnParallelDDM);
		btnUseParallelDDM.setText(ulObj.getUILabelValue(UILabel.use));
		
		Label lblDomain = new Label(grpSolvingOption, SWT.NONE);
		FormData fd_lblDomain = new FormData();
		fd_lblDomain.top = new FormAttachment(lblParallelDDM, 10);
		fd_lblDomain.left = new FormAttachment(lblSolvingTime, 0,SWT.LEFT);
		lblDomain.setLayoutData(fd_lblDomain);
		lblDomain.setText(ulObj.getUILabelValue(UILabel.Domain));
		
		Spinner spinnerDomain = new Spinner(grpSolvingOption, SWT.BORDER);
		FormData fd_spinnerDomain = new FormData();
		fd_spinnerDomain.top = new FormAttachment(lblDomain, -2,SWT.TOP);
		fd_spinnerDomain.left = new FormAttachment(textSolvingTime, 0, SWT.LEFT);
		fd_spinnerDomain.right = new FormAttachment(100,-10);
		spinnerDomain.setLayoutData(fd_spinnerDomain);
		spinnerDomain.setEnabled(false);
		
		Label lblParallelMultiThread = new Label(grpSolvingOption, SWT.NONE);
		FormData fd_lblParallelMultiThread = new FormData();
		fd_lblParallelMultiThread.top = new FormAttachment(lblDomain, 10);
		fd_lblParallelMultiThread.left = new FormAttachment(lblSolvingTime, 0,SWT.LEFT);
		lblParallelMultiThread.setLayoutData(fd_lblParallelMultiThread);
		lblParallelMultiThread.setText(ulObj.getUILabelValue(UILabel.Parallel_Multi_Thread));
		
		Button btnUseParallelMultiThread = new Button(grpSolvingOption, SWT.CHECK);
		FormData fd_btnParallelMultiThread = new FormData();
		fd_btnParallelMultiThread.top = new FormAttachment(lblParallelMultiThread, 0, SWT.TOP);
		fd_btnParallelMultiThread.left = new FormAttachment(textSolvingTime, 0, SWT.LEFT);
		btnUseParallelMultiThread.setLayoutData(fd_btnParallelMultiThread);
		btnUseParallelMultiThread.setText(ulObj.getUILabelValue(UILabel.use));
		
		Label lblThread = new Label(grpSolvingOption, SWT.NONE);
		FormData fd_lblThread = new FormData();
		fd_lblThread.top = new FormAttachment(lblParallelMultiThread, 10);
		fd_lblThread.left = new FormAttachment(lblSolvingTime, 0,SWT.LEFT);
		lblThread.setLayoutData(fd_lblThread);
		lblThread.setText(ulObj.getUILabelValue(UILabel.Thread));
		
		Spinner spinnerThread = new Spinner(grpSolvingOption, SWT.BORDER);
		FormData fd_spinnerThread = new FormData();
		fd_spinnerThread.top = new FormAttachment(lblThread, -2,SWT.TOP);
		fd_spinnerThread.left = new FormAttachment(textSolvingTime, 0, SWT.LEFT);
		fd_spinnerThread.right = new FormAttachment(100,-10);
		spinnerThread.setLayoutData(fd_spinnerThread);
		spinnerThread.setEnabled(false);
		
		
		//
		// Group8 End
		//=============
		// */
		Button btnApply = new Button(parent, SWT.NONE);
		med.setBtnApply(btnApply);
		CustomButton C_btnApply = new CustomButton(Mediator.BUTTON_btnApply,med);
		med.setC_btnApply(C_btnApply);
		C_btnApply.setCustomWidget_btnApply();
		FormData fd_btnApply = new FormData();
		fd_btnApply.top = new FormAttachment(tabFolder, 5);
		fd_btnApply.left = new FormAttachment(tabFolder,-110,SWT.RIGHT);
		fd_btnApply.right = new FormAttachment(tabFolder, -10, SWT.RIGHT);
		btnApply.setLayoutData(fd_btnApply);
		btnApply.setText(ulObj.getUILabelValue(UILabel.Apply));
		
		
		
		init_TableColunm();
		init_ActionComponent();
		
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
	
	public void init_ActionComponent(){
		//Button
		HandlerButton handlerButton = new HandlerButton();
		med.getBtnImportPLog().addListener(SWT.Selection, handlerButton);
		med.getBtnApply().addListener(SWT.Selection, handlerButton);
		//TableViewer
		HandlerTableViewer handlerTableViewer = new HandlerTableViewer();
		med.getTableViewerSlabPlateInfo().addSelectionChangedListener(handlerTableViewer);
		med.getTableViewerVariable().addSelectionChangedListener(handlerTableViewer);
		med.getTableViewerPLog().addSelectionChangedListener(handlerTableViewer);
	}
}
