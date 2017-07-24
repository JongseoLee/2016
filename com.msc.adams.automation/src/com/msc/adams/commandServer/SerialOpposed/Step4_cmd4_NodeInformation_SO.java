package com.msc.adams.commandServer.SerialOpposed;

import java.util.ArrayList;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.adams.automation.datas.Linkage;
import com.msc.adams.automation.datas.Part;
import com.msc.adams.commandServer.CMDFileList;
import com.msc.adams.commandServer.CmdSwapParameter;
import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.util.myUtil;

public class Step4_cmd4_NodeInformation_SO {
	private MainController MC = MainController.getInstance();
	private CMDFileList cmdFileListObj = CMDFileList.getInstantce();

	// add 03.30
	private String LinkageType;

	private String ori_CMDPath;
	private String new_CMDPath;
	private ArrayList<String> outputDataList;
	
	public Step4_cmd4_NodeInformation_SO() {
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
		this.ori_CMDPath = myUtil.setPath(linkageFolder,cmdFileListObj.getFileName(CMDFileList.cmd4_NodeInfomation_SO));
		String AdamsCommand = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.AdamsCommand); 
		this.new_CMDPath = myUtil.setPath(AdamsCommand, cmdFileListObj.getFileName(CMDFileList.cmd4_NodeInfomation_SO));
	}
	
	public void running(){
		this.initFolder();
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
	
	private void initFolder(){
		String resultFolder = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.Result);
		String nodeInformationFolder = myUtil.setPath(resultFolder, DatabaseFolderName.NodeInformation);
		for(Part obj: MC.getDBStep2().getSelectedPartList()){
			String partName = obj.getPartName();
			String partNameFolder = myUtil.setPath(nodeInformationFolder, partName);
			if(!myUtil.checkPath(partNameFolder)){
				myUtil.makeDir(partNameFolder);
			}
		}
	}
	
	private void readAndSwapCMD_ModuleTandem() {
		// TODO Auto-generated method stub
		
	}
	
	private void readAndSwapCMD_SerialOpposed(){
		Reader reader = new Reader(this.ori_CMDPath);
		reader.running();
		for(Part obj : MC.getDBStep2().getSelectedPartList()){
			for(String line : reader.getFileDataList()){
				if(line.contains(CmdSwapParameter.JobSpace)){
					String jobSpaceFolder = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.JobSpace);
					String convert_jobSpaceFolder = jobSpaceFolder.replace("\\", "/");
					
					String newLine = line.replace(CmdSwapParameter.JobSpace, convert_jobSpaceFolder);
					this.outputDataList.add(newLine);
				}else if(line.contains(CmdSwapParameter.NodeInfomationPath)){
					String resultFolder = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.Result);
					String nodeInformationFolder = myUtil.setPath(resultFolder, DatabaseFolderName.NodeInformation);
					String partName = obj.getPartName();
					String partNameFolder = myUtil.setPath(nodeInformationFolder, partName);
					String convert_jobSpaceFolder = partNameFolder.replace("\\", "/");
					
					String newLine = line.replace(CmdSwapParameter.NodeInfomationPath, convert_jobSpaceFolder);
					this.outputDataList.add(newLine);
				}else if(line.contains(CmdSwapParameter.PartName)){
					String newLine = line.replace(CmdSwapParameter.PartName, obj.getPartName());
					this.outputDataList.add(newLine);
				}else{
					this.outputDataList.add(line);	
				}
			}
		}
	}
	
	private void readAndSwapCMD_SerialParallel(){
		
	}
	
	private void readAndSwapCMD_SerialTandem(){
		
	}

	private void writeCMD() {
		// TODO Auto-generated method stub
		Writer writer = new Writer(this.new_CMDPath);
		writer.running(outputDataList);
	}
	
	public String getNew_CMDPath() {
		return new_CMDPath;
	}
}
