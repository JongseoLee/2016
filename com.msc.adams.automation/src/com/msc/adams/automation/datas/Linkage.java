package com.msc.adams.automation.datas;

import java.io.File;

public class Linkage {
	public static final String TYPE_SERIALPARALLEL = "SerialParallel";
	public static final String TYPE_SERIALOPPOSED = "SerialOpposed";
	public static final String TYPE_MODULETANDEM = "ModuleTandem";
	public static final String TYPE_SERIALTANDEM = "SerialTandem";
	
	private String type;		// type1~ type4
	private String dbPath;		// appDatabase ���� ��ġ
	private String dbName;		// xls ���� �̸� -> list�� ǥ���� ������
	public Linkage() {
		// TODO Auto-generated constructor stub
	}

	public void InitLinkageData(File f, String type){
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
