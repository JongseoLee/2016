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
		UILabelPath = myUtil.setPath(System.getProperty("user.dir"), "UILabel.txt");
	}
	
	public void readUILabelFile(){
		Reader obj = new Reader(UILabelPath);
		obj.running();
		ArrayList<String> fileData = new ArrayList<String>();
		fileData = obj.getFileDataList();
		for(String line : fileData){
			String data = line.trim();
			if(data.contains("=")){
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
	//Work Roll(WR) shape parameter
	//
	public static String Work_Roll_shape_parameter="Work Roll(WR) shape parameter";
	public static String _1st_diameter_in_WR_roll="1st diameter in WR roll(mm)";
	public static String _2nd_diameter_in_WR_roll="2nd diameter in WR roll(mm)";
	public static String _3rd_diameter_in_WR_roll="3rd diameter in WR roll(mm)";
	public static String _1st_length_in_WR_roll="1st length in WR roll(mm)";
	public static String _2nd_length_in_WR_roll="2nd length in WR roll(mm)";
	public static String _3rd_length_in_WR_roll="3rd length in WR roll(mm)";
	//
	//Back Up Roll(BUR) shape parameter
	//
	public static String Back_Up_Roll_shape_parameter="Back Up Roll(BUR) shape parameter";
	public static String _1st_diameter_in_BUR_roll="1st diameter in BUR roll(mm)";
	public static String _2nd_diameter_in_BUR_roll="2nd diameter in BUR roll(mm)";
	public static String _3rd_diameter_in_BUR_roll="3rd diameter in BUR roll(mm)";
	public static String _1st_length_in_BUR_roll="1st length in BUR roll(mm)";
	public static String _2nd_length_in_BUR_roll="2nd length in BUR roll(mm)";
	public static String _3rd_length_in_BUR_roll="3rd length in BUR roll(mm)";
	//
	//Plate shape parameter
	//
	public static String Plate_shape_parameter="Plate shape parameter";
	public static String length_of_plate="length of plate(mm)";
	public static String width_of_plate="width of plate(mm)";
	public static String thickness_of_plate="thickness of plate(mm)";
	public static String initial_position_of_plate="initial position of plate(mm)";
	public static String roll_gap="roll gap(mm)";
	//
	//Roll mesh parameter
	//
	public static String Roll_mesh_parameter="Roll mesh parameter"; 
	public static String arc_length_of_WR="arc length of WR(mm)";
	public static String arc_length_of_BUR="arc length of BUR(mm)";

	//
	//Plate mesh parameter
	//
	public static String Plate_mesh_parameter="Plate mesh parameter";
	public static String Element_number_of_thickness_direction="Element number of thickness direction";
	public static String Aspect_ratio_of_width_direction="Aspect ratio of width direction";
	public static String Aspect_ratio_of_length_direction="Aspect ratio of length direction";
	//
	//Roll & Plate information
	//
	public static String Roll_Plate_information="Roll & Plate information"; 
	public static String Angular_velocity_of_WR="Angular velocity of WR(rad/s)";
	public static String Angular_velocity_of_BUR="Angular velocity of BUR(rad/s)";
	public static String Plate_velocity="Plate velocity(mm/s)";
	public static String initial_temperature_of_plate="initial temperature of plate(��)";
	//
	//Material parameter
	//
	public static String Material_parameter="Material parameter";
	public static String Youngs_Modulus="Young's Modulus(MPa)";
	public static String Thermal_Expansion_Coefficient="Thermal Expansion Coefficient(mm/mm)";
	public static String Poissons_Ratio="Poisson's Ratio";
	public static String Mass_Density="Mass Density(Mg/mm^3)";
	//
	//Solving option
	//
	public static String Solving_Option="Solving Option";
	public static String Solving_time="Solving time(sec.)";
	public static String Increment_time="Increment time(sec.)";
	public static String Parallel_DDM="Parallel-DDM";
	public static String Domain="Domain";
	public static String Parallel_Multi_Thread="Parallel-Multi Thread";
	public static String Thread="Thread";
	//
	// ETC
	//
	public static String Constatnt="Constatnt";
	public static String Table="Table";
	public static String use="use";
	
}
