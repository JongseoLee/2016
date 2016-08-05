package com.js.ens.leveller.procs;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.io.Reader;
import com.js.util.myUtil;

public class CopyProc2D {
	private final static String _1_Flat = "1_Flat";
	private final static String _2_Wave = "2_Wave";
	private final static String _3_Curl = "3_Curl";
	
	private Logger log = Logger.getLogger(CopyProc3D.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private String userDirPath = System.getProperty("user.dir");
	private String moduleFolder = myUtil.setPath(userDirPath, "module");
	private String source2DFolder = myUtil.setPath(moduleFolder, "2D");
	private String filelistFolder = myUtil.setPath(moduleFolder, "filelist");
	private String fileList2DFolder = myUtil.setPath(filelistFolder, "2D");
	

	private String comboType;
	private String procFolder;
	
	private ArrayList<String> fullPathList = new ArrayList<String>();
	private ArrayList<String> destFullPathList = new ArrayList<String>();
	
	public CopyProc2D(String comboType, String procFolder) {
		// TODO Auto-generated constructor stub
		this.comboType = comboType;
		this.procFolder = procFolder;
	}
	
	public void running(){
		this.copy(this.comboType);
	}
	
	private void copy(String comboType){
		String filelistPath = "";
		if(comboType.equals(ComboLabel.TYPE1_2D)){
			filelistPath = myUtil.setPath(fileList2DFolder, this._1_Flat+".filelist");
		}else if(comboType.equals(ComboLabel.TYPE2_2D)){
			filelistPath = myUtil.setPath(fileList2DFolder, this._2_Wave+".filelist");
		}else if(comboType.equals(ComboLabel.TYPE3_2D)){
			filelistPath = myUtil.setPath(fileList2DFolder, this._3_Curl+".filelist");
		}
		this.sourceFilePahtList(filelistPath);
		
		for(int i=0;i<this.fullPathList.size();i++){
			try{
				myUtil.fileCopy(this.fullPathList.get(i), this.destFullPathList.get(i));
				log.info("SUCCESS -  Copy Proc File \n path : "+myUtil.getFileName(this.destFullPathList.get(i)));	
			}catch(Exception e){
				String msg = "ERROR - Copy "+myUtil.getFileName(this.destFullPathList.get(i));
				msg = msg +"\n"+e.getMessage();
				MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
				messageDlg.open();
				log.error(msg);
				LMain.getExportMSG().addData(msg);
			}
		}
	}
	
	private void sourceFilePahtList(String filelistPath){
		ArrayList<String> moduleFileList = new ArrayList<String>();
		Reader reader = new Reader(filelistPath);
		reader.running();
		moduleFileList = reader.getFileDataList();
		
		
		for(String filePath : moduleFileList){
			String fullPath = myUtil.setPath(source2DFolder, filePath);
			String destFullPath = myUtil.setPath(procFolder, myUtil.getFileName(fullPath));
			fullPathList.add(fullPath);
			destFullPathList.add(destFullPath);
		}
	}

	public ArrayList<String> getFullPathList() {
		return fullPathList;
	}

	public ArrayList<String> getDestFullPathList() {
		return destFullPathList;
	}
}
