package com.js.ens.leveller.proc;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class main_partialGutter {
	private Logger log = Logger.getLogger(main_partialGutter.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
	
	private ArrayList<String> procDataList;
	private String procFilePath;
	

	private String RollGenType;
	private String ChangeMotionToLoadPy;

	private ArrayList<String> fileDataList;	
	private String plateType;
	
	public main_partialGutter(){
		plateType = LMain.getcomboType();
		fileDataList = new ArrayList<String>();
	}
	
	public main_partialGutter(String path){
		this.procFilePath = path;
		plateType = LMain.getcomboType();
		fileDataList = new ArrayList<String>();
	}
	
	public void running(){
		try{
			// 실제 사용 
			procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "main_partialgutter.proc");
			if(Double.parseDouble(LMain.getTextRollCrown()) != 0.0){
				this.RollGenType = "*exec_procedure a2_roll_gen_crown.proc";
			}else {
				this.RollGenType = "*exec_procedure a2_roll_gen.proc";
			}
			if(LMain.getMillStiffnessType().equals("Spring")){
				this.ChangeMotionToLoadPy = "*py_file_run change_motion_to_load.py";
			}else {
				this.ChangeMotionToLoadPy = null;
			}
			
			readSourceData();
			swapValue();
			writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(main_partialgutter) \n path : "+ this.procFilePath);
			log.info("SUCCESS - Export(main_partialgutter) \n path : "+ this.procFilePath);
			
			//나머지 부분 작성 
			// 00_define_parameters.proc
			define_parameters DPObj = new define_parameters();
			DPObj.running();
			// m1_flat_proc
			m1_partialGutter M1FObj = new m1_partialGutter();
			M1FObj.running();
			// a2_roll_gen~~~
			if(this.RollGenType.equals("*exec_procedure a2_roll_gen.proc")){
				// a2_roll_gen.proc
				a2_roll_gen a2Obj = new a2_roll_gen();
				a2Obj.running();
			}else {
				// a2_roll_gen_crown.proc
				a2_roll_gen_crown a2cObj =new a2_roll_gen_crown();
				a2cObj.running();
			}
			// a3_material_define.proc
			a3_material_define a3Obj = new a3_material_define();
			a3Obj.running();
			// a4_contact.proc
			a4_contact a4Obj = new a4_contact();
			a4Obj.running();
			// a5_condition.proc
			a5_condition a5Obj = new a5_condition();
			a5Obj.running();
			// a6_loadcase.proc
			a6_loadcase a6Obj = new a6_loadcase();
			a6Obj.running();
			// a7_deformed_coordinate.proc
			a7_deformed_coordinate a7Obj = new a7_deformed_coordinate();
			a7Obj.running();
		}catch(Exception e){
			String msg = "ERROR - Export(main_partialgutter)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
	}
	
	public void readSourceData() throws Exception{
		String modulePath = myUtil.setPath(System.getProperty("user.dir"), "module");
		String s_folder = null;
		if(plateType.equals(ComboLabel.TYPE1))	
			s_folder = myUtil.setPath(modulePath,"1_Flat");
		else if(plateType.equals(ComboLabel.TYPE2))
			s_folder = myUtil.setPath(modulePath,"2_EdgeWave");
		else if(plateType.equals(ComboLabel.TYPE3))
			s_folder = myUtil.setPath(modulePath,"3_CenterWave");
		else if(plateType.equals(ComboLabel.TYPE4))
			s_folder = myUtil.setPath(modulePath,"4_SingleGutter");
		else if(plateType.equals(ComboLabel.TYPE5))
			s_folder = myUtil.setPath(modulePath,"5_PartialGutter");
		else if(plateType.equals(ComboLabel.TYPE6))
			s_folder = myUtil.setPath(modulePath,"6_DoubleGutter");
		else if(plateType.equals(ComboLabel.TYPE7))
			s_folder = myUtil.setPath(modulePath,"7_IslandGutter");
		String sourcePath = myUtil.setPath(s_folder, "main_partialgutter.proc");
		
		Reader reader = new Reader(sourcePath);
		reader.running();
		fileDataList = reader.getFileDataList();
		
	}
	
	public void swapValue() throws Exception{
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : fileDataList){
			if(line.contains("%"+ProcVariable.a2_RollGenType+"%")){
				newLine = line.replace("%"+ProcVariable.a2_RollGenType+"%", this.RollGenType );
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.changeMotionToLoadPy+"%")){
				if(this.ChangeMotionToLoadPy != null){
					newLine = line.replace("%"+ProcVariable.changeMotionToLoadPy+"%", this.ChangeMotionToLoadPy);
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
	
	public void writeProc() throws Exception{
		Writer writer = new Writer(procFilePath,procDataList);
		writer.running();
	}
	
}
