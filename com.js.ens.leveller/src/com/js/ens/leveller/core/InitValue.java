package com.js.ens.leveller.core;

import com.js.util.myUtil;

public class InitValue {
	// Shape parameter
	public static String comboType							= "1 (Flat)";
	public static String textWidth							= "3000";
	public static String textLength							= "5000";
	public static String textThickness						= "30";
	
	public static String lblType							= "1 (Flat)";
	public static String lblWidth							= "3000";
	public static String lblLength							= "5000";
	public static String lblThickness						= "30";
	//-Type2 
	public static String type2_textLeftEdgeWavePitch		= "1000";
	public static String type2_textRightEdgeWavePitch		= "1000";
	public static String type2_textLeftEdgeWaveHeight		= "20";
	public static String type2_textRightEdgeWaveHeight		= "20";
	
	public static String lblLeftEdgeWavePitch_type2			= "1000";
	public static String lblRightEdgeWavePitch_type2		= "1000";
	public static String lblLeftEdgeWaveHeight_type2		= "100";
	public static String lblRightEdgeWaveHeight_type2		= "100";
	//-Type3
	public static String type3_textWavePitch				= "1000";
	public static String type3_textWaveHeight				= "20";
	
	public static String lblWavePitch_type3					= "1000";
	public static String lblWaveHeight_type3				= "20";
	//-Type4
	public static String type4_textGutterHeight				= "20"; 
	
	public static String lblGutterHeight_type4				= "20";
	//-Type5
	public static String type5_textGutterHeight				= "20";
	public static String type5_textGutterLength				= "1500";
	
	public static String lblGutterHeight_type5				= "20";
	public static String lblGutterLength_type5				= "1500";
	//-Type6
	public static String type6_textHeadGutterHeight			= "20";
	public static String type6_textHeadGutterLength			= "1500";
	public static String type6_textTailGutterHeight			= "20";
	public static String type6_textTailGutterLength			= "1500";
	
	public static String lblHeadGutterHeight_type6			= "20";
	public static String lblHeadGutterLength_type6			= "1500";
	public static String lblTailGutterHeight_type6			= "20";
	public static String lblTailGutterLength_type6			= "1500";
	//-Type7
	public static String type7_textHeadGutterHeight			= "20";
	public static String type7_textGutterLength				= "1500";
	public static String type7_textGutterLengthLength		= "1500";
	public static String type7_textGutterWidthLength		= "2000";
	
	public static String lblHeadGutterHeight_type7			= "20";
	public static String lblGutterLength_type7				= "1500";
	public static String lblGutterLengthLength_type7		= "1500";
	public static String lblGutterWidthLength_type7			= "2000";
	// Mesh parameter
	public static String textThicknessElementNum			= "4";
	public static String textWidthAspectRatio				= "1";
	public static String textLengthAspectRatio				= "1";
	public static String textElementNumber					= "";
	
	public static String lblThicknessElementNum				= "4";
	public static String lblWidthAspectRatio				= "1";
	public static String lblLengthAspectRatio				= "1";
	public static String lblElementNumber					= "";
	// Plate Information
	public static String textPlateVelocity					= "1000";
	public static String textTemperatureStart				= "750";
	public static String textTemperatureEnd					= "650";
	public static String textPassLineOffset					= "0.0";
	
	public static String lblPlateVelocity					= "1000";
	public static String lblTemperatureStart				= "750";
	public static String lblTemperatureEnd					= "650";
	// Roll parameter
	public static String spinnerUpperRollNum				= "4";
	public static String spinnerLowerRollNum				= "5";
	public static String textRollPitch						= "300";
	public static String textRollLength						= "5000";
	public static String textEntryUpperRollGap				= "30";
	public static String textEntryLowerRollGap				= "0";
	public static String textExitUpperRollGap				= "50";
	public static String textExitLowerRollGap				= "0";	
	public static String textRollFriction					= "0.3";
	public static String textRollDiameter					= "280";
	public static String textRollCrown						= "0";
	public static String textMillStiffness					= "1.0e5"; 
	
	public static String lblUpperRollNumber					= "4";
	public static String lblLowerRollNumber					= "5";
	public static String lblRollPitch						= "300";
	public static String lblRollLength						= "5000";
	public static String lblEntryUpperRollGap				= "30";
	public static String lblEntryLowerRollGap				= "0";
	public static String lblExitUpperRollGap				= "50";
	public static String lblExitLowerRollGap				= "0";	
	public static String lblRollFriction					= "0.3";
	public static String lblRollDiameter					= "280";
	public static String lblRollCrown 						= "0.0";
	public static String lblMillStiffness					= "1.0e5";
	// Material parameter
	public static String textYoungsModulus					= "21000";
	public static String textFlowStress						= "1000";
	public static String textYieldStrength					= "900";
	public static String textTensileStrength 				= "1200";
	public static String textElongation						= "0.3";
	public static String textThermalExpansionCoefficient	= "1.0e-5";
	public static String textMassDensity					= "7.85e-9";
	public static String textPoissonsRatio					= "0.3";
	public static boolean btnRadioConstant					= false;
	public static boolean btnRadioTable						= true;
	
	/*
	static String matPath = myUtil.setPath(System.getProperty("user.dir"), "materialData");
	
	public static String lblYoungsModulus					= myUtil.setPath(matPath, "elastic_modulus.txt");
	public static String lblFlowStress						= myUtil.setPath(matPath, "flow_stress.txt");
	public static String lblThermalExpansionCoefficient		= myUtil.setPath(matPath, "expansion.txt");
	public static String lblPoissonsRatio					= myUtil.setPath(matPath, "poisson.txt");
	*/
	public static String lblMassDensity						= "7.85e-9";
	
	// Contact parameter
	//public static String textFriction						= "";
	//public static String lblFriction						= "";
	// Solving Option
	public static String textSolvingTime					= "";
	public static String textIncrementTime					= "0.0025";
	public static boolean btnParallelUse					= false;
	public static String spinnerDomain						= "4";
	public static String spinnerThread						= "4";
	//public static String textDeformedCoordinate				= "";
	
	public static String lblSolvingTime						= "";
	public static String lblIncrementTime					= "0.0025";
	public static String lblDomain							= "";
	public static String lblThread							= "4";
	//public static String lblDeformedCoordinate				= "";
}
