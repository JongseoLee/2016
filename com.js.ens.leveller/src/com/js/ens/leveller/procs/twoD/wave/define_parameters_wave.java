package com.js.ens.leveller.procs.twoD.wave;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.leveller.core.DownTableDataContent;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.core.UpTableDataContent;
import com.js.ens.leveller.dialog.MessageDlg;
import com.js.ens.leveller.procs.ProcMaker;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class define_parameters_wave {
	private Logger log = Logger.getLogger(define_parameters_wave.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
	
	private String procPath = "";
	
	private ArrayList<String> fileDataList;
	private ArrayList<String> procDataList;
	
	public define_parameters_wave() {
		// TODO Auto-generated constructor stub
	}

	public void running(String path) {
		// TODO Auto-generated method stub
		this.procPath = path;
		try{
			if(!LMain.getMillStiffnessType_2D().equals("Spring")){
				String filePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "change_motion_to_load_2D.py");
				myUtil.fileDelete(filePath);
			}
			
			this.readSourceData();
			this.swapValue();
			this.writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(define_parameters) \n path : "+ this.procPath);
			log.info("SUCCESS - Export(define_parameters) \n path : "+ this.procPath);
			
		}catch(Exception e){
			String msg = "ERROR - Export(define_parameters)";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(), msg);
			messageDlg.open();
			log.error(msg);
			LMain.getExportMSG().addData(msg);
		}
	}
	
	private void readSourceData() throws Exception{
		this.fileDataList = new ArrayList<String>();
		Reader reader = new Reader(this.procPath);
		reader.running();
		this.fileDataList = reader.getFileDataList();
	}
	
	private void swapValue() throws Exception{
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : fileDataList){
			if(line.contains(ProcMaker.textWidth)){
				newLine = line.replace(ProcMaker.textWidth, LMain.getTextWidth_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textLength)){
				newLine = line.replace(ProcMaker.textLength, LMain.getTextLength_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textThickness)){
				newLine = line.replace(ProcMaker.textThickness, LMain.getTextThickness_2D());
				procDataList.add(newLine);
				continue;
			}
			

			else if(line.contains(ProcMaker.type2_textWavePitch)){
				newLine = line.replace(ProcMaker.type2_textWavePitch, LMain.getType2_textWavePitch_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.type2_textWaveHeight)){
				newLine = line.replace(ProcMaker.type2_textWaveHeight, LMain.getType2_textWaveHeight_2D());
				procDataList.add(newLine);
			}else if(line.contains(ProcMaker.type2_textWavePhase)){
				newLine = line.replace(ProcMaker.type2_textWavePhase, LMain.getType2_textWavePhase_2D());
				procDataList.add(newLine);
			}
			
			
			else if(line.contains(ProcMaker.textThicknessElementNum)){
				newLine = line.replace(ProcMaker.textThicknessElementNum, LMain.getTextThicknessElementNum_2D());
				procDataList.add(newLine);
				continue;
			}
			/*
			else if(line.contains(ProcMaker.textWidthAspectRatio)){
				newLine = line.replace(ProcMaker.textWidthAspectRatio, LMain.getTextWidthAspectRatio());
				procDataList.add(newLine);
				continue;
			}
			// */
			else if(line.contains(ProcMaker.textEntryRollTableDistance)){
				newLine = line.replace(ProcMaker.textEntryRollTableDistance, LMain.getTextEntryRollTableDistance_2D());
				procDataList.add(newLine);
			}else if(line.contains(ProcMaker.textExitRollTableDistance)){
				newLine = line.replace(ProcMaker.textExitRollTableDistance, LMain.getTextExitRollTableDistance_2D());
				procDataList.add(newLine);
			}
			
			else if(line.contains(ProcMaker.textLengthAspectRatio)){
				newLine = line.replace(ProcMaker.textLengthAspectRatio, LMain.getTextLengthAspectRatio_2D());
				procDataList.add(newLine);
				continue;
			}
			
			else if(line.contains(ProcMaker.textRollPitch)){
				newLine = line.replace(ProcMaker.textRollPitch, LMain.getTextRollPitch_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textRollDiameter)){
				newLine = line.replace(ProcMaker.textRollDiameter, LMain.getTextRollDiameter_2D());
				procDataList.add(newLine);
				continue;
			}
			/*
			else if(line.contains(ProcMaker.textRollLength)){
				newLine = line.replace(ProcMaker.textRollLength, LMain.getTextRollLength_2D());
				procDataList.add(newLine);
				continue;
			}
			//*/
			else if(line.contains(ProcMaker.textEntryUpperRollGap)){
				newLine = line.replace(ProcMaker.textEntryUpperRollGap, LMain.getUpTableDataList_2D().get(0).getGap());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textExitUpperRollGap)){
				newLine = line.replace(ProcMaker.textExitUpperRollGap, LMain.getUpTableDataList_2D().get(LMain.getUpTableDataList_2D().size()-1).getGap());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textEntryLowerRollGap)){
				newLine = line.replace(ProcMaker.textEntryLowerRollGap,LMain.getDownTableDataList_2D().get(0).getGap());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textExitLowerRollGap)){
				newLine = line.replace(ProcMaker.textExitLowerRollGap, LMain.getDownTableDataList_2D().get(LMain.getDownTableDataList_2D().size()-1).getGap());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.spinnerUpperRollNum)){
				newLine = line.replace(ProcMaker.spinnerUpperRollNum, LMain.getSpinnerUpperRollNum_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.spinnerLowerRollNum)){
				newLine = line.replace(ProcMaker.spinnerLowerRollNum, LMain.getSpinnerLowerRollNum_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textPlateVelocity)){
				newLine = line.replace(ProcMaker.textPlateVelocity, LMain.getTextPlateVelocity_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textRollFriction)){
				newLine = line.replace(ProcMaker.textRollFriction, LMain.getTextRollFriction_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textTemperatureStart)){
				newLine = line.replace(ProcMaker.textTemperatureStart, LMain.getTextTemperatureStart_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textTemperatureEnd)){
				newLine = line.replace(ProcMaker.textTemperatureEnd, LMain.getTextTemperatureEnd_2D());
				procDataList.add(newLine);
				continue;
			}
			//update version2 2016.01.27
			/*
			else if(line.contains(ProcMaker.textRollCrown)){
				newLine = line.replace(ProcMaker.textRollCrown,LMain.getTextRollCrown());
				procDataList.add(newLine);
				continue;
			}
			//*/
			/*
			else if(line.contains(ProcMaker.textUpperRollCrown)){
				newLine = line.replace(ProcMaker.textUpperRollCrown,LMain.getTextUpperRollCrown());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textLowerRollCrown)){
				newLine = line.replace(ProcMaker.textLowerRollCrown,LMain.getTextLowerRollCrown());
				procDataList.add(newLine);
				continue;
			}
			// */
			else if(line.contains(ProcMaker.textMillStiffness)){
				newLine = line.replace(ProcMaker.textMillStiffness,LMain.getTextMillStiffness_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textPassLineOffset)){
				newLine = line.replace(ProcMaker.textPassLineOffset,LMain.getTextPassLineOffset_2D());
				procDataList.add(newLine);
				continue;
			}
			//update version3 2016.07.22
			else if(line.contains(ProcMaker.textUpperEntryRollGapMovement)){
				newLine = line.replace(ProcMaker.textUpperEntryRollGapMovement,LMain.getTextUpperEntryRollGapMovement_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textUpperExitRollGapMovement)){
				newLine = line.replace(ProcMaker.textUpperExitRollGapMovement,LMain.getTextUpperExitRollGapMovement_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textUpperRollGapStayingTime)){
				newLine = line.replace(ProcMaker.textUpperRollGapStayingTime,LMain.getTextUpperRollGapStayingTime_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textUpperRollGapMovingTime)){
				newLine = line.replace(ProcMaker.textUpperRollGapMovingTime,LMain.getTextUpperRollGapMovingTime_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textLowerEntryRollGapMovement)){
				newLine = line.replace(ProcMaker.textLowerEntryRollGapMovement, LMain.getTextLowerEntryRollGapMovement_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textLowerExitRollGapMovement)){
				newLine = line.replace(ProcMaker.textLowerExitRollGapMovement,LMain.getTextLowerExitRollGapMovement_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textLowerRollGapStayingTime)){
				newLine = line.replace(ProcMaker.textLowerRollGapStayingTime,LMain.getTextLowerRollGapStayingTime_2D());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textLowerRollGapMovingTime)){
				newLine = line.replace(ProcMaker.textLowerRollGapMovingTime,LMain.getTextLowerRollGapMovingTime_2D());
				procDataList.add(newLine);
				continue;
			}
			else if(line.contains(ProcMaker.HDRollType)){
				newLine = line.replace(ProcMaker.HDRollType, LMain.getHDRollType_2D().toLowerCase());
				procDataList.add(newLine);
				continue;
			}
			else if(line.contains(ProcMaker.textFrontHDRollDia)){
				if(!LMain.getHDRollType_2D().equals("None")){
					newLine = line.replace(ProcMaker.textFrontHDRollDia,LMain.getTextFrontHDRollDia_2D());
					procDataList.add(newLine);
				}
				continue;
			}else if(line.contains(ProcMaker.textFrontHDRollPitch)){
				if(!LMain.getHDRollType_2D().equals("None")){
					newLine = line.replace(ProcMaker.textFrontHDRollPitch,LMain.getTextFrontHDRollPitch_2D());
					procDataList.add(newLine);
				}
				continue;
			}else if(line.contains(ProcMaker.textFrontHDRollVericalPos)){
				if(!LMain.getHDRollType_2D().equals("None")){
					newLine = line.replace(ProcMaker.textFrontHDRollVericalPos,LMain.getTextFrontHDRollVericalPos_2D());
					procDataList.add(newLine);
				}
				continue;
			}else if(line.contains(ProcMaker.textRearHDRollDia)){
				if(!LMain.getHDRollType_2D().equals("None")){
					newLine = line.replace(ProcMaker.textRearHDRollDia,LMain.getTextRearHDRollDia_2D());
					procDataList.add(newLine);
				}
				continue;
			}else if(line.contains(ProcMaker.textRearHDRollPitch)){
				if(!LMain.getHDRollType_2D().equals("None")){
					newLine = line.replace(ProcMaker.textRearHDRollPitch,LMain.getTextRearHDRollPitch_2D());
					procDataList.add(newLine);
				}
				continue;
			}else if(line.contains(ProcMaker.textRearHDRollVerticalPos)){
				if(!LMain.getHDRollType_2D().equals("None")){
					newLine = line.replace(ProcMaker.textRearHDRollVerticalPos,LMain.getTextRearHDRollVerticalPos_2D());
					procDataList.add(newLine);
				}
				continue;
			}
			else if(line.contains(ProcMaker.UpperRollDataField)){
				String result = "";
				String defineStr = "*eval_define ur_";
				int num = 1;
				for(UpTableDataContent obj :LMain.getUpTableDataList_2D()){
					result += defineStr+ String.valueOf(num)+"_gap "+obj.getGap() +"  |"+obj.getNo() + " Gap" + "\n";
					result += defineStr+ String.valueOf(num)+"_friction "+obj.getFriction() +"  |"+obj.getNo() + " Friction" + "\n";
					result += defineStr+ String.valueOf(num)+"_diameter "+obj.getDiameter() +"  |"+obj.getNo() + " Diameter" + "\n";
					num++;
				}
				newLine = line.replace(ProcMaker.UpperRollDataField,result);
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.LowerRollDataField)){
				String result = "";
				String defineStr = "*eval_define lr_";
				int num = 1;
				for(DownTableDataContent obj : LMain.getDownTableDataList_2D()){
					result += defineStr+ String.valueOf(num)+"_gap "+obj.getGap() +"  |"+obj.getNo() + " Gap" + "\n";
					result += defineStr+ String.valueOf(num)+"_friction "+obj.getFriction() +"  |"+obj.getNo() + " Friction" + "\n";
					result += defineStr+ String.valueOf(num)+"_diameter "+obj.getDiameter() +"  |"+obj.getNo() + " Diameter" + "\n";
					num++;
				}
				
				newLine = line.replace(ProcMaker.LowerRollDataField,result);
				procDataList.add(newLine);
				continue;
			}
			
			else if(line.contains(ProcMaker.textIncrementTime)){
				newLine = line.replace(ProcMaker.textIncrementTime, LMain.getTextIncrementTime_2D());
				procDataList.add(newLine);
				continue;
			}else {
				if(!line.contains("%")){
					procDataList.add(line);
				}
			}
			
		}
	}
	
	private void writeProc() throws Exception{
		Writer writer = new Writer(this.procPath,this.procDataList);
		writer.running();
	}
}
