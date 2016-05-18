package com.js.ens.leveller.proc;


import java.util.ArrayList;

import com.js.io.Writer;
import com.js.util.myUtil;
import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;

public class m1_centerBuckle {
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private ArrayList<String> procDataList;
	private String procFilePath;
	
	private String plateType;
	
	public m1_centerBuckle(){
		plateType = LMain.getcomboType();
	}
	
	public m1_centerBuckle(String path){
		this.procFilePath = path;
		plateType = LMain.getcomboType();
	}
	
	public void running(){
		//
		procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "m1_centerBuckle.proc");
		copyProc();
	
		m1_a_centerBuckle m1Obj = new m1_a_centerBuckle();
		m1Obj.running();
		
		String modulePath = myUtil.setPath(System.getProperty("user.dir"), "module");
		String inpy1 = myUtil.setPath(modulePath,"mp01_define_post_set_centerBuckle.py");
		String inpy2 = myUtil.setPath(modulePath,"mp02_redefine_post_set_centerBuckle.py");
		String outpy1 = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "mp01_define_post_set_centerBuckle.py");
		String outpy2 = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "mp02_redefine_post_set_centerBuckle.py");
		myUtil.fileCopy(inpy1, outpy1);
		myUtil.fileCopy(inpy2, outpy2);
		
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
		String sourcePath = myUtil.setPath(s_folder, "m1_centerBuckle.proc");
		
		myUtil.fileCopy(sourcePath, procFilePath);
	}
	/*
	public void createProcData(){
		procDataList = new ArrayList<String>();
		procDataList.add("| Create Surface");
		procDataList.add("*set_surface_type quad");
		procDataList.add("*add_surfaces");
		procDataList.add("point(0,0, W/2)");
		procDataList.add("point(0,0, 0)");
		procDataList.add("point(-L,0,0)");
		procDataList.add("point(-L,0,W/2)");
		procDataList.add("point(0, 0,W)");
		procDataList.add("point(0, 0,W/2)");
		procDataList.add("point(-L,0,W/2)");
		procDataList.add("point(-L,0,W)");
		procDataList.add("*fill_view");
		procDataList.add("*set_convert_divisions");
		procDataList.add("|  Element Edge size is defined same as thickness");
		procDataList.add("(W/2)/T");
		procDataList.add("(L)/T");
		procDataList.add("*convert_surfaces");
		procDataList.add("1");
		procDataList.add("2");
		procDataList.add("#");
		procDataList.add("*set_sweep_tolerance 0.5");
		procDataList.add("*sweep_all");
		procDataList.add("*remove_unused_nodes");
		procDataList.add("*remove_unused_points");
		procDataList.add("*set_sweep_tolerance 0.001");
		procDataList.add("*detach_nodes");
		procDataList.add("all_existing");
		procDataList.add("*detach_edges");
		procDataList.add("all_existing");
		procDataList.add("*detach_faces");
		procDataList.add("all_existing");
		procDataList.add("*move_reset");
		procDataList.add("*set_move_translation y T/2");
		procDataList.add("*move_elements");
		procDataList.add("all_existing");
		procDataList.add("*move_surfaces");
		procDataList.add("all_existing");
		if(LMain.getcomboType().equals(ComboLabel.TYPE1))
			procDataList.add("*exec_procedure m1_a_flat.proc");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE2))
			procDataList.add("*exec_procedure m1_a_edgeWave.proc");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE3))
			procDataList.add("*exec_procedure m1_a_centerBuckle.proc");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE4))
			procDataList.add("*exec_procedure m1_a_singleGutter.proc");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE5))
			procDataList.add("*exec_procedure m1_a_partialGutter.proc");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE6))
			procDataList.add("*exec_procedure m1_a_doubleGutter.proc");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE7))
			procDataList.add("*exec_procedure m1_a_islandGutter.proc");
		procDataList.add("|");
		procDataList.add("| run python for saving sets with history plot points");
		procDataList.add("|");
		procDataList.add("*py_echo off");
		if(LMain.getcomboType().equals(ComboLabel.TYPE1))
			procDataList.add("*py_file_run mp01_define_post_set_flat.py");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE2))
			procDataList.add("*py_file_run mp01_define_post_set_edgeWave.py");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE3))
			procDataList.add("*py_file_run mp01_define_post_set_centerBuckle.py");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE4))
			procDataList.add("*py_file_run mp01_define_post_set_singleGutter.py");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE5))
			procDataList.add("*py_file_run mp01_define_post_set_partialGutter.py");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE6))
			procDataList.add("*py_file_run mp01_define_post_set_doubleGutter.py");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE7))
			procDataList.add("*py_file_run mp01_define_post_set_islandGutter.py");
		procDataList.add("|");
		procDataList.add("| Expand Shell Element with thickness");
		procDataList.add("|");
		procDataList.add("*expand_reset");
		procDataList.add("*shell_thickness");
		procDataList.add("T");
		procDataList.add("*expand_shells");
		procDataList.add("all_existing");
		procDataList.add("*subdivide_elements");
		procDataList.add("*sub_divisions");
		procDataList.add("1");
		procDataList.add("1");
		procDataList.add("4");
		procDataList.add("*subdivide_elements");
		procDataList.add("all_existing");
		procDataList.add("*sweep_all");
		procDataList.add("*remove_unused_nodes");
		procDataList.add("*renumber_all");
		
		if(LMain.getcomboType().equals(ComboLabel.TYPE1))
			procDataList.add("*py_file_run mp02_redefine_post_set_flat.py");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE2))
			procDataList.add("*py_file_run mp02_redefine_post_set_edgeWave.py");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE3))
			procDataList.add("*py_file_run mp02_redefine_post_set_centerBuckle.py");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE4))
			procDataList.add("*py_file_run mp02_redefine_post_set_singleGutter.py");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE5))
			procDataList.add("*py_file_run mp02_redefine_post_set_partialGutter.py");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE6))
			procDataList.add("*py_file_run mp02_redefine_post_set_doubleGutter.py");
		else if(LMain.getcomboType().equals(ComboLabel.TYPE7))
			procDataList.add("*py_file_run mp02_redefine_post_set_islandGutter.py");
		
		procDataList.add("*py_echo on");
	}
	
	private void writeProc(){
		Writer writer = new Writer(procFilePath,procDataList);
		writer.running();
	}
	
	*/

}
