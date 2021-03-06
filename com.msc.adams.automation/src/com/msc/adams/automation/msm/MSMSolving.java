package com.msc.adams.automation.msm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;
import com.msc.adams.automation.core.MessageWindow;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.adams.automation.datas.Part_FatSolving;
import com.msc.util.myUtil;

public class MSMSolving implements Runnable {
	private MainController MC = MainController.getInstance();
	private Mediator med = Mediator.getInstance();
	
	private Part_FatSolving fsObj;
	
	private String partName;
	private String onlyBulkFilePath;
	private String exportResultName;
	private String inputdeckPath;
	private String workspace;
	
	// Path 
	private String nodeInformationFilePath; 
	private String partNameFolder;
	// 
	private ArrayList<String> nodeIdList;
	private ArrayList<String> allNodeIdList;
	private ArrayList<String> nameList;
	private ArrayList<ArrayList<String>> forceDataPathList;
	private Map<String,String> nodeID_fileNameMap;
	
	
	// result 
	private String targetOnlyBulkPath;
	private String allStepNum;
	private String timeStep;
	private Map<String,String> resultMatchIDMap;
	private ArrayList<String> resultTabledIDList;
	
	
	
	
	public MSMSolving() {
		// TODO Auto-generated constructor stub
	}

	public void running(Part_FatSolving obj){
		this.fsObj = obj;
		//System.out.println("PartName  : "+ obj.getPartName());
		//System.out.println("only bulk path : "+ obj.getOnlyBulkFilePath());
		this.nodeIdList = new ArrayList<String>();
		this.allNodeIdList = new ArrayList<String>();
		this.nameList = new ArrayList<String>();
		
		this.forceDataPathList = new ArrayList<ArrayList<String>>();
		this.nodeID_fileNameMap = new HashMap<String,String>();
		this.resultMatchIDMap = new HashMap<String,String>();
		this.resultTabledIDList = new ArrayList<String>();
		
		this.partName = obj.getPartName();
		this.onlyBulkFilePath = obj.getOnlyBulkFilePath();
		this.exportResultName = MC.getDBStep4().getExportResultName();
		this.workspace = MC.getWorkspace();
		/*
		// 1) NodeInformation 에서 NodeId, force fileName 추출
		String resultFolder = myUtil.setPath(this.workspace, DatabaseFolderName.Result);
		String nodeInfoFolder = myUtil.setPath(resultFolder, DatabaseFolderName.NodeInformation);
		String partNameFolder = myUtil.setPath(nodeInfoFolder, this.partName);
		this.nodeInformationFilePath = myUtil.setPath(partNameFolder, "Node_information."+this.partName+".txt");
		this.findingNodeInfo();
		// 2) force 데이터를 TABLED1 로 변환
		this.changeForceDataToTABLED1();
		// 3) inputdeck 작성
		this.CreateInputDeck();
		//*/
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 1) NodeInformation 에서 NodeId, force fileName 추출
		String resultFolder = myUtil.setPath(this.workspace, DatabaseFolderName.Result);
		String nodeInfoFolder = myUtil.setPath(resultFolder, DatabaseFolderName.NodeInformation);
		String partNameFolder = myUtil.setPath(nodeInfoFolder, this.partName);
		this.nodeInformationFilePath = myUtil.setPath(partNameFolder, "Node_information."+this.partName+".txt");
		
		this.findingNodeInfo();
		// 2) bulkFile 복사
		this.copyBulkFile();
		// 3) force 데이터를 TABLED1 로 변환
		this.changeForceDataToTABLED1();
		// 4) inputdeck 작성
		this.CreateInputDeck();
		
		Process p = null;
		try{
			String runScript = "notepad.exe "+this.inputdeckPath;
			p = Runtime.getRuntime().exec(runScript);
			p.getErrorStream().close();
			p.getInputStream().close();
			p.getOutputStream().close();
			p.waitFor();
			
			med.getParentView().getDisplay().asyncExec(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					String msg = "<"+fsObj.getPartName()+"> Export Nastran inputdeck(for MSM) is complete.";
					String allMsg = MC.getMsgWindow().addMessage(msg, MessageWindow.Info);
					med.getTextMessageWindow().setText(allMsg);
					med.getTextMessageWindow().setSelection(allMsg.length());
					System.out.println(msg);
					/*
					String msg2 = "<"+fsObj.getPartName()+"> Start Durablility/Fatigue Analysis(MSM)";
					String allMsg2 = MC.getMsgWindow().addMessage(msg2, MessageWindow.Info);
					med.getTextMessageWindow().setText(allMsg2);
					med.getTextMessageWindow().setSelection(allMsg2.length());
					System.out.println(msg2);
					// */
				}
			});
		}catch(Exception e){
			med.getParentView().getDisplay().asyncExec(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					String msg = "<"+partName+"> Error Nastran inputdeck (for MSM).";
					String allMsg = MC.getMsgWindow().addMessage(msg, MessageWindow.Error);
					med.getTextMessageWindow().setText(allMsg);
					med.getTextMessageWindow().setSelection(allMsg.length());
					System.out.println(msg);
				}
				
			});
			e.printStackTrace();
		}
		
	}
	
	public void running_manual(){
		this.nodeIdList = new ArrayList<String>();
		this.allNodeIdList = new ArrayList<String>();
		this.nameList = new ArrayList<String>();
		this.forceDataPathList = new ArrayList<ArrayList<String>>();
		this.nodeID_fileNameMap = new HashMap<String,String>();
		this.resultMatchIDMap = new HashMap<String,String>();
		this.resultTabledIDList = new ArrayList<String>();
		
		this.partName = "PA_ROD";
		this.onlyBulkFilePath = "D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\123123123\\Result\\1_InputdeckForMNF\\RenameBulk\\PA_ROD_onlyBluk.fem";
		this.exportResultName = "Base";
		this.workspace = "D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\123123123";
		
		// 1) NodeInformation 에서 NodeId, force fileName 추출
		String resultFolder = myUtil.setPath(this.workspace, DatabaseFolderName.Result);
		String nodeInfoFolder = myUtil.setPath(resultFolder, DatabaseFolderName.NodeInformation);
		String partNameFolder = myUtil.setPath(nodeInfoFolder, this.partName);
		this.nodeInformationFilePath = myUtil.setPath(partNameFolder, "Node_information."+this.partName+".txt");
		System.out.println("===>"+this.nodeInformationFilePath);
		this.findingNodeInfo();
		// 2) bulkFile 복사
		this.copyBulkFile();
		
		// 3) force 데이터를 TABLED1 로 변환
		this.changeForceDataToTABLED1();
		
		// 4) inputdeck 작성
		this.CreateInputDeck();
	}
	
	private void findingNodeInfo(){
		FindingNodeInfo obj = new FindingNodeInfo();
		obj.running(this.nodeInformationFilePath);
		this.nodeIdList = obj.getNodeIDList();
		//this.allNodeIdList = obj.getAllNodeIDList();
		this.nameList = obj.getNameList();
		
		String resultFolder = myUtil.setPath(this.workspace, DatabaseFolderName.Result);
		String forceFolder = myUtil.setPath(resultFolder, DatabaseFolderName.Force);
		String resultNameFolder = myUtil.setPath(forceFolder, this.exportResultName);
		String LocalFolder = myUtil.setPath(resultNameFolder, DatabaseFolderName.Local);
		this.partNameFolder = myUtil.setPath(LocalFolder, this.partName);
		/*
		PA_ROD.Spherical_PA_ROD_to_MOTOR_CRANK_BALL_PIN_i.Fx_Local.txt
		PA_ROD.Spherical_PA_ROD_to_MOTOR_CRANK_BALL_PIN_i.Fy_Local.txt
		PA_ROD.Spherical_PA_ROD_to_MOTOR_CRANK_BALL_PIN_i.Fz_Local.txt
		PA_ROD.Universal_PA_BALL_PIN_to_PA_ROD_j.Fx_Local.txt
		PA_ROD.Universal_PA_BALL_PIN_to_PA_ROD_j.Fy_Local.txt
		PA_ROD.Universal_PA_BALL_PIN_to_PA_ROD_j.Fz_Local.txt
		partName+"."+name+".Fx_Local.txt"
		partName+"."+name+".Fy_Local.txt"
		partName+"."+name+".Fz_Local.txt"
		// */
		String fx = ".Fx_Local.txt";
		String fy = ".Fy_Local.txt";
		String fz = ".Fz_Local.txt";
		
		for(String name : this.nameList){
			System.out.println(name);
			ArrayList<String> detailNameList = new ArrayList<String>();
			String fxPath = myUtil.setPath(this.partNameFolder, this.partName + "." + name + fx);
			String fyPath = myUtil.setPath(this.partNameFolder, this.partName + "." + name + fy);
			String fzPath = myUtil.setPath(this.partNameFolder, this.partName + "." + name + fz);
			detailNameList.add(fxPath);
			detailNameList.add(fyPath);
			detailNameList.add(fzPath);
			this.forceDataPathList.add(detailNameList);
		}
		
		for(int i=0;i<this.nodeIdList.size();i++){
			String value_id = nodeIdList.get(i);
			for(String path : this.forceDataPathList.get(i)){
				String key = path;
				this.nodeID_fileNameMap.put(key, value_id);
				this.allNodeIdList.add(value_id);
			}
		}
	}
	
	private void changeForceDataToTABLED1(){
		ArrayList<String> filePathList = new ArrayList<String>();
		for(ArrayList<String> list : this.forceDataPathList){
			for(String path : list){
				//System.out.println(path);
				filePathList.add(path);
			}
		}
		ChangeTABLED1 obj = new ChangeTABLED1();
		obj.running(filePathList,nodeID_fileNameMap);
		this.resultMatchIDMap = obj.getResultMatchID();
		this.resultTabledIDList = obj.getResultTabledIDList();
		this.allStepNum = obj.getAllStepNum();
		this.timeStep = obj.getTimeStep();
	}
	
	private void copyBulkFile(){
		String source = this.onlyBulkFilePath;
		
		String resultFolder = myUtil.setPath(this.workspace, DatabaseFolderName.Result);
		String forceFolder = myUtil.setPath(resultFolder, DatabaseFolderName.Force);
		String baseFolder = myUtil.setPath(forceFolder, this.exportResultName);
		String localFolder = myUtil.setPath(baseFolder, DatabaseFolderName.Local); 
		String partNameFolder = myUtil.setPath(localFolder, this.partName);
		String fileName = new File(source).getName();
		String target = myUtil.setPath(partNameFolder, fileName);
		
		//System.out.println(fileName);
		//System.out.println(target);
		
		myUtil.fileCopy(source, target);
		this.targetOnlyBulkPath = target; 
	}
	
	private void CreateInputDeck(){
		CreateInputDeckNastranForMSM obj = new CreateInputDeckNastranForMSM();
		obj.initData(targetOnlyBulkPath, allStepNum, timeStep, resultMatchIDMap, resultTabledIDList, allNodeIdList );
		obj.running(this.fsObj);
		this.inputdeckPath = obj.getInputdeckPath();
		//obj.running_manual(this.partName);
	}
	
	
	
	
	public static void main(String [] args){
		MSMSolving obj = new MSMSolving();
		obj.running_manual();
	}

}
