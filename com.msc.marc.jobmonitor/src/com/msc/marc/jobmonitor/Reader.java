package com.msc.marc.jobmonitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Reader {
	
	private ArrayList<String> fileData= new ArrayList<String>();
	

	private BufferedReader reader;
	private String FilePath = null;
	
	public Reader(String filePath) {
		// TODO Auto-generated constructor stub
		this.FilePath = filePath;
		
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.FilePath),"UTF8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Error - Encoding ");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error - File Not Found");
			e.printStackTrace();
		}
	}
	
	public void readFile(){
		String line = null;
		try {
			while((line=reader.readLine()) != null){
				fileData.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error - Close reader");
				e.printStackTrace();
			}
		}
	}
	
	////////////////////////////////////////////////////////
	// get - set method
	public ArrayList<String> getFileData() {
		return fileData;
	}
	//
	////////////////////////////////////////////////////////
}
