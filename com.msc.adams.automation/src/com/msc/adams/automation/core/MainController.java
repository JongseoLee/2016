package com.msc.adams.automation.core;

//import java.io.BufferedReader;
import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

//import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.adams.automation.core.database.DatabaseStep1;
import com.msc.adams.automation.core.database.DatabaseStep2;
import com.msc.adams.automation.core.database.DatabaseStep3;
import com.msc.adams.automation.core.database.DatabaseStep4;
import com.msc.adams.automation.core.database.DatabaseStep5;
//import com.msc.adams.automation.core.database.WriteDBFile;
import com.msc.adams.automation.customTable.TableEditorInfo;
import com.msc.adams.automation.customTable.TableEditorInfo_FatSolving;
import com.msc.adams.automation.datas.Blade;
import com.msc.adams.automation.datas.Linkage;
import com.msc.adams.automation.datas.Part;
import com.msc.adams.automation.datas.Part_FatSolving;
import com.msc.adams.automation.datas.SwappingPart;
import com.msc.adams.automation.datas.Windshield;
//import com.msc.adams.automation.datas.factory.BladeFactory;
//import com.msc.adams.automation.datas.factory.LinkageFactory;
//import com.msc.adams.automation.datas.factory.PartFactory;
import com.msc.adams.automation.dialog.AddBulkDlg;
import com.msc.adams.automation.dialog.NewDlg;
import com.msc.adams.automation.dialog.NextStep;
import com.msc.adams.automation.dialog.OpenDlg;
import com.msc.adams.automation.dialog.PreferenceDlg;
import com.msc.adams.automation.dialog.SaveDlg;
import com.msc.adams.automation.handler.TextFocusListener;
import com.msc.adams.automation.image.ImagePath;
import com.msc.adams.automation.inputdeck.CreateInputdeckNASForMNF;
import com.msc.adams.automation.inputdeck.CreateInputdeckOPTForMNF;
import com.msc.adams.automation.inputdeck.HMSETObj;
import com.msc.adams.automation.inputdeck.SETObj;
import com.msc.adams.automation.msm.MSMSolving;
import com.msc.adams.automation.msr.MSRSolving;
import com.msc.adams.automation.provider.content.BladeDataContentProvider;
import com.msc.adams.automation.provider.content.LinkageDataContentProvider;
import com.msc.adams.automation.provider.content.PartDataContentProvider;
import com.msc.adams.automation.provider.content.SwappingPartDataContentProvider;
import com.msc.adams.automation.provider.content.WindshieldDataContentProvider;
import com.msc.adams.automation.provider.label.BladeDataLabelProvider;
import com.msc.adams.automation.provider.label.LinkageDataLabelProvider;
import com.msc.adams.automation.provider.label.PartDataLabelProvider;
import com.msc.adams.automation.provider.label.SwappingPartDataLabelProvider;
import com.msc.adams.automation.provider.label.WindshieldDataLabelProvider;
import com.msc.adams.commandServer.CMDFileList;
import com.msc.adams.commandServer.CheckAdamsStart;
import com.msc.adams.commandServer.ClientDemon;
import com.msc.adams.commandServer.Job;
/*
import com.msc.adams.commandServer.Step1_cmd1;
import com.msc.adams.commandServer.Step1_cmd1_2_Geo;
import com.msc.adams.commandServer.Step1_cmd1_2_Sph;
import com.msc.adams.commandServer.Step2_3_cmd3;
import com.msc.adams.commandServer.Step2_3_cmd3_Back;
import com.msc.adams.commandServer.Step4_cmd4_Abort;
import com.msc.adams.commandServer.Step4_cmd4_Animation;
import com.msc.adams.commandServer.Step4_cmd4_AssemModel;
import com.msc.adams.commandServer.Step4_cmd4_AssemModelBin;
import com.msc.adams.commandServer.Step4_cmd4_DAC;
import com.msc.adams.commandServer.Step4_cmd4_Force;
import com.msc.adams.commandServer.Step4_cmd4_NodeInformation;
import com.msc.adams.commandServer.Step4_cmd4_Simulation;
// */
import com.msc.adams.commandServer.StepAll_cmd_quit;
import com.msc.adams.commandServer.ModuleTandem.Step1_cmd1_2_Geo_MT;
import com.msc.adams.commandServer.ModuleTandem.Step1_cmd1_2_Sph_MT;
import com.msc.adams.commandServer.ModuleTandem.Step1_cmd1_MT;
import com.msc.adams.commandServer.ModuleTandem.Step2_3_cmd3_Back_MT;
import com.msc.adams.commandServer.ModuleTandem.Step2_3_cmd3_MT;
import com.msc.adams.commandServer.ModuleTandem.Step4_cmd4_Animation_MT;
import com.msc.adams.commandServer.ModuleTandem.Step4_cmd4_AssemModelBin_MT;
import com.msc.adams.commandServer.ModuleTandem.Step4_cmd4_AssemModel_MT;
import com.msc.adams.commandServer.ModuleTandem.Step4_cmd4_BladePosition_MT;
import com.msc.adams.commandServer.ModuleTandem.Step4_cmd4_DAC_MT;
import com.msc.adams.commandServer.ModuleTandem.Step4_cmd4_Force_MT;
import com.msc.adams.commandServer.ModuleTandem.Step4_cmd4_NodeInformation_MT;
import com.msc.adams.commandServer.ModuleTandem.Step4_cmd4_Simulation_MT;
import com.msc.adams.commandServer.SerialOpposed.Step1_cmd1_2_Geo_SO;
import com.msc.adams.commandServer.SerialOpposed.Step1_cmd1_2_Sph_SO;
import com.msc.adams.commandServer.SerialOpposed.Step1_cmd1_SO;
import com.msc.adams.commandServer.SerialOpposed.Step2_3_cmd3_Back_SO;
import com.msc.adams.commandServer.SerialOpposed.Step2_3_cmd3_SO;
import com.msc.adams.commandServer.SerialOpposed.Step4_cmd4_Animation_SO;
import com.msc.adams.commandServer.SerialOpposed.Step4_cmd4_AssemModelBin_SO;
import com.msc.adams.commandServer.SerialOpposed.Step4_cmd4_AssemModel_SO;
import com.msc.adams.commandServer.SerialOpposed.Step4_cmd4_BladePosition_SO;
import com.msc.adams.commandServer.SerialOpposed.Step4_cmd4_DAC_SO;
import com.msc.adams.commandServer.SerialOpposed.Step4_cmd4_Force_SO;
import com.msc.adams.commandServer.SerialOpposed.Step4_cmd4_NodeInformation_SO;
import com.msc.adams.commandServer.SerialOpposed.Step4_cmd4_Simulation_SO;
import com.msc.adams.commandServer.SerialParallel.Step1_cmd1_2_Geo_SP;
import com.msc.adams.commandServer.SerialParallel.Step1_cmd1_2_Sph_SP;
import com.msc.adams.commandServer.SerialParallel.Step1_cmd1_SP;
import com.msc.adams.commandServer.SerialParallel.Step2_3_cmd3_Back_SP;
import com.msc.adams.commandServer.SerialParallel.Step2_3_cmd3_SP;
import com.msc.adams.commandServer.SerialParallel.Step4_cmd4_Animation_SP;
import com.msc.adams.commandServer.SerialParallel.Step4_cmd4_AssemModelBin_SP;
import com.msc.adams.commandServer.SerialParallel.Step4_cmd4_AssemModel_SP;
import com.msc.adams.commandServer.SerialParallel.Step4_cmd4_BladePosition_SP;
import com.msc.adams.commandServer.SerialParallel.Step4_cmd4_DAC_SP;
import com.msc.adams.commandServer.SerialParallel.Step4_cmd4_Force_SP;
import com.msc.adams.commandServer.SerialParallel.Step4_cmd4_NodeInformation_SP;
import com.msc.adams.commandServer.SerialParallel.Step4_cmd4_Simulation_SP;
import com.msc.adams.commandServer.SerialTandem.Step1_cmd1_2_Geo_ST;
import com.msc.adams.commandServer.SerialTandem.Step1_cmd1_2_Sph_ST;
import com.msc.adams.commandServer.SerialTandem.Step1_cmd1_ST;
import com.msc.adams.commandServer.SerialTandem.Step2_3_cmd3_Back_ST;
import com.msc.adams.commandServer.SerialTandem.Step2_3_cmd3_ST;
import com.msc.adams.commandServer.SerialTandem.Step4_cmd4_Animation_ST;
import com.msc.adams.commandServer.SerialTandem.Step4_cmd4_AssemModelBin_ST;
import com.msc.adams.commandServer.SerialTandem.Step4_cmd4_AssemModel_ST;
import com.msc.adams.commandServer.SerialTandem.Step4_cmd4_BladePosition_ST;
import com.msc.adams.commandServer.SerialTandem.Step4_cmd4_DAC_ST;
import com.msc.adams.commandServer.SerialTandem.Step4_cmd4_Force_ST;
import com.msc.adams.commandServer.SerialTandem.Step4_cmd4_NodeInformation_ST;
import com.msc.adams.commandServer.SerialTandem.Step4_cmd4_Simulation_ST;
import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.parser.ParserDefault;
import com.msc.util.myUtil;

public class MainController {
	private static MainController instance = new MainController();
	public static MainController getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}
	
	
	private Mediator med = Mediator.getInstance();
	private Job jobObj = Job.getInstance();

	
	private String AppPath = System.getProperty("user.dir");
	public String getAppPath() {
		return AppPath;
	}
	////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////
	//
	// Global variable
	//
	private ImagePath imgP;
	private ClientDemon clientDemon = null;
	// Process Database
	private DatabaseStep1 DBStep1;
	private DatabaseStep2 DBStep2;
	private DatabaseStep3 DBStep3;
	private DatabaseStep4 DBStep4;
	private DatabaseStep5 DBStep5;
	// Preference 
	private String ExcelPath;
	private String DatabasePath;
	private String AdamsPath;
	//C:\ProgramData\Microsoft\Windows\Start Menu\Programs\Adams 2016 => .lnk 위치
	//private String AdamsExePath;
	//private String AdamsViewOption = " aview ru-st i";
	//private String SolverType;
	private String EIGRL_ND;
	private String HyperWorksPath;
	private String FemfatPath;
	
	//private static final String NastranType = "Nastran";
	//private static final String AbaqusType = "Abaqus";
	private static final String ExcelFile_T = "ExcelFile";
	private static final String DatabaseFolder_T = "DatabaseFolder"; 
	private static final String AdamsFolder_T = "AdamsFolder";
	//private static final String SolverType_T = "SolverTyepe"; 
	private static final String EIGRL_ND_T = "EIGRL_ND";
	private static final String HyperWorksFolder_T = "HyperWorksFolder";
	private static final String FemfatFolder_T = "FemfatFolder";
	//private static final String ResultEmpty = "EMPTY";
	
	//Export Reulst Folder
	private String bulkDataForMNF_step2 = Part.Path_Empty;
	
	
	
	//Common
	private String ProjectName;
	private String Workspace;
	private String currentStep;		// Step1 ~ Step5
	public static final String STEP1 = "STEP1";
	public static final String STEP2 = "STEP2";
	public static final String STEP3 = "STEP3";
	public static final String STEP4 = "STEP4";
	public static final String STEP5 = "STEP5";
	
	private boolean isCompletedStep1 = false;
	private boolean isCompletedStep2 = false;
	private boolean isCompletedStep3 = false;
	private boolean isCompletedStep4 = false;
	private boolean isCompletedStep4_2=false;
	private boolean isCompletedStep5 = false;
	
	private boolean isReadyStep1 = false;
	private boolean isReadyStep2 = false;
	private boolean isReadyStep3 = false;
	private boolean isReadyStep4 = false;
	private boolean isReadyStep5 = false;
	
	private boolean isBackStep2_3 = false;
	private DatabaseStep3 DBStep3_back;
	
	private MessageWindow MsgWindow;
	
	//
	//
	////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////
	
	


	public MainController() {
		// TODO Auto-generated constructor stub
		
		// Datbase 선언
		this.DBStep1 = new DatabaseStep1();
		this.DBStep2 = new DatabaseStep2();
		this.DBStep3 = new DatabaseStep3();
		this.DBStep4 = new DatabaseStep4();
		this.DBStep5 = new DatabaseStep5();
		
		// 시작시 초기 화면 Step 초기화 
		this.currentStep = STEP1;
		
		// 결과 출력 종류 이름 
		this.DBStep4.setExportResultName(DatabaseFolderName.Base);
		
		// Message Winodw 초기화
		this.MsgWindow =new MessageWindow();
	}
	

	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	// 
	//
	//
	//  Common Function
	//
	//
	//
	public void addMsgWindow(String msg, String type){
		String allMsg = this.MsgWindow.addMessage(msg,type);
		med.getTextMessageWindow().setText(allMsg);
		med.getTextMessageWindow().setSelection(allMsg.length());
	}
	
	public void ReadPreferenceFile(){
		try{
			ArrayList<String> fileDataList = new ArrayList<String>();
			String preferenceFilePath = myUtil.setPath(myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Config),"Preference.ini");
			Reader reader = new Reader(preferenceFilePath);
			reader.running();
			fileDataList = reader.getFileDataList();
			
			for(String line : fileDataList){
				if(line.contains("//")){
				}else{
					ArrayList<String> temp = new ArrayList<String>();
					temp = ParserDefault.splitLineData(line, "=");
					if(temp.get(0).equals(ExcelFile_T)){
						this.ExcelPath = temp.get(1);
					}else if(temp.get(0).equals(DatabaseFolder_T)){
						this.DatabasePath = temp.get(1);
					}else if(temp.get(0).equals(AdamsFolder_T)){
						this.AdamsPath = temp.get(1);
					}else if(temp.get(0).equals(HyperWorksFolder_T)){
						this.HyperWorksPath = temp.get(1);
					}else if(temp.get(0).equals(FemfatFolder_T)){
						this.FemfatPath = temp.get(1);
					}else if(temp.get(0).equals(EIGRL_ND_T)){
						this.EIGRL_ND = temp.get(1);
					}
				}
			}
		}catch(Exception e){
			this.addMsgWindow("Preference file is not correct!!"+"\n"+e.getMessage(), MessageWindow.Error);
			JOptionPane.showMessageDialog(null, "Preference file is not correct!!","Error - Preference",JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
	}
	
	public void DisableAllComponent(){
		med.getCompositeTop().setEnabled(false);
		med.getCompositeBottom().setEnabled(false);
		med.getBtnEditAllData().setEnabled(false);
		med.getBtnSaveAllData().setEnabled(false);
		med.getBtnReloadDb().setEnabled(false);
		med.getTextMessageWindow().setEnabled(false);
	}
	
	public void EnableAllComponent(){
		med.getCompositeTop().setEnabled(true);
		med.getCompositeBottom().setEnabled(true);
		med.getBtnEditAllData().setEnabled(true);
		med.getBtnSaveAllData().setEnabled(true);
		med.getBtnReloadDb().setEnabled(true);
		med.getTextMessageWindow().setEnabled(true);
		
		med.getLblStep1().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		this.SetupComponent(this.currentStep);
		this.isReadyStep1 = true;
	}
	
	public void SetupComponent(String step){
		if(step.equals(STEP1)){
			med.getCompositeStep1().setEnabled(true);
			med.getCompositeStep2().setEnabled(false);
			med.getCompositeStep3().setEnabled(false);
			med.getCompositeStep4().setEnabled(false);
			med.getCompositeStep5().setEnabled(false);
			med.getBtnReloadDb().setEnabled(true);
			med.getBtnReloadDb().setVisible(true);
			med.getBtnSaveAllData().setEnabled(true);
			med.getBtnSaveAllData().setVisible(true);
		}else if(step.equals(STEP2)){
			med.getCompositeStep1().setEnabled(false);
			med.getCompositeStep2().setEnabled(true);
			med.getCompositeStep3().setEnabled(false);
			med.getCompositeStep4().setEnabled(false);
			med.getCompositeStep5().setEnabled(false);
			med.getBtnReloadDb().setEnabled(false);
			med.getBtnReloadDb().setVisible(false);
			med.getBtnSaveAllData().setEnabled(true);
			med.getBtnSaveAllData().setVisible(true);
		}else if(step.equals(STEP3)){
			med.getCompositeStep1().setEnabled(false);
			med.getCompositeStep2().setEnabled(false);
			med.getCompositeStep3().setEnabled(true);
			med.getCompositeStep4().setEnabled(false);
			med.getCompositeStep5().setEnabled(false);
			med.getBtnReloadDb().setEnabled(false); 
			med.getBtnReloadDb().setVisible(false);
			med.getBtnSaveAllData().setVisible(true);
		}else if(step.equals(STEP4)){
			med.getCompositeStep1().setEnabled(false);
			med.getCompositeStep2().setEnabled(false);
			med.getCompositeStep3().setEnabled(false);
			med.getCompositeStep4().setEnabled(true);
			med.getCompositeStep5().setEnabled(false);
			med.getBtnReloadDb().setEnabled(false);
			med.getBtnReloadDb().setVisible(false);
			med.getBtnSaveAllData().setEnabled(true);
			med.getBtnSaveAllData().setVisible(true);
		}else if(step.equals(STEP5)){
			med.getCompositeStep1().setEnabled(false);
			med.getCompositeStep2().setEnabled(false);
			med.getCompositeStep3().setEnabled(false);
			med.getCompositeStep4().setEnabled(false);
			med.getCompositeStep5().setEnabled(true);
			med.getBtnReloadDb().setEnabled(false);
			med.getBtnReloadDb().setVisible(false);
			med.getBtnSaveAllData().setEnabled(false);
			med.getBtnSaveAllData().setVisible(false);
		}
	}
	
	private void createInputdeckForMNF(){
		try{
			for(SwappingPart obj : this.DBStep3.getSwappingPartList()){
				if(obj.getType().equals(Part.Type_NAS)){
					CreateInputdeckNASForMNF createInputObj = new CreateInputdeckNASForMNF();
					
					createInputObj.running(obj.getBulkPath(),obj.getPartName());
					obj.setInputdeckPath(createInputObj.getInputdeckPath());
					obj.setBulkDataList(createInputObj.getBulkDataList());
					
					Thread th = new Thread(createInputObj);
					th.start();
				}else if(obj.getType().equals(Part.Type_OPT)){
					CreateInputdeckOPTForMNF createInputObj = new CreateInputdeckOPTForMNF();
					
					createInputObj.running(obj.getBulkPath(),obj.getPartName());
					obj.setInputdeckPath(createInputObj.getInputdeckPath());
					obj.setBulkDataList(createInputObj.getBulkDataList());
					
					Thread th = new Thread(createInputObj);
					th.start();
				}
			}	
		}catch(Exception e){
			e.printStackTrace();
			this.addMsgWindow("Create Inputdeck for MNF"+"\n"+e.getMessage(), MessageWindow.Error);
		}
	}
	
	
	public void LoadingStep1(){
		this.DBStep1 = new DatabaseStep1();
		this.LoadingAppDataBase_Blade();
		this.LoadingAppDataBase_Linkage();
		this.LoadingAppDatabase_Windshield();
		this.LoadingStep1List_Blade();
		this.LoadingStep1List_Linkage();
		this.LoadingStep1List_Windshield();
		
		med.getListViewerBladeDatabase().refresh();
		med.getListViewerLinkageDatabase().refresh();
		med.getListViewerWindShieldDatabase().refresh();
		
		med.getListViewerBladeDatabase().getList().deselectAll();
		med.getListViewerLinkageDatabase().getList().deselectAll();
		med.getListViewerWindShieldDatabase().getList().deselectAll();
	}
	
	// Load appDatabase Blade / Linkage xls Files
	public void LoadingAppDataBase_Blade(){
		// 1. appDatabase 접근 ~/appDatabase/Blade/Type1~Type4z
		String appDatabasePath = this.DatabasePath;
		String BladeDatabasePath = myUtil.setPath(appDatabasePath, DatabaseFolderName.Blade);
		
		// 2. 각각의 데이터베이스 조회
		// 
		String Symmetric_Same_Basic = myUtil.setPath(BladeDatabasePath, DatabaseFolderName.Symmetric_Same_Basic);
		String Symmetric_Different_Basic = myUtil.setPath(BladeDatabasePath, DatabaseFolderName.Symmetric_Different_Basic);
		String NonSymmetric_Outer_Basic = myUtil.setPath(BladeDatabasePath, DatabaseFolderName.NonSymmetric_Outer_Basic);
		String NonSymmetric_Inner_Basic = myUtil.setPath(BladeDatabasePath, DatabaseFolderName.NonSymmetric_Inner_Basic);
		
		
		try{
			// Type1 - Symmetric_Same_Basic
			for(File f : myUtil.getDirFileList(Symmetric_Same_Basic)){
				Blade obj = new Blade();
				obj.InitBladeData(f,Blade.TYPE_SYMMETRIC_SAME_BASIC);
				this.DBStep1.getSymmetricSameBasicList().add(obj);
			}
			// Type1 - Symmetric_Different_Basic
			for(File f : myUtil.getDirFileList(Symmetric_Different_Basic)){
				Blade obj = new Blade();
				obj.InitBladeData(f,Blade.TYPE_SYMMETRIC_DIFFERENT_BASIC);
				this.DBStep1.getSymmetricDifferentBasicList().add(obj);
			}
			
			//Type3 - NonSymmetric_Outer_Basic
			for(File f : myUtil.getDirFileList(NonSymmetric_Outer_Basic)){
				Blade obj = new Blade();
				obj.InitBladeData(f,Blade.TYPE_NONSYMMETRIC_OUTER_BASIC);
				this.DBStep1.getNonSymmetricOuterBasicList().add(obj);
			}
			//Type4 - NonSymmetric_Inner_Basic
			for(File f : myUtil.getDirFileList(NonSymmetric_Inner_Basic)){
				Blade obj = new Blade();
				obj.InitBladeData(f,Blade.TYPE_NONSYMMETRIC_INNNER_BASIC);
				this.DBStep1.getNonSymmetricInnerBasicList().add(obj);
			}
			
		}catch(Exception e){
			//System.out.println("Error - LoadingAppDataBase_Blade");
			this.addMsgWindow("Loading fail - Blade DataBase."+"\n"+e.getMessage(), MessageWindow.Error);
		}
		
	}
	
	public void LoadingAppDataBase_Linkage(){
		// LoadingAppDataBase_Blade() 와 동일
		String appDatabasePath = this.DatabasePath;
		String LinkageDatabasePath = myUtil.setPath(appDatabasePath, DatabaseFolderName.Linkage);
		
		String SerialParallelPath = myUtil.setPath(LinkageDatabasePath, DatabaseFolderName.SerialParallel);
		String SerialOpposedPath = myUtil.setPath(LinkageDatabasePath, DatabaseFolderName.SerialOpposed);
		String ModuleTandemPath = myUtil.setPath(LinkageDatabasePath, DatabaseFolderName.ModuleTandem);
		String SerialTandemPath = myUtil.setPath(LinkageDatabasePath, DatabaseFolderName.SerialTandem);
		
		try{
			// Type1
			for(File f : myUtil.getDirFileList(SerialParallelPath)){
				Linkage obj = new Linkage();
				obj.InitLinkageData(f,Linkage.TYPE_SERIALPARALLEL);
				this.DBStep1.getSerialParallelList().add(obj);
			}
			//Type2
			for(File f : myUtil.getDirFileList(SerialOpposedPath)){
				Linkage obj = new Linkage();
				obj.InitLinkageData(f,Linkage.TYPE_SERIALOPPOSED);
				this.DBStep1.getSerialOpposedList().add(obj);
			}
			//Type3
			for(File f : myUtil.getDirFileList(ModuleTandemPath)){
				Linkage obj = new Linkage();
				obj.InitLinkageData(f,Linkage.TYPE_MODULETANDEM);
				this.DBStep1.getModuleTandemList().add(obj);
			}
			//Type4
			for(File f : myUtil.getDirFileList(SerialTandemPath)){
				Linkage obj = new Linkage();
				obj.InitLinkageData(f,Linkage.TYPE_SERIALTANDEM);
				this.DBStep1.getSerialTandemList().add(obj);
			}	
		}catch(Exception e){
			//System.out.println("Error - LoadingAppDataBase_Linkage");
			this.addMsgWindow("Loading fail - Linkage DataBase."+"\n"+e.getMessage(), MessageWindow.Error);
		}
		
	}
	public void LoadingAppDatabase_Windshield(){
		String appDatabasePath = this.DatabasePath;
		String WindshieldDatabasePath = myUtil.setPath(appDatabasePath, DatabaseFolderName.Windshield);
		
		try{
			for(File f : myUtil.getDirFileList(WindshieldDatabasePath)){
				Windshield obj = new Windshield();
				obj.InitWindshield_typeGeometry(f, Windshield.TYPE_GEOMETRY);
				this.DBStep1.getWindshieldList().add(obj);
			}
		}catch(Exception e){
			//System.out.println("Error - LoadingAppDatabase_Windshield");
			this.addMsgWindow("Loading fail - Windshield DataBase."+"\n"+e.getMessage(), MessageWindow.Error);
		}
	}
	
	
	// List(Blade / Linkage 타입 1을 기본으로 해당 정보  List에 Load
	public void LoadingStep1List_Blade(){
		try{
			//1. combo Type1으로 셋팅
			med.getComboBladeType().select(0);
			this.DBStep1.setBladeType(Blade.TYPE_SYMMETRIC_SAME_BASIC);
			this.LoadingStep1BladeImage(Blade.TYPE_SYMMETRIC_SAME_BASIC);
			//2. List 데이터 로드
			// -> 각각의 type 별로 ~factory 초기화
			this.DBStep1.getBladeFactorySymmetricSameBasicObjImpl().setAllData(this.DBStep1.getSymmetricSameBasicList());
			this.DBStep1.getBladeFactorySymmetricDifferentBasicObjImpl().setAllData(this.DBStep1.getSymmetricDifferentBasicList());
			this.DBStep1.getBladeFactoryNonSymmetricOuterBasicObjImpl().setAllData(this.DBStep1.getNonSymmetricOuterBasicList());
			this.DBStep1.getBladeFactoryNonSymmetricInnerBasicObjImpl().setAllData(this.DBStep1.getNonSymmetricInnerBasicList());

			// ->Type1 으로 리스트 데이터 초기화
			med.getListViewerBladeDatabase().setContentProvider(new BladeDataContentProvider());
			med.getListViewerBladeDatabase().setLabelProvider(new BladeDataLabelProvider());
			med.getListViewerBladeDatabase().setInput(this.DBStep1.getBladeFactorySymmetricSameBasicObjImpl());
			// 리스트의 데이터가 업데이트 될시 사용
			//med.getListViewerBladeDatabase().refresh();
		}catch(Exception e){
			//System.out.println("Error - LoadingStep1List_Blade");
			this.addMsgWindow("Loading Blade List"+"\n"+e.getMessage(), MessageWindow.Error);
			//e.printStackTrace();
		}
		
	}
	
	public void LoadingStep1List_Linkage(){
		try{
			// LoadingStep1List_Blade와 동일
			med.getComboLinkageType().select(0);
			this.DBStep1.setLinkageType(Linkage.TYPE_SERIALPARALLEL);
			this.LoadingStep1LinkageImage(Linkage.TYPE_SERIALPARALLEL);
			
			this.DBStep1.getLinkageFactorySerialParallelObjImpl().setAllData(this.DBStep1.getSerialParallelList());
			this.DBStep1.getLinkageFactorySerialOpposedObjImpl().setAllData(this.DBStep1.getSerialOpposedList());
			this.DBStep1.getLinkageFactoryModuleTandemObjImpl().setAllData(this.DBStep1.getModuleTandemList());
			this.DBStep1.getLinkageFactorySerialTandemObjImpl().setAllData(this.DBStep1.getSerialTandemList());

			med.getListViewerLinkageDatabase().setContentProvider(new LinkageDataContentProvider());
			med.getListViewerLinkageDatabase().setLabelProvider(new LinkageDataLabelProvider());
			med.getListViewerLinkageDatabase().setInput(this.DBStep1.getLinkageFactorySerialParallelObjImpl());
			//med.getListViewerLinkageDatabase().refresh();
		}catch(Exception e){
			//System.out.println("Error - LoadingStep1List_Linkage");
			this.addMsgWindow("Loading Linkage List"+"\n"+e.getMessage(), MessageWindow.Error);
			//e.printStackTrace();
		}

	}
	
	public void LoadingStep1List_Windshield(){
		try{
			med.getComboWindshieldType().select(0);
			med.getListWindShieldDatabase().setEnabled(false);
			
			this.DBStep1.setWindshieldType(Windshield.TYPE_SPHERE);
			this.DBStep1.setSelectedWindshieldDBName(Windshield.DBNAME_EMPTY);
			this.DBStep1.setSelectedWindshieldDBPath(Windshield.PATH_EMPTY);

			Windshield obj = new Windshield();
			String r = med.getTextRadiusValue().getText().trim();
			String x = med.getTextXValue().getText().trim();
			String y = med.getTextYValue().getText().trim();
			String z = med.getTextZValue().getText().trim();
			obj.InitWindshield_typeSphere(r, x, y, z);
			obj.setType(Windshield.TYPE_SPHERE);
			obj.setDbName(Windshield.DBNAME_EMPTY);
			obj.setDbPath(Windshield.PATH_EMPTY);
			this.DBStep1.setSelectedWindshieldObj(obj);
			this.DBStep1.setRadius(r);
			this.DBStep1.setX(x);
			this.DBStep1.setY(y);
			this.DBStep1.setZ(z);
			this.DBStep1.getWindshieldFactoryObjImpl().setAllData(this.DBStep1.getWindshieldList());
			med.getListViewerWindShieldDatabase().setContentProvider(new WindshieldDataContentProvider());
			med.getListViewerWindShieldDatabase().setLabelProvider(new WindshieldDataLabelProvider());
			med.getListViewerWindShieldDatabase().setInput(this.DBStep1.getWindshieldFactoryObjImpl());
			
		}catch(Exception e){
			//System.out.println("Error - LoadingStep1List_Windshield");
			this.addMsgWindow("Loading Windshield data."+"\n"+e.getMessage(), MessageWindow.Error);
		}
	}
	
	private void LoadingStep1BladeImage(String type){
		this.imgP = ImagePath.getInstatnce();
		Image imgBlade = null;
		
		if(type.equals(Blade.TYPE_SYMMETRIC_SAME_BASIC)){
			imgBlade = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.BladeSymmetric_Same_Basic, "Symmetry_Same_Basic"));
		}else if(type.equals(Blade.TYPE_SYMMETRIC_DIFFERENT_BASIC)){
			imgBlade = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.BladeSymmetric_Different_Basic, "Symmetry_Different_Basic"));
		}else if(type.equals(Blade.TYPE_NONSYMMETRIC_OUTER_BASIC)){
			imgBlade = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.BladeNonSymmetric_Outer_Basic,"NonSymmetric_Outer_Basic"));
		}else if(type.equals(Blade.TYPE_NONSYMMETRIC_INNNER_BASIC)){
			imgBlade = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.BladeNonSymmetric_Inner_Basic, "NonSymmetric_Inner_Basic"));
		}
	
		if(imgBlade == null){
			imgBlade = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.BladeSymmetric_Same_Basic, "0"));
		}
		//med.getLblImageBlade().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		med.getLblImageBlade().setImage(imgBlade);
		med.getLblImageBlade().pack();
	}
	
	private void LoadingStep1LinkageImage(String type){
		Image imgLinkage = null;
		
		if(type.equals(Linkage.TYPE_SERIALPARALLEL)){
			imgLinkage = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.LinkageSerialParallel, "SerialParallel"));
		}else if(type.equals(Linkage.TYPE_SERIALOPPOSED)){
			imgLinkage = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.LinkageSerialOpposed, "SerialOpposed"));
		}else if(type.equals(Linkage.TYPE_MODULETANDEM)){
			imgLinkage = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.LinkageModuleTandem, "ModuleTandem"));			
		}else if(type.equals(Linkage.TYPE_SERIALTANDEM)){
			imgLinkage = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.LinkageSerialTandem, "SerialTandem"));
		}
		
		if(imgLinkage == null){
			imgLinkage = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.LinkageSerialParallel, "0"));
		}
		//med.getLblImageLinkage().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		med.getLblImageLinkage().setImage(imgLinkage);
		med.getLblImageLinkage().pack();
		
	}

	public void LoadingStep2(){
		if(this.isReadyStep2){
			TableItem [] items = med.getTableSwappingPart().getItems();
			boolean bDeleted = false;
			for(int i=items.length-1 ; i>=0 ; i--){
				Part obj = this.DBStep2.getSelectedPartList().get(i);
				this.DBStep2.getTableRowList().get(i).disposeControls();
				this.DBStep2.removeTableRowList(obj.getTableEditorInfoObj());
				items[i].dispose();
				//List에 추가 및 업데이트
				this.DBStep2.getPartFactoryObjImpl().addAllDataObj(obj);
				med.getListViewerPart().refresh();
				//Table에서 삭제
				this.DBStep2.getSelectedPartList().get(i).setSelected(false);
				this.DBStep2.getSelectedPartList().get(i).setPath(Part.Path_Empty);
				this.DBStep2.getSelectedPartList().get(i).setRenamePath(Part.Path_Empty);
				this.DBStep2.getSelectedPartList().get(i).setType(Part.Type_NAS);
				this.DBStep2.removeSelectedPartList(obj);
			}		
			
			this.DBStep2 = null;
			this.DBStep2 = new DatabaseStep2();
			this.LoadingAppDataBase_Part();
			this.LoadingStep2List_Part();
			
			med.getLblStep1().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep5().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			
			this.isCompletedStep2 = false;
			this.isCompletedStep3 = false;
			this.isCompletedStep4 = false;
			this.isCompletedStep5 = false;
			
			this.isReadyStep3 = false;
			this.isReadyStep4 = false;
			this.isReadyStep5 = false;
			
			if(this.isBackStep2_3){
				this.DBStep3_back = this.DBStep3;
			}
			this.DBStep3 = null;
			this.DBStep3 = new DatabaseStep3();
			this.DBStep3.setChanged(this.isBackStep2_3);
			med.getLblPartNameValue().setText("");
			med.getLblSwappingTypeValue().setText("");
			med.getTextMnfFilePath().setText("");
			
		}else {
			this.LoadingAppDataBase_Part();
			this.LoadingStep2List_Part();
		}
		this.isReadyStep2 = true;
	}
	
	private void LoadingAppDataBase_Part(){
		//String appDatabasePath = myUtil.setPath(this.AppPath, "msck_Database");
		String appDatabasePath = this.DatabasePath;
		String PartDatabasePath = myUtil.setPath(appDatabasePath, "Part");
		String PartType = Linkage.TYPE_SERIALPARALLEL;
		if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALPARALLEL)){
			PartType = Linkage.TYPE_SERIALPARALLEL;
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALOPPOSED)){
			PartType = Linkage.TYPE_SERIALOPPOSED;
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_MODULETANDEM)){
			PartType = Linkage.TYPE_MODULETANDEM;
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALTANDEM)){
			PartType = Linkage.TYPE_SERIALTANDEM;
		}
		
		String PartTypeDataBasePath = myUtil.setPath(PartDatabasePath, PartType);
		String PartDataPath = myUtil.setPath(PartTypeDataBasePath, "PartData.ini");
		
		this.DBStep2.setPartDataPath(PartDataPath);
		try{
			Reader reader = new Reader(PartDataPath);
			reader.running();
			
			for(String line : reader.getFileDataList()){
				Part obj = new Part();
				obj.setPartName(line);
				this.DBStep2.addPartList(obj);
			}
		}catch(Exception e){
			//System.out.println("Error - LoadingAppDataBase_Part");
			this.addMsgWindow("Loading fail - Part Database"+"\n"+e.getMessage(), MessageWindow.Error);
		}
	}
	
	private void LoadingStep2List_Part(){
		try{
			this.DBStep2.getPartFactoryObjImpl().setAllData(this.DBStep2.getPartList());
			med.getListViewerPart().setContentProvider(new PartDataContentProvider());
			med.getListViewerPart().setLabelProvider(new PartDataLabelProvider());
			med.getListViewerPart().setInput(this.DBStep2.getPartFactoryObjImpl());
			med.getListViewerPart().refresh();
		}catch(Exception e){
			//System.out.println("Error - LoadingStep2List_Part");
			this.addMsgWindow("Loading Part List."+"\n"+e.getMessage(), MessageWindow.Error);
		}
	}
	
	private void LoadingStep2PartImage(String partName){
		String type = this.DBStep1.getLinkageType(); 
		Image imgPart = null;
		if(type.equals(Linkage.TYPE_SERIALPARALLEL)){
			imgPart = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.PartSerialParallel, partName));
		}else if(type.equals(Linkage.TYPE_SERIALOPPOSED)){
			imgPart = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.PartSerialOpposed, partName));
		}else if(type.equals(Linkage.TYPE_MODULETANDEM)){
			imgPart = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.PartModuleTandem, partName));			
		}else if(type.equals(Linkage.TYPE_SERIALTANDEM)){
			imgPart = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.PartSerialTandem, partName));
		}
		
		if(imgPart == null){
			imgPart = new Image(med.getParentView().getDisplay(),imgP.getImagePath(ImagePath.PartSerialParallel, "0"));
		}
		//med.getLblImageLinkage().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		med.getLblImagePart().setImage(imgPart);
		med.getLblImagePart().pack();
		
	}
	
	public void LoadingStep3(){
		this.DBStep3_back = this.DBStep3;
		this.DBStep3 = null;
		this.DBStep3 = new DatabaseStep3();
		this.DBStep3.setChanged(this.isBackStep2_3);
		this.LoadingStep3List_SwappingPart();
		
		med.getLblPartNameValue().setText("            ");
		med.getLblSwappingTypeValue().setText("          ");
		med.getTextBulkFilePath().setText("         ");
		med.getTextInputdeckPath().setText("         ");
		med.getTextMnfFilePath().setText("        ");
		this.isReadyStep3 = true;
		
	}
	
	private void LoadingStep3List_SwappingPart(){
		
		try{
			for(Part obj : this.DBStep2.getSelectedPartList()){
				SwappingPart swappingObj = new SwappingPart();
				swappingObj.setPartName(obj.getPartName());
				swappingObj.setType(obj.getType());
				if(obj.getType().equals(Part.Type_NAS)){
					swappingObj.setOriginalType(Part.Type_NAS);
					swappingObj.setBulkPath(obj.getBulkPath());
					swappingObj.setBulkRenamePath(obj.getbulkRenamePath());
					swappingObj.setPath(obj.getPath());
					swappingObj.setRenamePath(obj.getRenamePath());
					swappingObj.setInputdeckPath(Part.Path_Empty);
					swappingObj.setOp2Path(Part.Path_Empty);
					swappingObj.setOnlyBulkFilePath(obj.getOnlyBulkFilePath());
				}else if(obj.getType().equals(Part.Type_MNF)){
					swappingObj.setOriginalType(Part.Type_MNF);
					swappingObj.setBulkPath(obj.getBulkPath());
					swappingObj.setBulkRenamePath(obj.getbulkRenamePath());
					swappingObj.setPath(obj.getPath());
					swappingObj.setRenamePath(obj.getRenamePath());
					swappingObj.setInputdeckPath(Part.Path_Empty);
					swappingObj.setOp2Path(Part.Path_Empty);
					swappingObj.setOnlyBulkFilePath(obj.getOnlyBulkFilePath());
				}else if(obj.getType().equals(Part.Type_OPT)){
					swappingObj.setOriginalType(Part.Type_OPT);
					swappingObj.setBulkPath(obj.getBulkPath());
					swappingObj.setBulkRenamePath(obj.getbulkRenamePath());
					swappingObj.setPath(obj.getPath());
					swappingObj.setRenamePath(obj.getRenamePath());
					swappingObj.setInputdeckPath(Part.Path_Empty);
					swappingObj.setOp2Path(Part.Path_Empty);
					swappingObj.setOnlyBulkFilePath(obj.getOnlyBulkFilePath());
				}
				this.DBStep3.addSwappingPartList(swappingObj);
			}
			this.DBStep3.getSwappingPartFactoryObjImpl().setAllData(this.DBStep3.getSwappingPartList());
			med.getListViewerSwappingPart().setContentProvider(new SwappingPartDataContentProvider());
			med.getListViewerSwappingPart().setLabelProvider(new SwappingPartDataLabelProvider());
			med.getListViewerSwappingPart().setInput(this.DBStep3.getSwappingPartFactoryObjImpl());
		}catch(Exception e){
			//System.out.println("Error - LoadingStep3List_SwappingPart");
			this.addMsgWindow("Lodaing fail - Swapping Part"+"\n"+e.getMessage(), MessageWindow.Error);
		}
	}
	
	public void LoadingStep4(){
		this.DBStep4.setExportResultName(med.getTextResultName().getText());
	}
	
	public void LoadingStep5(){
		
		try{
			// 기존 데이터 삭제 
			TableItem [] items = med.getTableFatSolving().getItems();
			for(int i=items.length-1 ; i>=0 ; i--){
				Part_FatSolving obj = this.DBStep5.getPartList().get(i);
				this.DBStep5.getTableRowList().get(i).disposeControls();
				this.DBStep5.removeTableRowList(obj.getTableEditorInfoObj());
				items[i].dispose();
			}
			this.DBStep5.getTableComboTextMap().clear();
			this.DBStep5.getTableRowList().clear();
			this.DBStep5.getPartList().clear();	
			
			// 새롭게 데이터 추가
			for(SwappingPart obj : this.DBStep3.getSwappingPartList()){
				Part_FatSolving objFnS = new Part_FatSolving();
				objFnS.setPartName(obj.getPartName());
				objFnS.setBDFPath(obj.getBulkRenamePath());
				objFnS.setType(Part_FatSolving.Type_MSR_MANUAL);
				objFnS.setSwappingMNF(true);
				objFnS.setOnlyBulkFilePath(obj.getOnlyBulkFilePath());
				
				if(obj.getInputdeckPath().equals(Part_FatSolving.Path_Empty)){
					// mnf 선택 
					objFnS.setBulkForMNFPath(obj.getBulkRenamePath());
				}else{
					if(!obj.getOp2Path().equals(Part_FatSolving.Path_Empty)){
						// OPT 선택
						objFnS.setOP2Path(obj.getOp2Path());
						objFnS.setBulkForMNFPath(obj.getInputdeckPath());
					}else{
						// NAS 선택
						objFnS.setBulkForMNFPath(obj.getInputdeckPath());
					}
				}
				this.DBStep5.getPartList().add(objFnS);
			}
			this.LoadingTableData_FatSolving();	
		}catch(Exception e){
			//System.out.println("Error - LoadingStep5List_AllPart");
			this.addMsgWindow("Loading fail - Part Database."+"\n"+e.getMessage(), MessageWindow.Error);
		}
	}
	
	
	
	
	// 단계별 진행 사항 체크 
	private void CheckStep1(){
		try{
			if(this.DBStep1.getSelectedBladeObj() != null && this.DBStep1.getSelectedLinkageObj() != null && this.DBStep1.getSelectedWindshieldObj() != null){
				if(this.DBStep1.getSelectedBladeObj().getDbPath().equals(this.DBStep1.getSelectedBladeDBPath())){
					if(this.DBStep1.getSelectedLinkageObj().getDbPath().equals(this.DBStep1.getSelectedLinkageDBPath())){
						if(this.DBStep1.getWindshieldType().equals(Windshield.TYPE_SPHERE)){
							if(myUtil.isFloatValue(this.DBStep1.getRadius()) && myUtil.isFloatValue(this.DBStep1.getX()) && myUtil.isFloatValue(this.DBStep1.getZ())){
								this.isCompletedStep1 = true;
							}else{
								this.isCompletedStep1 = false;
								this.addMsgWindow("Sphere value is not correct.", MessageWindow.Warnning);
							}
						}else {
							if(this.DBStep1.getSelectedWindshieldObj().getDbPath().equals(this.DBStep1.getSelectedWindshieldDBPath())){
								if(myUtil.checkPath(this.DBStep1.getSelectedWindshieldDBPath())){
									this.isCompletedStep1 = true;
								}else{
									this.isCompletedStep1 = false;
									this.addMsgWindow("Windshield path is not correct(1).", MessageWindow.Warnning);
								}
								
							}else{
								this.isCompletedStep1 = false;
								this.addMsgWindow("Windshield path is not correct(2).", MessageWindow.Warnning);
							}
						}
					}else{
						this.isCompletedStep1 = false;
						this.addMsgWindow("Linkage path is not correct.", MessageWindow.Warnning);
					}
				}else{
					this.isCompletedStep1 = false;
					this.addMsgWindow("Blade path is not correct.", MessageWindow.Warnning);
				}
			}else{
				this.isCompletedStep1 = false;
				this.addMsgWindow("Blade, Linkage, Windshield type is not selected.", MessageWindow.Warnning );
			}
			
			
		}catch(Exception e){
			this.isCompletedStep1 = false;
			this.addMsgWindow("Check Step1 process."+"\n"+e.getMessage(), MessageWindow.Error);
		}
	}
	
	private void CheckStep2(){
		try{
			for(Part obj : this.DBStep2.getSelectedPartList()){
				if(obj.getType().equals(Part.Type_NAS)){
					if(obj.getBulkPath().equals(Part.Path_Empty)){
						this.addMsgWindow("Path is empty.(Part name : "+obj.getPartName()+")", MessageWindow.Warnning);
						this.isCompletedStep2 = false;
						break;
					}else{
						int selectedPartSize = this.DBStep2.getSelectedPartList().size();
						int tableRowListSize = this.DBStep2.getTableRowList().size();
						
						if(selectedPartSize != 0){
							if(selectedPartSize == tableRowListSize){
								this.isCompletedStep2 = true;
							}else {
								this.isCompletedStep2 = false;
								this.addMsgWindow("Table data is not same as selected part.", MessageWindow.Error);
								break;
							}
						}else{
							this.isCompletedStep2 = false;
							this.addMsgWindow("Selected part is empty", MessageWindow.Warnning);
							break;
						}
					}
				}else if(obj.getType().equals(Part.Type_MNF)){
					if(obj.getPath().equals(Part.Path_Empty)){
						this.addMsgWindow("Path is empty.(Part name : "+obj.getPartName()+")", MessageWindow.Warnning);
						this.isCompletedStep2 = false;
						break;
					}else {
						int selectedPartSize = this.DBStep2.getSelectedPartList().size();
						int tableRowListSize = this.DBStep2.getTableRowList().size();
						
						if(selectedPartSize != 0){
							if(selectedPartSize == tableRowListSize){
								this.isCompletedStep2 = true;
							}else {
								this.isCompletedStep2 = false;
								this.addMsgWindow("Table data is not same as selected part.", MessageWindow.Error);
								break;
							}
						}else{
							this.isCompletedStep2 = false;
							this.addMsgWindow("Selected part is empty", MessageWindow.Warnning);
							break;
						}
					}
				}else if(obj.getType().equals(Part.Type_OPT)){
					if(obj.getBulkPath().equals(Part.Path_Empty)){
						this.addMsgWindow("Path is empty.(Part name : "+obj.getPartName()+")", MessageWindow.Warnning);
						this.isCompletedStep2 = false;
						break;
					}else{
						int selectedPartSize = this.DBStep2.getSelectedPartList().size();
						int tableRowListSize = this.DBStep2.getTableRowList().size();
						
						if(selectedPartSize != 0){
							if(selectedPartSize == tableRowListSize){
								this.isCompletedStep2 = true;
							}else {
								this.isCompletedStep2 = false;
								this.addMsgWindow("Table data is not same as selected part.", MessageWindow.Error);
								break;
							}
						}else{
							this.isCompletedStep2 = false;
							this.addMsgWindow("Selected part is empty", MessageWindow.Warnning);
							break;
						}
					}
				}
			}

		}catch(Exception e){
			this.isCompletedStep2 = false;
			this.addMsgWindow("Check Step2 process."+"\n"+e.getMessage()+"\n"+e.getMessage(), MessageWindow.Error);
		}
	}
	
	private void CheckStep3(){
		try{
			for(SwappingPart obj : this.DBStep3.getSwappingPartList()){
				if(obj.getType().equals(Part.Type_NAS)){
					this.isCompletedStep3 = false;
					this.addMsgWindow("Select mnf file.(Part name : "+obj.getPartName()+")", MessageWindow.Warnning);
					break;
				}else if(obj.getType().equals(Part.Type_OPT)){
					this.isCompletedStep3 = false;
					this.addMsgWindow("Select mnf file.(Part name : "+obj.getPartName()+")", MessageWindow.Warnning);
					break;
				}else{
					if(!myUtil.checkPath(obj.getPath())){
						this.isCompletedStep3 = false;
						this.addMsgWindow("Check mnf path.(Part name : "+obj.getPartName()+")", MessageWindow.Warnning);
						break;
					}else{
						this.isCompletedStep3 = true;
					}
				}
			}
		}catch(Exception e){
			this.isCompletedStep3 = false;
			this.addMsgWindow("Check Step3 process."+"\n"+e.getMessage(), MessageWindow.Error);
		}
	}
	
	private void CheckStep4(){
		
	}
	
	private void CheckStep5(){
		
	}
	
	private void ChangeLableProperty(){
		// 각 step 별로 입력이 완성 되면 글씨를 bold로 변경
		if(this.isCompletedStep1){
			med.getLblStep1().setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else {
			med.getLblStep1().setFont(SWTResourceManager.getFont("Arial", 9, SWT.NONE));
		}
		if(this.isCompletedStep2){
			med.getLblStep2().setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else {
			med.getLblStep2().setFont(SWTResourceManager.getFont("Arial", 9, SWT.NONE));
		}
		if(this.isCompletedStep3){
			med.getLblStep3().setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else {
			med.getLblStep3().setFont(SWTResourceManager.getFont("Arial", 9, SWT.NONE));
		}
		if(this.isCompletedStep4){
			med.getLblStep4().setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else {
			med.getLblStep4().setFont(SWTResourceManager.getFont("Arial", 9, SWT.NONE));
		}
		if(this.isCompletedStep5){
			med.getLblStep5().setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else {
			med.getLblStep5().setFont(SWTResourceManager.getFont("Arial", 9, SWT.NONE));
		}
	}
	
	//
	//
	//
	//
	//
	//
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	
	
	
	
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	// 
	//
	//
	//  Handler Event
	//
	//
	//	
	
	// Button
	public void ButtonDoPrevious(){
		//System.out.println("<< (Previous) "+ this.currentStep);
		if(this.currentStep.equals(STEP1) && this.isCompletedStep1 && this.isReadyStep1) {
			med.getStackLayout().topControl = med.getCompositeStep1();
			this.currentStep = STEP1;
			med.getLblStep1().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			this.SetupComponent(this.currentStep);
			
		}else if(this.currentStep.equals(STEP2) && this.isCompletedStep1 && this.isReadyStep2){
			med.getStackLayout().topControl = med.getCompositeStep1();
			this.currentStep = STEP1;
			med.getLblStep1().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			this.SetupComponent(this.currentStep);
			this.DBStep3_back = this.DBStep3;
			
		}else if(this.currentStep.equals(STEP3) && this.isCompletedStep2 && this.isReadyStep3){
			this.SynchronizeStep2AndStep3();
			med.getStackLayout().topControl = med.getCompositeStep2();
			this.currentStep = STEP2;
			med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			this.SetupComponent(this.currentStep);
			this.DBStep3_back = this.DBStep3;
			
			
		}else if(this.currentStep.equals(STEP4) && this.isCompletedStep3 && this.isReadyStep4){
			med.getStackLayout().topControl = med.getCompositeStep3();
			this.currentStep = STEP3;
			med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			this.SetupComponent(this.currentStep);

		}else if(this.currentStep.equals(STEP5) && this.isCompletedStep4 && this.isReadyStep5){
			med.getStackLayout().topControl = med.getCompositeStep4();
			this.currentStep = STEP4;
			med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep5().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			this.SetupComponent(this.currentStep);
		
		}
		med.getCompositeBottom().layout();
		//System.out.println("Current step : "+this.currentStep);
	}
	
	public void ButtonDoNext(){
		//System.out.println(">> (Next) "+ this.currentStep);
		if(this.currentStep.equals(STEP1) && this.isCompletedStep1 && this.isReadyStep2){
			med.getStackLayout().topControl = med.getCompositeStep2();
			this.currentStep = STEP2;
			med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep1().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			this.SetupComponent(this.currentStep);
		}else if(this.currentStep.equals(STEP2) && this.isCompletedStep2 && this.isReadyStep3){
			med.getStackLayout().topControl = med.getCompositeStep3();
			this.currentStep = STEP3;
			med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			this.SetupComponent(this.currentStep);
		}else if(this.currentStep.equals(STEP3) && this.isCompletedStep3 && this.isReadyStep4){
			med.getStackLayout().topControl = med.getCompositeStep4();
			this.currentStep = STEP4;
			med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			this.SetupComponent(this.currentStep);
			this.checkStep1End();
		}else if(this.currentStep.equals(STEP4) && this.isCompletedStep4 && this.isReadyStep5){
			med.getStackLayout().topControl = med.getCompositeStep5();
			this.currentStep = STEP5;
			med.getLblStep5().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			this.SetupComponent(this.currentStep);
		}else if(this.currentStep.equals(STEP5) && this.isCompletedStep5 && this.isReadyStep5){
			med.getStackLayout().topControl = med.getCompositeStep5();
			this.currentStep = STEP5;
			med.getLblStep5().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			
			this.SetupComponent(this.currentStep);
		}
		med.getCompositeBottom().layout();
		//System.out.println("Current step : "+this.currentStep);
	}
	
	public void ButtonEditAllData(){
		// UI 에서 사용 안할듯
	}
	
	public void ButtonSaveAllData(){
		NextStep dlg = new NextStep(Display.getCurrent().getActiveShell());
		dlg.open();
		// => executeNextStep() 으로 연결됨
	}
	
	public void ButtonReloadDB(){
		this.addMsgWindow("Reloading Database", MessageWindow.Info);
		this.LoadingStep1();
	}
	
	public void ButtonNone(){
		if(med.getBtnNone().getSelection()){
			this.DBStep2.setSkiped(true);
			this.DBStep3.setSkiped(true);
			//System.out.println("Skip => Step2,Step3");
			med.getListPart().setEnabled(false);
			med.getBtnAdd().setEnabled(false);
			med.getBtnDel().setEnabled(false);
			med.getTableSwappingPart().setEnabled(false);
			
			med.getListSwappingPart().setEnabled(false);
			med.getTextMnfFilePath().setEnabled(false);
			med.getBtnExplorerStep3().setEnabled(false);
			
			this.isCompletedStep2 = true;
			this.isCompletedStep3 = true;
			
			this.isReadyStep3 = true;
			this.isReadyStep4 = true;
			
			this.ChangeLableProperty();
		}else{
			this.DBStep2.setSkiped(false);
			this.DBStep3.setSkiped(false);
			
			med.getListPart().setEnabled(true);
			med.getBtnAdd().setEnabled(true);
			med.getBtnDel().setEnabled(true);
			med.getTableSwappingPart().setEnabled(true);
			
			med.getListSwappingPart().setEnabled(true);
			med.getTextMnfFilePath().setEnabled(true);
			med.getBtnExplorerStep3().setEnabled(true);
			
			this.isCompletedStep2 = false;
			this.isCompletedStep3 = false;
			this.isCompletedStep4 = false;
			this.isCompletedStep4_2 = false;
			
			this.isReadyStep3 = false;
			this.isReadyStep4 = false;
			
			
			this.currentStep = STEP2;
			//med.getStackLayout().topControl = med.getCompositeStep4();
			//med.getCompositeBottom().layout();
			
			med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			this.ChangeLableProperty();
		}
	}
	
	public void ButtonAddPart(){
		for(Part obj : this.DBStep2.getTempSelectedPartList()){
			if(!this.DBStep2.getSelectedPartList().contains(obj)){
				//Table에 추가
				this.DBStep2.addSelectedPartList(obj);
				//List에서 삭제
				this.DBStep2.removePartList(obj);
				// 선택된 파트체크
				obj.setSelected(true);
				
				med.getListViewerPart().getList().deselectAll();
				med.getListViewerPart().refresh();
			}else{
				//System.out.println("선택 중복된 Part");
			}
		}
		this.AddTableData();
	}
	
	private void AddTableData(){ 
		this.DBStep2.getSelectedPartList();
		
		int nRowCnt = med.getTableSwappingPart().getItemCount();
		
		for(final Part obj : this.DBStep2.getTempSelectedPartList()){
			TableItem item = new TableItem(med.getTableSwappingPart(),SWT.NONE);
			TableEditorInfo editorInfo = new TableEditorInfo(med.getTableSwappingPart(),nRowCnt);
			editorInfo.setPartObj(obj);
			obj.setTableEditorInfoObj(editorInfo);
			
			// Part Name column
			Text textPartName = new Text(med.getTableSwappingPart(),SWT.READ_ONLY);
			textPartName.setText(obj.getPartName());
			editorInfo.setColtrol(textPartName, 1, item);
			
			// Part Type column
			final CCombo combo = new CCombo(med.getTableSwappingPart(),SWT.READ_ONLY);
			combo.add(Part.Type_NAS);
			combo.add(Part.Type_MNF);
			combo.add(Part.Type_OPT);
			combo.select(0);
			editorInfo.setColtrol(combo, 2, item);
			
			combo.addSelectionListener(new SelectionAdapter(){
				@Override
				public void widgetSelected(SelectionEvent e){
					// Nas 선택
					if(combo.getText().equals(Part.Type_NAS)){
						String path = FileExplorer_Table_Nas();
						
						if(path.equals(Part.Path_Empty)){
							//DBStep2.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor().setText(Part.Path_Empty);
							Text pathTextField = DBStep2.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor();
							pathTextField.setText(Part.Path_Empty);
							obj.setType(Part.Type_NAS);
							obj.setBulkPath(Part.Path_Empty);
							obj.setbulkRenamePath(Part.Path_Empty);
							obj.setPath(Part.Path_Empty);
							obj.setRenamePath(Part.Path_Empty);
							obj.setOnlyBulkFilePath(Part.Path_Empty);
							combo.select(0);
						}else{
							//DBStep2.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor().setText(path);
							Text pathTextField = DBStep2.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor();
							pathTextField.setText(path);
							obj.setType(Part.Type_NAS);
							obj.setBulkPath(path);
							obj.setbulkRenamePath(renameInputBlukFile(Part.Type_NAS,path,obj.getPartName()));
							obj.setPath(Part.Path_Empty);
							obj.setRenamePath(Part.Path_Empty);
							obj.setOnlyBulkFilePath(getOnlyBulkFilePath(path,obj.getPartName()));
						}
					// MNF 선택
					}else if(combo.getText().equals(Part.Type_MNF)){
						String path = FileExplorer_Table_mnf();
						
						if(path.equals(Part.Path_Empty)){
							//DBStep2.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor().setText(Part.Path_Empty);
							Text pathTextField = DBStep2.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor();
							pathTextField.setText(Part.Path_Empty);
							obj.setType(Part.Type_NAS);
							obj.setBulkPath(Part.Path_Empty);
							obj.setbulkRenamePath(Part.Path_Empty);
							obj.setPath(Part.Path_Empty);
							obj.setRenamePath(Part.Path_Empty);
							obj.setOnlyBulkFilePath(Part.Path_Empty);
							combo.select(0);
						}else{
							//DBStep2.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor().setText(path);
							Text pathTextField = DBStep2.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor();
							pathTextField.setText(path);
							obj.setType(Part.Type_MNF);
							obj.setBulkPath(Part.Path_Empty);
							obj.setbulkRenamePath(Part.Path_Empty);
							obj.setPath(path);
							obj.setRenamePath(renameInputFile(Part.Type_MNF,path,obj.getPartName()));
						}
						
						executeAddBulkDlg();
						if(!bulkDataForMNF_step2.equals(Part.Path_Empty)){
							obj.setBulkPath(bulkDataForMNF_step2);
							String newFilePath = renameInputBlukFile(Part.Type_NAS,bulkDataForMNF_step2,obj.getPartName());
							obj.setbulkRenamePath(newFilePath);
							findingBulk(newFilePath,obj.getPartName());
							obj.setOnlyBulkFilePath(getOnlyBulkFilePath(newFilePath,obj.getPartName()));
						}
						
						
					// OPT 선택
					}else if(combo.getText().equals(Part.Type_OPT)){
						String path = FileExplorer_Table_OPT();
						if(path.equals(Part.Path_Empty)){
							//DBStep2.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor().setText(Part.Path_Empty);
							Text pathTextField = DBStep2.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor();
							pathTextField.setText(Part.Path_Empty);
							obj.setType(Part.Type_NAS);
							obj.setBulkPath(Part.Path_Empty);
							obj.setbulkRenamePath(Part.Path_Empty);
							obj.setPath(Part.Path_Empty);
							obj.setRenamePath(Part.Path_Empty);
							obj.setOnlyBulkFilePath(Part.Path_Empty);
							combo.select(0);
						}else{
							//DBStep2.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor().setText(path);
							Text pathTextField = DBStep2.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor();
							pathTextField.setText(path);
							obj.setType(Part.Type_OPT);
							obj.setBulkPath(path);
							obj.setbulkRenamePath(renameInputBlukFile(Part.Type_OPT,path,obj.getPartName()));
							obj.setPath(Part.Path_Empty);
							obj.setRenamePath(Part.Path_Empty);
							obj.setOnlyBulkFilePath(getOnlyBulkFilePath(path,obj.getPartName()));
						}
					}
				}
			});
			
			// swap file path 
			Text textPath = new Text(med.getTableSwappingPart(),SWT.READ_ONLY);
			editorInfo.setColtrol(textPath, 3, item);
			textPath.addFocusListener(new TextFocusListener());
			
			nRowCnt++;
			
			DBStep2.putTableComoTextMap(combo, editorInfo);
			obj.setCombo(combo);
			DBStep2.addTableRowList(editorInfo);
			
		}
	}
	
	private String getOnlyBulkFilePath(String filePath, String partName){
		String oriPath = filePath;
		String exetension = myUtil.getExtensions(oriPath);		
		String newFileName = partName+"_onlyBluk."+exetension;
		String newFilePath = "";
		String resultPath = myUtil.setPath(this.Workspace,DatabaseFolderName.Result);
		
		String InputdeckForMNFPath = myUtil.setPath(resultPath, DatabaseFolderName.InputdeckForMNF);
		String bulkBdf = myUtil.setPath(InputdeckForMNFPath, DatabaseFolderName.RenameBulk);
		newFilePath = myUtil.setPath(bulkBdf, newFileName);
		
		return newFilePath;
	}
	private String FileExplorer_Table_mnf(){
		FileDialog dlg = new FileDialog(med.getTableSwappingPart().getShell(),SWT.OPEN);
		dlg.setText("Select MNF file");
		
		String [] extNames = {"MNF(*.MNF)"};
		String [] extType = {"*.MNF"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		
		dlg.setFilterPath(this.getAppPath());
		
		String path = dlg.open();
		if (path == null){
			return Part.Path_Empty;
		}else {
			return path;
		}
	}
	
	private String FileExplorer_Table_Nas(){
		FileDialog dlg = new FileDialog(med.getTableSwappingPart().getShell(),SWT.OPEN);
		dlg.setText("Select BDF file for Nastran.");
		
		String [] extNames = {"BDF(*.BDF)"};
		String [] extType = {"*.BDF"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		
		dlg.setFilterPath(this.getAppPath());
		
		String path = dlg.open();
		if (path == null){
			return Part.Path_Empty;
		}else {
			return path;
		}
	}
	
	private String FileExplorer_Table_OPT(){
		FileDialog dlg = new FileDialog(med.getTableSwappingPart().getShell(),SWT.OPEN);
		dlg.setText("Select file for Optistruct.");
		
		String [] extNames = {"FEM(*.FEM)","BDF(*.BDF)"};
		String [] extType = {"*.FEM","*.BDF"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		
		dlg.setFilterPath(this.getAppPath());
		
		String path = dlg.open();
		if (path == null){
			return Part.Path_Empty;
		}else {
			return path;
		}
	}
	
	private final String renameInputFile(String type, String filePath, String partName){
		// Step2 -> Nas 선택시
		String oriPath = filePath;
		String exetension = myUtil.getExtensions(oriPath);		
		String newFileName = partName+"."+exetension;
		String newFilePath = "";
		String resultPath = myUtil.setPath(this.Workspace,DatabaseFolderName.Result);
		
		/*
		if(type.equals(Part.Type_MNF)){
			
			String SwappingMNF = myUtil.setPath(resultPath, DatabaseFolderName.SwappingMNF);
			String RenameMNF = myUtil.setPath(SwappingMNF, DatabaseFolderName.RenameMNF);
			newFilePath = myUtil.setPath(RenameMNF, newFileName);
			
		}else{
			
			String InputdeckForMNFPath = myUtil.setPath(resultPath, DatabaseFolderName.InputdeckForMNF);
			String bulkBdf = myUtil.setPath(InputdeckForMNFPath, DatabaseFolderName.RenameBulkBdf);
			newFilePath = myUtil.setPath(bulkBdf, newFileName);	
		}
		// */
		
		String SwappingMNF = myUtil.setPath(resultPath, DatabaseFolderName.SwappingMNF);
		String RenameMNF = myUtil.setPath(SwappingMNF, DatabaseFolderName.RenameMNF);
		newFilePath = myUtil.setPath(RenameMNF, newFileName);
		
		if(oriPath.equals(Part.Path_Empty)){
			return Part.Path_Empty;
		}else{
			myUtil.fileCopy(oriPath, newFilePath);
			return newFilePath;
		}
	}
	
	private final String renameInputBlukFile(String type, String filePath, String partName){
		// Step2 -> NAS , OPT 선택시
		String oriPath = filePath;
		String exetension = myUtil.getExtensions(oriPath);		
		String newFileName = partName+"_bluk."+exetension;
		String newFilePath = "";
		String resultPath = myUtil.setPath(this.Workspace,DatabaseFolderName.Result);
		
		String InputdeckForMNFPath = myUtil.setPath(resultPath, DatabaseFolderName.InputdeckForMNF);
		String bulkBdf = myUtil.setPath(InputdeckForMNFPath, DatabaseFolderName.RenameBulk);
		newFilePath = myUtil.setPath(bulkBdf, newFileName);	
		
		
		if(oriPath.equals(Part.Path_Empty)){
			return Part.Path_Empty;
		}else{
			myUtil.fileCopy(oriPath, newFilePath);
			return newFilePath;
		}
	}
	
	private void LoadingTableData_FatSolving(){
		int nRowCnt = 0;
		
		for(final Part_FatSolving obj : this.DBStep5.getPartList()){
			TableItem item = new TableItem(med.getTableFatSolving(),SWT.NONE);
			TableEditorInfo_FatSolving editorInfo = new TableEditorInfo_FatSolving(med.getTableFatSolving(),nRowCnt);
			
			editorInfo.setPart_FatSolvingObj(obj);
			obj.setTableEditorInfoObj(editorInfo);

			// Part Name column
			Text textPartName = new Text(med.getTableFatSolving(),SWT.READ_ONLY);
			textPartName.setText(obj.getPartName());
			editorInfo.setColtrol(textPartName, 1, item);
			
			// Part Type column
			final CCombo combo = new CCombo(med.getTableFatSolving(),SWT.READ_ONLY);
			combo.add(Part_FatSolving.Type_MSR_MANUAL);
			combo.add(Part_FatSolving.Type_MSR_AUTO);
			combo.add(Part_FatSolving.Type_MSM);
			if(obj.getType().equals(Part_FatSolving.Type_MSR_MANUAL)){
				combo.select(0);
			}else if(obj.getType().equals(Part_FatSolving.Type_MSR_AUTO)){
				combo.select(1);
			}else if(obj.getType().equals(Part_FatSolving.Type_MSM)){
				combo.select(2);
			}
			editorInfo.setColtrol(combo, 2, item);
			
			combo.addSelectionListener(new SelectionAdapter(){
				@Override
				public void widgetSelected(SelectionEvent e){
					if(combo.getText().equals(Part_FatSolving.Type_MSR_MANUAL)){
						String path = FileExplorer_Table_MSR();
						if(path.equals(Part_FatSolving.Path_Empty)){
							Text pathTextField = DBStep5.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor();
							pathTextField.setText(Part_FatSolving.Path_Empty);
							obj.setOP2Path(Part_FatSolving.Path_Empty);
							obj.setType(Part_FatSolving.Type_MSR_MANUAL);
						}else {
							Text pathTextField = DBStep5.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor();
							pathTextField.setText(path);
							obj.setOP2Path(path);
							obj.setType(Part_FatSolving.Type_MSR_MANUAL);
						}
					}else if(combo.getText().equals(Part_FatSolving.Type_MSR_AUTO)){
						String path = FileExplorer_Table_MSR();
						if(path.equals(Part_FatSolving.Path_Empty)){
							Text pathTextField = DBStep5.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor();
							pathTextField.setText(Part_FatSolving.Path_Empty);
							obj.setOP2Path(Part_FatSolving.Path_Empty);
							obj.setType(Part_FatSolving.Type_MSR_AUTO);
						}else {
							Text pathTextField = DBStep5.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor();
							pathTextField.setText(path);
							obj.setOP2Path(path);
							obj.setType(Part_FatSolving.Type_MSR_AUTO);
						}
					}else if(combo.getText().equals(Part_FatSolving.Type_MSM)){
						
						String path = FileExplorer_Table_MSM(obj.getOnlyBulkFilePath());
						if(path.equals(Part_FatSolving.Path_Empty)){
							Text pathTextField = DBStep5.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor();
							pathTextField.setText(Part_FatSolving.Path_Empty);
							obj.setBDFPath(Part_FatSolving.Path_Empty);
							obj.setType(Part_FatSolving.Type_MSM);
						}else {
							Text pathTextField = DBStep5.getTableComboTextMap().get(e.getSource()).getTextPathTableEditor();
							pathTextField.setText(path);
							obj.setBDFPath(path);
							obj.setType(Part_FatSolving.Type_MSM);
						}
					}
				}
			});
			
			// Path 
			Text textPath = new Text(med.getTableFatSolving(),SWT.READ_ONLY);
			if(obj.getType().equals(Part_FatSolving.Type_MSR_MANUAL)){
				if(obj.getOP2Path().equals(Part_FatSolving.Path_Empty)){
					textPath.setText(" ");
				}else{
					textPath.setText(obj.getOP2Path());
				}
			}else if(obj.getType().equals(Part_FatSolving.Type_MSR_AUTO)){
				if(obj.getOP2Path().equals(Part_FatSolving.Path_Empty)){
					textPath.setText(" ");
				}else{
					textPath.setText(obj.getOP2Path());
				}
			}else if(obj.getType().equals(Part_FatSolving.Type_MSM)){
				if(obj.getBDFPath().equals(Part_FatSolving.Path_Empty)){
					textPath.setText(" ");
				}else{
					textPath.setText(obj.getBDFPath());
				}
			}
			editorInfo.setColtrol(textPath, 3, item);
			textPath.addFocusListener(new TextFocusListener());
			
			nRowCnt++;
			
			DBStep5.putTableComoTextMap(combo, editorInfo);
			obj.setCombo(combo);
			DBStep5.addTableRowList(editorInfo);
			
		}
	}
	
	private String FileExplorer_Table_MSR(){
		FileDialog dlg = new FileDialog(med.getTableSwappingPart().getShell(),SWT.OPEN);
		dlg.setText("Select OP2 file");
		
		String [] extNames = {"OP2(*.OP2)"};
		String [] extType = {"*.OP2"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		
		dlg.setFilterPath(this.getAppPath());
		
		String path = dlg.open();
		if (path == null){
			return Part_FatSolving.Path_Empty;
		}else {
			return path;
		}
	}
	
	private String FileExplorer_Table_MSM(String onlyBulkFilePath){
		//System.out.println("ININININININININ : "+onlyBulkFilePath);
		FileDialog dlg = new FileDialog(med.getTableSwappingPart().getShell(),SWT.OPEN);
		dlg.setText("Select Bulk bdf file");

		/*
		String [] extNames = {"BDF(*.BDF)"};
		String [] extType = {"*.BDF"};
		// */
		String [] extNames = {"ALL(*.*)","FEM(*.FEM)","BDF(*.BDF)"};
		String [] extType = {"*.*","*.FEM","*.BDF"};
		
		dlg.setFileName(onlyBulkFilePath);
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return Part_FatSolving.Path_Empty;
		}else {
			return path;
		}
	}
	
	
	public void ButtonRemovePart(){
		TableItem [] items = med.getTableSwappingPart().getItems();
		boolean bDeleted = false;
		for(int i=items.length-1 ; i>=0 ; i--){
			if(items[i].getChecked()){
				Part obj = this.DBStep2.getSelectedPartList().get(i);
				this.DBStep2.getTableRowList().get(i).disposeControls();
				this.DBStep2.removeTableRowList(obj.getTableEditorInfoObj());
				items[i].dispose();
				
				//List에 추가 및 업데이트
				this.DBStep2.getPartFactoryObjImpl().addAllDataObj(obj);
				med.getListViewerPart().refresh();
				//Table에서 삭제
				this.DBStep2.getSelectedPartList().get(i).setSelected(false);
				this.DBStep2.getSelectedPartList().get(i).setType(Part.Type_NAS);
				this.DBStep2.getSelectedPartList().get(i).setBulkPath(Part.Path_Empty);
				this.DBStep2.getSelectedPartList().get(i).setbulkRenamePath(Part.Path_Empty);
				this.DBStep2.getSelectedPartList().get(i).setPath(Part.Path_Empty);
				this.DBStep2.getSelectedPartList().get(i).setRenamePath(Part.Path_Empty);
				this.DBStep2.removeSelectedPartList(obj);
				if(bDeleted == false){
					bDeleted = true;
				}
			}
		}
		
		if(bDeleted == false){
			MessageDialog.openWarning(med.getTableFatSolving().getShell(), "Warning" , "Check items that you would like to delete");
			return;
		}
		
		med.getTableSwappingPart().pack();
		med.getTableSwappingPart().getParent().layout(false);
	}
	
	public void ButtonExplorerStep3(){
		if(this.DBStep3.getCurrentSwappingPart() != null){
			String path = this.FileExplorer_MNF();
			
			String exetension = myUtil.getExtensions(path);
			if(exetension != null){
				if(exetension.toLowerCase().equals("mnf")){
					med.getLblSwappingTypeValue().setText(Part.Type_MNF);
					med.getTextMnfFilePath().setText(path);
					med.getTextMnfFilePath().setEditable(false);
					this.DBStep3.getCurrentSwappingPart().setPath(path);
					String SwappingPartName = this.DBStep3.getCurrentSwappingPart().getPartName();
					String renamePath = renameInputFile(Part.Type_MNF,path,SwappingPartName);
					if(this.DBStep3.getCurrentSwappingPart().getType().equals(Part.Type_OPT)){
						String parentPath = myUtil.getParentPath(path);
						String fileName = myUtil.getFileName(path);
						//String op2Path = myUtil.setPath(parentPath,SwappingPartName+".op2");
						String op2Path = myUtil.setPath(parentPath,fileName+".op2");
						this.DBStep3.getCurrentSwappingPart().setOp2Path(op2Path);
					}
					this.DBStep3.getCurrentSwappingPart().setRenamePath(renamePath);
					this.DBStep3.getCurrentSwappingPart().setType(Part.Type_MNF);
					med.getListViewerSwappingPart().refresh();
					
					
					
					// DBStep2 와 데이터 연동
					//String SwappingPartName = this.DBStep3.getCurrentSwappingPart().getPartName();
					Part obj = this.DBStep2.SearchingPartObj(SwappingPartName);
					obj.setType(Part.Type_MNF);
					obj.setPath(path);
					obj.setRenamePath(renamePath);
				}else {
					//med.getLblSwappingTypeValue().setText(Part.Type_NAS);
					med.getTextMnfFilePath().setText("");
					med.getTextMnfFilePath().setEditable(true);
				}
			}
		}
		
	}
	private String FileExplorer_MNF(){
		FileDialog dlg = new FileDialog(med.getCompositeStep3().getShell(),SWT.OPEN);
		dlg.setText("Select MNF file");
		
		String [] extNames = {"mnf(*.mnf)"};
		String [] extType = {"*.mnf"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return Part.Path_Empty;
		}else {
			return path;
		}
	}
	
	public void ButtonStartSolving(){
		//System.out.println("Start Solving");
		//System.out.println("=================>First Solving : "+this.DBStep4.isFirstRun());
		/////////////////////////////////////////////////////////////////////////////////////////
		// Command Server 연결
		if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_MODULETANDEM)){
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_Simulation_MT));
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALOPPOSED)){
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_Simulation_SO));
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALPARALLEL)){
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_Simulation_SP));
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALTANDEM)){
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_Simulation_ST));
		}
		/////////////////////////////////////////////////////////////////////////////////////////
		this.DBStep4.setAdamsSolving("true");
		
		this.isCompletedStep4 = true;
		med.getBtnStartSolving().setEnabled(false);
		//med.getBtnStopSolving().setEnabled(true);
		med.getBtnStartAnimation().setEnabled(false);
		med.getBtnExportResult().setEnabled(false);
		med.getBtnSaveAllData().setEnabled(false);
		if(this.DBStep4.isFirstRun()){
			this.DBStep4.setFirstRun(false);
			//System.out.println("=================>Check First Solving : "+this.DBStep4.isFirstRun());
		}
	}
	
	/*
	public void ButtonStopSolving(){
		System.out.println("Stop Solving");
		/////////////////////////////////////////////////////////////////////////////////////////
		// Command Server 연결
		this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_Abort));
		/////////////////////////////////////////////////////////////////////////////////////////
		med.getBtnStartSolving().setEnabled(true);
	}
	//*/
	
	public void ButtonStartAnimation(){
		//System.out.println("Start Animation");
		/////////////////////////////////////////////////////////////////////////////////////////
		// Command Server 연결
		if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_MODULETANDEM)){
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_Animation_MT));
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALOPPOSED)){
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_Animation_SO));
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALPARALLEL)){
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_Animation_SP));
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALTANDEM)){
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_Animation_ST));
		}
		/////////////////////////////////////////////////////////////////////////////////////////
		
	}
	
	public void ButtonGSTIFF(){
		if(med.getBtnGSTIFF().getSelection()){
			this.DBStep4.setIntegrator(DatabaseStep4.GSTIFF);
		}
		
	}
	
	public void ButtonWSTIFF(){
		if(med.getBtnWSTIFF().getSelection()){
			this.DBStep4.setIntegrator(DatabaseStep4.WSTIFF);
		}
	}
	
	public void ButtonI3(){
		if(med.getBtnI3().getSelection()){
			this.DBStep4.setFormulation(DatabaseStep4.I3);
		}
	}
	
	public void ButtonSI2(){
		if(med.getBtnSI2().getSelection()){
			this.DBStep4.setFormulation(DatabaseStep4.SI2);
		}
	}
	
	public void ButtonOriginal(){
		if(med.getBtnOriginal().getSelection()){
			this.DBStep4.setCorrector(DatabaseStep4.Original);
		}
	}
	
	public void ButtonModified(){
		if(med.getBtnModified().getSelection()){
			this.DBStep4.setCorrector(DatabaseStep4.Modified);
		}
	}
	
	public void ButtonExtraMassOn(){
		
		if(med.getBtnExtraMassOn().getSelection()){
			this.DBStep4.setExtraMass("1");
			this.DBStep4.setExtraMassOn(true);
			med.getTextExtraMassRatio().setEnabled(true);
			med.getTextExtraMassRatio().setText(this.DBStep4.getExtraMassRatio());
			this.DBStep4.setExtraMassRatio(med.getTextExtraMassRatio().getText().trim());
			//System.out.println("1 Extra Mass On : "+this.DBStep4.isExtraMassOn());
		}else{
			//System.out.println("2 Extra Mass Off : "+this.DBStep4.isExtraMassOn());
		}
	}
	
	public void ButtonExtraMassOff(){
		if(med.getBtnExtraMassOff().getSelection()){
			this.DBStep4.setExtraMass("0");
			this.DBStep4.setExtraMassOn(false);
			med.getTextExtraMassRatio().setEnabled(false);
			//System.out.println("3 Extra Mass Off : "+this.DBStep4.isExtraMassOn());
		}else{
			//System.out.println("4 Extra Mass On : "+this.DBStep4.isExtraMassOn());
			
		}
	}
	
	public void ButtonModelData(){
		//System.out.println("Check Model Data");
		if(med.getBtnModelData().getSelection()){
			this.DBStep4.setExportModel("true");
		}else{
			this.DBStep4.setExportModel("false");
		}
	}
	
	public void ButtonDACFile(){
		//System.out.println("Check DAC Data");
		if(med.getBtnDACFile().getSelection()){
			this.DBStep4.setExportDAC("true");
		}else{
			this.DBStep4.setExportDAC("false");
		}
	}
	
	public void ButtonModelDataBin(){
		//System.out.println("Check Model Data Bin");
		if(med.getBtnModelDataBin().getSelection()){
			this.DBStep4.setExportModelBin("true");
		}else{
			this.DBStep4.setExportModelBin("false");
		}
	}
	
	public void ButtonForceFile(){
		//System.out.println("Check Force Data");
		if(med.getBtnForceFile().getSelection()){
			this.DBStep4.setExportForce("true");
		}else{
			this.DBStep4.setExportForce("false");
		}
	}
	
	
	
	public void ButtonExportResult(){
		//System.out.println("Export Result");
		if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_MODULETANDEM)){
			this.ButtonExportResult_ModuleTandem();
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALOPPOSED)){
			this.ButtonExportResult_SeiralOpposed();
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALPARALLEL)){
			this.ButtonExportResult_SerialParallel();
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALTANDEM)){
			this.ButtonExportResult_SerialTandem();
		}
		this.isCompletedStep4_2 = true;
		
	}
	
	private void ButtonExportResult_ModuleTandem(){
		if(this.DBStep4.getExportModel().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_AssemModel_MT));
			//System.out.println("END - CMD");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		if(this.DBStep4.getExportModelBin().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_AssemModelBin_MT));
			//System.out.println("END - Bin");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		if(this.DBStep4.getExportDAC().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_DAC_MT));
			//System.out.println("END - DAC Result");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		if(this.DBStep4.getExportForce().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_Force_MT));
			//System.out.println("END - Force Result");
			/////////////////////////////////////////////////////////////////////////////////////////
			
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			if(this.DBStep2.isSkiped()){
				
			}else{
				this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_NodeInfomation_MT));
			}
			//System.out.println("END - NodeInformation CMD");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		/////////////////////////////////////////////////////////////////////////////////////////
		// Command Server 연결
		this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_BladePosition_MT));
		//System.out.println("END - BladePosition CMD");
		/////////////////////////////////////////////////////////////////////////////////////////
	}
	
	private void ButtonExportResult_SeiralOpposed(){
		if(this.DBStep4.getExportModel().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_AssemModel_SO));
			//System.out.println("END - CMD");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		if(this.DBStep4.getExportModelBin().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_AssemModelBin_SO));
			//System.out.println("END - Bin");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		if(this.DBStep4.getExportDAC().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_DAC_SO));
			//System.out.println("END - DAC Result");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		if(this.DBStep4.getExportForce().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_Force_SO));
			//System.out.println("END - Force Result");
			/////////////////////////////////////////////////////////////////////////////////////////
			
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			if(this.DBStep2.isSkiped()){
				
			}else{
				this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_NodeInfomation_SO));
			}
			
			//System.out.println("END - NodeInformation CMD");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		/////////////////////////////////////////////////////////////////////////////////////////
		// Command Server 연결
		this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_BladePosition_SO));
		//System.out.println("END - BladePosition CMD");
		/////////////////////////////////////////////////////////////////////////////////////////
	}
	
	private void ButtonExportResult_SerialParallel(){
		if(this.DBStep4.getExportModel().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_AssemModel_SP));
			//System.out.println("END - CMD");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		if(this.DBStep4.getExportModelBin().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_AssemModelBin_SP));
			//System.out.println("END - Bin");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		if(this.DBStep4.getExportDAC().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_DAC_SP));
			//System.out.println("END - DAC Result");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		if(this.DBStep4.getExportForce().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_Force_SP));
			//System.out.println("END - Force Result");
			/////////////////////////////////////////////////////////////////////////////////////////
			
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			if(this.DBStep2.isSkiped()){
				
			}else{
				this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_NodeInfomation_SP));
			}
			//System.out.println("END - NodeInformation CMD");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		/////////////////////////////////////////////////////////////////////////////////////////
		// Command Server 연결
		this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_BladePosition_SP));
		//System.out.println("END - BladePosition CMD");
		/////////////////////////////////////////////////////////////////////////////////////////
	}
	
	private void ButtonExportResult_SerialTandem(){
		if(this.DBStep4.getExportModel().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_AssemModel_ST));
			//System.out.println("END - CMD");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		if(this.DBStep4.getExportModelBin().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_AssemModelBin_ST));
			//System.out.println("END - Bin");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		if(this.DBStep4.getExportDAC().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_DAC_ST));
			//System.out.println("END - DAC Result");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		if(this.DBStep4.getExportForce().equals("true")){
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_Force_ST));
			//System.out.println("END - Force Result");
			/////////////////////////////////////////////////////////////////////////////////////////
			
			/////////////////////////////////////////////////////////////////////////////////////////
			// Command Server 연결
			if(this.DBStep2.isSkiped()){
				
			}else{
				this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_NodeInfomation_ST));
			}
			//System.out.println("END - NodeInformation CMD");
			/////////////////////////////////////////////////////////////////////////////////////////
		}
		/////////////////////////////////////////////////////////////////////////////////////////
		// Command Server 연결
		this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd4_BladePosition_ST));
		//System.out.println("END - BladePosition CMD");
		/////////////////////////////////////////////////////////////////////////////////////////
	}
	
	public void ButtonSolvingAndCreate(){
		// Step 5
		//System.out.println("Step5 Solving And Create");
		TableItem[] items = med.getTableFatSolving().getItems();
		boolean bSelected = false;
		for(int i=0;i<items.length;i++){
			if(items[i].getChecked()){
				Part_FatSolving obj = this.DBStep5.getPartList().get(i);
				if(obj.getType().equals(Part_FatSolving.Type_MSR_MANUAL)){
					this.SovlingMSR_FemFat(obj);
				}else if(obj.getType().equals(Part_FatSolving.Type_MSR_AUTO)){
					this.SovlingMSR_FemFat(obj);
				}else if(obj.getType().equals(Part_FatSolving.Type_MSM)){
					this.CreateMSM_Nastran(obj);
				}
				if(bSelected == false){
					bSelected = true;
				}
			}
		}
		
		if(bSelected == false){
			MessageDialog.openWarning(med.getTableSwappingPart().getShell(), "Warning" , "Check items that you would like to Solving and Create");
			this.addMsgWindow("Check items that you would like to Solving and Create", MessageWindow.Warnning);
		}
	}
	
	private void SovlingMSR_FemFat(Part_FatSolving obj){
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
		
		//System.out.println("===== MSR =====");
		this.addMsgWindow("<"+obj.getPartName()+">"+" Start MSR anlysis.", MessageWindow.Info);
		MSRSolving msrObj = new MSRSolving();
		msrObj.running(obj);
		
		if(msrObj.isPossible()){
			Thread th = new Thread(msrObj);
			th.start();	
		}else{
			if(!msrObj.isPossible_ffd()){
				JOptionPane.showMessageDialog(null, obj.getPartName()+"\n"+"Please, Select FEMFAT path in Preference.","Check FEMFAT path",JOptionPane.ERROR_MESSAGE);
				this.addMsgWindow(" Please, Select FEMFAT path in Preference.", MessageWindow.Error);
				//executeFilePreference();
			}
			
			if(!msrObj.isPossible_Bulk()){
				String path = FileExplorer_Bulk_Step5(obj.getPartName());
				if(path.equals(Part_FatSolving.Path_Empty)){
					JOptionPane.showMessageDialog(null, obj.getPartName()+"\n"+"Please, Select Bluk data for MSR.","Check Bulk data",JOptionPane.ERROR_MESSAGE);
					this.addMsgWindow("<"+obj.getPartName()+">"+" Please, Select Bluk data for MSR.", MessageWindow.Error);
				}else{
					obj.setBulkForMNFPath(path);
				}
			}
			
			if(!msrObj.isPossible_OP2()){
				JOptionPane.showMessageDialog(null, obj.getPartName()+"\n"+"Please, Select OP2 file for MSR.","Check OP2 file",JOptionPane.ERROR_MESSAGE);
				this.addMsgWindow("<"+obj.getPartName()+">"+" Please, Select OP2 file for MSR.", MessageWindow.Error);
			}
			
			if(!msrObj.isPossible_DAC()){
				JOptionPane.showMessageDialog(null, obj.getPartName()+"\n"+"DAC files are not exist. Please, Export DAC Result.","Check DAC files",JOptionPane.ERROR_MESSAGE);
				this.addMsgWindow("<"+obj.getPartName()+">"+" DAC files are not exist.", MessageWindow.Error);
			}
		}
	}
	
	private String FileExplorer_Bulk_Step5(String partName){
		FileDialog dlg = new FileDialog(med.getTableSwappingPart().getShell(),SWT.OPEN);
		dlg.setText("Select Bulk data file(PartName : "+partName);
		
		String [] extNames = {"FEM(*.FEM)","BDF(*.BDF)"};
		String [] extType = {"*.FEM","*.BDF"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return Part.Path_Empty;
		}else {
			return path;
		}
	}
	
	private void CreateMSM_Nastran(Part_FatSolving obj){
		//System.out.println("===== MSM =====");
		this.addMsgWindow("<"+obj.getPartName()+">"+" Start MSM anlysis.", MessageWindow.Info);
		MSMSolving msmObj = new MSMSolving();
		msmObj.running(obj);
		
		Thread th = new Thread(msmObj);
		th.start();
		
		/* MSM 용 bulkdata 파일 입력 
		executeAddBulkDlg();
		if(!bulkDataForMNF_step2.equals(Part.Path_Empty)){
			obj.setBulkPath(bulkDataForMNF_step2);
			obj.setbulkRenamePath(renameInputBlukFile(Part.Type_NAS,bulkDataForMNF_step2,obj.getPartName()));
		}
		// */ 
	}
	
	// Combo
	public void ComboBladeType(){
		try{
			ComboLabel comboLabelObj = new ComboLabel();
			String selectedType = med.getComboBladeType().getItem(med.getComboBladeType().getSelectionIndex());
			//System.out.println(selectedType);
			if(comboLabelObj.getLabel(ComboLabel.Symmetric_Same_Basic).equals(selectedType)){
				med.getListViewerBladeDatabase().setInput(this.DBStep1.getBladeFactorySymmetricSameBasicObjImpl());
				med.getListViewerBladeDatabase().refresh();
				this.DBStep1.setBladeType(Blade.TYPE_SYMMETRIC_SAME_BASIC);
				this.LoadingStep1BladeImage(Blade.TYPE_SYMMETRIC_SAME_BASIC);
			}else if(comboLabelObj.getLabel(ComboLabel.Symmetric_Different_Basic).equals(selectedType)){
				med.getListViewerBladeDatabase().setInput(this.DBStep1.getBladeFactorySymmetricDifferentBasicObjImpl());
				med.getListViewerBladeDatabase().refresh();
				this.DBStep1.setBladeType(Blade.TYPE_SYMMETRIC_DIFFERENT_BASIC);
				this.LoadingStep1BladeImage(Blade.TYPE_SYMMETRIC_DIFFERENT_BASIC);
			}else if(comboLabelObj.getLabel(ComboLabel.NonSymmetric_Outer_Basic).equals(selectedType)){
				med.getListViewerBladeDatabase().setInput(this.DBStep1.getBladeFactoryNonSymmetricOuterBasicObjImpl());
				med.getListViewerBladeDatabase().refresh();
				this.DBStep1.setBladeType(Blade.TYPE_NONSYMMETRIC_OUTER_BASIC);
				this.LoadingStep1BladeImage(Blade.TYPE_NONSYMMETRIC_OUTER_BASIC);
			}else if(comboLabelObj.getLabel(ComboLabel.NonSymmetric_Inner_Basic).equals(selectedType)){
				med.getListViewerBladeDatabase().setInput(this.DBStep1.getBladeFactoryNonSymmetricInnerBasicObjImpl());
				med.getListViewerBladeDatabase().refresh();
				this.DBStep1.setBladeType(Blade.TYPE_NONSYMMETRIC_INNNER_BASIC);
				this.LoadingStep1BladeImage(Blade.TYPE_NONSYMMETRIC_INNNER_BASIC);
			}
			
		}catch(Exception e){
			System.out.println("Error - ComboBladeType");
		}
		
	}
	
	public void ComboLinkageType(){
		try{
			ComboLabel comboLabelObj = new ComboLabel();
			String selectedType = med.getComboLinkageType().getItem(med.getComboLinkageType().getSelectionIndex());
			//System.out.println(selectedType);
			if(comboLabelObj.getLabel(ComboLabel.SerialParallel).equals(selectedType)){
				med.getListViewerLinkageDatabase().setInput(this.DBStep1.getLinkageFactorySerialParallelObjImpl());
				med.getListViewerLinkageDatabase().refresh();
				this.DBStep1.setLinkageType(Linkage.TYPE_SERIALPARALLEL);
				this.LoadingStep1LinkageImage(Linkage.TYPE_SERIALPARALLEL);
			}else if(comboLabelObj.getLabel(ComboLabel.SerialOpposed).equals(selectedType)){
				med.getListViewerLinkageDatabase().setInput(this.DBStep1.getLinkageFactorySerialOpposedObjImpl());
				med.getListViewerLinkageDatabase().refresh();
				this.DBStep1.setLinkageType(Linkage.TYPE_SERIALOPPOSED);
				this.LoadingStep1LinkageImage(Linkage.TYPE_SERIALOPPOSED);
			}else if(comboLabelObj.getLabel(ComboLabel.ModuleTandem).equals(selectedType)){
				med.getListViewerLinkageDatabase().setInput(this.DBStep1.getLinkageFactoryModuleTandemObjImpl());
				med.getListViewerLinkageDatabase().refresh();
				this.DBStep1.setLinkageType(Linkage.TYPE_MODULETANDEM);
				this.LoadingStep1LinkageImage(Linkage.TYPE_MODULETANDEM);
			}else if(comboLabelObj.getLabel(ComboLabel.SerialTandem).equals(selectedType)){
				med.getListViewerLinkageDatabase().setInput(this.DBStep1.getLinkageFactorySerialTandemObjImpl());
				med.getListViewerLinkageDatabase().refresh();
				this.DBStep1.setLinkageType(Linkage.TYPE_SERIALTANDEM);
				this.LoadingStep1LinkageImage(Linkage.TYPE_SERIALTANDEM);
			}
		}catch(Exception e){
			System.out.println("Error - ComboLinkageType");
		}
	}
	
	public void ComboWindshieldType(){
		try{
			ComboLabel comboLabelObj = new ComboLabel();
			//comboLabelObj.getLabel(ComboLabel.WINDSHIELDTYPE1);
			String selectType = med.getComboWindshieldType().getItem(med.getComboWindshieldType().getSelectionIndex());
			//System.out.println(selectType);
			if(comboLabelObj.getLabel(ComboLabel.Sphere).equals(selectType)){
				this.DBStep1.setWindshieldType(Windshield.TYPE_SPHERE);
				this.DBStep1.setSelectedWindshieldDBName(Windshield.DBNAME_EMPTY);
				this.DBStep1.setSelectedWindshieldDBPath(Windshield.PATH_EMPTY);
				
				med.getTextRadiusValue().setEnabled(true);
				med.getTextXValue().setEnabled(true);
				med.getTextYValue().setEnabled(true);
				med.getTextZValue().setEnabled(true);
				med.getListWindShieldDatabase().setEnabled(false);
				
				
				Windshield obj = new Windshield();
				String r = med.getTextRadiusValue().getText().trim();
				String x = med.getTextXValue().getText().trim();
				String y = med.getTextYValue().getText().trim();
				String z = med.getTextZValue().getText().trim();
				obj.InitWindshield_typeSphere(r, x, y, z);
				obj.setDbName(Windshield.DBNAME_EMPTY);
				obj.setDbPath(Windshield.PATH_EMPTY);
				obj.setType(Windshield.TYPE_SPHERE);
				
				this.DBStep1.setSelectedWindshieldObj(obj);
				this.DBStep1.setRadius(r);
				this.DBStep1.setX(x);
				this.DBStep1.setY(y);
				this.DBStep1.setZ(z);
				
			}else if(comboLabelObj.getLabel(ComboLabel.Geometry).equals(selectType)){
				this.DBStep1.setWindshieldType(Windshield.TYPE_GEOMETRY);
				
				med.getTextRadiusValue().setEnabled(false);
				med.getTextXValue().setEnabled(false);
				med.getTextYValue().setEnabled(false);
				med.getTextZValue().setEnabled(false);
				med.getListWindShieldDatabase().setEnabled(true);
			}
		}catch(Exception e){
			System.out.println("Error - ComboWindshieldType");
		}
	}
	
	// Label
	public void LabelDClickStep1(){
		if(this.isCompletedStep1 || this.isReadyStep1){
			med.getStackLayout().topControl = med.getCompositeStep1();
			this.currentStep = STEP1;
			this.SetupComponent(this.currentStep);
			med.getCompositeBottom().layout();
			
			med.getLblStep1().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep5().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		}
		//this.currentStatus();
	}
	
	public void LabelDClickStep2(){
		if(this.isCompletedStep2 || this.isReadyStep2){
			med.getStackLayout().topControl = med.getCompositeStep2();
			this.currentStep = STEP2;
			this.SetupComponent(this.currentStep);
			med.getCompositeBottom().layout();
			
			this.SynchronizeStep2AndStep3();
			
			med.getLblStep1().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep5().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		}
		//this.currentStatus();
	}
	
	private void SynchronizeStep2AndStep3(){
		for(Part obj : this.DBStep2.getSelectedPartList()){
			if(obj.getType().equals(Part.Type_MNF)){
				obj.getTableEditorInfoObj().setTextPath(obj.getPath());
				obj.getTableEditorInfoObj().setComboType(Part.Type_MNF);
			}else if(obj.getType().equals(Part.Type_NAS)){
				obj.getTableEditorInfoObj().setTextPath(obj.getBulkPath());
				obj.getTableEditorInfoObj().setComboType(Part.Type_NAS);
			}else if(obj.getType().equals(Part.Type_OPT)){
				obj.getTableEditorInfoObj().setTextPath(obj.getBulkPath());
				obj.getTableEditorInfoObj().setComboType(Part.Type_OPT);
			}
		}
		
		this.DBStep3_back = this.DBStep3;
	}
	
	public void LabelDClickStep3(){
		if(this.isCompletedStep3 || this.isReadyStep3){
			med.getStackLayout().topControl = med.getCompositeStep3();
			this.currentStep = STEP3;
			this.SetupComponent(this.currentStep);
			med.getCompositeBottom().layout();
			
			med.getLblStep1().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep5().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			
			this.DBStep3_back = this.DBStep3;
		}
		//this.currentStatus();
		this.checkStep1End();
	}
	
	public void LabelDClickStep4(){
		if(this.isCompletedStep4 || this.isReadyStep4){
			med.getStackLayout().topControl = med.getCompositeStep4();
			this.currentStep = STEP4;
			this.SetupComponent(this.currentStep);
			med.getCompositeBottom().layout();
			
			med.getLblStep1().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblStep5().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		}
		//this.currentStatus();
	}
	
	public void LabelDClickStep5(){
		if(this.isCompletedStep5 || this.isReadyStep5){
			med.getStackLayout().topControl = med.getCompositeStep5();
			this.currentStep = STEP5;
			this.SetupComponent(this.currentStep);
			med.getCompositeBottom().layout();
			
			med.getLblStep1().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblStep5().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		}
		//this.currentStatus();
	}
	
	// List
	// Blade Type 결정 
	public void ListSelectBladeDatabase(){
		try{
			/*
			int selectionIndex = med.getListBladeDatabase().getSelectionIndex();
			BladeFactory Fobj = null;
			if(this.DBStep1.getBladeType().equals(ComboLabel.BLADETYPE1)){
				Fobj = this.DBStep1.getBladeFactoryT1ObjImpl();	
			}else if(this.DBStep1.getBladeType().equals(ComboLabel.BLADETYPE2)){
				Fobj = this.DBStep1.getBladeFactoryT2ObjImpl();
			}else if(this.DBStep1.getBladeType().equals(ComboLabel.BLADETYPE3)){
				Fobj = this.DBStep1.getBladeFactoryT3ObjImpl();
			}else if(this.DBStep1.getBladeType().equals(ComboLabel.BLADETYPE4)){
				Fobj = this.DBStep1.getBladeFactoryT4ObjImpl();
			}
			Blade obj = (Blade) Fobj.getAllData().get(selectionIndex);
			this.DBStep1.setSelectedBladeObj(obj);
			this.DBStep1.setSelectedBladeDBName(obj.getDbName());
			this.DBStep1.setSelectedBladeDBPath(obj.getDbPath());
			// */
			IStructuredSelection selectionList =(IStructuredSelection)med.getListViewerBladeDatabase().getSelection();
			Blade obj = (Blade)selectionList.getFirstElement();
			this.DBStep1.setSelectedBladeObj(obj);
			this.DBStep1.setSelectedBladeDBName(obj.getDbName());
			this.DBStep1.setSelectedBladeDBPath(obj.getDbPath());
			
		}catch(Exception e){
			System.out.println("Error - ListSelectBladeDatabase");
		}
		
	}
	// Linkage Type 결정
	public void ListSelectLinkageDatabase(){
		try{
			IStructuredSelection selectionList =(IStructuredSelection)med.getListViewerLinkageDatabase().getSelection();
			Linkage obj = (Linkage)selectionList.getFirstElement();
			this.DBStep1.setSelectedLinkageObj(obj);
			this.DBStep1.setSelectedLinkageDBName(obj.getDbName());
			this.DBStep1.setSelectedLinkageDBPath(obj.getDbPath());
			
		}catch(Exception e){
			System.out.println("Error - ListSelectLinkageDatabase");
		}
		
	}
	
	public void ListSelectWindshieldDatabase(){
		//System.out.println("adsfasdfasdfasdf");
		try{
			IStructuredSelection selectionList =(IStructuredSelection)med.getListViewerWindShieldDatabase().getSelection();
			Windshield obj = (Windshield)selectionList.getFirstElement();
			this.DBStep1.setSelectedWindshieldObj(obj);
			this.DBStep1.setSelectedWindshieldDBName(obj.getDbName());
			this.DBStep1.setSelectedWindshieldDBPath(obj.getDbPath());
			
		}catch(Exception e){
			System.out.println("Error - ListSelectWindshieldDatabase");
		}
	}
	
	public void ListDoubleClickBladeDatabase(){
		try{
			//System.out.println("Double Click - Blade");
			IStructuredSelection selectionList =(IStructuredSelection)med.getListViewerBladeDatabase().getSelection();
			Blade obj = (Blade)selectionList.getFirstElement();
			this.RunExcel(obj.getDbPath());
		}catch(Exception e){
			System.out.println("ERROR - ListDoubleClickBladeDatabase");
		}
		
	}
	
	public void ListDoubleClickLinkageDatabase(){
		try{
			//System.out.println("Double Click - Linkage");
			IStructuredSelection selectionList =(IStructuredSelection)med.getListViewerLinkageDatabase().getSelection();
			Linkage obj = (Linkage)selectionList.getFirstElement();
			this.RunExcel(obj.getDbPath());
		}catch(Exception e){
			System.out.println("ERROR - ListDoubleClickLinkageDatabase");
		}
		
	}
	
	private void RunExcel(String path){
		//System.out.println(path);
		try {
			try{
			    //String[] cmdArray = {"C:/Program Files/Microsoft Office/Office15/excel.exe", path};    //실행할 프로그램과 전달할 인수를 문자열 배열로 만든다.
				String[] cmdArray = {this.ExcelPath, path};    //실행할 프로그램과 전달할 인수를 문자열 배열로 만든다.
			    Runtime.getRuntime().exec(cmdArray);
			}catch(Exception e){
			    System.out.println(e);
			}
		} catch (Exception e) { // 에러 처리
			System.err.println("에러! 엑셀 실행에 실패했습니다.\n" + e.getMessage());
		}
	}
	
	// Part 선택
	public void ListSelectPartDatabase(){
		try{
			this.DBStep2.getTempSelectedPartList().clear();
			IStructuredSelection selectionList =(IStructuredSelection)med.getListViewerPart().getSelection();
			//Image Loading
			Part Pobj = (Part)selectionList.getFirstElement();
			this.LoadingStep2PartImage(Pobj.getPartName());
			
			for(Iterator itr = selectionList.iterator(); itr.hasNext(); ) {
				Part obj = (Part)itr.next();
				//System.out.println(obj.getPartName());
				this.DBStep2.addTempSelectedPartList(obj);
			}
			
			
		}catch(Exception e){
			System.out.println("ERROR - ListSelectPartDatabase");
		}
	}
	// Swapping Part 선택
	public void ListSelectSwappingPartDatabase(){
		try{
			IStructuredSelection selectionList =(IStructuredSelection)med.getListViewerSwappingPart().getSelection();
			SwappingPart obj = (SwappingPart)selectionList.getFirstElement();
			this.DBStep3.setCurrentSwappingPart(obj);
			// Part Name 
			med.getLblPartNameValue().setText(obj.getPartName());
			// Part Type
			med.getLblSwappingTypeValue().setText(obj.getType());
			// MNF Path
			if(obj.getType().equals(Part.Type_MNF)){
				med.getTextMnfFilePath().setText(obj.getPath());
				med.getTextMnfFilePath().setEditable(false);
				//med.getBtnExplorerStep3().setEnabled(false);
				med.getTextBulkFilePath().setText(obj.getBulkPath());
				med.getTextInputdeckPath().setText(obj.getInputdeckPath());
			}else if(obj.getType().equals(Part.Type_NAS)){
				med.getTextMnfFilePath().setText("");
				med.getTextMnfFilePath().setEditable(true);
				//med.getBtnExplorerStep3().setEnabled(true);
				med.getTextBulkFilePath().setText(obj.getBulkPath());
				med.getTextInputdeckPath().setText(obj.getInputdeckPath());
			}else if(obj.getType().equals(Part.Type_OPT)){
				med.getTextMnfFilePath().setText("");
				med.getTextMnfFilePath().setEditable(true);
				med.getTextBulkFilePath().setText(obj.getBulkPath());
				med.getTextInputdeckPath().setText(obj.getInputdeckPath());
			}
		}catch(Exception e){
			System.out.println("ERROR - ListSelectSwappingPartDatabase");
		}
	}
	// Table
	
	// Text
	public void TextChangeMnfFilePath(){
		String textValue = med.getTextMnfFilePath().getText();
		//System.out.println("Mnf Path : " + textValue);
	}
	
	public void TextChangeRadiusValue(){
		String textValue = med.getTextRadiusValue().getText().trim();
		//System.out.println("R : "+textValue);
		this.DBStep1.setRadius(textValue);
	}
	
	public void TextChangeXValue(){
		String textValue = med.getTextXValue().getText().trim();
		//System.out.println("X : "+textValue);
		this.DBStep1.setX(textValue);
	}
	
	public void TextChangeYValue(){
		String textValue = med.getTextYValue().getText().trim();
		//System.out.println("Y : "+textValue);
		this.DBStep1.setY(textValue);
	}
	
	public void TextChangeZValue(){
		String textValue = med.getTextZValue().getText().trim();
		//System.out.println("Z : "+textValue);
		this.DBStep1.setZ(textValue);
	}
	
	public void TextChangeError(){
		String textValue = med.getTextError().getText().trim();
		this.DBStep4.setError(textValue);
	}
	
	public void TextChangeHmax(){
		String textValue = med.getTextHmax().getText().trim();
		this.DBStep4.setHmax(textValue);
	}
	
	public void TextChangeNumberOfStep(){
		String textValue = med.getTextNumberOfStep().getText().trim();
		this.DBStep4.setNubmerOfStep(textValue);
	}
	
	public void TextChangeEndTime(){
		String textValue = med.getTextEndTime().getText().trim();
		this.DBStep4.setEndTime(textValue);
		
		med.getTextEndTimeRange().setText(textValue);
		this.DBStep4.setEndTimeRange(textValue);
	}
	
	public void TextChangedExtraMassRatio(){
		String textValue = med.getTextExtraMassRatio().getText().trim();
		this.DBStep4.setExtraMassRatio(textValue);
	}
	
	public void TextChangedNumberOfCycles(){
		String textValue = med.getTextNumberOfCycles().getText().trim();
		this.DBStep4.setNumberOfCycles(textValue);
	}
	
	public void TextChangedStartTimeRange(){
		String textValue = med.getTextStartTimeRange().getText().trim();
		this.DBStep4.setStartTimeRange(textValue);
	}

	public void TextChangedEndTimeRange(){
		String textValue = med.getTextEndTimeRange().getText().trim();
		this.DBStep4.setEndTimeRange(textValue);
	}
	
	public void TextChangedIncrementFrame(){
		String textValue = med.getTextIncrementFrame().getText().trim();
		this.DBStep4.setIncrementFrame(textValue);
	}
	
	public void TextChangeResultName(){
		String name = med.getTextResultName().getText().trim();
		//System.out.println("Result Name : "+ name);
		this.DBStep4.setExportResultName(name);
		//this.ExportResultName = name;
		
	}
	
	public void TextChangeCycleNumber(){
		String name = med.getTextCycleNumber().getText().trim();
		this.DBStep5.setCycleNumberValue(name);
		
	}
	//
	//
	//
	//
	//
	//
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	
	
	
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	// 
	//
	//
	//  File menu execute and Dialog
	//
	//
	//
	public void executeFileNew() {
		// TODO Auto-generated method stub
		NewDlg dlg = new NewDlg(Display.getCurrent().getActiveShell());
		dlg.open();
	}
	
	public void executeFileNewImpl(String projectName, String workspace){
		//System.out.println("Start New Project");
		//System.out.println("Project Name : "+projectName);
		//System.out.println("Workspace : "+workspace);
		//this.addMsgWindow("Start new project : "+projectName,MessageWindow.Info);
		//this.addMsgWindow("Workspace Path : "+workspace,MessageWindow.Info);
		/////////////////////////////
		// TODO Something....
		this.ProjectName = projectName;
		this.Workspace = workspace;
		// 1. Workspace 생성 
		String folderTree = myUtil.setPath(myUtil.setPath(this.AppPath,DatabaseFolderName.msck_Config), DatabaseFolderName.FolderTree);
		myUtil.makeDir(this.Workspace);
		File source = new File(folderTree); 
		File target = new File(this.Workspace);
		myUtil.copyDirectory(source, target);
		// 2. 생성한 내용   UI 표시
		med.getLblMyprojName().setText(this.ProjectName);
		med.getLblPath().setText(this.Workspace);
		/////////////////////////////
		//System.out.println("End New Dialog");
		this.EnableAllComponent();
		//this.writeDBObj = new WriteDBFile();
		//this.writeDBObj.writeDBfile_Common();
		
		
		
		// ui 셋팅 ///////////////////////////////////////////////////////////////////
		this.currentStep = STEP1;
		
		med.getStackLayout().topControl = med.getCompositeStep1();
		med.getCompositeBottom().layout();
		
		med.getLblStep1().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		med.getLblStep1().setFont(SWTResourceManager.getFont("Arial", 9, SWT.None));
		med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblStep2().setFont(SWTResourceManager.getFont("Arial", 9, SWT.None));
		med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblStep3().setFont(SWTResourceManager.getFont("Arial", 9, SWT.None));
		med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblStep4().setFont(SWTResourceManager.getFont("Arial", 9, SWT.None));
		med.getLblStep5().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblStep5().setFont(SWTResourceManager.getFont("Arial", 9, SWT.None));
		
		med.getTextRadiusValue().setEnabled(true);
		med.getTextXValue().setEnabled(true);
		med.getTextYValue().setEnabled(true);
		med.getTextZValue().setEnabled(true);
		
		med.getBtnSaveAllData().setEnabled(true);
		
		UILabel LabelDatas = UILabel.getInstatnce();
		med.getLblStatusValue().setText(LabelDatas.getLabel(UILabel.READY));
		med.getLblStatusValue().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblStatusValue().setFont(SWTResourceManager.getFont("Arial", 9, SWT.NONE));
		med.getBtnStartSolving().setEnabled(true);
		//med.getBtnStopSolving().setEnabled(false);
		med.getBtnStartAnimation().setEnabled(false);
		med.getBtnExportResult().setEnabled(false);
		
		med.getLblBladeValue().setText(" ");
		med.getLblLinkageValue().setText(" ");
		med.getLblWindshieldValue().setText(" ");
		
		this.isCompletedStep1 = false;
		this.isCompletedStep2 = false;
		this.isCompletedStep3 = false;
		this.isCompletedStep4 = false;
		this.isCompletedStep4_2 = false;
		this.isCompletedStep5 = false;
		
		this.isReadyStep1 = true;
		this.isReadyStep2 = false;
		this.isReadyStep3 = false;
		this.isReadyStep4 = false;
		this.isReadyStep5 = false;
		
		this.SetupComponent(this.currentStep);
		
		med.getBtnSaveAllData().setEnabled(false);
		//Adams 시작 연동
		/*
		if(this.client == null){
			this.client = Client.getInstance();
		}
		if(this.client.isRunning){
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.quit));
			this.clearAllUI();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			this.client.StartAdams();
			this.client.setRunning(true);
		}
		// */
		
		//------------------------ 
		if(this.clientDemon == null){
			this.clientDemon = new ClientDemon();
		}
		if(this.clientDemon.isRunning){
			this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.quit));
			this.clearAllUI();
			try{
				Thread.sleep(2000);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}else{
			this.clientDemon.StartAdams();
			this.clientDemon.setRunning(true);
		}
		
		
		
		
		// Adams가 시작 되었는지 체크
		CheckAdamsStart obj = new CheckAdamsStart();
		Thread th = new Thread(obj);
		th.start();
	}
	
	private void clearAllUI(){
		//Step2 Swapping Part Table 
		TableItem [] items = med.getTableSwappingPart().getItems();
		boolean bDeleted = false;
		for(int i=items.length-1 ; i>=0 ; i--){
			Part obj = this.DBStep2.getSelectedPartList().get(i);
			this.DBStep2.getTableRowList().get(i).disposeControls();
			this.DBStep2.removeTableRowList(obj.getTableEditorInfoObj());
			items[i].dispose();
			//Table에서 삭제
			this.DBStep2.getSelectedPartList().get(i).setSelected(false);
			this.DBStep2.getSelectedPartList().get(i).setPath(Part.Path_Empty);
			this.DBStep2.getSelectedPartList().get(i).setRenamePath(Part.Path_Empty);
			this.DBStep2.getSelectedPartList().get(i).setType(Part.Type_NAS);
			this.DBStep2.removeSelectedPartList(obj);
			if(bDeleted == false){
				bDeleted = true;
			}
		}
		med.getTableSwappingPart().removeAll();
		med.getTableSwappingPart().pack();
		med.getTableSwappingPart().getParent().layout(false);
		
		//Step5 MSR,MSM Table
		TableItem [] items2 = med.getTableFatSolving().getItems();
		boolean bDeleted2 = false;
		for(int i=items2.length-1 ; i>=0 ; i--){
			Part_FatSolving obj = this.DBStep5.getPartList().get(i);
			//this.DBStep2.getTableRowList().get(i).disposeControls();
			this.DBStep5.getTableRowList().get(i).disposeControls();
			//this.DBStep2.removeTableRowList(obj.getTableEditorInfoObj());
			this.DBStep5.removeTableRowList(obj.getTableEditorInfoObj());
			items[i].dispose();
			//Table에서 삭제
			/*
			this.DBStep2.getSelectedPartList().get(i).setSelected(false);
			this.DBStep2.getSelectedPartList().get(i).setPath(Part.Path_Empty);
			this.DBStep2.getSelectedPartList().get(i).setRenamePath(Part.Path_Empty);
			this.DBStep2.getSelectedPartList().get(i).setType(Part.Type_NAS);
			this.DBStep2.removeSelectedPartList(obj);
			//*/
			
			if(bDeleted2 == false){
				bDeleted2 = true;
			}
		}
		med.getTableFatSolving().removeAll();
		med.getTableFatSolving().pack();
		med.getTableFatSolving().getParent().layout(false);
		
		// All List Reset
		med.getListViewerBladeDatabase().getList().removeAll();
		med.getListViewerLinkageDatabase().getList().removeAll();
		med.getListViewerWindShieldDatabase().getList().removeAll();
		med.getListViewerPart().getList().removeAll();
		med.getListViewerSwappingPart().getList().removeAll();
		med.getTableSwappingPart().removeAll();

		//DB Reset
		this.DBStep1 = new DatabaseStep1();
		this.DBStep2 = new DatabaseStep2();
		this.DBStep3 = new DatabaseStep3();
		this.DBStep4 = new DatabaseStep4();
		this.DBStep5 = new DatabaseStep5();
		// Step1로 초기화
		//-> appDatabase 폴더안에 Blade / Linkage 타입별로 데이터 가져오기
		this.LoadingAppDataBase_Blade();
		this.LoadingAppDataBase_Linkage();
		this.LoadingAppDatabase_Windshield();
		//-> List(Blade / Linkage 타입 1을 기본으로 해당 정보  List에 Load
		this.LoadingStep1List_Blade();
		this.LoadingStep1List_Linkage();
		this.LoadingStep1List_Windshield();
	}
	

	public void executeFileOpen() {
		// TODO Auto-generated method stub
		OpenDlg dlg = new OpenDlg(Display.getCurrent().getActiveShell());
		dlg.open();
	}
	
	public void executeFileOpenImpl(String dbFilePath){
		/* 기능 삭제함
		System.out.println("Start Open Project");
		System.out.println("DB File Path : " + dbFilePath);
		/////////////////////////////
		// TODO	Something....
		this.writeDBObj = new WriteDBFile();
		
		/////////////////////////////
		System.out.println("End Open Dialog");
		// */ 
	}
	
	public void executeFileSave() {
		// TODO Auto-generated method stub
		SaveDlg dlg = new SaveDlg(Display.getCurrent().getActiveShell());
		dlg.open();
	}
	
	public void executeFileSaveImpl(){
		//System.out.println("Start Save Project");
		/////////////////////////////
		// TODO Something....
		
		/////////////////////////////
		//System.out.println("End Save Dialog");
	}

	public void executeFilePreference() {
		// TODO Auto-generated method stub
		this.addMsgWindow("Open Preferece", MessageWindow.Info);
		PreferenceDlg dlg = new PreferenceDlg(Display.getCurrent().getActiveShell());
		dlg.open();
	}
	
	public void executeFilePreferenceImpl(ArrayList<String> resultDataList){
		try{
			//System.out.println("Start Preference");
			
			/////////////////////////////
			// TODO Something....
			String preferenceFilePath = myUtil.setPath(myUtil.setPath(this.AppPath, "msck_Config"),"Preference.ini");
			Writer writer = new Writer(preferenceFilePath);
			writer.running(resultDataList);
			
			//this.writeDBObj.writeDBFile_Preference();
			/////////////////////////////
			/*
			System.out.println("End Preference Dialog");
			System.out.println("\n\n============================");
			System.out.println("Excel Path : "+this.ExcelPath);
			System.out.println("Database Path : "+this.DatabasePath);
			System.out.println("Adams Path : "+this.AdamsPath);
			System.out.println("Solver Type: "+this.SolverType);
			System.out.println("============================\n\n");
			// */
		}catch(Exception e){
			
		}
		
	}
	
	public void executeAddBulkDlg(){
		AddBulkDlg dlg = new AddBulkDlg(Display.getCurrent().getActiveShell());
		dlg.open();
	}
	
	public void executeAddBulkDlgImpl(String bulkDataPath){
		//System.out.println("BULK : "+bulkDataPath);
		try{
			if(myUtil.checkPath(bulkDataPath)){
				bulkDataForMNF_step2 = bulkDataPath;
			}else{
				bulkDataForMNF_step2 = Part.Path_Empty;
			}
		}catch(Exception e){
			bulkDataForMNF_step2 = Part.Path_Empty;
		}
	}
	
	private void findingBulk(String path,String partName){
		// mnf 용으로 bulk 받았을때 
		String oriPath = path;
		String exetension = myUtil.getExtensions(oriPath);		
		String newFileName = partName+"_onlyBluk."+exetension;
		String newFilePath = "";
		String resultPath = myUtil.setPath(this.Workspace,DatabaseFolderName.Result);
		
		String InputdeckForMNFPath = myUtil.setPath(resultPath, DatabaseFolderName.InputdeckForMNF);
		String bulkBdf = myUtil.setPath(InputdeckForMNFPath, DatabaseFolderName.RenameBulk);
		newFilePath = myUtil.setPath(bulkBdf, newFileName);	
		
		
		
		Reader reader = new Reader(oriPath);
		reader.running();
		ArrayList<String> BulkDataList = new ArrayList<String>();
		boolean start = false;
		boolean isSimpleBulk = true;
		for(String line : reader.getFileDataList()){
			if(line.contains("ENDDATA")){
				start = false;
			}
			
			if(start){
				if(line.contains("PARAM")){
				}else if(line.contains("DTI")){
				}else if(line.contains("EIGRL")){
				}else if(line.contains("ASET1")){
				}else if(line.contains("SPOINT")){
				}else if(line.contains("QSET1")){
				}else{
					BulkDataList.add(line);
				}
			}
			
			if(line.contains("BEGIN BULK")){
				start = true;
				isSimpleBulk = false;
			}
		}
		
		if(isSimpleBulk){
			BulkDataList.clear();
			for(String line2 : reader.getFileDataList()){
				BulkDataList.add(line2);
			}
		}
		
		Writer writer = new Writer(newFilePath);
		writer.running(this.findingHMSET(BulkDataList));
		
	}
	
	private ArrayList<String> findingHMSET(ArrayList<String> fileDataList){
		ArrayList<String> tokens = new ArrayList<String>();
		ArrayList<HMSETObj> HMSETObjList = new ArrayList<HMSETObj>();
		ArrayList<SETObj> SETObjList = new ArrayList<SETObj>();
		ArrayList<String> AllHMSETDataListForRemove = new ArrayList<String>();
		
		for(int i=0;i<fileDataList.size();i++){
			String line = fileDataList.get(i);
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
						String innerLine = fileDataList.get(j);
						ArrayList<String> innerTokens = myUtil.splitData(innerLine, " ");					
						String lastToken= innerTokens.get(innerTokens.size()-1);
						char lastChar = lastToken.charAt(lastToken.length()-1);
						
						String innerLine2 = fileDataList.get(j+1);
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
			fileDataList.remove(remove);
		}
		
		
		/*
		ArrayList<String> hmsetDataList = new ArrayList<String>();
		ArrayList<Integer> lineNumList = new ArrayList<Integer>();
		ArrayList<String> tokens = new ArrayList<String>();
		
		for(int i=0;i<fileDataList.size();i++){
			
			String line = fileDataList.get(i);
			tokens = myUtil.splitData(line, " ");
			
			if(tokens.size() != 0){
				if(tokens.get(0).equals("$HMSET")){
					hmsetDataList.add(line);
					lineNumList.add(i);
				}
				if(tokens.get(0).equals("SET")){
					hmsetDataList.add(line);
					lineNumList.add(i);
					if(myUtil.splitData_shortFormat(fileDataList.get(i+1)).get(0).equals("+")){
						hmsetDataList.add(fileDataList.get(i+1));
						lineNumList.add(i+1);
						if(myUtil.splitData_shortFormat(fileDataList.get(i+2)).get(0).equals("+")){
							hmsetDataList.add(fileDataList.get(i+2));
							lineNumList.add(i+2);
						}
					}
				}			
			}
		}
		
		
		for(int i = lineNumList.size()-1 ; i>=0 ; i--){
			String str = fileDataList.get(lineNumList.get(i));
			fileDataList.remove((int)lineNumList.get(i));
		}
		// */
		
		ArrayList<String> onlyHMSETData = new ArrayList<String>();
		for(HMSETObj hmsetObj : HMSETObjList){
			String hmsetId = hmsetObj.getID();
			for(SETObj setObj : SETObjList){
				String setID = setObj.getID();
				if(hmsetId.equals(setID)){
					for(String setLine : setObj.getLinesList()){
						onlyHMSETData.add(setLine);
					}
				}
			}
			for(String hmsetLine : hmsetObj.getLinesList()){
				onlyHMSETData.add(hmsetLine);
			}
		}
		
		ArrayList<String> newDataList = new ArrayList<String>();
		for(String line : fileDataList){
			if(line.trim().toLowerCase().equals("begin bulk")){
				for(String newLine : onlyHMSETData){
					newDataList.add(newLine);
				}
				newDataList.add(line);
			}else{
				newDataList.add(line);
			}
		}
		
		return newDataList;
		
	}
	
	/*
	private void currentStatus(){
		/*  
		System.out.println("+++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Step1 is completed : "+this.isCompletedStep1);
		System.out.println("Step2 is completed : "+this.isCompletedStep2);
		System.out.println("Step3 is completed : "+this.isCompletedStep3);
		System.out.println("Step4 is completed : "+this.isCompletedStep4);
		System.out.println("Step5 is completed : "+this.isCompletedStep5);
		
		System.out.println("Step1 is ready : "+this.isReadyStep1);
		System.out.println("Step2 is ready : "+this.isReadyStep2);
		System.out.println("Step3 is ready : "+this.isReadyStep3);
		System.out.println("Step4 is ready : "+this.isReadyStep4);
		System.out.println("Step5 is ready : "+this.isReadyStep5);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++");
		
	}
	// */
	
	public void executeNextStep(){
		//System.out.println("Start Next Step");
		/////////////////////////////
		// TODO Something....
		if(this.currentStep.equals(STEP1)){
			this.CheckStep1();
			if(this.isCompletedStep1){
				this.currentStep = STEP2;
				med.getStackLayout().topControl = med.getCompositeStep2();
				med.getCompositeBottom().layout();
				
				med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				med.getLblStep1().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				
				med.getLblBladeValue().setText(this.DBStep1.getSelectedBladeDBName());
				med.getLblLinkageValue().setText(this.DBStep1.getSelectedLinkageDBName());
				if(this.DBStep1.getWindshieldType().equals(Windshield.TYPE_SPHERE)){
					String value = "["+Windshield.TYPE_SPHERE+"]"+"     R : "+this.DBStep1.getRadius()+"     X : "+this.DBStep1.getX()+"     Y : "+this.DBStep1.getY()+"     Z : "+this.DBStep1.getZ();
					med.getLblWindshieldValue().setText(value);
				}else{
					med.getLblWindshieldValue().setText(this.DBStep1.getSelectedWindshieldDBName());
				}
				
				this.SetupComponent(this.currentStep);
				//this.writeDBObj.WriteDBFileData(STEP1);
				
				//Step2 데이터 Loading
				this.LoadingStep2();
				//this.currentStatus();
				/////////////////////////////////////////////////////////////////////////////////////////
				// Command Server 연결
				
				if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_MODULETANDEM)){
					this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_MT));
					if(this.DBStep1.getWindshieldType().equals(Windshield.TYPE_GEOMETRY)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_2_Geo_MT));
					}else if(this.DBStep1.getWindshieldType().equals(Windshield.TYPE_SPHERE)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_2_Sph_MT));	
					}
				}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALOPPOSED)){
					this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_SO));
					if(this.DBStep1.getWindshieldType().equals(Windshield.TYPE_GEOMETRY)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_2_Geo_SO));
					}else if(this.DBStep1.getWindshieldType().equals(Windshield.TYPE_SPHERE)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_2_Sph_SO));	
					}
				}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALPARALLEL)){
					this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_MT));
					if(this.DBStep1.getWindshieldType().equals(Windshield.TYPE_GEOMETRY)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_2_Geo_SP));
					}else if(this.DBStep1.getWindshieldType().equals(Windshield.TYPE_SPHERE)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_2_Sph_SP));	
					}
				}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALTANDEM)){
					this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_MT));
					if(this.DBStep1.getWindshieldType().equals(Windshield.TYPE_GEOMETRY)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_2_Geo_ST));
					}else if(this.DBStep1.getWindshieldType().equals(Windshield.TYPE_SPHERE)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_2_Sph_ST));	
					}
				}
				this.addMsgWindow("Assem Model...", MessageWindow.Info);
				
				/*
				this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1));
				try {
					Thread.sleep(1000);
					if(this.DBStep1.getWindshieldType().equals(Windshield.TYPE_GEOMETRY)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_2_Geo));
					}else if(this.DBStep1.getWindshieldType().equals(Windshield.TYPE_SPHERE)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd1_2_Sph));	
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// */
				
				/////////////////////////////////////////////////////////////////////////////////////////
			}else {
				JOptionPane.showMessageDialog(null, "Please, Check Selected Data.","Check Stpe1",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.currentStep.equals(STEP2)){
			if(this.DBStep2.isSkiped()){
				this.isCompletedStep2 = true;
				this.isCompletedStep3 = true;
				
				this.isReadyStep3 = true;
				this.isReadyStep4 = true;
				
				this.currentStep = STEP4;
				med.getStackLayout().topControl = med.getCompositeStep4();
				med.getCompositeBottom().layout();
				
				med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				
				if(jobObj.getJobStatus_step1().equals(Job.end)){
					med.getBtnStartSolving().setEnabled(true);
				}else{
					med.getBtnStartSolving().setEnabled(false);
				}
				
				
				this.SetupComponent(this.currentStep);
				//this.writeDBObj.writeDBFile_Step2_Skip();
				//this.writeDBObj.writeDBFile_Step3_Skip();
				
				this.LoadingStep4();
				//this.currentStatus();
				
				/////////////////////////////////////////////////////////////////////////////////////////
				// Command Server 연결
				//this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_skip));
				/////////////////////////////////////////////////////////////////////////////////////////
				if(this.DBStep3.isChanged()){
					this.DBStep3_back = this.DBStep3;
					if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_MODULETANDEM)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back_MT));
					}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALOPPOSED)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back_SO));
					}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALPARALLEL)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back_SP));
					}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALTANDEM)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back_ST));
					}
					//this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back));
					//System.out.println("IN back cmd");
				}
				this.DBStep3.setChanged(false);
				this.isBackStep2_3 = false;
				//System.out.println("###########################");
				//this.DBStep2 = null;
				//this.DBStep2 = new DatabaseStep2();
				this.DBStep3 = null;
				this.DBStep3 = new DatabaseStep3();
				this.DBStep3_back = null;
			}else{
				this.CheckStep2();
				if(this.isCompletedStep2){
					this.currentStep = STEP3;
					this.isReadyStep4 = false;
					this.isCompletedStep4 = false;
					this.isCompletedStep4_2 = false;
					this.isCompletedStep3 = false;
					
					med.getStackLayout().topControl = med.getCompositeStep3();
					med.getCompositeBottom().layout();
					
					med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
					
					this.checkStep1End();
					
					this.SetupComponent(this.currentStep);
					
					//Step3 데이터 Loading
					this.LoadingStep3();
					this.createInputdeckForMNF();
					
				}else {
					JOptionPane.showMessageDialog(null, "Please, Check Selected Data","Check Stpe2",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}else if(this.currentStep.equals(STEP3)){
			if(this.DBStep3.isSkiped()){
				this.currentStep = STEP4;
				
				this.isCompletedStep3 = true;
				this.isReadyStep4 = true;
				
				med.getStackLayout().topControl = med.getCompositeStep4();
				med.getCompositeBottom().layout();
				
				med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				med.getLblStep2().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				
				this.SetupComponent(this.currentStep);
				//this.writeDBObj.writeDBFile_Step2_Skip();
				//this.writeDBObj.writeDBFile_Step3_Skip();
				this.LoadingStep4();
				
				//this.currentStatus();
				/////////////////////////////////////////////////////////////////////////////////////////
				// Command Server 연결 ==> Step2에서 했음 중복되서 주석처리 
				// this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_skip));
				/////////////////////////////////////////////////////////////////////////////////////////
				
				if(this.DBStep3.isChanged()){
					//this.DBStep3_back = this.DBStep3;
					if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_MODULETANDEM)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back_MT));
					}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALOPPOSED)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back_SO));
					}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALPARALLEL)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back_SP));
					}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALTANDEM)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back_ST));
					}
					//this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back));
					//System.out.println("IN back cmd");
				}
				this.DBStep3.setChanged(true);
				this.isBackStep2_3 = true;
				//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				
			}else{
				this.CheckStep3();
				if(this.isCompletedStep3){
					this.currentStep = STEP4;
					
					this.isCompletedStep3 = true;
					this.isReadyStep4 = true;
					
					med.getStackLayout().topControl = med.getCompositeStep4();
					med.getCompositeBottom().layout();
					
					med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					med.getLblStep3().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
					
					this.SetupComponent(this.currentStep);
					//this.writeDBObj.WriteDBFileData(STEP3);
					
					this.LoadingStep4();
					/////////////////////////////////////////////////////////////////////////////////////////
					// Command Server 연결
					if(this.DBStep3.isChanged()){
						//this.DBStep3_back = this.DBStep3;
						if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_MODULETANDEM)){
							this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back_MT));
						}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALOPPOSED)){
							this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back_SO));
						}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALPARALLEL)){
							this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back_SP));
						}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALTANDEM)){
							this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back_ST));
						}
						//this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_Back));
						//System.out.println("IN back cmd");
						
					}
					
					if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_MODULETANDEM)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_MT));
					}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALOPPOSED)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_SO));
					}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALPARALLEL)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_SP));
					}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALTANDEM)){
						this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3_ST));
					}
					//this.SendCMD(CMDFileList.getInstantce().getFileName(CMDFileList.cmd3));
					this.DBStep3.setChanged(true);
					this.isBackStep2_3 = true;
					
					//System.out.println("!!!!!!2 : "+this.DBStep3.isChanged());
					/////////////////////////////////////////////////////////////////////////////////////////
					
				}else {
					JOptionPane.showMessageDialog(null, "Please, Check Selected Data","Check Stpe3",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}else if(this.currentStep.equals(STEP4)){
			if(this.isCompletedStep4 && this.isCompletedStep4_2){
				this.currentStep = STEP5;
				this.isCompletedStep4 = true;
				this.isCompletedStep4_2 = true;
				this.isReadyStep5 = true;
				
				
				med.getStackLayout().topControl = med.getCompositeStep5();
				med.getCompositeBottom().layout();
				
				med.getLblStep5().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				med.getLblStep4().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				
				this.SetupComponent(this.currentStep);
				//this.writeDBObj.WriteDBFileData(STEP4);
				
				this.LoadingStep5();
				
				//UILabel LabelDatas = UILabel.getInstatnce();
				//med.getLblStatusValue().setText(LabelDatas.getLabel(UILabel.READY));
				
			}else{
				JOptionPane.showMessageDialog(null, "Please, Check Selected Data","Check Stpe4",JOptionPane.ERROR_MESSAGE);	
			}
			
		}else if(this.currentStep.equals(STEP5)){
			
		}
			
		this.ChangeLableProperty();
		/////////////////////////////
		//System.out.println("End Next Step Dialog");
		//this.currentStatus();
	}
	
	private void checkStep1End(){
		// model 생성 cmd 실행 중이기때문에 다음단계의 cmd 전달 못하게 버튼 막음
		if(jobObj.getJobStatus_step1().equals(Job.end)){
			med.getBtnSaveAllData().setEnabled(true);
		}else{
			med.getBtnSaveAllData().setEnabled(false);
		}
	}
	
	private void SendCMD(String cmdFileName){
		if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_MODULETANDEM)){
			this.SendCMD_MT(cmdFileName);
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALOPPOSED)){
			this.SendCMD_SO(cmdFileName);
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALPARALLEL)){
			this.SendCMD_SP(cmdFileName);
		}else if(this.DBStep1.getLinkageType().equals(Linkage.TYPE_SERIALTANDEM)){
			this.SendCMD_ST(cmdFileName);
		}
	}
	
	
	
	private void SendCMD_MT(String cmdFileName){
		CMDFileList obj = CMDFileList.getInstantce();
		if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd1_MT))){
			String linkageType = this.DBStep1.getLinkageType();
			if(linkageType.equals(Linkage.TYPE_MODULETANDEM)){
				String CADFileName = "Linkage_Module_Tandem_Basic.xmt_txt";
				//String pltFileName = "Module_Tandem_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.ModuleTandem);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALOPPOSED)){
				String CADFileName = "Linkage_Serial_Opposed_Basic.xmt_txt";
				//String pltFileName = "Serial_Opposed_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.SerialOpposed);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALPARALLEL)){
				String CADFileName = "Linkage_Serial_Parallel_Basic.xmt_txt";
				//String pltFileName = "Serial_Parallel_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.SerialParallel);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALTANDEM)){
				String CADFileName = "Linkage_Serial_Tandem_Basic.xmt_txt";
				//String pltFileName = "Serial_Tandem_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.SerialTandem);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}
			
			Step1_cmd1_MT cmdObj = new Step1_cmd1_MT();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step1);
			
			Thread th = new Thread(this.clientDemon);
			th.setDaemon(true);
			th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd1_2_Geo_MT))){
			Step1_cmd1_2_Geo_MT cmdObj = new Step1_cmd1_2_Geo_MT();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step1_Geo);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd1_2_Sph_MT))){
			Step1_cmd1_2_Sph_MT cmdObj = new Step1_cmd1_2_Sph_MT();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step1_Sph);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd3_MT))){
			// Step1에서 복사한 linkage CAD 파일 지우기
			String linkageType = this.DBStep1.getLinkageType();
			if(linkageType.equals(Linkage.TYPE_MODULETANDEM)){
				String CADFileName = "Linkage_Module_Tandem_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALOPPOSED)){
				String CADFileName = "Linkage_Serial_Opposed_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALPARALLEL)){
				String CADFileName = "Linkage_Serial_Parallel_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALTANDEM)){
				String CADFileName = "Linkage_Serial_Tandem_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}
			// mnf 복사
			String ResultFolder = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
			String SwappingMNFFolder = myUtil.setPath(ResultFolder, DatabaseFolderName.SwappingMNF);
			for(SwappingPart SPobj : this.DBStep3.getSwappingPartList()){
				String oriFile = SPobj.getPath();
				String fileName = myUtil.getFileName(oriFile)+".mnf";
				String destFile = myUtil.setPath(SwappingMNFFolder, fileName);
				myUtil.fileCopy(oriFile, destFile);
			}
			
			
			// 
			Step2_3_cmd3_MT cmdObj = new Step2_3_cmd3_MT();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step3);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd3_Back_MT))){
			// Step3에서 생성한 
			String Result = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
			String SwappingMNF = myUtil.setPath(Result, DatabaseFolderName.SwappingMNF);
			//myUtil.fileDelete(SwappingMNF);
			myUtil.deleteDirectory(new File(SwappingMNF));
			myUtil.makeDir(SwappingMNF);
			//
			Step2_3_cmd3_Back_MT cmdObj = new Step2_3_cmd3_Back_MT();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step3);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_Simulation_MT))){

			UILabel LabelDatas = UILabel.getInstatnce();
			med.getLblStatusValue().setText(LabelDatas.getLabel(UILabel.RUNNING));
			med.getLblStatusValue().setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));

			Step4_cmd4_Simulation_MT cmdObj = new Step4_cmd4_Simulation_MT();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_Simulation);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_Animation_MT))){
			Step4_cmd4_Animation_MT cmdObj = new Step4_cmd4_Animation_MT();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step4_Animation);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_AssemModel_MT))){
			Step4_cmd4_AssemModel_MT cmdObj = new Step4_cmd4_AssemModel_MT();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_Assem);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_DAC_MT))){
			if(this.DBStep4.getExportResultName().equals(DatabaseFolderName.Base)){
				String Result = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
				String DAC = myUtil.setPath(Result, DatabaseFolderName.DAC);
				String resultNameFolder_DAC = myUtil.setPath(DAC, DatabaseFolderName.Base);
				//3) Result\DAC_Result
				myUtil.deleteDirectory(new File(resultNameFolder_DAC));
				myUtil.makeDir(resultNameFolder_DAC);
			}
			Step4_cmd4_DAC_MT cmdObj = new Step4_cmd4_DAC_MT();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_DAC);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}
		else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_AssemModelBin_MT))){
			Step4_cmd4_AssemModelBin_MT cmdObj = new Step4_cmd4_AssemModelBin_MT();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_AssemBin);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_Force_MT))){
			if(this.DBStep4.getExportResultName().equals(DatabaseFolderName.Base)){
				String Result = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
				String Force = myUtil.setPath(Result, DatabaseFolderName.Force);
				String resultNameFolder_Force = myUtil.setPath(Force, DatabaseFolderName.Base);
				//2) Result\Adams_Result\Force
				myUtil.deleteDirectory(new File(resultNameFolder_Force));
				myUtil.makeDir(resultNameFolder_Force);
			}
			
			Step4_cmd4_Force_MT cmdObj = new Step4_cmd4_Force_MT();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_Force);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_NodeInfomation_MT))){
			Step4_cmd4_NodeInformation_MT cmdObj = new Step4_cmd4_NodeInformation_MT();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step4_NodeInformation);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_BladePosition_MT))){
			Step4_cmd4_BladePosition_MT cmdObj = new Step4_cmd4_BladePosition_MT();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step4_BladePosition);
		}
		else if(cmdFileName.equals(obj.getFileName(CMDFileList.quit))){
			StepAll_cmd_quit cmdObj = new StepAll_cmd_quit();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.RESTART);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
			try {
				Thread.sleep(3000);
				/* */
				//Adams 시작 연동
				//this.client = Client.getInstance();
				//client.StartAdams();
				this.clientDemon = new ClientDemon();
				this.clientDemon.StartAdams();
				// */
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/* */
	private void SendCMD_SO(String cmdFileName){
		CMDFileList obj = CMDFileList.getInstantce();
		if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd1_SO))){
			String linkageType = this.DBStep1.getLinkageType();
			if(linkageType.equals(Linkage.TYPE_MODULETANDEM)){
				String CADFileName = "Linkage_Module_Tandem_Basic.xmt_txt";
				//String pltFileName = "Module_Tandem_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.ModuleTandem);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALOPPOSED)){
				String CADFileName = "Linkage_Serial_Opposed_Basic.xmt_txt";
				//String pltFileName = "Serial_Opposed_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.SerialOpposed);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALPARALLEL)){
				String CADFileName = "Linkage_Serial_Parallel_Basic.xmt_txt";
				//String pltFileName = "Serial_Parallel_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.SerialParallel);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALTANDEM)){
				String CADFileName = "Linkage_Serial_Tandem_Basic.xmt_txt";
				//String pltFileName = "Serial_Tandem_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.SerialTandem);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}
			
			Step1_cmd1_SO cmdObj = new Step1_cmd1_SO();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step1);
			
			Thread th = new Thread(this.clientDemon);
			th.setDaemon(true);
			th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd1_2_Geo_SO))){
			Step1_cmd1_2_Geo_SO cmdObj = new Step1_cmd1_2_Geo_SO();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step1_Geo);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd1_2_Sph_SO))){
			Step1_cmd1_2_Sph_SO cmdObj = new Step1_cmd1_2_Sph_SO();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step1_Sph);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd3_SO))){
			// Step1에서 복사한 linkage CAD 파일 지우기
			String linkageType = this.DBStep1.getLinkageType();
			if(linkageType.equals(Linkage.TYPE_MODULETANDEM)){
				String CADFileName = "Linkage_Module_Tandem_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALOPPOSED)){
				String CADFileName = "Linkage_Serial_Opposed_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALPARALLEL)){
				String CADFileName = "Linkage_Serial_Parallel_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALTANDEM)){
				String CADFileName = "Linkage_Serial_Tandem_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}
			// mnf 복사
			String ResultFolder = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
			String SwappingMNFFolder = myUtil.setPath(ResultFolder, DatabaseFolderName.SwappingMNF);
			for(SwappingPart SPobj : this.DBStep3.getSwappingPartList()){
				String oriFile = SPobj.getPath();
				String fileName = myUtil.getFileName(oriFile)+".mnf";
				String destFile = myUtil.setPath(SwappingMNFFolder, fileName);
				myUtil.fileCopy(oriFile, destFile);
			}
			
			
			// 
			Step2_3_cmd3_SO cmdObj = new Step2_3_cmd3_SO();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step3);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd3_Back_SO))){
			// Step3에서 생성한 
			String Result = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
			String SwappingMNF = myUtil.setPath(Result, DatabaseFolderName.SwappingMNF);
			//myUtil.fileDelete(SwappingMNF);
			myUtil.deleteDirectory(new File(SwappingMNF));
			myUtil.makeDir(SwappingMNF);
			//
			Step2_3_cmd3_Back_SO cmdObj = new Step2_3_cmd3_Back_SO();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step3);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_Simulation_SO))){

			UILabel LabelDatas = UILabel.getInstatnce();
			med.getLblStatusValue().setText(LabelDatas.getLabel(UILabel.RUNNING));
			med.getLblStatusValue().setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));

			Step4_cmd4_Simulation_SO cmdObj = new Step4_cmd4_Simulation_SO();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_Simulation);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_Animation_SO))){
			Step4_cmd4_Animation_SO cmdObj = new Step4_cmd4_Animation_SO();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step4_Animation);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_AssemModel_SO))){
			Step4_cmd4_AssemModel_SO cmdObj = new Step4_cmd4_AssemModel_SO();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_Assem);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_DAC_SO))){
			if(this.DBStep4.getExportResultName().equals(DatabaseFolderName.Base)){
				String Result = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
				String DAC = myUtil.setPath(Result, DatabaseFolderName.DAC);
				String resultNameFolder_DAC = myUtil.setPath(DAC, DatabaseFolderName.Base);
				//3) Result\DAC_Result
				myUtil.deleteDirectory(new File(resultNameFolder_DAC));
				myUtil.makeDir(resultNameFolder_DAC);
			}
			Step4_cmd4_DAC_SO cmdObj = new Step4_cmd4_DAC_SO();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_DAC);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}
		else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_AssemModelBin_SO))){
			Step4_cmd4_AssemModelBin_SO cmdObj = new Step4_cmd4_AssemModelBin_SO();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_AssemBin);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_Force_SO))){
			if(this.DBStep4.getExportResultName().equals(DatabaseFolderName.Base)){
				String Result = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
				String Force = myUtil.setPath(Result, DatabaseFolderName.Force);
				String resultNameFolder_Force = myUtil.setPath(Force, DatabaseFolderName.Base);
				//2) Result\Adams_Result\Force
				myUtil.deleteDirectory(new File(resultNameFolder_Force));
				myUtil.makeDir(resultNameFolder_Force);
			}
			
			Step4_cmd4_Force_SO cmdObj = new Step4_cmd4_Force_SO();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_Force);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_NodeInfomation_SO))){
			Step4_cmd4_NodeInformation_SO cmdObj = new Step4_cmd4_NodeInformation_SO();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step4_NodeInformation);
			
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_BladePosition_SO))){
			Step4_cmd4_BladePosition_SO cmdObj = new Step4_cmd4_BladePosition_SO();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step4_BladePosition);
		}
		else if(cmdFileName.equals(obj.getFileName(CMDFileList.quit))){
			StepAll_cmd_quit cmdObj = new StepAll_cmd_quit();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.RESTART);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
			try {
				Thread.sleep(3000);
				//Adams 시작 연동
				//this.client = Client.getInstance();
				//client.StartAdams();
				this.clientDemon = new ClientDemon();
				this.clientDemon.StartAdams();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void SendCMD_SP(String cmdFileName){
		CMDFileList obj = CMDFileList.getInstantce();
		if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd1_SP))){
			String linkageType = this.DBStep1.getLinkageType();
			if(linkageType.equals(Linkage.TYPE_MODULETANDEM)){
				String CADFileName = "Linkage_Module_Tandem_Basic.xmt_txt";
				//String pltFileName = "Module_Tandem_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.ModuleTandem);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALOPPOSED)){
				String CADFileName = "Linkage_Serial_Opposed_Basic.xmt_txt";
				//String pltFileName = "Serial_Opposed_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.SerialOpposed);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALPARALLEL)){
				String CADFileName = "Linkage_Serial_Parallel_Basic.xmt_txt";
				//String pltFileName = "Serial_Parallel_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.SerialParallel);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALTANDEM)){
				String CADFileName = "Linkage_Serial_Tandem_Basic.xmt_txt";
				//String pltFileName = "Serial_Tandem_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.SerialTandem);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}
			
			Step1_cmd1_SP cmdObj = new Step1_cmd1_SP();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step1);
			
			Thread th = new Thread(this.clientDemon);
			th.setDaemon(true);
			th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd1_2_Geo_SP))){
			Step1_cmd1_2_Geo_SP cmdObj = new Step1_cmd1_2_Geo_SP();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step1_Geo);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd1_2_Sph_SP))){
			Step1_cmd1_2_Sph_SP cmdObj = new Step1_cmd1_2_Sph_SP();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step1_Sph);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd3_SP))){
			// Step1에서 복사한 linkage CAD 파일 지우기
			String linkageType = this.DBStep1.getLinkageType();
			if(linkageType.equals(Linkage.TYPE_MODULETANDEM)){
				String CADFileName = "Linkage_Module_Tandem_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALOPPOSED)){
				String CADFileName = "Linkage_Serial_Opposed_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALPARALLEL)){
				String CADFileName = "Linkage_Serial_Parallel_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALTANDEM)){
				String CADFileName = "Linkage_Serial_Tandem_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}
			// mnf 복사
			String ResultFolder = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
			String SwappingMNFFolder = myUtil.setPath(ResultFolder, DatabaseFolderName.SwappingMNF);
			for(SwappingPart SPobj : this.DBStep3.getSwappingPartList()){
				String oriFile = SPobj.getPath();
				String fileName = myUtil.getFileName(oriFile)+".mnf";
				String destFile = myUtil.setPath(SwappingMNFFolder, fileName);
				myUtil.fileCopy(oriFile, destFile);
			}
			
			
			// 
			Step2_3_cmd3_SP cmdObj = new Step2_3_cmd3_SP();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step3);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd3_Back_SP))){
			// Step3에서 생성한 
			String Result = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
			String SwappingMNF = myUtil.setPath(Result, DatabaseFolderName.SwappingMNF);
			//myUtil.fileDelete(SwappingMNF);
			myUtil.deleteDirectory(new File(SwappingMNF));
			myUtil.makeDir(SwappingMNF);
			//
			Step2_3_cmd3_Back_SP cmdObj = new Step2_3_cmd3_Back_SP();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step3);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_Simulation_SP))){

			UILabel LabelDatas = UILabel.getInstatnce();
			med.getLblStatusValue().setText(LabelDatas.getLabel(UILabel.RUNNING));
			med.getLblStatusValue().setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));

			Step4_cmd4_Simulation_SP cmdObj = new Step4_cmd4_Simulation_SP();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_Simulation);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_Animation_SP))){
			Step4_cmd4_Animation_SP cmdObj = new Step4_cmd4_Animation_SP();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step4_Animation);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_AssemModel_SP))){
			Step4_cmd4_AssemModel_SP cmdObj = new Step4_cmd4_AssemModel_SP();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_Assem);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_DAC_SP))){
			//System.out.println("RERERESULT NAME : "+this.DBStep4.getExportResultName());
			if(this.DBStep4.getExportResultName().equals(DatabaseFolderName.Base)){
				String Result = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
				String DAC = myUtil.setPath(Result, DatabaseFolderName.DAC);
				String resultNameFolder_DAC = myUtil.setPath(DAC, DatabaseFolderName.Base);
				//3) Result\DAC_Result
				myUtil.deleteDirectory(new File(resultNameFolder_DAC));
				myUtil.makeDir(resultNameFolder_DAC);
			}
			Step4_cmd4_DAC_SP cmdObj = new Step4_cmd4_DAC_SP();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_DAC);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}
		else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_AssemModelBin_SP))){
			Step4_cmd4_AssemModelBin_SP cmdObj = new Step4_cmd4_AssemModelBin_SP();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_AssemBin);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_Force_SP))){
			if(this.DBStep4.getExportResultName().equals(DatabaseFolderName.Base)){
				String Result = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
				String Force = myUtil.setPath(Result, DatabaseFolderName.Force);
				String resultNameFolder_Force = myUtil.setPath(Force, DatabaseFolderName.Base);
				//2) Result\Adams_Result\Force
				myUtil.deleteDirectory(new File(resultNameFolder_Force));
				myUtil.makeDir(resultNameFolder_Force);
			}
			
			Step4_cmd4_Force_SP cmdObj = new Step4_cmd4_Force_SP();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_Force);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_NodeInfomation_SP))){
			Step4_cmd4_NodeInformation_SP cmdObj = new Step4_cmd4_NodeInformation_SP();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step4_NodeInformation);
			
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_BladePosition_SP))){
			Step4_cmd4_BladePosition_SP cmdObj = new Step4_cmd4_BladePosition_SP();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step4_BladePosition);
		}
		else if(cmdFileName.equals(obj.getFileName(CMDFileList.quit))){
			StepAll_cmd_quit cmdObj = new StepAll_cmd_quit();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.RESTART);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
			try {
				Thread.sleep(3000);
				//Adams 시작 연동
				this.clientDemon = new ClientDemon();
				this.clientDemon.StartAdams();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void SendCMD_ST(String cmdFileName){
		CMDFileList obj = CMDFileList.getInstantce();
		if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd1_ST))){
			String linkageType = this.DBStep1.getLinkageType();
			if(linkageType.equals(Linkage.TYPE_MODULETANDEM)){
				String CADFileName = "Linkage_Module_Tandem_Basic.xmt_txt";
				//String pltFileName = "Module_Tandem_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.ModuleTandem);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALOPPOSED)){
				String CADFileName = "Linkage_Serial_Opposed_Basic.xmt_txt";
				//String pltFileName = "Serial_Opposed_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.SerialOpposed);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALPARALLEL)){
				String CADFileName = "Linkage_Serial_Parallel_Basic.xmt_txt";
				//String pltFileName = "Serial_Parallel_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.SerialParallel);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALTANDEM)){
				String CADFileName = "Linkage_Serial_Tandem_Basic.xmt_txt";
				//String pltFileName = "Serial_Tandem_Graph.plt";
				
				String dbFolder = myUtil.setPath(this.AppPath, DatabaseFolderName.msck_Database);
				String ModelFolder = myUtil.setPath(dbFolder, DatabaseFolderName.Model);
				String linkageFolder = myUtil.setPath(ModelFolder, DatabaseFolderName.Linkage);
				String typeFolder = myUtil.setPath(linkageFolder, DatabaseFolderName.SerialTandem);
				String CADfilePath = myUtil.setPath(typeFolder, CADFileName);
				//String PLTFilePath = myUtil.setPath(typeFolder, pltFileName);
				
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destCADFilePath = myUtil.setPath(jobSpace, CADFileName);
				//String destPltFilePath = myUtil.setPath(jobSpace, pltFileName);
				
				myUtil.fileCopy(CADfilePath, destCADFilePath);
				//myUtil.fileCopy(PLTFilePath, destPltFilePath);
			}
			
			Step1_cmd1_ST cmdObj = new Step1_cmd1_ST();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step1);
			
			Thread th = new Thread(this.clientDemon);
			th.setDaemon(true);
			th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd1_2_Geo_ST))){
			Step1_cmd1_2_Geo_ST cmdObj = new Step1_cmd1_2_Geo_ST();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step1_Geo);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd1_2_Sph_ST))){
			Step1_cmd1_2_Sph_ST cmdObj = new Step1_cmd1_2_Sph_ST();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step1_Sph);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd3_ST))){
			// Step1에서 복사한 linkage CAD 파일 지우기
			String linkageType = this.DBStep1.getLinkageType();
			if(linkageType.equals(Linkage.TYPE_MODULETANDEM)){
				String CADFileName = "Linkage_Module_Tandem_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALOPPOSED)){
				String CADFileName = "Linkage_Serial_Opposed_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALPARALLEL)){
				String CADFileName = "Linkage_Serial_Parallel_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}else if(linkageType.equals(Linkage.TYPE_SERIALTANDEM)){
				String CADFileName = "Linkage_Serial_Tandem_Basic.xmt_txt";
				String jobSpace = myUtil.setPath(this.Workspace, DatabaseFolderName.JobSpace);
				String destFilePath = myUtil.setPath(jobSpace, CADFileName);
				//myUtil.fileDelete(destFilePath);
			}
			// mnf 복사
			String ResultFolder = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
			String SwappingMNFFolder = myUtil.setPath(ResultFolder, DatabaseFolderName.SwappingMNF);
			for(SwappingPart SPobj : this.DBStep3.getSwappingPartList()){
				String oriFile = SPobj.getPath();
				String fileName = myUtil.getFileName(oriFile)+".mnf";
				String destFile = myUtil.setPath(SwappingMNFFolder, fileName);
				myUtil.fileCopy(oriFile, destFile);
			}
			
			
			// 
			Step2_3_cmd3_ST cmdObj = new Step2_3_cmd3_ST();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step3);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd3_Back_ST))){
			// Step3에서 생성한 
			String Result = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
			String SwappingMNF = myUtil.setPath(Result, DatabaseFolderName.SwappingMNF);
			//myUtil.fileDelete(SwappingMNF);
			myUtil.deleteDirectory(new File(SwappingMNF));
			myUtil.makeDir(SwappingMNF);
			//
			Step2_3_cmd3_Back_ST cmdObj = new Step2_3_cmd3_Back_ST();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step3);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_Simulation_ST))){

			UILabel LabelDatas = UILabel.getInstatnce();
			med.getLblStatusValue().setText(LabelDatas.getLabel(UILabel.RUNNING));
			med.getLblStatusValue().setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));

			Step4_cmd4_Simulation_ST cmdObj = new Step4_cmd4_Simulation_ST();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_Simulation);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_Animation_ST))){
			Step4_cmd4_Animation_ST cmdObj = new Step4_cmd4_Animation_ST();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step4_Animation);
			
			//Thread th = new Thread(this.client);
			//th.start();
			
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_AssemModel_ST))){
			Step4_cmd4_AssemModel_ST cmdObj = new Step4_cmd4_AssemModel_ST();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_Assem);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_DAC_ST))){
			if(this.DBStep4.getExportResultName().equals(DatabaseFolderName.Base)){
				String Result = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
				String DAC = myUtil.setPath(Result, DatabaseFolderName.DAC);
				String resultNameFolder_DAC = myUtil.setPath(DAC, DatabaseFolderName.Base);
				//3) Result\DAC_Result
				myUtil.deleteDirectory(new File(resultNameFolder_DAC));
				myUtil.makeDir(resultNameFolder_DAC);
			}
			Step4_cmd4_DAC_ST cmdObj = new Step4_cmd4_DAC_ST();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_DAC);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}
		else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_AssemModelBin_ST))){
			Step4_cmd4_AssemModelBin_ST cmdObj = new Step4_cmd4_AssemModelBin_ST();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_AssemBin);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_Force_ST))){
			if(this.DBStep4.getExportResultName().equals(DatabaseFolderName.Base)){
				String Result = myUtil.setPath(this.Workspace, DatabaseFolderName.Result);
				String Force = myUtil.setPath(Result, DatabaseFolderName.Force);
				String resultNameFolder_Force = myUtil.setPath(Force, DatabaseFolderName.Base);
				//2) Result\Adams_Result\Force
				myUtil.deleteDirectory(new File(resultNameFolder_Force));
				myUtil.makeDir(resultNameFolder_Force);
			}
			
			Step4_cmd4_Force_ST cmdObj = new Step4_cmd4_Force_ST();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.Step4_Force);
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_NodeInfomation_ST))){
			Step4_cmd4_NodeInformation_ST cmdObj = new Step4_cmd4_NodeInformation_ST();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step4_NodeInformation);
			
			
			//Thread th = new Thread(this.client);
			//th.start();
		}else if(cmdFileName.equals(obj.getFileName(CMDFileList.cmd4_BladePosition_ST))){
			Step4_cmd4_BladePosition_ST cmdObj = new Step4_cmd4_BladePosition_ST();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(), ClientDemon.Step4_BladePosition);
		}
		else if(cmdFileName.equals(obj.getFileName(CMDFileList.quit))){
			StepAll_cmd_quit cmdObj = new StepAll_cmd_quit();
			cmdObj.running();
			this.clientDemon.SendCMD(cmdObj.getNew_CMDPath(),ClientDemon.RESTART);
			
			//Thread th = new Thread(this.client);
			//th.start();
			//Adams 시작 연동
			try {
				Thread.sleep(3000);
				this.clientDemon = new ClientDemon();
				this.clientDemon.StartAdams();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// */
	
	//
	//
	//
	//
	//
	//
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	// 
	//
	//
	//  Get-Set Method
	//
	//
	//
	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public String getWorkspace() {
		return Workspace;
	}

	public void setWorkspace(String workspace) {
		Workspace = workspace;
	}

	public String getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(String currentStep) {
		this.currentStep = currentStep;
	}
	
	public DatabaseStep1 getDBStep1() {
		return DBStep1;
	}
	
	public void setDBStep1(DatabaseStep1 dBStep1) {
		DBStep1 = dBStep1;
	}
	
	public DatabaseStep2 getDBStep2() {
		return DBStep2;
	}
	
	public void setDBStep2(DatabaseStep2 dBStep2) {
		DBStep2 = dBStep2;
	}
	
	public DatabaseStep3 getDBStep3() {
		return DBStep3;
	}
	
	public void setDBStep3(DatabaseStep3 dBStep3) {
		DBStep3 = dBStep3;
	}
	
	public DatabaseStep4 getDBStep4() {
		return DBStep4;
	}
	
	public void setDBStep4(DatabaseStep4 dBStep4) {
		DBStep4 = dBStep4;
	}
	
	public DatabaseStep5 getDBStep5() {
		return DBStep5;
	}
	
	public void setDBStep5(DatabaseStep5 dBStep5) {
		DBStep5 = dBStep5;
	}

	public boolean isCompletedStep1() {
		return isCompletedStep1;
	}

	public void setCompletedStep1(boolean isCompletedStep1) {
		this.isCompletedStep1 = isCompletedStep1;
	}

	public boolean isCompletedStep2() {
		return isCompletedStep2;
	}

	public void setCompletedStep2(boolean isCompletedStep2) {
		this.isCompletedStep2 = isCompletedStep2;
	}

	public boolean isCompletedStep3() {
		return isCompletedStep3;
	}

	public void setCompletedStep3(boolean isCompletedStep3) {
		this.isCompletedStep3 = isCompletedStep3;
	}

	public boolean isCompletedStep4() {
		return isCompletedStep4;
	}

	public void setCompletedStep4(boolean isCompletedStep4) {
		this.isCompletedStep4 = isCompletedStep4;
	}

	public boolean isCompletedStep5() {
		return isCompletedStep5;
	}

	public void setCompletedStep5(boolean isCompletedStep5) {
		this.isCompletedStep5 = isCompletedStep5;
	}


	public boolean isReadyStep1() {
		return isReadyStep1;
	}


	public void setReadyStep1(boolean isReadyStep1) {
		this.isReadyStep1 = isReadyStep1;
	}


	public boolean isReadyStep2() {
		return isReadyStep2;
	}


	public void setReadyStep2(boolean isReadyStep2) {
		this.isReadyStep2 = isReadyStep2;
	}


	public boolean isReadyStep3() {
		return isReadyStep3;
	}


	public void setReadyStep3(boolean isReadyStep3) {
		this.isReadyStep3 = isReadyStep3;
	}


	public boolean isReadyStep4() {
		return isReadyStep4;
	}


	public void setReadyStep4(boolean isReadyStep4) {
		this.isReadyStep4 = isReadyStep4;
	}


	public boolean isReadyStep5() {
		return isReadyStep5;
	}


	public void setReadyStep5(boolean isReadyStep5) {
		this.isReadyStep5 = isReadyStep5;
	}


	public String getExcelPath() {
		return ExcelPath;
	}


	public void setExcelPath(String excelPath) {
		ExcelPath = excelPath;
	}


	public String getDatabasePath() {
		return DatabasePath;
	}


	public void setDatabasePath(String databasePath) {
		DatabasePath = databasePath;
	}


	public String getAdamsPath() {
		return AdamsPath;
	}


	public void setAdamsPath(String adamsPath) {
		AdamsPath = adamsPath;
	}

	/*
	public String getSolverType() {
		return SolverType;
	}


	public void setSolverType(String solverType) {
		SolverType = solverType;
	}
	// */

	public String getEIGRL_ND() {
		//System.out.println("NDNDND : "+EIGRL_ND);
		return EIGRL_ND;
	}


	public void setEIGRL_ND(String eIGRL_ND) {
		EIGRL_ND = eIGRL_ND;
	}


	public String getHyperWorksPath() {
		return HyperWorksPath;
	}


	public void setHyperWorksPath(String hyperWorksPath) {
		HyperWorksPath = hyperWorksPath;
	}


	public DatabaseStep3 getDBStep3_back() {
		return DBStep3_back;
	}


	public void setDBStep3_back(DatabaseStep3 dBStep3_back) {
		DBStep3_back = dBStep3_back;
	}


	public MessageWindow getMsgWindow() {
		return MsgWindow;
	}


	public void setMsgWindow(MessageWindow msgWindow) {
		MsgWindow = msgWindow;
	}


	public String getFemfatPath() {
		return FemfatPath;
	}


	public void setFemfatPath(String femfatPath) {
		FemfatPath = femfatPath;
	}

	/*
	public String getExportResultName() {
		return ExportResultName;
	}


	public void setExportResultName(String exportResultName) {
		ExportResultName = exportResultName;
	}
	// */
	/*
	public String getAdamsExePath() {
		return AdamsExePath;
	}


	public void setAdamsExePath(String adamsExePath) {
		AdamsExePath = adamsExePath;
	}
	

	public String getAdamsViewOption() {
		return AdamsViewOption;
	}


	public void setAdamsViewOption(String adamsViewOption) {
		AdamsViewOption = adamsViewOption;
	}
	// */
	//
	//
	//
	//
	//
	//
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////


	
}
