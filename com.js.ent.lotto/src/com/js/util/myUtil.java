package com.js.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class myUtil {
	
	public static String checkOS(){
		String os = null;
		String osName = System.getProperty("os.name");
		if(osName.toLowerCase().contains("win")){
			os="window";
		}else {
			os="linus";
		}
		return os;
	}
	
	public static boolean isFloatValue(String value){
		try{
			Float.parseFloat(value);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean checkPath(String path){
		File f =new File(path);
		return f.exists();
	}
	
	public static String setPath(String currentPath, String FolderName){
		return currentPath+File.separator+FolderName;
	}
	
	public static String getParrentPath(String path){
		String resultPath = null; 
		File f = new File(path);
		resultPath = f.getParent();
		f = null;
		return resultPath;
	}
	
	public static String getFileName(String path){
		String resultPath = null;
		File f = new File(path);
		resultPath = f.getName();
		f = null;
		return resultPath;
	}
	
	public static void printArrayList(ArrayList<String> list){
		System.out.println("=================================");
		for(String str:list){
			System.out.println(str);
		}
		System.out.println("=================================");
	}
	/*
	public static void printMap(Map<String,object> map){
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object value = map.get(key);
			System.out.printf("key : |%s| value : |%s| %n", key, value);
		}
	}
	*/
	public static void printMap(Map<String,Integer> map){
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			int value = map.get(key);
			System.out.printf("key : |%s| value : |%i| %n", key, value);
		}
	}
	
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
		*/
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
	
	public static List<File> getDirFileList(String dirPath){
		List<File> dirFileList = null;
	  
		File dir = new File(dirPath);
		  
		if (dir.exists()){
			File[] files = dir.listFiles();
		   
			dirFileList = Arrays.asList(files);
		}
		return dirFileList;
	}
}
