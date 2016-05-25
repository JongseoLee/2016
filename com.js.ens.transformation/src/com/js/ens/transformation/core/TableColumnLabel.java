package com.js.ens.transformation.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.js.io.Reader;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;


public class TableColumnLabel {
	private Map<String,String> tableColumnLabelMap;
	private String tableColumnLabelPath;
	
	public TableColumnLabel(){
		tableColumnLabelMap = new HashMap<String,String>();
		tableColumnLabelPath = myUtil.setPath(System.getProperty("user.dir"), "TableColumnLable.txt");	
	}
	
	private void readTableColumnLabelFile(){
		Reader obj = new Reader(tableColumnLabelPath);
		obj.running();
		ArrayList<String> fileData = new ArrayList<String>();
		fileData = obj.getFileDataList();
		for(String line : fileData){
			String data = line.trim();
			if(data.contains("=")){
				ArrayList<String> splitDataTokens = new ArrayList<String>();
				splitDataTokens = ParserDefault.splitLineData(data,"=");
				tableColumnLabelMap.put(splitDataTokens.get(0).trim(), splitDataTokens.get(1).trim()); 
			}
		}
	}
	
	public String getTableColumnLabel(String key){
		if(tableColumnLabelMap.containsKey(key)){
			return tableColumnLabelMap.get(key);
		}else{
			return "no-InitValue";
		}
	}
	
	//
	// Table 1 
	//
	public static String CL1_0 = "STRIP NO";
	public static String CL1_1 = "STHK";
	public static String CL1_2 = "SWID";
	public static String CL1_3 = "SLEN";
	public static String CL1_4 = "SWET";
	public static String CL1_5 = "PTHK";
	public static String CL1_6 = "PWID";
	public static String CL1_7 = "PLEN";
	public static String CL1_8 = "PWET";
	//
	// Table 2 
	//
	public static String CL2_0 = "VAR1";
	public static String CL2_1 = "VAR2";
	public static String CL2_2 = "VAR3";
	public static String CL2_3 = "VAR4";
	public static String CL2_4 = "VAR5";
	public static String CL2_5 = "VAR6";
	public static String CL2_6 = "VAR7";
	public static String CL2_7 = "VAR8";
	public static String CL2_8 = "VAR9";
	public static String CL2_9 = "VAR10";
	public static String CL2_10 = "VAR11";
	public static String CL2_11 = "VAR12";
	public static String CL2_12 = "VAR13";
	public static String CL2_13 = "VAR14";
	public static String CL2_14 = "VAR15";
	public static String CL2_15 = "VAR16";
	//
	// Table 3
	//
	public static String CL3_0 = "STAND";
	public static String CL3_1 = "BUR TDIA";
	public static String CL3_2 = "BUR BDIA";
	public static String CL3_3 = "WR TDIA";
	public static String CL3_4 = "WR BDIA";
	public static String CL3_5 = "WR ICRN";
	public static String CL3_6 = "ENTRY THK";
	public static String CL3_7 = "EXIT THK";
	public static String CL3_8 = "PAS LINE";
	public static String CL3_9 = "ROL GAP";
	public static String CL3_10 = "STP WID";
	public static String CL3_11 = "STP LEN";
	public static String CL3_12 = "ENTRY TEMP";
	public static String CL3_13 = "EXIT TEMP";
	public static String CL3_14 = "FRCE";
	public static String CL3_15 = "TORQ";
	public static String CL3_16 = "SPEED(mpm)";
	public static String CL3_17 = "BEND";
	public static String CL3_18 = "P-CROSS";
	public static String CL3_19 = "TENS";
	public static String CL3_20 = "ROL TIM";
	public static String CL3_21 = "IDL TIM";
	public static String CL3_22 ="BUR WEAR";
	public static String CL3_23 = "WR WEAR";
	public static String CL3_24 = "WR THRM";
}
	