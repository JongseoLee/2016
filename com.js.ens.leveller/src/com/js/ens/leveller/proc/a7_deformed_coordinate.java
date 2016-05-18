package com.js.ens.leveller.proc;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.io.Writer;
import com.js.util.myUtil;

public class a7_deformed_coordinate {
	private Logger log = Logger.getLogger(a7_deformed_coordinate.class);
	
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private ArrayList<String> procDataList;
	private String procFilePath;
	
	private ArrayList<String> fileDataList;
	private String plateType;
	
	public a7_deformed_coordinate(){
		fileDataList = new ArrayList<String>();
		plateType = LMain.getcomboType();
	}
	
	public a7_deformed_coordinate(String path){
		fileDataList = new ArrayList<String>();
		this.procFilePath = path;
		plateType = LMain.getcomboType();
	}
	
	public void running(){
		try{
		//
			procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "a7_deformed_coordinate.proc");
			copyProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(a7_deformed_coordinate) \n path : "+ this.procFilePath);
			log.info("SUCCESS - Export(a7_deformed_coordinate) \n path : "+ this.procFilePath);
			
		}catch (Exception e){
			String msg = "ERROR - Exprot(a7_deformed_coordinate.proc)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
	}
	
	public void copyProc() throws Exception{
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
		String sourcePath = myUtil.setPath(s_folder, "a7_deformed_coordinate.proc");
		
		myUtil.fileCopy(sourcePath, procFilePath);	
	}
	
}

	