package com.msc.adams.automation;


import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.ViewPart;

import com.msc.adams.automation.core.ComboLabel;
import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;
import com.msc.adams.automation.core.MessageWindow;
import com.msc.adams.automation.core.UILabel;
import com.msc.adams.automation.core.database.DatabaseStep4;
import com.msc.adams.automation.customWidget.CustomButton;
import com.msc.adams.automation.customWidget.CustomCombo;
import com.msc.adams.automation.customWidget.CustomLabel;
import com.msc.adams.automation.customWidget.CustomList;
import com.msc.adams.automation.customWidget.CustomListDoubleClick;
import com.msc.adams.automation.customWidget.CustomTable;
import com.msc.adams.automation.customWidget.CustomText;
import com.msc.adams.automation.handler.HandlerButton;
import com.msc.adams.automation.handler.HandlerCombo;
import com.msc.adams.automation.handler.HandlerLabel;
import com.msc.adams.automation.handler.HandlerList;
import com.msc.adams.automation.handler.HandlerListDoubleClick;
import com.msc.adams.automation.handler.HandlerTable;
import com.msc.adams.automation.handler.HandlerText;
import com.msc.adams.automation.image.ImagePath;

import org.eclipse.swt.layout.FormLayout;

//import java.io.File;

//import javax.imageio.ImageIO;

//import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.ProgressBar;

public class View extends ViewPart {
	public static final String ID = "com.msc.adams.automation.view";
	
	private MainController MC = MainController.getInstance();
	private UILabel LabelDatas = UILabel.getInstatnce();
	private ComboLabel ComboLabelDatas = ComboLabel.getInstance();
	
	private Mediator med = Mediator.getInstance();
	private ImagePath imgP = ImagePath.getInstatnce();
	
	
	
	
	private StackLayout stackLayout;
	private Table tableSwappingPart;
	private Table tableFatSolving;
	private Text textMnfFilePath;
	private Text textBulkFilePath;
	private Text textInputdeckPath;
	private Composite compositeStep1;
	private Composite compositeStep2;
	private Composite compositeStep3;
	private Composite compositeStep4;
	private Composite compositeStep5;
	private Text textRadiusValue;
	private Text textXValue;
	private Text textYValue;
	private Text textZValue;
	private Text textResultName;
	private Text textError;
	private Text textHmax;
	private Text textNumberOfStep;
	private Text textEndTime;
	private Text textMessageWindow;
	private Text textNumberOfCycles;
	private Text textStartTimeRange;
	private Text textEndTimeRange;
	private Text textIncrementFrame;
	private Text textExtraMassRatio;
	private Text textCycleNumber;

	
	
	
	
	public View() {
		//System.out.println( System.getProperty("user.dir"));
	}
	
	public void createPartControl(final Composite parent) {
		Composite parentView = parent;
		med.setParentView(parentView);
		parentView.setLayout(new FormLayout());
		
		Composite compositeTop = new Composite(parentView, SWT.BORDER);
		med.setCompositeTop(compositeTop);
		compositeTop.setLayout(new FormLayout());
		FormData fd_compositeTop = new FormData();
		fd_compositeTop.top = new FormAttachment(0);
		fd_compositeTop.left = new FormAttachment(0);
		fd_compositeTop.right = new FormAttachment(100,0);
		fd_compositeTop.bottom = new FormAttachment(0, 160);
		compositeTop.setLayoutData(fd_compositeTop);
		
		Label lblProjectName = new Label(compositeTop, SWT.NONE);
		lblProjectName.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblProjectName = new FormData();
		fd_lblProjectName.top = new FormAttachment(0, 5);
		fd_lblProjectName.left = new FormAttachment(0, 10);
		lblProjectName.setLayoutData(fd_lblProjectName);
		lblProjectName.setText(LabelDatas.getLabel(UILabel.PROJECTNAME));
		
		Label lblMyprojName = new Label(compositeTop, SWT.NONE);
		lblMyprojName.setText("-");
		med.setLblMyprojName(lblMyprojName);
		FormData fd_lblMyprojName = new FormData();
		fd_lblMyprojName.top = new FormAttachment(lblProjectName, 0, SWT.TOP);
		fd_lblMyprojName.left = new FormAttachment(lblProjectName, 30);
		fd_lblMyprojName.right = new FormAttachment(100,-20);
		lblMyprojName.setLayoutData(fd_lblMyprojName);
		
		Label lblWorkspace = new Label(compositeTop, SWT.NONE);
		lblWorkspace.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblWorkspace = new FormData();
		fd_lblWorkspace.top = new FormAttachment(lblProjectName, 5);
		fd_lblWorkspace.left = new FormAttachment(lblProjectName, 0, SWT.LEFT);
		lblWorkspace.setLayoutData(fd_lblWorkspace);
		lblWorkspace.setText(LabelDatas.getLabel(UILabel.WORKSPACE));
		
		Label lblPath = new Label(compositeTop, SWT.NONE);
		med.setLblPath(lblPath);
		FormData fd_lblPath = new FormData();
		fd_lblPath.top = new FormAttachment(lblWorkspace, 0, SWT.TOP);
		fd_lblPath.left = new FormAttachment(lblMyprojName, 0, SWT.LEFT);
		fd_lblPath.right = new FormAttachment(100,-20);
		lblPath.setLayoutData(fd_lblPath);
		lblPath.setText("-");
		
		Label lblProcessLevel = new Label(compositeTop, SWT.NONE);
		lblProcessLevel.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblProcessLevel = new FormData();
		fd_lblProcessLevel.top = new FormAttachment(lblWorkspace, 5);
		fd_lblProcessLevel.left = new FormAttachment(lblProjectName, 0, SWT.LEFT);
		lblProcessLevel.setLayoutData(fd_lblProcessLevel);
		lblProcessLevel.setText(LabelDatas.getLabel(UILabel.PROCESSLEVEL));
		
		Button btnPrevious = new Button(compositeTop, SWT.NONE);
		med.setBtnPrevious(btnPrevious);
		CustomButton c_btnPrevious = new CustomButton(Mediator.BUTTON_btnPrevious,med);
		med.setC_btnPrevious(c_btnPrevious);
		c_btnPrevious.setCustomWidget_btnPrevious();
		FormData fd_btnPrevious = new FormData();
		fd_btnPrevious.top = new FormAttachment(lblProcessLevel, 5);
		fd_btnPrevious.left = new FormAttachment(lblProjectName, 0, SWT.LEFT);
		btnPrevious.setLayoutData(fd_btnPrevious);
		btnPrevious.setText("<<");
		
		Label lblStep1 = new Label(compositeTop, SWT.NONE);
		med.setLblStep1(lblStep1);
		CustomLabel c_lblStep1 = new CustomLabel(Mediator.LABEL_lblStep1,med);
		med.setC_lblStep1(c_lblStep1);
		c_lblStep1.setCustomWidget_lblStep1();
		FormData fd_lblStep1 = new FormData();
		fd_lblStep1.top = new FormAttachment(btnPrevious, 5, SWT.TOP);
		fd_lblStep1.left = new FormAttachment(btnPrevious, 30);
		fd_lblStep1.right = new FormAttachment(0,145);
		lblStep1.setLayoutData(fd_lblStep1);
		lblStep1.setText(LabelDatas.getLabel(UILabel.STPE1));
		
		Label lblStep2 = new Label(compositeTop, SWT.NONE);
		med.setLblStep2(lblStep2);
		CustomLabel c_lblStep2 = new CustomLabel(Mediator.LABEL_lblStep2,med);
		med.setC_lblStep2(c_lblStep2);
		c_lblStep2.setCustomWidget_lblStep2();
		FormData fd_lblStep2 = new FormData();
		fd_lblStep2.top = new FormAttachment(btnPrevious, 5, SWT.TOP);
		fd_lblStep2.left = new FormAttachment(lblStep1, 20);
		lblStep2.setLayoutData(fd_lblStep2);
		lblStep2.setText(LabelDatas.getLabel(UILabel.STPE2));
		
		Label lblStep3 = new Label(compositeTop, SWT.NONE);
		med.setLblStep3(lblStep3);
		CustomLabel c_lblStep3 = new CustomLabel(Mediator.LABEL_lblStep3,med);
		med.setC_lblStep3(c_lblStep3);
		c_lblStep3.setCustomWidget_lblStep3();
		FormData fd_lblStep3 = new FormData();
		fd_lblStep3.top = new FormAttachment(btnPrevious, 5, SWT.TOP);
		fd_lblStep3.left = new FormAttachment(lblStep2, 20);
		lblStep3.setLayoutData(fd_lblStep3);
		lblStep3.setText(LabelDatas.getLabel(UILabel.STPE3));
		
		Label lblStep4 = new Label(compositeTop, SWT.NONE);
		med.setLblStep4(lblStep4);
		CustomLabel c_lblStep4 = new CustomLabel(Mediator.LABEL_lblStep4,med);
		med.setC_lblStep4(c_lblStep4);
		c_lblStep4.setCustomWidget_lblStep4();
		FormData fd_lblStep4 = new FormData();
		fd_lblStep4.top = new FormAttachment(btnPrevious, 5, SWT.TOP);
		fd_lblStep4.left = new FormAttachment(lblStep3, 20);
		fd_lblStep4.right = new FormAttachment(lblStep3,120,SWT.RIGHT);
		lblStep4.setLayoutData(fd_lblStep4);
		lblStep4.setText(LabelDatas.getLabel(UILabel.STPE4));
		
		Label lblStep5 = new Label(compositeTop, SWT.NONE);
		med.setLblStep5(lblStep5);
		CustomLabel c_lblStep5 = new CustomLabel(Mediator.LABEL_lblStep5,med);
		med.setC_lblStep5(c_lblStep5);
		c_lblStep5.setCustomWidget_lblStep5();
		FormData fd_lblStep5 = new FormData();
		fd_lblStep5.top = new FormAttachment(btnPrevious, 5, SWT.TOP);
		fd_lblStep5.left = new FormAttachment(lblStep4, 20);
		lblStep5.setLayoutData(fd_lblStep5);
		lblStep5.setText(LabelDatas.getLabel(UILabel.STPE5));
		
		Button btnNext = new Button(compositeTop, SWT.NONE);
		med.setBtnNext(btnNext);
		CustomButton c_btnNext = new CustomButton(Mediator.BUTTON_btnNext,med);
		med.setC_btnNext(c_btnNext);
		c_btnNext.setCustomWidget_btnNext();
		FormData fd_btnNext = new FormData();
		fd_btnNext.top = new FormAttachment(btnPrevious, 0, SWT.TOP);
		fd_btnNext.left = new FormAttachment(lblStep5, 30);
		btnNext.setLayoutData(fd_btnNext);
		btnNext.setText(">>");
		
		Button btnEditAllData = new Button(parentView, SWT.NONE);
		med.setBtnEditAllData(btnEditAllData);
		CustomButton c_btnEditAllData = new CustomButton(Mediator.BUTTON_btnEditAllData,med);
		med.setC_btnEditAllData(c_btnEditAllData);
		c_btnEditAllData.setCustomWidget_btnEditAllData();
		FormData fd_btnEditAllData = new FormData();
		fd_btnEditAllData.left = new FormAttachment(0,400);
		fd_btnEditAllData.right = new FormAttachment(0,520);
		fd_btnEditAllData.bottom = new FormAttachment(100, -5);
		btnEditAllData.setLayoutData(fd_btnEditAllData);
		btnEditAllData.setText(LabelDatas.getLabel(UILabel.BUTTONEDIT));
		btnEditAllData.setVisible(false);
		
		Button btnSaveAllData = new Button(parentView, SWT.NONE);
		med.setBtnSaveAllData(btnSaveAllData);
		CustomButton c_btnSaveAllData = new CustomButton(Mediator.BUTTON_btnSaveAllData,med);
		med.setC_btnSaveAllData(c_btnSaveAllData);
		c_btnSaveAllData.setCustomWidget_btnSaveAllData();
		btnSaveAllData.setText(LabelDatas.getLabel(UILabel.BUTTONSAVE));
		FormData fd_btnSaveAllData = new FormData();
		fd_btnSaveAllData.top = new FormAttachment(btnEditAllData, 0, SWT.TOP);
		fd_btnSaveAllData.left = new FormAttachment(btnEditAllData, 10);
		fd_btnSaveAllData.right = new FormAttachment(btnEditAllData,130,SWT.RIGHT);
		btnSaveAllData.setLayoutData(fd_btnSaveAllData);
		
		Button btnReloadDb = new Button(parentView, SWT.NONE);
		med.setBtnReloadDb(btnReloadDb);
		CustomButton c_btnReloadDb = new CustomButton(Mediator.BUTTON_btnReloadDb,med);
		med.setC_btnReloadDb(c_btnReloadDb);
		c_btnReloadDb.setCustomWidget_btnReloadDB();
		FormData fd_btnReloadDb = new FormData();
		fd_btnReloadDb.top = new FormAttachment(btnSaveAllData, 0, SWT.TOP);
		fd_btnReloadDb.left = new FormAttachment(0, 20);
		fd_btnReloadDb.right = new FormAttachment(0,120);
		btnReloadDb.setLayoutData(fd_btnReloadDb);
		btnReloadDb.setText(LabelDatas.getLabel(UILabel.BUTTONRELOAD));
		
		Label lblBlade = new Label(compositeTop, SWT.NONE);
		lblBlade.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblBlade = new FormData();
		fd_lblBlade.top = new FormAttachment(btnPrevious, 5);
		fd_lblBlade.left = new FormAttachment(lblProjectName, 0, SWT.LEFT);
		lblBlade.setLayoutData(fd_lblBlade);
		lblBlade.setText(LabelDatas.getLabel(UILabel.BLADE));
		
		fd_lblMyprojName.top = new FormAttachment(lblProjectName, 0, SWT.TOP);
		fd_lblMyprojName.left = new FormAttachment(lblProjectName, 30);
		fd_lblMyprojName.right = new FormAttachment(100,-20);
		
		Label lblBladeValue = new Label(compositeTop, SWT.NONE);
		med.setLblBladeValue(lblBladeValue);
		FormData fd_lblBladeValue = new FormData();
		fd_lblBladeValue.top = new FormAttachment(lblBlade, 0, SWT.TOP);
		fd_lblBladeValue.left = new FormAttachment(lblMyprojName, 0, SWT.LEFT);
		fd_lblBladeValue.right = new FormAttachment(100,-20);
		lblBladeValue.setLayoutData(fd_lblBladeValue);
		lblBladeValue.setText("-");
		
		Label lblLinkage = new Label(compositeTop, SWT.NONE);
		lblLinkage.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblLinkage = new FormData();
		fd_lblLinkage.top = new FormAttachment(lblBlade, 5);
		fd_lblLinkage.left = new FormAttachment(lblProjectName, 0, SWT.LEFT);
		lblLinkage.setLayoutData(fd_lblLinkage);
		lblLinkage.setText(LabelDatas.getLabel(UILabel.LINKAGE));
		
		Label lblLinkageValue = new Label(compositeTop, SWT.NONE);
		med.setLblLinkageValue(lblLinkageValue);
		FormData fd_lblLinkageValue = new FormData();
		fd_lblLinkageValue.top = new FormAttachment(lblLinkage, 0,SWT.TOP);
		fd_lblLinkageValue.left = new FormAttachment(lblMyprojName, 0, SWT.LEFT);
		fd_lblLinkageValue.right = new FormAttachment(100,-20);
		lblLinkageValue.setLayoutData(fd_lblLinkageValue);
		lblLinkageValue.setText("-");
		
		Label lblWindshield = new Label(compositeTop, SWT.NONE);
		lblWindshield.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblWindshield = new FormData();
		fd_lblWindshield.top = new FormAttachment(lblLinkage, 5);
		fd_lblWindshield.left = new FormAttachment(lblProjectName, 0, SWT.LEFT);
		lblWindshield.setLayoutData(fd_lblWindshield);
		lblWindshield.setText(LabelDatas.getLabel(UILabel.WINDSHIELD));
		
		Label lblWindshieldValue = new Label(compositeTop, SWT.NONE);
		med.setLblWindshieldValue(lblWindshieldValue);
		FormData fd_lblWindshieldValue = new FormData();
		fd_lblWindshieldValue.top = new FormAttachment(lblWindshield, 0,SWT.TOP);
		fd_lblWindshieldValue.left = new FormAttachment(lblMyprojName, 0, SWT.LEFT);
		fd_lblWindshieldValue.right = new FormAttachment(100,-20);
		lblWindshieldValue.setLayoutData(fd_lblWindshieldValue);
		lblWindshieldValue.setText("-");
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Composite compositeBottom = new Composite(parentView, SWT.BORDER);
		med.setCompositeBottom(compositeBottom);
		stackLayout = new StackLayout();
		med.setStackLayout(stackLayout);
		compositeBottom.setLayout(stackLayout);
		FormData fd_compositeBottom = new FormData();
		fd_compositeBottom.top = new FormAttachment(compositeTop, 0);
		fd_compositeBottom.left = new FormAttachment(compositeTop, 0, SWT.LEFT);
		fd_compositeBottom.right = new FormAttachment(compositeTop, 0, SWT.RIGHT);
		fd_compositeBottom.bottom = new FormAttachment(100,-165);
		compositeBottom.setLayoutData(fd_compositeBottom);
		
		textMessageWindow = new Text(parent, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		med.setTextMessageWindow(textMessageWindow);
		textMessageWindow.setEditable(false);
		textMessageWindow.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_textMessageWindow = new FormData();
		fd_textMessageWindow.top = new FormAttachment(compositeBottom, 0);
		fd_textMessageWindow.left = new FormAttachment(compositeTop, 0, SWT.LEFT);
		fd_textMessageWindow.right = new FormAttachment(compositeTop, 0, SWT.RIGHT);
		fd_textMessageWindow.bottom = new FormAttachment(100,-35);
		textMessageWindow.setLayoutData(fd_textMessageWindow);		
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
		compositeStep1 = new Composite(compositeBottom, SWT.NONE);
		med.setCompositeStep1(compositeStep1);
		compositeStep1.setLayout(new FormLayout());
		
		Label lblBladeType = new Label(compositeStep1, SWT.NONE);
		lblBladeType.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblBladeType = new FormData();
		fd_lblBladeType.top = new FormAttachment(0, 10);
		fd_lblBladeType.left = new FormAttachment(0, 20);
		lblBladeType.setLayoutData(fd_lblBladeType);
		lblBladeType.setText(LabelDatas.getLabel(UILabel.BLADETYPE));
		
		Combo comboBladeType = new Combo(compositeStep1, SWT.READ_ONLY);
		med.setComboBladeType(comboBladeType);
		CustomCombo c_comboBladeType = new CustomCombo(Mediator.COMBO_comboBladeType,med);
		med.setC_comboBladeType(c_comboBladeType);
		c_comboBladeType.setCustomWidget_comboBladeType();
		comboBladeType.setItems(new String[] {ComboLabelDatas.getLabel(ComboLabel.Symmetric_Same_Basic),
												ComboLabelDatas.getLabel(ComboLabel.Symmetric_Different_Basic),
												ComboLabelDatas.getLabel(ComboLabel.NonSymmetric_Outer_Basic),
												ComboLabelDatas.getLabel(ComboLabel.NonSymmetric_Inner_Basic)});
		FormData fd_comboBladeType = new FormData();
		fd_comboBladeType.top = new FormAttachment(lblBladeType, -2, SWT.TOP);
		fd_comboBladeType.left = new FormAttachment(lblBladeType, 30);
		fd_comboBladeType.right = new FormAttachment(lblBladeType, 180, SWT.RIGHT);
		comboBladeType.setLayoutData(fd_comboBladeType);
		
		Image imgDefaultBlade = new Image(parentView.getDisplay(),imgP.getImagePath(ImagePath.BladeSymmetric_Same_Basic, "0"));
		Label lblImageBlade = new Label(compositeStep1,SWT.NONE);
		med.setLblImageBlade(lblImageBlade);
		//lblImageBlade.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblImageBlade = new FormData();
		fd_lblImageBlade.top = new FormAttachment(lblBladeType, 10);
		fd_lblImageBlade.left = new FormAttachment(lblBladeType, 0, SWT.LEFT);
		fd_lblImageBlade.right = new FormAttachment(comboBladeType, 0,SWT.RIGHT);
		fd_lblImageBlade.bottom = new FormAttachment(lblBladeType,140,SWT.BOTTOM);
		lblImageBlade.setLayoutData(fd_lblImageBlade);
		lblImageBlade.setImage(imgDefaultBlade);
		lblImageBlade.pack();
		
		ListViewer listViewerBladeDatabase = new ListViewer(compositeStep1, SWT.BORDER | SWT.V_SCROLL);
		med.setListViewerBladeDatabase(listViewerBladeDatabase);
		List listBladeDatabase = listViewerBladeDatabase.getList();
		med.setListBladeDatabase(listBladeDatabase);
		CustomList c_listBladeDatabase = new CustomList(Mediator.LIST_listBladeDatabase,med);
		med.setC_listBladeDatabase(c_listBladeDatabase);
		c_listBladeDatabase.setCustomWidget_listBladeDatabase();
		CustomListDoubleClick c_doubleClick_listBladeDatabase = new CustomListDoubleClick(Mediator.LIST_listBladeDatabase,med);
		med.setC_doubleClick_listBladeDatabase(c_doubleClick_listBladeDatabase);
		c_doubleClick_listBladeDatabase.setCustomWidget_listBladeDatabase();
		FormData fd_listBladeDatabase = new FormData();
		fd_listBladeDatabase.top = new FormAttachment(lblImageBlade, 5);
		fd_listBladeDatabase.left = new FormAttachment(lblBladeType, 0, SWT.LEFT);
		fd_listBladeDatabase.right = new FormAttachment(comboBladeType,0,SWT.RIGHT);
		fd_listBladeDatabase.bottom = new FormAttachment(100,-160);
		listBladeDatabase.setLayoutData(fd_listBladeDatabase);
		
		Label lblLinkageType = new Label(compositeStep1, SWT.NONE);
		lblLinkageType.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblLinkageType = new FormData();
		fd_lblLinkageType.top = new FormAttachment(lblBladeType, 0, SWT.TOP);
		fd_lblLinkageType.left = new FormAttachment(comboBladeType, 130);
		lblLinkageType.setLayoutData(fd_lblLinkageType);
		lblLinkageType.setText(LabelDatas.getLabel(UILabel.LINKAGETYPE));
		
		Combo comboLinkageType = new Combo(compositeStep1, SWT.READ_ONLY);
		med.setComboLinkageType(comboLinkageType);
		CustomCombo c_comboLinkageType = new CustomCombo(Mediator.COMBO_comboLinkageType,med);
		med.setC_comboLinkageType(c_comboLinkageType);
		c_comboLinkageType.setCustomWidget_comboLinkageType();
		comboLinkageType.setItems(new String[] {ComboLabelDatas.getLabel(ComboLabel.SerialParallel),
												ComboLabelDatas.getLabel(ComboLabel.SerialOpposed),
												ComboLabelDatas.getLabel(ComboLabel.ModuleTandem),
												ComboLabelDatas.getLabel(ComboLabel.SerialTandem)});
		FormData fd_comboLinkageType = new FormData();
		fd_comboLinkageType.top = new FormAttachment(comboBladeType, 0, SWT.TOP);
		fd_comboLinkageType.left = new FormAttachment(lblLinkageType, 27);
		fd_comboLinkageType.right = new FormAttachment(lblLinkageType, 170, SWT.RIGHT);
		comboLinkageType.setLayoutData(fd_comboLinkageType);
		
		Image imgDefaultLinkage = new Image(parentView.getDisplay(),imgP.getImagePath(ImagePath.LinkageSerialParallel, "0"));
		Label lblImageLinkage = new Label(compositeStep1,  SWT.NONE);
		med.setLblImageLinkage(lblImageLinkage);
		//lblImageLinkage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblImageLinkage = new FormData();
		fd_lblImageLinkage.top = new FormAttachment(lblLinkageType, 10);
		fd_lblImageLinkage.left = new FormAttachment(lblLinkageType, 0, SWT.LEFT);
		fd_lblImageLinkage.right = new FormAttachment(comboLinkageType,0,SWT.RIGHT);
		fd_lblImageLinkage.bottom = new FormAttachment(lblLinkageType,140,SWT.BOTTOM);
		lblImageLinkage.setLayoutData(fd_lblImageLinkage);
		lblImageLinkage.setImage(imgDefaultLinkage);
		lblImageLinkage.pack();
		
		ListViewer listViewerLinkageDatabase = new ListViewer(compositeStep1, SWT.BORDER | SWT.V_SCROLL);
		med.setListViewerLinkageDatabase(listViewerLinkageDatabase);
		List listLinkageDatabase = listViewerLinkageDatabase.getList();
		med.setListLinkageDatabase(listLinkageDatabase);
		CustomList c_listLinkageDatabase = new CustomList(Mediator.LIST_listLinkageDatabase,med);
		med.setC_listLinkageDatabase(c_listLinkageDatabase);
		c_listLinkageDatabase.setCustomWidget_listLinkageDatabase();
		CustomListDoubleClick c_doubleClick_listLinkageDatabase = new CustomListDoubleClick(Mediator.LIST_listLinkageDatabase,med);
		med.setC_doubleClick_listLinkageDatabase(c_doubleClick_listLinkageDatabase);
		c_doubleClick_listLinkageDatabase.setCustomWidget_listLinkageDatabase();
		FormData fd_listLinkageDatabase = new FormData();
		fd_listLinkageDatabase.top = new FormAttachment(listBladeDatabase, 0, SWT.TOP);
		fd_listLinkageDatabase.left = new FormAttachment(lblLinkageType, 0, SWT.LEFT);
		fd_listLinkageDatabase.right = new FormAttachment(comboLinkageType, 0, SWT.RIGHT);
		fd_listLinkageDatabase.bottom = new FormAttachment(100,-20);
		listLinkageDatabase.setLayoutData(fd_listLinkageDatabase);
		
		Label lblWindshieldType = new Label(compositeStep1, SWT.NONE);
		lblWindshieldType.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblWindshieldType = new FormData();
		fd_lblWindshieldType.top = new FormAttachment(listBladeDatabase, 10);
		fd_lblWindshieldType.left = new FormAttachment(lblBladeType, 0, SWT.LEFT);
		lblWindshieldType.setLayoutData(fd_lblWindshieldType);
		lblWindshieldType.setText(LabelDatas.getLabel(UILabel.WINDSHIELDTYPE));
		
		Combo comboWindshieldType = new Combo(compositeStep1, SWT.READ_ONLY);
		med.setComboWindshieldType(comboWindshieldType);
		CustomCombo c_comboWindshieldType = new CustomCombo(Mediator.COMBO_comboWindshieldType,med);
		med.setC_comboWindshieldType(c_comboWindshieldType);
		c_comboWindshieldType.setCustomWidget_comboWindshieldType();
		comboWindshieldType.setItems(new String[] {ComboLabelDatas.getLabel(ComboLabel.Sphere),
													ComboLabelDatas.getLabel(ComboLabel.Geometry)});
		FormData fd_comboWindshieldType = new FormData();
		fd_comboWindshieldType.top = new FormAttachment(lblWindshieldType, -2, SWT.TOP);
		fd_comboWindshieldType.left = new FormAttachment(comboBladeType, 20,SWT.LEFT);
		fd_comboWindshieldType.right = new FormAttachment(comboBladeType,90,SWT.RIGHT);
		comboWindshieldType.setLayoutData(fd_comboWindshieldType);
		
		Label lblRaius = new Label(compositeStep1, SWT.NONE);
		FormData fd_lblRaius = new FormData();
		fd_lblRaius.top = new FormAttachment(lblWindshieldType, 15);
		fd_lblRaius.left = new FormAttachment(lblBladeType, 5, SWT.LEFT);
		lblRaius.setLayoutData(fd_lblRaius);
		lblRaius.setText(LabelDatas.getLabel(UILabel.RADIUS));
		
		textRadiusValue = new Text(compositeStep1, SWT.BORDER);
		textRadiusValue.setText("1.9974E+006");
		med.setTextRadiusValue(textRadiusValue);
		CustomText c_textRadiusValue = new CustomText(Mediator.TEXT_textRadiusValue,med);
		med.setC_textRadiusValue(c_textRadiusValue);
		c_textRadiusValue.setCustomWidget_textRadiusValue();
		FormData fd_textRadiusValue = new FormData();
		fd_textRadiusValue.top = new FormAttachment(lblRaius, -2, SWT.TOP);
		fd_textRadiusValue.left = new FormAttachment(lblRaius, 10);
		fd_textRadiusValue.right = new FormAttachment(lblRaius, 100);
		textRadiusValue.setLayoutData(fd_textRadiusValue);
		
		Label lblX = new Label(compositeStep1, SWT.NONE);
		FormData fd_lblX = new FormData();
		fd_lblX.top = new FormAttachment(lblRaius, 10);
		fd_lblX.left = new FormAttachment(lblBladeType, 5, SWT.LEFT);
		lblX.setLayoutData(fd_lblX);
		lblX.setText(LabelDatas.getLabel(UILabel.X));
		
		textXValue = new Text(compositeStep1, SWT.BORDER);
		textXValue.setText("9.9870027199E+005");
		med.setTextXValue(textXValue);
		CustomText c_textXValue = new CustomText(Mediator.TEXT_textXValue,med);
		med.setC_textXValue(c_textXValue);
		c_textXValue.setCustomWidget_textXValue();
		FormData fd_textXValue = new FormData();
		fd_textXValue.top = new FormAttachment(lblX, -2, SWT.TOP);
		fd_textXValue.left = new FormAttachment(textRadiusValue, 0, SWT.LEFT);
		fd_textXValue.right = new FormAttachment(textRadiusValue, 0 ,SWT.RIGHT);
		textXValue.setLayoutData(fd_textXValue);
		
		Label lblY = new Label(compositeStep1, SWT.NONE);
		FormData fd_lblY = new FormData();
		fd_lblY.top = new FormAttachment(lblX, 10);
		fd_lblY.left = new FormAttachment(lblBladeType, 5, SWT.LEFT);
		lblY.setLayoutData(fd_lblY);
		lblY.setText(LabelDatas.getLabel(UILabel.Y));
		
		textYValue = new Text(compositeStep1, SWT.BORDER);
		textYValue.setText("-7.514");
		med.setTextYValue(textYValue);
		CustomText c_textYValue = new CustomText(Mediator.TEXT_textYValue,med);
		med.setC_textYValue(c_textYValue);
		c_textYValue.setCustomWidget_textYValue();
		FormData fd_textYValue = new FormData();
		fd_textYValue.top = new FormAttachment(lblY, -2, SWT.TOP);
		fd_textYValue.left = new FormAttachment(textRadiusValue, 0, SWT.LEFT);
		fd_textYValue.right = new FormAttachment(textRadiusValue, 0 ,SWT.RIGHT);
		textYValue.setLayoutData(fd_textYValue);
		
		Label lblZ = new Label(compositeStep1, SWT.NONE);
		FormData fd_lblZ = new FormData();
		fd_lblZ.top = new FormAttachment(lblY, 10);
		fd_lblZ.left = new FormAttachment(lblBladeType, 5, SWT.LEFT);
		lblZ.setLayoutData(fd_lblZ);
		lblZ.setText(LabelDatas.getLabel(UILabel.Z));
		
		textZValue = new Text(compositeStep1, SWT.BORDER);
		textZValue.setText("1500.3649999979");
		med.setTextZValue(textZValue);
		CustomText c_textZValue = new CustomText(Mediator.TEXT_textZValue,med);
		med.setC_textZValue(c_textZValue);
		c_textZValue.setCustomWidget_textZValue();
		FormData fd_textZValue = new FormData();
		fd_textZValue.top = new FormAttachment(lblZ, -2, SWT.TOP);
		fd_textZValue.left = new FormAttachment(textRadiusValue, 0, SWT.LEFT);
		fd_textZValue.right = new FormAttachment(textRadiusValue, 0 ,SWT.RIGHT);
		textZValue.setLayoutData(fd_textZValue);
		
		ListViewer listViewerWindShieldDatabase = new ListViewer(compositeStep1, SWT.BORDER | SWT.V_SCROLL);
		med.setListViewerWindShieldDatabase(listViewerWindShieldDatabase);
		List listWindShieldDatabase = listViewerWindShieldDatabase.getList();
		med.setListWindShieldDatabase(listWindShieldDatabase);
		CustomList c_listWindShieldDatabase = new CustomList(Mediator.LIST_listWindShieldDatabase,med);
		med.setC_listWindShieldDatabase(c_listWindShieldDatabase);
		c_listWindShieldDatabase.setCustomWidget_listWindshieldDatabase();
		FormData fd_listWindShieldDatabase = new FormData();
		fd_listWindShieldDatabase.top = new FormAttachment(textRadiusValue, 0, SWT.TOP);
		fd_listWindShieldDatabase.left = new FormAttachment(textRadiusValue, 10);
		fd_listWindShieldDatabase.right = new FormAttachment(comboWindshieldType,0,SWT.RIGHT);
		fd_listWindShieldDatabase.bottom = new FormAttachment(textZValue,0,SWT.BOTTOM);
		listWindShieldDatabase.setLayoutData(fd_listWindShieldDatabase);
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
		compositeStep2 = new Composite(compositeBottom, SWT.NONE);
		med.setCompositeStep2(compositeStep2);
		compositeStep2.setLayout(new FormLayout());
		
		Label lblPart = new Label(compositeStep2, SWT.NONE);
		lblPart.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblPart = new FormData();
		fd_lblPart.top = new FormAttachment(0, 10);
		fd_lblPart.left = new FormAttachment(0, 20);
		lblPart.setLayoutData(fd_lblPart);
		lblPart.setText(LabelDatas.getLabel(UILabel.PART));
		
		Button btnNone = new Button(compositeStep2, SWT.CHECK);
		med.setBtnNone(btnNone);
		CustomButton c_btnNone = new CustomButton(Mediator.BUTTON_btnNone,med);
		med.setC_btnNone(c_btnNone);
		c_btnNone.setCustomWidget_btnNone();
		FormData fd_btnNone = new FormData();
		fd_btnNone.top = new FormAttachment(lblPart, 0, SWT.TOP);
		fd_btnNone.left = new FormAttachment(lblPart, 25);
		btnNone.setLayoutData(fd_btnNone);
		btnNone.setText(LabelDatas.getLabel(UILabel.NONE));
		
		Image imgDefaultPart = new Image(parentView.getDisplay(), imgP.getImagePath(ImagePath.PartSerialParallel, "0"));
		Label lblImagePart = new Label(compositeStep2, SWT.CENTER);
		med.setLblImagePart(lblImagePart);
		lblImagePart.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblImagePart = new FormData();
		fd_lblImagePart.top = new FormAttachment(lblPart, 10);
		fd_lblImagePart.left = new FormAttachment(lblPart, 0, SWT.LEFT);
		fd_lblImagePart.right = new FormAttachment(lblPart, 210);
		fd_lblImagePart.bottom = new FormAttachment(lblPart,140,SWT.BOTTOM);
		lblImagePart.setLayoutData(fd_lblImagePart);
		lblImagePart.setImage(imgDefaultPart);
		lblImagePart.pack();
		
		ListViewer listViewerPart = new ListViewer(compositeStep2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		med.setListViewerPart(listViewerPart);
		List listPart = listViewerPart.getList();
		med.setListPart(listPart);
		CustomList c_listPart = new CustomList(Mediator.LIST_listPart,med);
		med.setC_listPart(c_listPart);
		c_listPart.setCustomWidget_listPart();
		FormData fd_listPart = new FormData();
		fd_listPart.top = new FormAttachment(lblImagePart, 10);
		fd_listPart.left = new FormAttachment(lblPart, 0, SWT.LEFT);
		fd_listPart.right = new FormAttachment(lblPart,190);
		fd_listPart.bottom = new FormAttachment(100,-10);
		listPart.setLayoutData(fd_listPart);
		
		Button btnAdd = new Button(compositeStep2, SWT.NONE);
		med.setBtnAdd(btnAdd);
		CustomButton c_btnAdd = new CustomButton(Mediator.BUTTON_btnAdd,med);
		med.setC_btnAdd(c_btnAdd);
		c_btnAdd.setCustomWidget_btnAdd();
		FormData fd_btnAdd = new FormData();
		fd_btnAdd.top = new FormAttachment(listPart, 0, SWT.TOP);
		fd_btnAdd.left = new FormAttachment(listPart, 6);
		fd_btnAdd.right = new FormAttachment(listPart,36,SWT.RIGHT);
		btnAdd.setLayoutData(fd_btnAdd);
		btnAdd.setText(">");
		
		Button btnDel = new Button(compositeStep2, SWT.NONE);
		med.setBtnDel(btnDel);
		CustomButton c_btnDel = new CustomButton(Mediator.BUTTON_btnDel,med);
		med.setC_btnDel(c_btnDel);
		c_btnDel.setCustemWidget_btnDel();
		FormData fd_btnDel = new FormData();
		fd_btnDel.top = new FormAttachment(btnAdd, 10);
		fd_btnDel.left = new FormAttachment(listPart, 6);
		fd_btnDel.right = new FormAttachment(btnAdd,0,SWT.RIGHT);
		btnDel.setLayoutData(fd_btnDel);
		btnDel.setText("<");
		
		Label lblSwappingPartStep2 = new Label(compositeStep2, SWT.NONE);
		lblSwappingPartStep2.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblSwappingPartStep2 = new FormData();
		fd_lblSwappingPartStep2.top = new FormAttachment(lblPart, 0, SWT.TOP);
		fd_lblSwappingPartStep2.left = new FormAttachment(lblPart, 210);
		lblSwappingPartStep2.setLayoutData(fd_lblSwappingPartStep2);
		lblSwappingPartStep2.setText(LabelDatas.getLabel(UILabel.SWAPPINGPART_S2));
		
		TableViewer tableViewerSwappingPart = new TableViewer(compositeStep2, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		tableSwappingPart = tableViewerSwappingPart.getTable();
		tableSwappingPart.setLinesVisible(true);
		tableSwappingPart.setHeaderVisible(true);
		med.setTableSwappingPart(tableSwappingPart);
		CustomTable c_tableSwappingPart = new CustomTable(Mediator.TABLE_tableSwappingPart,med);
		med.setC_tableSwappingPart(c_tableSwappingPart);
		c_tableSwappingPart.setCustomWidget_tableSwappingPart();
		FormData fd_tableSwappingPart = new FormData();
		fd_tableSwappingPart.top = new FormAttachment(lblImagePart, 0, SWT.TOP);
		fd_tableSwappingPart.left = new FormAttachment(btnAdd, 10, SWT.RIGHT);
		fd_tableSwappingPart.right = new FormAttachment(btnAdd, 410, SWT.RIGHT);
		fd_tableSwappingPart.bottom = new FormAttachment(listPart, 0, SWT.BOTTOM);
		tableSwappingPart.setLayoutData(fd_tableSwappingPart);
		
		TableColumn tblclmnCheck = new TableColumn(tableSwappingPart, SWT.NONE);
		tblclmnCheck.setWidth(30);
		
		TableColumn tblclmnPartName = new TableColumn(tableSwappingPart, SWT.NONE);
		tblclmnPartName.setWidth(100);
		tblclmnPartName.setText(LabelDatas.getLabel(UILabel.PARTNAME_S2));
		tblclmnPartName.setAlignment(SWT.CENTER);
		
		TableColumn tblclmnType = new TableColumn(tableSwappingPart, SWT.NONE);
		tblclmnType.setWidth(70);
		tblclmnType.setText(LabelDatas.getLabel(UILabel.TYPE_S2));
		tblclmnType.setAlignment(SWT.CENTER);
		
		TableColumn tblclmnPath = new TableColumn(tableSwappingPart, SWT.NONE);
		tblclmnPath.setWidth(350);
		tblclmnPath.setText(LabelDatas.getLabel(UILabel.PATH_S2));
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
		compositeStep3 = new Composite(compositeBottom, SWT.NONE);
		med.setCompositeStep3(compositeStep3);
		compositeStep3.setLayout(new FormLayout());
		
		Label lblSwappingPartStep3 = new Label(compositeStep3, SWT.NONE);
		lblSwappingPartStep3.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblSwappingPartStep3 = new FormData();
		fd_lblSwappingPartStep3.top = new FormAttachment(0, 10);
		fd_lblSwappingPartStep3.left = new FormAttachment(0, 10);
		lblSwappingPartStep3.setLayoutData(fd_lblSwappingPartStep3);
		lblSwappingPartStep3.setText(LabelDatas.getLabel(UILabel.SWAPPINGPART_S3));
		
		ListViewer listViewerSwappingPart = new ListViewer(compositeStep3, SWT.BORDER | SWT.V_SCROLL);
		med.setListViewerSwappingPart(listViewerSwappingPart);
		List listSwappingPart = listViewerSwappingPart.getList();
		med.setListSwappingPart(listSwappingPart);
		CustomList c_listSwappingPart = new CustomList(Mediator.LIST_listSwappingPart,med);
		med.setC_listSwappingPart(c_listSwappingPart);
		c_listSwappingPart.setCustomWidget_listSwappingPart();
		FormData fd_listSwappingPart = new FormData();
		fd_listSwappingPart.top = new FormAttachment(lblSwappingPartStep3, 6);
		fd_listSwappingPart.left = new FormAttachment(lblSwappingPartStep3, 0, SWT.LEFT);
		fd_listSwappingPart.right = new FormAttachment(lblSwappingPartStep3,200,SWT.LEFT);
		fd_listSwappingPart.bottom = new FormAttachment(100,-10);
		listSwappingPart.setLayoutData(fd_listSwappingPart);
		
		Group grpPartInformation = new Group(compositeStep3, SWT.NONE);
		med.setGrpPartInformation(grpPartInformation);
		grpPartInformation.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpPartInformation.setText(LabelDatas.getLabel(UILabel.PARTINFORMATION));
		grpPartInformation.setLayout(new FormLayout());
		FormData fd_grpPartInformation = new FormData();
		fd_grpPartInformation.top = new FormAttachment(lblSwappingPartStep3, 0, SWT.TOP);
		fd_grpPartInformation.left = new FormAttachment(listSwappingPart,10);
		fd_grpPartInformation.right = new FormAttachment(listSwappingPart,450,SWT.RIGHT);
		fd_grpPartInformation.bottom = new FormAttachment(listSwappingPart,0,SWT.BOTTOM);
		grpPartInformation.setLayoutData(fd_grpPartInformation);
		
		Label lblPartName = new Label(grpPartInformation, SWT.NONE);
		FormData fd_lblPartName = new FormData();
		fd_lblPartName.top = new FormAttachment(0, 10);
		fd_lblPartName.left = new FormAttachment(0, 10);
		lblPartName.setLayoutData(fd_lblPartName);
		lblPartName.setText(LabelDatas.getLabel(UILabel.PARTNAME_S3));
		
		Label lblPartNameValue = new Label(grpPartInformation, SWT.NONE);
		med.setLblPartNameValue(lblPartNameValue);
		lblPartNameValue.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		FormData fd_lblPartNameValue = new FormData();
		fd_lblPartNameValue.top = new FormAttachment(lblPartName, 0, SWT.TOP);
		fd_lblPartNameValue.left = new FormAttachment(lblPartName, 50);
		fd_lblPartNameValue.right = new FormAttachment(0,400);
		lblPartNameValue.setLayoutData(fd_lblPartNameValue);
		lblPartNameValue.setText("   ");
		
		Label lblSwappingType = new Label(grpPartInformation, SWT.NONE);
		FormData fd_lblSwappingType = new FormData();
		fd_lblSwappingType.top = new FormAttachment(lblPartName, 10);
		fd_lblSwappingType.left = new FormAttachment(lblPartName, 0, SWT.LEFT);
		lblSwappingType.setLayoutData(fd_lblSwappingType);
		lblSwappingType.setText(LabelDatas.getLabel(UILabel.TYPE_S3));
		
		Label lblSwappingTypeValue = new Label(grpPartInformation, SWT.NONE);
		med.setLblSwappingTypeValue(lblSwappingTypeValue);
		lblSwappingTypeValue.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		FormData fd_lblSwappingTypeValue = new FormData();
		fd_lblSwappingTypeValue.top = new FormAttachment(lblSwappingType, 0,SWT.TOP);
		fd_lblSwappingTypeValue.left = new FormAttachment(lblPartNameValue, 0, SWT.LEFT);
		fd_lblSwappingTypeValue.right = new FormAttachment(0,400);
		lblSwappingTypeValue.setLayoutData(fd_lblSwappingTypeValue);
		lblSwappingTypeValue.setText("   ");
		//
		Label lblBulkFilePath = new Label(grpPartInformation, SWT.NONE);
		FormData fd_lblBulkFilePath = new FormData();
		fd_lblBulkFilePath.top = new FormAttachment(lblSwappingType, 10);
		fd_lblBulkFilePath.left = new FormAttachment(lblSwappingType, 0, SWT.LEFT);
		lblBulkFilePath.setLayoutData(fd_lblBulkFilePath);
		lblBulkFilePath.setText(LabelDatas.getLabel(UILabel.BULKFILEPATH));
		
		textBulkFilePath = new Text(grpPartInformation, SWT.BORDER);
		med.setTextBulkFilePath(textBulkFilePath);
		FormData fd_textBulkFilePath = new FormData();
		fd_textBulkFilePath.top = new FormAttachment(lblBulkFilePath, 6);
		fd_textBulkFilePath.left = new FormAttachment(lblPartName, 0, SWT.LEFT);
		fd_textBulkFilePath.right = new FormAttachment(100,-10);
		textBulkFilePath.setLayoutData(fd_textBulkFilePath);
		textBulkFilePath.setEditable(false);
		
		Label lblInputdeckPath = new Label(grpPartInformation, SWT.NONE);
		FormData fd_lblInputdeckPath = new FormData();
		fd_lblInputdeckPath.top = new FormAttachment(textBulkFilePath, 10);
		fd_lblInputdeckPath.left = new FormAttachment(lblSwappingType, 0, SWT.LEFT);
		lblInputdeckPath.setLayoutData(fd_lblInputdeckPath);
		lblInputdeckPath.setText(LabelDatas.getLabel(UILabel.INPUTDECKPATH));

		textInputdeckPath = new Text(grpPartInformation, SWT.BORDER);
		med.setTextInputdeckPath(textInputdeckPath);
		FormData fd_textInputdeckPath = new FormData();
		fd_textInputdeckPath.top = new FormAttachment(lblInputdeckPath, 6);
		fd_textInputdeckPath.left = new FormAttachment(lblPartName, 0, SWT.LEFT);
		fd_textInputdeckPath.right = new FormAttachment(100,-10);
		textInputdeckPath.setLayoutData(fd_textInputdeckPath);
		textInputdeckPath.setEditable(false);
		
		Label lblMnfFilePath = new Label(grpPartInformation, SWT.NONE);
		FormData fd_lblMnfFilePath = new FormData();
		fd_lblMnfFilePath.top = new FormAttachment(textInputdeckPath, 10);
		fd_lblMnfFilePath.left = new FormAttachment(lblPartName, 0, SWT.LEFT);
		lblMnfFilePath.setLayoutData(fd_lblMnfFilePath);
		lblMnfFilePath.setText(LabelDatas.getLabel(UILabel.PATH_S3));
		
		textMnfFilePath = new Text(grpPartInformation, SWT.BORDER);
		//textMnfFilePath.setEnabled(true);
		//textMnfFilePath.setEditable(false);
		med.setTextMnfFilePath(textMnfFilePath);
		CustomText c_textMnfFilePath = new CustomText(Mediator.TEXT_textMnfFilePath,med);
		med.setC_textMnfFilePath(c_textMnfFilePath);
		c_textMnfFilePath.setCustomWidget_textMnfFilePath();
		FormData fd_textMnfFilePath = new FormData();
		fd_textMnfFilePath.top = new FormAttachment(lblMnfFilePath, 6);
		fd_textMnfFilePath.left = new FormAttachment(lblPartName, 0, SWT.LEFT);
		fd_textMnfFilePath.right = new FormAttachment(100,-30);
		textMnfFilePath.setLayoutData(fd_textMnfFilePath);
		
		Button btnExplorerStep3 = new Button(grpPartInformation, SWT.NONE);
		med.setBtnExplorerStep3(btnExplorerStep3);
		CustomButton c_btnExplorerStep3 = new CustomButton(Mediator.BUTTON_btnExplorerStep3,med);
		med.setC_btnExplorerStep3(c_btnExplorerStep3);
		c_btnExplorerStep3.setCustomWidget_btnExplorerStep3();
		FormData fd_btnExplorerStep3 = new FormData();
		fd_btnExplorerStep3.top = new FormAttachment(textMnfFilePath, -2, SWT.TOP);
		fd_btnExplorerStep3.left = new FormAttachment(textMnfFilePath, 6);
		btnExplorerStep3.setLayoutData(fd_btnExplorerStep3);
		btnExplorerStep3.setText("...");
		/*
		Group grpInputdeckVar = new Group(compositeStep3, SWT.NONE);
		med.setGrpInputdeckVar(grpInputdeckVar);
		grpInputdeckVar.setText(LabelDatas.getLabel(UILabel.INPUTDECKVAR));
		grpInputdeckVar.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpInputdeckVar.setLayout(new FormLayout());
		FormData fd_grpInputdeckVar = new FormData();
		fd_grpInputdeckVar.top = new FormAttachment(grpPartInformation, 5);
		fd_grpInputdeckVar.left = new FormAttachment(grpPartInformation, 0, SWT.LEFT);
		fd_grpInputdeckVar.right = new FormAttachment(grpPartInformation, 0, SWT.RIGHT);
		fd_grpInputdeckVar.bottom = new FormAttachment(listSwappingPart, 0, SWT.BOTTOM);
		grpInputdeckVar.setLayoutData(fd_grpInputdeckVar);
		// */
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
		compositeStep4 = new Composite(compositeBottom, SWT.NONE);
		med.setCompositeStep4(compositeStep4);
		compositeStep4.setLayout(new FormLayout());
		
		Group grpSolving = new Group(compositeStep4, SWT.NONE);
		grpSolving.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpSolving.setText(LabelDatas.getLabel(UILabel.SOLVING));
		grpSolving.setLayout(new FormLayout());
		FormData fd_grpSolving = new FormData();
		fd_grpSolving.top = new FormAttachment(0, 20);
		fd_grpSolving.left = new FormAttachment(0, 20);
		fd_grpSolving.right = new FormAttachment(0, 640);
		fd_grpSolving.bottom = new FormAttachment(0, 90);
		grpSolving.setLayoutData(fd_grpSolving);
		
		Label lblStatus = new Label(grpSolving, SWT.NONE);
		FormData fd_lblStatus = new FormData();
		fd_lblStatus.top = new FormAttachment(0, 15);
		fd_lblStatus.left = new FormAttachment(0, 30);
		lblStatus.setLayoutData(fd_lblStatus);
		lblStatus.setText(LabelDatas.getLabel(UILabel.STATUS));
		
		Label lblStatusValue = new Label(grpSolving, SWT.NONE);
		med.setLblStatusValue(lblStatusValue);
		lblStatusValue.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		FormData fd_lblStatusValue = new FormData();
		fd_lblStatusValue.top = new FormAttachment(lblStatus, 0, SWT.TOP);
		fd_lblStatusValue.left = new FormAttachment(lblStatus, 10);
		fd_lblStatusValue.right = new FormAttachment(0,200);
		fd_lblStatusValue.bottom = new FormAttachment(0,40);
		lblStatusValue.setLayoutData(fd_lblStatusValue);
		lblStatusValue.setText(LabelDatas.getLabel(UILabel.READY));
		
		Button btnStartSolving = new Button(grpSolving, SWT.NONE);
		med.setBtnStartSolving(btnStartSolving);
		CustomButton c_btnStartSolving = new CustomButton(Mediator.BUTTON_btnStartSolving,med);
		med.setC_btnStartSolving(c_btnStartSolving);
		c_btnStartSolving.setCustomWidget_btnStartSolving();
		FormData fd_btnStartSolving = new FormData();
		fd_btnStartSolving.top = new FormAttachment(lblStatus, -5, SWT.TOP);
		fd_btnStartSolving.left = new FormAttachment(0,360);
		fd_btnStartSolving.right = new FormAttachment(100,-160);
		btnStartSolving.setLayoutData(fd_btnStartSolving);
		btnStartSolving.setText(LabelDatas.getLabel(UILabel.BUTTONSTARTSOLVING));
		
		/*
		Button btnStopSolving = new Button(grpSolving, SWT.NONE);
		med.setBtnStopSolving(btnStopSolving);
		CustomButton c_btnStopSolving = new CustomButton(Mediator.BUTTON_btnStopSolving, med);
		med.setC_btnStopSolving(c_btnStopSolving);
		c_btnStopSolving.setCustomWidget_btnStopSolving();
		FormData fd_btnStopSolving = new FormData();
		fd_btnStopSolving.top = new FormAttachment(btnStartSolving, 0, SWT.TOP);
		fd_btnStopSolving.left = new FormAttachment(btnStartSolving, 10, SWT.RIGHT);
		fd_btnStopSolving.right = new FormAttachment(100, -20);
		btnStopSolving.setLayoutData(fd_btnStopSolving);
		btnStopSolving.setText(LabelDatas.getLabel(UILabel.BUTTONSTOPSOLVING));
		btnStopSolving.setEnabled(false);
		btnStopSolving.setVisible(false);
		//*/
		
		Button btnStartAnimation = new Button(grpSolving,SWT.NONE);
		med.setBtnStartAnimation(btnStartAnimation);
		CustomButton c_btnStartAnimation = new CustomButton(Mediator.BUTTON_btnStartAnimation, med);
		med.setC_btnStartAnimation(c_btnStartAnimation);
		c_btnStartAnimation.setCustomWidget_btnStartAnimation();
		FormData fd_btnStartAnimation = new FormData();
		fd_btnStartAnimation.top = new FormAttachment(btnStartSolving, 0, SWT.TOP);
		fd_btnStartAnimation.left = new FormAttachment(btnStartSolving, 10, SWT.RIGHT);
		fd_btnStartAnimation.right = new FormAttachment(100, -20);
		btnStartAnimation.setLayoutData(fd_btnStartAnimation);
		btnStartAnimation.setText(LabelDatas.getLabel(UILabel.BUTTONSTARTANIMATION));
		btnStartAnimation.setEnabled(false);
		
		
		
		ProgressBar progressBarSolving = new ProgressBar(grpSolving, SWT.INDETERMINATE);
		med.setProgressBarSolving(progressBarSolving);
		FormData fd_progressBarSolving = new FormData();
		fd_progressBarSolving.top = new FormAttachment(lblStatus,15);
		fd_progressBarSolving.left = new FormAttachment(lblStatus, 0, SWT.LEFT);
		fd_progressBarSolving.right = new FormAttachment(100,-20);
		progressBarSolving.setLayoutData(fd_progressBarSolving);
		progressBarSolving.setVisible(false);
		
		Group grpSetting = new Group(compositeStep4, SWT.NONE);
		grpSetting.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpSetting.setText(LabelDatas.getLabel(UILabel.SETTING));
		grpSetting.setLayout(new FormLayout());
		FormData fd_grpSetting = new FormData();
		fd_grpSetting.top = new FormAttachment(grpSolving,5);
		fd_grpSetting.left = new FormAttachment(grpSolving,0,SWT.LEFT);
		fd_grpSetting.right = new FormAttachment(grpSolving,0,SWT.RIGHT);
		fd_grpSetting.bottom = new FormAttachment(grpSolving,360);
		grpSetting.setLayoutData(fd_grpSetting);
		
		Group grpSolver = new Group(grpSetting, SWT.NONE);
		grpSolver.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpSolver.setText(LabelDatas.getLabel(UILabel.SOLVER));
		grpSolver.setLayout(new FormLayout());
		FormData fd_grpSolver = new FormData();
		fd_grpSolver.top = new FormAttachment(0,5);
		fd_grpSolver.left = new FormAttachment(0,10);
		fd_grpSolver.right = new FormAttachment(0,300);
		fd_grpSolver.bottom = new FormAttachment(0,160);
		grpSolver.setLayoutData(fd_grpSolver);
		
		Label lblIntegrator = new Label(grpSolver, SWT.NONE);
		FormData fd_lblIntegrator = new FormData();
		fd_lblIntegrator.top = new FormAttachment(0, 10);
		fd_lblIntegrator.left = new FormAttachment(0, 10);
		lblIntegrator.setLayoutData(fd_lblIntegrator);
		lblIntegrator.setText(LabelDatas.getLabel(UILabel.INTEGRATOR));

		Composite compositeIntegrator = new Composite(grpSolver, SWT.NONE);
		FormData fd_compositeIntegrator = new FormData();
		fd_compositeIntegrator.top = new FormAttachment(lblIntegrator, 0,SWT.TOP);
		fd_compositeIntegrator.left = new FormAttachment(lblIntegrator, 40);
		fd_compositeIntegrator.right = new FormAttachment(100,-10);
		compositeIntegrator.setLayoutData(fd_compositeIntegrator);
		compositeIntegrator.setLayout(new FormLayout());
		
		Button btnGSTIFF = new Button(compositeIntegrator, SWT.RADIO);
		med.setBtnGSTIFF(btnGSTIFF);
		CustomButton c_btnGSTIFF = new CustomButton(Mediator.BUTTON_btnGSTIFF,med);
		med.setC_btnGSTIFF(c_btnGSTIFF);
		c_btnGSTIFF.setCustomWidget_btnGSTIFF();
		FormData fd_btnGSTIFF = new FormData();
		fd_btnGSTIFF.top = new FormAttachment(0,0);
		fd_btnGSTIFF.left = new FormAttachment(0,0);
		btnGSTIFF.setLayoutData(fd_btnGSTIFF);
		btnGSTIFF.setText(LabelDatas.getLabel(UILabel.GSTIFF));
		btnGSTIFF.setSelection(true);
		
		Button btnWSTIFF = new Button(compositeIntegrator, SWT.RADIO);
		med.setBtnWSTIFF(btnWSTIFF);
		CustomButton c_btnWSTIFF = new CustomButton(Mediator.BUTTON_btnWSTIFF,med);
		med.setC_btnWSTIFF(c_btnWSTIFF);
		c_btnWSTIFF.setCustomWidget_btnWSTIFF();
		FormData fd_btnWSTIFF = new FormData();
		fd_btnWSTIFF.top = new FormAttachment(btnGSTIFF, 0,SWT.TOP);
		fd_btnWSTIFF.left = new FormAttachment(btnGSTIFF, 10,SWT.RIGHT);
		btnWSTIFF.setLayoutData(fd_btnWSTIFF);
		btnWSTIFF.setText(LabelDatas.getLabel(UILabel.WSTIFF));
		
		
		Label lblFormulation = new Label(grpSolver, SWT.NONE);
		FormData fd_lblFormulation = new FormData();
		fd_lblFormulation.top = new FormAttachment(lblIntegrator, 10);
		fd_lblFormulation.left = new FormAttachment(lblIntegrator, 0, SWT.LEFT);
		lblFormulation.setLayoutData(fd_lblFormulation);
		lblFormulation.setText(LabelDatas.getLabel(UILabel.FORMULATION));
		
		Composite compositeFormulator = new Composite(grpSolver, SWT.NONE);
		FormData fd_compositeFormulator = new FormData();
		fd_compositeFormulator.top = new FormAttachment(lblFormulation,0,SWT.TOP);
		fd_compositeFormulator.left = new FormAttachment(compositeIntegrator, 0, SWT.LEFT);
		fd_compositeFormulator.right = new FormAttachment(compositeIntegrator, 0, SWT.RIGHT);
		compositeFormulator.setLayoutData(fd_compositeFormulator);
		compositeFormulator.setLayout(new FormLayout());
		
		Button btnI3 = new Button(compositeFormulator, SWT.RADIO);
		med.setBtnI3(btnI3);
		CustomButton c_btnI3 = new CustomButton(Mediator.BUTTON_btnI3,med);
		med.setC_btnI3(c_btnI3);
		c_btnI3.setCustomWidget_btnI3();
		FormData fd_btnI3 = new FormData();
		fd_btnI3.top = new FormAttachment(0,0);
		fd_btnI3.left = new FormAttachment(0,0);
		btnI3.setLayoutData(fd_btnI3);
		btnI3.setText(LabelDatas.getLabel(UILabel.I3));
		btnI3.setSelection(true);
		
		Button btnSI2 = new Button(compositeFormulator, SWT.RADIO);
		med.setBtnSI2(btnSI2);
		CustomButton c_btnSI2 = new CustomButton(Mediator.BUTTON_btnSI2,med);
		med.setC_btnSI2(c_btnSI2);
		c_btnSI2.setCustomWidget_btnSI2();
		FormData fd_btnSI2 = new FormData();
		fd_btnSI2.top = new FormAttachment(btnI3, 0,SWT.TOP);
		fd_btnSI2.left = new FormAttachment(btnI3, 36,SWT.RIGHT);
		btnSI2.setLayoutData(fd_btnSI2);
		btnSI2.setText(LabelDatas.getLabel(UILabel.SI2));
		
		Label lblCorrector = new Label(grpSolver, SWT.NONE);
		FormData fd_lblCorrector = new FormData();
		fd_lblCorrector.top = new FormAttachment(lblFormulation, 10);
		fd_lblCorrector.left = new FormAttachment(lblIntegrator, 0, SWT.LEFT);
		lblCorrector.setLayoutData(fd_lblCorrector);
		lblCorrector.setText(LabelDatas.getLabel(UILabel.CORRECTOR));
		
		Composite compositeCorrector = new Composite(grpSolver, SWT.NONE);
		FormData fd_compositeCorrector = new FormData();
		fd_compositeCorrector.top = new FormAttachment(lblCorrector, 0,SWT.TOP);
		fd_compositeCorrector.left = new FormAttachment(compositeIntegrator, 0, SWT.LEFT);
		fd_compositeCorrector.right = new FormAttachment(compositeIntegrator, 0, SWT.RIGHT);
		compositeCorrector.setLayoutData(fd_compositeCorrector);
		compositeCorrector.setLayout(new FormLayout());
		
		Button btnOriginal = new Button(compositeCorrector, SWT.RADIO);
		med.setBtnOriginal(btnOriginal);
		CustomButton c_btnOriginal = new CustomButton(Mediator.BUTTON_btnOriginal,med);
		med.setC_btnOriginal(c_btnOriginal);
		c_btnOriginal.setCustomWidget_btnOriginal();
		FormData fd_btnOriginal = new FormData();
		fd_btnOriginal.top = new FormAttachment(0,0);
		fd_btnOriginal.left = new FormAttachment(0,0);
		btnOriginal.setLayoutData(fd_btnOriginal);
		btnOriginal.setText(LabelDatas.getLabel(UILabel.ORIGINAL));
		btnOriginal.setSelection(true);
		
		Button btnModified = new Button(compositeCorrector, SWT.RADIO);
		med.setBtnModified(btnModified);
		CustomButton c_btnModified = new CustomButton(Mediator.BUTTON_btnModified,med);
		med.setC_btnModified(c_btnModified);
		c_btnModified.setCustomWidget_btnModified();
		FormData fd_btnModified = new FormData();
		fd_btnModified.top = new FormAttachment(btnOriginal, 0,SWT.TOP);
		fd_btnModified.left = new FormAttachment(btnOriginal, 5,SWT.RIGHT);
		btnModified.setLayoutData(fd_btnModified);
		btnModified.setText(LabelDatas.getLabel(UILabel.MODIFIED));
		
		Label lblError = new Label(grpSolver, SWT.NONE);
		FormData fd_lblError = new FormData();
		fd_lblError.top = new FormAttachment(lblCorrector, 10);
		fd_lblError.left = new FormAttachment(lblIntegrator, 0, SWT.LEFT);
		lblError.setLayoutData(fd_lblError);
		lblError.setText(LabelDatas.getLabel(UILabel.ERROR));

		textError = new Text(grpSolver, SWT.BORDER);
		med.setTextError(textError);
		CustomText c_textError = new CustomText(Mediator.TEXT_textError,med);
		med.setC_textError(c_textError);
		c_textError.setCustomWidget_textError();
		FormData fd_textError = new FormData();
		fd_textError.top = new FormAttachment(lblError, -2, SWT.TOP);
		fd_textError.left = new FormAttachment(compositeIntegrator, 0, SWT.LEFT);
		fd_textError.right = new FormAttachment(compositeIntegrator, 0, SWT.RIGHT);
		textError.setLayoutData(fd_textError);
		textError.setText(DatabaseStep4.ErrorValue);
		
		Label lblHmax = new Label(grpSolver, SWT.NONE);
		FormData fd_lblHmax = new FormData();
		fd_lblHmax.top = new FormAttachment(lblError, 10);
		fd_lblHmax.left = new FormAttachment(lblIntegrator, 0, SWT.LEFT);
		lblHmax.setLayoutData(fd_lblHmax);
		lblHmax.setText(LabelDatas.getLabel(UILabel.HMAX));

		textHmax = new Text(grpSolver, SWT.BORDER);
		med.setTextHmax(textHmax);
		CustomText c_textHmax = new CustomText(Mediator.TEXT_textHmax, med);
		med.setC_textHmax(c_textHmax);
		c_textHmax.setCustomWidget_textHmax();
		FormData fd_textHmax = new FormData();
		fd_textHmax.top = new FormAttachment(lblHmax, -2, SWT.TOP);
		fd_textHmax.left = new FormAttachment(compositeIntegrator, 0, SWT.LEFT);
		fd_textHmax.right = new FormAttachment(compositeIntegrator, 0, SWT.RIGHT);
		textHmax.setLayoutData(fd_textHmax);
		textHmax.setText(DatabaseStep4.HmaxValue);
		
		Group grpExtraMass = new Group(grpSetting, SWT.NONE);
		grpExtraMass.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpExtraMass.setText(LabelDatas.getLabel(UILabel.EXTRA_MASS));
		grpExtraMass.setLayout(new FormLayout());
		FormData fd_grpExtraMass = new FormData();
		fd_grpExtraMass.top = new FormAttachment(grpSolver,5);
		fd_grpExtraMass.left = new FormAttachment(grpSolver,0,SWT.LEFT);
		fd_grpExtraMass.right = new FormAttachment(grpSolver,0,SWT.RIGHT);
		fd_grpExtraMass.bottom = new FormAttachment(grpSolver,100,SWT.BOTTOM);
		grpExtraMass.setLayoutData(fd_grpExtraMass);
		
		Label lblExtraMass = new Label(grpExtraMass, SWT.NONE);
		FormData fd_lblExtraMass = new FormData();
		fd_lblExtraMass.top = new FormAttachment(0, 10);
		fd_lblExtraMass.left = new FormAttachment(0, 10);
		lblExtraMass.setLayoutData(fd_lblExtraMass);
		lblExtraMass.setText(LabelDatas.getLabel(UILabel.EXTRA_MASS));
		
		Composite compositeExtraMass = new Composite(grpExtraMass, SWT.NONE);
		compositeExtraMass.setLayout(new FormLayout());
		FormData fd_compositeExtraMass = new FormData();
		fd_compositeExtraMass.top = new FormAttachment(lblExtraMass, 0, SWT.TOP);
		fd_compositeExtraMass.left = new FormAttachment(lblExtraMass, 40,SWT.RIGHT);
		fd_compositeExtraMass.right = new FormAttachment(100, -10);
		fd_compositeExtraMass.bottom = new FormAttachment(lblExtraMass,0, SWT.BOTTOM);
		compositeExtraMass.setLayoutData(fd_compositeExtraMass);
		
		Button btnExtraMassOn = new Button(compositeExtraMass, SWT.RADIO);
		med.setBtnExtraMassOn(btnExtraMassOn);
		CustomButton c_btnExtraMassOn = new CustomButton(Mediator.BUTTON_btnExtraMassOn,med);
		med.setC_btnExtraMassOn(c_btnExtraMassOn);
		c_btnExtraMassOn.setCustomWidget_btnExtraMassOn();
		FormData fd_btnExtraMassOn = new FormData();
		fd_btnExtraMassOn.top = new FormAttachment(0);
		fd_btnExtraMassOn.left = new FormAttachment(0);
		btnExtraMassOn.setLayoutData(fd_btnExtraMassOn);
		btnExtraMassOn.setText(LabelDatas.getLabel(UILabel.ON));
		
		Button btnExtraMassOff = new Button(compositeExtraMass, SWT.RADIO);
		med.setBtnExtraMassOff(btnExtraMassOff);
		CustomButton c_btnExtraMassOff = new CustomButton(Mediator.BUTTON_btnExtraMassOff,med);
		med.setC_btnExtraMassOff(c_btnExtraMassOff);
		c_btnExtraMassOff.setCustomWidget_btnExtraMassOff();
		FormData fd_btnExtraMassOff = new FormData();
		fd_btnExtraMassOff.top = new FormAttachment(0);
		fd_btnExtraMassOff.left = new FormAttachment(btnExtraMassOn, 15);
		btnExtraMassOff.setLayoutData(fd_btnExtraMassOff);
		btnExtraMassOff.setText(LabelDatas.getLabel(UILabel.OFF));
		btnExtraMassOff.setSelection(true);
		
		Label lblExtraMassRatio = new Label(grpExtraMass, SWT.NONE);
		FormData fd_lblExtraMassRatio = new FormData();
		fd_lblExtraMassRatio.top = new FormAttachment(lblExtraMass, 10);
		fd_lblExtraMassRatio.left = new FormAttachment(lblExtraMass, 0, SWT.LEFT);
		lblExtraMassRatio.setLayoutData(fd_lblExtraMassRatio);
		lblExtraMassRatio.setText(LabelDatas.getLabel(UILabel.EXTRA_MASS_RATIO));
		
		textExtraMassRatio = new Text(grpExtraMass, SWT.BORDER);
		med.setTextExtraMassRatio(textExtraMassRatio);
		CustomText c_textExtraMassRatio = new CustomText(Mediator.TEXT_textExtraMassRatio,med);
		med.setC_textExtraMassRatio(c_textExtraMassRatio);
		c_textExtraMassRatio.setCustomWidget_textExtraMassRatio();
		FormData fd_textExtraMassRatio = new FormData();
		fd_textExtraMassRatio.top = new FormAttachment(lblExtraMassRatio, -2,SWT.TOP);
		fd_textExtraMassRatio.left = new FormAttachment(compositeExtraMass, 0, SWT.LEFT);
		fd_textExtraMassRatio.right = new FormAttachment(compositeExtraMass, 0, SWT.RIGHT);
		textExtraMassRatio.setLayoutData(fd_textExtraMassRatio);
		textExtraMassRatio.setText(DatabaseStep4.ExtraMassRatioValue);
		textExtraMassRatio.setEnabled(false);
		
		Label lblRatioDetail = new Label(grpExtraMass, SWT.NONE);
		lblRatioDetail.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		lblRatioDetail.setFont(SWTResourceManager.getFont("Arial", 8, SWT.ITALIC));
		FormData fd_lblRatioDetail = new FormData();
		fd_lblRatioDetail.top = new FormAttachment(lblExtraMassRatio, 6);
		fd_lblRatioDetail.left = new FormAttachment(0,10);
		fd_lblRatioDetail.right = new FormAttachment(100, -10);
		lblRatioDetail.setLayoutData(fd_lblRatioDetail);
		//lblRatioDetail.setText("Ratio  :  1 = 18g(Rubber) / 100mm(Length)");
		lblRatioDetail.setText(LabelDatas.getLabel(UILabel.EXTRA_MASS_RATIO_DETAIL));
		
		
		Group grpSimulationCondition = new Group(grpSetting, SWT.NONE);
		grpSimulationCondition.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpSimulationCondition.setText(LabelDatas.getLabel(UILabel.SIMULATION_CONDITION));
		FormData fd_grpSimulationCondition = new FormData();
		fd_grpSimulationCondition.top = new FormAttachment(grpSolver,0,SWT.TOP);
		fd_grpSimulationCondition.left = new FormAttachment(grpSolver, 10, SWT.RIGHT);
		fd_grpSimulationCondition.right = new FormAttachment(100, -10);
		fd_grpSimulationCondition.bottom = new FormAttachment(0 , 130);
		grpSimulationCondition.setLayout(new FormLayout());
		grpSimulationCondition.setLayoutData(fd_grpSimulationCondition);
		
		Label lblNumberOfStep = new Label(grpSimulationCondition, SWT.NONE);
		FormData fd_lblNumberOfStep = new FormData();
		fd_lblNumberOfStep.top = new FormAttachment(0, 10);
		fd_lblNumberOfStep.left = new FormAttachment(0, 10);
		lblNumberOfStep.setLayoutData(fd_lblNumberOfStep);
		lblNumberOfStep.setText(LabelDatas.getLabel(UILabel.NUMBEROFSTEP));
		
		textNumberOfStep = new Text(grpSimulationCondition, SWT.BORDER);
		med.setTextNumberOfStep(textNumberOfStep);
		CustomText c_textNumberOfStep = new CustomText(Mediator.TEXT_textNumberOfStep, med);
		med.setC_textNumberOfStep(c_textNumberOfStep);
		c_textNumberOfStep.setCustomWidget_textNumberOfStep();
		FormData fd_textNumberOfStep = new FormData();
		fd_textNumberOfStep.top = new FormAttachment(lblNumberOfStep, -2, SWT.TOP);
		fd_textNumberOfStep.left = new FormAttachment(lblNumberOfStep, 40, SWT.RIGHT);
		fd_textNumberOfStep.right = new FormAttachment(100, -10);
		textNumberOfStep.setLayoutData(fd_textNumberOfStep);
		textNumberOfStep.setText(DatabaseStep4.NumberOfStepValue);
		
		Label lblEndTime = new Label(grpSimulationCondition, SWT.NONE);
		FormData fd_lblEndTime = new FormData();
		fd_lblEndTime.top = new FormAttachment(lblNumberOfStep, 10);
		fd_lblEndTime.left = new FormAttachment(lblNumberOfStep, 0,SWT.LEFT);
		lblEndTime.setLayoutData(fd_lblEndTime);
		lblEndTime.setText(LabelDatas.getLabel(UILabel.END_TIME));
		
		textEndTime = new Text(grpSimulationCondition, SWT.BORDER);
		med.setTextEndTime(textEndTime);
		CustomText c_textEndTime = new CustomText(Mediator.TEXT_textEndTime,med);
		med.setC_textEndTime(c_textEndTime);
		c_textEndTime.setCustomWidget_textEndTime();
		FormData fd_textEndTime = new FormData();
		fd_textEndTime.top = new FormAttachment(lblEndTime, -2, SWT.TOP);
		fd_textEndTime.left = new FormAttachment(textNumberOfStep, 0, SWT.LEFT);
		fd_textEndTime.right = new FormAttachment(100, -10);
		textEndTime.setLayoutData(fd_textEndTime);
		textEndTime.setText(DatabaseStep4.EndTimeValue);
		
		/*
		Label lblExtraMass = new Label(grpSimulationCondition, SWT.NONE);
		FormData fd_lblExtraMass = new FormData();
		fd_lblExtraMass.top = new FormAttachment(lblEndTime, 10);
		fd_lblExtraMass.left = new FormAttachment(lblEndTime, 0,SWT.LEFT);
		lblExtraMass.setLayoutData(fd_lblExtraMass);
		lblExtraMass.setText(LabelDatas.getLabel(UILabel.EXTRA_MASS));
		
		Composite compositeExtraMass = new Composite(grpSimulationCondition, SWT.NONE);
		compositeExtraMass.setLayout(new FormLayout());
		FormData fd_compositeExtraMass = new FormData();
		fd_compositeExtraMass.top = new FormAttachment(lblExtraMass, 0, SWT.TOP);
		fd_compositeExtraMass.left = new FormAttachment(textNumberOfStep, 0,SWT.LEFT);
		fd_compositeExtraMass.right = new FormAttachment(100, -10);
		fd_compositeExtraMass.bottom = new FormAttachment(lblExtraMass,0, SWT.BOTTOM);
		compositeExtraMass.setLayoutData(fd_compositeExtraMass);
		
		Button btnExtraMassOn = new Button(compositeExtraMass, SWT.RADIO);
		med.setBtnExtraMassOn(btnExtraMassOn);
		CustomButton c_btnExtraMassOn = new CustomButton(Mediator.BUTTON_btnExtraMassOn,med);
		med.setC_btnExtraMassOn(c_btnExtraMassOn);
		c_btnExtraMassOn.setCustomWidget_btnExtraMassOn();
		FormData fd_btnExtraMassOn = new FormData();
		fd_btnExtraMassOn.top = new FormAttachment(0);
		fd_btnExtraMassOn.left = new FormAttachment(0);
		btnExtraMassOn.setLayoutData(fd_btnExtraMassOn);
		btnExtraMassOn.setText(LabelDatas.getLabel(UILabel.ON));
		
		Button btnExtraMassOff = new Button(compositeExtraMass, SWT.RADIO);
		med.setBtnExtraMassOff(btnExtraMassOff);
		CustomButton c_btnExtraMassOff = new CustomButton(Mediator.BUTTON_btnExtraMassOff,med);
		med.setC_btnExtraMassOff(c_btnExtraMassOff);
		c_btnExtraMassOff.setCustomWidget_btnExtraMassOff();
		FormData fd_btnExtraMassOff = new FormData();
		fd_btnExtraMassOff.top = new FormAttachment(0);
		fd_btnExtraMassOff.left = new FormAttachment(btnExtraMassOn, 15);
		btnExtraMassOff.setLayoutData(fd_btnExtraMassOff);
		btnExtraMassOff.setText(LabelDatas.getLabel(UILabel.OFF));
		btnExtraMassOff.setSelection(true);
		
		Label lblExtraMassRatio = new Label(grpSimulationCondition, SWT.NONE);
		FormData fd_lblExtraMassRatio = new FormData();
		fd_lblExtraMassRatio.top = new FormAttachment(lblExtraMass, 10);
		fd_lblExtraMassRatio.left = new FormAttachment(lblEndTime, 0,SWT.LEFT);
		lblExtraMassRatio.setLayoutData(fd_lblExtraMassRatio);
		lblExtraMassRatio.setText(LabelDatas.getLabel(UILabel.EXTRA_MASS_RATIO));
		
		
		textExtraMassRatio = new Text(grpSimulationCondition, SWT.BORDER);
		med.setTextExtraMassRatio(textExtraMassRatio);
		CustomText c_textExtraMassRatio = new CustomText(Mediator.TEXT_textExtraMassRatio,med);
		med.setC_textExtraMassRatio(c_textExtraMassRatio);
		c_textExtraMassRatio.setCustomWidget_textExtraMassRatio();
		FormData fd_textExtraMassRatio = new FormData();
		fd_textExtraMassRatio.top = new FormAttachment(compositeExtraMass, 2);
		fd_textExtraMassRatio.left = new FormAttachment(textNumberOfStep, 0, SWT.LEFT);
		fd_textExtraMassRatio.right = new FormAttachment(textNumberOfStep, 0, SWT.RIGHT);
		textExtraMassRatio.setLayoutData(fd_textExtraMassRatio);
		textExtraMassRatio.setText(DatabaseStep4.ExtraMassRatioValue);
		textExtraMassRatio.setEnabled(false);
		// */
		
		Group grpAnimationCondition = new Group(grpSetting, SWT.NONE);
		grpAnimationCondition.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpAnimationCondition.setLayout(new FormLayout());
		FormData fd_grpAnimationCondition = new FormData();
		fd_grpAnimationCondition.top = new FormAttachment(grpSimulationCondition, 6);
		fd_grpAnimationCondition.left = new FormAttachment(grpSimulationCondition,0,SWT.LEFT);
		fd_grpAnimationCondition.right = new FormAttachment(grpSimulationCondition, 0, SWT.RIGHT);
		fd_grpAnimationCondition.bottom = new FormAttachment(grpExtraMass, 0,SWT.BOTTOM);
		grpAnimationCondition.setLayoutData(fd_grpAnimationCondition);
		grpAnimationCondition.setText(LabelDatas.getLabel(UILabel.ANIMATION_CONDITION));
		
		Label lblNumberOfCycles = new Label(grpAnimationCondition, SWT.NONE);
		FormData fd_lblNumberOfCycles = new FormData();
		fd_lblNumberOfCycles.top = new FormAttachment(0, 6);
		fd_lblNumberOfCycles.left = new FormAttachment(0, 10);
		lblNumberOfCycles.setLayoutData(fd_lblNumberOfCycles);
		lblNumberOfCycles.setText(LabelDatas.getLabel(UILabel.NUMBER_OF_CYCLES));
		
		textNumberOfCycles = new Text(grpAnimationCondition, SWT.BORDER);
		med.setTextNumberOfCycles(textNumberOfCycles);
		CustomText c_textNumberOfCycles = new CustomText(Mediator.TEXT_textNumberOfCycles,med);
		med.setC_textNumberOfCycles(c_textNumberOfCycles);
		c_textNumberOfCycles.setCustomWidget_textNumberOfCycles();
		FormData fd_textNumberOfCycles = new FormData();
		fd_textNumberOfCycles.top = new FormAttachment(lblNumberOfCycles, -2, SWT.TOP);
		fd_textNumberOfCycles.right = new FormAttachment(100, -10);
		fd_textNumberOfCycles.left = new FormAttachment(lblNumberOfCycles, 40);
		textNumberOfCycles.setLayoutData(fd_textNumberOfCycles);
		textNumberOfCycles.setText(DatabaseStep4.NumberOfCyclesValue);
		
		Label lblStartTimeRange = new Label(grpAnimationCondition, SWT.NONE);
		FormData fd_lblStartTimeRange = new FormData();
		fd_lblStartTimeRange.top = new FormAttachment(lblNumberOfCycles, 10);
		fd_lblStartTimeRange.left = new FormAttachment(lblNumberOfCycles, 0, SWT.LEFT);
		lblStartTimeRange.setLayoutData(fd_lblStartTimeRange);
		lblStartTimeRange.setText(LabelDatas.getLabel(UILabel.START_TIME_RANGE));
		
		textStartTimeRange = new Text(grpAnimationCondition, SWT.BORDER);
		med.setTextStartTimeRange(textStartTimeRange);
		CustomText c_textStartTimeRange = new CustomText(Mediator.TEXT_textStartTimeRange,med);
		med.setC_textStartTimeRange(c_textStartTimeRange);
		c_textStartTimeRange.setCustomWidget_textStartTimeRange();
		FormData fd_textStartTimeRange = new FormData();
		fd_textStartTimeRange.top = new FormAttachment(lblStartTimeRange, -2, SWT.TOP);
		fd_textStartTimeRange.right = new FormAttachment(100, -10);
		fd_textStartTimeRange.left = new FormAttachment(textNumberOfCycles, 0, SWT.LEFT);
		textStartTimeRange.setLayoutData(fd_textStartTimeRange);
		textStartTimeRange.setText(DatabaseStep4.StartTimeRangeValue);
		
		Label lblEndTimeRange = new Label(grpAnimationCondition, SWT.NONE);
		FormData fd_lblEndTimeRange = new FormData();
		fd_lblEndTimeRange.top = new FormAttachment(lblStartTimeRange, 10);
		fd_lblEndTimeRange.left = new FormAttachment(lblStartTimeRange, 0, SWT.LEFT);
		lblEndTimeRange.setLayoutData(fd_lblEndTimeRange);
		lblEndTimeRange.setText(LabelDatas.getLabel(UILabel.END_TIME_RANGE));
		
		textEndTimeRange = new Text(grpAnimationCondition, SWT.BORDER);
		med.setTextEndTimeRange(textEndTimeRange);
		CustomText c_textEndTimeRange = new CustomText(Mediator.TEXT_textEndTimeRange,med);
		med.setC_textEndTimeRange(c_textEndTimeRange);
		c_textEndTimeRange.setCustomWidget_textEndTimeRange();
		FormData fd_textEndTimeRange = new FormData();
		fd_textEndTimeRange.top = new FormAttachment(lblEndTimeRange, -2, SWT.TOP);
		fd_textEndTimeRange.left = new FormAttachment(textNumberOfCycles, 0, SWT.LEFT);
		fd_textEndTimeRange.right = new FormAttachment(100, -10);
		textEndTimeRange.setLayoutData(fd_textEndTimeRange);
		textEndTimeRange.setText(DatabaseStep4.EndTimeRangeValue);
		
		Label lblIncrementFrame = new Label(grpAnimationCondition, SWT.NONE);
		FormData fd_lblIncrementFrame = new FormData();
		fd_lblIncrementFrame.top = new FormAttachment(lblEndTimeRange, 10);
		fd_lblIncrementFrame.left = new FormAttachment(lblEndTimeRange, 0, SWT.LEFT);
		lblIncrementFrame.setLayoutData(fd_lblIncrementFrame);
		lblIncrementFrame.setText(LabelDatas.getLabel(UILabel.INCREMENT_FRAME));
		
		textIncrementFrame = new Text(grpAnimationCondition, SWT.BORDER);
		med.setTextIncrementFrame(textIncrementFrame);
		CustomText c_textIncrementFrame = new CustomText(Mediator.TEXT_textIncrementFrame,med);
		med.setC_textIncrementFrame(c_textIncrementFrame);
		c_textIncrementFrame.setCustomWidget_textIncrementFrame();
		FormData fd_textIncrementFrame = new FormData();
		fd_textIncrementFrame.top = new FormAttachment(lblIncrementFrame, -2, SWT.TOP);
		fd_textIncrementFrame.left = new FormAttachment(textNumberOfCycles, 0, SWT.LEFT);
		fd_textIncrementFrame.right = new FormAttachment(100, -10);
		textIncrementFrame.setLayoutData(fd_textIncrementFrame);
		textIncrementFrame.setText(DatabaseStep4.IncrementFrameValue);
		
		
		Group grpExportResult = new Group(compositeStep4, SWT.NONE);
		grpExportResult.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpExportResult.setText(LabelDatas.getLabel(UILabel.EXPORTRESULT));
		grpExportResult.setLayout(new FormLayout());
		FormData fd_grpExportResult = new FormData();
		fd_grpExportResult.top = new FormAttachment(grpSetting, 5);
		fd_grpExportResult.left = new FormAttachment(grpSetting, 0, SWT.LEFT);
		fd_grpExportResult.right = new FormAttachment(grpSetting, 0, SWT.RIGHT);
		fd_grpExportResult.bottom = new FormAttachment(grpSetting, 440);
		grpExportResult.setLayoutData(fd_grpExportResult);
		
		Label lblResultName = new Label(grpExportResult, SWT.NONE);
		FormData fd_lblResultName = new FormData();
		fd_lblResultName.top = new FormAttachment(0,10);
		fd_lblResultName.left = new FormAttachment(0,15);
		lblResultName.setLayoutData(fd_lblResultName);
		lblResultName.setText(LabelDatas.getLabel(UILabel.RESULTNAME));
		
		textResultName = new Text(grpExportResult, SWT.BORDER);
		med.setTextResultName(textResultName);
		CustomText c_textResultName = new CustomText(Mediator.TEXT_textResultName,med);
		med.setC_textResultName(c_textResultName);
		c_textResultName.setCustomWidget_textResultName();
		FormData fd_textResultName = new FormData();
		fd_textResultName.top = new FormAttachment(lblResultName, -3, SWT.TOP);
		fd_textResultName.left = new FormAttachment(lblResultName, 20);
		fd_textResultName.right = new FormAttachment(lblResultName, 220);
		textResultName.setLayoutData(fd_textResultName);
		textResultName.setText("Base");
		
		
		Composite compositeExportResult = new Composite(grpExportResult, SWT.NONE);
		compositeExportResult.setLayout(new FormLayout());
		FormData fd_compositeExportResult = new FormData();
		fd_compositeExportResult.top = new FormAttachment(0, 35);
		fd_compositeExportResult.left = new FormAttachment(0, 30);
		fd_compositeExportResult.right = new FormAttachment(100,-20);
		fd_compositeExportResult.bottom = new FormAttachment(0,90);
		compositeExportResult.setLayoutData(fd_compositeExportResult);
		
		Button btnModelData = new Button(compositeExportResult, SWT.CHECK);
		med.setBtnModelData(btnModelData);
		CustomButton c_btnModelData = new CustomButton(Mediator.BUTTON_btnModelData,med);
		med.setC_btnModelData(c_btnModelData);
		c_btnModelData.setCustomWidget_btnModelData();
		FormData fd_btnModelData = new FormData();
		fd_btnModelData.top = new FormAttachment(0, 5);
		fd_btnModelData.left = new FormAttachment(0, 10);
		btnModelData.setLayoutData(fd_btnModelData);
		btnModelData.setText(LabelDatas.getLabel(UILabel.CHECKMODELDATA));
		btnModelData.setSelection(true);
		
		Button btnDACFile = new Button(compositeExportResult, SWT.CHECK);
		med.setBtnDACFile(btnDACFile);
		CustomButton c_btnDACFile = new CustomButton(Mediator.BUTTON_btnDACFile,med);
		med.setC_btnDACFile(c_btnDACFile);
		c_btnDACFile.setCustomWidget_btnDACFile();
		FormData fd_btnDACFile = new FormData();
		fd_btnDACFile.top = new FormAttachment(btnModelData,0,SWT.TOP);
		fd_btnDACFile.left = new FormAttachment(0, 300);
		btnDACFile.setLayoutData(fd_btnDACFile);
		btnDACFile.setText(LabelDatas.getLabel(UILabel.CHECKDACFILE));
		btnDACFile.setSelection(true);
		
		Button btnModelDataBin = new Button(compositeExportResult, SWT.CHECK);
		med.setBtnModelDataBin(btnModelDataBin);
		CustomButton c_btnModelDataBin = new CustomButton(Mediator.BUTTON_btnModelDataBin,med);
		med.setC_btnModelDataBin(c_btnModelDataBin);
		c_btnModelDataBin.setCustomWidget_btnModelDataBin();
		FormData fd_btnModelDataBin = new FormData();
		fd_btnModelDataBin.top = new FormAttachment(btnModelData, 12);
		fd_btnModelDataBin.left = new FormAttachment(btnModelData, 0, SWT.LEFT);
		btnModelDataBin.setLayoutData(fd_btnModelDataBin);
		btnModelDataBin.setText(LabelDatas.getLabel(UILabel.CHECKMODELDATABIN));
		btnModelDataBin.setSelection(true);
		
		Button btnForceFile = new Button(compositeExportResult, SWT.CHECK);
		med.setBtnForceFile(btnForceFile);
		CustomButton c_btnForceFile = new CustomButton(Mediator.BUTTON_btnForceFile,med);
		med.setC_btnForceFile(c_btnForceFile);
		c_btnForceFile.setCustomWidget_btnForceFile();
		FormData fd_btnForceFile = new FormData();
		fd_btnForceFile.top = new FormAttachment(btnModelDataBin,0,SWT.TOP);
		fd_btnForceFile.left = new FormAttachment(btnDACFile , 0, SWT.LEFT);
		btnForceFile.setLayoutData(fd_btnForceFile);
		btnForceFile.setText(LabelDatas.getLabel(UILabel.CHECKFORCEFILE));
		btnForceFile.setSelection(true);
		
		Button btnExportResult = new Button(grpExportResult, SWT.NONE);
		med.setBtnExportResult(btnExportResult);
		CustomButton c_btnExportResult = new CustomButton(Mediator.BUTTON_btnExportResult,med);
		med.setC_btnExportResult(c_btnExportResult);
		c_btnExportResult.setCustomWidget_btnExportResult();
		FormData fd_btnExportResult = new FormData();
		fd_btnExportResult.top = new FormAttachment(compositeExportResult, 6);
		fd_btnExportResult.left = new FormAttachment(0,500);
		fd_btnExportResult.right = new FormAttachment(100,-20);
		btnExportResult.setLayoutData(fd_btnExportResult);
		btnExportResult.setText(LabelDatas.getLabel(UILabel.BUTTONEXPORTRESULTS));
		btnExportResult.setEnabled(false);
		
		
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
		compositeStep5 = new Composite(compositeBottom, SWT.NONE);
		compositeStep5.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		med.setCompositeStep5(compositeStep5);
		compositeStep5.setLayout(new FormLayout());
		
		Label lblStep5Title = new Label(compositeStep5, SWT.NONE);
		lblStep5Title.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblStep5Title = new FormData();
		fd_lblStep5Title.top = new FormAttachment(0, 10);
		fd_lblStep5Title.left = new FormAttachment(0, 10);
		lblStep5Title.setLayoutData(fd_lblStep5Title);
		lblStep5Title.setText(LabelDatas.getLabel(UILabel.Title_S5));
		
		
		/* */
		TableViewer tableViewerFatSolving = new TableViewer(compositeStep5, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		tableFatSolving = tableViewerFatSolving.getTable();
		tableFatSolving.setLinesVisible(true);
		tableFatSolving.setHeaderVisible(true);
		med.setTableFatSolving(tableFatSolving);
		CustomTable c_tableFatSolving = new CustomTable(Mediator.TABLE_tableFatSolving,med);
		med.setC_tableFatSolving(c_tableFatSolving);
		c_tableFatSolving.setCustomWidget_tableFatSolving();
		FormData fd_tableFatSolving = new FormData();
		fd_tableFatSolving.top = new FormAttachment(lblStep5Title, 15);
		fd_tableFatSolving.left = new FormAttachment(0,10);
		fd_tableFatSolving.right = new FormAttachment(100,-10);
		fd_tableFatSolving.bottom = new FormAttachment(100,-80);
		tableFatSolving.setLayoutData(fd_tableFatSolving);
		
		TableColumn tblclmnCheck_S5 = new TableColumn(tableFatSolving, SWT.NONE);
		tblclmnCheck_S5.setWidth(30);
		
		TableColumn tblclmnPartName_S5 = new TableColumn(tableFatSolving, SWT.NONE);
		tblclmnPartName_S5.setWidth(150);
		tblclmnPartName_S5.setText(LabelDatas.getLabel(UILabel.PARTNAME_S5));
		tblclmnPartName_S5.setAlignment(SWT.CENTER);
		
		TableColumn tblclmnType_S5 = new TableColumn(tableFatSolving, SWT.NONE);
		tblclmnType_S5.setWidth(150);
		tblclmnType_S5.setText(LabelDatas.getLabel(UILabel.TYPE_S5));
		tblclmnType_S5.setAlignment(SWT.CENTER);
		
		TableColumn tblclmnPath_S5 = new TableColumn(tableFatSolving, SWT.NONE);
		tblclmnPath_S5.setWidth(500);
		tblclmnPath_S5.setText(LabelDatas.getLabel(UILabel.PATH_S5)); 
		//*/
		
		Label lblAnalysisSetting = new Label(compositeStep5, SWT.NONE);
		lblAnalysisSetting.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblAnalysisSetting = new FormData();
		fd_lblAnalysisSetting.top = new FormAttachment(tableFatSolving, 10);
		fd_lblAnalysisSetting.left = new FormAttachment(lblStep5Title, 0, SWT.LEFT);
		lblAnalysisSetting.setLayoutData(fd_lblAnalysisSetting);
		lblAnalysisSetting.setText(LabelDatas.getLabel(UILabel.ANALYSIS_SETTING));
		
		Label lblCyclenumber = new Label(compositeStep5, SWT.NONE);
		FormData fd_lblCyclenumber = new FormData();
		fd_lblCyclenumber.top = new FormAttachment(lblAnalysisSetting, 12);
		fd_lblCyclenumber.left = new FormAttachment(lblAnalysisSetting, 5, SWT.LEFT);
		lblCyclenumber.setLayoutData(fd_lblCyclenumber);
		lblCyclenumber.setText(LabelDatas.getLabel(UILabel.CYCLENUMBER));
		
		textCycleNumber = new Text(compositeStep5, SWT.BORDER);
		/*
		med.setTextResultName(textResultName);
		CustomText c_textResultName = new CustomText(Mediator.TEXT_textResultName,med);
		med.setC_textResultName(c_textResultName);
		c_textResultName.setCustomWidget_textResultName();
		 */
		med.setTextCycleNumber(textCycleNumber);
		CustomText c_textCycleNumber = new CustomText(Mediator.TEXT_textCycleNumber,med);
		med.setC_textCycleNumber(c_textCycleNumber);
		c_textCycleNumber.setCustomWidget_textCycleNumber();
		FormData fd_textCycleNumber = new FormData();
		fd_textCycleNumber.top = new FormAttachment(lblCyclenumber, -2, SWT.TOP);
		fd_textCycleNumber.left = new FormAttachment(lblCyclenumber, 10);
		textCycleNumber.setLayoutData(fd_textCycleNumber);
		textCycleNumber.setText("1.50e+05");
		
		Button btnSolvingAndCreate = new Button(compositeStep5, SWT.NONE);
		med.setBtnSolvingAndCreate(btnSolvingAndCreate);
		CustomButton c_btnSolvingAndCreate = new CustomButton(Mediator.BUTTON_btnSolvingAndCreate,med);
		med.setC_btnSolvingAndCreate(c_btnSolvingAndCreate);
		c_btnSolvingAndCreate.setCustomWidget_btnSolvingAndCreate();
		FormData fd_btnSolvingAndCreate = new FormData();
		fd_btnSolvingAndCreate.top = new FormAttachment(tableFatSolving, 15);
		fd_btnSolvingAndCreate.right = new FormAttachment(tableFatSolving,0,SWT.RIGHT);
		btnSolvingAndCreate.setLayoutData(fd_btnSolvingAndCreate);
		btnSolvingAndCreate.setText(LabelDatas.getLabel(UILabel.SOLVING_CREATE));
		
		
		
		
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		
		init_WidgetSetting();
		init_WidgetEvent();
		DisableSettingtApp();
		
		MC.addMsgWindow("Start Wiper-Linkage Analysis Tool",MessageWindow.Info);
	}

	private void init_WidgetSetting() {
		// TODO Auto-generated method stub
		// 현재 Step 저장 및 최상위 화면에 표시
		
		stackLayout.topControl = compositeStep1;
		MC.setCurrentStep(MC.STEP1);
		
		// Reference Variable 
		MC.ReadPreferenceFile();
		// Load combo and List(Step1) data  
		//-> appDatabase 폴더안에 Blade / Linkage 타입별로 데이터 가져오기
		MC.LoadingAppDataBase_Blade();
		MC.LoadingAppDataBase_Linkage();
		MC.LoadingAppDatabase_Windshield();
		//-> List(Blade / Linkage 타입 1을 기본으로 해당 정보  List에 Load
		MC.LoadingStep1List_Blade();
		MC.LoadingStep1List_Linkage();
		MC.LoadingStep1List_Windshield();
		
	}
	
	private void init_WidgetEvent() {
		// TODO Auto-generated method stub
		// Button
		HandlerButton handlerButton = new HandlerButton();
		med.getBtnPrevious().addListener(SWT.Selection, handlerButton);
		med.getBtnNext().addListener(SWT.Selection, handlerButton);
		med.getBtnEditAllData().addListener(SWT.Selection, handlerButton);
		med.getBtnSaveAllData().addListener(SWT.Selection, handlerButton);
		med.getBtnReloadDb().addListener(SWT.Selection, handlerButton);
		med.getBtnNone().addListener(SWT.Selection, handlerButton);
		med.getBtnAdd().addListener(SWT.Selection, handlerButton);
		med.getBtnDel().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorerStep3().addListener(SWT.Selection, handlerButton);
		med.getBtnStartSolving().addListener(SWT.Selection, handlerButton);
		//med.getBtnStopSolving().addListener(SWT.Selection, handlerButton);
		med.getBtnStartAnimation().addListener(SWT.Selection, handlerButton);
		med.getBtnGSTIFF().addListener(SWT.Selection, handlerButton);
		med.getBtnWSTIFF().addListener(SWT.Selection, handlerButton);
		med.getBtnI3().addListener(SWT.Selection, handlerButton);
		med.getBtnSI2().addListener(SWT.Selection, handlerButton);
		med.getBtnOriginal().addListener(SWT.Selection, handlerButton);
		med.getBtnModified().addListener(SWT.Selection, handlerButton);
		med.getBtnExtraMassOn().addListener(SWT.Selection, handlerButton);
		med.getBtnExtraMassOff().addListener(SWT.Selection, handlerButton);
		med.getBtnModelData().addListener(SWT.Selection, handlerButton);
		med.getBtnDACFile().addListener(SWT.Selection, handlerButton);
		med.getBtnModelDataBin().addListener(SWT.Selection, handlerButton);
		med.getBtnForceFile().addListener(SWT.Selection, handlerButton);
		med.getBtnExportResult().addListener(SWT.Selection, handlerButton);
		med.getBtnSolvingAndCreate().addListener(SWT.Selection, handlerButton);
		// Combo
		HandlerCombo handlerCombo = new HandlerCombo();
		med.getComboBladeType().addListener(SWT.Selection, handlerCombo);
		med.getComboLinkageType().addListener(SWT.Selection, handlerCombo);
		med.getComboWindshieldType().addListener(SWT.Selection, handlerCombo);
		// Label
		HandlerLabel handlerLabel = new HandlerLabel();
		med.getLblStep1().addListener(SWT.MouseDoubleClick, handlerLabel);
		med.getLblStep2().addListener(SWT.MouseDoubleClick, handlerLabel);
		med.getLblStep3().addListener(SWT.MouseDoubleClick, handlerLabel);
		med.getLblStep4().addListener(SWT.MouseDoubleClick, handlerLabel);
		med.getLblStep5().addListener(SWT.MouseDoubleClick, handlerLabel);
		// List 
		HandlerList handlerList = new HandlerList();
		med.getListBladeDatabase().addListener(SWT.Selection, handlerList);
		med.getListLinkageDatabase().addListener(SWT.Selection, handlerList);
		med.getListPart().addListener(SWT.Selection, handlerList);
		med.getListSwappingPart().addListener(SWT.Selection, handlerList);
		med.getListWindShieldDatabase().addListener(SWT.Selection, handlerList);
		HandlerListDoubleClick hadlerListDoubleClick = new HandlerListDoubleClick();
		med.getListBladeDatabase().addMouseListener(hadlerListDoubleClick);
		med.getListLinkageDatabase().addMouseListener(hadlerListDoubleClick);
		// Table
		HandlerTable handlerTable = new HandlerTable();
		med.getTableSwappingPart().addListener(SWT.Selection, handlerTable);
		med.getTableFatSolving().addListener(SWT.Selection, handlerTable);
		// Text
		HandlerText handlerText = new HandlerText();
		med.getTextMnfFilePath().addListener(SWT.CHANGED, handlerText);
		med.getTextRadiusValue().addListener(SWT.CHANGED, handlerText);
		med.getTextXValue().addListener(SWT.CHANGED, handlerText);
		med.getTextYValue().addListener(SWT.CHANGED, handlerText);
		med.getTextZValue().addListener(SWT.CHANGED, handlerText);
		med.getTextError().addListener(SWT.CHANGED, handlerText);
		med.getTextHmax().addListener(SWT.CHANGED, handlerText);
		med.getTextNumberOfStep().addListener(SWT.CHANGED, handlerText);
		med.getTextEndTime().addListener(SWT.CHANGED, handlerText);
		med.getTextExtraMassRatio().addListener(SWT.CHANGED, handlerText);
		med.getTextNumberOfCycles().addListener(SWT.CHANGED, handlerText);
		med.getTextStartTimeRange().addListener(SWT.CHANGED, handlerText);
		med.getTextEndTimeRange().addListener(SWT.CHANGED, handlerText);
		med.getTextIncrementFrame().addListener(SWT.CHANGED, handlerText);
		med.getTextResultName().addListener(SWT.CHANGED, handlerText);
		med.getTextCycleNumber().addListener(SWT.CHANGED, handlerText);
	}
	
	public void DisableSettingtApp(){
		MC.DisableAllComponent();
	}
	public void setFocus() {}
}