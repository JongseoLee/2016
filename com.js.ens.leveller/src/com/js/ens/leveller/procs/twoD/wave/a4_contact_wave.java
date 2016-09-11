package com.js.ens.leveller.procs.twoD.wave;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.ens.leveller.procs.ProcMaker;
import com.js.io.Reader;
import com.js.io.Writer;

public class a4_contact_wave {
	private Logger log = Logger.getLogger(a4_contact_wave.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
	
	private String procPath = "";
	
	private ArrayList<String> fileDataList;
	private ArrayList<String> procDataList;
	
	
	private int AllRollNum = 0 ;
	
	private String add_contact_body_curves;
	private String flip_surfaces = "";
	
	//version 2 blocking
	//private ArrayList<Integer> add_contact_body_surfaces_List = new ArrayList<Integer>();
	private ArrayList<Integer> flip_surface_List = new ArrayList<Integer>();
	private String flip_surfaces_positiveNum = "";
	
	public a4_contact_wave() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(String path){
		this.procPath = path;
		try{
			AllRollNum = Integer.parseInt(LMain.getSpinnerUpperRollNum_2D())+Integer.parseInt(LMain.getSpinnerLowerRollNum_2D());
			add_contact_body_curves = (AllRollNum+1)+", "+(AllRollNum+2)+", "+(AllRollNum+3)+", "+(AllRollNum+4);
			for(int i=0;i<AllRollNum;i++){
				flip_surfaces += (i+1)+",";
				flip_surface_List.add(i+1);
				
			}
			flip_surfaces+=String.valueOf(AllRollNum+1);
			flip_surfaces_positiveNum = String.valueOf(AllRollNum+1);
			
			
			this.readSourceData();
			this.swapValue();
			this.writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(a4_contact) \n path : "+ this.procPath);
			log.info("SUCCESS - Export(a4_contact) \n path : "+ this.procPath);
		}catch(Exception e){
			String msg = "ERROR - Export(a4_contact)";
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
		String newLine = null;
		for(String line : fileDataList){
			if(line.contains(ProcMaker.upperRollGenContact)){
				for(int i=0;i<Integer.parseInt(LMain.getSpinnerUpperRollNum_2D());i++){
					procDataList.add("| =========== Upper Roll "+ (i+1) +" ================");
					procDataList.add("*new_cbody geometry *contact_option geometry_nodes:off");
					procDataList.add("*contact_body_name");
					procDataList.add(LMain.getUpTableDataList_2D().get(i).getNo());
					procDataList.add("*add_contact_body_curves");
					procDataList.add(""+(i+1));
					procDataList.add("#");
					procDataList.add("*cbody_center_rot");
					//procDataList.add("fdist+roll_pitch*("+i+"+1/2) sgap+(egap-sgap)*"+i+"+roll_dia/2 -1000");
					if( Integer.parseInt(LMain.getSpinnerUpperRollNum_2D()) <= Integer.parseInt(LMain.getSpinnerLowerRollNum_2D()) ){
						//procDataList.add("fdist+roll_pitch*("+i+"+1/2) "+LMain.getUpTableDataList().get(i).getGap()+"+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2 -1000");
						procDataList.add("fdist+"+LMain.getUpTableDataList_2D().get(i).getDiameter()+"/2+roll_pitch*(2*"+(i+1)+"-1)/2 "
										+LMain.getUpTableDataList_2D().get(i).getGap()+"+"+LMain.getUpTableDataList_2D().get(i).getDiameter()+"/2 0.0");						
				
					}else{
						//procDataList.add("fdist+roll_pitch*("+i+") "+LMain.getUpTableDataList().get(i).getGap()+"+"+LMain.getUpTableDataList().get(i).getDiameter()+"/2 -1000");
						procDataList.add("fdist+"+LMain.getUpTableDataList_2D().get(i).getDiameter()+"/2+roll_pitch*("+(i+1)+"-1) "
								+LMain.getUpTableDataList_2D().get(i).getGap()+"+"+LMain.getUpTableDataList_2D().get(i).getDiameter()+"/2 0.0");
					}
					procDataList.add("*contact_value az");
					procDataList.add("1");
					procDataList.add("*contact_value vrot");
					procDataList.add("plate_v/("+LMain.getUpTableDataList_2D().get(i).getDiameter()+"/2)");
				}
				continue;
			}else if(line.contains(ProcMaker.lowerRollGenContact)){
				for(int i=0;i<Integer.parseInt(LMain.getSpinnerLowerRollNum_2D());i++){
					procDataList.add("| =========== Lower Roll "+ (i+1) +" ================");
					procDataList.add("*new_cbody geometry *contact_option geometry_nodes:off");
					procDataList.add("*contact_body_name");
					procDataList.add(LMain.getDownTableDataList_2D().get(i).getNo());
					procDataList.add("*add_contact_body_curves");
					procDataList.add(""+String.valueOf((i+1)+Integer.parseInt(LMain.getSpinnerUpperRollNum_2D())));
					procDataList.add("#");
					procDataList.add("*cbody_center_rot");
					//procDataList.add("fdist+roll_pitch*("+i+") -1*roll_dia/2 -1000");
					if( Integer.parseInt(LMain.getSpinnerUpperRollNum_2D()) <= Integer.parseInt(LMain.getSpinnerLowerRollNum_2D()) ){
						//procDataList.add("fdist+roll_pitch*("+i+") "+LMain.getDownTableDataList().get(i).getGap() +"-1*"+LMain.getDownTableDataList().get(i).getDiameter()+"/2 -1000");
						procDataList.add("fdist+"+LMain.getDownTableDataList_2D().get(i).getDiameter()+"/2+roll_pitch*("+(i+1)+"-1) "
								+LMain.getDownTableDataList_2D().get(i).getGap()+"-"+LMain.getDownTableDataList_2D().get(i).getDiameter()+"/2 0.0");
					}else{
						//procDataList.add("fdist+roll_pitch*("+i+"+1/2) "+LMain.getDownTableDataList().get(i).getGap() +"-1*"+LMain.getDownTableDataList().get(i).getDiameter()+"/2 -1000");
						procDataList.add("fdist+"+LMain.getDownTableDataList_2D().get(i).getDiameter()+"/2+roll_pitch*(2*"+(i+1)+"-1)/2 "
								+LMain.getDownTableDataList_2D().get(i).getGap()+"-"+LMain.getDownTableDataList_2D().get(i).getDiameter()+"/2 0.0");
					}
					procDataList.add("*contact_value az");
					procDataList.add("1");
					procDataList.add("*contact_value vrot");
					procDataList.add("-1*plate_v/("+LMain.getDownTableDataList_2D().get(i).getDiameter()+"/2)");
				}
				continue;
			}else if(line.contains(ProcMaker.add_contact_body_curves)){
				newLine = line.replace(ProcMaker.add_contact_body_curves, this.add_contact_body_curves);
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.flip_curves)){
				//update version2
				/*
				if(Double.parseDouble(LMain.getTextRollCrown()) > 0.0){
					newLine = line.replace(ProcMaker.flip_surface, this.flip_surfaces_positiveNum);
					procDataList.add(newLine);
				}else{
					newLine = line.replace(ProcMaker.flip_surface, this.flip_surfaces);
					procDataList.add(newLine);
				}
				//*/
				
				//newLine = line.replace(ProcMaker.flip_curves, this.flip_surfaces_positiveNum);
				//procDataList.add(newLine);
				
				continue;
			}else if(line.contains(ProcMaker.plateRollGenContactTable)){
				for(int i=0;i<Integer.parseInt(LMain.getSpinnerUpperRollNum_2D());i++){
					procDataList.add("|  ===========  Plate <-> Upper Roll "+(i+1)+" ===========");  
					procDataList.add("*ctable_entry plate "+LMain.getUpTableDataList_2D().get(i).getNo());
					procDataList.add("*contact_table_option plate "+LMain.getUpTableDataList_2D().get(i).getNo()+" contact:on");
					procDataList.add("*prog_string ctable:old_interact friction *ctable_entry_interact plate "+LMain.getUpTableDataList_2D().get(i).getNo());
				}
				
				for(int i=0;i<Integer.parseInt(LMain.getSpinnerLowerRollNum_2D());i++){
					procDataList.add("|  ===========  Plate <-> Lower Roll "+(i+1)+" ===========");  
					procDataList.add("*ctable_entry plate "+LMain.getDownTableDataList_2D().get(i).getNo());
					procDataList.add("*contact_table_option plate "+LMain.getDownTableDataList_2D().get(i).getNo()+" contact:on");
					procDataList.add("*prog_string ctable:old_interact friction *ctable_entry_interact plate "+LMain.getDownTableDataList_2D().get(i).getNo());
				}
				continue;
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