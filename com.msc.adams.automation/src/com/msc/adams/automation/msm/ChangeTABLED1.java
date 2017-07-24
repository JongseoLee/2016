package com.msc.adams.automation.msm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.msc.io.Reader;
import com.msc.util.myUtil;

public class ChangeTABLED1 {

	private String allStepNum;
	private String timeStep;
	
	private ArrayList<String> xTimeList_firstData;
	
	private ArrayList<ArrayList<InputColumnData>> IDCLists;
	private ArrayList<String> inputFilePathList;
	private ArrayList<String> outputFileNameList;
	private ArrayList<String> outputFilePathList;
	private ArrayList<String> outputFilePathList_Short;
	
	private Map<String,String> resultMatchID;
	private ArrayList<String> resultTabledIDList;
	
	
	
	public ChangeTABLED1() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(ArrayList<String> forceFilePathList,Map<String,String> nodeID_fileName){
		
		//myUtil.printMapData(nodeID_fileName);
		
		String firstPath = forceFilePathList.get(0);
		this.xTimeList_firstData = new ArrayList<String>();
		this.inputFilePathList = new ArrayList<String>();
		this.inputFilePathList = forceFilePathList;
		this.resultMatchID = new HashMap<String,String>();
		this.resultTabledIDList = new ArrayList<String>();
		
		// 1) force Data 읽고 값 구하기 나누기 
		this.findingRawData(this.readForceData(firstPath));
		this.calcAllStepNum();
		this.calcTimeStep();
		// 2)Tabled1 만들기
		this.outputFileNameList = new ArrayList<String>();
		this.outputFilePathList = new ArrayList<String>();
		this.outputFilePathList_Short = new ArrayList<String>();
		this.initInputColumnDatas(forceFilePathList);
		this.makeTableData(nodeID_fileName);
	}
	
	
	private ArrayList<String> readForceData(String path){
		ArrayList<String> fileDataList = new ArrayList<String>();
		Reader reader = new Reader(path);
		reader.running();
		fileDataList = reader.getFileDataList();
		return fileDataList;
	}
	
	private void findingRawData(ArrayList<String> fileDataList){
		ArrayList<String> tokens = new ArrayList<String>();
		for(String line : fileDataList){
			tokens = myUtil.splitData(line, " ");
			if(tokens.size() == 2){
				if(myUtil.CheckFloatValue(tokens.get(0))){
					this.xTimeList_firstData.add(tokens.get(0));
				}
			}
		}
	}
	
	private ArrayList<String> findingRawDataList(ArrayList<String> fileDataList){
		ArrayList<String> tokens = new ArrayList<String>();
		ArrayList<String> resultList = new ArrayList<String>();
		for(String line : fileDataList){
			tokens = myUtil.splitData(line, " ");
			if(tokens.size() == 2){
				if(myUtil.CheckFloatValue(tokens.get(0))){
					resultList.add(line);
				}
			}
		}
		return resultList;
	}
	
	private void calcAllStepNum(){
		this.allStepNum = String.valueOf(this.xTimeList_firstData.size());
		//System.out.println(this.allStepNum);
	}
	
	private void calcTimeStep(){
		float x1 = Float.parseFloat(this.xTimeList_firstData.get(0));
		float x2 = Float.parseFloat(this.xTimeList_firstData.get(1));
		this.timeStep =myUtil.getSmallNumber(String.valueOf(x2-x1));
		//System.out.println(this.timeStep);
	}
	
	private void initInputColumnDatas(ArrayList<String> filePathList){
		// 값을 저장할 리스트 생성
		this.IDCLists = new ArrayList<ArrayList<InputColumnData>>();
		ArrayList<String> tempFileDataList = new ArrayList<String>();
		ArrayList<InputColumnData> inputColumnDataList; 
		int tableNum = 1;
		for(String path : filePathList){
			tempFileDataList = this.findingRawDataList(this.readForceData(path));
			inputColumnDataList = new ArrayList<InputColumnData>();
			//System.out.println(path);
			this.outputFileNameList.add(new File(path).getName()+".tabled1");
			this.outputFilePathList.add(myUtil.setPath(myUtil.getParentPath(path),new File(path).getName()+".tbd1_"+tableNum));
			
			String name = new File(path).getName();
			ArrayList<String> tokens = myUtil.splitData(name, "\\.");
			String shortFileName = tokens.get(2)+".tbd1_"+tableNum;
			this.outputFilePathList_Short.add(myUtil.setPath(myUtil.getParentPath(path), shortFileName));
			tableNum++;

			for(int i = 0; i < myUtil.splitData(tempFileDataList.get(0), " ").size();i++){
				InputColumnData obj = new InputColumnData();
				inputColumnDataList.add(obj);
			}
			//IDCList에는 ArrayList => x 축 y 축 쌍으로 된 list 가 들어감
			this.IDCLists.add(inputColumnDataList);
		}
		
		//myUtil.printArrData(this.outputFileNameList);
		//myUtil.printArrData(this.outputFilePathList);
		
		
		// 리스트에 값 저장
		
		ArrayList<String> fileDataList = new ArrayList<String>();
		ArrayList<String> tokens = new ArrayList<String>();
		for(int i=0; i< filePathList.size();i++){
			fileDataList = this.findingRawDataList(this.readForceData(filePathList.get(i)));
			//System.out.println(i+ " : "+fileDataList);
			for(String line : fileDataList){
				tokens = myUtil.splitData(line, " ");
				for(int j = 0;j<tokens.size();j++){
					if(tokens.get(j).length() == 1 || tokens.get(j).length() ==2){
						this.IDCLists.get(i).get(j).addData(tokens.get(j)+".");
					}else{
						this.IDCLists.get(i).get(j).addData(tokens.get(j));
					}
				}
			}
		}
	}
	
	private void makeTableData(Map<String,String> nodeID_fileName){
		/* */
		for(int i = 0; i<this.inputFilePathList.size();i++){
			String key = this.inputFilePathList.get(i);
			String value = nodeID_fileName.get(key);
			String tableID = value+(i+1);
			TableGenerator obj = new TableGenerator(this.IDCLists.get(i), this.outputFilePathList.get(i),this.outputFilePathList_Short.get(i),tableID);
			obj.running();
			resultTabledIDList.add(tableID);
			resultMatchID.put(this.outputFilePathList_Short.get(i), tableID);
			
		}
		// */
	}
	
	
	/**
	 * @return the allStepNum
	 */
	public String getAllStepNum() {
		return allStepNum;
	}

	/**
	 * @param allStepNum the allStepNum to set
	 */
	public void setAllStepNum(String allStepNum) {
		this.allStepNum = allStepNum;
	}

	/**
	 * @return the timeStep
	 */
	public String getTimeStep() {
		return timeStep;
	}

	/**
	 * @param timeStep the timeStep to set
	 */
	public void setTimeStep(String timeStep) {
		this.timeStep = timeStep;
	}

	/**
	 * @return the resultMatchID
	 */
	public Map<String, String> getResultMatchID() {
		return resultMatchID;
	}

	/**
	 * @return the resultTabledIDList
	 */
	public ArrayList<String> getResultTabledIDList() {
		return resultTabledIDList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Spherical_PA_ROD_to_MOTOR_CRANK_BALL_PIN_i.Fx_Local.txt";
		ArrayList<String> fileList = new ArrayList<String>();
		fileList.add("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Spherical_PA_ROD_to_MOTOR_CRANK_BALL_PIN_i.Fx_Local.txt");
		fileList.add("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Spherical_PA_ROD_to_MOTOR_CRANK_BALL_PIN_i.Fy_Local.txt");
		fileList.add("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Spherical_PA_ROD_to_MOTOR_CRANK_BALL_PIN_i.Fz_Local.txt");
		fileList.add("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Universal_PA_BALL_PIN_to_PA_ROD_j.Fx_Local.txt");
		fileList.add("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Universal_PA_BALL_PIN_to_PA_ROD_j.Fy_Local.txt");
		fileList.add("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Universal_PA_BALL_PIN_to_PA_ROD_j.Fz_Local.txt");
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Spherical_PA_ROD_to_MOTOR_CRANK_BALL_PIN_i.Fx_Local.txt", "9000001");
		map.put("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Spherical_PA_ROD_to_MOTOR_CRANK_BALL_PIN_i.Fy_Local.txt", "9000001");
		map.put("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Spherical_PA_ROD_to_MOTOR_CRANK_BALL_PIN_i.Fz_Local.txt", "9000001");
		map.put("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Universal_PA_BALL_PIN_to_PA_ROD_j.Fx_Local.txt", "9000002");
		map.put("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Universal_PA_BALL_PIN_to_PA_ROD_j.Fy_Local.txt", "9000002");
		map.put("D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\5_Force\\Base\\Local\\PA_ROD\\PA_ROD.Universal_PA_BALL_PIN_to_PA_ROD_j.Fz_Local.txt", "9000002");
		ChangeTABLED1 obj = new ChangeTABLED1();
		obj.running(fileList,map);
	}

}
