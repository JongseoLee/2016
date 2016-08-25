package com.js.ens.leveller.core;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

import com.js.ens.leveller.handler.ICommand;

public class Mediator {
	private static Mediator instance = new Mediator();
	public static Mediator getInstance(){
		return instance;
	}
	//--------------------------------------------
	private TabFolder tabFolder;
	public ICommand customTabFolder;
	public static String TABFOLDER_tabFolder = "tabFolder";
	
	
	//--------------------------------------------
	private Text textModelName;
	private Label lblworkspacePath;
	//--------------------------------------------	
	private Composite compositeShapeParameter; 
	
	private Combo comboType;
	public ICommand customComboType;
	public static String COMBO_Type = "comboType";
	private Label lblPhoto;
	private Text textWidth;
	private Text textLength;
	private Text textThickness;
	
	private Composite compositeShapeParameterChild_1;
	
	private Composite compositeShapeParameterChild_2;
	private Text type2_textLeftEdgeWavePitch;
	private Text type2_textRightEdgeWavePitch;
	private Text type2_textLeftEdgeWaveHeight;
	private Text type2_textRightEdgeWaveHeight;
	private Text type2_textLeftEdgeWavePhase;
	private Text type2_textRightEdgeWavePhase;
	
	private Composite compositeShapeParameterChild_3;
	private Text type3_textWavePitch;
	private Text type3_textWaveHeight;
	
	private Composite compositeShapeParameterChild_4;
	private Text type4_textGutterHeight;
	
	private Composite compositeShapeParameterChild_5;
	private Text type5_textGutterHeight;
	private Text type5_textGutterLength;
	
	private Composite compositeShapeParameterChild_6;
	private Text type6_textHeadGutterHeight;
	private Text type6_textHeadGutterLength;
	private Text type6_textTailGutterHeight;
	private Text type6_textTailGutterLength;
	
	private Composite compositeShapeParameterChild_7;
	private Text type7_textHeadGutterHeight;
	private Text type7_textGutterLength;
	private Text type7_textGutterLengthLength;
	private Text type7_textGutterWidthLength;
	//--------------------------------------------
	private Composite compositeMeshParameter;
	
	private Text textThicknessElementNum;
	private Text textWidthAspectRatio;
	private Text textLengthAspectRatio;
	private Text textElementNumber;
	private Button btnCalcElementNum;
	public ICommand customBtnCalcElementNum;
	public static String BUTTON_CalcElementNum = "btnCalcElementNum";
	
	//--------------------------------------------
	private Composite compositePlateInformation;

	private Text textPlateVelocity;
	private Text textTemperatureStart;
	private Text textTemperatureEnd;
	private Text textPassLineOffset;
	
	//--------------------------------------------
	private Composite compositeRollParameter;
	
	private Spinner spinnerUpperRollNum;
	public ICommand customSpinnerUpperRollNum;
	public static String SPINNER_UpperRollNum = "spinnerUpperRollNum";
	private Spinner spinnerLowerRollNum;
	public ICommand customSpinnerLowerRollNum;
	public static String SPINNER_LowerRollNum = "spinnerLowerRollNum";
	private Text textRollPitch;
	private Text textRollLength;
	private Text textEntryRollTableDistance;
	private Text textExitRollTableDistance;
	private Text textEntryUpperRollGap;
	private Text textEntryLowerRollGap;
	private Text textExitUpperRollGap;
	private Text textExitLowerRollGap;
	
	private Text textUpperEntryRollGapMovement;
	private Text textUpperExitRollGapMovement;
	private Text textUpperRollGapStayingTime;
	private Text textUpperRollGapMovingTime;
	private Text textLowerEntryRollGapMovement;
	private Text textLowerExitRollGapMovement;
	private Text textLowerRollGapStayingTime;
	private Text textLowerRollGapMovingTime;
	
	private Text textFrontHDRollDia;
	private Text textFrontHDRollPitch;
	private Text textFrontHDRollVericalPos;
	private Text textRearHDRollDia;
	private Text textRearHDRollPitch;
	private Text textRearHDRollVerticalPos;
	
	private Button btnNone;
	public ICommand customBtnNone;
	public static String BUTTON_btnNone = "btnNone";
	
	private Button btnUpper;
	public ICommand customBtnUpper;
	public static String BUTTON_btnUpper = "btnUpper";
	
	private Button btnLower;
	public ICommand customBtnLower;
	public static String BUTTON_btnLower = "btnLower";
	
	private Button btnShowRollTable;
	public ICommand customBtnShowRollTable;
	public static String BUTTON_btnShowRollTable = "btnShowRollTable";
	
	private Text textRollFriction;
	private Text textRollDiameter;
	//private Text textRollCrown;
	private Text textUpperRollCrown;
	private Text textLowerRollCrown;
	private Text textMillStiffness;
	/*
	private Button btnRadioNone_RC;
	public ICommand customBtnRadioNone_RC;
	public static String BUTTON_RadioNone_RC = "btnRadioNone_RC";
	
	private Button btnRadioApply_RC;
	public ICommand customBtnRadioApply_RC;
	public static String BUTTON_RadioApply_RC = "btnRadioApply_RC";
	*/
	private Button btnRadioRigid_MS;
	public ICommand customBtnRadioRigid_MS;
	public static String BUTTON_RadioRigid_MS = "btnRadioRigid_MS";
	
	private Button btnRadioSpring_MS;
	public ICommand customBtnRadioSpring_MS;
	public static String BUTTON_RadioSpring_MS = "btnRadioSpring_MS";
	
	private Button btnCreateRoll;
	public ICommand customBtnCreateRoll;
	public static String BUTTON_CreateRoll = "btnCreateRoll";
	private Button btnUpperEdit;
	public ICommand customBtnUpperEdit;
	public static String BUTTON_UpperEdit = "btnUpperEdit";
	private Button btnUpperSave;
	public ICommand customBtnUpperSave;
	public static String BUTTON_UpperSave = "btnUpperSave";
	private TableViewer tableViewerUpperRoll;
	public ICommand customTableViewerUpperRoll;
	public static String TABLEVIEWER_UpperRoll = "tableViewerUpperRoll";
	private Button btnLowerEdit;
	public ICommand customBtnLowerEdit;
	public static String BUTTON_LowerEdit = "btnLowerEdit";
	private Button btnLowerSave;
	public ICommand customBtnLowerSave;
	public static String BUTTON_LowerSave = "btnLowerSave";
	private TableViewer tableViewerLowerRoll;
	public ICommand customTableViewerLowerRoll;
	public static String TABLEVIEWER_LowerRoll = "tableViewerLowerRoll";
	public Button btnSaveRoll;
	private ICommand customBtnSaveRoll;
	public static String BUTTON_SaveRoll = "btnRollSave";
	//--------------------------------------------
	private Composite compositeMaterialParameter;
	
	private Text textYoungsModulus;
	private Button btnExplorerYoungsModulus;
	public ICommand customBtnExplorerYoungsModulus;
	public static String BUTTON_ExplorerYoungsModulus = "btnExplorerYoungsModulus";
	
	private Button btnRadioConstant_YM;
	public ICommand customBtnRadioConstant_YM;
	public static String BUTTON_RadioConstant_YM = "btnRadioConstant_YM";
	
	private Button btnRadioTable_YM;
	public ICommand customBtnRadioTable_YM;
	public static String BUTTON_RadioTable_YM = "btnRadioTable_YM";
	
	private Text textFlowStress;
	private Button btnExplorerFlowStress;
	public ICommand customBtnExplorerFlowStress;
	public static String BUTTON_ExplorerFlowStress = "btnExplorerFlowStress";
	
	private Text textYieldStrength;
	private Text textTensileStrength;
	private Text textElongation;
	
	private Button btnRadioConstant_FS;
	public ICommand customBtnRadioConstant_FS;
	public static String BUTTON_RadioConstant_FS = "btnRadioConstant_FS";
	
	private Button btnRadioTable_FS;
	public ICommand customBtnRadioTable_FS;
	public static String BUTTON_RadioTable_FS = "btnRadioTable_FS";
	
	private Text textThermalExpansionCoefficient;
	private Button btnExplorerThermalExpansionCoefficient;
	public ICommand customBtnExplorerThermalExpansionCoefficient;
	public static String BUTTON_ExplorerThermalExpansionCoefficient = "btnExplorerThermalExpansionCoefficient";
	
	private Button btnRadioConstant_TEC;
	public ICommand customBtnRadioConstant_TEC;
	public static String BUTTON_RadioConstant_TEC = "btnRadioConstant_TEC";
	
	private Button btnRadioTable_TEC;
	public ICommand customBtnRadioTable_TEC;
	public static String BUTTON_RadioTable_TEC = "btnRadioTable_TEC";
	
	private Text textPoissonsRatio;
	private Button btnExplorerPoissonsRatio;
	public ICommand customBtnExplorerPoissonsRatio;
	public static String BUTTON_ExplorerPoissonsRatio = "btnExplorerPoissonsRatio";
	
	private Button btnRadioConstant_PR;
	public ICommand customBtnRadioConstant_PR;
	public static String BUTTON_RadioConstant_PR = "btnRadioConstant_PR";
	
	private Button btnRadioTable_PR;
	public ICommand customBtnRadioTable_PR;
	public static String BUTTON_RadioTable_PR = "btnRadioTable_PR";
	
	private Text textMassDensity;
	
	private Button btnApply;
	public ICommand customBtnApply;
	public static String BUTTON_Apply = "btnApply";
	
	
	//--------------------------------------------
	private Composite compositeSolvingOption;
	
	private Text textSolvingTime;
	private Text textIncrementTime;
	
	private Button btnParallelDDMUse;
	public ICommand customBtnParallelDDMUse;
	public static String BUTTON_ParallelDDMUse = "btnParallelDDMUse";
	
	private Spinner spinnerDomain;
	public ICommand customSpinnerDomain;
	public static String SPINNER_Domain = "spinnerDomain";
	
	private Button btnParallelMultiThreadUse;
	public ICommand customBtnParallelMultiThreadUse;
	public static String BUTTON_ParallelMultiThreadUse ="btnParallelMultiThreadUse";
	
	private Spinner spinnerThread;
	public ICommand customSpinnerThread;
	public static String SPINNER_Thread = "spinnerThread";
	
	
	//=======================================================================================================
	//=======================================================================================================
	//=======================================================================================================
	//=======================================================================================================
	//=======================================================================================================
	//=======================================================================================================
	
	//--------------------------------------------
	//--------------------------------------------
	
	public Composite getCompositeShapeParameter() {
		return compositeShapeParameter;
	}
	public void setCompositeShapeParameter(Composite compositeShapeParameter) {
		this.compositeShapeParameter = compositeShapeParameter;
	}
	
	public Composite getCompositeMeshParameter() {
		return compositeMeshParameter;
	}
	public void setCompositeMeshParameter(Composite compositeMeshParameter) {
		this.compositeMeshParameter = compositeMeshParameter;
	}
	
	public Composite getCompositePlateInformation() {
		return compositePlateInformation;
	}
	public void setCompositePlateInformation(Composite compositePlateInformation) {
		this.compositePlateInformation = compositePlateInformation;
	}
	
	public Composite getCompositeRollParameter() {
		return compositeRollParameter;
	}
	public void setCompositeRollParameter(Composite compositeRollParameter) {
		this.compositeRollParameter = compositeRollParameter;
	}
	
	public Composite getCompositeMaterialParameter() {
		return compositeMaterialParameter;
	}
	public void setCompositeMaterialParameter(Composite compositeMaterialParameter) {
		this.compositeMaterialParameter = compositeMaterialParameter;
	}
	
	public Composite getCompositeSolvingOption() {
		return compositeSolvingOption;
	}
	public void setCompositeSolvingOption(Composite compositeSolvingOption) {
		this.compositeSolvingOption = compositeSolvingOption;
	}
	
	public Text getTextModelName() {
		return textModelName;
	}
	public void setTextModelName(Text textModelName) {
		this.textModelName = textModelName;
	}
	public Label getlblworkspacePath() {
		return lblworkspacePath;
	}
	public void setlblworkspacePath(Label lblworkspacePath) {
		this.lblworkspacePath = lblworkspacePath;
	}
	public Combo getComboType() {
		return comboType;
	}
	public void setComboType(Combo comboType) {
		this.comboType = comboType;
	}
	public ICommand getCustomComboType() {
		return customComboType;
	}
	public void setCustomComboType(ICommand customComboType) {
		this.customComboType = customComboType;
	}
	public Label getLblPhoto() {
		return lblPhoto;
	}
	public void setLblPhoto(Label lblPhoto) {
		this.lblPhoto = lblPhoto;
	}
	public Text getTextWidth() {
		return textWidth;
	}
	public void setTextWidth(Text textWidth) {
		this.textWidth = textWidth;
	}
	public Text getTextLength() {
		return textLength;
	}
	public void setTextLength(Text textLength) {
		this.textLength = textLength;
	}
	public Text getTextThickness() {
		return textThickness;
	}
	public void setTextThickness(Text textThickness) {
		this.textThickness = textThickness;
	}
	public Composite getCompositeShapeParameterChild_1() {
		return compositeShapeParameterChild_1;
	}
	public void setCompositeShapeParameterChild_1(
			Composite compositeShapeParameterChild_1) {
		this.compositeShapeParameterChild_1 = compositeShapeParameterChild_1;
	}
	public Composite getCompositeShapeParameterChild_2() {
		return compositeShapeParameterChild_2;
	}
	public void setCompositeShapeParameterChild_2(
			Composite compositeShapeParameterChild_2) {
		this.compositeShapeParameterChild_2 = compositeShapeParameterChild_2;
	}
	public Text getType2_textLeftEdgeWavePitch() {
		return type2_textLeftEdgeWavePitch;
	}
	public void setType2_textLeftEdgeWavePitch(Text type2_textLeftEdgeWavePitch) {
		this.type2_textLeftEdgeWavePitch = type2_textLeftEdgeWavePitch;
	}
	public Text getType2_textRightEdgeWavePitch() {
		return type2_textRightEdgeWavePitch;
	}
	public void setType2_textRightEdgeWavePitch(Text type2_textRightEdgeWavePitch) {
		this.type2_textRightEdgeWavePitch = type2_textRightEdgeWavePitch;
	}
	public Text getType2_textLeftEdgeWaveHeight() {
		return type2_textLeftEdgeWaveHeight;
	}
	public void setType2_textLeftEdgeWaveHeight(Text type2_textLeftEdgeWaveHeight) {
		this.type2_textLeftEdgeWaveHeight = type2_textLeftEdgeWaveHeight;
	}
	public Text getType2_textRightEdgeWaveHeight() {
		return type2_textRightEdgeWaveHeight;
	}
	public void setType2_textRightEdgeWaveHeight(Text type2_textRightEdgeWaveHeight) {
		this.type2_textRightEdgeWaveHeight = type2_textRightEdgeWaveHeight;
	}
	public Text getType2_textLeftEdgeWavePhase() {
		return type2_textLeftEdgeWavePhase;
	}
	public void setType2_textLeftEdgeWavePhase(Text type2_textLeftEdgeWavePhase) {
		this.type2_textLeftEdgeWavePhase = type2_textLeftEdgeWavePhase;
	}
	public Text getType2_textRightEdgeWavePhase() {
		return type2_textRightEdgeWavePhase;
	}
	public void setType2_textRightEdgeWavePhase(Text type2_textRightEdgeWavePhase) {
		this.type2_textRightEdgeWavePhase = type2_textRightEdgeWavePhase;
	}
	public Composite getCompositeShapeParameterChild_3() {
		return compositeShapeParameterChild_3;
	}
	public void setCompositeShapeParameterChild_3(
			Composite compositeShapeParameterChild_3) {
		this.compositeShapeParameterChild_3 = compositeShapeParameterChild_3;
	}
	public Text getType3_textWavePitch() {
		return type3_textWavePitch;
	}
	public void setType3_textWavePitch(Text type3_textWavePitch) {
		this.type3_textWavePitch = type3_textWavePitch;
	}
	public Text getType3_textWaveHeight() {
		return type3_textWaveHeight;
	}
	public void setType3_textWaveHeight(Text type3_textWaveHeight) {
		this.type3_textWaveHeight = type3_textWaveHeight;
	}
	public Composite getCompositeShapeParameterChild_4() {
		return compositeShapeParameterChild_4;
	}
	public void setCompositeShapeParameterChild_4(
			Composite compositeShapeParameterChild_4) {
		this.compositeShapeParameterChild_4 = compositeShapeParameterChild_4;
	}
	public Text getType4_textGutterHeight() {
		return type4_textGutterHeight;
	}
	public void setType4_textGutterHeight(Text type4_textGutterHeight) {
		this.type4_textGutterHeight = type4_textGutterHeight;
	}
	public Composite getCompositeShapeParameterChild_5() {
		return compositeShapeParameterChild_5;
	}
	public void setCompositeShapeParameterChild_5(
			Composite compositeShapeParameterChild_5) {
		this.compositeShapeParameterChild_5 = compositeShapeParameterChild_5;
	}
	public Text getType5_textGutterHeight() {
		return type5_textGutterHeight;
	}
	public void setType5_textGutterHeight(Text type5_textGutterHeight) {
		this.type5_textGutterHeight = type5_textGutterHeight;
	}
	public Text getType5_textGutterLength() {
		return type5_textGutterLength;
	}
	public void setType5_textGutterLength(Text type5_textGutterLength) {
		this.type5_textGutterLength = type5_textGutterLength;
	}
	public Composite getCompositeShapeParameterChild_6() {
		return compositeShapeParameterChild_6;
	}
	public void setCompositeShapeParameterChild_6(
			Composite compositeShapeParameterChild_6) {
		this.compositeShapeParameterChild_6 = compositeShapeParameterChild_6;
	}
	public Text getType6_textHeadGutterHeight() {
		return type6_textHeadGutterHeight;
	}
	public void setType6_textHeadGutterHeight(Text type6_textHeadGutterHeight) {
		this.type6_textHeadGutterHeight = type6_textHeadGutterHeight;
	}
	public Text getType6_textHeadGutterLength() {
		return type6_textHeadGutterLength;
	}
	public void setType6_textHeadGutterLength(Text type6_textHeadGutterLength) {
		this.type6_textHeadGutterLength = type6_textHeadGutterLength;
	}
	public Text getType6_textTailGutterHeight() {
		return type6_textTailGutterHeight;
	}
	public void setType6_textTailGutterHeight(Text type6_textTailGutterHeight) {
		this.type6_textTailGutterHeight = type6_textTailGutterHeight;
	}
	public Text getType6_textTailGutterLength() {
		return type6_textTailGutterLength;
	}
	public void setType6_textTailGutterLength(Text type6_textTailGutterLength) {
		this.type6_textTailGutterLength = type6_textTailGutterLength;
	}
	public Composite getCompositeShapeParameterChild_7() {
		return compositeShapeParameterChild_7;
	}
	public void setCompositeShapeParameterChild_7(
			Composite compositeShapeParameterChild_7) {
		this.compositeShapeParameterChild_7 = compositeShapeParameterChild_7;
	}
	public Text getType7_textHeadGutterHeight() {
		return type7_textHeadGutterHeight;
	}
	public void setType7_textHeadGutterHeight(Text type7_textHeadGutterHeight) {
		this.type7_textHeadGutterHeight = type7_textHeadGutterHeight;
	}
	public Text getType7_textGutterLength() {
		return type7_textGutterLength;
	}
	public void setType7_textGutterLength(Text type7_textGutterLength) {
		this.type7_textGutterLength = type7_textGutterLength;
	}
	public Text getType7_textGutterLengthLength() {
		return type7_textGutterLengthLength;
	}
	public void setType7_textGutterLengthLength(Text type7_textGutterLengthLength) {
		this.type7_textGutterLengthLength = type7_textGutterLengthLength;
	}
	public Text getType7_textGutterWidthLength() {
		return type7_textGutterWidthLength;
	}
	public void setType7_textGutterWidthLength(Text type7_textGutterWidthLength) {
		this.type7_textGutterWidthLength = type7_textGutterWidthLength;
	}
	public Text getTextThicknessElementNum() {
		return textThicknessElementNum;
	}
	public void setTextThicknessElementNum(Text textThicknessElementNum) {
		this.textThicknessElementNum = textThicknessElementNum;
	}
	public Text getTextWidthAspectRatio() {
		return textWidthAspectRatio;
	}
	public void setTextWidthAspectRatio(Text textWidthAspectRatio) {
		this.textWidthAspectRatio = textWidthAspectRatio;
	}
	public Text getTextLengthAspectRatio() {
		return textLengthAspectRatio;
	}
	public void setTextLengthAspectRatio(Text textLengthAspectRatio) {
		this.textLengthAspectRatio = textLengthAspectRatio;
	}
	public Text getTextElementNumber() {
		return textElementNumber;
	}
	public void setTextElementNumber(Text textElementNumber) {
		this.textElementNumber = textElementNumber;
	}
	public Button getBtnCalcElementNum() {
		return btnCalcElementNum;
	}
	public void setBtnCalcElementNum(Button btnCalcElementNum) {
		this.btnCalcElementNum = btnCalcElementNum;
	}
	public ICommand getCustomBtnCalcElementNum() {
		return customBtnCalcElementNum;
	}
	public void setCustomBtnCalcElementNum(ICommand customBtnCalcElementNum) {
		this.customBtnCalcElementNum = customBtnCalcElementNum;
	}
	public Text getTextPlateVelocity() {
		return textPlateVelocity;
	}
	public void setTextPlateVelocity(Text textPlateVelocity) {
		this.textPlateVelocity = textPlateVelocity;
	}
	public Text getTextTemperatureStart() {
		return textTemperatureStart;
	}
	public void setTextTemperatureStart(Text textTemperatureStart) {
		this.textTemperatureStart = textTemperatureStart;
	}
	public Text getTextTemperatureEnd() {
		return textTemperatureEnd;
	}
	public void setTextTemperatureEnd(Text textTemperatureEnd) {
		this.textTemperatureEnd = textTemperatureEnd;
	}
	public Text getTextPassLineOffset() {
		return textPassLineOffset;
	}
	public void setTextPassLineOffset(Text textPassLineOffset) {
		this.textPassLineOffset = textPassLineOffset;
	}
	public Spinner getSpinnerUpperRollNum() {
		return spinnerUpperRollNum;
	}
	public void setSpinnerUpperRollNum(Spinner spinnerUpperRollNum) {
		this.spinnerUpperRollNum = spinnerUpperRollNum;
	}
	public ICommand getCustomSpinnerUpperRollNum() {
		return customSpinnerUpperRollNum;
	}
	public void setCustomSpinnerUpperRollNum(ICommand customSpinnerUpperRollNum) {
		this.customSpinnerUpperRollNum = customSpinnerUpperRollNum;
	}
	public Spinner getSpinnerLowerRollNum() {
		return spinnerLowerRollNum;
	}
	public void setSpinnerLowerRollNum(Spinner spinnerLowerRollNum) {
		this.spinnerLowerRollNum = spinnerLowerRollNum;
	}
	public ICommand getCustomSpinnerLowerRollNum() {
		return customSpinnerLowerRollNum;
	}
	public void setCustomSpinnerLowerRollNum(ICommand customSpinnerLowerRollNum) {
		this.customSpinnerLowerRollNum = customSpinnerLowerRollNum;
	}
	public Text getTextRollPitch() {
		return textRollPitch;
	}
	public void setTextRollPitch(Text textRollPitch) {
		this.textRollPitch = textRollPitch;
	}
	public Text getTextRollLength() {
		return textRollLength;
	}
	public void setTextRollLength(Text textRollLength) {
		this.textRollLength = textRollLength;
	}
	public Text getTextEntryRollTableDistance() {
		return textEntryRollTableDistance;
	}
	public void setTextEntryRollTableDistance(Text textEntryRollTableDistance) {
		this.textEntryRollTableDistance = textEntryRollTableDistance;
	}
	public Text getTextExitRollTableDistance() {
		return textExitRollTableDistance;
	}
	public void setTextExitRollTableDistance(Text textExitRollTableDistance) {
		this.textExitRollTableDistance = textExitRollTableDistance;
	}
	public Text getTextEntryUpperRollGap() {
		return textEntryUpperRollGap;
	}
	public void setTextEntryUpperRollGap(Text textEntryUpperRollGap) {
		this.textEntryUpperRollGap = textEntryUpperRollGap;
	}
	public Text getTextEntryLowerRollGap() {
		return textEntryLowerRollGap;
	}
	public void setTextEntryLowerRollGap(Text textEntryLowerRollGap) {
		this.textEntryLowerRollGap = textEntryLowerRollGap;
	}
	public Text getTextExitUpperRollGap() {
		return textExitUpperRollGap;
	}
	public void setTextExitUpperRollGap(Text textExitUpperRollGap) {
		this.textExitUpperRollGap = textExitUpperRollGap;
	}
	public Text getTextExitLowerRollGap() {
		return textExitLowerRollGap;
	}
	public void setTextExitLowerRollGap(Text textExitLowerRollGap) {
		this.textExitLowerRollGap = textExitLowerRollGap;
	}
	public Text getTextRollFriction() {
		return textRollFriction;
	}
	public void setTextRollFriction(Text textRollFriction) {
		this.textRollFriction = textRollFriction;
	}
	public Text getTextRollDiameter() {
		return textRollDiameter;
	}
	public void setTextRollDiameter(Text textRollDiameter) {
		this.textRollDiameter = textRollDiameter;
	}
	public Text getTextUpperRollCrown() {
		return textUpperRollCrown;
	}
	public void setTextUpperRollCrown(Text textUpperRollCrown) {
		this.textUpperRollCrown = textUpperRollCrown;
	}
	public Text getTextLowerRollCrown() {
		return textLowerRollCrown;
	}
	public void setTextLowerRollCrown(Text textLowerRollCrown) {
		this.textLowerRollCrown = textLowerRollCrown;
	}
	public Text getTextMillStiffness() {
		return textMillStiffness;
	}
	public void setTextMillStiffness(Text textMillStiffness) {
		this.textMillStiffness = textMillStiffness;
	}
	/*
	public Button getBtnRadioNone_RC() {
		return btnRadioNone_RC;
	}
	public void setBtnRadioNone_RC(Button btnRadioNone_RC) {
		this.btnRadioNone_RC = btnRadioNone_RC;
	}
	public ICommand getCustomBtnRadioNone_RC() {
		return customBtnRadioNone_RC;
	}
	public void setCustomBtnRadioNone_RC(ICommand customBtnRadioNone_RC) {
		this.customBtnRadioNone_RC = customBtnRadioNone_RC;
	}
	public Button getBtnRadioApply_RC() {
		return btnRadioApply_RC;
	}
	public void setBtnRadioApply_RC(Button btnRadioApply_RC) {
		this.btnRadioApply_RC = btnRadioApply_RC;
	}
	public ICommand getCustomBtnRadioApply_RC() {
		return customBtnRadioApply_RC;
	}
	public void setCustomBtnRadioApply_RC(ICommand customBtnRadioApply_RC) {
		this.customBtnRadioApply_RC = customBtnRadioApply_RC;
	}
	// */
	public Button getBtnRadioRigid_MS() {
		return btnRadioRigid_MS;
	}
	public void setBtnRadioRigid_MS(Button btnRadioRigid_MS) {
		this.btnRadioRigid_MS = btnRadioRigid_MS;
	}
	public ICommand getCustomBtnRadioRigid_MS() {
		return customBtnRadioRigid_MS;
	}
	public void setCustomBtnRadioRigid_MS(ICommand customBtnRadioRigid_MS) {
		this.customBtnRadioRigid_MS = customBtnRadioRigid_MS;
	}
	public Button getBtnRadioSpring_MS() {
		return btnRadioSpring_MS;
	}
	public void setBtnRadioSpring_MS(Button btnRadioSpring_MS) {
		this.btnRadioSpring_MS = btnRadioSpring_MS;
	}
	public ICommand getCustomBtnRadioSpring_MS() {
		return customBtnRadioSpring_MS;
	}
	public void setCustomBtnRadioSpring_MS(ICommand customBtnRadioSpring_MS) {
		this.customBtnRadioSpring_MS = customBtnRadioSpring_MS;
	}
	public Button getBtnCreateRoll() {
		return btnCreateRoll;
	}
	public void setBtnCreateRoll(Button btnCreateRoll) {
		this.btnCreateRoll = btnCreateRoll;
	}
	public ICommand getCustomBtnCreateRoll() {
		return customBtnCreateRoll;
	}
	public void setCustomBtnCreateRoll(ICommand customBtnCreateRoll) {
		this.customBtnCreateRoll = customBtnCreateRoll;
	}
	public Button getBtnUpperEdit() {
		return btnUpperEdit;
	}
	public void setBtnUpperEdit(Button btnUpperEdit) {
		this.btnUpperEdit = btnUpperEdit;
	}
	public ICommand getCustomBtnUpperEdit() {
		return customBtnUpperEdit;
	}
	public void setCustomBtnUpperEdit(ICommand customBtnUpperEdit) {
		this.customBtnUpperEdit = customBtnUpperEdit;
	}
	public Button getBtnUpperSave() {
		return btnUpperSave;
	}
	public void setBtnUpperSave(Button btnUpperSave) {
		this.btnUpperSave = btnUpperSave;
	}
	public ICommand getCustomBtnUpperSave() {
		return customBtnUpperSave;
	}
	public void setCustomBtnUpperSave(ICommand customBtnUpperSave) {
		this.customBtnUpperSave = customBtnUpperSave;
	}
	public TableViewer getTableViewerUpperRoll() {
		return tableViewerUpperRoll;
	}
	public void setTableViewerUpperRoll(TableViewer tableViewerUpperRoll) {
		this.tableViewerUpperRoll = tableViewerUpperRoll;
	}
	public ICommand getCustomTableViewerUpperRoll() {
		return customTableViewerUpperRoll;
	}
	public void setCustomTableViewerUpperRoll(ICommand customTableViewerUpperRoll) {
		this.customTableViewerUpperRoll = customTableViewerUpperRoll;
	}
	public Button getBtnLowerEdit() {
		return btnLowerEdit;
	}
	public void setBtnLowerEdit(Button btnLowerEdit) {
		this.btnLowerEdit = btnLowerEdit;
	}
	public ICommand getCustomBtnLowerEdit() {
		return customBtnLowerEdit;
	}
	public void setCustomBtnLowerEdit(ICommand customBtnLowerEdit) {
		this.customBtnLowerEdit = customBtnLowerEdit;
	}
	public Button getBtnLowerSave() {
		return btnLowerSave;
	}
	public void setBtnLowerSave(Button btnLowerSave) {
		this.btnLowerSave = btnLowerSave;
	}
	public ICommand getCustomBtnLowerSave() {
		return customBtnLowerSave;
	}
	public void setCustomBtnLowerSave(ICommand customBtnLowerSave) {
		this.customBtnLowerSave = customBtnLowerSave;
	}
	public TableViewer getTableViewerLowerRoll() {
		return tableViewerLowerRoll;
	}
	public void setTableViewerLowerRoll(TableViewer tableViewerLowerRoll) {
		this.tableViewerLowerRoll = tableViewerLowerRoll;
	}
	public ICommand getCustomTableViewerLowerRoll() {
		return customTableViewerLowerRoll;
	}
	public void setCustomTableViewerLowerRoll(ICommand customTableViewerLowerRoll) {
		this.customTableViewerLowerRoll = customTableViewerLowerRoll;
	}
	public Button getBtnSaveRoll() {
		return btnSaveRoll;
	}
	public void setBtnSaveRoll(Button btnSaveRoll) {
		this.btnSaveRoll = btnSaveRoll;
	}
	public ICommand getcustomBtnSaveRoll() {
		return customBtnSaveRoll;
	}
	public void setcustomBtnSaveRoll(ICommand customBtnSaveRoll) {
		this.customBtnSaveRoll = customBtnSaveRoll;
	}
	public Text getTextYoungsModulus() {
		return textYoungsModulus;
	}
	public void setTextYoungsModulus(Text textYoungsModulus) {
		this.textYoungsModulus = textYoungsModulus;
	}
	public Button getBtnExplorerYoungsModulus() {
		return btnExplorerYoungsModulus;
	}
	public void setBtnExplorerYoungsModulus(Button btnExplorerYoungsModulus) {
		this.btnExplorerYoungsModulus = btnExplorerYoungsModulus;
	}
	public ICommand getCustomBtnExplorerYoungsModulus() {
		return customBtnExplorerYoungsModulus;
	}
	public void setCustomBtnExplorerYoungsModulus(
			ICommand customBtnExplorerYoungsModulus) {
		this.customBtnExplorerYoungsModulus = customBtnExplorerYoungsModulus;
	}
	public Text getTextFlowStress() {
		return textFlowStress;
	}
	public void setTextFlowStress(Text textFlowStress) {
		this.textFlowStress = textFlowStress;
	}
	public Button getBtnExplorerFlowStress() {
		return btnExplorerFlowStress;
	}
	public void setBtnExplorerFlowStress(Button btnExplorerFlowStress) {
		this.btnExplorerFlowStress = btnExplorerFlowStress;
	}
	public ICommand getCustomBtnExplorerFlowStress() {
		return customBtnExplorerFlowStress;
	}
	public void setCustomBtnExplorerFlowStress(ICommand customBtnExplorerFlowStress) {
		this.customBtnExplorerFlowStress = customBtnExplorerFlowStress;
	}
	public Text getTextYieldStrength() {
		return textYieldStrength;
	}
	public void setTextYieldStrength(Text textYieldStrength) {
		this.textYieldStrength = textYieldStrength;
	}
	public Text getTextTensileStrength() {
		return textTensileStrength;
	}
	public void setTextTensileStrength(Text textTensileStrength) {
		this.textTensileStrength = textTensileStrength;
	}
	public Text getTextElongation() {
		return textElongation;
	}
	public void setTextElongation(Text textElongation) {
		this.textElongation = textElongation;
	}
	public Text getTextThermalExpansionCoefficient() {
		return textThermalExpansionCoefficient;
	}
	public void setTextThermalExpansionCoefficient(
			Text textThermalExpansionCoefficient) {
		this.textThermalExpansionCoefficient = textThermalExpansionCoefficient;
	}
	public Button getBtnExplorerThermalExpansionCoefficient() {
		return btnExplorerThermalExpansionCoefficient;
	}
	public void setBtnExplorerThermalExpansionCoefficient(
			Button btnExplorerThermalExpansionCoefficient) {
		this.btnExplorerThermalExpansionCoefficient = btnExplorerThermalExpansionCoefficient;
	}
	public ICommand getCustomBtnExplorerThermalExpansionCoefficient() {
		return customBtnExplorerThermalExpansionCoefficient;
	}
	public void setCustomBtnExplorerThermalExpansionCoefficient(
			ICommand customBtnExplorerThermalExpansionCoefficient) {
		this.customBtnExplorerThermalExpansionCoefficient = customBtnExplorerThermalExpansionCoefficient;
	}
	public Text getTextMassDensity() {
		return textMassDensity;
	}
	public void setTextMassDensity(Text textMassDensity) {
		this.textMassDensity = textMassDensity;
	}
	
	public Button getBtnRadioConstant_YM() {
		return btnRadioConstant_YM;
	}
	public void setBtnRadioConstant_YM(Button btnRadioConstant_YM) {
		this.btnRadioConstant_YM = btnRadioConstant_YM;
	}
	public ICommand getCustomBtnRadioConstant_YM() {
		return customBtnRadioConstant_YM;
	}
	public void setCustomBtnRadioConstant_YM(ICommand customBtnRadioConstant_YM) {
		this.customBtnRadioConstant_YM = customBtnRadioConstant_YM;
	}
	public Button getBtnRadioTable_YM() {
		return btnRadioTable_YM;
	}
	public void setBtnRadioTable_YM(Button btnRadioTable_YM) {
		this.btnRadioTable_YM = btnRadioTable_YM;
	}
	public ICommand getCustomBtnRadioTable_YM() {
		return customBtnRadioTable_YM;
	}
	public void setCustomBtnRadioTable_YM(ICommand customBtnRadioTable_YM) {
		this.customBtnRadioTable_YM = customBtnRadioTable_YM;
	}
	
	
	
	public Button getBtnRadioConstant_FS() {
		return btnRadioConstant_FS;
	}
	public void setBtnRadioConstant_FS(Button btnRadioConstant_FS) {
		this.btnRadioConstant_FS = btnRadioConstant_FS;
	}
	public ICommand getCustomBtnRadioConstant_FS() {
		return customBtnRadioConstant_FS;
	}
	public void setCustomBtnRadioConstant_FS(ICommand customBtnRadioConstant_FS) {
		this.customBtnRadioConstant_FS = customBtnRadioConstant_FS;
	}
	public Button getBtnRadioTable_FS() {
		return btnRadioTable_FS;
	}
	public void setBtnRadioTable_FS(Button btnRadioTable_FS) {
		this.btnRadioTable_FS = btnRadioTable_FS;
	}
	public ICommand getCustomBtnRadioTable_FS() {
		return customBtnRadioTable_FS;
	}
	public void setCustomBtnRadioTable_FS(ICommand customBtnRadioTable_FS) {
		this.customBtnRadioTable_FS = customBtnRadioTable_FS;
	}
	
	
	
	public Button getBtnRadioConstant_TEC() {
		return btnRadioConstant_TEC;
	}
	public void setBtnRadioConstant_TEC(Button btnRadioConstant_TEC) {
		this.btnRadioConstant_TEC = btnRadioConstant_TEC;
	}
	public ICommand getCustomBtnRadioConstant_TEC() {
		return customBtnRadioConstant_TEC;
	}          
	public void setCustomBtnRadioConstant_TEC(ICommand customBtnRadioConstant_TEC) {
		this.customBtnRadioConstant_TEC = customBtnRadioConstant_TEC;
	}
	public Button getBtnRadioTable_TEC() {
		return btnRadioTable_TEC;
	}
	public void setBtnRadioTable_TEC(Button btnRadioTable_TEC) {
		this.btnRadioTable_TEC = btnRadioTable_TEC;
	}
	public ICommand getCustomBtnRadioTable_TEC() {
		return customBtnRadioTable_TEC;
	}
	public void setCustomBtnRadioTable_TEC(ICommand customBtnRadioTable_TEC) {
		this.customBtnRadioTable_TEC = customBtnRadioTable_TEC;
	}
	
	
	
	public Button getBtnRadioConstant_PR() {
		return btnRadioConstant_PR;
	}
	public void setBtnRadioConstant_PR(Button btnRadioConstant_PR) {
		this.btnRadioConstant_PR = btnRadioConstant_PR;
	}
	public ICommand getCustomBtnRadioConstant_PR() {
		return customBtnRadioConstant_PR;
	}
	public void setCustomBtnRadioConstant_PR(ICommand customBtnRadioConstant_PR) {
		this.customBtnRadioConstant_PR = customBtnRadioConstant_PR;
	}
	public Button getBtnRadioTable_PR() {
		return btnRadioTable_PR;
	}
	public void setBtnRadioTable_PR(Button btnRadioTable_PR) {
		this.btnRadioTable_PR = btnRadioTable_PR;
	}
	public ICommand getCustomBtnRadioTable_PR() {
		return customBtnRadioTable_PR;
	}
	public void setCustomBtnRadioTable_PR(ICommand customBtnRadioTable_PR) {
		this.customBtnRadioTable_PR = customBtnRadioTable_PR;
	}

	
	
	
	
	public Text getTextPoissonsRatio() {
		return textPoissonsRatio;
	}
	public void setTextPoissonsRatio(Text textPoissonsRatio) {
		this.textPoissonsRatio = textPoissonsRatio;
	}
	public Button getBtnExplorerPoissonsRatio() {
		return btnExplorerPoissonsRatio;
	}
	public void setBtnExplorerPoissonsRatio(Button btnExplorerPoissonsRatio) {
		this.btnExplorerPoissonsRatio = btnExplorerPoissonsRatio;
	}
	public ICommand getCustomBtnExplorerPoissonsRatio() {
		return customBtnExplorerPoissonsRatio;
	}
	public void setCustomBtnExplorerPoissonsRatio(ICommand customBtnExplorerPoissonsRatio) {
		this.customBtnExplorerPoissonsRatio = customBtnExplorerPoissonsRatio;
	}
	
	public Text getTextSolvingTime() {
		return textSolvingTime;
	}
	public void setTextSolvingTime(Text textSolvingTime) {
		this.textSolvingTime = textSolvingTime;
	}
	public Text getTextIncrementTime() {
		return textIncrementTime;
	}
	public void setTextIncrementTime(Text textIncrementTime) {
		this.textIncrementTime = textIncrementTime;
	}
	public Button getBtnParallelDDMUse() {
		return btnParallelDDMUse;
	}
	public void setBtnParallelDDMUse(Button btnParallelDDMUse) {
		this.btnParallelDDMUse = btnParallelDDMUse;
	}
	public ICommand getCustomBtnParallelDDMUse() {
		return customBtnParallelDDMUse;
	}
	public void setCustomBtnParallelDDMUse(ICommand customBtnParallelDDMUse) {
		this.customBtnParallelDDMUse = customBtnParallelDDMUse;
	}
	public Spinner getSpinnerDomain() {
		return spinnerDomain;
	}
	public void setSpinnerDomain(Spinner spinnerDomain) {
		this.spinnerDomain = spinnerDomain;
	}
	public ICommand getCustomSpinnerDomain() {
		return customSpinnerDomain;
	}
	public void setCustomSpinnerDomain(ICommand customSpinnerDomain) {
		this.customSpinnerDomain = customSpinnerDomain;
	}
	public Button getBtnParallelMultiThreadUse() {
		return btnParallelMultiThreadUse;
	}
	public void setBtnParallelMultiThreadUse(Button btnParallelMultiThreadUse) {
		this.btnParallelMultiThreadUse = btnParallelMultiThreadUse;
	}
	public ICommand getCustomBtnParallelMultiThreadUse() {
		return customBtnParallelMultiThreadUse;
	}
	public void setCustomBtnParallelMultiThreadUse(ICommand customBtnParallelMultiThreadUse) {
		this.customBtnParallelMultiThreadUse = customBtnParallelMultiThreadUse;
	}
	public Spinner getSpinnerThread() {
		return spinnerThread;
	}
	public void setSpinnerThread(Spinner spinnerThread) {
		this.spinnerThread = spinnerThread;
	}
	public ICommand getCustomSpinnerThread() {
		return customSpinnerThread;
	}
	public void setCustomSpinnerThread(ICommand customSpinnerThread) {
		this.customSpinnerThread = customSpinnerThread;
	}
	//--------------------------------------------
	//--------------------------------------------
	public Button getBtnApply() {
		return btnApply;
	}
	public void setBtnApply(Button btnApply) {
		this.btnApply = btnApply;
	}
	public ICommand getCustomBtnApply() {
		return customBtnApply;
	}
	public void setCustomBtnApply(ICommand customBtnApply) {
		this.customBtnApply = customBtnApply;
	}
	public Text getTextUpperEntryRollGapMovement() {
		return textUpperEntryRollGapMovement;
	}
	public void setTextUpperEntryRollGapMovement(Text textUpperEntryRollGapMovement) {
		this.textUpperEntryRollGapMovement = textUpperEntryRollGapMovement;
	}
	public Text getTextUpperExitRollGapMovement() {
		return textUpperExitRollGapMovement;
	}
	public void setTextUpperExitRollGapMovement(Text textUpperExitRollGapMovement) {
		this.textUpperExitRollGapMovement = textUpperExitRollGapMovement;
	}
	public Text getTextUpperRollGapStayingTime() {
		return textUpperRollGapStayingTime;
	}
	public void setTextUpperRollGapStayingTime(Text textUpperRollGapStayingTime) {
		this.textUpperRollGapStayingTime = textUpperRollGapStayingTime;
	}
	public Text getTextUpperRollGapMovingTime() {
		return textUpperRollGapMovingTime;
	}
	public void setTextUpperRollGapMovingTime(Text textUpperRollGapMovingTime) {
		this.textUpperRollGapMovingTime = textUpperRollGapMovingTime;
	}
	public Text getTextLowerEntryRollGapMovement() {
		return textLowerEntryRollGapMovement;
	}
	public void setTextLowerEntryRollGapMovement(Text textLowerEntryRollGapMovement) {
		this.textLowerEntryRollGapMovement = textLowerEntryRollGapMovement;
	}
	public Text getTextLowerExitRollGapMovement() {
		return textLowerExitRollGapMovement;
	}
	public void setTextLowerExitRollGapMovement(Text textLowerExitRollGapMovement) {
		this.textLowerExitRollGapMovement = textLowerExitRollGapMovement;
	}
	public Text getTextLowerRollGapStayingTime() {
		return textLowerRollGapStayingTime;
	}
	public void setTextLowerRollGapStayingTime(Text textLowerRollGapStayingTime) {
		this.textLowerRollGapStayingTime = textLowerRollGapStayingTime;
	}
	public Text getTextLowerRollGapMovingTime() {
		return textLowerRollGapMovingTime;
	}
	public void setTextLowerRollGapMovingTime(Text textLowerRollGapMovingTime) {
		this.textLowerRollGapMovingTime = textLowerRollGapMovingTime;
	}
	public Text getTextFrontHDRollDia() {
		return textFrontHDRollDia;
	}
	public void setTextFrontHDRollDia(Text textFrontHDRollDia) {
		this.textFrontHDRollDia = textFrontHDRollDia;
	}
	public Text getTextFrontHDRollPitch() {
		return textFrontHDRollPitch;
	}
	public void setTextFrontHDRollPitch(Text textFrontHDRollPitch) {
		this.textFrontHDRollPitch = textFrontHDRollPitch;
	}
	public Text getTextFrontHDRollVericalPos() {
		return textFrontHDRollVericalPos;
	}
	public void setTextFrontHDRollVericalPos(Text textFrontHDRollVericalPos) {
		this.textFrontHDRollVericalPos = textFrontHDRollVericalPos;
	}
	public Text getTextRearHDRollDia() {
		return textRearHDRollDia;
	}
	public void setTextRearHDRollDia(Text textRearHDRollDia) {
		this.textRearHDRollDia = textRearHDRollDia;
	}
	public Text getTextRearHDRollPitch() {
		return textRearHDRollPitch;
	}
	public void setTextRearHDRollPitch(Text textRearHDRollPitch) {
		this.textRearHDRollPitch = textRearHDRollPitch;
	}
	public Text getTextRearHDRollVerticalPos() {
		return textRearHDRollVerticalPos;
	}
	public void setTextRearHDRollVerticalPos(Text textRearHDRollVerticalPos) {
		this.textRearHDRollVerticalPos = textRearHDRollVerticalPos;
	}
	public Button getBtnNone() {
		return btnNone;
	}
	public void setBtnNone(Button btnNone) {
		this.btnNone = btnNone;
	}
	public ICommand getCustomBtnNone() {
		return customBtnNone;
	}
	public void setCustomBtnNone(ICommand customBtnNone) {
		this.customBtnNone = customBtnNone;
	}
	public Button getBtnUpper() {
		return btnUpper;
	}
	public void setBtnUpper(Button btnUpper) {
		this.btnUpper = btnUpper;
	}
	public ICommand getCustomBtnUpper() {
		return customBtnUpper;
	}
	public void setCustomBtnUpper(ICommand customBtnUpper) {
		this.customBtnUpper = customBtnUpper;
	}
	public Button getBtnLower() {
		return btnLower;
	}
	public void setBtnLower(Button btnLower) {
		this.btnLower = btnLower;
	}
	public ICommand getCustomBtnLower() {
		return customBtnLower;
	}
	public void setCustomBtnLower(ICommand customBtnLower) {
		this.customBtnLower = customBtnLower;
	}
	public Button getBtnShowRollTable() {
		return btnShowRollTable;
	}
	public void setBtnShowRollTable(Button btnShowRollTable) {
		this.btnShowRollTable = btnShowRollTable;
	}
	public ICommand getCustomBtnShowRollTable() {
		return customBtnShowRollTable;
	}
	public void setCustomBtnShowRollTable(ICommand customBtnShowRollTable) {
		this.customBtnShowRollTable = customBtnShowRollTable;
	}
	//=======================================================================================================
	//=======================================================================================================
	//=======================================================================================================
	//=======================================================================================================
	//=======================================================================================================
	//=======================================================================================================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//--------------------------------------------	
		private Composite compositeShapeParameter_2D; 
		
		private Combo comboType_2D;
		public ICommand customComboType_2D;
		public static String COMBO_Type_2D = "comboType_2D";
		private Label lblPhoto_2D;
		//private Text textWidth_2D;
		private Text textLength_2D;
		private Text textThickness_2D;
		
		private Composite compositeShapeParameterChild_1_2D;
		               
		private Composite compositeShapeParameterChild_2_2D;
		/*
		private Text type2_textLeftEdgeWavePitch_2D;
		private Text type2_textRightEdgeWavePitch_2D;
		private Text type2_textLeftEdgeWaveHeight_2D;
		private Text type2_textRightEdgeWaveHeight_2D;
		private Text type2_textLeftEdgeWavePhase_2D;
		private Text type2_textRightEdgeWavePhase_2D;
		//*/
		private Text type2_textWavePitch_2D;
		private Text type2_textWaveHeight_2D;
		private Text type2_textWavePhase_2D;
		
		
		private Composite compositeShapeParameterChild_3_2D;
		/*
		private Text type3_textWavePitch_2D;
		private Text type3_textWaveHeight_2D;
		//*/
		private Text type3_textFrontCurlHeight_2D;
		private Text type3_textFrontCurlLength_2D;
		private Text type3_textRearCurlHeight_2D;
		private Text type3_textRearCurlLength_2D;
		
		/*
		private Composite compositeShapeParameterChild_4_2D;
		private Text type4_textGutterHeight_2D;
		
		private Composite compositeShapeParameterChild_5_2D;
		private Text type5_textGutterHeight_2D;
		private Text type5_textGutterLength_2D;
		
		private Composite compositeShapeParameterChild_6_2D;
		private Text type6_textHeadGutterHeight_2D;
		private Text type6_textHeadGutterLength_2D;
		private Text type6_textTailGutterHeight_2D;
		private Text type6_textTailGutterLength_2D;
		
		private Composite compositeShapeParameterChild_7_2D;
		private Text type7_textHeadGutterHeight_2D;
		private Text type7_textGutterLength_2D;
		private Text type7_textGutterLengthLength_2D;
		private Text type7_textGutterWidthLength_2D;
		//*/
		
		//--------------------------------------------
		private Composite compositeMeshParameter_2D;
		
		private Text textThicknessElementNum_2D;
		//private Text textWidthAspectRatio_2D;
		private Text textLengthAspectRatio_2D;
		private Text textElementNumber_2D;
		private Button btnCalcElementNum_2D;
		public ICommand customBtnCalcElementNum_2D;
		public static String BUTTON_CalcElementNum_2D = "btnCalcElementNum_2D";
		
		//--------------------------------------------
		private Composite compositePlateInformation_2D;

		private Text textPlateVelocity_2D;
		private Text textTemperatureStart_2D;
		private Text textTemperatureEnd_2D;
		private Text textPassLineOffset_2D;
		
		//--------------------------------------------
		private Composite compositeRollParameter_2D;
		
		private Spinner spinnerUpperRollNum_2D;
		public ICommand customSpinnerUpperRollNum_2D;
		public static String SPINNER_UpperRollNum_2D = "spinnerUpperRollNum_2D";
		private Spinner spinnerLowerRollNum_2D;
		public ICommand customSpinnerLowerRollNum_2D;
		public static String SPINNER_LowerRollNum_2D = "spinnerLowerRollNum_2D";
		private Text textRollPitch_2D;
		//private Text textRollLength_2D;
		private Text textEntryRollTableDistance_2D;
		private Text textExitRollTableDistance_2D;
		
		private Text textEntryUpperRollGap_2D;
		private Text textEntryLowerRollGap_2D;
		private Text textExitUpperRollGap_2D;
		private Text textExitLowerRollGap_2D;
		
		private Text textUpperEntryRollGapMovement_2D;
		private Text textUpperExitRollGapMovement_2D;
		private Text textUpperRollGapStayingTime_2D;
		private Text textUpperRollGapMovingTime_2D;
		private Text textLowerEntryRollGapMovement_2D;
		private Text textLowerExitRollGapMovement_2D;
		private Text textLowerRollGapStayingTime_2D;
		private Text textLowerRollGapMovingTime_2D;
		
		private Text textFrontHDRollDia_2D;
		private Text textFrontHDRollPitch_2D;
		private Text textFrontHDRollVericalPos_2D;
		private Text textRearHDRollDia_2D;
		private Text textRearHDRollPitch_2D;
		private Text textRearHDRollVerticalPos_2D;
		
		private Button btnNone_2D;
		public ICommand customBtnNone_2D;
		public static String BUTTON_btnNone_2D = "btnNone_2D";
		
		private Button btnUpper_2D;
		public ICommand customBtnUpper_2D;
		public static String BUTTON_btnUpper_2D = "btnUpper_2D";
		
		private Button btnLower_2D;
		public ICommand customBtnLower_2D;
		public static String BUTTON_btnLower_2D = "btnLower_2D";
		
		private Button btnShowRollTable_2D;
		public ICommand customBtnShowRollTable_2D;
		public static String BUTTON_btnShowRollTable_2D = "btnShowRollTable_2D";
		
		private Text textRollFriction_2D;
		private Text textRollDiameter_2D;
		//private Text textRollCrown_2D;
		//private Text textUpperRollCrown_2D;
		//private Text textLowerRollCrown_2D;
		private Text textMillStiffness_2D;
		/*
		private Button btnRadioNone_RC_2D;
		public ICommand customBtnRadioNone_RC_2D;
		public static String BUTTON_RadioNone_RC_2D = "btnRadioNone_RC_2D";
		
		private Button btnRadioApply_RC_2D;
		public ICommand customBtnRadioApply_RC_2D;
		public static String BUTTON_RadioApply_RC_2D = "btnRadioApply_RC_2D";
		// */
		private Button btnRadioRigid_MS_2D;
		public ICommand customBtnRadioRigid_MS_2D;
		public static String BUTTON_RadioRigid_MS_2D = "btnRadioRigid_MS_2D";
		
		private Button btnRadioSpring_MS_2D;
		public ICommand customBtnRadioSpring_MS_2D;
		public static String BUTTON_RadioSpring_MS_2D = "btnRadioSpring_MS_2D";
		
		private Button btnCreateRoll_2D;
		public ICommand customBtnCreateRoll_2D;
		public static String BUTTON_CreateRoll_2D = "btnCreateRoll_2D";
		private Button btnUpperEdit_2D;
		public ICommand customBtnUpperEdit_2D;
		public static String BUTTON_UpperEdit_2D = "btnUpperEdit_2D";
		private Button btnUpperSave_2D;
		public ICommand customBtnUpperSave_2D;
		public static String BUTTON_UpperSave_2D = "btnUpperSave_2D";
		private TableViewer tableViewerUpperRoll_2D;
		public ICommand customTableViewerUpperRoll_2D;
		public static String TABLEVIEWER_UpperRoll_2D = "tableViewerUpperRoll_2D";
		private Button btnLowerEdit_2D;
		public ICommand customBtnLowerEdit_2D;
		public static String BUTTON_LowerEdit_2D = "btnLowerEdit_2D";
		private Button btnLowerSave_2D;
		public ICommand customBtnLowerSave_2D;
		public static String BUTTON_LowerSave_2D = "btnLowerSave_2D";
		private TableViewer tableViewerLowerRoll_2D;
		public ICommand customTableViewerLowerRoll_2D;
		public static String TABLEVIEWER_LowerRoll_2D = "tableViewerLowerRoll_2D";
		public Button btnSaveRoll_2D;
		private ICommand customBtnSaveRoll_2D;
		public static String BUTTON_SaveRoll_2D = "btnRollSave_2D";
		//--------------------------------------------
		private Composite compositeMaterialParameter_2D;
		
		private Text textYoungsModulus_2D;
		private Button btnExplorerYoungsModulus_2D;
		public ICommand customBtnExplorerYoungsModulus_2D;
		public static String BUTTON_ExplorerYoungsModulus_2D = "btnExplorerYoungsModulus_2D";
		
		private Button btnRadioConstant_YM_2D;
		public ICommand customBtnRadioConstant_YM_2D;
		public static String BUTTON_RadioConstant_YM_2D = "btnRadioConstant_YM_2D";
		
		private Button btnRadioTable_YM_2D;
		public ICommand customBtnRadioTable_YM_2D;
		public static String BUTTON_RadioTable_YM_2D = "btnRadioTable_YM_2D";
		
		private Text textFlowStress_2D;
		private Button btnExplorerFlowStress_2D;
		public ICommand customBtnExplorerFlowStress_2D;
		public static String BUTTON_ExplorerFlowStress_2D = "btnExplorerFlowStress_2D";
		
		private Text textYieldStrength_2D;
		private Text textTensileStrength_2D;
		private Text textElongation_2D;
		
		private Button btnRadioConstant_FS_2D;
		public ICommand customBtnRadioConstant_FS_2D;
		public static String BUTTON_RadioConstant_FS_2D = "btnRadioConstant_FS_2D";
		
		private Button btnRadioTable_FS_2D;
		public ICommand customBtnRadioTable_FS_2D;
		public static String BUTTON_RadioTable_FS_2D = "btnRadioTable_FS_2D";
		
		private Text textThermalExpansionCoefficient_2D;
		private Button btnExplorerThermalExpansionCoefficient_2D;
		public ICommand customBtnExplorerThermalExpansionCoefficient_2D;
		public static String BUTTON_ExplorerThermalExpansionCoefficient_2D = "btnExplorerThermalExpansionCoefficient_2D";
		
		private Button btnRadioConstant_TEC_2D;
		public ICommand customBtnRadioConstant_TEC_2D;
		public static String BUTTON_RadioConstant_TEC_2D = "btnRadioConstant_TEC_2D";
		
		private Button btnRadioTable_TEC_2D;
		public ICommand customBtnRadioTable_TEC_2D;
		public static String BUTTON_RadioTable_TEC_2D = "btnRadioTable_TEC_2D";
		
		private Text textPoissonsRatio_2D;
		private Button btnExplorerPoissonsRatio_2D;
		public ICommand customBtnExplorerPoissonsRatio_2D;
		public static String BUTTON_ExplorerPoissonsRatio_2D = "btnExplorerPoissonsRatio_2D";
		
		private Button btnRadioConstant_PR_2D;
		public ICommand customBtnRadioConstant_PR_2D;
		public static String BUTTON_RadioConstant_PR_2D = "btnRadioConstant_PR_2D";
		
		private Button btnRadioTable_PR_2D;
		public ICommand customBtnRadioTable_PR_2D;
		public static String BUTTON_RadioTable_PR_2D = "btnRadioTable_PR_2D";
		
		private Text textMassDensity_2D;
		
		private Button btnApply_2D;
		public ICommand customBtnApply_2D;
		public static String BUTTON_Apply_2D = "btnApply_2D";
		
		
		//--------------------------------------------
		private Composite compositeSolvingOption_2D;
		
		private Text textSolvingTime_2D;
		private Text textIncrementTime_2D;
		
		private Button btnParallelDDMUse_2D;
		public ICommand customBtnParallelDDMUse_2D;
		public static String BUTTON_ParallelDDMUse_2D = "btnParallelDDMUse_2D";
		
		private Spinner spinnerDomain_2D;
		public ICommand customSpinnerDomain_2D;
		public static String SPINNER_Domain_2D = "spinnerDomain_2D";
		
		private Button btnParallelMultiThreadUse_2D;
		public ICommand customBtnParallelMultiThreadUse_2D;
		public static String BUTTON_ParallelMultiThreadUse_2D ="btnParallelMultiThreadUse_2D";
		
		private Spinner spinnerThread_2D;
		public ICommand customSpinnerThread_2D;
		public static String SPINNER_Thread_2D = "spinnerThread_2D";
		
		
		
		//--------------------------------------------
		//--------------------------------------------
		//--------------------------------------------
		//--------------------------------------------
		
		
		
		public Composite getCompositeShapeParameter_2D() {
			return compositeShapeParameter_2D;
		}
		public void setCompositeShapeParameter_2D(Composite compositeShapeParameter_2D) {
			this.compositeShapeParameter_2D = compositeShapeParameter_2D;
		}
		public Combo getComboType_2D() {
			return comboType_2D;
		}
		public void setComboType_2D(Combo comboType_2D) {
			this.comboType_2D = comboType_2D;
		}
		public ICommand getCustomComboType_2D() {
			return customComboType_2D;
		}
		public void setCustomComboType_2D(ICommand customComboType_2D) {
			this.customComboType_2D = customComboType_2D;
		}
		public Label getLblPhoto_2D() {
			return lblPhoto_2D;
		}
		public void setLblPhoto_2D(Label lblPhoto_2D) {
			this.lblPhoto_2D = lblPhoto_2D;
		}
		/*
		public Text getTextWidth_2D() {
			return textWidth_2D;
		}
		public void setTextWidth_2D(Text textWidth_2D) {
			this.textWidth_2D = textWidth_2D;
		}
		//*/
		public Text getTextLength_2D() {
			return textLength_2D;
		}
		public void setTextLength_2D(Text textLength_2D) {
			this.textLength_2D = textLength_2D;
		}
		public Text getTextThickness_2D() {
			return textThickness_2D;
		}
		public void setTextThickness_2D(Text textThickness_2D) {
			this.textThickness_2D = textThickness_2D;
		}
		/*
		public Text getType2_textLeftEdgeWavePitch_2D() {
			return type2_textLeftEdgeWavePitch_2D;
		}
		public void setType2_textLeftEdgeWavePitch_2D(
				Text type2_textLeftEdgeWavePitch_2D) {
			this.type2_textLeftEdgeWavePitch_2D = type2_textLeftEdgeWavePitch_2D;
		}
		public Text getType2_textRightEdgeWavePitch_2D() {
			return type2_textRightEdgeWavePitch_2D;
		}
		public void setType2_textRightEdgeWavePitch_2D(
				Text type2_textRightEdgeWavePitch_2D) {
			this.type2_textRightEdgeWavePitch_2D = type2_textRightEdgeWavePitch_2D;
		}
		public Text getType2_textLeftEdgeWaveHeight_2D() {
			return type2_textLeftEdgeWaveHeight_2D;
		}
		public void setType2_textLeftEdgeWaveHeight_2D(
				Text type2_textLeftEdgeWaveHeight_2D) {
			this.type2_textLeftEdgeWaveHeight_2D = type2_textLeftEdgeWaveHeight_2D;
		}
		public Text getType2_textRightEdgeWaveHeight_2D() {
			return type2_textRightEdgeWaveHeight_2D;
		}
		public void setType2_textRightEdgeWaveHeight_2D(
				Text type2_textRightEdgeWaveHeight_2D) {
			this.type2_textRightEdgeWaveHeight_2D = type2_textRightEdgeWaveHeight_2D;
		}
		public Text getType2_textLeftEdgeWavePhase_2D() {
			return type2_textLeftEdgeWavePhase_2D;
		}
		public void setType2_textLeftEdgeWavePhase_2D(
				Text type2_textLeftEdgeWavePhase_2D) {
			this.type2_textLeftEdgeWavePhase_2D = type2_textLeftEdgeWavePhase_2D;
		}
		public Text getType2_textRightEdgeWavePhase_2D() {
			return type2_textRightEdgeWavePhase_2D;
		}
		public void setType2_textRightEdgeWavePhase_2D(
				Text type2_textRightEdgeWavePhase_2D) {
			this.type2_textRightEdgeWavePhase_2D = type2_textRightEdgeWavePhase_2D;
		}
		//*/
		public Text getType2_textWavePitch_2D() {
			return type2_textWavePitch_2D;
		}
		public void setType2_textWavePitch_2D(Text type2_textWavePitch_2D) {
			this.type2_textWavePitch_2D = type2_textWavePitch_2D;
		}
		public Text getType2_textWaveHeight_2D() {
			return type2_textWaveHeight_2D;
		}
		public void setType2_textWaveHeight_2D(Text type2_textWaveHeight_2D) {
			this.type2_textWaveHeight_2D = type2_textWaveHeight_2D;
		}
		public Text getType2_textWavePhase_2D() {
			return type2_textWavePhase_2D;
		}
		public void setType2_textWavePhase_2D(Text type2_textwavePhase_2D) {
			this.type2_textWavePhase_2D = type2_textwavePhase_2D;
		}
		/*
		public Text getType3_textWavePitch_2D() {
			return type3_textWavePitch_2D;
		}
		public void setType3_textWavePitch_2D(Text type3_textWavePitch_2D) {
			this.type3_textWavePitch_2D = type3_textWavePitch_2D;
		}
		public Text getType3_textWaveHeight_2D() {
			return type3_textWaveHeight_2D;
		}
		public void setType3_textWaveHeight_2D(Text type3_textWaveHeight_2D) {
			this.type3_textWaveHeight_2D = type3_textWaveHeight_2D;
		}
		//*/
		public Text getType3_textFrontCurlHeight_2D() {
			return type3_textFrontCurlHeight_2D;
		}
		public void setType3_textFrontCurlHeight_2D(Text type3_textFrontCurlHeight_2D) {
			this.type3_textFrontCurlHeight_2D = type3_textFrontCurlHeight_2D;
		}
		public Text getType3_textFrontCurlLength_2D() {
			return type3_textFrontCurlLength_2D;
		}
		public void setType3_textFrontCurlLength_2D(Text type3_textFrontCurlLength_2D) {
			this.type3_textFrontCurlLength_2D = type3_textFrontCurlLength_2D;
		}
		public Text getType3_textRearCurlHeight_2D() {
			return type3_textRearCurlHeight_2D;
		}
		public void setType3_textRearCurlHeight_2D(Text type3_textRearCurlHeight_2D) {
			this.type3_textRearCurlHeight_2D = type3_textRearCurlHeight_2D;
		}
		public Text getType3_textRearCurlLength_2D() {
			return type3_textRearCurlLength_2D;
		}
		public void setType3_textRearCurlLength_2D(Text type3_textRearCurlLength_2D) {
			this.type3_textRearCurlLength_2D = type3_textRearCurlLength_2D;
		}
		/*
		public Text getType4_textGutterHeight_2D() {
			return type4_textGutterHeight_2D;
		}
		public void setType4_textGutterHeight_2D(Text type4_textGutterHeight_2D) {
			this.type4_textGutterHeight_2D = type4_textGutterHeight_2D;
		}
		public Text getType5_textGutterHeight_2D() {
			return type5_textGutterHeight_2D;
		}
		public void setType5_textGutterHeight_2D(Text type5_textGutterHeight_2D) {
			this.type5_textGutterHeight_2D = type5_textGutterHeight_2D;
		}
		public Text getType5_textGutterLength_2D() {
			return type5_textGutterLength_2D;
		}
		public void setType5_textGutterLength_2D(Text type5_textGutterLength_2D) {
			this.type5_textGutterLength_2D = type5_textGutterLength_2D;
		}
		public Text getType6_textHeadGutterHeight_2D() {
			return type6_textHeadGutterHeight_2D;
		}
		public void setType6_textHeadGutterHeight_2D(Text type6_textHeadGutterHeight_2D) {
			this.type6_textHeadGutterHeight_2D = type6_textHeadGutterHeight_2D;
		}
		public Text getType6_textHeadGutterLength_2D() {
			return type6_textHeadGutterLength_2D;
		}
		public void setType6_textHeadGutterLength_2D(Text type6_textHeadGutterLength_2D) {
			this.type6_textHeadGutterLength_2D = type6_textHeadGutterLength_2D;
		}
		public Text getType6_textTailGutterHeight_2D() {
			return type6_textTailGutterHeight_2D;
		}
		public void setType6_textTailGutterHeight_2D(Text type6_textTailGutterHeight_2D) {
			this.type6_textTailGutterHeight_2D = type6_textTailGutterHeight_2D;
		}
		public Text getType6_textTailGutterLength_2D() {
			return type6_textTailGutterLength_2D;
		}
		public void setType6_textTailGutterLength_2D(Text type6_textTailGutterLength_2D) {
			this.type6_textTailGutterLength_2D = type6_textTailGutterLength_2D;
		}
		public Text getType7_textHeadGutterHeight_2D() {
			return type7_textHeadGutterHeight_2D;
		}
		public void setType7_textHeadGutterHeight_2D(Text type7_textHeadGutterHeight_2D) {
			this.type7_textHeadGutterHeight_2D = type7_textHeadGutterHeight_2D;
		}
		public Text getType7_textGutterLength_2D() {
			return type7_textGutterLength_2D;
		}
		public void setType7_textGutterLength_2D(Text type7_textGutterLength_2D) {
			this.type7_textGutterLength_2D = type7_textGutterLength_2D;
		}
		public Text getType7_textGutterLengthLength_2D() {
			return type7_textGutterLengthLength_2D;
		}
		public void setType7_textGutterLengthLength_2D(
				Text type7_textGutterLengthLength_2D) {
			this.type7_textGutterLengthLength_2D = type7_textGutterLengthLength_2D;
		}
		public Text getType7_textGutterWidthLength_2D() {
			return type7_textGutterWidthLength_2D;
		}
		public void setType7_textGutterWidthLength_2D(
				Text type7_textGutterWidthLength_2D) {
			this.type7_textGutterWidthLength_2D = type7_textGutterWidthLength_2D;
		}
		// */
		public Composite getCompositeMeshParameter_2D() {
			return compositeMeshParameter_2D;
		}
		public void setCompositeMeshParameter_2D(Composite compositeMeshParameter_2D) {
			this.compositeMeshParameter_2D = compositeMeshParameter_2D;
		}
		public Text getTextThicknessElementNum_2D() {
			return textThicknessElementNum_2D;
		}
		public void setTextThicknessElementNum_2D(Text textThicknessElementNum_2D) {
			this.textThicknessElementNum_2D = textThicknessElementNum_2D;
		}
		/*
		public Text getTextWidthAspectRatio_2D() {
			return textWidthAspectRatio_2D;
		}
		public void setTextWidthAspectRatio_2D(Text textWidthAspectRatio_2D) {
			this.textWidthAspectRatio_2D = textWidthAspectRatio_2D;
		}
		// */
		public Text getTextLengthAspectRatio_2D() {
			return textLengthAspectRatio_2D;
		}
		public void setTextLengthAspectRatio_2D(Text textLengthAspectRatio_2D) {
			this.textLengthAspectRatio_2D = textLengthAspectRatio_2D;
		}
		public Text getTextElementNumber_2D() {
			return textElementNumber_2D;
		}
		public void setTextElementNumber_2D(Text textElementNumber_2D) {
			this.textElementNumber_2D = textElementNumber_2D;
		}
		public Button getBtnCalcElementNum_2D() {
			return btnCalcElementNum_2D;
		}
		public void setBtnCalcElementNum_2D(Button btnCalcElementNum_2D) {
			this.btnCalcElementNum_2D = btnCalcElementNum_2D;
		}
		public ICommand getCustomBtnCalcElementNum_2D() {
			return customBtnCalcElementNum_2D;
		}
		public void setCustomBtnCalcElementNum_2D(ICommand customBtnCalcElementNum_2D) {
			this.customBtnCalcElementNum_2D = customBtnCalcElementNum_2D;
		}
		public Composite getCompositePlateInformation_2D() {
			return compositePlateInformation_2D;
		}
		public void setCompositePlateInformation_2D(
				Composite compositePlateInformation_2D) {
			this.compositePlateInformation_2D = compositePlateInformation_2D;
		}
		public Text getTextPlateVelocity_2D() {
			return textPlateVelocity_2D;
		}
		public void setTextPlateVelocity_2D(Text textPlateVelocity_2D) {
			this.textPlateVelocity_2D = textPlateVelocity_2D;
		}
		public Text getTextTemperatureStart_2D() {
			return textTemperatureStart_2D;
		}
		public void setTextTemperatureStart_2D(Text textTemperatureStart_2D) {
			this.textTemperatureStart_2D = textTemperatureStart_2D;
		}
		public Text getTextTemperatureEnd_2D() {
			return textTemperatureEnd_2D;
		}
		public void setTextTemperatureEnd_2D(Text textTemperatureEnd_2D) {
			this.textTemperatureEnd_2D = textTemperatureEnd_2D;
		}
		public Text getTextPassLineOffset_2D() {
			return textPassLineOffset_2D;
		}
		public void setTextPassLineOffset_2D(Text textPassLineOffset_2D) {
			this.textPassLineOffset_2D = textPassLineOffset_2D;
		}
		public Composite getCompositeRollParameter_2D() {
			return compositeRollParameter_2D;
		}
		public void setCompositeRollParameter_2D(Composite compositeRollParameter_2D) {
			this.compositeRollParameter_2D = compositeRollParameter_2D;
		}
		public Spinner getSpinnerUpperRollNum_2D() {
			return spinnerUpperRollNum_2D;
		}
		public void setSpinnerUpperRollNum_2D(Spinner spinnerUpperRollNum_2D) {
			this.spinnerUpperRollNum_2D = spinnerUpperRollNum_2D;
		}
		public ICommand getCustomSpinnerUpperRollNum_2D() {
			return customSpinnerUpperRollNum_2D;
		}
		public void setCustomSpinnerUpperRollNum_2D(
				ICommand customSpinnerUpperRollNum_2D) {
			this.customSpinnerUpperRollNum_2D = customSpinnerUpperRollNum_2D;
		}
		public Spinner getSpinnerLowerRollNum_2D() {
			return spinnerLowerRollNum_2D;
		}
		public void setSpinnerLowerRollNum_2D(Spinner spinnerLowerRollNum_2D) {
			this.spinnerLowerRollNum_2D = spinnerLowerRollNum_2D;
		}
		public ICommand getCustomSpinnerLowerRollNum_2D() {
			return customSpinnerLowerRollNum_2D;
		}
		public void setCustomSpinnerLowerRollNum_2D(
				ICommand customSpinnerLowerRollNum_2D) {
			this.customSpinnerLowerRollNum_2D = customSpinnerLowerRollNum_2D;
		}
		public Text getTextRollPitch_2D() {
			return textRollPitch_2D;
		}
		public void setTextRollPitch_2D(Text textRollPitch_2D) {
			this.textRollPitch_2D = textRollPitch_2D;
		}
		/*
		public Text getTextRollLength_2D() {
			return textRollLength_2D;
		}
		public void setTextRollLength_2D(Text textRollLength_2D) {
			this.textRollLength_2D = textRollLength_2D;
		}
		// */
		public Text getTextEntryUpperRollGap_2D() {
			return textEntryUpperRollGap_2D;
		}
		public void setTextEntryUpperRollGap_2D(Text textEntryUpperRollGap_2D) {
			this.textEntryUpperRollGap_2D = textEntryUpperRollGap_2D;
		}
		public Text getTextEntryLowerRollGap_2D() {
			return textEntryLowerRollGap_2D;
		}
		public void setTextEntryLowerRollGap_2D(Text textEntryLowerRollGap_2D) {
			this.textEntryLowerRollGap_2D = textEntryLowerRollGap_2D;
		}
		public Text getTextExitUpperRollGap_2D() {
			return textExitUpperRollGap_2D;
		}
		public void setTextExitUpperRollGap_2D(Text textExitUpperRollGap_2D) {
			this.textExitUpperRollGap_2D = textExitUpperRollGap_2D;
		}
		public Text getTextExitLowerRollGap_2D() {
			return textExitLowerRollGap_2D;
		}
		public void setTextExitLowerRollGap_2D(Text textExitLowerRollGap_2D) {
			this.textExitLowerRollGap_2D = textExitLowerRollGap_2D;
		}
		public Text getTextUpperEntryRollGapMovement_2D() {
			return textUpperEntryRollGapMovement_2D;
		}
		public void setTextUpperEntryRollGapMovement_2D(
				Text textUpperEntryRollGapMovement_2D) {
			this.textUpperEntryRollGapMovement_2D = textUpperEntryRollGapMovement_2D;
		}
		public Text getTextUpperExitRollGapMovement_2D() {
			return textUpperExitRollGapMovement_2D;
		}
		public void setTextUpperExitRollGapMovement_2D(
				Text textUpperExitRollGapMovement_2D) {
			this.textUpperExitRollGapMovement_2D = textUpperExitRollGapMovement_2D;
		}
		public Text getTextUpperRollGapStayingTime_2D() {
			return textUpperRollGapStayingTime_2D;
		}
		public void setTextUpperRollGapStayingTime_2D(
				Text textUpperRollGapStayingTime_2D) {
			this.textUpperRollGapStayingTime_2D = textUpperRollGapStayingTime_2D;
		}
		public Text getTextUpperRollGapMovingTime_2D() {
			return textUpperRollGapMovingTime_2D;
		}
		public void setTextUpperRollGapMovingTime_2D(Text textUpperRollGapMovingTime_2D) {
			this.textUpperRollGapMovingTime_2D = textUpperRollGapMovingTime_2D;
		}
		public Text getTextLowerEntryRollGapMovement_2D() {
			return textLowerEntryRollGapMovement_2D;
		}
		public void setTextLowerEntryRollGapMovement_2D(
				Text textLowerEntryRollGapMovement_2D) {
			this.textLowerEntryRollGapMovement_2D = textLowerEntryRollGapMovement_2D;
		}
		public Text getTextLowerExitRollGapMovement_2D() {
			return textLowerExitRollGapMovement_2D;
		}
		public void setTextLowerExitRollGapMovement_2D(
				Text textLowerExitRollGapMovement_2D) {
			this.textLowerExitRollGapMovement_2D = textLowerExitRollGapMovement_2D;
		}
		public Text getTextLowerRollGapStayingTime_2D() {
			return textLowerRollGapStayingTime_2D;
		}
		public void setTextLowerRollGapStayingTime_2D(
				Text textLowerRollGapStayingTime_2D) {
			this.textLowerRollGapStayingTime_2D = textLowerRollGapStayingTime_2D;
		}
		public Text getTextLowerRollGapMovingTime_2D() {
			return textLowerRollGapMovingTime_2D;
		}
		public void setTextLowerRollGapMovingTime_2D(Text textLowerRollGapMovingTime_2D) {
			this.textLowerRollGapMovingTime_2D = textLowerRollGapMovingTime_2D;
		}
		public Text getTextFrontHDRollDia_2D() {
			return textFrontHDRollDia_2D;
		}
		public void setTextFrontHDRollDia_2D(Text textFrontHDRollDia_2D) {
			this.textFrontHDRollDia_2D = textFrontHDRollDia_2D;
		}
		public Text getTextFrontHDRollPitch_2D() {
			return textFrontHDRollPitch_2D;
		}
		public void setTextFrontHDRollPitch_2D(Text textFrontHDRollPitch_2D) {
			this.textFrontHDRollPitch_2D = textFrontHDRollPitch_2D;
		}
		public Text getTextFrontHDRollVericalPos_2D() {
			return textFrontHDRollVericalPos_2D;
		}
		public void setTextFrontHDRollVericalPos_2D(Text textFrontHDRollVericalPos_2D) {
			this.textFrontHDRollVericalPos_2D = textFrontHDRollVericalPos_2D;
		}
		public Text getTextRearHDRollDia_2D() {
			return textRearHDRollDia_2D;
		}
		public void setTextRearHDRollDia_2D(Text textRearHDRollDia_2D) {
			this.textRearHDRollDia_2D = textRearHDRollDia_2D;
		}
		public Text getTextRearHDRollPitch_2D() {
			return textRearHDRollPitch_2D;
		}
		public void setTextRearHDRollPitch_2D(Text textRearHDRollPitch_2D) {
			this.textRearHDRollPitch_2D = textRearHDRollPitch_2D;
		}
		public Text getTextRearHDRollVerticalPos_2D() {
			return textRearHDRollVerticalPos_2D;
		}
		public void setTextRearHDRollVerticalPos_2D(Text textRearHDRollVerticalPos_2D) {
			this.textRearHDRollVerticalPos_2D = textRearHDRollVerticalPos_2D;
		}
		public Button getBtnNone_2D() {
			return btnNone_2D;
		}
		public void setBtnNone_2D(Button btnNone_2D) {
			this.btnNone_2D = btnNone_2D;
		}
		public ICommand getCustomBtnNone_2D() {
			return customBtnNone_2D;
		}
		public void setCustomBtnNone_2D(ICommand customBtnNone_2D) {
			this.customBtnNone_2D = customBtnNone_2D;
		}
		public Button getBtnUpper_2D() {
			return btnUpper_2D;
		}
		public void setBtnUpper_2D(Button btnUpper_2D) {
			this.btnUpper_2D = btnUpper_2D;
		}
		public ICommand getCustomBtnUpper_2D() {
			return customBtnUpper_2D;
		}
		public void setCustomBtnUpper_2D(ICommand customBtnUpper_2D) {
			this.customBtnUpper_2D = customBtnUpper_2D;
		}
		public Button getBtnLower_2D() {
			return btnLower_2D;
		}
		public void setBtnLower_2D(Button btnLower_2D) {
			this.btnLower_2D = btnLower_2D;
		}
		public ICommand getCustomBtnLower_2D() {
			return customBtnLower_2D;
		}
		public void setCustomBtnLower_2D(ICommand customBtnLower_2D) {
			this.customBtnLower_2D = customBtnLower_2D;
		}
		public Button getBtnShowRollTable_2D() {
			return btnShowRollTable_2D;
		}
		public void setBtnShowRollTable_2D(Button btnShowRollTable_2D) {
			this.btnShowRollTable_2D = btnShowRollTable_2D;
		}
		public ICommand getCustomBtnShowRollTable_2D() {
			return customBtnShowRollTable_2D;
		}
		public void setCustomBtnShowRollTable_2D(ICommand customBtnShowRollTable_2D) {
			this.customBtnShowRollTable_2D = customBtnShowRollTable_2D;
		}
		public Text getTextRollFriction_2D() {
			return textRollFriction_2D;
		}
		public void setTextRollFriction_2D(Text textRollFriction_2D) {
			this.textRollFriction_2D = textRollFriction_2D;
		}
		public Text getTextRollDiameter_2D() {
			return textRollDiameter_2D;
		}
		public void setTextRollDiameter_2D(Text textRollDiameter_2D) {
			this.textRollDiameter_2D = textRollDiameter_2D;
		}
		/*
		public Text getTextUpperRollCrown_2D() {
			return textUpperRollCrown_2D;
		}
		public void setTextUpperRollCrown_2D(Text textUpperRollCrown_2D) {
			this.textUpperRollCrown_2D = textUpperRollCrown_2D;
		}
		public Text getTextLowerRollCrown_2D() {
			return textLowerRollCrown_2D;
		}
		public void setTextLowerRollCrown_2D(Text textLowerRollCrown_2D) {
			this.textLowerRollCrown_2D = textLowerRollCrown_2D;
		}
		// */
		public Text getTextMillStiffness_2D() {
			return textMillStiffness_2D;
		}
		public void setTextMillStiffness_2D(Text textMillStiffness_2D) {
			this.textMillStiffness_2D = textMillStiffness_2D;
		}
		/*
		public Button getBtnRadioNone_RC_2D() {
			return btnRadioNone_RC_2D;
		}
		public void setBtnRadioNone_RC_2D(Button btnRadioNone_RC_2D) {
			this.btnRadioNone_RC_2D = btnRadioNone_RC_2D;
		}
		public ICommand getCustomBtnRadioNone_RC_2D() {
			return customBtnRadioNone_RC_2D;
		}
		public void setCustomBtnRadioNone_RC_2D(ICommand customBtnRadioNone_RC_2D) {
			this.customBtnRadioNone_RC_2D = customBtnRadioNone_RC_2D;
		}
		public Button getBtnRadioApply_RC_2D() {
			return btnRadioApply_RC_2D;
		}
		public void setBtnRadioApply_RC_2D(Button btnRadioApply_RC_2D) {
			this.btnRadioApply_RC_2D = btnRadioApply_RC_2D;
		}
		public ICommand getCustomBtnRadioApply_RC_2D() {
			return customBtnRadioApply_RC_2D;
		}
		public void setCustomBtnRadioApply_RC_2D(ICommand customBtnRadioApply_RC_2D) {
			this.customBtnRadioApply_RC_2D = customBtnRadioApply_RC_2D;
		}
		//*/
		public Button getBtnRadioRigid_MS_2D() {
			return btnRadioRigid_MS_2D;
		}
		public void setBtnRadioRigid_MS_2D(Button btnRadioRigid_MS_2D) {
			this.btnRadioRigid_MS_2D = btnRadioRigid_MS_2D;
		}
		public ICommand getCustomBtnRadioRigid_MS_2D() {
			return customBtnRadioRigid_MS_2D;
		}
		public void setCustomBtnRadioRigid_MS_2D(ICommand customBtnRadioRigid_MS_2D) {
			this.customBtnRadioRigid_MS_2D = customBtnRadioRigid_MS_2D;
		}
		public Button getBtnRadioSpring_MS_2D() {
			return btnRadioSpring_MS_2D;
		}
		public void setBtnRadioSpring_MS_2D(Button btnRadioSpring_MS_2D) {
			this.btnRadioSpring_MS_2D = btnRadioSpring_MS_2D;
		}
		public ICommand getCustomBtnRadioSpring_MS_2D() {
			return customBtnRadioSpring_MS_2D;
		}
		public void setCustomBtnRadioSpring_MS_2D(ICommand customBtnRadioSpring_MS_2D) {
			this.customBtnRadioSpring_MS_2D = customBtnRadioSpring_MS_2D;
		}
		public Button getBtnCreateRoll_2D() {
			return btnCreateRoll_2D;
		}
		public void setBtnCreateRoll_2D(Button btnCreateRoll_2D) {
			this.btnCreateRoll_2D = btnCreateRoll_2D;
		}
		public ICommand getCustomBtnCreateRoll_2D() {
			return customBtnCreateRoll_2D;
		}
		public void setCustomBtnCreateRoll_2D(ICommand customBtnCreateRoll_2D) {
			this.customBtnCreateRoll_2D = customBtnCreateRoll_2D;
		}
		public Button getBtnUpperEdit_2D() {
			return btnUpperEdit_2D;
		}
		public void setBtnUpperEdit_2D(Button btnUpperEdit_2D) {
			this.btnUpperEdit_2D = btnUpperEdit_2D;
		}
		public ICommand getCustomBtnUpperEdit_2D() {
			return customBtnUpperEdit_2D;
		}
		public void setCustomBtnUpperEdit_2D(ICommand customBtnUpperEdit_2D) {
			this.customBtnUpperEdit_2D = customBtnUpperEdit_2D;
		}
		public Button getBtnUpperSave_2D() {
			return btnUpperSave_2D;
		}
		public void setBtnUpperSave_2D(Button btnUpperSave_2D) {
			this.btnUpperSave_2D = btnUpperSave_2D;
		}
		public ICommand getCustomBtnUpperSave_2D() {
			return customBtnUpperSave_2D;
		}
		public void setCustomBtnUpperSave_2D(ICommand customBtnUpperSave_2D) {
			this.customBtnUpperSave_2D = customBtnUpperSave_2D;
		}
		public TableViewer getTableViewerUpperRoll_2D() {
			return tableViewerUpperRoll_2D;
		}
		public void setTableViewerUpperRoll_2D(TableViewer tableViewerUpperRoll_2D) {
			this.tableViewerUpperRoll_2D = tableViewerUpperRoll_2D;
		}
		public ICommand getCustomTableViewerUpperRoll_2D() {
			return customTableViewerUpperRoll_2D;
		}
		public void setCustomTableViewerUpperRoll_2D(
				ICommand customTableViewerUpperRoll_2D) {
			this.customTableViewerUpperRoll_2D = customTableViewerUpperRoll_2D;
		}
		public Button getBtnLowerEdit_2D() {
			return btnLowerEdit_2D;
		}
		public void setBtnLowerEdit_2D(Button btnLowerEdit_2D) {
			this.btnLowerEdit_2D = btnLowerEdit_2D;
		}
		public ICommand getCustomBtnLowerEdit_2D() {
			return customBtnLowerEdit_2D;
		}
		public void setCustomBtnLowerEdit_2D(ICommand customBtnLowerEdit_2D) {
			this.customBtnLowerEdit_2D = customBtnLowerEdit_2D;
		}
		public Button getBtnLowerSave_2D() {
			return btnLowerSave_2D;
		}
		public void setBtnLowerSave_2D(Button btnLowerSave_2D) {
			this.btnLowerSave_2D = btnLowerSave_2D;
		}
		public ICommand getCustomBtnLowerSave_2D() {
			return customBtnLowerSave_2D;
		}
		public void setCustomBtnLowerSave_2D(ICommand customBtnLowerSave_2D) {
			this.customBtnLowerSave_2D = customBtnLowerSave_2D;
		}
		public TableViewer getTableViewerLowerRoll_2D() {
			return tableViewerLowerRoll_2D;
		}
		public void setTableViewerLowerRoll_2D(TableViewer tableViewerLowerRoll_2D) {
			this.tableViewerLowerRoll_2D = tableViewerLowerRoll_2D;
		}
		public ICommand getCustomTableViewerLowerRoll_2D() {
			return customTableViewerLowerRoll_2D;
		}
		public void setCustomTableViewerLowerRoll_2D(
				ICommand customTableViewerLowerRoll_2D) {
			this.customTableViewerLowerRoll_2D = customTableViewerLowerRoll_2D;
		}
		public Button getBtnSaveRoll_2D() {
			return btnSaveRoll_2D;
		}
		public void setBtnSaveRoll_2D(Button btnSaveRoll_2D) {
			this.btnSaveRoll_2D = btnSaveRoll_2D;
		}
		
		
		
		public ICommand getCustomBtnSaveRoll_2D() {
			return customBtnSaveRoll_2D;
		}
		public void setCustomBtnSaveRoll_2D(ICommand customBtnSaveRoll_2D) {
			this.customBtnSaveRoll_2D = customBtnSaveRoll_2D;
		}
		public Composite getCompositeMaterialParameter_2D() {
			return compositeMaterialParameter_2D;
		}
		public void setCompositeMaterialParameter_2D(
				Composite compositeMaterialParameter_2D) {
			this.compositeMaterialParameter_2D = compositeMaterialParameter_2D;
		}
		public Text getTextYoungsModulus_2D() {
			return textYoungsModulus_2D;
		}
		public void setTextYoungsModulus_2D(Text textYoungsModulus_2D) {
			this.textYoungsModulus_2D = textYoungsModulus_2D;
		}
		public Button getBtnExplorerYoungsModulus_2D() {
			return btnExplorerYoungsModulus_2D;
		}
		public void setBtnExplorerYoungsModulus_2D(Button btnExplorerYoungsModulus_2D) {
			this.btnExplorerYoungsModulus_2D = btnExplorerYoungsModulus_2D;
		}
		public ICommand getCustomBtnExplorerYoungsModulus_2D() {
			return customBtnExplorerYoungsModulus_2D;
		}
		public void setCustomBtnExplorerYoungsModulus_2D(
				ICommand customBtnExplorerYoungsModulus_2D) {
			this.customBtnExplorerYoungsModulus_2D = customBtnExplorerYoungsModulus_2D;
		}
		public Button getBtnRadioConstant_YM_2D() {
			return btnRadioConstant_YM_2D;
		}
		public void setBtnRadioConstant_YM_2D(Button btnRadioConstant_YM_2D) {
			this.btnRadioConstant_YM_2D = btnRadioConstant_YM_2D;
		}
		public ICommand getCustomBtnRadioConstant_YM_2D() {
			return customBtnRadioConstant_YM_2D;
		}
		public void setCustomBtnRadioConstant_YM_2D(
				ICommand customBtnRadioConstant_YM_2D) {
			this.customBtnRadioConstant_YM_2D = customBtnRadioConstant_YM_2D;
		}
		public Button getBtnRadioTable_YM_2D() {
			return btnRadioTable_YM_2D;
		}
		public void setBtnRadioTable_YM_2D(Button btnRadioTable_YM_2D) {
			this.btnRadioTable_YM_2D = btnRadioTable_YM_2D;
		}
		public ICommand getCustomBtnRadioTable_YM_2D() {
			return customBtnRadioTable_YM_2D;
		}
		public void setCustomBtnRadioTable_YM_2D(ICommand customBtnRadioTable_YM_2D) {
			this.customBtnRadioTable_YM_2D = customBtnRadioTable_YM_2D;
		}
		public Text getTextFlowStress_2D() {
			return textFlowStress_2D;
		}
		public void setTextFlowStress_2D(Text textFlowStress_2D) {
			this.textFlowStress_2D = textFlowStress_2D;
		}
		public Button getBtnExplorerFlowStress_2D() {
			return btnExplorerFlowStress_2D;
		}
		public void setBtnExplorerFlowStress_2D(Button btnExplorerFlowStress_2D) {
			this.btnExplorerFlowStress_2D = btnExplorerFlowStress_2D;
		}
		public ICommand getCustomBtnExplorerFlowStress_2D() {
			return customBtnExplorerFlowStress_2D;
		}
		public void setCustomBtnExplorerFlowStress_2D(
				ICommand customBtnExplorerFlowStress_2D) {
			this.customBtnExplorerFlowStress_2D = customBtnExplorerFlowStress_2D;
		}
		public Text getTextYieldStrength_2D() {
			return textYieldStrength_2D;
		}
		public void setTextYieldStrength_2D(Text textYieldStrength_2D) {
			this.textYieldStrength_2D = textYieldStrength_2D;
		}
		public Text getTextTensileStrength_2D() {
			return textTensileStrength_2D;
		}
		public void setTextTensileStrength_2D(Text textTensileStrength_2D) {
			this.textTensileStrength_2D = textTensileStrength_2D;
		}
		public Text getTextElongation_2D() {
			return textElongation_2D;
		}
		public void setTextElongation_2D(Text textElongation_2D) {
			this.textElongation_2D = textElongation_2D;
		}
		public Button getBtnRadioConstant_FS_2D() {
			return btnRadioConstant_FS_2D;
		}
		public void setBtnRadioConstant_FS_2D(Button btnRadioConstant_FS_2D) {
			this.btnRadioConstant_FS_2D = btnRadioConstant_FS_2D;
		}
		public ICommand getCustomBtnRadioConstant_FS_2D() {
			return customBtnRadioConstant_FS_2D;
		}
		public void setCustomBtnRadioConstant_FS_2D(
				ICommand customBtnRadioConstant_FS_2D) {
			this.customBtnRadioConstant_FS_2D = customBtnRadioConstant_FS_2D;
		}
		public Button getBtnRadioTable_FS_2D() {
			return btnRadioTable_FS_2D;
		}
		public void setBtnRadioTable_FS_2D(Button btnRadioTable_FS_2D) {
			this.btnRadioTable_FS_2D = btnRadioTable_FS_2D;
		}
		public ICommand getCustomBtnRadioTable_FS_2D() {
			return customBtnRadioTable_FS_2D;
		}
		public void setCustomBtnRadioTable_FS_2D(ICommand customBtnRadioTable_FS_2D) {
			this.customBtnRadioTable_FS_2D = customBtnRadioTable_FS_2D;
		}
		public Text getTextThermalExpansionCoefficient_2D() {
			return textThermalExpansionCoefficient_2D;
		}
		public void setTextThermalExpansionCoefficient_2D(
				Text textThermalExpansionCoefficient_2D) {
			this.textThermalExpansionCoefficient_2D = textThermalExpansionCoefficient_2D;
		}
		public Button getBtnExplorerThermalExpansionCoefficient_2D() {
			return btnExplorerThermalExpansionCoefficient_2D;
		}
		public void setBtnExplorerThermalExpansionCoefficient_2D(
				Button btnExplorerThermalExpansionCoefficient_2D) {
			this.btnExplorerThermalExpansionCoefficient_2D = btnExplorerThermalExpansionCoefficient_2D;
		}
		public ICommand getCustomBtnExplorerThermalExpansionCoefficient_2D() {
			return customBtnExplorerThermalExpansionCoefficient_2D;
		}
		public void setCustomBtnExplorerThermalExpansionCoefficient_2D(
				ICommand customBtnExplorerThermalExpansionCoefficient_2D) {
			this.customBtnExplorerThermalExpansionCoefficient_2D = customBtnExplorerThermalExpansionCoefficient_2D;
		}
		public Button getBtnRadioConstant_TEC_2D() {
			return btnRadioConstant_TEC_2D;
		}
		public void setBtnRadioConstant_TEC_2D(Button btnRadioConstant_TEC_2D) {
			this.btnRadioConstant_TEC_2D = btnRadioConstant_TEC_2D;
		}
		public ICommand getCustomBtnRadioConstant_TEC_2D() {
			return customBtnRadioConstant_TEC_2D;
		}
		public void setCustomBtnRadioConstant_TEC_2D(
				ICommand customBtnRadioConstant_TEC_2D) {
			this.customBtnRadioConstant_TEC_2D = customBtnRadioConstant_TEC_2D;
		}
		public Button getBtnRadioTable_TEC_2D() {
			return btnRadioTable_TEC_2D;
		}
		public void setBtnRadioTable_TEC_2D(Button btnRadioTable_TEC_2D) {
			this.btnRadioTable_TEC_2D = btnRadioTable_TEC_2D;
		}
		public ICommand getCustomBtnRadioTable_TEC_2D() {
			return customBtnRadioTable_TEC_2D;
		}
		public void setCustomBtnRadioTable_TEC_2D(ICommand customBtnRadioTable_TEC_2D) {
			this.customBtnRadioTable_TEC_2D = customBtnRadioTable_TEC_2D;
		}
		public Text getTextPoissonsRatio_2D() {
			return textPoissonsRatio_2D;
		}
		public void setTextPoissonsRatio_2D(Text textPoissonsRatio_2D) {
			this.textPoissonsRatio_2D = textPoissonsRatio_2D;
		}
		public Button getBtnExplorerPoissonsRatio_2D() {
			return btnExplorerPoissonsRatio_2D;
		}
		public void setBtnExplorerPoissonsRatio_2D(Button btnExplorerPoissonsRatio_2D) {
			this.btnExplorerPoissonsRatio_2D = btnExplorerPoissonsRatio_2D;
		}
		public ICommand getCustomBtnExplorerPoissonsRatio_2D() {
			return customBtnExplorerPoissonsRatio_2D;
		}
		public void setCustomBtnExplorerPoissonsRatio_2D(
				ICommand customBtnExplorerPoissonsRatio_2D) {
			this.customBtnExplorerPoissonsRatio_2D = customBtnExplorerPoissonsRatio_2D;
		}
		public Button getBtnRadioConstant_PR_2D() {
			return btnRadioConstant_PR_2D;
		}
		public void setBtnRadioConstant_PR_2D(Button btnRadioConstant_PR_2D) {
			this.btnRadioConstant_PR_2D = btnRadioConstant_PR_2D;
		}
		public ICommand getCustomBtnRadioConstant_PR_2D() {
			return customBtnRadioConstant_PR_2D;
		}
		public void setCustomBtnRadioConstant_PR_2D(
				ICommand customBtnRadioConstant_PR_2D) {
			this.customBtnRadioConstant_PR_2D = customBtnRadioConstant_PR_2D;
		}
		public Button getBtnRadioTable_PR_2D() {
			return btnRadioTable_PR_2D;
		}
		public void setBtnRadioTable_PR_2D(Button btnRadioTable_PR_2D) {
			this.btnRadioTable_PR_2D = btnRadioTable_PR_2D;
		}
		public ICommand getCustomBtnRadioTable_PR_2D() {
			return customBtnRadioTable_PR_2D;
		}
		public void setCustomBtnRadioTable_PR_2D(ICommand customBtnRadioTable_PR_2D) {
			this.customBtnRadioTable_PR_2D = customBtnRadioTable_PR_2D;
		}
		public Text getTextMassDensity_2D() {
			return textMassDensity_2D;
		}
		public void setTextMassDensity_2D(Text textMassDensity_2D) {
			this.textMassDensity_2D = textMassDensity_2D;
		}
		public Button getBtnApply_2D() {
			return btnApply_2D;
		}
		public void setBtnApply_2D(Button btnApply_2D) {
			this.btnApply_2D = btnApply_2D;
		}
		public ICommand getCustomBtnApply_2D() {
			return customBtnApply_2D;
		}
		public void setCustomBtnApply_2D(ICommand customBtnApply_2D) {
			this.customBtnApply_2D = customBtnApply_2D;
		}
		public Composite getCompositeSolvingOption_2D() {
			return compositeSolvingOption_2D;
		}
		public void setCompositeSolvingOption_2D(Composite compositeSolvingOption_2D) {
			this.compositeSolvingOption_2D = compositeSolvingOption_2D;
		}
		public Text getTextSolvingTime_2D() {
			return textSolvingTime_2D;
		}
		public void setTextSolvingTime_2D(Text textSolvingTime_2D) {
			this.textSolvingTime_2D = textSolvingTime_2D;
		}
		public Text getTextIncrementTime_2D() {
			return textIncrementTime_2D;
		}
		public void setTextIncrementTime_2D(Text textIncrementTime_2D) {
			this.textIncrementTime_2D = textIncrementTime_2D;
		}
		public Button getBtnParallelDDMUse_2D() {
			return btnParallelDDMUse_2D;
		}
		public void setBtnParallelDDMUse_2D(Button btnParallelDDMUse_2D) {
			this.btnParallelDDMUse_2D = btnParallelDDMUse_2D;
		}
		public ICommand getCustomBtnParallelDDMUse_2D() {
			return customBtnParallelDDMUse_2D;
		}
		public void setCustomBtnParallelDDMUse_2D(ICommand customBtnParallelDDMUse_2D) {
			this.customBtnParallelDDMUse_2D = customBtnParallelDDMUse_2D;
		}
		public Spinner getSpinnerDomain_2D() {
			return spinnerDomain_2D;
		}
		public void setSpinnerDomain_2D(Spinner spinnerDomain_2D) {
			this.spinnerDomain_2D = spinnerDomain_2D;
		}
		public ICommand getCustomSpinnerDomain_2D() {
			return customSpinnerDomain_2D;
		}
		public void setCustomSpinnerDomain_2D(ICommand customSpinnerDomain_2D) {
			this.customSpinnerDomain_2D = customSpinnerDomain_2D;
		}
		public Button getBtnParallelMultiThreadUse_2D() {
			return btnParallelMultiThreadUse_2D;
		}
		public void setBtnParallelMultiThreadUse_2D(Button btnParallelMultiThreadUse_2D) {
			this.btnParallelMultiThreadUse_2D = btnParallelMultiThreadUse_2D;
		}
		public ICommand getCustomBtnParallelMultiThreadUse_2D() {
			return customBtnParallelMultiThreadUse_2D;
		}
		public void setCustomBtnParallelMultiThreadUse_2D(
				ICommand customBtnParallelMultiThreadUse_2D) {
			this.customBtnParallelMultiThreadUse_2D = customBtnParallelMultiThreadUse_2D;
		}
		public Spinner getSpinnerThread_2D() {
			return spinnerThread_2D;
		}
		public void setSpinnerThread_2D(Spinner spinnerThread_2D) {
			this.spinnerThread_2D = spinnerThread_2D;
		}
		public ICommand getCustomSpinnerThread_2D() {
			return customSpinnerThread_2D;
		}
		public void setCustomSpinnerThread_2D(ICommand customSpinnerThread_2D) {
			this.customSpinnerThread_2D = customSpinnerThread_2D;
		}
		public Composite getCompositeShapeParameterChild_1_2D() {
			return compositeShapeParameterChild_1_2D;
		}
		public void setCompositeShapeParameterChild_1_2D(
				Composite compositeShapeParameterChild_1_2D) {
			this.compositeShapeParameterChild_1_2D = compositeShapeParameterChild_1_2D;
		}
		public Composite getCompositeShapeParameterChild_2_2D() {
			return compositeShapeParameterChild_2_2D;
		}
		public void setCompositeShapeParameterChild_2_2D(
				Composite compositeShapeParameterChild_2_2D) {
			this.compositeShapeParameterChild_2_2D = compositeShapeParameterChild_2_2D;
		}
		public Composite getCompositeShapeParameterChild_3_2D() {
			return compositeShapeParameterChild_3_2D;
		}
		public void setCompositeShapeParameterChild_3_2D(
				Composite compositeShapeParameterChild_3_2D) {
			this.compositeShapeParameterChild_3_2D = compositeShapeParameterChild_3_2D;
		}
		/*
		public Composite getCompositeShapeParameterChild_4_2D() {
			return compositeShapeParameterChild_4_2D;
		}
		public void setCompositeShapeParameterChild_4_2D(
				Composite compositeShapeParameterChild_4_2D) {
			this.compositeShapeParameterChild_4_2D = compositeShapeParameterChild_4_2D;
		}
		public Composite getCompositeShapeParameterChild_5_2D() {
			return compositeShapeParameterChild_5_2D;
		}
		public void setCompositeShapeParameterChild_5_2D(
				Composite compositeShapeParameterChild_5_2D) {
			this.compositeShapeParameterChild_5_2D = compositeShapeParameterChild_5_2D;
		}
		public Composite getCompositeShapeParameterChild_6_2D() {
			return compositeShapeParameterChild_6_2D;
		}
		public void setCompositeShapeParameterChild_6_2D(
				Composite compositeShapeParameterChild_6_2D) {
			this.compositeShapeParameterChild_6_2D = compositeShapeParameterChild_6_2D;
		}
		public Composite getCompositeShapeParameterChild_7_2D() {
			return compositeShapeParameterChild_7_2D;
		}
		public void setCompositeShapeParameterChild_7_2D(
				Composite compositeShapeParameterChild_7_2D) {
			this.compositeShapeParameterChild_7_2D = compositeShapeParameterChild_7_2D;
		}
		// */
		public TabFolder getTabFolder() {
			return tabFolder;
		}
		public void setTabFolder(TabFolder tabFolder) {
			this.tabFolder = tabFolder;
		}
		public ICommand getCustomTabFolder() {
			return customTabFolder;
		}
		public void setCustomTabFolder(ICommand customTabFolder) {
			this.customTabFolder = customTabFolder;
		}
		public Text getTextEntryRollTableDistance_2D() {
			return textEntryRollTableDistance_2D;
		}
		public void setTextEntryRollTableDistance_2D(Text textEntryRollTableDistance_2D) {
			this.textEntryRollTableDistance_2D = textEntryRollTableDistance_2D;
		}
		public Text getTextExitRollTableDistance_2D() {
			return textExitRollTableDistance_2D;
		}
		public void setTextExitRollTableDistance_2D(Text textExitRollTableDistance_2D) {
			this.textExitRollTableDistance_2D = textExitRollTableDistance_2D;
		}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
