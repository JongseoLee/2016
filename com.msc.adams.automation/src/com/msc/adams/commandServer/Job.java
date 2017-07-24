package com.msc.adams.commandServer;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;

public class Job {
	
	private MainController MC = MainController.getInstance();
	private Mediator med = Mediator.getInstance();
	
	private static Job instance = new Job();
	public static Job getInstance(){
		return instance;
	}
	private String JobStatus_step1;
	private String JobStatus_step4;
	
	public static String start = "start";
	public static String end = "end";
	public static String fail = "fail";
	public static String stop = "stop";

	
	public Job() {
		// TODO Auto-generated constructor stub
	}

	public String getJobStatus_step1() {
		return this.JobStatus_step1;
	}

	public void setJobStatus_step1(String jobStatus_step1) {
		this.JobStatus_step1 = jobStatus_step1;
	}

	public String getJobStatus_step4() {
		return this.JobStatus_step4;
	}

	public void setJobStatus_step4(String jobStatus_step4) {
		this.JobStatus_step4 = jobStatus_step4;
	}

}
