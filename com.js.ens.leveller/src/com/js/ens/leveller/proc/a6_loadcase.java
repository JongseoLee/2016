package com.js.ens.leveller.proc;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class a6_loadcase {
	private Logger log = Logger.getLogger(a6_loadcase.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private ArrayList<String> procDataList;
	private String procFilePath;
	
	private ArrayList<String> fileDataList;
	private String plateType;
	
	public a6_loadcase(){
		fileDataList = new ArrayList<String>();
		plateType = LMain.getcomboType();
	}
	
	public a6_loadcase(String path){
		fileDataList = new ArrayList<String>();
		this.procFilePath = path;
		plateType = LMain.getcomboType();
	}
	
	public void running(){
		try{
			//
			procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "a6_loadcase.proc");
			
			readSourceData();
			swapValue();
			writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(a6_loadcase) \n path : "+ this.procFilePath);
			log.info("SUCCESS - Export(a6_loadcase) \n path : "+ this.procFilePath);
			
		}catch(Exception e){
			String msg = "ERROR - Export(a6_loadcase)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
	}
	
	public void readSourceData() throws Exception{
		String modulePath = myUtil.setPath(System.getProperty("user.dir"), "module");
		String s_folder = null;
		if(plateType.equals(ComboLabel.TYPE1))	
			s_folder = myUtil.setPath(modulePath,"1_Flat");
		else if(plateType.equals(ComboLabel.TYPE2))
			s_folder = myUtil.setPath(modulePath,"2_EdgeWave");
		else if(plateType.equals(ComboLabel.TYPE3))
			s_folder = myUtil.setPath(modulePath,"3_CenterWave");
		else if(plateType.equals(ComboLabel.TYPE4))
			s_folder = myUtil.setPath(modulePath,"4_SingleGutter");
		else if(plateType.equals(ComboLabel.TYPE5))
			s_folder = myUtil.setPath(modulePath,"5_PartialGutter");
		else if(plateType.equals(ComboLabel.TYPE6))
			s_folder = myUtil.setPath(modulePath,"6_DoubleGutter");
		else if(plateType.equals(ComboLabel.TYPE7))
			s_folder = myUtil.setPath(modulePath,"7_IslandGutter");
		String sourcePath = myUtil.setPath(s_folder, "a6_loadcase.proc");
		
		Reader reader = new Reader(sourcePath);
		reader.running();
		fileDataList = reader.getFileDataList();
	}
	
	public void swapValue() throws Exception{
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : fileDataList){
			if(line.contains("%"+ProcVariable.lblSolvingTime+"%")){
				newLine = line.replace("%"+ProcVariable.lblSolvingTime+"%", LMain.getTextSolvingTime());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblDomain+"%")){
				if(LMain.isBtnParallelDDMUse()){
					procDataList.add("*job_option parallel:on");
					procDataList.add("*job_param ndomains");
					procDataList.add(LMain.getSpinnerDomain());
				}
			}else if(line.contains("%"+ProcVariable.lblThread+"%")){
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

	private void writeProc() throws Exception{
		Writer writer = new Writer(procFilePath,procDataList);
		writer.running();
	}

}
