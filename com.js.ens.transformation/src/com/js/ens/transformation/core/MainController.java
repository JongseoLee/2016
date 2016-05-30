package com.js.ens.transformation.core;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

public class MainController {
	
	private Mediator med = Mediator.getInstance();
	
	private static MainController instance = new MainController();
	public static MainController getInstance(){
		return instance;
	}
	//--------------
	// tab1
	private String PLogFilePath = null;
	
	private ArrayList<TableData_SlabPlateInfo> tableDataSlabPlateInfo = null;
	private ArrayList<TableData_Variable> tableDataVariable = null;
	private ArrayList<TableData_PLog> tableDataPLog = null;
	
	//--------------
	// tab2
	
	public MainController() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void ImportPLog(){
		FileDialog dlg = new FileDialog(med.getBtnImportPLog().getShell(),SWT.OPEN);
		dlg.setText("Select P Log.csv file.");
		String [] extNames = {"ALL(*.*)","CSV"};
		String [] extType = {"*.*","*.csv"}; 
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		String path = dlg.open();
		if(path == null){
			return;
		}else { 
			PLogFilePath = path;
			initTableData_SlabPlateInfo();
			initTableData_Variable();
			initTableData_PLog();
		}
	}

	public void initTableData_SlabPlateInfo(){
		System.out.println("1.P Log Path : "+this.PLogFilePath);
	}
	
	public void initTableData_Variable(){
		System.out.println("2.P Log Path : "+this.PLogFilePath);
	}
	
	public void initTableData_PLog(){
		System.out.println("3.P Log Path : "+this.PLogFilePath);
	}
	
	public void Apply(){
		System.out.println("Click Apply Button");
	}
	
}
