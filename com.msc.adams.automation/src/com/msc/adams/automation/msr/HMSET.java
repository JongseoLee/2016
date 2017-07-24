package com.msc.adams.automation.msr;

import java.util.ArrayList;

import com.msc.util.myUtil;

public class HMSET {
	
	private ArrayList<String> tokenList;
	private String index;
	private String groupName;
	private String materialName;
	
	
	public HMSET() {
		// TODO Auto-generated constructor stub
		this.tokenList = new ArrayList<String>();
	}
	
	public void running(ArrayList<String> tokenList, String index){
		this.tokenList = tokenList;
		this.index = index;
		ArrayList<String> token = new ArrayList<String>();
		String name = this.tokenList.get(3);
		token = myUtil.splitData(name.substring(1, name.length()-1),"_"); 	// " " Á¦°Å
		this.groupName = name.substring(1, name.length()-1);
		this.materialName = token.get(token.size()-1);
		if(this.groupName.equals(this.materialName)){
			this.groupName = "ALL";
		}
	}

	public ArrayList<String> getTokenList() {
		return tokenList;
	}

	public void setTokenList(ArrayList<String> tokenList) {
		this.tokenList = tokenList;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

}
