package com.js.ens.leveller.proc;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class a2_roll_gen_crown {
	private Logger log = Logger.getLogger(a2_roll_gen_crown.class);
	
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private ArrayList<String> procDataList;
	private String procFilePath;
	
	private ArrayList<String> fileDataList;
	private String plateType;
	
	public a2_roll_gen_crown(){
		fileDataList = new ArrayList<String>();
		plateType = LMain.getcomboType();
	}
	
	public a2_roll_gen_crown(String path){
		fileDataList = new ArrayList<String>();
		this.procFilePath = path;
		plateType = LMain.getcomboType();
	}
	
	public void running(){
		try{
			procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "a2_roll_gen_crown.proc");
			
			readSourceData();
			//version3 update 
			//swapValue();
			writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(a2_roll_gen_crown) \n path : "+ this.procFilePath);
			log.info("SUCCESS - Export(a2_roll_gen_crown) \n path : "+ this.procFilePath);
		}catch(Exception e){
			String msg = "ERROR - Export(a2_roll_gen_crown)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
	}
	
	public void readSourceData() throws Exception{
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
		String sourcePath = myUtil.setPath(s_folder, "a2_roll_gen_crown.proc");
		
		Reader reader = new Reader(sourcePath);
		reader.running();
		fileDataList = reader.getFileDataList();
	}
	
	public void swapValue() throws Exception{
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line :fileDataList){
			if(line.contains("%"+ProcVariable.upperRollGenField+"%")){
				if( Integer.parseInt(LMain.getSpinnerUpperRollNum()) <= Integer.parseInt(LMain.getSpinnerLowerRollNum()) ){
					for(int i=0;i<Integer.parseInt(LMain.getSpinnerUpperRollNum());i++){
						/*
						procDataList.add("fdist+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2+roll_pitch*(2*"+(i+1)+"-1)/2 "
										+LMain.getUpTableDataList().get(i).getGap()+"+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2 -1000");						
						procDataList.add("roll_dia/2");
						*/
						
						procDataList.add("*origin fdist+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2+roll_pitch*(2*"+(i+1)+"-1)/2 "
										+LMain.getUpTableDataList().get(i).getGap()+"+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2 0.0");
						procDataList.add("*add_curves");
						procDataList.add("roll_dia/2 -1000 0.0");
						procDataList.add("roll_dia/2 roll_length+1000 0.0");
						procDataList.add("roll_dia/2+r_crown roll_length/2 0.0");
						procDataList.add("*revolve_curves all_visible");
						procDataList.add("*store_surfaces upper_roll_s all_visible");
						procDataList.add("*select_clear");
						procDataList.add("*visible_selected");
					}
					continue;
				}else {
					for(int i=0;i<Integer.parseInt(LMain.getSpinnerUpperRollNum());i++){
						/*
						procDataList.add("fdist+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2+roll_pitch*("+(i+1)+"-1) "
								+LMain.getUpTableDataList().get(i).getGap()+"+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2 -1000");
						procDataList.add("roll_dia/2");
						*/
						
						procDataList.add("*origin fdist+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2+roll_pitch*("+(i+1)+"-1) "
										+LMain.getUpTableDataList().get(i).getGap()+"+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2 0.0");
						procDataList.add("*add_curves");
						procDataList.add("roll_dia/2 -1000 0.0");
						procDataList.add("roll_dia/2 roll_length+1000 0.0");
						procDataList.add("roll_dia/2+r_crown roll_length/2 0.0");
						procDataList.add("*revolve_curves all_visible");
						procDataList.add("*store_surfaces upper_roll_s all_visible");
						procDataList.add("*select_clear");
						procDataList.add("*visible_selected");
						
					}
					continue;
				}
				
			}else if(line.contains("%"+ProcVariable.lowerRollGenField+"%")){
				if( Integer.parseInt(LMain.getSpinnerUpperRollNum()) <= Integer.parseInt(LMain.getSpinnerLowerRollNum()) ){
					for(int i=-0;i<Integer.parseInt(LMain.getSpinnerLowerRollNum());i++){
						/*
						procDataList.add("fdist+"+LMain.getDownTableDataList().get(i).getDiameter()+"/2+roll_pitch*("+(i+1)+"-1) "
								+LMain.getDownTableDataList().get(i).getGap()+"-"+LMain.getDownTableDataList().get(i).getDiameter()+"/2 -1000");
						procDataList.add("roll_dia/2");
						*/
						procDataList.add("*origin fdist+"+LMain.getDownTableDataList().get(i).getDiameter()+"/2+roll_pitch*("+(i+1)+"-1) "
										+LMain.getDownTableDataList().get(i).getGap()+"-"+LMain.getDownTableDataList().get(i).getDiameter()+"/2 0.0");
						procDataList.add("*add_curves");
						procDataList.add("roll_dia/2 -1000 0.0");
						procDataList.add("roll_dia/2 roll_length+1000 0.0");
						procDataList.add("roll_dia/2+r_crown roll_length/2 0.0");
						procDataList.add("*revolve_curves all_visible");
						procDataList.add("*store_surfaces lower_roll_s all_visible");
						procDataList.add("*select_clear");
						procDataList.add("*visible_selected");
						
					}
					continue;
				}else{
					for(int i=-0;i<Integer.parseInt(LMain.getSpinnerLowerRollNum());i++){
						/*
						procDataList.add("fdist+"+LMain.getDownTableDataList().get(i).getDiameter()+"/2+roll_pitch*(2*"+(i+1)+"-1)/2 "
								+LMain.getDownTableDataList().get(i).getGap()+"-"+LMain.getDownTableDataList().get(i).getDiameter()+"/2 -1000");						
						procDataList.add("roll_dia/2");
						*/
						
						procDataList.add("*origin fdist+"+LMain.getDownTableDataList().get(i).getDiameter()+"/2+roll_pitch*(2*"+(i+1)+"-1)/2 "
										+LMain.getDownTableDataList().get(i).getGap()+"-"+LMain.getDownTableDataList().get(i).getDiameter()+"/2 0.0");
						procDataList.add("*add_curves");
						procDataList.add("roll_dia/2 -1000 0.0");
						procDataList.add("roll_dia/2 roll_length+1000 0.0");
						procDataList.add("roll_dia/2+r_crown roll_length/2 0.0");
						procDataList.add("*revolve_curves all_visible");
						procDataList.add("*store_surfaces lower_roll_s all_visible");
						procDataList.add("*select_clear");
						procDataList.add("*visible_selected");
					}
					continue;
				}
			}else if(line.contains("%"+ProcVariable.exitRollTableField+"%")){
				if( Integer.parseInt(LMain.getSpinnerUpperRollNum()) <= Integer.parseInt(LMain.getSpinnerLowerRollNum()) ){
					int lastNum = LMain.getDownTableDataList().size()-1;
					//|point(fdist+roll_pitch*(l_roll_no-1)+fdist,0,-1000)
					//|point(fdist+roll_pitch*(l_roll_no-1)+fdist+L+5000,0,-1000)
					/*
					point(-roll_dia/2+fdist+fdist+roll_dia/2+roll_pitch*(5-1)+roll_pitch/2, -roll_dia/2, -1000)
					point(fdist+fdist+roll_dia/2+roll_pitch*(5-1)+roll_pitch/2, 0, -1000)
					*/
					procDataList.add("point(-roll_dia/2+fdist+fdist+"+LMain.getDownTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2, -roll_dia/2, -1000)");
					procDataList.add("point(fdist+fdist+"+LMain.getDownTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2, 0, -1000)");
					procDataList.add("point(fdist+fdist+"+LMain.getDownTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2, 0, -1000)");
					procDataList.add("point(fdist+fdist+"+LMain.getDownTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2 + L + 5000, 0, -1000)");
				}else{
					int lastNum = LMain.getUpTableDataList().size()-1;
					procDataList.add("point(-roll_dia/2+fdist+fdist+"+LMain.getUpTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2, -roll_dia/2, -1000)");
					procDataList.add("point(fdist+fdist+"+LMain.getUpTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2, 0, -1000)");
					procDataList.add("point(fdist+fdist+"+LMain.getUpTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2, 0, -1000)");
					procDataList.add("point(fdist+fdist+"+LMain.getUpTableDataList().get(lastNum).getDiameter()+"/2+roll_pitch*("+lastNum+")+roll_pitch/2 + L + 5000, 0, -1000)");
				}
			}else{
				procDataList.add(line);
			}
			
		}
	}
		
	
	private void writeProc() throws Exception{
		Writer writer = new Writer(procFilePath,procDataList);
		writer.running();
	}
}
