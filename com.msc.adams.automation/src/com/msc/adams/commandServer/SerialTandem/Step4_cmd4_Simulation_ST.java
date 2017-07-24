package com.msc.adams.commandServer.SerialTandem;

import java.io.File;
import java.util.ArrayList;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.adams.automation.datas.Linkage;
import com.msc.adams.commandServer.CMDFileList;
import com.msc.adams.commandServer.CmdSwapParameter;
import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.util.myUtil;

public class Step4_cmd4_Simulation_ST {
	private MainController MC = MainController.getInstance();
	private CMDFileList cmdFileListObj = CMDFileList.getInstantce();
	
	// add 03.30
	private String LinkageType;

	private String ori_CMDPath;
	private String new_CMDPath;
	private ArrayList<String> outputDataList;
	
	private String pltFileName_Module_Tandem = "Module_Tandem_Graph.plt";
	private String pltFileName_Serial_Opposed = "Serial_Opposed_Graph.plt";
	private String pltFileName_Serial_Parallel = "Serial_Parallel_Graph.plt";
	private String pltFileName_Serial_Tandem = "Serial_Tandem_Graph.plt";
	
	private String RemovePreSimulationScript ="";
	public Step4_cmd4_Simulation_ST() {
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
		this.ori_CMDPath = myUtil.setPath(linkageFolder,cmdFileListObj.getFileName(CMDFileList.cmd4_Simulation_ST));
		String AdamsCommand = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.AdamsCommand); 
		this.new_CMDPath = myUtil.setPath(AdamsCommand, cmdFileListObj.getFileName(CMDFileList.cmd4_Simulation_ST));
	}

	
	public void running(){
		if(!MC.getDBStep4().isFirstRun()){
			this.readFirstRunCMD();
		}
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
	
	public void readFirstRunCMD(){
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
		String RemovePreSimulation = myUtil.setPath(linkageFolder, cmdFileListObj.getFileName(CMDFileList.cmd4_RemovePreSimulation_ST));
		Reader reader = new Reader(RemovePreSimulation);
		reader.running();
		for(String line : reader.getFileDataList()){
			this.RemovePreSimulationScript += line +"\n";
		}
		//System.out.println(this.RemovePreSimulationScript);
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
		for(String line :reader.getFileDataList()){
			if(line.contains(CmdSwapParameter.FirstSolving)){
				String newLine = "";
				if(MC.getDBStep4().isFirstRun()){
					newLine = line.replace(CmdSwapParameter.FirstSolving, "!--First Solving");
				}else{
					newLine = line.replace(CmdSwapParameter.FirstSolving, RemovePreSimulationScript);
				}
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.ExtraMass)){
				if(MC.getDBStep4().isExtraMassOn()){
					//System.out.println("True True===>" + MC.getDBStep4().isExtraMassOn());
					String newLine1 = "variable modify variable_name=.Wiper_Assem.Trigger_Extra_Mass real="+MC.getDBStep4().getExtraMass();
					String newLine2 = "variable modify variable_name=.Wiper_Assem.Rubber_Extra_Mass_Ratio real="+MC.getDBStep4().getExtraMassRatio();
					this.outputDataList.add(newLine1);
					this.outputDataList.add(newLine2);
				}else{
					//System.out.println("False False===>" + MC.getDBStep4().isExtraMassOn());
					String newLine = "variable modify variable_name=.Wiper_Assem.Trigger_Extra_Mass real=0";
					this.outputDataList.add(newLine);					
				}
			}
			else if(line.contains(CmdSwapParameter.Integrator)){
				String newLine = line.replace(CmdSwapParameter.Integrator, MC.getDBStep4().getIntegrator());
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.Formulation)){
				String newLine = line.replace(CmdSwapParameter.Formulation, MC.getDBStep4().getFormulation());
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.Corrector)){
				String newLine = line.replace(CmdSwapParameter.Corrector, MC.getDBStep4().getCorrector());
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.Error)){
				String newLine = line.replace(CmdSwapParameter.Error, MC.getDBStep4().getError());
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.Hmax)){
				String newLine = line.replace(CmdSwapParameter.Hmax, MC.getDBStep4().getHmax());
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.NumberOfStep)){
				String newLine = line.replace(CmdSwapParameter.NumberOfStep, MC.getDBStep4().getNubmerOfStep());
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.EndTime)){
				String newLine = line.replace(CmdSwapParameter.EndTime, MC.getDBStep4().getEndTime());
				this.outputDataList.add(newLine);
			}else if(line.contains(CmdSwapParameter.PltFileName)){
				String fileName = "";
				if(MC.getDBStep1().getLinkageType().equals(Linkage.TYPE_MODULETANDEM)){
					fileName = this.pltFileName_Module_Tandem;
				}else if(MC.getDBStep1().getLinkageType().equals(Linkage.TYPE_SERIALOPPOSED)){
					fileName = this.pltFileName_Serial_Opposed;
				}else if(MC.getDBStep1().getLinkageType().equals(Linkage.TYPE_SERIALPARALLEL)){
					fileName = this.pltFileName_Serial_Parallel;
				}else if(MC.getDBStep1().getLinkageType().equals(Linkage.TYPE_SERIALTANDEM)){
					fileName = this.pltFileName_Serial_Tandem;
				}
				String newLine = line.replace(CmdSwapParameter.PltFileName, fileName);
				this.outputDataList.add(newLine);
			}else{
				this.outputDataList.add(line);
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
