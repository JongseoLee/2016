package com.js.ens.leveller.procs.threeD.edgeWave;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.ens.leveller.procs.ProcMaker;
import com.js.io.Reader;
import com.js.io.Writer;

public class mat02_flow_stress_const_edgeWave {
	private Logger log = Logger.getLogger(mat02_flow_stress_const_edgeWave.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
	
	private String procPath = "";
	
	private ArrayList<String> fileDataList;
	private ArrayList<String> procDataList;
	private ArrayList<String> tempDataList;
	
	private boolean swapFinish = false;
	
	public mat02_flow_stress_const_edgeWave() {
		// TODO Auto-generated constructor stub
	}

	public void running(String path) {
		// TODO Auto-generated method stub
		this.procPath = path;

		try{
			this.readSourceData();
			this.swapValue();
			
			while(!swapFinish){
				this.checkFinish();
			}
			this.writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(mat02_flow_stress_const) \n path : "+ this.procPath);
			log.info("SUCCESS - Export(mat02_flow_stress_const) \n path : "+ this.procPath);
		}catch(Exception e){
			String msg = "ERROR - Export(mat02_flow_stress_const)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
	}
	
	private void readSourceData() throws Exception {
		// TODO Auto-generated method stub
		this.fileDataList = new ArrayList<String>();
		Reader reader = new Reader(this.procPath);
		reader.running();
		this.fileDataList = reader.getFileDataList();
	}
	
	private void swapValue() throws Exception {
		// TODO Auto-generated method stub
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : fileDataList){
			if(line.contains(ProcMaker.textYieldStrength)){
				newLine = line.replace(ProcMaker.textYieldStrength, LMain.getTextYieldStrength() );
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textTensileStrength)){
				newLine = line.replace(ProcMaker.textTensileStrength, LMain.getTextTensileStrength());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textElongation)){
				newLine = line.replace(ProcMaker.textElongation, LMain.getTextElongation());
				procDataList.add(newLine);
				continue;
			}else {
				procDataList.add(line);
			}
		}
	}
	
	private void checkFinish() throws Exception{
		boolean isFinish = false;
		tempDataList = new ArrayList<String>();
		for(String line:procDataList){
			tempDataList.add(line);
		}
		for(String line : tempDataList){
			if(line.contains(ProcMaker.textYieldStrength)){
				isFinish = false;
				break;
			}else if(line.contains(ProcMaker.textTensileStrength)){
				isFinish = false;
				break;
			}else if(line.contains(ProcMaker.textElongation)){
				isFinish = false;
				break;
			}else { 
				isFinish = true;
				this.swapFinish = true;
			}
		}
		
		if (!isFinish){
			swapValue2(tempDataList);
		}
	}
	
	public void swapValue2(ArrayList<String> tempDataList) throws Exception{
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : tempDataList){
			if(line.contains(ProcMaker.textYieldStrength)){
				newLine = line.replace(ProcMaker.textYieldStrength, LMain.getTextYieldStrength() );
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textTensileStrength)){
				newLine = line.replace(ProcMaker.textTensileStrength, LMain.getTextTensileStrength() );
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textElongation)){
				newLine = line.replace(ProcMaker.textElongation, LMain.getTextTensileStrength());
				procDataList.add(newLine);
				continue;
			}else {
				procDataList.add(line);
			}
		}
	}
	
	private void writeProc() throws Exception {
		// TODO Auto-generated method stub
		Writer writer = new Writer(this.procPath,this.procDataList);
		writer.running();
	}
}
