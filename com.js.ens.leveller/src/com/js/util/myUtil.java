package com.js.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class myUtil {
	public static String getCurrentDate(){
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format ( currentTime );
		//System.out.println ( mTime );
		return mTime;
	}
	
	public static String getIPAddress(){
		InetAddress ip = null;
		String IPAddress = "";
		// 로컬 IP취득
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IPAddress = ip.getHostAddress();
		return IPAddress;
	}
	
	public static String getMacAddress(){
		InetAddress ip = null;
		NetworkInterface netif = null;
		String MacAddress = "";
		
		// 로컬 IP취득
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 네트워크 인터페이스 취득
		try {
			netif = NetworkInterface.getByInetAddress(ip);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 네트워크 인터페이스가 NULL이 아니면
		if (netif != null) {
			// 네트워크 인터페이스 표시명 출력
			//System.out.print(netif.getDisplayName() + " : ");

			// 맥어드레스 취득
			byte[] mac = null;
			try {
				mac = netif.getHardwareAddress();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 맥어드레스 출력
			for (byte b : mac) {
				MacAddress = MacAddress + String.format("%02X", b);
			}
		}
		return MacAddress;
	}
	
	
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
	
	public static void printMap(Map<String,String> map){
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object value = map.get(key);
			System.out.printf("key : |%s| value : |%s| %n", key, value);
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
	/*
	public static void fileDelete(String path){
		try{
			File f = new File(path);
			if(f.exists()){
				f.delete();
			}
		}catch(Exception e){
			
		}
	}
	 */
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
