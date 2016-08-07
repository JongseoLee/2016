package com.js.ens.leveller.procs;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.io.Reader;
import com.js.util.myUtil;

public class CopyProc3D {
	private final static String _1_Flat = "1_Flat";
	private final static String _2_EdgeWave = "2_EdgeWave";
	private final static String _3_CenterWave = "3_CenterWave";
	private final static String _4_SingleGutter = "4_SingleGutter";
	private final static String _5_PartialGutter = "5_PartialGutter";
	private final static String _6_DoubleGutter = "6_DoubleGutter";
	private final static String _7_IslandGutter = "7_IslandGutter";
	
	private Logger log = Logger.getLogger(CopyProc3D.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private String userDirPath = System.getProperty("user.dir");
	private String moduleFolder = myUtil.setPath(userDirPath, "module");
	private String source3DFolder = myUtil.setPath(moduleFolder, "3D");
	private String filelistFolder = myUtil.setPath(moduleFolder, "filelist");
	private String fileList3DFolder = myUtil.setPath(filelistFolder, "3D");
	

	private String comboType;
	private String procFolder;
	
	private ArrayList<String> fullPathList = new ArrayList<String>();
	private ArrayList<String> destFullPathList = new ArrayList<String>();
	
	public CopyProc3D(String comboType, String procFolder) {
		// TODO Auto-generated constructor stub
		this.comboType = comboType;
		this.procFolder = procFolder;
	}
	
	public void running(){
		this.copy(this.comboType);
	}
	
	private void copy(String comboType){
		String filelistPath = "";
		if(comboType.equals(ComboLabel.TYPE1)){
			filelistPath = myUtil.setPath(fileList3DFolder, this._1_Flat+".filelist");
		}else if(comboType.equals(ComboLabel.TYPE2)){
			filelistPath = myUtil.setPath(fileList3DFolder, this._2_EdgeWave+".filelist");
		}else if(comboType.equals(ComboLabel.TYPE3)){
			filelistPath = myUtil.setPath(fileList3DFolder, this._3_CenterWave+".filelist");
		}else if(comboType.equals(ComboLabel.TYPE4)){
			filelistPath = myUtil.setPath(fileList3DFolder, this._4_SingleGutter+".filelist");
		}else if(comboType.equals(ComboLabel.TYPE5)){
			filelistPath = myUtil.setPath(fileList3DFolder, this._5_PartialGutter+".filelist");
		}else if(comboType.equals(ComboLabel.TYPE6)){
			filelistPath = myUtil.setPath(fileList3DFolder, this._6_DoubleGutter+".filelist");
		}else if(comboType.equals(ComboLabel.TYPE7)){
			filelistPath = myUtil.setPath(fileList3DFolder, this._7_IslandGutter+".filelist");
		}
		this.sourceFilePahtList(filelistPath);
		
		for(int i=0;i<this.fullPathList.size();i++){
			try{
				//System.out.println(this.fullPathList.get(i));
				//System.out.println(this.destFullPathList.get(i));
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
			String fullPath = myUtil.setPath(source3DFolder, filePath);
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