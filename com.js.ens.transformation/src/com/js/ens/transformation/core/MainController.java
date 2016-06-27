package com.js.ens.transformation.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.jfree.util.Log;

import com.js.ens.transformation.core.tableDatas.TableData_PLog;
import com.js.ens.transformation.core.tableDatas.TableData_SlabPlateInfo;
import com.js.ens.transformation.core.tableDatas.TableData_Variable;
import com.js.ens.transformation.core.tableDatas.TableRowData;
import com.js.ens.transformation.core.tableDatas.TableRowData_BEND;
import com.js.ens.transformation.core.tableDatas.TableRowData_BUR_BDIA;
import com.js.ens.transformation.core.tableDatas.TableRowData_BUR_TDIA;
import com.js.ens.transformation.core.tableDatas.TableRowData_BUR_WEAR;
import com.js.ens.transformation.core.tableDatas.TableRowData_ENTRY_TEMP;
import com.js.ens.transformation.core.tableDatas.TableRowData_ENTRY_THK;
import com.js.ens.transformation.core.tableDatas.TableRowData_EXIT_TEMP;
import com.js.ens.transformation.core.tableDatas.TableRowData_EXIT_THK;
import com.js.ens.transformation.core.tableDatas.TableRowData_FRCE;
import com.js.ens.transformation.core.tableDatas.TableRowData_IDL_TIM;
import com.js.ens.transformation.core.tableDatas.TableRowData_PAS_LINE;
import com.js.ens.transformation.core.tableDatas.TableRowData_P_CROSS;
import com.js.ens.transformation.core.tableDatas.TableRowData_ROL_GAP;
import com.js.ens.transformation.core.tableDatas.TableRowData_ROL_TIM;
import com.js.ens.transformation.core.tableDatas.TableRowData_SPEED;
import com.js.ens.transformation.core.tableDatas.TableRowData_STP_LEN;
import com.js.ens.transformation.core.tableDatas.TableRowData_STP_WID;
import com.js.ens.transformation.core.tableDatas.TableRowData_TENS;
import com.js.ens.transformation.core.tableDatas.TableRowData_TORQ;
import com.js.ens.transformation.core.tableDatas.TableRowData_WR_BDIA;
import com.js.ens.transformation.core.tableDatas.TableRowData_WR_ICRN;
import com.js.ens.transformation.core.tableDatas.TableRowData_WR_TDIA;
import com.js.ens.transformation.core.tableDatas.TableRowData_WR_THRM;
import com.js.ens.transformation.core.tableDatas.TableRowData_WR_WEAR;
import com.js.ens.transformation.dialog.ApplyDlg;
import com.js.ens.transformation.dialog.ExportDlg;
import com.js.ens.transformation.dialog.NewDlg;
import com.js.ens.transformation.dialog.OpenDlg;
import com.js.ens.transformation.dialog.ResultDlg;
import com.js.ens.transformation.dialog.SaveAsDlg;
import com.js.ens.transformation.dialog.SaveDlg;
import com.js.ens.transformation.dialog.MessageDlg;
import com.js.ens.transformation.proc.ProcMaker;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class MainController {
	
	private Mediator med = Mediator.getInstance();
	
	private static MainController instance = new MainController();
	public static MainController getInstance(){
		return instance;
	}
	//--------------
	// common
	private ArrayList<Boolean> ApplyResult = new ArrayList<Boolean>(); 
	
	private String modelName = null;
	private String workspace = null;
	private String exportResult = null;
	//--------------
	// tab1
	private String PLogFilePath = null;
	private ArrayList<String> PLogFileDataList = null;
	
	private TableData_SlabPlateInfo TableDataSlabPlateInfoObj = null;
	private ArrayList<TableData_SlabPlateInfo> tableDataSlabPlateInfoList = null;
	//private Map<String,String> tableDataSlabPlateInfoMap = null;
	
	private TableData_Variable TableDataVariableObj = null;
	private ArrayList<TableData_Variable> tableDataVariableList = null;
	//private Map<String,String> tableDataVariableMap = null;
	
	// tableDataPLogList 는 F1 ~ F7 까지의 데이터 7개 obj가 저장됨
	//private TableData_common  tabeDataCommon = null;
	private ArrayList<TableData_PLog> tableDataPLogList = null;
	//private Map<String,String> tableDataPLogMap; 
	private ArrayList<TableRowData> tableRowDataList = null;
	
	//--------------
	// tab2
	private String StandValue = "F1";
	//private ArrayList<TableData_PLog> F_ObjList = new ArrayList<TableData_PLog>();
	
	
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
			this.parsingPLogFile();
		}
	}
	
	private void parsingPLogFile(){
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
			
			this.updateTableData();
		}else{
		}
	}
	
	private void parsingPLogFile(ArrayList<String> initDataList){
		TableColumnLabel tclObj = new TableColumnLabel();
		tclObj.readTableColumnLabelFile();
		
		initTableData_SlabPlateInfo(initDataList,tclObj);
		initTableData_Variable(initDataList,tclObj);
		initTableData_PLog(initDataList,tclObj);
		
		this.updateTableData();
	}

	private void initTableData_SlabPlateInfo(ArrayList<String> fileDataList,TableColumnLabel tclObj){
		// line 0~1 => column line0, data line1
		int lineNumber = 0;
		String data = "";
		for(String line:fileDataList){
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
	
	private void initTableData_Variable(ArrayList<String> fileDataList,TableColumnLabel tclObj){
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
	
	private void initTableData_PLog(ArrayList<String> fileDataList,TableColumnLabel tclObj){
		// line 7~31 => column 6, data line 7~30
		int lineNumber = 0;
		ArrayList<String> dataList = new ArrayList<String>();

		//CSV 파일에서 PLog 테이블 라인 수 찾기
		for(String line:fileDataList){
			ArrayList<String> tempList = ParserDefault.splitLineData(line, ",");
			if(tempList.size()!=0){
				if(tempList.get(0).toLowerCase().equals(tclObj.getTableColumnLabel(TableColumnLabel.CL3_0).toLowerCase())){
					break;
				}
			}
			lineNumber++;
		}
		
		// dataList에 PLog의 Line 데이터 저장
		for(int i = lineNumber+1 ; i<lineNumber+25;i++){
			dataList.add(fileDataList.get(i));
		}
		this.createPLogObj(dataList);

		
		// UI에 표시한 데이터 형식으로 재 가공
		this.createTableRowData();
	}
	
	//F1~F7 Obj 만들기
	private void createPLogObj(ArrayList<String> dataList){
		// dataList 는 CSV 의 PLog데이터만 넘어옴
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
	
	//PLog 테이블 용 데이터 만들기
	private void createTableRowData(){
		//1
		TableRowData_BUR_TDIA r1Obj = new TableRowData_BUR_TDIA();
		r1Obj.setAllData(this.tableDataPLogList);
		//2
		TableRowData_BUR_BDIA r2Obj = new TableRowData_BUR_BDIA();
		r2Obj.setAllData(this.tableDataPLogList);
		//3
		TableRowData_WR_TDIA r3Obj = new TableRowData_WR_TDIA();
		r3Obj.setAllData(this.tableDataPLogList);
		//4
		TableRowData_WR_BDIA r4Obj = new TableRowData_WR_BDIA();
		r4Obj.setAllData(this.tableDataPLogList);
		//5
		TableRowData_WR_ICRN r5Obj = new TableRowData_WR_ICRN();
		r5Obj.setAllData(this.tableDataPLogList);
		//6
		TableRowData_ENTRY_THK r6Obj = new TableRowData_ENTRY_THK();
		r6Obj.setAllData(this.tableDataPLogList);
		//7
		TableRowData_EXIT_THK r7Obj = new TableRowData_EXIT_THK();
		r7Obj.setAllData(this.tableDataPLogList);
		//8
		TableRowData_PAS_LINE r8Obj = new TableRowData_PAS_LINE();
		r8Obj.setAllData(this.tableDataPLogList);
		//9
		TableRowData_ROL_GAP r9Obj = new TableRowData_ROL_GAP();
		r9Obj.setAllData(this.tableDataPLogList);
		//10
		TableRowData_STP_WID r10Obj = new TableRowData_STP_WID();
		r10Obj.setAllData(this.tableDataPLogList);
		//11
		TableRowData_STP_LEN r11Obj = new TableRowData_STP_LEN();
		r11Obj.setAllData(this.tableDataPLogList);
		//12
		TableRowData_ENTRY_TEMP r12Obj = new TableRowData_ENTRY_TEMP();
		r12Obj.setAllData(this.tableDataPLogList);
		//13
		TableRowData_EXIT_TEMP r13Obj = new TableRowData_EXIT_TEMP();
		r13Obj.setAllData(this.tableDataPLogList);
		//14
		TableRowData_FRCE r14Obj = new TableRowData_FRCE();
		r14Obj.setAllData(this.tableDataPLogList);
		//15
		TableRowData_TORQ r15Obj = new TableRowData_TORQ();
		r15Obj.setAllData(this.tableDataPLogList);
		//16
		TableRowData_SPEED r16Obj = new TableRowData_SPEED();
		r16Obj.setAllData(this.tableDataPLogList);
		//17
		TableRowData_BEND r17Obj = new TableRowData_BEND();
		r17Obj.setAllData(this.tableDataPLogList);
		//18
		TableRowData_P_CROSS r18Obj = new TableRowData_P_CROSS();
		r18Obj.setAllData(this.tableDataPLogList);
		//19
		TableRowData_TENS r19Obj = new TableRowData_TENS();
		r19Obj.setAllData(this.tableDataPLogList);
		//20
		TableRowData_ROL_TIM r20Obj = new TableRowData_ROL_TIM();
		r20Obj.setAllData(this.tableDataPLogList);
		//21
		TableRowData_IDL_TIM r21Obj = new TableRowData_IDL_TIM();
		r21Obj.setAllData(this.tableDataPLogList);
		//22
		TableRowData_BUR_WEAR r22Obj = new TableRowData_BUR_WEAR();
		r22Obj.setAllData(this.tableDataPLogList);
		//23
		TableRowData_WR_WEAR r23Obj = new TableRowData_WR_WEAR();
		r23Obj.setAllData(this.tableDataPLogList);
		//24
		TableRowData_WR_THRM r24Obj = new TableRowData_WR_THRM();
		r24Obj.setAllData(this.tableDataPLogList);
		
		this.tableRowDataList = new ArrayList<TableRowData>();
		this.tableRowDataList.add(r1Obj);
		this.tableRowDataList.add(r2Obj);
		this.tableRowDataList.add(r3Obj);
		this.tableRowDataList.add(r4Obj);
		this.tableRowDataList.add(r5Obj);
		this.tableRowDataList.add(r6Obj);
		this.tableRowDataList.add(r7Obj);
		this.tableRowDataList.add(r8Obj);
		this.tableRowDataList.add(r9Obj);
		this.tableRowDataList.add(r10Obj);
		this.tableRowDataList.add(r11Obj);
		this.tableRowDataList.add(r12Obj);
		this.tableRowDataList.add(r13Obj);
		this.tableRowDataList.add(r14Obj);
		this.tableRowDataList.add(r15Obj);
		this.tableRowDataList.add(r16Obj);
		this.tableRowDataList.add(r17Obj);
		this.tableRowDataList.add(r18Obj);
		this.tableRowDataList.add(r19Obj);
		this.tableRowDataList.add(r20Obj);
		this.tableRowDataList.add(r21Obj);
		this.tableRowDataList.add(r22Obj);
		this.tableRowDataList.add(r23Obj);
		this.tableRowDataList.add(r24Obj);
	}
	
	// Table에 데이터 삽입 
	private void updateTableData(){
		try{
			med.getTableViewerSlabPlateInfo().setLabelProvider(new TableViewerLabelProvider_SlabPlateInfo());
			med.getTableViewerSlabPlateInfo().setContentProvider(new ArrayContentProvider());
			med.getTableViewerSlabPlateInfo().setInput(this.tableDataSlabPlateInfoList);
			
			med.getTableViewerVariable().setLabelProvider(new TableViewerLabelProvider_Variable());
			med.getTableViewerVariable().setContentProvider(new ArrayContentProvider());
			med.getTableViewerVariable().setInput(this.tableDataVariableList);
			
			med.getTableViewerPLog().setLabelProvider(new TableViewerLabelProvider_PLog());
			med.getTableViewerPLog().setContentProvider(new ArrayContentProvider());
			med.getTableViewerPLog().setInput(this.tableRowDataList);
			
		}catch (Exception e){
			String msg = "ERROR - Update Table Data";
			msg = msg +"\n"+e.getMessage();
			Log.error(msg);
		}
	}
	
	//
	//
	//=====================================================================
	
	//=====================================================================
	// Button - Export P Log 
	//
	public void ExportPLog(){
		ArrayList<String> initDataList = new ArrayList<String>();
		initDataList.add("STRIP NO,STHK,SWID,SLEN,SWET,PTHK,PWID,PLEN,PWET,,,,,,,");
		String line2 = TableDataSlabPlateInfoObj.getSTRIP_NO()+","+
				TableDataSlabPlateInfoObj.getSTHK()+","+
				TableDataSlabPlateInfoObj.getSWID()+","+
				TableDataSlabPlateInfoObj.getSLEN()+","+
				TableDataSlabPlateInfoObj.getSWET()+","+
				TableDataSlabPlateInfoObj.getPTHK()+","+
				TableDataSlabPlateInfoObj.getPWID()+","+
				TableDataSlabPlateInfoObj.getPLEN()+","+
				TableDataSlabPlateInfoObj.getPWET()+","+
				",,,,,,";
		initDataList.add(line2);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16");
		String line5 = TableDataVariableObj.getVAR1()+","+
				TableDataVariableObj.getVAR2()+","+
				TableDataVariableObj.getVAR3()+","+
				TableDataVariableObj.getVAR4()+","+
				TableDataVariableObj.getVAR5()+","+
				TableDataVariableObj.getVAR6()+","+
				TableDataVariableObj.getVAR7()+","+
				TableDataVariableObj.getVAR8()+","+
				TableDataVariableObj.getVAR9()+","+
				TableDataVariableObj.getVAR10()+","+
				TableDataVariableObj.getVAR11()+","+
				TableDataVariableObj.getVAR12()+","+
				TableDataVariableObj.getVAR13()+","+
				TableDataVariableObj.getVAR14()+","+
				TableDataVariableObj.getVAR15()+","+
				TableDataVariableObj.getVAR16();
		initDataList.add(line5);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("STAND,F1,F2,F3,F4,F5,F6,F7,,,,,,,,");
		String line8 = "BUR TDIA"+","+
				tableDataPLogList.get(0).getBUR_TDIA()+","+
				tableDataPLogList.get(1).getBUR_TDIA()+","+
				tableDataPLogList.get(2).getBUR_TDIA()+","+
				tableDataPLogList.get(3).getBUR_TDIA()+","+
				tableDataPLogList.get(4).getBUR_TDIA()+","+
				tableDataPLogList.get(5).getBUR_TDIA()+","+
				tableDataPLogList.get(6).getBUR_TDIA()+","+
				",,,,,,,";
		initDataList.add(line8);
		String line9 = "BUR BDIA"+","+
				tableDataPLogList.get(0).getBUR_BDIA()+","+
				tableDataPLogList.get(1).getBUR_BDIA()+","+
				tableDataPLogList.get(2).getBUR_BDIA()+","+
				tableDataPLogList.get(3).getBUR_BDIA()+","+
				tableDataPLogList.get(4).getBUR_BDIA()+","+
				tableDataPLogList.get(5).getBUR_BDIA()+","+
				tableDataPLogList.get(6).getBUR_BDIA()+","+
				",,,,,,,";
		initDataList.add(line9);
		String line10 = "WR TDIA"+","+
				tableDataPLogList.get(0).getWR_TDIA()+","+
				tableDataPLogList.get(1).getWR_TDIA()+","+
				tableDataPLogList.get(2).getWR_TDIA()+","+
				tableDataPLogList.get(3).getWR_TDIA()+","+
				tableDataPLogList.get(4).getWR_TDIA()+","+
				tableDataPLogList.get(5).getWR_TDIA()+","+
				tableDataPLogList.get(6).getWR_TDIA()+","+
				",,,,,,,";
		initDataList.add(line10);
		String line11 = "WR BDIA"+","+
				tableDataPLogList.get(0).getWR_BDIA()+","+
				tableDataPLogList.get(1).getWR_BDIA()+","+
				tableDataPLogList.get(2).getWR_BDIA()+","+
				tableDataPLogList.get(3).getWR_BDIA()+","+
				tableDataPLogList.get(4).getWR_BDIA()+","+
				tableDataPLogList.get(5).getWR_BDIA()+","+
				tableDataPLogList.get(6).getWR_BDIA()+","+
				",,,,,,,";
		initDataList.add(line11);
		String line12 = "WR ICRN"+","+
				tableDataPLogList.get(0).getWR_ICRN()+","+
				tableDataPLogList.get(1).getWR_ICRN()+","+
				tableDataPLogList.get(2).getWR_ICRN()+","+
				tableDataPLogList.get(3).getWR_ICRN()+","+
				tableDataPLogList.get(4).getWR_ICRN()+","+
				tableDataPLogList.get(5).getWR_ICRN()+","+
				tableDataPLogList.get(6).getWR_ICRN()+","+
				",,,,,,,";
		initDataList.add(line12);
		String line13 = "ENTRY THK"+","+
				tableDataPLogList.get(0).getENTRY_THK()+","+
				tableDataPLogList.get(1).getENTRY_THK()+","+
				tableDataPLogList.get(2).getENTRY_THK()+","+
				tableDataPLogList.get(3).getENTRY_THK()+","+
				tableDataPLogList.get(4).getENTRY_THK()+","+
				tableDataPLogList.get(5).getENTRY_THK()+","+
				tableDataPLogList.get(6).getENTRY_THK()+","+
				",,,,,,,";
		initDataList.add(line13);
		String line14 = "EXIT THK"+","+
				tableDataPLogList.get(0).getEXIT_THK()+","+
				tableDataPLogList.get(1).getEXIT_THK()+","+
				tableDataPLogList.get(2).getEXIT_THK()+","+
				tableDataPLogList.get(3).getEXIT_THK()+","+
				tableDataPLogList.get(4).getEXIT_THK()+","+
				tableDataPLogList.get(5).getEXIT_THK()+","+
				tableDataPLogList.get(6).getEXIT_THK()+","+
				",,,,,,,";
		initDataList.add(line14);
		String line15 = "PAS LINE"+","+
				tableDataPLogList.get(0).getPAS_LINE()+","+
				tableDataPLogList.get(1).getPAS_LINE()+","+
				tableDataPLogList.get(2).getPAS_LINE()+","+
				tableDataPLogList.get(3).getPAS_LINE()+","+
				tableDataPLogList.get(4).getPAS_LINE()+","+
				tableDataPLogList.get(5).getPAS_LINE()+","+
				tableDataPLogList.get(6).getPAS_LINE()+","+
				",,,,,,,";
		initDataList.add(line15);
		String line16 = "ROL GAP"+","+
				tableDataPLogList.get(0).getROL_GAP()+","+
				tableDataPLogList.get(1).getROL_GAP()+","+
				tableDataPLogList.get(2).getROL_GAP()+","+
				tableDataPLogList.get(3).getROL_GAP()+","+
				tableDataPLogList.get(4).getROL_GAP()+","+
				tableDataPLogList.get(5).getROL_GAP()+","+
				tableDataPLogList.get(6).getROL_GAP()+","+
				",,,,,,,";
		initDataList.add(line16);
		String line17 = "STP WID"+","+
				tableDataPLogList.get(0).getSTP_WID()+","+
				tableDataPLogList.get(1).getSTP_WID()+","+
				tableDataPLogList.get(2).getSTP_WID()+","+
				tableDataPLogList.get(3).getSTP_WID()+","+
				tableDataPLogList.get(4).getSTP_WID()+","+
				tableDataPLogList.get(5).getSTP_WID()+","+
				tableDataPLogList.get(6).getSTP_WID()+","+
				",,,,,,,";
		initDataList.add(line17);
		String line18 = "STP LEN"+","+
				tableDataPLogList.get(0).getSTP_LEN()+","+
				tableDataPLogList.get(1).getSTP_LEN()+","+
				tableDataPLogList.get(2).getSTP_LEN()+","+
				tableDataPLogList.get(3).getSTP_LEN()+","+
				tableDataPLogList.get(4).getSTP_LEN()+","+
				tableDataPLogList.get(5).getSTP_LEN()+","+
				tableDataPLogList.get(6).getSTP_LEN()+","+
				",,,,,,,";
		initDataList.add(line18);
		String line19 = "ENTRY TEMP"+","+
				tableDataPLogList.get(0).getSTP_LEN()+","+
				tableDataPLogList.get(1).getSTP_LEN()+","+
				tableDataPLogList.get(2).getSTP_LEN()+","+
				tableDataPLogList.get(3).getSTP_LEN()+","+
				tableDataPLogList.get(4).getSTP_LEN()+","+
				tableDataPLogList.get(5).getSTP_LEN()+","+
				tableDataPLogList.get(6).getSTP_LEN()+","+
				",,,,,,,";
		initDataList.add(line19);
		String line20 = "EXIT TEMP"+","+
				tableDataPLogList.get(0).getEXIT_TEMP()+","+
				tableDataPLogList.get(1).getEXIT_TEMP()+","+
				tableDataPLogList.get(2).getEXIT_TEMP()+","+
				tableDataPLogList.get(3).getEXIT_TEMP()+","+
				tableDataPLogList.get(4).getEXIT_TEMP()+","+
				tableDataPLogList.get(5).getEXIT_TEMP()+","+
				tableDataPLogList.get(6).getEXIT_TEMP()+","+
				",,,,,,,";
		initDataList.add(line20);
		String line21 = "FRCE"+","+
				tableDataPLogList.get(0).getFRCE()+","+
				tableDataPLogList.get(1).getFRCE()+","+
				tableDataPLogList.get(2).getFRCE()+","+
				tableDataPLogList.get(3).getFRCE()+","+
				tableDataPLogList.get(4).getFRCE()+","+
				tableDataPLogList.get(5).getFRCE()+","+
				tableDataPLogList.get(6).getFRCE()+","+					
				",,,,,,,";
		initDataList.add(line21);
		String line22 = "TORQ"+","+
				tableDataPLogList.get(0).getTORQ()+","+					
				tableDataPLogList.get(1).getTORQ()+","+
				tableDataPLogList.get(2).getTORQ()+","+
				tableDataPLogList.get(3).getTORQ()+","+
				tableDataPLogList.get(4).getTORQ()+","+
				tableDataPLogList.get(5).getTORQ()+","+
				tableDataPLogList.get(6).getTORQ()+","+
				",,,,,,,";
		initDataList.add(line22);
		String line23 = "SPEED(mpm)"+","+
				tableDataPLogList.get(0).getSPEED()+","+
				tableDataPLogList.get(1).getSPEED()+","+
				tableDataPLogList.get(2).getSPEED()+","+
				tableDataPLogList.get(3).getSPEED()+","+
				tableDataPLogList.get(4).getSPEED()+","+
				tableDataPLogList.get(5).getSPEED()+","+
				tableDataPLogList.get(6).getSPEED()+","+					
				",,,,,,,";
		initDataList.add(line23);
		String line24 = "BEND"+","+
				tableDataPLogList.get(0).getBEND()+","+
				tableDataPLogList.get(1).getBEND()+","+
				tableDataPLogList.get(2).getBEND()+","+
				tableDataPLogList.get(3).getBEND()+","+
				tableDataPLogList.get(4).getBEND()+","+
				tableDataPLogList.get(5).getBEND()+","+
				tableDataPLogList.get(6).getBEND()+","+
				",,,,,,,";
		initDataList.add(line24);
		String line25 = "P-CROSS"+","+
				tableDataPLogList.get(0).getP_CROSS()+","+
				tableDataPLogList.get(1).getP_CROSS()+","+
				tableDataPLogList.get(2).getP_CROSS()+","+
				tableDataPLogList.get(3).getP_CROSS()+","+
				tableDataPLogList.get(4).getP_CROSS()+","+
				tableDataPLogList.get(5).getP_CROSS()+","+
				tableDataPLogList.get(6).getP_CROSS()+","+
				",,,,,,,";
		initDataList.add(line25);
		String line26 = "TENS"+","+
				tableDataPLogList.get(0).getTENS()+","+
				tableDataPLogList.get(1).getTENS()+","+
				tableDataPLogList.get(2).getTENS()+","+
				tableDataPLogList.get(3).getTENS()+","+
				tableDataPLogList.get(4).getTENS()+","+
				tableDataPLogList.get(5).getTENS()+","+
				tableDataPLogList.get(6).getTENS()+","+
				",,,,,,,";
		initDataList.add(line26);
		String line27 = "ROL TIM"+","+
				tableDataPLogList.get(0).getROL_TIM()+","+
				tableDataPLogList.get(1).getROL_TIM()+","+
				tableDataPLogList.get(2).getROL_TIM()+","+
				tableDataPLogList.get(3).getROL_TIM()+","+
				tableDataPLogList.get(4).getROL_TIM()+","+
				tableDataPLogList.get(5).getROL_TIM()+","+
				tableDataPLogList.get(6).getROL_TIM()+","+					
				",,,,,,,";
		initDataList.add(line27);
		String line28 = "IDL TIM"+","+
				tableDataPLogList.get(0).getIDL_TIM()+","+
				tableDataPLogList.get(1).getIDL_TIM()+","+
				tableDataPLogList.get(2).getIDL_TIM()+","+
				tableDataPLogList.get(3).getIDL_TIM()+","+
				tableDataPLogList.get(4).getIDL_TIM()+","+
				tableDataPLogList.get(5).getIDL_TIM()+","+
				tableDataPLogList.get(6).getIDL_TIM()+","+
				",,,,,,,";
		initDataList.add(line28);
		String line29 = "BUR WEAR"+","+
				tableDataPLogList.get(0).getBUR_WEAR()+","+
				tableDataPLogList.get(1).getBUR_WEAR()+","+
				tableDataPLogList.get(2).getBUR_WEAR()+","+
				tableDataPLogList.get(3).getBUR_WEAR()+","+
				tableDataPLogList.get(4).getBUR_WEAR()+","+
				tableDataPLogList.get(5).getBUR_WEAR()+","+
				tableDataPLogList.get(6).getBUR_WEAR()+","+					
				",,,,,,,";
		initDataList.add(line29);
		String line30 = "WR WEAR"+","+ 
				tableDataPLogList.get(0).getWR_WEAR()+","+
				tableDataPLogList.get(1).getWR_WEAR()+","+
				tableDataPLogList.get(2).getWR_WEAR()+","+
				tableDataPLogList.get(3).getWR_WEAR()+","+
				tableDataPLogList.get(4).getWR_WEAR()+","+
				tableDataPLogList.get(5).getWR_WEAR()+","+
				tableDataPLogList.get(6).getWR_WEAR()+","+
				",,,,,,,";
		initDataList.add(line30);
		String line31 = "WR THRM"+","+
				tableDataPLogList.get(0).getWR_THRM()+","+
				tableDataPLogList.get(1).getWR_THRM()+","+
				tableDataPLogList.get(2).getWR_THRM()+","+
				tableDataPLogList.get(3).getWR_THRM()+","+
				tableDataPLogList.get(4).getWR_THRM()+","+
				tableDataPLogList.get(5).getWR_THRM()+","+
				tableDataPLogList.get(6).getWR_THRM()+","+					
				",,,,,,,";
		initDataList.add(line31);
		String csvPath = myUtil.setPath(this.workspace, this.modelName +".csv");
		Writer obj = new Writer(csvPath,initDataList);
		obj.running();
	}

	//
	//
	//=====================================================================
	
	
	//=====================================================================
	// Button - Apply
	//
	public void Apply(){
		ApplyDlg applyDlg = new ApplyDlg(Display.getCurrent().getActiveShell());
		applyDlg.open();
	}
	
	public void RunApplyResult(ArrayList<Boolean> result){
		this.ApplyResult.clear();
		this.ApplyResult = result;
		for(int i=0;i<7;i++){
			if(ApplyResult.get(i)){
				if(i == 0) makeResultF1();
				if(i == 1) makeResultF2();
				if(i == 2) makeResultF3();
				if(i == 3) makeResultF4();
				if(i == 4) makeResultF5();
				if(i == 5) makeResultF6();
				if(i == 6) makeResultF7();
			}
		}
		// DB File 저장
		this.RunSaveProject();
		// CSV 파일 저장
		this.ExportPLog();
		
		this.checkResultFiles_All();
		
		if(myUtil.checkOS().equals("window")){
			try{
				String procPath = myUtil.setPath(this.workspace, "proc");
				Runtime.getRuntime().exec("explorer "+ procPath);
			}catch(Exception e){
				String msg2 = "ERROR - Open Proc Folder";
				MessageDlg messageDlg2 = new MessageDlg(Display.getCurrent().getActiveShell(),msg2);
				messageDlg2.open();
			}
		}
	}
	
	private void checkResultFiles_All(){
		this.exportResult = "";
		this.exportResult = "[Export Result]"+"\n";
		String userConfigPath = myUtil.setPath(System.getProperty("user.dir"), "userConfig");
		
		for(int i=0;i<7;i++){
			if(ApplyResult.get(i)){
				if(i == 0){
					this.exportResult += "* F1"+"\n";
					String fileListPath = myUtil.setPath(userConfigPath, "f1.filelist");
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f1.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F1),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 1){
					String fileListPath = myUtil.setPath(userConfigPath, "f2.filelist");
					this.exportResult += "* F2"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f2.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F2),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 2){
					String fileListPath = myUtil.setPath(userConfigPath, "f3.filelist");
					this.exportResult += "* F3"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f3.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F3),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 3){
					String fileListPath = myUtil.setPath(userConfigPath, "f4.filelist");
					this.exportResult += "* F4"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f4.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F4),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 4){
					String fileListPath = myUtil.setPath(userConfigPath, "f5.filelist");
					this.exportResult += "* F5"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f5.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F5),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 5){
					String fileListPath = myUtil.setPath(userConfigPath, "f6.filelist");
					this.exportResult += "* F6"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f6.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F6),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 6){
					String fileListPath = myUtil.setPath(userConfigPath, "f7.filelist");
					this.exportResult += "* F7"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f7.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F7),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}
			}
		}
		
		String dbFile = myUtil.setPath(this.workspace, this.modelName+".ens");
		this.exportResult += "* DB file"+"\n";
		if(myUtil.checkPath(dbFile)){
			this.exportResult += "SUCCESS - "+dbFile+"\n";
		}else{
			this.exportResult += "FAIL - "+dbFile+"\n";
		}
		
		String csvFile = myUtil.setPath(this.workspace, this.modelName+".csv");
		this.exportResult +="* P Log file"+"\n";
		if(myUtil.checkPath(csvFile)){
			this.exportResult += "SUCCESS - "+csvFile+"\n";
		}else{
			this.exportResult += "FAIL - "+csvFile+"\n";
		}
		
		MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),exportResult);
		messageDlg.open();
	}
	
	private void makeResultF1(){
		ProcMaker procObj1 = new ProcMaker(UILabel.F1);
		procObj1.running();		
	}
	private void makeResultF2(){
		ProcMaker procObj2 = new ProcMaker(UILabel.F2);
		procObj2.running();
	}
	private void makeResultF3(){
		ProcMaker procObj3 = new ProcMaker(UILabel.F3);
		procObj3.running();
	}
	private void makeResultF4(){
		ProcMaker procObj4 = new ProcMaker(UILabel.F4);
		procObj4.running();
	}
	private void makeResultF5(){
		ProcMaker procObj5 = new ProcMaker(UILabel.F5);
		procObj5.running();
	}
	private void makeResultF6(){
		ProcMaker procObj6 = new ProcMaker(UILabel.F6);
		procObj6.running();
	}
	private void makeResultF7(){
		ProcMaker procObj7 = new ProcMaker(UILabel.F7);
		procObj7.running();
	}
	//
	// 
	//=====================================================================

	//=====================================================================
	// FileMenu - New Project
	//
	public void NewProject(){
		NewDlg newDlg = new NewDlg(Display.getCurrent().getActiveShell());
		newDlg.open();
	}
	
	public void RunNewProject(String modelName, String workspace){
		// NewDlg -> MC
		
		this.modelName = modelName;
		String topFolder = myUtil.setPath(workspace, this.modelName);
		String procFolder = myUtil.setPath(topFolder, "proc");
		String resultFolder = myUtil.setPath(topFolder, "result");
		this.workspace = topFolder;
		
		if(!myUtil.makeDir(topFolder)) System.out.println("[Model Name:"+modelName +"] topFolder did not make.");
		if(!myUtil.makeDir(procFolder)) System.out.println("[Model Name:"+modelName +"] prodFolder did not make.");
		if(!myUtil.makeDir(resultFolder)) System.out.println("[Model Name:"+modelName +"] resultFolder did not make.");
		
		this.setUpDataSheet();
		this.AllComponentEnable();
		this.InitComponentValue();
		
		med.getLblModelNameValue().setText(this.modelName);
		med.getLblWorkspacePath().setText(this.workspace);
		
	}
	
	private void AllComponentEnable(){
		med.getTabFolder().setEnabled(true);
		med.getBtnApply().setEnabled(true);
	}
	
	private void CleanAllData(){
		this.setUpDataSheet();
		this.AllComponentEnable();
		this.InitComponentValue();
	}
	
	//F1~F7에서 입력해서 데이터 저장할때 Obj 생성하는 곳
	private void setUpDataSheet(){
		this.tableDataPLogList = new ArrayList<TableData_PLog>();
		for(int i=0;i<7;i++){
			TableData_PLog obj = new TableData_PLog();
			obj.setSTAND("F"+(i+1));
			this.tableDataPLogList.add(obj);
		}
	}
	
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Open Project
	//
	public void OpenProject(){
		OpenDlg openDlg = new OpenDlg(Display.getCurrent().getActiveShell());
		openDlg.open();
	}
	
	public void RunOpenProject(String DBFilePath){
		//System.out.println(DBFilePath);
		this.CleanAllData();	//InitValue로 초기화
		this.readDBFile(DBFilePath);
		
	}
	
	private void readDBFile(String DBFilePath){
		
		String path = myUtil.getParrentPath(DBFilePath);
		String fileName = myUtil.getFileName(DBFilePath);
		this.modelName = ParserDefault.splitLineData(fileName, "\\.").get(0);
		
		if(myUtil.getFileName(path).equals(this.modelName)){
			String procFolder = myUtil.setPath(path, "proc");
			String resultFolder = myUtil.setPath(path, "result");
			
			if(!myUtil.checkPath(procFolder)){
				myUtil.makeDir(procFolder);
			}
			if(!myUtil.checkPath(resultFolder)){
				myUtil.makeDir(resultFolder);
			}
			
			this.workspace = path;
			System.out.println(this.workspace);
			
			med.getLblModelNameValue().setText(this.modelName);
			med.getLblWorkspacePath().setText(this.workspace);
			setAllDataUI(DBFilePath);
			AllComponentEnable();
			
		}else {
			String topFolder =  myUtil.setPath(path, this.modelName);
			String procFolder = myUtil.setPath(topFolder, "proc");
			String resultFolder = myUtil.setPath(topFolder, "result");
			String newDBFilePath = myUtil.setPath(topFolder, this.modelName+".ens");
			this.workspace = topFolder;
			
			myUtil.makeDir(topFolder);
			myUtil.makeDir(procFolder);
			myUtil.makeDir(resultFolder);
			myUtil.fileCopy(DBFilePath, newDBFilePath);
			
			med.getLblModelNameValue().setText(this.modelName);
			med.getLblWorkspacePath().setText(this.workspace);
			setAllDataUI(newDBFilePath);
			AllComponentEnable();
		}
	}
	
	private void setAllDataUI(String DBFilePath){
		
		Reader obj = new Reader(DBFilePath);
		obj.running();

		Map<String,String> openDBMap = new HashMap<String,String>();
		ArrayList<String> parsingDataList;
		
		for(String line : obj.getFileDataList()){
			parsingDataList = ParserDefault.splitLineData(line, "=");
			if(parsingDataList.size()==1){
				openDBMap.put(parsingDataList.get(0), " ");
			}else{
				openDBMap.put(parsingDataList.get(0), parsingDataList.get(1));
			}
		}
		
		
		for(TableData_PLog Pobj : this.tableDataPLogList){
			Pobj.setWR_TDIA(openDBMap.get(UILabel.Top_WR_Diameter+"_"+Pobj.getSTAND()));
			Pobj.setWR_BDIA(openDBMap.get(UILabel.Bottom_WR_Diameter+"_"+Pobj.getSTAND()));
			Pobj.setWR_ICRN(openDBMap.get(UILabel.WR_Crown+"_"+Pobj.getSTAND()));
			Pobj.setWr_len(openDBMap.get(UILabel.WR_Length+"_"+Pobj.getSTAND()));
			Pobj.setWr_div_angle(openDBMap.get(UILabel.WR_Mesh_Angle+"_"+Pobj.getSTAND()));
			
			Pobj.setBUR_TDIA(openDBMap.get(UILabel.Top_BUR_Diameter+"_"+Pobj.getSTAND()));
			Pobj.setBUR_BDIA(openDBMap.get(UILabel.Bottom_BUR_Diameter+"_"+Pobj.getSTAND()));
			Pobj.setBur_len(openDBMap.get(UILabel.BUR_Length+"_"+Pobj.getSTAND()));
			Pobj.setBur_div_angle(openDBMap.get(UILabel.BUR_Mesh_Angle+"_"+Pobj.getSTAND()));
			
			Pobj.setENTRY_THK(openDBMap.get(UILabel.Thickness+"_"+Pobj.getSTAND()));
			Pobj.setSTP_WID(openDBMap.get(UILabel.Width+"_"+Pobj.getSTAND()));
			Pobj.setSTP_LEN(openDBMap.get(UILabel.Length+"_"+Pobj.getSTAND()));
			Pobj.setENTRY_TEMP(openDBMap.get(UILabel.Entry_Temperature+"_"+Pobj.getSTAND()));
			Pobj.setEXIT_TEMP(openDBMap.get(UILabel.Exit_Temperature+"_"+Pobj.getSTAND()));
			Pobj.setP_in(openDBMap.get(UILabel.Initial_Position+"_"+Pobj.getSTAND()));
			Pobj.setPl_m(openDBMap.get(UILabel.Mesh_Length+"_"+Pobj.getSTAND()));
			Pobj.setT_div(openDBMap.get(UILabel.Thickness_Mesh_Divisions+"_"+Pobj.getSTAND()));
			
			Pobj.setSPEED(openDBMap.get(UILabel.Velocity+"_"+Pobj.getSTAND()));
			Pobj.setROL_GAP(openDBMap.get(UILabel.Roll_Gap+"_"+Pobj.getSTAND()));
			Pobj.setPAS_LINE(openDBMap.get(UILabel.Pass_Line+"_"+Pobj.getSTAND()));
			Pobj.setP_CROSS(openDBMap.get(UILabel.Pair_Cross_Angle+"_"+Pobj.getSTAND()));
			Pobj.setBEND(openDBMap.get(UILabel.Bender_Force+"_"+Pobj.getSTAND()));
			Pobj.setTORQ(openDBMap.get(UILabel.Roll_Torque+"_"+Pobj.getSTAND()));
			Pobj.setTENS(openDBMap.get(UILabel.Tension_Stress+"_"+Pobj.getSTAND()));
			Pobj.setF_r2p(openDBMap.get(UILabel.Roll_to_Plate_Frict_Coef+"_"+Pobj.getSTAND()));
			Pobj.setF_r2r(openDBMap.get(UILabel.Roll_to_Roll_Fric_Coef+"_"+Pobj.getSTAND()));
			
			Pobj.setTb_vel_rate(openDBMap.get(UILabel.Top_Bot_Vel_Rete+"_"+Pobj.getSTAND()));
			Pobj.setWr_trot(openDBMap.get(UILabel.Top_WR_Rot_Vel_RPM+"_"+Pobj.getSTAND()));
			Pobj.setWr_brot(openDBMap.get(UILabel.Bottom_BUR_Rot_Vel_RPM+"_"+Pobj.getSTAND()));
			Pobj.setBur_trot(openDBMap.get(UILabel.Top_BUR_Rot_Vel_RPM+"_"+Pobj.getSTAND()));
			Pobj.setBur_brot(openDBMap.get(UILabel.Bottom_BUR_Rot_Vel_RPM+"_"+Pobj.getSTAND()));
			
			Pobj.setYM_Constant(openDBMap.get("YM_Constant"+"_"+Pobj.getSTAND()));
			Pobj.setYM_Table(openDBMap.get("YM_Table"+"_"+Pobj.getSTAND()));
			Pobj.setYM_Value(openDBMap.get(UILabel.Youngs_Modulus+"_"+Pobj.getSTAND()));
			
			Pobj.setTEC_Constant(openDBMap.get("TEC_Constant"+"_"+Pobj.getSTAND()));
			Pobj.setTEC_Table(openDBMap.get("TEC_Table"+"_"+Pobj.getSTAND()));
			Pobj.setTEC_Value(openDBMap.get(UILabel.Thermal_Expansion_Coefficient+"_"+Pobj.getSTAND()));
			
			Pobj.setPR_Constant(openDBMap.get("PR_Constant"+"_"+Pobj.getSTAND()));
			Pobj.setPR_Table(openDBMap.get("PR_Table"+"_"+Pobj.getSTAND()));
			Pobj.setPR_Value(openDBMap.get(UILabel.Poissons_Ratio+"_"+Pobj.getSTAND()));
			
			Pobj.setMD_Value(openDBMap.get(UILabel.Mass_Density+"_"+Pobj.getSTAND()));
			
			Pobj.setLcase_time(openDBMap.get(UILabel.Time_Increment_time+"_"+Pobj.getSTAND()));
			Pobj.setLcase_dt(openDBMap.get(UILabel.Time_Increment_dt+"_"+Pobj.getSTAND()));
			Pobj.setPost_inc(openDBMap.get(UILabel.Post_Writing_frequency+"_"+Pobj.getSTAND()));
			Pobj.setInc_time(openDBMap.get(UILabel.Increment_time+"_"+Pobj.getSTAND()));
			Pobj.setParallelDDM(openDBMap.get(UILabel.Parallel_DDM+"_"+Pobj.getSTAND()));
			Pobj.setDomain(openDBMap.get(UILabel.Domain+"_"+Pobj.getSTAND()));
			Pobj.setParallelMultiThread(openDBMap.get(UILabel.Parallel_Multi_Thread+"_"+Pobj.getSTAND()));
			Pobj.setThread(openDBMap.get(UILabel.Thread+"_"+Pobj.getSTAND()));
			
			Pobj.setFRCE(openDBMap.get("FRCE"+"_"+Pobj.getSTAND()));
			Pobj.setEXIT_THK(openDBMap.get("EXIT THK"+"_"+Pobj.getSTAND()));
			Pobj.setROL_TIM(openDBMap.get("ROL TIM"+"_"+Pobj.getSTAND()));
			Pobj.setIDL_TIM(openDBMap.get("IDL TIM"+"_"+Pobj.getSTAND()));
			Pobj.setBUR_WEAR(openDBMap.get("BUR WEAR"+"_"+Pobj.getSTAND()));
			Pobj.setWR_WEAR(openDBMap.get("WR WEAR"+"_"+Pobj.getSTAND()));
			Pobj.setWR_THRM(openDBMap.get("WR THRM"+"_"+Pobj.getSTAND()));
		}
		this.initPLogTables_open(openDBMap);
	}
	
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Save Project
	//
	public void SaveProject(){
		SaveDlg saveDlg = new SaveDlg(Display.getCurrent().getActiveShell());
		saveDlg.open();
	}
	
	public void RunSaveProject(){
		//System.out.println("Save Project");
		// Create DB File => modelName.ens
		ArrayList<String> outputDBList = new ArrayList<String>();
		String DBPath = myUtil.setPath(this.workspace, this.modelName+".ens");
		outputDBList.add("###################");
		outputDBList.add("### ENS DB FILE ###");
		outputDBList.add("###################");
		//outputDBList.add("Model Name="+this.modelName);
		//outputDBList.add("Workspace="+this.workspace);
		try{
			for(String line1 : this.TableDataSlabPlateInfoObj.getDB()){
				outputDBList.add(line1);
			}
			for(String line2 : this.TableDataVariableObj.getDB()){
				outputDBList.add(line2);
			}
			for(TableData_PLog obj : this.tableDataPLogList){
				for(String line3 : obj.getDB()){
					outputDBList.add(line3);
				}
			}
			Writer obj = new Writer(DBPath,outputDBList);
			obj.running();
			/*
			String msg = "* [Save] DB File"+"\n";
			msg +=  "SUCCESS - "+DBPath+"\n";
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),msg);
			messageDlg.open();
			Log.error(msg);
			*/
		}catch(Exception e){
			String msg = "ERROR - Save Data";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),msg);
			messageDlg.open();
			Log.error(msg);
		}
	}
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Save As Project
	//
	public void SaveAsProject(){
		SaveAsDlg saveAsDlg = new SaveAsDlg(Display.getCurrent().getActiveShell());
		saveAsDlg.open();
	}
	
	public void RunSaveAsProject(String newPath, String newModelName){
		// SaveAs DB File => modelName.ens 
		ArrayList<String> outputDBList = new ArrayList<String>();
		this.modelName = newModelName;
		String topFolder =  myUtil.setPath(newPath, newModelName);
		String procFolder = myUtil.setPath(topFolder, "proc");
		String resultFolder = myUtil.setPath(topFolder, "result");
		String newDBFilePath = myUtil.setPath(topFolder, newModelName+".ens");
		this.workspace = topFolder;
		
		med.getLblModelNameValue().setText(this.modelName);
		med.getLblWorkspacePath().setText(this.workspace);

		myUtil.makeDir(topFolder);
		myUtil.makeDir(procFolder);
		myUtil.makeDir(resultFolder);
		
		outputDBList.add("###################");
		outputDBList.add("### ENS DB FILE ###");
		outputDBList.add("###################");
		//outputDBList.add("Model Name="+this.modelName);
		//outputDBList.add("Workspace="+this.workspace);
		
		try{
			if(newDBFilePath != null){
				for(String line1 : this.TableDataSlabPlateInfoObj.getDB()){
					outputDBList.add(line1);
				}
				for(String line2 : this.TableDataVariableObj.getDB()){
					outputDBList.add(line2);
				}
				for(TableData_PLog obj : this.tableDataPLogList){
					for(String line3 : obj.getDB()){
						outputDBList.add(line3);
					}
				}
				Writer obj = new Writer(newDBFilePath,outputDBList);
				obj.running();
			}
			/*
			String msg = "*[Save As] DB File"+"\n";
			msg +=  "SUCCESS - "+newDBFilePath+"\n";
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),msg);
			messageDlg.open();
			Log.error(msg);
			*/
		}catch(Exception e){
			String msg = "ERROR - Save Data";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),msg);
			messageDlg.open();
			Log.error(msg);
		}
	}
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Export Project
	//
	public void ExportProject(){
		ExportDlg exportDlg = new ExportDlg(Display.getCurrent().getActiveShell());
		exportDlg.open();
	}
	
	public void RunExportProject(){
		//모든 proc 생성
		//System.out.println("Export");
		/*
		ProcMaker procObj1 = new ProcMaker(UILabel.F1);
		procObj1.running();
		ProcMaker procObj2 = new ProcMaker(UILabel.F2);
		procObj2.running();
		ProcMaker procObj3 = new ProcMaker(UILabel.F3);
		procObj3.running();
		ProcMaker procObj4 = new ProcMaker(UILabel.F4);
		procObj4.running();
		ProcMaker procObj5 = new ProcMaker(UILabel.F5);
		procObj5.running();
		ProcMaker procObj6 = new ProcMaker(UILabel.F6);
		procObj6.running();
		ProcMaker procObj7 = new ProcMaker(UILabel.F7);
		procObj7.running();
		*/
		this.makeResultF1();
		this.makeResultF2();
		this.makeResultF3();
		this.makeResultF4();
		this.makeResultF5();
		this.makeResultF6();
		this.makeResultF7();
		
		this.checkResultFiles_proc();
		if(myUtil.checkOS().equals("window")){
			try{
				String procPath = myUtil.setPath(this.workspace, "proc");
				Runtime.getRuntime().exec("explorer "+ procPath);
			}catch(Exception e){
				String msg2 = "ERROR - Open Proc Folder";
				MessageDlg messageDlg2 = new MessageDlg(Display.getCurrent().getActiveShell(),msg2);
				messageDlg2.open();
			}
		}
	}
	
	private void checkResultFiles_proc(){
		this.ApplyResult.clear();
		this.ApplyResult.add(true);
		this.ApplyResult.add(true);
		this.ApplyResult.add(true);
		this.ApplyResult.add(true);
		this.ApplyResult.add(true);
		this.ApplyResult.add(true);
		this.ApplyResult.add(true);
		
		this.exportResult = "";
		this.exportResult = "[Export Reulst]"+"\n";
		String userConfigPath = myUtil.setPath(System.getProperty("user.dir"), "userConfig");
		
		for(int i=0;i<7;i++){
			if(ApplyResult.get(i)){
				if(i == 0){
					this.exportResult += "* F1"+"\n";
					String fileListPath = myUtil.setPath(userConfigPath, "f1.filelist");
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f1.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F1),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 1){
					String fileListPath = myUtil.setPath(userConfigPath, "f2.filelist");
					this.exportResult += "* F2"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f2.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F2),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 2){
					String fileListPath = myUtil.setPath(userConfigPath, "f3.filelist");
					this.exportResult += "* F3"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f3.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F3),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 3){
					String fileListPath = myUtil.setPath(userConfigPath, "f4.filelist");
					this.exportResult += "* F4"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f4.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F4),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 4){
					String fileListPath = myUtil.setPath(userConfigPath, "f5.filelist");
					this.exportResult += "* F5"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f5.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F5),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 5){
					String fileListPath = myUtil.setPath(userConfigPath, "f6.filelist");
					this.exportResult += "* F6"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f6.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F6),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 6){
					String fileListPath = myUtil.setPath(userConfigPath, "f7.filelist");
					this.exportResult += "* F7"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f7.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, "proc"),UILabel.F7),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}
			}
		}
		
		MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),exportResult);
		messageDlg.open();
	}
	
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Result Project
	//
	public void ResultProject(){
		ResultDlg resultDlg = new ResultDlg(Display.getCurrent().getActiveShell());
		resultDlg.open();
	}
	//
	//
	//=====================================================================
	
	//=====================================================================
	// 나머지 부분
	//
	
	private void InitComponentValue(){
		// New Project --> Call InitCompoenetValue
		this.initF7Values();
		this.saveAllF7Values();
		this.initF6Values();
		this.saveAllF6Values();
		this.initF5Values();
		this.saveAllF5Values();
		this.initF4Values();
		this.saveAllF4Values();
		this.initF3Values();
		this.saveAllF3Values();
		this.initF2Values();
		this.saveAllF2Values();
		this.initF1Values();
		this.saveAllF1Values();
	
		// initvalue -> PLog table 동기화
		this.initPLogTables();
	}
	
	private void initPLogTables(){
		InitValue obj =new InitValue();
		obj.readInitValueFile();
		ArrayList<String> initDataList = new ArrayList<String>();
		initDataList.add("STRIP NO,STHK,SWID,SLEN,SWET,PTHK,PWID,PLEN,PWET,,,,,,,");
		String line2 = obj.getInitValue(InitValue.STRIP_NO)+","+
						obj.getInitValue(InitValue.STHK)+","+
						obj.getInitValue(InitValue.SWID)+","+
						obj.getInitValue(InitValue.SLEN)+","+
						obj.getInitValue(InitValue.SWET)+","+
						obj.getInitValue(InitValue.PTHK)+","+
						obj.getInitValue(InitValue.PWID)+","+
						obj.getInitValue(InitValue.PLEN)+","+
						obj.getInitValue(InitValue.PWET)+","+
						",,,,,,";
		initDataList.add(line2);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16");
		String line5 = obj.getInitValue(InitValue.VAR1)+","+
						obj.getInitValue(InitValue.VAR2)+","+
						obj.getInitValue(InitValue.VAR3)+","+
						obj.getInitValue(InitValue.VAR4)+","+
						obj.getInitValue(InitValue.VAR5)+","+
						obj.getInitValue(InitValue.VAR6)+","+
						obj.getInitValue(InitValue.VAR7)+","+
						obj.getInitValue(InitValue.VAR8)+","+
						obj.getInitValue(InitValue.VAR9)+","+
						obj.getInitValue(InitValue.VAR10)+","+
						obj.getInitValue(InitValue.VAR11)+","+
						obj.getInitValue(InitValue.VAR12)+","+
						obj.getInitValue(InitValue.VAR13)+","+
						obj.getInitValue(InitValue.VAR14)+","+
						obj.getInitValue(InitValue.VAR15)+","+
						obj.getInitValue(InitValue.VAR16);
		initDataList.add(line5);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("STAND,F1,F2,F3,F4,F5,F6,F7,,,,,,,,");
		String line8 = "BUR TDIA"+","+
					tableDataPLogList.get(0).getBUR_TDIA()+","+
					tableDataPLogList.get(1).getBUR_TDIA()+","+
					tableDataPLogList.get(2).getBUR_TDIA()+","+
					tableDataPLogList.get(3).getBUR_TDIA()+","+
					tableDataPLogList.get(4).getBUR_TDIA()+","+
					tableDataPLogList.get(5).getBUR_TDIA()+","+
					tableDataPLogList.get(6).getBUR_TDIA()+","+
					",,,,,,,";
		initDataList.add(line8);
		String line9 = "BUR BDIA"+","+
					tableDataPLogList.get(0).getBUR_BDIA()+","+
					tableDataPLogList.get(1).getBUR_BDIA()+","+
					tableDataPLogList.get(2).getBUR_BDIA()+","+
					tableDataPLogList.get(3).getBUR_BDIA()+","+
					tableDataPLogList.get(4).getBUR_BDIA()+","+
					tableDataPLogList.get(5).getBUR_BDIA()+","+
					tableDataPLogList.get(6).getBUR_BDIA()+","+
					",,,,,,,";
		initDataList.add(line9);
		String line10 = "WR TDIA"+","+
					tableDataPLogList.get(0).getWR_TDIA()+","+
					tableDataPLogList.get(1).getWR_TDIA()+","+
					tableDataPLogList.get(2).getWR_TDIA()+","+
					tableDataPLogList.get(3).getWR_TDIA()+","+
					tableDataPLogList.get(4).getWR_TDIA()+","+
					tableDataPLogList.get(5).getWR_TDIA()+","+
					tableDataPLogList.get(6).getWR_TDIA()+","+
					",,,,,,,";
		initDataList.add(line10);
		String line11 = "WR BDIA"+","+
					tableDataPLogList.get(0).getWR_BDIA()+","+
					tableDataPLogList.get(1).getWR_BDIA()+","+
					tableDataPLogList.get(2).getWR_BDIA()+","+
					tableDataPLogList.get(3).getWR_BDIA()+","+
					tableDataPLogList.get(4).getWR_BDIA()+","+
					tableDataPLogList.get(5).getWR_BDIA()+","+
					tableDataPLogList.get(6).getWR_BDIA()+","+
					",,,,,,,";
		initDataList.add(line11);
		String line12 = "WR ICRN"+","+
					tableDataPLogList.get(0).getWR_ICRN()+","+
					tableDataPLogList.get(1).getWR_ICRN()+","+
					tableDataPLogList.get(2).getWR_ICRN()+","+
					tableDataPLogList.get(3).getWR_ICRN()+","+
					tableDataPLogList.get(4).getWR_ICRN()+","+
					tableDataPLogList.get(5).getWR_ICRN()+","+
					tableDataPLogList.get(6).getWR_ICRN()+","+
					",,,,,,,";
		initDataList.add(line12);
		String line13 = "ENTRY THK"+","+
					tableDataPLogList.get(0).getENTRY_THK()+","+
					tableDataPLogList.get(1).getENTRY_THK()+","+
					tableDataPLogList.get(2).getENTRY_THK()+","+
					tableDataPLogList.get(3).getENTRY_THK()+","+
					tableDataPLogList.get(4).getENTRY_THK()+","+
					tableDataPLogList.get(5).getENTRY_THK()+","+
					tableDataPLogList.get(6).getENTRY_THK()+","+
					",,,,,,,";
		initDataList.add(line13);
		String line14 = "EXIT THK"+","+
					tableDataPLogList.get(0).getEXIT_THK()+","+
					tableDataPLogList.get(1).getEXIT_THK()+","+
					tableDataPLogList.get(2).getEXIT_THK()+","+
					tableDataPLogList.get(3).getEXIT_THK()+","+
					tableDataPLogList.get(4).getEXIT_THK()+","+
					tableDataPLogList.get(5).getEXIT_THK()+","+
					tableDataPLogList.get(6).getEXIT_THK()+","+
					",,,,,,,";
		initDataList.add(line14);
		String line15 = "PAS LINE"+","+
					tableDataPLogList.get(0).getPAS_LINE()+","+
					tableDataPLogList.get(1).getPAS_LINE()+","+
					tableDataPLogList.get(2).getPAS_LINE()+","+
					tableDataPLogList.get(3).getPAS_LINE()+","+
					tableDataPLogList.get(4).getPAS_LINE()+","+
					tableDataPLogList.get(5).getPAS_LINE()+","+
					tableDataPLogList.get(6).getPAS_LINE()+","+
					",,,,,,,";
		initDataList.add(line15);
		String line16 = "ROL GAP"+","+
					tableDataPLogList.get(0).getROL_GAP()+","+
					tableDataPLogList.get(1).getROL_GAP()+","+
					tableDataPLogList.get(2).getROL_GAP()+","+
					tableDataPLogList.get(3).getROL_GAP()+","+
					tableDataPLogList.get(4).getROL_GAP()+","+
					tableDataPLogList.get(5).getROL_GAP()+","+
					tableDataPLogList.get(6).getROL_GAP()+","+
					",,,,,,,";
		initDataList.add(line16);
		String line17 = "STP WID"+","+
					tableDataPLogList.get(0).getSTP_WID()+","+
					tableDataPLogList.get(1).getSTP_WID()+","+
					tableDataPLogList.get(2).getSTP_WID()+","+
					tableDataPLogList.get(3).getSTP_WID()+","+
					tableDataPLogList.get(4).getSTP_WID()+","+
					tableDataPLogList.get(5).getSTP_WID()+","+
					tableDataPLogList.get(6).getSTP_WID()+","+
					",,,,,,,";
		initDataList.add(line17);
		String line18 = "STP LEN"+","+
					tableDataPLogList.get(0).getSTP_LEN()+","+
					tableDataPLogList.get(1).getSTP_LEN()+","+
					tableDataPLogList.get(2).getSTP_LEN()+","+
					tableDataPLogList.get(3).getSTP_LEN()+","+
					tableDataPLogList.get(4).getSTP_LEN()+","+
					tableDataPLogList.get(5).getSTP_LEN()+","+
					tableDataPLogList.get(6).getSTP_LEN()+","+
					",,,,,,,";
		initDataList.add(line18);
		String line19 = "ENTRY TEMP"+","+
					tableDataPLogList.get(0).getSTP_LEN()+","+
					tableDataPLogList.get(1).getSTP_LEN()+","+
					tableDataPLogList.get(2).getSTP_LEN()+","+
					tableDataPLogList.get(3).getSTP_LEN()+","+
					tableDataPLogList.get(4).getSTP_LEN()+","+
					tableDataPLogList.get(5).getSTP_LEN()+","+
					tableDataPLogList.get(6).getSTP_LEN()+","+
					",,,,,,,";
		initDataList.add(line19);
		String line20 = "EXIT TEMP"+","+
					tableDataPLogList.get(0).getEXIT_TEMP()+","+
					tableDataPLogList.get(1).getEXIT_TEMP()+","+
					tableDataPLogList.get(2).getEXIT_TEMP()+","+
					tableDataPLogList.get(3).getEXIT_TEMP()+","+
					tableDataPLogList.get(4).getEXIT_TEMP()+","+
					tableDataPLogList.get(5).getEXIT_TEMP()+","+
					tableDataPLogList.get(6).getEXIT_TEMP()+","+
					",,,,,,,";
		initDataList.add(line20);
		String line21 = "FRCE"+","+
					tableDataPLogList.get(0).getFRCE()+","+
					tableDataPLogList.get(1).getFRCE()+","+
					tableDataPLogList.get(2).getFRCE()+","+
					tableDataPLogList.get(3).getFRCE()+","+
					tableDataPLogList.get(4).getFRCE()+","+
					tableDataPLogList.get(5).getFRCE()+","+
					tableDataPLogList.get(6).getFRCE()+","+					
					",,,,,,,";
		initDataList.add(line21);
		String line22 = "TORQ"+","+
					tableDataPLogList.get(0).getTORQ()+","+					
					tableDataPLogList.get(1).getTORQ()+","+
					tableDataPLogList.get(2).getTORQ()+","+
					tableDataPLogList.get(3).getTORQ()+","+
					tableDataPLogList.get(4).getTORQ()+","+
					tableDataPLogList.get(5).getTORQ()+","+
					tableDataPLogList.get(6).getTORQ()+","+
					",,,,,,,";
		initDataList.add(line22);
		String line23 = "SPEED(mpm)"+","+
					tableDataPLogList.get(0).getSPEED()+","+
					tableDataPLogList.get(1).getSPEED()+","+
					tableDataPLogList.get(2).getSPEED()+","+
					tableDataPLogList.get(3).getSPEED()+","+
					tableDataPLogList.get(4).getSPEED()+","+
					tableDataPLogList.get(5).getSPEED()+","+
					tableDataPLogList.get(6).getSPEED()+","+					
					",,,,,,,";
		initDataList.add(line23);
		String line24 = "BEND"+","+
					tableDataPLogList.get(0).getBEND()+","+
					tableDataPLogList.get(1).getBEND()+","+
					tableDataPLogList.get(2).getBEND()+","+
					tableDataPLogList.get(3).getBEND()+","+
					tableDataPLogList.get(4).getBEND()+","+
					tableDataPLogList.get(5).getBEND()+","+
					tableDataPLogList.get(6).getBEND()+","+
					",,,,,,,";
		initDataList.add(line24);
		String line25 = "P-CROSS"+","+
					tableDataPLogList.get(0).getP_CROSS()+","+
					tableDataPLogList.get(1).getP_CROSS()+","+
					tableDataPLogList.get(2).getP_CROSS()+","+
					tableDataPLogList.get(3).getP_CROSS()+","+
					tableDataPLogList.get(4).getP_CROSS()+","+
					tableDataPLogList.get(5).getP_CROSS()+","+
					tableDataPLogList.get(6).getP_CROSS()+","+
					",,,,,,,";
		initDataList.add(line25);
		String line26 = "TENS"+","+
					tableDataPLogList.get(0).getTENS()+","+
					tableDataPLogList.get(1).getTENS()+","+
					tableDataPLogList.get(2).getTENS()+","+
					tableDataPLogList.get(3).getTENS()+","+
					tableDataPLogList.get(4).getTENS()+","+
					tableDataPLogList.get(5).getTENS()+","+
					tableDataPLogList.get(6).getTENS()+","+
					",,,,,,,";
		initDataList.add(line26);
		String line27 = "ROL TIM"+","+
					tableDataPLogList.get(0).getROL_TIM()+","+
					tableDataPLogList.get(1).getROL_TIM()+","+
					tableDataPLogList.get(2).getROL_TIM()+","+
					tableDataPLogList.get(3).getROL_TIM()+","+
					tableDataPLogList.get(4).getROL_TIM()+","+
					tableDataPLogList.get(5).getROL_TIM()+","+
					tableDataPLogList.get(6).getROL_TIM()+","+					
					",,,,,,,";
		initDataList.add(line27);
		String line28 = "IDL TIM"+","+
					tableDataPLogList.get(0).getIDL_TIM()+","+
					tableDataPLogList.get(1).getIDL_TIM()+","+
					tableDataPLogList.get(2).getIDL_TIM()+","+
					tableDataPLogList.get(3).getIDL_TIM()+","+
					tableDataPLogList.get(4).getIDL_TIM()+","+
					tableDataPLogList.get(5).getIDL_TIM()+","+
					tableDataPLogList.get(6).getIDL_TIM()+","+
					",,,,,,,";
		initDataList.add(line28);
		String line29 = "BUR WEAR"+","+
					tableDataPLogList.get(0).getBUR_WEAR()+","+
					tableDataPLogList.get(1).getBUR_WEAR()+","+
					tableDataPLogList.get(2).getBUR_WEAR()+","+
					tableDataPLogList.get(3).getBUR_WEAR()+","+
					tableDataPLogList.get(4).getBUR_WEAR()+","+
					tableDataPLogList.get(5).getBUR_WEAR()+","+
					tableDataPLogList.get(6).getBUR_WEAR()+","+					
					",,,,,,,";
		initDataList.add(line29);
		String line30 = "WR WEAR"+","+ 
					tableDataPLogList.get(0).getWR_WEAR()+","+
					tableDataPLogList.get(1).getWR_WEAR()+","+
					tableDataPLogList.get(2).getWR_WEAR()+","+
					tableDataPLogList.get(3).getWR_WEAR()+","+
					tableDataPLogList.get(4).getWR_WEAR()+","+
					tableDataPLogList.get(5).getWR_WEAR()+","+
					tableDataPLogList.get(6).getWR_WEAR()+","+
					",,,,,,,";
		initDataList.add(line30);
		String line31 = "WR THRM"+","+
					tableDataPLogList.get(0).getWR_THRM()+","+
					tableDataPLogList.get(1).getWR_THRM()+","+
					tableDataPLogList.get(2).getWR_THRM()+","+
					tableDataPLogList.get(3).getWR_THRM()+","+
					tableDataPLogList.get(4).getWR_THRM()+","+
					tableDataPLogList.get(5).getWR_THRM()+","+
					tableDataPLogList.get(6).getWR_THRM()+","+					
					",,,,,,,";
		initDataList.add(line31);
		this.parsingPLogFile(initDataList);
	}
	
	private void initPLogTables_open(Map<String,String> openDBMap){
		InitValue obj =new InitValue();
		obj.readInitValueFile();
		ArrayList<String> initDataList = new ArrayList<String>();
		initDataList.add("STRIP NO,STHK,SWID,SLEN,SWET,PTHK,PWID,PLEN,PWET,,,,,,,");
		String line2 = openDBMap.get(InitValue.STRIP_NO)+","+
						openDBMap.get(InitValue.STHK)+","+
						openDBMap.get(InitValue.SWID)+","+
						openDBMap.get(InitValue.SLEN)+","+
						openDBMap.get(InitValue.SWET)+","+
						openDBMap.get(InitValue.PTHK)+","+
						openDBMap.get(InitValue.PWID)+","+
						openDBMap.get(InitValue.PLEN)+","+
						openDBMap.get(InitValue.PWET)+","+
						",,,,,,";
		initDataList.add(line2);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16");
		String line5 = openDBMap.get(InitValue.VAR1)+","+
						openDBMap.get(InitValue.VAR2)+","+
						openDBMap.get(InitValue.VAR3)+","+
						openDBMap.get(InitValue.VAR4)+","+
						openDBMap.get(InitValue.VAR5)+","+
						openDBMap.get(InitValue.VAR6)+","+
						openDBMap.get(InitValue.VAR7)+","+
						openDBMap.get(InitValue.VAR8)+","+
						openDBMap.get(InitValue.VAR9)+","+
						openDBMap.get(InitValue.VAR10)+","+
						openDBMap.get(InitValue.VAR11)+","+
						openDBMap.get(InitValue.VAR12)+","+
						openDBMap.get(InitValue.VAR13)+","+
						openDBMap.get(InitValue.VAR14)+","+
						openDBMap.get(InitValue.VAR15)+","+
						openDBMap.get(InitValue.VAR16);
		initDataList.add(line5);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("STAND,F1,F2,F3,F4,F5,F6,F7,,,,,,,,");
		String line8 = "BUR TDIA"+","+
					tableDataPLogList.get(0).getBUR_TDIA()+","+
					tableDataPLogList.get(1).getBUR_TDIA()+","+
					tableDataPLogList.get(2).getBUR_TDIA()+","+
					tableDataPLogList.get(3).getBUR_TDIA()+","+
					tableDataPLogList.get(4).getBUR_TDIA()+","+
					tableDataPLogList.get(5).getBUR_TDIA()+","+
					tableDataPLogList.get(6).getBUR_TDIA()+","+
					",,,,,,,";
		initDataList.add(line8);
		String line9 = "BUR BDIA"+","+
					tableDataPLogList.get(0).getBUR_BDIA()+","+
					tableDataPLogList.get(1).getBUR_BDIA()+","+
					tableDataPLogList.get(2).getBUR_BDIA()+","+
					tableDataPLogList.get(3).getBUR_BDIA()+","+
					tableDataPLogList.get(4).getBUR_BDIA()+","+
					tableDataPLogList.get(5).getBUR_BDIA()+","+
					tableDataPLogList.get(6).getBUR_BDIA()+","+
					",,,,,,,";
		initDataList.add(line9);
		String line10 = "WR TDIA"+","+
					tableDataPLogList.get(0).getWR_TDIA()+","+
					tableDataPLogList.get(1).getWR_TDIA()+","+
					tableDataPLogList.get(2).getWR_TDIA()+","+
					tableDataPLogList.get(3).getWR_TDIA()+","+
					tableDataPLogList.get(4).getWR_TDIA()+","+
					tableDataPLogList.get(5).getWR_TDIA()+","+
					tableDataPLogList.get(6).getWR_TDIA()+","+
					",,,,,,,";
		initDataList.add(line10);
		String line11 = "WR BDIA"+","+
					tableDataPLogList.get(0).getWR_BDIA()+","+
					tableDataPLogList.get(1).getWR_BDIA()+","+
					tableDataPLogList.get(2).getWR_BDIA()+","+
					tableDataPLogList.get(3).getWR_BDIA()+","+
					tableDataPLogList.get(4).getWR_BDIA()+","+
					tableDataPLogList.get(5).getWR_BDIA()+","+
					tableDataPLogList.get(6).getWR_BDIA()+","+
					",,,,,,,";
		initDataList.add(line11);
		String line12 = "WR ICRN"+","+
					tableDataPLogList.get(0).getWR_ICRN()+","+
					tableDataPLogList.get(1).getWR_ICRN()+","+
					tableDataPLogList.get(2).getWR_ICRN()+","+
					tableDataPLogList.get(3).getWR_ICRN()+","+
					tableDataPLogList.get(4).getWR_ICRN()+","+
					tableDataPLogList.get(5).getWR_ICRN()+","+
					tableDataPLogList.get(6).getWR_ICRN()+","+
					",,,,,,,";
		initDataList.add(line12);
		String line13 = "ENTRY THK"+","+
					tableDataPLogList.get(0).getENTRY_THK()+","+
					tableDataPLogList.get(1).getENTRY_THK()+","+
					tableDataPLogList.get(2).getENTRY_THK()+","+
					tableDataPLogList.get(3).getENTRY_THK()+","+
					tableDataPLogList.get(4).getENTRY_THK()+","+
					tableDataPLogList.get(5).getENTRY_THK()+","+
					tableDataPLogList.get(6).getENTRY_THK()+","+
					",,,,,,,";
		initDataList.add(line13);
		String line14 = "EXIT THK"+","+
					tableDataPLogList.get(0).getEXIT_THK()+","+
					tableDataPLogList.get(1).getEXIT_THK()+","+
					tableDataPLogList.get(2).getEXIT_THK()+","+
					tableDataPLogList.get(3).getEXIT_THK()+","+
					tableDataPLogList.get(4).getEXIT_THK()+","+
					tableDataPLogList.get(5).getEXIT_THK()+","+
					tableDataPLogList.get(6).getEXIT_THK()+","+
					",,,,,,,";
		initDataList.add(line14);
		String line15 = "PAS LINE"+","+
					tableDataPLogList.get(0).getPAS_LINE()+","+
					tableDataPLogList.get(1).getPAS_LINE()+","+
					tableDataPLogList.get(2).getPAS_LINE()+","+
					tableDataPLogList.get(3).getPAS_LINE()+","+
					tableDataPLogList.get(4).getPAS_LINE()+","+
					tableDataPLogList.get(5).getPAS_LINE()+","+
					tableDataPLogList.get(6).getPAS_LINE()+","+
					",,,,,,,";
		initDataList.add(line15);
		String line16 = "ROL GAP"+","+
					tableDataPLogList.get(0).getROL_GAP()+","+
					tableDataPLogList.get(1).getROL_GAP()+","+
					tableDataPLogList.get(2).getROL_GAP()+","+
					tableDataPLogList.get(3).getROL_GAP()+","+
					tableDataPLogList.get(4).getROL_GAP()+","+
					tableDataPLogList.get(5).getROL_GAP()+","+
					tableDataPLogList.get(6).getROL_GAP()+","+
					",,,,,,,";
		initDataList.add(line16);
		String line17 = "STP WID"+","+
					tableDataPLogList.get(0).getSTP_WID()+","+
					tableDataPLogList.get(1).getSTP_WID()+","+
					tableDataPLogList.get(2).getSTP_WID()+","+
					tableDataPLogList.get(3).getSTP_WID()+","+
					tableDataPLogList.get(4).getSTP_WID()+","+
					tableDataPLogList.get(5).getSTP_WID()+","+
					tableDataPLogList.get(6).getSTP_WID()+","+
					",,,,,,,";
		initDataList.add(line17);
		String line18 = "STP LEN"+","+
					tableDataPLogList.get(0).getSTP_LEN()+","+
					tableDataPLogList.get(1).getSTP_LEN()+","+
					tableDataPLogList.get(2).getSTP_LEN()+","+
					tableDataPLogList.get(3).getSTP_LEN()+","+
					tableDataPLogList.get(4).getSTP_LEN()+","+
					tableDataPLogList.get(5).getSTP_LEN()+","+
					tableDataPLogList.get(6).getSTP_LEN()+","+
					",,,,,,,";
		initDataList.add(line18);
		String line19 = "ENTRY TEMP"+","+
					tableDataPLogList.get(0).getSTP_LEN()+","+
					tableDataPLogList.get(1).getSTP_LEN()+","+
					tableDataPLogList.get(2).getSTP_LEN()+","+
					tableDataPLogList.get(3).getSTP_LEN()+","+
					tableDataPLogList.get(4).getSTP_LEN()+","+
					tableDataPLogList.get(5).getSTP_LEN()+","+
					tableDataPLogList.get(6).getSTP_LEN()+","+
					",,,,,,,";
		initDataList.add(line19);
		String line20 = "EXIT TEMP"+","+
					tableDataPLogList.get(0).getEXIT_TEMP()+","+
					tableDataPLogList.get(1).getEXIT_TEMP()+","+
					tableDataPLogList.get(2).getEXIT_TEMP()+","+
					tableDataPLogList.get(3).getEXIT_TEMP()+","+
					tableDataPLogList.get(4).getEXIT_TEMP()+","+
					tableDataPLogList.get(5).getEXIT_TEMP()+","+
					tableDataPLogList.get(6).getEXIT_TEMP()+","+
					",,,,,,,";
		initDataList.add(line20);
		String line21 = "FRCE"+","+
					tableDataPLogList.get(0).getFRCE()+","+
					tableDataPLogList.get(1).getFRCE()+","+
					tableDataPLogList.get(2).getFRCE()+","+
					tableDataPLogList.get(3).getFRCE()+","+
					tableDataPLogList.get(4).getFRCE()+","+
					tableDataPLogList.get(5).getFRCE()+","+
					tableDataPLogList.get(6).getFRCE()+","+					
					",,,,,,,";
		initDataList.add(line21);
		String line22 = "TORQ"+","+
					tableDataPLogList.get(0).getTORQ()+","+					
					tableDataPLogList.get(1).getTORQ()+","+
					tableDataPLogList.get(2).getTORQ()+","+
					tableDataPLogList.get(3).getTORQ()+","+
					tableDataPLogList.get(4).getTORQ()+","+
					tableDataPLogList.get(5).getTORQ()+","+
					tableDataPLogList.get(6).getTORQ()+","+
					",,,,,,,";
		initDataList.add(line22);
		String line23 = "SPEED(mpm)"+","+
					tableDataPLogList.get(0).getSPEED()+","+
					tableDataPLogList.get(1).getSPEED()+","+
					tableDataPLogList.get(2).getSPEED()+","+
					tableDataPLogList.get(3).getSPEED()+","+
					tableDataPLogList.get(4).getSPEED()+","+
					tableDataPLogList.get(5).getSPEED()+","+
					tableDataPLogList.get(6).getSPEED()+","+					
					",,,,,,,";
		initDataList.add(line23);
		String line24 = "BEND"+","+
					tableDataPLogList.get(0).getBEND()+","+
					tableDataPLogList.get(1).getBEND()+","+
					tableDataPLogList.get(2).getBEND()+","+
					tableDataPLogList.get(3).getBEND()+","+
					tableDataPLogList.get(4).getBEND()+","+
					tableDataPLogList.get(5).getBEND()+","+
					tableDataPLogList.get(6).getBEND()+","+
					",,,,,,,";
		initDataList.add(line24);
		String line25 = "P-CROSS"+","+
					tableDataPLogList.get(0).getP_CROSS()+","+
					tableDataPLogList.get(1).getP_CROSS()+","+
					tableDataPLogList.get(2).getP_CROSS()+","+
					tableDataPLogList.get(3).getP_CROSS()+","+
					tableDataPLogList.get(4).getP_CROSS()+","+
					tableDataPLogList.get(5).getP_CROSS()+","+
					tableDataPLogList.get(6).getP_CROSS()+","+
					",,,,,,,";
		initDataList.add(line25);
		String line26 = "TENS"+","+
					tableDataPLogList.get(0).getTENS()+","+
					tableDataPLogList.get(1).getTENS()+","+
					tableDataPLogList.get(2).getTENS()+","+
					tableDataPLogList.get(3).getTENS()+","+
					tableDataPLogList.get(4).getTENS()+","+
					tableDataPLogList.get(5).getTENS()+","+
					tableDataPLogList.get(6).getTENS()+","+
					",,,,,,,";
		initDataList.add(line26);
		String line27 = "ROL TIM"+","+
					tableDataPLogList.get(0).getROL_TIM()+","+
					tableDataPLogList.get(1).getROL_TIM()+","+
					tableDataPLogList.get(2).getROL_TIM()+","+
					tableDataPLogList.get(3).getROL_TIM()+","+
					tableDataPLogList.get(4).getROL_TIM()+","+
					tableDataPLogList.get(5).getROL_TIM()+","+
					tableDataPLogList.get(6).getROL_TIM()+","+					
					",,,,,,,";
		initDataList.add(line27);
		String line28 = "IDL TIM"+","+
					tableDataPLogList.get(0).getIDL_TIM()+","+
					tableDataPLogList.get(1).getIDL_TIM()+","+
					tableDataPLogList.get(2).getIDL_TIM()+","+
					tableDataPLogList.get(3).getIDL_TIM()+","+
					tableDataPLogList.get(4).getIDL_TIM()+","+
					tableDataPLogList.get(5).getIDL_TIM()+","+
					tableDataPLogList.get(6).getIDL_TIM()+","+
					",,,,,,,";
		initDataList.add(line28);
		String line29 = "BUR WEAR"+","+
					tableDataPLogList.get(0).getBUR_WEAR()+","+
					tableDataPLogList.get(1).getBUR_WEAR()+","+
					tableDataPLogList.get(2).getBUR_WEAR()+","+
					tableDataPLogList.get(3).getBUR_WEAR()+","+
					tableDataPLogList.get(4).getBUR_WEAR()+","+
					tableDataPLogList.get(5).getBUR_WEAR()+","+
					tableDataPLogList.get(6).getBUR_WEAR()+","+					
					",,,,,,,";
		initDataList.add(line29);
		String line30 = "WR WEAR"+","+ 
					tableDataPLogList.get(0).getWR_WEAR()+","+
					tableDataPLogList.get(1).getWR_WEAR()+","+
					tableDataPLogList.get(2).getWR_WEAR()+","+
					tableDataPLogList.get(3).getWR_WEAR()+","+
					tableDataPLogList.get(4).getWR_WEAR()+","+
					tableDataPLogList.get(5).getWR_WEAR()+","+
					tableDataPLogList.get(6).getWR_WEAR()+","+
					",,,,,,,";
		initDataList.add(line30);
		String line31 = "WR THRM"+","+
					tableDataPLogList.get(0).getWR_THRM()+","+
					tableDataPLogList.get(1).getWR_THRM()+","+
					tableDataPLogList.get(2).getWR_THRM()+","+
					tableDataPLogList.get(3).getWR_THRM()+","+
					tableDataPLogList.get(4).getWR_THRM()+","+
					tableDataPLogList.get(5).getWR_THRM()+","+
					tableDataPLogList.get(6).getWR_THRM()+","+					
					",,,,,,,";
		initDataList.add(line31);
		this.parsingPLogFile(initDataList);
	}
	
	private void saveAllF1Values(){
		TableData_PLog obj = tableDataPLogList.get(0);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setTb_vel_rate(med.getTextTopBotVelRate().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");;
			obj.setYM_Table("false");
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
		}
		obj.setYM_Value(med.getTextYoungsModulus().getText());
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
		}
		obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("fasle");
		}
		obj.setPR_Value(med.getTextPoissonsRatio().getText());
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextTimeIncrement_time().getText());
		obj.setLcase_dt(med.getTextTimeIncrement_dt().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setInc_time(med.getTextIncrementTime().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F1));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F1));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F1));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F1));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F1));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F1));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F1));
	}
	
	private void saveAllF2Values(){
		TableData_PLog obj = tableDataPLogList.get(1);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setTb_vel_rate(med.getTextTopBotVelRate().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");;
			obj.setYM_Table("false");
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
		}
		obj.setYM_Value(med.getTextYoungsModulus().getText());
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
		}
		obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("fasle");
		}
		obj.setPR_Value(med.getTextPoissonsRatio().getText());
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextTimeIncrement_time().getText());
		obj.setLcase_dt(med.getTextTimeIncrement_dt().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setInc_time(med.getTextIncrementTime().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F2));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F2));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F2));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F2));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F2));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F2));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F2));
	}
	
	private void saveAllF3Values(){
		TableData_PLog obj = tableDataPLogList.get(2);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setTb_vel_rate(med.getTextTopBotVelRate().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");;
			obj.setYM_Table("false");
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
		}
		obj.setYM_Value(med.getTextYoungsModulus().getText());
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
		}
		obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("fasle");
		}
		obj.setPR_Value(med.getTextPoissonsRatio().getText());
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextTimeIncrement_time().getText());
		obj.setLcase_dt(med.getTextTimeIncrement_dt().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setInc_time(med.getTextIncrementTime().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F3));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F3));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F3));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F3));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F3));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F3));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F3));
	}

	private void saveAllF4Values(){
		TableData_PLog obj = tableDataPLogList.get(3);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setTb_vel_rate(med.getTextTopBotVelRate().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");;
			obj.setYM_Table("false");
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
		}
		obj.setYM_Value(med.getTextYoungsModulus().getText());
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
		}
		obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("fasle");
		}
		obj.setPR_Value(med.getTextPoissonsRatio().getText());
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextTimeIncrement_time().getText());
		obj.setLcase_dt(med.getTextTimeIncrement_dt().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setInc_time(med.getTextIncrementTime().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F4));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F4));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F4));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F4));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F4));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F4));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F4));
	}
	
	private void saveAllF5Values(){
		TableData_PLog obj = tableDataPLogList.get(4);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setTb_vel_rate(med.getTextTopBotVelRate().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");;
			obj.setYM_Table("false");
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
		}
		obj.setYM_Value(med.getTextYoungsModulus().getText());
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
		}
		obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("fasle");
		}
		obj.setPR_Value(med.getTextPoissonsRatio().getText());
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextTimeIncrement_time().getText());
		obj.setLcase_dt(med.getTextTimeIncrement_dt().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setInc_time(med.getTextIncrementTime().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F5));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F5));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F5));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F5));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F5));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F5));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F5));
	}
	
	private void saveAllF6Values(){
		TableData_PLog obj = tableDataPLogList.get(5);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setTb_vel_rate(med.getTextTopBotVelRate().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");;
			obj.setYM_Table("false");
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
		}
		obj.setYM_Value(med.getTextYoungsModulus().getText());
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
		}
		obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("fasle");
		}
		obj.setPR_Value(med.getTextPoissonsRatio().getText());
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextTimeIncrement_time().getText());
		obj.setLcase_dt(med.getTextTimeIncrement_dt().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setInc_time(med.getTextIncrementTime().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F6));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F6));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F6));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F6));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F6));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F6));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F6));
	}
	
	private void saveAllF7Values(){
		TableData_PLog obj = tableDataPLogList.get(6);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setTb_vel_rate(med.getTextTopBotVelRate().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");;
			obj.setYM_Table("false");
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
		}
		obj.setYM_Value(med.getTextYoungsModulus().getText());
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
		}
		obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("fasle");
		}
		obj.setPR_Value(med.getTextPoissonsRatio().getText());
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextTimeIncrement_time().getText());
		obj.setLcase_dt(med.getTextTimeIncrement_dt().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setInc_time(med.getTextIncrementTime().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F7));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F7));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F7));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F7));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F7));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F7));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F7));
	}
	
	private void initF1Values(){
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(true);
		med.getBtnF2().setSelection(false);
		med.getBtnF3().setSelection(false);
		med.getBtnF4().setSelection(false);
		med.getBtnF5().setSelection(false);
		med.getBtnF6().setSelection(false);
		med.getBtnF7().setSelection(false);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F1));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F1));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F1));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F1));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F1));
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F1));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F1));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F1));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F1));
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F1));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F1));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F1));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F1));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F1));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F1));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F1));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F1));
		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F1));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F1));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F1));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F1));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F1));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F1));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F1));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F1));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F1));
		med.getTextTopBotVelRate().setText(obj.getInitValue(InitValue.tb_vel_rate_F1));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F1));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F1));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F1));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F1));
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F1).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}
		med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F1));
		
		if(obj.getInitValue(InitValue.TEC_Constant_F1).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F1));
		
		if(obj.getInitValue(InitValue.PR_Constant_F1).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F1));
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F1));
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getInitValue(InitValue.lcase_time_F1));
		med.getTextTimeIncrement_dt().setText(obj.getInitValue(InitValue.lcase_dt_F1));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F1));
		med.getTextIncrementTime().setText(obj.getInitValue(InitValue.inc_time_F1));
		if(obj.getInitValue(InitValue.ParallelDDM_F1).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F1)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F1)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F1).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F1)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F1)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void initF2Values(){
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(false);
		med.getBtnF2().setSelection(true);
		med.getBtnF3().setSelection(false);
		med.getBtnF4().setSelection(false);
		med.getBtnF5().setSelection(false);
		med.getBtnF6().setSelection(false);
		med.getBtnF7().setSelection(false);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F2));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F2));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F2));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F2));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F2));
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F2));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F2));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F2));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F2));
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F2));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F2));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F2));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F2));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F2));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F2));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F2));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F2));
		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F2));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F2));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F2));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F2));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F2));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F2));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F2));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F2));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F2));
		med.getTextTopBotVelRate().setText(obj.getInitValue(InitValue.tb_vel_rate_F2));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F2));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F2));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F2));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F2));
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F2).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}
		
		med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F2));
		if(obj.getInitValue(InitValue.TEC_Constant_F2).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F2));
		
		if(obj.getInitValue(InitValue.PR_Constant_F2).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F2));
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F2));
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getInitValue(InitValue.lcase_time_F2));
		med.getTextTimeIncrement_dt().setText(obj.getInitValue(InitValue.lcase_dt_F2));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F2));
		med.getTextIncrementTime().setText(obj.getInitValue(InitValue.inc_time_F2));
		if(obj.getInitValue(InitValue.ParallelDDM_F2).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F2)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F2)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F2).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F2)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F2)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void initF3Values(){
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(false);
		med.getBtnF2().setSelection(false);
		med.getBtnF3().setSelection(true);
		med.getBtnF4().setSelection(false);
		med.getBtnF5().setSelection(false);
		med.getBtnF6().setSelection(false);
		med.getBtnF7().setSelection(false);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F3));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F3));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F3));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F3));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F3));
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F3));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F3));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F3));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F3));
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F3));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F3));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F3));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F3));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F3));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F3));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F3));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F3));
		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F3));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F3));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F3));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F3));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F3));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F3));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F3));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F3));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F3));
		med.getTextTopBotVelRate().setText(obj.getInitValue(InitValue.tb_vel_rate_F3));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F3));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F3));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F3));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F3));
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F3).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}
		
		med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F3));
		if(obj.getInitValue(InitValue.TEC_Constant_F3).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F3));
		
		if(obj.getInitValue(InitValue.PR_Constant_F3).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F3));
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F3));
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getInitValue(InitValue.lcase_time_F3));
		med.getTextTimeIncrement_dt().setText(obj.getInitValue(InitValue.lcase_dt_F3));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F3));
		med.getTextIncrementTime().setText(obj.getInitValue(InitValue.inc_time_F3));
		if(obj.getInitValue(InitValue.ParallelDDM_F3).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F3)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F3)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F3).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F3)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F3)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void initF4Values(){
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(false);
		med.getBtnF2().setSelection(false);
		med.getBtnF3().setSelection(false);
		med.getBtnF4().setSelection(true);
		med.getBtnF5().setSelection(false);
		med.getBtnF6().setSelection(false);
		med.getBtnF7().setSelection(false);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F4));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F4));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F4));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F4));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F4));
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F4));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F4));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F4));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F4));
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F4));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F4));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F4));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F4));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F4));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F4));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F4));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F4));
		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F4));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F4));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F4));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F4));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F4));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F4));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F4));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F4));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F4));
		med.getTextTopBotVelRate().setText(obj.getInitValue(InitValue.tb_vel_rate_F4));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F4));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F4));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F4));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F4));
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F4).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}
		
		med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F4));
		if(obj.getInitValue(InitValue.TEC_Constant_F4).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F4));
		
		if(obj.getInitValue(InitValue.PR_Constant_F4).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F4));
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F4));
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getInitValue(InitValue.lcase_time_F4));
		med.getTextTimeIncrement_dt().setText(obj.getInitValue(InitValue.lcase_dt_F4));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F4));
		med.getTextIncrementTime().setText(obj.getInitValue(InitValue.inc_time_F4));
		if(obj.getInitValue(InitValue.ParallelDDM_F4).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F4)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F4)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F4).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F4)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F4)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void initF5Values(){
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(false);
		med.getBtnF2().setSelection(false);
		med.getBtnF3().setSelection(false);
		med.getBtnF4().setSelection(false);
		med.getBtnF5().setSelection(true);
		med.getBtnF6().setSelection(false);
		med.getBtnF7().setSelection(false);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F5));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F5));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F5));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F5));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F5));
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F5));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F5));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F5));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F5));
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F5));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F5));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F5));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F5));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F5));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F5));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F5));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F5));
		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F5));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F5));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F5));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F5));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F5));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F5));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F5));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F5));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F5));
		med.getTextTopBotVelRate().setText(obj.getInitValue(InitValue.tb_vel_rate_F5));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F5));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F5));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F5));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F5));
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F5).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}
		
		med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F5));
		if(obj.getInitValue(InitValue.TEC_Constant_F5).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F5));
		
		if(obj.getInitValue(InitValue.PR_Constant_F5).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F5));
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F5));
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getInitValue(InitValue.lcase_time_F5));
		med.getTextTimeIncrement_dt().setText(obj.getInitValue(InitValue.lcase_dt_F5));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F5));
		med.getTextIncrementTime().setText(obj.getInitValue(InitValue.inc_time_F5));
		if(obj.getInitValue(InitValue.ParallelDDM_F5).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F5)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F5)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F5).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F5)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F5)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void initF6Values(){
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(false);
		med.getBtnF2().setSelection(false);
		med.getBtnF3().setSelection(false);
		med.getBtnF4().setSelection(false);
		med.getBtnF5().setSelection(false);
		med.getBtnF6().setSelection(true);
		med.getBtnF7().setSelection(false);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F6));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F6));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F6));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F6));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F6));
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F6));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F6));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F6));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F6));
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F6));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F6));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F6));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F6));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F6));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F6));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F6));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F6));
		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F6));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F6));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F6));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F6));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F6));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F6));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F6));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F6));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F6));
		med.getTextTopBotVelRate().setText(obj.getInitValue(InitValue.tb_vel_rate_F6));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F6));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F6));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F6));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F6));
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F6).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}
		
		med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F6));
		if(obj.getInitValue(InitValue.TEC_Constant_F6).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F6));
		
		if(obj.getInitValue(InitValue.PR_Constant_F6).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F6));
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F6));
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getInitValue(InitValue.lcase_time_F6));
		med.getTextTimeIncrement_dt().setText(obj.getInitValue(InitValue.lcase_dt_F6));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F6));
		med.getTextIncrementTime().setText(obj.getInitValue(InitValue.inc_time_F6));
		if(obj.getInitValue(InitValue.ParallelDDM_F6).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F6)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F6)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F6).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F6)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F6)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void initF7Values(){
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(false);
		med.getBtnF2().setSelection(false);
		med.getBtnF3().setSelection(false);
		med.getBtnF4().setSelection(false);
		med.getBtnF5().setSelection(false);
		med.getBtnF6().setSelection(false);
		med.getBtnF7().setSelection(true);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F7));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F7));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F7));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F7));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F7));
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F7));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F7));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F7));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F7));
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F7));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F7));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F7));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F7));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F7));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F7));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F7));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F7));
		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F7));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F7));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F7));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F7));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F7));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F7));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F7));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F7));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F7));
		med.getTextTopBotVelRate().setText(obj.getInitValue(InitValue.tb_vel_rate_F7));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F7));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F7));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F7));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F7));
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F7).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}
		
		med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F7));
		if(obj.getInitValue(InitValue.TEC_Constant_F7).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F7));
		
		if(obj.getInitValue(InitValue.PR_Constant_F7).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F7));
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F7));
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getInitValue(InitValue.lcase_time_F7));
		med.getTextTimeIncrement_dt().setText(obj.getInitValue(InitValue.lcase_dt_F7));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F7));
		med.getTextIncrementTime().setText(obj.getInitValue(InitValue.inc_time_F7));
		if(obj.getInitValue(InitValue.ParallelDDM_F7).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F7)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F7)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F7).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F7)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F7)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	public void ChangedSTANDValue(String value){
		this.StandValue = value;
		//System.out.println("Current STAND : "+value);
		// UI 초기값 변경 코드 삽입
		switch(this.StandValue){
		case "F1":
			this.changedF1UIValues();
			//System.out.println(tableDataPLogList.get(0).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(0).getPR_Value());
			break;
		case "F2":
			this.changedF2UIValues();
			//System.out.println(tableDataPLogList.get(1).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(1).getPR_Value());
			break;
		case "F3":
			this.changedF3UIValues();
			//System.out.println(tableDataPLogList.get(2).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(2).getPR_Value());
			break;
		case "F4":
			this.changedF4UIValues();
			//System.out.println(tableDataPLogList.get(3).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(3).getPR_Value());
			break;
		case "F5":
			this.changedF5UIValues();
			//System.out.println(tableDataPLogList.get(4).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(4).getPR_Value());
			break;
		case "F6":
			this.changedF6UIValues();
			//System.out.println(tableDataPLogList.get(5).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(5).getPR_Value());
			break;
		case "F7":
			this.changedF7UIValues();
			//System.out.println(tableDataPLogList.get(6).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(6).getPR_Value());
			break;
		}
	}
	
	private void changedF1UIValues(){
		TableData_PLog obj = tableDataPLogList.get(0);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextTopBotVelRate().setText(obj.getTb_vel_rate());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		//Group5
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}
		med.getTextYoungsModulus().setText(obj.getYM_Value());
		
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getLcase_time());
		med.getTextTimeIncrement_dt().setText(obj.getLcase_dt());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextIncrementTime().setText(obj.getInc_time());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void changedF2UIValues(){
		TableData_PLog obj = tableDataPLogList.get(1);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextTopBotVelRate().setText(obj.getTb_vel_rate());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		//Group5
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}

		med.getTextYoungsModulus().setText(obj.getYM_Value());
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getLcase_time());
		med.getTextTimeIncrement_dt().setText(obj.getLcase_dt());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextIncrementTime().setText(obj.getInc_time());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void changedF3UIValues(){
		TableData_PLog obj = tableDataPLogList.get(2);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextTopBotVelRate().setText(obj.getTb_vel_rate());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		//Group5
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}

		med.getTextYoungsModulus().setText(obj.getYM_Value());
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getLcase_time());
		med.getTextTimeIncrement_dt().setText(obj.getLcase_dt());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextIncrementTime().setText(obj.getInc_time());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void changedF4UIValues(){
		TableData_PLog obj = tableDataPLogList.get(3);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextTopBotVelRate().setText(obj.getTb_vel_rate());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		//Group5
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}

		med.getTextYoungsModulus().setText(obj.getYM_Value());
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getLcase_time());
		med.getTextTimeIncrement_dt().setText(obj.getLcase_dt());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextIncrementTime().setText(obj.getInc_time());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void changedF5UIValues(){
		TableData_PLog obj = tableDataPLogList.get(4);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextTopBotVelRate().setText(obj.getTb_vel_rate());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		//Group5
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}

		med.getTextYoungsModulus().setText(obj.getYM_Value());
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getLcase_time());
		med.getTextTimeIncrement_dt().setText(obj.getLcase_dt());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextIncrementTime().setText(obj.getInc_time());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void changedF6UIValues(){
		TableData_PLog obj = tableDataPLogList.get(5);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextTopBotVelRate().setText(obj.getTb_vel_rate());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		//Group5
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}

		med.getTextYoungsModulus().setText(obj.getYM_Value());
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getLcase_time());
		med.getTextTimeIncrement_dt().setText(obj.getLcase_dt());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextIncrementTime().setText(obj.getInc_time());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void changedF7UIValues(){
		TableData_PLog obj = tableDataPLogList.get(6);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextTopBotVelRate().setText(obj.getTb_vel_rate());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		//Group5
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}

		med.getTextYoungsModulus().setText(obj.getYM_Value());
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		//Group6
		med.getTextTimeIncrement_time().setText(obj.getLcase_time());
		med.getTextTimeIncrement_dt().setText(obj.getLcase_dt());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextIncrementTime().setText(obj.getInc_time());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	
	public void ChangedTextWidget(String value,String widgetName){
		//System.out.println("[ChangedTextWidget] widgetName : "+widgetName+"  ||  key : "+value);
		switch(this.StandValue){
			case "F1":
				this.saveF1Values(value, widgetName);
				break;
			case "F2":
				this.saveF2Values(value, widgetName);
				break;
			case "F3":
				this.saveF3Values(value, widgetName);
				break;
			case "F4":
				this.saveF4Values(value, widgetName);
				break;
			case "F5":
				this.saveF5Values(value, widgetName);
				break;
			case "F6":
				this.saveF6Values(value, widgetName);
				break;
			case "F7":
				this.saveF7Values(value, widgetName);
				break;
		}
	}
	
	private void saveF1Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(0);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBotVelRate)){
			obj.setTb_vel_rate(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			obj.setYM_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			obj.setTEC_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_time)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_dt)){
			obj.setLcase_dt(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textIncrementTime)){
			obj.setInc_time(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
	}
	
	private void saveF2Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(1);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBotVelRate)){
			obj.setTb_vel_rate(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			obj.setYM_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			obj.setTEC_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_time)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_dt)){
			obj.setLcase_dt(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textIncrementTime)){
			obj.setInc_time(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
	}
	
	private void saveF3Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(2);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBotVelRate)){
			obj.setTb_vel_rate(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			obj.setYM_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			obj.setTEC_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_time)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_dt)){
			obj.setLcase_dt(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textIncrementTime)){
			obj.setInc_time(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
	}
	
	private void saveF4Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(3);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBotVelRate)){
			obj.setTb_vel_rate(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			obj.setYM_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			obj.setTEC_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_time)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_dt)){
			obj.setLcase_dt(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textIncrementTime)){
			obj.setInc_time(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
	}
	
	private void saveF5Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(4);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBotVelRate)){
			obj.setTb_vel_rate(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			obj.setYM_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			obj.setTEC_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_time)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_dt)){
			obj.setLcase_dt(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textIncrementTime)){
			obj.setInc_time(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
	}
	
	private void saveF6Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(5);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBotVelRate)){
			obj.setTb_vel_rate(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			obj.setYM_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			obj.setTEC_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_time)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_dt)){
			obj.setLcase_dt(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textIncrementTime)){
			obj.setInc_time(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
	}
	
	private void saveF7Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(6);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBotVelRate)){
			obj.setTb_vel_rate(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			obj.setYM_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			obj.setTEC_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_time)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_dt)){
			obj.setLcase_dt(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textIncrementTime)){
			obj.setInc_time(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
	}
	
	public void Explorer_YoungsModulus(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerYoungsModulus().getShell(),SWT.OPEN);
		dlg.setText("Select Young's Modulus File");
				
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			//this.textYoungsModulus = path;
			this.ChangedTextWidget(path, Mediator.TEXT_textYoungsModulus);
			med.getTextYoungsModulus().setText(path);
		}
	}
	
	public void Explorer_ThermalExpansionCoefficient(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerThermalExpansionCoefficient().getShell(),SWT.OPEN);
		dlg.setText("Select Thermal Expansion Codfficient File");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			this.ChangedTextWidget(path, Mediator.TEXT_textThermalExpansionCoefficient);
			med.getTextThermalExpansionCoefficient().setText(path);
		}
	}

	public void Explorer_PoissonsRatio(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerPoissonsRatio().getShell(),SWT.OPEN);
		dlg.setText("Select Poisson Ratio File");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			//this.textPoissonsRatio = path;
			this.ChangedTextWidget(path, Mediator.TEXT_textPoissonsRatio);
			med.getTextPoissonsRatio().setText(path);
		}
	}
	
	public void ChangedTabFolder(int index){
		if(index == 0){
			this.loadPlogTables();
		}else if(index == 1){
			med.getBtnF1().setSelection(true);
			med.getBtnF2().setSelection(false);
			med.getBtnF3().setSelection(false);
			med.getBtnF4().setSelection(false);
			med.getBtnF5().setSelection(false);
			med.getBtnF6().setSelection(false);
			med.getBtnF7().setSelection(false);
			this.ChangedSTANDValue("F1");
		}
	}
	
	private void loadPlogTables(){
		ArrayList<String> initDataList = new ArrayList<String>();
		initDataList.add("STRIP NO,STHK,SWID,SLEN,SWET,PTHK,PWID,PLEN,PWET,,,,,,,");
		String line2 = TableDataSlabPlateInfoObj.getSTRIP_NO()+","+
				TableDataSlabPlateInfoObj.getSTHK()+","+
				TableDataSlabPlateInfoObj.getSWID()+","+
				TableDataSlabPlateInfoObj.getSLEN()+","+
				TableDataSlabPlateInfoObj.getSWET()+","+
				TableDataSlabPlateInfoObj.getPTHK()+","+
				TableDataSlabPlateInfoObj.getPWID()+","+
				TableDataSlabPlateInfoObj.getPLEN()+","+
				TableDataSlabPlateInfoObj.getPWET()+","+
				",,,,,,";
		initDataList.add(line2);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16");
		String line5 = TableDataVariableObj.getVAR1()+","+
				TableDataVariableObj.getVAR2()+","+
				TableDataVariableObj.getVAR3()+","+
				TableDataVariableObj.getVAR4()+","+
				TableDataVariableObj.getVAR5()+","+
				TableDataVariableObj.getVAR6()+","+
				TableDataVariableObj.getVAR7()+","+
				TableDataVariableObj.getVAR8()+","+
				TableDataVariableObj.getVAR9()+","+
				TableDataVariableObj.getVAR10()+","+
				TableDataVariableObj.getVAR11()+","+
				TableDataVariableObj.getVAR12()+","+
				TableDataVariableObj.getVAR13()+","+
				TableDataVariableObj.getVAR14()+","+
				TableDataVariableObj.getVAR15()+","+
				TableDataVariableObj.getVAR16();
		
		initDataList.add(line5);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("STAND,F1,F2,F3,F4,F5,F6,F7,,,,,,,,");
		String line8 = "BUR TDIA"+","+
					tableDataPLogList.get(0).getBUR_TDIA()+","+
					tableDataPLogList.get(1).getBUR_TDIA()+","+
					tableDataPLogList.get(2).getBUR_TDIA()+","+
					tableDataPLogList.get(3).getBUR_TDIA()+","+
					tableDataPLogList.get(4).getBUR_TDIA()+","+
					tableDataPLogList.get(5).getBUR_TDIA()+","+
					tableDataPLogList.get(6).getBUR_TDIA()+","+
					",,,,,,,";
		initDataList.add(line8);
		String line9 = "BUR BDIA"+","+
					tableDataPLogList.get(0).getBUR_BDIA()+","+
					tableDataPLogList.get(1).getBUR_BDIA()+","+
					tableDataPLogList.get(2).getBUR_BDIA()+","+
					tableDataPLogList.get(3).getBUR_BDIA()+","+
					tableDataPLogList.get(4).getBUR_BDIA()+","+
					tableDataPLogList.get(5).getBUR_BDIA()+","+
					tableDataPLogList.get(6).getBUR_BDIA()+","+
					",,,,,,,";
		initDataList.add(line9);
		String line10 = "WR TDIA"+","+
					tableDataPLogList.get(0).getWR_TDIA()+","+
					tableDataPLogList.get(1).getWR_TDIA()+","+
					tableDataPLogList.get(2).getWR_TDIA()+","+
					tableDataPLogList.get(3).getWR_TDIA()+","+
					tableDataPLogList.get(4).getWR_TDIA()+","+
					tableDataPLogList.get(5).getWR_TDIA()+","+
					tableDataPLogList.get(6).getWR_TDIA()+","+
					",,,,,,,";
		initDataList.add(line10);
		String line11 = "WR BDIA"+","+
					tableDataPLogList.get(0).getWR_BDIA()+","+
					tableDataPLogList.get(1).getWR_BDIA()+","+
					tableDataPLogList.get(2).getWR_BDIA()+","+
					tableDataPLogList.get(3).getWR_BDIA()+","+
					tableDataPLogList.get(4).getWR_BDIA()+","+
					tableDataPLogList.get(5).getWR_BDIA()+","+
					tableDataPLogList.get(6).getWR_BDIA()+","+
					",,,,,,,";
		initDataList.add(line11);
		String line12 = "WR ICRN"+","+
					tableDataPLogList.get(0).getWR_ICRN()+","+
					tableDataPLogList.get(1).getWR_ICRN()+","+
					tableDataPLogList.get(2).getWR_ICRN()+","+
					tableDataPLogList.get(3).getWR_ICRN()+","+
					tableDataPLogList.get(4).getWR_ICRN()+","+
					tableDataPLogList.get(5).getWR_ICRN()+","+
					tableDataPLogList.get(6).getWR_ICRN()+","+
					",,,,,,,";
		initDataList.add(line12);
		String line13 = "ENTRY THK"+","+
					tableDataPLogList.get(0).getENTRY_THK()+","+
					tableDataPLogList.get(1).getENTRY_THK()+","+
					tableDataPLogList.get(2).getENTRY_THK()+","+
					tableDataPLogList.get(3).getENTRY_THK()+","+
					tableDataPLogList.get(4).getENTRY_THK()+","+
					tableDataPLogList.get(5).getENTRY_THK()+","+
					tableDataPLogList.get(6).getENTRY_THK()+","+
					",,,,,,,";
		initDataList.add(line13);
		String line14 = "EXIT THK"+","+
					tableDataPLogList.get(0).getEXIT_THK()+","+
					tableDataPLogList.get(1).getEXIT_THK()+","+
					tableDataPLogList.get(2).getEXIT_THK()+","+
					tableDataPLogList.get(3).getEXIT_THK()+","+
					tableDataPLogList.get(4).getEXIT_THK()+","+
					tableDataPLogList.get(5).getEXIT_THK()+","+
					tableDataPLogList.get(6).getEXIT_THK()+","+
					",,,,,,,";
		initDataList.add(line14);
		String line15 = "PAS LINE"+","+
					tableDataPLogList.get(0).getPAS_LINE()+","+
					tableDataPLogList.get(1).getPAS_LINE()+","+
					tableDataPLogList.get(2).getPAS_LINE()+","+
					tableDataPLogList.get(3).getPAS_LINE()+","+
					tableDataPLogList.get(4).getPAS_LINE()+","+
					tableDataPLogList.get(5).getPAS_LINE()+","+
					tableDataPLogList.get(6).getPAS_LINE()+","+
					",,,,,,,";
		initDataList.add(line15);
		String line16 = "ROL GAP"+","+
					tableDataPLogList.get(0).getROL_GAP()+","+
					tableDataPLogList.get(1).getROL_GAP()+","+
					tableDataPLogList.get(2).getROL_GAP()+","+
					tableDataPLogList.get(3).getROL_GAP()+","+
					tableDataPLogList.get(4).getROL_GAP()+","+
					tableDataPLogList.get(5).getROL_GAP()+","+
					tableDataPLogList.get(6).getROL_GAP()+","+
					",,,,,,,";
		initDataList.add(line16);
		String line17 = "STP WID"+","+
					tableDataPLogList.get(0).getSTP_WID()+","+
					tableDataPLogList.get(1).getSTP_WID()+","+
					tableDataPLogList.get(2).getSTP_WID()+","+
					tableDataPLogList.get(3).getSTP_WID()+","+
					tableDataPLogList.get(4).getSTP_WID()+","+
					tableDataPLogList.get(5).getSTP_WID()+","+
					tableDataPLogList.get(6).getSTP_WID()+","+
					",,,,,,,";
		initDataList.add(line17);
		String line18 = "STP LEN"+","+
					tableDataPLogList.get(0).getSTP_LEN()+","+
					tableDataPLogList.get(1).getSTP_LEN()+","+
					tableDataPLogList.get(2).getSTP_LEN()+","+
					tableDataPLogList.get(3).getSTP_LEN()+","+
					tableDataPLogList.get(4).getSTP_LEN()+","+
					tableDataPLogList.get(5).getSTP_LEN()+","+
					tableDataPLogList.get(6).getSTP_LEN()+","+
					",,,,,,,";
		initDataList.add(line18);
		String line19 = "ENTRY TEMP"+","+
					tableDataPLogList.get(0).getSTP_LEN()+","+
					tableDataPLogList.get(1).getSTP_LEN()+","+
					tableDataPLogList.get(2).getSTP_LEN()+","+
					tableDataPLogList.get(3).getSTP_LEN()+","+
					tableDataPLogList.get(4).getSTP_LEN()+","+
					tableDataPLogList.get(5).getSTP_LEN()+","+
					tableDataPLogList.get(6).getSTP_LEN()+","+
					",,,,,,,";
		initDataList.add(line19);
		String line20 = "EXIT TEMP"+","+
					tableDataPLogList.get(0).getEXIT_TEMP()+","+
					tableDataPLogList.get(1).getEXIT_TEMP()+","+
					tableDataPLogList.get(2).getEXIT_TEMP()+","+
					tableDataPLogList.get(3).getEXIT_TEMP()+","+
					tableDataPLogList.get(4).getEXIT_TEMP()+","+
					tableDataPLogList.get(5).getEXIT_TEMP()+","+
					tableDataPLogList.get(6).getEXIT_TEMP()+","+
					",,,,,,,";
		initDataList.add(line20);
		String line21 = "FRCE"+","+
					tableDataPLogList.get(0).getFRCE()+","+
					tableDataPLogList.get(1).getFRCE()+","+
					tableDataPLogList.get(2).getFRCE()+","+
					tableDataPLogList.get(3).getFRCE()+","+
					tableDataPLogList.get(4).getFRCE()+","+
					tableDataPLogList.get(5).getFRCE()+","+
					tableDataPLogList.get(6).getFRCE()+","+					
					",,,,,,,";
		initDataList.add(line21);
		String line22 = "TORQ"+","+
					tableDataPLogList.get(0).getTORQ()+","+					
					tableDataPLogList.get(1).getTORQ()+","+
					tableDataPLogList.get(2).getTORQ()+","+
					tableDataPLogList.get(3).getTORQ()+","+
					tableDataPLogList.get(4).getTORQ()+","+
					tableDataPLogList.get(5).getTORQ()+","+
					tableDataPLogList.get(6).getTORQ()+","+
					",,,,,,,";
		initDataList.add(line22);
		String line23 = "SPEED(mpm)"+","+
					tableDataPLogList.get(0).getSPEED()+","+
					tableDataPLogList.get(1).getSPEED()+","+
					tableDataPLogList.get(2).getSPEED()+","+
					tableDataPLogList.get(3).getSPEED()+","+
					tableDataPLogList.get(4).getSPEED()+","+
					tableDataPLogList.get(5).getSPEED()+","+
					tableDataPLogList.get(6).getSPEED()+","+					
					",,,,,,,";
		initDataList.add(line23);
		String line24 = "BEND"+","+
					tableDataPLogList.get(0).getBEND()+","+
					tableDataPLogList.get(1).getBEND()+","+
					tableDataPLogList.get(2).getBEND()+","+
					tableDataPLogList.get(3).getBEND()+","+
					tableDataPLogList.get(4).getBEND()+","+
					tableDataPLogList.get(5).getBEND()+","+
					tableDataPLogList.get(6).getBEND()+","+
					",,,,,,,";
		initDataList.add(line24);
		String line25 = "P-CROSS"+","+
					tableDataPLogList.get(0).getP_CROSS()+","+
					tableDataPLogList.get(1).getP_CROSS()+","+
					tableDataPLogList.get(2).getP_CROSS()+","+
					tableDataPLogList.get(3).getP_CROSS()+","+
					tableDataPLogList.get(4).getP_CROSS()+","+
					tableDataPLogList.get(5).getP_CROSS()+","+
					tableDataPLogList.get(6).getP_CROSS()+","+
					",,,,,,,";
		initDataList.add(line25);
		String line26 = "TENS"+","+
					tableDataPLogList.get(0).getTENS()+","+
					tableDataPLogList.get(1).getTENS()+","+
					tableDataPLogList.get(2).getTENS()+","+
					tableDataPLogList.get(3).getTENS()+","+
					tableDataPLogList.get(4).getTENS()+","+
					tableDataPLogList.get(5).getTENS()+","+
					tableDataPLogList.get(6).getTENS()+","+
					",,,,,,,";
		initDataList.add(line26);
		String line27 = "ROL TIM"+","+
					tableDataPLogList.get(0).getROL_TIM()+","+
					tableDataPLogList.get(1).getROL_TIM()+","+
					tableDataPLogList.get(2).getROL_TIM()+","+
					tableDataPLogList.get(3).getROL_TIM()+","+
					tableDataPLogList.get(4).getROL_TIM()+","+
					tableDataPLogList.get(5).getROL_TIM()+","+
					tableDataPLogList.get(6).getROL_TIM()+","+					
					",,,,,,,";
		initDataList.add(line27);
		String line28 = "IDL TIM"+","+
					tableDataPLogList.get(0).getIDL_TIM()+","+
					tableDataPLogList.get(1).getIDL_TIM()+","+
					tableDataPLogList.get(2).getIDL_TIM()+","+
					tableDataPLogList.get(3).getIDL_TIM()+","+
					tableDataPLogList.get(4).getIDL_TIM()+","+
					tableDataPLogList.get(5).getIDL_TIM()+","+
					tableDataPLogList.get(6).getIDL_TIM()+","+
					",,,,,,,";
		initDataList.add(line28);
		String line29 = "BUR WEAR"+","+
					tableDataPLogList.get(0).getBUR_WEAR()+","+
					tableDataPLogList.get(1).getBUR_WEAR()+","+
					tableDataPLogList.get(2).getBUR_WEAR()+","+
					tableDataPLogList.get(3).getBUR_WEAR()+","+
					tableDataPLogList.get(4).getBUR_WEAR()+","+
					tableDataPLogList.get(5).getBUR_WEAR()+","+
					tableDataPLogList.get(6).getBUR_WEAR()+","+					
					",,,,,,,";
		initDataList.add(line29);
		String line30 = "WR WEAR"+","+ 
					tableDataPLogList.get(0).getWR_WEAR()+","+
					tableDataPLogList.get(1).getWR_WEAR()+","+
					tableDataPLogList.get(2).getWR_WEAR()+","+
					tableDataPLogList.get(3).getWR_WEAR()+","+
					tableDataPLogList.get(4).getWR_WEAR()+","+
					tableDataPLogList.get(5).getWR_WEAR()+","+
					tableDataPLogList.get(6).getWR_WEAR()+","+
					",,,,,,,";
		initDataList.add(line30);
		String line31 = "WR THRM"+","+
					tableDataPLogList.get(0).getWR_THRM()+","+
					tableDataPLogList.get(1).getWR_THRM()+","+
					tableDataPLogList.get(2).getWR_THRM()+","+
					tableDataPLogList.get(3).getWR_THRM()+","+
					tableDataPLogList.get(4).getWR_THRM()+","+
					tableDataPLogList.get(5).getWR_THRM()+","+
					tableDataPLogList.get(6).getWR_THRM()+","+					
					",,,,,,,";
		initDataList.add(line31);
		this.parsingPLogFile(initDataList);
	}
	
	//
	//
	//=====================================================================
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getWorkspace() {
		return workspace;
	}
	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}
	public TableData_SlabPlateInfo getTableDataSlabPlateInfoObj() {
		return TableDataSlabPlateInfoObj;
	}
	public void setTableDataSlabPlateInfoObj(
			TableData_SlabPlateInfo tableDataSlabPlateInfoObj) {
		TableDataSlabPlateInfoObj = tableDataSlabPlateInfoObj;
	}
	public TableData_Variable getTableDataVariableObj() {
		return TableDataVariableObj;
	}
	public void setTableDataVariableObj(TableData_Variable tableDataVariableObj) {
		TableDataVariableObj = tableDataVariableObj;
	}
	public ArrayList<TableData_PLog> getTableDataPLogList() {
		return tableDataPLogList;
	}
	public void setTableDataPLogList(ArrayList<TableData_PLog> tableDataPLogList) {
		this.tableDataPLogList = tableDataPLogList;
	}
	
}


