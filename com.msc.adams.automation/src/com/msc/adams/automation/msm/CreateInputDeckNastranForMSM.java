package com.msc.adams.automation.msm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.adams.automation.datas.Part_FatSolving;
import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.util.myUtil;

public class CreateInputDeckNastranForMSM {
	private MainController MC = MainController.getInstance();
	private Mediator med = Mediator.getInstance();
	
	private Part_FatSolving fsObj;
	
	public final String HeaderFileName = "MSM-HEADER-NASTRAN.txt";
	
	public final String OnlyBulkFilePath	= "%OnlyBulkFilePath%";
	public final String TSTEP_Line			= "%TSTEP_Line%";
	public final String TLOAD1_Line			= "%TLOAD1_Line%";
	public final String DLOAD_Line  		= "%DLOAD_Line%";
	public final String FORCE_Line			= "%FORCE_Line%";
	public final String TABLED1_Line  		= "%TABLED1_Line%";
	
	public String headerFilePath;
	private ArrayList<String> HeaderFileDataList;
	
	public String bulkFilePath;
	public String allStepNum;
	public String timeStep;
	private ArrayList<String> tload1IDList;
	private ArrayList<String> forceIDList;
	private Map<String,String> resultMatchIDMap;
	private ArrayList<String> resultTabledIDList;
	private ArrayList<String> allNodeIdList;
	//result 
	private String inputdeckPath;
	private ArrayList<String> outputDataList;
	
	
	public CreateInputDeckNastranForMSM() {
		// TODO Auto-generated constructor stub
	}
	
	public void initData(String bulkFilePath, String allStepNum, String timeStep, Map<String,String> resultMatchIDMap, ArrayList<String> resultTabledIDList, ArrayList<String> allNodeIdList){
		this.bulkFilePath = bulkFilePath;
		this.allStepNum = allStepNum;
		this.timeStep = timeStep;
		this.resultMatchIDMap = resultMatchIDMap;
		this.resultTabledIDList = resultTabledIDList;
		this.allNodeIdList = allNodeIdList;
		this.outputDataList = new ArrayList<String>();
		this.tload1IDList = new ArrayList<String>();
		this.forceIDList = new ArrayList<String>();
		
	}
	
	public void running(Part_FatSolving fsObj){
		this.fsObj = fsObj;
		String path = myUtil.getParentPath(this.bulkFilePath);
		String inputFileName = this.fsObj.getPartName()+"_msm_creation.bdf";
		this.inputdeckPath = myUtil.setPath(path, inputFileName);
		
		String msck_TemplatePath = myUtil.setPath(MC.getAppPath(), DatabaseFolderName.msck_Template);
		String HeaderFileFolderPath = myUtil.setPath(msck_TemplatePath, DatabaseFolderName.HeaderFile);
		this.headerFilePath = myUtil.setPath(HeaderFileFolderPath, HeaderFileName);
		
		this.ReadHeaderFile();
		this.CreateInputdeck();
		this.writerInputdeck();
	}
	
	public void running_manual(String partName){
		String path = myUtil.getParentPath(this.bulkFilePath);
		String inputFileName = partName+"_msm_creation.bdf";
		this.inputdeckPath = myUtil.setPath(path, inputFileName);
		
		String AppPath = "D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC";
		String msck_TemplatePath = myUtil.setPath(AppPath, DatabaseFolderName.msck_Template);
		String HeaderFileFolderPath = myUtil.setPath(msck_TemplatePath, DatabaseFolderName.HeaderFile);
		this.headerFilePath = myUtil.setPath(HeaderFileFolderPath, HeaderFileName);
		
		this.ReadHeaderFile();
		this.CreateInputdeck();
		this.writerInputdeck();
	}
	
	private void ReadHeaderFile(){
		Reader reader = new Reader(this.headerFilePath);
		reader.running();
		this.HeaderFileDataList = reader.getFileDataList();
	}
	
	private void CreateInputdeck(){
		for(String line : this.HeaderFileDataList){
			if(line.contains(OnlyBulkFilePath)){
				String fileName = new File(this.bulkFilePath).getName();
				String newLine = line.replace(OnlyBulkFilePath, fileName);
				this.outputDataList.add(newLine);
			}else if(line.contains(TSTEP_Line)){
				String newLine = line.replace(TSTEP_Line, this.getTSTEP_Line());
				this.outputDataList.add(newLine);
			}else if(line.contains(TLOAD1_Line)){
				for(String getLine : this.getTLOAD1_Line()){
					this.outputDataList.add(getLine);
				}
			}else if(line.contains(DLOAD_Line)){
				String newLine = line.replace(DLOAD_Line, this.getDLOAD_Line());
				this.outputDataList.add(newLine);
			}else if(line.contains(FORCE_Line)){
				for(String getLine : this.getFORCE_Line()){
					this.outputDataList.add(getLine);
				}
			}else if(line.contains(TABLED1_Line)){
				for(String getLine : this.getTABLED1_Line()){
					this.outputDataList.add(getLine);
				}
			}else {
				this.outputDataList.add(line);
			}
		}
	}
	
	private String getTSTEP_Line(){
		// $2345678$2345678$2345678$2345678$2345678
		// TSTEP   10      10      .1
		String result = String.format("%-8s", "TSTEP") +
						String.format("%-8s", "10") +
						String.format("%-8s", this.allStepNum) +
						String.format("%-8s", this.timeStep);
		return result;
	}
	
	private ArrayList<String> getTLOAD1_Line(){
		ArrayList<String> resultList = new ArrayList<String>();
		int startTLOAD1ID = 101;
		int startForceID = 301;
		for(String id : this.resultTabledIDList){
			String line = String.format("%-8s", "TLOAD1") + 
							String.format("%-8s", String.valueOf(startTLOAD1ID)) + 
							String.format("%-8s", String.valueOf(startForceID)) +
							String.format("%-8s", " ") + 
							String.format("%-8s", " ") + 
							String.format("%-8s", id);
			resultList.add(line);
			this.tload1IDList.add(String.valueOf(startTLOAD1ID));
			this.forceIDList.add(String.valueOf(startForceID));
			startTLOAD1ID++;
			startForceID++;
		}
		return resultList;
	}
	
	private String getDLOAD_Line(){
		//    1        2       3       4      5       6        7       8       9      
		// $2345678$2345678$2345678$2345678$2345678$2345678$2345678$2345678$2345678
		// DLOAD   200     1.      1.      21      1.      22      1.      23
		//         1.      24      1.      25      1.      26
		
		int size = this.tload1IDList.size();
		
		String result = String.format("%-8s","DLOAD") + 
				String.format("%-8s", "200") +
				String.format("%-8s", "1.");
		if(size == 1){
			result = result +	String.format("%-8s", "1.") + String.format("%-8s", this.tload1IDList.get(0));
		}else if(size == 2){
			result = result + String.format("%-8s", "1.") + 	String.format("%-8s", this.tload1IDList.get(0)) +
								String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(1));
		}else if(size == 3){
			result = result + String.format("%-8s", "1.") + 	String.format("%-8s", this.tload1IDList.get(0)) +
								String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(1)) +
								String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(2));
		}else if(size == 4){
			result = result + String.format("%-8s", "1.") + 	String.format("%-8s", this.tload1IDList.get(0)) +
								String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(1)) +
								String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(2)) + 
								"\n" + String.format("%-8s", " ") +
								String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(3));
		}else if(size == 5){
			result = result + String.format("%-8s", "1.") + 	String.format("%-8s", this.tload1IDList.get(0)) +
								String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(1)) +
								String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(2)) + 
								"\n" + String.format("%-8s", " ") +
								String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(3)) + 
								String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(4));
		}else if(size == 6){
			result = result + String.format("%-8s", "1.") + 	String.format("%-8s", this.tload1IDList.get(0)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(1)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(2)) + 
					"\n" + String.format("%-8s", " ") +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(3)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(4)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(5));
		}else if(size == 7){
			result = result + String.format("%-8s", "1.") + 	String.format("%-8s", this.tload1IDList.get(0)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(1)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(2)) + 
					"\n" + String.format("%-8s", " ") +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(3)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(4)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(5)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(6));
		}else if(size == 8){
			result = result + String.format("%-8s", "1.") + 	String.format("%-8s", this.tload1IDList.get(0)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(1)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(2)) + 
					"\n" + String.format("%-8s", " ") +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(3)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(4)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(5)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(6)) +
					"\n" + String.format("%-8s", " ") +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(7));
		}else if(size == 9){
			result = result + String.format("%-8s", "1.") + 	String.format("%-8s", this.tload1IDList.get(0)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(1)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(2)) + 
					"\n" + String.format("%-8s", " ") +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(3)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(4)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(5)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(6)) +
					"\n" + String.format("%-8s", " ") +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(7)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(8));
		}else if(size == 10){
			result = result + String.format("%-8s", "1.") + 	String.format("%-8s", this.tload1IDList.get(0)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(1)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(2)) + 
					"\n" + String.format("%-8s", " ") +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(3)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(4)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(5)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(6)) +
					"\n" + String.format("%-8s", " ") +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(7)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(8)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(9));
		}else if(size == 11){
			result = result + String.format("%-8s", "1.") + 	String.format("%-8s", this.tload1IDList.get(0)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(1)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(2)) + 
					"\n" + String.format("%-8s", " ") +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(3)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(4)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(5)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(6)) +
					"\n" + String.format("%-8s", " ") +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(7)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(8)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(9)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(10));
		}else if(size == 12){
			result = result + String.format("%-8s", "1.") + 	String.format("%-8s", this.tload1IDList.get(0)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(1)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(2)) + 
					"\n" + String.format("%-8s", " ") +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(3)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(4)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(5)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(6)) +
					"\n" + String.format("%-8s", " ") +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(7)) + 
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(8)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(9)) +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(10)) +
					"\n" + String.format("%-8s", " ") +
					String.format("%-8s", "1.") +	String.format("%-8s", this.tload1IDList.get(11));
		}
		return result;
	}
	
	private ArrayList<String> getFORCE_Line(){
		ArrayList<String> resultList = new ArrayList<String>();
		int checkNum = 1;
		for(int i=0; i<this.forceIDList.size();i++){
			String line = String.format("%-8s", "FORCE") +
							String.format("%-8s", this.forceIDList.get(i)) + 
							String.format("%-8s", this.allNodeIdList.get(i)) +
							String.format("%-8s", "0") +
							String.format("%-8s", "1.");
			if(checkNum == 1){
				line = line + String.format("%-8s", "1.")+
								String.format("%-8s", "0.")+
								String.format("%-8s", "0.");
				checkNum++;
			}else if(checkNum == 2){
				line = line + String.format("%-8s", "0.")+
						String.format("%-8s", "1.")+
						String.format("%-8s", "0.");
				checkNum++;
			}else if(checkNum ==3){
				line = line + String.format("%-8s", "0.")+
						String.format("%-8s", "0.")+
						String.format("%-8s", "1.");
				checkNum = 1;
			}
			resultList.add(line);
		}
		return resultList;
	}
	
	private ArrayList<String> getTABLED1_Line(){
		ArrayList<String> resultList = new ArrayList<String>();
		for(String ID : this.resultTabledIDList){
			String fileName = new File(this.getTABLED1FilePath(ID)).getName();
			String line = "INCLUDE '"+fileName+"'";
			resultList.add(line);
		}
		return resultList;
 	}
	
	private String getTABLED1FilePath(String id){
		String path = "";
		Iterator<String> iterator = this.resultMatchIDMap.keySet().iterator();
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			String value = this.resultMatchIDMap.get(key);
			if(value.equals(id)){
				path = key;
				break;
			}
		}
		return path;
	}
	
	private void writerInputdeck(){
		Writer writer = new Writer(this.inputdeckPath);
		writer.running(outputDataList);
		
		//System.out.println("========>"+this.inputdeckPath);
	}
	
	
	
	
	
	/**
	 * @return the inputdeckPath
	 */
	public String getInputdeckPath() {
		return inputdeckPath;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("90000011");
		list.add("90000012");
		list.add("90000013");
		list.add("90000024");
		list.add("90000025");
		list.add("90000026");
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Spherical_PA_ROD_to_MOTOR_CRANK_BALL_PIN_i.Fx_Local.txt", "90000011");
		map.put("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Spherical_PA_ROD_to_MOTOR_CRANK_BALL_PIN_i.Fy_Local.txt", "90000012");
		map.put("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Spherical_PA_ROD_to_MOTOR_CRANK_BALL_PIN_i.Fz_Local.txt", "90000013");
		map.put("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Universal_PA_BALL_PIN_to_PA_ROD_j.Fx_Local.txt", "90000024");
		map.put("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Universal_PA_BALL_PIN_to_PA_ROD_j.Fy_Local.txt", "90000025");
		map.put("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Universal_PA_BALL_PIN_to_PA_ROD_j.Fz_Local.txt", "90000026");
		
		
		for(String id : list){
			Iterator<String> iterator = map.keySet().iterator();
			String path = "";
			while(iterator.hasNext()){
				String key = (String)iterator.next();
				String value = map.get(key);
				if(value.equals(id)){
					path = key;
					break;
				}
			}
			System.out.println(path);
		}
		
		
	}

}
