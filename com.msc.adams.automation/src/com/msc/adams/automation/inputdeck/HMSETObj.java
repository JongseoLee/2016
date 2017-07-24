package com.msc.adams.automation.inputdeck;

import java.util.ArrayList;

public class HMSETObj {

	private String ID;
	private ArrayList<String> LinesList;
	
	public HMSETObj(String id) {
		// TODO Auto-generated constructor stub
		this.ID = id;
		this.LinesList = new ArrayList<String>();
	}
	
	public String getID(){
		return this.ID;
	}
	
	public void addLine(String line){
		this.LinesList.add(line);
	}
	
	public ArrayList<String> getLinesList(){
		return this.LinesList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
