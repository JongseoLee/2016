package com.msc.adams.automation.datas;

import java.util.ArrayList;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.util.myUtil;

public class SwappingPart {
	public MainController MC = MainController.getInstance();
	
	public static final String Type_NAS = "NAS";		//default value
	public static final String Type_MNF = "MNF";		//default value
	public static final String Type_OPT = "OPT";
	public static final String Path_Empty = "EMPTY";	//default value
	
	
	private String partName;
	private String originalType = Type_NAS;
	private String Type =Type_NAS;
	private String bulkPath = Path_Empty;
	private String bulkRenamePath = Path_Empty;
	private String path = Path_Empty; // empty || real path
	private String renamePath = Path_Empty;
	private String inputdeckPath = Path_Empty;
	private String op2Path = Path_Empty;
	private ArrayList<String> bulkDataList = new ArrayList<String>();
	
	private String onlyBulkFilePath = Path_Empty;
	
	

	
	
	public SwappingPart() {
		// TODO Auto-generated constructor stub
	}
	
	public String getStringSwappingPartDB(){
		//partName,type,bulkPath,inputdecPath,MNFPath
		String data = this.partName+","+this.Type+","+this.bulkPath+","+this.inputdeckPath+","+this.path+",";
		return data;
	}
	/*
	public void AutoLoadingSwappingPart(String partName, String type, String path){
		
		// msck 파일 로딩시 사용
		this.partName = partName;
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

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
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

	public String getBulkPath() {
		return bulkPath;
	}

	public void setBulkPath(String bulkPath) {
		this.bulkPath = bulkPath;
	}

	public String getBulkRenamePath() {
		return bulkRenamePath;
	}

	public void setBulkRenamePath(String bulkRenamePath) {
		this.bulkRenamePath = bulkRenamePath;
	}

	public String getInputdeckPath() {
		return inputdeckPath;
	}

	public void setInputdeckPath(String inputdeckPath) {
		this.inputdeckPath = inputdeckPath;
	}

	public String getOp2Path() {
		return op2Path;
	}

	public void setOp2Path(String op2Path) {
		this.op2Path = op2Path;
	}

	/* */
	public ArrayList<String> getBulkDataList() {
		this.bulkDataList.clear();
		return bulkDataList;
	}

	public void setBulkDataList(ArrayList<String> bulkDataList) {
		this.bulkDataList = bulkDataList;
	}
	// */
	/**
	 * @return the originalType
	 */
	public String getOriginalType() {
		return originalType;
	}

	/**
	 * @param originalType the originalType to set
	 */
	public void setOriginalType(String originalType) {
		this.originalType = originalType;
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
