package com.js.ent.lotto;

import java.util.ArrayList;

public class MyRound {
	
	private ArrayList<Integer> numList = new ArrayList<Integer>();
	private String strResult = "";
	
	

	public MyRound() {
		// TODO Auto-generated constructor stub
	}
	
	public void setAllData(ArrayList<Integer> numList){
		this.numList = numList;
		for(int n : numList){
			strResult += n+",";
		}
	}
	
	public ArrayList<Integer> getNumList() {
		return numList;
	}
	
	public String getStrResult() {
		return strResult;
	}
	
}
