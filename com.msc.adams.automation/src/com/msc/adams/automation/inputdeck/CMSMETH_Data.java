package com.msc.adams.automation.inputdeck;

import java.util.ArrayList;

import com.msc.adams.automation.core.MainController;
import com.msc.util.myUtil;

public class CMSMETH_Data {
	private MainController MC = MainController.getInstance();
	
	
	private String CardName = "CMSMETH";	//CMSMETH
	private String CMSID = " ";		// 99
	private String METHOD = " ";	// CB
	private String UB_FREQ = " ";	// blank
	private String NMODES = " ";	// 20 EIGRL ND
	private String SPID = " ";
	
	
	public CMSMETH_Data() {
		// TODO Auto-generated constructor stub
		//this.ND = MC.getEIGRL_ND();
	}
	
	public void running(String line){
		
		Parsing_ShortFormat(line);
		
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
				this.CMSID = tokenList.get(1);
			}else if(tokenSize == 3){
				this.CardName = tokenList.get(0);
				this.CMSID = tokenList.get(1);
				this.METHOD = tokenList.get(2);
			}else if(tokenSize == 4){
				this.CardName = tokenList.get(0);
				this.CMSID = tokenList.get(1);
				this.METHOD = tokenList.get(2);
				this.UB_FREQ = tokenList.get(3);
			}else if(tokenSize == 5){
				this.CardName = tokenList.get(0);
				this.CMSID = tokenList.get(1);
				this.METHOD = tokenList.get(2);
				this.UB_FREQ = tokenList.get(3);
				this.NMODES = MC.getEIGRL_ND();
			}else if(tokenSize == 6){
				this.CardName = tokenList.get(0);
				this.CMSID = tokenList.get(1);
				this.METHOD = tokenList.get(2);
				this.UB_FREQ = tokenList.get(3);
				this.NMODES = MC.getEIGRL_ND();
				this.SPID = tokenList.get(5);
			}
			//this.NMODES = MC.getEIGRL_ND();
		}catch(Exception e){
			System.out.println("##ERROR##-CMSMETH_Data.java");
			System.out.println(line);
		}
		
	}
	public boolean isSame(CMSMETH_Data obj){
		boolean result = false;
		if(this.CardName.equals(obj.getCardName()))
			if(this.CMSID.equals(obj.getCMSID()))
				if(this.METHOD.equals(obj.getMETHOD()))
					if(this.UB_FREQ.equals(obj.getUB_FREQ()))
						if(this.NMODES.equals(obj.getNMODES()))
							if(this.SPID.equals(obj.getSPID()))
								result = true;
		return result;
	}
	
	public String getFullCardData(){
		this.NMODES = MC.getEIGRL_ND();
		//System.out.println("ND (Fullcard): " + this.NMODES);
		String cName = String.format("%-8s", CardName);
		String cmsid = String.format("%-8s",CMSID);
		String method = String.format("%-8s", METHOD);
		String ub_freq = String.format("%-8s", UB_FREQ);
		String nmodes = String.format("%-8s", NMODES);
		String spid = String.format("%-8s", SPID);
		//System.out.println("cName >"+cName);
		//System.out.println("cmsid >"+cmsid);
		//System.out.println("method >"+method);
		//System.out.println("ub_freq >"+ub_freq);
		//System.out.println("*nmodes >"+nmodes);
		//System.out.println("spid >"+spid);
		return cName+cmsid+method+ub_freq+nmodes+spid;
	}

	public String getCardName() {
		return CardName;
	}

	public void setCardName(String cardName) {
		CardName = cardName;
	}
	
	public String getCMSID() {
		return CMSID;
	}

	public void setCMSID(String cMSID) {
		CMSID = cMSID;
	}

	public String getMETHOD() {
		return METHOD;
	}

	public void setMETHOD(String mETHOD) {
		METHOD = mETHOD;
	}

	public String getUB_FREQ() {
		return UB_FREQ;
	}

	public void setUB_FREQ(String uB_FREQ) {
		UB_FREQ = uB_FREQ;
	}

	public String getNMODES() {
		return NMODES;
	}

	public void setNMODES(String nMODES) {
		NMODES = nMODES;
	}

	public String getSPID() {
		return SPID;
	}

	public void setSPID(String sPID) {
		SPID = sPID;
	}
	
}
