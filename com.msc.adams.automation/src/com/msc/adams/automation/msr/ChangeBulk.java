package com.msc.adams.automation.msr;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.datas.Part;
import com.msc.adams.automation.inputdeck.HMSETObj;
import com.msc.adams.automation.inputdeck.SETObj;
import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.parser.ParserDefault;
import com.msc.util.myUtil;

public class ChangeBulk {
	//private MainController MC = MainController.getInstance();
	
	private String sourcePath;
	private String destPath;
	private ArrayList<String> fileDataList;
	private ArrayList<String> hmsetDataList;
	private ArrayList<Integer> lineNumList;
	private ArrayList<String> outputList;
	//private int benginBulkLineNum = 0;
	public ChangeBulk() {
		// TODO Auto-generated constructor stub
		this.fileDataList = new ArrayList<String>();
		this.hmsetDataList = new ArrayList<String>();
		this.lineNumList = new ArrayList<Integer>();
		this.outputList = new ArrayList<String>();
	}
	
	public void running(String sourceBulkPath,String destBulkPath, String mnfType){
		this.sourcePath = sourceBulkPath;
		this.destPath = destBulkPath;
		
		this.readSourceFile();
		if(mnfType.equals(Part.Type_MNF)){
			this.findingHMSET_TypeMnf();
		}else{
			this.findingHMSET();
		}
		
		//this.removeHMSET();
		//this.genBulk();
		this.writeBulk();
	}
	
	private void readSourceFile(){
		Reader reader = new Reader(this.sourcePath);
		reader.running();
		this.fileDataList = reader.getFileDataList();
	}
	
	private void findingHMSET(){
		ArrayList<String> tokens = new ArrayList<String>();
		for(int i=0;i<this.fileDataList.size();i++){
			
			String line = this.fileDataList.get(i);
			tokens = myUtil.splitData(line, " ");
			
			if(tokens.size() != 0){
				if(tokens.get(0).equals("$HMSET")){
					this.hmsetDataList.add(line);
					this.lineNumList.add(i);
				}
				if(tokens.get(0).equals("SET")){
					int endLineNum = i+1;
					for(int j=i ;j < endLineNum ;j++){
						String innerLine = fileDataList.get(j);
						ArrayList<String> innerTokens = myUtil.splitData(innerLine, " ");					
						String lastToken= innerTokens.get(innerTokens.size()-1);
						char lastChar = lastToken.charAt(lastToken.length()-1);
						
						String innerLine2 = fileDataList.get(j+1);
						if(innerLine2.length() == 0){
							innerLine2 = "empty";
						}
						ArrayList<String> innerTokens2 = myUtil.splitData(innerLine2, " ");
						String firstToken = innerTokens2.get(0);
						char firstChar = firstToken.charAt(0);
						
						if(lastChar == ','){
							endLineNum++;
							hmsetDataList.add(innerLine);
							
						}else{
							hmsetDataList.add(innerLine);
							if(firstChar == '+'){
								endLineNum++;
							}
						}
						
					}
				}			
			}
		}
		
		for(String remove : hmsetDataList){
			fileDataList.remove(remove);
		}
		
		for(String line : fileDataList){
			
			if(line.trim().toLowerCase().equals("begin bulk")){
				outputList.add("$----------------------------------------------------------------------------------");
				for(String newLine : hmsetDataList){
					outputList.add(newLine);
				}
				outputList.add("$----------------------------------------------------------------------------------");
				outputList.add(line);
				
			}else{
				outputList.add(line);
			}
		}
	}
	
	
	private void findingHMSET_TypeMnf(){

		ArrayList<String> tokens = new ArrayList<String>();
		ArrayList<SETObj> SETObjList = new ArrayList<SETObj>();
		ArrayList<HMSETObj> HMSETObjList = new ArrayList<HMSETObj>();
		ArrayList<String> AllHMSETDataListForRemove = new ArrayList<String>();
		
		
		for(int i=0;i<this.fileDataList.size();i++){
			
			String line = this.fileDataList.get(i);
			tokens = myUtil.splitData(line, " ");
			
			if(tokens.size() != 0){
				if(tokens.get(0).equals("$HMSET")){
					//this.hmsetDataList.add(line);
					//this.lineNumList.add(i);
					AllHMSETDataListForRemove.add(line);
					String id = tokens.get(1);
					HMSETObj hmsetObj = new HMSETObj(id);
					hmsetObj.addLine(line);
					HMSETObjList.add(hmsetObj);
				}
				if(tokens.get(0).equals("SET")){
					String id = tokens.get(1);
					SETObj setObj = new SETObj(id);
					
					int endLineNum = i+1;
					for(int j=i ;j < endLineNum ;j++){
						String innerLine = fileDataList.get(j);
						ArrayList<String> innerTokens = myUtil.splitData(innerLine, " ");					
						String lastToken= innerTokens.get(innerTokens.size()-1);
						char lastChar = lastToken.charAt(lastToken.length()-1);
						
						String innerLine2 = fileDataList.get(j+1);
						if(innerLine2.length() == 0){
							innerLine2 = "empty";
						}
						ArrayList<String> innerTokens2 = myUtil.splitData(innerLine2, " ");
						String firstToken = innerTokens2.get(0);
						char firstChar = firstToken.charAt(0);
						
						if(lastChar == ','){
							endLineNum++;
							AllHMSETDataListForRemove.add(innerLine);
							setObj.addLine(innerLine);
							
						}else{
							AllHMSETDataListForRemove.add(innerLine);
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
		
		for(String remove : AllHMSETDataListForRemove){
			fileDataList.remove(remove);
		}
		
		for(String line : fileDataList){
			if(line.trim().toLowerCase().equals("begin bulk")){
				this.outputList.add("$----------------------------------------------------------------------------------");
				for(HMSETObj hmsetObj : HMSETObjList){
					String hmsetId = hmsetObj.getID();
					for(String hmsetLine : hmsetObj.getLinesList()){
						this.outputList.add(hmsetLine);
					}
					
					for(SETObj setObj : SETObjList){
						String setID = setObj.getID();
						if(hmsetId.equals(setID)){
							for(String setLine : setObj.getLinesList()){
								this.outputList.add(setLine);
							}
						}
					}
					
					for(String hmsetLine : hmsetObj.getLinesList()){
						this.outputList.add(hmsetLine);
					}					
				}
				this.outputList.add("$----------------------------------------------------------------------------------");
				this.outputList.add(line);
				
			}else{
				this.outputList.add(line);
			}
			
		}
	}
	
	/*
	private void removeHMSET(){
		for(int i = this.lineNumList.size()-1 ; i>=0 ; i--){
			String str = this.fileDataList.get(this.lineNumList.get(i));
			this.fileDataList.remove((int)this.lineNumList.get(i));
		}
	}
	
	private void genBulk(){
		for(String line : this.fileDataList){
			if(line.trim().toLowerCase().equals("begin bulk")){
				for(String newLine : this.hmsetDataList){
					this.outputList.add(newLine);
				}
				this.outputList.add(line);
			}else{
				this.outputList.add(line);
			}
		}
	}
	// */
	
	private void writeBulk(){
		Writer writer = new Writer(this.destPath);
		writer.running(this.outputList);
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sPath = "E:\\jslee\\PA_ROD_mnf_creation.fem";
		String dPath = "E:\\jslee\\PA_ROD_result.fem";
		ChangeBulk obj = new ChangeBulk();
		obj.running(sPath, dPath,Part.Type_OPT);
	}

}
