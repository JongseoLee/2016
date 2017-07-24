package com.msc.adams.automation.inputdeck;

import java.util.ArrayList;

import com.msc.adams.automation.core.MainController;
import com.msc.util.myUtil;

public class EIGRL_Data {
	private MainController MC = MainController.getInstance();
	
	private String CardName = "EIGRL";
	private String SID = " ";
	private String V1 = " ";
	private String V2 = " ";
	private String ND = " ";
	private String MSGLVL = " ";
	private String MAXSET = " ";
	private String SHFSCL = " ";
	private String NORM = " ";
	
	
	public EIGRL_Data() {
		// TODO Auto-generated constructor stub
		//this.ND = MC.getEIGRL_ND();
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
		this.SID = tokenList.get(1);
		this.V1 = tokenList.get(2);
		this.V2 = tokenList.get(3);
		this.ND = MC.getEIGRL_ND();
		/*
		this.MSGLVL = tokenList.get(5);
		this.MAXSET = tokenList.get(6);
		this.SHFSCL = tokenList.get(7);
		this.NORM = tokenList.get(8);
		// */
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
				this.SID = tokenList.get(1);
			}else if(tokenSize == 3){
				this.CardName = tokenList.get(0);
				this.SID = tokenList.get(1);
				this.V1 = tokenList.get(2);
			}else if(tokenSize == 4){
				this.CardName = tokenList.get(0);
				this.SID = tokenList.get(1);
				this.V1 = tokenList.get(2);
				this.V2 = tokenList.get(3);
			}else if(tokenSize == 5){
				this.CardName = tokenList.get(0);
				this.SID = tokenList.get(1);
				this.V1 = tokenList.get(2);
				this.V2 = tokenList.get(3);
				this.ND = MC.getEIGRL_ND();
			}else if(tokenSize == 6){
				this.CardName = tokenList.get(0);
				this.SID = tokenList.get(1);
				this.V1 = tokenList.get(2);
				this.V2 = tokenList.get(3);
				this.ND = MC.getEIGRL_ND();
				this.MSGLVL = tokenList.get(5);
			}else if(tokenSize == 7){
				this.CardName = tokenList.get(0);
				this.SID = tokenList.get(1);
				this.V1 = tokenList.get(2);
				this.V2 = tokenList.get(3);
				this.ND = MC.getEIGRL_ND();
				this.MSGLVL = tokenList.get(5);
				this.MAXSET = tokenList.get(6);
			}else if(tokenSize == 8){
				this.CardName = tokenList.get(0);
				this.SID = tokenList.get(1);
				this.V1 = tokenList.get(2);
				this.V2 = tokenList.get(3);
				this.ND = MC.getEIGRL_ND();
				this.MSGLVL = tokenList.get(5);
				this.MAXSET = tokenList.get(6);
				this.SHFSCL = tokenList.get(7);
			}else if(tokenSize == 9){
				this.CardName = tokenList.get(0);
				this.SID = tokenList.get(1);
				this.V1 = tokenList.get(2);
				this.V2 = tokenList.get(3);
				this.ND = MC.getEIGRL_ND();
				this.MSGLVL = tokenList.get(5);
				this.MAXSET = tokenList.get(6);
				this.SHFSCL = tokenList.get(7);
				this.NORM = tokenList.get(8);	
			}
			//System.out.println("EIGRL_Data (ND): " +this.ND);
		}catch(Exception e){
			System.out.println("##ERROR##-EIGRL_Data.java");
			System.out.println(line);
		}
		
	}
	public boolean isSame(EIGRL_Data obj){
		boolean result = false;
		if(this.CardName.equals(obj.getCardName()))
			if(this.SID.equals(obj.getSID()))
				if(this.V1.equals(obj.getV1()))
					if(this.V2.equals(obj.getV2()))
						if(this.ND.equals(obj.getND()))
							if(this.MSGLVL.equals(obj.getMSGLVL()))
								if(this.MAXSET.equals(obj.getMAXSET()))
									if(this.SHFSCL.equals(obj.getSHFSCL()))
										if(this.NORM.equals(obj.getNORM()))
											result = true;
		return result;
	}
	
	public String getFullCardData(){
		this.ND = MC.getEIGRL_ND();
		//System.out.println("ND(FullCard) : " + this.ND);
		String cName = String.format("%-8s", CardName);
		String sid = String.format("%-8s", SID);
		String v1 = String.format("%-8s", V1);
		String v2 = String.format("%-8s", V2);
		String nd = String.format("%-8s", ND);
		String msglvl = String.format("%-8s", MSGLVL);
		String maxset = String.format("%-8s", MAXSET);
		String shfscl = String.format("%-8s", SHFSCL);
		String norm = String.format("%-8s", NORM);
		return cName+sid+v1+v2+nd+msglvl+maxset+shfscl+norm;
	}

	public String getCardName() {
		return CardName;
	}

	public void setCardName(String cardName) {
		CardName = cardName;
	}

	public String getSID() {
		return SID;
	}

	public void setSID(String sID) {
		SID = sID;
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

	public String getND() {
		return ND;
	}

	public void setND(String nD) {
		ND = nD;
	}

	public String getMSGLVL() {
		return MSGLVL;
	}

	public void setMSGLVL(String mSGLVL) {
		MSGLVL = mSGLVL;
	}

	public String getMAXSET() {
		return MAXSET;
	}

	public void setMAXSET(String mAXSET) {
		MAXSET = mAXSET;
	}

	public String getSHFSCL() {
		return SHFSCL;
	}

	public void setSHFSCL(String sHFSCL) {
		SHFSCL = sHFSCL;
	}

	public String getNORM() {
		return NORM;
	}

	public void setNORM(String nORM) {
		NORM = nORM;
	}
	
}
