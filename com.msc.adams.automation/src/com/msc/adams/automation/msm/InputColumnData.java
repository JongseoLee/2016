package com.msc.adams.automation.msm;

import java.util.ArrayList;

public class InputColumnData {

	private ArrayList<String> DataList; 
	
	public InputColumnData(){
		DataList = new ArrayList<String>();
	}
	
	public void addData(String data){
		DataList.add(data);
	}
	
	public String getData(int index){
		return DataList.get(index);
	}
	
	public ArrayList<String> getxDataList() {
		return DataList;
	}

	public void setxDataList(ArrayList<String> xDataList) {
		this.DataList = xDataList;
	}
	
	public int getDataSize(){
		return this.DataList.size();
	}

}
