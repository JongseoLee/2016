package com.js.ens.transformation.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.js.io.Reader;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class Mapping {
	private Map<String,ArrayList<String>> MappingMap;
	//private ArrayList<ArrayList<String>> valueList;
	//private ArrayList<String> values;
	
	private String MappingPath;
	public Mapping() {
		// TODO Auto-generated constructor stub
		MappingMap = new HashMap<String,ArrayList<String>>();
		//valueList = new ArrayList<ArrayList<String>>();
		//values = new ArrayList<String>();
		MappingPath = myUtil.setPath(myUtil.setPath(System.getProperty("user.dir"), "userConfig"),"mappingTable.csv");
	}

	public void readMappingTableFile(){
		Reader obj = new Reader(MappingPath);
		obj.running();
		ArrayList<String> fileData = new ArrayList<String>();
		fileData = obj.getFileDataList();
		for(String line : fileData){
			String data = line.trim();
			if(data.contains(",")){
				ArrayList<String> splitDataTokens = new ArrayList<String>();
				splitDataTokens = ParserDefault.splitLineData(data,",");
				String key = splitDataTokens.get(0).trim();
				
				ArrayList<String> valueList = new ArrayList<String>();
				valueList.add(splitDataTokens.get(1));
				valueList.add(splitDataTokens.get(2));
				valueList.add(splitDataTokens.get(3));
				valueList.add(splitDataTokens.get(4));
				
				MappingMap.put(key,valueList);
			}
		}
	}
	
	public String getVar(String key){
		if(MappingMap.containsKey(key)){
			return MappingMap.get(key).get(0);
		}else{
			return "no-MappingValue";
		}
	}
	
	public String getProcVar(String key){
		if(MappingMap.containsKey(key)){
			return MappingMap.get(key).get(1);
		}else{
			return "no-InitValue";
		}
	}
	
	public String getUILabel(String key){
		if(MappingMap.containsKey(key)){
			return MappingMap.get(key).get(2);
		}else{
			return "no-MappingValue";
		}
	}
	
	/*
	public static String wr_tdia
	public static String wr_bdia
	public static String bur_tdia
	public static String bur_bdia
	public static String wr_crown

	public static String roll_gap

	public static String wr_div_angle
	public static String bur_div_angle

	public static String wr_len
	public static String bur_len

	public static String p_thk
	public static String p_wid
	public static String p_len
	public static String p_ent_temp
	public static String p_exit_temp
	public static String p_in
	public static String pl_m
	public static String t_div

	public static String pl_vel_mpm

	public static String pass_line
	public static String p_cross_ang
	public static String roll_torque
	public static String bend_f
	public static String tens_f

	public static String f_r2p
	public static String f_r2r

	public static String lcase_inc
	public static String post_inc

	public static String frce
	public static String exit_thk
	public static String rol_tim
	public static String idl_tim
	public static String bur_wear
	public static String wr_wear
	public static String wr_thrm

	public static String ym_value
	public static String the_value
	public static String pr_value
	public static String md_value

	public static String lcase_time
	public static String lcase_dt
	public static String ltime_scale
	public static String domain
	public static String thread

	public static String tb_vel_rate_top
	public static String tb_vel_rate_bottom
	public static String wr_trot
	public static String wr_brot
	public static String bur_trot
	public static String bur_brot

	public static String var1
	public static String var2
	public static String var3
	public static String var4
	public static String var5
	public static String var6
	public static String var7
	public static String var8
	public static String var9
	public static String var10
	public static String var11
	public static String var12
	public static String var13
	public static String var14
	public static String var15
	public static String var16

	public static String sthk
	public static String swid
	public static String slen
	public static String swet
	public static String pthk
	public static String pwid
	public static String plen
	public static String pwet
	 */
}
