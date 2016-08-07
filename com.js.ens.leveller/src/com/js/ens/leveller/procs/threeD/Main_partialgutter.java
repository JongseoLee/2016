package com.js.ens.leveller.procs.threeD;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.ens.leveller.procs.ProcMaker;
import com.js.ens.leveller.procs.threeD.partialGutter.a2_roll_gen_partialGutter;
import com.js.ens.leveller.procs.threeD.partialGutter.a3_material_define_partialGutter;
import com.js.ens.leveller.procs.threeD.partialGutter.a4_contact_partialGutter;
import com.js.ens.leveller.procs.threeD.partialGutter.a6_loadcase_partialGutter;
import com.js.ens.leveller.procs.threeD.partialGutter.define_parameters_partialGutter;
import com.js.io.Reader;
import com.js.io.Writer;

public class Main_partialgutter {
	private Logger log = Logger.getLogger(Main_partialgutter.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
	
	private ArrayList<String> filePathList = new ArrayList<String>();
	private String main_partialgutter_Path 				= "";
	public String define_parameters_PATH	 			= "";
	public String a2_roll_gen_PATH 						= "";
	public String a3_material_define_PATH 				= "";
	public String mat01_elastic_modulus_PATH 			= "";
	public String mat01_elastic_modulus_const_PATH 		= "";
	public String mat02_flow_stress_PATH 				= "";
	public String mat02_flow_stress_const_PATH 			= "";
	public String mat03_thermal_expansion_PATH 			= "";
	public String mat03_thermal_expansion_const_PATH 	= "";
	public String mat04_poisson_PATH 					= "";
	public String mat04_poisson_const_PATH 				= "";
	public String mat05_mass_density_PATH 				= "";
	public String a4_contact_PATH 						= "";
	public String a6_loadcase_PATH						= "";
	
	
	
	private String RollGenType;
	private String ChangeMotionToLoadPy;
	private ArrayList<String> fileDataList;
	private ArrayList<String> procDataList;
	
	public Main_partialgutter() {
		// TODO Auto-generated constructor stub
	}

	public void running(ArrayList<String> destFullPath){
		this.filePathList = destFullPath;
		try{
			this.setFilesPath();
			
			this.RollGenType = "*exec_procedure a2_roll_gen.proc";
			if(LMain.getMillStiffnessType().equals("Spring")){
				this.ChangeMotionToLoadPy = "*py_file_run change_motion_to_load.py";
			}else {
				this.ChangeMotionToLoadPy = null;
			}
			this.readSourceData();
			this.swapValue();
			this.writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(main_partialgutter) \n path : "+ this.main_partialgutter_Path);
			log.info("SUCCESS - Export(main_partialgutter) \n path : "+ this.main_partialgutter_Path);
			
			this.GenOtherProcs();
		}catch(Exception e){
			String msg = "ERROR - Export(main_partialgutter)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
		
	}
	
	private void setFilesPath() throws Exception{
		for(String path : this.filePathList){
			if(path.contains("main_partialgutter.proc")){
				this.main_partialgutter_Path = path.trim();
			}else if(path.contains(ProcMaker.define_parameters)){
				this.define_parameters_PATH = path.trim();
			}else if(path.contains(ProcMaker.a2_roll_gen)){
				this.a2_roll_gen_PATH = path.trim();
			}else if(path.contains(ProcMaker.a3_material_define)){
				this.a3_material_define_PATH = path.trim();
			}else if(path.contains(ProcMaker.mat01_elastic_modulus)){
				this.mat01_elastic_modulus_PATH = path.trim();
			}else if(path.contains(ProcMaker.mat01_elastic_modulus_const)){
				this.mat01_elastic_modulus_const_PATH = path.trim();
			}else if(path.contains(ProcMaker.mat02_flow_stress)){
				this.mat02_flow_stress_PATH = path.trim();
			}else if(path.contains(ProcMaker.mat02_flow_stress_const)){
				this.mat02_flow_stress_const_PATH = path.trim();
			}else if(path.contains(ProcMaker.mat03_thermal_expansion)){
				this.mat03_thermal_expansion_PATH = path.trim();
			}else if(path.contains(ProcMaker.mat03_thermal_expansion_const)){
				this.mat03_thermal_expansion_const_PATH = path.trim();
			}else if(path.contains(ProcMaker.mat04_poisson)){
				this.mat04_poisson_PATH = path.trim();
			}else if(path.contains(ProcMaker.mat04_poisson_const)){
				this.mat04_poisson_const_PATH = path.trim();
			}else if(path.contains(ProcMaker.mat05_mass_density)){
				this.mat05_mass_density_PATH = path.trim();
			}else if(path.contains(ProcMaker.a4_contact)){
				this.a4_contact_PATH = path.trim();
			}else if(path.contains(ProcMaker.a6_loadcase)){
				this.a6_loadcase_PATH = path.trim();
			}
		}
	}
	
	private void readSourceData() throws Exception{
		this.fileDataList = new ArrayList<String>();
		Reader reader = new Reader(this.main_partialgutter_Path);
		reader.running();
		this.fileDataList = reader.getFileDataList();
	}
	
	private void swapValue() throws Exception{
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : fileDataList){
			if(line.contains(ProcMaker.a2_RollGenType)){
				newLine = line.replace(ProcMaker.a2_RollGenType, this.RollGenType );
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.changeMotionToLoadPy)){
				if(this.ChangeMotionToLoadPy != null){
					newLine = line.replace(ProcMaker.changeMotionToLoadPy, this.ChangeMotionToLoadPy);
					procDataList.add(newLine);
					continue;
				}else{
					continue;
				}
			}else {
				procDataList.add(line);
			}
		} 
	}
	
	private void writeProc() throws Exception{
		Writer writer = new Writer(this.main_partialgutter_Path,this.procDataList);
		writer.running();
	}
	
	private void GenOtherProcs(){
		ArrayList<String> matsPath = new ArrayList<String>();
		matsPath.add(this.mat01_elastic_modulus_PATH);
		matsPath.add(this.mat01_elastic_modulus_const_PATH);
		matsPath.add(this.mat02_flow_stress_PATH);
		matsPath.add(this.mat02_flow_stress_const_PATH);
		matsPath.add(this.mat03_thermal_expansion_PATH);
		matsPath.add(this.mat03_thermal_expansion_const_PATH);
		matsPath.add(this.mat04_poisson_PATH);
		matsPath.add(this.mat04_poisson_const_PATH);
		matsPath.add(this.mat05_mass_density_PATH);
		
		//00_define_parameters.proc
		define_parameters_partialGutter DPObj = new define_parameters_partialGutter();
		DPObj.running(this.define_parameters_PATH);
		//a2_roll_gen.proc
		a2_roll_gen_partialGutter a2Obj = new a2_roll_gen_partialGutter();
		a2Obj.running(this.a2_roll_gen_PATH);
		//a3_material_define.proc
		a3_material_define_partialGutter a3Obj = new a3_material_define_partialGutter();
		a3Obj.running(this.a3_material_define_PATH, matsPath);
		//a4_contact.proc
		a4_contact_partialGutter a4Obj = new a4_contact_partialGutter();
		a4Obj.running(this.a4_contact_PATH);
		//a6_loadcase.proc
		a6_loadcase_partialGutter a6Obj = new a6_loadcase_partialGutter();
		a6Obj.running(this.a6_loadcase_PATH);
	}
}
