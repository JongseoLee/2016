package com.js.ens.leveller.core;

import java.util.ArrayList;

public class exportInfo {
	
	private ArrayList<String> InfoList;
	
	public exportInfo(){
		this.InfoList = new ArrayList<String>();
	}
	
	public void clearList(){
		this.InfoList.clear();
	}
	
	public void addData(String msg){
		this.InfoList.add(msg);
	}
	
	public ArrayList<String> getInfoList() {
		return InfoList;
	}

	public void setInfoList(ArrayList<String> infoList) {
		InfoList = infoList;
	}
	
	
	
}
