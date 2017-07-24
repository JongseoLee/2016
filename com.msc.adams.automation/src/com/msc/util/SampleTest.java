package com.msc.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.io.Writer;

public class SampleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		String path = "D:\\zsample\\test.mnf";
		System.out.println(path);
		File f = new File(path);
		System.out.println(f.getName());
		String fileName = f.getName();
		for(String str : fileName.split("\\.")){
			System.out.println(str);
		}
		//*/
		
		/*
		String path = myUtil.setPath("D:\\", "abc");
		String path2 = myUtil.setPath(path, "a");
		String path3 = path2.replace("\\", "/");
		
		System.out.println(path);
		System.out.println(path2);
		System.out.println(path3);
		// */ 
		
		/*
		String str = "vflex make rigid rigid_part=%PartName% flex_body=%PartName%_flex";
		String result = str.replaceAll("%PartName%", "testPart");
		System.out.println(result);
		// */
		
		/*
		String path = "D:\\ztest\\jslee.bdf";
		File f = new File(path);
		System.out.println(f.getParent());
		//*/
		
		/*
		try{
			if(myUtil.checkPath(null)){
				System.out.println("null.");
			}else {
				System.out.println("else" );
			}
		}catch(Exception e){
			System.out.println("Catch ");
		}
		// */
		/*
		String path = "D:\\ztest\\jslee.bdf";
		System.out.println(myUtil.getFileName(path));
		System.out.println(new File(path).getName());
		// */
		/*
		String resultFolder = myUtil.setPath(MC.getWorkspace(),DatabaseFolderName.Result);
		String DACFolder = myUtil.setPath(resultFolder, DatabaseFolderName.DAC);
		String ResultNameFolder = myUtil.setPath(DACFolder, MC.getDBStep4().getExportResultName());
		this.PartNameFolder = myUtil.setPath(ResultNameFolder, this.fsObj.getPartName());
		// */
		
		/*
		int DACFileNum = 0;
		String path = "D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\4\\Result\\4_DAC\\Base\\DR_PIVOT_DRIVER_ARM";
		for(File f : myUtil.getDirFileList(path)){
			System.out.println(f.getAbsolutePath());
			if(isPattern(f.getName())){
				DACFileNum++;
			}
		}
		System.out.println("Result : "+DACFileNum);
		//*/
		/*
		String str = "'NODE_SET1'";
		String result = str.substring(1, str.length()-1);
		System.out.println(result);
		// */
		/*
		int a = 1;
		int b =22;
		int c = 333;
		
		System.out.println(String.format("%04d", a));
		System.out.println(String.format("%04d", b));
		System.out.println(String.format("%04d", c));
		// */
		
		
		/*
		Map<String,String> map = new HashMap<String,String>();
		map.put("CV-FAToMAT_ADC", "C:\\CAESoft\\ECS\\FEMFAT5.2a\\material_database\\HMC-CV_MAToDB\\CV-FAToMAT_ADC.ffd");
		map.put("CV-FAToMAT_SAPH440", "C:\\CAESoft\\ECS\\FEMFAT5.2a\\material_database\\HMC-CV_MAToDB\\CV-FAToMAT_SAPH440.ffd");
		map.put("CV-FAToMAT_SPCC", "C:\\CAESoft\\ECS\\FEMFAT5.2a\\material_database\\HMC-CV_MAToDB\\CV-FAToMAT_SPCC.ffd");
		map.put("CV-FAToMAT_SPHC", "C:\\CAESoft\\ECS\\FEMFAT5.2a\\material_database\\HMC-CV_MAToDB\\CV-FAToMAT_SPHC.ffd");
		
		String m1 = "SAPH440";
		String m2 = "SPCC";
		String m3 = "SPHC";
		
		myUtil.printMapData(map);
		Iterator<String> iterator = map.keySet().iterator();
		String result = "";
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			if(key.contains(m1)){
				result = key;
				break;
			}
			//System.out.print("Key = "+key);
			//System.out.println(" || Value = "+ mapData.get(key));
		}
		
		System.out.println(result);
		System.out.println(map.get(result));
		// */
		
		/*
		String path = "\"D:\\CodeFactory\\Java_workspace\\com.msc.adams.automation\\exe\\Wiper-Linkage Analysis Tool\\msck_Workspace\\test\\Result\\4_DAC\\Base\\DR_ROD\\DR_ROD_bluk.fem\"";
		String newPath = path.replace("\\", "/");
		
		System.out.println(newPath);
		//*/
		
		/*
		String line = "increment_frame_by=(1*( %incrementFrame% !=  ? %incrementFrame% : 1 ))";
		String newLine = line.replace("%incrementFrame%", "TTTT");
		System.out.println(newLine);
		//*/
		/*
		ArrayList<String> list = new ArrayList<String>();
		for(int i=1;i<=100000;i++){
			list.add(String.valueOf(i));
		}
		String path = "D:\\CodeFactory\\CLang\\Search\\data";
		Writer writer = new Writer(path);
		writer.running(list);
		// */
		
		/*
		String path = "DR_PIVOT_DRIVER_ARM.Fixed_DR_PIVOT_DRIVER_ARM_to_DR_BALL_PIN_i.Fx_Local.txt";
		String name = new File(path).getName();
		ArrayList<String> tokens = myUtil.splitData(name, "\\.");
		String shortFileName = tokens.get(0)+"_"+tokens.get(2)+".tbd1";
		
		System.out.println(name);
		myUtil.printArrData(tokens);
		System.out.println(shortFileName);
		
		// */
		/*
		String line1 = "SET     1       ELEM    LIST";
		String line2 = "+       12668   12669";                                                   
		ArrayList<String> tokens1 = new ArrayList<String>();
		ArrayList<String> tokens2 = new ArrayList<String>();
		tokens1 = myUtil.splitData(line1, " ");
		tokens2 = myUtil.splitData(line2, " ");
		String newLine1 = "";
		String newLine2 = "";
		
		for(int i = 0; i<tokens1.size();i++){
			if(i == 0){
				newLine1 = newLine1+"$"+String.format("%-7s", tokens1.get(i));
			}else{
				newLine1 = newLine1+String.format("%-8s", tokens1.get(i));
			}
		}
		for(int i = 0; i<tokens2.size();i++){
			if(i == 0){
				newLine2 = newLine2+"$"+String.format("%-7s", tokens2.get(i));
			}else{
				newLine2 = newLine2+String.format("%-8s", tokens2.get(i));
			}
		}
		
		System.out.println(line1);
		System.out.println(newLine1);
		
		System.out.println(line2);
		System.out.println(newLine2);
		
		// */
		
		
	}
	
	private static boolean isPattern(String fileName){
		String partName = "DR_PIVOT_DRIVER_ARM";
		Pattern p = Pattern.compile("(^"+partName+"_[0-9][0-9][0-9][0-9].dac$)");
		Matcher m = p.matcher(fileName);

		if(m.find()){
			return true;
		}else{
			return false;
		}    
	}

}
