package com.msc.adams.automation.msr;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.adams.automation.datas.Part_FatSolving;
import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.util.myUtil;

public class CreateInputdeckFEMFATForMSR {
	private MainController MC = MainController.getInstance();
	private Mediator med = Mediator.getInstance();
	
	private Part_FatSolving fsObj;
	
	private String MSR_Type;
	
	public final String HeaderFileName_MANUAL = "MSR-HEADER-FEMFAT_MANUAL.txt";
	public final String HeaderFileName_AUTO = "MSR-HEADER-FEMFAT_AUTO.txt";
	
	public final String DefineBulkFilePath = "%DefineBulkFilePath%";
	public final String DefineGroup = "%DefineGroup%";
	public final String DefineItrNum = "%DefineItrNum%";
	public final String DefineMaxStressFileFormat = "%DefineMaxStressFileFormat%";
	public final String DefineMaxStressInputFile = "%DefineMaxStressInputFile%";
	public final String DefineLoadCaseNumber = "%DefineLoadCaseNumber%";
	public final String DefineMaxHistoryFileFormat = "%DefineMaxHistoryFileFormat%";
	public final String DefineHistoryInputFile = "%DefineHistoryInputFile%";
	public final String DefineMaterialReadFile = "%DefineMaterialReadFile%";
	public final String DefineGroupMaterialMatching = "%DefineGroupMaterialMatching%";
	public final String DefineOutputFilename = "%DefineOutputFilename%";
	public final String CycleNumber = "%CycleNumber%";
	public final String GroupAllIndex = "%GroupAllIndex%";
	
	// MSR 타입으로 인풋뎃 생성 하고 실행 부분 만들기
	
	private String partName;
	private String headerFilePath;
	private String bulkFilePath;
	private String op2FilePath;
	private String DACFolderPath;
	private String inputdeckPath;
	private String DACNumber;
	private String CycleNumberValue = "1.50e+05";
	private int itrNum = 0;
	
	private ArrayList<String> HeaderFileDataList;
	private ArrayList<String> bulkFileDataList;
	private ArrayList<HMSET> HMSETObjList;
	private ArrayList<String> outputDataList;
	private ArrayList<String> FemfatMaterialDBList;
	private Map<String,String> FemfatMaterialDBMap;
	private Map<String,String> FemfatMaterialDBDefineNumberMap;
	
	private String Group_All_index = "1";
	
	public CreateInputdeckFEMFATForMSR() {
		// TODO Auto-generated constructor stub
		this.HeaderFileDataList = new ArrayList<String>();
		this.bulkFileDataList = new ArrayList<String>();
		this.HMSETObjList = new ArrayList<HMSET>();
		this.outputDataList = new ArrayList<String>();
		this.FemfatMaterialDBList = new ArrayList<String>();
		this.FemfatMaterialDBMap = new HashMap<String,String>();
		this.FemfatMaterialDBDefineNumberMap = new HashMap<String,String>();
		this.CycleNumberValue = MC.getDBStep5().getCycleNumberValue();
	
		
	}
	
	public void running(Part_FatSolving fsObj,ArrayList<String> FemfatMaterialDBList){
		//===================================================
		this.fsObj = fsObj;
		this.MSR_Type = this.fsObj.getType();
		
		String msck_TemplatePath = myUtil.setPath(MC.getAppPath(), DatabaseFolderName.msck_Template);
		String HeaderFileFolderPath = myUtil.setPath(msck_TemplatePath, DatabaseFolderName.HeaderFile);
		if(MSR_Type.equals(Part_FatSolving.Type_MSR_AUTO)){
			this.headerFilePath = myUtil.setPath(HeaderFileFolderPath, HeaderFileName_AUTO);
		}else if(MSR_Type.equals(Part_FatSolving.Type_MSR_MANUAL)){
			this.headerFilePath = myUtil.setPath(HeaderFileFolderPath, HeaderFileName_MANUAL);
		}
		
		
		this.partName = fsObj.getPartName();
		this.bulkFilePath = fsObj.getFinalBulkPath().replace("\\","/");
		this.op2FilePath = fsObj.getFinalOP2Path().replace("\\","/");
		this.DACFolderPath = fsObj.getFinalDACFolder().replace("\\","/");
		this.inputdeckPath = myUtil.setPath(this.DACFolderPath, this.partName+"_msr_creation.ffj");
		this.DACNumber = fsObj.getFinalDACNumber();
		this.itrNum = Integer.valueOf(this.DACNumber) - 6;
		
		this.FemfatMaterialDBList = FemfatMaterialDBList;
		int defineNum = 1;
		for(String path : this.FemfatMaterialDBList){
			String fileName = myUtil.getFileName(path);	// 확장자 포함 이름
			this.FemfatMaterialDBMap.put(fileName, path);	// 확장자 포함이름, 전체 경로
			String MaterialDbName = this.getMaterialDBName(fileName);	// 물성이름만 ex) SPAH440
			this.FemfatMaterialDBDefineNumberMap.put(MaterialDbName,String.valueOf(defineNum));	// 물성이름 , 물성 선언 ID 번호
			defineNum++;
		}
		//===================================================
		
		this.ReadHeaderFile();
		this.ReadBulkFile();
		this.ParsingBulkFile();
		this.CreateInputdeck();
		this.writeInputdeck();
	}
	
	public void running_manual(){
		//===================================================
		//String msck_TemplatePath = myUtil.setPath(MC.getAppPath(), DatabaseFolderName.msck_Template);
		
		// Auto - Manual 선택
		//this.MSR_Type = Part_FatSolving.Type_MSR_AUTO;
		this.MSR_Type = Part_FatSolving.Type_MSR_MANUAL;
		String HeaderFileFolderPath = "D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Template\\HeaderFile";
		if(MSR_Type.equals(Part_FatSolving.Type_MSR_AUTO)){
			this.headerFilePath = myUtil.setPath(HeaderFileFolderPath, HeaderFileName_AUTO);
		}else if(MSR_Type.equals(Part_FatSolving.Type_MSR_MANUAL)){
			this.headerFilePath = myUtil.setPath(HeaderFileFolderPath, HeaderFileName_MANUAL);
		}
		
		
		//this.headerFilePath = myUtil.setPath(HeaderFileFolderPath, HeaderFileName);
		////String DacFolderPath = myUtil.setPath(HeaderFileFolderPath, "DR_PIVOT_DRIVER_ARM");
		String DacFolderPath = myUtil.setPath(HeaderFileFolderPath, "PA_ROD");
		this.DACFolderPath = DacFolderPath.replace("\\","/");
		////this.partName = "DR_PIVOT_DRIVER_ARM";
		this.partName = "PA_ROD";
		////this.bulkFilePath = myUtil.setPath(DACFolderPath, partName+"_mnf_creation.bdf").replace("\\","/");
		this.bulkFilePath = myUtil.setPath(DACFolderPath, partName+"_mnf_creation.fem").replace("\\","/");;
		this.op2FilePath = myUtil.setPath(DACFolderPath, partName+"_mnf_creation.op2").replace("\\","/");
		this.inputdeckPath = myUtil.setPath(this.DACFolderPath, this.partName+"_msr_creation.ffj");
		this.DACNumber = "38";
		this.itrNum = Integer.valueOf(this.DACNumber) - 6;
		
		this.FemfatMaterialDBList.add("C:\\CAESoft\\ECS\\FEMFAT5.2a\\material_database\\HMC-CV_MAToDB\\CV-FAToMAT_ADC.ffd");
		this.FemfatMaterialDBList.add("C:\\CAESoft\\ECS\\FEMFAT5.2a\\material_database\\HMC-CV_MAToDB\\CV-FAToMAT_SAPH440.ffd");
		this.FemfatMaterialDBList.add("C:\\CAESoft\\ECS\\FEMFAT5.2a\\material_database\\HMC-CV_MAToDB\\CV-FAToMAT_SPCC.ffd");
		this.FemfatMaterialDBList.add("C:\\CAESoft\\ECS\\FEMFAT5.2a\\material_database\\HMC-CV_MAToDB\\CV-FAToMAT_SPHC.ffd");
		this.FemfatMaterialDBMap.put("CV-FAToMAT_ADC", "C:\\CAESoft\\ECS\\FEMFAT5.2a\\material_database\\HMC-CV_MAToDB\\CV-FAToMAT_ADC.ffd");
		this.FemfatMaterialDBMap.put("CV-FAToMAT_SAPH440", "C:\\CAESoft\\ECS\\FEMFAT5.2a\\material_database\\HMC-CV_MAToDB\\CV-FAToMAT_SAPH440.ffd");
		this.FemfatMaterialDBMap.put("CV-FAToMAT_SPCC", "C:\\CAESoft\\ECS\\FEMFAT5.2a\\material_database\\HMC-CV_MAToDB\\CV-FAToMAT_SPCC.ffd");
		this.FemfatMaterialDBMap.put("CV-FAToMAT_SPHC", "C:\\CAESoft\\ECS\\FEMFAT5.2a\\material_database\\HMC-CV_MAToDB\\CV-FAToMAT_SPHC.ffd");
		String MaterialDbName = this.getMaterialDBName("CV-FAToMAT_ADC");	// 물성이름만 ex) SPAH440
		this.FemfatMaterialDBDefineNumberMap.put(MaterialDbName,"1");	// 물성이름 , 물성 선언 ID 번호
		String MaterialDbName2 = this.getMaterialDBName("CV-FAToMAT_SAPH440");	// 물성이름만 ex) SPAH440
		this.FemfatMaterialDBDefineNumberMap.put(MaterialDbName2,"2");	// 물성이름 , 물성 선언 ID 번호
		String MaterialDbName3 = this.getMaterialDBName("CV-FAToMAT_SPCC");	// 물성이름만 ex) SPAH440
		this.FemfatMaterialDBDefineNumberMap.put(MaterialDbName3,"3");	// 물성이름 , 물성 선언 ID 번호
		String MaterialDbName4 = this.getMaterialDBName("CV-FAToMAT_SPHC");	// 물성이름만 ex) SPAH440
		this.FemfatMaterialDBDefineNumberMap.put(MaterialDbName4,"4");	// 물성이름 , 물성 선언 ID 번호
		//myUtil.printMapData(FemfatMaterialDBDefineNumberMap);
		//===================================================
		
		this.ReadHeaderFile();
		this.ReadBulkFile();
		this.ParsingBulkFile();
		this.CreateInputdeck();
		this.writeInputdeck();
		
	}
	
	private void ReadHeaderFile(){
		Reader reader = new Reader(this.headerFilePath);
		reader.running();
		this.HeaderFileDataList = reader.getFileDataList();
	}
	
	private void ReadBulkFile(){
		Reader reader = new Reader(this.bulkFilePath);
		reader.running();
		this.bulkFileDataList = reader.getFileDataList();
	}
	
	private void ParsingBulkFile(){
		ArrayList<String> allHMSETLine = new ArrayList<String>();
		int index = 1;
		for(String line : this.bulkFileDataList){
			if(line.contains("$HMSET")){
				if(allHMSETLine.contains(line)){
					
				}else{
					allHMSETLine.add(line);
					ArrayList<String> tokenList = new ArrayList<String>();
					tokenList = myUtil.splitData(line, " ");
					if(tokenList.get(2).equals("2")){
						HMSET obj = new HMSET();
						obj.running(tokenList,String.valueOf(index));
						index++;
						HMSETObjList.add(obj);
					}
				}
			}
		}
	}
	
	private ArrayList<String> getDefineGroup(){
		ArrayList<String> result = new ArrayList<String>();
		if(this.HMSETObjList.size() == 0){
			this.Group_All_index = "1";
			result.add("# Group name is empty.");
			result.add("setValue {} {} GUI_Group:Group {1 - ALL}");
			//result.add("setValue {} {} GUI_Group:OperatorAttrib 9");
			//result.add("setValue {} {} GUI_Group:Add");
		}else{
			int indexNum = 0;
			
			if(this.HMSETObjList.size() == 1){
				if(this.HMSETObjList.get(0).getGroupName().equals("ALL")){
					int lastIndex = indexNum +1;
					this.Group_All_index = String.valueOf(lastIndex);
					result.add("setValue {} {} GUI_Group:Group {"+lastIndex+" - ALL}");
				}else{
					for(HMSET obj : this.HMSETObjList){
										
						String index = obj.getIndex();
						indexNum ++;
						String groupName = obj.getGroupName();
						result.add("setValue {} {} GUI_Group:Group {"+index+" - "+groupName+"}");
						result.add("setValue {} {} GUI_Group:OperatorAttrib 9");
						result.add("setValue {} {0 ok} GUI_Group:Add");
					}
					
					int lastIndex = indexNum +1;
					this.Group_All_index = String.valueOf(lastIndex);
					result.add("setValue {} {} GUI_Group:Group {"+lastIndex+" - ALL}");
					//result.add("setValue {} {} GUI_Group:OperatorAttrib 9");
					//result.add("setValue {} {} GUI_Group:Add");
				}
			}else{
				for(HMSET obj : this.HMSETObjList){
					String index = obj.getIndex();
					indexNum ++;
					String groupName = obj.getGroupName();
					result.add("setValue {} {} GUI_Group:Group {"+index+" - "+groupName+"}");
					result.add("setValue {} {} GUI_Group:OperatorAttrib 9");
					result.add("setValue {} {0 ok} GUI_Group:Add");
				}
				
				int lastIndex = indexNum +1;
				this.Group_All_index = String.valueOf(lastIndex);
				result.add("setValue {} {} GUI_Group:Group {"+lastIndex+" - ALL}");
				//result.add("setValue {} {} GUI_Group:OperatorAttrib 9");
				//result.add("setValue {} {} GUI_Group:Add");
			}
		}
		
		return result;
	}
	
	private ArrayList<String> getDefineMaxStressFileFormat(){
		ArrayList<String> result = new ArrayList<String>();
		for(int i= 1; i<=this.itrNum; i++){
			result.add("setValue {} {} MaxStressFileFormat "+ i +" 10");
		}
		return result;
	}
	
	private ArrayList<String> getDefineMaxStressInputFile(){
		ArrayList<String> result = new ArrayList<String>();
		for (int i=1; i<=this.itrNum ; i++){
			result.add("setValue {} {} MaxStressInputFile "+ i +" "+this.op2FilePath);
		}
		return result;
	}
	
	private ArrayList<String> getDefineLoadCaseNumber(){
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 1; i<=this.itrNum;i++){
			result.add("setValue {} {} LoadCaseNumber "+i+" "+(i+6));
		}
		return result;
	}
	
	private ArrayList<String> getDefineMaxHistoryFileFormat(){
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 1; i<=this.itrNum;i++){
			result.add("setValue {} {} MaxHistoryFileFormat "+i+" 8");
		}
		return result;
	}
	
	private ArrayList<String> getDefineHistoryInputFile(){
		ArrayList<String> result = new ArrayList<String>();
		for(int i=1;i<=this.itrNum;i++){
			String dacFileName = this.partName.toLowerCase()+"_"+String.format("%04d", (i+6))+".dac";
			String dacFilePath = myUtil.setPath(this.DACFolderPath, dacFileName);
			result.add("setValue {} {} MaxHistoryInputFile "+i+" "+dacFilePath.replace("\\", "/"));
		};
		return result; 
	}
	
	private ArrayList<String> getDefineMaterialReadFile(){
		ArrayList<String> result = new ArrayList<String>();
		for(String path : this.FemfatMaterialDBList){
			result.add("setValue {} {0 ok 1 ok 2 ok} MaterialReadFile "+path.replace("\\", "/"));
		}
		return result;
	}
	
	private ArrayList<String> getDefineGroupMaterialMatching(){
		ArrayList<String> result = new ArrayList<String>();
		if(this.HMSETObjList.size() == 0){
			
			String materialFileName = this.getMaterialDBFileName("SAPH440");
			String materialName = this.getMaterialDBName(materialFileName);
			String defineMaterialNumber = this.getDefineMaterialDBNumber(materialName);
			
			result.add("setValue {} {} GUI_Group:Group {1 - ALL}");
			result.add("setValue {} {} FatParamMaterial {"+defineMaterialNumber+" - SAPH440}");
			result.add("setValue {} {} FatParamPredefine "+defineMaterialNumber);
			result.add("setValue {} {} FatParamTecSize 10");
		}else {
			if(this.HMSETObjList.size() == 1){
				if(this.HMSETObjList.get(0).getGroupName().equals("ALL")){
					int indexNum = 0;
					
					String index = this.HMSETObjList.get(0).getIndex();
					indexNum ++;
					String materialName = this.HMSETObjList.get(0).getMaterialName();
					String groupName = this.HMSETObjList.get(0).getGroupName();
					//String materialFileName = this.getMaterialDBFileName(materialName);
					String defineMaterialNumber = this.getDefineMaterialDBNumber(materialName);
					String result_materialName = this.getMaterialDBName(materialName);
					//System.out.println(materialName+ "\t" +defineMaterialNumber);
					
					//result.add("setValue {} {} MaterialLabel {"+index+" - "+materialFileName+"}");
					result.add("setValue {} {} GUI_Group:Group {"+index+" - "+groupName+"}");
					//result.add("setValue {} {} FatParamMaterial {"+index+" - "+materialFileName+"}");
					result.add("setValue {} {} FatParamMaterial {"+defineMaterialNumber+" - "+result_materialName+"}");
					result.add("setValue {} {} FatParamPredefine "+defineMaterialNumber);
					result.add("setValue {} {} FatParamTecSize 10");
					
					
				}else{
					int indexNum = 0;
					
					String materialFileName_base = this.getMaterialDBFileName("SAPH440");
					String materialName_base = this.getMaterialDBName(materialFileName_base);
					String defineMaterialNumber_base = this.getDefineMaterialDBNumber(materialName_base);
					//System.out.println(materialName_base+ "\t" +defineMaterialNumber_base);
					
					int lastIndex = indexNum +1;
					result.add("setValue {} {} GUI_Group:Group {"+this.Group_All_index+" - ALL}");
					result.add("setValue {} {} FatParamMaterial {"+defineMaterialNumber_base+" - SAPH440}");
					result.add("setValue {} {} FatParamPredefine "+defineMaterialNumber_base);
					result.add("setValue {} {} FatParamTecSize 10");
					
					for(HMSET obj : this.HMSETObjList){
						String index = obj.getIndex();
						indexNum ++;
						String materialName = obj.getMaterialName();
						String groupName = obj.getGroupName();
						//String materialFileName = this.getMaterialDBFileName(materialName);
						String defineMaterialNumber = this.getDefineMaterialDBNumber(materialName);
						String result_materialName = this.getMaterialDBName(materialName);
						//System.out.println(materialName+ "\t" +defineMaterialNumber);
						
						//result.add("setValue {} {} MaterialLabel {"+index+" - "+materialFileName+"}");
						result.add("setValue {} {} GUI_Group:Group {"+index+" - "+groupName+"(MOD.)}");
						//result.add("setValue {} {} FatParamMaterial {"+index+" - "+materialFileName+"}");
						result.add("setValue {} {} FatParamMaterial {"+defineMaterialNumber+" - "+result_materialName+"}");
						result.add("setValue {} {} FatParamPredefine "+defineMaterialNumber);
						result.add("setValue {} {} FatParamTecSize 5");
					}
				}
			}else{
				int indexNum = 0;
				
				String materialFileName_base = this.getMaterialDBFileName("SAPH440");
				String materialName_base = this.getMaterialDBName(materialFileName_base);
				String defineMaterialNumber_base = this.getDefineMaterialDBNumber(materialName_base);
				//System.out.println(materialName_base+ "\t" +defineMaterialNumber_base);
				
				int lastIndex = indexNum +1;
				result.add("setValue {} {} GUI_Group:Group {"+this.Group_All_index+" - ALL}");
				result.add("setValue {} {} FatParamMaterial {"+defineMaterialNumber_base+" - SAPH440}");
				result.add("setValue {} {} FatParamPredefine "+defineMaterialNumber_base);
				result.add("setValue {} {} FatParamTecSize 10");
				
				for(HMSET obj : this.HMSETObjList){
					String index = obj.getIndex();
					indexNum ++;
					String materialName = obj.getMaterialName();
					String groupName = obj.getGroupName();
					//String materialFileName = this.getMaterialDBFileName(materialName);
					String defineMaterialNumber = this.getDefineMaterialDBNumber(materialName);
					String result_materialName = this.getMaterialDBName(materialName);
					//System.out.println(materialName+ "\t" +defineMaterialNumber);
					
					//result.add("setValue {} {} MaterialLabel {"+index+" - "+materialFileName+"}");
					result.add("setValue {} {} GUI_Group:Group {"+index+" - "+groupName+"(MOD.)}");
					//result.add("setValue {} {} FatParamMaterial {"+index+" - "+materialFileName+"}");
					result.add("setValue {} {} FatParamMaterial {"+defineMaterialNumber+" - "+result_materialName+"}");
					result.add("setValue {} {} FatParamPredefine "+defineMaterialNumber);
					result.add("setValue {} {} FatParamTecSize 5");
				}
			}
			
			
			
		}
		return result;
	}
	
	private String getMaterialDBFileName(String materialName){
		String result = "";
		Iterator<String> iterator = this.FemfatMaterialDBMap.keySet().iterator();
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			if(key.contains(materialName)){
				result = key;
				break;
			}
		}
		return result;
	}
	
	private String getMaterialDBName(String materialName){
		String result = "";
		Iterator<String> iterator = this.FemfatMaterialDBMap.keySet().iterator();
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			if(key.contains(materialName)){
				ArrayList<String> tokens = new ArrayList<String>();
				tokens = myUtil.splitData(key, "_");
				result = tokens.get(tokens.size()-1);
				break;
			}
		}
		return result;
	}
	
	private String getDefineMaterialDBNumber(String dbName){
		String result = "1";
		if(this.FemfatMaterialDBDefineNumberMap.containsKey(dbName)){
			result = this.FemfatMaterialDBDefineNumberMap.get(dbName);
		}
		return result;
	}
	
	private void CreateInputdeck(){
		for(String line : this.HeaderFileDataList){
			if(line.contains(DefineBulkFilePath)){
				String newLine = line.replace(DefineBulkFilePath, this.bulkFilePath);
				this.outputDataList.add(newLine);
				
			}else if(line.contains(DefineGroup)){
				for(String data : this.getDefineGroup()){
					this.outputDataList.add(data);
				}
			}else if(line.contains(DefineItrNum)){
				String newLine = line.replace(DefineItrNum, String.valueOf(this.itrNum));
				this.outputDataList.add(newLine);
			}else if(line.contains(DefineMaxStressFileFormat)){
				for(String data: this.getDefineMaxStressFileFormat()){
					this.outputDataList.add(data);
				}
			}else if(line.contains(DefineMaxStressInputFile)){
				for(String data : this.getDefineMaxStressInputFile()){
					this.outputDataList.add(data);
				}
			}else if(line.contains(DefineLoadCaseNumber)){
				for(String data : this.getDefineLoadCaseNumber()){
					this.outputDataList.add(data);
				}
			}else if(line.contains(DefineMaxHistoryFileFormat)){
				for(String data : this.getDefineMaxHistoryFileFormat()){
					this.outputDataList.add(data);
				}
			}else if(line.contains(DefineHistoryInputFile)){
				for(String data : this.getDefineHistoryInputFile()){
					this.outputDataList.add(data);
				}
			}else if(line.contains(DefineMaterialReadFile)){
				for(String data : this.getDefineMaterialReadFile()){
					this.outputDataList.add(data);
				}
			}else if(line.contains(DefineGroupMaterialMatching)){
				for(String data : this.getDefineGroupMaterialMatching()){
					this.outputDataList.add(data);
				}
			}else if(line.contains(CycleNumber)){
				String newLine = line.replace(CycleNumber, this.CycleNumberValue);
				this.outputDataList.add(newLine);
			}else if(line.contains(DefineOutputFilename)){
				String newLine = line.replace(DefineOutputFilename, this.partName+"_msr_creation");
				this.outputDataList.add(newLine);
			}else if(line.contains(GroupAllIndex)){
				String newLine = line.replace(GroupAllIndex, this.Group_All_index);
				this.outputDataList.add(newLine);
			}else{
				this.outputDataList.add(line);
			}
		}
	}
	
	private void writeInputdeck(){
		Writer writer = new Writer(this.inputdeckPath);
		writer.running(outputDataList);
	}
	
	public String getInputdeckPath() {
		return inputdeckPath;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateInputdeckFEMFATForMSR obj = new CreateInputdeckFEMFATForMSR();
		obj.running_manual();
		
	}

}
