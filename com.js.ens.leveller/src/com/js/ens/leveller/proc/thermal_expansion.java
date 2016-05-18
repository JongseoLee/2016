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

public class thermal_expansion {
	private Logger log = Logger.getLogger(thermal_expansion.class);
	
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private String value_thermal_expansion;
	private String procFilePath;
	
	private String tableName;
	private ArrayList<String> fileDataList;
	
	private ArrayList<String> tableDataList;
	private ArrayList<String> procDataList;
	
	private ArrayList<String> fileDataList2;
	private String plateType;
	
	public thermal_expansion(){
		// this.value_thermal_expansion = LMain.getValueElasticModuls;
		plateType = LMain.getcomboType();
		fileDataList = new ArrayList<String>();
		fileDataList2 = new ArrayList<String>();
	}
	
	public thermal_expansion(String value_thermal_expansion){
		this.value_thermal_expansion = value_thermal_expansion;
		plateType = LMain.getcomboType();
		fileDataList = new ArrayList<String>();
		fileDataList2 = new ArrayList<String>();
	}
	
	public void running(){
		try{
			procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(),"proc"),"mat03_thermal_expansion.proc");
	
			readSourceData();
			parsingData();
			
			readSourceData2();
			swapValue();
			writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(mat03_thermal_expansion) \n path : "+ this.procFilePath);
			log.info("SUCCESS - Export(mat03_thermal_expansion) \n path : "+ this.procFilePath);
			
		}catch(Exception e){
			String msg = "ERROR - Exprot(mat03_thermal_expansion)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
	}
	
	public void readSourceData() throws Exception{
		
		Reader reader = new Reader(this.value_thermal_expansion);
		reader.running();
		fileDataList = reader.getFileDataList(); 
	}
	
	public void parsingData() throws Exception{
		tableDataList = new ArrayList<String>();
		ArrayList<String> splitDataTokens = new ArrayList<String>();
		tableName = fileDataList.get(0);
		for(int i=0;i<fileDataList.size();i++){
			String line = fileDataList.get(i).replace("\t", " ");
			splitDataTokens = ParserDefault.splitLineData(line, " ");
			String firstTokens = splitDataTokens.get(0);
			if(splitDataTokens.size() == 2 && myUtil.isFloatValue(firstTokens)){
				tableDataList.add(splitDataTokens.get(0)+", "+splitDataTokens.get(1));
			}
		}
	}
	
	public void readSourceData2() throws Exception{
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
		String sourcePath = myUtil.setPath(s_folder, "mat03_thermal_expansion.proc");
		
		Reader reader = new Reader(sourcePath);
		reader.running();
		fileDataList2 = reader.getFileDataList();
	}
	
	public void swapValue() throws Exception{
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : fileDataList2){
			if(line.contains("%"+ProcVariable.tableName+"%")){
				newLine = line.replace("%"+ProcVariable.tableName+"%", this.tableName);
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.tableDataList+"%")){
				for(String line2 : tableDataList){
					procDataList.add(line2);
				}
				continue;
			}else {
				procDataList.add(line);
			}
		}
	}
	
	public void writeProc() throws Exception{
		Writer writer = new Writer(procFilePath,procDataList);
		writer.running();
	}

	public String getTableName() {
		return tableName;
	}
}
