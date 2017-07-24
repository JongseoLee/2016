package com.msc.marc.jobmonitor;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class JobMonitor {
	public static final String ForceExit = "3020";
	public static final String NomalExit = "3004";
	//public static final String ErrorExit = "3006";
	
	public static ArrayList<String> getData(){
		ArrayList<String> data = new ArrayList<String>();
		// 종류 별로 수정 JobMonitor1 ~~ 3
		String in = myUtil.setPath(System.getProperty("user.dir"),"JobMonitor.inp");
		//String in = myUtil.setPath(System.getProperty("user.dir"),"JobMonitor1.inp");
		//String in = myUtil.setPath(System.getProperty("user.dir"),"JobMonitor2.inp");
		//String in = myUtil.setPath(System.getProperty("user.dir"),"JobMonitor3.inp");
		Reader reader = new Reader(in);
		reader.readFile();
		data = reader.getFileData();
		return data;
	}
	
	public static String getModelName(String line){
		//System.out.println(line);
		String path = ParserDefault.splitLineData(line, " ").get(1);
		//System.out.println(path);
		File f = new File(path);
		String fileName = f.getName();
		//System.out.println(fileName);
		String modelName = ParserDefault.splitLineData(fileName, "\\.").get(0);
		//System.out.println(modelName);
		return modelName;
	}
	
	public static String getINC(String line){
		return ParserDefault.splitLineData(line, " ").get(1);
	}
	
	public static String getPath(String line){
		return ParserDefault.splitLineData(line, " ").get(1);
	}
	
	public static String getInterval(String line){
		return ParserDefault.splitLineData(line, " ").get(1);
	}
	
	public static void main(String[] args) {
		ArrayList<String> inData = new ArrayList<String>();
		inData = getData();
		String modelName = getModelName(inData.get(0));
		String inc = getINC(inData.get(1));
		String interval = getInterval(inData.get(2)); 
		String stsFilePath = getPath(inData.get(0));
		String workspace = new File(stsFilePath).getParent();
		final String cntFilePath = myUtil.setPath(workspace, modelName+".cnt");
		String jmrFilePath = myUtil.setPath(workspace, modelName+".jmr");
		
		///////////////////////////////////////////
		// fake solving
		//makeDummyData(stsFilePath);		
		///////////////////////////////////////////
		Job jObj = Job.getInstance();
		String initStep = "(inc: 0/"+inc+")";
		jObj.setStep(initStep);
		
		final Display display = Display.getDefault();
		final Shell shell = new Shell();
		shell.setSize(568, 264);
		shell.setText("Job Monitor");
		shell.setLayout(new FormLayout());
		
		Label lblJobMonitor = new Label(shell, SWT.NONE);
		lblJobMonitor.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblJobMonitor = new FormData();
		fd_lblJobMonitor.top = new FormAttachment(0, 10);
		fd_lblJobMonitor.left = new FormAttachment(0, 10);
		lblJobMonitor.setLayoutData(fd_lblJobMonitor);
		lblJobMonitor.setText("Job Monitor");
		
		Label lblModelName = new Label(shell, SWT.NONE);
		lblModelName.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblModelName = new FormData();
		fd_lblModelName.top = new FormAttachment(lblJobMonitor, 22);
		fd_lblModelName.left = new FormAttachment(lblJobMonitor, 0, SWT.LEFT);
		lblModelName.setLayoutData(fd_lblModelName);
		lblModelName.setText("Model Name");
		
		Label lblModel = new Label(shell, SWT.NONE);
		FormData fd_lblModel = new FormData();
		fd_lblModel.top = new FormAttachment(lblModelName, 0, SWT.TOP);
		fd_lblModel.left = new FormAttachment(lblModelName, 20);
		lblModel.setLayoutData(fd_lblModel);
		lblModel.setText(modelName);
		
		Label lblWorkspace = new Label(shell, SWT.NONE);
		lblWorkspace.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblWorkspace = new FormData();
		fd_lblWorkspace.top = new FormAttachment(lblModelName, 10);
		fd_lblWorkspace.left = new FormAttachment(lblJobMonitor, 0, SWT.LEFT);
		lblWorkspace.setLayoutData(fd_lblWorkspace);
		lblWorkspace.setText("Workspace");
		
		Label lblPath = new Label(shell, SWT.NONE);
		FormData fd_lblPath = new FormData();
		fd_lblPath.top = new FormAttachment(lblWorkspace, 0, SWT.TOP);
		fd_lblPath.left = new FormAttachment(lblWorkspace, 20);
		lblPath.setLayoutData(fd_lblPath);
		lblPath.setText(workspace);
		/*
		Label lblIncrement = new Label(shell, SWT.NONE);
		lblIncrement.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblIncrement = new FormData();
		fd_lblIncrement.top = new FormAttachment(lblWorkspace, 10);
		fd_lblIncrement.left = new FormAttachment(lblJobMonitor, 0, SWT.LEFT);
		lblIncrement.setLayoutData(fd_lblIncrement);
		lblIncrement.setText("Increment");
		
		Label lblInc = new Label(shell, SWT.NONE);
		FormData fd_lblInc = new FormData();
		fd_lblInc.top = new FormAttachment(lblIncrement, 0, SWT.TOP);
		fd_lblInc.left = new FormAttachment(lblModel, 0, SWT.LEFT);
		lblInc.setLayoutData(fd_lblInc);
		lblInc.setText(inc);
		
		Label lblInterval = new Label(shell, SWT.NONE);
		lblInterval.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblInterval = new FormData();
		fd_lblInterval.top = new FormAttachment(lblIncrement, 10);
		fd_lblInterval.left = new FormAttachment(lblJobMonitor, 0, SWT.LEFT);
		lblInterval.setLayoutData(fd_lblInterval);
		lblInterval.setText("Interval (ms)");
		
		Label lblItv = new Label(shell, SWT.NONE);
		FormData fd_lblItv = new FormData();
		fd_lblItv.top = new FormAttachment(lblInterval, 0, SWT.TOP);
		fd_lblItv.left = new FormAttachment(lblModel, 0, SWT.LEFT);
		lblItv.setLayoutData(fd_lblItv);
		lblItv.setText(interval);
		//*/
		
		/* */
		
		Label lblRunning = new Label(shell, SWT.NONE);
		FormData fd_lblRunning = new FormData();
		fd_lblRunning.top = new FormAttachment(lblWorkspace, 30);
		fd_lblRunning.left = new FormAttachment(lblJobMonitor, 0, SWT.LEFT);
		lblRunning.setLayoutData(fd_lblRunning);
		lblRunning.setText("Progress...");
		
		ProgressBar progressBar = new ProgressBar(shell, SWT.BORDER | SWT.SMOOTH);
		FormData fd_progressBar = new FormData();
		fd_progressBar.top = new FormAttachment(lblRunning, 10);
		fd_progressBar.left = new FormAttachment(0, 10);
		fd_progressBar.right = new FormAttachment(100,-10);
		progressBar.setLayoutData(fd_progressBar);
		
		Button btnClose = new Button(shell, SWT.NONE);
		FormData fd_btnClose = new FormData();
		fd_btnClose.top = new FormAttachment(progressBar, 19);
		fd_btnClose.left = new FormAttachment(progressBar, -100, SWT.RIGHT);
		fd_btnClose.right = new FormAttachment(progressBar, 0, SWT.RIGHT);
		btnClose.setLayoutData(fd_btnClose);
		btnClose.setText("Kill Job");
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				makeKillJobFile(cntFilePath);
				if(myUtil.checkPath(cntFilePath)){
					System.out.println("Create cnt File Success");
				}
				//System.exit(0);
			}
		});
		
		
		Label lblValue = new Label(shell, SWT.NONE);
		FormData fd_lblValue = new FormData();
		fd_lblValue.top = new FormAttachment(lblRunning, 0, SWT.TOP);
		fd_lblValue.left = new FormAttachment(lblModel, 0, SWT.LEFT);
		fd_lblValue.right = new FormAttachment(100,-10);
		lblValue.setLayoutData(fd_lblValue);
		lblValue.setText("Start");
		
		
		checkMonitor(stsFilePath,Integer.parseInt(inc),Integer.parseInt(interval));
		while(true){
			if(myUtil.fileIsLive(stsFilePath)){
				// Run UI
				shell.open();
				shell.layout();
				while (!shell.isDisposed()) {
					//lblValue.setText(jObj.getStatus()+"% " + jObj.getStep());
					lblValue.setText(jObj.getD_status()+"% " + jObj.getStep());
					progressBar.setSelection(jObj.getStatus());
					
					if (!display.readAndDispatch()) {
						display.sleep();
					}
					
					if(jObj.getExitNum() == null){
						//System.out.println("Waiting...");
					}else{
						if(jObj.getExitNum().equals(ForceExit)){
							System.out.println("ForceExit");
							lblValue.setText("Stop!!!   "+lblValue.getText());
							JOptionPane.showMessageDialog(null, "Stop Job.","Job Monitor",JOptionPane.INFORMATION_MESSAGE);
							display.close();
							break;
						}else if(jObj.getExitNum().equals(NomalExit)){
							System.out.println("NomalExit");
							display.close();

							break;
						}else {
							JOptionPane.showMessageDialog(null, "Solving Error - "+jObj.getExitNum(),"Job Monitor",JOptionPane.ERROR_MESSAGE);
							break;
						}

					}
				}
				break;
			}else{
				try {
					Thread.sleep(1000);
					//lblValue.setText("Ready");
					System.out.println("Readey");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("BYE BYE");
		writerJobMonitorResult(jmrFilePath,jObj.getExitNum());
		if(myUtil.checkPath(jmrFilePath)){
			System.out.println("Create jmr File Success");
		}
		//JOptionPane.showMessageDialog(null, "Solving Complete.","Job Monitor",JOptionPane.INFORMATION_MESSAGE);
		// */
		
		
	}
	
	public static void makeKillJobFile(String path){
		int selectedOption = JOptionPane.showConfirmDialog(null, 
				"Do you kill the Job?", 
				"Kill Job", 
				JOptionPane.YES_NO_OPTION); 
		if (selectedOption == JOptionPane.YES_OPTION) {
			ArrayList<String> contents = new ArrayList<String>();
			contents.add("STOP");
			Writer obj = new Writer(contents,path);
			obj.writeFile();
		}
	}
	
	
	public static void writerJobMonitorResult(String path, String exitNum){
		ArrayList<String> contents = new ArrayList<String>();
		contents.add(exitNum);
		Writer obj = new Writer(contents, path);
		obj.writeFile();
	}
	
	public static void makeDummyData(String path){
		System.out.println(path);
		Runnable r = new dummyResult(path);
		Thread t = new Thread(r);
		t.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void checkMonitor(String stsPath, int MaxInc,int interval ){
		Runnable r = new CheckProgress(stsPath, MaxInc, interval);
		Thread t = new Thread(r);
		t.start();
	}
}
