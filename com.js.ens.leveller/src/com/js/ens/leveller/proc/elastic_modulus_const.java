package com.js.ens.leveller.proc;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class elastic_modulus_const {
	private Logger log = Logger.getLogger(elastic_modulus_const.class);
	
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private String value_YoungsModulus;
	private String procFilePath;
	
	private ArrayList<String> procDataList;
	
	private ArrayList<String> fileDataList2;
	private String plateType;
	
	public elastic_modulus_const(){
		plateType = LMain.getcomboType();
		fileDataList2 = new ArrayList<String>();
	}
	
	public elastic_modulus_const(String value_YoungsModulus){
		this.value_YoungsModulus = value_YoungsModulus;
		plateType = LMain.getcomboType();
		fileDataList2 = new ArrayList<String>();
	}
	
	public void running(){
		try{
			procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(),"proc"),"mat01_elastic_modulus_const.proc");
			
			readSourceData2();
			swapValue();
			writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(mat01_elastic_modulus_const) \n path : "+ this.procFilePath);
			log.info("SUCCESS - Export(mat01_elastic_modulus_const) \n path : "+ this.procFilePath);

			
		}catch(Exception e){
			String msg = "ERROR - Export(mat01_elastic_modulus_const)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
	}
	
	public void readSourceData2() throws Exception{
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
		String sourcePath = myUtil.setPath(s_folder, "mat01_elastic_modulus_const.proc");
		
		Reader reader = new Reader(sourcePath);
		reader.running();
		fileDataList2 = reader.getFileDataList();
	}
	
	public void swapValue() throws Exception{
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : fileDataList2){
			//"%"+ProcVariable.lblMassDensity+"%"
			if(line.contains("%"+ProcVariable.lblYoungsModulus+"%")){
				newLine = line.replace("%"+ProcVariable.lblYoungsModulus+"%", this.value_YoungsModulus );
				procDataList.add(newLine);
				continue;
			}else {
				procDataList.add(line);
			}
		}
	}
	
	public void writeProc() throws Exception{
		Writer writer = new Writer(procFilePath,procDataList);
		writer.running();
	}

}
