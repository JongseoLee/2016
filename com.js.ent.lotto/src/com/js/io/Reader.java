package com.js.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;



public class Reader {
	
	private BufferedReader reader;
	private String inFilePath; 
	private ArrayList<String> fileDataList;
	
	
	public Reader(String inFilePath){
		this.inFilePath = inFilePath;
	}
	
	public void running(){
		//log.info("* START - Read file");
		//log.info("* File Path : " + this.inFilePath);
		long start = System.currentTimeMillis();
		initReader();
		readFile();
		long end =System.currentTimeMillis();
		//log.info("* Read file path("+ (end-start)/1000.0 +"sec) : "+ this.inFilePath);
	}
	
	private void initReader(){
		File fObj = new File(this.inFilePath);
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fObj),"UTF8"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//log.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//log.error(e.getMessage());
		}
	}
	
	private void readFile(){
		String line = null;
		fileDataList = new ArrayList<String>();
		
		try {
			while((line = reader.readLine())!=null){
				if(line.length() !=0 ){
					fileDataList.add(line);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//log.error(e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//log.error(e.getMessage());
			}
		}
	}
	
	public ArrayList<String> getFileDataList() {
		return fileDataList;
	}
	
}
