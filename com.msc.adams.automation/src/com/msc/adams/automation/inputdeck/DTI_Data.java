package com.msc.adams.automation.inputdeck;

import java.util.ArrayList;

import com.msc.util.myUtil;

public class DTI_Data {
	private String CardName = "DTI";
	private String Name = " ";
	private String Var = " ";
	private String T1 = " ";
	private String T2 = " ";
	private String T3 = " ";
	private String T4 = " ";
	private String T5 = " ";
	private String T6 = " ";
	//private String T7 = "";
	//private String T8 = "";
	//private String T9 = "";
	
	
	public DTI_Data() {
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
				this.Name = tokenList.get(1);	
			}else if(tokenSize == 3){
				this.CardName = tokenList.get(0);
				this.Name = tokenList.get(1);	
				this.Var = tokenList.get(2);
			}else if(tokenSize == 4){
				this.CardName = tokenList.get(0);
				this.Name = tokenList.get(1);	
				this.Var = tokenList.get(2);
				this.T1 = tokenList.get(3);
			}else if(tokenSize == 5){
				this.CardName = tokenList.get(0);
				this.Name = tokenList.get(1);	
				this.Var = tokenList.get(2);
				this.T1 = tokenList.get(3);
				this.T2 = tokenList.get(4);
			}else if(tokenSize == 6){
				this.CardName = tokenList.get(0);
				this.Name = tokenList.get(1);	
				this.Var = tokenList.get(2);
				this.T1 = tokenList.get(3);
				this.T2 = tokenList.get(4);
				this.T3 = tokenList.get(5);	
			}else if(tokenSize == 7){
				this.CardName = tokenList.get(0);
				this.Name = tokenList.get(1);	
				this.Var = tokenList.get(2);
				this.T1 = tokenList.get(3);
				this.T2 = tokenList.get(4);
				this.T3 = tokenList.get(5);
				this.T4 = tokenList.get(6);
			}else if(tokenSize == 8){
				this.CardName = tokenList.get(0);
				this.Name = tokenList.get(1);	
				this.Var = tokenList.get(2);
				this.T1 = tokenList.get(3);
				this.T2 = tokenList.get(4);
				this.T3 = tokenList.get(5);
				this.T4 = tokenList.get(6);
				this.T5 = tokenList.get(7);
			}else if(tokenSize == 9){
				this.CardName = tokenList.get(0);
				this.Name = tokenList.get(1);	
				this.Var = tokenList.get(2);
				this.T1 = tokenList.get(3);
				this.T2 = tokenList.get(4);
				this.T3 = tokenList.get(5);
				this.T4 = tokenList.get(6);
				this.T5 = tokenList.get(7);
				this.T6 = tokenList.get(8);
			}
		}catch(Exception e){
			System.out.println("##ERROR##-DTI_Data.java");
			System.out.println(line);
		}
		
	}
	
	public boolean isSame(DTI_Data obj){
		boolean result = false;
		if(this.CardName.equals(obj.getCardName()))
			if(this.Name.equals(obj.getName()))
				if(this.Var.equals(obj.getVar()))
					if(this.T1.equals(obj.getT1()))
						if(this.T2.equals(obj.getT2()))
							if(this.T3.equals(obj.getT3()))
								if(this.T4.equals(obj.getT4()))
									if(this.T5.equals(obj.getT5()))
										if(this.T6.equals(obj.getT6()))
											result = true;
		return result;
	}
	
	public String getFullCardData(){
		String cName = String.format("%-8s", CardName);
		String name = String.format("%-8s", Name);
		String var = String.format("%-8s", Var);
		String t1 = String.format("%-8s", T1);
		String t2 = String.format("%-8s", T2);
		String t3 = String.format("%-8s", T3);
		String t4 = String.format("%-8s", T4);
		String t5 = String.format("%-8s", T5);
		String t6 = String.format("%-8s", T6);
		return cName+name+var+t1+t2+t3+t4+t5+t6;
	}

	public String getCardName() {
		return CardName;
	}

	public void setCardName(String cardName) {
		CardName = cardName;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getVar() {
		return Var;
	}

	public void setVar(String var) {
		Var = var;
	}

	public String getT1() {
		return T1;
	}

	public void setT1(String t1) {
		T1 = t1;
	}

	public String getT2() {
		return T2;
	}

	public void setT2(String t2) {
		T2 = t2;
	}

	public String getT3() {
		return T3;
	}

	public void setT3(String t3) {
		T3 = t3;
	}

	public String getT4() {
		return T4;
	}

	public void setT4(String t4) {
		T4 = t4;
	}

	public String getT5() {
		return T5;
	}

	public void setT5(String t5) {
		T5 = t5;
	}

	public String getT6() {
		return T6;
	}

	public void setT6(String t6) {
		T6 = t6;
	}

}
