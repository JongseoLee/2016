package com.msc.adams.automation.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.io.Reader;
import com.msc.parser.ParserDefault;
import com.msc.util.myUtil;

public class UILabel {
	private MainController MC = MainController.getInstance();
	private static UILabel instance = new UILabel();
	public static UILabel getInstatnce(){
		return instance;
	}
	
	private Map<String,String> UILabelMap;
	private String UILabelPath;
	
	public UILabel(){
		this.UILabelMap = new HashMap<String,String>();
		//this.UILabelPath = myUtil.setPath(myUtil.setPath(System.getProperty("user.dir"), DatabaseFolderName.msck_Config),"UILabel.ini");
		this.UILabelPath = myUtil.setPath(myUtil.setPath(MC.getAppPath(), DatabaseFolderName.msck_Config),"UILabel.ini");
		this.readUILabelFile();
	}
	
	private void readUILabelFile(){
		Reader obj = new Reader(this.UILabelPath);
		obj.running();
		for(String line : obj.getFileDataList()){
			String data = line.trim();
			if(data.contains("=")){
				ArrayList<String> tokens = new ArrayList<String>();
				tokens = ParserDefault.splitLineData(data, "=");
				this.UILabelMap.put(tokens.get(0), tokens.get(1));
			}
		}
	}
	
	public String getLabel(String key){
		if(this.UILabelMap.containsKey(key)){
			return this.UILabelMap.get(key);
		}else{
			return "no-Label";
		}
	}
	
	///////////////////////////////////////////////////////////////////
	// UILabel.java => Var = value
	// UILabel.txt  =>       value = labelValue
	//
	// Common Label
	public static String PROJECTNAME 		= 	"Project Name";
	public static String WORKSPACE 			= 	"Workspace";
	public static String PROCESSLEVEL 		= 	"Process Level";
	public static String STPE1				=	"STEP1";
	public static String STPE2				=	"STEP2";
	public static String STPE3				=	"STEP3";
	public static String STPE4				=	"STEP4";
	public static String STPE5				=	"STEP5";
	public static String BLADE				= 	"Blade";
	public static String LINKAGE			= 	"Linkage";
	public static String WINDSHIELD			=	"Windshield";
	public static String BUTTONEDIT			=	"Edit all data"	;
	public static String BUTTONSAVE			=	"Save all data";
	public static String BUTTONRELOAD		=	"Reload DB";
	// Step1
	public static String BLADETYPE			=	"Blade Type";
	public static String LINKAGETYPE		=	"Linkage Type";
	public static String WINDSHIELDTYPE		=	"Windshield Type";
	public static String RADIUS				=	"Radius";
	public static String X					=	"X";
	public static String Y					=	"Y";
	public static String Z					=	"Z";
	public static String EDITALLDATA		= 	"Edit all data";
	public static String SAVEALLDATA		=	"Save all data";
	// Step2
	public static String PART				=	"Part";
	public static String SWAPPINGPART_S2	= 	"Swapping part_S2";
	public static String PARTNAME_S2		=	"Part Name_S2";
	public static String TYPE_S2			=	"Type_S2";
	public static String PATH_S2			=	"Path_S2";
	public static String NONE				=	"None";
	// Step3
	public static String SWAPPINGPART_S3	= 	"Swapping Part_S3";
	public static String PARTINFORMATION	=	"Part Information";
	public static String PARTNAME_S3		=	"Part Name_S3";
	public static String TYPE_S3			= 	"Type_S3";
	public static String BULKFILEPATH		=	"Bulk File Path";
	public static String INPUTDECKPATH		=	"Inputdeck Path";
	public static String PATH_S3			=	"Path_S3";
	// Step4
	public static String SOLVING			=	"Solving";
	public static String STATUS				=	"Status";
	public static String READY				=	"Ready";
	public static String RUNNING			=	"Running";
	public static String DONE				=	"Done";
	public static String FAIL				= 	"Fail";
	public static String STOP 				= 	"Stop";
	public static String BUTTONSTARTSOLVING	=	"Strart Solving";
	//public static String BUTTONSTOPSOLVING 	= 	"Stop Solving";
	public static String BUTTONSTARTANIMATION = "Start Animation";
	
	public static String SETTING			=	"Setting";
	public static String SOLVER				=	"Solver";
	public static String INTEGRATOR			=	"Integrator";
	public static String GSTIFF				=	"GSTIFF";
	public static String WSTIFF				=	"WSTIFF";
	public static String FORMULATION		=	"Formulation";
	public static String I3					= 	"I3";
	public static String SI2				= 	"SI2";
	public static String CORRECTOR			=	"Corrector";
	public static String ORIGINAL			=	"Original";
	public static String MODIFIED			=	"Modified";
	public static String ERROR				=	"Error";
	public static String HMAX				=	"Hmax";
	public static String SIMULATION_CONDITION	= "Simnulation Condition";
	public static String NUMBEROFSTEP		=	"Number Of Step";
	public static String END_TIME			=	"End time";
	public static String EXTRA_MASS			=	"Extra Mass";
	public static String EXTRA_MASS_RATIO	=	"Extra Mass Ratio";
	public static String EXTRA_MASS_RATIO_DETAIL = "Extra Mass Ratio Detail";
	public static String ON					=	"On";
	public static String OFF				= 	"Off";
	public static String ANIMATION_CONDITION=	"Animation Condition";
	public static String NUMBER_OF_CYCLES	=	"Number Of Cycles";
	public static String START_TIME_RANGE	=	"Start time range";
	public static String END_TIME_RANGE		=	"End time range";
	public static String INCREMENT_FRAME	=	"Increment Frame";
	public static String EXPORTRESULT		=	"Export Result";
	public static String RESULTNAME			=	"Result Name";
	public static String CHECKMODELDATA		=	"Model Data CMD";
	public static String CHECKMODELDATABIN	=	"Model Data Bin";
	public static String CHECKDACFILE		=	"DAC Files";
	public static String CHECKFORCEFILE		=	"Force Files";
	public static String BUTTONEXPORTRESULTS=	"Export Results";
	
	// Step 5
	public static String Title_S5			= 	"Title_S5";
	public static String RUN				=	"Run";
	public static String PARTNAME_S5		=	"Part Name_S5";
	public static String TYPE_S5			=	"Type_S5";
	public static String PATH_S5			=	"Path_S5";
	public static String ANALYSIS_SETTING	= 	"Analysis setting";
	public static String CYCLENUMBER		=	"CycleNumber";
	public static String SOLVING_CREATE		=	"Solving_Create"; 
	
	
	
	
}