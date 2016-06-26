package com.js.ens.transformation.proc;

import java.util.ArrayList;

import com.js.ens.transformation.core.MainController;
import com.js.ens.transformation.core.UILabel;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class GenProc_00 {
	private MainController MC = MainController.getInstance();
	private String configFolder = myUtil.setPath(System.getProperty("user.dir"),"userConfig");
	private String userModuleFolder = myUtil.setPath(System.getProperty("user.dir"), "userModule");
	private String destFolder;
	private String modelName;
	private String newFileName;
	private ArrayList<String> ori_fileDataList = new ArrayList<String>();
	private ArrayList<String> procDataList = new ArrayList<String>();
	
	public GenProc_00() {
		// TODO Auto-generated constructor stub
		this.modelName = MC.getModelName();
		
	}
	
	public void genProc00F1(String destFolder){
		this.newFileName = "00_main_"+this.modelName+"_gen_f1.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(userModuleFolder, "f1"),"00_main_model_gen_f1.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), "proc"),UILabel.F1),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
	}
	
	
	public void genProc00F2(String destFolder){
		this.newFileName = "00_main_"+this.modelName+"_gen_f2.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(userModuleFolder, "f2"),"00_main_model_gen_f2.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), "proc"),UILabel.F2),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
	}

	public void genProc00F3(String destFolder){
		this.newFileName = "00_main_"+this.modelName+"_gen_f3.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(userModuleFolder, "f3"),"00_main_model_gen_f3.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), "proc"),UILabel.F3),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
	}
	
	public void genProc00F4(String destFolder){
		this.newFileName = "00_main_"+this.modelName+"_gen_f4.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(userModuleFolder, "f4"),"00_main_model_gen_f4.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), "proc"),UILabel.F4),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
	}
	
	public void genProc00F5(String destFolder){
		this.newFileName = "00_main_"+this.modelName+"_gen_f5.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(userModuleFolder, "f5"),"00_main_model_gen_f5.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), "proc"),UILabel.F5),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
	}
	
	public void genProc00F6(String destFolder){
		this.newFileName = "00_main_"+this.modelName+"_gen_f6.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(userModuleFolder, "f6"),"00_main_model_gen_f6.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), "proc"),UILabel.F6),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
	}
	
	public void genProc00F7(String destFolder){
		this.newFileName = "00_main_"+this.modelName+"_gen_f7.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(userModuleFolder, "f7"),"00_main_model_gen_f7.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), "proc"),UILabel.F7),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
	}
	
	private void readSourceData(String ori_filePath){
		Reader reader = new Reader(ori_filePath);
		reader.running();
		this.ori_fileDataList=reader.getFileDataList();
		
	}
	
	private void swapValue(){
		for(String line :this.ori_fileDataList){
			if(line.contains("%model%")){
				String newLien = line.replace("%model%", this.modelName);
				procDataList.add(newLien);
						
			}else{
				procDataList.add(line);
			}
		}
	}
	
	private void writeProc(String destFolder){
		Writer obj = new Writer(destFolder, procDataList);
		obj.running();
	}
}
