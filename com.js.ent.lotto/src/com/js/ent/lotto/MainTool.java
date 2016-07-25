package com.js.ent.lotto;

import java.util.ArrayList;

import com.js.io.Reader;
import com.js.util.myUtil;

public class MainTool {
	private String dbPath = null;
	private ArrayList<String> dbFileDataList = new ArrayList<String>();
	private ArrayList<Round> RoundList = new ArrayList<Round>();
	
	public MainTool() {
		// TODO Auto-generated constructor stub
	}
	
	public MainTool(String dbPath) {
		// TODO Auto-generated constructor stub
		this.dbPath = dbPath;
	}
	
	public void LoadingDB(){
		Reader obj = new Reader(this.dbPath);
		obj.running();
		this.dbFileDataList = obj.getFileDataList();
		this.dbFileDataList.remove(0);	// column ∏Ì ªË¡¶
		
		for(String line : this.dbFileDataList){
			//System.out.println(line);
			Round Robj = new Round();
			Robj.setAllData(line);
			RoundList.add(Robj);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dbPath = myUtil.setPath(System.getProperty("user.dir"), "db.csv");
		if(myUtil.checkPath(dbPath)){
			System.out.println("DB Loading...");
			System.out.println(dbPath);	
		}else {
			System.out.println("DB Loading failed...");
		}
		
		MainTool mt = new MainTool(dbPath);
		mt.LoadingDB();
	}

}
