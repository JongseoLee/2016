package com.js.ens.leveller.procs.threeD.doubleGutter;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.ens.leveller.procs.ProcMaker;
import com.js.io.Reader;
import com.js.io.Writer;

public class a6_loadcase_doubleGutter {

	private Logger log = Logger.getLogger(a6_loadcase_doubleGutter.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
	
	private String procPath = "";
	
	private ArrayList<String> fileDataList;
	private ArrayList<String> procDataList;
	
	public a6_loadcase_doubleGutter() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(String path){
		this.procPath = path;
		try{
			this.readSourceData();
			this.swapValue();
			this.writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(a6_loadcase) \n path : "+ this.procPath);
			log.info("SUCCESS - Export(a6_loadcase) \n path : "+ this.procPath);
		}catch(Exception e){
			String msg = "ERROR - Export(a6_loadcase)";
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
			if(line.contains(ProcMaker.textSolvingTime)){
				newLine = line.replace(ProcMaker.textSolvingTime, LMain.getTextSolvingTime());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textDomain)){
				if(LMain.isBtnParallelDDMUse()){
					procDataList.add("*job_option parallel:on");
					procDataList.add("*job_param ndomains");
					procDataList.add(LMain.getSpinnerDomain());
				}
			}else if(line.contains(ProcMaker.textThread)){
				if(LMain.isbtnParallelMultiThreadUse()){
					procDataList.add("*job_option mfront_sparse_multi_threading:on");
					procDataList.add("*job_param nthreads");
					procDataList.add(LMain.getSpinnerThread());
				}
			}else{
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
