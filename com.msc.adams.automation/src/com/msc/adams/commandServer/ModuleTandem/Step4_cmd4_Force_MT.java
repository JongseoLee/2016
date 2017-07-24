package com.msc.adams.commandServer.ModuleTandem;

import java.io.File;
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

public class Step4_cmd4_Force_MT {
	private MainController MC = MainController.getInstance();
	private CMDFileList cmdFileListObj = CMDFileList.getInstantce();
	
	// add 03.30
	private String LinkageType;

	private String ori_CMDPath;
	private String new_CMDPath;
	private ArrayList<String> outputDataList;
	public Step4_cmd4_Force_MT() {
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
		this.ori_CMDPath = myUtil.setPath(linkageFolder,cmdFileListObj.getFileName(CMDFileList.cmd4_Force_MT));
		String AdamsCommand = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.AdamsCommand); 
		this.new_CMDPath = myUtil.setPath(AdamsCommand, cmdFileListObj.getFileName(CMDFileList.cmd4_Force_MT));
	}

	
	public void running(){
		this.initForceFolder();
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
	
	private void initForceFolder(){
		String resultFolder = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.Result);
		String ForceFolder = myUtil.setPath(resultFolder, DatabaseFolderName.Force); 
		String resultNameFolder = myUtil.setPath(ForceFolder, MC.getDBStep4().getExportResultName());
		if(!myUtil.checkPath(resultNameFolder)){
			myUtil.makeDir(resultNameFolder);
		}
		String GlobalFolder = myUtil.setPath(resultNameFolder, DatabaseFolderName.Global);
		String LocalFolder = myUtil.setPath(resultNameFolder, DatabaseFolderName.Local);
		
		
		for(Part obj : MC.getDBStep2().getAllPartList()){
			String folderName = obj.getPartName();
			String globalForceFolder = myUtil.setPath(GlobalFolder, folderName);
			myUtil.makeDir(globalForceFolder);
			String localForceFolder = myUtil.setPath(LocalFolder, folderName);
			myUtil.makeDir(localForceFolder);
		}
	}
	
	private void readAndSwapCMD_ModuleTandem(){
		Reader reader = new Reader(this.ori_CMDPath);
		reader.running();
		for(String line :reader.getFileDataList()){
			if(line.contains(CmdSwapParameter.JobSpace)){
				String jobSpaceFolder = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.JobSpace);
				String convert_jobSpaceFolder = jobSpaceFolder.replace("\\", "/");
				
				String newLine = line.replace(CmdSwapParameter.JobSpace, convert_jobSpaceFolder);
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.ForceGlobalPath)){
				String resultFolder = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.Result);
				String ForceFolder = myUtil.setPath(resultFolder, DatabaseFolderName.Force); 
				String resultNameFolder = myUtil.setPath(ForceFolder, MC.getDBStep4().getExportResultName());
				if(!myUtil.checkPath(resultNameFolder)){
					myUtil.makeDir(resultNameFolder);
				}
				String GlobalFolder = myUtil.setPath(resultNameFolder, DatabaseFolderName.Global);
				String convert_ForcetFolder = GlobalFolder.replace("\\", "/");
				String newLine = line.replace(CmdSwapParameter.ForceGlobalPath, convert_ForcetFolder);
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.ForceLocalPath)){
				String resultFolder = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.Result);
				String ForceFolder = myUtil.setPath(resultFolder, DatabaseFolderName.Force); 
				String resultNameFolder = myUtil.setPath(ForceFolder, MC.getDBStep4().getExportResultName());
				if(!myUtil.checkPath(resultNameFolder)){
					myUtil.makeDir(resultNameFolder);
				}
				String LocalFolder = myUtil.setPath(resultNameFolder, DatabaseFolderName.Local);
				String convert_ForcetFolder = LocalFolder.replace("\\", "/");
				String newLine = line.replace(CmdSwapParameter.ForceLocalPath, convert_ForcetFolder);
				this.outputDataList.add(newLine);
			}
			/*
			else if(line.contains(CmdSwapParameter.DACResultPath)){
				String resultFolder = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.Result);
				String DACResultFolder = myUtil.setPath(resultFolder, DatabaseFolderName.DAC_Result);
				String convert_DACResultFolder = DACResultFolder.replace("\\", "/");
				
				String newLine = line.replace(CmdSwapParameter.DACResultPath, convert_DACResultFolder);
				this.outputDataList.add(newLine);
			}
			// */
			else{
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
