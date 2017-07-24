package com.msc.adams.automation.datas;

import java.io.File;

public class Windshield {
	
	public static final String PATH_EMPTY = "EMPTY";
	public static final String DBNAME_EMPTY = "EMPTY";
	public static final String TYPE_SPHERE = "Sphere";
	public static final String TYPE_GEOMETRY = "Geometry";
	
	private String type; 
	private String dbPath;
	private String dbName;
	
	private String radius;
	private String x;
	private String y;
	private String z;
	
	public Windshield() {
		// TODO Auto-generated constructor stub
	}

	public void InitWindshield_typeGeometry(File f, String type){
		this.type = type;
		this.dbPath = f.getAbsolutePath();
		this.dbName = f.getName();
	}
	
	public void InitWindshield_typeSphere(String radius, String x, String y, String z){
		this.radius = radius;
		this.x = x;
		this.y = y;
		this.z = z;
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

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getZ() {
		return z;
	}

	public void setZ(String z) {
		this.z = z;
	}

}
