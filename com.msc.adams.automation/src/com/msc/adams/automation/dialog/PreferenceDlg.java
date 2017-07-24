package com.msc.adams.automation.dialog;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.wb.swt.SWTResourceManager;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.MessageWindow;
import com.msc.adams.automation.datas.Part;
import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.parser.ParserDefault;
import com.msc.util.myUtil;

import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class PreferenceDlg extends Dialog {
	private MainController MC = MainController.getInstance();
	
	private static final String NastranType = "Nastran";
	private static final String AbaqusType = "Abaqus";
	private static final String ExcelFile_T = "ExcelFile";
	private static final String DatabaseFolder_T = "DatabaseFolder"; 
	private static final String AdamsFolder_T = "AdamsFolder";
	private static final String SolverType_T = "SolverTyepe"; 
	private static final String EIGRL_ND_T	= "EIGRL_ND";
	private static final String HyperWorksFolder_T = "HyperWorksFolder";
	private static final String FemfatFolder_T = "FemfatFolder";
	private static final String ResultEmpty = "EMPTY";
	
	private String textExcelPath = "";
	private String textDatabasePath = "";
	private String textAdamsPath = "";
	private String SolverType = NastranType;
	private String textEIGRL_ND = "";
	private String textHyperWorksPath = "";
	private String textFemfatPath = "";
	
	private ArrayList<String> resultDataList;
	
	private Text textExcel;
	private Text textDatabase;
	private Text textAdams;
	private Text textEIGRL;
	private Text textHyperWorks;
	private Text textFemfat;
	
	private Button btnNastran;
	private Button btnAbaqus;
	
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public PreferenceDlg(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.BORDER);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		FormLayout fl_container = new FormLayout();
		//fl_container.spacing = 10;
		fl_container.marginWidth = 10;
		fl_container.marginTop = 10;
		fl_container.marginRight = 10;
		fl_container.marginLeft = 10;
		fl_container.marginHeight = 10;
		fl_container.marginBottom = 10;
		container.setLayout(fl_container);
		
		Label lblPreference = new Label(container, SWT.NONE);
		lblPreference.setText("Preference");
		lblPreference.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblPreference = new FormData();
		fd_lblPreference.top = new FormAttachment(0);
		fd_lblPreference.left = new FormAttachment(0);
		lblPreference.setLayoutData(fd_lblPreference);
		
		Label lblExcelExcutionFile = new Label(container, SWT.NONE);
		lblExcelExcutionFile.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblExcelExcutionFile = new FormData();
		fd_lblExcelExcutionFile.top = new FormAttachment(lblPreference, 24);
		fd_lblExcelExcutionFile.left = new FormAttachment(lblPreference, 0, SWT.LEFT);
		lblExcelExcutionFile.setLayoutData(fd_lblExcelExcutionFile);
		lblExcelExcutionFile.setText("Excel Execution File Path");
		
		textExcel = new Text(container, SWT.BORDER);
		textExcel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textExcel.setEditable(false);
		textExcel.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				textExcelPath = text.getText().toString().trim();
			}
		});
		FormData fd_textExcel = new FormData();
		fd_textExcel.top = new FormAttachment(lblExcelExcutionFile, 6);
		fd_textExcel.left = new FormAttachment(lblPreference,0,SWT.LEFT);
		fd_textExcel.right = new FormAttachment(100, -20);
		textExcel.setLayoutData(fd_textExcel);
		textExcel.setText(MC.getExcelPath());
		textExcelPath = MC.getExcelPath();
		
		final Button btnExcel = new Button(container, SWT.NONE);
		btnExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(btnExcel.getShell(),SWT.OPEN);
				dlg.setText("Select Excel Excution File");
				
				String [] extNames = {"EXE(*.exe)"};
				String [] extType = {"*.exe"};
				
				dlg.setFilterNames(extNames);
				dlg.setFilterExtensions(extType);
				
				dlg.setFilterNames(extNames);
				String path = dlg.open();
				if (path != null){
					textExcel.setText(path);
					textExcelPath = path;
				}else {
					textExcel.setText(ResultEmpty);
					textExcelPath = ResultEmpty;
				}
			}
		});
		FormData fd_btnExcel = new FormData();
		fd_btnExcel.top = new FormAttachment(textExcel, -2, SWT.TOP);
		fd_btnExcel.left = new FormAttachment(textExcel, 6);
		btnExcel.setLayoutData(fd_btnExcel);
		btnExcel.setText("...");
		
		Label lblUserDatabaseFolder = new Label(container, SWT.NONE);
		lblUserDatabaseFolder.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblUserDatabaseFolder = new FormData();
		fd_lblUserDatabaseFolder.top = new FormAttachment(textExcel, 15);
		fd_lblUserDatabaseFolder.left = new FormAttachment(lblPreference, 0, SWT.LEFT);
		lblUserDatabaseFolder.setLayoutData(fd_lblUserDatabaseFolder);
		lblUserDatabaseFolder.setText("User Database Folder Path");
		
		textDatabase = new Text(container, SWT.BORDER);
		textDatabase.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textDatabase.setEditable(false);
		textDatabase.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				textDatabasePath = text.getText().toString().trim();
			}
		});
		FormData fd_textDatabase = new FormData();
		fd_textDatabase.top = new FormAttachment(lblUserDatabaseFolder, 6);
		fd_textDatabase.left = new FormAttachment(lblPreference, 0, SWT.LEFT);
		fd_textDatabase.right = new FormAttachment(textExcel,0,SWT.RIGHT);
		textDatabase.setLayoutData(fd_textDatabase);
		textDatabase.setText(MC.getDatabasePath());
		textDatabasePath = MC.getDatabasePath();
		
		final Button btnDatabase = new Button(container, SWT.NONE);
		btnDatabase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog  dlg = new DirectoryDialog(btnDatabase.getShell());
				dlg.setFilterPath(myUtil.setPath(System.getProperty("user.dir"),"msck_Database"));
				dlg.setMessage("Select User Database Folder");
				String path = dlg.open();
				if (path != null){
					textDatabase.setText(path);
					textDatabasePath = path;
				}else{
					textDatabase.setText(ResultEmpty);
					textDatabasePath = ResultEmpty;
				}
			}
		});
		FormData fd_btnDatabase = new FormData();
		fd_btnDatabase.top = new FormAttachment(textDatabase, -2, SWT.TOP);
		fd_btnDatabase.left = new FormAttachment(textDatabase, 6);
		btnDatabase.setLayoutData(fd_btnDatabase);
		btnDatabase.setText("...");
		
		Label lblAdamsPath = new Label(container, SWT.NONE);
		lblAdamsPath.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblAdamsPath = new FormData();
		fd_lblAdamsPath.top = new FormAttachment(textDatabase, 15);
		fd_lblAdamsPath.left = new FormAttachment(lblPreference, 0, SWT.LEFT);
		lblAdamsPath.setLayoutData(fd_lblAdamsPath);
		lblAdamsPath.setText("Adams Path");
		
		textAdams = new Text(container, SWT.BORDER);
		textAdams.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textAdams.setEditable(false);
		textAdams.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				textAdamsPath = text.getText().toString().trim();
			}
		});
		FormData fd_textAdams = new FormData();
		fd_textAdams.top = new FormAttachment(lblAdamsPath, 6);
		fd_textAdams.left = new FormAttachment(lblPreference, 0, SWT.LEFT);
		fd_textAdams.right = new FormAttachment(textExcel,0,SWT.RIGHT);
		textAdams.setLayoutData(fd_textAdams);
		textAdams.setText(MC.getAdamsPath());
		textAdamsPath = MC.getAdamsPath();
		
		final Button btnAdams = new Button(container, SWT.NONE);
		btnAdams.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog  dlg = new DirectoryDialog(btnAdams.getShell());
				dlg.setFilterPath(System.getProperty("user.dir"));
				dlg.setMessage("Select Adams Folder");
				String path = dlg.open();
				if (path != null){
					textAdams.setText(path);
					textAdamsPath = path;
				}else{
					textAdams.setText(ResultEmpty);
					textAdamsPath = ResultEmpty;
				}
			}
		});
		FormData fd_btnAdams = new FormData();
		fd_btnAdams.top = new FormAttachment(textAdams, -2, SWT.TOP);
		fd_btnAdams.left = new FormAttachment(textAdams, 6);
		btnAdams.setLayoutData(fd_btnAdams);
		btnAdams.setText("...");
		
		/*
		Group grpSolverType = new Group(container, SWT.NONE);
		grpSolverType.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpSolverType.setText("Solver Type");
		grpSolverType.setLayout(new FormLayout());
		FormData fd_grpSolverType = new FormData();
		fd_grpSolverType.top = new FormAttachment(textAdams, 15);
		fd_grpSolverType.left = new FormAttachment(lblPreference, 0, SWT.LEFT);
		fd_grpSolverType.right = new FormAttachment(100, 0);
		fd_grpSolverType.bottom = new FormAttachment(0,270);
		grpSolverType.setLayoutData(fd_grpSolverType);
		
		btnNastran = new Button(grpSolverType, SWT.RADIO);
		btnNastran.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnNastran.getSelection()){
					SolverType = NastranType;
				}else{
					SolverType = AbaqusType;
				}
			}
		});
		FormData fd_btnNastran = new FormData();
		fd_btnNastran.top = new FormAttachment(0, 10);
		fd_btnNastran.left = new FormAttachment(0, 50);
		btnNastran.setLayoutData(fd_btnNastran);
		btnNastran.setSelection(true);
		btnNastran.setText("Nastran");
		
		btnAbaqus = new Button(grpSolverType, SWT.RADIO);
		btnAbaqus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnAbaqus.getSelection()){
					SolverType = AbaqusType;
				}else {
					SolverType = NastranType;
				}
			}
		});
		FormData fd_btnAbaqus = new FormData();
		fd_btnAbaqus.top = new FormAttachment(btnNastran, 0, SWT.TOP);
		fd_btnAbaqus.left = new FormAttachment(btnNastran, 100);
		btnAbaqus.setLayoutData(fd_btnAbaqus);
		btnAbaqus.setText("Abaqus");
		// */
		/*
		Label lblEIGRL = new Label(container, SWT.NONE);
		lblEIGRL.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblEIGRL = new FormData();
		fd_lblEIGRL.top = new FormAttachment(textAdams, 15);
		fd_lblEIGRL.left = new FormAttachment(lblPreference, 0, SWT.LEFT);
		lblEIGRL.setLayoutData(fd_lblEIGRL);
		lblEIGRL.setText("EIGRL ND");	
		
		textEIGRL = new Text(container, SWT.BORDER);
		textEIGRL.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				textEIGRL_ND = text.getText().toString().trim();
			}
		});
		FormData fd_textEIGRL= new FormData();
		fd_textEIGRL.top = new FormAttachment(lblEIGRL, -2, SWT.TOP);
		fd_textEIGRL.left = new FormAttachment(lblEIGRL, 40,SWT.RIGHT);
		fd_textEIGRL.right = new FormAttachment(lblEIGRL, 140,SWT.RIGHT);
		textEIGRL.setLayoutData(fd_textEIGRL);
		textEIGRL.setText(MC.getEIGRL_ND());
		//*/
		
		Label lblHyperWorksPath = new Label(container, SWT.NONE);
		lblHyperWorksPath.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblHyperWorksPath = new FormData();
		fd_lblHyperWorksPath.top = new FormAttachment(textAdams, 15);
		fd_lblHyperWorksPath.left = new FormAttachment(lblPreference, 0, SWT.LEFT);
		lblHyperWorksPath.setLayoutData(fd_lblHyperWorksPath);
		lblHyperWorksPath.setText("HyperWorks Path");
		
		textHyperWorks = new Text(container, SWT.BORDER);
		textHyperWorks.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textHyperWorks.setEditable(false);
		textHyperWorks.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				textHyperWorksPath = text.getText().toString().trim();
			}
		});
		FormData fd_textHyperWorks = new FormData();
		fd_textHyperWorks.top = new FormAttachment(lblHyperWorksPath, 6);
		fd_textHyperWorks.left = new FormAttachment(lblPreference, 0, SWT.LEFT);
		fd_textHyperWorks.right = new FormAttachment(textExcel,0,SWT.RIGHT);
		textHyperWorks.setLayoutData(fd_textHyperWorks);
		textHyperWorks.setText(MC.getHyperWorksPath());
		textHyperWorksPath = MC.getHyperWorksPath();
		
		final Button btnHyperWorks = new Button(container, SWT.NONE);
		btnHyperWorks.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog  dlg = new DirectoryDialog(btnHyperWorks.getShell());
				dlg.setFilterPath(System.getProperty("user.dir"));
				dlg.setMessage("Select HyperWorks Folder");
				String path = dlg.open();
				if (path != null){
					textHyperWorks.setText(path);
					textHyperWorksPath = path;
				}else{
					textHyperWorks.setText(ResultEmpty);
					textHyperWorksPath = ResultEmpty;
				}
			}
		});
		FormData fd_btnHypwerWorks = new FormData();
		fd_btnHypwerWorks.top = new FormAttachment(textHyperWorks, -2, SWT.TOP);
		fd_btnHypwerWorks.left = new FormAttachment(textHyperWorks, 6);
		btnHyperWorks.setLayoutData(fd_btnHypwerWorks);
		btnHyperWorks.setText("...");
		
		
		Label lblFemfatPath = new Label(container, SWT.NONE);
		lblFemfatPath.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblFemFatPath = new FormData();
		fd_lblFemFatPath.top = new FormAttachment(textHyperWorks, 15);
		fd_lblFemFatPath.left = new FormAttachment(lblPreference, 0, SWT.LEFT);
		lblFemfatPath.setLayoutData(fd_lblFemFatPath);
		lblFemfatPath.setText("FEMFAT Path");
		
		textFemfat = new Text(container, SWT.BORDER);
		textFemfat.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textFemfat.setEditable(false);
		textFemfat.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				textFemfatPath = text.getText().toString().trim();
			}
		});
		FormData fd_textFemfat = new FormData();
		fd_textFemfat.top = new FormAttachment(lblFemfatPath, 6);
		fd_textFemfat.left = new FormAttachment(lblPreference, 0, SWT.LEFT);
		fd_textFemfat.right = new FormAttachment(textExcel,0,SWT.RIGHT);
		textFemfat.setLayoutData(fd_textFemfat);
		textFemfat.setText(MC.getFemfatPath());
		textFemfatPath = MC.getFemfatPath();
		
		final Button btnFemfat = new Button(container, SWT.NONE);
		btnFemfat.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog  dlg = new DirectoryDialog(btnFemfat.getShell());
				dlg.setFilterPath(MC.getFemfatPath());
				dlg.setMessage("Select FEMFAT Folder");
				String path = dlg.open();
				if (path != null){
					textFemfat.setText(path);
					textFemfatPath = path;
				}else{
					textFemfat.setText(ResultEmpty);
					textFemfatPath = ResultEmpty;
				}
			}
		});
		FormData fd_btnFemfat = new FormData();
		fd_btnFemfat.top = new FormAttachment(textFemfat, -2, SWT.TOP);
		fd_btnFemfat.left = new FormAttachment(textFemfat, 6);
		btnFemfat.setLayoutData(fd_btnFemfat);
		btnFemfat.setText("...");
		
		Label lblEIGRL = new Label(container, SWT.NONE);
		lblEIGRL.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblEIGRL = new FormData();
		fd_lblEIGRL.top = new FormAttachment(textFemfat, 15);
		fd_lblEIGRL.left = new FormAttachment(lblPreference, 0, SWT.LEFT);
		lblEIGRL.setLayoutData(fd_lblEIGRL);
		lblEIGRL.setText("EIGRL ND");	
		
		textEIGRL = new Text(container, SWT.BORDER);
		textEIGRL.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				textEIGRL_ND = text.getText().toString().trim();
			}
		});
		FormData fd_textEIGRL= new FormData();
		fd_textEIGRL.top = new FormAttachment(lblEIGRL, -2, SWT.TOP);
		fd_textEIGRL.left = new FormAttachment(lblEIGRL, 40,SWT.RIGHT);
		fd_textEIGRL.right = new FormAttachment(lblEIGRL, 140,SWT.RIGHT);
		textEIGRL.setLayoutData(fd_textEIGRL);
		textEIGRL.setText(MC.getEIGRL_ND());
		
		/*
		if(MC.getSolverType().equals(NastranType)){
			btnNastran.setSelection(true);
			btnAbaqus.setSelection(false);
			this.SolverType = NastranType;
		}else if(MC.getSolverType().equals(AbaqusType)){
			btnNastran.setSelection(false);
			btnAbaqus.setSelection(true);
			this.SolverType = AbaqusType;
		}else{
			btnNastran.setSelection(true);
			btnAbaqus.setSelection(false);
			this.SolverType = NastranType;
		}
		//*/
		//initSetValue();
		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button btnOk = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(checkValues()){
					RunPreference();
				}else {
					JOptionPane.showMessageDialog(null, "Please, Check Data.","Preference",JOptionPane.ERROR_MESSAGE);
					PreferenceDlg dlg = new PreferenceDlg(Display.getCurrent().getActiveShell());
					dlg.open();
					return;
				}
				
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 501);
	}
	
	private boolean checkValues(){
		boolean result = false;
		
		if(myUtil.checkPath(this.textExcelPath)){
			result = true;
		}else{
			MC.addMsgWindow("Excel path is not correct.", MessageWindow.Warnning);
			return false;
		}
		if(myUtil.checkPath(this.textDatabasePath)){
			result = true;
		}else{
			MC.addMsgWindow("Database path is not correct.", MessageWindow.Warnning);
			return false;
		}
		if(myUtil.checkPath(this.textAdamsPath)){
			result = true;
		}else{
			MC.addMsgWindow("Adams path is not correct.", MessageWindow.Warnning);
			return false;
		}
		if(myUtil.checkPath(this.textHyperWorksPath)){
			result = true;
		}else{
			MC.addMsgWindow("HyperWorks path is not correct.", MessageWindow.Warnning);
			return false;
		}
		if(myUtil.checkPath(this.textFemfatPath)){
			result = true;
		}else{
			MC.addMsgWindow("Femfat path is not correct", MessageWindow.Warnning);
			return false;
		}
		
		return result;
	}
	
	public void RunPreference(){
		
		MC.setExcelPath(this.textExcelPath);
		MC.setDatabasePath(this.textDatabasePath);
		MC.setAdamsPath(this.textAdamsPath);
		//MC.setSolverType(this.SolverType);
		MC.setEIGRL_ND(this.textEIGRL_ND);
		MC.setHyperWorksPath(this.textHyperWorksPath);
		MC.setFemfatPath(this.textFemfatPath);
		
		this.resultDataList = new ArrayList<String>();
		this.resultDataList.add("//===================================================");
		this.resultDataList.add("//");
		this.resultDataList.add("//  Preference Variables");
		this.resultDataList.add("//");
		this.resultDataList.add("//===================================================");
		this.resultDataList.add(this.ExcelFile_T+"="+this.textExcelPath);
		this.resultDataList.add(this.DatabaseFolder_T+"="+this.textDatabasePath);
		this.resultDataList.add(this.AdamsFolder_T+"="+this.textAdamsPath);
		//this.resultDataList.add(this.SolverType_T+"="+this.SolverType);
		this.resultDataList.add(this.HyperWorksFolder_T+"="+this.textHyperWorksPath);
		this.resultDataList.add(this.FemfatFolder_T+"="+this.textFemfatPath);
		this.resultDataList.add(this.EIGRL_ND_T+"="+this.textEIGRL_ND);
		
		MC.executeFilePreferenceImpl(this.resultDataList);
	}
	
	private void initSetValue(){
		ArrayList<String> fileDataList = new ArrayList<String>();
		String preferenceFilePath = myUtil.setPath(myUtil.setPath(MC.getAppPath(), "msck_Config"),"Preference.ini");
		Reader reader = new Reader(preferenceFilePath);
		reader.running();
		fileDataList = reader.getFileDataList();
		
		for(String line : fileDataList){
			if(line.contains("//")){
			}else{
				ArrayList<String> temp = new ArrayList<String>();
				temp = ParserDefault.splitLineData(line, "=");
				if(temp.get(0).equals(ExcelFile_T)){
					this.textExcel.setText(temp.get(1));
					this.textExcelPath = temp.get(1);
				}else if(temp.get(0).equals(DatabaseFolder_T)){
					this.textDatabase.setText(temp.get(1));
					this.textDatabasePath = temp.get(1);
				}else if(temp.get(0).equals(AdamsFolder_T)){
					this.textAdams.setText(temp.get(1));
					this.textAdamsPath = temp.get(1);
				}
				/*
				else if(temp.get(0).equals(SolverType_T)){
					if(temp.get(1).equals(NastranType)){
						this.btnNastran.setSelection(true);
						this.btnAbaqus.setSelection(false);
						this.SolverType = NastranType;
					}else if(temp.get(1).equals(AbaqusType)){
						this.btnAbaqus.setSelection(true);
						this.btnNastran.setSelection(false);
						this.SolverType = AbaqusType;
					}else{
						this.btnNastran.setSelection(true);
						this.btnAbaqus.setSelection(false);
						this.SolverType = NastranType;
					}
				}
				// */
				else if(temp.get(0).equals(HyperWorksFolder_T)){
					this.textHyperWorks.setText(temp.get(1));
					this.textHyperWorksPath = temp.get(1);
				}else if(temp.get(0).equals(FemfatFolder_T)){
					this.textFemfat.setText(temp.get(1));
					this.textFemfatPath = temp.get(1);
				}else if(temp.get(0).equals(EIGRL_ND_T)){
					this.textEIGRL.setText(temp.get(1));
					this.textEIGRL_ND = temp.get(1);
				}
			}
		}
	}
}
