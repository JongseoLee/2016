package com.msc.adams.automation.inputdeck;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;
import com.msc.adams.automation.core.MessageWindow;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.adams.commandServer.Job;
import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.util.myUtil;

public class CreateInputdeckNASForMNF implements Runnable{
	private MainController MC = MainController.getInstance();
	private Mediator med = Mediator.getInstance();
	
	public final String BEGIN_BULK 	= "BEGIN BULK";
	public final String ENDDATA 	= "ENDDATA";
	
	public final String PARAM		= "PARAM";
	public final String DTI			= "DTI";
	public final String EIGRL		= "EIGRL";
	public final String RBE2 		= "RBE2";
	public final String ASET1 		= "ASET1";
	public final String SPOINT 		= "SPOINT";
	public final String QSET1		= "QSET1";
	
	public final String S_FILENAME	= "%fileName%";
	public final String S_PARAM 	= "%ValueParam%";
	public final String S_DTI		= "%ValueDti%";
	public final String S_EIGRL		= "%ValueEigrl%";
	public final String S_ASET1		= "%ASET1%";
	public final String S_SPOINT 	= "%SPOINT%";
	public final String S_QSET1 	= "%QSET1%";
	public final String S_BULKDATA  = "%BulkData%";
	public final String S_HMSETData	= "%HMSETData%";
	
	public final String HeaderFileName = "MNF-HEADER-NASTRAN.txt";
	private int SPOINT_startID = 999991;
	
	
	private String partName;
	private String bulkFilePath;
	private String inputdeckPath;
		
	private ArrayList<String> HeaderFileDataList;
	private ArrayList<String> BulkFileDataList;
	private ArrayList<String> BulkDataList;
	//private ArrayList<String> BulkDataListUpdate;
	private ArrayList<String> outputDataList;
	private ArrayList<String> HMSETDataList;
	
	private ArrayList<Param_Data> ParamDataList = new ArrayList<Param_Data>(); 
	private ArrayList<Param_Data> ParamDataList_header = new ArrayList<Param_Data>();
	private ArrayList<DTI_Data> DTIDataList = new ArrayList<DTI_Data>();
	private ArrayList<DTI_Data> DTIDataList_header = new ArrayList<DTI_Data>();
	private ArrayList<EIGRL_Data> EIGRLDataList = new ArrayList<EIGRL_Data>();
	private ArrayList<EIGRL_Data> EIGRLDataList_header = new ArrayList<EIGRL_Data>();
	private ASET1 Aset1Obj = new ASET1();
	private SPOINT SpointObj = new SPOINT();
	private QSET1 QSet1Obj = new QSET1();
	
	
	
	
	public CreateInputdeckNASForMNF() {
		// TODO Auto-generated constructor stub
		//MC = MainController.getInstance();
	}
	

	
	public void running(String bulkFilePath,String partName){

		this.bulkFilePath = bulkFilePath;
		//String fileName = myUtil.getFileName(this.bulkFilePath);
		String fileName = partName;
		this.partName=partName;
		String inputdeckFileName = fileName+"_mnf_creation.bdf";
		String resultPath = myUtil.setPath(MC.getWorkspace(),DatabaseFolderName.Result);
		String InputdeckForMNFPath = myUtil.setPath(resultPath, DatabaseFolderName.InputdeckForMNF);
		String partNameFolder = myUtil.setPath(InputdeckForMNFPath, partName);
		myUtil.makeDir(partNameFolder);
		this.inputdeckPath = myUtil.setPath(partNameFolder, inputdeckFileName);
		//MC.addMsgWindow("<"+partName+"> Export Nastran inputdeck(for MNF) is complete.", MessageWindow.Info);
	}
	
	public void running_manual(String bulk){
		//long start = System.currentTimeMillis();

		this.bulkFilePath = bulk;
		String fileName = "DR_PIVOT_DRIVER_ARM";
		this.partName = fileName;
		String inputdeckFileName = fileName+ "_mnf_creation.bdf";
		String path = "E:\\HMC_FEM";
		this.inputdeckPath = myUtil.setPath(path, inputdeckFileName);
		
		//long end = System.currentTimeMillis();
		//System.out.println( "실행 시간 - running_manual : " + ( end - start )/1000.0 );
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		/* 
		//단위 테스트 
		this.ReadHeaderFile_manual();
		this.Finding_headerData();
		this.ReadBulkFile();	// $HMSET 처리
		this.Finding_data();
		this.Finding_bulk();
		this.CreateInputdeck_manual();
		this.writeInputdeck();
		this.createOnlyBulk_manual();
		// */
		/* */
		// 어플에서 실행 부분
		this.ReadHeaderFile();
		this.Finding_headerData();
		this.ReadBulkFile();
		this.Finding_data();
		this.Finding_bulk();
		this.CreateInputdeck();
		this.writeInputdeck();
		this.createOnlyBulk();
		// */
		Process p = null;
		try{
			//System.out.println(this.inputdeckPath);
			String runScript = "notepad.exe "+this.inputdeckPath;
			//System.out.println(runScript);
			p = Runtime.getRuntime().exec(runScript);
			p.getErrorStream().close();
			p.getInputStream().close();
			p.getOutputStream().close();
			p.waitFor();
			
			/* = 실제로 주석 지우기 manual 을 위해서 주석 처리 함 */
			med.getParentView().getDisplay().asyncExec(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					//MessageDialog.openInformation(med.getTableSwappingPart().getShell(), "Info" , "<"+partName+"> Export Nastran inputdeck(for MNF) is complete.");
					String msg = "<"+partName+"> Export Nastran inputdeck(for MNF) is complete.";
					String allMsg = MC.getMsgWindow().addMessage(msg, MessageWindow.Info);
					med.getTextMessageWindow().setText(allMsg);
					med.getTextMessageWindow().setSelection(allMsg.length());
					System.out.println(msg);
					
				}
				
			});
			// */
		}catch(Exception e){
			med.getParentView().getDisplay().asyncExec(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					String msg = "<"+partName+"> Error Nastran inputdeck (for MNF).";
					String allMsg = MC.getMsgWindow().addMessage(msg, MessageWindow.Error);
					med.getTextMessageWindow().setText(allMsg);
					med.getTextMessageWindow().setSelection(allMsg.length());
					System.out.println(msg);
				}
				
			});
			e.printStackTrace();
		}
		
		
		
		
	}

	private void ReadHeaderFile(){
		String msck_TemplatePath = myUtil.setPath(MC.getAppPath(), DatabaseFolderName.msck_Template);
		String HeaderFilePath = myUtil.setPath(msck_TemplatePath, DatabaseFolderName.HeaderFile);
		String tmpHeaderFilePath = myUtil.setPath(HeaderFilePath, HeaderFileName);
		this.HeaderFileDataList = new ArrayList<String>();
		
		Reader reader = new Reader(tmpHeaderFilePath);
		reader.running();
		this.HeaderFileDataList = reader.getFileDataList();
		
	}
	
	private void ReadHeaderFile_manual(){
		
		String path = "D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Template\\HeaderFile";
		String headerFile = "MNF-HEADER-NASTRAN.txt";
		
		String tmpHeaderFilePath = myUtil.setPath(path, headerFile);
		
				
		this.HeaderFileDataList = new ArrayList<String>();
		
		Reader reader = new Reader(tmpHeaderFilePath);
		reader.running();
		this.HeaderFileDataList = reader.getFileDataList();
		
	}
	
	private void Finding_headerData(){
		for(String line : this.HeaderFileDataList){
			if(line.contains(PARAM)){
				if(!line.contains("WTMASS")){
					Param_Data pObj = new Param_Data();
					pObj.running(line);
					this.ParamDataList_header.add(pObj);
				}else{
					//System.out.println(line);
				}
			}else if(line.contains(DTI)){
				DTI_Data dObj = new DTI_Data();
				dObj.running(line);
				this.DTIDataList_header.add(dObj);
			}else if(line.contains(EIGRL)){
				EIGRL_Data eObj =new EIGRL_Data();
				eObj.running(line);
				this.EIGRLDataList_header.add(eObj);
				this.EIGRLDataList.add(eObj);
			}
		}
	}
	
	private void ReadBulkFile(){
		this.HMSETDataList = new ArrayList<String>();
		this.BulkFileDataList = this.getBulkData();
	}
	
	private ArrayList<String> getBulkData(){
		ArrayList<String> tempFileDataList = new ArrayList<String>();
		Reader reader = new Reader(bulkFilePath);
		reader.running();
		tempFileDataList = reader.getFileDataList();
		
		ArrayList<String> tokens = new ArrayList<String>();
		ArrayList<HMSETObj> HMSETObjList = new ArrayList<HMSETObj>();
		ArrayList<SETObj> SETObjList = new ArrayList<SETObj>();
		ArrayList<String> AllHMSETDataListForRemove = new ArrayList<String>();
		
		for(int i=0;i<tempFileDataList.size();i++){
			String line = tempFileDataList.get(i);
			tokens = myUtil.splitData(line, " ");
			
			if(tokens.size() != 0){
				// $HMSET 찾기
				if(tokens.get(0).equals("$HMSET")){
					AllHMSETDataListForRemove.add(line);
					String id = tokens.get(1);
					HMSETObj hmsetObj = new HMSETObj(id);
					hmsetObj.addLine(line);
					HMSETObjList.add(hmsetObj);
				}
				// SET 찾기
				if(tokens.get(0).equals("SET")){
					String id = tokens.get(1);
					SETObj setObj = new SETObj(id);
					
					int endLineNum = i+1;
					for(int j=i ; j<endLineNum ;j++){
						String innerLine = tempFileDataList.get(j);
						ArrayList<String> innerTokens = myUtil.splitData(innerLine, " ");					
						String lastToken= innerTokens.get(innerTokens.size()-1);
						char lastChar = lastToken.charAt(lastToken.length()-1);
						
						String innerLine2 = tempFileDataList.get(j+1);
						if(innerLine2.length() == 0){
							innerLine2 = "empty";
						}
						ArrayList<String> innerTokens2 = myUtil.splitData(innerLine2, " ");
						String firstToken = innerTokens2.get(0);
						char firstChar = firstToken.charAt(0);
						
						if(lastChar == ','){
							endLineNum++;
							AllHMSETDataListForRemove.add(innerLine);
							setObj.addLine(innerLine);
						}else{
							AllHMSETDataListForRemove.add(innerLine);
							setObj.addLine(innerLine);
							if(firstChar == '+'){
								endLineNum++;
							}
						}
					}
					
					SETObjList.add(setObj);
				}
			}
		}
		
		// 원본 파일에서 HMSET / SET 지우기
		for(String remove : AllHMSETDataListForRemove){
			tempFileDataList.remove(remove);
		}
		 
		for(HMSETObj hmsetObj : HMSETObjList){
			String hmsetId = hmsetObj.getID();
			
			for(String hmsetLine : hmsetObj.getLinesList()){
				this.HMSETDataList.add(hmsetLine);
			}
			
			for(SETObj setObj : SETObjList){
				String setID = setObj.getID();
				if(hmsetId.equals(setID)){
					for(String setLine : setObj.getLinesList()){
						this.HMSETDataList.add(setLine);
					}
				}
			}
			
			for(String hmsetLine : hmsetObj.getLinesList()){
				this.HMSETDataList.add(hmsetLine);
			}
			this.HMSETDataList.add("$$");
		}
		
		/*
		ArrayList<String> newDataList = new ArrayList<String>();
		for(String line : tempFileDataList){
			if(line.trim().toLowerCase().equals("begin bulk")){
				for(String newLine : HMSETDataList){
					newDataList.add(newLine);
				}
				newDataList.add(line);
			}else{
				newDataList.add(line);
			}
		}
		
		return newDataList;
		// */
		return tempFileDataList;
	}
	
	private void Finding_data(){
		// Bulk 에서 가져온 데이터 
		for(String line : this.BulkFileDataList){
			if(line.contains(PARAM)){
				
				if(line.contains("WTMASS")){
					//System.out.println("WTMASS line : "+ line);
					continue;
				}
				if(line.contains("POST")){
					//System.out.println("POST line : "+ line);
					continue;
				}
				/* */
				Param_Data pObj = new Param_Data();
				pObj.running(line);
				boolean add = false;
				for(Param_Data obj : this.ParamDataList_header){
					if(pObj.isSame(obj)){
						add = false;
						break;
					}else{
						add = true;
					}
				}
				if(add){
					this.ParamDataList.add(pObj);
				}
			}else if(line.contains(DTI)){
				DTI_Data dObj = new DTI_Data();
				dObj.running(line);
				boolean add = false;
				for(DTI_Data obj : this.DTIDataList_header){
					if(dObj.isSame(obj)){
						add = false;
						break;
					}else{
						add =true;
					}
				}
				if(add){
					this.DTIDataList.add(dObj);
				}
			}else if(line.contains(EIGRL)){
				EIGRL_Data eObj =new EIGRL_Data();
				eObj.running(line);
				boolean add =false;
				for(EIGRL_Data obj : this.EIGRLDataList_header){
					if(eObj.isSame(obj)){
						add = false;
						break;
					}else{
						add = true;
					}
				}
				if(add){
					this.EIGRLDataList.add(eObj);
				}
			}else if(line.contains(RBE2)){
				Aset1Obj.running(line);
			}
		}
	}
	
	private void Finding_bulk(){
		this.BulkDataList = new ArrayList<String>();
		boolean start = false;
		boolean isSimpleBulk = true;
		for(String line : this.BulkFileDataList){
			if(line.contains(ENDDATA)){
				start = false;
			}
			
			if(start){
				if(line.contains(PARAM)){
				}else if(line.contains(DTI)){
				}else if(line.contains(EIGRL)){
				}else if(line.contains(ASET1)){
				}else if(line.contains(SPOINT)){
				}else if(line.contains(QSET1)){
				}else{
					this.BulkDataList.add(line);
				}
			}
			
			if(line.contains(BEGIN_BULK)){
				start = true;
				isSimpleBulk = false;
			}
		}
		
		if(isSimpleBulk){
			this.BulkDataList.clear();
			for(String line : this.BulkFileDataList){
				this.BulkDataList.add(line);
			}
		}
		
	}
	
	private void CreateInputdeck_manual(){
		this.outputDataList = new ArrayList<String>();
		String firstLine = this.HeaderFileDataList.get(0);
		this.SPOINT_startID = Integer.parseInt(firstLine.replace("$", "").trim()); 
		for(String line : this.HeaderFileDataList){
			if(line.contains(S_PARAM)){
				String newLine = "";
				for(Param_Data obj : this.ParamDataList){
					newLine += obj.getFullCardData() +"\n";
				}
				this.outputDataList.add(newLine);
			}else if(line.contains(S_DTI)){
				String newLine = "";
				for(DTI_Data obj : this.DTIDataList){
					newLine += obj.getFullCardData() +"\n";
				}
				this.outputDataList.add(newLine);
			}else if(line.contains(EIGRL)){
				
			}else if(line.contains(S_EIGRL)){
				String newLine = "";
				for(EIGRL_Data obj : this.EIGRLDataList){
					newLine += obj.getFullCardData() +"\n";
				}
				this.outputDataList.add(newLine);
			}else if(line.contains(S_ASET1)){
				String newLine = this.Aset1Obj.getFullCardData();
				this.outputDataList.add(newLine);
				// 주석 풀기
				//int lastNum = 6*this.Aset1Obj.getIDListSize()+6 + Integer.parseInt(MC.getEIGRL_ND());
				int lastNum = 6*this.Aset1Obj.getIDListSize()+6 + Integer.parseInt("20");
				this.SpointObj.running(this.SPOINT_startID, lastNum);
				this.QSet1Obj.running(this.SPOINT_startID, lastNum);
			}else if(line.contains(S_SPOINT)){
				String newLine = this.SpointObj.getFullCardData();
			}else if(line.contains(S_QSET1)){
				String newLine = this.QSet1Obj.getFullCardData();
				//this.outputDataList.add(newLine);
			}else if(line.contains(S_BULKDATA)){
				String newLine = "";
				for(String bulkLine : this.BulkDataList){
					this.outputDataList.add(bulkLine);
				}
			}else if(line.contains(S_HMSETData)){
				String newLine = "";
				for(String HMSETLine : this.HMSETDataList){
					this.outputDataList.add(HMSETLine);
				}
			}
			else{
				this.outputDataList.add(line);
			}
		}
	}
	private void CreateInputdeck(){
		this.outputDataList = new ArrayList<String>();
		String firstLine = this.HeaderFileDataList.get(0);
		this.SPOINT_startID = Integer.parseInt(firstLine.replace("$", "").trim()); 
		for(String line : this.HeaderFileDataList){
			if(line.contains(S_PARAM)){
				
				String newLine = "";
				for(Param_Data obj : this.ParamDataList){
					newLine += obj.getFullCardData() +"\n";
				}
				this.outputDataList.add(newLine);
			}else if(line.contains(S_DTI)){
				
				String newLine = "";
				for(DTI_Data obj : this.DTIDataList){
					newLine += obj.getFullCardData() +"\n";
				}
				this.outputDataList.add(newLine);
			}else if(line.contains(EIGRL)){
				
			}else if(line.contains(S_EIGRL)){
				
				String newLine = "";
				for(EIGRL_Data obj : this.EIGRLDataList){
					newLine += obj.getFullCardData() +"\n";
				}
				this.outputDataList.add(newLine);
			}else if(line.contains(S_ASET1)){
				
				String newLine = this.Aset1Obj.getFullCardData();
				this.outputDataList.add(newLine);
				int lastNum = 6*this.Aset1Obj.getIDListSize()+6 + Integer.parseInt(MC.getEIGRL_ND());
				this.SpointObj.running(this.SPOINT_startID, lastNum);
				this.QSet1Obj.running(this.SPOINT_startID, lastNum);
			}else if(line.contains(S_SPOINT)){
				String newLine = this.SpointObj.getFullCardData();
				//this.outputDataList.add(newLine);
			}else if(line.contains(S_QSET1)){
				String newLine = this.QSet1Obj.getFullCardData();
				//this.outputDataList.add(newLine);
			}else if(line.contains(S_BULKDATA)){
				String newLine = "";
				for(String bulkLine : this.BulkDataList){
					this.outputDataList.add(bulkLine);
				}
			}else if(line.contains(S_HMSETData)){
				String newLine = "";
				for(String HMSETLine : this.HMSETDataList){
					this.outputDataList.add(HMSETLine);
				}
			}
			else{
				this.outputDataList.add(line);
			}
		}
	}
	
	
	private void writeInputdeck(){
		Writer writer = new Writer(this.inputdeckPath);
		writer.running(outputDataList);
	}
	
	private void createOnlyBulk_manual(){
		//////////////////////////////////
		// (for MSM) create bulk file
		//String oriPath = path;
		String exetension = myUtil.getExtensions(this.bulkFilePath);		
		String newFileName = partName+"_onlyBluk."+exetension;
		String newFilePath = "";
		String path = "E:\\HMC_FEM";
		//String resultPath = myUtil.setPath(MC.getWorkspace(),DatabaseFolderName.Result);
		
		//String InputdeckForMNFPath = myUtil.setPath(resultPath, DatabaseFolderName.InputdeckForMNF);
		//String bulkBdf = myUtil.setPath(InputdeckForMNFPath, DatabaseFolderName.RenameBulk);
		newFilePath = myUtil.setPath(path, newFileName);	
		
		Writer writer = new Writer(newFilePath);
		writer.running(this.findingHMSET(this.bulkFilePath));
		//
		//////////////////////////////////
	}
	
	private void createOnlyBulk(){
		//////////////////////////////////
		// (for MSM) create bulk file
		//String oriPath = path;
		String exetension = myUtil.getExtensions(this.bulkFilePath);		
		String newFileName = partName+"_onlyBluk."+exetension;
		String newFilePath = "";
		String resultPath = myUtil.setPath(MC.getWorkspace(),DatabaseFolderName.Result);
		
		String InputdeckForMNFPath = myUtil.setPath(resultPath, DatabaseFolderName.InputdeckForMNF);
		String bulkBdf = myUtil.setPath(InputdeckForMNFPath, DatabaseFolderName.RenameBulk);
		newFilePath = myUtil.setPath(bulkBdf, newFileName);	
		
		Writer writer = new Writer(newFilePath);
		writer.running(this.findingHMSET(this.bulkFilePath));
		//
		//////////////////////////////////
	}
	
	
	
	private ArrayList<String> findingHMSET(String bulkFilePath){
		ArrayList<String> tempFileDataList = new ArrayList<String>();
		Reader reader = new Reader(bulkFilePath);
		reader.running();
		tempFileDataList = reader.getFileDataList();
		
		ArrayList<String> tokens = new ArrayList<String>();
		ArrayList<HMSETObj> HMSETObjList = new ArrayList<HMSETObj>();
		ArrayList<SETObj> SETObjList = new ArrayList<SETObj>();
		ArrayList<String> AllHMSETDataListForRemove = new ArrayList<String>();
		
		for(int i=0;i<tempFileDataList.size();i++){
			String line = tempFileDataList.get(i);
			tokens = myUtil.splitData(line, " ");
			
			if(tokens.size() != 0){
				// $HMSET 찾기 
				if(tokens.get(0).equals("$HMSET")){
					AllHMSETDataListForRemove.add(line);
					String id = tokens.get(1);
					HMSETObj hmsetObj = new HMSETObj(id);
					hmsetObj.addLine(line);
					HMSETObjList.add(hmsetObj);
				}
				
				// SET 찾기 
				if(tokens.get(0).equals("SET")){
					String id = tokens.get(1);
					SETObj setObj = new SETObj(id); 
					int endLineNum = i+1;
					for(int j=i ; j<endLineNum ;j++){
						String innerLine = tempFileDataList.get(j);
						ArrayList<String> innerTokens = myUtil.splitData(innerLine, " ");					
						String lastToken= innerTokens.get(innerTokens.size()-1);
						char lastChar = lastToken.charAt(lastToken.length()-1);
						
						String innerLine2 = tempFileDataList.get(j+1);
						if(innerLine2.length() == 0){
							innerLine2 = "empty";
						}
						ArrayList<String> innerTokens2 = myUtil.splitData(innerLine2, " ");
						String firstToken = innerTokens2.get(0);
						char firstChar = firstToken.charAt(0);
						
						if(lastChar == ','){
							endLineNum++;
							AllHMSETDataListForRemove.add(innerLine);
							setObj.addLine(innerLine);
						}else{
							AllHMSETDataListForRemove.add(innerLine);
							setObj.addLine(innerLine);
							if(firstChar == '+'){
								endLineNum++;
							}
						}
					}
					SETObjList.add(setObj);
				}
			}
		}
		
		for(String remove : AllHMSETDataListForRemove){
			tempFileDataList.remove(remove);
		}
		
		tempFileDataList.remove("BEGIN BULK");
		tempFileDataList.remove("ENDDATA");
		 
				
		return tempFileDataList;
	}
	
	
	
	public String getInputdeckPath() {
		return inputdeckPath;
	}

	public void setInputdeckPath(String inputdeckPath) {
		this.inputdeckPath = inputdeckPath;
	}

	public ArrayList<String> getBulkDataList() {
		return BulkDataList;
	}

	public void setBulkDataList(ArrayList<String> bulkDataList) {
		BulkDataList = bulkDataList;
	}
	
	
	
	
	public static void main(String [] args){
		/* 
		//System.out.println("START");
		String path = "D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Template\\editBulk";
		String bulkFile = "DR_PIVOT_DRIVER_ARM_170417.bdf";		
		String bulkFilePath = myUtil.setPath(path,bulkFile);
		
		CreateInputdeckNASForMNF createInputObj = new CreateInputdeckNASForMNF();
		createInputObj.running_manual(bulkFilePath);
		
		Thread th = new Thread(createInputObj);
		th.start();
		//System.out.println("END");
		//*/
	}

	
	
}
