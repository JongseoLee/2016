package com.msc.marc.jobmonitor;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

public class Job {
	
	private static Job instance = new Job();
	public static Job getInstance(){
		return instance;
	}
	
	
	private int status;
	private double d_status;
	private String step = "(inc: / )";
	private String exitNum;
	
	public String getExitNum() {
		return exitNum;
	}
	public void setExitNum(String exitNum) {
		this.exitNum = exitNum;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
		//System.out.println("Job : "+status+"%");
	}
	public double getD_status() {
		return d_status;
	}
	public void setD_status(double d_status) {
		this.d_status = d_status;
	}
	
	

}
