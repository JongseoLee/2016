package com.js.ens.transformation.core;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

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
import com.js.ens.transformation.dialog.NewDlg;
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
	// common
	private ArrayList<Boolean> ApplyResult = new ArrayList<Boolean>(); 
	
	private String modelName = null;
	private String workspace = null;
	
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
	
	// tableDataPLogList 는 F1 ~ F7 까지의 데이터 7개 obj가 저장됨
	private ArrayList<TableData_PLog> tableDataPLogList = null;
	private Map<String,String> tableDataPLogMap; 
	private ArrayList<TableRowData> tableRowDataList = null;
	
	//--------------
	// tab2
	private ArrayList<TableData_PLog> F_ObjList = new ArrayList<TableData_PLog>();
	
	
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
			
			this.updateTableData();
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
		/////////////////////////////////////////////////////////////////////
		//this.TableDataSlabPlateInfoObj.printAllData();
		//this.TableDataVariableObj.printAllData();
		/////////////////////////////////////////////////////////////////////
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
			/////////////////////////////////////////////////////////////////////
			//System.out.println(i + " : "+fileDataList.get(i));
			/////////////////////////////////////////////////////////////////////
			dataList.add(fileDataList.get(i));
		}
		this.createPLogObj(dataList);
		
		/////////////////////////////////////////////////////////////////////
		/*
		for(TableData_PLog obj : this.tableDataPLogList){
			obj.printAllData();
		}
		 */
		/////////////////////////////////////////////////////////////////////
		
		// UI에 표시한 데이터 형식으로 재 가공
		this.createTableRowData();
	}
	
	//F1~F7 Obj 만들기
	public void createPLogObj(ArrayList<String> dataList){
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
	public void createTableRowData(){
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
			//med.getTableViewerPLog().setInput(this.tableDataPLogList);
			med.getTableViewerPLog().setInput(this.tableRowDataList);
			
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
		ApplyDlg applyDlg = new ApplyDlg(Display.getCurrent().getActiveShell());
		applyDlg.open();
	}
	
	public void RunApplyResult(ArrayList<Boolean> result){
		ApplyResult = result;
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
	}
	
	private void makeResultF1(){
	}
	private void makeResultF2(){
	}
	private void makeResultF3(){
	}
	private void makeResultF4(){
	}
	private void makeResultF5(){
	}
	private void makeResultF6(){
	}
	private void makeResultF7(){
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
		/*
		if(!myUtil.makeDir(topFolder)) log.warn("[Model Name:"+ModelName +"] topFolder did not make.");
		if(!myUtil.makeDir(procFolder)) log.warn("[Model Name:"+ModelName +"] prodFolder did not make.");
		if(!myUtil.makeDir(resultFolder)) log.warn("[Model Name:"+ModelName +"] resultFolder did not make.");
		*/
		if(!myUtil.makeDir(topFolder)) System.out.println("[Model Name:"+modelName +"] topFolder did not make.");
		if(!myUtil.makeDir(procFolder)) System.out.println("[Model Name:"+modelName +"] prodFolder did not make.");
		if(!myUtil.makeDir(resultFolder)) System.out.println("[Model Name:"+modelName +"] resultFolder did not make.");
		
		this.AllComponentEnable();
		
		med.getLblModelNameValue().setText(this.modelName);
		med.getLblWorkspacePath().setText(this.workspace);
		
		this.setUpDataSheet();
		
	}
	
	private void AllComponentEnable(){
		med.getTabFolder().setEnabled(true);
	}
	
	private void CleaerAllData(){
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
	}
	
	public void RunOpenProject(){
	}
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Save Project
	//
	public void SaveProject(){
	}
	
	public void RunSaveProject(){
	}
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Save As Project
	//
	public void SaveAsProject(){
	}
	
	public void RunSaveAsProject(){
	}
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Export Project
	//
	public void ExportProject(){
	}
	
	public void RunExportProject(){
	}
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Result Project
	//
	public void ResultProject(){
	}
	
	public void RunResultProject(){
	}
	//
	//
	//=====================================================================
	
	//=====================================================================
	// 나머지 부분
	//
	public void ChangedTextWidget(){
		
	}
	
	//
	//
	//=====================================================================
}
