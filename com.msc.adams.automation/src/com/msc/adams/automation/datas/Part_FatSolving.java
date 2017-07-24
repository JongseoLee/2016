package com.msc.adams.automation.datas;

import org.eclipse.swt.custom.CCombo;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.customTable.TableEditorInfo_FatSolving;

public class Part_FatSolving {
	public MainController MC = MainController.getInstance();
	
	//public static final String Type_MSR = "MSR";
	public static final String Type_MSR_AUTO = "MSR AUTO RUN";
	public static final String Type_MSR_MANUAL = "MSR MANUAL RUN";
	public static final String Type_MSM = "MSM";
	public static final String Path_Empty = "EMPTY";
	
	private String partName;
	//private String type = Type_MSR;
	private String type = Type_MSR_MANUAL;
	private boolean SwappingMNF = false;
	private String SwappingType;
	private String bulkForMNFPath = Path_Empty; //// MSR , MSM 용 input
	private String OP2Path = Path_Empty;
	private String BDFPath = Path_Empty;	
	private CCombo combo;
	private String onlyBulkFilePath;
	
	// 최종 입력 데이터 경로!!!
	private String finalDACFolder;
	private String finalBulkPath;
	private String finalOP2Path;
	private String finalDACNumber;
	
	
	private TableEditorInfo_FatSolving tableEditorInfoObj = null;
	
	
	public Part_FatSolving() {
		// TODO Auto-generated constructor stub
		
	}


	public String getPartName() {
		return partName;
	}


	public void setPartName(String partName) {
		this.partName = partName;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getSwappingType() {
		return SwappingType;
	}


	public void setSwappingType(String swappingType) {
		SwappingType = swappingType;
	}


	public boolean isSwappingMNF() {
		return SwappingMNF;
	}


	public void setSwappingMNF(boolean swappingMNF) {
		SwappingMNF = swappingMNF;
	}



	public String getBulkForMNFPath() {
		return bulkForMNFPath;
	}


	public void setBulkForMNFPath(String bulkForMNFPath) {
		this.bulkForMNFPath = bulkForMNFPath;
	}


	public String getOP2Path() {
		return OP2Path;
	}


	public void setOP2Path(String oP2Path) {
		OP2Path = oP2Path;
	}


	public String getBDFPath() {
		return BDFPath;
	}


	public void setBDFPath(String bDFPath) {
		BDFPath = bDFPath;
	}


	public CCombo getCombo() {
		return combo;
	}


	public void setCombo(CCombo combo) {
		this.combo = combo;
	}


	public TableEditorInfo_FatSolving getTableEditorInfoObj() {
		return tableEditorInfoObj;
	}


	public void setTableEditorInfoObj(TableEditorInfo_FatSolving tableEditorInfoObj) {
		this.tableEditorInfoObj = tableEditorInfoObj;
	}


	public String getFinalDACFolder() {
		return finalDACFolder;
	}


	public void setFinalDACFolder(String finalDACFolder) {
		this.finalDACFolder = finalDACFolder;
	}


	public String getFinalBulkPath() {
		return finalBulkPath;
	}


	public void setFinalBulkPath(String finalBulkPath) {
		this.finalBulkPath = finalBulkPath;
	}


	public String getFinalOP2Path() {
		return finalOP2Path;
	}


	public void setFinalOP2Path(String finalOP2Path) {
		this.finalOP2Path = finalOP2Path;
	}


	public String getFinalDACNumber() {
		return finalDACNumber;
	}


	public void setFinalDACNumber(String finalDACNumber) {
		this.finalDACNumber = finalDACNumber;
	}


	/**
	 * @return the onlyBulkFilePath
	 */
	public String getOnlyBulkFilePath() {
		return onlyBulkFilePath;
	}


	/**
	 * @param onlyBulkFilePath the onlyBulkFilePath to set
	 */
	public void setOnlyBulkFilePath(String onlyBulkFilePath) {
		this.onlyBulkFilePath = onlyBulkFilePath;
	}

}
