package com.js.ens.transformation.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.js.io.Reader;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class UILabel {
	private Map<String,String> UILabelMap;
	private String UILabelPath;
	
	public UILabel(){
		UILabelMap = new HashMap<String,String>();
		UILabelPath = myUtil.setPath(myUtil.setPath(System.getProperty("user.dir"), "userConfig"),"UILabel.txt");
	}
	
	public void readUILabelFile(){
		Reader obj = new Reader(UILabelPath);
		obj.running();
		ArrayList<String> fileData = new ArrayList<String>();
		fileData = obj.getFileDataList();
		for(String line : fileData){
			String data = line.trim();
			if(data.contains("=")){
				//System.out.println(line);
				ArrayList<String> splitDataTokens = new ArrayList<String>();
				splitDataTokens = ParserDefault.splitLineData(data,"=");
				UILabelMap.put(splitDataTokens.get(0).trim(), splitDataTokens.get(1).trim()); 
			}
		}
	}
	
	public String getUILabelValue(String key){
		if(UILabelMap.containsKey(key)){
			return UILabelMap.get(key);
		}else{
			return "no-UILabel";
		}
	}
	
	
	// program variable = map key => value(in UILabel.txt)
	//
	//Common Label
	//
	public static String Model_Name = "Model Name";
	public static String Workspace = "Workspace";
	//
	// button label
	//
	public static String Import_P_Log = "Import P Log";
	public static String Export_P_Log = "Export P Log";
	public static String Apply = "Apply";
	//
	// Tab title
	//
	public static String Tab1Label="Tab1Label";
	public static String Tab2Label="Tab2Label";
	//
	// Table
	//
	public static String Table1Label="Table1Label";
	public static String Table2Label="Table2Label";
	public static String Table3Label="Table3Label";
	//
	// STAND
	//
	public static String STAND="STAND";
	public static String F1="F1";
	public static String F2="F2";
	public static String F3="F3";
	public static String F4="F4";
	public static String F5="F5";
	public static String F6="F6";
	public static String F7="F7";
		
	//
	// -> Work Roll(WR) parameter
	// Title 
	public static String Work_Roll_WR_Parameters="Work Roll(WR) Parameters";
	// 1) WR_TDIA
	public static String Top_WR_Diameter="Top WR Diameter(mm)";
	// 2) WR_BDIA
	public static String Bottom_WR_Diameter="Bottom WR Diameter(mm)";
	// 3) WR_ICRN
	public static String WR_Crown="WR Crown(mm)";
	// 4) Default-wr_len1-3
	public static String WR_Length="WR Length(mm)";
	// 5) wr_div_angle
	public static String WR_Mesh_Angle="WR Mesh Angle(deg.)";

	//
	// -> Backup Roll(BUR) Parameters
	// Title
	public static String Backup_Roll_BUR_Parameters="Backup Roll(BUR) Parameters";
	// 1) BUR_TDIA
	public static String Top_BUR_Diameter="Top BUR Diameter(mm)";
	// 2) BUR_BDIA
	public static String Bottom_BUR_Diameter="Bottom BUR Diameter(mm)";
	// 3) bur_len1-3
	public static String BUR_Length="BUR Length(mm)";
	// 4) bur_div_angle
	public static String BUR_Mesh_Angle="BUR Mesh Angle(deg.)";
	
	//
	//-> Plate Parameters
	// Title
	public static String Plate_Parameters="Plate Parameters";
	// 1) ENTRY_THK
	public static String Thickness="Thickness(mm)";
	// 2) STP_WID
	public static String Width="Width(mm)";			
	// 3) STP_LEN
	public static String Length="Length(mm)";
	// 4) ENTRTY_TEMP
	public static String Entry_Temperature="Entry Temperature(C)";
	// 5) EXIT_TEMP
	public static String Exit_Temperature="Exit Temperature(C)";
	// 6) p_in
	public static String Initial_Position="Initial Position(mm)";	
	// 7) pl_m
	public static String Mesh_Length="Mesh Length(mm)";	
	// 8) t_div
	public static String Thickness_Mesh_Divisions="Thickness Mesh Divisions";
	
	//
	//-> Plate Parameters
	// Title 
	public static String Process_Information="Process Information";
	// 1) SPEED
	public static String Velocity="Velocity(mpm)";
	// 2) ROL_GAP
	public static String Roll_Gap="Roll Gap(mm)";
	// 3) Pass_LINE
	public static String Pass_Line="Pass Line(mm)";
	// 4) P-CROSS
	public static String Pair_Cross_Angle="Pair Cross Angle(Deg.)";
	// 5) BEND
	public static String Bender_Force="Bender Force(Tone)";
	// 6) TORQ
	public static String Roll_Torque="Roll Torque(Tone.m)";
	// 7) TENS
	public static String Tension_Stress="Tension Stress(Kgf/mm2)";			
	// 8) f_r2p
	public static String Roll_to_Plate_Frict_Coef="Roll to Plate Frict. Coef.";			
	// 9) f_r2r
	public static String Roll_to_Roll_Fric_Coef="Roll to Roll Fric. Coef.";
	
	//
	//-> Material parameter
	// Title
	public static String Material_parameter="Material parameter";
	// 1) 
	public static String Youngs_Modulus="Young's Modulus(MPa)";
	// 2)
	public static String Thermal_Expansion_Coefficient="Thermal Expansion Coefficient(mm/mm)";
	// 3)
	public static String Poissons_Ratio="Poisson's Ratio";
	// 4)
	public static String Mass_Density="Mass Density(Mg/mm^3)";
	
	//
	//-> Analysis Information
	// Title
	public static String Analysis_Information="Analysis Information";
	// 1) lcase_time
	public static String Time_Increment_time="Time Increment(sec.-time)";
	// 2) lcase_dt
	public static String Time_Increment_dt="Time Increment(sec.-dt)";			
	// 3) post_inc
	public static String Post_Writing_frequency="Post Writing frequency";
	// 4) solving_time
	public static String Solving_time="Solving time(sec.)";
	// 5) increment_time
	public static String Increment_time="Increment time(sec.)";
	// 6) parallel_DDM
	public static String Parallel_DDM="Parallel-DDM";
	// 7) domain
	public static String Domain="Domain";
	// 8) parallel_Multi_Thread
	public static String Parallel_Multi_Thread="Parallel-Multi Thread";			
	// 9) thread
	public static String Thread="Thread";
	
	//
	// ETC
	//
	public static String Constatnt="Constatnt";
	public static String Table="Table";
	public static String use="use";
	
	public static String BUR_TDIA = "BUR TDIA";
	public static String BUR_BDIA = "BUR BDIA";
	public static String WR_TDIA = "WR TDIA";
	public static String WR_BDIA = "WR BDIA";
	public static String WR_ICRN = "WR ICRN";
	public static String ENTRY_THK = "ENTRY THK";
	public static String EXIT_THK = "EXIT THK";
	public static String PAS_LINE = "PAS LINE";
	public static String ROL_GAP = "ROL GAP";
	public static String STP_WID = "STP WID";
	public static String STP_LEN = "STP LEN";
	public static String ENTRY_TEMP = "ENTRY TEMP";
	public static String EXIT_TEMP = "EXIT TEMP";
	public static String FRCE = "FRCE";
	public static String TORQ = "TORQ";
	public static String SPEED_mpm = "SPEED(mpm)";
	public static String BEND = "BEND";
	public static String P_CROSS =  "P-CROSS";
	public static String TENS = "TENS";
	public static String ROL_TIM = "ROL TIM";
	public static String IDL_TIM = "IDL TIM";
	public static String BUR_WEAR = "BUR WEAR";
	public static String WR_WEAR = "WR WEAR";
	public static String WR_THRM = "WR THRM";
	
}
