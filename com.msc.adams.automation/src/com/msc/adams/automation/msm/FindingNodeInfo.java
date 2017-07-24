package com.msc.adams.automation.msm;

import java.util.ArrayList;

import com.msc.io.Reader;
import com.msc.util.myUtil;

public class FindingNodeInfo {

	private ArrayList<String> nodeIDList;
	//private ArrayList<String> allNodeIDList;
	private ArrayList<String> nameList;
	private ArrayList<String> fileDataList; 
	
	public FindingNodeInfo() {
		// TODO Auto-generated constructor stub
		this.nodeIDList = new ArrayList<String>();
		//this.allNodeIDList = new ArrayList<String>();
		this.nameList = new ArrayList<String>();
		this.fileDataList = new ArrayList<String>();
	}

	public void running(String nodeInfoFilepath){
		this.readFileData(nodeInfoFilepath);
		this.findData();
	}
	
	private void readFileData(String path){
		Reader reader = new Reader(path);
		reader.running();
		this.fileDataList = reader.getFileDataList();
	}
	
	private void findData(){
		// size 7
		String id = "";
		String name = "";
		
		ArrayList<String> tokens = new ArrayList<String>();
		for(String line : this.fileDataList){
			tokens = myUtil.splitData(line, " ");
			if(tokens.size() == 7){
				if(tokens.get(0).equals("$")){
					if(myUtil.CheckIntegerValue(tokens.get(1))){
						id = tokens.get(1);
						name = tokens.get(6);
						//this.allNodeIDList.add(id);
						if(this.nodeIDList.contains(id)){
						}else{
							this.nodeIDList.add(tokens.get(1));
							this.nameList.add(name);
						}
						//System.out.println(id + "\t\t" + name);
					}
				}
			}
		}
	}
	
	
	/**
	 * @return the nodeIDList
	 */
	public ArrayList<String> getNodeIDList() {
		return nodeIDList;
	}

	/**
	 * @param nodeIDList the nodeIDList to set
	 */
	public void setNodeIDList(ArrayList<String> nodeIDList) {
		this.nodeIDList = nodeIDList;
	}

	/**
	 * @return the nameList
	 */
	public ArrayList<String> getNameList() {
		return nameList;
	}

	/**
	 * @param nameList the nameList to set
	 */
	public void setNameList(ArrayList<String> nameList) {
		this.nameList = nameList;
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\ee\\Result\\6_NodeInformation\\PA_ROD\\Node_information.PA_ROD.txt";
		
		FindingNodeInfo obj = new FindingNodeInfo();
		obj.running(path);
		
		//myUtil.printArrData(obj.getNodeIDList());
		//myUtil.printArrData(obj.getNameList());
		
		
	}

}
