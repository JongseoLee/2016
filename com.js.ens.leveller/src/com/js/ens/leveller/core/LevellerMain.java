package com.js.ens.leveller.core;

import java.io.File;
import java.util.ArrayList;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Table;

import com.js.ens.leveller.View;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.ens.leveller.img.ImagePath;
import com.js.ens.leveller.procs.ProcMaker;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class LevellerMain {
	private Logger log = Logger.getLogger(LevellerMain.class);
	
	private static LevellerMain instance = new LevellerMain();
	public static LevellerMain getInstatnce(){
		return instance;
	}
	private Mediator med = Mediator.getInstance();
	
	private String fdist = "50";
	private String fdist_2D = "50";
	private Map<String,String> InitValueMap = new HashMap<String,String>();
	
	private String workspace = null;
	private String textModelName;
	
	private String comboType;
	private String textWidth;
	private String textLength;
	private String textThickness;
	
	private String type2_textLeftEdgeWavePitch;
	private String type2_textRightEdgeWavePitch;
	private String type2_textLeftEdgeWaveHeight;
	private String type2_textRightEdgeWaveHeight;
	private String type2_textLeftEdgeWavePhase;
	private String type2_textRightEdgeWavePhase;
	
	private String type3_textWavePitch;
	private String type3_textWaveHeight;
	
	private String type4_textGutterHeight;
	
	private String type5_textGutterHeight;
	private String type5_textGutterLength;
	
	private String type6_textHeadGutterHeight;
	private String type6_textHeadGutterLength;
	private String type6_textTailGutterHeight;
	private String type6_textTailGutterLength;
	
	private String type7_textHeadGutterHeight;
	private String type7_textGutterLength;
	private String type7_textGutterLengthLength;
	private String type7_textGutterWidthLength;
	
	private String textThicknessElementNum;
	private String textWidthAspectRatio;
	private String textLengthAspectRatio;
	private String textElementNumber;
	
	private String textPlateVelocity;
	private String textTemperatureStart;
	private String textTemperatureEnd;
	//update version2 2016.01.27
	private String textPassLineOffset; 
	
	private String spinnerUpperRollNum;
	private String spinnerLowerRollNum;
	private String textRollPitch;
	private String textRollLength;
	private String textEntryUpperRollGap;
	private String textEntryLowerRollGap;
	private String textExitUpperRollGap;
	private String textExitLowerRollGap;
	private String textRollFriction;
	private String textRollDiameter;
	//update version2 2016.01.27
	private String textUpperRollCrown;
	private String textLowerRollCrown;
	//private String RollCrownType;
	private String textMillStiffness;
	private String MillStiffnessType;
	//update version3 2016.07.20
	private String textUpperEntryRollGapMovement;
	private String textUpperExitRollGapMovement;
	private String textUpperRollGapStayingTime;
	private String textUpperRollGapMovingTime;
	private String textLowerEntryRollGapMovement;
	private String textLowerExitRollGapMovement;
	private String textLowerRollGapStayingTime;
	private String textLowerRollGapMovingTime;
	private String textFrontHDRollDia;
	private String textFrontHDRollPitch;
	private String textFrontHDRollVericalPos;
	private String textRearHDRollDia;
	private String textRearHDRollPitch;
	private String textRearHDRollVerticalPos;
	private String HDRollType;
	
	private ArrayList<UpTableDataContent> upTableDataList = null;
	private ArrayList<DownTableDataContent> downTableDataList = null;
	
	
	private String textYoungsModulus;
	private String YoungsModulusType;
	private String textFlowStress;
	private String FlowStressType;
	//update version2 2016.01.27
	private String textYieldStrength;
	private String textTensileStrength;
	private String textElongation;
	
	private String textThermalExpansionCoefficient;
	private String ThermalExpansionCoefficientType;
	private String textMassDensity;
	private String textPoissonsRatio;
	private String PoissonsRatioType;
	
	private String textSolvingTime;
	private String textIncrementTime;
	private boolean btnParallelDDMUse;
	private String spinnerDomain;
	private boolean btnParallelMultiThreadUse;
	private String spinnerThread;
	
	
	private ArrayList<String> db = new ArrayList<String>();
	private ArrayList<String> db_saveAs;
	
	private exportInfo exportMSG;
	
	
	
	
	
	
	
	
	
	
	// 2D define
	private String LevellerType = "2D";
	
	//private Map<String,String> InitValueMap_2D = new HashMap<String,String>();
	
	private String comboType_2D;
	private String textWidth_2D;
	private String textLength_2D;
	private String textThickness_2D;
	
	/*
	private String type2_textLeftEdgeWavePitch_2D;
	private String type2_textRightEdgeWavePitch_2D;
	private String type2_textLeftEdgeWaveHeight_2D;
	private String type2_textRightEdgeWaveHeight_2D;
	private String type2_textLeftEdgeWavePhase_2D;
	private String type2_textRightEdgeWavePhase_2D;
	// */
	private String type2_textWavePitch_2D;
	private String type2_textWaveHeight_2D;
	private String type2_textWavePhase_2D;
	
	/*
	private String type3_textWavePitch_2D;
	private String type3_textWaveHeight_2D;
	// */
	private String type3_textFrontCurlHeight_2D;
	private String type3_textFrontCurlLength_2D;
	private String type3_textRearCurlHeight_2D;
	private String type3_textRearCurlLength_2D;
	
	/*
	private String type4_textGutterHeight_2D;
	
	private String type5_textGutterHeight_2D;
	private String type5_textGutterLength_2D;
	
	private String type6_textHeadGutterHeight_2D;
	private String type6_textHeadGutterLength_2D;
	private String type6_textTailGutterHeight_2D;
	private String type6_textTailGutterLength_2D;
	
	private String type7_textHeadGutterHeight_2D;
	private String type7_textGutterLength_2D;
	private String type7_textGutterLengthLength_2D;
	private String type7_textGutterWidthLength_2D;
	// */
	
	private String textThicknessElementNum_2D;
	//private String textWidthAspectRatio_2D;
	private String textLengthAspectRatio_2D;
	private String textElementNumber_2D;
	
	private String textPlateVelocity_2D;
	private String textTemperatureStart_2D;
	private String textTemperatureEnd_2D;
	//update version2 2016.01.27
	private String textPassLineOffset_2D; 
	
	private String spinnerUpperRollNum_2D;
	private String spinnerLowerRollNum_2D;
	private String textRollPitch_2D;
	//private String textRollLength_2D;
	private String textEntryUpperRollGap_2D;
	private String textEntryLowerRollGap_2D;
	private String textExitUpperRollGap_2D;
	private String textExitLowerRollGap_2D;
	private String textRollFriction_2D;
	private String textRollDiameter_2D;
	//update version2 2016.01.27
	//private String textUpperRollCrown_2D;
	//private String textLowerRollCrown_2D;
	//private String RollCrownType_2D;
	private String textMillStiffness_2D;
	private String MillStiffnessType_2D;
	//update version3 2016.07.20
	private String textUpperEntryRollGapMovement_2D;
	private String textUpperExitRollGapMovement_2D;
	private String textUpperRollGapStayingTime_2D;
	private String textUpperRollGapMovingTime_2D;
	private String textLowerEntryRollGapMovement_2D;
	private String textLowerExitRollGapMovement_2D;
	private String textLowerRollGapStayingTime_2D;
	private String textLowerRollGapMovingTime_2D;
	private String textFrontHDRollDia_2D;
	private String textFrontHDRollPitch_2D;
	private String textFrontHDRollVericalPos_2D;
	private String textRearHDRollDia_2D;
	private String textRearHDRollPitch_2D;
	private String textRearHDRollVerticalPos_2D;
	private String HDRollType_2D;
	
	private ArrayList<UpTableDataContent> upTableDataList_2D = null;
	private ArrayList<DownTableDataContent> downTableDataList_2D = null;
	
	
	private String textYoungsModulus_2D;
	private String YoungsModulusType_2D;
	private String textFlowStress_2D;
	private String FlowStressType_2D;
	//update version2 2016.01.27
	private String textYieldStrength_2D;
	private String textTensileStrength_2D;
	private String textElongation_2D;
	
	private String textThermalExpansionCoefficient_2D;
	private String ThermalExpansionCoefficientType_2D;
	private String textMassDensity_2D;
	private String textPoissonsRatio_2D;
	private String PoissonsRatioType_2D;
	
	private String textSolvingTime_2D;
	private String textIncrementTime_2D;
	private boolean btnParallelDDMUse_2D;
	private String spinnerDomain_2D;
	private boolean btnParallelMultiThreadUse_2D;
	private String spinnerThread_2D;
	
	
	private ArrayList<String> db_2D = new ArrayList<String>();
	private ArrayList<String> db_saveAs_2D;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public LevellerMain(){
		exportMSG = new exportInfo();
	}
	
	public void ChangedTabFolder(int type){
		if(type == 0){
			this.LevellerType = "2D";
			System.out.println(this.LevellerType);
		}else{
			this.LevellerType = "3D";
			System.out.println(this.LevellerType);
		}
	}
	
	public void AllComponentDisable(){
		med.getTextModelName().setEnabled(false);
		//----------------------------		
		med.getCompositeShapeParameter().setEnabled(false);
		med.getCompositeShapeParameterChild_1().setEnabled(false);
		med.getCompositeShapeParameterChild_2().setEnabled(false);
		med.getCompositeShapeParameterChild_3().setEnabled(false);
		med.getCompositeShapeParameterChild_4().setEnabled(false);
		med.getCompositeShapeParameterChild_5().setEnabled(false);
		med.getCompositeShapeParameterChild_6().setEnabled(false);
		med.getCompositeShapeParameterChild_7().setEnabled(false);
		med.getCompositeMeshParameter().setEnabled(false);
		med.getCompositePlateInformation().setEnabled(false);
		
		med.getCompositeShapeParameter_2D().setEnabled(false);
		med.getCompositeShapeParameterChild_1_2D().setEnabled(false);
		med.getCompositeShapeParameterChild_2_2D().setEnabled(false);
		med.getCompositeShapeParameterChild_3_2D().setEnabled(false);
		/*
		med.getCompositeShapeParameterChild_4_2D().setEnabled(false);
		med.getCompositeShapeParameterChild_5_2D().setEnabled(false);
		med.getCompositeShapeParameterChild_6_2D().setEnabled(false);
		med.getCompositeShapeParameterChild_7_2D().setEnabled(false);
		// */
		med.getCompositeMeshParameter_2D().setEnabled(false);
		med.getCompositePlateInformation_2D().setEnabled(false);
		
		//----------------------------
		med.getCompositeRollParameter().setEnabled(false);
	
		med.getCompositeRollParameter_2D().setEnabled(false);
		//----------------------------
		med.getCompositeMaterialParameter().setEnabled(false);
		med.getCompositeSolvingOption().setEnabled(false);
		med.getBtnApply().setEnabled(false);
		
		med.getCompositeMaterialParameter_2D().setEnabled(false);
		med.getCompositeSolvingOption_2D().setEnabled(false);
		med.getBtnApply_2D().setEnabled(false);
		
	}
	
	public void AllComponentEnable(){
		med.getTextModelName().setEnabled(false);
		//----------------------------		
		med.getCompositeShapeParameter().setEnabled(true);
		med.getCompositeShapeParameterChild_1().setEnabled(true);
		med.getCompositeShapeParameterChild_2().setEnabled(true);
		med.getCompositeShapeParameterChild_3().setEnabled(true);
		med.getCompositeShapeParameterChild_4().setEnabled(true);
		med.getCompositeShapeParameterChild_5().setEnabled(true);
		med.getCompositeShapeParameterChild_6().setEnabled(true);
		med.getCompositeShapeParameterChild_7().setEnabled(true);
		med.getCompositeMeshParameter().setEnabled(true);
		med.getCompositePlateInformation().setEnabled(true);
		
		med.getCompositeShapeParameter_2D().setEnabled(true);
		med.getCompositeShapeParameterChild_1_2D().setEnabled(true);
		med.getCompositeShapeParameterChild_2_2D().setEnabled(true);
		med.getCompositeShapeParameterChild_3_2D().setEnabled(true);
		/*
		med.getCompositeShapeParameterChild_4_2D().setEnabled(true);
		med.getCompositeShapeParameterChild_5_2D().setEnabled(true);
		med.getCompositeShapeParameterChild_6_2D().setEnabled(true);
		med.getCompositeShapeParameterChild_7_2D().setEnabled(true);
		// */
		med.getCompositeMeshParameter_2D().setEnabled(true);
		med.getCompositePlateInformation_2D().setEnabled(true);
		//----------------------------
		med.getCompositeRollParameter().setEnabled(true);
		
		med.getCompositeRollParameter_2D().setEnabled(true);
		//----------------------------
		med.getCompositeMaterialParameter().setEnabled(true);
		med.getCompositeMaterialParameter_2D().setEnabled(true);
		// update version2 2016.01.27
		/*
		if(med.getBtnRadioConstant_FS().getSelection()){
			//Constants
			med.getTextFlowStress().setEnabled(false);
			med.getTextYieldStrength().setEnabled(true);
			med.getTextTensileStrength().setEnabled(true);
			med.getTextElongation().setEnabled(true);
		}else{*/
			//Table
		/*
			med.getTextFlowStress().setEnabled(true);
			med.getTextYieldStrength().setEnabled(false);
			med.getTextTensileStrength().setEnabled(false);
			med.getTextElongation().setEnabled(false);
			
			med.getTextFlowStress_2D().setEnabled(true);
			med.getTextYieldStrength_2D().setEnabled(false);
			med.getTextTensileStrength_2D().setEnabled(false);
			med.getTextElongation_2D().setEnabled(false);
		*/
		//}
		
		/*	
		if(this.FlowStressType.equals("Constant")){
			med.getTextFlowStress().setEnabled(false);
			med.getTextYieldStrength().setEnabled(true);
			med.getTextTensileStrength().setEnabled(true);
			med.getTextElongation().setEnabled(true);
		}else if(this.FlowStressType.equals("Table")){
			med.getTextFlowStress().setEnabled(true);
			med.getTextYieldStrength().setEnabled(false);
			med.getTextTensileStrength().setEnabled(false);
			med.getTextElongation().setEnabled(false);
		}else{
			med.getTextFlowStress().setEnabled(true);
			med.getTextYieldStrength().setEnabled(false);
			med.getTextTensileStrength().setEnabled(false);
			med.getTextElongation().setEnabled(false);
		}
		*/
		
		med.getCompositeSolvingOption().setEnabled(true);
		med.getBtnApply().setEnabled(true);
		
		med.getCompositeSolvingOption_2D().setEnabled(true);
		med.getBtnApply_2D().setEnabled(true);
	}
	
	public void ReadInitValue(){
		//update-160314 
		String initValuePath = myUtil.setPath(System.getProperty("user.dir"), "InitValue.txt");
		Reader initValueObj = new Reader(initValuePath);
		initValueObj.running();
		ArrayList<String> fileData = new ArrayList<String>();
		fileData = initValueObj.getFileDataList();
		
		for(String line : fileData){
			String data = line.trim();
			if(data.contains("=")){
				ArrayList<String> splitDataTokens = new ArrayList<String>();
				splitDataTokens = ParserDefault.splitLineData(data,"=");
				InitValueMap.put(splitDataTokens.get(0).trim(), splitDataTokens.get(1).trim());
			}
		}
		/*
		System.out.println("aaaaaaaaa");
		
		Iterator<String> iterator = InitValueMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
		    System.out.print("key="+key);
		    System.out.println(" value="+InitValueMap.get(key));
		}		
		
		System.out.println("bbbbbb");
		*/
	}
	
	public void init_AllComponentValue(){
		ReadInitValue();
		// 3D
		med.getComboType().select(0);
		
		Image img = ImageDescriptor.createFromFile(View.class,ImagePath.Type0).createImage();
		img = ImageDescriptor.createFromFile(View.class,ImagePath.Type1).createImage();
		med.getCompositeShapeParameterChild_1().setVisible(true);
		med.getCompositeShapeParameterChild_2().setVisible(false);
		med.getCompositeShapeParameterChild_3().setVisible(false);
		med.getCompositeShapeParameterChild_4().setVisible(false);
		med.getCompositeShapeParameterChild_5().setVisible(false);
		med.getCompositeShapeParameterChild_6().setVisible(false);
		med.getCompositeShapeParameterChild_7().setVisible(false);
		
		med.getLblPhoto().setImage(img);
		med.getLblPhoto().pack();
		
		med.getTextWidth().setText(InitValueMap.get("Width"));
		med.getTextLength().setText(InitValueMap.get("Length"));
		med.getTextThickness().setText(InitValueMap.get("Thickness"));
		
		med.getType2_textLeftEdgeWavePitch().setText(InitValueMap.get("type2_LeftEdgeWavePitch"));
		med.getType2_textRightEdgeWavePitch().setText(InitValueMap.get("type2_RightEdgeWavePitch"));
		med.getType2_textLeftEdgeWaveHeight().setText(InitValueMap.get("type2_LeftEdgeWaveHeight"));
		med.getType2_textRightEdgeWaveHeight().setText(InitValueMap.get("type2_RightEdgeWaveHeight"));
		med.getType2_textLeftEdgeWavePhase().setText(InitValueMap.get("type2_LeftEdgeWavePhase"));
		med.getType2_textRightEdgeWavePhase().setText(InitValueMap.get("type2_RightEdgeWavePhase"));
		
		med.getType3_textWaveHeight().setText(InitValueMap.get("type3_WaveHeight"));
		med.getType3_textWavePitch().setText(InitValueMap.get("type3_WavePitch"));
		
		med.getType4_textGutterHeight().setText(InitValueMap.get("type4_GutterHeight"));
		
		med.getType5_textGutterHeight().setText(InitValueMap.get("type5_GutterHeight"));
		med.getType5_textGutterLength().setText(InitValueMap.get("type5_GutterLength"));
		
		med.getType6_textHeadGutterHeight().setText(InitValueMap.get("type6_HeadGutterHeight"));
		med.getType6_textHeadGutterLength().setText(InitValueMap.get("type6_HeadGutterLength"));
		med.getType6_textTailGutterHeight().setText(InitValueMap.get("type6_TailGutterHeight"));
		med.getType6_textTailGutterLength().setText(InitValueMap.get("type6_TailGutterLength"));
		
		med.getType7_textGutterLength().setText(InitValueMap.get("type7_GutterLength"));
		med.getType7_textGutterLengthLength().setText(InitValueMap.get("type7_GutterLengthLength"));
		med.getType7_textGutterWidthLength().setText(InitValueMap.get("type7_GutterWidthLength"));
		med.getType7_textHeadGutterHeight().setText(InitValueMap.get("type7_HeadGutterHeight"));

		med.getTextThicknessElementNum().setText(InitValueMap.get("ThicknessElementNum"));
		med.getTextWidthAspectRatio().setText(InitValueMap.get("WidthAspectRatio"));
		med.getTextLengthAspectRatio().setText(InitValueMap.get("LengthAspectRatio"));
		CalcElementNumber();
		
		med.getTextPlateVelocity().setText(InitValueMap.get("PlateVelocity"));
		med.getTextTemperatureStart().setText(InitValueMap.get("TemperatureStart"));
		med.getTextTemperatureEnd().setText(InitValueMap.get("TemperatureEnd"));
		//update version2 2016.01.27
		med.getTextPassLineOffset().setText(InitValueMap.get("PassLineOffset"));
		
		med.getSpinnerUpperRollNum().setSelection(Integer.parseInt(InitValueMap.get("UpperRollNum")));
		med.getSpinnerLowerRollNum().setSelection(Integer.parseInt(InitValueMap.get("LowerRollNum")));
		med.getTextRollPitch().setText(InitValueMap.get("RollPitch"));
		med.getTextRollLength().setText(InitValueMap.get("RollLength"));
		med.getTextEntryUpperRollGap().setText(InitValueMap.get("EntryUpperRollGap"));
		med.getTextExitUpperRollGap().setText(InitValueMap.get("ExitUpperRollGap"));
		med.getTextEntryLowerRollGap().setText(InitValueMap.get("EntryLowerRollGap"));
		med.getTextExitLowerRollGap().setText(InitValueMap.get("ExitLowerRollGap"));
		med.getTextRollFriction().setText(InitValueMap.get("RollFriction"));
		med.getTextRollDiameter().setText(InitValueMap.get("RollDiameter"));
		//update version2 2016.01.27
		//med.getTextRollCrown().setText(InitValueMap.get("RollCrown"));
		med.getTextUpperRollCrown().setText(InitValueMap.get("UpperRollCrown"));
		med.getTextLowerRollCrown().setText(InitValueMap.get("LowerRollCrown"));
		//med.getBtnRadioNone_RC().setSelection(false);
		//med.getBtnRadioApply_RC().setSelection(true);
		med.getTextMillStiffness().setText(InitValueMap.get("MillStiffness"));
		med.getBtnRadioRigid_MS().setSelection(false);
		med.getBtnRadioSpring_MS().setSelection(true);
		
		/*
		if(med.getBtnRadioNone_RC().getSelection()){
			// None
			med.getTextRollCrown().setText(InitValueMap.get("RollCrown"));
			med.getTextRollCrown().setEnabled(false);
		}else {
			// Apply
			med.getTextRollCrown().setText(InitValueMap.get("RollCrown"));
			med.getTextRollCrown().setEnabled(true);
		}
		*/
		
		if(med.getBtnRadioRigid_MS().getSelection()){
			// Rigid
			med.getTextMillStiffness().setText(InitValueMap.get("MillStiffness"));
			med.getTextMillStiffness().setEnabled(false);
		}else {
			// Spring
			med.getTextMillStiffness().setText(InitValueMap.get("MillStiffness"));
			med.getTextMillStiffness().setEnabled(true);
		}
		
		//update 2017.07.20 leveller version3
		med.getTextUpperEntryRollGapMovement().setText(InitValueMap.get("UpperEntryRollGapMovement"));
		med.getTextUpperExitRollGapMovement().setText(InitValueMap.get("UpperExitRollGapMovement"));
		med.getTextUpperRollGapStayingTime().setText(InitValueMap.get("UpperRollGapStayingTime"));
		med.getTextUpperRollGapMovingTime().setText(InitValueMap.get("UpperRollGapMovingTime"));
		med.getTextLowerEntryRollGapMovement().setText(InitValueMap.get("LowerEntryRollGapMovement"));
		med.getTextLowerExitRollGapMovement().setText(InitValueMap.get("LowerExitRollGapMovement"));
		med.getTextLowerRollGapStayingTime().setText(InitValueMap.get("LowerRollGapStayingTime"));
		med.getTextLowerRollGapMovingTime().setText(InitValueMap.get("LowerRollGapMovingTime"));
		String RollType = InitValueMap.get("HDRollType");
		if(RollType.equals("None")){
			med.getBtnUpper().setSelection(false);
			med.getBtnLower().setSelection(false);
			med.getBtnNone().setSelection(true);
		}else if(RollType.equals("Upper")){
			med.getBtnNone().setSelection(false);
			med.getBtnLower().setSelection(false);
			med.getBtnUpper().setSelection(true);
		}else if(RollType.equals("Lower")){
			med.getBtnNone().setSelection(false);
			med.getBtnUpper().setSelection(false);
			med.getBtnLower().setSelection(true);
		}
		med.getTextFrontHDRollDia().setText(InitValueMap.get("FrontHDRollDia"));
		med.getTextFrontHDRollPitch().setText(InitValueMap.get("FrontHDRollPitch"));
		med.getTextFrontHDRollVericalPos().setText(InitValueMap.get("FrontHDRollVericalPos"));
		med.getTextRearHDRollDia().setText(InitValueMap.get("RearHDRollDia"));
		med.getTextRearHDRollPitch().setText(InitValueMap.get("RearHDRollPitch"));
		med.getTextRearHDRollVerticalPos().setText(InitValueMap.get("RearHDRollVerticalPos"));
		
		
		CreateRoll();
		
		String matPath = myUtil.setPath(System.getProperty("user.dir"), "materialData");
		String YoungsModulus					= myUtil.setPath(matPath, "elastic_modulus.txt");
		String FlowStress						= myUtil.setPath(matPath, "flow_stress.txt");
		String ThermalExpansionCoefficient		= myUtil.setPath(matPath, "expansion.txt");
		String PoissonsRatio					= myUtil.setPath(matPath, "poisson.txt");
		//update version2 2016.01.27
		
		med.getBtnRadioTable_YM().setSelection(true);
		med.getBtnRadioConstant_YM().setSelection(false);
		med.getBtnRadioTable_FS().setSelection(true);
		med.getBtnRadioConstant_FS().setSelection(false);
		med.getBtnRadioTable_TEC().setSelection(true);
		med.getBtnRadioConstant_TEC().setSelection(false);
		med.getBtnRadioTable_PR().setSelection(true);
		med.getBtnRadioConstant_PR().setSelection(false);
		
		med.getTextYoungsModulus().setText(YoungsModulus);

		if(med.getBtnRadioConstant_FS().getSelection()){
			//Constant
			med.getTextFlowStress().setText("");
			med.getTextYieldStrength().setText(InitValueMap.get("YieldStrength"));
			med.getTextTensileStrength().setText(InitValueMap.get("TensileStrength"));
			med.getTextElongation().setText(InitValueMap.get("Elongation"));
		}else{
			//Table
			med.getTextFlowStress().setText(FlowStress);
			med.getTextYieldStrength().setText("");
			med.getTextTensileStrength().setText("");
			med.getTextElongation().setText("");
		}
		
		
		med.getTextThermalExpansionCoefficient().setText(ThermalExpansionCoefficient);
		med.getTextPoissonsRatio().setText(PoissonsRatio);
		med.getTextMassDensity().setText(InitValueMap.get("MassDensity"));
		
		
		
		med.getTextIncrementTime().setText(InitValueMap.get("IncrementTime"));
		med.getSpinnerDomain().setSelection(Integer.parseInt(InitValueMap.get("Domain")));
		med.getSpinnerThread().setSelection(Integer.parseInt(InitValueMap.get("Thread")));
		
		med.getBtnParallelDDMUse().setSelection(false);
		med.getSpinnerDomain().setEnabled(false);
		med.getBtnParallelMultiThreadUse().setSelection(false);
		med.getSpinnerThread().setEnabled(false);
		
		
		
		
		
		
		
		
		//2D
		med.getComboType_2D().select(0);
		
		Image img_2D = ImageDescriptor.createFromFile(View.class,ImagePath.Type0).createImage();
		img_2D = ImageDescriptor.createFromFile(View.class,ImagePath.Type1_2D).createImage();
		med.getCompositeShapeParameterChild_1_2D().setVisible(true);
		med.getCompositeShapeParameterChild_2_2D().setVisible(false);
		med.getCompositeShapeParameterChild_3_2D().setVisible(false);
		/*
		med.getCompositeShapeParameterChild_4_2D().setVisible(false);
		med.getCompositeShapeParameterChild_5_2D().setVisible(false);
		med.getCompositeShapeParameterChild_6_2D().setVisible(false);
		med.getCompositeShapeParameterChild_7_2D().setVisible(false);
		// */
		med.getLblPhoto_2D().setImage(img_2D);
		med.getLblPhoto_2D().pack();
		
		med.getTextWidth_2D().setText(InitValueMap.get("Width_2D"));
		med.getTextLength_2D().setText(InitValueMap.get("Length_2D"));
		med.getTextThickness_2D().setText(InitValueMap.get("Thickness_2D"));
		
		/*
		med.getType2_textLeftEdgeWavePitch_2D().setText(InitValueMap.get("type2_LeftEdgeWavePitch_2D"));
		med.getType2_textRightEdgeWavePitch_2D().setText(InitValueMap.get("type2_RightEdgeWavePitch_2D"));
		med.getType2_textLeftEdgeWaveHeight_2D().setText(InitValueMap.get("type2_LeftEdgeWaveHeight_2D"));
		med.getType2_textRightEdgeWaveHeight_2D().setText(InitValueMap.get("type2_RightEdgeWaveHeight_2D"));
		med.getType2_textLeftEdgeWavePhase_2D().setText(InitValueMap.get("type2_LeftEdgeWavePhase_2D"));
		med.getType2_textRightEdgeWavePhase_2D().setText(InitValueMap.get("type2_RightEdgeWavePhase_2D"));
		// */
		med.getType2_textWavePitch_2D().setText(InitValueMap.get("type2_WavePitch_2D"));
		med.getType2_textWaveHeight_2D().setText(InitValueMap.get("type2_WaveHeight_2D"));
		med.getType2_textWavePhase_2D().setText(InitValueMap.get("type2_WavePhase_2D"));
		/*
		med.getType3_textWaveHeight_2D().setText(InitValueMap.get("type3_WaveHeight_2D"));
		med.getType3_textWavePitch_2D().setText(InitValueMap.get("type3_WavePitch_2D"));
		// */
		med.getType3_textFrontCurlHeight_2D().setText(InitValueMap.get("type3_FrontCurlHeight_2D"));
		med.getType3_textFrontCurlLength_2D().setText(InitValueMap.get("type3_FrontCurlLength_2D"));
		med.getType3_textRearCurlHeight_2D().setText(InitValueMap.get("type3_RearCurlHeight_2D"));
		med.getType3_textRearCurlLength_2D().setText(InitValueMap.get("type3_RearCurlLength_2D"));
		/*
		med.getType4_textGutterHeight_2D().setText(InitValueMap.get("type4_GutterHeight_2D"));
		
		med.getType5_textGutterHeight_2D().setText(InitValueMap.get("type5_GutterHeight_2D"));
		med.getType5_textGutterLength_2D().setText(InitValueMap.get("type5_GutterLength_2D"));
		
		med.getType6_textHeadGutterHeight_2D().setText(InitValueMap.get("type6_HeadGutterHeight_2D"));
		med.getType6_textHeadGutterLength_2D().setText(InitValueMap.get("type6_HeadGutterLength_2D"));
		med.getType6_textTailGutterHeight_2D().setText(InitValueMap.get("type6_TailGutterHeight_2D"));
		med.getType6_textTailGutterLength_2D().setText(InitValueMap.get("type6_TailGutterLength_2D"));
		
		med.getType7_textGutterLength_2D().setText(InitValueMap.get("type7_GutterLength_2D"));
		med.getType7_textGutterLengthLength_2D().setText(InitValueMap.get("type7_GutterLengthLength_2D"));
		med.getType7_textGutterWidthLength_2D().setText(InitValueMap.get("type7_GutterWidthLength_2D"));
		med.getType7_textHeadGutterHeight_2D().setText(InitValueMap.get("type7_HeadGutterHeight_2D"));
		// */
		med.getTextThicknessElementNum_2D().setText(InitValueMap.get("ThicknessElementNum_2D"));
		//med.getTextWidthAspectRatio_2D().setText(InitValueMap.get("WidthAspectRatio_2D"));
		med.getTextLengthAspectRatio_2D().setText(InitValueMap.get("LengthAspectRatio_2D"));
		CalcElementNumber_2D();
		
		med.getTextPlateVelocity_2D().setText(InitValueMap.get("PlateVelocity_2D"));
		med.getTextTemperatureStart_2D().setText(InitValueMap.get("TemperatureStart_2D"));
		med.getTextTemperatureEnd_2D().setText(InitValueMap.get("TemperatureEnd_2D"));
		//update version2 2016.01.27
		med.getTextPassLineOffset_2D().setText(InitValueMap.get("PassLineOffset_2D"));
		
		med.getSpinnerUpperRollNum_2D().setSelection(Integer.parseInt(InitValueMap.get("UpperRollNum_2D")));
		med.getSpinnerLowerRollNum_2D().setSelection(Integer.parseInt(InitValueMap.get("LowerRollNum_2D")));
		med.getTextRollPitch_2D().setText(InitValueMap.get("RollPitch_2D"));
		//med.getTextRollLength_2D().setText(InitValueMap.get("RollLength_2D"));
		med.getTextEntryUpperRollGap_2D().setText(InitValueMap.get("EntryUpperRollGap_2D"));
		med.getTextExitUpperRollGap_2D().setText(InitValueMap.get("ExitUpperRollGap_2D"));
		med.getTextEntryLowerRollGap_2D().setText(InitValueMap.get("EntryLowerRollGap_2D"));
		med.getTextExitLowerRollGap_2D().setText(InitValueMap.get("ExitLowerRollGap_2D"));
		med.getTextRollFriction_2D().setText(InitValueMap.get("RollFriction_2D"));
		med.getTextRollDiameter_2D().setText(InitValueMap.get("RollDiameter_2D"));
		//update version2 2016.01.27
		//med.getTextRollCrown_2D().setText(InitValueMap.get("RollCrown_2D"));
		//med.getTextUpperRollCrown_2D().setText(InitValueMap.get("UpperRollCrown_2D"));
		//med.getTextLowerRollCrown_2D().setText(InitValueMap.get("LowerRollCrown_2D"));
		//med.getBtnRadioNone_RC_2D().setSelection(false);
		//med.getBtnRadioApply_RC_2D().setSelection(true);
		med.getTextMillStiffness_2D().setText(InitValueMap.get("MillStiffness_2D"));
		med.getBtnRadioRigid_MS_2D().setSelection(false);
		med.getBtnRadioSpring_MS_2D().setSelection(true);
		/*
		if(med.getBtnRadioNone_RC_2D().getSelection()){
			// None
			med.getTextRollCrown_2D().setText(InitValueMap.get("RollCrown_2D"));
			med.getTextRollCrown_2D().setEnabled(false);
		}else {
			// Apply
			med.getTextRollCrown_2D().setText(InitValueMap.get("RollCrown_2D"));
			med.getTextRollCrown_2D().setEnabled(true);
		}
		//*/
		if(med.getBtnRadioRigid_MS_2D().getSelection()){
			// Rigid
			med.getTextMillStiffness_2D().setText(InitValueMap.get("MillStiffness_2D"));
			med.getTextMillStiffness_2D().setEnabled(false);
		}else {
			// Spring
			med.getTextMillStiffness_2D().setText(InitValueMap.get("MillStiffness_2D"));
			med.getTextMillStiffness_2D().setEnabled(true);
		}
		
		//update 2017.07.20 leveller version3
		med.getTextUpperEntryRollGapMovement_2D().setText(InitValueMap.get("UpperEntryRollGapMovement_2D"));
		med.getTextUpperExitRollGapMovement_2D().setText(InitValueMap.get("UpperExitRollGapMovement_2D"));
		med.getTextUpperRollGapStayingTime_2D().setText(InitValueMap.get("UpperRollGapStayingTime_2D"));
		med.getTextUpperRollGapMovingTime_2D().setText(InitValueMap.get("UpperRollGapMovingTime_2D"));
		med.getTextLowerEntryRollGapMovement_2D().setText(InitValueMap.get("LowerEntryRollGapMovement_2D"));
		med.getTextLowerExitRollGapMovement_2D().setText(InitValueMap.get("LowerExitRollGapMovement_2D"));
		med.getTextLowerRollGapStayingTime_2D().setText(InitValueMap.get("LowerRollGapStayingTime_2D"));
		med.getTextLowerRollGapMovingTime_2D().setText(InitValueMap.get("LowerRollGapMovingTime_2D"));
		String RollType_2D = InitValueMap.get("HDRollType_2D");
		if(RollType_2D.equals("None")){
			med.getBtnUpper_2D().setSelection(false);
			med.getBtnLower_2D().setSelection(false);
			med.getBtnNone_2D().setSelection(true);
		}else if(RollType_2D.equals("Upper")){
			med.getBtnNone_2D().setSelection(false);
			med.getBtnLower_2D().setSelection(false);
			med.getBtnUpper_2D().setSelection(true);
		}else if(RollType_2D.equals("Lower")){
			med.getBtnNone_2D().setSelection(false);
			med.getBtnUpper_2D().setSelection(false);
			med.getBtnLower_2D().setSelection(true);
		}
		med.getTextFrontHDRollDia_2D().setText(InitValueMap.get("FrontHDRollDia_2D"));
		med.getTextFrontHDRollPitch_2D().setText(InitValueMap.get("FrontHDRollPitch_2D"));
		med.getTextFrontHDRollVericalPos_2D().setText(InitValueMap.get("FrontHDRollVericalPos_2D"));
		med.getTextRearHDRollDia_2D().setText(InitValueMap.get("RearHDRollDia_2D"));
		med.getTextRearHDRollPitch_2D().setText(InitValueMap.get("RearHDRollPitch_2D"));
		med.getTextRearHDRollVerticalPos_2D().setText(InitValueMap.get("RearHDRollVerticalPos_2D"));
		
		
		CreateRoll_2D();
		
		String matPath_2D 						= myUtil.setPath(System.getProperty("user.dir"), "materialData");
		String YoungsModulus_2D					= myUtil.setPath(matPath, "elastic_modulus.txt");
		String FlowStress_2D					= myUtil.setPath(matPath, "flow_stress.txt");
		String ThermalExpansionCoefficient_2D	= myUtil.setPath(matPath, "expansion.txt");
		String PoissonsRatio_2D					= myUtil.setPath(matPath, "poisson.txt");
		//update version2 2016.01.27
		
		med.getBtnRadioTable_YM_2D().setSelection(true);
		med.getBtnRadioConstant_YM_2D().setSelection(false);
		med.getBtnRadioTable_FS_2D().setSelection(true);
		med.getBtnRadioConstant_FS_2D().setSelection(false);
		med.getBtnRadioTable_TEC_2D().setSelection(true);
		med.getBtnRadioConstant_TEC_2D().setSelection(false);
		med.getBtnRadioTable_PR_2D().setSelection(true);
		med.getBtnRadioConstant_PR_2D().setSelection(false);
		
		med.getTextYoungsModulus_2D().setText(YoungsModulus_2D);

		if(med.getBtnRadioConstant_FS_2D().getSelection()){
			//Constant
			med.getTextFlowStress_2D().setText("");
			med.getTextYieldStrength_2D().setText(InitValueMap.get("YieldStrength_2D"));
			med.getTextTensileStrength_2D().setText(InitValueMap.get("TensileStrength_2D"));
			med.getTextElongation_2D().setText(InitValueMap.get("Elongation_2D"));
		}else{
			//Table
			med.getTextFlowStress_2D().setText(FlowStress_2D);
			med.getTextYieldStrength_2D().setText("");
			med.getTextTensileStrength_2D().setText("");
			med.getTextElongation_2D().setText("");
		}
		
		
		med.getTextThermalExpansionCoefficient_2D().setText(ThermalExpansionCoefficient_2D);
		med.getTextPoissonsRatio_2D().setText(PoissonsRatio_2D);
		med.getTextMassDensity_2D().setText(InitValueMap.get("MassDensity_2D"));
		
		
		
		med.getTextIncrementTime_2D().setText(InitValueMap.get("IncrementTime_2D"));
		med.getSpinnerDomain_2D().setSelection(Integer.parseInt(InitValueMap.get("Domain_2D")));
		med.getSpinnerThread_2D().setSelection(Integer.parseInt(InitValueMap.get("Thread_2D")));
		
		med.getBtnParallelDDMUse_2D().setSelection(false);
		med.getSpinnerDomain_2D().setEnabled(false);
		med.getBtnParallelMultiThreadUse_2D().setSelection(false);
		med.getSpinnerThread_2D().setEnabled(false);
		
	}
	
	
	
	
	public void ChangePlateType(){
		this.db.clear();
		//this.db_saveAs.clear();
		this.saveAllData();
		
		String plateType = med.getComboType().getText();
		this.comboType = plateType;
		Image img = ImageDescriptor.createFromFile(View.class,ImagePath.Type0).createImage();
		med.getCompositeShapeParameterChild_1().setVisible(false);
		med.getCompositeShapeParameterChild_2().setVisible(false);
		med.getCompositeShapeParameterChild_3().setVisible(false);
		med.getCompositeShapeParameterChild_4().setVisible(false);
		med.getCompositeShapeParameterChild_5().setVisible(false);
		med.getCompositeShapeParameterChild_6().setVisible(false);
		med.getCompositeShapeParameterChild_7().setVisible(false);
		
		med.getTextWidth().setText(this.textWidth);
		med.getTextLength().setText(this.textLength);
		med.getTextThickness().setText(this.textThickness);
		
		if(plateType.equals(ComboLabel.TYPE1)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type1).createImage();
			med.getCompositeShapeParameterChild_1().setVisible(true);
			
		}else if(plateType.equals(ComboLabel.TYPE2)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type2).createImage();
			med.getCompositeShapeParameterChild_2().setVisible(true);
			try{
				if(comboType.equals(plateType)){
					/*
					System.out.println(this.type2_textLeftEdgeWaveHeight);
					System.out.println(this.type2_textLeftEdgeWavePhase);
					System.out.println(this.type2_textLeftEdgeWavePitch);
					System.out.println(this.type2_textRightEdgeWaveHeight);
					System.out.println(this.type2_textRightEdgeWavePhase);
					System.out.println(this.type2_textRightEdgeWavePitch);
					// */
					med.getType2_textLeftEdgeWavePitch().setText(this.type2_textLeftEdgeWavePitch);
					med.getType2_textRightEdgeWavePitch().setText(this.type2_textRightEdgeWavePitch);
					med.getType2_textLeftEdgeWaveHeight().setText(this.type2_textLeftEdgeWaveHeight);
					med.getType2_textRightEdgeWaveHeight().setText(this.type2_textRightEdgeWaveHeight);
					med.getType2_textLeftEdgeWavePhase().setText(this.type2_textLeftEdgeWavePhase);
					med.getType2_textRightEdgeWavePhase().setText(this.type2_textRightEdgeWavePhase);
				}else{
					med.getType2_textLeftEdgeWavePitch().setText(InitValueMap.get("type2_LeftEdgeWavePitch"));
					med.getType2_textRightEdgeWavePitch().setText(InitValueMap.get("type2_RightEdgeWavePitch"));
					med.getType2_textLeftEdgeWaveHeight().setText(InitValueMap.get("type2_LeftEdgeWaveHeight"));
					med.getType2_textRightEdgeWaveHeight().setText(InitValueMap.get("type2_RightEdgeWaveHeight"));
					med.getType2_textLeftEdgeWavePhase().setText(InitValueMap.get("type2_LeftEdgeWavePhase"));
					med.getType2_textRightEdgeWavePhase().setText(InitValueMap.get("type2_RightEdgeWavePhase"));
					
				}
			}catch(Exception e){
				med.getType2_textLeftEdgeWavePitch().setText(InitValueMap.get("type2_LeftEdgeWavePitch"));
				med.getType2_textRightEdgeWavePitch().setText(InitValueMap.get("type2_RightEdgeWavePitch"));
				med.getType2_textLeftEdgeWaveHeight().setText(InitValueMap.get("type2_LeftEdgeWaveHeight"));
				med.getType2_textRightEdgeWaveHeight().setText(InitValueMap.get("type2_RightEdgeWaveHeight"));
				med.getType2_textLeftEdgeWavePhase().setText(InitValueMap.get("type2_LeftEdgeWavePhase"));
				med.getType2_textRightEdgeWavePhase().setText(InitValueMap.get("type2_RightEdgeWavePhase"));
			}
			
		}else if(plateType.equals(ComboLabel.TYPE3)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type3).createImage();
			med.getCompositeShapeParameterChild_3().setVisible(true);
			try{
				if(comboType.equals(plateType)){
					med.getType3_textWaveHeight().setText(this.type3_textWaveHeight);
					med.getType3_textWavePitch().setText(this.type3_textWavePitch);
				}else{
					med.getType3_textWaveHeight().setText(InitValueMap.get("type3_WaveHeight"));
					med.getType3_textWavePitch().setText(InitValueMap.get("type3_WavePitch"));
				}
			}catch(Exception e){
				med.getType3_textWaveHeight().setText(InitValueMap.get("type3_WaveHeight"));
				med.getType3_textWavePitch().setText(InitValueMap.get("type3_WavePitch"));
			}
			
		}else if(plateType.equals(ComboLabel.TYPE4)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type4).createImage();
			med.getCompositeShapeParameterChild_4().setVisible(true);
			try{
				if(comboType.equals(plateType)){
					med.getType4_textGutterHeight().setText(this.type4_textGutterHeight);
				}else{
					med.getType4_textGutterHeight().setText(InitValueMap.get("type4_GutterHeight"));
				}
			}catch(Exception e){
				med.getType4_textGutterHeight().setText(InitValueMap.get("type4_GutterHeight"));
			}
			
		}else if(plateType.equals(ComboLabel.TYPE5)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type5).createImage();
			med.getCompositeShapeParameterChild_5().setVisible(true);
			try{
				if(comboType.equals(plateType)){
					med.getType5_textGutterHeight().setText(this.type5_textGutterHeight);
					med.getType5_textGutterLength().setText(this.type5_textGutterLength);
				}else{
					med.getType5_textGutterHeight().setText(InitValueMap.get("type5_GutterHeight"));
					med.getType5_textGutterLength().setText(InitValueMap.get("type5_GutterLength"));
				}
			}catch(Exception e){
				med.getType5_textGutterHeight().setText(InitValueMap.get("type5_GutterHeight"));
				med.getType5_textGutterLength().setText(InitValueMap.get("type5_GutterLength"));
			}
		}else if(plateType.equals(ComboLabel.TYPE6)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type6).createImage();
			med.getCompositeShapeParameterChild_6().setVisible(true);
			try{
				if(comboType.equals(plateType)){
					med.getType6_textHeadGutterHeight().setText(this.type6_textHeadGutterHeight);
					med.getType6_textHeadGutterLength().setText(this.type6_textHeadGutterLength);
					med.getType6_textTailGutterHeight().setText(this.type6_textTailGutterHeight);
					med.getType6_textTailGutterLength().setText(this.type6_textTailGutterLength);
				}else{
					med.getType6_textHeadGutterHeight().setText(InitValueMap.get("type6_HeadGutterHeight"));
					med.getType6_textHeadGutterLength().setText(InitValueMap.get("type6_HeadGutterLength"));
					med.getType6_textTailGutterHeight().setText(InitValueMap.get("type6_TailGutterHeight"));
					med.getType6_textTailGutterLength().setText(InitValueMap.get("type6_TailGutterLength"));
				}
			}catch(Exception e){
				med.getType6_textHeadGutterHeight().setText(InitValueMap.get("type6_HeadGutterHeight"));
				med.getType6_textHeadGutterLength().setText(InitValueMap.get("type6_HeadGutterLength"));
				med.getType6_textTailGutterHeight().setText(InitValueMap.get("type6_TailGutterHeight"));
				med.getType6_textTailGutterLength().setText(InitValueMap.get("type6_TailGutterLength"));
			}
			
			
		}else if(plateType.equals(ComboLabel.TYPE7)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type7).createImage();
			med.getCompositeShapeParameterChild_7().setVisible(true);
			try{
				if(comboType.equals(plateType)){
					med.getType7_textGutterLength().setText(this.type7_textGutterLength);
					med.getType7_textGutterLengthLength().setText(this.type7_textGutterLengthLength);
					med.getType7_textGutterWidthLength().setText(this.type7_textGutterWidthLength);
					med.getType7_textHeadGutterHeight().setText(this.type7_textHeadGutterHeight);
				}else{
					med.getType7_textGutterLength().setText(InitValueMap.get("type7_GutterLength"));
					med.getType7_textGutterLengthLength().setText(InitValueMap.get("type7_GutterLengthLength"));
					med.getType7_textGutterWidthLength().setText(InitValueMap.get("type7_GutterWidthLength"));
					med.getType7_textHeadGutterHeight().setText(InitValueMap.get("type7_HeadGutterHeight"));
				}
			}catch(Exception e){
				med.getType7_textGutterLength().setText(InitValueMap.get("type7_GutterLength"));
				med.getType7_textGutterLengthLength().setText(InitValueMap.get("type7_GutterLengthLength"));
				med.getType7_textGutterWidthLength().setText(InitValueMap.get("type7_GutterWidthLength"));
				med.getType7_textHeadGutterHeight().setText(InitValueMap.get("type7_HeadGutterHeight"));
			}
			
		}
		
		//this.comboType = plateType;
		med.getLblPhoto().setImage(img);
		med.getLblPhoto().pack();
	}
	
	public void ChangePlateType_2D(){
		this.db_2D.clear();
		//this.db_saveAs_2D.clear();
		this.saveAllData_2D();
		String plateType = med.getComboType_2D().getText();
		this.comboType_2D = plateType;
		Image img = ImageDescriptor.createFromFile(View.class,ImagePath.Type0).createImage();
		med.getCompositeShapeParameterChild_1_2D().setVisible(false);
		med.getCompositeShapeParameterChild_2_2D().setVisible(false);
		med.getCompositeShapeParameterChild_3_2D().setVisible(false);
		/*
		med.getCompositeShapeParameterChild_4_2D().setVisible(false);
		med.getCompositeShapeParameterChild_5_2D().setVisible(false);
		med.getCompositeShapeParameterChild_6_2D().setVisible(false);
		med.getCompositeShapeParameterChild_7_2D().setVisible(false);
		// */
		
		med.getTextWidth_2D().setText(this.textWidth_2D);
		                                   
		med.getTextLength_2D().setText(this.textLength_2D);
		med.getTextThickness_2D().setText(this.textThickness_2D);	
		
		if(plateType.equals(ComboLabel.TYPE1_2D)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type1_2D).createImage();
			med.getCompositeShapeParameterChild_1_2D().setVisible(true);
			
		}else if(plateType.equals(ComboLabel.TYPE2_2D)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type2_2D).createImage();
			med.getCompositeShapeParameterChild_2_2D().setVisible(true);
			try{
				if(comboType_2D.equals(plateType)){
					/*
					med.getType2_textLeftEdgeWavePitch_2D().setText(this.type2_textLeftEdgeWavePitch_2D);
					med.getType2_textRightEdgeWavePitch_2D().setText(this.type2_textRightEdgeWavePitch_2D);
					med.getType2_textLeftEdgeWaveHeight_2D().setText(this.type2_textLeftEdgeWaveHeight_2D);
					med.getType2_textRightEdgeWaveHeight_2D().setText(this.type2_textRightEdgeWaveHeight_2D);
					med.getType2_textLeftEdgeWavePhase_2D().setText(this.type2_textLeftEdgeWavePhase_2D);
					med.getType2_textRightEdgeWavePhase_2D().setText(this.type2_textRightEdgeWavePhase_2D);
					//*/
					med.getType2_textWavePitch_2D().setText(this.type2_textWavePitch_2D);
					med.getType2_textWaveHeight_2D().setText(this.type2_textWaveHeight_2D);
					med.getType2_textWavePhase_2D().setText(this.type2_textWavePhase_2D);
				}else{
					/*
					med.getType2_textLeftEdgeWavePitch_2D().setText(InitValueMap.get("type2_LeftEdgeWavePitch_2D"));
					med.getType2_textRightEdgeWavePitch_2D().setText(InitValueMap.get("type2_RightEdgeWavePitch_2D"));
					med.getType2_textLeftEdgeWaveHeight_2D().setText(InitValueMap.get("type2_LeftEdgeWaveHeight_2D"));
					med.getType2_textRightEdgeWaveHeight_2D().setText(InitValueMap.get("type2_RightEdgeWaveHeight_2D"));
					med.getType2_textLeftEdgeWavePhase_2D().setText(InitValueMap.get("type2_LeftEdgeWavePhase_2D"));
					med.getType2_textRightEdgeWavePhase_2D().setText(InitValueMap.get("type2_RightEdgeWavePhase_2D"));
					// */
					med.getType2_textWavePitch_2D().setText(InitValueMap.get("type2_WavePitch_2D"));
					med.getType2_textWaveHeight_2D().setText(InitValueMap.get("type2_WaveHeight_2D"));
					med.getType2_textWavePhase_2D().setText(InitValueMap.get("type2_WavePhase_2D"));
				}
			}catch(Exception e){
				/*
				med.getType2_textLeftEdgeWavePitch_2D().setText(InitValueMap.get("type2_LeftEdgeWavePitch_2D"));
				med.getType2_textRightEdgeWavePitch_2D().setText(InitValueMap.get("type2_RightEdgeWavePitch_2D"));
				med.getType2_textLeftEdgeWaveHeight_2D().setText(InitValueMap.get("type2_LeftEdgeWaveHeight_2D"));
				med.getType2_textRightEdgeWaveHeight_2D().setText(InitValueMap.get("type2_RightEdgeWaveHeight_2D"));
				med.getType2_textLeftEdgeWavePhase_2D().setText(InitValueMap.get("type2_LeftEdgeWavePhase_2D"));
				med.getType2_textRightEdgeWavePhase_2D().setText(InitValueMap.get("type2_RightEdgeWavePhase_2D"));
				// */
				med.getType2_textWavePitch_2D().setText(InitValueMap.get("type2_WavePitch_2D"));
				med.getType2_textWaveHeight_2D().setText(InitValueMap.get("type2_WaveHeight_2D"));
				med.getType2_textWavePhase_2D().setText(InitValueMap.get("type2_WavePhase_2D"));
			}
			
		}else if(plateType.equals(ComboLabel.TYPE3_2D)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type3_2D).createImage();
			med.getCompositeShapeParameterChild_3_2D().setVisible(true);
			try{
				if(comboType_2D.equals(plateType)){
					/*
					med.getType3_textWaveHeight_2D().setText(this.type3_textWaveHeight_2D);
					med.getType3_textWavePitch_2D().setText(this.type3_textWavePitch_2D);
					// */
					med.getType3_textFrontCurlHeight_2D().setText(this.type3_textFrontCurlHeight_2D);
					med.getType3_textFrontCurlLength_2D().setText(this.type3_textFrontCurlLength_2D);
					med.getType3_textRearCurlHeight_2D().setText(this.type3_textRearCurlHeight_2D);
					med.getType3_textRearCurlLength_2D().setText(this.type3_textRearCurlLength_2D);
					
				}else{
					/*
					med.getType3_textWaveHeight_2D().setText(InitValueMap.get("type3_WaveHeight_2D"));
					med.getType3_textWavePitch_2D().setText(InitValueMap.get("type3_WavePitch_2D"));
					// */
					med.getType3_textFrontCurlHeight_2D().setText(InitValueMap.get("type3_RearCurlHeight_2D"));
					med.getType3_textFrontCurlLength_2D().setText(InitValueMap.get("type3_RearCurlLength_2D"));
					med.getType3_textRearCurlHeight_2D().setText(InitValueMap.get("type3_RearCurlHeight_2D"));
					med.getType3_textRearCurlLength_2D().setText(InitValueMap.get("type3_RearCurlLength_2D"));
				}
			}catch(Exception e){
				/*
				med.getType3_textWaveHeight_2D().setText(InitValueMap.get("type3_WaveHeight_2D"));
				med.getType3_textWavePitch_2D().setText(InitValueMap.get("type3_WavePitch_2D"));
				// */
				med.getType3_textFrontCurlHeight_2D().setText(InitValueMap.get("type3_RearCurlHeight_2D"));
				med.getType3_textFrontCurlLength_2D().setText(InitValueMap.get("type3_RearCurlLength_2D"));
				med.getType3_textRearCurlHeight_2D().setText(InitValueMap.get("type3_RearCurlHeight_2D"));
				med.getType3_textRearCurlLength_2D().setText(InitValueMap.get("type3_RearCurlLength_2D"));
			}
			
		}
		/*
		else if(plateType.equals(ComboLabel.TYPE4)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type4).createImage();
			med.getCompositeShapeParameterChild_4_2D().setVisible(true);
			try{
				if(comboType_2D.equals(plateType)){
					med.getType4_textGutterHeight_2D().setText(this.type4_textGutterHeight_2D);
				}else{
					med.getType4_textGutterHeight_2D().setText(InitValueMap.get("type4_GutterHeight_2D"));
				}
			}catch(Exception e){
				med.getType4_textGutterHeight_2D().setText(InitValueMap.get("type4_GutterHeight_2D"));
			}
			
		}else if(plateType.equals(ComboLabel.TYPE5)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type5).createImage();
			med.getCompositeShapeParameterChild_5_2D().setVisible(true);
			try{
				if(comboType_2D.equals(plateType)){
					med.getType5_textGutterHeight_2D().setText(this.type5_textGutterHeight_2D);
					med.getType5_textGutterLength_2D().setText(this.type5_textGutterLength_2D);
				}else{
					med.getType5_textGutterHeight_2D().setText(InitValueMap.get("type5_GutterHeight_2D"));
					med.getType5_textGutterLength_2D().setText(InitValueMap.get("type5_GutterLength_2D"));
				}
			}catch(Exception e){
				med.getType5_textGutterHeight_2D().setText(InitValueMap.get("type5_GutterHeight_2D"));
				med.getType5_textGutterLength_2D().setText(InitValueMap.get("type5_GutterLength_2D"));
			}
		}else if(plateType.equals(ComboLabel.TYPE6)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type6).createImage();
			med.getCompositeShapeParameterChild_6_2D().setVisible(true);
			try{
				if(comboType_2D.equals(plateType)){
					med.getType6_textHeadGutterHeight_2D().setText(this.type6_textHeadGutterHeight_2D);
					med.getType6_textHeadGutterLength_2D().setText(this.type6_textHeadGutterLength_2D);
					med.getType6_textTailGutterHeight_2D().setText(this.type6_textTailGutterHeight_2D);
					med.getType6_textTailGutterLength_2D().setText(this.type6_textTailGutterLength_2D);
				}else{
					med.getType6_textHeadGutterHeight_2D().setText(InitValueMap.get("type6_HeadGutterHeight_2D"));
					med.getType6_textHeadGutterLength_2D().setText(InitValueMap.get("type6_HeadGutterLength_2D"));
					med.getType6_textTailGutterHeight_2D().setText(InitValueMap.get("type6_TailGutterHeight_2D"));
					med.getType6_textTailGutterLength_2D().setText(InitValueMap.get("type6_TailGutterLength_2D"));
				}
			}catch(Exception e){
				med.getType6_textHeadGutterHeight_2D().setText(InitValueMap.get("type6_HeadGutterHeight_2D"));
				med.getType6_textHeadGutterLength_2D().setText(InitValueMap.get("type6_HeadGutterLength_2D"));
				med.getType6_textTailGutterHeight_2D().setText(InitValueMap.get("type6_TailGutterHeight_2D"));
				med.getType6_textTailGutterLength_2D().setText(InitValueMap.get("type6_TailGutterLength_2D"));
			}
			
			
		}else if(plateType.equals(ComboLabel.TYPE7)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type7).createImage();
			med.getCompositeShapeParameterChild_7_2D().setVisible(true);
			try{
				if(comboType_2D.equals(plateType)){
					med.getType7_textGutterLength_2D().setText(this.type7_textGutterLength_2D);
					med.getType7_textGutterLengthLength_2D().setText(this.type7_textGutterLengthLength_2D);
					med.getType7_textGutterWidthLength_2D().setText(this.type7_textGutterWidthLength_2D);
					med.getType7_textHeadGutterHeight_2D().setText(this.type7_textHeadGutterHeight_2D);
				}else{
					med.getType7_textGutterLength_2D().setText(InitValueMap.get("type7_GutterLength_2D"));
					med.getType7_textGutterLengthLength_2D().setText(InitValueMap.get("type7_GutterLengthLength_2D"));
					med.getType7_textGutterWidthLength_2D().setText(InitValueMap.get("type7_GutterWidthLength_2D"));
					med.getType7_textHeadGutterHeight_2D().setText(InitValueMap.get("type7_HeadGutterHeight_2D"));
				}
			}catch(Exception e){
				med.getType7_textGutterLength_2D().setText(InitValueMap.get("type7_GutterLength_2D"));
				med.getType7_textGutterLengthLength_2D().setText(InitValueMap.get("type7_GutterLengthLength_2D"));
				med.getType7_textGutterWidthLength_2D().setText(InitValueMap.get("type7_GutterWidthLength_2D"));
				med.getType7_textHeadGutterHeight_2D().setText(InitValueMap.get("type7_HeadGutterHeight_2D"));
			}
			
		}
		// */
		//this.comboType = plateType;
		med.getLblPhoto_2D().setImage(img);
		med.getLblPhoto_2D().pack();
	}
	
	public void CalcElementNumber(){
		
		try{
			//A
			this.textWidth = med.getTextWidth().getText().trim();
			double A = Double.parseDouble(this.textWidth);
			//B
			this.textLength = med.getTextLength().getText().trim();
			double B = Double.parseDouble(this.textLength);
			//C
			this.textThickness = med.getTextThickness().getText().trim();
			double C = Double.parseDouble(this.textThickness);
			//D
			this.textThicknessElementNum = med.getTextThicknessElementNum().getText().trim();
			double D = Double.parseDouble(this.textThicknessElementNum);
			//E
			this.textWidthAspectRatio = med.getTextWidthAspectRatio().getText().trim();
			double E = Double.parseDouble(this.textWidthAspectRatio);
			//F
			this.textLengthAspectRatio = med.getTextLengthAspectRatio().getText().trim();
			double F = Double.parseDouble(this.textLengthAspectRatio);
			//G
			int G = 0;
			G = (int)(A / ( C * E ));
			//H
			int H = 0;
			H = (int)(B / ( C * F ));
			//result
			int result = 0;
			result = (int)(D*G*H);

			this.textElementNumber = String.valueOf(result);
			med.getTextElementNumber().setText(this.textElementNumber);
		}catch (Exception e){
			this.textElementNumber = "Error";
			med.getTextElementNumber().setText(this.textElementNumber);
			
			String msg = "ERROR - Calcuation element number";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);			
		}
		
	}
	
	public void CalcElementNumber_2D(){
		
		try{
			//=> (L)/(aspect_ratio_of_length_direction*T)*(element_num_of_thickness_direction)
			
			// L 
			this.textLength_2D = med.getTextLength_2D().getText().trim();
			double L = Double.parseDouble(this.textLength_2D);
			// T 
			this.textThickness_2D = med.getTextThickness_2D().getText().trim();
			double T = Double.parseDouble(this.textThickness_2D);
			// aspect_ratio_of_width_direction
			this.textLengthAspectRatio_2D = med.getTextLengthAspectRatio_2D().getText().trim();
			double aspect_ratio_of_length_direction = Double.parseDouble(this.textLengthAspectRatio_2D);
			// element_num_of_thickness_direction
			this.textThicknessElementNum_2D = med.getTextThicknessElementNum_2D().getText().trim();
			double element_num_of_thickness_direction = Double.parseDouble(this.textThicknessElementNum_2D);
			
			int result = 0;
			result = (int)((L)/(aspect_ratio_of_length_direction*T)*(element_num_of_thickness_direction));
			
			
			this.textWidth_2D = med.getTextWidth_2D().getText().trim();
			/*
			//A
			this.textWidth_2D = med.getTextWidth_2D().getText().trim();
			double A = Double.parseDouble(this.textWidth_2D);
			//B
			this.textLength_2D = med.getTextLength_2D().getText().trim();
			double B = Double.parseDouble(this.textLength_2D);
			//C
			this.textThickness_2D = med.getTextThickness_2D().getText().trim();
			double C = Double.parseDouble(this.textThickness_2D);
			//D
			this.textThicknessElementNum_2D = med.getTextThicknessElementNum_2D().getText().trim();
			double D = Double.parseDouble(this.textThicknessElementNum_2D);
			//E
			// ENS 확인해야 할 부분
			//this.textWidthAspectRatio_2D = med.getTextWidthAspectRatio_2D().getText().trim();
			//double E = Double.parseDouble(this.textWidthAspectRatio_2D);
			double E = 1.0;
			//F
			this.textLengthAspectRatio_2D = med.getTextLengthAspectRatio_2D().getText().trim();
			double F = Double.parseDouble(this.textLengthAspectRatio_2D);
			//G
			int G = 0;
			G = (int)(A / ( C * E ));
			//H
			int H = 0;
			H = (int)(B / ( C * F ));
			//result
			int result = 0;
			result = (int)(D*G*H);
			// */

			this.textElementNumber_2D = String.valueOf(result);
			med.getTextElementNumber_2D().setText(this.textElementNumber_2D);
		}catch (Exception e){
			this.textElementNumber_2D = "Error";
			med.getTextElementNumber_2D().setText(this.textElementNumber_2D);
			
			String msg = "ERROR - Calcuation element number_2D";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);			
		}
	}
	
	public void CreateRoll(){
		try{
			upTableDataList = new ArrayList<UpTableDataContent>();
			downTableDataList = new ArrayList<DownTableDataContent>();
			
			this.spinnerUpperRollNum = med.getSpinnerUpperRollNum().getText().trim();
			this.spinnerLowerRollNum = med.getSpinnerLowerRollNum().getText().trim();
			
			this.textRollPitch = med.getTextRollPitch().getText().trim();
			this.textRollLength = calcRollLength().trim();
			this.textEntryUpperRollGap = med.getTextEntryUpperRollGap().getText().trim();
			this.textEntryLowerRollGap = med.getTextEntryLowerRollGap().getText().trim();
			this.textExitUpperRollGap = med.getTextExitUpperRollGap().getText().trim();
			this.textExitLowerRollGap = med.getTextExitLowerRollGap().getText().trim();
			this.textRollFriction = med.getTextRollFriction().getText().trim();
			this.textRollDiameter = med.getTextRollDiameter().getText().trim();
			//update version2 2016.01.27
			//this.textRollCrown = med.getTextRollCrown().getText().trim();
			this.textUpperRollCrown = med.getTextUpperRollCrown().getText().trim();
			this.textLowerRollCrown = med.getTextLowerRollCrown().getText().trim();
			this.textMillStiffness = med.getTextMillStiffness().getText().trim();
			
			med.getTextRollLength().setText(this.textRollLength);
			
			initUpperTableData();
			initLowerTableData();
			
			// update version3 this function is called by show roll table button
			//updateTableData();
			
			calcSolvingTime();
			/*
			String msg = "SUCCESS - Create Roll Data";
			msg = msg + checkRollData();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.info(msg);
			*/
		}catch(Exception e){
			String msg = "ERROR - Create ROll";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		
	}
	
	public void CreateRoll_2D(){
		try{
			upTableDataList_2D = new ArrayList<UpTableDataContent>();
			downTableDataList_2D = new ArrayList<DownTableDataContent>();
			
			this.spinnerUpperRollNum_2D = med.getSpinnerUpperRollNum_2D().getText().trim();
			this.spinnerLowerRollNum_2D = med.getSpinnerLowerRollNum_2D().getText().trim();
			
			this.textRollPitch_2D = med.getTextRollPitch_2D().getText().trim();
			//this.textRollLength_2D = calcRollLength_2D().trim();
			this.textEntryUpperRollGap_2D = med.getTextEntryUpperRollGap_2D().getText().trim();
			this.textEntryLowerRollGap_2D = med.getTextEntryLowerRollGap_2D().getText().trim();
			this.textExitUpperRollGap_2D = med.getTextExitUpperRollGap_2D().getText().trim();
			this.textExitLowerRollGap_2D = med.getTextExitLowerRollGap_2D().getText().trim();
			this.textRollFriction_2D = med.getTextRollFriction_2D().getText().trim();
			this.textRollDiameter_2D = med.getTextRollDiameter_2D().getText().trim();
			//update version2 2016.01.27
			//this.textRollCrown_2D = med.getTextRollCrown_2D().getText().trim();
			//this.textUpperRollCrown_2D = med.getTextUpperRollCrown_2D().getText().trim();
			//this.textLowerRollCrown_2D = med.getTextLowerRollCrown_2D().getText().trim();
			this.textMillStiffness_2D = med.getTextMillStiffness_2D().getText().trim();
			
			//med.getTextRollLength_2D().setText(this.textRollLength_2D);
			
			initUpperTableData_2D();
			initLowerTableData_2D();
			
			// update version3 this function is called by show roll table button
			//updateTableData();
			
			calcSolvingTime_2D();
			/*
			String msg = "SUCCESS - Create Roll Data";
			msg = msg + checkRollData();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.info(msg);
			*/
		}catch(Exception e){
			//e.printStackTrace();
			String msg = "ERROR - Create ROll_2D";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		
	}
	
	
	public void initUpperTableData(){
		try{
			int UpperRollNum = Integer.parseInt(this.spinnerUpperRollNum);
			for(int i=0;i<UpperRollNum;i++){
				String no = "UpperRoll_"+(i+1);
				UpTableDataContent UTDCObj = new UpTableDataContent();
				UTDCObj.setNo(no);
				UTDCObj.setPitch(this.textRollPitch);
				UTDCObj.setGap(calcUpperRollGap(i,UpperRollNum));
				UTDCObj.setFriction(this.textRollFriction);
				UTDCObj.setDiameter(this.textRollDiameter);
				upTableDataList.add(UTDCObj);
			}
		}catch(Exception e){
			String msg = "ERROR - Generate Upper Rolls";
			msg = msg +"\n" +e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();	
			log.error(msg);
		}
		
	}
	
	public void initUpperTableData_2D(){
		try{
			int UpperRollNum = Integer.parseInt(this.spinnerUpperRollNum_2D);
			for(int i=0;i<UpperRollNum;i++){
				String no = "UpperRoll_"+(i+1);
				UpTableDataContent UTDCObj = new UpTableDataContent();
				UTDCObj.setNo(no);
				UTDCObj.setPitch(this.textRollPitch_2D);
				UTDCObj.setGap(calcUpperRollGap_2D(i,UpperRollNum));
				UTDCObj.setFriction(this.textRollFriction_2D);
				UTDCObj.setDiameter(this.textRollDiameter_2D);
				upTableDataList_2D.add(UTDCObj);
			}
		}catch(Exception e){
			String msg = "ERROR - Generate Upper Rolls";
			msg = msg +"\n" +e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();	
			log.error(msg);
		}
		
	}
	
	public void initLowerTableData(){
		try{
			int LowerRollNum = Integer.parseInt(this.spinnerLowerRollNum);
			for(int i=0;i<LowerRollNum;i++){
				String no = "LowerRoll_"+(i+1);
				DownTableDataContent DTDCObj = new DownTableDataContent();
				DTDCObj.setNo(no);
				DTDCObj.setPitch(this.textRollPitch);
				DTDCObj.setGap(calcLowerRollGap(i,LowerRollNum));
				DTDCObj.setFriction(this.textRollFriction);
				DTDCObj.setDiameter(this.textRollDiameter);
				downTableDataList.add(DTDCObj);
			}
		}catch(Exception e){
			String msg = "ERROR - Generate Lower Rolls";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		
	}
	
	public void initLowerTableData_2D(){
		try{
			int LowerRollNum = Integer.parseInt(this.spinnerLowerRollNum_2D);
			for(int i=0;i<LowerRollNum;i++){
				String no = "LowerRoll_"+(i+1);
				DownTableDataContent DTDCObj = new DownTableDataContent();
				DTDCObj.setNo(no);
				DTDCObj.setPitch(this.textRollPitch_2D);
				DTDCObj.setGap(calcLowerRollGap_2D(i,LowerRollNum));
				DTDCObj.setFriction(this.textRollFriction_2D);
				DTDCObj.setDiameter(this.textRollDiameter_2D);
				downTableDataList_2D.add(DTDCObj);
			}
		}catch(Exception e){
			String msg = "ERROR - Generate Lower Rolls_2D";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		
	}
	
	public void showRollTable(){
		//CreateRoll();
		updateTableData();
	}
	
	public void showRollTable_2D(){
		//CreateRoll();
		updateTableData_2D();
	}
	
	public void updateTableData(){
		try{
			med.getTableViewerUpperRoll().setLabelProvider(new TableViewerLabelProvider_up());
			med.getTableViewerUpperRoll().setContentProvider(new ArrayContentProvider());
			med.getTableViewerUpperRoll().setInput(this.upTableDataList);
			
			med.getTableViewerLowerRoll().setLabelProvider(new TableViewerLabelProvider_down());
			med.getTableViewerLowerRoll().setContentProvider(new ArrayContentProvider());
			med.getTableViewerLowerRoll().setInput(this.downTableDataList);	
		}catch (Exception e){
			String msg = "ERROR - Update Roll Table Data";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
	}
	
	public void updateTableData_2D(){
		try{
			med.getTableViewerUpperRoll_2D().setLabelProvider(new TableViewerLabelProvider_up());
			med.getTableViewerUpperRoll_2D().setContentProvider(new ArrayContentProvider());
			med.getTableViewerUpperRoll_2D().setInput(this.upTableDataList_2D);
			
			med.getTableViewerLowerRoll_2D().setLabelProvider(new TableViewerLabelProvider_down());
			med.getTableViewerLowerRoll_2D().setContentProvider(new ArrayContentProvider());
			med.getTableViewerLowerRoll_2D().setInput(this.downTableDataList_2D);	
		}catch (Exception e){
			//e.printStackTrace();
			String msg = "ERROR - Update Roll Table Data_2D";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
	}
	
	public String calcUpperRollGap(int index,int upperRollNum){
		String result = null;
		try{
			double entry = Double.parseDouble(this.textEntryUpperRollGap);
			double exit = Double.parseDouble(this.textExitUpperRollGap);
			double rollNum = (upperRollNum == 1)?2:upperRollNum;
			double distance = Double.parseDouble(String.format("%.10f", (exit-entry)/(rollNum-1)));
			double gap = entry + (distance*index);
			if(index == 0){
				result = this.textEntryUpperRollGap;
			}else if(index == Integer.parseInt(this.spinnerUpperRollNum)-1){
				result = this.textExitUpperRollGap;
			}else {
				result = String.valueOf(gap);
			}
		}catch (Exception e){
			result = "0";
			
			String msg = "ERROR - Calcuation Upper Roll Gap";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		return result;
	}
	
	public String calcUpperRollGap_2D(int index,int upperRollNum){
		String result = null;
		try{
			double entry = Double.parseDouble(this.textEntryUpperRollGap_2D);
			double exit = Double.parseDouble(this.textExitUpperRollGap_2D);
			double rollNum = (upperRollNum == 1)?2:upperRollNum;
			double distance = Double.parseDouble(String.format("%.10f", (exit-entry)/(rollNum-1)));
			double gap = entry + (distance*index);
			if(index == 0){
				result = this.textEntryUpperRollGap_2D;
			}else if(index == Integer.parseInt(this.spinnerUpperRollNum_2D)-1){
				result = this.textExitUpperRollGap_2D;
			}else {
				result = String.valueOf(gap);
			}
		}catch (Exception e){
			result = "0";
			
			String msg = "ERROR - Calcuation Upper Roll Gap_2D";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		return result;
	}
	
	public String calcLowerRollGap(int index, int lowerRollNum){
		String result = null;
		try{
			double entry = Double.parseDouble(this.textEntryLowerRollGap);
			double exit = Double.parseDouble(this.textExitLowerRollGap);
			double rollNum = (lowerRollNum == 1)?2:lowerRollNum; 
			double distance = Double.parseDouble(String.format("%.10f", (exit-entry)/(rollNum-1)));
			double gap = entry + (distance*index);
			
			if(index == 0){
				result = this.textEntryLowerRollGap;
			}else if(index == Integer.parseInt(this.spinnerLowerRollNum)-1){
				result = this.textExitLowerRollGap;
			}else {
				result = String.valueOf(gap);
			}
			
		}catch (Exception e){
			result = "0";
			
			String msg = "ERROR - Calcuation Lower Roll Gap";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		return result;
	}
	
	public String calcLowerRollGap_2D(int index, int lowerRollNum){
		String result = null;
		try{
			double entry = Double.parseDouble(this.textEntryLowerRollGap_2D);
			double exit = Double.parseDouble(this.textExitLowerRollGap_2D);
			double rollNum = (lowerRollNum == 1)?2:lowerRollNum; 
			double distance = Double.parseDouble(String.format("%.10f", (exit-entry)/(rollNum-1)));
			double gap = entry + (distance*index);
			
			if(index == 0){
				result = this.textEntryLowerRollGap_2D;
			}else if(index == Integer.parseInt(this.spinnerLowerRollNum_2D)-1){
				result = this.textExitLowerRollGap_2D;
			}else {
				result = String.valueOf(gap);
			}
			
		}catch (Exception e){
			result = "0";
			
			String msg = "ERROR - Calcuation Lower Roll Gap_2D";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		return result;
	}
	
	public String calcRollLength(){
		this.textWidth = med.getTextWidth().getText().trim();
		try{
			double width = Double.parseDouble(this.textWidth);
			double length = width +2000;
			return String.valueOf(length); 
		}catch (Exception e){
			
			String msg = "ERROR - Calcuation Roll Length";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			
			return "0";
		}
	}
	
	public String calcRollLength_2D(){
		this.textWidth_2D = med.getTextWidth_2D().getText().trim();
		try{
			double width = Double.parseDouble(this.textWidth_2D);
			double length = width +2000;
			return String.valueOf(length); 
		}catch (Exception e){
			
			String msg = "ERROR - Calcuation Roll Length_2D";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			
			return "0";
		}
	}
	
	public void SaveRoll(){
		try{
			updateTableData();
			
			String msg = "SUCCESS - Save Roll Data";
			msg = msg + checkRollData();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.info(msg);
		}catch(Exception e){
			String msg = "ERROR - Save ROll";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		
	}
	
	public void SaveRoll_2D(){
		try{
			updateTableData_2D();
			
			String msg = "SUCCESS - Save Roll Data_2D";
			msg = msg + checkRollData_2D();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.info(msg);
		}catch(Exception e){
			//e.printStackTrace();
			String msg = "ERROR - Save Roll_2D";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		
	}
	
	public void checkData(){
		System.out.println("###################################");
		System.out.println("# Leveller Roll Data              #");
		System.out.println("###################################");
		
		System.out.println("Up Table Number \t: "+this.spinnerUpperRollNum);
		System.out.println("Down Table Number \t: "+this.spinnerLowerRollNum);
		
		System.out.println("\n\n"+"[Up Table]");

		System.out.println(String.format("%10s", "No")+
				String.format("%10s", "Gap")+
				String.format("%10s", "Friction")+
				String.format("%10s", "Diameter")+
				String.format("%10s", "Pitch")
				);
		try{
			for(UpTableDataContent obj : this.upTableDataList){
				obj.printAllData();
			}
		}catch (Exception e){
			System.out.println("Data is empty.");
		}
		System.out.println("\n\n"+"[Down Table]");
		System.out.println(String.format("%10s", "No")+
				String.format("%10s", "Gap")+
				String.format("%10s", "Friction")+
				String.format("%10s", "Diameter")+
				String.format("%10s", "Pitch")
				);
		try{
			for(DownTableDataContent obj : this.downTableDataList){
				obj.printAllData();
			}
		}catch (Exception e){
			System.out.println("Data is empty.");
		}
	}
	
	public String checkRollData(){
		ArrayList<String> msgList = new ArrayList<String>();
		String msg ="\n\n"; 
		
		msgList.add("################################");
		msgList.add("\tLeveller Roll Data");        
		msgList.add("################################");
		
		msgList.add("Upper Roll Number \t: "+this.spinnerUpperRollNum);
		msgList.add("Lower Roll Number \t: "+this.spinnerLowerRollNum);
		
		msgList.add("\n\n"+"[Up Table]\n");

		msgList.add(String.format("%-20s", "No")+
				String.format("%-20s", "Gap")+
				String.format("%-20s", "Friction")+
				String.format("%-20s", "Diameter")+
				String.format("%-20s", "Pitch")+"\n"
				);
		try{
			for(UpTableDataContent obj : this.upTableDataList){
				msgList.add(obj.getAllData());
			}
		}catch (Exception e){
			msgList.add("Roll Data is empty.");
		}
		msgList.add("\n\n"+"[Down Table]\n");
		msgList.add(String.format("%-20s", "No")+
				String.format("%-20s", "Gap")+
				String.format("%-20s", "Friction")+
				String.format("%-20s", "Diameter")+
				String.format("%-20s", "Pitch")+"\n"
				);
		try{
			for(DownTableDataContent obj : this.downTableDataList){
				msgList.add(obj.getAllData());
			}
		}catch (Exception e){
			msgList.add("Roll Data is empty.");
		}
		
		for(String data: msgList){
			msg = msg + data +"\n";
		}
		
		return msg;
	}
	
	public String checkRollData_2D(){
		ArrayList<String> msgList = new ArrayList<String>();
		String msg ="\n\n"; 
		
		msgList.add("################################");
		msgList.add("\tLeveller Roll Data_2D");        
		msgList.add("################################");
		
		msgList.add("Upper Roll Number \t: "+this.spinnerUpperRollNum_2D);
		msgList.add("Lower Roll Number \t: "+this.spinnerLowerRollNum_2D);
		
		msgList.add("\n\n"+"[Up Table]\n");

		msgList.add(String.format("%-20s", "No")+
				String.format("%-20s", "Gap")+
				String.format("%-20s", "Friction")+
				String.format("%-20s", "Diameter")+
				String.format("%-20s", "Pitch")+"\n"
				);
		try{
			for(UpTableDataContent obj : this.upTableDataList_2D){
				msgList.add(obj.getAllData());
			}
		}catch (Exception e){
			msgList.add("Roll Data is empty.");
		}
		msgList.add("\n\n"+"[Down Table]\n");
		msgList.add(String.format("%-20s", "No")+
				String.format("%-20s", "Gap")+
				String.format("%-20s", "Friction")+
				String.format("%-20s", "Diameter")+
				String.format("%-20s", "Pitch")+"\n"
				);
		try{
			for(DownTableDataContent obj : this.downTableDataList_2D){
				msgList.add(obj.getAllData());
			}
		}catch (Exception e){
			msgList.add("Roll Data is empty.");
		}
		
		for(String data: msgList){
			msg = msg + data +"\n";
		}
		
		return msg;
	}
	
	public void calcSolvingTime(){
		try{
			this.textLength = med.getTextLength().getText().trim();
			double L = Double.parseDouble(this.textLength);
			//this.fdist
			double fdists = Double.parseDouble(this.fdist);
			this.textPlateVelocity = med.getTextPlateVelocity().getText().trim();
			double plate_v = Double.parseDouble(this.textPlateVelocity);
			this.textRollPitch = med.getTextRollPitch().getText().trim();
			double roll_pitch = Double.parseDouble(this.textRollPitch);
			this.spinnerLowerRollNum = med.getSpinnerLowerRollNum().getText().trim();
			double LowerRollNum = Double.parseDouble(this.spinnerLowerRollNum);
			
			double result = (L + fdists + roll_pitch*(LowerRollNum-1))*1.1/plate_v;
			this.textSolvingTime = String.valueOf(result);
			med.getTextSolvingTime().setText(this.textSolvingTime);	
		}catch(Exception e){
			this.textSolvingTime = "ERROR";
			med.getTextSolvingTime().setText(this.textSolvingTime);
			
			String msg = "ERROR - Calcuation Solving time";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
	}
	
	public void calcSolvingTime_2D(){
		try{
			this.textLength_2D = med.getTextLength_2D().getText().trim();
			double L = Double.parseDouble(this.textLength_2D);
			//this.fdist
			double fdists = Double.parseDouble(this.fdist_2D);
			this.textPlateVelocity_2D = med.getTextPlateVelocity_2D().getText().trim();
			double plate_v = Double.parseDouble(this.textPlateVelocity_2D);
			this.textRollPitch_2D = med.getTextRollPitch_2D().getText().trim();
			double roll_pitch = Double.parseDouble(this.textRollPitch_2D);
			this.spinnerLowerRollNum_2D = med.getSpinnerLowerRollNum_2D().getText().trim();
			double LowerRollNum = Double.parseDouble(this.spinnerLowerRollNum_2D);
			
			double result = (L + fdists + roll_pitch*(LowerRollNum-1))*1.1/plate_v;
			this.textSolvingTime_2D = String.valueOf(result);
			med.getTextSolvingTime_2D().setText(this.textSolvingTime_2D);	
		}catch(Exception e){
			this.textSolvingTime_2D = "ERROR";
			med.getTextSolvingTime_2D().setText(this.textSolvingTime_2D);
			
			String msg = "ERROR - Calcuation Solving time_2D";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
	}
	
	public void Explorer_YoungsModulus(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerYoungsModulus().getShell(),SWT.OPEN);
		dlg.setText("Select Young's Modulus File");
				
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			this.textYoungsModulus = path;
			med.getTextYoungsModulus().setText(path);
		}
	}
	
	public void Explorer_YoungsModulus_2D(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerYoungsModulus().getShell(),SWT.OPEN);
		dlg.setText("Select Young's Modulus File");
				
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			this.textYoungsModulus_2D = path;
			med.getTextYoungsModulus_2D().setText(path);
		}
	}
	
	public void Explorer_FlowStress(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerFlowStress().getShell(),SWT.OPEN);
		dlg.setText("Select Flow Stress File");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			this.textFlowStress = path;
			med.getTextFlowStress().setText(path);
		}
	}
	
	public void Explorer_FlowStress_2D(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerFlowStress().getShell(),SWT.OPEN);
		dlg.setText("Select Flow Stress File");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			this.textFlowStress_2D = path;
			med.getTextFlowStress_2D().setText(path);
		}
	}
	
	public void Explorer_ThermalExpansionCoefficient(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerThermalExpansionCoefficient().getShell(),SWT.OPEN);
		dlg.setText("Select Thermal Expansion Codfficient File");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			this.textThermalExpansionCoefficient = path;
			med.getTextThermalExpansionCoefficient().setText(path);
		}
	}
	
	public void Explorer_ThermalExpansionCoefficient_2D(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerThermalExpansionCoefficient().getShell(),SWT.OPEN);
		dlg.setText("Select Thermal Expansion Codfficient File");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			this.textThermalExpansionCoefficient_2D = path;
			med.getTextThermalExpansionCoefficient_2D().setText(path);
		}
	}

	public void Explorer_PoissonsRatio(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerPoissonsRatio().getShell(),SWT.OPEN);
		dlg.setText("Select Poisson Ratio File");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			this.textPoissonsRatio = path;
			med.getTextPoissonsRatio().setText(path);
		}
	}
	
	public void Explorer_PoissonsRatio_2D(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerPoissonsRatio().getShell(),SWT.OPEN);
		dlg.setText("Select Poisson Ratio File");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			this.textPoissonsRatio_2D = path;
			med.getTextPoissonsRatio_2D().setText(path);
		}
	}
	
	
	//-------------------------------------------------------------------------
	// File menu
	//
	public void NewLeveller(String ModelName, String Workspace){
		this.textModelName = ModelName;
		med.getTextModelName().setText(ModelName);
		
		String topFolder = myUtil.setPath(Workspace,this.textModelName);
		String procFolder = myUtil.setPath(topFolder, "proc");
		String resultFolder = myUtil.setPath(topFolder, "result");
		this.workspace = topFolder;		
		med.getlblworkspacePath().setText("Workspace : "+this.workspace);
		
		if(!myUtil.makeDir(topFolder)) log.warn("[Model Name:"+ModelName +"] topFolder did not make.");
		if(!myUtil.makeDir(procFolder)) log.warn("[Model Name:"+ModelName +"] prodFolder did not make.");
		if(!myUtil.makeDir(resultFolder)) log.warn("[Model Name:"+ModelName +"] resultFolder did not make.");
		
		this.AllComponentEnable();
		this.clearAllData();
		this.clearAllData_2D();
		
		init_AllComponentValue();
	}
	
	public void clearAllData(){
		this.db.clear();
		this.InitValueMap.clear();
		
		
		//--------------------------------------------
		this.comboType = null; 
		med.getComboType().select(-1);
		Image img = ImageDescriptor.createFromFile(View.class,ImagePath.Type0).createImage();
		med.getLblPhoto().setImage(img);
		med.getLblPhoto().pack();
		this.textWidth = null;
		med.getTextWidth().setText("");
		this.textLength = null;
		med.getTextLength().setText("");
		this.textThickness = null;
		med.getTextThickness().setText("");
		
		this.type2_textLeftEdgeWavePitch = null;
		med.getType2_textLeftEdgeWavePitch().setText("");
		this.type2_textRightEdgeWavePitch = null;
		med.getType2_textRightEdgeWavePitch().setText("");
		this.type2_textLeftEdgeWaveHeight = null;
		med.getType2_textLeftEdgeWaveHeight().setText("");
		this.type2_textRightEdgeWaveHeight = null;
		med.getType2_textRightEdgeWaveHeight().setText("");
		this.type2_textLeftEdgeWavePhase = null;
		med.getType2_textLeftEdgeWavePhase().setText("");
		this.type2_textRightEdgeWavePhase = null;
		med.getType2_textRightEdgeWavePhase().setText("");
		
		this.type3_textWavePitch = null;
		med.getType3_textWavePitch().setText("");
		this.type3_textWaveHeight = null;
		med.getType3_textWaveHeight().setText("");
		
		this.type4_textGutterHeight = null;
		med.getType4_textGutterHeight().setText("");
		
		this.type5_textGutterHeight = null;
		med.getType5_textGutterHeight().setText("");
		this.type5_textGutterLength = null;
		med.getType5_textGutterLength().setText("");
		
		this.type6_textHeadGutterHeight = null;
		med.getType6_textHeadGutterHeight().setText("");
		this.type6_textHeadGutterLength = null;
		med.getType6_textHeadGutterLength().setText("");
		this.type6_textTailGutterHeight = null;
		med.getType6_textTailGutterHeight().setText("");
		this.type6_textTailGutterLength = null;
		med.getType6_textTailGutterLength().setText("");
		
		this.type7_textHeadGutterHeight = null;
		med.getType7_textHeadGutterHeight().setText("");
		this.type7_textGutterLength = null;
		med.getType7_textGutterLength().setText("");
		this.type7_textGutterLengthLength = null;
		med.getType7_textGutterLengthLength().setText("");
		this.type7_textGutterWidthLength = null;
		med.getType7_textGutterWidthLength().setText("");
		
		//--------------------------------------------		
		this.textThicknessElementNum = null;
		med.getTextThicknessElementNum().setText("");
		this.textWidthAspectRatio = null;
		med.getTextWidthAspectRatio().setText("");
		this.textLengthAspectRatio = null;
		med.getTextLengthAspectRatio().setText("");
		this.textElementNumber = null;
		med.getTextElementNumber().setText("");
		//--------------------------------------------
		this.textPlateVelocity = null;
		med.getTextPlateVelocity().setText("");
		this.textTemperatureStart = null;
		med.getTextTemperatureStart().setText("");
		this.textTemperatureEnd = null;
		med.getTextTemperatureEnd().setText("");
		//update version2 2017.01.27
		this.textPassLineOffset = null;
		med.getTextPassLineOffset().setText("");
		//--------------------------------------------		
		this.spinnerUpperRollNum = null;
		med.getSpinnerUpperRollNum().setSelection(0);
		this.spinnerLowerRollNum = null;
		med.getSpinnerLowerRollNum().setSelection(0);
		this.textRollPitch = null;
		med.getTextRollPitch().setText("");
		this.textRollLength = null;
		med.getTextRollLength().setText("");
		this.textEntryUpperRollGap = null;
		med.getTextEntryUpperRollGap().setText("");
		this.textEntryLowerRollGap = null;
		med.getTextEntryLowerRollGap().setText("");
		this.textExitUpperRollGap = null;
		med.getTextExitUpperRollGap().setText("");
		this.textExitLowerRollGap = null;
		med.getTextExitLowerRollGap().setText("");
		this.textRollFriction = null;
		med.getTextRollFriction().setText("");
		this.textRollDiameter = null;
		med.getTextRollDiameter().setText("");
		//update version2 2017.01.27
		//this.textRollCrown = null;
		//med.getTextRollCrown().setText("");
		this.textUpperRollCrown = null;
		med.getTextUpperRollCrown().setText("");
		this.textLowerRollCrown = null;
		med.getTextLowerRollCrown().setText("");
		this.textMillStiffness = null;
		med.getTextMillStiffness().setText("");
		
		//update version3 
		this.textUpperEntryRollGapMovement = null;
		med.getTextUpperEntryRollGapMovement().setText("");
		this.textUpperExitRollGapMovement = null;
		med.getTextUpperExitRollGapMovement().setText("");
		this.textUpperRollGapStayingTime = null;
		med.getTextUpperRollGapStayingTime().setText("");
		this.textUpperRollGapMovingTime = null;
		med.getTextUpperRollGapMovingTime().setText("");
		this.textLowerEntryRollGapMovement = null;
		med.getTextLowerEntryRollGapMovement().setText("");
		this.textLowerExitRollGapMovement = null;
		med.getTextLowerExitRollGapMovement().setText("");
		this.textLowerRollGapStayingTime = null;
		med.getTextLowerRollGapStayingTime().setText("");
		this.textLowerRollGapMovingTime = null;
		med.getTextLowerRollGapMovingTime().setText("");
		
		this.textFrontHDRollDia = null;
		med.getTextFrontHDRollDia().setText("");
		this.textFrontHDRollPitch = null;
		med.getTextFrontHDRollPitch().setText("");
		this.textFrontHDRollVericalPos = null;
		med.getTextFrontHDRollVericalPos().setText("");
		this.textRearHDRollDia = null;
		med.getTextRearHDRollDia().setText("");
		this.textRearHDRollPitch = null;
		med.getTextRearHDRollPitch().setText("");
		this.textRearHDRollVerticalPos = null;
		med.getTextRearHDRollVerticalPos().setText("");
		
		this.HDRollType = "Upper";
		med.getBtnNone().setSelection(false);
		med.getBtnLower().setSelection(false);
		med.getBtnUpper().setSelection(true);
		
		
		
		try{
			for(UpTableDataContent obj : this.upTableDataList){
				obj = null;
			}
			if(this.upTableDataList != null)	this.upTableDataList.clear();
			// version3 update
			//this.updateTableData();
		}catch (Exception e){
			//System.out.println("Data is empty.clearAllData");
		}
		
		try{
			for(DownTableDataContent obj : this.downTableDataList){
				obj = null;
			}
			if(this.downTableDataList != null) this.downTableDataList.clear();
			// version3 update
			//this.updateTableData();
		}catch (Exception e){
			//System.out.println("Data is empty.clearAllData");
		}
			
		
		//--------------------------------------------
		this.textYoungsModulus = null;
		med.getTextYoungsModulus().setText("");
		this.textFlowStress = null;
		med.getTextFlowStress().setText("");
		// update version2 2017.01.27
		this.textYieldStrength = null;
		med.getTextYieldStrength().setText("");
		this.textTensileStrength = null;
		med.getTextTensileStrength().setText("");
		this.textElongation = null;
		med.getTextElongation().setText("");
		
		this.textThermalExpansionCoefficient = null;
		med.getTextThermalExpansionCoefficient().setText("");
		this.textMassDensity = null;
		med.getTextMassDensity().setText("");
		this.textPoissonsRatio = null;
		med.getTextPoissonsRatio().setText("");
		
		//--------------------------------------------
		this.textSolvingTime = null;
		med.getTextSolvingTime().setText("");
		this.textIncrementTime = null;
		med.getTextIncrementTime().setText("");
		this.btnParallelDDMUse = false;
		med.getBtnParallelDDMUse().setSelection(false);
		this.spinnerDomain = null;
		med.getSpinnerDomain().setSelection(3);
		this.spinnerThread = null;
		med.getSpinnerThread().setSelection(3);
		
		//160331 update
		this.ReadInitValue();
	}
	
	public void clearAllData_2D(){
		this.db_2D.clear();
		this.InitValueMap.clear();
		
		
		//--------------------------------------------
		this.comboType_2D = null; 
		med.getComboType_2D().select(-1);
		Image img = ImageDescriptor.createFromFile(View.class,ImagePath.Type0).createImage();
		med.getLblPhoto_2D().setImage(img);
		med.getLblPhoto_2D().pack();
		this.textWidth_2D = null;
		med.getTextWidth_2D().setText("");
		this.textLength_2D = null;
		med.getTextLength_2D().setText("");
		this.textThickness_2D = null;
		med.getTextThickness_2D().setText("");
		
		/*
		this.type2_textLeftEdgeWavePitch_2D = null;
		med.getType2_textLeftEdgeWavePitch_2D().setText("");
		this.type2_textRightEdgeWavePitch_2D = null;
		med.getType2_textRightEdgeWavePitch_2D().setText("");
		this.type2_textLeftEdgeWaveHeight_2D = null;
		med.getType2_textLeftEdgeWaveHeight_2D().setText("");
		this.type2_textRightEdgeWaveHeight_2D = null;
		med.getType2_textRightEdgeWaveHeight_2D().setText("");
		this.type2_textLeftEdgeWavePhase_2D = null;
		med.getType2_textLeftEdgeWavePhase_2D().setText("");
		this.type2_textRightEdgeWavePhase_2D = null;
		med.getType2_textRightEdgeWavePhase_2D().setText("");
		// */
		this.type2_textWavePitch_2D = null;
		med.getType2_textWavePitch_2D().setText("");
		this.type2_textWaveHeight_2D = null;
		med.getType2_textWaveHeight_2D().setText("");
		this.type2_textWavePhase_2D = null;
		med.getType2_textWavePhase_2D().setText("");
		
		/*
		this.type3_textWavePitch_2D = null;
		med.getType3_textWavePitch_2D().setText("");
		this.type3_textWaveHeight_2D = null;
		med.getType3_textWaveHeight_2D().setText("");
		// */
		this.type3_textFrontCurlHeight_2D = null;
		med.getType3_textRearCurlHeight_2D().setText("");
		this.type3_textFrontCurlLength_2D = null;
		med.getType3_textFrontCurlLength_2D().setText("");
		this.type3_textRearCurlHeight_2D = null;
		med.getType3_textRearCurlHeight_2D().setText("");
		this.type3_textRearCurlLength_2D = null;
		med.getType3_textRearCurlLength_2D().setText("");
		/*
		this.type4_textGutterHeight_2D = null;
		med.getType4_textGutterHeight_2D().setText("");
		
		this.type5_textGutterHeight_2D = null;
		med.getType5_textGutterHeight_2D().setText("");
		this.type5_textGutterLength_2D = null;
		med.getType5_textGutterLength_2D().setText("");
		
		this.type6_textHeadGutterHeight_2D = null;
		med.getType6_textHeadGutterHeight_2D().setText("");
		this.type6_textHeadGutterLength_2D = null;
		med.getType6_textHeadGutterLength_2D().setText("");
		this.type6_textTailGutterHeight_2D = null;
		med.getType6_textTailGutterHeight_2D().setText("");
		this.type6_textTailGutterLength_2D = null;
		med.getType6_textTailGutterLength_2D().setText("");
		
		this.type7_textHeadGutterHeight_2D = null;
		med.getType7_textHeadGutterHeight_2D().setText("");
		this.type7_textGutterLength_2D = null;
		med.getType7_textGutterLength_2D().setText("");
		this.type7_textGutterLengthLength_2D = null;
		med.getType7_textGutterLengthLength_2D().setText("");
		this.type7_textGutterWidthLength_2D = null;
		med.getType7_textGutterWidthLength_2D().setText("");
		// */
		
		//--------------------------------------------		
		this.textThicknessElementNum_2D = null;
		med.getTextThicknessElementNum_2D().setText("");
		//this.textWidthAspectRatio_2D = null;
		//med.getTextWidthAspectRatio_2D().setText("");
		this.textLengthAspectRatio_2D = null;
		med.getTextLengthAspectRatio_2D().setText("");
		this.textElementNumber_2D = null;
		med.getTextElementNumber_2D().setText("");
		//--------------------------------------------
		this.textPlateVelocity_2D = null;
		med.getTextPlateVelocity_2D().setText("");
		this.textTemperatureStart_2D = null;
		med.getTextTemperatureStart_2D().setText("");
		this.textTemperatureEnd_2D = null;
		med.getTextTemperatureEnd_2D().setText("");
		//update version2 2017.01.27
		this.textPassLineOffset_2D = null;
		med.getTextPassLineOffset_2D().setText("");
		//--------------------------------------------		
		this.spinnerUpperRollNum_2D = null;
		med.getSpinnerUpperRollNum_2D().setSelection(0);
		this.spinnerLowerRollNum_2D = null;
		med.getSpinnerLowerRollNum_2D().setSelection(0);
		this.textRollPitch_2D = null;
		med.getTextRollPitch_2D().setText("");
		//this.textRollLength_2D = null;
		//med.getTextRollLength_2D().setText("");
		this.textEntryUpperRollGap_2D = null;
		med.getTextEntryUpperRollGap_2D().setText("");
		this.textEntryLowerRollGap_2D = null;
		med.getTextEntryLowerRollGap_2D().setText("");
		this.textExitUpperRollGap_2D = null;
		med.getTextExitUpperRollGap_2D().setText("");
		this.textExitLowerRollGap_2D = null;
		med.getTextExitLowerRollGap_2D().setText("");
		this.textRollFriction_2D = null;
		med.getTextRollFriction_2D().setText("");
		this.textRollDiameter_2D = null;
		med.getTextRollDiameter_2D().setText("");
		//update version2 2017.01.27
		//this.textRollCrown_2D = null;
		//med.getTextRollCrown_2D().setText("");
		/*
		this.textUpperRollCrown_2D = null;
		med.getTextUpperRollCrown_2D().setText("");
		this.textLowerRollCrown_2D = null;
		med.getTextLowerRollCrown_2D().setText("");
		//*/
		this.textMillStiffness_2D = null;
		med.getTextMillStiffness_2D().setText("");
		
		//update version3 
		this.textUpperEntryRollGapMovement_2D = null;
		med.getTextUpperEntryRollGapMovement_2D().setText("");
		this.textUpperExitRollGapMovement_2D = null;
		med.getTextUpperExitRollGapMovement_2D().setText("");
		this.textUpperRollGapStayingTime_2D = null;
		med.getTextUpperRollGapStayingTime_2D().setText("");
		this.textUpperRollGapMovingTime_2D = null;
		med.getTextUpperRollGapMovingTime_2D().setText("");
		this.textLowerEntryRollGapMovement_2D = null;
		med.getTextLowerEntryRollGapMovement_2D().setText("");
		this.textLowerExitRollGapMovement_2D = null;
		med.getTextLowerExitRollGapMovement_2D().setText("");
		this.textLowerRollGapStayingTime_2D = null;
		med.getTextLowerRollGapStayingTime_2D().setText("");
		this.textLowerRollGapMovingTime_2D = null;
		med.getTextLowerRollGapMovingTime_2D().setText("");
		
		this.textFrontHDRollDia_2D = null;
		med.getTextFrontHDRollDia_2D().setText("");
		this.textFrontHDRollPitch_2D = null;
		med.getTextFrontHDRollPitch_2D().setText("");
		this.textFrontHDRollVericalPos_2D = null;
		med.getTextFrontHDRollVericalPos_2D().setText("");
		this.textRearHDRollDia_2D = null;
		med.getTextRearHDRollDia_2D().setText("");
		this.textRearHDRollPitch_2D = null;
		med.getTextRearHDRollPitch_2D().setText("");
		this.textRearHDRollVerticalPos_2D = null;
		med.getTextRearHDRollVerticalPos_2D().setText("");
		
		this.HDRollType_2D = "Upper";
		med.getBtnNone_2D().setSelection(false);
		med.getBtnLower_2D().setSelection(false);
		med.getBtnUpper_2D().setSelection(true);
		
		
		
		try{
			for(UpTableDataContent obj : this.upTableDataList_2D){
				obj = null;
			}
			if(this.upTableDataList_2D != null)	this.upTableDataList_2D.clear();
			// version3 update
			//this.updateTableData();
		}catch (Exception e){
			//System.out.println("Data is empty.clearAllData");
		}
		
		try{
			for(DownTableDataContent obj : this.downTableDataList_2D){
				obj = null;
			}
			if(this.downTableDataList_2D != null) this.downTableDataList_2D.clear();
			// version3 update
			//this.updateTableData();
		}catch (Exception e){
			//System.out.println("Data is empty.clearAllData");
		}
			
		
		//--------------------------------------------
		this.textYoungsModulus_2D = null;
		med.getTextYoungsModulus_2D().setText("");
		this.textFlowStress_2D = null;
		med.getTextFlowStress_2D().setText("");
		// update version2 2017.01.27
		this.textYieldStrength_2D = null;
		med.getTextYieldStrength_2D().setText("");
		this.textTensileStrength_2D = null;
		med.getTextTensileStrength_2D().setText("");
		this.textElongation_2D = null;
		med.getTextElongation_2D().setText("");
		
		this.textThermalExpansionCoefficient_2D = null;
		med.getTextThermalExpansionCoefficient_2D().setText("");
		this.textMassDensity_2D = null;
		med.getTextMassDensity_2D().setText("");
		this.textPoissonsRatio_2D = null;
		med.getTextPoissonsRatio_2D().setText("");
		
		//--------------------------------------------
		this.textSolvingTime_2D = null;
		med.getTextSolvingTime_2D().setText("");
		this.textIncrementTime_2D = null;
		med.getTextIncrementTime_2D().setText("");
		this.btnParallelDDMUse_2D = false;
		med.getBtnParallelDDMUse_2D().setSelection(false);
		this.spinnerDomain_2D = null;
		med.getSpinnerDomain_2D().setSelection(3);
		this.spinnerThread_2D = null;
		med.getSpinnerThread_2D().setSelection(3);
		
		//160331 update
		this.ReadInitValue();
	}
	
	public void OpenLeveller(String dbFilePath){
		this.clearAllData();
		this.clearAllData_2D();
		
		this.ReadInitValue();
		
		String path = myUtil.getParrentPath(dbFilePath);
		String fileName = myUtil.getFileName(dbFilePath);
		this.textModelName = ParserDefault.splitLineData(fileName,"\\.").get(0);
		
		if(myUtil.getFileName(path).equals(this.textModelName)){
			String procFolder = myUtil.setPath(path, "proc");
			String resultFolder = myUtil.setPath(path, "result");
			
			if(!myUtil.checkPath(procFolder)){
				myUtil.makeDir(procFolder);
			}
			
			if(!myUtil.checkPath(resultFolder)){
				myUtil.makeDir(resultFolder);
			}
			
			this.workspace = path;
			med.getlblworkspacePath().setText("Workspace : "+this.workspace);
			
			
			Reader reader = new Reader(dbFilePath);
			reader.running();
			ArrayList<String> fileDataList = reader.getFileDataList();
			
			ArrayList<String> parsingDataList;
			for(String line : fileDataList){
				parsingDataList = ParserDefault.splitLineData(line, "=");
				if(parsingDataList.get(0).equals("LevellerType")){
					//this.db_2D.add("LevellerType="+this.LevellerType);
					if(parsingDataList.get(1).equals("2D")){
						setAllDataUI_2D(dbFilePath);
						break;
					}else{
						setAllDataUI(dbFilePath);
						break;
					}
				}
			}
			//setAllDataUI(dbFilePath);
			AllComponentEnable();
			
		}else {
			String topFolder =  myUtil.setPath(path, this.textModelName);
			String procFolder = myUtil.setPath(topFolder, "proc");
			String resultFolder = myUtil.setPath(topFolder, "result");
			String newDBFilePath = myUtil.setPath(topFolder, this.textModelName+".lvdb");
			this.workspace = topFolder;
			med.getlblworkspacePath().setText("Workspace : "+this.workspace);
			myUtil.makeDir(topFolder);
			myUtil.makeDir(procFolder);
			myUtil.makeDir(resultFolder);
			myUtil.fileCopy(dbFilePath, newDBFilePath);
			
			Reader reader = new Reader(newDBFilePath);
			reader.running();
			ArrayList<String> fileDataList = reader.getFileDataList();
			
			ArrayList<String> parsingDataList;
			for(String line : fileDataList){
				parsingDataList = ParserDefault.splitLineData(line, "=");
				if(parsingDataList.get(0).equals("LevellerType")){
					//this.db_2D.add("LevellerType="+this.LevellerType);
					if(parsingDataList.get(1).equals("2D")){
						setAllDataUI_2D(dbFilePath);
						break;
					}else{
						setAllDataUI(dbFilePath);
						break;
					}
				}
			}
			//setAllDataUI(newDBFilePath);
			AllComponentEnable();
		}
		
	}
	
	public void setAllDataUI(String newDBFilePath){
		upTableDataList = new ArrayList<UpTableDataContent>();
		downTableDataList = new ArrayList<DownTableDataContent>();
		
		Reader reader = new Reader(newDBFilePath);
		reader.running();
		ArrayList<String> fileDataList = reader.getFileDataList();
		
		ArrayList<String> parsingDataList;
		for(String line : fileDataList){
			parsingDataList = ParserDefault.splitLineData(line, "=");
			
			med.getTabFolder().setSelection(1);
			this.LevellerType = "3D";
			
			if(parsingDataList.get(0).equals(TextLabel.lblModelName)){
				if(parsingDataList.size() == 2){
					this.textModelName = parsingDataList.get(1);
					med.getTextModelName().setText(this.textModelName);
				}else {
					this.textModelName = "No value";
					med.getTextModelName().setText(this.textModelName);	
				}
				
			}
			//----------------------------------------------------
			else if(parsingDataList.get(0).equals(TextLabel.lblType)){
				if(parsingDataList.size() == 2){
					this.comboType = parsingDataList.get(1);
					Image img = ImageDescriptor.createFromFile(View.class,ImagePath.Type0).createImage();
					med.getCompositeShapeParameterChild_1().setVisible(false);
					med.getCompositeShapeParameterChild_2().setVisible(false);
					med.getCompositeShapeParameterChild_3().setVisible(false);
					med.getCompositeShapeParameterChild_4().setVisible(false);
					med.getCompositeShapeParameterChild_5().setVisible(false);
					med.getCompositeShapeParameterChild_6().setVisible(false);
					med.getCompositeShapeParameterChild_7().setVisible(false);
					if(comboType.equals(ComboLabel.TYPE1)){
						med.getComboType().select(0);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type1).createImage();
						med.getCompositeShapeParameterChild_1().setVisible(true);	
					}else if(comboType.equals(ComboLabel.TYPE2)){
						med.getComboType().select(1);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type2).createImage();
						med.getCompositeShapeParameterChild_2().setVisible(true);
					}else if(comboType.equals(ComboLabel.TYPE3)){
						med.getComboType().select(2);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type3).createImage();
						med.getCompositeShapeParameterChild_3().setVisible(true);
					}else if(comboType.equals(ComboLabel.TYPE4)){
						med.getComboType().select(3);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type4).createImage();
						med.getCompositeShapeParameterChild_4().setVisible(true);
					}else if(comboType.equals(ComboLabel.TYPE5)){
						med.getComboType().select(4);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type5).createImage();
						med.getCompositeShapeParameterChild_5().setVisible(true);
					}else if(comboType.equals(ComboLabel.TYPE6)){
						med.getComboType().select(5);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type6).createImage();
						med.getCompositeShapeParameterChild_6().setVisible(true);
					}else if(comboType.equals(ComboLabel.TYPE7)){
						med.getComboType().select(6);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type7).createImage();
						med.getCompositeShapeParameterChild_7().setVisible(true);
					}
					med.getLblPhoto().setImage(img);
					med.getLblPhoto().pack();	
				}else {
					this.comboType = "No value";
					Image img = ImageDescriptor.createFromFile(View.class,ImagePath.Type0).createImage();
					med.getComboType().select(-1);
					med.getCompositeShapeParameterChild_1().setVisible(false);
					med.getCompositeShapeParameterChild_2().setVisible(false);
					med.getCompositeShapeParameterChild_3().setVisible(false);
					med.getCompositeShapeParameterChild_4().setVisible(false);
					med.getCompositeShapeParameterChild_5().setVisible(false);
					med.getCompositeShapeParameterChild_6().setVisible(false);
					med.getCompositeShapeParameterChild_7().setVisible(false);
					
					med.getLblPhoto().setImage(img);
					med.getLblPhoto().pack();
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblWidth)){
				if(parsingDataList.size() == 2){
					this.textWidth = parsingDataList.get(1);
					med.getTextWidth().setText(this.textWidth);
				}else {
					this.textWidth = "No value";
					med.getTextWidth().setText(this.textWidth);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblLength)){
				if(parsingDataList.size() == 2){
					this.textLength = parsingDataList.get(1);
					med.getTextLength().setText(this.textLength);
				}else {
					this.textLength = "No value";
					med.getTextLength().setText(this.textLength);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblThickness)){
				if(parsingDataList.size() == 2){
					this.textThickness = parsingDataList.get(1);
					med.getTextThickness().setText(this.textThickness);
				}else {
					this.textThickness = "No value";
					med.getTextThickness().setText(this.textThickness);	
				}
				
			}
			
			else if(parsingDataList.get(0).equals(TextLabel.lblLeftEdgeWavePitch_type2)){
				if(parsingDataList.size() == 2){
					this.type2_textLeftEdgeWavePitch = parsingDataList.get(1);
					med.getType2_textLeftEdgeWavePitch().setText(this.type2_textLeftEdgeWavePitch);
				}else {
					this.type2_textLeftEdgeWavePitch = "No value";
					med.getType2_textLeftEdgeWavePitch().setText(this.type2_textLeftEdgeWavePitch);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblRightEdgeWavePitch_type2)){
				if(parsingDataList.size() == 2){
					this.type2_textRightEdgeWavePitch = parsingDataList.get(1);
					med.getType2_textRightEdgeWavePitch().setText(this.type2_textRightEdgeWavePitch);
				}else {
					this.type2_textRightEdgeWavePitch = "No value";
					med.getType2_textRightEdgeWavePitch().setText(this.type2_textRightEdgeWavePitch);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblLeftEdgeWaveHeight_type2)){
				if(parsingDataList.size() == 2){
					this.type2_textLeftEdgeWaveHeight = parsingDataList.get(1);
					med.getType2_textLeftEdgeWaveHeight().setText(this.type2_textLeftEdgeWaveHeight);
				}else {
					this.type2_textLeftEdgeWaveHeight = "No value";
					med.getType2_textLeftEdgeWaveHeight().setText(this.type2_textLeftEdgeWaveHeight);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblRightEdgeWaveHeight_type2)){
				if(parsingDataList.size() == 2){
					this.type2_textRightEdgeWaveHeight = parsingDataList.get(1);
					med.getType2_textRightEdgeWaveHeight().setText(this.type2_textRightEdgeWaveHeight);
				}else {
					this.type2_textRightEdgeWaveHeight = "No value";
					med.getType2_textRightEdgeWaveHeight().setText(this.type2_textRightEdgeWaveHeight);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblLeftEdgeWavePhase_type2)){
				if(parsingDataList.size() == 2){
					this.type2_textLeftEdgeWavePhase = parsingDataList.get(1);
					med.getType2_textLeftEdgeWavePhase().setText(this.type2_textLeftEdgeWavePhase);
				}else {
					this.type2_textLeftEdgeWavePhase = "No value";
					med.getType2_textLeftEdgeWavePhase().setText(this.type2_textLeftEdgeWavePhase);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblRightEdgeWavePhase_type2)){
				if(parsingDataList.size() == 2){
					this.type2_textRightEdgeWavePhase = parsingDataList.get(1);
					med.getType2_textRightEdgeWavePhase().setText(this.type2_textRightEdgeWavePhase);
				}else {
					this.type2_textRightEdgeWavePhase = "No value";
					med.getType2_textRightEdgeWavePhase().setText(this.type2_textRightEdgeWavePhase);	
				}
				
			}
						
			else if(parsingDataList.get(0).equals(TextLabel.lblWavePitch_type3)){
				if(parsingDataList.size() == 2){
					this.type3_textWavePitch = parsingDataList.get(1);
					med.getType3_textWavePitch().setText(this.type3_textWavePitch);
				}else {
					this.type3_textWavePitch = "No value";
					med.getType3_textWavePitch().setText(this.type3_textWavePitch);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblWaveHeight_type3)){
				if(parsingDataList.size() == 2){
					this.type3_textWaveHeight = parsingDataList.get(1);
					med.getType3_textWaveHeight().setText(this.type3_textWaveHeight);
				}else {
					this.type3_textWaveHeight = "No value";
					med.getType3_textWaveHeight().setText(this.type3_textWaveHeight);	
				}
				
			}
			
			else if(parsingDataList.get(0).equals(TextLabel.lblGutterHeight_type4)){
				if(parsingDataList.size() == 2){
					if(comboType.equals(ComboLabel.TYPE4)){
						this.type4_textGutterHeight = parsingDataList.get(1);
						med.getType4_textGutterHeight().setText(this.type4_textGutterHeight);	
					}else if(comboType.equals(ComboLabel.TYPE5)){
						this.type5_textGutterHeight = parsingDataList.get(1);
						med.getType5_textGutterHeight().setText(this.type5_textGutterHeight);
					}else if(comboType.equals(ComboLabel.TYPE7)){
						this.type7_textHeadGutterHeight = parsingDataList.get(1);
						med.getType7_textHeadGutterHeight().setText(this.type7_textHeadGutterHeight);
					}
					
					
				}else {
					if(comboType.equals(ComboLabel.TYPE4)){
						this.type4_textGutterHeight = "No value";
						med.getType4_textGutterHeight().setText(this.type4_textGutterHeight);	
					}else if(comboType.equals(ComboLabel.TYPE5)){
						this.type5_textGutterHeight = "No value";
						med.getType5_textGutterHeight().setText(this.type5_textGutterHeight);
					}else if(comboType.equals(ComboLabel.TYPE7)){
						this.type7_textHeadGutterHeight = "No value";
						med.getType7_textHeadGutterHeight().setText(this.type7_textHeadGutterHeight);
					}
				}
				
				
			}
			
			else if(parsingDataList.get(0).equals(TextLabel.lblGutterLength_type5)){
				if(parsingDataList.size() == 2){
					this.type5_textGutterLength = parsingDataList.get(1);
					med.getType5_textGutterLength().setText(this.type5_textGutterLength);	
				}else {
					this.type5_textGutterLength = "No value";
					med.getType5_textGutterLength().setText(this.type5_textGutterLength);	
				}
			}
			
			else if(parsingDataList.get(0).equals(TextLabel.lblHeadGutterHeight_type6)){
				if(parsingDataList.size() == 2){
					this.type6_textHeadGutterHeight = parsingDataList.get(1);
					med.getType6_textHeadGutterHeight().setText(this.type6_textHeadGutterHeight);
				}else {
					this.type6_textHeadGutterHeight = "No value";
					med.getType6_textHeadGutterHeight().setText(this.type6_textHeadGutterHeight);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblHeadGutterLength_type6)){
				if(parsingDataList.size() == 2){
					this.type6_textHeadGutterLength = parsingDataList.get(1);
					med.getType6_textHeadGutterLength().setText(this.type6_textHeadGutterLength);
				}else {
					this.type6_textHeadGutterLength = "No value";
					med.getType6_textHeadGutterLength().setText(this.type6_textHeadGutterLength);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblTailGutterHeight_type6)){
				if(parsingDataList.size() == 2){
					this.type6_textTailGutterHeight = parsingDataList.get(1);
					med.getType6_textTailGutterHeight().setText(this.type6_textTailGutterHeight);
				}else {
					this.type6_textTailGutterHeight = "No value";
					med.getType6_textTailGutterHeight().setText(this.type6_textTailGutterHeight);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblTailGutterLength_type6)){
				if(parsingDataList.size() == 2){
					this.type6_textTailGutterLength = parsingDataList.get(1);
					med.getType6_textTailGutterLength().setText(this.type6_textTailGutterLength);
				}else {
					this.type6_textTailGutterLength = "No value";
					med.getType6_textTailGutterLength().setText(this.type6_textTailGutterLength);	
				}
				
			}
			
			else if(parsingDataList.get(0).equals(TextLabel.lblGutterLength_type7)){
				if(parsingDataList.size() == 2){
					this.type7_textGutterLength = parsingDataList.get(1);
					med.getType7_textGutterLength().setText(this.type7_textGutterLength);
				}else {
					this.type7_textGutterLength = "No value";
					med.getType7_textGutterLength().setText(this.type7_textGutterLength);	
				}
			}
			
			else if(parsingDataList.get(0).equals(TextLabel.lblGutterLengthLength_type7)){
				if(parsingDataList.size() == 2){
					this.type7_textGutterLengthLength = parsingDataList.get(1);
					med.getType7_textGutterLengthLength().setText(this.type7_textGutterLengthLength);
				}else {
					this.type7_textGutterLengthLength = "No value";
					med.getType7_textGutterLengthLength().setText(this.type7_textGutterLengthLength);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblGutterWidthLength_type7)){
				if(parsingDataList.size() == 2){
					this.type7_textGutterWidthLength = parsingDataList.get(1);
					med.getType7_textGutterWidthLength().setText(this.type7_textGutterWidthLength);
				}else {
					this.type7_textGutterWidthLength = "No value";
					med.getType7_textGutterWidthLength().setText(this.type7_textGutterWidthLength);	
				}
				
			}
			//----------------------------------------------------
			else if(parsingDataList.get(0).equals(TextLabel.lblThicknessElementNum)){
				if(parsingDataList.size() == 2){
					this.textThicknessElementNum = parsingDataList.get(1);
					med.getTextThicknessElementNum().setText(this.textThicknessElementNum);
				}else {
					this.textThicknessElementNum = "No value";
					med.getTextThicknessElementNum().setText(this.textThicknessElementNum);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblWidthAspectRatio)){
				if(parsingDataList.size() == 2){
					this.textWidthAspectRatio = parsingDataList.get(1);
					med.getTextWidthAspectRatio().setText(this.textWidthAspectRatio);
				}else {
					this.textWidthAspectRatio = "No value";
					med.getTextWidthAspectRatio().setText(this.textWidthAspectRatio);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblLengthAspectRatio)){
				if(parsingDataList.size() == 2){
					this.textLengthAspectRatio = parsingDataList.get(1);
					med.getTextLengthAspectRatio().setText(this.textLengthAspectRatio);
				}else {
					this.textLengthAspectRatio = "No value";
					med.getTextLengthAspectRatio().setText(this.textLengthAspectRatio);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblElementNumber)){
				if(parsingDataList.size() == 2){
					this.textElementNumber = parsingDataList.get(1);
					med.getTextElementNumber().setText(this.textElementNumber);
				}else {
					this.textElementNumber = "No value";
					med.getTextElementNumber().setText(this.textElementNumber);	
				}
				
			}
			//----------------------------------------------------
			else if(parsingDataList.get(0).equals(TextLabel.lblPlateVelocity)){
				if(parsingDataList.size() == 2){
					this.textPlateVelocity = parsingDataList.get(1);
					med.getTextPlateVelocity().setText(this.textPlateVelocity);
				}else {
					this.textPlateVelocity = "No value";
					med.getTextPlateVelocity().setText(this.textPlateVelocity);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblTemperatureStart)){
				if(parsingDataList.size() == 2){
					this.textTemperatureStart = parsingDataList.get(1);
					med.getTextTemperatureStart().setText(this.textTemperatureStart);
				}else {
					this.textTemperatureStart = "No value";
					med.getTextTemperatureStart().setText(this.textTemperatureStart);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblTemperatureEnd)){
				if(parsingDataList.size() == 2){
					this.textTemperatureEnd = parsingDataList.get(1);
					med.getTextTemperatureEnd().setText(this.textTemperatureEnd);
				}else {
					this.textTemperatureEnd = "No value";
					med.getTextTemperatureEnd().setText(this.textTemperatureEnd);	
				}
				
			}
			//update version2 2017.01.27
			else if(parsingDataList.get(0).equals(TextLabel.lblPassLineOffset)){
				if(parsingDataList.size() == 2){
					this.textPassLineOffset = parsingDataList.get(1);
					med.getTextPassLineOffset().setText(this.textPassLineOffset);
				}else {
					this.textPassLineOffset = "No value";
					med.getTextPassLineOffset().setText(this.textPassLineOffset);
				}
			}
			//----------------------------------------------------
			else if(parsingDataList.get(0).equals(TextLabel.lblUpperRollNumber)){
				if(parsingDataList.size() == 2){
					this.spinnerUpperRollNum = parsingDataList.get(1);
					med.getSpinnerUpperRollNum().setSelection(Integer.parseInt(this.spinnerUpperRollNum));
				}else {
					this.spinnerUpperRollNum = "0";
					med.getSpinnerUpperRollNum().setSelection(0);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblLowerRollNumber)){
				if(parsingDataList.size() == 2){
					this.spinnerLowerRollNum = parsingDataList.get(1);
					med.getSpinnerLowerRollNum().setSelection(Integer.parseInt(this.spinnerLowerRollNum));
				}else {
					this.spinnerLowerRollNum = "0";
					med.getSpinnerLowerRollNum().setSelection(0);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblRollPitch)){
				if(parsingDataList.size() == 2){
					this.textRollPitch = parsingDataList.get(1);
					med.getTextRollPitch().setText(this.textRollPitch);
				}else {
					this.textRollPitch = "No value";
					med.getTextRollPitch().setText(this.textRollPitch);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblRollLength)){
				if(parsingDataList.size() == 2){
					this.textRollLength = parsingDataList.get(1);
					med.getTextRollLength().setText(this.textRollLength);	
				}else {
					this.textRollLength = "No value";
					med.getTextRollLength().setText(this.textRollLength);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblEntryUpperRollGap)){
				if(parsingDataList.size() == 2){
					this.textEntryUpperRollGap = parsingDataList.get(1);
					med.getTextEntryUpperRollGap().setText(this.textEntryUpperRollGap);	
				}else {
					this.textEntryUpperRollGap = "No value";
					med.getTextEntryUpperRollGap().setText(this.textEntryUpperRollGap);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblEntryLowerRollGap)){
				if(parsingDataList.size() == 2){
					this.textEntryLowerRollGap = parsingDataList.get(1);
					med.getTextEntryLowerRollGap().setText(this.textEntryLowerRollGap);	
				}else {
					this.textEntryLowerRollGap = "No value";
					med.getTextEntryLowerRollGap().setText(this.textEntryLowerRollGap);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblExitUpperRollGap)){
				if(parsingDataList.size() == 2){
					this.textExitUpperRollGap = parsingDataList.get(1);
					med.getTextExitUpperRollGap().setText(this.textExitUpperRollGap);	
				}else {
					this.textExitUpperRollGap = "No value";
					med.getTextExitUpperRollGap().setText(this.textExitUpperRollGap);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblExitLowerRollGap)){
				if(parsingDataList.size() == 2){
					this.textExitLowerRollGap = parsingDataList.get(1);
					med.getTextExitLowerRollGap().setText(this.textExitLowerRollGap);	
				}else {
					this.textExitLowerRollGap = "No value";
					med.getTextExitLowerRollGap().setText(this.textExitLowerRollGap);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblRollFriction)){
				if(parsingDataList.size() == 2){
					this.textRollFriction = parsingDataList.get(1);
					med.getTextRollFriction().setText(this.textRollFriction);	
				}else {
					this.textRollFriction = "No value";
					med.getTextRollFriction().setText(this.textRollFriction);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblRollDiameter)){
				if(parsingDataList.size() == 2){
					this.textRollDiameter = parsingDataList.get(1);
					med.getTextRollDiameter().setText(this.textRollDiameter);	
				}else {
					this.textRollDiameter = "No Value";
					med.getTextRollDiameter().setText(this.textRollDiameter);
				}
				
			}
			//update version2 2017.01.27
			/*
			else if(parsingDataList.get(0).equals(TextLabel.lblRollCrown)){
				if(parsingDataList.size() == 2){
					this.textRollCrown = parsingDataList.get(1);
					med.getTextRollCrown().setText(this.textRollCrown);
				}else {
					this.textRollCrown = "No value";
					med.getTextRollCrown().setText(this.textRollCrown);
				}
			}
			//*/
			else if(parsingDataList.get(0).equals(TextLabel.lblUpperRollCrown)){
				if(parsingDataList.size() == 2){
					this.textUpperRollCrown = parsingDataList.get(1);
					med.getTextUpperRollCrown().setText(this.textUpperRollCrown);
				}else {
					this.textUpperRollCrown = "No value";
					med.getTextUpperRollCrown().setText(this.textUpperRollCrown);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblLowerRollCrown)){
				if(parsingDataList.size() == 2){
					this.textLowerRollCrown = parsingDataList.get(1);
					med.getTextLowerRollCrown().setText(this.textLowerRollCrown);
				}else {
					this.textLowerRollCrown = "No value";
					med.getTextLowerRollCrown().setText(this.textLowerRollCrown);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblMillStiffness)){
				if(parsingDataList.size() == 2){
					this.textMillStiffness = parsingDataList.get(1);
					med.getTextMillStiffness().setText(this.textMillStiffness);
				}else {
					this.textMillStiffness = "No value";
					med.getTextMillStiffness().setText(this.textMillStiffness);
				}
			}
			/*
			else if(parsingDataList.get(0).equals(TextLabel.lblRollCrownType)){
				if(parsingDataList.size() == 2){
					this.RollCrownType = parsingDataList.get(1);
					if(this.RollCrownType.equals("None")){
						// None Type
						med.getBtnRadioNone_RC().setSelection(true);
						med.getBtnRadioApply_RC().setSelection(false);
						med.getTextRollCrown().setEnabled(false);
					}else {
						// Apply Type
						med.getBtnRadioNone_RC().setSelection(false);
						med.getBtnRadioApply_RC().setSelection(true);
						med.getTextRollCrown().setEnabled(true);
					}
					
				}else {
					this.RollCrownType = "Apply";
					med.getBtnRadioNone_RC().setSelection(false);
					med.getBtnRadioApply_RC().setSelection(true);
					med.getTextRollCrown().setEnabled(true);
				}
			}
			//*/
			else if(parsingDataList.get(0).equals(TextLabel.lblMillStiffnessType)){
				if(parsingDataList.size() == 2){
					this.MillStiffnessType = parsingDataList.get(1);
					if(this.MillStiffnessType.equals("Rigid")){
						// Rigid Type
						med.getBtnRadioRigid_MS().setSelection(true);
						med.getBtnRadioSpring_MS().setSelection(false);
						med.getTextMillStiffness().setEnabled(false);
					}else {
						// Spring Type
						med.getBtnRadioRigid_MS().setSelection(false);
						med.getBtnRadioSpring_MS().setSelection(true);
						med.getTextMillStiffness().setEnabled(true);
					}
				}else {
					this.MillStiffnessType = "Spring";
					med.getBtnRadioRigid_MS().setSelection(false);
					med.getBtnRadioSpring_MS().setSelection(true);
					med.getTextMillStiffness().setEnabled(true);
				}
			}
			// version3 update 
			else if(parsingDataList.get(0).equals(TextLabel.lblUpperEntryRollGapMovement)){
				if(parsingDataList.size() == 2){
					this.textUpperEntryRollGapMovement = parsingDataList.get(1);
					med.getTextUpperEntryRollGapMovement().setText(this.textUpperEntryRollGapMovement);
				}else  {
					this.textUpperEntryRollGapMovement = "No value";
					med.getTextUpperEntryRollGapMovement().setText(this.textUpperEntryRollGapMovement);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblUpperExitRollGapMovement)){
				if(parsingDataList.size() == 2){
					this.textUpperExitRollGapMovement = parsingDataList.get(1);
					med.getTextUpperExitRollGapMovement().setText(this.textUpperExitRollGapMovement);
				}else {
					this.textUpperExitRollGapMovement = "No value";
					med.getTextUpperExitRollGapMovement().setText(this.textUpperExitRollGapMovement);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblUpperRollGapStayingTime)){
				if(parsingDataList.size() == 2){
					this.textUpperRollGapStayingTime = parsingDataList.get(1);
					med.getTextUpperRollGapStayingTime().setText(this.textUpperRollGapStayingTime);
				}else {
					this.textUpperRollGapStayingTime = "No value";
					med.getTextUpperRollGapStayingTime().setText(this.textUpperRollGapStayingTime);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblUpperRollGapMovingTime)){
				if(parsingDataList.size() == 2){
					this.textUpperRollGapMovingTime = parsingDataList.get(1);
					med.getTextUpperRollGapMovingTime().setText(this.textUpperRollGapMovingTime);
				}else {
					this.textUpperRollGapMovingTime = "No value";
					med.getTextUpperRollGapMovingTime().setText(this.textUpperRollGapMovingTime);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblLowerEntryRollGapMovement)){
				if(parsingDataList.size() == 2){
					this.textLowerEntryRollGapMovement = parsingDataList.get(1);
					med.getTextLowerEntryRollGapMovement().setText(this.textLowerEntryRollGapMovement);
				}else {
					this.textLowerEntryRollGapMovement = "No value";
					med.getTextLowerEntryRollGapMovement().setText(this.textLowerEntryRollGapMovement);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblLowerExitRollGapMovement)){
				if(parsingDataList.size() == 2){
					this.textLowerExitRollGapMovement = parsingDataList.get(1);
					med.getTextLowerExitRollGapMovement().setText(this.textLowerExitRollGapMovement);
				}else {
					this.textLowerExitRollGapMovement = "No value";
					med.getTextLowerExitRollGapMovement().setText(this.textLowerExitRollGapMovement);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblLowerRollGapStayingTime)){
				if(parsingDataList.size() == 2){
					this.textLowerRollGapStayingTime = parsingDataList.get(1);
					med.getTextLowerRollGapStayingTime().setText(this.textLowerRollGapStayingTime);
				}else {
					this.textLowerRollGapStayingTime = "No value";
					med.getTextLowerRollGapStayingTime().setText(this.textLowerRollGapStayingTime);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblLowerRollGapMovingTime)){
				if(parsingDataList.size() == 2){
					this.textLowerRollGapMovingTime = parsingDataList.get(1);
					med.getTextLowerRollGapMovingTime().setText(this.textLowerRollGapMovingTime);
				}else {
					this.textLowerRollGapMovingTime = "No value";
					med.getTextLowerRollGapMovingTime().setText(this.textLowerRollGapMovingTime);
				}
			}
			else if(parsingDataList.get(0).equals(TextLabel.lblFrontHDRollDia)){
				if(parsingDataList.size() == 2){
					this.textFrontHDRollDia = parsingDataList.get(1);
					med.getTextFrontHDRollDia().setText(this.textFrontHDRollDia);
				}else{
					this.textFrontHDRollDia = "No value";
					med.getTextFrontHDRollDia().setText(this.textFrontHDRollDia);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblFrontHDRollPitch)){
				if(parsingDataList.size() == 2){
					this.textFrontHDRollPitch = parsingDataList.get(1);
					med.getTextFrontHDRollPitch().setText(this.textFrontHDRollPitch);
				}else{
					this.textFrontHDRollPitch = "No value";
					med.getTextFrontHDRollPitch().setText(this.textFrontHDRollPitch);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblFrontHDRollVericalPos)){
				if(parsingDataList.size() == 2){
					this.textFrontHDRollVericalPos = parsingDataList.get(1);
					med.getTextFrontHDRollVericalPos().setText(this.textFrontHDRollVericalPos);
				}else{
					this.textFrontHDRollVericalPos = "No value";
					med.getTextFrontHDRollVericalPos().setText(this.textFrontHDRollVericalPos);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblRearHDRollDia)){
				if(parsingDataList.size() == 2){
					this.textRearHDRollDia = parsingDataList.get(1);
					med.getTextRearHDRollDia().setText(this.textRearHDRollDia);
				}else{
					this.textRearHDRollDia = "No value";
					med.getTextRearHDRollDia().setText(this.textRearHDRollDia);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblRearHDRollPitch)){
				if(parsingDataList.size() == 2){
					this.textRearHDRollPitch = parsingDataList.get(1);
					med.getTextRearHDRollPitch().setText(this.textRearHDRollPitch);
				}else{
					this.textRearHDRollPitch = "No value";
					med.getTextRearHDRollPitch().setText(this.textRearHDRollPitch);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblRearHDRollVerticalPos)){
				if(parsingDataList.size() == 2){
					this.textRearHDRollVerticalPos = parsingDataList.get(1);
					med.getTextRearHDRollVerticalPos().setText(this.textRearHDRollVerticalPos);
				}else{
					this.textRearHDRollVerticalPos = "No value";
					med.getTextRearHDRollVerticalPos().setText(this.textRearHDRollVerticalPos);
				}
			}
			
			else if(parsingDataList.get(0).equals(TextLabel.HDRollType)){
				if(parsingDataList.size() == 2){
					this.HDRollType = parsingDataList.get(1);
					if(this.HDRollType.equals("None")){
						med.getBtnUpper().setSelection(false);
						med.getBtnLower().setSelection(false);
						med.getBtnNone().setSelection(true);
					}else if(this.HDRollType.equals("Lower")){
						med.getBtnUpper().setSelection(false);
						med.getBtnNone().setSelection(false);
						med.getBtnLower().setSelection(true);
					}else if(this.HDRollType.equals("Upper")){
						med.getBtnNone().setSelection(false);
						med.getBtnLower().setSelection(false);
						med.getBtnUpper().setSelection(true);
					}
				}else {
					this.HDRollType = "Upper";
					med.getBtnNone().setSelection(false);
					med.getBtnLower().setSelection(false);
					med.getBtnUpper().setSelection(true);
				}
			}

			else if(parsingDataList.get(0).contains("UpperRoll_")){
				if(parsingDataList.size() == 2){
					ArrayList<String> valueList = ParserDefault.splitLineData(parsingDataList.get(1),"\\*");
					UpTableDataContent UTDCObj = new UpTableDataContent();
					UTDCObj.setNo(valueList.get(0));
					UTDCObj.setGap(valueList.get(1));
					UTDCObj.setFriction(valueList.get(2));
					UTDCObj.setDiameter(valueList.get(3));
					UTDCObj.setPitch(valueList.get(4));
					upTableDataList.add(UTDCObj);	
				}else {
					upTableDataList = new ArrayList<UpTableDataContent>();
					
				}
				
			}else if(parsingDataList.get(0).contains("LowerRoll_")){
				if(parsingDataList.size() == 2){
					ArrayList<String> valueList = ParserDefault.splitLineData(parsingDataList.get(1),"\\*");
					DownTableDataContent DTDCObj = new DownTableDataContent();
					DTDCObj.setNo(valueList.get(0));
					DTDCObj.setGap(valueList.get(1));
					DTDCObj.setFriction(valueList.get(2));
					DTDCObj.setDiameter(valueList.get(3));
					DTDCObj.setPitch(valueList.get(4));
					downTableDataList.add(DTDCObj);
				}else {
					downTableDataList = new ArrayList<DownTableDataContent>();
				}
			
			}

			else if(parsingDataList.get(0).equals(TextLabel.lblYoungsModulus)){
				if(parsingDataList.size() == 2){
					this.textYoungsModulus = parsingDataList.get(1);
					med.getTextYoungsModulus().setText(this.textYoungsModulus);
				}else {
					this.textYoungsModulus = "No value";
					med.getTextYoungsModulus().setText(this.textYoungsModulus);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblFlowStress)){
				if(parsingDataList.size() == 2){
					this.textFlowStress = parsingDataList.get(1);
					med.getTextFlowStress().setText(this.textFlowStress);
				}else { 
					this.textFlowStress = "No value";
					med.getTextFlowStress().setText(this.textFlowStress);
				}
			}
			//update version2 2016.01.27
			else if(parsingDataList.get(0).equals(TextLabel.lblYieldStrength)){
				if(parsingDataList.size() == 2){
					this.textYieldStrength = parsingDataList.get(1);
					med.getTextYieldStrength().setText(this.textYieldStrength);
				}else {
					this.textYieldStrength = "No value";
					med.getTextYieldStrength().setText(this.textYieldStrength);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblTensileStrength)){
				if(parsingDataList.size() == 2){
					this.textTensileStrength = parsingDataList.get(1);
					med.getTextTensileStrength().setText(this.textTensileStrength);
				}else {
					this.textTensileStrength = "No value";
					med.getTextTensileStrength().setText(this.textTensileStrength);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblElongation)){
				if(parsingDataList.size() == 2){
					this.textElongation = parsingDataList.get(1);
					med.getTextElongation().setText(this.textElongation);
				}else {
					this.textElongation = "No value";
					med.getTextElongation().setText(this.textElongation);
				}
			}
			
			
			else if(parsingDataList.get(0).equals(TextLabel.lblThermalExpansionCoefficient)){
				if(parsingDataList.size() == 2){
					this.textThermalExpansionCoefficient = parsingDataList.get(1);
					med.getTextThermalExpansionCoefficient().setText(this.textThermalExpansionCoefficient);
				}else {
					this.textThermalExpansionCoefficient = "No value";
					med.getTextThermalExpansionCoefficient().setText(this.textThermalExpansionCoefficient);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblMassDensity)){
				if(parsingDataList.size() == 2){
					this.textMassDensity = parsingDataList.get(1);
					med.getTextMassDensity().setText(this.textMassDensity);
				}else {
					this.textMassDensity = "No value";
					med.getTextMassDensity().setText(this.textMassDensity);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblYoungsModulus+"Type")){
				if(parsingDataList.size() == 2){
					this.YoungsModulusType = parsingDataList.get(1);
					if(this.YoungsModulusType.equals("Constant")){
						med.getBtnRadioConstant_YM().setSelection(true);
						med.getBtnRadioTable_YM().setSelection(false);
						med.getBtnExplorerYoungsModulus().setEnabled(false);
					}else {
						med.getBtnRadioConstant_YM().setSelection(false);
						med.getBtnRadioTable_YM().setSelection(true);
						med.getBtnExplorerYoungsModulus().setEnabled(true);
					}
				}else {
					this.YoungsModulusType = "Table";
					med.getBtnRadioConstant_YM().setSelection(false);
					med.getBtnRadioTable_YM().setSelection(true);
					med.getBtnExplorerYoungsModulus().setEnabled(true);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblFlowStress+"Type")){
				if(parsingDataList.size() == 2){
					this.FlowStressType = parsingDataList.get(1);
					if(this.FlowStressType.equals("Constant")){
						med.getBtnRadioConstant_FS().setSelection(true);
						med.getBtnRadioTable_FS().setSelection(false);
						med.getBtnExplorerFlowStress().setEnabled(false);
						//update version2 2016.01.27
						med.getTextFlowStress().setEnabled(false);
						med.getTextYieldStrength().setEnabled(true);
						med.getTextTensileStrength().setEnabled(true);
						med.getTextElongation().setEnabled(true);
						
					}else {
						med.getBtnRadioConstant_FS().setSelection(false);
						med.getBtnRadioTable_FS().setSelection(true);
						med.getBtnExplorerFlowStress().setEnabled(true);
						//update version2 2016.01.27
						med.getTextFlowStress().setEnabled(true);
						med.getTextYieldStrength().setEnabled(false);
						med.getTextTensileStrength().setEnabled(false);
						med.getTextElongation().setEnabled(false);
						
					}
				}else {
					this.FlowStressType = "Table";
					med.getBtnRadioConstant_FS().setSelection(false);
					med.getBtnRadioTable_FS().setSelection(true);
					med.getBtnExplorerFlowStress().setEnabled(true);
					//update version2 2016.01.27
					med.getTextFlowStress().setEnabled(true);
					med.getTextYieldStrength().setEnabled(false);
					med.getTextTensileStrength().setEnabled(false);
					med.getTextElongation().setEnabled(false);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblThermalExpansionCoefficient+"Type")){
				if(parsingDataList.size() == 2){
					this.ThermalExpansionCoefficientType = parsingDataList.get(1);
					if(this.ThermalExpansionCoefficientType.equals("Constant")){
						med.getBtnRadioConstant_TEC().setSelection(true);
						med.getBtnRadioTable_TEC().setSelection(false);
						med.getBtnExplorerThermalExpansionCoefficient().setEnabled(false);
					}else {
						med.getBtnRadioConstant_TEC().setSelection(false);
						med.getBtnRadioTable_TEC().setSelection(true);
						med.getBtnExplorerThermalExpansionCoefficient().setEnabled(true);
					}
				}else {
					this.ThermalExpansionCoefficientType = "Table";
					med.getBtnRadioConstant_TEC().setSelection(false);
					med.getBtnRadioTable_TEC().setSelection(true);
					med.getBtnExplorerThermalExpansionCoefficient().setEnabled(true);
				}
			}
			else if(parsingDataList.get(0).equals(TextLabel.lblPoissonsRatio+"Type")){
				if(parsingDataList.size() == 2){
					this.PoissonsRatioType = parsingDataList.get(1);
					if(this.PoissonsRatioType.equals("Constant")){
						med.getBtnRadioConstant_PR().setSelection(true);
						med.getBtnRadioTable_PR().setSelection(false);
						med.getBtnExplorerPoissonsRatio().setEnabled(false);
					}else {
						med.getBtnRadioConstant_PR().setSelection(false);
						med.getBtnRadioTable_PR().setSelection(true);
						med.getBtnExplorerPoissonsRatio().setEnabled(true);
					}
				}else {
					this.PoissonsRatioType = "Table";
					med.getBtnRadioConstant_PR().setSelection(false);
					med.getBtnRadioTable_PR().setSelection(true);
					med.getBtnExplorerPoissonsRatio().setEnabled(true);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblPoissonsRatio)){
				if(parsingDataList.size() == 2){
					this.textPoissonsRatio = parsingDataList.get(1);
					med.getTextPoissonsRatio().setText(this.textPoissonsRatio);
				}else {
					this.textPoissonsRatio = "No value";
					med.getTextPoissonsRatio().setText(this.textPoissonsRatio);
				}
			}

			else if(parsingDataList.get(0).equals(TextLabel.lblSolvingTime)){
				if(parsingDataList.size() == 2){
					this.textSolvingTime = parsingDataList.get(1);
					med.getTextSolvingTime().setText(this.textSolvingTime);	
				}else {
					this.textSolvingTime = "No value";
					med.getTextSolvingTime().setText(this.textSolvingTime);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblIncrementTime)){
				if(parsingDataList.size() == 2){
					this.textIncrementTime = parsingDataList.get(1);
					med.getTextIncrementTime().setText(this.textIncrementTime);	
				}else {
					this.textIncrementTime = "No value";
					med.getTextIncrementTime().setText(this.textIncrementTime);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblParallelDDM)){
				if(parsingDataList.size() == 2){
					if(parsingDataList.get(1).equals("true")){
						this.btnParallelDDMUse = true;
						med.getBtnParallelDDMUse().setSelection(true);
						med.getSpinnerDomain().setEnabled(true);
					}else{
						this.btnParallelDDMUse = false;
						med.getBtnParallelDDMUse().setSelection(false);
						med.getSpinnerDomain().setEnabled(false);
					}
				}else {
					this.btnParallelDDMUse = false;
					med.getBtnParallelDDMUse().setSelection(false);
					med.getSpinnerDomain().setEnabled(false);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblDomain)){
				if(parsingDataList.size() == 2){
					this.spinnerDomain = parsingDataList.get(1);
					med.getSpinnerDomain().setSelection(Integer.parseInt(this.spinnerDomain));	
				}else {
					this.spinnerDomain = "No value";
					med.getSpinnerDomain().setSelection(-1);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblParallelMultiThread)){
				if(parsingDataList.size() == 2){
					if(parsingDataList.get(1).equals("true")){
						this.btnParallelMultiThreadUse = true;
						med.getBtnParallelMultiThreadUse().setSelection(true);
						med.getSpinnerThread().setEnabled(true);
					}else{
						this.btnParallelMultiThreadUse = false;
						med.getBtnParallelMultiThreadUse().setSelection(false);
						med.getSpinnerThread().setEnabled(false);
					}
				}else {
					this.btnParallelMultiThreadUse = false;
					med.getBtnParallelMultiThreadUse().setSelection(false);
					med.getSpinnerThread().setEnabled(false);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblThread)){
				if(parsingDataList.size() == 2){
					this.spinnerThread = parsingDataList.get(1);
					med.getSpinnerThread().setSelection(Integer.parseInt(this.spinnerThread));
				}else {
					this.spinnerThread = "No value";
					med.getSpinnerThread().setSelection(-1);
				}
			}
		}
		//version3 update
		//updateTableData();
	}
	
	
	public void setAllDataUI_2D(String newDBFilePath){
		upTableDataList_2D = new ArrayList<UpTableDataContent>();
		downTableDataList_2D = new ArrayList<DownTableDataContent>();
		
		Reader reader = new Reader(newDBFilePath);
		reader.running();
		ArrayList<String> fileDataList = reader.getFileDataList();
		
		ArrayList<String> parsingDataList;
		for(String line : fileDataList){
			parsingDataList = ParserDefault.splitLineData(line, "=");
			
			med.getTabFolder().setSelection(0);
			this.LevellerType = "2D";
			
			if(parsingDataList.get(0).equals(TextLabel.lblModelName)){
				if(parsingDataList.size() == 2){
					this.textModelName = parsingDataList.get(1);
					med.getTextModelName().setText(this.textModelName);
				}else {
					this.textModelName = "No value";
					med.getTextModelName().setText(this.textModelName);	
				}
				
			}
			//----------------------------------------------------
			else if(parsingDataList.get(0).equals(TextLabel.lblType_2D)){
				if(parsingDataList.size() == 2){
					this.comboType_2D = parsingDataList.get(1);
					Image img = ImageDescriptor.createFromFile(View.class,ImagePath.Type0).createImage();
					med.getCompositeShapeParameterChild_1_2D().setVisible(false);
					med.getCompositeShapeParameterChild_2_2D().setVisible(false);
					med.getCompositeShapeParameterChild_3_2D().setVisible(false);
					/*
					med.getCompositeShapeParameterChild_4_2D().setVisible(false);
					med.getCompositeShapeParameterChild_5_2D().setVisible(false);
					med.getCompositeShapeParameterChild_6_2D().setVisible(false);
					med.getCompositeShapeParameterChild_7_2D().setVisible(false);
					// */
					if(comboType_2D.equals(ComboLabel.TYPE1_2D)){
						med.getComboType_2D().select(0);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type1_2D).createImage();
						med.getCompositeShapeParameterChild_1_2D().setVisible(true);	
					}else if(comboType_2D.equals(ComboLabel.TYPE2_2D)){
						med.getComboType_2D().select(1);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type2_2D).createImage();
						med.getCompositeShapeParameterChild_2_2D().setVisible(true);
					}else if(comboType_2D.equals(ComboLabel.TYPE3_2D)){
						med.getComboType_2D().select(2);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type3_2D).createImage();
						med.getCompositeShapeParameterChild_3_2D().setVisible(true);
					}
					/*
					else if(comboType_2D.equals(ComboLabel.TYPE4)){
						med.getComboType_2D().select(3);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type4).createImage();
						med.getCompositeShapeParameterChild_4_2D().setVisible(true);
					}else if(comboType_2D.equals(ComboLabel.TYPE5)){
						med.getComboType_2D().select(4);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type5).createImage();
						med.getCompositeShapeParameterChild_5_2D().setVisible(true);
					}else if(comboType_2D.equals(ComboLabel.TYPE6)){
						med.getComboType_2D().select(5);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type6).createImage();
						med.getCompositeShapeParameterChild_6_2D().setVisible(true);
					}else if(comboType_2D.equals(ComboLabel.TYPE7)){
						med.getComboType_2D().select(6);
						img = ImageDescriptor.createFromFile(View.class,ImagePath.Type7).createImage();
						med.getCompositeShapeParameterChild_7_2D().setVisible(true);
					}
					// */
					med.getLblPhoto_2D().setImage(img);
					med.getLblPhoto_2D().pack();	
				}else {
					this.comboType = "No value";
					Image img = ImageDescriptor.createFromFile(View.class,ImagePath.Type0).createImage();
					med.getComboType_2D().select(-1);
					med.getCompositeShapeParameterChild_1_2D().setVisible(false);
					med.getCompositeShapeParameterChild_2_2D().setVisible(false);
					med.getCompositeShapeParameterChild_3_2D().setVisible(false);
					/*
					med.getCompositeShapeParameterChild_4_2D().setVisible(false);
					med.getCompositeShapeParameterChild_5_2D().setVisible(false);
					med.getCompositeShapeParameterChild_6_2D().setVisible(false);
					med.getCompositeShapeParameterChild_7_2D().setVisible(false);
					// */
					med.getLblPhoto_2D().setImage(img);
					med.getLblPhoto_2D().pack();
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblWidth_2D)){
				if(parsingDataList.size() == 2){
					this.textWidth_2D = parsingDataList.get(1);
					med.getTextWidth_2D().setText(this.textWidth_2D);
				}else {
					this.textWidth_2D = "No value";
					med.getTextWidth_2D().setText(this.textWidth_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblLength_2D)){
				if(parsingDataList.size() == 2){
					this.textLength_2D = parsingDataList.get(1);
					med.getTextLength_2D().setText(this.textLength_2D);
				}else {
					this.textLength_2D = "No value";
					med.getTextLength_2D().setText(this.textLength_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblThickness_2D)){
				if(parsingDataList.size() == 2){
					this.textThickness_2D = parsingDataList.get(1);
					med.getTextThickness_2D().setText(this.textThickness_2D);
				}else {
					this.textThickness_2D = "No value";
					med.getTextThickness_2D().setText(this.textThickness_2D);	
				}
				
			}
			
			else if(parsingDataList.get(0).equals( TextLabel.lblWavePitch_type2_2D)){
				if(parsingDataList.size() == 2){
					this.type2_textWavePitch_2D = parsingDataList.get(1);
					med.getType2_textWavePitch_2D().setText(this.type2_textWavePitch_2D);
				}else {
					this.type2_textWavePitch_2D = "No value";
					med.getType2_textWavePitch_2D().setText(this.type2_textWavePitch_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblWaveHeight_type2_2D)){
				if(parsingDataList.size() == 2){
					this.type2_textWaveHeight_2D = parsingDataList.get(1);
					med.getType2_textWaveHeight_2D().setText(this.type2_textWaveHeight_2D);
				}else {
					this.type2_textWaveHeight_2D = "No value";
					med.getType2_textWaveHeight_2D().setText(this.type2_textWaveHeight_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblWavePhase_type2_2D)){
				if(parsingDataList.size() == 2){
					this.type2_textWavePhase_2D = parsingDataList.get(1);
					med.getType2_textWavePhase_2D().setText(this.type2_textWavePhase_2D);
				}else {
					this.type2_textWavePhase_2D = "No value";
					med.getType2_textWavePhase_2D().setText(this.type2_textWavePhase_2D);	
				}
				
			}
			/*
			else if(parsingDataList.get(0).equals(TextLabel.lblRightEdgeWaveHeight_type2_2D)){
				if(parsingDataList.size() == 2){
					this.type2_textRightEdgeWaveHeight_2D = parsingDataList.get(1);
					med.getType2_textRightEdgeWaveHeight_2D().setText(this.type2_textRightEdgeWaveHeight_2D);
				}else {
					this.type2_textRightEdgeWaveHeight_2D = "No value";
					med.getType2_textRightEdgeWaveHeight_2D().setText(this.type2_textRightEdgeWaveHeight_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblLeftEdgeWavePhase_type2_2D)){
				if(parsingDataList.size() == 2){
					this.type2_textLeftEdgeWavePhase_2D = parsingDataList.get(1);
					med.getType2_textLeftEdgeWavePhase_2D().setText(this.type2_textLeftEdgeWavePhase_2D);
				}else {
					this.type2_textLeftEdgeWavePhase_2D = "No value";
					med.getType2_textLeftEdgeWavePhase_2D().setText(this.type2_textLeftEdgeWavePhase_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblRightEdgeWavePhase_type2_2D)){
				if(parsingDataList.size() == 2){
					this.type2_textRightEdgeWavePhase_2D = parsingDataList.get(1);
					med.getType2_textRightEdgeWavePhase_2D().setText(this.type2_textRightEdgeWavePhase_2D);
				}else {
					this.type2_textRightEdgeWavePhase_2D = "No value";
					med.getType2_textRightEdgeWavePhase_2D().setText(this.type2_textRightEdgeWavePhase_2D);	
				}
				
			}
			// */
			
			else if(parsingDataList.get(0).equals(TextLabel.lblFrontCurlHeight_type3_2D)){
				if(parsingDataList.size() == 2){
					this.type3_textFrontCurlHeight_2D = parsingDataList.get(1);
					med.getType3_textFrontCurlHeight_2D().setText(this.type3_textFrontCurlHeight_2D);
				}else {
					this.type3_textFrontCurlHeight_2D = "No value";
					med.getType3_textFrontCurlHeight_2D().setText(this.type3_textFrontCurlHeight_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblFrontCurlLength_type3_2D)){
				if(parsingDataList.size() == 2){
					this.type3_textFrontCurlLength_2D = parsingDataList.get(1);
					med.getType3_textFrontCurlLength_2D().setText(this.type3_textFrontCurlLength_2D);
				}else {
					this.type3_textFrontCurlLength_2D = "No value";
					med.getType3_textFrontCurlLength_2D().setText(this.type3_textFrontCurlLength_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblRearCurlHeight_type3_2D)){
				if(parsingDataList.size() == 2){
					this.type3_textRearCurlHeight_2D = parsingDataList.get(1);
					med.getType3_textRearCurlHeight_2D().setText(this.type3_textRearCurlHeight_2D);
				}else {
					this.type3_textRearCurlHeight_2D = "No value";
					med.getType3_textRearCurlHeight_2D().setText(this.type3_textRearCurlHeight_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblRearCurlLength_type3_2D)){
				if(parsingDataList.size() == 2){
					this.type3_textRearCurlLength_2D = parsingDataList.get(1);
					med.getType3_textRearCurlLength_2D().setText(this.type3_textRearCurlLength_2D);
				}else {
					this.type3_textRearCurlLength_2D = "No value";
					med.getType3_textRearCurlLength_2D().setText(this.type3_textRearCurlLength_2D);	
				}
				
			}
			/*
			else if(parsingDataList.get(0).equals(TextLabel.lblGutterHeight_type4_2D)){
				if(parsingDataList.size() == 2){
					if(comboType_2D.equals(ComboLabel.TYPE4)){
						this.type4_textGutterHeight_2D = parsingDataList.get(1);
						med.getType4_textGutterHeight_2D().setText(this.type4_textGutterHeight_2D);	
					}else if(comboType_2D.equals(ComboLabel.TYPE5)){
						this.type5_textGutterHeight_2D = parsingDataList.get(1);
						med.getType5_textGutterHeight_2D().setText(this.type5_textGutterHeight_2D);
					}else if(comboType_2D.equals(ComboLabel.TYPE7)){
						this.type7_textHeadGutterHeight_2D = parsingDataList.get(1);
						med.getType7_textHeadGutterHeight_2D().setText(this.type7_textHeadGutterHeight_2D);
					}
					
					
				}else {
					if(comboType_2D.equals(ComboLabel.TYPE4)){
						this.type4_textGutterHeight_2D = "No value";
						med.getType4_textGutterHeight_2D().setText(this.type4_textGutterHeight_2D);	
					}else if(comboType_2D.equals(ComboLabel.TYPE5)){
						this.type5_textGutterHeight_2D = "No value";
						med.getType5_textGutterHeight_2D().setText(this.type5_textGutterHeight_2D);
					}else if(comboType_2D.equals(ComboLabel.TYPE7)){
						this.type7_textHeadGutterHeight_2D = "No value";
						med.getType7_textHeadGutterHeight_2D().setText(this.type7_textHeadGutterHeight_2D);
					}
				}
				
				
			}
			
			else if(parsingDataList.get(0).equals(TextLabel.lblGutterLength_type5_2D)){
				if(parsingDataList.size() == 2){
					this.type5_textGutterLength_2D = parsingDataList.get(1);
					med.getType5_textGutterLength_2D().setText(this.type5_textGutterLength_2D);	
				}else {
					this.type5_textGutterLength_2D = "No value";
					med.getType5_textGutterLength_2D().setText(this.type5_textGutterLength_2D);	
				}
			}
			
			else if(parsingDataList.get(0).equals(TextLabel.lblHeadGutterHeight_type6_2D)){
				if(parsingDataList.size() == 2){
					this.type6_textHeadGutterHeight_2D = parsingDataList.get(1);
					med.getType6_textHeadGutterHeight_2D().setText(this.type6_textHeadGutterHeight_2D);
				}else {
					this.type6_textHeadGutterHeight_2D = "No value";
					med.getType6_textHeadGutterHeight_2D().setText(this.type6_textHeadGutterHeight_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblHeadGutterLength_type6_2D)){
				if(parsingDataList.size() == 2){
					this.type6_textHeadGutterLength_2D = parsingDataList.get(1);
					med.getType6_textHeadGutterLength_2D().setText(this.type6_textHeadGutterLength_2D);
				}else {
					this.type6_textHeadGutterLength_2D = "No value";
					med.getType6_textHeadGutterLength_2D().setText(this.type6_textHeadGutterLength_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblTailGutterHeight_type6_2D)){
				if(parsingDataList.size() == 2){
					this.type6_textTailGutterHeight_2D = parsingDataList.get(1);
					med.getType6_textTailGutterHeight_2D().setText(this.type6_textTailGutterHeight_2D);
				}else {
					this.type6_textTailGutterHeight_2D = "No value";
					med.getType6_textTailGutterHeight_2D().setText(this.type6_textTailGutterHeight_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblTailGutterLength_type6_2D)){
				if(parsingDataList.size() == 2){
					this.type6_textTailGutterLength_2D = parsingDataList.get(1);
					med.getType6_textTailGutterLength_2D().setText(this.type6_textTailGutterLength_2D);
				}else {
					this.type6_textTailGutterLength_2D = "No value";
					med.getType6_textTailGutterLength_2D().setText(this.type6_textTailGutterLength_2D);	
				}
				
			}
			
			else if(parsingDataList.get(0).equals(TextLabel.lblGutterLength_type7_2D)){
				if(parsingDataList.size() == 2){
					this.type7_textGutterLength_2D = parsingDataList.get(1);
					med.getType7_textGutterLength_2D().setText(this.type7_textGutterLength_2D);
				}else {
					this.type7_textGutterLength_2D = "No value";
					med.getType7_textGutterLength_2D().setText(this.type7_textGutterLength_2D);	
				}
			}
			
			else if(parsingDataList.get(0).equals(TextLabel.lblGutterLengthLength_type7_2D)){
				if(parsingDataList.size() == 2){
					this.type7_textGutterLengthLength_2D = parsingDataList.get(1);
					med.getType7_textGutterLengthLength_2D().setText(this.type7_textGutterLengthLength_2D);
				}else {
					this.type7_textGutterLengthLength_2D = "No value";
					med.getType7_textGutterLengthLength_2D().setText(this.type7_textGutterLengthLength_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblGutterWidthLength_type7_2D)){
				if(parsingDataList.size() == 2){
					this.type7_textGutterWidthLength_2D = parsingDataList.get(1);
					med.getType7_textGutterWidthLength_2D().setText(this.type7_textGutterWidthLength_2D);
				}else {
					this.type7_textGutterWidthLength_2D = "No value";
					med.getType7_textGutterWidthLength_2D().setText(this.type7_textGutterWidthLength_2D);	
				}
				
			}
			// */
			//----------------------------------------------------
			else if(parsingDataList.get(0).equals(TextLabel.lblThicknessElementNum_2D)){
				if(parsingDataList.size() == 2){
					this.textThicknessElementNum_2D = parsingDataList.get(1);
					med.getTextThicknessElementNum_2D().setText(this.textThicknessElementNum_2D);
				}else {
					this.textThicknessElementNum_2D = "No value";
					med.getTextThicknessElementNum_2D().setText(this.textThicknessElementNum_2D);	
				}
				
			}
			/*
			else if(parsingDataList.get(0).equals(TextLabel.lblWidthAspectRatio_2D)){
				if(parsingDataList.size() == 2){
					this.textWidthAspectRatio_2D = parsingDataList.get(1);
					med.getTextWidthAspectRatio_2D().setText(this.textWidthAspectRatio_2D);
				}else {
					this.textWidthAspectRatio_2D = "No value";
					med.getTextWidthAspectRatio_2D().setText(this.textWidthAspectRatio_2D);	
				}
				
			}
			// */
			else if(parsingDataList.get(0).equals(TextLabel.lblLengthAspectRatio_2D)){
				if(parsingDataList.size() == 2){
					this.textLengthAspectRatio_2D = parsingDataList.get(1);
					med.getTextLengthAspectRatio_2D().setText(this.textLengthAspectRatio_2D);
				}else {
					this.textLengthAspectRatio_2D = "No value";
					med.getTextLengthAspectRatio_2D().setText(this.textLengthAspectRatio_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblElementNumber_2D)){
				if(parsingDataList.size() == 2){
					this.textElementNumber_2D = parsingDataList.get(1);
					med.getTextElementNumber_2D().setText(this.textElementNumber_2D);
				}else {
					this.textElementNumber_2D = "No value";
					med.getTextElementNumber_2D().setText(this.textElementNumber_2D);	
				}
				
			}
			//----------------------------------------------------
			else if(parsingDataList.get(0).equals(TextLabel.lblPlateVelocity_2D)){
				if(parsingDataList.size() == 2){
					this.textPlateVelocity_2D = parsingDataList.get(1);
					med.getTextPlateVelocity_2D().setText(this.textPlateVelocity_2D);
				}else {
					this.textPlateVelocity_2D = "No value";
					med.getTextPlateVelocity_2D().setText(this.textPlateVelocity_2D);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblTemperatureStart_2D)){
				if(parsingDataList.size() == 2){
					this.textTemperatureStart_2D = parsingDataList.get(1);
					med.getTextTemperatureStart_2D().setText(this.textTemperatureStart_2D);
				}else {
					this.textTemperatureStart = "No value";
					med.getTextTemperatureStart_2D().setText(this.textTemperatureStart_2D);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblTemperatureEnd_2D)){
				if(parsingDataList.size() == 2){
					this.textTemperatureEnd_2D = parsingDataList.get(1);
					med.getTextTemperatureEnd_2D().setText(this.textTemperatureEnd_2D);
				}else {
					this.textTemperatureEnd_2D = "No value";
					med.getTextTemperatureEnd_2D().setText(this.textTemperatureEnd_2D);	
				}
				
			}
			//update version2 2017.01.27
			else if(parsingDataList.get(0).equals(TextLabel.lblPassLineOffset_2D)){
				if(parsingDataList.size() == 2){
					this.textPassLineOffset_2D = parsingDataList.get(1);
					med.getTextPassLineOffset_2D().setText(this.textPassLineOffset_2D);
				}else {
					this.textPassLineOffset_2D = "No value";
					med.getTextPassLineOffset_2D().setText(this.textPassLineOffset_2D);
				}
			}
			//----------------------------------------------------
			else if(parsingDataList.get(0).equals(TextLabel.lblUpperRollNumber_2D)){
				if(parsingDataList.size() == 2){
					this.spinnerUpperRollNum_2D = parsingDataList.get(1);
					med.getSpinnerUpperRollNum_2D().setSelection(Integer.parseInt(this.spinnerUpperRollNum_2D));
				}else {
					this.spinnerUpperRollNum_2D = "0";
					med.getSpinnerUpperRollNum_2D().setSelection(0);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblLowerRollNumber_2D)){
				if(parsingDataList.size() == 2){
					this.spinnerLowerRollNum_2D = parsingDataList.get(1);
					med.getSpinnerLowerRollNum_2D().setSelection(Integer.parseInt(this.spinnerLowerRollNum_2D));
				}else {
					this.spinnerLowerRollNum_2D = "0";
					med.getSpinnerLowerRollNum_2D().setSelection(0);	
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblRollPitch_2D)){
				if(parsingDataList.size() == 2){
					this.textRollPitch_2D = parsingDataList.get(1);
					med.getTextRollPitch_2D().setText(this.textRollPitch_2D);
				}else {
					this.textRollPitch_2D = "No value";
					med.getTextRollPitch_2D().setText(this.textRollPitch_2D);	
				}
				
			}
			/*
			else if(parsingDataList.get(0).equals(TextLabel.lblRollLength_2D)){
				if(parsingDataList.size() == 2){
					this.textRollLength_2D = parsingDataList.get(1);
					med.getTextRollLength_2D().setText(this.textRollLength_2D);	
				}else {
					this.textRollLength_2D = "No value";
					med.getTextRollLength_2D().setText(this.textRollLength_2D);
				}
				
			}
			// */
			else if(parsingDataList.get(0).equals(TextLabel.lblEntryUpperRollGap_2D)){
				if(parsingDataList.size() == 2){
					this.textEntryUpperRollGap_2D = parsingDataList.get(1);
					med.getTextEntryUpperRollGap_2D().setText(this.textEntryUpperRollGap_2D);	
				}else {
					this.textEntryUpperRollGap_2D = "No value";
					med.getTextEntryUpperRollGap_2D().setText(this.textEntryUpperRollGap_2D);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblEntryLowerRollGap_2D)){
				if(parsingDataList.size() == 2){
					this.textEntryLowerRollGap_2D = parsingDataList.get(1);
					med.getTextEntryLowerRollGap_2D().setText(this.textEntryLowerRollGap_2D);	
				}else {
					this.textEntryLowerRollGap_2D = "No value";
					med.getTextEntryLowerRollGap_2D().setText(this.textEntryLowerRollGap_2D);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblExitUpperRollGap_2D)){
				if(parsingDataList.size() == 2){
					this.textExitUpperRollGap_2D = parsingDataList.get(1);
					med.getTextExitUpperRollGap_2D().setText(this.textExitUpperRollGap_2D);	
				}else {
					this.textExitUpperRollGap_2D = "No value";
					med.getTextExitUpperRollGap_2D().setText(this.textExitUpperRollGap_2D);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblExitLowerRollGap_2D)){
				if(parsingDataList.size() == 2){
					this.textExitLowerRollGap_2D = parsingDataList.get(1);
					med.getTextExitLowerRollGap_2D().setText(this.textExitLowerRollGap_2D);	
				}else {
					this.textExitLowerRollGap_2D = "No value";
					med.getTextExitLowerRollGap_2D().setText(this.textExitLowerRollGap_2D);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblRollFriction_2D)){
				if(parsingDataList.size() == 2){
					this.textRollFriction_2D = parsingDataList.get(1);
					med.getTextRollFriction_2D().setText(this.textRollFriction_2D);	
				}else {
					this.textRollFriction_2D = "No value";
					med.getTextRollFriction_2D().setText(this.textRollFriction_2D);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblRollDiameter_2D)){
				if(parsingDataList.size() == 2){
					this.textRollDiameter_2D = parsingDataList.get(1);
					med.getTextRollDiameter_2D().setText(this.textRollDiameter_2D);	
				}else {
					this.textRollDiameter_2D = "No Value";
					med.getTextRollDiameter_2D().setText(this.textRollDiameter_2D);
				}
				
			}
			//update version2 2017.01.27
			/*
			else if(parsingDataList.get(0).equals(TextLabel.lblRollCrown_2D)){
				if(parsingDataList.size() == 2){
					this.textRollCrown_2D = parsingDataList.get(1);
					med.getTextRollCrown_2D().setText(this.textRollCrown_2D);
				}else {
					this.textRollCrown_2D = "No value";
					med.getTextRollCrown_2D().setText(this.textRollCrown_2D);
				}
			}
			// */
			/*
			else if(parsingDataList.get(0).equals(TextLabel.lblUpperRollCrown_2D)){
				if(parsingDataList.size() == 2){
					this.textUpperRollCrown_2D = parsingDataList.get(1);
					med.getTextUpperRollCrown_2D().setText(this.textUpperRollCrown_2D);
				}else {
					this.textUpperRollCrown_2D = "No value";
					med.getTextUpperRollCrown_2D().setText(this.textUpperRollCrown_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblLowerRollCrown_2D)){
				if(parsingDataList.size() == 2){
					this.textLowerRollCrown_2D = parsingDataList.get(1);
					med.getTextLowerRollCrown_2D().setText(this.textLowerRollCrown_2D);
				}else {
					this.textLowerRollCrown_2D = "No value";
					med.getTextLowerRollCrown_2D().setText(this.textLowerRollCrown_2D);
				}
			}
			// */
			else if(parsingDataList.get(0).equals(TextLabel.lblMillStiffness_2D)){
				if(parsingDataList.size() == 2){
					this.textMillStiffness_2D = parsingDataList.get(1);
					med.getTextMillStiffness_2D().setText(this.textMillStiffness_2D);
				}else {
					this.textMillStiffness_2D = "No value";
					med.getTextMillStiffness_2D().setText(this.textMillStiffness_2D);
				}
			}
			/*
			else if(parsingDataList.get(0).equals(TextLabel.lblRollCrownType_2D)){
				if(parsingDataList.size() == 2){
					this.RollCrownType_2D = parsingDataList.get(1);
					if(this.RollCrownType_2D.equals("None")){
						// None Type
						med.getBtnRadioNone_RC_2D().setSelection(true);
						med.getBtnRadioApply_RC_2D().setSelection(false);
						med.getTextRollCrown_2D().setEnabled(false);
					}else {
						// Apply Type
						med.getBtnRadioNone_RC_2D().setSelection(false);
						med.getBtnRadioApply_RC_2D().setSelection(true);
						med.getTextRollCrown_2D().setEnabled(true);
					}
					
				}else {
					this.RollCrownType_2D = "Apply";
					med.getBtnRadioNone_RC_2D().setSelection(false);
					med.getBtnRadioApply_RC_2D().setSelection(true);
					med.getTextRollCrown_2D().setEnabled(true);
				}
			}
			//*/
			else if(parsingDataList.get(0).equals(TextLabel.lblMillStiffnessType_2D)){
				if(parsingDataList.size() == 2){
					this.MillStiffnessType_2D = parsingDataList.get(1);
					if(this.MillStiffnessType_2D.equals("Rigid")){
						// Rigid Type
						med.getBtnRadioRigid_MS_2D().setSelection(true);
						med.getBtnRadioSpring_MS_2D().setSelection(false);
						med.getTextMillStiffness_2D().setEnabled(false);
					}else {
						// Spring Type
						med.getBtnRadioRigid_MS_2D().setSelection(false);
						med.getBtnRadioSpring_MS_2D().setSelection(true);
						med.getTextMillStiffness_2D().setEnabled(true);
					}
				}else {
					this.MillStiffnessType_2D = "Spring";
					med.getBtnRadioRigid_MS_2D().setSelection(false);
					med.getBtnRadioSpring_MS_2D().setSelection(true);
					med.getTextMillStiffness_2D().setEnabled(true);
				}
			}
			// version3 update 
			else if(parsingDataList.get(0).equals(TextLabel.lblUpperEntryRollGapMovement_2D)){
				if(parsingDataList.size() == 2){
					this.textUpperEntryRollGapMovement_2D = parsingDataList.get(1);
					med.getTextUpperEntryRollGapMovement_2D().setText(this.textUpperEntryRollGapMovement_2D);
				}else  {
					this.textUpperEntryRollGapMovement_2D = "No value";
					med.getTextUpperEntryRollGapMovement_2D().setText(this.textUpperEntryRollGapMovement_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblUpperExitRollGapMovement_2D)){
				if(parsingDataList.size() == 2){
					this.textUpperExitRollGapMovement_2D = parsingDataList.get(1);
					med.getTextUpperExitRollGapMovement_2D().setText(this.textUpperExitRollGapMovement_2D);
				}else {
					this.textUpperExitRollGapMovement_2D = "No value";
					med.getTextUpperExitRollGapMovement_2D().setText(this.textUpperExitRollGapMovement_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblUpperRollGapStayingTime_2D)){
				if(parsingDataList.size() == 2){
					this.textUpperRollGapStayingTime_2D = parsingDataList.get(1);
					med.getTextUpperRollGapStayingTime_2D().setText(this.textUpperRollGapStayingTime_2D);
				}else {
					this.textUpperRollGapStayingTime_2D = "No value";
					med.getTextUpperRollGapStayingTime_2D().setText(this.textUpperRollGapStayingTime_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblUpperRollGapMovingTime_2D)){
				if(parsingDataList.size() == 2){
					this.textUpperRollGapMovingTime_2D = parsingDataList.get(1);
					med.getTextUpperRollGapMovingTime_2D().setText(this.textUpperRollGapMovingTime_2D);
				}else {
					this.textUpperRollGapMovingTime_2D = "No value";
					med.getTextUpperRollGapMovingTime_2D().setText(this.textUpperRollGapMovingTime_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblLowerEntryRollGapMovement_2D)){
				if(parsingDataList.size() == 2){
					this.textLowerEntryRollGapMovement_2D = parsingDataList.get(1);
					med.getTextLowerEntryRollGapMovement_2D().setText(this.textLowerEntryRollGapMovement_2D);
				}else {
					this.textLowerEntryRollGapMovement_2D = "No value";
					med.getTextLowerEntryRollGapMovement_2D().setText(this.textLowerEntryRollGapMovement_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblLowerExitRollGapMovement_2D)){
				if(parsingDataList.size() == 2){
					this.textLowerExitRollGapMovement_2D = parsingDataList.get(1);
					med.getTextLowerExitRollGapMovement_2D().setText(this.textLowerExitRollGapMovement_2D);
				}else {
					this.textLowerExitRollGapMovement_2D = "No value";
					med.getTextLowerExitRollGapMovement_2D().setText(this.textLowerExitRollGapMovement_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblLowerRollGapStayingTime_2D)){
				if(parsingDataList.size() == 2){
					this.textLowerRollGapStayingTime_2D = parsingDataList.get(1);
					med.getTextLowerRollGapStayingTime_2D().setText(this.textLowerRollGapStayingTime_2D);
				}else {
					this.textLowerRollGapStayingTime_2D = "No value";
					med.getTextLowerRollGapStayingTime_2D().setText(this.textLowerRollGapStayingTime_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblLowerRollGapMovingTime_2D)){
				if(parsingDataList.size() == 2){
					this.textLowerRollGapMovingTime_2D = parsingDataList.get(1);
					med.getTextLowerRollGapMovingTime_2D().setText(this.textLowerRollGapMovingTime_2D);
				}else {
					this.textLowerRollGapMovingTime_2D = "No value";
					med.getTextLowerRollGapMovingTime_2D().setText(this.textLowerRollGapMovingTime_2D);
				}
			}
			else if(parsingDataList.get(0).equals(TextLabel.lblFrontHDRollDia_2D)){
				if(parsingDataList.size() == 2){
					this.textFrontHDRollDia_2D = parsingDataList.get(1);
					med.getTextFrontHDRollDia_2D().setText(this.textFrontHDRollDia_2D);
				}else{
					this.textFrontHDRollDia_2D = "No value";
					med.getTextFrontHDRollDia_2D().setText(this.textFrontHDRollDia_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblFrontHDRollPitch_2D)){
				if(parsingDataList.size() == 2){
					this.textFrontHDRollPitch_2D = parsingDataList.get(1);
					med.getTextFrontHDRollPitch_2D().setText(this.textFrontHDRollPitch_2D);
				}else{
					this.textFrontHDRollPitch_2D = "No value";
					med.getTextFrontHDRollPitch_2D().setText(this.textFrontHDRollPitch_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblFrontHDRollVericalPos_2D)){
				if(parsingDataList.size() == 2){
					this.textFrontHDRollVericalPos_2D = parsingDataList.get(1);
					med.getTextFrontHDRollVericalPos_2D().setText(this.textFrontHDRollVericalPos_2D);
				}else{
					this.textFrontHDRollVericalPos_2D = "No value";
					med.getTextFrontHDRollVericalPos_2D().setText(this.textFrontHDRollVericalPos_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblRearHDRollDia_2D)){
				if(parsingDataList.size() == 2){
					this.textRearHDRollDia_2D = parsingDataList.get(1);
					med.getTextRearHDRollDia_2D().setText(this.textRearHDRollDia_2D);
				}else{
					this.textRearHDRollDia_2D = "No value";
					med.getTextRearHDRollDia_2D().setText(this.textRearHDRollDia_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblRearHDRollPitch_2D)){
				if(parsingDataList.size() == 2){
					this.textRearHDRollPitch_2D = parsingDataList.get(1);
					med.getTextRearHDRollPitch_2D().setText(this.textRearHDRollPitch_2D);
				}else{
					this.textRearHDRollPitch_2D = "No value";
					med.getTextRearHDRollPitch_2D().setText(this.textRearHDRollPitch_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblRearHDRollVerticalPos_2D)){
				if(parsingDataList.size() == 2){
					this.textRearHDRollVerticalPos_2D = parsingDataList.get(1);
					med.getTextRearHDRollVerticalPos_2D().setText(this.textRearHDRollVerticalPos_2D);
				}else{
					this.textRearHDRollVerticalPos_2D = "No value";
					med.getTextRearHDRollVerticalPos_2D().setText(this.textRearHDRollVerticalPos_2D);
				}
			}
			
			else if(parsingDataList.get(0).equals(TextLabel.HDRollType_2D)){
				if(parsingDataList.size() == 2){
					this.HDRollType_2D = parsingDataList.get(1);
					if(this.HDRollType_2D.equals("None")){
						med.getBtnUpper_2D().setSelection(false);
						med.getBtnLower_2D().setSelection(false);
						med.getBtnNone_2D().setSelection(true);
					}else if(this.HDRollType_2D.equals("Lower")){
						med.getBtnUpper_2D().setSelection(false);
						med.getBtnNone_2D().setSelection(false);
						med.getBtnLower_2D().setSelection(true);
					}else if(this.HDRollType_2D.equals("Upper")){
						med.getBtnNone_2D().setSelection(false);
						med.getBtnLower_2D().setSelection(false);
						med.getBtnUpper_2D().setSelection(true);
					}
				}else {
					this.HDRollType_2D = "Upper";
					med.getBtnNone_2D().setSelection(false);
					med.getBtnLower_2D().setSelection(false);
					med.getBtnUpper_2D().setSelection(true);
				}
			}

			else if(parsingDataList.get(0).contains("UpperRoll_")){
				if(parsingDataList.size() == 2){
					ArrayList<String> valueList = ParserDefault.splitLineData(parsingDataList.get(1),"\\*");
					UpTableDataContent UTDCObj = new UpTableDataContent();
					UTDCObj.setNo(valueList.get(0));
					UTDCObj.setGap(valueList.get(1));
					UTDCObj.setFriction(valueList.get(2));
					UTDCObj.setDiameter(valueList.get(3));
					UTDCObj.setPitch(valueList.get(4));
					upTableDataList_2D.add(UTDCObj);	
				}else {
					upTableDataList_2D = new ArrayList<UpTableDataContent>();
					
				}
				
			}else if(parsingDataList.get(0).contains("LowerRoll_")){
				if(parsingDataList.size() == 2){
					ArrayList<String> valueList = ParserDefault.splitLineData(parsingDataList.get(1),"\\*");
					DownTableDataContent DTDCObj = new DownTableDataContent();
					DTDCObj.setNo(valueList.get(0));
					DTDCObj.setGap(valueList.get(1));
					DTDCObj.setFriction(valueList.get(2));
					DTDCObj.setDiameter(valueList.get(3));
					DTDCObj.setPitch(valueList.get(4));
					downTableDataList_2D.add(DTDCObj);
				}else {
					downTableDataList_2D = new ArrayList<DownTableDataContent>();
				}
			
			}

			else if(parsingDataList.get(0).equals(TextLabel.lblYoungsModulus_2D)){
				if(parsingDataList.size() == 2){
					this.textYoungsModulus_2D = parsingDataList.get(1);
					med.getTextYoungsModulus_2D().setText(this.textYoungsModulus_2D);
				}else {
					this.textYoungsModulus_2D = "No value";
					med.getTextYoungsModulus_2D().setText(this.textYoungsModulus_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblFlowStress_2D)){
				if(parsingDataList.size() == 2){
					this.textFlowStress_2D = parsingDataList.get(1);
					med.getTextFlowStress_2D().setText(this.textFlowStress_2D);
				}else { 
					this.textFlowStress_2D = "No value";
					med.getTextFlowStress_2D().setText(this.textFlowStress_2D);
				}
			}
			//update version2 2016.01.27
			else if(parsingDataList.get(0).equals(TextLabel.lblYieldStrength_2D)){
				if(parsingDataList.size() == 2){
					this.textYieldStrength_2D = parsingDataList.get(1);
					med.getTextYieldStrength_2D().setText(this.textYieldStrength_2D);
				}else {
					this.textYieldStrength_2D = "No value";
					med.getTextYieldStrength_2D().setText(this.textYieldStrength_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblTensileStrength_2D)){
				if(parsingDataList.size() == 2){
					this.textTensileStrength_2D = parsingDataList.get(1);
					med.getTextTensileStrength_2D().setText(this.textTensileStrength_2D);
				}else {
					this.textTensileStrength_2D = "No value";
					med.getTextTensileStrength_2D().setText(this.textTensileStrength_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblElongation_2D)){
				if(parsingDataList.size() == 2){
					this.textElongation_2D = parsingDataList.get(1);
					med.getTextElongation_2D().setText(this.textElongation_2D);
				}else {
					this.textElongation_2D = "No value";
					med.getTextElongation_2D().setText(this.textElongation_2D);
				}
			}
			
			
			else if(parsingDataList.get(0).equals(TextLabel.lblThermalExpansionCoefficient_2D)){
				if(parsingDataList.size() == 2){
					this.textThermalExpansionCoefficient_2D = parsingDataList.get(1);
					med.getTextThermalExpansionCoefficient_2D().setText(this.textThermalExpansionCoefficient_2D);
				}else {
					this.textThermalExpansionCoefficient_2D = "No value";
					med.getTextThermalExpansionCoefficient_2D().setText(this.textThermalExpansionCoefficient_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblMassDensity_2D)){
				if(parsingDataList.size() == 2){
					this.textMassDensity_2D = parsingDataList.get(1);
					med.getTextMassDensity_2D().setText(this.textMassDensity_2D);
				}else {
					this.textMassDensity_2D = "No value";
					med.getTextMassDensity_2D().setText(this.textMassDensity_2D);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblYoungsModulus_2D+"Type")){
				if(parsingDataList.size() == 2){
					this.YoungsModulusType_2D = parsingDataList.get(1);
					if(this.YoungsModulusType_2D.equals("Constant")){
						med.getBtnRadioConstant_YM_2D().setSelection(true);
						med.getBtnRadioTable_YM_2D().setSelection(false);
						med.getBtnExplorerYoungsModulus_2D().setEnabled(false);
					}else {
						med.getBtnRadioConstant_YM_2D().setSelection(false);
						med.getBtnRadioTable_YM_2D().setSelection(true);
						med.getBtnExplorerYoungsModulus_2D().setEnabled(true);
					}
				}else {
					this.YoungsModulusType_2D = "Table";
					med.getBtnRadioConstant_YM_2D().setSelection(false);
					med.getBtnRadioTable_YM_2D().setSelection(true);
					med.getBtnExplorerYoungsModulus_2D().setEnabled(true);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblFlowStress_2D+"Type")){
				if(parsingDataList.size() == 2){
					this.FlowStressType_2D = parsingDataList.get(1);
					if(this.FlowStressType_2D.equals("Constant")){
						med.getBtnRadioConstant_FS_2D().setSelection(true);
						med.getBtnRadioTable_FS_2D().setSelection(false);
						med.getBtnExplorerFlowStress_2D().setEnabled(false);
						//update version2 2016.01.27
						med.getTextFlowStress_2D().setEnabled(false);
						med.getTextYieldStrength_2D().setEnabled(true);
						med.getTextTensileStrength_2D().setEnabled(true);
						med.getTextElongation_2D().setEnabled(true);
						
					}else {
						med.getBtnRadioConstant_FS_2D().setSelection(false);
						med.getBtnRadioTable_FS_2D().setSelection(true);
						med.getBtnExplorerFlowStress_2D().setEnabled(true);
						//update version2 2016.01.27
						med.getTextFlowStress_2D().setEnabled(true);
						med.getTextYieldStrength_2D().setEnabled(false);
						med.getTextTensileStrength_2D().setEnabled(false);
						med.getTextElongation_2D().setEnabled(false);
						
					}
				}else {
					this.FlowStressType_2D = "Table";
					med.getBtnRadioConstant_FS_2D().setSelection(false);
					med.getBtnRadioTable_FS_2D().setSelection(true);
					med.getBtnExplorerFlowStress_2D().setEnabled(true);
					//update version2 2016.01.27
					med.getTextFlowStress_2D().setEnabled(true);
					med.getTextYieldStrength_2D().setEnabled(false);
					med.getTextTensileStrength_2D().setEnabled(false);
					med.getTextElongation_2D().setEnabled(false);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblThermalExpansionCoefficient_2D+"Type")){
				if(parsingDataList.size() == 2){
					this.ThermalExpansionCoefficientType_2D = parsingDataList.get(1);
					if(this.ThermalExpansionCoefficientType_2D.equals("Constant")){
						med.getBtnRadioConstant_TEC_2D().setSelection(true);
						med.getBtnRadioTable_TEC_2D().setSelection(false);
						med.getBtnExplorerThermalExpansionCoefficient_2D().setEnabled(false);
					}else {
						med.getBtnRadioConstant_TEC_2D().setSelection(false);
						med.getBtnRadioTable_TEC_2D().setSelection(true);
						med.getBtnExplorerThermalExpansionCoefficient_2D().setEnabled(true);
					}
				}else {
					this.ThermalExpansionCoefficientType_2D = "Table";
					med.getBtnRadioConstant_TEC_2D().setSelection(false);
					med.getBtnRadioTable_TEC_2D().setSelection(true);
					med.getBtnExplorerThermalExpansionCoefficient_2D().setEnabled(true);
				}
			}
			else if(parsingDataList.get(0).equals(TextLabel.lblPoissonsRatio_2D+"Type")){
				if(parsingDataList.size() == 2){
					this.PoissonsRatioType_2D= parsingDataList.get(1);
					if(this.PoissonsRatioType_2D.equals("Constant")){
						med.getBtnRadioConstant_PR_2D().setSelection(true);
						med.getBtnRadioTable_PR_2D().setSelection(false);
						med.getBtnExplorerPoissonsRatio_2D().setEnabled(false);
					}else {
						med.getBtnRadioConstant_PR_2D().setSelection(false);
						med.getBtnRadioTable_PR_2D().setSelection(true);
						med.getBtnExplorerPoissonsRatio_2D().setEnabled(true);
					}
				}else {
					this.PoissonsRatioType_2D = "Table";
					med.getBtnRadioConstant_PR_2D().setSelection(false);
					med.getBtnRadioTable_PR_2D().setSelection(true);
					med.getBtnExplorerPoissonsRatio_2D().setEnabled(true);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblPoissonsRatio_2D)){
				if(parsingDataList.size() == 2){
					this.textPoissonsRatio_2D = parsingDataList.get(1);
					med.getTextPoissonsRatio_2D().setText(this.textPoissonsRatio_2D);
				}else {
					this.textPoissonsRatio_2D = "No value";
					med.getTextPoissonsRatio_2D().setText(this.textPoissonsRatio_2D);
				}
			}

			else if(parsingDataList.get(0).equals(TextLabel.lblSolvingTime_2D)){
				if(parsingDataList.size() == 2){
					this.textSolvingTime_2D = parsingDataList.get(1);
					med.getTextSolvingTime_2D().setText(this.textSolvingTime_2D);	
				}else {
					this.textSolvingTime_2D = "No value";
					med.getTextSolvingTime_2D().setText(this.textSolvingTime_2D);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblIncrementTime_2D)){
				if(parsingDataList.size() == 2){
					this.textIncrementTime_2D = parsingDataList.get(1);
					med.getTextIncrementTime_2D().setText(this.textIncrementTime_2D);	
				}else {
					this.textIncrementTime_2D = "No value";
					med.getTextIncrementTime_2D().setText(this.textIncrementTime_2D);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblParallelDDM_2D)){
				if(parsingDataList.size() == 2){
					if(parsingDataList.get(1).equals("true")){
						this.btnParallelDDMUse_2D = true;
						med.getBtnParallelDDMUse_2D().setSelection(true);
						med.getSpinnerDomain_2D().setEnabled(true);
					}else{
						this.btnParallelDDMUse_2D = false;
						med.getBtnParallelDDMUse_2D().setSelection(false);
						med.getSpinnerDomain_2D().setEnabled(false);
					}
				}else {
					this.btnParallelDDMUse_2D = false;
					med.getBtnParallelDDMUse_2D().setSelection(false);
					med.getSpinnerDomain_2D().setEnabled(false);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblDomain_2D)){
				if(parsingDataList.size() == 2){
					this.spinnerDomain_2D = parsingDataList.get(1);
					med.getSpinnerDomain_2D().setSelection(Integer.parseInt(this.spinnerDomain_2D));	
				}else {
					this.spinnerDomain_2D = "No value";
					med.getSpinnerDomain_2D().setSelection(-1);
				}
				
			}else if(parsingDataList.get(0).equals(TextLabel.lblParallelMultiThread_2D)){
				if(parsingDataList.size() == 2){
					if(parsingDataList.get(1).equals("true")){
						this.btnParallelMultiThreadUse_2D = true;
						med.getBtnParallelMultiThreadUse_2D().setSelection(true);
						med.getSpinnerThread_2D().setEnabled(true);
					}else{
						this.btnParallelMultiThreadUse_2D = false;
						med.getBtnParallelMultiThreadUse_2D().setSelection(false);
						med.getSpinnerThread_2D().setEnabled(false);
					}
				}else {
					this.btnParallelMultiThreadUse_2D = false;
					med.getBtnParallelMultiThreadUse_2D().setSelection(false);
					med.getSpinnerThread_2D().setEnabled(false);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblThread_2D)){
				if(parsingDataList.size() == 2){
					this.spinnerThread_2D = parsingDataList.get(1);
					med.getSpinnerThread_2D().setSelection(Integer.parseInt(this.spinnerThread_2D));
				}else {
					this.spinnerThread_2D = "No value";
					med.getSpinnerThread_2D().setSelection(-1);
				}
			}
		}
		//version3 update
		//updateTableData();
	}
	
	public void SaveLeveller(){
		try{
			saveAllData();
			
			String dbPath = myUtil.setPath(this.workspace,this.textModelName+".lvdb");
			
			if(this.workspace != null){
				Writer writer = new Writer(dbPath,db);
				writer.running();
				MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), "SUCCESS - Save Leveller Data \n path : "+dbPath);
				messageDlg.open();
			}
			
			
		}catch(Exception e){
			String msg = "ERROR - Save Leveller Data";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
	}
	
	public void SaveLeveller_2D(){
		try{
			saveAllData_2D();
			
			String dbPath = myUtil.setPath(this.workspace,this.textModelName+".lvdb");
			
			if(this.workspace != null){
				Writer writer = new Writer(dbPath,db_2D);
				writer.running();
				MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), "SUCCESS - Save Leveller Data_2D \n path : "+dbPath);
				messageDlg.open();
			}
			
			
		}catch(Exception e){
			String msg = "ERROR - Save Leveller Data_2D";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
	}
	
	public void SaveAsLeveller_2D(String newPath,String newModelName){
		try{
			db_saveAs_2D = new ArrayList<String>();
			
			
			String path = newPath;
			this.textModelName = newModelName;
			
			String topFolder =  myUtil.setPath(path, textModelName);
			String procFolder = myUtil.setPath(topFolder, "proc");
			String resultFolder = myUtil.setPath(topFolder, "result");
			String newDBFilePath = myUtil.setPath(topFolder, this.textModelName+".lvdb");
			this.workspace = topFolder;
			med.getlblworkspacePath().setText("Workspace : "+this.workspace);
			myUtil.makeDir(topFolder);
			myUtil.makeDir(procFolder);
			myUtil.makeDir(resultFolder);
			
			saveAsAllData_2D(newModelName);
			
			if(newDBFilePath != null){
				Writer writer = new Writer(newDBFilePath,this.db_saveAs_2D);
				writer.running();
				MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), "SUCCESS - Save as Leveller Data_2D \n path : "+newDBFilePath);
				messageDlg.open();
			}
			
			this.clearAllData_2D();			
			setAllDataUI_2D(newDBFilePath);

			AllComponentEnable();
				
		}catch(Exception e){
			//e.printStackTrace();
			String msg = "ERROR - Save as Leveller Data";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		
	}
	
	public void SaveAsLeveller(String newPath,String newModelName){
		try{
			db_saveAs = new ArrayList<String>();
			
			
			String path = newPath;
			this.textModelName = newModelName;
			
			String topFolder =  myUtil.setPath(path, textModelName);
			String procFolder = myUtil.setPath(topFolder, "proc");
			String resultFolder = myUtil.setPath(topFolder, "result");
			String newDBFilePath = myUtil.setPath(topFolder, this.textModelName+".lvdb");
			this.workspace = topFolder;
			med.getlblworkspacePath().setText("Workspace : "+this.workspace);
			myUtil.makeDir(topFolder);
			myUtil.makeDir(procFolder);
			myUtil.makeDir(resultFolder);
			
			saveAsAllData(newModelName);
			
			if(newDBFilePath != null){
				Writer writer = new Writer(newDBFilePath,this.db_saveAs);
				writer.running();
				MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), "SUCCESS - Save as Leveller Data \n path : "+newDBFilePath);
				messageDlg.open();
			}
			
			this.clearAllData();			
			setAllDataUI(newDBFilePath);

			AllComponentEnable();
				
		}catch(Exception e){
			String msg = "ERROR - Save as Leveller Data";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		
	}
	
	public void saveAllData(){
		//this.db.clear();
		this.db.add("LevellerType="+this.LevellerType);
		this.db.add(TextLabel.lblModelName+"="+this.textModelName);
		//--------------------------------------------
		this.comboType = med.getComboType().getText().trim();
		this.textWidth = med.getTextWidth().getText().trim();
		this.textLength = med.getTextLength().getText().trim();
		this.textThickness = med.getTextThickness().getText().trim();

		this.db.add(TextLabel.lblType+"="+this.comboType);
		this.db.add(TextLabel.lblWidth+"="+this.textWidth);
		this.db.add(TextLabel.lblLength+"="+this.textLength);
		this.db.add(TextLabel.lblThickness+"="+this.textThickness);
		
		if(this.comboType.equals(ComboLabel.TYPE1)){
			
		}else if(this.comboType.equals(ComboLabel.TYPE2)){
			this.type2_textLeftEdgeWavePitch = med.getType2_textLeftEdgeWavePitch().getText().trim();
			this.type2_textRightEdgeWavePitch = med.getType2_textRightEdgeWavePitch().getText().trim();
			this.type2_textLeftEdgeWaveHeight = med.getType2_textLeftEdgeWaveHeight().getText().trim();
			this.type2_textRightEdgeWaveHeight = med.getType2_textRightEdgeWaveHeight().getText().trim();
			this.type2_textLeftEdgeWavePhase = med.getType2_textLeftEdgeWavePhase().getText().trim();
			this.type2_textRightEdgeWavePhase = med.getType2_textRightEdgeWavePhase().getText().trim();
			
			this.db.add(TextLabel.lblLeftEdgeWavePitch_type2+"="+this.type2_textLeftEdgeWavePitch);
			this.db.add(TextLabel.lblRightEdgeWavePitch_type2+"="+this.type2_textRightEdgeWavePitch);
			this.db.add(TextLabel.lblLeftEdgeWaveHeight_type2+"="+this.type2_textLeftEdgeWaveHeight);
			this.db.add(TextLabel.lblRightEdgeWaveHeight_type2+"="+this.type2_textRightEdgeWaveHeight);
			this.db.add(TextLabel.lblLeftEdgeWavePhase_type2+"="+this.type2_textLeftEdgeWavePhase);
			this.db.add(TextLabel.lblRightEdgeWavePhase_type2+"="+this.type2_textRightEdgeWavePhase);
		}else if(this.comboType.equals(ComboLabel.TYPE3)){
			this.type3_textWavePitch = med.getType3_textWavePitch().getText().trim();
			this.type3_textWaveHeight = med.getType3_textWaveHeight().getText().trim();
			
			this.db.add(TextLabel.lblWavePitch_type3+"="+this.type3_textWavePitch);
			this.db.add(TextLabel.lblWaveHeight_type3+"="+this.type3_textWaveHeight);
		}else if(this.comboType.equals(ComboLabel.TYPE4)){
			this.type4_textGutterHeight = med.getType4_textGutterHeight().getText().trim();
			
			this.db.add(TextLabel.lblGutterHeight_type4+"="+this.type4_textGutterHeight);
		}else if(this.comboType.equals(ComboLabel.TYPE5)){
			this.type5_textGutterHeight = med.getType5_textGutterHeight().getText().trim();
			this.type5_textGutterLength = med.getType5_textGutterLength().getText().trim();
			
			this.db.add(TextLabel.lblGutterHeight_type5+"="+this.type5_textGutterHeight);
			this.db.add(TextLabel.lblGutterLength_type5+"="+this.type5_textGutterLength);
			
		}else if(this.comboType.equals(ComboLabel.TYPE6)){
			this.type6_textHeadGutterHeight = med.getType6_textHeadGutterHeight().getText().trim();
			this.type6_textHeadGutterLength = med.getType6_textHeadGutterLength().getText().trim();
			this.type6_textTailGutterHeight = med.getType6_textTailGutterHeight().getText().trim();
			this.type6_textTailGutterLength = med.getType6_textTailGutterLength().getText().trim();
			
			this.db.add(TextLabel.lblHeadGutterHeight_type6+"="+this.type6_textHeadGutterHeight);
			this.db.add(TextLabel.lblHeadGutterLength_type6+"="+this.type6_textHeadGutterLength);
			this.db.add(TextLabel.lblTailGutterHeight_type6+"="+this.type6_textTailGutterHeight);
			this.db.add(TextLabel.lblTailGutterLength_type6+"="+this.type6_textTailGutterLength);
			
		}else if(this.comboType.equals(ComboLabel.TYPE7)){
			this.type7_textHeadGutterHeight = med.getType7_textHeadGutterHeight().getText().trim();
			this.type7_textGutterLength = med.getType7_textGutterLength().getText().trim();
			this.type7_textGutterLengthLength = med.getType7_textGutterLengthLength().getText().trim();
			this.type7_textGutterWidthLength = med.getType7_textGutterWidthLength().getText().trim();
			
			this.db.add(TextLabel.lblHeadGutterHeight_type7+"="+this.type7_textHeadGutterHeight);
			this.db.add(TextLabel.lblGutterLength_type7+"="+this.type7_textGutterLength);
			this.db.add(TextLabel.lblGutterLengthLength_type7+"="+this.type7_textGutterLengthLength);
			this.db.add(TextLabel.lblGutterWidthLength_type7+"="+this.type7_textGutterWidthLength);
		}
		//--------------------------------------------		
		this.textThicknessElementNum = med.getTextThicknessElementNum().getText().trim();
		this.textWidthAspectRatio = med.getTextWidthAspectRatio().getText().trim();
		this.textLengthAspectRatio = med.getTextLengthAspectRatio().getText().trim();
		CalcElementNumber();
		// version3 update
		//CreateRoll();
		this.textElementNumber = med.getTextElementNumber().getText().trim();
		
		this.db.add(TextLabel.lblThicknessElementNum+"="+this.textThicknessElementNum);
		this.db.add(TextLabel.lblWidthAspectRatio+"="+this.textWidthAspectRatio);
		this.db.add(TextLabel.lblLengthAspectRatio+"="+this.textLengthAspectRatio);
		this.db.add(TextLabel.lblElementNumber+"="+this.textElementNumber);
		//--------------------------------------------
		this.textPlateVelocity = med.getTextPlateVelocity().getText().trim();
		this.textTemperatureStart = med.getTextTemperatureStart().getText().trim();
		this.textTemperatureEnd = med.getTextTemperatureEnd().getText().trim();
		//update version2 2016.01.27
		this.textPassLineOffset = med.getTextPassLineOffset().getText().trim();
		
		
		this.db.add(TextLabel.lblPlateVelocity+"="+this.textPlateVelocity);
		this.db.add(TextLabel.lblTemperatureStart+"="+this.textTemperatureStart);
		this.db.add(TextLabel.lblTemperatureEnd+"="+this.textTemperatureEnd);
		//update version2 2016.01.27
		this.db.add(TextLabel.lblPassLineOffset+"="+this.textPassLineOffset);
		//--------------------------------------------		
		this.spinnerUpperRollNum = med.getSpinnerUpperRollNum().getText().trim();
		this.spinnerLowerRollNum = med.getSpinnerLowerRollNum().getText().trim();
		this.textRollPitch = med.getTextRollPitch().getText().trim();
		this.textRollLength = calcRollLength().trim();
		this.textEntryUpperRollGap = med.getTextEntryUpperRollGap().getText().trim();
		this.textEntryLowerRollGap = med.getTextEntryLowerRollGap().getText().trim();
		this.textExitUpperRollGap = med.getTextExitUpperRollGap().getText().trim();
		this.textExitLowerRollGap = med.getTextExitLowerRollGap().getText().trim();
		this.textRollFriction = med.getTextRollFriction().getText().trim();
		this.textRollDiameter = med.getTextRollDiameter().getText().trim();
		//update version2 2016.01.27
		/*
		if(med.getBtnRadioNone_RC().getSelection()){
			this.RollCrownType = med.getBtnRadioNone_RC().getText().trim();
		}else{
			this.RollCrownType = med.getBtnRadioApply_RC().getText().trim();
		}
		// */
		this.textUpperRollCrown = med.getTextUpperRollCrown().getText().trim();
		this.textLowerRollCrown = med.getTextLowerRollCrown().getText().trim();
		
		if(med.getBtnRadioRigid_MS().getSelection()){
			this.MillStiffnessType = med.getBtnRadioRigid_MS().getText().trim();
		}else{
			this.MillStiffnessType = med.getBtnRadioSpring_MS().getText().trim();
		}
		this.textMillStiffness = med.getTextMillStiffness().getText().trim();
		
		
		med.getTextRollLength().setText(this.textRollLength);
		
		this.db.add(TextLabel.lblUpperRollNumber+"="+this.spinnerUpperRollNum);
		this.db.add(TextLabel.lblLowerRollNumber+"="+this.spinnerLowerRollNum);
		this.db.add(TextLabel.lblRollPitch+"="+this.textRollPitch);
		this.db.add(TextLabel.lblRollLength+"="+this.textRollLength);
		this.db.add(TextLabel.lblEntryUpperRollGap+"="+this.textEntryUpperRollGap);
		this.db.add(TextLabel.lblEntryLowerRollGap+"="+this.textEntryLowerRollGap);
		this.db.add(TextLabel.lblExitUpperRollGap+"="+this.textExitUpperRollGap);
		this.db.add(TextLabel.lblExitLowerRollGap+"="+this.textExitLowerRollGap);
		this.db.add(TextLabel.lblRollFriction+"="+this.textRollFriction);
		this.db.add(TextLabel.lblRollDiameter+"="+this.textRollDiameter);
		//update version2 2016.01.27
		//this.db.add(TextLabel.lblRollCrownType+"="+this.RollCrownType);
		this.db.add(TextLabel.lblUpperRollCrown+"="+this.textUpperRollCrown);
		this.db.add(TextLabel.lblLowerRollCrown+"="+this.textLowerRollCrown);
		this.db.add(TextLabel.lblMillStiffnessType+"="+this.MillStiffnessType);
		this.db.add(TextLabel.lblMillStiffness+"="+this.textMillStiffness);
		//update version3 
		this.textUpperEntryRollGapMovement = med.getTextUpperEntryRollGapMovement().getText().trim();
		this.textUpperExitRollGapMovement = med.getTextUpperExitRollGapMovement().getText().trim();
		this.textUpperRollGapStayingTime = med.getTextUpperRollGapStayingTime().getText().trim();
		this.textUpperRollGapMovingTime = med.getTextUpperRollGapMovingTime().getText().trim();
		this.textLowerEntryRollGapMovement = med.getTextLowerEntryRollGapMovement().getText().trim();
		this.textLowerExitRollGapMovement = med.getTextLowerExitRollGapMovement().getText().trim();
		this.textLowerRollGapStayingTime = med.getTextLowerRollGapStayingTime().getText().trim();
		this.textLowerRollGapMovingTime = med.getTextLowerRollGapMovingTime().getText().trim();
		
		this.db.add(TextLabel.lblUpperEntryRollGapMovement+"="+this.textUpperEntryRollGapMovement);
		this.db.add(TextLabel.lblUpperExitRollGapMovement+"="+this.textUpperExitRollGapMovement);
		this.db.add(TextLabel.lblUpperRollGapStayingTime+"="+this.textUpperRollGapStayingTime);
		this.db.add(TextLabel.lblUpperRollGapMovingTime+"="+this.textUpperRollGapMovingTime);
		this.db.add(TextLabel.lblLowerEntryRollGapMovement+"="+this.textLowerEntryRollGapMovement);
		this.db.add(TextLabel.lblLowerExitRollGapMovement+"="+this.textLowerExitRollGapMovement);
		this.db.add(TextLabel.lblLowerRollGapStayingTime+"="+this.textLowerRollGapStayingTime);
		this.db.add(TextLabel.lblLowerRollGapMovingTime+"="+this.textLowerRollGapMovingTime);
		
		if(med.getBtnNone().getSelection()){
			this.HDRollType = med.getBtnNone().getText();
		}
		if(med.getBtnUpper().getSelection()){
			this.HDRollType = med.getBtnUpper().getText();
		}
		if(med.getBtnLower().getSelection()){
			this.HDRollType = med.getBtnLower().getText();
		}
		this.textFrontHDRollDia = med.getTextFrontHDRollDia().getText().trim();
		this.textFrontHDRollPitch = med.getTextFrontHDRollPitch().getText().trim();
		this.textFrontHDRollVericalPos = med.getTextFrontHDRollVericalPos().getText().trim();
		this.textRearHDRollDia = med.getTextRearHDRollDia().getText().trim();
		this.textRearHDRollPitch = med.getTextRearHDRollPitch().getText().trim();
		this.textRearHDRollVerticalPos = med.getTextRearHDRollVerticalPos().getText().trim();
		
		this.db.add(TextLabel.lblFrontHDRollDia+"="+this.textFrontHDRollDia);
		this.db.add(TextLabel.lblFrontHDRollPitch+"="+this.textFrontHDRollPitch);
		this.db.add(TextLabel.lblFrontHDRollVericalPos+"="+this.textFrontHDRollVericalPos);
		this.db.add(TextLabel.lblRearHDRollDia+"="+this.textRearHDRollDia);
		this.db.add(TextLabel.lblRearHDRollPitch+"="+this.textRearHDRollPitch);
		this.db.add(TextLabel.lblRearHDRollVerticalPos+"="+this.textRearHDRollVerticalPos);
		this.db.add(TextLabel.HDRollType+"="+this.HDRollType);
		
		try{
			for(UpTableDataContent obj : this.upTableDataList){
				this.db.add(obj.getNo()+"="+obj.getDB());
			}
		}catch (Exception e){
			//System.out.println("[LevellerMain.saveAllData()] Table Data is empty.");
		}
		try{
			for(DownTableDataContent obj : this.downTableDataList){
				this.db.add(obj.getNo()+"="+obj.getDB());
			}
		}catch (Exception e){
			//System.out.println("[LevellerMain.saveAllData()] Table Data is empty.");
		}
		
		//--------------------------------------------
		
		if(med.getBtnRadioConstant_YM().getSelection()){
			this.YoungsModulusType = med.getBtnRadioConstant_YM().getText().trim();
		}else{
			this.YoungsModulusType = med.getBtnRadioTable_YM().getText().trim();
		}
		this.textYoungsModulus = med.getTextYoungsModulus().getText().trim();
		this.db.add(TextLabel.lblYoungsModulus+"Type"+"="+this.YoungsModulusType);
		this.db.add(TextLabel.lblYoungsModulus+"="+this.textYoungsModulus);
		
		
		if(med.getBtnRadioConstant_FS().getSelection()){
			this.FlowStressType = med.getBtnRadioConstant_FS().getText().trim();
			//update version2 2017.01.27
			this.textYieldStrength = med.getTextYieldStrength().getText().trim();
			this.textTensileStrength = med.getTextTensileStrength().getText().trim();
			this.textElongation = med.getTextElongation().getText().trim();
			
			this.db.add(TextLabel.lblFlowStress+"Type"+"="+this.FlowStressType);
			this.db.add(TextLabel.lblYieldStrength+"="+this.textYieldStrength);
			this.db.add(TextLabel.lblTensileStrength+"="+this.textTensileStrength);
			this.db.add(TextLabel.lblElongation+"="+this.textElongation);
			
		}else{
			this.FlowStressType = med.getBtnRadioTable_FS().getText().trim();
			//update version2 2017.01.27
			this.textFlowStress = med.getTextFlowStress().getText().trim();
			this.db.add(TextLabel.lblFlowStress+"Type"+"="+this.FlowStressType);
			this.db.add(TextLabel.lblFlowStress+"="+this.textFlowStress);
		}
		
		if(med.getBtnRadioConstant_TEC().getSelection()){
			this.ThermalExpansionCoefficientType = med.getBtnRadioConstant_TEC().getText().trim();
		}else{
			this.ThermalExpansionCoefficientType = med.getBtnRadioTable_TEC().getText().trim();
		}
		this.textThermalExpansionCoefficient = med.getTextThermalExpansionCoefficient().getText().trim();
		if(med.getBtnRadioConstant_PR().getSelection()){
			this.PoissonsRatioType = med.getBtnRadioConstant_PR().getText().trim();
		}else{
			this.PoissonsRatioType = med.getBtnRadioTable_PR().getText().trim();
		}
		this.textPoissonsRatio = med.getTextPoissonsRatio().getText().trim();
		this.textMassDensity = med.getTextMassDensity().getText().trim();
		
		
		
		this.db.add(TextLabel.lblThermalExpansionCoefficient+"Type"+"="+this.ThermalExpansionCoefficientType);
		this.db.add(TextLabel.lblThermalExpansionCoefficient+"="+this.textThermalExpansionCoefficient);
		this.db.add(TextLabel.lblPoissonsRatio+"Type"+"="+this.PoissonsRatioType);
		this.db.add(TextLabel.lblPoissonsRatio+"="+this.textPoissonsRatio);
		this.db.add(TextLabel.lblMassDensity+"="+this.textMassDensity);
		
		//--------------------------------------------
		this.textSolvingTime = med.getTextSolvingTime().getText().trim();
		this.textIncrementTime = med.getTextIncrementTime().getText().trim();
		this.btnParallelDDMUse = med.getBtnParallelDDMUse().getSelection();
		this.spinnerDomain = med.getSpinnerDomain().getText().trim();
		this.btnParallelMultiThreadUse = med.getBtnParallelMultiThreadUse().getSelection();
		this.spinnerThread = med.getSpinnerThread().getText().trim();
		
		this.db.add(TextLabel.lblSolvingTime+"="+this.textSolvingTime);
		this.db.add(TextLabel.lblIncrementTime+"="+this.textIncrementTime);
		this.db.add(TextLabel.lblParallelDDM+"="+this.btnParallelDDMUse);
		this.db.add(TextLabel.lblDomain+"="+this.spinnerDomain);
		this.db.add(TextLabel.lblParallelMultiThread+"="+this.btnParallelMultiThreadUse);
		this.db.add(TextLabel.lblThread+"="+this.spinnerThread);
	}
	
	public void saveAllData_2D(){
		//this.db_2D.clear();
		this.db_2D.add("LevellerType="+this.LevellerType);
		this.db_2D.add(TextLabel.lblModelName+"="+this.textModelName);
		//--------------------------------------------
		
		this.comboType_2D = med.getComboType_2D().getText().trim();
		this.textWidth_2D = med.getTextWidth_2D().getText().trim();
		this.textLength_2D = med.getTextLength_2D().getText().trim();
		this.textThickness_2D = med.getTextThickness_2D().getText().trim();

		this.db_2D.add(TextLabel.lblType_2D+"="+this.comboType_2D);
		this.db_2D.add(TextLabel.lblWidth_2D+"="+this.textWidth_2D);
		this.db_2D.add(TextLabel.lblLength_2D+"="+this.textLength_2D);
		this.db_2D.add(TextLabel.lblThickness_2D+"="+this.textThickness_2D);
		
		if(this.comboType_2D.equals(ComboLabel.TYPE1_2D)){
			
		}else if(this.comboType_2D.equals(ComboLabel.TYPE2_2D)){
			/*
			this.type2_textLeftEdgeWavePitch_2D = med.getType2_textLeftEdgeWavePitch_2D().getText().trim();
			this.type2_textRightEdgeWavePitch_2D = med.getType2_textRightEdgeWavePitch_2D().getText().trim();
			this.type2_textLeftEdgeWaveHeight_2D = med.getType2_textLeftEdgeWaveHeight_2D().getText().trim();
			this.type2_textRightEdgeWaveHeight_2D = med.getType2_textRightEdgeWaveHeight_2D().getText().trim();
			this.type2_textLeftEdgeWavePhase_2D = med.getType2_textLeftEdgeWavePhase_2D().getText().trim();
			this.type2_textRightEdgeWavePhase_2D = med.getType2_textRightEdgeWavePhase_2D().getText().trim();
			
			this.db_2D.add(TextLabel.lblLeftEdgeWavePitch_type2_2D+"="+this.type2_textLeftEdgeWavePitch_2D);
			this.db_2D.add(TextLabel.lblRightEdgeWavePitch_type2_2D+"="+this.type2_textRightEdgeWavePitch_2D);
			this.db_2D.add(TextLabel.lblLeftEdgeWaveHeight_type2_2D+"="+this.type2_textLeftEdgeWaveHeight_2D);
			this.db_2D.add(TextLabel.lblRightEdgeWaveHeight_type2_2D+"="+this.type2_textRightEdgeWaveHeight_2D);
			this.db_2D.add(TextLabel.lblLeftEdgeWavePhase_type2_2D+"="+this.type2_textLeftEdgeWavePhase_2D);
			this.db_2D.add(TextLabel.lblRightEdgeWavePhase_type2_2D+"="+this.type2_textRightEdgeWavePhase_2D);
			// */
			this.type2_textWavePitch_2D = med.getType2_textWavePitch_2D().getText().trim();
			this.type2_textWaveHeight_2D = med.getType2_textWaveHeight_2D().getText().trim();
			this.type2_textWavePhase_2D = med.getType2_textWavePhase_2D().getText().trim();
			
			this.db_2D.add(TextLabel.lblWavePitch_type2_2D+"="+this.type2_textWavePitch_2D);
			this.db_2D.add(TextLabel.lblWaveHeight_type2_2D+"="+this.type2_textWaveHeight_2D);
			this.db_2D.add(TextLabel.lblWavePhase_type2_2D+"="+this.type2_textWavePhase_2D);
			
		}else if(this.comboType_2D.equals(ComboLabel.TYPE3_2D)){
			/*
			this.type3_textWavePitch_2D = med.getType3_textWavePitch_2D().getText().trim();
			this.type3_textWaveHeight_2D = med.getType3_textWaveHeight_2D().getText().trim();
			
			this.db_2D.add(TextLabel.lblWavePitch_type3_2D+"="+this.type3_textWavePitch_2D);
			this.db_2D.add(TextLabel.lblWaveHeight_type3_2D+"="+this.type3_textWaveHeight_2D);
			// */
			this.type3_textFrontCurlHeight_2D = med.getType3_textFrontCurlHeight_2D().getText().trim();
			this.type3_textFrontCurlLength_2D = med.getType3_textFrontCurlLength_2D().getText().trim();
			this.type3_textRearCurlHeight_2D = med.getType3_textRearCurlHeight_2D().getText().trim();
			this.type3_textRearCurlLength_2D = med.getType3_textRearCurlLength_2D().getText().trim();
			
			this.db_2D.add(TextLabel.lblFrontCurlHeight_type3_2D+"="+this.type3_textFrontCurlHeight_2D);
			this.db_2D.add(TextLabel.lblFrontCurlLength_type3_2D+"="+this.type3_textFrontCurlLength_2D);
			this.db_2D.add(TextLabel.lblRearCurlHeight_type3_2D+"="+this.type3_textRearCurlHeight_2D);
			this.db_2D.add(TextLabel.lblRearCurlLength_type3_2D+"="+this.type3_textRearCurlLength_2D);
			
		}
		/*
		else if(this.comboType_2D.equals(ComboLabel.TYPE4)){
			this.type4_textGutterHeight_2D = med.getType4_textGutterHeight_2D().getText().trim();
			
			this.db_2D.add(TextLabel.lblGutterHeight_type4_2D+"="+this.type4_textGutterHeight_2D);
		}else if(this.comboType_2D.equals(ComboLabel.TYPE5)){
			this.type5_textGutterHeight_2D = med.getType5_textGutterHeight_2D().getText().trim();
			this.type5_textGutterLength_2D = med.getType5_textGutterLength_2D().getText().trim();
			
			this.db_2D.add(TextLabel.lblGutterHeight_type5_2D+"="+this.type5_textGutterHeight_2D);
			this.db_2D.add(TextLabel.lblGutterLength_type5_2D+"="+this.type5_textGutterLength_2D);
			
		}else if(this.comboType_2D.equals(ComboLabel.TYPE6)){
			this.type6_textHeadGutterHeight_2D = med.getType6_textHeadGutterHeight_2D().getText().trim();
			this.type6_textHeadGutterLength_2D = med.getType6_textHeadGutterLength_2D().getText().trim();
			this.type6_textTailGutterHeight_2D = med.getType6_textTailGutterHeight_2D().getText().trim();
			this.type6_textTailGutterLength_2D = med.getType6_textTailGutterLength_2D().getText().trim();
			
			this.db_2D.add(TextLabel.lblHeadGutterHeight_type6_2D+"="+this.type6_textHeadGutterHeight_2D);
			this.db_2D.add(TextLabel.lblHeadGutterLength_type6_2D+"="+this.type6_textHeadGutterLength_2D);
			this.db_2D.add(TextLabel.lblTailGutterHeight_type6_2D+"="+this.type6_textTailGutterHeight_2D);
			this.db_2D.add(TextLabel.lblTailGutterLength_type6_2D+"="+this.type6_textTailGutterLength_2D);
			
		}else if(this.comboType_2D.equals(ComboLabel.TYPE7)){
			this.type7_textHeadGutterHeight_2D = med.getType7_textHeadGutterHeight_2D().getText().trim();
			this.type7_textGutterLength_2D = med.getType7_textGutterLength_2D().getText().trim();
			this.type7_textGutterLengthLength_2D = med.getType7_textGutterLengthLength_2D().getText().trim();
			this.type7_textGutterWidthLength_2D = med.getType7_textGutterWidthLength_2D().getText().trim();
			
			this.db_2D.add(TextLabel.lblHeadGutterHeight_type7_2D+"="+this.type7_textHeadGutterHeight_2D);
			this.db_2D.add(TextLabel.lblGutterLength_type7_2D+"="+this.type7_textGutterLength_2D);
			this.db_2D.add(TextLabel.lblGutterLengthLength_type7_2D+"="+this.type7_textGutterLengthLength_2D);
			this.db_2D.add(TextLabel.lblGutterWidthLength_type7_2D+"="+this.type7_textGutterWidthLength_2D);
		}
		// */
		//--------------------------------------------		
		this.textThicknessElementNum_2D = med.getTextThicknessElementNum_2D().getText().trim();
		//this.textWidthAspectRatio_2D = med.getTextWidthAspectRatio_2D().getText().trim();
		this.textLengthAspectRatio_2D = med.getTextLengthAspectRatio_2D().getText().trim();
		CalcElementNumber_2D();
		// version3 update
		//CreateRoll();
		this.textElementNumber_2D = med.getTextElementNumber_2D().getText().trim();
		
		this.db_2D.add(TextLabel.lblThicknessElementNum_2D+"="+this.textThicknessElementNum_2D);
		//this.db_2D.add(TextLabel.lblWidthAspectRatio_2D+"="+this.textWidthAspectRatio_2D);
		this.db_2D.add(TextLabel.lblLengthAspectRatio_2D+"="+this.textLengthAspectRatio_2D);
		this.db_2D.add(TextLabel.lblElementNumber_2D+"="+this.textElementNumber_2D);
		//--------------------------------------------
		this.textPlateVelocity_2D = med.getTextPlateVelocity_2D().getText().trim();
		this.textTemperatureStart_2D = med.getTextTemperatureStart_2D().getText().trim();
		this.textTemperatureEnd_2D = med.getTextTemperatureEnd_2D().getText().trim();
		//update version2 2016.01.27
		this.textPassLineOffset_2D = med.getTextPassLineOffset_2D().getText().trim();
		
		
		this.db_2D.add(TextLabel.lblPlateVelocity_2D+"="+this.textPlateVelocity_2D);
		this.db_2D.add(TextLabel.lblTemperatureStart_2D+"="+this.textTemperatureStart_2D);
		this.db_2D.add(TextLabel.lblTemperatureEnd_2D+"="+this.textTemperatureEnd_2D);
		//update version2 2016.01.27
		this.db_2D.add(TextLabel.lblPassLineOffset_2D+"="+this.textPassLineOffset_2D);
		//--------------------------------------------		
		this.spinnerUpperRollNum_2D = med.getSpinnerUpperRollNum_2D().getText().trim();
		this.spinnerLowerRollNum_2D = med.getSpinnerLowerRollNum_2D().getText().trim();
		this.textRollPitch_2D = med.getTextRollPitch_2D().getText().trim();
		//this.textRollLength_2D = calcRollLength_2D().trim();
		this.textEntryUpperRollGap_2D = med.getTextEntryUpperRollGap_2D().getText().trim();
		this.textEntryLowerRollGap_2D = med.getTextEntryLowerRollGap_2D().getText().trim();
		this.textExitUpperRollGap_2D = med.getTextExitUpperRollGap_2D().getText().trim();
		this.textExitLowerRollGap_2D = med.getTextExitLowerRollGap_2D().getText().trim();
		this.textRollFriction_2D = med.getTextRollFriction_2D().getText().trim();
		this.textRollDiameter_2D = med.getTextRollDiameter_2D().getText().trim();
		//update version2 2016.01.27
		/*
		if(med.getBtnRadioNone_RC_2D().getSelection()){
			this.RollCrownType_2D = med.getBtnRadioNone_RC_2D().getText().trim();
		}else{
			this.RollCrownType_2D = med.getBtnRadioApply_RC_2D().getText().trim();
		}
		//*/
		//this.textUpperRollCrown_2D = med.getTextUpperRollCrown_2D().getText().trim();
		//this.textLowerRollCrown_2D = med.getTextLowerRollCrown_2D().getText().trim();
		
		if(med.getBtnRadioRigid_MS_2D().getSelection()){
			this.MillStiffnessType_2D = med.getBtnRadioRigid_MS_2D().getText().trim();
		}else{
			this.MillStiffnessType_2D = med.getBtnRadioSpring_MS_2D().getText().trim();
		}
		this.textMillStiffness_2D = med.getTextMillStiffness_2D().getText().trim();
		
		
		//med.getTextRollLength_2D().setText(this.textRollLength_2D);
		
		this.db_2D.add(TextLabel.lblUpperRollNumber_2D+"="+this.spinnerUpperRollNum_2D);
		this.db_2D.add(TextLabel.lblLowerRollNumber_2D+"="+this.spinnerLowerRollNum_2D);
		this.db_2D.add(TextLabel.lblRollPitch_2D+"="+this.textRollPitch_2D);
		//this.db_2D.add(TextLabel.lblRollLength_2D+"="+this.textRollLength_2D);
		this.db_2D.add(TextLabel.lblEntryUpperRollGap_2D+"="+this.textEntryUpperRollGap_2D);
		this.db_2D.add(TextLabel.lblEntryLowerRollGap_2D+"="+this.textEntryLowerRollGap_2D);
		this.db_2D.add(TextLabel.lblExitUpperRollGap_2D+"="+this.textExitUpperRollGap_2D);
		this.db_2D.add(TextLabel.lblExitLowerRollGap_2D+"="+this.textExitLowerRollGap_2D);
		this.db_2D.add(TextLabel.lblRollFriction_2D+"="+this.textRollFriction_2D);
		this.db_2D.add(TextLabel.lblRollDiameter_2D+"="+this.textRollDiameter_2D);
		//update version2 2016.01.27
		//this.db_2D.add(TextLabel.lblRollCrownType_2D+"="+this.RollCrownType_2D);
		//this.db_2D.add(TextLabel.lblUpperRollCrown_2D+"="+this.textUpperRollCrown_2D);
		//this.db_2D.add(TextLabel.lblLowerRollCrown_2D+"="+this.textLowerRollCrown_2D);
		
		this.db_2D.add(TextLabel.lblMillStiffnessType_2D+"="+this.MillStiffnessType_2D);
		this.db_2D.add(TextLabel.lblMillStiffness_2D+"="+this.textMillStiffness_2D);
		//update version3 
		this.textUpperEntryRollGapMovement_2D = med.getTextUpperEntryRollGapMovement_2D().getText().trim();
		this.textUpperExitRollGapMovement_2D = med.getTextUpperExitRollGapMovement_2D().getText().trim();
		this.textUpperRollGapStayingTime_2D = med.getTextUpperRollGapStayingTime_2D().getText().trim();
		this.textUpperRollGapMovingTime_2D = med.getTextUpperRollGapMovingTime_2D().getText().trim();
		this.textLowerEntryRollGapMovement_2D = med.getTextLowerEntryRollGapMovement_2D().getText().trim();
		this.textLowerExitRollGapMovement_2D = med.getTextLowerExitRollGapMovement_2D().getText().trim();
		this.textLowerRollGapStayingTime_2D = med.getTextLowerRollGapStayingTime_2D().getText().trim();
		this.textLowerRollGapMovingTime_2D = med.getTextLowerRollGapMovingTime_2D().getText().trim();
		
		this.db_2D.add(TextLabel.lblUpperEntryRollGapMovement_2D+"="+this.textUpperEntryRollGapMovement_2D);
		this.db_2D.add(TextLabel.lblUpperExitRollGapMovement_2D+"="+this.textUpperExitRollGapMovement_2D);
		this.db_2D.add(TextLabel.lblUpperRollGapStayingTime_2D+"="+this.textUpperRollGapStayingTime_2D);
		this.db_2D.add(TextLabel.lblUpperRollGapMovingTime_2D+"="+this.textUpperRollGapMovingTime_2D);
		this.db_2D.add(TextLabel.lblLowerEntryRollGapMovement_2D+"="+this.textLowerEntryRollGapMovement_2D);
		this.db_2D.add(TextLabel.lblLowerExitRollGapMovement_2D+"="+this.textLowerExitRollGapMovement_2D);
		this.db_2D.add(TextLabel.lblLowerRollGapStayingTime_2D+"="+this.textLowerRollGapStayingTime_2D);
		this.db_2D.add(TextLabel.lblLowerRollGapMovingTime_2D+"="+this.textLowerRollGapMovingTime_2D);
		
		if(med.getBtnNone_2D().getSelection()){
			this.HDRollType_2D = med.getBtnNone_2D().getText();
		}
		if(med.getBtnUpper_2D().getSelection()){
			this.HDRollType_2D = med.getBtnUpper_2D().getText();
		}
		if(med.getBtnLower_2D().getSelection()){
			this.HDRollType_2D = med.getBtnLower_2D().getText();
		}
		this.textFrontHDRollDia_2D = med.getTextFrontHDRollDia_2D().getText().trim();
		this.textFrontHDRollPitch_2D = med.getTextFrontHDRollPitch_2D().getText().trim();
		this.textFrontHDRollVericalPos_2D = med.getTextFrontHDRollVericalPos_2D().getText().trim();
		this.textRearHDRollDia_2D = med.getTextRearHDRollDia_2D().getText().trim();
		this.textRearHDRollPitch_2D = med.getTextRearHDRollPitch_2D().getText().trim();
		this.textRearHDRollVerticalPos_2D = med.getTextRearHDRollVerticalPos_2D().getText().trim();
		
		this.db_2D.add(TextLabel.lblFrontHDRollDia_2D+"="+this.textFrontHDRollDia_2D);
		this.db_2D.add(TextLabel.lblFrontHDRollPitch_2D+"="+this.textFrontHDRollPitch_2D);
		this.db_2D.add(TextLabel.lblFrontHDRollVericalPos_2D+"="+this.textFrontHDRollVericalPos_2D);
		this.db_2D.add(TextLabel.lblRearHDRollDia_2D+"="+this.textRearHDRollDia_2D);
		this.db_2D.add(TextLabel.lblRearHDRollPitch_2D+"="+this.textRearHDRollPitch_2D);
		this.db_2D.add(TextLabel.lblRearHDRollVerticalPos_2D+"="+this.textRearHDRollVerticalPos_2D);
		this.db_2D.add(TextLabel.HDRollType_2D+"="+this.HDRollType_2D);
		
		try{
			for(UpTableDataContent obj : this.upTableDataList_2D){
				this.db_2D.add(obj.getNo()+"="+obj.getDB());
			}
		}catch (Exception e){
			//System.out.println("[LevellerMain.saveAllData()] Table Data is empty.");
		}
		try{
			for(DownTableDataContent obj : this.downTableDataList_2D){
				this.db_2D.add(obj.getNo()+"="+obj.getDB());
			}
		}catch (Exception e){
			//System.out.println("[LevellerMain.saveAllData()] Table Data is empty.");
		}
		
		//--------------------------------------------
		
		if(med.getBtnRadioConstant_YM_2D().getSelection()){
			this.YoungsModulusType_2D = med.getBtnRadioConstant_YM_2D().getText().trim();
		}else{
			this.YoungsModulusType_2D = med.getBtnRadioTable_YM_2D().getText().trim();
		}
		this.textYoungsModulus_2D = med.getTextYoungsModulus_2D().getText().trim();
		this.db_2D.add(TextLabel.lblYoungsModulus_2D+"Type"+"="+this.YoungsModulusType_2D);
		this.db_2D.add(TextLabel.lblYoungsModulus_2D+"="+this.textYoungsModulus_2D);
		
		
		if(med.getBtnRadioConstant_FS_2D().getSelection()){
			this.FlowStressType_2D = med.getBtnRadioConstant_FS_2D().getText().trim();
			//update version2 2017.01.27
			this.textYieldStrength_2D = med.getTextYieldStrength_2D().getText().trim();
			this.textTensileStrength_2D = med.getTextTensileStrength_2D().getText().trim();
			this.textElongation_2D = med.getTextElongation_2D().getText().trim();
			
			this.db_2D.add(TextLabel.lblFlowStress_2D+"Type"+"="+this.FlowStressType_2D);
			this.db_2D.add(TextLabel.lblYieldStrength_2D+"="+this.textYieldStrength_2D);
			this.db_2D.add(TextLabel.lblTensileStrength_2D+"="+this.textTensileStrength_2D);
			this.db_2D.add(TextLabel.lblElongation_2D+"="+this.textElongation_2D);
			
		}else{
			this.FlowStressType_2D = med.getBtnRadioTable_FS_2D().getText().trim();
			//update version2 2017.01.27
			this.textFlowStress_2D = med.getTextFlowStress_2D().getText().trim();
			this.db_2D.add(TextLabel.lblFlowStress_2D+"Type"+"="+this.FlowStressType_2D);
			this.db_2D.add(TextLabel.lblFlowStress_2D+"="+this.textFlowStress_2D);
		}
		
		if(med.getBtnRadioConstant_TEC_2D().getSelection()){
			this.ThermalExpansionCoefficientType_2D = med.getBtnRadioConstant_TEC_2D().getText().trim();
		}else{
			this.ThermalExpansionCoefficientType_2D = med.getBtnRadioTable_TEC_2D().getText().trim();
		}
		this.textThermalExpansionCoefficient_2D = med.getTextThermalExpansionCoefficient_2D().getText().trim();
		if(med.getBtnRadioConstant_PR_2D().getSelection()){
			this.PoissonsRatioType_2D = med.getBtnRadioConstant_PR_2D().getText().trim();
		}else{
			this.PoissonsRatioType_2D = med.getBtnRadioTable_PR_2D().getText().trim();
		}
		this.textPoissonsRatio_2D = med.getTextPoissonsRatio_2D().getText().trim();
		this.textMassDensity_2D = med.getTextMassDensity_2D().getText().trim();
		
		
		
		this.db_2D.add(TextLabel.lblThermalExpansionCoefficient_2D+"Type"+"="+this.ThermalExpansionCoefficientType_2D);
		this.db_2D.add(TextLabel.lblThermalExpansionCoefficient_2D+"="+this.textThermalExpansionCoefficient_2D);
		this.db_2D.add(TextLabel.lblPoissonsRatio_2D+"Type"+"="+this.PoissonsRatioType_2D);
		this.db_2D.add(TextLabel.lblPoissonsRatio_2D+"="+this.textPoissonsRatio_2D);
		this.db_2D.add(TextLabel.lblMassDensity_2D+"="+this.textMassDensity_2D);
		
		//--------------------------------------------
		this.textSolvingTime_2D = med.getTextSolvingTime_2D().getText().trim();
		this.textIncrementTime_2D = med.getTextIncrementTime_2D().getText().trim();
		this.btnParallelDDMUse_2D = med.getBtnParallelDDMUse_2D().getSelection();
		this.spinnerDomain_2D = med.getSpinnerDomain_2D().getText().trim();
		this.btnParallelMultiThreadUse_2D = med.getBtnParallelMultiThreadUse_2D().getSelection();
		this.spinnerThread_2D = med.getSpinnerThread_2D().getText().trim();
		
		this.db_2D.add(TextLabel.lblSolvingTime_2D+"="+this.textSolvingTime_2D);
		this.db_2D.add(TextLabel.lblIncrementTime_2D+"="+this.textIncrementTime_2D);
		this.db_2D.add(TextLabel.lblParallelDDM_2D+"="+this.btnParallelDDMUse_2D);
		this.db_2D.add(TextLabel.lblDomain_2D+"="+this.spinnerDomain_2D);
		this.db_2D.add(TextLabel.lblParallelMultiThread_2D+"="+this.btnParallelMultiThreadUse_2D);
		this.db_2D.add(TextLabel.lblThread_2D+"="+this.spinnerThread_2D);
	}
	
	public void saveAsAllData(String newModelName){
		//this.db_saveAs.clear();
		this.db_saveAs.add("LevellerType="+this.LevellerType);
		this.db_saveAs.add(TextLabel.lblModelName+"="+newModelName);
		
		//--------------------------------------------
		this.comboType = med.getComboType().getText().trim();
		this.textWidth = med.getTextWidth().getText().trim();
		this.textLength = med.getTextLength().getText().trim();
		this.textThickness = med.getTextThickness().getText().trim();

		this.db_saveAs.add(TextLabel.lblType+"="+this.comboType);
		this.db_saveAs.add(TextLabel.lblWidth+"="+this.textWidth);
		this.db_saveAs.add(TextLabel.lblLength+"="+this.textLength);
		this.db_saveAs.add(TextLabel.lblThickness+"="+this.textThickness);
		
		if(this.comboType.equals(ComboLabel.TYPE1)){
			
		}else if(this.comboType.equals(ComboLabel.TYPE2)){
			this.type2_textLeftEdgeWavePitch = med.getType2_textLeftEdgeWavePitch().getText().trim();
			this.type2_textRightEdgeWavePitch = med.getType2_textRightEdgeWavePitch().getText().trim();
			this.type2_textLeftEdgeWaveHeight = med.getType2_textLeftEdgeWaveHeight().getText().trim();
			this.type2_textRightEdgeWaveHeight = med.getType2_textRightEdgeWaveHeight().getText().trim();
			this.type2_textLeftEdgeWavePhase = med.getType2_textLeftEdgeWavePhase().getText().trim();
			this.type2_textRightEdgeWavePhase = med.getType2_textRightEdgeWavePhase().getText().trim();
			
			this.db_saveAs.add(TextLabel.lblLeftEdgeWavePitch_type2+"="+this.type2_textLeftEdgeWavePitch);
			this.db_saveAs.add(TextLabel.lblRightEdgeWavePitch_type2+"="+this.type2_textRightEdgeWavePitch);
			this.db_saveAs.add(TextLabel.lblLeftEdgeWaveHeight_type2+"="+this.type2_textLeftEdgeWaveHeight);
			this.db_saveAs.add(TextLabel.lblRightEdgeWaveHeight_type2+"="+this.type2_textRightEdgeWaveHeight);
			this.db_saveAs.add(TextLabel.lblLeftEdgeWavePhase_type2+"="+this.type2_textLeftEdgeWavePhase);
			this.db_saveAs.add(TextLabel.lblRightEdgeWavePhase_type2+"="+this.type2_textRightEdgeWavePhase);
		}else if(this.comboType.equals(ComboLabel.TYPE3)){
			this.type3_textWavePitch = med.getType3_textWavePitch().getText().trim();
			this.type3_textWaveHeight = med.getType3_textWaveHeight().getText().trim();
			
			this.db_saveAs.add(TextLabel.lblWavePitch_type3+"="+this.type3_textWavePitch);
			this.db_saveAs.add(TextLabel.lblWaveHeight_type3+"="+this.type3_textWaveHeight);
		}else if(this.comboType.equals(ComboLabel.TYPE4)){
			this.type4_textGutterHeight = med.getType4_textGutterHeight().getText().trim();
			
			this.db_saveAs.add(TextLabel.lblGutterHeight_type4+"="+this.type4_textGutterHeight);
		}else if(this.comboType.equals(ComboLabel.TYPE5)){
			this.type5_textGutterHeight = med.getType5_textGutterHeight().getText().trim();
			this.type5_textGutterLength = med.getType5_textGutterLength().getText().trim();
			
			this.db_saveAs.add(TextLabel.lblGutterHeight_type5+"="+this.type5_textGutterHeight);
			this.db_saveAs.add(TextLabel.lblGutterLength_type5+"="+this.type5_textGutterLength);
			
		}else if(this.comboType.equals(ComboLabel.TYPE6)){
			this.type6_textHeadGutterHeight = med.getType6_textHeadGutterHeight().getText().trim();
			this.type6_textHeadGutterLength = med.getType6_textHeadGutterLength().getText().trim();
			this.type6_textTailGutterHeight = med.getType6_textTailGutterHeight().getText().trim();
			this.type6_textTailGutterLength = med.getType6_textTailGutterLength().getText().trim();
			
			this.db_saveAs.add(TextLabel.lblHeadGutterHeight_type6+"="+this.type6_textHeadGutterHeight);
			this.db_saveAs.add(TextLabel.lblHeadGutterLength_type6+"="+this.type6_textHeadGutterLength);
			this.db_saveAs.add(TextLabel.lblTailGutterHeight_type6+"="+this.type6_textTailGutterHeight);
			this.db_saveAs.add(TextLabel.lblTailGutterLength_type6+"="+this.type6_textTailGutterLength);
			
		}else if(this.comboType.equals(ComboLabel.TYPE7)){
			this.type7_textHeadGutterHeight = med.getType7_textHeadGutterHeight().getText().trim();
			this.type7_textGutterLength = med.getType7_textGutterLength().getText().trim();
			this.type7_textGutterLengthLength = med.getType7_textGutterLengthLength().getText().trim();
			this.type7_textGutterWidthLength = med.getType7_textGutterWidthLength().getText().trim();
			
			this.db_saveAs.add(TextLabel.lblHeadGutterHeight_type7+"="+this.type7_textHeadGutterHeight);
			this.db_saveAs.add(TextLabel.lblGutterLength_type7+"="+this.type7_textGutterLength);
			this.db_saveAs.add(TextLabel.lblGutterLengthLength_type7+"="+this.type7_textGutterLengthLength);
			this.db_saveAs.add(TextLabel.lblGutterWidthLength_type7+"="+this.type7_textGutterWidthLength);
		}
		//--------------------------------------------		
		this.textThicknessElementNum = med.getTextThicknessElementNum().getText().trim();
		this.textWidthAspectRatio = med.getTextWidthAspectRatio().getText().trim();
		this.textLengthAspectRatio = med.getTextLengthAspectRatio().getText().trim();
		CalcElementNumber();
		// version3 update
		//CreateRoll();
		this.textElementNumber = med.getTextElementNumber().getText().trim();
		
		this.db_saveAs.add(TextLabel.lblThicknessElementNum+"="+this.textThicknessElementNum);
		this.db_saveAs.add(TextLabel.lblWidthAspectRatio+"="+this.textWidthAspectRatio);
		this.db_saveAs.add(TextLabel.lblLengthAspectRatio+"="+this.textLengthAspectRatio);
		this.db_saveAs.add(TextLabel.lblElementNumber+"="+this.textElementNumber);
		//--------------------------------------------
		this.textPlateVelocity = med.getTextPlateVelocity().getText().trim();
		this.textTemperatureStart = med.getTextTemperatureStart().getText().trim();
		this.textTemperatureEnd = med.getTextTemperatureEnd().getText().trim();
		//update version2 2016.01.27
		this.textPassLineOffset = med.getTextPassLineOffset().getText().trim();
		
		this.db_saveAs.add(TextLabel.lblPlateVelocity+"="+this.textPlateVelocity);
		this.db_saveAs.add(TextLabel.lblTemperatureStart+"="+this.textTemperatureStart);
		this.db_saveAs.add(TextLabel.lblTemperatureEnd+"="+this.textTemperatureEnd);
		//update version2 2016.01.27
		this.db_saveAs.add(TextLabel.lblPassLineOffset+"="+this.textPassLineOffset);
		//--------------------------------------------		
		this.spinnerUpperRollNum = med.getSpinnerUpperRollNum().getText().trim();
		this.spinnerLowerRollNum = med.getSpinnerLowerRollNum().getText().trim();
		this.textRollPitch = med.getTextRollPitch().getText().trim();
		this.textRollLength = calcRollLength().trim();
		this.textEntryUpperRollGap = med.getTextEntryUpperRollGap().getText().trim();
		this.textEntryLowerRollGap = med.getTextEntryLowerRollGap().getText().trim();
		this.textExitUpperRollGap = med.getTextExitUpperRollGap().getText().trim();
		this.textExitLowerRollGap = med.getTextExitLowerRollGap().getText().trim();
		this.textRollFriction = med.getTextRollFriction().getText().trim();
		this.textRollDiameter = med.getTextRollDiameter().getText().trim();
		//update version2 2016.01.27
		/*
		if(med.getBtnRadioNone_RC().getSelection()){
			this.RollCrownType = med.getBtnRadioNone_RC().getText().trim();
		}else{
			this.RollCrownType = med.getBtnRadioApply_RC().getText().trim();
		}
		//*/
		this.textUpperRollCrown = med.getTextUpperRollCrown().getText().trim();
		this.textLowerRollCrown = med.getTextLowerRollCrown().getText().trim();
		
		if(med.getBtnRadioRigid_MS().getSelection()){
			this.MillStiffnessType = med.getBtnRadioRigid_MS().getText().trim();
		}else{
			this.MillStiffnessType = med.getBtnRadioSpring_MS().getText().trim();
		}
		this.textMillStiffness = med.getTextMillStiffness().getText().trim();
		
		med.getTextRollLength().setText(this.textRollLength);
		
		this.db_saveAs.add(TextLabel.lblUpperRollNumber+"="+this.spinnerUpperRollNum);
		this.db_saveAs.add(TextLabel.lblLowerRollNumber+"="+this.spinnerLowerRollNum);
		this.db_saveAs.add(TextLabel.lblRollPitch+"="+this.textRollPitch);
		this.db_saveAs.add(TextLabel.lblRollLength+"="+this.textRollLength);
		this.db_saveAs.add(TextLabel.lblEntryUpperRollGap+"="+this.textEntryUpperRollGap);
		this.db_saveAs.add(TextLabel.lblEntryLowerRollGap+"="+this.textEntryLowerRollGap);
		this.db_saveAs.add(TextLabel.lblExitUpperRollGap+"="+this.textExitUpperRollGap);
		this.db_saveAs.add(TextLabel.lblExitLowerRollGap+"="+this.textExitLowerRollGap);
		this.db_saveAs.add(TextLabel.lblRollFriction+"="+this.textRollFriction);
		this.db_saveAs.add(TextLabel.lblRollDiameter+"="+this.textRollDiameter);
		//update version2 2016.01.27
		//this.db_saveAs.add(TextLabel.lblRollCrownType+"="+this.RollCrownType);
		this.db_saveAs.add(TextLabel.lblUpperRollCrown+"="+this.textUpperRollCrown);
		this.db_saveAs.add(TextLabel.lblLowerRollCrown+"="+this.textLowerRollCrown);
		this.db_saveAs.add(TextLabel.lblMillStiffnessType+"="+this.MillStiffnessType);
		this.db_saveAs.add(TextLabel.lblMillStiffness+"="+this.textMillStiffness);
		//update version3
		this.textUpperEntryRollGapMovement = med.getTextUpperEntryRollGapMovement().getText().trim();
		this.textUpperExitRollGapMovement = med.getTextUpperExitRollGapMovement().getText().trim();
		this.textUpperRollGapStayingTime = med.getTextUpperRollGapStayingTime().getText().trim();
		this.textUpperRollGapMovingTime = med.getTextUpperRollGapMovingTime().getText().trim();
		this.textLowerEntryRollGapMovement = med.getTextLowerEntryRollGapMovement().getText().trim();
		this.textLowerExitRollGapMovement = med.getTextLowerExitRollGapMovement().getText().trim();
		this.textLowerRollGapStayingTime = med.getTextLowerRollGapStayingTime().getText().trim();
		this.textLowerRollGapMovingTime = med.getTextLowerRollGapMovingTime().getText().trim();
		
		this.db_saveAs.add(TextLabel.lblUpperEntryRollGapMovement+"="+this.textUpperEntryRollGapMovement);
		this.db_saveAs.add(TextLabel.lblUpperExitRollGapMovement+"="+this.textUpperExitRollGapMovement);
		this.db_saveAs.add(TextLabel.lblUpperRollGapStayingTime+"="+this.textUpperRollGapStayingTime);
		this.db_saveAs.add(TextLabel.lblUpperRollGapMovingTime+"="+this.textUpperRollGapMovingTime);
		this.db_saveAs.add(TextLabel.lblLowerEntryRollGapMovement+"="+this.textLowerEntryRollGapMovement);
		this.db_saveAs.add(TextLabel.lblLowerExitRollGapMovement+"="+this.textLowerExitRollGapMovement);
		this.db_saveAs.add(TextLabel.lblLowerRollGapStayingTime+"="+this.textLowerRollGapStayingTime);
		this.db_saveAs.add(TextLabel.lblLowerRollGapMovingTime+"="+this.textLowerRollGapMovingTime);
		
		if(med.getBtnNone().getSelection()){
			this.HDRollType = med.getBtnNone().getText();
		}
		if(med.getBtnUpper().getSelection()){
			this.HDRollType = med.getBtnUpper().getText();
		}
		if(med.getBtnLower().getSelection()){
			this.HDRollType = med.getBtnLower().getText();
		}
		this.textFrontHDRollDia = med.getTextFrontHDRollDia().getText().trim();
		this.textFrontHDRollPitch = med.getTextFrontHDRollPitch().getText().trim();
		this.textFrontHDRollVericalPos = med.getTextFrontHDRollVericalPos().getText().trim();
		this.textRearHDRollDia = med.getTextRearHDRollDia().getText().trim();
		this.textRearHDRollPitch = med.getTextRearHDRollPitch().getText().trim();
		this.textRearHDRollVerticalPos = med.getTextRearHDRollVerticalPos().getText().trim();
		
		this.db_saveAs.add(TextLabel.lblFrontHDRollDia+"="+this.textFrontHDRollDia);
		this.db_saveAs.add(TextLabel.lblFrontHDRollPitch+"="+this.textFrontHDRollPitch);
		this.db_saveAs.add(TextLabel.lblFrontHDRollVericalPos+"="+this.textFrontHDRollVericalPos);
		this.db_saveAs.add(TextLabel.lblRearHDRollDia+"="+this.textRearHDRollDia);
		this.db_saveAs.add(TextLabel.lblRearHDRollPitch+"="+this.textRearHDRollPitch);
		this.db_saveAs.add(TextLabel.lblRearHDRollVerticalPos+"="+this.textRearHDRollVerticalPos);
		this.db_saveAs.add(TextLabel.HDRollType+"="+this.HDRollType);
				
		try{
			for(UpTableDataContent obj : this.upTableDataList){
				this.db_saveAs.add(obj.getNo()+"="+obj.getDB());
			}
		}catch (Exception e){
			//System.out.println("[LevellerMain.saveAsAllData()] Table Data is empty.");
		}
		try{
			for(DownTableDataContent obj : this.downTableDataList){
				this.db_saveAs.add(obj.getNo()+"="+obj.getDB());
			}
		}catch (Exception e){
			//System.out.println("[LevellerMain.saveAsAllData()] Table Data is empty.");
		}
		
		//--------------------------------------------
		if(med.getBtnRadioConstant_YM().getSelection()){
			this.YoungsModulusType = med.getBtnRadioConstant_YM().getText().trim();
		}else{
			this.YoungsModulusType = med.getBtnRadioTable_YM().getText().trim();
		}
		this.textYoungsModulus = med.getTextYoungsModulus().getText().trim();
		this.db_saveAs.add(TextLabel.lblYoungsModulus+"Type"+"="+this.YoungsModulusType);
		this.db_saveAs.add(TextLabel.lblYoungsModulus+"="+this.textYoungsModulus);
		
		if(med.getBtnRadioConstant_FS().getSelection()){
			this.FlowStressType = med.getBtnRadioConstant_FS().getText().trim();
			//update version2 2017.01.27
			this.textYieldStrength = med.getTextYieldStrength().getText().trim();
			this.textTensileStrength = med.getTextTensileStrength().getText().trim();
			this.textElongation = med.getTextElongation().getText().trim();
			
			this.db_saveAs.add(TextLabel.lblFlowStress+"Type"+"="+this.FlowStressType);
			this.db_saveAs.add(TextLabel.lblYieldStrength+"="+this.textYieldStrength);
			this.db_saveAs.add(TextLabel.lblTensileStrength+"="+this.textTensileStrength);
			this.db_saveAs.add(TextLabel.lblElongation+"="+this.textElongation);
			
		}else{
			this.FlowStressType = med.getBtnRadioTable_FS().getText().trim();
			//update version2 2017.01.27
			this.textFlowStress = med.getTextFlowStress().getText().trim();
			this.db_saveAs.add(TextLabel.lblFlowStress+"Type"+"="+this.FlowStressType);
			this.db_saveAs.add(TextLabel.lblFlowStress+"="+this.textFlowStress);
		}
		
		if(med.getBtnRadioConstant_TEC().getSelection()){
			this.ThermalExpansionCoefficientType = med.getBtnRadioConstant_TEC().getText().trim();
		}else{
			this.ThermalExpansionCoefficientType = med.getBtnRadioTable_TEC().getText().trim();
		}
		this.textThermalExpansionCoefficient = med.getTextThermalExpansionCoefficient().getText().trim();
		this.db_saveAs.add(TextLabel.lblThermalExpansionCoefficient+"Type"+"="+this.ThermalExpansionCoefficientType);
		this.db_saveAs.add(TextLabel.lblThermalExpansionCoefficient+"="+this.textThermalExpansionCoefficient);
		
		if(med.getBtnRadioConstant_PR().getSelection()){
			this.PoissonsRatioType = med.getBtnRadioConstant_PR().getText().trim();
		}else{
			this.PoissonsRatioType = med.getBtnRadioTable_PR().getText().trim();
		}
		this.textPoissonsRatio = med.getTextPoissonsRatio().getText().trim();
		this.db_saveAs.add(TextLabel.lblPoissonsRatio+"Type"+"="+this.PoissonsRatioType);
		this.db_saveAs.add(TextLabel.lblPoissonsRatio+"="+this.textPoissonsRatio);
		
		this.textMassDensity = med.getTextMassDensity().getText().trim();
		this.db_saveAs.add(TextLabel.lblMassDensity+"="+this.textMassDensity);
		
		//--------------------------------------------
		this.textSolvingTime = med.getTextSolvingTime().getText().trim();
		this.textIncrementTime = med.getTextIncrementTime().getText().trim();
		this.btnParallelDDMUse = med.getBtnParallelDDMUse().getSelection();
		this.spinnerDomain = med.getSpinnerDomain().getText().trim();
		this.btnParallelMultiThreadUse = med.getBtnParallelMultiThreadUse().getSelection();
		this.spinnerThread = med.getSpinnerThread().getText().trim();
		
		this.db_saveAs.add(TextLabel.lblSolvingTime+"="+this.textSolvingTime);
		this.db_saveAs.add(TextLabel.lblIncrementTime+"="+this.textIncrementTime);
		this.db_saveAs.add(TextLabel.lblParallelDDM+"="+this.btnParallelDDMUse);
		this.db_saveAs.add(TextLabel.lblDomain+"="+this.spinnerDomain);
		this.db_saveAs.add(TextLabel.lblParallelMultiThread+"="+this.btnParallelMultiThreadUse);
		this.db_saveAs.add(TextLabel.lblThread+"="+this.spinnerThread);
	}
	
	public void saveAsAllData_2D(String newModelName){
		//this.db_saveAs_2D.clear();
		this.db_saveAs_2D.add("LevellerType="+this.LevellerType);
		this.db_saveAs_2D.add(TextLabel.lblModelName+"="+this.textModelName);

		//--------------------------------------------
		this.comboType_2D = med.getComboType_2D().getText().trim();
		this.textWidth_2D = med.getTextWidth_2D().getText().trim();
		this.textLength_2D = med.getTextLength_2D().getText().trim();
		this.textThickness_2D = med.getTextThickness_2D().getText().trim();

		this.db_saveAs_2D.add(TextLabel.lblType_2D+"="+this.comboType_2D);
		this.db_saveAs_2D.add(TextLabel.lblWidth_2D+"="+this.textWidth_2D);
		this.db_saveAs_2D.add(TextLabel.lblLength_2D+"="+this.textLength_2D);
		this.db_saveAs_2D.add(TextLabel.lblThickness_2D+"="+this.textThickness_2D);

		if(this.comboType_2D.equals(ComboLabel.TYPE1_2D)){

		}else if(this.comboType_2D.equals(ComboLabel.TYPE2_2D)){
			/*
			this.type2_textLeftEdgeWavePitch_2D = med.getType2_textLeftEdgeWavePitch_2D().getText().trim();
			this.type2_textRightEdgeWavePitch_2D = med.getType2_textRightEdgeWavePitch_2D().getText().trim();
			this.type2_textLeftEdgeWaveHeight_2D = med.getType2_textLeftEdgeWaveHeight_2D().getText().trim();
			this.type2_textRightEdgeWaveHeight_2D = med.getType2_textRightEdgeWaveHeight_2D().getText().trim();
			this.type2_textLeftEdgeWavePhase_2D = med.getType2_textLeftEdgeWavePhase_2D().getText().trim();
			this.type2_textRightEdgeWavePhase_2D = med.getType2_textRightEdgeWavePhase_2D().getText().trim();

			this.db_saveAs_2D.add(TextLabel.lblLeftEdgeWavePitch_type2_2D+"="+this.type2_textLeftEdgeWavePitch_2D);
			this.db_saveAs_2D.add(TextLabel.lblRightEdgeWavePitch_type2_2D+"="+this.type2_textRightEdgeWavePitch_2D);
			this.db_saveAs_2D.add(TextLabel.lblLeftEdgeWaveHeight_type2_2D+"="+this.type2_textLeftEdgeWaveHeight_2D);
			this.db_saveAs_2D.add(TextLabel.lblRightEdgeWaveHeight_type2_2D+"="+this.type2_textRightEdgeWaveHeight_2D);
			this.db_saveAs_2D.add(TextLabel.lblLeftEdgeWavePhase_type2_2D+"="+this.type2_textLeftEdgeWavePhase_2D);
			this.db_saveAs_2D.add(TextLabel.lblRightEdgeWavePhase_type2_2D+"="+this.type2_textRightEdgeWavePhase_2D);
			// */
			this.type2_textWavePitch_2D = med.getType2_textWavePitch_2D().getText().trim();
			this.type2_textWaveHeight_2D = med.getType2_textWaveHeight_2D().getText().trim();
			this.type2_textWavePhase_2D = med.getType2_textWavePhase_2D().getText().trim();
			
			this.db_saveAs_2D.add(TextLabel.lblWavePitch_type2_2D+"="+this.type2_textWavePitch_2D);
			this.db_saveAs_2D.add(TextLabel.lblWaveHeight_type2_2D+"="+this.type2_textWaveHeight_2D);
			this.db_saveAs_2D.add(TextLabel.lblWavePhase_type2_2D+"="+this.type2_textWavePhase_2D);
		}else if(this.comboType_2D.equals(ComboLabel.TYPE3_2D)){
			/*
			this.type3_textWavePitch_2D = med.getType3_textWavePitch_2D().getText().trim();
			this.type3_textWaveHeight_2D = med.getType3_textWaveHeight_2D().getText().trim();

			this.db_saveAs_2D.add(TextLabel.lblWavePitch_type3_2D+"="+this.type3_textWavePitch_2D);
			this.db_saveAs_2D.add(TextLabel.lblWaveHeight_type3_2D+"="+this.type3_textWaveHeight_2D);
			// */
			
			this.type3_textFrontCurlHeight_2D = med.getType3_textFrontCurlHeight_2D().getText().trim();
			this.type3_textFrontCurlLength_2D = med.getType3_textFrontCurlLength_2D().getText().trim();
			this.type3_textRearCurlHeight_2D = med.getType3_textRearCurlHeight_2D().getText().trim();
			this.type3_textRearCurlLength_2D = med.getType3_textRearCurlLength_2D().getText().trim();
			
			this.db_saveAs_2D.add(TextLabel.lblFrontCurlHeight_type3_2D+"="+this.type3_textFrontCurlHeight_2D);
			this.db_saveAs_2D.add(TextLabel.lblFrontCurlLength_type3_2D+"="+this.type3_textFrontCurlLength_2D);
			this.db_saveAs_2D.add(TextLabel.lblRearCurlHeight_type3_2D+"="+this.type3_textRearCurlHeight_2D);
			this.db_saveAs_2D.add(TextLabel.lblRearCurlLength_type3_2D+"="+this.type3_textRearCurlLength_2D);
		}
		/*
		else if(this.comboType_2D.equals(ComboLabel.TYPE4)){
			this.type4_textGutterHeight_2D = med.getType4_textGutterHeight_2D().getText().trim();

			this.db_saveAs_2D.add(TextLabel.lblGutterHeight_type4_2D+"="+this.type4_textGutterHeight_2D);
		}else if(this.comboType_2D.equals(ComboLabel.TYPE5)){
			this.type5_textGutterHeight_2D = med.getType5_textGutterHeight_2D().getText().trim();
			this.type5_textGutterLength_2D = med.getType5_textGutterLength_2D().getText().trim();

			this.db_saveAs_2D.add(TextLabel.lblGutterHeight_type5_2D+"="+this.type5_textGutterHeight_2D);
			this.db_saveAs_2D.add(TextLabel.lblGutterLength_type5_2D+"="+this.type5_textGutterLength_2D);

		}else if(this.comboType_2D.equals(ComboLabel.TYPE6)){
			this.type6_textHeadGutterHeight_2D = med.getType6_textHeadGutterHeight_2D().getText().trim();
			this.type6_textHeadGutterLength_2D = med.getType6_textHeadGutterLength_2D().getText().trim();
			this.type6_textTailGutterHeight_2D = med.getType6_textTailGutterHeight_2D().getText().trim();
			this.type6_textTailGutterLength_2D = med.getType6_textTailGutterLength_2D().getText().trim();

			this.db_saveAs_2D.add(TextLabel.lblHeadGutterHeight_type6_2D+"="+this.type6_textHeadGutterHeight_2D);
			this.db_saveAs_2D.add(TextLabel.lblHeadGutterLength_type6_2D+"="+this.type6_textHeadGutterLength_2D);
			this.db_saveAs_2D.add(TextLabel.lblTailGutterHeight_type6_2D+"="+this.type6_textTailGutterHeight_2D);
			this.db_saveAs_2D.add(TextLabel.lblTailGutterLength_type6_2D+"="+this.type6_textTailGutterLength_2D);

		}else if(this.comboType_2D.equals(ComboLabel.TYPE7)){
			this.type7_textHeadGutterHeight_2D = med.getType7_textHeadGutterHeight_2D().getText().trim();
			this.type7_textGutterLength_2D = med.getType7_textGutterLength_2D().getText().trim();
			this.type7_textGutterLengthLength_2D = med.getType7_textGutterLengthLength_2D().getText().trim();
			this.type7_textGutterWidthLength_2D = med.getType7_textGutterWidthLength_2D().getText().trim();

			this.db_saveAs_2D.add(TextLabel.lblHeadGutterHeight_type7_2D+"="+this.type7_textHeadGutterHeight_2D);
			this.db_saveAs_2D.add(TextLabel.lblGutterLength_type7_2D+"="+this.type7_textGutterLength_2D);
			this.db_saveAs_2D.add(TextLabel.lblGutterLengthLength_type7_2D+"="+this.type7_textGutterLengthLength_2D);
			this.db_saveAs_2D.add(TextLabel.lblGutterWidthLength_type7_2D+"="+this.type7_textGutterWidthLength_2D);
		}
		// */
		//--------------------------------------------		
		this.textThicknessElementNum_2D = med.getTextThicknessElementNum_2D().getText().trim();
		//this.textWidthAspectRatio_2D = med.getTextWidthAspectRatio_2D().getText().trim();
		this.textLengthAspectRatio_2D = med.getTextLengthAspectRatio_2D().getText().trim();
		CalcElementNumber_2D();
		// version3 update
		//CreateRoll();
		this.textElementNumber_2D = med.getTextElementNumber_2D().getText().trim();

		this.db_saveAs_2D.add(TextLabel.lblThicknessElementNum_2D+"="+this.textThicknessElementNum_2D);
		//this.db_saveAs_2D.add(TextLabel.lblWidthAspectRatio_2D+"="+this.textWidthAspectRatio_2D);
		this.db_saveAs_2D.add(TextLabel.lblLengthAspectRatio_2D+"="+this.textLengthAspectRatio_2D);
		this.db_saveAs_2D.add(TextLabel.lblElementNumber_2D+"="+this.textElementNumber_2D);
		//--------------------------------------------
		this.textPlateVelocity_2D = med.getTextPlateVelocity_2D().getText().trim();
		this.textTemperatureStart_2D = med.getTextTemperatureStart_2D().getText().trim();
		this.textTemperatureEnd_2D = med.getTextTemperatureEnd_2D().getText().trim();
		//update version2 2016.01.27
		this.textPassLineOffset_2D = med.getTextPassLineOffset_2D().getText().trim();


		this.db_saveAs_2D.add(TextLabel.lblPlateVelocity_2D+"="+this.textPlateVelocity_2D);
		this.db_saveAs_2D.add(TextLabel.lblTemperatureStart_2D+"="+this.textTemperatureStart_2D);
		this.db_saveAs_2D.add(TextLabel.lblTemperatureEnd_2D+"="+this.textTemperatureEnd_2D);
		//update version2 2016.01.27
		this.db_saveAs_2D.add(TextLabel.lblPassLineOffset_2D+"="+this.textPassLineOffset_2D);
		//--------------------------------------------		
		this.spinnerUpperRollNum_2D = med.getSpinnerUpperRollNum_2D().getText().trim();
		this.spinnerLowerRollNum_2D = med.getSpinnerLowerRollNum_2D().getText().trim();
		this.textRollPitch_2D = med.getTextRollPitch_2D().getText().trim();
		//this.textRollLength_2D = calcRollLength_2D().trim();
		this.textEntryUpperRollGap_2D = med.getTextEntryUpperRollGap_2D().getText().trim();
		this.textEntryLowerRollGap_2D = med.getTextEntryLowerRollGap_2D().getText().trim();
		this.textExitUpperRollGap_2D = med.getTextExitUpperRollGap_2D().getText().trim();
		this.textExitLowerRollGap_2D = med.getTextExitLowerRollGap_2D().getText().trim();
		this.textRollFriction_2D = med.getTextRollFriction_2D().getText().trim();
		this.textRollDiameter_2D = med.getTextRollDiameter_2D().getText().trim();
		//update version2 2016.01.27
		/*
		if(med.getBtnRadioNone_RC_2D().getSelection()){
			this.RollCrownType_2D = med.getBtnRadioNone_RC_2D().getText().trim();
		}else{
			this.RollCrownType_2D = med.getBtnRadioApply_RC_2D().getText().trim();
		}
		//*/
		//this.textUpperRollCrown_2D = med.getTextUpperRollCrown_2D().getText().trim();
		//this.textLowerRollCrown_2D = med.getTextLowerRollCrown_2D().getText().trim();
		if(med.getBtnRadioRigid_MS_2D().getSelection()){
			this.MillStiffnessType_2D = med.getBtnRadioRigid_MS_2D().getText().trim();
		}else{
			this.MillStiffnessType_2D = med.getBtnRadioSpring_MS_2D().getText().trim();
		}
		this.textMillStiffness_2D = med.getTextMillStiffness_2D().getText().trim();


		//med.getTextRollLength_2D().setText(this.textRollLength_2D);

		this.db_saveAs_2D.add(TextLabel.lblUpperRollNumber_2D+"="+this.spinnerUpperRollNum_2D);
		this.db_saveAs_2D.add(TextLabel.lblLowerRollNumber_2D+"="+this.spinnerLowerRollNum_2D);
		this.db_saveAs_2D.add(TextLabel.lblRollPitch_2D+"="+this.textRollPitch_2D);
		//this.db_saveAs_2D.add(TextLabel.lblRollLength_2D+"="+this.textRollLength_2D);
		this.db_saveAs_2D.add(TextLabel.lblEntryUpperRollGap_2D+"="+this.textEntryUpperRollGap_2D);
		this.db_saveAs_2D.add(TextLabel.lblEntryLowerRollGap_2D+"="+this.textEntryLowerRollGap_2D);
		this.db_saveAs_2D.add(TextLabel.lblExitUpperRollGap_2D+"="+this.textExitUpperRollGap_2D);
		this.db_saveAs_2D.add(TextLabel.lblExitLowerRollGap_2D+"="+this.textExitLowerRollGap_2D);
		this.db_saveAs_2D.add(TextLabel.lblRollFriction_2D+"="+this.textRollFriction_2D);
		this.db_saveAs_2D.add(TextLabel.lblRollDiameter_2D+"="+this.textRollDiameter_2D);
		//update version2 2016.01.27
		//this.db_saveAs_2D.add(TextLabel.lblRollCrownType_2D+"="+this.RollCrownType_2D);
		//this.db_saveAs_2D.add(TextLabel.lblUpperRollCrown_2D+"="+this.textUpperRollCrown_2D);
		//this.db_saveAs_2D.add(TextLabel.lblLowerRollCrown_2D+"="+this.textLowerRollCrown_2D);
		this.db_saveAs_2D.add(TextLabel.lblMillStiffnessType_2D+"="+this.MillStiffnessType_2D);
		this.db_saveAs_2D.add(TextLabel.lblMillStiffness_2D+"="+this.textMillStiffness_2D);
		//update version3 
		this.textUpperEntryRollGapMovement_2D = med.getTextUpperEntryRollGapMovement_2D().getText().trim();
		this.textUpperExitRollGapMovement_2D = med.getTextUpperExitRollGapMovement_2D().getText().trim();
		this.textUpperRollGapStayingTime_2D = med.getTextUpperRollGapStayingTime_2D().getText().trim();
		this.textUpperRollGapMovingTime_2D = med.getTextUpperRollGapMovingTime_2D().getText().trim();
		this.textLowerEntryRollGapMovement_2D = med.getTextLowerEntryRollGapMovement_2D().getText().trim();
		this.textLowerExitRollGapMovement_2D = med.getTextLowerExitRollGapMovement_2D().getText().trim();
		this.textLowerRollGapStayingTime_2D = med.getTextLowerRollGapStayingTime_2D().getText().trim();
		this.textLowerRollGapMovingTime_2D = med.getTextLowerRollGapMovingTime_2D().getText().trim();

		this.db_saveAs_2D.add(TextLabel.lblUpperEntryRollGapMovement_2D+"="+this.textUpperEntryRollGapMovement_2D);
		this.db_saveAs_2D.add(TextLabel.lblUpperExitRollGapMovement_2D+"="+this.textUpperExitRollGapMovement_2D);
		this.db_saveAs_2D.add(TextLabel.lblUpperRollGapStayingTime_2D+"="+this.textUpperRollGapStayingTime_2D);
		this.db_saveAs_2D.add(TextLabel.lblUpperRollGapMovingTime_2D+"="+this.textUpperRollGapMovingTime_2D);
		this.db_saveAs_2D.add(TextLabel.lblLowerEntryRollGapMovement_2D+"="+this.textLowerEntryRollGapMovement_2D);
		this.db_saveAs_2D.add(TextLabel.lblLowerExitRollGapMovement_2D+"="+this.textLowerExitRollGapMovement_2D);
		this.db_saveAs_2D.add(TextLabel.lblLowerRollGapStayingTime_2D+"="+this.textLowerRollGapStayingTime_2D);
		this.db_saveAs_2D.add(TextLabel.lblLowerRollGapMovingTime_2D+"="+this.textLowerRollGapMovingTime_2D);

		if(med.getBtnNone_2D().getSelection()){
			this.HDRollType_2D = med.getBtnNone_2D().getText();
		}
		if(med.getBtnUpper_2D().getSelection()){
			this.HDRollType_2D = med.getBtnUpper_2D().getText();
		}
		if(med.getBtnLower_2D().getSelection()){
			this.HDRollType_2D = med.getBtnLower_2D().getText();
		}
		this.textFrontHDRollDia_2D = med.getTextFrontHDRollDia_2D().getText().trim();
		this.textFrontHDRollPitch_2D = med.getTextFrontHDRollPitch_2D().getText().trim();
		this.textFrontHDRollVericalPos_2D = med.getTextFrontHDRollVericalPos_2D().getText().trim();
		this.textRearHDRollDia_2D = med.getTextRearHDRollDia_2D().getText().trim();
		this.textRearHDRollPitch_2D = med.getTextRearHDRollPitch_2D().getText().trim();
		this.textRearHDRollVerticalPos_2D = med.getTextRearHDRollVerticalPos_2D().getText().trim();

		this.db_saveAs_2D.add(TextLabel.lblFrontHDRollDia_2D+"="+this.textFrontHDRollDia_2D);
		this.db_saveAs_2D.add(TextLabel.lblFrontHDRollPitch_2D+"="+this.textFrontHDRollPitch_2D);
		this.db_saveAs_2D.add(TextLabel.lblFrontHDRollVericalPos_2D+"="+this.textFrontHDRollVericalPos_2D);
		this.db_saveAs_2D.add(TextLabel.lblRearHDRollDia_2D+"="+this.textRearHDRollDia_2D);
		this.db_saveAs_2D.add(TextLabel.lblRearHDRollPitch_2D+"="+this.textRearHDRollPitch_2D);
		this.db_saveAs_2D.add(TextLabel.lblRearHDRollVerticalPos_2D+"="+this.textRearHDRollVerticalPos_2D);
		this.db_saveAs_2D.add(TextLabel.HDRollType_2D+"="+this.HDRollType_2D);

		try{
			for(UpTableDataContent obj : this.upTableDataList_2D){
				this.db_saveAs_2D.add(obj.getNo()+"="+obj.getDB());
			}
		}catch (Exception e){
			//System.out.println("[LevellerMain.saveAllData()] Table Data is empty.");
		}
		try{
			for(DownTableDataContent obj : this.downTableDataList_2D){
				this.db_saveAs_2D.add(obj.getNo()+"="+obj.getDB());
			}
		}catch (Exception e){
			//System.out.println("[LevellerMain.saveAllData()] Table Data is empty.");
		}

		//--------------------------------------------

		if(med.getBtnRadioConstant_YM_2D().getSelection()){
			this.YoungsModulusType_2D = med.getBtnRadioConstant_YM_2D().getText().trim();
		}else{
			this.YoungsModulusType_2D = med.getBtnRadioTable_YM_2D().getText().trim();
		}
		this.textYoungsModulus_2D = med.getTextYoungsModulus_2D().getText().trim();
		this.db_saveAs_2D.add(TextLabel.lblYoungsModulus_2D+"Type"+"="+this.YoungsModulusType_2D);
		this.db_saveAs_2D.add(TextLabel.lblYoungsModulus_2D+"="+this.textYoungsModulus_2D);


		if(med.getBtnRadioConstant_FS_2D().getSelection()){
			this.FlowStressType_2D = med.getBtnRadioConstant_FS_2D().getText().trim();
			//update version2 2017.01.27
			this.textYieldStrength_2D = med.getTextYieldStrength_2D().getText().trim();
			this.textTensileStrength_2D = med.getTextTensileStrength_2D().getText().trim();
			this.textElongation_2D = med.getTextElongation_2D().getText().trim();

			this.db_saveAs_2D.add(TextLabel.lblFlowStress_2D+"Type"+"="+this.FlowStressType_2D);
			this.db_saveAs_2D.add(TextLabel.lblYieldStrength_2D+"="+this.textYieldStrength_2D);
			this.db_saveAs_2D.add(TextLabel.lblTensileStrength_2D+"="+this.textTensileStrength_2D);
			this.db_saveAs_2D.add(TextLabel.lblElongation_2D+"="+this.textElongation_2D);

		}else{
			this.FlowStressType_2D = med.getBtnRadioTable_FS_2D().getText().trim();
			//update version2 2017.01.27
			this.textFlowStress_2D = med.getTextFlowStress_2D().getText().trim();
			this.db_saveAs_2D.add(TextLabel.lblFlowStress_2D+"Type"+"="+this.FlowStressType_2D);
			this.db_saveAs_2D.add(TextLabel.lblFlowStress_2D+"="+this.textFlowStress_2D);
		}

		if(med.getBtnRadioConstant_TEC_2D().getSelection()){
			this.ThermalExpansionCoefficientType_2D = med.getBtnRadioConstant_TEC_2D().getText().trim();
		}else{
			this.ThermalExpansionCoefficientType_2D = med.getBtnRadioTable_TEC_2D().getText().trim();
		}
		this.textThermalExpansionCoefficient_2D = med.getTextThermalExpansionCoefficient_2D().getText().trim();
		if(med.getBtnRadioConstant_PR_2D().getSelection()){
			this.PoissonsRatioType_2D = med.getBtnRadioConstant_PR_2D().getText().trim();
		}else{
			this.PoissonsRatioType_2D = med.getBtnRadioTable_PR_2D().getText().trim();
		}
		this.textPoissonsRatio_2D = med.getTextPoissonsRatio_2D().getText().trim();
		this.textMassDensity_2D = med.getTextMassDensity_2D().getText().trim();



		this.db_saveAs_2D.add(TextLabel.lblThermalExpansionCoefficient_2D+"Type"+"="+this.ThermalExpansionCoefficientType_2D);
		this.db_saveAs_2D.add(TextLabel.lblThermalExpansionCoefficient_2D+"="+this.textThermalExpansionCoefficient_2D);
		this.db_saveAs_2D.add(TextLabel.lblPoissonsRatio_2D+"Type"+"="+this.PoissonsRatioType_2D);
		this.db_saveAs_2D.add(TextLabel.lblPoissonsRatio_2D+"="+this.textPoissonsRatio_2D);
		this.db_saveAs_2D.add(TextLabel.lblMassDensity_2D+"="+this.textMassDensity_2D);

		//--------------------------------------------
		this.textSolvingTime_2D = med.getTextSolvingTime_2D().getText().trim();
		this.textIncrementTime_2D = med.getTextIncrementTime_2D().getText().trim();
		this.btnParallelDDMUse_2D = med.getBtnParallelDDMUse_2D().getSelection();
		this.spinnerDomain_2D = med.getSpinnerDomain_2D().getText().trim();
		this.btnParallelMultiThreadUse_2D = med.getBtnParallelMultiThreadUse_2D().getSelection();
		this.spinnerThread_2D = med.getSpinnerThread_2D().getText().trim();

		this.db_saveAs_2D.add(TextLabel.lblSolvingTime_2D+"="+this.textSolvingTime_2D);
		this.db_saveAs_2D.add(TextLabel.lblIncrementTime_2D+"="+this.textIncrementTime_2D);
		this.db_saveAs_2D.add(TextLabel.lblParallelDDM_2D+"="+this.btnParallelDDMUse_2D);
		this.db_saveAs_2D.add(TextLabel.lblDomain_2D+"="+this.spinnerDomain_2D);
		this.db_saveAs_2D.add(TextLabel.lblParallelMultiThread_2D+"="+this.btnParallelMultiThreadUse_2D);
		this.db_saveAs_2D.add(TextLabel.lblThread_2D+"="+this.spinnerThread_2D);
		
	}
	
	
	// 여기다 proc 만들기 jsclubb
	public void Export(){
		try{
			deleteOldFiles();
			
			this.SaveLeveller();
			/*
			if(this.comboType.equals(ComboLabel.TYPE1)){
				main_flat mainFlatProc = new main_flat();
				mainFlatProc.running();				
			}else if(this.comboType.equals(ComboLabel.TYPE2)){
				main_edgeWave mainEdgeWaveProc = new main_edgeWave();
				mainEdgeWaveProc.running();
			}else if(this.comboType.equals(ComboLabel.TYPE3)){
				main_centerWave mainCenterWave = new main_centerWave();
				mainCenterWave.running();
			}else if(this.comboType.equals(ComboLabel.TYPE4)){
				main_singleGutter mainSingleGutter = new main_singleGutter();
				mainSingleGutter.running();
			}else if(this.comboType.equals(ComboLabel.TYPE5)){
				main_partialGutter mainPartialGutter = new main_partialGutter();
				mainPartialGutter.running();
			}else if(this.comboType.equals(ComboLabel.TYPE6)){
				main_doubleGutter mainDoubleGutter = new main_doubleGutter();
				mainDoubleGutter.running();
			}else if(this.comboType.equals(ComboLabel.TYPE7)){
				main_islandGutter mainIslandGutter = new main_islandGutter();
				mainIslandGutter.running();
			}
			*/
			String procFolder = myUtil.setPath(this.workspace, "proc");
			ProcMaker obj = new ProcMaker();
			obj.running("3D", this.comboType, procFolder);
			
		}catch(Exception e){
			String msg = "ERROR - Export files";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		
		if(this.exportMSG.getInfoList().size()!=0){
			String msg = "[Export Procedure Log]\n\n";
			for(String data : this.exportMSG.getInfoList()){
				msg = msg + data + "\n";
			}
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),msg);
			messageDlg.open();
			exportMSG.clearList();
			
			
			if(myUtil.checkOS().equals("window")){
				try{
					String procPath = myUtil.setPath(this.workspace, "proc");
					Runtime.getRuntime().exec("explorer "+ procPath);
					writeRunBat();
				}catch(Exception e){
					String msg2 = "ERROR - Open Proc Folder";
					MessageDlg messageDlg2 = new MessageDlg(Display.getCurrent().getActiveShell(),msg2);
					messageDlg2.open();
				}
			}
		}		
	}
	
	public void Export_2D(){
		try{
			deleteOldFiles();
			
			this.SaveLeveller_2D();
			/*
			if(this.comboType_2D.equals(ComboLabel.TYPE1)){
				main_flat mainFlatProc = new main_flat();
				mainFlatProc.running();				
			}else if(this.comboType_2D.equals(ComboLabel.TYPE2)){
				main_edgeWave mainEdgeWaveProc = new main_edgeWave();
				mainEdgeWaveProc.running();
			}else if(this.comboType_2D.equals(ComboLabel.TYPE3)){
				main_centerWave mainCenterWave = new main_centerWave();
				mainCenterWave.running();
			}else if(this.comboType_2D.equals(ComboLabel.TYPE4)){
				main_singleGutter mainSingleGutter = new main_singleGutter();
				mainSingleGutter.running();
			}else if(this.comboType_2D.equals(ComboLabel.TYPE5)){
				main_partialGutter mainPartialGutter = new main_partialGutter();
				mainPartialGutter.running();
			}else if(this.comboType_2D.equals(ComboLabel.TYPE6)){
				main_doubleGutter mainDoubleGutter = new main_doubleGutter();
				mainDoubleGutter.running();
			}else if(this.comboType_2D.equals(ComboLabel.TYPE7)){
				main_islandGutter mainIslandGutter = new main_islandGutter();
				mainIslandGutter.running();
			}
			// */
			String procFolder = myUtil.setPath(this.workspace, "proc");
			ProcMaker obj = new ProcMaker();
			obj.running("2D", this.comboType_2D, procFolder);
		}catch(Exception e){
			e.printStackTrace();
			String msg = "ERROR - Export files_2D";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
		
		if(this.exportMSG.getInfoList().size()!=0){
			String msg = "[Export Procedure Log_2D]\n\n";
			for(String data : this.exportMSG.getInfoList()){
				msg = msg + data + "\n";
			}
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),msg);
			messageDlg.open();
			exportMSG.clearList();
			
			
			if(myUtil.checkOS().equals("window")){
				try{
					String procPath = myUtil.setPath(this.workspace, "proc");
					Runtime.getRuntime().exec("explorer "+ procPath);
					writeRunBat();
				}catch(Exception e){
					String msg2 = "ERROR - Open Proc Folder";
					MessageDlg messageDlg2 = new MessageDlg(Display.getCurrent().getActiveShell(),msg2);
					messageDlg2.open();
				}
			}
			
		}		
		
	}
	
	public void deleteOldFiles(){
		try{
			String procPath = myUtil.setPath(this.workspace, "proc");
			boolean result = myUtil.deleteDirectory(new File(procPath));
			myUtil.makeDir(procPath);
			
		}catch(Exception e){
			String msg = "ERROR - Export(Delete old files)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
		}
	}
	
	public void writeRunBat(){
		ArrayList<String> cmdLine = new ArrayList<String>();
		String procPath = myUtil.setPath(this.workspace, "proc");
		String runBatPath = myUtil.setPath(procPath, "ReadyToSolving.bat");
		String runFolder = myUtil.setPath(this.workspace, "solving");
		String token = "\"";
		cmdLine.add("@echo off");
		cmdLine.add("md "+token+runFolder+token);
		cmdLine.add("copy *.f "+token+runFolder+token);
		cmdLine.add("copy *.proc "+token+runFolder+token);
		cmdLine.add("copy *.py "+token+runFolder+token);
		cmdLine.add("explorer "+token+runFolder+token);
		//cmdLine.add("@pause");
		
		Writer writer = new Writer(runBatPath,cmdLine);
		writer.running_bat();
		cmdLine.clear();
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	//
	//
	//				get set method 
	//
	//
	public String getTextModelName() {
		return textModelName;
	}

	public void setTextModelName(String textModelName) {
		this.textModelName = textModelName;
	}
	
	public String getcomboType() {
		return comboType;
	}

	public void setcomboType(String comboType) {
		this.comboType = comboType;
	}

	public String getTextWidth() {
		return textWidth;
	}

	public void setTextWidth(String textWidth) {
		this.textWidth = textWidth;
	}

	public String getTextLength() {
		return textLength;
	}

	public void setTextLength(String textLength) {
		this.textLength = textLength;
	}

	public String getTextThickness() {
		return textThickness;
	}

	public void setTextThickness(String textThickness) {
		this.textThickness = textThickness;
	}

	public String getType2_textLeftEdgeWavePitch() {
		return type2_textLeftEdgeWavePitch;
	}

	public void setType2_textLeftEdgeWavePitch(String type2_textLeftEdgeWavePitch) {
		this.type2_textLeftEdgeWavePitch = type2_textLeftEdgeWavePitch;
	}

	public String getType2_textRightEdgeWavePitch() {
		return type2_textRightEdgeWavePitch;
	}

	public void setType2_textRightEdgeWavePitch(String type2_textRightEdgeWavePitch) {
		this.type2_textRightEdgeWavePitch = type2_textRightEdgeWavePitch;
	}

	public String getType2_textLeftEdgeWaveHeight() {
		return type2_textLeftEdgeWaveHeight;
	}

	public void setType2_textLeftEdgeWaveHeight(String type2_textLeftEdgeWaveHeight) {
		this.type2_textLeftEdgeWaveHeight = type2_textLeftEdgeWaveHeight;
	}

	public String getType2_textRightEdgeWaveHeight() {
		return type2_textRightEdgeWaveHeight;
	}

	public void setType2_textRightEdgeWaveHeight(
			String type2_textRightEdgeWaveHeight) {
		this.type2_textRightEdgeWaveHeight = type2_textRightEdgeWaveHeight;
	}

	public String getType2_textLeftEdgeWavePhase() {
		return type2_textLeftEdgeWavePhase;
	}

	public void setType2_textLeftEdgeWavePhase(String type2_textLeftEdgeWavePhase) {
		this.type2_textLeftEdgeWavePhase = type2_textLeftEdgeWavePhase;
	}

	public String getType2_textRightEdgeWavePhase() {
		return type2_textRightEdgeWavePhase;
	}

	public void setType2_textRightEdgeWavePhase(String type2_textRightEdgeWavePhase) {
		this.type2_textRightEdgeWavePhase = type2_textRightEdgeWavePhase;
	}
	
	public String getType3_textWavePitch() {
		return type3_textWavePitch;
	}

	public void setType3_textWavePitch(String type3_textWavePitch) {
		this.type3_textWavePitch = type3_textWavePitch;
	}

	public String getType3_textWaveHeight() {
		return type3_textWaveHeight;
	}

	public void setType3_textWaveHeight(String type3_textWaveHeight) {
		this.type3_textWaveHeight = type3_textWaveHeight;
	}

	public String getType4_textGutterHeight() {
		return type4_textGutterHeight;
	}

	public void setType4_textGutterHeight(String type4_textGutterHeight) {
		this.type4_textGutterHeight = type4_textGutterHeight;
	}

	public String getType5_textGutterHeight() {
		return type5_textGutterHeight;
	}

	public void setType5_textGutterHeight(String type5_textGutterHeight) {
		this.type5_textGutterHeight = type5_textGutterHeight;
	}

	public String getType5_textGutterLength() {
		return type5_textGutterLength;
	}

	public void setType5_textGutterLength(String type5_textGutterLength) {
		this.type5_textGutterLength = type5_textGutterLength;
	}

	public String getType6_textHeadGutterHeight() {
		return type6_textHeadGutterHeight;
	}

	public void setType6_textHeadGutterHeight(String type6_textHeadGutterHeight) {
		this.type6_textHeadGutterHeight = type6_textHeadGutterHeight;
	}

	public String getType6_textHeadGutterLength() {
		return type6_textHeadGutterLength;
	}

	public void setType6_textHeadGutterLength(String type6_textHeadGutterLength) {
		this.type6_textHeadGutterLength = type6_textHeadGutterLength;
	}

	public String getType6_textTailGutterHeight() {
		return type6_textTailGutterHeight;
	}

	public void setType6_textTailGutterHeight(String type6_textTailGutterHeight) {
		this.type6_textTailGutterHeight = type6_textTailGutterHeight;
	}

	public String getType6_textTailGutterLength() {
		return type6_textTailGutterLength;
	}

	public void setType6_textTailGutterLength(String type6_textTailGutterLength) {
		this.type6_textTailGutterLength = type6_textTailGutterLength;
	}

	public String getType7_textHeadGutterHeight() {
		return type7_textHeadGutterHeight;
	}

	public void setType7_textHeadGutterHeight(String type7_textHeadGutterHeight) {
		this.type7_textHeadGutterHeight = type7_textHeadGutterHeight;
	}

	public String getType7_textGutterLength() {
		return type7_textGutterLength;
	}

	public void setType7_textGutterLength(String type7_textGutterLength) {
		this.type7_textGutterLength = type7_textGutterLength;
	}

	public String getType7_textGutterLengthLength() {
		return type7_textGutterLengthLength;
	}

	public void setType7_textGutterLengthLength(String type7_textGutterLengthLength) {
		this.type7_textGutterLengthLength = type7_textGutterLengthLength;
	}

	public String getType7_textGutterWidthLength() {
		return type7_textGutterWidthLength;
	}

	public void setType7_textGutterWidthLength(String type7_textGutterWidthLength) {
		this.type7_textGutterWidthLength = type7_textGutterWidthLength;
	}

	public String getTextThicknessElementNum() {
		return textThicknessElementNum;
	}

	public void setTextThicknessElementNum(String textThicknessElementNum) {
		this.textThicknessElementNum = textThicknessElementNum;
	}

	public String getTextWidthAspectRatio() {
		return textWidthAspectRatio;
	}

	public void setTextWidthAspectRatio(String textWidthAspectRatio) {
		this.textWidthAspectRatio = textWidthAspectRatio;
	}

	public String getTextLengthAspectRatio() {
		return textLengthAspectRatio;
	}

	public void setTextLengthAspectRatio(String textLengthAspectRatio) {
		this.textLengthAspectRatio = textLengthAspectRatio;
	}

	public String getTextElementNumber() {
		return textElementNumber;
	}

	public void setTextElementNumber(String textElementNumber) {
		this.textElementNumber = textElementNumber;
	}

	public String getTextPlateVelocity() {
		return textPlateVelocity;
	}

	public void setTextPlateVelocity(String textPlateVelocity) {
		this.textPlateVelocity = textPlateVelocity;
	}

	public String getTextTemperatureStart() {
		return textTemperatureStart;
	}

	public void setTextTemperatureStart(String textTemperatureStart) {
		this.textTemperatureStart = textTemperatureStart;
	}

	public String getTextTemperatureEnd() {
		return textTemperatureEnd;
	}

	public void setTextTemperatureEnd(String textTemperatureEnd) {
		this.textTemperatureEnd = textTemperatureEnd;
	}
	
	public String getTextPassLineOffset() {
		return textPassLineOffset;
	}

	public void setTextPassLineOffset(String textPassLineOffset) {
		this.textPassLineOffset = textPassLineOffset;
	}
	
	public String getSpinnerUpperRollNum() {
		return spinnerUpperRollNum;
	}

	public void setSpinnerUpperRollNum(String spinnerUpperRollNum) {
		this.spinnerUpperRollNum = spinnerUpperRollNum;
	}

	public String getSpinnerLowerRollNum() {
		return spinnerLowerRollNum;
	}

	public void setSpinnerLowerRollNum(String spinnerLowerRollNum) {
		this.spinnerLowerRollNum = spinnerLowerRollNum;
	}

	public String getTextRollPitch() {
		return textRollPitch;
	}

	public void setTextRollPitch(String textRollPitch) {
		this.textRollPitch = textRollPitch;
	}

	public String getTextRollLength() {
		return textRollLength;
	}

	public void setTextRollLength(String textRollLength) {
		this.textRollLength = textRollLength;
	}

	public String getTextEntryUpperRollGap() {
		return textEntryUpperRollGap;
	}

	public void setTextEntryUpperRollGap(String textEntryUpperRollGap) {
		this.textEntryUpperRollGap = textEntryUpperRollGap;
	}

	public String getTextEntryLowerRollGap() {
		return textEntryLowerRollGap;
	}

	public void setTextEntryLowerRollGap(String textEntryLowerRollGap) {
		this.textEntryLowerRollGap = textEntryLowerRollGap;
	}

	public String getTextExitUpperRollGap() {
		return textExitUpperRollGap;
	}

	public void setTextExitUpperRollGap(String textExitUpperRollGap) {
		this.textExitUpperRollGap = textExitUpperRollGap;
	}

	public String getTextExitLowerRollGap() {
		return textExitLowerRollGap;
	}

	public void setTextExitLowerRollGap(String textExitLowerRollGap) {
		this.textExitLowerRollGap = textExitLowerRollGap;
	}

	public String getTextRollFriction() {
		return textRollFriction;
	}

	public void setTextRollFriction(String textRollFriction) {
		this.textRollFriction = textRollFriction;
	}

	public String getTextRollDiameter() {
		return textRollDiameter;
	}

	public void setTextRollDiameter(String textRollDiameter) {
		this.textRollDiameter = textRollDiameter;
	}
	
	public String getTextUpperRollCrown() {
		return textUpperRollCrown;
	}

	public void setTextUpperRollCrown(String textUpperRollCrown) {
		this.textUpperRollCrown = textUpperRollCrown;
	}
	
	public String getTextLowerRollCrown() {
		return textLowerRollCrown;
	}

	public void setTextLowerRollCrown(String textLowerRollCrown) {
		this.textLowerRollCrown = textLowerRollCrown;
	}

	public String getTextMillStiffness() {
		return textMillStiffness;
	}

	public void setTextMillStiffness(String textMillStiffness) {
		this.textMillStiffness = textMillStiffness;
	}
	/*
	public String getRollCrownType() {
		return RollCrownType;
	}

	public void setRollCrownType(String rollCrownType) {
		RollCrownType = rollCrownType;
	}
	//*/
	public String getMillStiffnessType() {
		return MillStiffnessType;
	}

	public void setMillStiffnessType(String millStiffnessType) {
		MillStiffnessType = millStiffnessType;
	}
	
	public ArrayList<UpTableDataContent> getUpTableDataList() {
		return upTableDataList;
	}

	public void setUpTableDataList(ArrayList<UpTableDataContent> upTableDataList) {
		this.upTableDataList = upTableDataList;
	}

	public ArrayList<DownTableDataContent> getDownTableDataList() {
		return downTableDataList;
	}

	public void setDownTableDataList(
			ArrayList<DownTableDataContent> downTableDataList) {
		this.downTableDataList = downTableDataList;
	}
	
	public String getTextYoungsModulus() {
		return textYoungsModulus;
	}

	public void setTextYoungsModulus(String textYoungsModulus) {
		this.textYoungsModulus = textYoungsModulus;
	}

	public String getTextFlowStress() {
		return textFlowStress;
	}

	public void setTextFlowStress(String textFlowStress) {
		this.textFlowStress = textFlowStress;
	}

	public String getTextThermalExpansionCoefficient() {
		return textThermalExpansionCoefficient;
	}

	public void setTextThermalExpansionCoefficient(
			String textThermalExpansionCoefficient) {
		this.textThermalExpansionCoefficient = textThermalExpansionCoefficient;
	}

	public String getTextMassDensity() {
		return textMassDensity;
	}

	public void setTextMassDensity(String textMassDensity) {
		this.textMassDensity = textMassDensity;
	}

	public String getTextPoissonsRatio() {
		return textPoissonsRatio;
	}

	public void setTextPoissonsRatio(String textPoissonsRatio) {
		this.textPoissonsRatio = textPoissonsRatio;
	}

	public String getYoungsModulusType() {
		return YoungsModulusType;
	}

	public void setYoungsModulusType(String youngsModulusType) {
		YoungsModulusType = youngsModulusType;
	}

	public String getFlowStressType() {
		return FlowStressType;
	}

	public void setFlowStressType(String flowStressType) {
		FlowStressType = flowStressType;
	}
	
	public String getTextYieldStrength() {
		return textYieldStrength;
	}

	public void setTextYieldStrength(String textYieldStrength) {
		this.textYieldStrength = textYieldStrength;
	}

	public String getTextTensileStrength() {
		return textTensileStrength;
	}

	public void setTextTensileStrength(String textTensileStrength) {
		this.textTensileStrength = textTensileStrength;
	}

	public String getTextElongation() {
		return textElongation;
	}

	public void setTextElongation(String textElongation) {
		this.textElongation = textElongation;
	}
	public String getThermalExpansionCoefficientType() {
		return ThermalExpansionCoefficientType;
	}

	public void setThermalExpansionCoefficientType(
			String thermalExpansionCoefficientType) {
		ThermalExpansionCoefficientType = thermalExpansionCoefficientType;
	}
	
	public String getPoissonsRatioType() {
		return PoissonsRatioType;
	}

	public void setPoissonsRatioType(String poissonsRatioType) {
		PoissonsRatioType = poissonsRatioType;
	}
	/*
	public String getTextFriction() {
		return textFriction;
	}

	public void setTextFriction(String textFriction) {
		this.textFriction = textFriction;
	}
	 */
	public String getTextSolvingTime() {
		return textSolvingTime;
	}

	public void setTextSolvingTime(String textSolvingTime) {
		this.textSolvingTime = textSolvingTime;
	}

	public String getTextIncrementTime() {
		return textIncrementTime;
	}

	public void setTextIncrementTime(String textIncrementTime) {
		this.textIncrementTime = textIncrementTime;
	}

	public boolean isBtnParallelDDMUse() {
		return btnParallelDDMUse;
	}

	public void setBtnParallelDDMUse(boolean btnParallelDDMUse) {
		this.btnParallelDDMUse = btnParallelDDMUse;
	}

	public String getSpinnerDomain() {
		return spinnerDomain;
	}

	public void setSpinnerDomain(String spinnerDomain) {
		this.spinnerDomain = spinnerDomain;
	}

	public boolean isbtnParallelMultiThreadUse() {
		return btnParallelMultiThreadUse;
	}

	public void setbtnParallelMultiThreadUse(boolean btnParallelMultiThreadUse) {
		this.btnParallelMultiThreadUse = btnParallelMultiThreadUse;
	}

	public String getSpinnerThread() {
		return spinnerThread;
	}

	public void setSpinnerThread(String spinnerThread) {
		this.spinnerThread = spinnerThread;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}
	
	public exportInfo getExportMSG() {
		return exportMSG;
	}

	public void setExportMSG(exportInfo exportMSG) {
		this.exportMSG = exportMSG;
	}

	public String getTextUpperEntryRollGapMovement() {
		return textUpperEntryRollGapMovement;
	}

	public void setTextUpperEntryRollGapMovement(
			String textUpperEntryRollGapMovement) {
		this.textUpperEntryRollGapMovement = textUpperEntryRollGapMovement;
	}

	public String getTextUpperExitRollGapMovement() {
		return textUpperExitRollGapMovement;
	}

	public void setTextUpperExitRollGapMovement(String textUpperExitRollGapMovement) {
		this.textUpperExitRollGapMovement = textUpperExitRollGapMovement;
	}

	public String getTextUpperRollGapStayingTime() {
		return textUpperRollGapStayingTime;
	}

	public void setTextUpperRollGapStayingTime(String textUpperRollGapStayingTime) {
		this.textUpperRollGapStayingTime = textUpperRollGapStayingTime;
	}

	public String getTextUpperRollGapMovingTime() {
		return textUpperRollGapMovingTime;
	}

	public void setTextUpperRollGapMovingTime(String textUpperRollGapMovingTime) {
		this.textUpperRollGapMovingTime = textUpperRollGapMovingTime;
	}

	public String getTextLowerEntryRollGapMovement() {
		return textLowerEntryRollGapMovement;
	}

	public void setTextLowerEntryRollGapMovement(
			String textLowerEntryRollGapMovement) {
		this.textLowerEntryRollGapMovement = textLowerEntryRollGapMovement;
	}

	public String getTextLowerExitRollGapMovement() {
		return textLowerExitRollGapMovement;
	}

	public void setTextLowerExitRollGapMovement(String textLowerExitRollGapMovement) {
		this.textLowerExitRollGapMovement = textLowerExitRollGapMovement;
	}

	public String getTextLowerRollGapStayingTime() {
		return textLowerRollGapStayingTime;
	}

	public void setTextLowerRollGapStayingTime(String textLowerRollGapStayingTime) {
		this.textLowerRollGapStayingTime = textLowerRollGapStayingTime;
	}

	public String getTextLowerRollGapMovingTime() {
		return textLowerRollGapMovingTime;
	}

	public void setTextLowerRollGapMovingTime(String textLowerRollGapMovingTime) {
		this.textLowerRollGapMovingTime = textLowerRollGapMovingTime;
	}

	public String getTextFrontHDRollDia() {
		return textFrontHDRollDia;
	}

	public void setTextFrontHDRollDia(String textFrontHDRollDia) {
		this.textFrontHDRollDia = textFrontHDRollDia;
	}

	public String getTextFrontHDRollPitch() {
		return textFrontHDRollPitch;
	}

	public void setTextFrontHDRollPitch(String textFrontHDRollPitch) {
		this.textFrontHDRollPitch = textFrontHDRollPitch;
	}

	public String getTextFrontHDRollVericalPos() {
		return textFrontHDRollVericalPos;
	}

	public void setTextFrontHDRollVericalPos(String textFrontHDRollVericalPos) {
		this.textFrontHDRollVericalPos = textFrontHDRollVericalPos;
	}

	public String getTextRearHDRollDia() {
		return textRearHDRollDia;
	}

	public void setTextRearHDRollDia(String textRearHDRollDia) {
		this.textRearHDRollDia = textRearHDRollDia;
	}

	public String getTextRearHDRollPitch() {
		return textRearHDRollPitch;
	}

	public void setTextRearHDRollPitch(String textRearHDRollPitch) {
		this.textRearHDRollPitch = textRearHDRollPitch;
	}

	public String getTextRearHDRollVerticalPos() {
		return textRearHDRollVerticalPos;
	}

	public void setTextRearHDRollVerticalPos(String textRearHDRollVerticalPos) {
		this.textRearHDRollVerticalPos = textRearHDRollVerticalPos;
	}

	public String getHDRollType() {
		return HDRollType;
	}

	public void setHDRollType(String hDRollType) {
		HDRollType = hDRollType;
	}
	
	public Map<String, String> getInitValueMap() {
		return InitValueMap;
	}
	//
	//
	//
	//
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////

	public String getLevellerType() {
		return LevellerType;
	}

	public void setLevellerType(String levellerType) {
		LevellerType = levellerType;
	}
	/*
	public Map<String, String> getInitValueMap_2D() {
		return InitValueMap_2D;
	}

	public void setInitValueMap_2D(Map<String, String> initValueMap_2D) {
		InitValueMap_2D = initValueMap_2D;
	}
	 */
	public String getTextWidth_2D() {
		return textWidth_2D;
	}

	public void setTextWidth_2D(String textWidth_2D) {
		this.textWidth_2D = textWidth_2D;
	}

	public String getTextLength_2D() {
		return textLength_2D;
	}

	public void setTextLength_2D(String textLength_2D) {
		this.textLength_2D = textLength_2D;
	}

	public String getTextThickness_2D() {
		return textThickness_2D;
	}

	public void setTextThickness_2D(String textThickness_2D) {
		this.textThickness_2D = textThickness_2D;
	}
	/*
	public String getType2_textLeftEdgeWavePitch_2D() {
		return type2_textLeftEdgeWavePitch_2D;
	}

	public void setType2_textLeftEdgeWavePitch_2D(
			String type2_textLeftEdgeWavePitch_2D) {
		this.type2_textLeftEdgeWavePitch_2D = type2_textLeftEdgeWavePitch_2D;
	}

	public String getType2_textRightEdgeWavePitch_2D() {
		return type2_textRightEdgeWavePitch_2D;
	}

	public void setType2_textRightEdgeWavePitch_2D(
			String type2_textRightEdgeWavePitch_2D) {
		this.type2_textRightEdgeWavePitch_2D = type2_textRightEdgeWavePitch_2D;
	}

	public String getType2_textLeftEdgeWaveHeight_2D() {
		return type2_textLeftEdgeWaveHeight_2D;
	}

	public void setType2_textLeftEdgeWaveHeight_2D(
			String type2_textLeftEdgeWaveHeight_2D) {
		this.type2_textLeftEdgeWaveHeight_2D = type2_textLeftEdgeWaveHeight_2D;
	}

	public String getType2_textRightEdgeWaveHeight_2D() {
		return type2_textRightEdgeWaveHeight_2D;
	}

	public void setType2_textRightEdgeWaveHeight_2D(
			String type2_textRightEdgeWaveHeight_2D) {
		this.type2_textRightEdgeWaveHeight_2D = type2_textRightEdgeWaveHeight_2D;
	}

	public String getType2_textLeftEdgeWavePhase_2D() {
		return type2_textLeftEdgeWavePhase_2D;
	}

	public void setType2_textLeftEdgeWavePhase_2D(
			String type2_textLeftEdgeWavePhase_2D) {
		this.type2_textLeftEdgeWavePhase_2D = type2_textLeftEdgeWavePhase_2D;
	}

	public String getType2_textRightEdgeWavePhase_2D() {
		return type2_textRightEdgeWavePhase_2D;
	}

	public void setType2_textRightEdgeWavePhase_2D(
			String type2_textRightEdgeWavePhase_2D) {
		this.type2_textRightEdgeWavePhase_2D = type2_textRightEdgeWavePhase_2D;
	}

	public String getType3_textWavePitch_2D() {
		return type3_textWavePitch_2D;
	}

	public void setType3_textWavePitch_2D(String type3_textWavePitch_2D) {
		this.type3_textWavePitch_2D = type3_textWavePitch_2D;
	}

	public String getType3_textWaveHeight_2D() {
		return type3_textWaveHeight_2D;
	}

	public void setType3_textWaveHeight_2D(String type3_textWaveHeight_2D) {
		this.type3_textWaveHeight_2D = type3_textWaveHeight_2D;
	}

	public String getType4_textGutterHeight_2D() {
		return type4_textGutterHeight_2D;
	}

	public void setType4_textGutterHeight_2D(String type4_textGutterHeight_2D) {
		this.type4_textGutterHeight_2D = type4_textGutterHeight_2D;
	}

	public String getType5_textGutterHeight_2D() {
		return type5_textGutterHeight_2D;
	}

	public void setType5_textGutterHeight_2D(String type5_textGutterHeight_2D) {
		this.type5_textGutterHeight_2D = type5_textGutterHeight_2D;
	}

	public String getType5_textGutterLength_2D() {
		return type5_textGutterLength_2D;
	}

	public void setType5_textGutterLength_2D(String type5_textGutterLength_2D) {
		this.type5_textGutterLength_2D = type5_textGutterLength_2D;
	}

	public String getType6_textHeadGutterHeight_2D() {
		return type6_textHeadGutterHeight_2D;
	}

	public void setType6_textHeadGutterHeight_2D(
			String type6_textHeadGutterHeight_2D) {
		this.type6_textHeadGutterHeight_2D = type6_textHeadGutterHeight_2D;
	}

	public String getType6_textHeadGutterLength_2D() {
		return type6_textHeadGutterLength_2D;
	}

	public void setType6_textHeadGutterLength_2D(
			String type6_textHeadGutterLength_2D) {
		this.type6_textHeadGutterLength_2D = type6_textHeadGutterLength_2D;
	}

	public String getType6_textTailGutterHeight_2D() {
		return type6_textTailGutterHeight_2D;
	}

	public void setType6_textTailGutterHeight_2D(
			String type6_textTailGutterHeight_2D) {
		this.type6_textTailGutterHeight_2D = type6_textTailGutterHeight_2D;
	}

	public String getType6_textTailGutterLength_2D() {
		return type6_textTailGutterLength_2D;
	}

	public void setType6_textTailGutterLength_2D(
			String type6_textTailGutterLength_2D) {
		this.type6_textTailGutterLength_2D = type6_textTailGutterLength_2D;
	}

	public String getType7_textHeadGutterHeight_2D() {
		return type7_textHeadGutterHeight_2D;
	}

	public void setType7_textHeadGutterHeight_2D(
			String type7_textHeadGutterHeight_2D) {
		this.type7_textHeadGutterHeight_2D = type7_textHeadGutterHeight_2D;
	}

	public String getType7_textGutterLength_2D() {
		return type7_textGutterLength_2D;
	}

	public void setType7_textGutterLength_2D(String type7_textGutterLength_2D) {
		this.type7_textGutterLength_2D = type7_textGutterLength_2D;
	}

	public String getType7_textGutterLengthLength_2D() {
		return type7_textGutterLengthLength_2D;
	}

	public void setType7_textGutterLengthLength_2D(
			String type7_textGutterLengthLength_2D) {
		this.type7_textGutterLengthLength_2D = type7_textGutterLengthLength_2D;
	}

	public String getType7_textGutterWidthLength_2D() {
		return type7_textGutterWidthLength_2D;
	}

	public void setType7_textGutterWidthLength_2D(
			String type7_textGutterWidthLength_2D) {
		this.type7_textGutterWidthLength_2D = type7_textGutterWidthLength_2D;
	}
	// */
	public String getTextThicknessElementNum_2D() {
		return textThicknessElementNum_2D;
	}

	public void setTextThicknessElementNum_2D(String textThicknessElementNum_2D) {
		this.textThicknessElementNum_2D = textThicknessElementNum_2D;
	}
	/*
	public String getTextWidthAspectRatio_2D() {
		return textWidthAspectRatio_2D;
	}

	public void setTextWidthAspectRatio_2D(String textWidthAspectRatio_2D) {
		this.textWidthAspectRatio_2D = textWidthAspectRatio_2D;
	}
	// */
	public String getTextLengthAspectRatio_2D() {
		return textLengthAspectRatio_2D;
	}

	public void setTextLengthAspectRatio_2D(String textLengthAspectRatio_2D) {
		this.textLengthAspectRatio_2D = textLengthAspectRatio_2D;
	}

	public String getTextElementNumber_2D() {
		return textElementNumber_2D;
	}

	public void setTextElementNumber_2D(String textElementNumber_2D) {
		this.textElementNumber_2D = textElementNumber_2D;
	}

	public String getTextPlateVelocity_2D() {
		return textPlateVelocity_2D;
	}

	public void setTextPlateVelocity_2D(String textPlateVelocity_2D) {
		this.textPlateVelocity_2D = textPlateVelocity_2D;
	}

	public String getTextTemperatureStart_2D() {
		return textTemperatureStart_2D;
	}

	public void setTextTemperatureStart_2D(String textTemperatureStart_2D) {
		this.textTemperatureStart_2D = textTemperatureStart_2D;
	}

	public String getTextTemperatureEnd_2D() {
		return textTemperatureEnd_2D;
	}

	public void setTextTemperatureEnd_2D(String textTemperatureEnd_2D) {
		this.textTemperatureEnd_2D = textTemperatureEnd_2D;
	}

	public String getTextPassLineOffset_2D() {
		return textPassLineOffset_2D;
	}

	public void setTextPassLineOffset_2D(String textPassLineOffset_2D) {
		this.textPassLineOffset_2D = textPassLineOffset_2D;
	}

	public String getSpinnerUpperRollNum_2D() {
		return spinnerUpperRollNum_2D;
	}

	public void setSpinnerUpperRollNum_2D(String spinnerUpperRollNum_2D) {
		this.spinnerUpperRollNum_2D = spinnerUpperRollNum_2D;
	}

	public String getSpinnerLowerRollNum_2D() {
		return spinnerLowerRollNum_2D;
	}

	public void setSpinnerLowerRollNum_2D(String spinnerLowerRollNum_2D) {
		this.spinnerLowerRollNum_2D = spinnerLowerRollNum_2D;
	}

	public String getTextRollPitch_2D() {
		return textRollPitch_2D;
	}

	public void setTextRollPitch_2D(String textRollPitch_2D) {
		this.textRollPitch_2D = textRollPitch_2D;
	}
	/*
	public String getTextRollLength_2D() {
		return textRollLength_2D;
	}

	public void setTextRollLength_2D(String textRollLength_2D) {
		this.textRollLength_2D = textRollLength_2D;
	}
	// */
	public String getTextEntryUpperRollGap_2D() {
		return textEntryUpperRollGap_2D;
	}

	public void setTextEntryUpperRollGap_2D(String textEntryUpperRollGap_2D) {
		this.textEntryUpperRollGap_2D = textEntryUpperRollGap_2D;
	}

	public String getTextEntryLowerRollGap_2D() {
		return textEntryLowerRollGap_2D;
	}

	public void setTextEntryLowerRollGap_2D(String textEntryLowerRollGap_2D) {
		this.textEntryLowerRollGap_2D = textEntryLowerRollGap_2D;
	}

	public String getTextExitUpperRollGap_2D() {
		return textExitUpperRollGap_2D;
	}

	public void setTextExitUpperRollGap_2D(String textExitUpperRollGap_2D) {
		this.textExitUpperRollGap_2D = textExitUpperRollGap_2D;
	}

	public String getTextExitLowerRollGap_2D() {
		return textExitLowerRollGap_2D;
	}

	public void setTextExitLowerRollGap_2D(String textExitLowerRollGap_2D) {
		this.textExitLowerRollGap_2D = textExitLowerRollGap_2D;
	}

	public String getTextRollFriction_2D() {
		return textRollFriction_2D;
	}

	public void setTextRollFriction_2D(String textRollFriction_2D) {
		this.textRollFriction_2D = textRollFriction_2D;
	}

	public String getTextRollDiameter_2D() {
		return textRollDiameter_2D;
	}

	public void setTextRollDiameter_2D(String textRollDiameter_2D) {
		this.textRollDiameter_2D = textRollDiameter_2D;
	}
	/*
	public String getTextUpperRollCrown_2D() {
		return textUpperRollCrown_2D;
	}

	public void setTextUpperRollCrown_2D(String textUpperRollCrown_2D) {
		this.textUpperRollCrown_2D = textUpperRollCrown_2D;
	}
	
	public String getTextLowerRollCrown_2D() {
		return textLowerRollCrown_2D;
	}

	public void setTextLowerRollCrown_2D(String textLowerRollCrown_2D) {
		this.textLowerRollCrown_2D = textLowerRollCrown_2D;
	}
	//*/
	/*
	public String getRollCrownType_2D() {
		return RollCrownType_2D;
	}

	public void setRollCrownType_2D(String rollCrownType_2D) {
		RollCrownType_2D = rollCrownType_2D;
	}
	//*/

	public String getTextMillStiffness_2D() {
		return textMillStiffness_2D;
	}

	public void setTextMillStiffness_2D(String textMillStiffness_2D) {
		this.textMillStiffness_2D = textMillStiffness_2D;
	}

	public String getMillStiffnessType_2D() {
		return MillStiffnessType_2D;
	}

	public void setMillStiffnessType_2D(String millStiffnessType_2D) {
		MillStiffnessType_2D = millStiffnessType_2D;
	}

	public String getTextUpperEntryRollGapMovement_2D() {
		return textUpperEntryRollGapMovement_2D;
	}

	public void setTextUpperEntryRollGapMovement_2D(
			String textUpperEntryRollGapMovement_2D) {
		this.textUpperEntryRollGapMovement_2D = textUpperEntryRollGapMovement_2D;
	}

	public String getTextUpperExitRollGapMovement_2D() {
		return textUpperExitRollGapMovement_2D;
	}

	public void setTextUpperExitRollGapMovement_2D(
			String textUpperExitRollGapMovement_2D) {
		this.textUpperExitRollGapMovement_2D = textUpperExitRollGapMovement_2D;
	}

	public String getTextUpperRollGapStayingTime_2D() {
		return textUpperRollGapStayingTime_2D;
	}

	public void setTextUpperRollGapStayingTime_2D(
			String textUpperRollGapStayingTime_2D) {
		this.textUpperRollGapStayingTime_2D = textUpperRollGapStayingTime_2D;
	}

	public String getTextUpperRollGapMovingTime_2D() {
		return textUpperRollGapMovingTime_2D;
	}

	public void setTextUpperRollGapMovingTime_2D(
			String textUpperRollGapMovingTime_2D) {
		this.textUpperRollGapMovingTime_2D = textUpperRollGapMovingTime_2D;
	}

	public String getTextLowerEntryRollGapMovement_2D() {
		return textLowerEntryRollGapMovement_2D;
	}

	public void setTextLowerEntryRollGapMovement_2D(
			String textLowerEntryRollGapMovement_2D) {
		this.textLowerEntryRollGapMovement_2D = textLowerEntryRollGapMovement_2D;
	}

	public String getTextLowerExitRollGapMovement_2D() {
		return textLowerExitRollGapMovement_2D;
	}

	public void setTextLowerExitRollGapMovement_2D(
			String textLowerExitRollGapMovement_2D) {
		this.textLowerExitRollGapMovement_2D = textLowerExitRollGapMovement_2D;
	}

	public String getTextLowerRollGapStayingTime_2D() {
		return textLowerRollGapStayingTime_2D;
	}

	public void setTextLowerRollGapStayingTime_2D(
			String textLowerRollGapStayingTime_2D) {
		this.textLowerRollGapStayingTime_2D = textLowerRollGapStayingTime_2D;
	}

	public String getTextLowerRollGapMovingTime_2D() {
		return textLowerRollGapMovingTime_2D;
	}

	public void setTextLowerRollGapMovingTime_2D(
			String textLowerRollGapMovingTime_2D) {
		this.textLowerRollGapMovingTime_2D = textLowerRollGapMovingTime_2D;
	}

	public String getTextFrontHDRollDia_2D() {
		return textFrontHDRollDia_2D;
	}

	public void setTextFrontHDRollDia_2D(String textFrontHDRollDia_2D) {
		this.textFrontHDRollDia_2D = textFrontHDRollDia_2D;
	}

	public String getTextFrontHDRollPitch_2D() {
		return textFrontHDRollPitch_2D;
	}

	public void setTextFrontHDRollPitch_2D(String textFrontHDRollPitch_2D) {
		this.textFrontHDRollPitch_2D = textFrontHDRollPitch_2D;
	}

	public String getTextFrontHDRollVericalPos_2D() {
		return textFrontHDRollVericalPos_2D;
	}

	public void setTextFrontHDRollVericalPos_2D(String textFrontHDRollVericalPos_2D) {
		this.textFrontHDRollVericalPos_2D = textFrontHDRollVericalPos_2D;
	}

	public String getTextRearHDRollDia_2D() {
		return textRearHDRollDia_2D;
	}

	public void setTextRearHDRollDia_2D(String textRearHDRollDia_2D) {
		this.textRearHDRollDia_2D = textRearHDRollDia_2D;
	}

	public String getTextRearHDRollPitch_2D() {
		return textRearHDRollPitch_2D;
	}

	public void setTextRearHDRollPitch_2D(String textRearHDRollPitch_2D) {
		this.textRearHDRollPitch_2D = textRearHDRollPitch_2D;
	}

	public String getTextRearHDRollVerticalPos_2D() {
		return textRearHDRollVerticalPos_2D;
	}

	public void setTextRearHDRollVerticalPos_2D(String textRearHDRollVerticalPos_2D) {
		this.textRearHDRollVerticalPos_2D = textRearHDRollVerticalPos_2D;
	}

	public String getHDRollType_2D() {
		return HDRollType_2D;
	}

	public void setHDRollType_2D(String hDRollType_2D) {
		HDRollType_2D = hDRollType_2D;
	}

	public ArrayList<UpTableDataContent> getUpTableDataList_2D() {
		return upTableDataList_2D;
	}

	public void setUpTableDataList_2D(
			ArrayList<UpTableDataContent> upTableDataList_2D) {
		this.upTableDataList_2D = upTableDataList_2D;
	}

	public ArrayList<DownTableDataContent> getDownTableDataList_2D() {
		return downTableDataList_2D;
	}

	public void setDownTableDataList_2D(
			ArrayList<DownTableDataContent> downTableDataList_2D) {
		this.downTableDataList_2D = downTableDataList_2D;
	}

	public String getTextYoungsModulus_2D() {
		return textYoungsModulus_2D;
	}

	public void setTextYoungsModulus_2D(String textYoungsModulus_2D) {
		this.textYoungsModulus_2D = textYoungsModulus_2D;
	}

	public String getYoungsModulusType_2D() {
		return YoungsModulusType_2D;
	}

	public void setYoungsModulusType_2D(String youngsModulusType_2D) {
		YoungsModulusType_2D = youngsModulusType_2D;
	}

	public String getTextFlowStress_2D() {
		return textFlowStress_2D;
	}

	public void setTextFlowStress_2D(String textFlowStress_2D) {
		this.textFlowStress_2D = textFlowStress_2D;
	}

	public String getFlowStressType_2D() {
		return FlowStressType_2D;
	}

	public void setFlowStressType_2D(String flowStressType_2D) {
		FlowStressType_2D = flowStressType_2D;
	}

	public String getTextYieldStrength_2D() {
		return textYieldStrength_2D;
	}

	public void setTextYieldStrength_2D(String textYieldStrength_2D) {
		this.textYieldStrength_2D = textYieldStrength_2D;
	}

	public String getTextTensileStrength_2D() {
		return textTensileStrength_2D;
	}

	public void setTextTensileStrength_2D(String textTensileStrength_2D) {
		this.textTensileStrength_2D = textTensileStrength_2D;
	}

	public String getTextElongation_2D() {
		return textElongation_2D;
	}

	public void setTextElongation_2D(String textElongation_2D) {
		this.textElongation_2D = textElongation_2D;
	}

	public String getTextThermalExpansionCoefficient_2D() {
		return textThermalExpansionCoefficient_2D;
	}

	public void setTextThermalExpansionCoefficient_2D(
			String textThermalExpansionCoefficient_2D) {
		this.textThermalExpansionCoefficient_2D = textThermalExpansionCoefficient_2D;
	}

	public String getThermalExpansionCoefficientType_2D() {
		return ThermalExpansionCoefficientType_2D;
	}

	public void setThermalExpansionCoefficientType_2D(
			String thermalExpansionCoefficientType_2D) {
		ThermalExpansionCoefficientType_2D = thermalExpansionCoefficientType_2D;
	}

	public String getTextMassDensity_2D() {
		return textMassDensity_2D;
	}

	public void setTextMassDensity_2D(String textMassDensity_2D) {
		this.textMassDensity_2D = textMassDensity_2D;
	}

	public String getTextPoissonsRatio_2D() {
		return textPoissonsRatio_2D;
	}

	public void setTextPoissonsRatio_2D(String textPoissonsRatio_2D) {
		this.textPoissonsRatio_2D = textPoissonsRatio_2D;
	}

	public String getPoissonsRatioType_2D() {
		return PoissonsRatioType_2D;
	}

	public void setPoissonsRatioType_2D(String poissonsRatioType_2D) {
		PoissonsRatioType_2D = poissonsRatioType_2D;
	}

	public String getTextSolvingTime_2D() {
		return textSolvingTime_2D;
	}

	public void setTextSolvingTime_2D(String textSolvingTime_2D) {
		this.textSolvingTime_2D = textSolvingTime_2D;
	}

	public String getTextIncrementTime_2D() {
		return textIncrementTime_2D;
	}

	public void setTextIncrementTime_2D(String textIncrementTime_2D) {
		this.textIncrementTime_2D = textIncrementTime_2D;
	}

	public boolean isBtnParallelDDMUse_2D() {
		return btnParallelDDMUse_2D;
	}

	public void setBtnParallelDDMUse_2D(boolean btnParallelDDMUse_2D) {
		this.btnParallelDDMUse_2D = btnParallelDDMUse_2D;
	}

	public String getSpinnerDomain_2D() {
		return spinnerDomain_2D;
	}

	public void setSpinnerDomain_2D(String spinnerDomain_2D) {
		this.spinnerDomain_2D = spinnerDomain_2D;
	}

	public boolean isBtnParallelMultiThreadUse_2D() {
		return btnParallelMultiThreadUse_2D;
	}

	public void setBtnParallelMultiThreadUse_2D(boolean btnParallelMultiThreadUse_2D) {
		this.btnParallelMultiThreadUse_2D = btnParallelMultiThreadUse_2D;
	}

	public String getSpinnerThread_2D() {
		return spinnerThread_2D;
	}

	public void setSpinnerThread_2D(String spinnerThread_2D) {
		this.spinnerThread_2D = spinnerThread_2D;
	}

	public String getType2_textWavePitch_2D() {
		return type2_textWavePitch_2D;
	}

	public void setType2_textWavePitch_2D(String type2_textWavePitch_2D) {
		this.type2_textWavePitch_2D = type2_textWavePitch_2D;
	}

	public String getType2_textWaveHeight_2D() {
		return type2_textWaveHeight_2D;
	}

	public void setType2_textWaveHeight_2D(String type2_textWaveHeight_2D) {
		this.type2_textWaveHeight_2D = type2_textWaveHeight_2D;
	}

	public String getType2_textWavePhase_2D() {
		return type2_textWavePhase_2D;
	}

	public void setType2_textWavePhase_2D(String type2_textWavePhase_2D) {
		this.type2_textWavePhase_2D = type2_textWavePhase_2D;
	}

	public String getType3_textFrontCurlHeight_2D() {
		return type3_textFrontCurlHeight_2D;
	}

	public void setType3_textFrontCurlHeight_2D(String type3_textFrontCurlHeight_2D) {
		this.type3_textFrontCurlHeight_2D = type3_textFrontCurlHeight_2D;
	}

	public String getType3_textFrontCurlLength_2D() {
		return type3_textFrontCurlLength_2D;
	}

	public void setType3_textFrontCurlLength_2D(String type3_textFrontCurlLength_2D) {
		this.type3_textFrontCurlLength_2D = type3_textFrontCurlLength_2D;
	}

	public String getType3_textRearCurlHeight_2D() {
		return type3_textRearCurlHeight_2D;
	}

	public void setType3_textRearCurlHeight_2D(String type3_textRearCurlHeight_2D) {
		this.type3_textRearCurlHeight_2D = type3_textRearCurlHeight_2D;
	}

	public String getType3_textRearCurlLength_2D() {
		return type3_textRearCurlLength_2D;
	}

	public void setType3_textRearCurlLength_2D(String type3_textRearCurlLength_2D) {
		this.type3_textRearCurlLength_2D = type3_textRearCurlLength_2D;
	}

	
	
	
	
	
}
