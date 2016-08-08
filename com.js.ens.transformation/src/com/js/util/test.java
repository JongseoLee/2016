package com.js.util;

import java.io.File;
import java.util.ArrayList;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.js.io.Reader;
import com.js.io.Writer;
import com.js.parser.ParserDefault;


public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}
	
	public void copyAndSwap(String oriPath, String destPath){
		myUtil.fileCopy(oriPath, destPath);
		ArrayList<String> fileDataList = new ArrayList<String>();
		ArrayList<String> newFileDataList = new ArrayList<String>();
		
		if(myUtil.checkPath(destPath)){
			Reader Robj = new Reader(destPath);
			Robj.running();
			fileDataList = Robj.getFileDataList();
			
			String newLine = "";
			for(String line : fileDataList){
				if(line.contains("%jsclubb%")){
					newLine = line.replace("%jsclubb%", "JongseoLee");
					newFileDataList.add(newLine);
				}else{
					newFileDataList.add(line);
				}
			}
			
			Writer Wobj = new Writer(destPath, newFileDataList);
			Wobj.running();
			
			System.out.println("END");
		}else{
			System.out.println("Copy Error");
		}
		
		
		
	}
	
	public void setFileList(String folder,String path){
		ArrayList<String> fList = new ArrayList<String>();
		
		for(File f : myUtil.getDirFileList(folder)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		
		Writer obj = new Writer(path,fList);
		obj.running();
	}
	
	public void setFileList(){
	
		System.out.println("path : "+System.getProperty("user.dir"));
		String f1 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f1";
		String f1rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f1\\rolls";
		String f2 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f2";
		String f2rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f2\\rolls";
		String f3 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f3";
		String f3rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f3\\rolls";
		String f4 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f4";
		String f4rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f4\\rolls";
		String f5 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f5";
		String f5rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f5\\rolls";
		String f6 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f6";
		String f6rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f6\\rolls";
		String f7 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f7";
		String f7rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f7\\rolls";
		
		String f1Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f1.filelist";
		String f2Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f2.filelist";
		String f3Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f3.filelist";
		String f4Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f4.filelist";
		String f5Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f5.filelist";
		String f6Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f6.filelist";
		String f7Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f7.filelist";
		
		ArrayList<String> fList = new ArrayList<String>();
		
		for(File f : myUtil.getDirFileList(f1)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f1rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj = new Writer(f1Name,fList);
		obj.running();
		fList.clear();
		
		for(File f : myUtil.getDirFileList(f2)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f2rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj2 = new Writer(f2Name,fList);
		obj2.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f3)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f3rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj3 = new Writer(f3Name,fList);
		obj3.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f4)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f4rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj4 = new Writer(f4Name,fList);
		obj4.running();
		fList.clear();
		
		
		
		for(File f : myUtil.getDirFileList(f5)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f5rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj5 = new Writer(f5Name,fList);
		obj5.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f6)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f6rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj6 = new Writer(f6Name,fList);
		obj6.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f7)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f7rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj7 = new Writer(f7Name,fList);
		obj7.running();
		fList.clear();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		String str = "|\t\t\t|";
		System.out.println(str);
		String newStr = str.replace("\t", "a");
		System.out.println(newStr);
		 */
		
		/*
		 * 문자열 수식 계산
		 
		String eq = "#p_len# * #p_thk# /( #roll_gap# * #pl_vel_mpm# * 1000.0 / 60.0 ) * #ltime_scale#";
		String eq2 = "10 * 10 /( 2 * 5 * 1000.0 / 1000.0 ) * 1 * Math.PI";
		//double result =  
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		try {
			System.out.println("Result : "+ engine.eval(eq2));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		String outPutFile2D = "D:\\8____내문서\\workspace\\2016\\com.js.ens.leveller\\module\\filelist\\2D";
		String outPutFile3D = "D:\\8____내문서\\workspace\\2016\\com.js.ens.leveller\\module\\filelist\\3D";
		String path2D = "D:\\8____내문서\\workspace\\2016\\com.js.ens.leveller\\module\\2D";
		String path3D = "D:\\8____내문서\\workspace\\2016\\com.js.ens.leveller\\module\\3D";
		
		String f1_3D = myUtil.setPath(outPutFile3D, "1_Flat.filelist");
		String f2_3D = myUtil.setPath(outPutFile3D, "2_EdgeWave.filelist");
		String f3_3D = myUtil.setPath(outPutFile3D, "3_CenterWave.filelist");
		String f4_3D = myUtil.setPath(outPutFile3D, "4_SingleGutter.filelist");
		String f5_3D = myUtil.setPath(outPutFile3D, "5_PartialGutter.filelist");
		String f6_3D = myUtil.setPath(outPutFile3D, "6_DoubleGutter.filelist");
		String f7_3D = myUtil.setPath(outPutFile3D, "7_IslandGutter.filelist");
		
		String path3D_f1 = myUtil.setPath(path3D, "1_Flat");
		String path3D_f2 = myUtil.setPath(path3D, "2_Wave");
		String path3D_f3 = myUtil.setPath(path3D, "3_CenterWave");
		String path3D_f4 = myUtil.setPath(path3D, "4_SingleGutter");
		String path3D_f5 = myUtil.setPath(path3D, "5_PartialGutter");
		String path3D_f6 = myUtil.setPath(path3D, "6_DoubleGutter");
		String path3D_f7 = myUtil.setPath(path3D, "7_IslandGutter");
		
		
		
		String f1_2D = myUtil.setPath(outPutFile2D, "1_Flat.filelist");
		String f2_2D = myUtil.setPath(outPutFile2D, "2_Wave.filelist");
		String f3_2D = myUtil.setPath(outPutFile2D, "3_Curl.filelist");
		
		String path2D_f1 = myUtil.setPath(path2D, "1_Flat");
		String path2D_f2 = myUtil.setPath(path2D, "2_Wave");
		String path2D_f3 = myUtil.setPath(path2D, "3_Curl");
		
	
		
		test obj = new test();
		/*  //office
		obj.setFileList(path3D_f1, f1_3D);
		obj.setFileList(path3D_f2, f2_3D);
		obj.setFileList(path3D_f3, f3_3D);
		obj.setFileList(path3D_f4, f4_3D);
		obj.setFileList(path3D_f5, f5_3D);
		obj.setFileList(path3D_f6, f6_3D);
		obj.setFileList(path3D_f7, f7_3D);
		// */
		obj.setFileList(path2D_f1, f1_2D);
		obj.setFileList(path2D_f2, f2_2D);
		obj.setFileList(path2D_f3, f3_2D);
		
		
		/*
		// Copy -> Swap LevellerVer3 Test ProcGen
		String oriPath = "G:\\06.ENS2_2016\\workspace\\2016\\com.js.ens.leveller\\module\\2D\\main_flat.proc";
		String destPath = "G:\\06.ENS2_2016\\workspace\\2016\\com.js.ens.leveller\\module\\2D\\new\\main_flat.proc";
		obj.copyAndSwap(oriPath,destPath);
		//*/
	}
	
}
