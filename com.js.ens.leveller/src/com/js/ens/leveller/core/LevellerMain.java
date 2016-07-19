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
import com.js.ens.leveller.proc.main_centerWave;
import com.js.ens.leveller.proc.main_doubleGutter;
import com.js.ens.leveller.proc.main_edgeWave;
import com.js.ens.leveller.proc.main_flat;
import com.js.ens.leveller.proc.main_islandGutter;
import com.js.ens.leveller.proc.main_partialGutter;
import com.js.ens.leveller.proc.main_singleGutter;
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
	private String textRollCrown;
	private String RollCrownType;
	private String textMillStiffness;
	private String MillStiffnessType;
	
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
	
	
	private String fdist = "50";
	
	private String workspace = null;
	private ArrayList<String> db = new ArrayList<String>();
	private ArrayList<String> db_saveAs;
	
	private exportInfo exportMSG;
	
	private Map<String,String> InitValueMap = new HashMap<String,String>();

	
	public Map<String, String> getInitValueMap() {
		return InitValueMap;
	}

	public LevellerMain(){
		exportMSG = new exportInfo();
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
		//----------------------------
		med.getCompositeRollParameter().setEnabled(false);
		//----------------------------
		med.getCompositeMaterialParameter().setEnabled(false);
		med.getCompositeSolvingOption().setEnabled(false);
		med.getBtnApply().setEnabled(false);
		
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
		//----------------------------
		med.getCompositeRollParameter().setEnabled(true);
		//----------------------------
		med.getCompositeMaterialParameter().setEnabled(true);
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
			med.getTextFlowStress().setEnabled(true);
			med.getTextYieldStrength().setEnabled(false);
			med.getTextTensileStrength().setEnabled(false);
			med.getTextElongation().setEnabled(false);
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
		med.getTextRollCrown().setText(InitValueMap.get("RollCrown"));
		med.getBtnRadioNone_RC().setSelection(false);
		med.getBtnRadioApply_RC().setSelection(true);
		med.getTextMillStiffness().setText(InitValueMap.get("MillStiffness"));
		med.getBtnRadioRigid_MS().setSelection(false);
		med.getBtnRadioSpring_MS().setSelection(true);
		
		if(med.getBtnRadioNone_RC().getSelection()){
			// None
			med.getTextRollCrown().setText(InitValueMap.get("RollCrown"));
			med.getTextRollCrown().setEnabled(false);
		}else {
			// Apply
			med.getTextRollCrown().setText(InitValueMap.get("RollCrown"));
			med.getTextRollCrown().setEnabled(true);
		}
		
		if(med.getBtnRadioRigid_MS().getSelection()){
			// Rigid
			med.getTextMillStiffness().setText(InitValueMap.get("MillStiffness"));
			med.getTextMillStiffness().setEnabled(false);
		}else {
			// Spring
			med.getTextMillStiffness().setText(InitValueMap.get("MillStiffness"));
			med.getTextMillStiffness().setEnabled(true);
		}
		
		
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
		
	}
	
	public void ChangePlateType(){
		String plateType = med.getComboType().getText();
		Image img = ImageDescriptor.createFromFile(View.class,ImagePath.Type0).createImage();
		med.getCompositeShapeParameterChild_1().setVisible(false);
		med.getCompositeShapeParameterChild_2().setVisible(false);
		med.getCompositeShapeParameterChild_3().setVisible(false);
		med.getCompositeShapeParameterChild_4().setVisible(false);
		med.getCompositeShapeParameterChild_5().setVisible(false);
		med.getCompositeShapeParameterChild_6().setVisible(false);
		med.getCompositeShapeParameterChild_7().setVisible(false);
		
		if(plateType.equals(ComboLabel.TYPE1)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type1).createImage();
			med.getCompositeShapeParameterChild_1().setVisible(true);
			
		}else if(plateType.equals(ComboLabel.TYPE2)){
			img = ImageDescriptor.createFromFile(View.class,ImagePath.Type2).createImage();
			med.getCompositeShapeParameterChild_2().setVisible(true);
			try{
				if(comboType.equals(plateType)){
					System.out.println(this.type2_textLeftEdgeWaveHeight);
					System.out.println(this.type2_textLeftEdgeWavePhase);
					System.out.println(this.type2_textLeftEdgeWavePitch);
					System.out.println(this.type2_textRightEdgeWaveHeight);
					System.out.println(this.type2_textRightEdgeWavePhase);
					System.out.println(this.type2_textRightEdgeWavePitch);
					
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
			this.textRollCrown = med.getTextRollCrown().getText().trim();
			this.textMillStiffness = med.getTextMillStiffness().getText().trim();
			
			med.getTextRollLength().setText(this.textRollLength);
			
			initUpperTableData();
			initLowerTableData();
			
			updateTableData();
			
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
		msgList.add("\t\tLeveller Roll Data");        
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
		this.textRollCrown = null;
		med.getTextRollCrown().setText("");
		this.textMillStiffness = null;
		med.getTextMillStiffness().setText("");
		
		
		try{
			for(UpTableDataContent obj : this.upTableDataList){
				obj = null;
			}
			if(this.upTableDataList != null)	this.upTableDataList.clear();
			this.updateTableData();
		}catch (Exception e){
			//System.out.println("Data is empty.clearAllData");
		}
		
		try{
			for(DownTableDataContent obj : this.downTableDataList){
				obj = null;
			}
			if(this.downTableDataList != null) this.downTableDataList.clear();
			this.updateTableData();
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
	
	public void OpenLeveller(String dbFilePath){
		this.clearAllData();
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
			setAllDataUI(dbFilePath);
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
			
			setAllDataUI(newDBFilePath);
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
			else if(parsingDataList.get(0).equals(TextLabel.lblRollCrown)){
				if(parsingDataList.size() == 2){
					this.textRollCrown = parsingDataList.get(1);
					med.getTextRollCrown().setText(this.textRollCrown);
				}else {
					this.textRollCrown = "No value";
					med.getTextRollCrown().setText(this.textRollCrown);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblMillStiffness)){
				if(parsingDataList.size() == 2){
					this.textMillStiffness = parsingDataList.get(1);
					med.getTextMillStiffness().setText(this.textMillStiffness);
				}else {
					this.textMillStiffness = "No value";
					med.getTextMillStiffness().setText(this.textMillStiffness);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblRollCrownType)){
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
			}else if(parsingDataList.get(0).equals(TextLabel.lblMillStiffnessType)){
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
						med.getBtnExplorerPoissonsRatio().setEnabled(false);
					}else {
						med.getBtnRadioConstant_YM().setSelection(false);
						med.getBtnRadioTable_YM().setSelection(true);
						med.getBtnExplorerPoissonsRatio().setEnabled(true);
					}
				}else {
					this.YoungsModulusType = "Table";
					med.getBtnRadioConstant_YM().setSelection(false);
					med.getBtnRadioTable_YM().setSelection(true);
					med.getBtnExplorerPoissonsRatio().setEnabled(true);
				}
			}else if(parsingDataList.get(0).equals(TextLabel.lblFlowStress+"Type")){
				if(parsingDataList.size() == 2){
					this.FlowStressType = parsingDataList.get(1);
					if(this.FlowStressType.equals("Constant")){
						med.getBtnRadioConstant_FS().setSelection(true);
						med.getBtnRadioTable_FS().setSelection(false);
						med.getBtnExplorerPoissonsRatio().setEnabled(false);
						//update version2 2016.01.27
						med.getTextFlowStress().setEnabled(false);
						med.getTextYieldStrength().setEnabled(true);
						med.getTextTensileStrength().setEnabled(true);
						med.getTextElongation().setEnabled(true);
						
					}else {
						med.getBtnRadioConstant_FS().setSelection(false);
						med.getBtnRadioTable_FS().setSelection(true);
						med.getBtnExplorerPoissonsRatio().setEnabled(true);
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
					med.getBtnExplorerPoissonsRatio().setEnabled(true);
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
						med.getBtnExplorerPoissonsRatio().setEnabled(false);
					}else {
						med.getBtnRadioConstant_TEC().setSelection(false);
						med.getBtnRadioTable_TEC().setSelection(true);
						med.getBtnExplorerPoissonsRatio().setEnabled(true);
					}
				}else {
					this.ThermalExpansionCoefficientType = "Table";
					med.getBtnRadioConstant_TEC().setSelection(false);
					med.getBtnRadioTable_TEC().setSelection(true);
					med.getBtnExplorerPoissonsRatio().setEnabled(true);
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
		updateTableData();
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
		CreateRoll();
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
		if(med.getBtnRadioNone_RC().getSelection()){
			this.RollCrownType = med.getBtnRadioNone_RC().getText().trim();
		}else{
			this.RollCrownType = med.getBtnRadioApply_RC().getText().trim();
		}
		this.textRollCrown = med.getTextRollCrown().getText().trim();
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
		this.db.add(TextLabel.lblRollCrownType+"="+this.RollCrownType);
		this.db.add(TextLabel.lblRollCrown+"="+this.textRollCrown);
		this.db.add(TextLabel.lblMillStiffnessType+"="+this.MillStiffnessType);
		this.db.add(TextLabel.lblMillStiffness+"="+this.textMillStiffness);
		
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
	
	public void saveAsAllData(String newModelName){
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
		CreateRoll();
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
		if(med.getBtnRadioNone_RC().getSelection()){
			this.RollCrownType = med.getBtnRadioNone_RC().getText().trim();
		}else{
			this.RollCrownType = med.getBtnRadioApply_RC().getText().trim();
		}
		this.textRollCrown = med.getTextRollCrown().getText().trim();
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
		this.db_saveAs.add(TextLabel.lblRollCrownType+"="+this.RollCrownType);
		this.db_saveAs.add(TextLabel.lblRollCrown+"="+this.textRollCrown);
		this.db_saveAs.add(TextLabel.lblMillStiffnessType+"="+this.MillStiffnessType);
		this.db_saveAs.add(TextLabel.lblMillStiffness+"="+this.textMillStiffness);
				
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
	// 여기다 proc 만들기 jsclubb
	public void Export(){
		try{
			deleteOldFiles();
			
			this.SaveLeveller();
			
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
	
	public String getTextRollCrown() {
		return textRollCrown;
	}

	public void setTextRollCrown(String textRollCrown) {
		this.textRollCrown = textRollCrown;
	}

	public String getTextMillStiffness() {
		return textMillStiffness;
	}

	public void setTextMillStiffness(String textMillStiffness) {
		this.textMillStiffness = textMillStiffness;
	}
	
	public String getRollCrownType() {
		return RollCrownType;
	}

	public void setRollCrownType(String rollCrownType) {
		RollCrownType = rollCrownType;
	}

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

	
	
	
	
	
}
