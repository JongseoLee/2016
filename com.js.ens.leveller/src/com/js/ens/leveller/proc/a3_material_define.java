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

public class a3_material_define {
	private Logger log = Logger.getLogger(a3_material_define.class);
	
	private LevellerMain LMain = LevellerMain.getInstatnce();
	private ArrayList<String> procDataList;
	private String procFilePath;
	
	private ArrayList<String> fileDataList;
	private String plateType;
	
	private String tableName_elasticModulus = "elastic_modulus";
	private String tableName_flowStress = "flow_stress";
	private String tableName_thermal_expansion ="CTE";
	private String tableName_poisson = "poisson";
	

	public a3_material_define(){
		fileDataList = new ArrayList<String>();
		plateType = LMain.getcomboType();
	}
	
	public a3_material_define(String path){
		fileDataList = new ArrayList<String>();
		this.procFilePath = path;
		plateType = LMain.getcomboType();
	}
	
	public void running(){
		try{
			//01_elastic_modulus
			if (LMain.getYoungsModulusType().toLowerCase().equals("constant")) {
				elastic_modulus_const EMCObj = new elastic_modulus_const(LMain.getTextYoungsModulus());
				EMCObj.running();
			} else if (LMain.getYoungsModulusType().toLowerCase().equals("table")) {
				elastic_modulus EMObj = new elastic_modulus(LMain.getTextYoungsModulus());
				EMObj.running();
				this.tableName_elasticModulus = EMObj.getTableName();	
			}
			// 02_flow_stress
			if (LMain.getFlowStressType().toLowerCase().equals("constant")) {
				flow_stress_const FSCObj = new flow_stress_const(LMain.getTextYieldStrength(),LMain.getTextTensileStrength(),LMain.getTextElongation());
				FSCObj.running();
			} else if (LMain.getFlowStressType().toLowerCase().equals("table")) {
				flow_stress FSObj = new flow_stress(LMain.getTextFlowStress());
				FSObj.running();
				this.tableName_flowStress = FSObj.getTableName();	
			}
			// 03_thermal_expansion
			if (LMain.getThermalExpansionCoefficientType().toLowerCase().equals("constant")) {
				thermal_expansion_const TECObj = new thermal_expansion_const(LMain.getTextThermalExpansionCoefficient());
				TECObj.running();
			}else if(LMain.getThermalExpansionCoefficientType().toLowerCase().equals("table")){
				thermal_expansion TEObj = new thermal_expansion(LMain.getTextThermalExpansionCoefficient());
				TEObj.running();
				this.tableName_thermal_expansion = TEObj.getTableName();
			}
			// 04_poisson
			if (LMain.getPoissonsRatioType().toLowerCase().equals("constant")) {
				poisson_const PCObj = new poisson_const(LMain.getTextPoissonsRatio());
				PCObj.running();
			} else if (LMain.getPoissonsRatioType().toLowerCase().equals("table")) {
				poisson PObj = new poisson(LMain.getTextPoissonsRatio());
				PObj.running();
				this.tableName_poisson = PObj.getTableName();
			}
			// 05 Mass Density
			mass_density MDObj = new mass_density(LMain.getTextMassDensity());
			MDObj.running();
			
			procFilePath = myUtil.setPath(myUtil.setPath(LMain.getWorkspace(), "proc"), "a3_material_define.proc");
			
			readSourceData();
			swapValue();
			writeProc();
			
			LMain.getExportMSG().addData("SUCCESS - Export(a3_material_define) \n path : "+ this.procFilePath);
			log.info("SUCCESS - Export(a3_material_define) \n path : "+ this.procFilePath);

		}catch(Exception e){
			String msg = "ERROR - Export(a3_material_define)";
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
		String sourcePath = myUtil.setPath(s_folder, "a3_material_define.proc");
		
		Reader reader = new Reader(sourcePath);
		reader.running();
		fileDataList = reader.getFileDataList();
	}
	
	public void swapValue() throws Exception{
		procDataList = new ArrayList<String>();
		String newLine = null;
		for(String line : fileDataList){
			if(line.contains("%"+ProcVariable.elasticModulusType+"%")){
				if(LMain.getYoungsModulusType().toLowerCase().equals("constant")){
					procDataList.add("*exec_procedure mat01_elastic_modulus_const.proc");
				}else if(LMain.getYoungsModulusType().toLowerCase().equals("table")){
					procDataList.add("*exec_procedure mat01_elastic_modulus.proc");		
				}
				continue;
			}else if(line.contains("%"+ProcVariable.poissonType+"%")){
				if(LMain.getPoissonsRatioType().toLowerCase().equals("constant")){
					procDataList.add("*exec_procedure mat04_poisson_const.proc");
				}else if(LMain.getPoissonsRatioType().toLowerCase().equals("table")){
					procDataList.add("*exec_procedure mat04_poisson.proc");		
				}
				continue;
			}else if(line.contains("%"+ProcVariable.flowStressType+"%")){
				if(LMain.getFlowStressType().toLowerCase().equals("constant")){
					procDataList.add("*exec_procedure mat02_flow_stress_const.proc");
				}else if(LMain.getFlowStressType().toLowerCase().equals("table")){
					procDataList.add("*exec_procedure mat02_flow_stress.proc");		
				}
				continue;
			}else if(line.contains("%"+ProcVariable.thermal_expansionType+"%")){
				if(LMain.getThermalExpansionCoefficientType().toLowerCase().equals("constant")){
					procDataList.add("*exec_procedure mat03_thermal_expansion_const.proc");
				}else if(LMain.getThermalExpansionCoefficientType().toLowerCase().equals("table")){
					procDataList.add("*exec_procedure mat03_thermal_expansion.proc");		
				}
				continue;
			}else if(line.contains("%"+ProcVariable.tableName_elasticModulus+"%")){
				newLine = line.replace("%"+ProcVariable.tableName_elasticModulus+"%", this.tableName_elasticModulus);
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.tableName_poisson+"%")){
				newLine = line.replace("%"+ProcVariable.tableName_poisson+"%", this.tableName_poisson);
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.tableName_flowStress+"%")){
				newLine = line.replace("%"+ProcVariable.tableName_flowStress+"%", this.tableName_flowStress);
				procDataList.add(newLine);
				continue;
			}else if(line.contains("%"+ProcVariable.tableName_thermal_expansion+"%")){
				newLine = line.replace("%"+ProcVariable.tableName_thermal_expansion+"%", this.tableName_thermal_expansion);
				procDataList.add(newLine);
				continue;
			}else {
				procDataList.add(line);
			}
		}
	}
	
	private void writeProc() throws Exception{
		Writer writer = new Writer(procFilePath,procDataList);
		writer.running();
	}

}
