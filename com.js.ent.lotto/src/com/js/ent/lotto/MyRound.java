package com.js.ent.lotto;

import java.util.ArrayList;

public class MyRound {
	private ArrayList<Integer> numList = new ArrayList<Integer>();
	
	public MyRound() {
		// TODO Auto-generated constructor stub
	}
	
	public void setAllData(ArrayList<Integer> numList){
		this.numList = numList;
	}

	public ArrayList<Integer> getNumList() {
		return numList;
	}
	

}
