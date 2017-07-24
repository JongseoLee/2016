package com.msc.marc.jobmonitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class myUtil {
	// Get current Time 
	public static String getCurrentTime(){
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyyMMddHHmmss" );
		Date currentTime = new Date ( );
		String dTime = formatter.format (currentTime);
		return dTime;
	}
	
	// Check value
	public static boolean CheckDoubleValue(String inputData){
		boolean result = false;
		try{
			if (!inputData.isEmpty()){
				double value = Double.parseDouble(inputData);
				if (value > 0){
					result = true;	
				}else {
					result = false;
					JOptionPane.showMessageDialog(null, "Input Data must be greater than '0'. ", "Input Data error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}catch(Exception e){
			//JOptionPane.showMessageDialog(null, "Input Data is not String", "Input Data error", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Input Data must be greater than '0'. ", "Input Data error", JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	// Check OS
	public static String checkOS(){
		String os = null;
		String osName = System.getProperty("os.name");
		if(osName.toLowerCase().contains("win")){
			os = "window";
		}else {
			os = "linux";
		}
		return os;
	}

	public static List<String> splitData(String line,String token){
		List<String> result = new ArrayList<String>();

		String[] arr = line.split(token);

		for(int i=0; i<arr.length;i++){
			if(arr[i].length() !=0){
				result.add(arr[i]);
			}
		}

		return result;
	}


	// Change char to Sting 
	public static String change_charToString(char _c){
		char ch = _c;
		Character cr = new Character(ch);
		String str = cr.toString();
		return str;
	}

	// ArrayList print data 
	public static void printArrData(ArrayList<String> ArrData){
		for (int i = 0; i<ArrData.size();i++){
			System.out.println("index = "+i+" || value = "+ArrData.get(i));
		}
	}
	// Map Data prit data
	public static void printMapData(Map <String,String> mapData){
		System.out.println("----------------------------------------------------------------");
		Iterator<String> iterator = mapData.keySet().iterator();
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			System.out.print("Key = "+key);
			System.out.println(" || Value = "+ mapData.get(key));

		}
	}
	// Check float value
	public static boolean isFloatValue(String value){
		try{
			Float.parseFloat(value);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	// Check double Value
	public static boolean isDouble(String inputData){
		boolean result = false;
		try{
			if (!inputData.isEmpty()){
				double value = Double.parseDouble(inputData);
				result = true;
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Input Data is not String", "Input Data error", JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	// check path
	public static boolean checkPath(String path){
		File f =new File(path);
		return f.exists();
	}

	// set Path
	public static String setPath(String currentPath, String FolderName){
		return currentPath+File.separator+FolderName;
	}	

	// make folder 
	public static Boolean makeDir(String folderName){
		boolean result = false;
		File folder = new File(folderName);
		if(!folder.exists()){
			folder.mkdirs();
			//System.out.println("Success - mkdir");
			result = true; 
		}
		return result;
	}
	// file list from dirPath
	public static List<File> getDirFileList(String dirPath){
		List<File> dirFileList = null;

		File dir = new File(dirPath);

		if (dir.exists()){
			File[] files = dir.listFiles();
			dirFileList = Arrays.asList(files);
		}
		return dirFileList;
	}

	public static ArrayList<File> findFiles(String dirPath, String token){
		ArrayList<File> fileList = new ArrayList<File>();
		List<File> dirFileList = null;
		File dir = new File(dirPath);

		if(dir.exists()){
			File [] files = dir.listFiles();
			dirFileList = Arrays.asList(files);

			for(int i=0;i<dirFileList.size();i++){
				if(dirFileList.get(i).isFile()){
					if(dirFileList.get(i).getName().contains(".userMenu")){
						//System.out.println(dirFileList.get(i).getName());
						fileList.add(dirFileList.get(i));
					}
				}
			}
		}
		return fileList;
	}


	public static Boolean fileIsLive(String isLivefile) {
		File f1 = new File(isLivefile);
		if(f1.exists()) return true;
		else return false;		  
	}

	public static void fileMake(String makeFileName) {
		File f1 = new File(makeFileName);
		try {
			f1.createNewFile();
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void fileDelete(String deleteFileName) {
		File I = new File(deleteFileName);
		I.delete();
	}

	public static void fileCopy(String inFileName, String outFileName) {
		try{
			FileInputStream inputStream = new FileInputStream(inFileName);         
			FileOutputStream outputStream = new FileOutputStream(outFileName);
			  
			FileChannel fcin =  inputStream.getChannel();
			FileChannel fcout = outputStream.getChannel();
			  
			long size = fcin.size();
			fcin.transferTo(0, size, fcout);
			  
			fcout.close();
			fcin.close();
			  
			outputStream.close();
			inputStream.close();
		}catch(Exception e){
			
		}
		
		/* 
		 * old version
		FileInputStream fis = null;
		FileOutputStream fos = null;
		File fisf = new File(inFileName);
		File fosf = new File(outFileName);
		try {
			fis = new FileInputStream(fisf);
			fos = new FileOutputStream(fosf);

			int data = 0;
			while((data=fis.read())!=-1) {
				fos.write(data);
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				fis.close();
			}catch (Exception e){
				e.printStackTrace();
			}
			try{
				fos.close();
			}catch (Exception e){
				e.printStackTrace();
			}
			fosf.setExecutable(true);
		}
		//*/
	}

	public static void fileMove(String inFileName, String outFileName) {
		try {
			FileInputStream fis = new FileInputStream(inFileName);
			FileOutputStream fos = new FileOutputStream(outFileName);

			int data = 0;
			while((data=fis.read())!=-1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

			//fileDelete(inFileName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean deleteDirectory(File targetPath){
		//boolean result = false;
		if(!targetPath.exists()){
			return false;
		}
		File[] files = targetPath.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				deleteDirectory(file);
			} else {
				file.delete();
			}
		}

		return targetPath.delete();
	}
}
