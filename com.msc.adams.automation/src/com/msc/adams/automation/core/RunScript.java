package com.msc.adams.automation.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.io.Reader;
import com.msc.parser.ParserDefault;
import com.msc.util.myUtil;

public class RunScript {
	private MainController MC = MainController.getInstance();
	/*
	private static RunScript instance = new RunScript();
	public static RunScript getInstance(){
		return instance;
	}
	// */
	
	private String RunScriptPath;
	private Map<String,String> RunScriptMap;
	public RunScript() {
		// TODO Auto-generated constructor stub
		this.RunScriptMap = new HashMap<String,String>();
		this.RunScriptPath = myUtil.setPath(myUtil.setPath(MC.getAppPath(), DatabaseFolderName.msck_Config), "SolvingScript.ini");
		this.readSolvingScriptFile();
	}

	private void readSolvingScriptFile(){
		Reader reader = new Reader(this.RunScriptPath);
		reader.running();
		for(String line : reader.getFileDataList()){
			String data = line.trim();
			if(data.contains("=")){
				ArrayList<String> tokens = new ArrayList<String>();
				tokens = ParserDefault.splitLineData(data, "=");
				this.RunScriptMap.put(tokens.get(0), tokens.get(1));
				/*
				System.out.println("------------");
				System.out.println("key : "+tokens.get(0));
				System.out.println("Value : "+tokens.get(1));
				// */
			}
		}
	}
	
	public String getRunScript(String key){
		if(this.RunScriptMap.containsKey(key)){
			//System.out.println("!!!!!!"+this.RunScriptMap.get(key));
			return this.RunScriptMap.get(key);
		}else{
			return "no-Scirpt";
		}
	}
	
	public static String OPT 		= 	"OPT";
	public static String OPT_PARAM 	= 	"OPT_PARAM";
	public static String FEMFAT 	= 	"FEMFAT";
	public static String FEMFAT_PARAM	=	"FEMFAT_PARAM";
	
}
