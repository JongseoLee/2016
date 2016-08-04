package com.js.ens.leveller.procs;

import java.util.ArrayList;

import com.js.ens.leveller.core.ComboLabel;

public class ProcMaker {
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	public static String textWidth						= "%Width%";
	public static String textLength						= "%Length%";
	public static String textThickness					= "%Thickness%";

	public static String type2_textLeftEdgeWavePitch 	= "%LeftEdgeWavePitch%";
	public static String type2_textRightEdgeWavePitch	= "%RightEdgeWavePitch%";
	public static String type2_textLeftEdgeWaveHeight 	= "%LeftEdgeWaveHeight%";
	public static String type2_textRightEdgeWaveHeight	= "%RightEdgeWaveHeight%";
	public static String type2_textLeftEdgeWavePhase	= "%LeftEdgeWavePhase%";
	public static String type2_textRightEdgeWavePhase 	= "%RightEdgeWavePhase%";
	public static String MaxWaveHeight					= "%MaxWaveHeight%";

	public static String type3_textWavePitch 			= "%WavePitch%";
	public static String type3_textWaveHeight 			= "%WaveHeight%";

	public static String type4_textGutterHeight			= "%GutterHeight%";

	public static String type5_textGutterHeight			= "%GutterHeight%";
	public static String type5_textGutterLength			= "%GutterLength%";

	public static String type6_textHeadGutterHeight		= "%HeadGutterHeight%";
	public static String type6_textHeadGutterLength		= "%HeadGutterLength%";
	public static String type6_textTailGutterHeight		= "%TailGutterHeight%";
	public static String type6_textTailGutterLength		= "%TailGutterLength%";

	public static String type7_textHeadGutterHeight		= "%GutterHeight%";
	public static String type7_textGutterLength			= "%GutterPosition%";
	public static String type7_textGutterLengthLength	= "%GutterLongitudinalLenght%";
	public static String type7_textGutterWidthLength	= "%GutterWidthLength%";
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	/*
	public static String textThicknessElementNum		= "%ElementNumberOfThicknessDirection%";
	public static String textWidthAspectRatio			= "%AspectRatioOfWidthDirection%";
	public static String textLengthAspectRatio			= "%AspectRatioOfLengthDirection%";
	public static String textElementNumber				= "%NumberOfElements%";
	*/
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	public static String textPlateVelocity				= "%PlateVelocity%";
	public static String textTemperatureStart			= "%TemperatureAtStartOfLevelling%";
	public static String textTemperatureEnd				= "%TemperatureAtEndOfLevelling%";
	public static String textPassLineOffset				= "%PassLineOffset%";
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	public static String spinnerUpperRollNum			= "%UpperRollNnumber%";
	public static String spinnerLowerRollNum			= "%LowerRollNumber%";
	public static String textRollPitch					= "%RollPitch%";
	public static String textRollLength					= "%RollLength%";
	public static String textEntryUpperRollGap			= "%EntryUpperRollGap%";
	public static String textEntryLowerRollGap			= "%EntryLowerRollGap%";
	public static String textExitUpperRollGap			= "%ExitUpperRollGap%";
	public static String textExitLowerRollGap			= "%ExitLowerRollGap%";
	public static String textRollFriction				= "%RollFriction%";
	public static String textRollDiameter				= "%RollDiameter%";
	public static String textRollCrown					= "%RollCrown%";
	public static String textMillStiffness				= "%MillStiffness%";
	
	public static String textUpperEntryRollGapMovement		= "%UpperEntryRollGapMovement%";
	public static String textUpperExitRollGapMovement		= "%UpperExitRollGapMovement%";
	public static String textUpperRollGapStayingTime		= "%UpperRollGapStayingTime%";
	public static String textUpperRollGapMovingTime			= "%UpperRollGapMovingTime%";
	public static String textLowerEntryRollGapMovement		= "%LowerEntryRollGapMovement%";
	public static String textLowerExitRollGapMovement		= "%LowerExitRollGapMovement%";
	public static String textLowerRollGapStayingTime		= "%LowerRollGapStayingTime%";
	public static String textLowerRollGapMovingTime			= "%LowerRollGapMovingTime%";

	public static String HDRollType							= "%HDRollType%";
	
	public static String textFrontHDRollDia					= "%FrontHDRollDia%";
	public static String textFrontHDRollPitch				= "%FrontHDRollPitch%";
	public static String textFrontHDRollVericalPos			= "%FrontHDRollVerticalPos%";
	public static String textRearHDRollDia					= "%RearHDRollDia%";
	public static String textRearHDRollPitch				= "%RearHDRollPitch%";
	public static String textRearHDRollVerticalPos			= "%RearHDRollVerticalPos%"; 

	public static String UpperRollDataField					= "%UpperRollDataField%";
	public static String LowerRollDataField					= "%LowerRollDataField%";
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	public static String textYoungsModulus 					= "%value_YoungsModulus%";
	
	public static String textFlowStress						= "%value_flowStress%";
	public static String textYieldStrength					= "%value_YieldStrength%";
	public static String textTensileStrength				= "%value_TensileStrength%";
	public static String textElongation						= "%value_Elongation%";
	
	public static String textThermalExpansionCoefficient	= "%value_expansion%";

	public static String textPoissonsRatio					= "%value_poisson%";
	
	public static String textMassDensity					= "%value_MassDensity%";
	

	public static String tableName						= "%tableName%";
	public static String tableDataList					= "%tableDataList%";
	public static String PlasticDataSetNum				= "%PlasticDataSetNum%";
	public static String TemperatureDataSetNum			= "%TemperatureDataSetNum%";
	public static String PlaticStrainDataSetNum			= "%PlaticStrainDataSetNum%";
	public static String plasstic_Strain				= "%plasstic_Strain%";
	public static String Temperature					= "%Temperature%";
	public static String Plastic_Strain_Rate			= "%Plastic_Strain_Rate%";
	public static String curveDataTable					= "%curveDataTable%";
	//---------------------------------------------------------------------------	
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	public static String textSolvingTime				= "%SolvingTime%";
	public static String textIncrementTime				= "%IncrementTime%";
	public static String textDomain						= "%Parallel_D%";
	public static String textThread						= "%Parallel_T%";
	//---------------------------------------------------------------------------	
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	public static String upperRollGenField				= "%UpperRollGenField%";
	public static String lowerRollGenField				= "%LowerRollGenField%";
	public static String entryRollTableField			= "%EntryRollTableField%";
	public static String exitRollTableField				= "%ExitRollTableField%";

	public static String elasticModulusType				= "%ElasticModulusType%";
	public static String poissonType					= "%PoissonType%";
	public static String flowStressType					= "%FlowStressType%";
	public static String thermal_expansionType			= "%Thermal_expansionType%";
	public static String tableName_elasticModulus		= "%TableName_elasticModulus%";
	public static String tableName_poisson				= "%TableName_poisson%";
	public static String tableName_flowStress			= "%TableName_flowStress%";
	public static String tableName_thermal_expansion	= "%TableName_thermal_expansion%";

	public static String upperRollGenContact			= "%UpperRollGenContact%";
	public static String lowerRollGenContact			= "%LowerRollGenContact%";
	public static String add_contact_body_surfaces		= "%Add_contact_body_surfaces%";
	public static String flip_surface					= "%Flip_surfaces%";
	public static String plateRollGenContactTable		= "%PlateRollGenContactTable%";

	public static String a2_RollGenType					= "%RollGenType%";
	public static String changeMotionToLoadPy			= "%ChangeMotionToLoadPy%";
	
	private ArrayList<String> FullPathList = new ArrayList<String>();
	
	public ProcMaker() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(String levellerType, String comboType, String procFolder){
		this.copyProc(levellerType, comboType, procFolder);
		this.genProc(levellerType, comboType,procFolder);
		
	}
	private void copyProc(String levellerType, String comboType, String procFolder){
		if(levellerType.equals("2D")){
			CopyProc2D obj = new CopyProc2D(comboType,procFolder);
			obj.running();
			FullPathList = obj.getFullPathList();
		}else if(levellerType.equals("3D")){
			CopyProc3D obj = new CopyProc3D(comboType,procFolder);
			obj.running();
			FullPathList = obj.getFullPathList();
		}
		
	}
	private void genProc(String levellerType, String comboType, String prodFolder){
		if(levellerType.equals("2D")){
			if(comboType.equals(ComboLabel.TYPE1_2D)){
				
			}else if(comboType.equals(ComboLabel.TYPE2_2D)){
				
			}else if(comboType.equals(ComboLabel.TYPE3_2D)){
				
			}
		}else if(levellerType.equals("3D")){
			if(comboType.equals(ComboLabel.TYPE1)){
				
			}else if(comboType.equals(ComboLabel.TYPE2)){
				
			}else if(comboType.equals(ComboLabel.TYPE3)){
				
			}else if(comboType.equals(ComboLabel.TYPE4)){
				
			}else if(comboType.equals(ComboLabel.TYPE5)){
				
			}else if(comboType.equals(ComboLabel.TYPE6)){
				
			}else if(comboType.equals(ComboLabel.TYPE7)){
				
			}
		}
		
	}

}
