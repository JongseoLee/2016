package com.msc.marc.jobmonitor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class CheckProgress implements Runnable{
//public class CheckProgress extends Thread{

	private String resultFilePath;
	private int maxInc;
	private int readFileInterval;
	private RandomAccessFile rf;
	private Job jObj = null;
	
	
	public CheckProgress(String resultFilePath, int maxInc,int interval) {
		// TODO Auto-generated constructor stub
		//System.out.println("sssssss");
		this.resultFilePath = resultFilePath;
		this.maxInc = maxInc;
		this.readFileInterval = interval;
		this.jObj = Job.getInstance();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		long linePosition = 0;
		String incNum = "0";
		boolean flag = true;
		
		
		// 0 : file position || 1 : inc || 2: line || 3 : monitorStatus(running or done)
		ArrayList<String> resultList = new ArrayList<String>();
		
		//System.out.println("# Start"+"\n");
		
		
		while(flag){
			try {
				Thread.sleep(this.readFileInterval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(myUtil.fileIsLive(resultFilePath)){
				
				try {
					rf = new RandomAccessFile(this.resultFilePath,"r");
					rf.seek(linePosition);
					resultList = readLines(rf);
					
					linePosition = Long.parseLong(resultList.get(0));
					if(!resultList.get(1).equals("null")){
						if(!incNum.equals(resultList.get(1))){
							incNum = resultList.get(1);
							//System.out.println("INC : " +incNum);
							
							int status = (int) Math.round((Double.parseDouble(incNum) / this.maxInc) * 100.0);
							// 소수점 2째 자리 표현
							double d_status = Math.round((Double.parseDouble(incNum) / this.maxInc)*10000) / 100.0;
							//System.out.println(d_status);
							System.out.println("# Progress (inc: "+incNum+ "/"+this.maxInc+")");
							String step = "(inc: "+incNum+ "/"+this.maxInc+")";
							jObj.setStatus(status);
							jObj.setStep(step);
							jObj.setD_status(d_status);
							
							/*
							String line = "";
							if(status > 0 && status<=5){
								line = "> □□□□□□□□□□□□□□□□□□□□";
							}else if(status > 5 && status <=10 ){
								line = "> ■□□□□□□□□□□□□□□□□□□□";
							}else if(status > 10 && status <=15 ){
								line = "> ■■□□□□□□□□□□□□□□□□□□";
							}else if(status > 15 && status <=20 ){
								line = "> ■■■□□□□□□□□□□□□□□□□□";
							}else if(status > 20 && status <=25 ){
								line = "> ■■■■□□□□□□□□□□□□□□□□";
							}else if(status > 25 && status <=30 ){
								line = "> ■■■■■□□□□□□□□□□□□□□□";
							}else if(status > 30 && status <=35 ){
								line = "> ■■■■■■□□□□□□□□□□□□□□";
							}else if(status > 35 && status <=40 ){
								line = "> ■■■■■■■□□□□□□□□□□□□□";
							}else if(status > 40 && status <=45 ){
								line = "> ■■■■■■■■□□□□□□□□□□□□";
							}else if(status > 45 && status <=50 ){
								line = "> ■■■■■■■■■□□□□□□□□□□□";
							}else if(status > 50 && status <=55 ){
								line = "> ■■■■■■■■■■□□□□□□□□□□";
							}else if(status > 55 && status <=60 ){
								line = "> ■■■■■■■■■■■□□□□□□□□□";
							}else if(status > 60 && status <=65 ){
								line = "> ■■■■■■■■■■■■□□□□□□□□";
							}else if(status > 65 && status <=70 ){
								line = "> ■■■■■■■■■■■■■□□□□□□□";
							}else if(status > 70 && status <=75 ){
								line = "> ■■■■■■■■■■■■■■□□□□□□";
							}else if(status > 75 && status <=80 ){
								line = "> ■■■■■■■■■■■■■■■□□□□□";
							}else if(status > 80 && status <=85 ){
								line = "> ■■■■■■■■■■■■■■■■□□□□";
							}else if(status > 85 && status <=90 ){
								line = "> ■■■■■■■■■■■■■■■■■□□□";
							}else if(status > 90 && status <=95 ){
								line = "> ■■■■■■■■■■■■■■■■■■□□";
							}else if(status > 95 && status <=100 ){
								line = "> ■■■■■■■■■■■■■■■■■■■□";
							}
							line += "   "+ status+"%";
							//System.out.println(line);
							// */
						}
					}
					rf.close();
					
					if(resultList.get(3).equals("done")){
						//myUtil.printArrData(resultList);
						//Job ends with exit number :    3004
						ArrayList<String> tempList = new ArrayList<String>();
						tempList = (ArrayList<String>) ParserDefault.splitLineData(resultList.get(2)," ");
						
						int status = (int) Math.round((this.maxInc / this.maxInc) * 100.0);
						String step = "(inc: "+this.maxInc+ "/"+this.maxInc+")";
						String exitNum = tempList.get(6);
						
						jObj.setStatus(status);
						jObj.setStep(step);
						jObj.setExitNum(exitNum);
						
						flag = false;
					}
					
				}catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		//System.out.println("ss");
		
		//JOptionPane.showMessageDialog(null, "Solving Complete.","Job Monitor",JOptionPane.INFORMATION_MESSAGE);
		//System.exit(0);
	}
	
	public ArrayList<String> readLines(RandomAccessFile rf) throws IOException{
		ArrayList<String> resultList = new ArrayList<String>();
		
		long recnum = 1;
		String filePointer;
		String incNum = "null";
		String line;
		String MonitorStatus = "running";
		
		
		while((line = rf.readLine())!= null){
			//System.out.println("Line " + recnum + " : " + line);
			incNum = parsingInc(line);	// value => null or incNum(1,2,3...)
			
			if(endFile(line)){
				//System.out.println("Line data contains Job End");
				MonitorStatus = "done";
				break;
			}
		}
		
		filePointer = String.valueOf(rf.getFilePointer());
		
		resultList.add(filePointer);
		resultList.add(incNum);
		resultList.add(line);
		resultList.add(MonitorStatus);
		
		return resultList;
	}
	
	public String parsingInc(String line){
		String result = "null";
		ArrayList<String> splitDataList = new ArrayList<String>();
		splitDataList = (ArrayList<String>) ParserDefault.splitLineData(line," ");
		try{
			if(splitDataList.size() == 13){
				if(isInteger(splitDataList.get(1))){
					result = splitDataList.get(1);
				}
			}else{
				//System.out.println("no line");
			}
		}catch(Exception e){
			
		}
		return result;
	}
		 
	
	public boolean isInteger(String data){
		boolean result = false;
		try{
			Integer.parseInt(data);
			result = true;
		} catch (NumberFormatException nfe) {
			result = false;
		}
		return result;
	}
	
	public boolean endFile(String line){
		boolean result = false;
		if(line != null){
			if(line.contains("Job ends")){
				result = true;
			}
		}
		return result;
	}

}
