package com.msc.adams.automation.inputdeck;

import java.util.ArrayList;

import com.msc.util.myUtil;

public class Param_Data {

	private String CardName = "PARAM";
	private String N = " ";
	private String V1 = " ";
	private String V2 = " ";
	
	public Param_Data() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(String line){
		if(line.contains("*")){
			Parsing_LongFormat(line);
		}else{
			Parsing_ShortFormat(line);
		}
	}
	
	private void Parsing_LongFormat(String line){
		ArrayList<String> tokenList = new ArrayList<String>();
		tokenList = myUtil.splitData_longFormat(line);
		this.CardName = tokenList.get(0);
		this.N = tokenList.get(1);
		this.V1 = tokenList.get(2);
		this.V2 = tokenList.get(3);
	}
	
	private void Parsing_ShortFormat(String line){
		try{
			ArrayList<String> tokenList = new ArrayList<String>();
			tokenList = myUtil.splitData_shortFormat(line);
			
			int tokenSize = tokenList.size();
			if(tokenSize == 1){
				this.CardName = tokenList.get(0);
			}else if(tokenSize == 2){
				this.CardName = tokenList.get(0);
				this.N = tokenList.get(1);	
			}else if(tokenSize == 3){
				this.CardName = tokenList.get(0);
				this.N = tokenList.get(1);	
				this.V1 = tokenList.get(2);
			}else if(tokenSize == 4){
				this.CardName = tokenList.get(0);
				this.N = tokenList.get(1);	
				this.V1 = tokenList.get(2);
				this.V2 = tokenList.get(3);
			}
		}catch(Exception e){
			System.out.println("##ERROR##-Param_Data.java");
			System.out.println(line);
			e.printStackTrace();
		}
		
	}
	
	public boolean isSame(Param_Data obj){
		boolean result = false;
		if(this.CardName.equals(obj.getCardName()))
			if(this.N.equals(obj.getN()))
				if(this.V1.equals(obj.getV1()))
					if(this.V2.equals(obj.getV2()))
						result = true;
					
		return result;
	}
	
	
	public String getFullCardData(){
		String cName = String.format("%-8s", CardName);
		String n = String.format("%-8s", N);
		String v1 = String.format("%-8s", V1);
		String v2 = String.format("%-8s", V2);
		//System.out.println("==============>"+cName+n+v1+v2);
		return cName+n+v1+v2;
	}
	
	public String getCardName() {
		return CardName;
	}

	public void setCardName(String cardName) {
		CardName = cardName;
	}

	public String getN() {
		return N;
	}

	public void setN(String n) {
		N = n;
	}

	public String getV1() {
		return V1;
	}

	public void setV1(String v1) {
		V1 = v1;
	}

	public String getV2() {
		return V2;
	}

	public void setV2(String v2) {
		V2 = v2;
	}

	public static void main(String [] args){
		Param_Data obj = new Param_Data();
		//System.out.println("$2345678$2345678$2345678$2345678$2345678$2345678$2345678$2345678$2345678$2345678");
		obj.getFullCardData();
	}

}
