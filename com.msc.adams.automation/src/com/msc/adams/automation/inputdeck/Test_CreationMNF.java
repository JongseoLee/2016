package com.msc.adams.automation.inputdeck;

import java.util.ArrayList;

import com.msc.adams.automation.core.MainController;
import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.util.myUtil;

public class Test_CreationMNF {

	public final String BEGIN_BULK 	= "BEGIN BULK";
	public final String ENDDATA 	= "ENDDATA";
	
	public final String PARAM		= "PARAM";
	public final String DTI			= "DTI";
	public final String EIGRL		= "EIGRL";
	public final String RBE2 		= "RBE2";
	public final String ASET1 		= "ASET1";
	public final String SPOINT 		= "SPOINT";
	public final String QSET1		= "QSET1";
	
	
	public final String S_PARAM 	= "%ValueParam%";
	public final String S_DTI		= "%ValueDti%";
	public final String S_EIGRL		= "%ValueEigrl%";
	public final String S_ASET1		= "%ASET1%";
	public final String S_SPOINT 	= "%SPOINT%";
	public final String S_QSET1 	= "%QSET1%";
	public final String S_FILENAME	= "%fileName%";
	public final String S_BULKDATA  = "%BulkData%";
	
	private String bulkFilePath;
	private String inputdeckPath;
	
	public final String HeaderFileName = "MNF-HEADER-NASTRAN.txt";	
	private ArrayList<String> HeaderFileDataList;
	//private ArrayList<String> HeaderDataList;
	private ArrayList<String> BulkFileDataList;
	private ArrayList<String> BulkDataList;
	private ArrayList<String> outputDataList;
	
	private ArrayList<Param_Data> ParamDataList = new ArrayList<Param_Data>(); 
	private ArrayList<Param_Data> ParamDataList_header = new ArrayList<Param_Data>();
	private ArrayList<DTI_Data> DTIDataList = new ArrayList<DTI_Data>();
	private ArrayList<DTI_Data> DTIDataList_header = new ArrayList<DTI_Data>();
	private ArrayList<EIGRL_Data> EIGRLDataList = new ArrayList<EIGRL_Data>();
	private ArrayList<EIGRL_Data> EIGRLDataList_header = new ArrayList<EIGRL_Data>();
	private ASET1 Aset1Obj = new ASET1();
	private SPOINT SpointObj = new SPOINT();
	private QSET1 QSet1Obj = new QSET1();

	private String headerFile = "MNF-HEADER-NASTRAN.txt";
	//private String inputdeckFileName = "";
	
	private int SPOINT_startID = 999991;
	
	public Test_CreationMNF() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(String bulkFilePath){
		String path = "D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Template\\HeaderFile";
		this.bulkFilePath = bulkFilePath;
		String fileName = myUtil.getFileName(this.bulkFilePath);
		String inputdeckFileName = fileName+"_mnf_creation.bdf";
		this.inputdeckPath = myUtil.setPath(path, inputdeckFileName);
		
		this.ReadHeaderFile();
		
		this.ReadBulkFile();
		this.Finding_bulk();
		
		
		
		
		this.CreateInputdeck();
		//this.createInputDeck();
		this.writeInput();
	}
	
	private void ReadHeaderFile(){
		HeaderFileDataList = new ArrayList<String>();
		String path = "D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Template\\HeaderFile";
		String headerFilePath = myUtil.setPath(path, headerFile);
		
		Reader reader = new Reader(headerFilePath);
		reader.running();
		this.HeaderFileDataList = reader.getFileDataList();
		this.Finding_hedaerData();
		/*
		System.out.println("header size param: "+ this.ParamDataList_header.size());
		System.out.println("header size DTI : "+ this.DTIDataList_header.size());
		System.out.println("header size EIGRL : "+ this.EIGRLDataList_header.size());
		//*/
	}
	
	private void Finding_hedaerData(){
		for(String line : this.HeaderFileDataList){
			if(line.contains(PARAM)){
				Param_Data pObj = new Param_Data();
				pObj.running(line);
				this.ParamDataList_header.add(pObj);
			}else if(line.contains(DTI)){
				DTI_Data dObj = new DTI_Data();
				dObj.running(line);
				this.DTIDataList_header.add(dObj);
			}else if(line.contains(EIGRL)){
				EIGRL_Data eObj =new EIGRL_Data();
				eObj.running(line);
				this.EIGRLDataList_header.add(eObj);
			}
		}
	}
	
	
	private void ReadBulkFile(){
		this.BulkFileDataList = new ArrayList<String>();
		
		Reader reader = new Reader(this.bulkFilePath);
		reader.running();
		this.BulkFileDataList = reader.getFileDataList();
		this.Finding_data();
	}
	
	private void Finding_data(){
		for(String line : this.BulkFileDataList){
			
			if(line.contains(PARAM)){
				Param_Data pObj = new Param_Data();
				pObj.running(line);
				boolean add = false;
				for(Param_Data obj : this.ParamDataList_header){
					if(pObj.isSame(obj)){
						//System.out.println("header: "+obj.getFullCardData().trim()+"|");
						//System.out.println("bulk  : "+pObj.getFullCardData().trim()+"|\n");
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
		//this.writeInput();
	}
	
	
	
	private void CreateInputdeck(){
		//HeaderDataList = new ArrayList<String>();
		outputDataList = new ArrayList<String>();
		String firstLine = this.HeaderFileDataList.get(0);
		SPOINT_startID = Integer.parseInt(firstLine.replace("$", "").trim()); 
		for(String line : this.HeaderFileDataList){
			if(line.contains(S_FILENAME)){
				String newLine = line.replace(S_FILENAME, myUtil.getFileName(this.inputdeckPath));
				this.outputDataList.add(newLine);
			}else if(line.contains(S_PARAM)){
				String newLine = "";
				for(Param_Data obj : this.ParamDataList){
					newLine += obj.getFullCardData() +"\n";
				}
				//System.out.println(newLine);
				this.outputDataList.add(newLine);
			}else if(line.contains(S_DTI)){
				String newLine = "";
				for(DTI_Data obj : this.DTIDataList){
					newLine += obj.getFullCardData() +"\n";
				}
				this.outputDataList.add(newLine);
			}else if(line.contains(S_EIGRL)){
				String newLine = "";
				for(EIGRL_Data obj : this.EIGRLDataList){
					newLine += obj.getFullCardData() +"\n";
				}
				this.outputDataList.add(newLine);
			}else if(line.contains(S_ASET1)){
				String newLine = this.Aset1Obj.getFullCardData();
				this.outputDataList.add(newLine);
				
				int lastNum = 6*this.Aset1Obj.getIDListSize()+6;
				this.SpointObj.running(this.SPOINT_startID, lastNum);
				this.QSet1Obj.running(999991, lastNum);
			}else if(line.contains(S_SPOINT)){
				String newLine = this.SpointObj.getFullCardData();
				this.outputDataList.add(newLine);
			}else if(line.contains(S_QSET1)){
				String newLine = this.QSet1Obj.getFullCardData();
				this.outputDataList.add(newLine);
			}else if(line.contains(S_BULKDATA)){
				String newLine = "";
				for(String bulkLine : this.BulkDataList){
					newLine += bulkLine +"\n";
				}
				this.outputDataList.add(newLine);
			}
			else{
				this.outputDataList.add(line);
			}
		}
		
		//this.outputDataList.add(ENDDATA);
	}
	
	
	
	
	public void writeInput(){
		Writer writer = new Writer(this.inputdeckPath);
		writer.running(this.outputDataList);
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Template\\HeaderFile";
		String simpleBulk = "bulk_simple.dat";
		String complxBulk = "bulk_complx.dat";
		String headerFile = "MNF-HEADER-NASTRAN.txt";
		String simpleBulkPath = myUtil.setPath(path, simpleBulk);
		String complxBulkPath = myUtil.setPath(path, complxBulk);
		String headerPath = myUtil.setPath(path, headerFile);
		
		Test_CreationMNF createInputObj = new Test_CreationMNF();
		//createInputObj.running(simpleBulkPath);
		createInputObj.running(complxBulkPath);
	}

}
