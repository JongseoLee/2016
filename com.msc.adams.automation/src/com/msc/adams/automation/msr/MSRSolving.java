package com.msc.adams.automation.msr;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;
import com.msc.adams.automation.core.MessageWindow;
import com.msc.adams.automation.core.RunScript;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.adams.automation.datas.Part;
import com.msc.adams.automation.datas.Part_FatSolving;
import com.msc.adams.automation.datas.SwappingPart;
import com.msc.io.Writer;
import com.msc.util.myUtil;

public class MSRSolving implements Runnable {
	private MainController MC = MainController.getInstance();
	private Mediator med = Mediator.getInstance();
	private RunScript RunScriptData;
	
	
	private Part_FatSolving fsObj;
	private boolean isPossible = false;
	private boolean isPossible_DAC = false;
	private boolean isPossible_OP2 = false;
	private boolean isPossible_Bulk = false;
	private boolean isPossible_ffd = false;
	
	
	private String PartNameFolder;
	private int DACFileNum = 0;
	private ArrayList<String> FemfatMaterialDBList;
	private String jobFilePath = "";
	
	private final String S_FemfatPath = "%FemfatPath%";
	private final String S_JobFilePath = "%JobFilePath%";
	
	public MSRSolving() {
		// TODO Auto-generated constructor stub
		//
		// InputdeckForMNF 폴더 -> PartName 폴더 안에 opt용 inputdeck(.fem)
		// + 해당 폴더에서 OPT 해성이 완료되면 PartName.mnf, PartName.op2 가 생성됨.
		//
		// Result 폴더 -> 4_DAC 폴더 -> Reuslt Name 폴더 -> PartName 폴더 로 
		// 1) partName_mnf_creation.fem 파일, partName.op2 파일 을 복사!!(OPT 해석 폴더에 있음)
		// 2) 해당 폴더내의 DAC 파일 수 개산 후 DACNumber.txt 출력 
		// 3) .ffj 파일 생성
		// 4) .ffd 파일 존재 확인
		// 5) .ffj 파일로 FEMFAT batch job 실행 
		// 
		this.RunScriptData = new RunScript();
	}
	
	public void running(Part_FatSolving obj){
		/*
		System.out.println("PartName  : "+ obj.getPartName());
		System.out.println("bulk path : "+ obj.getBulkForMNFPath());
		System.out.println("OP2 path  : "+ obj.getOP2Path());
		// */
		this.fsObj = obj;
		this.FemfatMaterialDBList = new ArrayList<String>();
		
		this.countingDACFile();
		this.copySourceData();
		this.checkFemfatMaterialDB();
		this.checkSourceData();
		// MSR inputdeck 만들기 ffj 파일 
		CreateInputdeckFEMFATForMSR inputdeckObj = new CreateInputdeckFEMFATForMSR();
		inputdeckObj.running(this.fsObj,this.FemfatMaterialDBList);
		this.jobFilePath= inputdeckObj.getInputdeckPath();
		MC.addMsgWindow("<"+this.fsObj.getPartName()+"> Export FEMFAT inputdeck(for MSR) is complete.", MessageWindow.Info);
		
	}
	
	private void countingDACFile(){
		try{
			String resultFolder = myUtil.setPath(MC.getWorkspace(),DatabaseFolderName.Result);
			String DACFolder = myUtil.setPath(resultFolder, DatabaseFolderName.DAC);
			String ResultNameFolder = myUtil.setPath(DACFolder, MC.getDBStep4().getExportResultName());
			this.PartNameFolder = myUtil.setPath(ResultNameFolder, this.fsObj.getPartName());
			for(File f : myUtil.getDirFileList(this.PartNameFolder)){
				if(this.isPattern(f.getName())){
					this.DACFileNum++;
				}
			}
		}catch(Exception e){
			MC.addMsgWindow("<"+this.fsObj.getPartName()+"> DAC Folder is not exist.", MessageWindow.Error);
		}
		
	}
	
	private boolean isPattern(String fileName){
		String partName = this.fsObj.getPartName().toLowerCase();
		Pattern p = Pattern.compile("(^"+partName+"_[0-9][0-9][0-9][0-9].dac$)");
		Matcher m = p.matcher(fileName);

		if(m.find()){
			return true;
		}else{
			return false;
		}    
	}
	
	private void copySourceData(){
		//Start====> Swapping Type 이 mnf 일때 bulk 파일 입력 받기 ==>>>> STEP2 에서도 처리하기
		this.fsObj.setFinalDACFolder(this.PartNameFolder);
		// 1) Bulk data File 
		SwappingPart SPObj = MC.getDBStep3().SearchingSwappingPart(this.fsObj.getPartName());
		if(SPObj.getOriginalType().equals(Part.Type_OPT) || SPObj.getOriginalType().equals(Part.Type_MNF)){
			String sourceBulkPath = this.fsObj.getBulkForMNFPath();
			String fileName_bulk = new File(sourceBulkPath).getName();
			String destBulkPath = myUtil.setPath(this.PartNameFolder,fileName_bulk);
			ChangeBulk CBObj = new ChangeBulk();
			CBObj.running(sourceBulkPath,destBulkPath,SPObj.getOriginalType());
			//myUtil.fileCopy(sourceBulkPath, destBulkPath);
			this.fsObj.setFinalBulkPath(destBulkPath);
		}else{
			String sourceBulkPath = this.fsObj.getBulkForMNFPath();
			String fileName_bulk = new File(sourceBulkPath).getName();
			String destBulkPath = myUtil.setPath(this.PartNameFolder,fileName_bulk); 
			myUtil.fileCopy(sourceBulkPath, destBulkPath);
			this.fsObj.setFinalBulkPath(destBulkPath);
		}
		
		
		// 2) OP2 file 
		String sourceOP2Path = this.fsObj.getOP2Path();
		String fileName_op2 = new File(sourceOP2Path).getName();
		String destOP2Path = myUtil.setPath(this.PartNameFolder, fileName_op2);
		myUtil.fileCopy(sourceOP2Path, destOP2Path);
		this.fsObj.setFinalOP2Path(destOP2Path);
		
		// 3) Dac file number
		this.fsObj.setFinalDACNumber(String.valueOf(this.DACFileNum));
		
	}
	
	private void checkFemfatMaterialDB(){
		String materialDatabaseFolder = myUtil.setPath(MC.getFemfatPath(),"material_database");
		String HMC_CV_MatoDBFolder = myUtil.setPath(materialDatabaseFolder, "HMC-CV_MAToDB");
		
		if(myUtil.checkPath(HMC_CV_MatoDBFolder)){
			for(File f : myUtil.getDirFileList(HMC_CV_MatoDBFolder)){
				String filePath = f.getAbsolutePath();
				if(myUtil.getExtensions(filePath).equals("ffd")){
					this.FemfatMaterialDBList.add(filePath);
				}
			}
			if(this.FemfatMaterialDBList.size() != 0){
				this.isPossible_ffd = true;
			}else{
				this.isPossible_ffd = false;
			}
		}else{
			this.isPossible_ffd = false;
		}
	}

	private void checkSourceData(){
		
		if(this.isPossible_ffd){
			this.isPossible_ffd = true;
		}else{
			this.isPossible_ffd = false;
		}
		
		if(myUtil.checkPath(this.fsObj.getFinalBulkPath())){
			this.isPossible_Bulk = true;
		}else{
			this.isPossible_Bulk = false;
		}
		
		if(myUtil.checkPath(this.fsObj.getFinalOP2Path())){
			this.isPossible_OP2 = true;
		}else{
			this.isPossible_OP2 = false;
		}
		
		if(!this.fsObj.getFinalDACNumber().equals("0")){
			this.isPossible_DAC = true;
		}else{
			this.isPossible_DAC = false;
		}
		
		if(this.isPossible_Bulk && this.isPossible_OP2 && this.isPossible_DAC && this.isPossible_ffd){
			this.isPossible = true;
		}else{
			this.isPossible = false;
		}
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String type = Part_FatSolving.Type_MSR_MANUAL;
		if(this.fsObj.getType().equals(Part_FatSolving.Type_MSR_MANUAL)){
			type = Part_FatSolving.Type_MSR_MANUAL;
		}else if(this.fsObj.getType().equals(Part_FatSolving.Type_MSR_AUTO)){
			type = Part_FatSolving.Type_MSR_AUTO;
		}
		
		
		System.out.println(this.fsObj.getFinalDACFolder());
		System.out.println(this.fsObj.getFinalBulkPath());
		System.out.println(this.fsObj.getFinalOP2Path());
		System.out.println(this.fsObj.getFinalDACNumber());
		// Solving batch 돌리기
		med.getParentView().getDisplay().asyncExec(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String type = Part_FatSolving.Type_MSR_MANUAL;
				if(fsObj.getType().equals(Part_FatSolving.Type_MSR_MANUAL)){
					type = Part_FatSolving.Type_MSR_MANUAL;
				}else if(fsObj.getType().equals(Part_FatSolving.Type_MSR_AUTO)){
					type = Part_FatSolving.Type_MSR_AUTO;
				}
				
				String msg = "<"+fsObj.getPartName()+"> Start Durablility Analysis("+type+")";
				String allMsg = MC.getMsgWindow().addMessage(msg, MessageWindow.Info);
				med.getTextMessageWindow().setText(allMsg);
				med.getTextMessageWindow().setSelection(allMsg.length());
				System.out.println(msg);
				
			}
		});
		this.RunMSRSolving();
		
	}
	
	private void RunMSRSolving(){
		String FemfatPath = MC.getFemfatPath();
		//String exePath = myUtil.setPath(myUtil.setPath(FemfatPath, "bin"),"femfat.bat");
		String exePath = this.RunScriptData.getRunScript(RunScript.FEMFAT).replace(this.S_FemfatPath, FemfatPath);
		System.out.println("===>EXE : "+exePath.substring(1, exePath.length()-1));
		/*
		String binPath = myUtil.setPath(FemfatPath, "bin");
		String exePath = myUtil.setPath(binPath, "femfat.bat");
		String param = " -job=("+this.jobFilePath+")";
		String runScript = exePath + param;
		// */
		
		// 실제 사용시 주석 풀기
		/* */
		String scriptExe = this.RunScriptData.getRunScript(RunScript.FEMFAT);
		String reScriptExe =scriptExe.replace(this.S_FemfatPath, FemfatPath);
		String scriptParam = this.RunScriptData.getRunScript(RunScript.FEMFAT_PARAM);
		//String reScriptParam = scriptParam.replace(this.S_JobFilePath, "=("+this.jobFilePath+")");
		String reScriptParam = scriptParam.replace(this.S_JobFilePath, "="+this.jobFilePath);
		String runScript = reScriptExe+" "+reScriptParam;
	 	// */
		// test 용 스크립트 작성
		String runScript_error = "notepad.exe "+this.jobFilePath;
		try{
			Thread.sleep(1500);
			System.out.println("MSR Run Start - "+ runScript);
			System.out.println("MSR Run Start error - "+ runScript_error);
			/* */
			
			Process p = null;
			if(myUtil.checkPath(exePath.substring(1, exePath.length()-1))){
				
				if(this.fsObj.getType().equals(Part_FatSolving.Type_MSR_MANUAL)){
					System.out.println("1) exePath : "+exePath);
					p = Runtime.getRuntime().exec(runScript_error);
					System.out.println("2) Script : "+ runScript_error);
				}else if(this.fsObj.getType().equals(Part_FatSolving.Type_MSR_AUTO)){
					System.out.println("3) exePath : "+exePath);
					p = Runtime.getRuntime().exec(runScript);
					System.out.println("4) Script : "+ runScript);
				}
				
			}else {
				System.out.println("5) exePath : "+exePath);
				p = Runtime.getRuntime().exec(runScript_error);
				System.out.println("6) Script : "+ runScript_error);
			}
			/*
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(runScript);
			Writer w = new Writer("D:\\runMSR.bat");
			w.running(temp);
			// */
			
			p.getErrorStream().close();
			p.getInputStream().close();
			p.getOutputStream().close();
			p.waitFor(); 
			// */
			
			med.getParentView().getDisplay().asyncExec(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					//MessageDialog.openInformation(med.getTableFatSolving().getShell(), "Info" , "End Durablility/Fatigue Analysis(MSR).");
					String type = Part_FatSolving.Type_MSR_MANUAL;
					if(fsObj.getType().equals(Part_FatSolving.Type_MSR_MANUAL)){
						type = Part_FatSolving.Type_MSR_MANUAL;
					}else if(fsObj.getType().equals(Part_FatSolving.Type_MSR_AUTO)){
						type = Part_FatSolving.Type_MSR_AUTO;
					}
					
					String msg = "<"+fsObj.getPartName()+"> End Durablility Analysis("+type+")";
					String allMsg = MC.getMsgWindow().addMessage(msg, MessageWindow.Info);
					med.getTextMessageWindow().setText(allMsg);
					med.getTextMessageWindow().setSelection(allMsg.length());
					System.out.println(msg);
				}
			});
			//System.out.println("MSR Run End");
		}catch(Exception e){
			med.getParentView().getDisplay().asyncExec(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					String type = Part_FatSolving.Type_MSR_MANUAL;
					if(fsObj.getType().equals(Part_FatSolving.Type_MSR_MANUAL)){
						type = Part_FatSolving.Type_MSR_MANUAL;
					}else if(fsObj.getType().equals(Part_FatSolving.Type_MSR_AUTO)){
						type = Part_FatSolving.Type_MSR_AUTO;
					}
					String msg = "<"+fsObj.getPartName()+"> Error Durablility Analysis("+type+")";
					String allMsg = MC.getMsgWindow().addMessage(msg, MessageWindow.Error);
					med.getTextMessageWindow().setText(allMsg);
					med.getTextMessageWindow().setSelection(allMsg.length());
					System.out.println(msg);
				}
			});
			e.printStackTrace();
		}
	}

	public boolean isPossible() {
		return isPossible;
	}

	public void setPossible(boolean isPossible) {
		this.isPossible = isPossible;
	}

	public boolean isPossible_DAC() {
		return isPossible_DAC;
	}

	public void setPossible_DAC(boolean isPossible_DAC) {
		this.isPossible_DAC = isPossible_DAC;
	}

	public boolean isPossible_OP2() {
		return isPossible_OP2;
	}

	public void setPossible_OP2(boolean isPossible_OP2) {
		this.isPossible_OP2 = isPossible_OP2;
	}

	public boolean isPossible_Bulk() {
		return isPossible_Bulk;
	}

	public void setPossible_Bulk(boolean isPossible_Bulk) {
		this.isPossible_Bulk = isPossible_Bulk;
	}

	public boolean isPossible_ffd() {
		return isPossible_ffd;
	}

	public void setPossible_ffd(boolean isPossible_ffd) {
		this.isPossible_ffd = isPossible_ffd;
	}

}
