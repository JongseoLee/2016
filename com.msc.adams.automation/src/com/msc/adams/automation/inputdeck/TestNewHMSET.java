package com.msc.adams.automation.inputdeck;

import java.util.ArrayList;

import com.msc.io.Reader;
import com.msc.util.myUtil;

public class TestNewHMSET {

	
	private ArrayList<String> fileDataList = new ArrayList<String>();
	private ArrayList<HMSETObj> HMSETObjList = new ArrayList<HMSETObj>();
	private ArrayList<SETObj> SETObjList = new ArrayList<SETObj>();
	
	
	public TestNewHMSET() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(String path){
		//this.readData(path);
		this.getBulkData(path);
		this.checkResult();
	}
	
	private void readData(String path){
		Reader reader = new Reader(path);
		reader.running();
		this.fileDataList = reader.getFileDataList();
	}
	
	public void getBulkData(String path){
		ArrayList<String> tempFileDataList = new ArrayList<String>();
		Reader reader = new Reader(path);
		reader.running();
		tempFileDataList = reader.getFileDataList();
		
		ArrayList<String> tokens = new ArrayList<String>();
		
		for(int i=0;i<tempFileDataList.size();i++){
			String line = tempFileDataList.get(i);
			tokens = myUtil.splitData(line, " ");
			
			if(tokens.size() != 0){
				// $HMSET Ã£±â
				if(tokens.get(0).equals("$HMSET")){
					String id = tokens.get(1);
					HMSETObj hmsetObj = new HMSETObj(id);
					hmsetObj.addLine(line);
					HMSETObjList.add(hmsetObj);
				}
				
				if(tokens.get(0).equals("SET")){
					String id = tokens.get(1);
					SETObj setObj = new SETObj(id);
					
					int endLineNum = i+1;
					for(int j=i;j<endLineNum;j++){
						String innerLine = tempFileDataList.get(j);
						ArrayList<String> innerTokens = myUtil.splitData(innerLine, " ");					
						String lastToken= innerTokens.get(innerTokens.size()-1);
						char lastChar = lastToken.charAt(lastToken.length()-1);
						
						String innerLine2 = tempFileDataList.get(j+1);
						if(innerLine2.length() == 0){
							innerLine2 = "empty";
						}
						ArrayList<String> innerTokens2 = myUtil.splitData(innerLine2, " ");
						String firstToken = innerTokens2.get(0);
						char firstChar = firstToken.charAt(0);
						
						if(lastChar == ','){
							endLineNum++;
							setObj.addLine(innerLine);
						}else{
							setObj.addLine(innerLine);
							if(firstChar == '+'){
								endLineNum++;
							}
						}
					}
					SETObjList.add(setObj);
				}
			}
		}
	}
	
	private void checkResult(){
		ArrayList<String> sortingHMSET = new ArrayList<String>();
		for(HMSETObj hmsetObj : this.HMSETObjList){
			String hmsetId = hmsetObj.getID();
			for(SETObj setObj : this.SETObjList){
				String setID = setObj.getID();
				if(hmsetId.equals(setID)){
					for(String setLine : setObj.getLinesList()){
						sortingHMSET.add(setLine);
					}
				}
			}
			for(String hmsetLine : hmsetObj.getLinesList())
				sortingHMSET.add(hmsetLine);
		}
		myUtil.printArrData(sortingHMSET);
	}
	
	public static void main(String [] args){
		String path ="D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Template\\editBulk\\sample.fem";
		
		TestNewHMSET obj = new TestNewHMSET();
		obj.running(path);
	}
}
