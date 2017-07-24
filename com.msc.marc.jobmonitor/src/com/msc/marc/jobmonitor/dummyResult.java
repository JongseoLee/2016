package com.msc.marc.jobmonitor;

import java.util.ArrayList;

public class dummyResult implements Runnable {
	
	private String resultFilePath;
	private String dummyFilePath;
	private ArrayList<String> d_dataList;
	
	
	public dummyResult(String resultFilePath,String dummyFilePath ) {
		// TODO Auto-generated constructor stub
		this.resultFilePath = resultFilePath;
		this.dummyFilePath = dummyFilePath;
	}
	
	public dummyResult(String resultFilePath ) {
		// TODO Auto-generated constructor stub
		this.resultFilePath = resultFilePath;
	}
		
	public void running(){
		readDummyFile();
		writeDummyFile();
	}
	
	public void readDummyFile(){
		String d_file = "D:\\CodeFactory\\Java_workspace\\com.msc.marc.jobmonitor\\dummy_w3500t50.sts";
		Reader reader = new Reader(d_file);
		reader.readFile();
		d_dataList = reader.getFileData();
	}
	
	public void writeDummyFile(){
		
		ArrayList<String> tempDataList = new ArrayList<String>();
		
		//System.out.println("Start --- DummyResult");
		for(int i = 0;i<d_dataList.size();i++){
			tempDataList.add(d_dataList.get(i));
			
			try {
				int sleepTime = (int) ((Math.random()*10000)+1000)%2;
				//System.out.println("sleepTime : " + sleepTime);
				Thread.sleep(sleepTime*1000);
				
				Writer writer = new Writer(tempDataList, this.resultFilePath);
				writer.writeFile();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Error - Sleep ");
				e.printStackTrace();
			}
		}
		//System.out.println("End --- DummyResult");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		running();
	}
	
	
	
	
	

}
