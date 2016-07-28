package com.js.ens.leveller.core;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.js.ens.leveller.handler.ICommand;

public class Mediator {
	private static Mediator instance = new Mediator();
	public static Mediator getInstance(){
		return instance;
	}
	//--------------------------------------------
	private Text textModelName;
	private Label lblworkspacePath;
	//--------------------------------------------	
	private Composite compositeShapeParameter; 
	public Composite getCompositeShapeParameter() {
		return compositeShapeParameter;
	}
	public void setCompositeShapeParameter(Composite compositeShapeParameter) {
		this.compositeShapeParameter = compositeShapeParameter;
	}
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
	public Composite getCompositeMeshParameter() {
		return compositeMeshParameter;
	}
	public void setCompositeMeshParameter(Composite compositeMeshParameter) {
		this.compositeMeshParameter = compositeMeshParameter;
	}
	private Text textThicknessElementNum;
	private Text textWidthAspectRatio;
	private Text textLengthAspectRatio;
	private Text textElementNumber;
	private Button btnCalcElementNum;
	public ICommand customBtnCalcElementNum;
	public static String BUTTON_CalcElementNum = "btnCalcElementNum";
	
	//--------------------------------------------
	private Composite compositePlateInformation;
	public Composite getCompositePlateInformation() {
		return compositePlateInformation;
	}
	public void setCompositePlateInformation(Composite compositePlateInformation) {
		this.compositePlateInformation = compositePlateInformation;
	}
	private Text textPlateVelocity;
	private Text textTemperatureStart;
	private Text textTemperatureEnd;
	private Text textPassLineOffset;
	
	//--------------------------------------------
	private Composite compositeRollParameter;
	public Composite getCompositeRollParameter() {
		return compositeRollParameter;
	}
	public void setCompositeRollParameter(Composite compositeRollParameter) {
		this.compositeRollParameter = compositeRollParameter;
	}
	private Spinner spinnerUpperRollNum;
	public ICommand customSpinnerUpperRollNum;
	public static String SPINNER_UpperRollNum = "spinnerUpperRollNum";
	private Spinner spinnerLowerRollNum;
	public ICommand customSpinnerLowerRollNum;
	public static String SPINNER_LowerRollNum = "spinnerLowerRollNum";
	private Text textRollPitch;
	private Text textRollLength;
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
	private Text textRollCrown;
	private Text textMillStiffness;
	
	private Button btnRadioNone_RC;
	public ICommand customBtnRadioNone_RC;
	public static String BUTTON_RadioNone_RC = "btnRadioNone_RC";
	
	private Button btnRadioApply_RC;
	public ICommand customBtnRadioApply_RC;
	public static String BUTTON_RadioApply_RC = "btnRadioApply_RC";
	
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
	public Composite getCompositeMaterialParameter() {
		return compositeMaterialParameter;
	}
	public void setCompositeMaterialParameter(Composite compositeMaterialParameter) {
		this.compositeMaterialParameter = compositeMaterialParameter;
	}
	/*
	private Text textModulusElasticity;
	private Button btnExplorer;
	public ICommand customBtnExplorer;
	public static String BUTTON_Explorer = "btnExplorer";
	private Text textYieldStrength;
	private Text textPoissonRatio;
	*/
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
	/*
	private Composite compositeContactParameter;
	
	public Composite getCompositeContactParameter() {
		return compositeContactParameter;
	}
	public void setCompositeContactParameter(Composite compositeContactParameter) {
		this.compositeContactParameter = compositeContactParameter;
	}
	private Text textFriction;
	*/
	//--------------------------------------------
	private Composite compositeSolvingOption;
	public Composite getCompositeSolvingOption() {
		return compositeSolvingOption;
	}
	public void setCompositeSolvingOption(Composite compositeSolvingOption) {
		this.compositeSolvingOption = compositeSolvingOption;
	}
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
	
	//private Text textDeformedCoordinate;
	//--------------------------------------------
	//--------------------------------------------
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
	public Text getTextRollCrown() {
		return textRollCrown;
	}
	public void setTextRollCrown(Text textRollCrown) {
		this.textRollCrown = textRollCrown;
	}
	public Text getTextMillStiffness() {
		return textMillStiffness;
	}
	public void setTextMillStiffness(Text textMillStiffness) {
		this.textMillStiffness = textMillStiffness;
	}
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
	/*
	public Text getTextModulusElasticity() {
		return textModulusElasticity;
	}
	public void setTextModulusElasticity(Text textModulusElasticity) {
		this.textModulusElasticity = textModulusElasticity;
	}
	public Button getBtnExplorer() {
		return btnExplorer;
	}
	public void setBtnExplorer(Button btnExplorer) {
		this.btnExplorer = btnExplorer;
	}
	public ICommand getCustomBtnExplorer() {
		return customBtnExplorer;
	}
	public void setCustomBtnExplorer(ICommand customBtnExplorer) {
		this.customBtnExplorer = customBtnExplorer;
	}
	public Text getTextYieldStrength() {
		return textYieldStrength;
	}
	public void setTextYieldStrength(Text textYieldStrength) {
		this.textYieldStrength = textYieldStrength;
	}
	public Text getTextPoissonRatio() {
		return textPoissonRatio;
	}
	public void setTextPoissonRatio(Text textPoissonRatio) {
		this.textPoissonRatio = textPoissonRatio;
	}
	*/
	/*
	public Text getTextFriction() {
		return textFriction;
	}
	public void setTextFriction(Text textFriction) {
		this.textFriction = textFriction;
	}
	*/
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
	/*
	public Text getTextDeformedCoordinate() {
		return textDeformedCoordinate;
	}
	public void setTextDeformedCoordinate(Text textDeformedCoordinate) {
		this.textDeformedCoordinate = textDeformedCoordinate;
	}
	*/
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
	
}
