package com.js.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Writer {
	
	//private Logger log = Logger.getLogger(Writer.class);
	
	private BufferedWriter writer;
	private String outFilePath;
	private ArrayList<String> fileDataList;
	
	
	public Writer(String outFilePath, ArrayList<String> fileDataList){
		this.outFilePath = outFilePath;
		this.fileDataList = new ArrayList<String>();
		this.fileDataList = fileDataList;
	}
	
	public void running(){
		//log.info("* START - Write file");
		long start = System.currentTimeMillis();
		initWriter();
		writeFile();
		long end =System.currentTimeMillis();
		//log.info("* End - Write file("+ (end-start)/1000.0 +"sec)");
		//log.info("* Export File Path("+ (end-start)/1000.0 +"sec) : " + this.outFilePath);
		this.fileDataList.clear();
	}
	
	private void initWriter(){
		File fObj = new File(this.outFilePath);
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fObj),"UTF8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//log.error(e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//log.error(e.getMessage());
		}
	}
	
	public void running_bat(){
		initWriter_bat();
		writeFile();
		this.fileDataList.clear();
	}
	
	public void initWriter_bat(){
		File fObj = new File(this.outFilePath);
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fObj)));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//log.error(e.getMessage());
		}
	}
	
	private void writeFile(){
		try {
			for(String line : this.fileDataList){
				writer.write(line);
				writer.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//log.error(e.getMessage());
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//log.error(e.getMessage());
			}
		}
	}
	
}
