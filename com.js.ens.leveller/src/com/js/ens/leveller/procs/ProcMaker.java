package com.js.ens.leveller.procs;

import java.util.ArrayList;

import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.procs.threeD.Main_centerwave;
import com.js.ens.leveller.procs.threeD.Main_doublegutter;
import com.js.ens.leveller.procs.threeD.Main_edgewave;
import com.js.ens.leveller.procs.threeD.Main_flat;
import com.js.ens.leveller.procs.threeD.Main_islandgutter;
import com.js.ens.leveller.procs.threeD.Main_partialgutter;
import com.js.ens.leveller.procs.threeD.Main_singlegutter;
import com.js.ens.leveller.procs.twoD.Main_curl_2D;
import com.js.ens.leveller.procs.twoD.Main_flat_2D;
import com.js.ens.leveller.procs.twoD.Main_wave_2D;

public class ProcMaker {
	// 3D file name
	public static String define_parameters	 			= "00_define_parameters.proc";
	public static String a2_roll_gen 					= "a2_roll_gen.proc";
	public static String a3_material_define 			= "a3_material_define.proc";
	public static String mat01_elastic_modulus 			= "mat01_elastic_modulus.proc";
	public static String mat01_elastic_modulus_const 	= "mat01_elastic_modulus_const.proc";
	public static String mat02_flow_stress 				= "mat02_flow_stress.proc";
	public static String mat02_flow_stress_const 		= "mat02_flow_stress_const.proc";
	public static String mat03_thermal_expansion 		= "mat03_thermal_expansion.proc";
	public static String mat03_thermal_expansion_const 	= "mat03_thermal_expansion_const.proc";
	public static String mat04_poisson 					= "mat04_poisson.proc";
	public static String mat04_poisson_const 			= "mat04_poisson_const.proc";
	public static String mat05_mass_density 			= "mat05_mass_density.proc";
	public static String a4_contact 					= "a4_contact.proc";
	public static String a6_loadcase 					= "a6_loadcase.proc";
	// 2D file name
	public static String define_parameters_2D				= "00_define_parameters.proc";
	public static String a2_roll_gen_2D						= "a2_roll_gen_2d.proc";
	public static String a3_material_define_2D				= "a3_material_define.proc";
	public static String mat01_elastic_modulus_2D 			= "mat01_elastic_modulus.proc";
	public static String mat01_elastic_modulus_const_2D 	= "mat01_elastic_modulus_const.proc";
	public static String mat02_flow_stress_2D 				= "mat02_flow_stress.proc";
	public static String mat02_flow_stress_const_2D 		= "mat02_flow_stress_const.proc";
	public static String mat03_thermal_expansion_2D 		= "mat03_thermal_expansion.proc";
	public static String mat03_thermal_expansion_const_2D 	= "mat03_thermal_expansion_const.proc";
	public static String mat04_poisson_2D 					= "mat04_poisson.proc";
	public static String mat04_poisson_const_2D 			= "mat04_poisson_const.proc";
	public static String mat05_mass_density_2D 				= "mat05_mass_density.proc";
	public static String a4_contact_2D 						= "a4_contact_2d.proc";
	public static String a6_loadcase_2D						= "a6_loadcase_2d.proc"; 
	
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	public static String textWidth						= "%Width%";
	public static String textLength						= "%Length%";
	public static String textThickness					= "%Thickness%";

	//3D
	public static String type2_textLeftEdgeWavePitch 	= "%LeftEdgeWavePitch%";
	public static String type2_textRightEdgeWavePitch	= "%RightEdgeWavePitch%";
	public static String type2_textLeftEdgeWaveHeight 	= "%LeftEdgeWaveHeight%";
	public static String type2_textRightEdgeWaveHeight	= "%RightEdgeWaveHeight%";
	public static String type2_textLeftEdgeWavePhase	= "%LeftEdgeWavePhase%";
	public static String type2_textRightEdgeWavePhase 	= "%RightEdgeWavePhase%";
	public static String MaxWaveHeight					= "%MaxWaveHeight%";
	//2D
	public static String type2_textWavePitch 			= "%WavePitch%";
	public static String type2_textWaveHeight			= "%WaveHeight%";
	public static String type2_textWavePhase			= "%WavePhase%";
	//3D
	public static String type3_textWavePitch 			= "%WavePitch%";
	public static String type3_textWaveHeight 			= "%WaveHeight%";
	//2D
	public static String type3_textFrontCurlHeight		= "%FrontCurlHeight%";
	public static String type3_textFrontCurlLength		= "%FrontCurlLength%";
	public static String type3_textRearCurlHeight		= "%RearCurlHeight%";
	public static String type3_textRearCurlLength		= "%RearCurlLength%";
	
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
	public static String textThicknessElementNum		= "%ElementNumberOfThicknessDirection%";
	public static String textWidthAspectRatio			= "%AspectRatioOfWidthDirection%";
	public static String textLengthAspectRatio			= "%AspectRatioOfLengthDirection%";
	//public static String textElementNumber				= "%NumberOfElements%";
	
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
	public static String textEntryRollTableDistance		= "%EntryRollTableDist%";
	public static String textExitRollTableDistance 		= "%ExitRollTableDist%";
	public static String textEntryUpperRollGap			= "%EntryUpperRollGap%";
	public static String textEntryLowerRollGap			= "%EntryLowerRollGap%";
	public static String textExitUpperRollGap			= "%ExitUpperRollGap%";
	public static String textExitLowerRollGap			= "%ExitLowerRollGap%";
	public static String textRollFriction				= "%RollFriction%";
	public static String textRollDiameter				= "%RollDiameter%";
	public static String textUpperRollCrown				= "%UpperRollCrown%";
	public static String textLowerRollCrown				= "%LowerRollCrown%";
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
	public static String add_contact_body_curves		= "%Add_contact_body_curves%";
	public static String flip_surface					= "%Flip_surfaces%";
	public static String flip_curves					= "%Flip_curves%";
	public static String plateRollGenContactTable		= "%PlateRollGenContactTable%";

	public static String a2_RollGenType					= "%RollGenType%";
	public static String changeMotionToLoadPy			= "%ChangeMotionToLoadPy%";
	
	private ArrayList<String> FullPathList = new ArrayList<String>();
	private ArrayList<String> destFullPathList = new ArrayList<String>();
	
	public ProcMaker() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(String levellerType, String comboType, String procFolder){
		this.copyProc(levellerType, comboType, procFolder);
		this.genProc(levellerType, comboType);
		
	}
	private void copyProc(String levellerType, String comboType, String procFolder){
		if(levellerType.equals("2D")){
			CopyProc2D obj = new CopyProc2D(comboType,procFolder);
			obj.running();
			FullPathList = obj.getFullPathList();
			destFullPathList = obj.getDestFullPathList();
		}else if(levellerType.equals("3D")){
			CopyProc3D obj = new CopyProc3D(comboType,procFolder);
			obj.running();
			FullPathList = obj.getFullPathList();
			destFullPathList = obj.getDestFullPathList();
		}
		
	}
	private void genProc(String levellerType, String comboType){
		if(levellerType.equals("2D")){
			/* */
			if(comboType.equals(ComboLabel.TYPE1_2D)){
				Main_flat_2D obj = new Main_flat_2D();
				obj.running(this.destFullPathList);
			}else if(comboType.equals(ComboLabel.TYPE2_2D)){
				Main_wave_2D obj = new Main_wave_2D();
				obj.running(this.destFullPathList);
			}else if(comboType.equals(ComboLabel.TYPE3_2D)){
				Main_curl_2D obj = new Main_curl_2D();
				obj.running(this.destFullPathList);
			}
			//*/
		}else if(levellerType.equals("3D")){
			if(comboType.equals(ComboLabel.TYPE1)){
				Main_flat obj = new Main_flat();
				obj.running(this.destFullPathList);
			}else if(comboType.equals(ComboLabel.TYPE2)){
				Main_edgewave obj = new Main_edgewave();
				obj.running(this.destFullPathList);
			}else if(comboType.equals(ComboLabel.TYPE3)){
				Main_centerwave obj = new Main_centerwave();
				obj.running(this.destFullPathList);
			}else if(comboType.equals(ComboLabel.TYPE4)){
				Main_singlegutter obj = new Main_singlegutter();
				obj.running(this.destFullPathList);
			}else if(comboType.equals(ComboLabel.TYPE5)){
				Main_partialgutter obj = new Main_partialgutter();
				obj.running(this.destFullPathList);
			}else if(comboType.equals(ComboLabel.TYPE6)){
				Main_doublegutter obj = new Main_doublegutter();
				obj.running(this.destFullPathList);
			}else if(comboType.equals(ComboLabel.TYPE7)){
				Main_islandgutter obj = new Main_islandgutter();
				obj.running(this.destFullPathList);
			}
		}
		
	}

}
