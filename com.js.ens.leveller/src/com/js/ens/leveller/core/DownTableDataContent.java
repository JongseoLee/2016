package com.js.ens.leveller.core;

public class DownTableDataContent {
	
	private String Pitch = "";
	private String No = "";
	private String Gap = "";
	private String Friction = "";
	private String Diameter ="";
	
	public DownTableDataContent(){
		
	}

	public String getPitch() {
		return Pitch;
	}

	public void setPitch(String pitch) {
		Pitch = pitch;
	}

	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}
	
	public String getGap() {
		return Gap;
	}

	public void setGap(String gap) {
		Gap = gap;
	}

	public String getFriction() {
		return Friction;
	}

	public void setFriction(String friction) {
		Friction = friction;
	}

	public String getDiameter() {
		return Diameter;
	}

	public void setDiameter(String diameter) {
		Diameter = diameter;
	}
	
	public void printAllData(){
		System.out.println(String.format("%20s", this.No)+
				String.format("%20s", this.Gap)+
				String.format("%20s", this.Friction)+
				String.format("%20s", this.Diameter)+
				String.format("%20s", this.Pitch)
				);
	}
	
	public String getAllData(){
		String data = "";
		data = String.format("%-20s", this.No)+
				String.format("%-20s", this.Gap)+
				String.format("%-20s", this.Friction)+
				String.format("%-20s", this.Diameter)+
				String.format("%-20s", this.Pitch);
		return data;
	}
	
	public String getDB(){
		return this.No+"*"+this.Gap+"*"+this.Friction+"*"+this.Diameter+"*"+this.Pitch;
	}
}
