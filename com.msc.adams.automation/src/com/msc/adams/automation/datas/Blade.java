package com.msc.adams.automation.datas;

import java.io.File;

public class Blade {
	/*
	public static final String TYPE_SYMMETRIC = "Symmetric";	                                              
	public static final String TYPE_NONSYMMETRIC_INNER = "NonSymmetric_inner";
	public static final String TYPE_NONSYMMETRIC_OUTER = "NonSymmetric_outer";
	// */
	public static final String TYPE_SYMMETRIC_SAME_BASIC = "Symmetric_Same_Basic";
	public static final String TYPE_SYMMETRIC_DIFFERENT_BASIC = "Symmetric_Different_Basic";
	public static final String TYPE_NONSYMMETRIC_OUTER_BASIC = "NonSymmetric_Outer_Basic";
	public static final String TYPE_NONSYMMETRIC_INNNER_BASIC = "NonSymmetric_Inner_Basic";
	
	
	private String type;		// type1~ type4
	private String dbPath;		// appDatabase 파일 위치
	private String dbName;		// xls 파일 이름 -> list에 표시할 데이터
	public Blade() {
		// TODO Auto-generated constructor stub
	}
	
	public void InitBladeData(File f, String type){
		this.type = type;
		this.dbPath = f.getAbsolutePath();
		this.dbName = f.getName();
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDbPath() {
		return dbPath;
	}
	public void setDbPath(String dbPath) {
		this.dbPath = dbPath;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

}
