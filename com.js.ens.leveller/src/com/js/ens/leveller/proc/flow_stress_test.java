package com.js.ens.leveller.proc;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class flow_stress_test {
	//private Logger log = Logger.getLogger(flow_stress_test.class);
	
	//private LevellerMain LMain = LevellerMain.getInstatnce();
	private String value_flow_stress;
	private String procFilePath;
	
	private ArrayList<String> fileDataList;
	private ArrayList<String> procDataList;
	private ArrayList<String> fileDataList2;
	//
	private String tableName;
	private String AllCurveNum;
	private String PlasticDataSetNum;
	private String TemperatureDataSetNum;
	private String PlaticStrainDataSetNum;
	
	private ArrayList<String> plasstic_Strain;
	private ArrayList<String> Temperature;
	private ArrayList<String> Plastic_Strain_Rate;
	private ArrayList<String> curveTitleList;
	private ArrayList<String> curveDataTable;
	//
	
	public flow_stress_test(){
		//plateType = LMain.getcomboType();
		fileDataList = new ArrayList<String>();
		fileDataList2 = new ArrayList<String>();
	}
	
	public flow_stress_test(String value_flow_stress){
		this.value_flow_stress = value_flow_stress;
		fileDataList = new ArrayList<String>();
		fileDataList2 = new ArrayList<String>();
	}
	
	public void running(){
		
			// 실제 path 설정
			procFilePath = "G:\\eclipse-rcp\\eclipse\\materialData\\result_1.proc";
			// sample Test 
			//procFilePath = "D:\\Q\\new\\Workspace\\com.js.ens.leveller\\src\\com\\js\\ens\\leveller\\proc\\sample\\02_flow_stress.proc";
			readSourceData();
			parsingData();
			
			readSourceData2();
			swapValue();
			writeProc();
			
			
		
	}
	
	public void readSourceData(){
		Reader reader = new Reader(this.value_flow_stress);
		reader.running();
		fileDataList = reader.getFileDataList(); 
	}
	
	public void parsingData() {
		plasstic_Strain = new ArrayList<String>();
		Temperature = new ArrayList<String>();
		Plastic_Strain_Rate = new ArrayList<String>();
		curveTitleList = new ArrayList<String>();
		curveDataTable = new ArrayList<String>();
		
		ArrayList<String> splitDataTokens = new ArrayList<String>();
		String firstLine = fileDataList.get(1).replace("\t", " ").replace("," ,"");
		splitDataTokens = ParserDefault.splitLineData(firstLine," ");
		System.out.println(splitDataTokens);
		tableName = fileDataList.get(0);
		AllCurveNum = splitDataTokens.get(0);
		PlasticDataSetNum = splitDataTokens.get(1);
		TemperatureDataSetNum = splitDataTokens.get(2);
		PlaticStrainDataSetNum = splitDataTokens.get(3);
		
		int curveNum = 0;
		
		for(int i=5;i<fileDataList.size();i++){
			String line = fileDataList.get(i).replace("\t", " ").replace(",", "");
			splitDataTokens = ParserDefault.splitLineData(line, " ");
			String firstTokens = splitDataTokens.get(0);
			
			if(firstTokens.contains("===")){
				// === CURVE_01 Sig_Yiel	 T=20.0 C	 Eps_dot=1.60 1/s
				//               0               1           2                    
				// === CURVE_02 Sig_Yiel	 T=100.0 C	 Eps_dot=1.60 1/s
				//  0      1        2            3   4       5         6 
				curveTitleList.add(line);
				//String TValue = ParserDefault.splitLineData(ParserDefault.splitLineData(splitDataTokens.get(1), "=").get(1), " ").get(0);
				//String SPValue = ParserDefault.splitLineData(ParserDefault.splitLineData(splitDataTokens.get(2), "=").get(1), " ").get(0);
				String TValue = ParserDefault.splitLineData(splitDataTokens.get(3), "=").get(1);
				String SPValue = ParserDefault.splitLineData(splitDataTokens.get(5), "=").get(1);
				if(curveNum == -1){
					Temperature.add(TValue);
					Plastic_Strain_Rate.add(SPValue);
					
				}else {
					if(!Temperature.contains(TValue)){
						Temperature.add(TValue);
					}
					if(!Plastic_Strain_Rate.contains(SPValue)){
						Plastic_Strain_Rate.add(SPValue);	
					}
				}
				i=i+1;
				curveNum++;
			}
			if(splitDataTokens.size() == 2 && myUtil.isFloatValue(firstTokens)){
				if(curveNum == 1){
					plasstic_Strain.add(splitDataTokens.get(0));
				}
				curveDataTable.add(splitDataTokens.get(1));
			}
		}
	}
	
	public void readSourceData2(){
		/*
		String modulePath = myUtil.setPath(System.getProperty("user.dir"), "module");
		String s_folder = null;
		if(plateType.equals(ComboLabel.TYPE1))	
			s_folder = myUtil.setPath(modulePath,"1_Flat");
		else if(plateType.equals(ComboLabel.TYPE2))
			s_folder = myUtil.setPath(modulePath,"2_EdgeWave");
		else if(plateType.equals(ComboLabel.TYPE3))
			s_folder = myUtil.setPath(modulePath,"3_CenterWave");
		else if(plateType.equals(ComboLabel.TYPE4))
			s_folder = myUtil.setPath(modulePath,"4_SingleGutter");
		else if(plateType.equals(ComboLabel.TYPE5))
			s_folder = myUtil.setPath(modulePath,"5_PartialGutter");
		else if(plateType.equals(ComboLabel.TYPE6))
			s_folder = myUtil.setPath(modulePath,"6_DoubleGutter");
		else if(plateType.equals(ComboLabel.TYPE7))
			s_folder = myUtil.setPath(modulePath,"7_IslandGutter");
		String sourcePath = myUtil.setPath(s_folder, "mat02_flow_stress.proc");
		*/
		String procPath = "G:\\eclipse-rcp\\eclipse\\materialData\\mat02_flow_stress.proc";
		Reader reader = new Reader(procPath);
		reader.running();
		fileDataList2 = reader.getFileDataList();
	}
	
	public void swapValue(){
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : fileDataList2){
			if(line.contains("%tableName%")){
				newLine = line.replace("%tableName%", this.tableName);
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%PlasticDataSetNum%")){
				newLine = line.replace("%PlasticDataSetNum%", this.PlasticDataSetNum);
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%TemperatureDataSetNum%")){
				newLine = line.replace("%TemperatureDataSetNum%", this.TemperatureDataSetNum);
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%PlaticStrainDataSetNum%")){
				newLine = line.replace("%PlaticStrainDataSetNum%", this.PlaticStrainDataSetNum);
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%plasstic_Strain%")){
				for(String str : plasstic_Strain){
					procDataList.add(str);
				}
				continue;
			}else if(line.contains("%Temperature%")){
				for(String str : Temperature){
					procDataList.add(str);
				}
				continue;
			}else if(line.contains("%Plastic_Strain_Rate%")){
				for(String str : Plastic_Strain_Rate){
					procDataList.add(str);
				}
				continue;
			}else if(line.contains("%curveDataTable%")){
				int tableColNum = 0;
				for(int i=0; i<Integer.parseInt(AllCurveNum) ;i++){
					procDataList.add("|"+curveTitleList.get(i));
					for(int j = tableColNum;j<Integer.parseInt(PlasticDataSetNum)*(i+1);j++){
						procDataList.add(curveDataTable.get(j));
						tableColNum++;
					}
				}
				continue;
			}else {
				procDataList.add(line);
			}
		}
	}
	
	public void writeProc(){
		Writer writer = new Writer(procFilePath,procDataList);
		writer.running();
	}
	
	public static void main (String [] args){
		System.out.println("start");
		String path1 = "G:\\eclipse-rcp\\eclipse\\materialData\\flow_stress.txt";
		String path2 = "G:\\eclipse-rcp\\eclipse\\materialData\\flow_stress_test.txt";
		String path3 = "G:\\eclipse-rcp\\eclipse\\materialData\\flow_stress_test2.txt";
		
		flow_stress_test obj = new flow_stress_test(path1);
		obj.running();
		
	}
}

