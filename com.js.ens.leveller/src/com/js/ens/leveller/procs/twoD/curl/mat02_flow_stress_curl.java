package com.js.ens.leveller.procs.twoD.curl;

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

public class mat02_flow_stress_curl {
	private Logger log = Logger.getLogger(mat02_flow_stress_curl.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
	
	private String procPath = "";
	
	private String tableName;
	private String AllCurveNum;
	private String PlasticDataSetNum;
	private String TemperatureDataSetNum;
	private String PlaticStrainDataSetNum;
	
	private ArrayList<String> plasstic_Strain;
	private ArrayList<String> Temperature;
	private ArrayList<String> Plastic_Strain_Rate;
	private ArrayList<String> curveTitleList;
	private ArrayList<String> curveDataTable;
	
	private ArrayList<String> fileDataList;
	private ArrayList<String> MaterialTableDataList;
	private ArrayList<String> procDataList;
	
	public mat02_flow_stress_curl() {
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
			
			LMain.getExportMSG().addData("SUCCESS - Export(mat02_flow_stress) \n path : "+ this.procPath);
			log.info("SUCCESS - Export(mat02_flow_stress) \n path : "+ this.procPath);
			
		}catch(Exception e){
			//e.printStackTrace();
			String msg = "ERROR - Export(mat02_flow_stress)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
	}
	
	private void readMaterialTableData() throws Exception {
		this.MaterialTableDataList = new ArrayList<String>();
		Reader reader = new Reader(LMain.getTextFlowStress_2D());
		reader.running();
		this.MaterialTableDataList = reader.getFileDataList();
	}
	
	private void parsingData() throws Exception{
		plasstic_Strain = new ArrayList<String>();
		Temperature = new ArrayList<String>();
		Plastic_Strain_Rate = new ArrayList<String>();
		curveTitleList = new ArrayList<String>();
		curveDataTable = new ArrayList<String>();
		
		ArrayList<String> splitDataTokens = new ArrayList<String>();
		String firstLine = MaterialTableDataList.get(1).replace("\t", " ").replace("," ,"");
		splitDataTokens = ParserDefault.splitLineData(firstLine," ");
		tableName = MaterialTableDataList.get(0);
		AllCurveNum = splitDataTokens.get(0);
		PlasticDataSetNum = splitDataTokens.get(1);
		TemperatureDataSetNum = splitDataTokens.get(2);
		PlaticStrainDataSetNum = splitDataTokens.get(3);
		
		int curveNum = 0;
		
		for(int i=5;i<MaterialTableDataList.size();i++){
			String line = MaterialTableDataList.get(i).replace("\t", " ").replace(",","");
			splitDataTokens = ParserDefault.splitLineData(line, " ");
			String firstTokens = splitDataTokens.get(0);
			
			if(firstTokens.contains("===")){
				// === CURVE_01 Sig_Yiel	 T=20.0 C	 Eps_dot=1.60 1/s
				//               0               1           2                    
				// === CURVE_02 Sig_Yiel	 T=100.0 C	 Eps_dot=1.60 1/s
				//  0      1        2            3   4       5         6 
				curveTitleList.add(line);
				//String TValue = ParserDefault.splitLineData(ParserDefault.splitLineData(splitDataTokens.get(1), "=").get(1), " ").get(0);
				//String SPValue = ParserDefault.splitLineData(ParserDefault.splitLineData(splitDataTokens.get(2), "=").get(1), " ").get(0);
				String TValue = ParserDefault.splitLineData(splitDataTokens.get(3), "=").get(1);
				String SPValue = ParserDefault.splitLineData(splitDataTokens.get(5), "=").get(1);
				if(curveNum == -1){
					Temperature.add(TValue);
					Plastic_Strain_Rate.add(SPValue);
					
				}else {
					if(!Temperature.contains(TValue)){
						Temperature.add(TValue);
					}
					if(!Plastic_Strain_Rate.contains(SPValue)){
						Plastic_Strain_Rate.add(SPValue);	
					}
				}
				i=i+1;
				curveNum++;
			}
			if(splitDataTokens.size() == 2 && myUtil.isFloatValue(firstTokens)){
				if(curveNum == 1){
					plasstic_Strain.add(splitDataTokens.get(0));
				}
				curveDataTable.add(splitDataTokens.get(1));
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
			}else if(line.contains(ProcMaker.PlasticDataSetNum)){
				newLine = line.replace(ProcMaker.PlasticDataSetNum, this.PlasticDataSetNum);
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.TemperatureDataSetNum)){
				newLine = line.replace(ProcMaker.TemperatureDataSetNum, this.TemperatureDataSetNum);
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.PlaticStrainDataSetNum)){
				newLine = line.replace(ProcMaker.PlaticStrainDataSetNum, this.PlaticStrainDataSetNum);
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.plasstic_Strain)){
				for(String str : plasstic_Strain){
					procDataList.add(str);
				}
				continue;
			}else if(line.contains(ProcMaker.Temperature)){
				for(String str : Temperature){
					procDataList.add(str);
				}
				continue;
			}else if(line.contains(ProcMaker.Plastic_Strain_Rate)){
				for(String str : Plastic_Strain_Rate){
					procDataList.add(str);
				}
				continue;
			}else if(line.contains(ProcMaker.curveDataTable)){
				int tableColNum = 0;
				for(int i=0; i<Integer.parseInt(AllCurveNum) ;i++){
					procDataList.add("|"+curveTitleList.get(i));
					for(int j = tableColNum;j<Integer.parseInt(PlasticDataSetNum)*(i+1);j++){
						procDataList.add(curveDataTable.get(j));
						tableColNum++;
					}
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
	
