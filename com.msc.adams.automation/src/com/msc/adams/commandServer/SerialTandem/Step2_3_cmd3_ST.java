package com.msc.adams.commandServer.SerialTandem;

import java.util.ArrayList;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.adams.automation.datas.Linkage;
import com.msc.adams.automation.datas.SwappingPart;
import com.msc.adams.commandServer.CMDFileList;
import com.msc.adams.commandServer.CmdSwapParameter;
import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.util.myUtil;

public class Step2_3_cmd3_ST {
	private MainController MC = MainController.getInstance();
	private CMDFileList cmdFileListObj = CMDFileList.getInstantce();
	
	// add 03.30
	private String LinkageType;
		
	private String ori_CMDPath;
	private String new_CMDPath;
	private ArrayList<String> fileDataList;
	private ArrayList<String> outputDataList;
	public Step2_3_cmd3_ST() {
		// TODO Auto-generated constructor stub
		this.LinkageType = MC.getDBStep1().getLinkageType();
		
		this.fileDataList = new ArrayList<String>();
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
		this.ori_CMDPath = myUtil.setPath(linkageFolder,cmdFileListObj.getFileName(CMDFileList.cmd3_ST));
		String AdamsCommand = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.AdamsCommand); 
		this.new_CMDPath = myUtil.setPath(AdamsCommand, cmdFileListObj.getFileName(CMDFileList.cmd3_ST));
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
		
	}
	
	private void readAndSwapCMD_SerialTandem(){
		Reader reader = new Reader(this.ori_CMDPath);
		reader.running();
		this.fileDataList = reader.getFileDataList();
		for(SwappingPart obj : MC.getDBStep3().getSwappingPartList()){
			
			for(String line : this.fileDataList){
				String partName = obj.getPartName();
				String mnfPath = obj.getPath();
				if(line.contains(CmdSwapParameter.PartName)){
					String newLine = line.replace(CmdSwapParameter.PartName, partName);
					this.outputDataList.add(newLine);
				}else if(line.contains(CmdSwapParameter.MNFPath)){
					String newLine = line.replace(CmdSwapParameter.MNFPath, mnfPath);
					this.outputDataList.add(newLine);
				}else {
					this.outputDataList.add(line);
				}
				
			}
		}
	}
	
	private void writeCMD(){
		Writer writer = new Writer(this.new_CMDPath);
		writer.running(outputDataList);
	}
	
	public String getNew_CMDPath() {
		return new_CMDPath;
	}
}
