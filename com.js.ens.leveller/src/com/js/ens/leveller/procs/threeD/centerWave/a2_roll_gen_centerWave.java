package com.js.ens.leveller.procs.threeD.centerWave;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.ens.leveller.procs.ProcMaker;
import com.js.io.Reader;
import com.js.io.Writer;

public class a2_roll_gen_centerWave {
	private Logger log = Logger.getLogger(a2_roll_gen_centerWave.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
	
	private String procPath = "";
	
	private ArrayList<String> fileDataList;
	private ArrayList<String> procDataList;
	
	public a2_roll_gen_centerWave() {
		// TODO Auto-generated constructor stub
	}

	public void running(String path) {
		// TODO Auto-generated method stub
		this.procPath = path;
		try{
			
			this.readSourceData();
			this.swapValue();
			this.writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(a2_roll_gen) \n path : "+ this.procPath);
			log.info("SUCCESS - Export(a2_roll_gen) \n path : "+ this.procPath);
		}catch(Exception e){
			String msg = "ERROR - Export(a2_roll_gen)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
	}

	private void readSourceData() throws Exception {
		// TODO Auto-generated method stub
		this.fileDataList = new ArrayList<String>();
		Reader reader = new Reader(this.procPath);
		reader.running();
		this.fileDataList = reader.getFileDataList();
	}
	
	private void swapValue() throws Exception {
		// TODO Auto-generated method stub
		procDataList = new ArrayList<String>();
		for(String line : fileDataList){
			if(line.contains(ProcMaker.upperRollGenField)){
				if( Integer.parseInt(LMain.getSpinnerUpperRollNum()) <= Integer.parseInt(LMain.getSpinnerLowerRollNum()) ){
					for(int i=0;i<Integer.parseInt(LMain.getSpinnerUpperRollNum());i++){
						procDataList.add("fdist+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2+roll_pitch*(2*"+(i+1)+"-1)/2 "
										+LMain.getUpTableDataList().get(i).getGap()+"+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2 -1000");						
						procDataList.add("roll_dia/2");
					}
					continue;
				}else {
					for(int i=0;i<Integer.parseInt(LMain.getSpinnerUpperRollNum());i++){
						procDataList.add("fdist+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2+roll_pitch*("+(i+1)+"-1) "
								+LMain.getUpTableDataList().get(i).getGap()+"+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2 -1000");
						procDataList.add("roll_dia/2");
					}
					continue;
				}
				
			}else if(line.contains(ProcMaker.lowerRollGenField)){
				if( Integer.parseInt(LMain.getSpinnerUpperRollNum()) <= Integer.parseInt(LMain.getSpinnerLowerRollNum()) ){
					for(int i=-0;i<Integer.parseInt(LMain.getSpinnerLowerRollNum());i++){
						procDataList.add("fdist+"+LMain.getDownTableDataList().get(i).getDiameter()+"/2+roll_pitch*("+(i+1)+"-1) "
								+LMain.getDownTableDataList().get(i).getGap()+"-"+LMain.getDownTableDataList().get(i).getDiameter()+"/2 -1000");
						procDataList.add("roll_dia/2");
					}
					continue;
				}else{
					for(int i=-0;i<Integer.parseInt(LMain.getSpinnerLowerRollNum());i++){
						procDataList.add("fdist+"+LMain.getDownTableDataList().get(i).getDiameter()+"/2+roll_pitch*(2*"+(i+1)+"-1)/2 "
								+LMain.getDownTableDataList().get(i).getGap()+"-"+LMain.getDownTableDataList().get(i).getDiameter()+"/2 -1000");						
						procDataList.add("roll_dia/2");
					}
					continue;
				}
			}
		
			else if(line.contains(ProcMaker.exitRollTableField)){
				if( Integer.parseInt(LMain.getSpinnerUpperRollNum()) <= Integer.parseInt(LMain.getSpinnerLowerRollNum()) ){
					int lastNum = LMain.getDownTableDataList().size()-1;
					procDataList.add("point(-roll_dia/2+fdist+rdist+"+LMain.getDownTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2, -roll_dia/2, -1000)");
					procDataList.add("point(fdist+rdist+"+LMain.getDownTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2, 0, -1000)");
					procDataList.add("point(fdist+rdist+"+LMain.getDownTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2, 0, -1000)");
					procDataList.add("point(fdist+rdist+"+LMain.getDownTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2 + L + 5000, 0, -1000)");
				}else{
					int lastNum = LMain.getUpTableDataList().size()-1;
					procDataList.add("point(-roll_dia/2+fdist+rdist+"+LMain.getUpTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2, -roll_dia/2, -1000)");
					procDataList.add("point(fdist+rdist+"+LMain.getUpTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2, 0, -1000)");
					procDataList.add("point(fdist+rdist+"+LMain.getUpTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2, 0, -1000)");
					procDataList.add("point(fdist+rdist+"+LMain.getUpTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2 + L + 5000, 0, -1000)");
				}
			}else{
				procDataList.add(line);
			}
		}
	}
	
	private void writeProc() throws Exception {
		// TODO Auto-generated method stub
		Writer writer = new Writer(this.procPath,this.procDataList);
		writer.running();
	}
}
