package com.js.ens.leveller.proc;

import java.util.ArrayList;

import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.io.Writer;
import com.js.util.myUtil;

public class m1_a_centerBuckle {

	private LevellerMain LMain = LevellerMain.getInstatnce();
	private ArrayList<String> procDataList;
	private String procFilePath;
	
	private String plateType;
	
	public m1_a_centerBuckle(){
		plateType = LMain.getcomboType();
	}
	
	public m1_a_centerBuckle(String path){
		this.procFilePath = path;
		plateType = LMain.getcomboType();
	}
	
	public void running(){
		//
		procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "m1_a_centerBuckle.proc");
		copyProc();
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
		String sourcePath = myUtil.setPath(s_folder, "m1_a_centerBuckle.proc");
		
		myUtil.fileCopy(sourcePath, procFilePath);
	}
	

	/*
	public void createProcData(){
		procDataList = new ArrayList<String>();
		procDataList.add("|");
		procDataList.add("| Start of Create Post Sections & Points sets");
		procDataList.add("|");
		procDataList.add("|");
		procDataList.add("|   Create Surfaces for finding Trans & Longi path points ");
		procDataList.add("|");
		procDataList.add("*set_surface_type quad");
		procDataList.add("*add_surfaces");
		procDataList.add("point(0+L/100,-T*50,0)");
		procDataList.add("point(0+L/100,T*50,0)");
		procDataList.add("point(-L-L/100,T*50,0)");
		procDataList.add("point(-L-L/100,-T*50,0)");
		procDataList.add("*duplicate_reset");
		procDataList.add("*set_duplicate_translation z W/4");
		procDataList.add("*set_duplicate_repetitions");
		procDataList.add("4");
		procDataList.add("*duplicate_surfaces");
		procDataList.add("3");
		procDataList.add("#");
		procDataList.add("*add_surfaces");
		procDataList.add("point(0,-T*50,0-W/100)");
		procDataList.add("point(0,T*50,0-W/100)");
		procDataList.add("point(0,T*50,W+W/100)");
		procDataList.add("point(0,-T*50,W+W/100)");
		procDataList.add("*duplicate_reset");
		procDataList.add("*set_duplicate_translation x -L/4");
		procDataList.add("*set_duplicate_repetitions");
		procDataList.add("4");
		procDataList.add("*duplicate_surfaces");
		procDataList.add("8");
		procDataList.add("#");
		procDataList.add("| Define Section Set for Post Processing");
		procDataList.add("|");
		procDataList.add("*select_method_surface_dist");
		procDataList.add("*set_select_distance");
		procDataList.add("T/1.9");
		procDataList.add("*select_clear");
		procDataList.add("*select_nodes");
		
		procDataList.add("3");
		procDataList.add("*select_elements_nodes");
		procDataList.add("all_selected");
		procDataList.add("*store_elements sec_lle");
		procDataList.add("all_selected");
		procDataList.add("*select_clear");
		procDataList.add("*select_nodes");
		
		procDataList.add("4");
		procDataList.add("*select_elements_nodes");
		procDataList.add("all_selected");
		procDataList.add("*store_elements sec_llq");
		procDataList.add("all_selected");
		procDataList.add("*select_clear");
		procDataList.add("*select_nodes");
		
		procDataList.add("5");
		procDataList.add("*select_elements_nodes");
		procDataList.add("all_selected");
		procDataList.add("*store_elements sec_lc");
		procDataList.add("all_selected");
		procDataList.add("*select_clear");
		procDataList.add("*select_nodes");
		
		procDataList.add("6");
		procDataList.add("*select_elements_nodes");
		procDataList.add("all_selected");
		procDataList.add("*store_elements sec_lrq");
		procDataList.add("all_selected");
		procDataList.add("*select_clear");
		procDataList.add("*select_nodes");
		
		procDataList.add("7");
		procDataList.add("*select_elements_nodes");
		procDataList.add("all_selected");
		procDataList.add("*store_elements sec_lre");
		procDataList.add("all_selected");
		procDataList.add("*select_clear");
		procDataList.add("*select_nodes");
		
		procDataList.add("8");
		procDataList.add("*select_elements_nodes");
		procDataList.add("all_selected");
		procDataList.add("*store_elements sec_tf");
		procDataList.add("all_selected");
		procDataList.add("*select_clear");
		procDataList.add("*select_nodes");
		
		procDataList.add("9");
		procDataList.add("*select_elements_nodes");
		procDataList.add("all_selected");
		procDataList.add("*store_elements sec_tfq");
		procDataList.add("all_selected");
		procDataList.add("*select_clear");
		procDataList.add("*select_nodes");
		
		procDataList.add("10");
		procDataList.add("*select_elements_nodes");
		procDataList.add("all_selected");
		procDataList.add("*store_elements sec_tc");
		procDataList.add("all_selected");
		procDataList.add("*select_clear");
		procDataList.add("*select_nodes");
		
		procDataList.add("11");
		procDataList.add("*select_elements_nodes");
		procDataList.add("all_selected");
		procDataList.add("*store_elements sec_trq");
		procDataList.add("all_selected");
		procDataList.add("*select_clear");
		procDataList.add("*select_nodes");
		
		procDataList.add("12");
		procDataList.add("*select_elements_nodes");
		procDataList.add("all_selected");
		procDataList.add("*store_elements sec_tre");
		procDataList.add("all_selected");
		procDataList.add("*select_clear");
		procDataList.add("*select_nodes");
		
		procDataList.add("*set_select_distance");
		procDataList.add("0.01");
		procDataList.add("*select_method_single");
		procDataList.add("*set_relative_tol");
		procDataList.add("0.1");
		procDataList.add("*set_surfint_trim1 on");
		procDataList.add("|");
		procDataList.add("| Create Curve for longitudinal direction");
		procDataList.add("|");
		procDataList.add("*intersect_surface");
		procDataList.add("3");
		procDataList.add("1");
		procDataList.add("4");
		procDataList.add("1");
		procDataList.add("5");
		procDataList.add("2");
		procDataList.add("6");
		procDataList.add("2");
		procDataList.add("7");
		procDataList.add("2");
		procDataList.add("");
		procDataList.add("|");
		procDataList.add("| Create Curve for Transverse direction");
		procDataList.add("|");
		procDataList.add("*intersect_surface");
		procDataList.add("8");
		procDataList.add("1");
		procDataList.add("8");
		procDataList.add("2");
		procDataList.add("9");
		procDataList.add("1");
		procDataList.add("9");
		procDataList.add("2");
		procDataList.add("10");
		procDataList.add("1");
		procDataList.add("10");
		procDataList.add("2");
		procDataList.add("11");
		procDataList.add("1");
		procDataList.add("11");
		procDataList.add("2");
		procDataList.add("12");
		procDataList.add("1");
		procDataList.add("12");
		procDataList.add("2");
		procDataList.add("@set($convert_entities,curves)");
		procDataList.add("@set($convert_curves_method,convert_curves)");
		procDataList.add("*set_convert_uvdiv u");
		procDataList.add("4");
		procDataList.add("*convert_curves");
		procDataList.add("1 to 5");
		procDataList.add("# | End of List");
		
		procDataList.add("*set_convert_uvdiv u");
		procDataList.add("2");
		procDataList.add("*convert_curves");
		procDataList.add("6 to 15");
		procDataList.add("# | End of List");
		
		procDataList.add("*sweep_nodes");
		procDataList.add("all_visible");
		procDataList.add("*clear_geometry");
		procDataList.add("*select_elements_class line2");
		procDataList.add("*visible_selected");
		procDataList.add("@set($convert_entities,edges)");
		procDataList.add("*edge_curves");
		procDataList.add("all_visible");
		procDataList.add("*remove_elements");
		procDataList.add("all_visible");
		procDataList.add("*sweep_points");
		procDataList.add("all_visible");
		procDataList.add("*renumber_all");
		procDataList.add("|");
		procDataList.add("| End of Create Post Sections & Points sets");
		procDataList.add("|");
		
	}
	
	private void writeProc(){
		Writer writer = new Writer(procFilePath,procDataList);
		writer.running();
	}
	// */
}
