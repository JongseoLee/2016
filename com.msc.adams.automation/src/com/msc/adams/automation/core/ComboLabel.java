package com.msc.adams.automation.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.io.Reader;
import com.msc.parser.ParserDefault;
import com.msc.util.myUtil;

public class ComboLabel {
	private MainController MC = MainController.getInstance();
	private static ComboLabel instance = new ComboLabel();
	public static ComboLabel getInstance(){
		return instance;
	}
	
	private Map<String,String> ComboLabelMap;
	private String ComboLabelPath;
	
	public ComboLabel() {
		// TODO Auto-generated constructor stub
		this.ComboLabelMap = new HashMap<String,String>();
		//this.ComboLabelPath = myUtil.setPath(myUtil.setPath(System.getProperty("user.dir"),DatabaseFolderName.msck_Config), "ComboLabel.ini");
		this.ComboLabelPath = myUtil.setPath(myUtil.setPath(MC.getAppPath(),DatabaseFolderName.msck_Config), "ComboLabel.ini");
		this.readUILabelFile();
	}

	private void readUILabelFile(){
		Reader obj = new Reader(this.ComboLabelPath);
		obj.running();
		for(String line : obj.getFileDataList()){
			String data = line.trim();
			if(data.contains("=")){
				ArrayList<String> tokens = new ArrayList<String>();
				tokens = ParserDefault.splitLineData(data, "=");
				this.ComboLabelMap.put(tokens.get(0), tokens.get(1));
			}
		}
	}
	
	public String getLabel(String key){
		if(this.ComboLabelMap.containsKey(key)){
			return this.ComboLabelMap.get(key);
		}else{
			return "no-Label";
		}
	}
	
	// Step1 Label
	/*
	public static String Symmetric 	=	"Symmetric";
	public static String NonSymmetric_inner 	=	"NonSymmetric_inner";
	public static String NonSymmetric_outer 	=	"NonSymmetric_outer";
	// */
	public static String Symmetric_Same_Basic = "Symmetric_Same_Basic";
	public static String Symmetric_Different_Basic = "Symmetric_Different_Basic";
	public static String NonSymmetric_Outer_Basic = "NonSymmetric_Outer_Basic";
	public static String NonSymmetric_Inner_Basic = "NonSymmetric_Inner_Basic";
	//public static String BLADETYPE3 	=	"BladeType3";
	//public static String BLADETYPE4 	=	"BladeType4";
	
	public static String SerialParallel 	=	"SerialParallel";
	public static String SerialOpposed 	=	"SerialOpposed";
	public static String ModuleTandem 	=	"ModuleTandem";
	public static String SerialTandem 	=	"SerialTandem";
	
	public static String Sphere=	"Sphere";
	public static String Geometry=	"Geometry";
	
	
}
