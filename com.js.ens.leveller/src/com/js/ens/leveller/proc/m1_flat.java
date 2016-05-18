package com.js.ens.leveller.proc;


import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.io.Writer;
import com.js.util.myUtil;
import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;

public class m1_flat {
	private Logger log = Logger.getLogger(m1_flat.class);
	
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private ArrayList<String> procDataList;
	private String procFilePath;
	
	private String plateType;
	
	public m1_flat(){
		plateType = LMain.getcomboType();
	}
	
	public m1_flat(String path){
		this.procFilePath = path;
		plateType = LMain.getcomboType();
	}
	
	public void running(){
		//
		try{
			procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "m1_flat.proc");
			copyProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(m1_flat) \n path : "+ this.procFilePath);
			log.info("SUCCESS - Export(m1_flat) \n path : "+ this.procFilePath);
				
			m1_a_flat m1Obj = new m1_a_flat();
			m1Obj.running();
			
			String modulePath = myUtil.setPath(System.getProperty("user.dir"), "module");
			String inpy1 = myUtil.setPath(modulePath,"mp01_define_post_set_flat.py");
			String inpy2 = myUtil.setPath(modulePath,"mp02_redefine_post_set_flat.py");
			String outpy1 = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "mp01_define_post_set_flat.py");
			String outpy2 = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "mp02_redefine_post_set_flat.py");
			myUtil.fileCopy(inpy1, outpy1);
			myUtil.fileCopy(inpy2, outpy2);
		}catch(Exception e){
			String msg = "ERROR - Export(m1_flat)";
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
		String sourcePath = myUtil.setPath(s_folder, "m1_flat.proc");
		
		myUtil.fileCopy(sourcePath, procFilePath);
	}

}
