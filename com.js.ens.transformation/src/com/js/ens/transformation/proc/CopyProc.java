package com.js.ens.transformation.proc;

import java.util.ArrayList;

import com.js.ens.transformation.core.MainController;
import com.js.ens.transformation.core.UILabel;
import com.js.io.Reader;
import com.js.util.myUtil;

public class CopyProc {
	private MainController MC = MainController.getInstance();
	private String configFolder = myUtil.setPath(System.getProperty("user.dir"),"userConfig");
	private String userModuleFolder = myUtil.setPath(System.getProperty("user.dir"), "userModule");
	private String destFolder;
	
	public CopyProc() {
		// TODO Auto-generated constructor stub
	}
	
	public void copyF1(String destFolder){
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configFolder, "f1.filelist");
		this.copy(filelistPath,UILabel.F1);
	}
	
	public void copyF2(String destFolder){
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configFolder, "f2.filelist");
		this.copy(filelistPath,UILabel.F2);
	}

	public void copyF3(String destFolder){
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configFolder, "f3.filelist");
		this.copy(filelistPath,UILabel.F3);
	}

	public void copyF4(String destFolder){
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configFolder, "f4.filelist");
		this.copy(filelistPath,UILabel.F4);
	}
	
	public void copyF5(String destFolder){
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configFolder, "f5.filelist");
		this.copy(filelistPath,UILabel.F5);
	}
	
	public void copyF6(String destFolder){
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configFolder, "f6.filelist");
		this.copy(filelistPath,UILabel.F6);
	}
	
	public void copyF7(String destFolder){
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configFolder, "f7.filelist");
		this.copy(filelistPath,UILabel.F7);
	}
	
	private void copy(String filelistPath,String StandType){
		Reader reader = new Reader(filelistPath);
		reader.running();
		ArrayList<String> fileList = new ArrayList<String>();
		fileList = reader.getFileDataList();
		
		for(int i=2;i<fileList.size();i++){
			String procPath = myUtil.setPath(myUtil.setPath(userModuleFolder, StandType), fileList.get(i));
			String destProcPath = myUtil.setPath(myUtil.setPath(destFolder,StandType), fileList.get(i));
			myUtil.fileCopy(procPath, destProcPath);
		}
	}
	


}
