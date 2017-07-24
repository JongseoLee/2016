package com.msc.marc.jobmonitor;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Writer {
	private ArrayList<String> FileData = new ArrayList<String>();
	private String outputFilePath = null;	//table_acc.bdf
	BufferedWriter writer;
	
	public Writer(ArrayList<String> fileData,String outputFilePath) {
		// TODO Auto-generated constructor stub
		this.FileData = fileData;
		this.outputFilePath = outputFilePath;
		
		//System.out.println("size : "+fileData.size());
		//System.out.println("outputFilePath : "+outputFilePath);
		
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.outputFilePath),"UTF8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error - IO 1 ");
			e.printStackTrace();
		}
	}
	
	public void writeFile() {
		for(int i=0;i<FileData.size();i++){
			try {
				writer.write(FileData.get(i));
				writer.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error - IO 2 ");
				e.printStackTrace();
			} 
		}
		
		
		
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error - IO 3");
			e.printStackTrace();
		}
		
		
		
	}
		
}
