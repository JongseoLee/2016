package com.js.ens.license;

import java.util.ArrayList;

import com.js.io.Reader;
import com.js.util.ParserDefault;
import com.js.util.myUtil;

public class LicenseModule {
	 
	private String userLicensePeriod = "";
	private String startDate = "";
	private String endDate = "";
	private String userLicense = "";
	
	public LicenseModule() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(){
		this.readLicenseFile();
		
	}
	
	public void readLicenseFile(){
		String progPath = System.getProperty("user.dir");
		String licensePath = myUtil.setPath(myUtil.setPath(progPath, "License"),"License.ens");
		//System.out.println(licensePath);
		Reader reader = new Reader(licensePath);
		reader.running();
		ArrayList<String> dataList = new ArrayList<String>();
		dataList = reader.getFileDataList();
		this.userLicensePeriod = dataList.get(0);
		this.userLicense = dataList.get(1);
		this.parsingDate(this.userLicensePeriod);
	}
	
	public void parsingDate(String period){
		ArrayList<String> tokens = new ArrayList<String>();
		tokens = ParserDefault.splitLineData(period, "_");
		this.startDate = tokens.get(0);
		this.endDate = tokens.get(1);
		System.out.println(this.startDate);
		System.out.println(this.endDate);
	}
	
	public void checkLicense(){
		String macAddress = myUtil.getMacAddress();
		String currentDate = myUtil.getCurrentData();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LicenseModule obj = new LicenseModule();
		obj.running();
	}

}
