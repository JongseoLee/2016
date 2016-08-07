package com.js.ens.leveller.procs.threeD.edgeWave;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.ens.leveller.procs.ProcMaker;
import com.js.io.Reader;
import com.js.io.Writer;

public class mat01_elastic_modulus_const_edgeWave {
	private Logger log = Logger.getLogger(mat01_elastic_modulus_const_edgeWave.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
	
	private String procPath = "";
	
	private ArrayList<String> fileDataList;
	private ArrayList<String> procDataList;
	
	public mat01_elastic_modulus_const_edgeWave() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(String path){
		this.procPath = path;
		try{
			this.readSourceData();
			this.swapValue();
			this.writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(mat01_elastic_modulus_const) \n path : "+ this.procPath);
			log.info("SUCCESS - Export(mat01_elastic_modulus_const) \n path : "+ this.procPath);
		}catch(Exception e){
			String msg = "ERROR - Export(mat01_elastic_modulus_const)";
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
			if(line.contains(ProcMaker.textYoungsModulus)){
				newLine = line.replace(ProcMaker.textYoungsModulus, LMain.getTextYoungsModulus());
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
