package com.msc.adams.commandServer.SerialParallel;

import java.util.ArrayList;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.adams.automation.datas.Linkage;
import com.msc.adams.commandServer.CMDFileList;
import com.msc.adams.commandServer.CmdSwapParameter;
import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.util.myUtil;

public class Step1_cmd1_2_Geo_SP {
	private MainController MC = MainController.getInstance();
	private CMDFileList cmdFileListObj = CMDFileList.getInstantce();
	
	// add 03.30
	private String LinkageType;
	
	private String ori_CMDPath;
	private String new_CMDPath;
	private ArrayList<String> outputDataList;
	public Step1_cmd1_2_Geo_SP() {
		// TODO Auto-generated constructor stub
		this.LinkageType = MC.getDBStep1().getLinkageType();
		this.outputDataList = new ArrayList<String>();
		
		String msck_Template = myUtil.setPath(MC.getAppPath(), DatabaseFolderName.msck_Template);
		String AdamsCMD = myUtil.setPath(msck_Template, DatabaseFolderName.AdamsCMD);
		String linkageFolder = AdamsCMD; 
		if(this.LinkageType.equals(Linkage.TYPE_MODULETANDEM)){
			linkageFolder = myUtil.setPath(AdamsCMD, DatabaseFolderName.ModuleTandem);
		}else if(this.LinkageType.equals(Linkage.TYPE_SERIALOPPOSED)){
			linkageFolder = myUtil.setPath(AdamsCMD, DatabaseFolderName.SerialOpposed);
		}else if(this.LinkageType.equals(Linkage.TYPE_SERIALPARALLEL)){
			linkageFolder = myUtil.setPath(AdamsCMD, DatabaseFolderName.SerialParallel);
		}else if(this.LinkageType.equals(Linkage.TYPE_SERIALTANDEM)){
			linkageFolder = myUtil.setPath(AdamsCMD, DatabaseFolderName.SerialTandem);
		}
		this.ori_CMDPath = myUtil.setPath(linkageFolder,cmdFileListObj.getFileName(CMDFileList.cmd1_2_Geo_SP));
		String AdamsCommand = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.AdamsCommand); 
		this.new_CMDPath = myUtil.setPath(AdamsCommand, cmdFileListObj.getFileName(CMDFileList.cmd1_2_Geo_SP));
		
	}
	
	public void running(){
		if(this.LinkageType.equals(Linkage.TYPE_MODULETANDEM)){
			this.readAndSwapCMD_ModuleTandem();
		}else if(this.LinkageType.equals(Linkage.TYPE_SERIALOPPOSED)){
			this.readAndSwapCMD_SerialOpposed();
		}else if(this.LinkageType.equals(Linkage.TYPE_SERIALPARALLEL)){
			this.readAndSwapCMD_SerialParallel();
		}else if(this.LinkageType.equals(Linkage.TYPE_SERIALTANDEM)){
			this.readAndSwapCMD_SerialTandem();
		}
		this.writeCMD();
	}
	
	private void readAndSwapCMD_ModuleTandem(){
		
	}
	
	private void readAndSwapCMD_SerialOpposed(){
		
	}
	
	private void readAndSwapCMD_SerialParallel(){
		Reader reader = new Reader(this.ori_CMDPath);
		reader.running();
		for(String line : reader.getFileDataList()){
			if(line.contains(CmdSwapParameter.SelectedWindshieldDBPath)){
				String newLine = line.replace(CmdSwapParameter.SelectedWindshieldDBPath, MC.getDBStep1().getSelectedWindshieldDBPath());
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.SelectedWindshieldDBName)){
				String newLine = line.replace(CmdSwapParameter.SelectedWindshieldDBName, myUtil.getFileName(MC.getDBStep1().getSelectedWindshieldDBName()));
				this.outputDataList.add(newLine);
			}else{
				this.outputDataList.add(line);
			}
		}
	}
	
	private void readAndSwapCMD_SerialTandem(){
		
	}
	
	private void writeCMD(){
		Writer writer = new Writer(this.new_CMDPath);
		writer.running(outputDataList);
	}

	public String getNew_CMDPath() {
		return new_CMDPath;
	}
}
