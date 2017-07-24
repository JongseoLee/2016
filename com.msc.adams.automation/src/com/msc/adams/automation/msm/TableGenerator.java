package com.msc.adams.automation.msm;

import java.util.ArrayList;

import com.msc.io.Writer;
import com.msc.util.myUtil;

public class TableGenerator {

	private ArrayList<InputColumnData> inputColumnDataList;
	private String outputFilePath;
	private String outputFilePath_Short;
	private String fName_long;
	private String tableID;
	
	private int allDataSize = 0;
	private ArrayList<String> resultDataList_long;
	
	public TableGenerator(ArrayList<InputColumnData> inputColumnDataList, String outputFilePath,String outputFilePath_short,String tableID ) {
		// TODO Auto-generated constructor stub
		this.inputColumnDataList = inputColumnDataList;
		this.outputFilePath = outputFilePath;
		this.outputFilePath_Short = outputFilePath_short;
		resultDataList_long = new ArrayList<String>();
		this.allDataSize = this.inputColumnDataList.get(0).getDataSize();
		this.tableID = tableID;
		/*
		System.out.println(outputFilePath);
		System.out.println(this.allDataSize);
		System.out.println(inputColumnDataList.get(0).getData(0));
		System.out.println(inputColumnDataList.get(1).getData(0));
		//*/
	}
	
	public void running(){
		makeLine_long();
		writeFile();
	}
	
	private String changeFormat(String data){
		return String.format("%.8e%n", Float.valueOf(data)).trim();
	}
	
	private void makeLine_long(){
		int totalCount = allDataSize;
		
		for(int i=1;i<this.inputColumnDataList.size();i++){
			String title = String.format("%-8s", "TABLED1*")+String.format("%-16s", tableID);
			resultDataList_long.add(title);
			resultDataList_long.add("*");
				
			if(totalCount % 2 == 0){
				// Â¦¼ö
				for(int j=0;j<totalCount;j=j+2){
					String line =  String.format("%-8s", "*") 
							+ String.format("%-16s", changeFormat(inputColumnDataList.get(0).getData(j)))
							+ String.format("%-16s", changeFormat(inputColumnDataList.get(i).getData(j)))
							+ String.format("%-16s", changeFormat(inputColumnDataList.get(0).getData(j+1)))
							+ String.format("%-16s", changeFormat(inputColumnDataList.get(i).getData(j+1)))
							+ String.format("%-8s", "");
					resultDataList_long.add(line);
					
					if(j == totalCount-2){
						String endLine = String.format("%-8s", "*") 
								+ String.format("%-16s", "ENDT");
						resultDataList_long.add(endLine);
						break;
					}
				}
			}else{
					// È¦¼ö 
				for(int j=0;j<totalCount;j=j+2){
					
					if(j == totalCount-1){ 
						String endLine = String.format("%-8s", "*")
								+ String.format("%-16s", changeFormat(inputColumnDataList.get(0).getData(j)))
								+ String.format("%-16s", changeFormat(inputColumnDataList.get(i).getData(j)))
								+ String.format("%-16s", "ENDT");
						resultDataList_long.add(endLine);
						 break;
					}
						
					String line =  String.format("%-8s", "*") 
							+ String.format("%-16s", changeFormat(inputColumnDataList.get(0).getData(j)))
							+ String.format("%-16s", changeFormat(inputColumnDataList.get(i).getData(j)))
							+ String.format("%-16s", changeFormat(inputColumnDataList.get(0).getData(j+1)))
							+ String.format("%-16s", changeFormat(inputColumnDataList.get(i).getData(j+1)))
							+ String.format("%-8s", "");
					resultDataList_long.add(line);
				}
			}
		}
	}
	
	
	public void writeFile(){
		/*
		String longPath = Util.setPath(System.getProperty("user.dir"), this.fName_long);
		//String shortPath = Util.setPath(System.getProperty("user.dir"),this.fName_short);
		
		//System.out.println(longPath);
		Writer writer = new Writer(this.resultDataList_long, longPath);
		writer.writeFile();
		
		//System.out.println(shortPath);
		//Writer writer2 = new Writer(this.resultDataList_short, shortPath);
		//writer2.writeFile(); 
		
		//*/
		
		Writer writer = new Writer(this.outputFilePath);
		writer.running(this.resultDataList_long);
		
		Writer wirter2 = new Writer(this.outputFilePath_Short);
		wirter2.running(this.resultDataList_long);
		
	}
	
	
	public String round(String s){
		String result = null;
		double temp = Double.parseDouble(s);
		result = String.format("%.1e", temp);
		return result;
	}
	
	
}
