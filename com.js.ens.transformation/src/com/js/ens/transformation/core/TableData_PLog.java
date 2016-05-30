package com.js.ens.transformation.core;

public class TableData_PLog {
	private String STAND = "";
	private String BUR_TDIA = "";
	private String BUR_BDIA = "";
	private String WR_TDIA = "";
	private String WR_BDIA = "";
	private String WR_ICRN = "";
	private String ENTRY_THK = "";
	private String EXIT_THK = "";
	private String PAS_LINE = "";
	private String ROL_GAP = "";
	private String STP_WID = "";
	private String STP_LEN = "";
	private String ENTRY_TEMP = "";
	private String EXIT_TEMP = "";
	private String FRCE = "";
	private String TORQ = "";
	private String SPEED = "";
	private String BEND = "";
	private String P_CROSS = "";
	private String TENS = "";
	private String ROL_TIM = "";
	private String IDL_TIM = "";
	private String BUR_WEAR = "";
	private String WR_WEAR = "";
	private String WR_THRM = "";
	
	public TableData_PLog() {
		// TODO Auto-generated constructor stub
	}

	public String getSTAND() {
		return STAND;
	}

	public void setSTAND(String sTAND) {
		STAND = sTAND;
	}

	public String getBUR_TDIA() {
		return BUR_TDIA;
	}

	public void setBUR_TDIA(String bUR_TDIA) {
		BUR_TDIA = bUR_TDIA;
	}

	public String getBUR_BDIA() {
		return BUR_BDIA;
	}

	public void setBUR_BDIA(String bUR_BDIA) {
		BUR_BDIA = bUR_BDIA;
	}

	public String getWR_TDIA() {
		return WR_TDIA;
	}

	public void setWR_TDIA(String wR_TDIA) {
		WR_TDIA = wR_TDIA;
	}

	public String getWR_BDIA() {
		return WR_BDIA;
	}

	public void setWR_BDIA(String wR_BDIA) {
		WR_BDIA = wR_BDIA;
	}

	public String getWR_ICRN() {
		return WR_ICRN;
	}

	public void setWR_ICRN(String wR_ICRN) {
		WR_ICRN = wR_ICRN;
	}

	public String getENTRY_THK() {
		return ENTRY_THK;
	}

	public void setENTRY_THK(String eNTRY_THK) {
		ENTRY_THK = eNTRY_THK;
	}

	public String getEXIT_THK() {
		return EXIT_THK;
	}

	public void setEXIT_THK(String eXIT_THK) {
		EXIT_THK = eXIT_THK;
	}

	public String getPAS_LINE() {
		return PAS_LINE;
	}

	public void setPAS_LINE(String pAS_LINE) {
		PAS_LINE = pAS_LINE;
	}

	public String getROL_GAP() {
		return ROL_GAP;
	}

	public void setROL_GAP(String rOL_GAP) {
		ROL_GAP = rOL_GAP;
	}

	public String getSTP_WID() {
		return STP_WID;
	}

	public void setSTP_WID(String sTP_WID) {
		STP_WID = sTP_WID;
	}

	public String getSTP_LEN() {
		return STP_LEN;
	}

	public void setSTP_LEN(String sTP_LEN) {
		STP_LEN = sTP_LEN;
	}

	public String getENTRY_TEMP() {
		return ENTRY_TEMP;
	}

	public void setENTRY_TEMP(String eNTRY_TEMP) {
		ENTRY_TEMP = eNTRY_TEMP;
	}

	public String getEXIT_TEMP() {
		return EXIT_TEMP;
	}

	public void setEXIT_TEMP(String eXIT_TEMP) {
		EXIT_TEMP = eXIT_TEMP;
	}

	public String getFRCE() {
		return FRCE;
	}

	public void setFRCE(String fRCE) {
		FRCE = fRCE;
	}

	public String getTORQ() {
		return TORQ;
	}

	public void setTORQ(String tORQ) {
		TORQ = tORQ;
	}

	public String getSPEED() {
		return SPEED;
	}

	public void setSPEED(String sPEED) {
		SPEED = sPEED;
	}

	public String getBEND() {
		return BEND;
	}

	public void setBEND(String bEND) {
		BEND = bEND;
	}

	public String getP_CROSS() {
		return P_CROSS;
	}

	public void setP_CROSS(String p_CROSS) {
		P_CROSS = p_CROSS;
	}

	public String getTENS() {
		return TENS;
	}

	public void setTENS(String tENS) {
		TENS = tENS;
	}

	public String getROL_TIM() {
		return ROL_TIM;
	}

	public void setROL_TIM(String rOL_TIM) {
		ROL_TIM = rOL_TIM;
	}

	public String getIDL_TIM() {
		return IDL_TIM;
	}

	public void setIDL_TIM(String iDL_TIM) {
		IDL_TIM = iDL_TIM;
	}

	public String getBUR_WEAR() {
		return BUR_WEAR;
	}

	public void setBUR_WEAR(String bUR_WEAR) {
		BUR_WEAR = bUR_WEAR;
	}

	public String getWR_WEAR() {
		return WR_WEAR;
	}

	public void setWR_WEAR(String wR_WEAR) {
		WR_WEAR = wR_WEAR;
	}

	public String getWR_THRM() {
		return WR_THRM;
	}

	public void setWR_THRM(String wR_THRM) {
		WR_THRM = wR_THRM;
	}
	
	public String getDB(){
		return "";
	}
	
	public void printAllData(){
		System.out.println("<<Table - P Log>>");
		System.out.println("STAND = "+this.STAND);
		System.out.println("BUR_TDIA = "+this.BUR_TDIA);
		System.out.println("BUR_BDIA = "+this.BUR_BDIA);
		System.out.println("WR_TDIA = "+this.WR_TDIA);
		System.out.println("WR_BDIA = "+this.WR_BDIA);
		System.out.println("WR_ICRN = "+this.WR_ICRN);
		System.out.println("ENTRY_THK = "+this.ENTRY_THK);
		System.out.println("EXIT_THK = "+this.EXIT_THK);
		System.out.println("PAS_LINE = "+this.PAS_LINE);
		System.out.println("ROL_GAP = "+this.ROL_GAP);
		System.out.println("STP_WID = "+this.STP_WID);
		System.out.println("STP_LEN = "+this.STP_LEN);
		System.out.println("ENTRY_TEMP = "+this.ENTRY_TEMP);
		System.out.println("EXIT_TEMP = "+this.EXIT_TEMP);
		System.out.println("FRCE = "+this.FRCE);
		System.out.println("TORQ = "+this.TORQ);
		System.out.println("SPEED = "+this.SPEED);
		System.out.println("BEND = "+this.BEND);
		System.out.println("P_CROSS = "+this.P_CROSS);
		System.out.println("TENS = "+this.TENS);
		System.out.println("ROL_TIM = "+this.ROL_TIM);
		System.out.println("IDL_TIM = "+this.IDL_TIM);
		System.out.println("BUR_WEAR = "+this.BUR_WEAR);
		System.out.println("WR_WEAR = "+this.WR_WEAR);
		System.out.println("WR_THRM = "+this.WR_THRM);
	}
}
