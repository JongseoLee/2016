package com.js.ens.leveller.proc;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class flow_stress_const {
	private Logger log = Logger.getLogger(flow_stress_const.class);
	
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private String value_YieldStrength;
	private String value_TensileStrength;
	private String value_Elongation;
	private String procFilePath;
	
	private ArrayList<String> procDataList;
	private ArrayList<String> tempDataList;
	
	private boolean swapFinish = false;
	
	private ArrayList<String> fileDataList2;
	private String plateType;
	
	public flow_stress_const(){
		plateType = LMain.getcomboType();
		fileDataList2 = new ArrayList<String>();
	}
	
	public flow_stress_const(String value_YieldStrength,String value_TensileStrength,String value_Elongation){
		this.value_YieldStrength = value_YieldStrength;
		this.value_TensileStrength = value_TensileStrength;
		this.value_Elongation = value_Elongation;
		plateType = LMain.getcomboType();
		fileDataList2 = new ArrayList<String>();
	}
	
	public void running(){
		try{
			procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(),"proc"),"mat02_flow_stress_const.proc");
			
			readSourceData2();
			swapValue();
			while(!swapFinish){
				checkFinish();
			}
			writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(mat02_flow_stress_const) \n path : "+ this.procFilePath);
			log.info("SUCCESS - Export(mat02_flow_stress_const) \n path : "+ this.procFilePath);
			
		}catch(Exception e){
			String msg = "ERROR - Export(mat02_flow_stress_const)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
			
		}
	}
	
	public void readSourceData2() throws Exception {
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
		String sourcePath = myUtil.setPath(s_folder, "mat02_flow_stress_const.proc");
		
		Reader reader = new Reader(sourcePath);
		reader.running();
		fileDataList2 = reader.getFileDataList();
	}
	
	public void checkFinish() throws Exception{
		boolean isFinish = false;
		tempDataList = new ArrayList<String>();
		for(String line:procDataList){
			tempDataList.add(line);
		}
		for(String line : tempDataList){
			if(line.contains("%"+ProcVariable.lblYieldStrength+"%")){
				isFinish = false;
				break;
			}else if(line.contains("%"+ProcVariable.lblTensileStrength+"%")){
				isFinish = false;
				break;
			}else if(line.contains("%"+ProcVariable.lblElongation+"%")){
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
	
	public void swapValue() throws Exception{
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : fileDataList2){
			if(line.contains("%"+ProcVariable.lblYieldStrength+"%")){
				newLine = line.replace("%"+ProcVariable.lblYieldStrength+"%", this.value_YieldStrength );
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblTensileStrength+"%")){
				newLine = line.replace("%"+ProcVariable.lblTensileStrength+"%", this.value_TensileStrength );
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblElongation+"%")){
				newLine = line.replace("%"+ProcVariable.lblElongation+"%", this.value_Elongation );
				procDataList.add(newLine);
				continue;
			}else {
				procDataList.add(line);
			}
		}
	}

	public void swapValue2(ArrayList<String> tempDataList) throws Exception{
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : tempDataList){
			if(line.contains("%"+ProcVariable.lblYieldStrength+"%")){
				newLine = line.replace("%"+ProcVariable.lblYieldStrength+"%", this.value_YieldStrength );
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblTensileStrength+"%")){
				newLine = line.replace("%"+ProcVariable.lblTensileStrength+"%", this.value_TensileStrength );
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblElongation+"%")){
				newLine = line.replace("%"+ProcVariable.lblElongation+"%", this.value_Elongation );
				procDataList.add(newLine);
				continue;
			}else {
				procDataList.add(line);
			}
		}
	}
	
	public void writeProc() throws Exception {
		Writer writer = new Writer(procFilePath,procDataList);
		writer.running();
	}
}
