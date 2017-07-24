package com.msc.adams.automation.datas;

import org.eclipse.swt.custom.CCombo;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.adams.automation.customTable.TableEditorInfo;
import com.msc.util.myUtil;


public class Part {
	public MainController MC = MainController.getInstance();
	
	public static final String Type_NAS = "NAS";		//default value
	public static final String Type_MNF = "MNF";		//default value
	public static final String Type_OPT = "OPT";		//default value
	public static final String Path_Empty = "EMPTY";	//default value
	
	
	private String partName;
	
	private boolean isSelected = false;
	private String Type =Type_NAS;
	private String bulkPath = Path_Empty;		// real bulk Path;
	private String bulkRenamePath = Path_Empty;		// rename bulk Path for Fatige solving
	private String path = Path_Empty; // empty || real mnf path
	private String renamePath = Path_Empty;
	
	private String onlyBulkFilePath = Path_Empty;
	
	private CCombo combo;
	
	private TableEditorInfo tableEditorInfoObj = null;
	
	public Part() {
		// TODO Auto-generated constructor stub
	}
	
	public String getStringPartDB(){
		String data = this.partName +","+this.Type+","+this.path+","; 
		return data;
	}
	/*
	public void AutoLoadingPart(String partName,String type,String path){
		
		// msck 파일 로딩시 사용
		this.partName = partName;
		this.isSelected = false;
		this.Type = type;
		this.path = path;

		
		if(this.path.equals(Part.Path_Empty)){
			this.bulkRenamePath=  Part.Path_Empty;
		}else{
			String oriPath = path;
			String exetension = myUtil.getExtensions(oriPath);		
			String newFileName = partName+"."+exetension;
			String resultPath = myUtil.setPath(MC.getWorkspace(),DatabaseFolderName.Result);
			String InputdeckForMNFPath = myUtil.setPath(resultPath, DatabaseFolderName.InputdeckForMNF);
			String bulkBdf = myUtil.setPath(InputdeckForMNFPath, DatabaseFolderName.RenameBulkBdf);
			String newFilePath = myUtil.setPath(bulkBdf, newFileName);
			myUtil.fileCopy(oriPath, newFilePath);
			this.bulkRenamePath = newFilePath;
		}
		
	}
	// */
	
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public TableEditorInfo getTableEditorInfoObj() {
		return tableEditorInfoObj;
	}

	public void setTableEditorInfoObj(TableEditorInfo tableEditorInfoObj) {
		this.tableEditorInfoObj = tableEditorInfoObj;
	}

	public CCombo getCombo() {
		return combo;
	}

	public void setCombo(CCombo combo) {
		this.combo = combo;
	}
	
	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getBulkPath() {
		return bulkPath;
	}

	public void setBulkPath(String bulkPath) {
		this.bulkPath = bulkPath;
	}

	public String getbulkRenamePath() {
		return bulkRenamePath;
	}

	public void setbulkRenamePath(String bulkRenamePath) {
		this.bulkRenamePath = bulkRenamePath;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRenamePath() {
		return renamePath;
	}

	public void setRenamePath(String renamePath) {
		this.renamePath = renamePath;
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
