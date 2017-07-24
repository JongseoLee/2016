package com.msc.adams.automation.core;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import com.msc.adams.automation.customWidget.ICommand;

public class Mediator {
	private static Mediator instance = new Mediator();
	public static Mediator getInstance(){
		return instance;
	}

	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	// 
	// Common
	//
	private Composite parentView;
	
	private Composite compositeTop;
	public static String COMPOSITE_compositeTop = "compositeTop"; 
	
	private Label lblMyprojName;
	public static String LABEL_lblMyprojName = "lblMyprojName";
	
	private Label lblPath;
	public static String LABEL_lblPath = "lblPath";
	
	private Button btnPrevious;
	public ICommand C_btnPrevious;
	public static String BUTTON_btnPrevious = "btnPrevious";
	
	private Label lblStep1;
	public ICommand C_lblStep1;
	public static String LABEL_lblStep1 = "lblStep1";
	
	private Label lblStep2;
	public ICommand C_lblStep2;
	public static String LABEL_lblStep2 = "lblStep2";
	
	private Label lblStep3;
	public ICommand C_lblStep3;
	public static String LABEL_lblStep3 = "lblStep3";
	
	private Label lblStep4;
	public ICommand C_lblStep4;
	public static String LABEL_lblStep4 = "lblStep4";
	
	private Label lblStep5;
	public ICommand C_lblStep5;
	public static String LABEL_lblStep5 = "lblStep5";
	
	private Button btnNext;
	public ICommand C_btnNext;
	public static String BUTTON_btnNext = "btnNext";
	
	private Button btnEditAllData;
	public ICommand C_btnEditAllData;
	public static String BUTTON_btnEditAllData = "btnEditAllData";
	
	private Button btnSaveAllData;
	public ICommand C_btnSaveAllData;
	public static String BUTTON_btnSaveAllData = "btnSaveAllData";
	
	private Button btnReloadDb;
	public ICommand C_btnReloadDb;
	public static String BUTTON_btnReloadDb = "btnReloadDb";
	
	private Label lblBladeValue;
	public ICommand C_lblBladeValue;
	public static String LABEL_lblBladeValue = "lblBladeValue";
	
	private Label lblLinkageValue;
	public ICommand C_lblLinkageValue;
	public static String LABEL_lblLinkageValue = "lblLinkageValue";
	
	private Label lblWindshieldValue;
	public ICommand C_lblWindshieldValue;
	public static String LABEL_lblWindshieldValue = "lblWindshieldValue";
	//
	//
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//
	// Bottom
	//
	private Composite compositeBottom;
	public static String COMPOSITE_compositeBottom = "compositeBottom";
	
	private Text textMessageWindow;
	public ICommand C_textMessageWindow;
	public static String TEXT_textMessageWindow = "textMessageWindow";
	
	private StackLayout stackLayout;
	//
	//
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//
	// Bottom - step1
	//
	private Composite compositeStep1;
	public static String COMPOSITE_compositeStep1 = "compositeStep1";
	
	private Combo comboBladeType;
	public ICommand C_comboBladeType;
	public static String COMBO_comboBladeType = "comboBladeType";
	
	private Label lblImageBlade;
	public static String LABEL_lblImageBlade = "lblImageBlade";
	
	private ListViewer listViewerBladeDatabase;
	public ICommand C_listViewerBladeDatabase;
	public static String LISTVIEWER_listViewerBladeDatabase = "listViewerBladeDatabase";
	
	private List listBladeDatabase;
	public ICommand C_listBladeDatabase;
	public ICommand C_doubleClick_listBladeDatabase;
	public static String LIST_listBladeDatabase = "listBladeDatabase";
	
	private Combo comboLinkageType;
	public ICommand C_comboLinkageType;
	public static String COMBO_comboLinkageType = "comboLinkageType";
	
	private Label lblImageLinkage;
	public ICommand C_lblImageLinkage;
	public static String LABEL_lblImageLinkage = "lblImageLinkage";
	
	private ListViewer listViewerLinkageDatabase;
	public ICommand C_listViewerLinkageDatabase;
	public static String LISTVIEWER_listViewerLinkageDatabase = "listViewerLinkageDatabase";
	
	private List listLinkageDatabase;
	public ICommand C_listLinkageDatabase;
	public ICommand C_doubleClick_listLinkageDatabase;
	public static String LIST_listLinkageDatabase = "listLinkageDatabase";
	
	private Combo comboWindshieldType;
	public ICommand C_comboWindshieldType;
	public static String COMBO_comboWindshieldType = "comboWindshieldType";
	
	private Text textRadiusValue;
	public ICommand C_textRadiusValue;
	public static String TEXT_textRadiusValue = "textRadiusValue";
	
	private Text textXValue;
	public ICommand C_textXValue;
	public static String TEXT_textXValue = "textXValue";
	
	private Text textYValue;
	public ICommand C_textYValue;
	public static String TEXT_textYValue = "textYValue";
	
	private Text textZValue;
	public ICommand C_textZValue;
	public static String TEXT_textZValue = "textZValue";
	
	private ListViewer listViewerWindShieldDatabase;
	public ICommand C_listViewerWindShieldDatabase;
	public static String LISTVIEWER_listViewerWindShieldDatabase = "listViewerWindShieldDatabase";
	
	private List listWindShieldDatabase;
	public ICommand C_listWindShieldDatabase;
	public static String LIST_listWindShieldDatabase = "listWindShieldDatabase";
	//
	//
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//
	// Bottom - step2
	//
	private Composite compositeStep2;
	public static String COMPOSITE_compositeStep2 = "compositeStep2";
	
	private Label lblImagePart;
	public static String LABEL_lblImagePart = "lblImagePart";
	
	private Button btnNone;
	public ICommand C_btnNone;
	public static String BUTTON_btnNone = "btnNone";

	private ListViewer listViewerPart;
	public ICommand C_listViewerPart;
	public static String LISTVIEWER_listViewerPart = "listViewerPart";
	
	private List listPart;
	public ICommand C_listPart;
	public static String LIST_listPart = "listPart";
	
	private Button btnAdd;
	public ICommand C_btnAdd;
	public static String BUTTON_btnAdd = "btnAdd";
	
	private Button btnDel;
	public ICommand C_btnDel;
	public static String BUTTON_btnDel = "btnDel";
	
	private Table tableSwappingPart;
	public ICommand C_tableSwappingPart;
	public static String TABLE_tableSwappingPart = "tableSwappingPart";
	
	
	//
	//
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//
	// Bottom - step3
	//
	private Composite compositeStep3;
	public static String COMPOSITE_compositeStep3 = "compositeStep3";
	
	private ListViewer listViewerSwappingPart;
	public ICommand C_listViewerSwappingPart;
	public static String LISTVIEWER_listViewerSwappingPart = "listViewerSwappingPart";
	
	private List listSwappingPart;
	public ICommand C_listSwappingPart;
	public static String LIST_listSwappingPart = "listSwappingPart";
	
	private Group grpPartInformation;
	public static String GROUP_grpPartInformation = "grpPartInformation";
	
	private Label lblPartNameValue;
	public static String LABEL_lblPartNameValue = "lblPartNameValue";
	
	private Label lblSwappingTypeValue;
	public static String LABEL_lblSwappingTypeValue = "lblSwappingTypeValue";
	
	private Text textBulkFilePath;
	public ICommand C_textBulkFilePath;
	public static String TEXT_textBulkFilePath = "textBulkFilePath";
	
	private Text textInputdeckPath;
	public ICommand C_textInputdeckPath;
	public static String TEXT_textInputdeckPath = "textInputdeckPath";
	
	private Text textMnfFilePath;
	public ICommand C_textMnfFilePath;
	public static String TEXT_textMnfFilePath = "textMnfFilePath";
	
	private Button btnExplorerStep3;
	public ICommand C_btnExplorerStep3;
	public static String BUTTON_btnExplorerStep3 = "btnExplorerStep3";
	
	private Group grpInputdeckVar;
	public ICommand C_grpInputdeckVar;
	public static String GROUP_grpInputdeckVar = "grpInputdeckVar";
	//
	//
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//
	// Bottom - step4
	//
	private Composite compositeStep4;
	public static String COMPOSITE_compositeStep4 = "compositeStep4";
	
	private Label lblStatusValue;
	public ICommand C_lblStatusValue;
	public static String LABEL_lblStatusValue = "lblStatusValue";
	
	private Button btnStartSolving;
	public ICommand C_btnStartSolving;
	public static String BUTTON_btnStartSolving = "btnStartSolving";
	
	/*
	private Button btnStopSolving;
	public ICommand C_btnStopSolving;
	public static String BUTTON_btnStopSolving = "btnStopSolving";
	//*/
	
	private Button btnStartAnimation;
	public ICommand C_btnStartAnimation;
	public static String BUTTON_btnStartAnimation = "btnStartAnimation";
	
	private ProgressBar progressBarSolving;
	public ICommand C_progressBarSolving;
	public static String PROGRESSBAR_progressBarSolving = "progressBarSolving";
	
	private Button btnGSTIFF;
	public ICommand C_btnGSTIFF;
	public static String BUTTON_btnGSTIFF = "btnGSTIFF";
	
	private Button btnWSTIFF;
	public ICommand C_btnWSTIFF;
	public static String BUTTON_btnWSTIFF = "btnWSTIFF";
	
	private Button btnI3;
	public ICommand C_btnI3;
	public static String BUTTON_btnI3 = "btnI3";
	
	private Button btnSI2;
	public ICommand C_btnSI2;
	public static String BUTTON_btnSI2 = "btnSI2";
	
	private Button btnOriginal;
	public ICommand C_btnOriginal;
	public static String BUTTON_btnOriginal = "btnOriginal";
	
	private Button btnModified;
	public ICommand C_btnModified;
	public static String BUTTON_btnModified = "btnModified";
	
	private Text textError;
	public ICommand C_textError;
	public static String TEXT_textError = "textError";
	
	private Text textHmax;
	public ICommand C_textHmax;
	public static String TEXT_textHmax = "textHmax";
	
	private Text textNumberOfStep;
	public ICommand C_textNumberOfStep;
	public static String TEXT_textNumberOfStep = "textNumberOfStep";
	
	private Text textEndTime;
	public ICommand C_textEndTime;
	public static String TEXT_textEndTime = "textEndTime";
	
	private Button btnExtraMassOn;
	public ICommand C_btnExtraMassOn;
	public static String BUTTON_btnExtraMassOn = "btnExtraMassOn";
	
	private Button btnExtraMassOff;
	public ICommand C_btnExtraMassOff;
	public static String BUTTON_btnExtraMassOff = "btnExtraMassOff";
	
	private Text textExtraMassRatio;
	public ICommand C_textExtraMassRatio;
	public static String TEXT_textExtraMassRatio = "textExtraMassRatio";
	
	private Text textNumberOfCycles;
	public ICommand C_textNumberOfCycles;
	public static String TEXT_textNumberOfCycles = "textNumberOfCycles";
	
	private Text textStartTimeRange;
	public ICommand C_textStartTimeRange;
	public static String TEXT_textStartTimeRange = "textStartTimeRange";
	
	private Text textEndTimeRange;
	public ICommand C_textEndTimeRange;
	public static String TEXT_textEndTimeRange = "textEndTimeRange";
	
	private Text textIncrementFrame;
	public ICommand C_textIncrementFrame;
	public static String TEXT_textIncrementFrame = "textIncrementFrame";
	
	private Text textResultName;
	public ICommand C_textResultName;
	public static String TEXT_textResultName = "textResultName";
	
	private Button btnModelData;
	public ICommand C_btnModelData;
	public static String BUTTON_btnModelData = "btnModelData";
	
	private Button btnDACFile;
	public ICommand C_btnDACFile;
	public static String BUTTON_btnDACFile = "btnDACFile";
	
	private Button btnModelDataBin;
	public ICommand C_btnModelDataBin;
	public static String BUTTON_btnModelDataBin = "btnModelDataBin";
	
	private Button btnForceFile;
	public ICommand C_btnForceFile;
	public static String BUTTON_btnForceFile = "btnForceFile";
	
	
	private Button btnExportResult;
	public ICommand C_btnExportResult;
	public static String BUTTON_btnExportResult = "btnExportResult";
	
	//
	//
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//
	// Bottom - step5
	//
	private Composite compositeStep5;
	public static String COMPOSITE_compositeStep5 = "compositeStep5";
	
	private Table tableFatSolving;
	public ICommand C_tableFatSolving;
	public static String TABLE_tableFatSolving = "tableFatSolving";
	
	private Text textCycleNumber;
	public ICommand C_textCycleNumber;
	public static String TEXT_textCycleNumber = "textCycleNumber";
	
	private Button btnSolvingAndCreate;
	public ICommand C_btnSolvingAndCreate;
	public static String BUTTON_btnSolvingAndCreate = "btnSolvingAndCreate";
	//
	//
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//
	// Get-Set method
	//
	public Composite getParentView() {
		return parentView;
	}
	public void setParentView(Composite parentView) {
		this.parentView = parentView;
	}
	
	public Composite getCompositeTop() {
		return compositeTop;
	}
	public void setCompositeTop(Composite compositeTop) {
		this.compositeTop = compositeTop;
	}
	public static String getCOMPOSITE_compositeTop() {
		return COMPOSITE_compositeTop;
	}
	public static void setCOMPOSITE_compositeTop(String cOMPOSITE_compositeTop) {
		COMPOSITE_compositeTop = cOMPOSITE_compositeTop;
	}
	public Label getLblMyprojName() {
		return lblMyprojName;
	}
	public void setLblMyprojName(Label lblMyprojName) {
		this.lblMyprojName = lblMyprojName;
	}
	public static String getLABEL_lblMyprojName() {
		return LABEL_lblMyprojName;
	}
	public static void setLABEL_lblMyprojName(String lABEL_lblMyprojName) {
		LABEL_lblMyprojName = lABEL_lblMyprojName;
	}
	public Label getLblPath() {
		return lblPath;
	}
	public void setLblPath(Label lblPath) {
		this.lblPath = lblPath;
	}
	public static String getLABEL_lblPath() {
		return LABEL_lblPath;
	}
	public static void setLABEL_lblPath(String lABEL_lblPath) {
		LABEL_lblPath = lABEL_lblPath;
	}
	public Button getBtnPrevious() {
		return btnPrevious;
	}
	public void setBtnPrevious(Button btnPrevious) {
		this.btnPrevious = btnPrevious;
	}
	public ICommand getC_btnPrevious() {
		return C_btnPrevious;
	}
	public void setC_btnPrevious(ICommand c_btnPrevious) {
		C_btnPrevious = c_btnPrevious;
	}
	public static String getBUTTON_btnPrevious() {
		return BUTTON_btnPrevious;
	}
	public static void setBUTTON_btnPrevious(String bUTTON_btnPrevious) {
		BUTTON_btnPrevious = bUTTON_btnPrevious;
	}
	public Label getLblStep1() {
		return lblStep1;
	}
	public void setLblStep1(Label lblStep1) {
		this.lblStep1 = lblStep1;
	}
	public ICommand getC_lblStep1() {
		return C_lblStep1;
	}
	public void setC_lblStep1(ICommand c_lblStep1) {
		C_lblStep1 = c_lblStep1;
	}
	public static String getLABEL_lblStep1() {
		return LABEL_lblStep1;
	}
	public static void setLABEL_lblStep1(String lABEL_lblStep1) {
		LABEL_lblStep1 = lABEL_lblStep1;
	}
	public Label getLblStep2() {
		return lblStep2;
	}
	public void setLblStep2(Label lblStep2) {
		this.lblStep2 = lblStep2;
	}
	public ICommand getC_lblStep2() {
		return C_lblStep2;
	}
	public void setC_lblStep2(ICommand c_lblStep2) {
		C_lblStep2 = c_lblStep2;
	}
	public static String getLABEL_lblStep2() {
		return LABEL_lblStep2;
	}
	public static void setLABEL_lblStep2(String lABEL_lblStep2) {
		LABEL_lblStep2 = lABEL_lblStep2;
	}
	public Label getLblStep3() {
		return lblStep3;
	}
	public void setLblStep3(Label lblStep3) {
		this.lblStep3 = lblStep3;
	}
	public ICommand getC_lblStep3() {
		return C_lblStep3;
	}
	public void setC_lblStep3(ICommand c_lblStep3) {
		C_lblStep3 = c_lblStep3;
	}
	public static String getLABEL_lblStep3() {
		return LABEL_lblStep3;
	}
	public static void setLABEL_lblStep3(String lABEL_lblStep3) {
		LABEL_lblStep3 = lABEL_lblStep3;
	}
	public Label getLblStep4() {
		return lblStep4;
	}
	public void setLblStep4(Label lblStep4) {
		this.lblStep4 = lblStep4;
	}
	public ICommand getC_lblStep4() {
		return C_lblStep4;
	}
	public void setC_lblStep4(ICommand c_lblStep4) {
		C_lblStep4 = c_lblStep4;
	}
	public static String getLABEL_lblStep4() {
		return LABEL_lblStep4;
	}
	public static void setLABEL_lblStep4(String lABEL_lblStep4) {
		LABEL_lblStep4 = lABEL_lblStep4;
	}
	public Label getLblStep5() {
		return lblStep5;
	}
	public void setLblStep5(Label lblStep5) {
		this.lblStep5 = lblStep5;
	}
	public ICommand getC_lblStep5() {
		return C_lblStep5;
	}
	public void setC_lblStep5(ICommand c_lblStep5) {
		C_lblStep5 = c_lblStep5;
	}
	public static String getLABEL_lblStep5() {
		return LABEL_lblStep5;
	}
	public static void setLABEL_lblStep5(String lABEL_lblStep5) {
		LABEL_lblStep5 = lABEL_lblStep5;
	}
	public Button getBtnNext() {
		return btnNext;
	}
	public void setBtnNext(Button btnNext) {
		this.btnNext = btnNext;
	}
	public ICommand getC_btnNext() {
		return C_btnNext;
	}
	public void setC_btnNext(ICommand c_btnNext) {
		C_btnNext = c_btnNext;
	}
	public static String getBUTTON_btnNext() {
		return BUTTON_btnNext;
	}
	public static void setBUTTON_btnNext(String bUTTON_btnNext) {
		BUTTON_btnNext = bUTTON_btnNext;
	}
	public Composite getCompositeBottom() {
		return compositeBottom;
	}
	public void setCompositeBottom(Composite compositeBottom) {
		this.compositeBottom = compositeBottom;
	}
	public static String getCOMPOSITE_compositeBottom() {
		return COMPOSITE_compositeBottom;
	}
	public static void setCOMPOSITE_compositeBottom(String cOMPOSITE_compositeBottom) {
		COMPOSITE_compositeBottom = cOMPOSITE_compositeBottom;
	}
	public Text getTextMessageWindow() {
		return textMessageWindow;
	}
	public void setTextMessageWindow(Text textMessageWindow) {
		this.textMessageWindow = textMessageWindow;
	}
	public ICommand getC_textMessageWindow() {
		return C_textMessageWindow;
	}
	public void setC_textMessageWindow(ICommand c_textMessageWindow) {
		C_textMessageWindow = c_textMessageWindow;
	}
	public static String getTEXT_textMessageWindow() {
		return TEXT_textMessageWindow;
	}
	public static void setTEXT_textMessageWindow(String tEXT_textMessageWindow) {
		TEXT_textMessageWindow = tEXT_textMessageWindow;
	}
	public Composite getCompositeStep1() {
		return compositeStep1;
	}
	public void setCompositeStep1(Composite compositeStep1) {
		this.compositeStep1 = compositeStep1;
	}
	public static String getCOMPOSITE_compositeStep1() {
		return COMPOSITE_compositeStep1;
	}
	public static void setCOMPOSITE_compositeStep1(String cOMPOSITE_compositeStep1) {
		COMPOSITE_compositeStep1 = cOMPOSITE_compositeStep1;
	}
	public Combo getComboBladeType() {
		return comboBladeType;
	}
	public void setComboBladeType(Combo comboBladeType) {
		this.comboBladeType = comboBladeType;
	}
	public ICommand getC_comboBladeType() {
		return C_comboBladeType;
	}
	public void setC_comboBladeType(ICommand c_comboBladeType) {
		C_comboBladeType = c_comboBladeType;
	}
	public static String getCOMBO_comboBladeType() {
		return COMBO_comboBladeType;
	}
	public static void setCOMBO_comboBladeType(String cOMBO_comboBladeType) {
		COMBO_comboBladeType = cOMBO_comboBladeType;
	}
	public Label getLblImageBlade() {
		return lblImageBlade;
	}
	public void setLblImageBlade(Label lblImageBlade) {
		this.lblImageBlade = lblImageBlade;
	}
	public static String getLABEL_lblImageBlade() {
		return LABEL_lblImageBlade;
	}
	public static void setLABEL_lblImageBlade(String lABEL_lblImageBlade) {
		LABEL_lblImageBlade = lABEL_lblImageBlade;
	}
	public List getListBladeDatabase() {
		return listBladeDatabase;
	}
	public void setListBladeDatabase(List listBladeDatabase) {
		this.listBladeDatabase = listBladeDatabase;
	}
	public ICommand getC_listBladeDatabase() {
		return C_listBladeDatabase;
	}
	public void setC_listBladeDatabase(ICommand c_listBladeDatabase) {
		C_listBladeDatabase = c_listBladeDatabase;
	}
	public static String getLIST_listBladeDatabase() {
		return LIST_listBladeDatabase;
	}
	public static void setLIST_listBladeDatabase(String lIST_listBladeDatabase) {
		LIST_listBladeDatabase = lIST_listBladeDatabase;
	}
	public Combo getComboLinkageType() {
		return comboLinkageType;
	}
	public void setComboLinkageType(Combo comboLinkageType) {
		this.comboLinkageType = comboLinkageType;
	}
	public ICommand getC_comboLinkageType() {
		return C_comboLinkageType;
	}
	public void setC_comboLinkageType(ICommand c_comboLinkageType) {
		C_comboLinkageType = c_comboLinkageType;
	}
	public static String getCOMBO_comboLinkageType() {
		return COMBO_comboLinkageType;
	}
	public static void setCOMBO_comboLinkageType(String cOMBO_comboLinkageType) {
		COMBO_comboLinkageType = cOMBO_comboLinkageType;
	}
	public Label getLblImageLinkage() {
		return lblImageLinkage;
	}
	public void setLblImageLinkage(Label lblImageLinkage) {
		this.lblImageLinkage = lblImageLinkage;
	}
	public ICommand getC_lblImageLinkage() {
		return C_lblImageLinkage;
	}
	public void setC_lblImageLinkage(ICommand c_lblImageLinkage) {
		C_lblImageLinkage = c_lblImageLinkage;
	}
	public static String getLABEL_lblImageLinkage() {
		return LABEL_lblImageLinkage;
	}
	public static void setLABEL_lblImageLinkage(String lABEL_lblImageLinkage) {
		LABEL_lblImageLinkage = lABEL_lblImageLinkage;
	}
	public List getListLinkageDatabase() {
		return listLinkageDatabase;
	}
	public void setListLinkageDatabase(List listLinkageDatabase) {
		this.listLinkageDatabase = listLinkageDatabase;
	}
	public ICommand getC_listLinkageDatabase() {
		return C_listLinkageDatabase;
	}
	public void setC_listLinkageDatabase(ICommand c_listLinkageDatabase) {
		C_listLinkageDatabase = c_listLinkageDatabase;
	}
	public static String getLIST_listLinkageDatabase() {
		return LIST_listLinkageDatabase;
	}
	public static void setLIST_listLinkageDatabase(String lIST_listLinkageDatabase) {
		LIST_listLinkageDatabase = lIST_listLinkageDatabase;
	}
	public Composite getCompositeStep2() {
		return compositeStep2;
	}
	public void setCompositeStep2(Composite compositeStep2) {
		this.compositeStep2 = compositeStep2;
	}
	public static String getCOMPOSITE_compositeStep2() {
		return COMPOSITE_compositeStep2;
	}
	public static void setCOMPOSITE_compositeStep2(String cOMPOSITE_compositeStep2) {
		COMPOSITE_compositeStep2 = cOMPOSITE_compositeStep2;
	}
	public Label getLblImagePart() {
		return lblImagePart;
	}
	public void setLblImagePart(Label lblImagePart) {
		this.lblImagePart = lblImagePart;
	}
	public static String getLABEL_lblImagePart() {
		return LABEL_lblImagePart;
	}
	public static void setLABEL_lblImagePart(String lABEL_lblImagePart) {
		LABEL_lblImagePart = lABEL_lblImagePart;
	}
	public List getListPart() {
		return listPart;
	}
	public void setListPart(List listPart) {
		this.listPart = listPart;
	}
	public ICommand getC_listPart() {
		return C_listPart;
	}
	public void setC_listPart(ICommand c_listPart) {
		C_listPart = c_listPart;
	}
	public static String getLIST_listPart() {
		return LIST_listPart;
	}
	public static void setLIST_listPart(String lIST_listPart) {
		LIST_listPart = lIST_listPart;
	}
	public Button getBtnAdd() {
		return btnAdd;
	}
	public void setBtnAdd(Button btnAdd) {
		this.btnAdd = btnAdd;
	}
	public ICommand getC_btnAdd() {
		return C_btnAdd;
	}
	public void setC_btnAdd(ICommand c_btnAdd) {
		C_btnAdd = c_btnAdd;
	}
	public static String getBUTTON_btnAdd() {
		return BUTTON_btnAdd;
	}
	public static void setBUTTON_btnAdd(String bUTTON_btnAdd) {
		BUTTON_btnAdd = bUTTON_btnAdd;
	}
	public Button getBtnDel() {
		return btnDel;
	}
	public void setBtnDel(Button btnDel) {
		this.btnDel = btnDel;
	}
	public ICommand getC_btnDel() {
		return C_btnDel;
	}
	public void setC_btnDel(ICommand c_btnDel) {
		C_btnDel = c_btnDel;
	}
	public static String getBUTTON_btnDel() {
		return BUTTON_btnDel;
	}
	public static void setBUTTON_btnDel(String bUTTON_btnDel) {
		BUTTON_btnDel = bUTTON_btnDel;
	}
	public Table getTableSwappingPart() {
		return tableSwappingPart;
	}
	public void setTableSwappingPart(Table tableSwappingPart) {
		this.tableSwappingPart = tableSwappingPart;
	}
	public ICommand getC_tableSwappingPart() {
		return C_tableSwappingPart;
	}
	public void setC_tableSwappingPart(ICommand c_tableSwappingPart) {
		C_tableSwappingPart = c_tableSwappingPart;
	}
	public static String getTABLE_tableSwappingPart() {
		return TABLE_tableSwappingPart;
	}
	public static void setTABLE_tableSwappingPart(String tABLE_tableSwappingPart) {
		TABLE_tableSwappingPart = tABLE_tableSwappingPart;
	}
	public Composite getCompositeStep3() {
		return compositeStep3;
	}
	public void setCompositeStep3(Composite compositeStep3) {
		this.compositeStep3 = compositeStep3;
	}
	public static String getCOMPOSITE_compositeStep3() {
		return COMPOSITE_compositeStep3;
	}
	public static void setCOMPOSITE_compositeStep3(String cOMPOSITE_compositeStep3) {
		COMPOSITE_compositeStep3 = cOMPOSITE_compositeStep3;
	}
	public List getListSwappingPart() {
		return listSwappingPart;
	}
	public void setListSwappingPart(List listSwappingPart) {
		this.listSwappingPart = listSwappingPart;
	}
	public ICommand getC_listSwappingPart() {
		return C_listSwappingPart;
	}
	public void setC_listSwappingPart(ICommand c_listSwappingPart) {
		C_listSwappingPart = c_listSwappingPart;
	}
	public static String getLIST_listSwappingPart() {
		return LIST_listSwappingPart;
	}
	public static void setLIST_listSwappingPart(String lIST_listSwappingPart) {
		LIST_listSwappingPart = lIST_listSwappingPart;
	}
	public Group getGrpPartInformation() {
		return grpPartInformation;
	}
	public void setGrpPartInformation(Group grpPartInformation) {
		this.grpPartInformation = grpPartInformation;
	}
	public static String getGROUP_grpPartInformation() {
		return GROUP_grpPartInformation;
	}
	public static void setGROUP_grpPartInformation(String gROUP_grpPartInformation) {
		GROUP_grpPartInformation = gROUP_grpPartInformation;
	}
	public Label getLblPartNameValue() {
		return lblPartNameValue;
	}
	public void setLblPartNameValue(Label lblPartNameValue) {
		this.lblPartNameValue = lblPartNameValue;
	}
	public static String getLABEL_lblPartNameValue() {
		return LABEL_lblPartNameValue;
	}
	public static void setLABEL_lblPartNameValue(String lABEL_lblPartNameValue) {
		LABEL_lblPartNameValue = lABEL_lblPartNameValue;
	}
	public Label getLblSwappingTypeValue() {
		return lblSwappingTypeValue;
	}
	public void setLblSwappingTypeValue(Label lblSwappingTypeValue) {
		this.lblSwappingTypeValue = lblSwappingTypeValue;
	}
	public static String getLABEL_lblSwappingTypeValue() {
		return LABEL_lblSwappingTypeValue;
	}
	public static void setLABEL_lblSwappingTypeValue(String lABEL_lblSwappingTypeValue) {
		LABEL_lblSwappingTypeValue = lABEL_lblSwappingTypeValue;
	}
	public Text getTextMnfFilePath() {
		return textMnfFilePath;
	}
	public void setTextMnfFilePath(Text textMnfFilePath) {
		this.textMnfFilePath = textMnfFilePath;
	}
	public ICommand getC_textMnfFilePath() {
		return C_textMnfFilePath;
	}
	public void setC_textMnfFilePath(ICommand c_textMnfFilePath) {
		C_textMnfFilePath = c_textMnfFilePath;
	}
	public static String getTEXT_textMnfFilePath() {
		return TEXT_textMnfFilePath;
	}
	public static void setTEXT_textMnfFilePath(String tEXT_textMnfFilePath) {
		TEXT_textMnfFilePath = tEXT_textMnfFilePath;
	}
	public Button getBtnExplorerStep3() {
		return btnExplorerStep3;
	}
	public void setBtnExplorerStep3(Button btnExplorerStep3) {
		this.btnExplorerStep3 = btnExplorerStep3;
	}
	public ICommand getC_btnExplorerStep3() {
		return C_btnExplorerStep3;
	}
	public void setC_btnExplorerStep3(ICommand c_btnExplorerStep3) {
		C_btnExplorerStep3 = c_btnExplorerStep3;
	}
	public static String getBUTTON_btnExplorerStep3() {
		return BUTTON_btnExplorerStep3;
	}
	public static void setBUTTON_btnExplorerStep3(String bUTTON_btnExplorerStep3) {
		BUTTON_btnExplorerStep3 = bUTTON_btnExplorerStep3;
	}
	public Group getGrpInputdeckVar() {
		return grpInputdeckVar;
	}
	public void setGrpInputdeckVar(Group grpInputdeckVar) {
		this.grpInputdeckVar = grpInputdeckVar;
	}
	public ICommand getC_grpInputdeckVar() {
		return C_grpInputdeckVar;
	}
	public void setC_grpInputdeckVar(ICommand c_grpInputdeckVar) {
		C_grpInputdeckVar = c_grpInputdeckVar;
	}
	public static String getGROUP_grpInputdeckVar() {
		return GROUP_grpInputdeckVar;
	}
	public static void setGROUP_grpInputdeckVar(String gROUP_grpInputdeckVar) {
		GROUP_grpInputdeckVar = gROUP_grpInputdeckVar;
	}
	public Composite getCompositeStep4() {
		return compositeStep4;
	}
	public void setCompositeStep4(Composite compositeStep4) {
		this.compositeStep4 = compositeStep4;
	}
	public static String getCOMPOSITE_compositeStep4() {
		return COMPOSITE_compositeStep4;
	}
	public static void setCOMPOSITE_compositeStep4(String cOMPOSITE_compositeStep4) {
		COMPOSITE_compositeStep4 = cOMPOSITE_compositeStep4;
	}
	public Composite getCompositeStep5() {
		return compositeStep5;
	}
	public void setCompositeStep5(Composite compositeStep5) {
		this.compositeStep5 = compositeStep5;
	}
	public static String getCOMPOSITE_compositeStep5() {
		return COMPOSITE_compositeStep5;
	}
	public static void setCOMPOSITE_compositeStep5(String cOMPOSITE_compositeStep5) {
		COMPOSITE_compositeStep5 = cOMPOSITE_compositeStep5;
	}
	public static void setInstance(Mediator instance) {
		Mediator.instance = instance;
	}
	public Button getBtnEditAllData() {
		return btnEditAllData;
	}
	public void setBtnEditAllData(Button btnEditAllData) {
		this.btnEditAllData = btnEditAllData;
	}
	public ICommand getC_btnEditAllData() {
		return C_btnEditAllData;
	}
	public void setC_btnEditAllData(ICommand c_btnEditAllData) {
		C_btnEditAllData = c_btnEditAllData;
	}
	public static String getBUTTON_btnEditAllData() {
		return BUTTON_btnEditAllData;
	}
	public static void setBUTTON_btnEditAllData(String bUTTON_btnEditAllData) {
		BUTTON_btnEditAllData = bUTTON_btnEditAllData;
	}
	public Button getBtnSaveAllData() {
		return btnSaveAllData;
	}
	public void setBtnSaveAllData(Button btnSaveAllData) {
		this.btnSaveAllData = btnSaveAllData;
	}
	public ICommand getC_btnSaveAllData() {
		return C_btnSaveAllData;
	}
	public void setC_btnSaveAllData(ICommand c_btnSaveAllData) {
		C_btnSaveAllData = c_btnSaveAllData;
	}
	public static String getBUTTON_btnSaveAllData() {
		return BUTTON_btnSaveAllData;
	}
	public static void setBUTTON_btnSaveAllData(String bUTTON_btnSaveAllData) {
		BUTTON_btnSaveAllData = bUTTON_btnSaveAllData;
	}
	public StackLayout getStackLayout() {
		return stackLayout;
	}
	public void setStackLayout(StackLayout stackLayout) {
		this.stackLayout = stackLayout;
	}
	public ListViewer getListViewerBladeDatabase() {
		return listViewerBladeDatabase;
	}
	public void setListViewerBladeDatabase(ListViewer listViewerBladeDatabase) {
		this.listViewerBladeDatabase = listViewerBladeDatabase;
	}
	public ICommand getC_listViewerBladeDatabase() {
		return C_listViewerBladeDatabase;
	}
	public void setC_listViewerBladeDatabase(ICommand c_listViewerBladeDatabase) {
		C_listViewerBladeDatabase = c_listViewerBladeDatabase;
	}
	public static String getLISTVIEWER_listViewerBladeDatabase() {
		return LISTVIEWER_listViewerBladeDatabase;
	}
	public static void setLISTVIEWER_listViewerBladeDatabase(String lISTVIEWER_listViewerBladeDatabase) {
		LISTVIEWER_listViewerBladeDatabase = lISTVIEWER_listViewerBladeDatabase;
	}
	public ListViewer getListViewerLinkageDatabase() {
		return listViewerLinkageDatabase;
	}
	public void setListViewerLinkageDatabase(ListViewer listViewerLinkageDatabase) {
		this.listViewerLinkageDatabase = listViewerLinkageDatabase;
	}
	public ICommand getC_listViewerLinkageDatabase() {
		return C_listViewerLinkageDatabase;
	}
	public void setC_listViewerLinkageDatabase(ICommand c_listViewerLinkageDatabase) {
		C_listViewerLinkageDatabase = c_listViewerLinkageDatabase;
	}
	public static String getLISTVIEWER_listViewerLinkageDatabase() {
		return LISTVIEWER_listViewerLinkageDatabase;
	}
	public static void setLISTVIEWER_listViewerLinkageDatabase(String lISTVIEWER_listViewerLinkageDatabase) {
		LISTVIEWER_listViewerLinkageDatabase = lISTVIEWER_listViewerLinkageDatabase;
	}
	public ListViewer getListViewerPart() {
		return listViewerPart;
	}
	public void setListViewerPart(ListViewer listViewerPart) {
		this.listViewerPart = listViewerPart;
	}
	public ICommand getC_listViewerPart() {
		return C_listViewerPart;
	}
	public void setC_listViewerPart(ICommand c_listViewerPart) {
		C_listViewerPart = c_listViewerPart;
	}
	public static String getLISTVIEWER_listViewerPart() {
		return LISTVIEWER_listViewerPart;
	}
	public static void setLISTVIEWER_listViewerPart(String listViewer_listViewerPart) {
		LISTVIEWER_listViewerPart = listViewer_listViewerPart;
	}
	public ListViewer getListViewerSwappingPart() {
		return listViewerSwappingPart;
	}
	public void setListViewerSwappingPart(ListViewer listViewerSwappingPart) {
		this.listViewerSwappingPart = listViewerSwappingPart;
	}
	public ICommand getC_listViewerSwappingPart() {
		return C_listViewerSwappingPart;
	}
	public void setC_listViewerSwappingPart(ICommand c_listViewerSwappingPart) {
		C_listViewerSwappingPart = c_listViewerSwappingPart;
	}
	public static String getLISTVIEWER_listViewerSwappingPart() {
		return LISTVIEWER_listViewerSwappingPart;
	}
	public static void setLISTVIEWER_listViewerSwappingPart(String lISTVIEWER_listViewerSwappingPart) {
		LISTVIEWER_listViewerSwappingPart = lISTVIEWER_listViewerSwappingPart;
	}
	public Button getBtnReloadDb() {
		return btnReloadDb;
	}
	public void setBtnReloadDb(Button btnReloadDb) {
		this.btnReloadDb = btnReloadDb;
	}
	public ICommand getC_btnReloadDb() {
		return C_btnReloadDb;
	}
	public void setC_btnReloadDb(ICommand c_btnReloadDb) {
		C_btnReloadDb = c_btnReloadDb;
	}
	public static String getBUTTON_btnReloadDb() {
		return BUTTON_btnReloadDb;
	}
	public static void setBUTTON_btnReloadDb(String bUTTON_btnReloadDb) {
		BUTTON_btnReloadDb = bUTTON_btnReloadDb;
	}
	public Button getBtnNone() {
		return btnNone;
	}
	public void setBtnNone(Button btnNone) {
		this.btnNone = btnNone;
	}
	public ICommand getC_btnNone() {
		return C_btnNone;
	}
	public void setC_btnNone(ICommand c_btnNone) {
		C_btnNone = c_btnNone;
	}
	public static String getBUTTON_btnNone() {
		return BUTTON_btnNone;
	}
	public static void setBUTTON_btnNone(String bUTTON_btnNone) {
		BUTTON_btnNone = bUTTON_btnNone;
	}
	public ICommand getC_doubleClick_listBladeDatabase() {
		return C_doubleClick_listBladeDatabase;
	}
	public void setC_doubleClick_listBladeDatabase(ICommand c_doubleClick_listBladeDatabase) {
		C_doubleClick_listBladeDatabase = c_doubleClick_listBladeDatabase;
	}
	public ICommand getC_doubleClick_listLinkageDatabase() {
		return C_doubleClick_listLinkageDatabase;
	}
	public void setC_doubleClick_listLinkageDatabase(ICommand c_doubleClick_listLinkageDatabase) {
		C_doubleClick_listLinkageDatabase = c_doubleClick_listLinkageDatabase;
	}
	public Label getLblBladeValue() {
		return lblBladeValue;
	}
	public void setLblBladeValue(Label lblBladeValue) {
		this.lblBladeValue = lblBladeValue;
	}
	public ICommand getC_lblBladeValue() {
		return C_lblBladeValue;
	}
	public void setC_lblBladeValue(ICommand c_lblBladeValue) {
		C_lblBladeValue = c_lblBladeValue;
	}
	public static String getLABEL_lblBladeValue() {
		return LABEL_lblBladeValue;
	}
	public static void setLABEL_lblBladeValue(String lABEL_lblBladeValue) {
		LABEL_lblBladeValue = lABEL_lblBladeValue;
	}
	public Label getLblLinkageValue() {
		return lblLinkageValue;
	}
	public void setLblLinkageValue(Label lblLinkageValue) {
		this.lblLinkageValue = lblLinkageValue;
	}
	public ICommand getC_lblLinkageValue() {
		return C_lblLinkageValue;
	}
	public void setC_lblLinkageValue(ICommand c_lblLinkageValue) {
		C_lblLinkageValue = c_lblLinkageValue;
	}
	public static String getLABEL_lblLinkageValue() {
		return LABEL_lblLinkageValue;
	}
	public static void setLABEL_lblLinkageValue(String lABEL_lblLinkageValue) {
		LABEL_lblLinkageValue = lABEL_lblLinkageValue;
	}
	public Label getLblWindshieldValue() {
		return lblWindshieldValue;
	}
	public void setLblWindshieldValue(Label lblWindshieldValue) {
		this.lblWindshieldValue = lblWindshieldValue;
	}
	public ICommand getC_lblWindshieldValue() {
		return C_lblWindshieldValue;
	}
	public void setC_lblWindshieldValue(ICommand c_lblWindshieldValue) {
		C_lblWindshieldValue = c_lblWindshieldValue;
	}
	public static String getLABEL_lblWindshieldValue() {
		return LABEL_lblWindshieldValue;
	}
	public static void setLABEL_lblWindshieldValue(String lABEL_lblWindshieldValue) {
		LABEL_lblWindshieldValue = lABEL_lblWindshieldValue;
	}
	public Combo getComboWindshieldType() {
		return comboWindshieldType;
	}
	public void setComboWindshieldType(Combo comboWindshieldType) {
		this.comboWindshieldType = comboWindshieldType;
	}
	public ICommand getC_comboWindshieldType() {
		return C_comboWindshieldType;
	}
	public void setC_comboWindshieldType(ICommand c_comboWindshieldType) {
		C_comboWindshieldType = c_comboWindshieldType;
	}
	public static String getCOMBO_comboWindshieldType() {
		return COMBO_comboWindshieldType;
	}
	public static void setCOMBO_comboWindshieldType(String cOMBO_comboWindshieldType) {
		COMBO_comboWindshieldType = cOMBO_comboWindshieldType;
	}
	public Text getTextRadiusValue() {
		return textRadiusValue;
	}
	public void setTextRadiusValue(Text textRadiusValue) {
		this.textRadiusValue = textRadiusValue;
	}
	public ICommand getC_textRadiusValue() {
		return C_textRadiusValue;
	}
	public void setC_textRadiusValue(ICommand c_textRadiusValue) {
		C_textRadiusValue = c_textRadiusValue;
	}
	public static String getTEXT_textRadiusValue() {
		return TEXT_textRadiusValue;
	}
	public static void setTEXT_textRadiusValue(String tEXT_textRadiusValue) {
		TEXT_textRadiusValue = tEXT_textRadiusValue;
	}
	public Text getTextXValue() {
		return textXValue;
	}
	public void setTextXValue(Text textXValue) {
		this.textXValue = textXValue;
	}
	public ICommand getC_textXValue() {
		return C_textXValue;
	}
	public void setC_textXValue(ICommand c_textXValue) {
		C_textXValue = c_textXValue;
	}
	public static String getTEXT_textXValue() {
		return TEXT_textXValue;
	}
	public static void setTEXT_textXValue(String tEXT_textXValue) {
		TEXT_textXValue = tEXT_textXValue;
	}
	public Text getTextYValue() {
		return textYValue;
	}
	public void setTextYValue(Text textYValue) {
		this.textYValue = textYValue;
	}
	public ICommand getC_textYValue() {
		return C_textYValue;
	}
	public void setC_textYValue(ICommand c_textYValue) {
		C_textYValue = c_textYValue;
	}
	public static String getTEXT_textYValue() {
		return TEXT_textYValue;
	}
	public static void setTEXT_textYValue(String tEXT_textYValue) {
		TEXT_textYValue = tEXT_textYValue;
	}
	public Text getTextZValue() {
		return textZValue;
	}
	public void setTextZValue(Text textZValue) {
		this.textZValue = textZValue;
	}
	public ICommand getC_textZValue() {
		return C_textZValue;
	}
	public void setC_textZValue(ICommand c_textZValue) {
		C_textZValue = c_textZValue;
	}
	public static String getTEXT_textZValue() {
		return TEXT_textZValue;
	}
	public static void setTEXT_textZValue(String tEXT_textZValue) {
		TEXT_textZValue = tEXT_textZValue;
	}
	public ListViewer getListViewerWindShieldDatabase() {
		return listViewerWindShieldDatabase;
	}
	public void setListViewerWindShieldDatabase(ListViewer listViewerWindShieldDatabase) {
		this.listViewerWindShieldDatabase = listViewerWindShieldDatabase;
	}
	public ICommand getC_listViewerWindShieldDatabase() {
		return C_listViewerWindShieldDatabase;
	}
	public void setC_listViewerWindShieldDatabase(ICommand c_listViewerWindShieldDatabase) {
		C_listViewerWindShieldDatabase = c_listViewerWindShieldDatabase;
	}
	public static String getLISTVIEWER_listViewerWindShieldDatabase() {
		return LISTVIEWER_listViewerWindShieldDatabase;
	}
	public static void setLISTVIEWER_listViewerWindShieldDatabase(String lISTVIEWER_listViewerWindShieldDatabase) {
		LISTVIEWER_listViewerWindShieldDatabase = lISTVIEWER_listViewerWindShieldDatabase;
	}
	public List getListWindShieldDatabase() {
		return listWindShieldDatabase;
	}
	public void setListWindShieldDatabase(List listWindShieldDatabase) {
		this.listWindShieldDatabase = listWindShieldDatabase;
	}
	public ICommand getC_listWindShieldDatabase() {
		return C_listWindShieldDatabase;
	}
	public void setC_listWindShieldDatabase(ICommand c_listWindShieldDatabase) {
		C_listWindShieldDatabase = c_listWindShieldDatabase;
	}
	public static String getLIST_listWindShieldDatabase() {
		return LIST_listWindShieldDatabase;
	}
	public static void setLIST_listWindShieldDatabase(String lIST_listWindShieldDatabase) {
		LIST_listWindShieldDatabase = lIST_listWindShieldDatabase;
	}
	public Text getTextBulkFilePath() {
		return textBulkFilePath;
	}
	public void setTextBulkFilePath(Text textBulkFilePath) {
		this.textBulkFilePath = textBulkFilePath;
	}
	public ICommand getC_textBulkFilePath() {
		return C_textBulkFilePath;
	}
	public void setC_textBulkFilePath(ICommand c_textBulkFilePath) {
		C_textBulkFilePath = c_textBulkFilePath;
	}
	public static String getTEXT_textBulkFilePath() {
		return TEXT_textBulkFilePath;
	}
	public static void setTEXT_textBulkFilePath(String tEXT_textBulkFilePath) {
		TEXT_textBulkFilePath = tEXT_textBulkFilePath;
	}
	public Text getTextInputdeckPath() {
		return textInputdeckPath;
	}
	public void setTextInputdeckPath(Text textInputdeckPath) {
		this.textInputdeckPath = textInputdeckPath;
	}
	public ICommand getC_textInputdeckPath() {
		return C_textInputdeckPath;
	}
	public void setC_textInputdeckPath(ICommand c_textInputdeckPath) {
		C_textInputdeckPath = c_textInputdeckPath;
	}
	public static String getTEXT_textInputdeckPath() {
		return TEXT_textInputdeckPath;
	}
	public static void setTEXT_textInputdeckPath(String tEXT_textInputdeckPath) {
		TEXT_textInputdeckPath = tEXT_textInputdeckPath;
	}
	public Label getLblStatusValue() {
		return lblStatusValue;
	}
	public void setLblStatusValue(Label lblStatusValue) {
		this.lblStatusValue = lblStatusValue;
	}
	public ICommand getC_lblStatusValue() {
		return C_lblStatusValue;
	}
	public void setC_lblStatusValue(ICommand c_lblStatusValue) {
		C_lblStatusValue = c_lblStatusValue;
	}
	public static String getLABEL_lblStatusValue() {
		return LABEL_lblStatusValue;
	}
	public static void setLABEL_lblStatusValue(String lABEL_lblStatusValue) {
		LABEL_lblStatusValue = lABEL_lblStatusValue;
	}
	public Button getBtnStartSolving() {
		return btnStartSolving;
	}
	public void setBtnStartSolving(Button btnStartSolving) {
		this.btnStartSolving = btnStartSolving;
	}
	public ICommand getC_btnStartSolving() {
		return C_btnStartSolving;
	}
	public void setC_btnStartSolving(ICommand c_btnStartSolving) {
		C_btnStartSolving = c_btnStartSolving;
	}
	public static String getBUTTON_btnStartSolving() {
		return BUTTON_btnStartSolving;
	}
	public static void setBUTTON_btnStartSolving(String bUTTON_btnStartSolving) {
		BUTTON_btnStartSolving = bUTTON_btnStartSolving;
	}
	public Button getBtnStartAnimation() {
		return btnStartAnimation;
	}
	public void setBtnStartAnimation(Button btnStartAnimation) {
		this.btnStartAnimation = btnStartAnimation;
	}
	public ICommand getC_btnStartAnimation() {
		return C_btnStartAnimation;
	}
	public void setC_btnStartAnimation(ICommand c_btnStartAnimation) {
		C_btnStartAnimation = c_btnStartAnimation;
	}
	public static String getBUTTON_btnStartAnimation() {
		return BUTTON_btnStartAnimation;
	}
	public static void setBUTTON_btnStartAnimation(String bUTTON_btnStartAnimation) {
		BUTTON_btnStartAnimation = bUTTON_btnStartAnimation;
	}
	public ProgressBar getProgressBarSolving() {
		return progressBarSolving;
	}
	public void setProgressBarSolving(ProgressBar progressBarSolving) {
		this.progressBarSolving = progressBarSolving;
	}
	public ICommand getC_progressBarSolving() {
		return C_progressBarSolving;
	}
	public void setC_progressBarSolving(ICommand c_progressBarSolving) {
		C_progressBarSolving = c_progressBarSolving;
	}
	public static String getPROGRESSBAR_progressBarSolving() {
		return PROGRESSBAR_progressBarSolving;
	}
	public static void setPROGRESSBAR_progressBarSolving(String pROGRESSBAR_progressBarSolving) {
		PROGRESSBAR_progressBarSolving = pROGRESSBAR_progressBarSolving;
	}
	public Button getBtnModelData() {
		return btnModelData;
	}
	public void setBtnModelData(Button btnModelData) {
		this.btnModelData = btnModelData;
	}
	public ICommand getC_btnModelData() {
		return C_btnModelData;
	}
	public void setC_btnModelData(ICommand c_btnModelData) {
		C_btnModelData = c_btnModelData;
	}
	public static String getBUTTON_btnModelData() {
		return BUTTON_btnModelData;
	}
	public static void setBUTTON_btnModelData(String bUTTON_btnModelData) {
		BUTTON_btnModelData = bUTTON_btnModelData;
	}
	public Button getBtnDACFile() {
		return btnDACFile;
	}
	public void setBtnDACFile(Button btnDACFile) {
		this.btnDACFile = btnDACFile;
	}
	public Button getBtnModelDataBin() {
		return btnModelDataBin;
	}
	public void setBtnModelDataBin(Button btnModelDataBin) {
		this.btnModelDataBin = btnModelDataBin;
	}
	public ICommand getC_btnModelDataBin() {
		return C_btnModelDataBin;
	}
	public void setC_btnModelDataBin(ICommand c_btnModelDataBin) {
		C_btnModelDataBin = c_btnModelDataBin;
	}
	public static String getBUTTON_btnModelDataBin() {
		return BUTTON_btnModelDataBin;
	}
	public static void setButton_btnModelDataBin(String bUTTON_btnModelDataBin) {
		BUTTON_btnModelDataBin = bUTTON_btnModelDataBin;
	}
	public Button getBtnForceFile() {
		return btnForceFile;
	}
	public void setBtnForceFile(Button btnForceFile) {
		this.btnForceFile = btnForceFile;
	}
	public ICommand getC_btnForceFile() {
		return C_btnForceFile;
	}
	public void setC_btnForceFile(ICommand c_btnForceFile) {
		C_btnForceFile = c_btnForceFile;
	}
	public static String getBUTTON_btnForceFile() {
		return BUTTON_btnForceFile;
	}
	public static void setButton_btnForceFile(String bUTTON_btnForceFile) {
		BUTTON_btnForceFile = bUTTON_btnForceFile;
	}
	public ICommand getC_btnDACFile() {
		return C_btnDACFile;
	}
	public void setC_btnDACFile(ICommand c_btnDACFile) {
		C_btnDACFile = c_btnDACFile;
	}
	public static String getBUTTON_btnDACFile() {
		return BUTTON_btnDACFile;
	}
	public static void setBUTTON_btnDACFile(String bUTTON_btnDACFile) {
		BUTTON_btnDACFile = bUTTON_btnDACFile;
	}
	public Button getBtnExportResult() {
		return btnExportResult;
	}
	public void setBtnExportResult(Button btnExportResult) {
		this.btnExportResult = btnExportResult;
	}
	public ICommand getC_btnExportResult() {
		return C_btnExportResult;
	}
	public void setC_btnExportResult(ICommand c_btnExportResult) {
		C_btnExportResult = c_btnExportResult;
	}
	public static String getBUTTON_btnExportResult() {
		return BUTTON_btnExportResult;
	}
	public static void setBUTTON_btnExportResult(String bUTTON_btnExportResult) {
		BUTTON_btnExportResult = bUTTON_btnExportResult;
	}
	/*
	public Button getBtnStopSolving() {
		return btnStopSolving;
	}
	public void setBtnStopSolving(Button btnStopSolving) {
		this.btnStopSolving = btnStopSolving;
	}
	public ICommand getC_btnStopSolving() {
		return C_btnStopSolving;
	}
	public void setC_btnStopSolving(ICommand c_btnStopSolving) {
		C_btnStopSolving = c_btnStopSolving;
	}
	public static String getBUTTON_btnStopSolving() {
		return BUTTON_btnStopSolving;
	}
	public static void setBUTTON_btnStopSolving(String bUTTON_btnStopSolving) {
		BUTTON_btnStopSolving = bUTTON_btnStopSolving;
	}
	// */
	public Text getTextResultName() {
		return textResultName;
	}
	public void setTextResultName(Text textResultName) {
		this.textResultName = textResultName;
	}
	public ICommand getC_textResultName() {
		return C_textResultName;
	}
	public void setC_textResultName(ICommand c_textResultName) {
		C_textResultName = c_textResultName;
	}
	public static String getTEXT_textResultName() {
		return TEXT_textResultName;
	}
	public static void setTEXT_textResultName(String tEXT_textResultName) {
		TEXT_textResultName = tEXT_textResultName;
	}
	public static void setBUTTON_btnModelDataBin(String bUTTON_btnModelDataBin) {
		BUTTON_btnModelDataBin = bUTTON_btnModelDataBin;
	}
	public static void setBUTTON_btnForceFile(String bUTTON_btnForceFile) {
		BUTTON_btnForceFile = bUTTON_btnForceFile;
	}
	public Button getBtnGSTIFF() {
		return btnGSTIFF;
	}
	public void setBtnGSTIFF(Button btnGSTIFF) {
		this.btnGSTIFF = btnGSTIFF;
	}
	public ICommand getC_btnGSTIFF() {
		return C_btnGSTIFF;
	}
	public void setC_btnGSTIFF(ICommand c_btnGSTIFF) {
		C_btnGSTIFF = c_btnGSTIFF;
	}
	public static String getBUTTON_btnGSTIFF() {
		return BUTTON_btnGSTIFF;
	}
	public static void setBUTTON_btnGSTIFF(String bUTTON_btnGSTIFF) {
		BUTTON_btnGSTIFF = bUTTON_btnGSTIFF;
	}
	public Button getBtnWSTIFF() {
		return btnWSTIFF;
	}
	public void setBtnWSTIFF(Button btnWSTIFF) {
		this.btnWSTIFF = btnWSTIFF;
	}
	public ICommand getC_btnWSTIFF() {
		return C_btnWSTIFF;
	}
	public void setC_btnWSTIFF(ICommand c_btnWSTIFF) {
		C_btnWSTIFF = c_btnWSTIFF;
	}
	public static String getBUTTON_btnWSTIFF() {
		return BUTTON_btnWSTIFF;
	}
	public static void setBUTTON_btnWSTIFF(String bUTTON_btnWSTIFF) {
		BUTTON_btnWSTIFF = bUTTON_btnWSTIFF;
	}
	public Button getBtnI3() {
		return btnI3;
	}
	public void setBtnI3(Button btnI3) {
		this.btnI3 = btnI3;
	}
	public ICommand getC_btnI3() {
		return C_btnI3;
	}
	public void setC_btnI3(ICommand c_btnI3) {
		C_btnI3 = c_btnI3;
	}
	public static String getBUTTON_btnI3() {
		return BUTTON_btnI3;
	}
	public static void setBUTTON_btnI3(String bUTTON_btnI3) {
		BUTTON_btnI3 = bUTTON_btnI3;
	}
	public Button getBtnSI2() {
		return btnSI2;
	}
	public void setBtnSI2(Button btnSI2) {
		this.btnSI2 = btnSI2;
	}
	public ICommand getC_btnSI2() {
		return C_btnSI2;
	}
	public void setC_btnSI2(ICommand c_btnSI2) {
		C_btnSI2 = c_btnSI2;
	}
	public static String getBUTTON_btnSI2() {
		return BUTTON_btnSI2;
	}
	public static void setBUTTON_btnSI2(String bUTTON_btnSI2) {
		BUTTON_btnSI2 = bUTTON_btnSI2;
	}
	public Button getBtnOriginal() {
		return btnOriginal;
	}
	public void setBtnOriginal(Button btnOriginal) {
		this.btnOriginal = btnOriginal;
	}
	public ICommand getC_btnOriginal() {
		return C_btnOriginal;
	}
	public void setC_btnOriginal(ICommand c_btnOriginal) {
		C_btnOriginal = c_btnOriginal;
	}
	public static String getBUTTON_btnOriginal() {
		return BUTTON_btnOriginal;
	}
	public static void setBUTTON_btnOriginal(String bUTTON_btnOriginal) {
		BUTTON_btnOriginal = bUTTON_btnOriginal;
	}
	public Button getBtnModified() {
		return btnModified;
	}
	public void setBtnModified(Button btnModified) {
		this.btnModified = btnModified;
	}
	public ICommand getC_btnModified() {
		return C_btnModified;
	}
	public void setC_btnModified(ICommand c_btnModified) {
		C_btnModified = c_btnModified;
	}
	public static String getBUTTON_btnModified() {
		return BUTTON_btnModified;
	}
	public static void setBUTTON_btnModified(String bUTTON_btnModified) {
		BUTTON_btnModified = bUTTON_btnModified;
	}
	public Text getTextError() {
		return textError;
	}
	public void setTextError(Text textError) {
		this.textError = textError;
	}
	public ICommand getC_textError() {
		return C_textError;
	}
	public void setC_textError(ICommand c_textError) {
		C_textError = c_textError;
	}
	public static String getTEXT_textError() {
		return TEXT_textError;
	}
	public static void setTEXT_textError(String tEXT_textError) {
		TEXT_textError = tEXT_textError;
	}
	public Text getTextHmax() {
		return textHmax;
	}
	public void setTextHmax(Text textHmax) {
		this.textHmax = textHmax;
	}
	public ICommand getC_textHmax() {
		return C_textHmax;
	}
	public void setC_textHmax(ICommand c_textHmax) {
		C_textHmax = c_textHmax;
	}
	public static String getTEXT_textHmax() {
		return TEXT_textHmax;
	}
	public static void setTEXT_textHmax(String tEXT_textHmax) {
		TEXT_textHmax = tEXT_textHmax;
	}
	public Text getTextNumberOfStep() {
		return textNumberOfStep;
	}
	public void setTextNumberOfStep(Text textNumberOfStep) {
		this.textNumberOfStep = textNumberOfStep;
	}
	public ICommand getC_textNumberOfStep() {
		return C_textNumberOfStep;
	}
	public void setC_textNumberOfStep(ICommand c_textNumberOfStep) {
		C_textNumberOfStep = c_textNumberOfStep;
	}
	public static String getTEXT_textNumberOfStep() {
		return TEXT_textNumberOfStep;
	}
	public static void setTEXT_textNumberOfStep(String tEXT_textNumberOfStep) {
		TEXT_textNumberOfStep = tEXT_textNumberOfStep;
	}
	public Text getTextEndTime() {
		return textEndTime;
	}
	public void setTextEndTime(Text textEndTime) {
		this.textEndTime = textEndTime;
	}
	public ICommand getC_textEndTime() {
		return C_textEndTime;
	}
	public void setC_textEndTime(ICommand c_textEndTime) {
		C_textEndTime = c_textEndTime;
	}
	public static String getTEXT_textEndTime() {
		return TEXT_textEndTime;
	}
	public static void setTEXT_textEndTime(String tEXT_textEndTime) {
		TEXT_textEndTime = tEXT_textEndTime;
	}
	public Table getTableFatSolving() {
		return tableFatSolving;
	}
	public void setTableFatSolving(Table tableFatSolving) {
		this.tableFatSolving = tableFatSolving;
	}
	public ICommand getC_tableFatSolving() {
		return C_tableFatSolving;
	}
	public void setC_tableFatSolving(ICommand c_tableFatSolving) {
		C_tableFatSolving = c_tableFatSolving;
	}
	public static String getTABLE_tableFatSolving() {
		return TABLE_tableFatSolving;
	}
	public static void setTABLE_tableFatSolving(String tABLE_tableFatSolving) {
		TABLE_tableFatSolving = tABLE_tableFatSolving;
	}
	public Button getBtnSolvingAndCreate() {
		return btnSolvingAndCreate;
	}
	public void setBtnSolvingAndCreate(Button btnSolvingAndCreate) {
		this.btnSolvingAndCreate = btnSolvingAndCreate;
	}
	public ICommand getC_btnSolvingAndCreate() {
		return C_btnSolvingAndCreate;
	}
	public void setC_btnSolvingAndCreate(ICommand c_btnSolvingAndCreate) {
		C_btnSolvingAndCreate = c_btnSolvingAndCreate;
	}
	public static String getBUTTON_btnSolvingAndCreate() {
		return BUTTON_btnSolvingAndCreate;
	}
	public static void setBUTTON_btnSolvingAndCreate(String bUTTON_btnSolvingAndCreate) {
		BUTTON_btnSolvingAndCreate = bUTTON_btnSolvingAndCreate;
	}
	/**
	 * @return the btnExtraMassOn
	 */
	public Button getBtnExtraMassOn() {
		return btnExtraMassOn;
	}
	/**
	 * @param btnExtraMassOn the btnExtraMassOn to set
	 */
	public void setBtnExtraMassOn(Button btnExtraMassOn) {
		this.btnExtraMassOn = btnExtraMassOn;
	}
	/**
	 * @return the c_btnExtraMassOn
	 */
	public ICommand getC_btnExtraMassOn() {
		return C_btnExtraMassOn;
	}
	/**
	 * @param c_btnExtraMassOn the c_btnExtraMassOn to set
	 */
	public void setC_btnExtraMassOn(ICommand c_btnExtraMassOn) {
		C_btnExtraMassOn = c_btnExtraMassOn;
	}
	/**
	 * @return the bUTTON_btnExtraMassOn
	 */
	public static String getBUTTON_btnExtraMassOn() {
		return BUTTON_btnExtraMassOn;
	}
	/**
	 * @param bUTTON_btnExtraMassOn the bUTTON_btnExtraMassOn to set
	 */
	public static void setBUTTON_btnExtraMassOn(String bUTTON_btnExtraMassOn) {
		BUTTON_btnExtraMassOn = bUTTON_btnExtraMassOn;
	}
	/**
	 * @return the btnExtraMassOff
	 */
	public Button getBtnExtraMassOff() {
		return btnExtraMassOff;
	}
	/**
	 * @param btnExtraMassOff the btnExtraMassOff to set
	 */
	public void setBtnExtraMassOff(Button btnExtraMassOff) {
		this.btnExtraMassOff = btnExtraMassOff;
	}
	/**
	 * @return the c_btnExtraMassOff
	 */
	public ICommand getC_btnExtraMassOff() {
		return C_btnExtraMassOff;
	}
	/**
	 * @param c_btnExtraMassOff the c_btnExtraMassOff to set
	 */
	public void setC_btnExtraMassOff(ICommand c_btnExtraMassOff) {
		C_btnExtraMassOff = c_btnExtraMassOff;
	}
	/**
	 * @return the bUTTON_btnExtraMassOff
	 */
	public static String getBUTTON_btnExtraMassOff() {
		return BUTTON_btnExtraMassOff;
	}
	/**
	 * @param bUTTON_btnExtraMassOff the bUTTON_btnExtraMassOff to set
	 */
	public static void setBUTTON_btnExtraMassOff(String bUTTON_btnExtraMassOff) {
		BUTTON_btnExtraMassOff = bUTTON_btnExtraMassOff;
	}
	/**
	 * @return the textExtraMass
	 */
	public Text getTextExtraMassRatio() {
		return textExtraMassRatio;
	}
	/**
	 * @param textExtraMass the textExtraMass to set
	 */
	public void setTextExtraMassRatio(Text textExtraMassRatio) {
		this.textExtraMassRatio = textExtraMassRatio;
	}
	/**
	 * @return the c_textExtraMass
	 */
	public ICommand getC_textExtraMassRatio() {
		return C_textExtraMassRatio;
	}
	/**
	 * @param c_textExtraMass the c_textExtraMass to set
	 */
	public void setC_textExtraMassRatio(ICommand c_textExtraMassRatio) {
		C_textExtraMassRatio = c_textExtraMassRatio;
	}
	/**
	 * @return the tEXT_textExtraMass
	 */
	public static String getTEXT_textExtraMassRatio() {
		return TEXT_textExtraMassRatio;
	}
	/**
	 * @param tEXT_textExtraMass the tEXT_textExtraMass to set
	 */
	public static void setTEXT_textExtraMassRatio(String tEXT_textExtraMassRatio) {
		TEXT_textExtraMassRatio = tEXT_textExtraMassRatio;
	}
	/**
	 * @return the textNumberOfCycles
	 */
	public Text getTextNumberOfCycles() {
		return textNumberOfCycles;
	}
	/**
	 * @param textNumberOfCycles the textNumberOfCycles to set
	 */
	public void setTextNumberOfCycles(Text textNumberOfCycles) {
		this.textNumberOfCycles = textNumberOfCycles;
	}
	/**
	 * @return the c_textNumberOfCycles
	 */
	public ICommand getC_textNumberOfCycles() {
		return C_textNumberOfCycles;
	}
	/**
	 * @param c_textNumberOfCycles the c_textNumberOfCycles to set
	 */
	public void setC_textNumberOfCycles(ICommand c_textNumberOfCycles) {
		C_textNumberOfCycles = c_textNumberOfCycles;
	}
	/**
	 * @return the tEXT_textNumberOfCycles
	 */
	public static String getTEXT_textNumberOfCycles() {
		return TEXT_textNumberOfCycles;
	}
	/**
	 * @param tEXT_textNumberOfCycles the tEXT_textNumberOfCycles to set
	 */
	public static void setTEXT_textNumberOfCycles(String tEXT_textNumberOfCycles) {
		TEXT_textNumberOfCycles = tEXT_textNumberOfCycles;
	}
	/**
	 * @return the textStartTimeRange
	 */
	public Text getTextStartTimeRange() {
		return textStartTimeRange;
	}
	/**
	 * @param textStartTimeRange the textStartTimeRange to set
	 */
	public void setTextStartTimeRange(Text textStartTimeRange) {
		this.textStartTimeRange = textStartTimeRange;
	}
	/**
	 * @return the c_textStartTimeRange
	 */
	public ICommand getC_textStartTimeRange() {
		return C_textStartTimeRange;
	}
	/**
	 * @param c_textStartTimeRange the c_textStartTimeRange to set
	 */
	public void setC_textStartTimeRange(ICommand c_textStartTimeRange) {
		C_textStartTimeRange = c_textStartTimeRange;
	}
	/**
	 * @return the tEXT_textStartTimeRange
	 */
	public static String getTEXT_textStartTimeRange() {
		return TEXT_textStartTimeRange;
	}
	/**
	 * @param tEXT_textStartTimeRange the tEXT_textStartTimeRange to set
	 */
	public static void setTEXT_textStartTimeRange(String tEXT_textStartTimeRange) {
		TEXT_textStartTimeRange = tEXT_textStartTimeRange;
	}
	/**
	 * @return the textEndTimeRange
	 */
	public Text getTextEndTimeRange() {
		return textEndTimeRange;
	}
	/**
	 * @param textEndTimeRange the textEndTimeRange to set
	 */
	public void setTextEndTimeRange(Text textEndTimeRange) {
		this.textEndTimeRange = textEndTimeRange;
	}
	/**
	 * @return the c_textEndTimeRange
	 */
	public ICommand getC_textEndTimeRange() {
		return C_textEndTimeRange;
	}
	/**
	 * @param c_textEndTimeRange the c_textEndTimeRange to set
	 */
	public void setC_textEndTimeRange(ICommand c_textEndTimeRange) {
		C_textEndTimeRange = c_textEndTimeRange;
	}
	/**
	 * @return the tEXT_textEndTimeRange
	 */
	public static String getTEXT_textEndTimeRange() {
		return TEXT_textEndTimeRange;
	}
	/**
	 * @param tEXT_textEndTimeRange the tEXT_textEndTimeRange to set
	 */
	public static void setTEXT_textEndTimeRange(String tEXT_textEndTimeRange) {
		TEXT_textEndTimeRange = tEXT_textEndTimeRange;
	}
	/**
	 * @return the textIncrementFrame
	 */
	public Text getTextIncrementFrame() {
		return textIncrementFrame;
	}
	/**
	 * @param textIncrementFrame the textIncrementFrame to set
	 */
	public void setTextIncrementFrame(Text textIncrementFrame) {
		this.textIncrementFrame = textIncrementFrame;
	}
	/**
	 * @return the c_textIncrementFrame
	 */
	public ICommand getC_textIncrementFrame() {
		return C_textIncrementFrame;
	}
	/**
	 * @param c_textIncrementFrame the c_textIncrementFrame to set
	 */
	public void setC_textIncrementFrame(ICommand c_textIncrementFrame) {
		C_textIncrementFrame = c_textIncrementFrame;
	}
	/**
	 * @return the tEXT_textIncrementFrame
	 */
	public static String getTEXT_textIncrementFrame() {
		return TEXT_textIncrementFrame;
	}
	/**
	 * @param tEXT_textIncrementFrame the tEXT_textIncrementFrame to set
	 */
	public static void setTEXT_textIncrementFrame(String tEXT_textIncrementFrame) {
		TEXT_textIncrementFrame = tEXT_textIncrementFrame;
	}
	/**
	 * @return the textCycleNumber
	 */
	public Text getTextCycleNumber() {
		return textCycleNumber;
	}
	/**
	 * @param textCycleNumber the textCycleNumber to set
	 */
	public void setTextCycleNumber(Text textCycleNumber) {
		this.textCycleNumber = textCycleNumber;
	}
	/**
	 * @return the c_textCycleNumber
	 */
	public ICommand getC_textCycleNumber() {
		return C_textCycleNumber;
	}
	/**
	 * @param c_textCycleNumber the c_textCycleNumber to set
	 */
	public void setC_textCycleNumber(ICommand c_textCycleNumber) {
		C_textCycleNumber = c_textCycleNumber;
	}
	/**
	 * @return the tEXT_textCycleNumber
	 */
	public static String getTEXT_textCycleNumber() {
		return TEXT_textCycleNumber;
	}
	/**
	 * @param tEXT_textCycleNumber the tEXT_textCycleNumber to set
	 */
	public static void setTEXT_textCycleNumber(String tEXT_textCycleNumber) {
		TEXT_textCycleNumber = tEXT_textCycleNumber;
	}

	//
	//
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
}
