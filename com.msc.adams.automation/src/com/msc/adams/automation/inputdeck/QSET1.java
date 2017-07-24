package com.msc.adams.automation.inputdeck;

public class QSET1 {
	
	private String CardName = "QSET1";
	private int STARTNUM = 999991;
	private String THRU = "THRU";
	private int LASTNUM = 0;
	
	public QSET1() {
		// TODO Auto-generated constructor stub
	}

	public void running(int startNum, int ASET1Num){
		this.setStartNum(startNum);
		this.setLastNum(ASET1Num);
	}
	
	public String getFullCardData(){
		String cName = String.format("%-8s", CardName);
		String blank = String.format("%-8s", " ");
		String startNum = String.format("%-8s", String.valueOf(STARTNUM));
		String thru = String.format("%-8s", THRU);
		String lastNum = String.format("%-8s", String.valueOf(LASTNUM));
		return cName+blank+startNum+thru+lastNum;
	}
	
	private void setLastNum(int ASET1Num){
		this.LASTNUM = STARTNUM + ASET1Num;
	}
	

	private void setStartNum(int startNum) {
		this.STARTNUM = startNum;
	}
}
