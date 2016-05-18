package com.js.ens.leveller.proc;

import java.util.ArrayList;

import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.io.Writer;
import com.js.util.myUtil;

public class main_centerBuckle {
	/*
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private ArrayList<String> procDataList;
	private String procFilePath;
	
	private String plateType;

	public main_centerBuckle(){
		plateType = LMain.getcomboType();
	}
	
	public main_centerBuckle(String path){
		this.procFilePath = path;
		plateType = LMain.getcomboType();
	}
	
	public void running(){
		// 실제 사용 
		procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "main_centerBuckle.proc");
		
		copyProc();
		
		//나머지 부분 작성 
		// 00_define_parameters.proc
		define_parameters DPObj = new define_parameters();
		DPObj.running();
		// m1_flat_proc
		m1_centerBuckle M1FObj = new m1_centerBuckle();
		M1FObj.running();
		// a2_roll_gen.proc
		a2_roll_gen a2Obj = new a2_roll_gen();
		a2Obj.running();
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
		
	}
	
	public void copyProc(){
		String modulePath = myUtil.setPath(System.getProperty("user.dir"), "module");
		String s_folder = null;
		if(plateType.equals(ComboLabel.TYPE1))	
			s_folder = myUtil.setPath(modulePath,"1_Flat");
		else if(plateType.equals(ComboLabel.TYPE2))
			s_folder = myUtil.setPath(modulePath,"2_EdgeWave");
		else if(plateType.equals(ComboLabel.TYPE3))
			s_folder = myUtil.setPath(modulePath,"3_CenterBukle");
		else if(plateType.equals(ComboLabel.TYPE4))
			s_folder = myUtil.setPath(modulePath,"4_SingleGutter");
		else if(plateType.equals(ComboLabel.TYPE5))
			s_folder = myUtil.setPath(modulePath,"5_PartialGutter");
		else if(plateType.equals(ComboLabel.TYPE6))
			s_folder = myUtil.setPath(modulePath,"6_DoubleGutter");
		else if(plateType.equals(ComboLabel.TYPE7))
			s_folder = myUtil.setPath(modulePath,"7_IslandGutter");
		String sourcePath = myUtil.setPath(s_folder, "main_centerBuckle.proc");
		
		myUtil.fileCopy(sourcePath, procFilePath);
	}
	*/
	/*
	
	public void createProcData(){
		this.procDataList = new ArrayList<String>();
		procDataList.add("|");
		procDataList.add("| Main Procedure of Levelling analysis");
		procDataList.add("|           created by S.H. Kim 2014.12.20");
		procDataList.add("|");
		procDataList.add("*set_undo off");
		procDataList.add("*exec_procedure 00_define_parameters.proc");
		procDataList.add("*exec_procedure m1_centerBuckle.proc");
		procDataList.add("*exec_procedure a2_roll_gen.proc");
		procDataList.add("*exec_procedure a3_material_define.proc");
		procDataList.add("*exec_procedure a4_contact.proc");
		procDataList.add("*exec_procedure a5_condition.proc");
		procDataList.add("*exec_procedure a6_loadcase.proc");
		procDataList.add("*exec_procedure a7_deformed_coordinate.proc");
		procDataList.add("*set_undo on");
	}
	
	public void writeProc(){
		Writer writer = new Writer(procFilePath,procDataList);
		writer.running();
	}
	*/
	
}
