package com.js.ens.leveller.procs.twoD.flat;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.ens.leveller.procs.ProcMaker;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class mat04_poisson_flat {
	private Logger log = Logger.getLogger(mat04_poisson_flat.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();

	private String procPath = "";
	
	private String tableName;
	private ArrayList<String> tableDataList;
	
	private ArrayList<String> fileDataList;
	private ArrayList<String> MaterialTableDataList;
	private ArrayList<String> procDataList;
	
	public mat04_poisson_flat() {
		// TODO Auto-generated constructor stub
	}

	public void running(String path) {
		// TODO Auto-generated method stub
		this.procPath = path;
		try{
			this.readMaterialTableData();
			this.parsingData();
			
			this.readSourceData();
			this.swapValue();
			this.writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(mat04_poisson) \n path : "+ this.procPath);
			log.info("SUCCESS - Export(mat04_poisson) \n path : "+ this.procPath);
		}catch(Exception e){
			String msg = "ERROR - Export(mat04_poisson)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
		
	}

	private void readMaterialTableData() throws Exception {
		// TODO Auto-generated method stub
		this.MaterialTableDataList = new ArrayList<String>();
		Reader reader = new Reader(LMain.getTextPoissonsRatio_2D());
		reader.running();
		this.MaterialTableDataList = reader.getFileDataList();
	}

	private void parsingData() throws Exception {
		// TODO Auto-generated method stub
		tableDataList = new ArrayList<String>();
		ArrayList<String> splitDataTokens = new ArrayList<String>();
		tableName = MaterialTableDataList.get(0);
		for(int i=0;i<MaterialTableDataList.size();i++){
			String line = MaterialTableDataList.get(i).replace("\t", " ");
			splitDataTokens = ParserDefault.splitLineData(line, " ");
			String firstTokens = splitDataTokens.get(0);
			if(splitDataTokens.size() == 2 && myUtil.isFloatValue(firstTokens)){
				tableDataList.add(splitDataTokens.get(0)+", "+splitDataTokens.get(1));
			}
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
			if(line.contains(ProcMaker.tableName)){
				newLine = line.replace(ProcMaker.tableName, this.tableName);
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.tableDataList)){
				for(String line2 : tableDataList){
					procDataList.add(line2);
				}
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
	
	public String getTableName() {
		return tableName;
	}
}
