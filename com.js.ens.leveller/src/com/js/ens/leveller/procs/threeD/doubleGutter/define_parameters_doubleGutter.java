package com.js.ens.leveller.procs.threeD.doubleGutter;

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

public class define_parameters_doubleGutter {

	private Logger log = Logger.getLogger(define_parameters_doubleGutter.class);
	private LevellerMain LMain = LevellerMain.getInstatnce();
	
	private String procPath = "";
	
	private ArrayList<String> fileDataList;
	private ArrayList<String> procDataList;
	
	public define_parameters_doubleGutter() {
		// TODO Auto-generated constructor stub
	}

	public void running(String path) {
		// TODO Auto-generated method stub
		this.procPath = path;
		try{
			if(!LMain.getMillStiffnessType().equals("Spring")){
				String filePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "change_motion_to_load.py");
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
				newLine = line.replace(ProcMaker.textWidth, LMain.getTextWidth());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textLength)){
				newLine = line.replace(ProcMaker.textLength, LMain.getTextLength());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textThickness)){
				newLine = line.replace(ProcMaker.textThickness, LMain.getTextThickness());
				procDataList.add(newLine);
				continue;
			}
			
			else if(line.contains(ProcMaker.type6_textHeadGutterHeight)){
				newLine = line.replace(ProcMaker.type6_textHeadGutterHeight, LMain.getType6_textHeadGutterHeight());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.type6_textHeadGutterLength)){
				newLine = line.replace(ProcMaker.type6_textHeadGutterLength, LMain.getType6_textHeadGutterLength());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.type6_textTailGutterHeight)){
				newLine = line.replace(ProcMaker.type6_textTailGutterHeight, LMain.getType6_textTailGutterHeight());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.type6_textTailGutterLength)){
				newLine = line.replace(ProcMaker.type6_textTailGutterLength, LMain.getType6_textTailGutterLength());
				procDataList.add(newLine);
				continue;
			}
			
			else if(line.contains(ProcMaker.textThicknessElementNum)){
				newLine = line.replace(ProcMaker.textThicknessElementNum, LMain.getTextThicknessElementNum());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textWidthAspectRatio)){
				newLine = line.replace(ProcMaker.textWidthAspectRatio, LMain.getTextWidthAspectRatio());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textLengthAspectRatio)){
				newLine = line.replace(ProcMaker.textLengthAspectRatio, LMain.getTextLengthAspectRatio());
				procDataList.add(newLine);
				continue;
			}
			
			else if(line.contains(ProcMaker.textRollPitch)){
				newLine = line.replace(ProcMaker.textRollPitch, LMain.getTextRollPitch());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textRollDiameter)){
				newLine = line.replace(ProcMaker.textRollDiameter, LMain.getTextRollDiameter());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textRollLength)){
				newLine = line.replace(ProcMaker.textRollLength, LMain.getTextRollLength());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textEntryUpperRollGap)){
				newLine = line.replace(ProcMaker.textEntryUpperRollGap, LMain.getUpTableDataList().get(0).getGap());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textExitUpperRollGap)){
				newLine = line.replace(ProcMaker.textExitUpperRollGap, LMain.getUpTableDataList().get(LMain.getUpTableDataList().size()-1).getGap());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textEntryLowerRollGap)){
				newLine = line.replace(ProcMaker.textEntryLowerRollGap,LMain.getDownTableDataList().get(0).getGap());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textExitLowerRollGap)){
				newLine = line.replace(ProcMaker.textExitLowerRollGap, LMain.getDownTableDataList().get(LMain.getDownTableDataList().size()-1).getGap());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.spinnerUpperRollNum)){
				newLine = line.replace(ProcMaker.spinnerUpperRollNum, LMain.getSpinnerUpperRollNum());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.spinnerLowerRollNum)){
				newLine = line.replace(ProcMaker.spinnerLowerRollNum, LMain.getSpinnerLowerRollNum());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textPlateVelocity)){
				newLine = line.replace(ProcMaker.textPlateVelocity, LMain.getTextPlateVelocity());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textRollFriction)){
				newLine = line.replace(ProcMaker.textRollFriction, LMain.getTextRollFriction());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textTemperatureStart)){
				newLine = line.replace(ProcMaker.textTemperatureStart, LMain.getTextTemperatureStart());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textTemperatureEnd)){
				newLine = line.replace(ProcMaker.textTemperatureEnd, LMain.getTextTemperatureEnd());
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
			else if(line.contains(ProcMaker.textUpperRollCrown)){
				newLine = line.replace(ProcMaker.textUpperRollCrown,LMain.getTextUpperRollCrown());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textLowerRollCrown)){
				newLine = line.replace(ProcMaker.textLowerRollCrown,LMain.getTextLowerRollCrown());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textMillStiffness)){
				newLine = line.replace(ProcMaker.textMillStiffness,LMain.getTextMillStiffness());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textPassLineOffset)){
				newLine = line.replace(ProcMaker.textPassLineOffset,LMain.getTextPassLineOffset());
				procDataList.add(newLine);
				continue;
			}
			//update version3 2016.07.22
			else if(line.contains(ProcMaker.textUpperEntryRollGapMovement)){
				newLine = line.replace(ProcMaker.textUpperEntryRollGapMovement,LMain.getTextUpperEntryRollGapMovement());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textUpperExitRollGapMovement)){
				newLine = line.replace(ProcMaker.textUpperExitRollGapMovement,LMain.getTextUpperExitRollGapMovement());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textUpperRollGapStayingTime)){
				newLine = line.replace(ProcMaker.textUpperRollGapStayingTime,LMain.getTextUpperRollGapStayingTime());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textUpperRollGapMovingTime)){
				newLine = line.replace(ProcMaker.textUpperRollGapMovingTime,LMain.getTextUpperRollGapMovingTime());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textLowerEntryRollGapMovement)){
				newLine = line.replace(ProcMaker.textLowerEntryRollGapMovement, LMain.getTextLowerEntryRollGapMovement());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textLowerExitRollGapMovement)){
				newLine = line.replace(ProcMaker.textLowerExitRollGapMovement,LMain.getTextLowerExitRollGapMovement());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textLowerRollGapStayingTime)){
				newLine = line.replace(ProcMaker.textLowerRollGapStayingTime,LMain.getTextLowerRollGapStayingTime());
				procDataList.add(newLine);
				continue;
			}else if(line.contains(ProcMaker.textLowerRollGapMovingTime)){
				newLine = line.replace(ProcMaker.textLowerRollGapMovingTime,LMain.getTextLowerRollGapMovingTime());
				procDataList.add(newLine);
				continue;
			}
			else if(line.contains(ProcMaker.HDRollType)){
				newLine = line.replace(ProcMaker.HDRollType, LMain.getHDRollType().toLowerCase());
				procDataList.add(newLine);
				continue;
			}
			else if(line.contains(ProcMaker.textFrontHDRollDia)){
				if(!LMain.getHDRollType().equals("None")){
					newLine = line.replace(ProcMaker.textFrontHDRollDia,LMain.getTextFrontHDRollDia());
					procDataList.add(newLine);
				}
				continue;
			}else if(line.contains(ProcMaker.textFrontHDRollPitch)){
				if(!LMain.getHDRollType().equals("None")){
					newLine = line.replace(ProcMaker.textFrontHDRollPitch,LMain.getTextFrontHDRollPitch());
					procDataList.add(newLine);
				}
				continue;
			}else if(line.contains(ProcMaker.textFrontHDRollVericalPos)){
				if(!LMain.getHDRollType().equals("None")){
					newLine = line.replace(ProcMaker.textFrontHDRollVericalPos,LMain.getTextFrontHDRollVericalPos());
					procDataList.add(newLine);
				}
				continue;
			}else if(line.contains(ProcMaker.textRearHDRollDia)){
				if(!LMain.getHDRollType().equals("None")){
					newLine = line.replace(ProcMaker.textRearHDRollDia,LMain.getTextRearHDRollDia());
					procDataList.add(newLine);
				}
				continue;
			}else if(line.contains(ProcMaker.textRearHDRollPitch)){
				if(!LMain.getHDRollType().equals("None")){
					newLine = line.replace(ProcMaker.textRearHDRollPitch,LMain.getTextRearHDRollPitch());
					procDataList.add(newLine);
				}
				continue;
			}else if(line.contains(ProcMaker.textRearHDRollVerticalPos)){
				if(!LMain.getHDRollType().equals("None")){
					newLine = line.replace(ProcMaker.textRearHDRollVerticalPos,LMain.getTextRearHDRollVerticalPos());
					procDataList.add(newLine);
				}
				continue;
			}
			else if(line.contains(ProcMaker.UpperRollDataField)){
				String result = "";
				String defineStr = "*eval_define ur_";
				int num = 1;
				for(UpTableDataContent obj :LMain.getUpTableDataList()){
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
				for(DownTableDataContent obj : LMain.getDownTableDataList()){
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
				newLine = line.replace(ProcMaker.textIncrementTime, LMain.getTextIncrementTime());
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
