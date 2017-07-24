package com.msc.adams.commandServer.ModuleTandem;

import java.util.ArrayList;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.adams.automation.datas.Linkage;
import com.msc.adams.commandServer.CMDFileList;
import com.msc.adams.commandServer.CmdSwapParameter;
import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.util.myUtil;

public class Step1_cmd1_2_Sph_MT {
	private MainController MC = MainController.getInstance();
	private CMDFileList cmdFileListObj = CMDFileList.getInstantce();
	
	// add 03.30
	private String LinkageType;
	
	private String ori_CMDPath;
	private String new_CMDPath;
	private ArrayList<String> outputDataList;
	public Step1_cmd1_2_Sph_MT() {
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
		this.ori_CMDPath = myUtil.setPath(linkageFolder,cmdFileListObj.getFileName(CMDFileList.cmd1_2_Sph_MT));
		String AdamsCommand = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.AdamsCommand); 
		this.new_CMDPath = myUtil.setPath(AdamsCommand, cmdFileListObj.getFileName(CMDFileList.cmd1_2_Sph_MT));
		
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
		
		Reader reader = new Reader(this.ori_CMDPath);
		reader.running();
		for(String line : reader.getFileDataList()){
			if(line.contains(CmdSwapParameter.RadiusValue)){
				String newLine = line.replace(CmdSwapParameter.RadiusValue, MC.getDBStep1().getRadius());
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.XValue)){
				String newLine = line.replace(CmdSwapParameter.XValue, MC.getDBStep1().getX());
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.YValue)){
				String newLine = line.replace(CmdSwapParameter.YValue, MC.getDBStep1().getY());
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.ZValue)){
				String newLine = line.replace(CmdSwapParameter.ZValue, MC.getDBStep1().getZ());
				this.outputDataList.add(newLine);
			}else{
				this.outputDataList.add(line);
			}
			
		}
	}
	
	private void readAndSwapCMD_SerialOpposed(){
		
	}
	
	private void readAndSwapCMD_SerialParallel(){
		
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
