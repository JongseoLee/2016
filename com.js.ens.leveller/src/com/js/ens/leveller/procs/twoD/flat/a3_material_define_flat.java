package com.js.ens.leveller.procs.twoD.flat;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.ens.leveller.procs.ProcMaker;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class a3_material_define_flat {
	private Logger log = Logger.getLogger(a3_material_define_flat.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
		
	private String procPath 							= "";
	private String mat01_elastic_modulus_PATH 			= "";
	private String mat01_elastic_modulus_const_PATH 	= "";
	private String mat02_flow_stress_PATH 				= "";
	private String mat02_flow_stress_const_PATH 		= "";
	private String mat03_thermal_expansion_PATH 		= "";
	private String mat03_thermal_expansion_const_PATH 	= "";
	private String mat04_poisson_PATH 					= "";
	private String mat04_poisson_const_PATH 			= "";
	private String mat05_mass_density_PATH 				= "";
		
	private ArrayList<String> fileDataList;
	private ArrayList<String> procDataList;
	
	private String tableName_elasticModulus = "elastic_modulus";
	private String tableName_flowStress = "flow_stress";
	private String tableName_thermal_expansion ="CTE";
	private String tableName_poisson = "poisson";
	
	public a3_material_define_flat() {
		// TODO Auto-generated constructor stub
	}

	public void running(String path, ArrayList<String> matsPath) {
		// TODO Auto-generated method stub
		this.procPath = path;
		this.mat01_elastic_modulus_PATH 		= matsPath.get(0);
		this.mat01_elastic_modulus_const_PATH 	= matsPath.get(1);
		this.mat02_flow_stress_PATH				= matsPath.get(2);
		this.mat02_flow_stress_const_PATH		= matsPath.get(3);
		this.mat03_thermal_expansion_PATH		= matsPath.get(4);
		this.mat03_thermal_expansion_const_PATH	= matsPath.get(5);
		this.mat04_poisson_PATH					= matsPath.get(6);
		this.mat04_poisson_const_PATH			= matsPath.get(7);
		this.mat05_mass_density_PATH			= matsPath.get(8);
		
		try{
			
			this.genMatProc();
			this.readSourceData();
			this.swapValue();
			this.writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(a3_material_define) \n path : "+ this.procPath);
			log.info("SUCCESS - Export(a3_material_define) \n path : "+ this.procPath);
		}catch(Exception e){
			String msg = "ERROR - Export(a3_material_define)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
	}
	
	private void genMatProc() {
		//mat01_~.proc
		if(LMain.getYoungsModulusType().toLowerCase().equals("constant")){
			mat01_elastic_modulus_const_flat EMObj = new mat01_elastic_modulus_const_flat();
			EMObj.running(this.mat01_elastic_modulus_const_PATH);
			myUtil.fileDelete(this.mat01_elastic_modulus_PATH);
		}
		//mat01_~_const.proc
		else if(LMain.getYoungsModulusType().toLowerCase().equals("table")){
			mat01_elastic_modulus_flat EMObj = new mat01_elastic_modulus_flat();
			EMObj.running(this.mat01_elastic_modulus_PATH);
			myUtil.fileDelete(this.mat01_elastic_modulus_const_PATH);
			this.tableName_elasticModulus = EMObj.getTableName();
		}
		//mat02_~proc
		if(LMain.getFlowStressType().toLowerCase().equals("constant")){
			mat02_flow_stress_const_flat FSObj = new mat02_flow_stress_const_flat();
			FSObj.running(this.mat02_flow_stress_const_PATH);
			myUtil.fileDelete(this.mat02_flow_stress_PATH);
		}
		//mat02_~_const.proc
		else if(LMain.getFlowStressType().toLowerCase().equals("table")){
			mat02_flow_stress_flat FSObj = new mat02_flow_stress_flat();
			FSObj.running(this.mat02_flow_stress_PATH);
			myUtil.fileDelete(this.mat02_flow_stress_const_PATH);
			this.tableName_flowStress = FSObj.getTableName();
		}
		//mat03_~proc
		if(LMain.getThermalExpansionCoefficientType().toLowerCase().equals("constant")){
			mat03_thermal_expansion_const_flat TECObj = new mat03_thermal_expansion_const_flat();
			TECObj.running(this.mat03_thermal_expansion_const_PATH);
			myUtil.fileDelete(this.mat03_thermal_expansion_PATH);
		}
		//mat03_~_const.proc
		else if(LMain.getThermalExpansionCoefficientType().toLowerCase().equals("table")){
			mat03_thermal_expansion_flat TECObj = new mat03_thermal_expansion_flat();
			TECObj.running(this.mat03_thermal_expansion_PATH);
			myUtil.fileDelete(this.mat03_thermal_expansion_const_PATH);
			this.tableName_thermal_expansion = TECObj.getTableName();
		}
		//mat04_~proc
		if (LMain.getPoissonsRatioType().toLowerCase().equals("constant")) {
			mat04_poisson_const_flat PObj = new mat04_poisson_const_flat();
			PObj.running(this.mat04_poisson_const_PATH);
			myUtil.fileDelete(this.mat04_poisson_PATH);
		}
		//mat04_~_const.proc
		else if (LMain.getPoissonsRatioType().toLowerCase().equals("table")) {
			mat04_poisson_flat PObj = new mat04_poisson_flat();
			PObj.running(this.mat04_poisson_PATH);
			myUtil.fileDelete(this.mat04_poisson_const_PATH);
			this.tableName_poisson = PObj.getTableName();
		}
		//mat05_~proc
		mat05_mass_density_flat MDObj = new mat05_mass_density_flat();
		MDObj.running(this.mat05_mass_density_PATH);
	}
	
	private void readSourceData() throws Exception {
		// TODO Auto-generated method stub
		this.fileDataList = new ArrayList<String>();
		Reader reader = new Reader(this.procPath);
		reader.running();
		this.fileDataList = reader.getFileDataList();
	}
	
	private void swapValue() throws Exception {
		// TODO Auto-generated method stub
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : fileDataList){
			if(line.contains(ProcMaker.elasticModulusType)){
				if(LMain.getYoungsModulusType().toLowerCase().equals("constant")){
					procDataList.add("*exec_procedure mat01_elastic_modulus_const.proc");
				}else if(LMain.getYoungsModulusType().toLowerCase().equals("table")){
					procDataList.add("*exec_procedure mat01_elastic_modulus.proc");		
				}
				continue;
			}else if(line.contains(ProcMaker.poissonType)){
				if(LMain.getPoissonsRatioType().toLowerCase().equals("constant")){
					procDataList.add("*exec_procedure mat04_poisson_const.proc");
				}else if(LMain.getPoissonsRatioType().toLowerCase().equals("table")){
					procDataList.add("*exec_procedure mat04_poisson.proc");		
				}
				continue;
			}else if(line.contains(ProcMaker.flowStressType)){
				if(LMain.getFlowStressType().toLowerCase().equals("constant")){
					procDataList.add("*exec_procedure mat02_flow_stress_const.proc");
				}else if(LMain.getFlowStressType().toLowerCase().equals("table")){
					procDataList.add("*exec_procedure mat02_flow_stress.proc");		
				}
				continue;
			}else if(line.contains(ProcMaker.thermal_expansionType)){
				if(LMain.getThermalExpansionCoefficientType().toLowerCase().equals("constant")){
					procDataList.add("*exec_procedure mat03_thermal_expansion_const.proc");
				}else if(LMain.getThermalExpansionCoefficientType().toLowerCase().equals("table")){
					procDataList.add("*exec_procedure mat03_thermal_expansion.proc");		
				}
				continue;
			}else if(line.contains(ProcMaker.tableName_elasticModulus)){
				newLine = line.replace(ProcMaker.tableName_elasticModulus, this.tableName_elasticModulus);
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.tableName_poisson)){
				newLine = line.replace(ProcMaker.tableName_poisson, this.tableName_poisson);
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.tableName_flowStress)){
				newLine = line.replace(ProcMaker.tableName_flowStress, this.tableName_flowStress);
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.tableName_thermal_expansion)){
				newLine = line.replace(ProcMaker.tableName_thermal_expansion, this.tableName_thermal_expansion);
				procDataList.add(newLine);
				continue;
			}else {
				procDataList.add(line);
			}
		}
	}
	
	private void writeProc() throws Exception {
		// TODO Auto-generated method stub
		Writer writer = new Writer(this.procPath,this.procDataList);
		writer.running();
	}

}
