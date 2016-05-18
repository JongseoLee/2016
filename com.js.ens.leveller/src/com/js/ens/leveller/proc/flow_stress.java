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

public class flow_stress {
	private Logger log = Logger.getLogger(flow_stress.class);
	
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private String value_flow_stress;
	private String procFilePath;
	
	private ArrayList<String> fileDataList;
	private ArrayList<String> procDataList;
	private ArrayList<String> fileDataList2;
	private String plateType;
	//
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
	//
	
	public flow_stress(){
		plateType = LMain.getcomboType();
		fileDataList = new ArrayList<String>();
		fileDataList2 = new ArrayList<String>();
	}
	
	public flow_stress(String value_flow_stress){
		this.value_flow_stress = value_flow_stress;
		plateType = LMain.getcomboType();
		fileDataList = new ArrayList<String>();
		fileDataList2 = new ArrayList<String>();
	}
	
	public void running(){
		try{
			// 실제 path 설정
			procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(),"proc"),"mat02_flow_stress.proc");
			// sample Test 
			//procFilePath = "D:\\Q\\new\\Workspace\\com.js.ens.leveller\\src\\com\\js\\ens\\leveller\\proc\\sample\\02_flow_stress.proc";
			readSourceData();
			parsingData();
			
			readSourceData2();
			swapValue();
			writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(mat02_flow_stress) \n path : "+ this.procFilePath);
			log.info("SUCCESS - Export(mat02_flow_stress) \n path : "+ this.procFilePath);
			
		}catch(Exception e){
			String msg = "ERROR - Export(mat02_flow_stress)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
	}
	
	public void readSourceData() throws Exception{
		Reader reader = new Reader(this.value_flow_stress);
		reader.running();
		fileDataList = reader.getFileDataList(); 
	}
	
	public void parsingData() throws Exception{
		plasstic_Strain = new ArrayList<String>();
		Temperature = new ArrayList<String>();
		Plastic_Strain_Rate = new ArrayList<String>();
		curveTitleList = new ArrayList<String>();
		curveDataTable = new ArrayList<String>();
		
		ArrayList<String> splitDataTokens = new ArrayList<String>();
		String firstLine = fileDataList.get(1).replace("\t", " ").replace("," ,"");
		splitDataTokens = ParserDefault.splitLineData(firstLine," ");
		tableName = fileDataList.get(0);
		AllCurveNum = splitDataTokens.get(0);
		PlasticDataSetNum = splitDataTokens.get(1);
		TemperatureDataSetNum = splitDataTokens.get(2);
		PlaticStrainDataSetNum = splitDataTokens.get(3);
		
		int curveNum = 0;
		
		for(int i=5;i<fileDataList.size();i++){
			String line = fileDataList.get(i).replace("\t", " ").replace(",","");
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
		String sourcePath = myUtil.setPath(s_folder, "mat02_flow_stress.proc");
		
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
			}else if(line.contains("%"+ProcVariable.PlasticDataSetNum+"%")){
				newLine = line.replace("%"+ProcVariable.PlasticDataSetNum+"%", this.PlasticDataSetNum);
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.TemperatureDataSetNum+"%")){
				newLine = line.replace("%"+ProcVariable.TemperatureDataSetNum+"%", this.TemperatureDataSetNum);
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.PlaticStrainDataSetNum+"%")){
				newLine = line.replace("%"+ProcVariable.PlaticStrainDataSetNum+"%", this.PlaticStrainDataSetNum);
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.plasstic_Strain+"%")){
				for(String str : plasstic_Strain){
					procDataList.add(str);
				}
				continue;
			}else if(line.contains("%"+ProcVariable.Temperature+"%")){
				for(String str : Temperature){
					procDataList.add(str);
				}
				continue;
			}else if(line.contains("%"+ProcVariable.Plastic_Strain_Rate+"%")){
				for(String str : Plastic_Strain_Rate){
					procDataList.add(str);
				}
				continue;
			}else if(line.contains("%"+ProcVariable.curveDataTable+"%")){
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
	
	public void writeProc() throws Exception{
		Writer writer = new Writer(procFilePath,procDataList);
		writer.running();
	}
	
	
	public String getTableName() {
		return tableName;
	}
}
