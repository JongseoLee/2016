package com.msc.adams.automation.inputdeck;

import java.util.ArrayList;

import com.msc.util.myUtil;

public class ASET1 {

	private String CardName = "ASET1";
	private String C = "123456";
	private String ID1 = "";
	private String ID2 = "";
	private String ID3 = "";
	private String ID4 = "";
	private String ID5 = "";
	private String ID6 = "";
	private String ID7 = "";
	
	private String ID8 = "";
	private String ID9 = "";
	private String ID10 = "";
	
	private ArrayList<String> IDList = new ArrayList<String>();
	
	public ASET1() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(String line){
		if(line.contains("$")){
			//System.out.println("skip : "+line);
		}else{
			if(line.contains("RBE2")){
				if(line.contains("*")){
					//System.out.println("Long format : " + line);
					Parsing_LongFormat(line);
				}else{
					//System.out.println("Short format : " + line);
					Parsing_ShortFormat(line);
				}
			}	
		}
	}
	
	private void Parsing_LongFormat(String line){
		ArrayList<String> tokenList = new ArrayList<String>();
		tokenList = myUtil.splitData_longFormat(line);
		//System.out.println("--------------");
		//myUtil.printArrData(tokenList);
		//System.out.println("--------------");
		this.AddIDList(tokenList.get(2));
	}
	
	private void Parsing_ShortFormat(String line){
		//System.out.println("line : "+line);
		ArrayList<String> tokenList = new ArrayList<String>();
		tokenList = myUtil.splitData_shortFormat(line);
		this.AddIDList(tokenList.get(2));
	}
	
	private void AddIDList(String ID){
		this.IDList.add(ID);
	}
	
	public String getFullCardData(){
		String cName = String.format("%-8s", CardName);
		String c = String.format("%-8s", C);
		String idList = "";
		
		for(int i = 0;i<IDList.size();i++){
			if(IDList.size() == 8){
				idList +="\n"+String.format("%-8s", " ");
			}
			idList += String.format("%-8s", this.IDList.get(i));
		}
		//System.out.println("ID List : "+idList);
		return cName+c+idList;
	}

	public int getIDListSize() {
		return IDList.size();
	}
	
	
}
