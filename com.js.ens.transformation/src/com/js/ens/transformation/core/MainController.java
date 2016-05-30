package com.js.ens.transformation.core;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import com.js.io.Reader;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class MainController {
	
	private Mediator med = Mediator.getInstance();
	
	private static MainController instance = new MainController();
	public static MainController getInstance(){
		return instance;
	}
	//--------------
	// tab1
	private String PLogFilePath = null;
	private ArrayList<String> PLogFileDataList = null;
	
	private TableData_SlabPlateInfo TableDataSlabPlateInfoObj = null;
	private ArrayList<TableData_SlabPlateInfo> tableDataSlabPlateInfoList = null;
	private Map<String,String> tableDataSlabPlateInfoMap = null;
	
	private TableData_Variable TableDataVariableObj = null;
	private ArrayList<TableData_Variable> tableDataVariableList = null;
	private Map<String,String> tableDataVariableMap = null;
	
	private ArrayList<TableData_PLog> tableDataPLogList = null;
	private Map<String,String> tableDataPLogMap; 
	
	//--------------
	// tab2
	
	public MainController() {
		// TODO Auto-generated constructor stub
	}
	//=====================================================================
	//	Button - Import P Log
	//
	public void ImportPLog(){
		
		FileDialog dlg = new FileDialog(med.getBtnImportPLog().getShell(),SWT.OPEN);
		dlg.setText("Select P Log.csv file.");
		String [] extNames = {"ALL(*.*)","CSV"};
		String [] extType = {"*.*","*.csv"}; 
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		String path = dlg.open();
		if(path == null){
			PLogFilePath = null;
			return;
		}else { 
			PLogFilePath = path;
			parsingPLogFile();
		}
	}
	
	public void parsingPLogFile(){
		//System.out.println("P Log Path : "+this.PLogFilePath);
		if(myUtil.checkPath(PLogFilePath)){
			
			Reader obj = new Reader(PLogFilePath);
			obj.running();
			PLogFileDataList = new ArrayList<String>();
			PLogFileDataList = obj.getFileDataList();
			
			TableColumnLabel tclObj = new TableColumnLabel();
			tclObj.readTableColumnLabelFile();
			
			initTableData_SlabPlateInfo(PLogFileDataList,tclObj);
			initTableData_Variable(PLogFileDataList,tclObj);
			initTableData_PLog(PLogFileDataList,tclObj);
		}else{
		}
	}

	public void initTableData_SlabPlateInfo(ArrayList<String> fileDataList,TableColumnLabel tclObj){
		// line 0~1 => column line0, data line1
		int lineNumber = 0;
		String data = "";
		for(String line:fileDataList){
			//System.out.println(lineNumber +" : "+line);
			ArrayList<String> tempList = ParserDefault.splitLineData(line, ",");
			if(tempList.size()!=0){
				if(tempList.get(0).toLowerCase().equals(tclObj.getTableColumnLabel(TableColumnLabel.CL1_0).toLowerCase())){
					data = fileDataList.get(lineNumber+1);
					break;
				}
			}
			lineNumber++;
		}
		
		TableData_SlabPlateInfo obj = new TableData_SlabPlateInfo();
		obj.setAllData(data);
		this.TableDataSlabPlateInfoObj = new TableData_SlabPlateInfo();
		this.TableDataSlabPlateInfoObj = obj;
		//this.TableDataSlabPlateInfoObj.printAllData();
		this.tableDataSlabPlateInfoList = new ArrayList<TableData_SlabPlateInfo>();
		this.tableDataSlabPlateInfoList.add(obj);
		
	}
	
	public void initTableData_Variable(ArrayList<String> fileDataList,TableColumnLabel tclObj){
		// line 3~4 => column line3, data line4
		int lineNumber = 0;
		String data = "";
		for(String line:fileDataList){
			//System.out.println(lineNumber +" : "+line);
			ArrayList<String> tempList = ParserDefault.splitLineData(line, ",");
			if(tempList.size()!=0){
				if(tempList.get(0).toLowerCase().equals(tclObj.getTableColumnLabel(TableColumnLabel.CL2_0).toLowerCase())){
					data = fileDataList.get(lineNumber+1);
					break;
				}
			}
			lineNumber++;
		}
		
		TableData_Variable obj = new TableData_Variable();
		obj.setAllData(data);
		
		this.TableDataVariableObj = new TableData_Variable(); 
		this.TableDataVariableObj = obj;
		//this.TableDataVariableObj.printAllData();
		this.tableDataVariableList = new ArrayList<TableData_Variable>();
		this.tableDataVariableList.add(obj);
	}
	
	public void initTableData_PLog(ArrayList<String> fileDataList,TableColumnLabel tclObj){
		// line 7~31 => column 6, data line 7~30
		//this.TableDataSlabPlateInfoObj.printAllData();
		//this.TableDataVariableObj.printAllData();
		int lineNumber = 0;
		ArrayList<String> dataList = new ArrayList<String>();
		for(String line:fileDataList){
			ArrayList<String> tempList = ParserDefault.splitLineData(line, ",");
			if(tempList.size()!=0){
				if(tempList.get(0).toLowerCase().equals(tclObj.getTableColumnLabel(TableColumnLabel.CL3_0).toLowerCase())){
					break;
				}
			}
			lineNumber++;
		}
		
		for(int i = lineNumber+1 ; i<lineNumber+25;i++){
			//System.out.println(i + " : "+fileDataList.get(i));
			dataList.add(fileDataList.get(i));
		}
		this.createPLogObj(dataList);
		for(TableData_PLog obj : this.tableDataPLogList){
			obj.printAllData();
		}
		this.updateTableData();
	}
	
	public void createPLogObj(ArrayList<String> dataList){
		this.tableDataPLogList = new ArrayList<TableData_PLog>();
		for(int i=0;i<7;i++){
			TableData_PLog obj = new TableData_PLog();
			obj.setSTAND("F"+(i+1));
			this.tableDataPLogList.add(obj);
			
		}
		
		for (String line : dataList){
			ArrayList<String> tempList = ParserDefault.splitLineData_table3(line, ",");
			if(tempList.get(0).equals(UILabel.BUR_TDIA)){
				this.tableDataPLogList.get(0).setBUR_TDIA(tempList.get(1));
				this.tableDataPLogList.get(1).setBUR_TDIA(tempList.get(2));
				this.tableDataPLogList.get(2).setBUR_TDIA(tempList.get(3));
				this.tableDataPLogList.get(3).setBUR_TDIA(tempList.get(4));
				this.tableDataPLogList.get(4).setBUR_TDIA(tempList.get(5));
				this.tableDataPLogList.get(5).setBUR_TDIA(tempList.get(6));
				this.tableDataPLogList.get(6).setBUR_TDIA(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.BUR_BDIA)){
				this.tableDataPLogList.get(0).setBUR_BDIA(tempList.get(1));
				this.tableDataPLogList.get(1).setBUR_BDIA(tempList.get(2));
				this.tableDataPLogList.get(2).setBUR_BDIA(tempList.get(3));
				this.tableDataPLogList.get(3).setBUR_BDIA(tempList.get(4));
				this.tableDataPLogList.get(4).setBUR_BDIA(tempList.get(5));
				this.tableDataPLogList.get(5).setBUR_BDIA(tempList.get(6));
				this.tableDataPLogList.get(6).setBUR_BDIA(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.WR_TDIA)){
				this.tableDataPLogList.get(0).setWR_TDIA(tempList.get(1));
				this.tableDataPLogList.get(1).setWR_TDIA(tempList.get(2));
				this.tableDataPLogList.get(2).setWR_TDIA(tempList.get(3));
				this.tableDataPLogList.get(3).setWR_TDIA(tempList.get(4));
				this.tableDataPLogList.get(4).setWR_TDIA(tempList.get(5));
				this.tableDataPLogList.get(5).setWR_TDIA(tempList.get(6));
				this.tableDataPLogList.get(6).setWR_TDIA(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.WR_BDIA)){
				this.tableDataPLogList.get(0).setWR_BDIA(tempList.get(1));
				this.tableDataPLogList.get(1).setWR_BDIA(tempList.get(2));
				this.tableDataPLogList.get(2).setWR_BDIA(tempList.get(3));
				this.tableDataPLogList.get(3).setWR_BDIA(tempList.get(4));
				this.tableDataPLogList.get(4).setWR_BDIA(tempList.get(5));
				this.tableDataPLogList.get(5).setWR_BDIA(tempList.get(6));
				this.tableDataPLogList.get(6).setWR_BDIA(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.WR_ICRN)){
				this.tableDataPLogList.get(0).setWR_ICRN(tempList.get(1));
				this.tableDataPLogList.get(1).setWR_ICRN(tempList.get(2));
				this.tableDataPLogList.get(2).setWR_ICRN(tempList.get(3));
				this.tableDataPLogList.get(3).setWR_ICRN(tempList.get(4));
				this.tableDataPLogList.get(4).setWR_ICRN(tempList.get(5));
				this.tableDataPLogList.get(5).setWR_ICRN(tempList.get(6));
				this.tableDataPLogList.get(6).setWR_ICRN(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.ENTRY_THK)){
				this.tableDataPLogList.get(0).setENTRY_THK(tempList.get(1));
				this.tableDataPLogList.get(1).setENTRY_THK(tempList.get(2));
				this.tableDataPLogList.get(2).setENTRY_THK(tempList.get(3));
				this.tableDataPLogList.get(3).setENTRY_THK(tempList.get(4));
				this.tableDataPLogList.get(4).setENTRY_THK(tempList.get(5));
				this.tableDataPLogList.get(5).setENTRY_THK(tempList.get(6));
				this.tableDataPLogList.get(6).setENTRY_THK(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.EXIT_THK)){
				this.tableDataPLogList.get(0).setEXIT_THK(tempList.get(1));
				this.tableDataPLogList.get(1).setEXIT_THK(tempList.get(2));
				this.tableDataPLogList.get(2).setEXIT_THK(tempList.get(3));
				this.tableDataPLogList.get(3).setEXIT_THK(tempList.get(4));
				this.tableDataPLogList.get(4).setEXIT_THK(tempList.get(5));
				this.tableDataPLogList.get(5).setEXIT_THK(tempList.get(6));
				this.tableDataPLogList.get(6).setEXIT_THK(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.PAS_LINE)){
				this.tableDataPLogList.get(0).setPAS_LINE(tempList.get(1));
				this.tableDataPLogList.get(1).setPAS_LINE(tempList.get(2));
				this.tableDataPLogList.get(2).setPAS_LINE(tempList.get(3));
				this.tableDataPLogList.get(3).setPAS_LINE(tempList.get(4));
				this.tableDataPLogList.get(4).setPAS_LINE(tempList.get(5));
				this.tableDataPLogList.get(5).setPAS_LINE(tempList.get(6));
				this.tableDataPLogList.get(6).setPAS_LINE(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.ROL_GAP)){
				this.tableDataPLogList.get(0).setROL_GAP(tempList.get(1));
				this.tableDataPLogList.get(1).setROL_GAP(tempList.get(2));
				this.tableDataPLogList.get(2).setROL_GAP(tempList.get(3));
				this.tableDataPLogList.get(3).setROL_GAP(tempList.get(4));
				this.tableDataPLogList.get(4).setROL_GAP(tempList.get(5));
				this.tableDataPLogList.get(5).setROL_GAP(tempList.get(6));
				this.tableDataPLogList.get(6).setROL_GAP(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.STP_WID)){
				this.tableDataPLogList.get(0).setSTP_WID(tempList.get(1));
				this.tableDataPLogList.get(1).setSTP_WID(tempList.get(2));
				this.tableDataPLogList.get(2).setSTP_WID(tempList.get(3));
				this.tableDataPLogList.get(3).setSTP_WID(tempList.get(4));
				this.tableDataPLogList.get(4).setSTP_WID(tempList.get(5));
				this.tableDataPLogList.get(5).setSTP_WID(tempList.get(6));
				this.tableDataPLogList.get(6).setSTP_WID(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.STP_LEN)){
				this.tableDataPLogList.get(0).setSTP_LEN(tempList.get(1));
				this.tableDataPLogList.get(1).setSTP_LEN(tempList.get(2));
				this.tableDataPLogList.get(2).setSTP_LEN(tempList.get(3));
				this.tableDataPLogList.get(3).setSTP_LEN(tempList.get(4));
				this.tableDataPLogList.get(4).setSTP_LEN(tempList.get(5));
				this.tableDataPLogList.get(5).setSTP_LEN(tempList.get(6));
				this.tableDataPLogList.get(6).setSTP_LEN(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.ENTRY_TEMP)){
				this.tableDataPLogList.get(0).setENTRY_TEMP(tempList.get(1));
				this.tableDataPLogList.get(1).setENTRY_TEMP(tempList.get(2));
				this.tableDataPLogList.get(2).setENTRY_TEMP(tempList.get(3));
				this.tableDataPLogList.get(3).setENTRY_TEMP(tempList.get(4));
				this.tableDataPLogList.get(4).setENTRY_TEMP(tempList.get(5));
				this.tableDataPLogList.get(5).setENTRY_TEMP(tempList.get(6));
				this.tableDataPLogList.get(6).setENTRY_TEMP(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.EXIT_TEMP)){
				this.tableDataPLogList.get(0).setEXIT_TEMP(tempList.get(1));
				this.tableDataPLogList.get(1).setEXIT_TEMP(tempList.get(2));
				this.tableDataPLogList.get(2).setEXIT_TEMP(tempList.get(3));
				this.tableDataPLogList.get(3).setEXIT_TEMP(tempList.get(4));
				this.tableDataPLogList.get(4).setEXIT_TEMP(tempList.get(5));
				this.tableDataPLogList.get(5).setEXIT_TEMP(tempList.get(6));
				this.tableDataPLogList.get(6).setEXIT_TEMP(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.FRCE)){
				this.tableDataPLogList.get(0).setFRCE(tempList.get(1));
				this.tableDataPLogList.get(1).setFRCE(tempList.get(2));
				this.tableDataPLogList.get(2).setFRCE(tempList.get(3));
				this.tableDataPLogList.get(3).setFRCE(tempList.get(4));
				this.tableDataPLogList.get(4).setFRCE(tempList.get(5));
				this.tableDataPLogList.get(5).setFRCE(tempList.get(6));
				this.tableDataPLogList.get(6).setFRCE(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.TORQ)){
				this.tableDataPLogList.get(0).setTORQ(tempList.get(1));
				this.tableDataPLogList.get(1).setTORQ(tempList.get(2));
				this.tableDataPLogList.get(2).setTORQ(tempList.get(3));
				this.tableDataPLogList.get(3).setTORQ(tempList.get(4));
				this.tableDataPLogList.get(4).setTORQ(tempList.get(5));
				this.tableDataPLogList.get(5).setTORQ(tempList.get(6));
				this.tableDataPLogList.get(6).setTORQ(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.SPEED_mpm)){
				this.tableDataPLogList.get(0).setSPEED(tempList.get(1));
				this.tableDataPLogList.get(1).setSPEED(tempList.get(2));
				this.tableDataPLogList.get(2).setSPEED(tempList.get(3));
				this.tableDataPLogList.get(3).setSPEED(tempList.get(4));
				this.tableDataPLogList.get(4).setSPEED(tempList.get(5));
				this.tableDataPLogList.get(5).setSPEED(tempList.get(6));
				this.tableDataPLogList.get(6).setSPEED(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.BEND)){
				this.tableDataPLogList.get(0).setBEND(tempList.get(1));
				this.tableDataPLogList.get(1).setBEND(tempList.get(2));
				this.tableDataPLogList.get(2).setBEND(tempList.get(3));
				this.tableDataPLogList.get(3).setBEND(tempList.get(4));
				this.tableDataPLogList.get(4).setBEND(tempList.get(5));
				this.tableDataPLogList.get(5).setBEND(tempList.get(6));
				this.tableDataPLogList.get(6).setBEND(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.P_CROSS)){
				this.tableDataPLogList.get(0).setP_CROSS(tempList.get(1));
				this.tableDataPLogList.get(1).setP_CROSS(tempList.get(2));
				this.tableDataPLogList.get(2).setP_CROSS(tempList.get(3));
				this.tableDataPLogList.get(3).setP_CROSS(tempList.get(4));
				this.tableDataPLogList.get(4).setP_CROSS(tempList.get(5));
				this.tableDataPLogList.get(5).setP_CROSS(tempList.get(6));
				this.tableDataPLogList.get(6).setP_CROSS(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.TENS)){
				this.tableDataPLogList.get(0).setTENS(tempList.get(1));
				this.tableDataPLogList.get(1).setTENS(tempList.get(2));
				this.tableDataPLogList.get(2).setTENS(tempList.get(3));
				this.tableDataPLogList.get(3).setTENS(tempList.get(4));
				this.tableDataPLogList.get(4).setTENS(tempList.get(5));
				this.tableDataPLogList.get(5).setTENS(tempList.get(6));
				this.tableDataPLogList.get(6).setTENS(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.ROL_TIM)){
				this.tableDataPLogList.get(0).setROL_TIM(tempList.get(1));
				this.tableDataPLogList.get(1).setROL_TIM(tempList.get(2));
				this.tableDataPLogList.get(2).setROL_TIM(tempList.get(3));
				this.tableDataPLogList.get(3).setROL_TIM(tempList.get(4));
				this.tableDataPLogList.get(4).setROL_TIM(tempList.get(5));
				this.tableDataPLogList.get(5).setROL_TIM(tempList.get(6));
				this.tableDataPLogList.get(6).setROL_TIM(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.IDL_TIM)){
				this.tableDataPLogList.get(0).setIDL_TIM(tempList.get(1));
				this.tableDataPLogList.get(1).setIDL_TIM(tempList.get(2));
				this.tableDataPLogList.get(2).setIDL_TIM(tempList.get(3));
				this.tableDataPLogList.get(3).setIDL_TIM(tempList.get(4));
				this.tableDataPLogList.get(4).setIDL_TIM(tempList.get(5));
				this.tableDataPLogList.get(5).setIDL_TIM(tempList.get(6));
				this.tableDataPLogList.get(6).setIDL_TIM(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.BUR_WEAR)){
				this.tableDataPLogList.get(0).setBUR_WEAR(tempList.get(1));
				this.tableDataPLogList.get(1).setBUR_WEAR(tempList.get(2));
				this.tableDataPLogList.get(2).setBUR_WEAR(tempList.get(3));
				this.tableDataPLogList.get(3).setBUR_WEAR(tempList.get(4));
				this.tableDataPLogList.get(4).setBUR_WEAR(tempList.get(5));
				this.tableDataPLogList.get(5).setBUR_WEAR(tempList.get(6));
				this.tableDataPLogList.get(6).setBUR_WEAR(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.WR_WEAR)){
				this.tableDataPLogList.get(0).setWR_WEAR(tempList.get(1));
				this.tableDataPLogList.get(1).setWR_WEAR(tempList.get(2));
				this.tableDataPLogList.get(2).setWR_WEAR(tempList.get(3));
				this.tableDataPLogList.get(3).setWR_WEAR(tempList.get(4));
				this.tableDataPLogList.get(4).setWR_WEAR(tempList.get(5));
				this.tableDataPLogList.get(5).setWR_WEAR(tempList.get(6));
				this.tableDataPLogList.get(6).setWR_WEAR(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.WR_THRM)){
				this.tableDataPLogList.get(0).setWR_THRM(tempList.get(1));
				this.tableDataPLogList.get(1).setWR_THRM(tempList.get(2));
				this.tableDataPLogList.get(2).setWR_THRM(tempList.get(3));
				this.tableDataPLogList.get(3).setWR_THRM(tempList.get(4));
				this.tableDataPLogList.get(4).setWR_THRM(tempList.get(5));
				this.tableDataPLogList.get(5).setWR_THRM(tempList.get(6));
				this.tableDataPLogList.get(6).setWR_THRM(tempList.get(7));
			}
		}
	}
	
	public void updateTableData(){
		try{
			med.getTableViewerSlabPlateInfo().setLabelProvider(new TableViewerLabelProvider_SlabPlateInfo());
			med.getTableViewerSlabPlateInfo().setContentProvider(new ArrayContentProvider());
			med.getTableViewerSlabPlateInfo().setInput(this.tableDataSlabPlateInfoList);
			
			med.getTableViewerVariable().setLabelProvider(new TableViewerLabelProvider_Variable());
			med.getTableViewerVariable().setContentProvider(new ArrayContentProvider());
			med.getTableViewerVariable().setInput(this.tableDataVariableList);
			
			med.getTableViewerPLog().setLabelProvider(new TableViewerLabelProvider_PLog());
			med.getTableViewerPLog().setContentProvider(new ArrayContentProvider());
			med.getTableViewerPLog().setInput(this.tableDataPLogList);
			
		}catch (Exception e){
			String msg = "ERROR - Update Roll Table Data";
			msg = msg +"\n"+e.getMessage();
			System.out.println(msg);
		}
	}
	//
	//
	//=====================================================================
	
	//=====================================================================
	// Button - Apply
	//
	public void Apply(){
		System.out.println("Click Apply Button");
	}
	
}
