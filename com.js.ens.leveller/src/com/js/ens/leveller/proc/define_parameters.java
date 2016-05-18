package com.js.ens.leveller.proc;


import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;
import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.dialog.MessageDlg;

public class define_parameters {
	private Logger log = Logger.getLogger(define_parameters.class);
	
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private ArrayList<String> procDataList;
	private String procFilePath;
	
	private ArrayList<String> fileDataList;
	private String plateType;
	
	public define_parameters(){
		fileDataList = new ArrayList<String>();
		plateType = LMain.getcomboType();
	}
	
	public define_parameters(String path){
		fileDataList = new ArrayList<String>();
		this.procFilePath = path;
		plateType = LMain.getcomboType();
	}
	
	public void running(){
		try{
			procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "00_define_parameters.proc");
			
			readSourceData();
			swapValue();
			writeProc();
			
			String modulePath = myUtil.setPath(System.getProperty("user.dir"), "module");
			String in = myUtil.setPath(modulePath,"000_post_deformed.f");
			String out = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "000_post_deformed.f");
			myUtil.fileCopy(in, out);
			// update version2
			if(LMain.getMillStiffnessType().equals("Spring")){
				String inpy = myUtil.setPath(modulePath,"change_motion_to_load.py");
				String outpy = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "change_motion_to_load.py");
				myUtil.fileCopy(inpy, outpy);
			}

			//JOptionPane.showMessageDialog(new JFrame(), "define_parameters", "Dialog", JOptionPane.INFORMATION_MESSAGE);
			LMain.getExportMSG().addData("SUCCESS - Export(define_parameters) \n path : "+ this.procFilePath);
			log.info("SUCCESS - Export(define_parameters) \n path : "+ this.procFilePath);
			
		}catch(Exception e){
			String msg = "ERROR - Export(define_parameters)";
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
		String sourcePath = myUtil.setPath(s_folder, "00_define_parameters.proc");
		
		Reader reader = new Reader(sourcePath);
		reader.running();
		fileDataList = reader.getFileDataList();
	}
	
	public void swapValue() throws Exception{
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : fileDataList){
			if(line.contains("%"+ProcVariable.lblWidth+"%")){
				newLine = line.replace("%"+ProcVariable.lblWidth+"%", LMain.getTextWidth());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblLength+"%")){
				newLine = line.replace("%"+ProcVariable.lblLength+"%", LMain.getTextLength());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblThickness+"%")){
				newLine = line.replace("%"+ProcVariable.lblThickness+"%", LMain.getTextThickness());
				procDataList.add(newLine);
				continue;
			}
			
			else if(line.contains("%"+ProcVariable.lblThicknessElementNum+"%")){
				newLine = line.replace("%"+ProcVariable.lblThicknessElementNum+"%", LMain.getTextThicknessElementNum());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblWidthAspectRatio+"%")){
				newLine = line.replace("%"+ProcVariable.lblWidthAspectRatio+"%", LMain.getTextWidthAspectRatio());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblLengthAspectRatio+"%")){
				newLine = line.replace("%"+ProcVariable.lblLengthAspectRatio+"%", LMain.getTextLengthAspectRatio());
				procDataList.add(newLine);
				continue;
			}
			
			else if(line.contains("%"+ProcVariable.lblRollPitch+"%")){
				newLine = line.replace("%"+ProcVariable.lblRollPitch+"%", LMain.getTextRollPitch());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblRollDiameter+"%")){
				newLine = line.replace("%"+ProcVariable.lblRollDiameter+"%", LMain.getTextRollDiameter());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblRollLength+"%")){
				newLine = line.replace("%"+ProcVariable.lblRollLength+"%", LMain.getTextRollLength());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblEntryUpperRollGap+"%")){
				newLine = line.replace("%"+ProcVariable.lblEntryUpperRollGap+"%", LMain.getUpTableDataList().get(0).getGap());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblExitUpperRollGap+"%")){
				newLine = line.replace("%"+ProcVariable.lblExitUpperRollGap+"%", LMain.getUpTableDataList().get(LMain.getUpTableDataList().size()-1).getGap());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblEntryLowerRollGap+"%")){
				newLine = line.replace("%"+ProcVariable.lblEntryLowerRollGap+"%",LMain.getDownTableDataList().get(0).getGap());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblExitLowerRollGap+"%")){
				newLine = line.replace("%"+ProcVariable.lblExitLowerRollGap+"%", LMain.getDownTableDataList().get(LMain.getDownTableDataList().size()-1).getGap());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblUpperRollNumber+"%")){
				newLine = line.replace("%"+ProcVariable.lblUpperRollNumber+"%", LMain.getSpinnerUpperRollNum());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblLowerRollNumber+"%")){
				newLine = line.replace("%"+ProcVariable.lblLowerRollNumber+"%", LMain.getSpinnerLowerRollNum());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblPlateVelocity+"%")){
				newLine = line.replace("%"+ProcVariable.lblPlateVelocity+"%", LMain.getTextPlateVelocity());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblRollFriction+"%")){
				newLine = line.replace("%"+ProcVariable.lblRollFriction+"%", LMain.getTextRollFriction());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblTemperatureStart+"%")){
				newLine = line.replace("%"+ProcVariable.lblTemperatureStart+"%", LMain.getTextTemperatureStart());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblTemperatureEnd+"%")){
				newLine = line.replace("%"+ProcVariable.lblTemperatureEnd+"%", LMain.getTextTemperatureEnd());
				procDataList.add(newLine);
				continue;
			}
			//update version2 2016.01.27
			else if(line.contains("%"+ProcVariable.lblRollCrown+"%")){
				newLine = line.replace("%"+ProcVariable.lblRollCrown+"%",LMain.getTextRollCrown());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblMillStiffness+"%")){
				newLine = line.replace("%"+ProcVariable.lblMillStiffness+"%",LMain.getTextMillStiffness());
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.lblPassLineOffset+"%")){
				newLine = line.replace("%"+ProcVariable.lblPassLineOffset+"%",LMain.getTextPassLineOffset());
				procDataList.add(newLine);
				continue;
			}
			
			else if(line.contains("%"+ProcVariable.lblIncrementTime+"%")){
				newLine = line.replace("%"+ProcVariable.lblIncrementTime+"%", LMain.getTextIncrementTime());
				procDataList.add(newLine);
				continue;
			}else {
				if(!line.contains("%")){
					procDataList.add(line);
				}
			}
			
			if(LMain.getcomboType().equals(ComboLabel.TYPE2)){
				if(line.contains("%"+ProcVariable.lblLeftEdgeWavePitch_type2+"%")){
					newLine = line.replace("%"+ProcVariable.lblLeftEdgeWavePitch_type2+"%", LMain.getType2_textLeftEdgeWavePitch());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.lblRightEdgeWavePitch_type2+"%")){
					newLine = line.replace("%"+ProcVariable.lblRightEdgeWavePitch_type2+"%", LMain.getType2_textRightEdgeWavePitch());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.lblLeftEdgeWaveHeight_type2+"%")){
					newLine = line.replace("%"+ProcVariable.lblLeftEdgeWaveHeight_type2+"%", LMain.getType2_textLeftEdgeWaveHeight());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.lblRightEdgeWaveHeight_type2+"%")){
					newLine = line.replace("%"+ProcVariable.lblRightEdgeWaveHeight_type2+"%", LMain.getType2_textRightEdgeWaveHeight());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.lblLeftEdgeWavePhase_type2+"%")){
					newLine = line.replace("%"+ProcVariable.lblLeftEdgeWavePhase_type2+"%", LMain.getType2_textLeftEdgeWavePhase());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.lblRightEdgeWavePhase_type2+"%")){
					newLine = line.replace("%"+ProcVariable.lblRightEdgeWavePhase_type2+"%", LMain.getType2_textRightEdgeWavePhase());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.MaxWaveHeight+"%")){
					double RH = Double.parseDouble(LMain.getType2_textRightEdgeWaveHeight());
					double LH = Double.parseDouble(LMain.getType2_textLeftEdgeWaveHeight());
					
					if(RH >= LH )	newLine = line.replace("%"+ProcVariable.MaxWaveHeight+"%",String.valueOf(RH));
					else 	newLine = line.replace("%"+ProcVariable.MaxWaveHeight+"%",String.valueOf(LH));
					procDataList.add(newLine);
					continue;
				}
			}else if(LMain.getcomboType().equals(ComboLabel.TYPE3)){
				if(line.contains("%"+ProcVariable.lblWavePitch_type3+"%")){
					newLine = line.replace("%"+ProcVariable.lblWavePitch_type3+"%", LMain.getType3_textWavePitch());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.lblWaveHeight_type3+"%")){
					newLine = line.replace("%"+ProcVariable.lblWaveHeight_type3+"%", LMain.getType3_textWaveHeight());
					procDataList.add(newLine);
					continue;
				}
			}else if(LMain.getcomboType().equals(ComboLabel.TYPE4)){
				if(line.contains("%"+ProcVariable.lblGutterHeight_type4+"%")){
					newLine = line.replace("%"+ProcVariable.lblGutterHeight_type4+"%", LMain.getType4_textGutterHeight());
					procDataList.add(newLine);
					continue;
				}
			}else if(LMain.getcomboType().equals(ComboLabel.TYPE5)){
				if(line.contains("%"+ProcVariable.lblGutterHeight_type5+"%")){
					newLine = line.replace("%"+ProcVariable.lblGutterHeight_type5+"%", LMain.getType5_textGutterHeight());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.lblGutterLength_type5+"%")){
					newLine = line.replace("%"+ProcVariable.lblGutterLength_type5+"%", LMain.getType5_textGutterLength());
					procDataList.add(newLine);
					continue;
				}
			}else if(LMain.getcomboType().equals(ComboLabel.TYPE6)){
				if(line.contains("%"+ProcVariable.lblHeadGutterHeight_type6+"%")){
					newLine = line.replace("%"+ProcVariable.lblHeadGutterHeight_type6+"%", LMain.getType6_textHeadGutterHeight());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.lblHeadGutterLength_type6+"%")){
					newLine = line.replace("%"+ProcVariable.lblHeadGutterLength_type6+"%", LMain.getType6_textHeadGutterLength());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.lblTailGutterHeight_type6+"%")){
					newLine = line.replace("%"+ProcVariable.lblTailGutterHeight_type6+"%", LMain.getType6_textTailGutterHeight());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.lblTailGutterLength_type6+"%")){
					newLine = line.replace("%"+ProcVariable.lblTailGutterLength_type6+"%", LMain.getType6_textTailGutterLength());
					procDataList.add(newLine);
					continue;
				}
			}else if(LMain.getcomboType().equals(ComboLabel.TYPE7)){
				if(line.contains("%"+ProcVariable.lblHeadGutterHeight_type7+"%")){
					newLine = line.replace("%"+ProcVariable.lblHeadGutterHeight_type7+"%", LMain.getType7_textHeadGutterHeight());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.lblGutterLength_type7+"%")){
					newLine = line.replace("%"+ProcVariable.lblGutterLength_type7+"%", LMain.getType7_textGutterLength());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.lblGutterLengthLength_type7+"%")){
					newLine = line.replace("%"+ProcVariable.lblGutterLengthLength_type7+"%", LMain.getType7_textGutterLengthLength());
					procDataList.add(newLine);
					continue;
				}else if(line.contains("%"+ProcVariable.lblGutterWidthLength_type7+"%")){
					newLine = line.replace("%"+ProcVariable.lblGutterWidthLength_type7+"%", LMain.getType7_textGutterWidthLength());
					procDataList.add(newLine);
					continue;
				}
			}
		}
	}
	
	private void writeProc() throws Exception{
		Writer writer = new Writer(procFilePath,procDataList);
		writer.running();
	}
	

}

