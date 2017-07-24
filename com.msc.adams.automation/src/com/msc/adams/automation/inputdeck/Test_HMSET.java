package com.msc.adams.automation.inputdeck;

import java.util.ArrayList;

import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.util.myUtil;

public class Test_HMSET {

	private ArrayList<String> fileDataList = new ArrayList<String>();
	public Test_HMSET() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(){
		String bulkPath = "D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Template\\PA_ROD_170417_2.fem";
		/*
		for(String line : this.moveHMSET(bulkPath)){
			System.out.println(line);
		}
		// */
		Writer writer = new Writer("E:\\HMC_FEM\\result.txt");
		writer.running(this.moveHMSET(bulkPath));
		
	}
	
	public ArrayList<String> moveHMSET(String bulkFilePath){
		ArrayList<String> tempFileDataList = new ArrayList<String>();
		Reader reader = new Reader(bulkFilePath);
		reader.running();
		tempFileDataList = reader.getFileDataList();
		
		ArrayList<String> hmsetDataList = new ArrayList<String>();
		ArrayList<String> setDataList = new ArrayList<String>();
		ArrayList<String> tokens = new ArrayList<String>();
		ArrayList<String> removeLine = new ArrayList<String>();
		
		for(int i=0;i<tempFileDataList.size();i++){
			String line = tempFileDataList.get(i);
			tokens = myUtil.splitData(line, " ");
			
			if(tokens.size() != 0){
				if(tokens.get(0).equals("$HMSET")){
					hmsetDataList.add(line);
					removeLine.add(line);
				}
				if(tokens.get(0).equals("SET")){
					int endLineNum = i+1;
					for(int j=i ;j < endLineNum ;j++){
						String innerLine = tempFileDataList.get(j);
						ArrayList<String> innerTokens = myUtil.splitData(innerLine, " ");					
						String lastToken= innerTokens.get(innerTokens.size()-1);
						char lastChar = lastToken.charAt(lastToken.length()-1);
						if(lastChar == ','){
							endLineNum++;
							setDataList.add(innerLine);
							removeLine.add(innerLine);
						}else{
							setDataList.add(innerLine);
							removeLine.add(innerLine);
						}
					}

				}
			}
		}
		
		for(String remove : removeLine){
			tempFileDataList.remove(remove);
		}
		 
		ArrayList<String> newDataList = new ArrayList<String>();
		for(String line : tempFileDataList){
			if(line.trim().toLowerCase().equals("begin bulk")){
				//System.out.println("JSJSJSJSJSJSJ "+line);
				for(String newLine : removeLine){
					newDataList.add(newLine);
				}
				newDataList.add(line);
			}else{
				newDataList.add(line);
			}
		}
		// */
		//System.out.println("\n\n");
				
		return newDataList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test_HMSET obj = new Test_HMSET();
		obj.running();
		
		
		/* 
		String str = "SET 1 = 1,2,3,4,5,6,7;";
		ArrayList<String> tokens = myUtil.splitData(str, " ");
		String lastToken = tokens.get(tokens.size()-1);
		System.out.println(lastToken);
		
		char lastChar = lastToken.charAt(lastToken.length()-1);
		
		
		if(lastChar == ','){
			System.out.println(lastChar);
		}else{
			System.out.println("NONO");
		}
		// */
	}

}
