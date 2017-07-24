package com.msc.adams.commandServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.io.Reader;
import com.msc.parser.ParserDefault;
import com.msc.util.myUtil;

public class CMDFileList {
	
	private static CMDFileList instance = new CMDFileList();
	public static CMDFileList getInstantce(){
		return instance;
	}
	
	private MainController MC = MainController.getInstance();
	private Map<String,String> CMDFileListMap;
	private String CMDFileListPath;
	public CMDFileList() {
		// TODO Auto-generated constructor stub
		this.CMDFileListMap = new HashMap<String,String>();
		this.CMDFileListPath = myUtil.setPath(myUtil.setPath(MC.getAppPath(), DatabaseFolderName.msck_Config),"CMDFileList.ini");
		this.readCMDFileListFile();
	}

	private void readCMDFileListFile(){
		Reader obj = new Reader(this.CMDFileListPath);
		obj.running();
		for(String line : obj.getFileDataList()){
			String data = line.trim();
			if(data.contains("=")){
				ArrayList<String> tokens = new ArrayList<String>();
				tokens = ParserDefault.splitLineData(data, "=");
				this.CMDFileListMap.put(tokens.get(0), tokens.get(1));
			}
		}
	}
	
	public String getFileName(String key){
		if(this.CMDFileListMap.containsKey(key)){
			return this.CMDFileListMap.get(key);
		}else{
			return "no-data";
		}
	}
	
	// 1종 : Module Tandem
	public static String cmd1_MT = "cmd1_MT";
	public static String cmd1_2_Geo_MT = "cmd1_2_Geo_MT";
	public static String cmd1_2_Sph_MT = "cmd1_2_Sph_MT";
	public static String cmd3_MT = "cmd3_MT";
	public static String cmd3_Back_MT = "cmd3_Back_MT";
	public static String cmd4_Simulation_MT = "cmd4_Simulation_MT";
	public static String cmd4_RemovePreSimulation_MT ="cmd4_RemovePreSimulation_MT";
	public static String cmd4_Animation_MT="cmd4_Animation_MT";
	public static String cmd4_AssemModel_MT = "cmd4_AssemModel_MT";
	public static String cmd4_AssemModelBin_MT = "cmd4_AssemModelBin_MT";
	public static String cmd4_DAC_MT = "cmd4_DAC_MT";
	public static String cmd4_Force_MT = "cmd4_Force_MT";
	public static String cmd4_NodeInfomation_MT = "cmd4_NodeInformation_MT";
	public static String cmd4_BladePosition_MT = "cmd4_BladePosition_MT";
	// 2종 : Serial Parallel
	public static String cmd1_SP = "cmd1_SP";
	public static String cmd1_2_Geo_SP = "cmd1_2_Geo_SP";
	public static String cmd1_2_Sph_SP = "cmd1_2_Sph_SP";
	public static String cmd3_SP = "cmd3_SP";
	public static String cmd3_Back_SP = "cmd3_Back_SP";
	public static String cmd4_Simulation_SP = "cmd4_Simulation_SP";
	public static String cmd4_RemovePreSimulation_SP ="cmd4_RemovePreSimulation_SP";
	public static String cmd4_Animation_SP="cmd4_Animation_SP";
	public static String cmd4_AssemModel_SP = "cmd4_AssemModel_SP";
	public static String cmd4_AssemModelBin_SP = "cmd4_AssemModelBin_SP";
	public static String cmd4_DAC_SP = "cmd4_DAC_SP";
	public static String cmd4_Force_SP = "cmd4_Force_SP";
	public static String cmd4_NodeInfomation_SP = "cmd4_NodeInformation_SP";
	public static String cmd4_BladePosition_SP = "cmd4_BladePosition_SP";
	// 3종 : Serial Opposed
	public static String cmd1_SO = "cmd1_SO";
	public static String cmd1_2_Geo_SO = "cmd1_2_Geo_SO";
	public static String cmd1_2_Sph_SO = "cmd1_2_Sph_SO";
	public static String cmd3_SO = "cmd3_SO";
	public static String cmd3_Back_SO = "cmd3_Back_SO";
	public static String cmd4_Simulation_SO = "cmd4_Simulation_SO";
	public static String cmd4_RemovePreSimulation_SO ="cmd4_RemovePreSimulation_SO";
	public static String cmd4_Animation_SO="cmd4_Animation_SO";
	public static String cmd4_AssemModel_SO = "cmd4_AssemModel_SO";
	public static String cmd4_AssemModelBin_SO = "cmd4_AssemModelBin_SO";
	public static String cmd4_DAC_SO = "cmd4_DAC_SO";
	public static String cmd4_Force_SO = "cmd4_Force_SO";
	public static String cmd4_NodeInfomation_SO = "cmd4_NodeInformation_SO";
	public static String cmd4_BladePosition_SO = "cmd4_BladePosition_SO";
	// 4종 : Serial Tandem
	public static String cmd1_ST = "cmd1_ST";
	public static String cmd1_2_Geo_ST = "cmd1_2_Geo_ST";
	public static String cmd1_2_Sph_ST = "cmd1_2_Sph_ST";
	public static String cmd3_ST = "cmd3_ST";
	public static String cmd3_Back_ST = "cmd3_Back_ST";
	public static String cmd4_Simulation_ST = "cmd4_Simulation_ST";
	public static String cmd4_RemovePreSimulation_ST ="cmd4_RemovePreSimulation_ST";
	public static String cmd4_Animation_ST="cmd4_Animation_ST";
	public static String cmd4_AssemModel_ST = "cmd4_AssemModel_ST";
	public static String cmd4_AssemModelBin_ST = "cmd4_AssemModelBin_ST";
	public static String cmd4_DAC_ST = "cmd4_DAC_ST";
	public static String cmd4_Force_ST = "cmd4_Force_ST";
	public static String cmd4_NodeInfomation_ST = "cmd4_NodeInformation_ST";
	public static String cmd4_BladePosition_ST = "cmd4_BladePosition_ST";
	// All
	public static String quit = "quit";
	public static String cmd4_RemovePreSimulation;
}
