package com.js.ens.transformation.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.js.io.Reader;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class InitValue {
	private Map<String,String> initValueMap;
	private String initValuePath;
	
	public InitValue(){
		initValueMap = new HashMap<String,String>();
		initValuePath = myUtil.setPath(System.getProperty("user.dir"), "InitValue.txt");
	}
	
	private void readInitValueFile(){
		Reader obj = new Reader(initValuePath);
		obj.running();
		ArrayList<String> fileData = new ArrayList<String>();
		fileData = obj.getFileDataList();
		for(String line : fileData){
			String data = line.trim();
			if(data.contains("=")){
				ArrayList<String> splitDataTokens = new ArrayList<String>();
				splitDataTokens = ParserDefault.splitLineData(data,"=");
				initValueMap.put(splitDataTokens.get(0).trim(), splitDataTokens.get(1).trim()); 
			}
		}
	}
	
	public String getInitValue(String key){
		if(initValueMap.containsKey(key)){
			return initValueMap.get(key);
		}else{
			return "no-InitValue";
		}
	}
	
	//
	// basic
	//
	public static String STRIP_NO = "STRIP NO";
	public static String STHK = "STHK";
	public static String SWID = "SWID";
	public static String SLEN = "SLEN";
	public static String SWET = "SWET";
	public static String PTHK = "PTHK";
	public static String PWID = "PWID";
	public static String PLEN = "PLEN";
	public static String PWET = "PWET";
	//
	// var
	//
	public static String VAR1 = "VAR1";
	public static String VAR2 = "VAR2";
	public static String VAR3 = "VAR3";
	public static String VAR4 = "VAR4";
	public static String VAR5 = "VAR5";
	public static String VAR6 = "VAR6";
	public static String VAR7 = "VAR7";
	public static String VAR8 = "VAR8";
	public static String VAR9 = "VAR9";
	public static String VAR10 = "VAR10";
	public static String VAR11 = "VAR11";
	public static String VAR12 = "VAR12";
	public static String VAR13 = "VAR13";
	public static String VAR14 = "VAR14";
	public static String VAR15 = "VAR15";
	public static String VAR16 = "VAR16";
	//
	//F1
	//
	//STAND_F1
	public static String BUR_TDIA_F1 = "BUR TDIA_F1";
	public static String BUR_BDIA_F1 = "BUR BDIA_F1";
	public static String WR_TDIA_F1 = "WR TDIA_F1";
	public static String WR_BDIA_F1 = "WR BDIA_F1";
	public static String WR_ICRN_F1 = "WR ICRN_F1";
	public static String ENTRY_THK_F1 = "ENTRY THK_F1";
	public static String EXIT_THK_F1 = "EXIT THK_F1";
	public static String PAS_LINE_F1 = "PAS LINE_F1";
	public static String ROL_GAP_F1 = "ROL GAP_F1";
	public static String STP_WID_F1 = "STP WID_F1";
	public static String STP_LEN_F1 = "STP LEN_F1";
	public static String ENTRY_TEMP_F1 = "ENTRY TEMP_F1";
	public static String EXIT_TEMP_F1 = "EXIT TEMP_F1";
	public static String FRCE_F1 = "FRCE_F1";
	public static String TORQ_F1 = "TORQ_F1";
	public static String SPEED_mpm_F1 = "SPEED(mpm)_F1";
	public static String BEND_F1 = "BEND_F1";
	public static String P_CROSS_F1 =  "P-CROSS_F1";
	public static String TENS_F1 = "TENS_F1";
	public static String ROL_TIM_F1 = "ROL TIM_F1";
	public static String IDL_TIM_F1 = "IDL TIM_F1";
	public static String BUR_WEAR_F1 = "BUR WEAR_F1";
	public static String WR_WEAR_F1 = "WR WEAR_F1";
	public static String WR_THRM_F1 = "THRM_F1";
	//
	//F2
	//
	//STAND_F2
	public static String BUR_TDIA_F2 = "BUR TDIA_F2";
	public static String BUR_BDIA_F2 = "BUR BDIA_F2";
	public static String WR_TDIA_F2 = "WR TDIA_F2";
	public static String WR_BDIA_F2 = "WR BDIA_F2";
	public static String WR_ICRN_F2 = "WR ICRN_F2";
	public static String ENTRY_THK_F2 = "ENTRY THK_F2";
	public static String EXIT_THK_F2 = "EXIT THK_F2";
	public static String PAS_LINE_F2 = "PAS LINE_F2";
	public static String ROL_GAP_F2 = "ROL GAP_F2";
	public static String STP_WID_F2 = "STP WID_F2";
	public static String STP_LEN_F2 = "STP LEN_F2";
	public static String ENTRY_TEMP_F2 = "ENTRY TEMP_F2";
	public static String EXIT_TEMP_F2 = "EXIT TEMP_F2";
	public static String FRCE_F2 = "FRCE_F2";
	public static String TORQ_F2 = "TORQ_F2";
	public static String SPEED_mpm_F2 = "SPEED(mpm)_F2";
	public static String BEND_F2 = "BEND_F2";
	public static String P_CROSS_F2 =  "P-CROSS_F2";
	public static String TENS_F2 = "TENS_F2";
	public static String ROL_TIM_F2 = "ROL TIM_F2";
	public static String IDL_TIM_F2 = "IDL TIM_F2";
	public static String BUR_WEAR_F2 = "BUR WEAR_F2";
	public static String WR_WEAR_F2 = "WR WEAR_F2";
	public static String WR_THRM_F2 = "THRM_F2";
	//
	//F3
	//
	//STAND_F3
	public static String BUR_TDIA_F3 = "BUR TDIA_F3";
	public static String BUR_BDIA_F3 = "BUR BDIA_F3";
	public static String WR_TDIA_F3 = "WR TDIA_F3";
	public static String WR_BDIA_F3 = "WR BDIA_F3";
	public static String WR_ICRN_F3 = "WR ICRN_F3";
	public static String ENTRY_THK_F3 = "ENTRY THK_F3";
	public static String EXIT_THK_F3 = "EXIT THK_F3";
	public static String PAS_LINE_F3 = "PAS LINE_F3";
	public static String ROL_GAP_F3 = "ROL GAP_F3";
	public static String STP_WID_F3 = "STP WID_F3";
	public static String STP_LEN_F3 = "STP LEN_F3";
	public static String ENTRY_TEMP_F3 = "ENTRY TEMP_F3";
	public static String EXIT_TEMP_F3 = "EXIT TEMP_F3";
	public static String FRCE_F3 = "FRCE_F3";
	public static String TORQ_F3 = "TORQ_F3";
	public static String SPEED_mpm_F3 = "SPEED(mpm)_F3";
	public static String BEND_F3 = "BEND_F3";
	public static String P_CROSS_F3 =  "P-CROSS_F3";
	public static String TENS_F3 = "TENS_F3";
	public static String ROL_TIM_F3 = "ROL TIM_F3";
	public static String IDL_TIM_F3 = "IDL TIM_F3";
	public static String BUR_WEAR_F3 = "BUR WEAR_F3";
	public static String WR_WEAR_F3 = "WR WEAR_F3";
	public static String WR_THRM_F3 = "THRM_F3";
	//
	//F4
	//
	//STAND_F4
	public static String BUR_TDIA_F4 = "BUR TDIA_F4";
	public static String BUR_BDIA_F4 = "BUR BDIA_F4";
	public static String WR_TDIA_F4 = "WR TDIA_F4";
	public static String WR_BDIA_F4 = "WR BDIA_F4";
	public static String WR_ICRN_F4 = "WR ICRN_F4";
	public static String ENTRY_THK_F4 = "ENTRY THK_F4";
	public static String EXIT_THK_F4 = "EXIT THK_F4";
	public static String PAS_LINE_F4 = "PAS LINE_F4";
	public static String ROL_GAP_F4 = "ROL GAP_F4";
	public static String STP_WID_F4 = "STP WID_F4";
	public static String STP_LEN_F4 = "STP LEN_F4";
	public static String ENTRY_TEMP_F4 = "ENTRY TEMP_F4";
	public static String EXIT_TEMP_F4 = "EXIT TEMP_F4";
	public static String FRCE_F4 = "FRCE_F4";
	public static String TORQ_F4 = "TORQ_F4";
	public static String SPEED_mpm_F4 = "SPEED(mpm)_F4";
	public static String BEND_F4 = "BEND_F4";
	public static String P_CROSS_F4 =  "P-CROSS_F4";
	public static String TENS_F4 = "TENS_F4";
	public static String ROL_TIM_F4 = "ROL TIM_F4";
	public static String IDL_TIM_F4 = "IDL TIM_F4";
	public static String BUR_WEAR_F4 = "BUR WEAR_F4";
	public static String WR_WEAR_F4 = "WR WEAR_F4";
	public static String WR_THRM_F4 = "THRM_F4";
	//
	//F5
	//
	//STAND_F5
	public static String BUR_TDIA_F5 = "BUR TDIA_F5";
	public static String BUR_BDIA_F5 = "BUR BDIA_F5";
	public static String WR_TDIA_F5 = "WR TDIA_F5";
	public static String WR_BDIA_F5 = "WR BDIA_F5";
	public static String WR_ICRN_F5 = "WR ICRN_F5";
	public static String ENTRY_THK_F5 = "ENTRY THK_F5";
	public static String EXIT_THK_F5 = "EXIT THK_F5";
	public static String PAS_LINE_F5 = "PAS LINE_F5";
	public static String ROL_GAP_F5 = "ROL GAP_F5";
	public static String STP_WID_F5 = "STP WID_F5";
	public static String STP_LEN_F5 = "STP LEN_F5";
	public static String ENTRY_TEMP_F5 = "ENTRY TEMP_F5";
	public static String EXIT_TEMP_F5 = "EXIT TEMP_F5";
	public static String FRCE_F5 = "FRCE_F5";
	public static String TORQ_F5 = "TORQ_F5";
	public static String SPEED_mpm_F5 = "SPEED(mpm)_F5";
	public static String BEND_F5 = "BEND_F5";
	public static String P_CROSS_F5 =  "P-CROSS_F5";
	public static String TENS_F5 = "TENS_F5";
	public static String ROL_TIM_F5 = "ROL TIM_F5";
	public static String IDL_TIM_F5 = "IDL TIM_F5";
	public static String BUR_WEAR_F5 = "BUR WEAR_F5";
	public static String WR_WEAR_F5 = "WR WEAR_F5";
	public static String WR_THRM_F5 = "THRM_F5";
	//
	//F6
	//
	//STAND_F6
	public static String BUR_TDIA_F6 = "BUR TDIA_F6";
	public static String BUR_BDIA_F6 = "BUR BDIA_F6";
	public static String WR_TDIA_F6 = "WR TDIA_F6";
	public static String WR_BDIA_F6 = "WR BDIA_F6";
	public static String WR_ICRN_F6 = "WR ICRN_F6";
	public static String ENTRY_THK_F6 = "ENTRY THK_F6";
	public static String EXIT_THK_F6 = "EXIT THK_F6";
	public static String PAS_LINE_F6 = "PAS LINE_F6";
	public static String ROL_GAP_F6 = "ROL GAP_F6";
	public static String STP_WID_F6 = "STP WID_F6";
	public static String STP_LEN_F6 = "STP LEN_F6";
	public static String ENTRY_TEMP_F6 = "ENTRY TEMP_F6";
	public static String EXIT_TEMP_F6 = "EXIT TEMP_F6";
	public static String FRCE_F6 = "FRCE_F6";
	public static String TORQ_F6 = "TORQ_F6";
	public static String SPEED_mpm_F6 = "SPEED(mpm)_F6";
	public static String BEND_F6 = "BEND_F6";
	public static String P_CROSS_F6 =  "P-CROSS_F6";
	public static String TENS_F6 = "TENS_F6";
	public static String ROL_TIM_F6 = "ROL TIM_F6";
	public static String IDL_TIM_F6 = "IDL TIM_F6";
	public static String BUR_WEAR_F6 = "BUR WEAR_F6";
	public static String WR_WEAR_F6 = "WR WEAR_F6";
	public static String WR_THRM_F6 = "THRM_F6";
	//
	//F7
	//
	//STAND_F7
	public static String BUR_TDIA_F7 = "BUR TDIA_F7";
	public static String BUR_BDIA_F7 = "BUR BDIA_F7";
	public static String WR_TDIA_F7 = "WR TDIA_F7";
	public static String WR_BDIA_F7 = "WR BDIA_F7";
	public static String WR_ICRN_F7 = "WR ICRN_F7";
	public static String ENTRY_THK_F7 = "ENTRY THK_F7";
	public static String EXIT_THK_F7 = "EXIT THK_F7";
	public static String PAS_LINE_F7 = "PAS LINE_F7";
	public static String ROL_GAP_F7 = "ROL GAP_F7";
	public static String STP_WID_F7 = "STP WID_F7";
	public static String STP_LEN_F7 = "STP LEN_F7";
	public static String ENTRY_TEMP_F7 = "ENTRY TEMP_F7";
	public static String EXIT_TEMP_F7 = "EXIT TEMP_F7";
	public static String FRCE_F7 = "FRCE_F7";
	public static String TORQ_F7 = "TORQ_F7";
	public static String SPEED_mpm_F7 = "SPEED(mpm)_F7";
	public static String BEND_F7 = "BEND_F7";
	public static String P_CROSS_F7 =  "P-CROSS_F7";
	public static String TENS_F7 = "TENS_F7";
	public static String ROL_TIM_F7 = "ROL TIM_F7";
	public static String IDL_TIM_F7 = "IDL TIM_F7";
	public static String BUR_WEAR_F7 = "BUR WEAR_F7";
	public static String WR_WEAR_F7 = "WR WEAR_F7";
	public static String WR_THRM_F7 = "THRM_F7";
	
}
