package com.js.ens.leveller;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;

import com.js.ens.leveller.core.ComboLabel;
import com.js.ens.leveller.core.LevellerMain;
import com.js.ens.leveller.core.Mediator;
import com.js.ens.leveller.core.TableColumnLabel;
import com.js.ens.leveller.core.TextLabel;
import com.js.ens.leveller.core.TextLabel_UI;
import com.js.ens.leveller.customWidget.CustomButton;
import com.js.ens.leveller.customWidget.CustomCombo;
import com.js.ens.leveller.customWidget.CustomSpinner;
import com.js.ens.leveller.customWidget.CustomTabFolder;
import com.js.ens.leveller.customWidget.CustomTableViewer;
import com.js.ens.leveller.customWidget.CustomText;
import com.js.ens.leveller.handler.HandlerButton;
import com.js.ens.leveller.handler.HandlerCombo;
import com.js.ens.leveller.handler.HandlerSpinner;
import com.js.ens.leveller.handler.HandlerTabFolder;
import com.js.ens.leveller.handler.HandlerTableViewer;
import com.js.ens.leveller.handler.HandlerText;
import com.js.ens.leveller.img.ImagePath;

import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.TabItem;

public class View extends ViewPart {
	private Mediator med = Mediator.getInstance();
	private LevellerMain LMain = LevellerMain.getInstatnce();
	
	
	public static final String ID = "com.js.ens.leveller.view";
	
	// Plate Shape Parameter - Default
	private Text textModelName_2D;
	private Text textWidth_2D;
	private Text textLength_2D;
	private Text textThickness_2D;
	// Plate Shape Parameter - Plate Type 2
	/*
	private Text type2_textLeftEdgeWavePitch_2D;
	private Text type2_textRightEdgeWavePitch_2D;
	private Text type2_textLeftEdgeWaveHeight_2D;
	private Text type2_textRightEdgeWaveHeight_2D;
	private Text type2_textLeftEdgeWavePhase_2D;
	private Text type2_textRightEdgeWavePhase_2D;
	// */
	private Text type2_textWavePitch_2D;
	private Text type2_textWaveHeight_2D;
	private Text type2_textWavePhase_2D;
	// Plate Shape Parameter - Plate Type 3
	/*
	private Text type3_textWavePitch_2D;
	private Text type3_textWaveHeight_2D;
	//*/
	private Text type3_textFrontCurlHeight_2D;
	private Text type3_textFrontCurlLength_2D;
	private Text type3_textRearCurlHeight_2D;
	private Text type3_textRearCurlLength_2D;
	// Plate Shape Parameter - Plate Type 4 
	private Text type4_textGutterHeight_2D;
	// Plate Shape Parameter - Plate Type 5
	private Text type5_textGutterHeight_2D;
	private Text type5_textGutterLength_2D;
	// Plate Shape Parameter - Plate Type 6
	private Text type6_textHeadGutterHeight_2D;
	private Text type6_textHeadGutterLength_2D;
	private Text type6_textTailGutterHeight_2D;
	private Text type6_textTailGutterLength_2D;
	// Plate Shape Parameter - Plate Type 7
	private Text type7_textHeadGutterHeight_2D;
	private Text type7_textGutterLength_2D;
	private Text type7_textGutterLengthLength_2D;
	private Text type7_textGutterWidthLength_2D;
	//-------------------------------- 
	private Text textThicknessElementNum_2D;
	private Text textWidthAspectRatio_2D;
	private Text textLengthAspectRatio_2D;
	private Text textElementNumber_2D;
	//--------------------------------
	private Text textPlateVelocity_2D;
	private Text textTemperatureStart_2D;
	private Text textTemperatureEnd_2D;
	private Text textPassLineOffset_2D;
	//--------------------------------
	private Text textRollPitch_2D;
	private Text textEntryRollTableDistance_2D;
	private Text textExitRollTableDistance_2D;
	//private Text textRollLength_2D;
	private Text textEntryUpperRollGap_2D;
	private Text textEntryLowerRollGap_2D;
	private Text textExitUpperRollGap_2D;
	private Text textExitLowerRollGap_2D;
	private Text textRollFriction_2D;
	private Text textRollDiameter_2D;
	//private Text textRollCrown_2D;
	//private Text textUpperRollCrown_2D;
	//private Text textLowerRollCrown_2D;
	private Text textMillStiffness_2D;
	//private Table tableUpperRoll_2D;
	//private Table tableLowerRoll_2D;
	//--------------------------------
	private Text textYoungsModulus_2D;
	private Text textFlowStress_2D;
	private Text textYieldStrength_2D;
	private Text textTensileStrength_2D;
	private Text textElongation_2D;
	private Text textThermalExpansionCoefficient_2D;
	private Text textMassDensity_2D;
	private Text textPoissonsRatio_2D;
	//--------------------------------
	private Text textFriction_2D;
	//--------------------------------
	private Text textSolvingTime_2D;
	private Text textIncrementTime_2D;
	private Text textDeformedCoordinate_2D;
	//--------------------------------
	private Text textUpperEntryRollGapMovement_2D;
	private Text textUpperExitRollGapMovement_2D;
	private Text textUpperRollGapStayingTime_2D;
	private Text textUpperRollGapMovingTime_2D;
	private Text textLowerEntryRollGapMovement_2D;
	private Text textLowerExitRollGapMovement_2D;
	private Text textLowerRollGapStayingTime_2D;
	private Text textLowerRollGapMovingTime_2D;

	private Text textFrontHDRollDia_2D;
	private Text textFrontHDRollPitch_2D;
	private Text textFrontHDRollVericalPos_2D;
	private Text textRearHDRollDia_2D;
	private Text textRearHDRollPitch_2D;
	private Text textRearHDRollVerticalPos_2D;

	
	
	
	
	// Plate Shape Parameter - Default
	private Text textModelName;
	private Text textWidth;
	private Text textLength;
	private Text textThickness;
	// Plate Shape Parameter - Plate Type 2
	private Text type2_textLeftEdgeWavePitch;
	private Text type2_textRightEdgeWavePitch;
	private Text type2_textLeftEdgeWaveHeight;
	private Text type2_textRightEdgeWaveHeight;
	private Text type2_textLeftEdgeWavePhase;
	private Text type2_textRightEdgeWavePhase;
	// Plate Shape Parameter - Plate Type 3 
	private Text type3_textWavePitch;
	private Text type3_textWaveHeight;
	// Plate Shape Parameter - Plate Type 4 
	private Text type4_textGutterHeight;
	// Plate Shape Parameter - Plate Type 5
	private Text type5_textGutterHeight;
	private Text type5_textGutterLength;
	// Plate Shape Parameter - Plate Type 6
	private Text type6_textHeadGutterHeight;
	private Text type6_textHeadGutterLength;
	private Text type6_textTailGutterHeight;
	private Text type6_textTailGutterLength;
	// Plate Shape Parameter - Plate Type 7
	private Text type7_textHeadGutterHeight;
	private Text type7_textGutterLength;
	private Text type7_textGutterLengthLength;
	private Text type7_textGutterWidthLength;
	//-------------------------------- 
	private Text textThicknessElementNum;
	private Text textWidthAspectRatio;
	private Text textLengthAspectRatio;
	private Text textElementNumber;
	//--------------------------------
	private Text textPlateVelocity;
	private Text textTemperatureStart;
	private Text textTemperatureEnd;
	private Text textPassLineOffset;
	//--------------------------------
	private Text textRollPitch;
	private Text textRollLength;
	private Text textEntryRollTableDistance;
	private Text textExitRollTableDistance;
	private Text textEntryUpperRollGap;
	private Text textEntryLowerRollGap;
	private Text textExitUpperRollGap;
	private Text textExitLowerRollGap;
	private Text textRollFriction;
	private Text textRollDiameter;
	private Text textUpperRollCrown;
	private Text textLowerRollCrown;
	private Text textMillStiffness;
	private Table tableUpperRoll;
	private Table tableLowerRoll;
	//--------------------------------
	private Text textYoungsModulus;
	private Text textFlowStress;
	private Text textYieldStrength;
	private Text textTensileStrength;
	private Text textElongation;
	private Text textThermalExpansionCoefficient;
	private Text textMassDensity;
	private Text textPoissonsRatio;
	//--------------------------------
	private Text textFriction;
	//--------------------------------
	private Text textSolvingTime;
	private Text textIncrementTime;
	private Text textDeformedCoordinate;
	//--------------------------------
	private Text textUpperEntryRollGapMovement;
	private Text textUpperExitRollGapMovement;
	private Text textUpperRollGapStayingTime;
	private Text textUpperRollGapMovingTime;
	private Text textLowerEntryRollGapMovement;
	private Text textLowerExitRollGapMovement;
	private Text textLowerRollGapStayingTime;
	private Text textLowerRollGapMovingTime;
	
	private Text textFrontHDRollDia;
	private Text textFrontHDRollPitch;
	private Text textFrontHDRollVericalPos;
	private Text textRearHDRollDia;
	private Text textRearHDRollPitch;
	private Text textRearHDRollVerticalPos;
	
	
	
	//--------------------------------
	
	public View() {
	}
	
	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		
		final ScrolledComposite sc = new ScrolledComposite(composite, SWT.H_SCROLL|SWT.V_SCROLL | SWT.BORDER);
		sc.setLayout(new FillLayout());
		sc.setMinSize(1420, 900);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		
		/* 
		sc.addControlListener(new ControlAdapter(){
			public void controlResized(ControlEvent e) {
		        Rectangle r = sc.getClientArea();
		        sc.setMinSize(compositeParents.computeSize(r.width, SWT.DEFAULT));
		        //sc.setMinSize(r.width, r.height);
		    }
		});
		 //*/
		 
	
		
		Composite compositeParents = new Composite(sc, SWT.NONE);
		FormLayout fl_compositeParent = new FormLayout();
		compositeParents.setLayout(fl_compositeParent);
		
		sc.setContent(compositeParents);
		
		Label lblModelName = new Label(compositeParents, SWT.NONE);
		FormData fd_lblModelName = new FormData();
		fd_lblModelName.top = new FormAttachment(0, 20);
		fd_lblModelName.left = new FormAttachment(0, 10);
		lblModelName.setLayoutData(fd_lblModelName);
		lblModelName.setText(TextLabel_UI.lblModelName);
		
		textModelName = new Text(compositeParents, SWT.BORDER);
		med.setTextModelName(textModelName);
		FormData fd_textModelName = new FormData();
		fd_textModelName.top = new FormAttachment(lblModelName, -2,SWT.TOP);
		fd_textModelName.left = new FormAttachment(lblModelName, 30, SWT.RIGHT);
		fd_textModelName.right = new FormAttachment(lblModelName, 150, SWT.RIGHT);
		textModelName.setLayoutData(fd_textModelName);
		textModelName.setEnabled(false);
		
		Label lblWorkspacePath = new Label(compositeParents, SWT.NONE);
		med.setlblworkspacePath(lblWorkspacePath);
		FormData fd_lblWorkspacePath = new FormData();
		fd_lblWorkspacePath.top = new FormAttachment(lblModelName, 0, SWT.TOP);
		fd_lblWorkspacePath.left = new FormAttachment(textModelName, 5);
		fd_lblWorkspacePath.right = new FormAttachment(100,-10);
		lblWorkspacePath.setLayoutData(fd_lblWorkspacePath);
		lblWorkspacePath.setText("Workspace : ");
		
		final TabFolder tabFolder = new TabFolder(compositeParents, SWT.NONE);
		med.setTabFolder(tabFolder);
		CustomTabFolder customTabFolder = new CustomTabFolder(Mediator.TABFOLDER_tabFolder,med);
		med.setCustomTabFolder(customTabFolder);
		customTabFolder.setCustomWidget_tabFolder();
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.top = new FormAttachment(lblModelName, 10);
		fd_tabFolder.left = new FormAttachment(0, 0);
		fd_tabFolder.right = new FormAttachment(100, 0);
		fd_tabFolder.bottom = new FormAttachment(100,-15);
		tabFolder.setLayoutData(fd_tabFolder);
		
		TabItem tbtmd2D = new TabItem(tabFolder, SWT.NONE);
		tbtmd2D.setText("2D");
		
		Composite compositeParent2D = new Composite(tabFolder, SWT.NONE);
		compositeParent2D.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tbtmd2D.setControl(compositeParent2D);
		compositeParent2D.setLayout(new FormLayout());
		
		 
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		//
		
		Composite compositeShapeParameter_2D = new Composite(compositeParent2D, SWT.BORDER);
		med.setCompositeShapeParameter_2D(compositeShapeParameter_2D);
		FormData fd_compositeShapeParameter_2D = new FormData();
		fd_compositeShapeParameter_2D.top = new FormAttachment(0, 10);
		fd_compositeShapeParameter_2D.left = new FormAttachment(0,10);
		fd_compositeShapeParameter_2D.right = new FormAttachment(0,450);
		fd_compositeShapeParameter_2D.bottom = new FormAttachment(0, 360);
		compositeShapeParameter_2D.setLayoutData(fd_compositeShapeParameter_2D);
		compositeShapeParameter_2D.setLayout(new FormLayout());
		
		Label lblShapeParameter_2D = new Label(compositeShapeParameter_2D, SWT.NONE);
		lblShapeParameter_2D.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblShapeParameter_2D = new FormData();
		fd_lblShapeParameter_2D.top = new FormAttachment(0, 10);
		fd_lblShapeParameter_2D.left = new FormAttachment(0, 10);
		lblShapeParameter_2D.setLayoutData(fd_lblShapeParameter_2D);
		lblShapeParameter_2D.setText(TextLabel_UI.lblShapeParameter_2D);
		
		Label lblType_2D = new Label(compositeShapeParameter_2D, SWT.NONE);
		FormData fd_lblType_2D = new FormData();
		fd_lblType_2D.top = new FormAttachment(lblShapeParameter_2D, 12);
		fd_lblType_2D.left = new FormAttachment(lblShapeParameter_2D,10,SWT.LEFT);
		lblType_2D.setLayoutData(fd_lblType_2D);
		lblType_2D.setText(TextLabel_UI.lblType_2D);
		
		Combo comboType_2D = new Combo(compositeShapeParameter_2D, SWT.READ_ONLY);
		med.setComboType_2D(comboType_2D);
		CustomCombo customComboType_2D = new CustomCombo(Mediator.COMBO_Type_2D,med);
		med.setCustomComboType_2D(customComboType_2D);
		customComboType_2D.setCustomWidget_comboType_2D();
		comboType_2D.setItems(new String[] {ComboLabel.TYPE1_2D,
											ComboLabel.TYPE2_2D,
											ComboLabel.TYPE3_2D
											});
		FormData fd_comboType_2D = new FormData();
		fd_comboType_2D.top = new FormAttachment(lblType_2D, -2, SWT.TOP);
		fd_comboType_2D.left = new FormAttachment(lblType_2D, 50);
		fd_comboType_2D.right = new FormAttachment(lblType_2D, 200, SWT.RIGHT);
		comboType_2D.setLayoutData(fd_comboType_2D);

		
		Image img_2D = ImageDescriptor.createFromFile(View.class,ImagePath.Type0).createImage();
		// Image Size => 121 X 101 
		Label lblPhoto_2D = new Label(compositeShapeParameter_2D, SWT.NONE);
		FormData fd_lblPhoto_2D = new FormData();
		fd_lblPhoto_2D.top = new FormAttachment(lblType_2D,0,SWT.TOP);
		fd_lblPhoto_2D.left = new FormAttachment(comboType_2D, 10);
		fd_lblPhoto_2D.right = new FormAttachment(comboType_2D, 130,SWT.RIGHT);
		fd_lblPhoto_2D.bottom = new FormAttachment(lblType_2D, 85, SWT.BOTTOM);
		lblPhoto_2D.setLayoutData(fd_lblPhoto_2D);
		lblPhoto_2D.setImage(img_2D);
		lblPhoto_2D.pack();
		
		Label lblWidth_2D = new Label(compositeShapeParameter_2D, SWT.NONE);
		med.setLblPhoto_2D(lblPhoto_2D);
		FormData fd_lblWidth_2D = new FormData();
		fd_lblWidth_2D.top = new FormAttachment(lblType_2D, 12);
		fd_lblWidth_2D.left = new FormAttachment(lblType_2D, 0, SWT.LEFT);
		lblWidth_2D.setLayoutData(fd_lblWidth_2D);
		lblWidth_2D.setText(TextLabel_UI.lblWidth_2D);
		
		
		textWidth_2D = new Text(compositeShapeParameter_2D, SWT.BORDER);
		//med.setTextWidth_2D(textWidth_2D);
		FormData fd_textWidth_2D = new FormData();
		fd_textWidth_2D.top = new FormAttachment(lblWidth_2D, -2, SWT.TOP);
		fd_textWidth_2D.left = new FormAttachment(comboType_2D, 0, SWT.LEFT);
		fd_textWidth_2D.right = new FormAttachment(comboType_2D, 0, SWT.RIGHT);
		textWidth_2D.setLayoutData(fd_textWidth_2D);
		textWidth_2D.setEnabled(false);
		
		Label lblLength_2D = new Label(compositeShapeParameter_2D, SWT.NONE);
		FormData fd_lblLength_2D = new FormData();
		fd_lblLength_2D.top = new FormAttachment(lblWidth_2D, 12);
		fd_lblLength_2D.left = new FormAttachment(lblType_2D, 0, SWT.LEFT);
		lblLength_2D.setLayoutData(fd_lblLength_2D);
		lblLength_2D.setText(TextLabel_UI.lblLength_2D);
		
		textLength_2D = new Text(compositeShapeParameter_2D, SWT.BORDER);
		med.setTextLength_2D(textLength_2D);
		CustomText c_textLength_2D = new CustomText(Mediator.TEXT_textLength_2D,med);
		med.setC_textLength_2D(c_textLength_2D);
		c_textLength_2D.setCustomWidget_textLength_2D();
		FormData fd_textLength_2D = new FormData();
		fd_textLength_2D.top = new FormAttachment(lblLength_2D, -2, SWT.TOP);
		fd_textLength_2D.left = new FormAttachment(comboType_2D, 0, SWT.LEFT);
		fd_textLength_2D.right = new FormAttachment(comboType_2D, 0, SWT.RIGHT);
		textLength_2D.setLayoutData(fd_textLength_2D);
		
		Label lblThickness_2D = new Label(compositeShapeParameter_2D, SWT.NONE);
		FormData fd_lblThickness_2D = new FormData();
		fd_lblThickness_2D.top = new FormAttachment(lblLength_2D, 12);
		fd_lblThickness_2D.left = new FormAttachment(lblType_2D, 0, SWT.LEFT);
		lblThickness_2D.setLayoutData(fd_lblThickness_2D);
		lblThickness_2D.setText(TextLabel_UI.lblThickness_2D);
		
		textThickness_2D = new Text(compositeShapeParameter_2D, SWT.BORDER);
		med.setTextThickness_2D(textThickness_2D);
		CustomText c_textThickness_2D = new CustomText(Mediator.TEXT_textThickness_2D,med);
		med.setC_textThickness_2D(c_textThickness_2D);
		c_textThickness_2D.setCustomWidget_textThickness_2D();
		FormData fd_textThickness_2D = new FormData();
		fd_textThickness_2D.top = new FormAttachment(lblThickness_2D, -2, SWT.TOP);
		fd_textThickness_2D.left = new FormAttachment(comboType_2D, 0, SWT.LEFT);
		fd_textThickness_2D.right = new FormAttachment(comboType_2D, 0, SWT.RIGHT);
		textThickness_2D.setLayoutData(fd_textThickness_2D);
		
		Composite compositeShapeParameterChild_2D = new Composite(compositeShapeParameter_2D, SWT.NONE);
		compositeShapeParameterChild_2D.setLayout(null);
		FormData fd_compositeShapeParameterChild_2D = new FormData();
		fd_compositeShapeParameterChild_2D.top = new FormAttachment(lblThickness_2D,2);
		fd_compositeShapeParameterChild_2D.left = new FormAttachment(0, 10);
		fd_compositeShapeParameterChild_2D.right = new FormAttachment(100,-10);
		fd_compositeShapeParameterChild_2D.bottom = new FormAttachment(100,-10);
		compositeShapeParameterChild_2D.setLayoutData(fd_compositeShapeParameterChild_2D);
		
		Composite compositeShapeParameterChild_1_2D = new Composite(compositeShapeParameterChild_2D, SWT.NONE);
		med.setCompositeShapeParameterChild_1_2D(compositeShapeParameterChild_1_2D);
		    
		compositeShapeParameterChild_1_2D.setBounds(0, 0, 400, 130);
		
		// Type2
		Composite compositeShapeParameterChild_2_2D = new Composite(compositeShapeParameterChild_2D, SWT.NONE);
		med.setCompositeShapeParameterChild_2_2D(compositeShapeParameterChild_2_2D);
		compositeShapeParameterChild_2_2D.setBounds(0, 0, 400, 184);
		
		Label lblWavePitch_type2_2D = new Label(compositeShapeParameterChild_2_2D, SWT.NONE);
		lblWavePitch_type2_2D.setBounds(10, 10, 200, 20);
		lblWavePitch_type2_2D.setText(TextLabel_UI.lblWavePitch_type2_2D);
		
		type2_textWavePitch_2D = new Text(compositeShapeParameterChild_2_2D, SWT.BORDER);
		med.setType2_textWavePitch_2D(type2_textWavePitch_2D);
		type2_textWavePitch_2D.setBounds(250, 8, 120, 24);
		
		Label lblWaveHeight_type2_2D = new Label(compositeShapeParameterChild_2_2D, SWT.NONE);
		lblWaveHeight_type2_2D.setBounds(10, 37, 200, 20);
		lblWaveHeight_type2_2D.setText(TextLabel_UI.lblWaveHeight_type2_2D);
		
		type2_textWaveHeight_2D = new Text(compositeShapeParameterChild_2_2D, SWT.BORDER);
		med.setType2_textWaveHeight_2D(type2_textWaveHeight_2D);
		CustomText c_type2_textWaveHeight_2D = new CustomText(Mediator.TEXT_type2_textWaveHeight_2D,med);
		med.setC_type2_textWaveHeight_2D(c_type2_textWaveHeight_2D);
		c_type2_textWaveHeight_2D.setCustomWidget_type2_textWaveHeight_2D();
		type2_textWaveHeight_2D.setBounds(250, 35, 120, 24);
		
		Label lblWavePhase_type2_2D = new Label(compositeShapeParameterChild_2_2D, SWT.NONE);
		lblWavePhase_type2_2D.setBounds(10, 64, 200, 20);
		lblWavePhase_type2_2D.setText(TextLabel_UI.lblWavePhase_type2_2D);
		
		type2_textWavePhase_2D = new Text(compositeShapeParameterChild_2_2D, SWT.BORDER);
		med.setType2_textWavePhase_2D(type2_textWavePhase_2D);
		type2_textWavePhase_2D.setBounds(250, 62, 120, 24);
		
		/*
		Label lblRightEdgeWaveHeight_type2_2D = new Label(compositeShapeParameterChild_2_2D, SWT.NONE);
		lblRightEdgeWaveHeight_type2_2D.setBounds(10, 91, 200, 20);
		lblRightEdgeWaveHeight_type2_2D.setText(TextLabel_UI.lblRightEdgeWaveHeight_type2_2D);
		
		type2_textRightEdgeWaveHeight_2D = new Text(compositeShapeParameterChild_2_2D, SWT.BORDER);
		med.setType2_textRightEdgeWaveHeight_2D(type2_textRightEdgeWaveHeight_2D);
		type2_textRightEdgeWaveHeight_2D.setBounds(250, 89, 120, 24);
		
		// update -160315
		Label lblLeftEdgeWavePhase_type2_2D = new Label(compositeShapeParameterChild_2_2D, SWT.NONE);
		lblLeftEdgeWavePhase_type2_2D.setBounds(10, 118, 200, 20);
		lblLeftEdgeWavePhase_type2_2D.setText(TextLabel_UI.lblLeftEdgeWavePhase_type2_2D);
		
		type2_textLeftEdgeWavePhase_2D = new Text(compositeShapeParameterChild_2_2D, SWT.BORDER);
		med.setType2_textLeftEdgeWavePhase_2D(type2_textLeftEdgeWavePhase_2D);
		type2_textLeftEdgeWavePhase_2D.setBounds(250, 116, 120, 24);
		
		Label lblRightEdgeWavePhase_type2_2D = new Label(compositeShapeParameterChild_2_2D, SWT.NONE);
		lblRightEdgeWavePhase_type2_2D.setBounds(10, 145, 200, 20);
		lblRightEdgeWavePhase_type2_2D.setText(TextLabel_UI.lblRightEdgeWavePhase_type2_2D);
		
		type2_textRightEdgeWavePhase_2D = new Text(compositeShapeParameterChild_2_2D, SWT.BORDER);
		med.setType2_textRightEdgeWavePhase_2D(type2_textRightEdgeWavePhase_2D);
		type2_textRightEdgeWavePhase_2D.setBounds(250, 143, 120, 24);
		// */
		// Type3
		Composite compositeShapeParameterChild_3_2D = new Composite(compositeShapeParameterChild_2D, SWT.NONE);
		med.setCompositeShapeParameterChild_3_2D(compositeShapeParameterChild_3_2D);
		compositeShapeParameterChild_3_2D.setBounds(0, 0, 400, 184);
		
		Label lblFrontCurlHeight_type3_2D = new Label(compositeShapeParameterChild_3_2D, SWT.NONE);
		lblFrontCurlHeight_type3_2D.setBounds(10, 10, 170, 20);
		lblFrontCurlHeight_type3_2D.setText(TextLabel_UI.lblFrontCurlHeight_type3_2D);
		
		type3_textFrontCurlHeight_2D = new Text(compositeShapeParameterChild_3_2D, SWT.BORDER);
		med.setType3_textFrontCurlHeight_2D(type3_textFrontCurlHeight_2D);
		CustomText c_type3_textFrontCurlHeight_2D = new CustomText(Mediator.TEXT_type3_textFrontCurlHeight_2D,med);
		med.setC_type3_textFrontCurlHeight_2D(c_type3_textFrontCurlHeight_2D);
		c_type3_textFrontCurlHeight_2D.setCustomWidget_type3_textFrontCurlHeight_2D();
		type3_textFrontCurlHeight_2D.setBounds(210, 8, 120, 24);
		
		Label lblFrontCurlLength_type3_2D = new Label(compositeShapeParameterChild_3_2D, SWT.NONE);
		lblFrontCurlLength_type3_2D.setBounds(10, 37, 170, 20);
		lblFrontCurlLength_type3_2D.setText(TextLabel_UI.lblFrontCurlLength_type3_2D);
		
		type3_textFrontCurlLength_2D = new Text(compositeShapeParameterChild_3_2D, SWT.BORDER);
		med.setType3_textFrontCurlLength_2D(type3_textFrontCurlLength_2D);
		CustomText c_type3_textFrontCurlLength_2D = new CustomText(Mediator.TEXT_type3_textFrontCurlLength_2D,med);
		med.setC_type3_textFrontCurlLength_2D(c_type3_textFrontCurlLength_2D);
		c_type3_textFrontCurlLength_2D.setCustomWidget_type3_textFrontCurlLength_2D();
		med.setType3_textFrontCurlLength_2D(type3_textFrontCurlLength_2D);
		type3_textFrontCurlLength_2D.setBounds(210, 35, 120, 24);
		
		
		Label lblRearCurlHeigth_type3_2D = new Label(compositeShapeParameterChild_3_2D, SWT.NONE);
		lblRearCurlHeigth_type3_2D.setBounds(10, 64, 170, 20);
		lblRearCurlHeigth_type3_2D.setText(TextLabel_UI.lblRearCurlHeight_type3_2D);
		
		type3_textRearCurlHeight_2D = new Text(compositeShapeParameterChild_3_2D, SWT.BORDER);
		med.setType3_textRearCurlHeight_2D(type3_textRearCurlHeight_2D);
		CustomText c_type3_textRearCurlHeight_2D = new CustomText(Mediator.TEXT_type3_textRearCurlHeight_2D,med);
		med.setC_type3_textRearCurlHeight_2D(c_type3_textRearCurlHeight_2D);
		c_type3_textRearCurlHeight_2D.setCustomWidget_type3_textRearCurlHeight_2D();
		type3_textRearCurlHeight_2D.setBounds(210, 62, 120, 24);
		
		Label lblRearCurlLength_type3_2D = new Label(compositeShapeParameterChild_3_2D, SWT.NONE);
		lblRearCurlLength_type3_2D.setBounds(10, 91, 170, 20);
		lblRearCurlLength_type3_2D.setText(TextLabel_UI.lblRearCurlLength_type3_2D);
		
		type3_textRearCurlLength_2D = new Text(compositeShapeParameterChild_3_2D, SWT.BORDER);
		med.setType3_textRearCurlLength_2D(type3_textRearCurlLength_2D);
		CustomText c_type3_textRearCurlLength_2D = new CustomText(Mediator.TEXT_type3_textRearCurlLength_2D,med);
		med.setC_type3_textRearCurlLength_2D(c_type3_textRearCurlLength_2D);
		c_type3_textRearCurlLength_2D.setCustomWidget_type3_textRearCurlLength_2D();
		type3_textRearCurlLength_2D.setBounds(210, 89, 120, 24);
		
		/*
		// Type4
		Composite compositeShapeParameterChild_4_2D = new Composite(compositeShapeParameterChild_2D, SWT.NONE);
		med.setCompositeShapeParameterChild_4_2D(compositeShapeParameterChild_4_2D);
		compositeShapeParameterChild_4_2D.setBounds(0, 0, 400, 130);
		
		Label lblGutterHeight_type4_2D = new Label(compositeShapeParameterChild_4_2D, SWT.NONE);
		lblGutterHeight_type4_2D.setBounds(10, 10, 170, 20);
		lblGutterHeight_type4_2D.setText(TextLabel_UI.lblGutterHeight_type4_2D);

		type4_textGutterHeight_2D = new Text(compositeShapeParameterChild_4_2D, SWT.BORDER);
		med.setType4_textGutterHeight_2D(type4_textGutterHeight_2D);
		type4_textGutterHeight_2D.setBounds(210, 8, 120, 24);

		// Type5		
		Composite compositeShapeParameterChild_5_2D = new Composite(compositeShapeParameterChild_2D, SWT.NONE);
		med.setCompositeShapeParameterChild_5_2D(compositeShapeParameterChild_5_2D);
		compositeShapeParameterChild_5_2D.setBounds(0, 0, 400, 130);
		
		Label lblGutterHeight_type5_2D = new Label(compositeShapeParameterChild_5_2D, SWT.NONE);
		lblGutterHeight_type5_2D.setBounds(10, 10, 170, 20);
		lblGutterHeight_type5_2D.setText(TextLabel_UI.lblGutterHeight_type5_2D);
		
		type5_textGutterHeight_2D = new Text(compositeShapeParameterChild_5_2D, SWT.BORDER);
		med.setType5_textGutterHeight_2D(type5_textGutterHeight_2D);
		type5_textGutterHeight_2D.setBounds(210, 8, 120, 24);
		
		Label lblGutterLength_type5_2D = new Label(compositeShapeParameterChild_5_2D, SWT.NONE);
		lblGutterLength_type5_2D.setBounds(10, 37, 170, 20);
		lblGutterLength_type5_2D.setText(TextLabel_UI.lblGutterLength_type5_2D);
		
		type5_textGutterLength_2D = new Text(compositeShapeParameterChild_5_2D, SWT.BORDER);
		med.setType5_textGutterLength_2D(type5_textGutterLength_2D);
		type5_textGutterLength_2D.setBounds(210, 35, 120, 24);
	
		// Type6
		Composite compositeShapeParameterChild_6_2D = new Composite(compositeShapeParameterChild_2D, SWT.NONE);
		med.setCompositeShapeParameterChild_6_2D(compositeShapeParameterChild_6_2D);
		compositeShapeParameterChild_6_2D.setBounds(0, 0, 400, 130);
		
		Label lblHeadGutterHeight_type6_2D = new Label(compositeShapeParameterChild_6_2D, SWT.NONE);
		lblHeadGutterHeight_type6_2D.setBounds(10, 10, 200, 20);
		lblHeadGutterHeight_type6_2D.setText(TextLabel_UI.lblHeadGutterHeight_type6_2D);
		
		type6_textHeadGutterHeight_2D = new Text(compositeShapeParameterChild_6_2D, SWT.BORDER);
		med.setType6_textHeadGutterHeight_2D(type6_textHeadGutterHeight_2D);
		type6_textHeadGutterHeight_2D.setBounds(250, 8, 120, 24);
		
		Label lblHeadGutterLength_type6_2D = new Label(compositeShapeParameterChild_6_2D, SWT.NONE);
		lblHeadGutterLength_type6_2D.setBounds(10, 37, 200, 20);
		lblHeadGutterLength_type6_2D.setText(TextLabel_UI.lblHeadGutterLength_type6_2D);
		
		type6_textHeadGutterLength_2D = new Text(compositeShapeParameterChild_6_2D, SWT.BORDER);
		med.setType6_textHeadGutterLength_2D(type6_textHeadGutterLength_2D);
		type6_textHeadGutterLength_2D.setBounds(250, 35, 120, 24);
		
		Label lblTailGutterHeight_type6_2D = new Label(compositeShapeParameterChild_6_2D, SWT.NONE);
		lblTailGutterHeight_type6_2D.setBounds(10, 64, 200, 20);
		lblTailGutterHeight_type6_2D.setText(TextLabel_UI.lblTailGutterHeight_type6_2D);
		
		type6_textTailGutterHeight_2D = new Text(compositeShapeParameterChild_6_2D, SWT.BORDER);
		med.setType6_textTailGutterHeight_2D(type6_textTailGutterHeight_2D);
		type6_textTailGutterHeight_2D.setBounds(250, 62, 120, 24);
		
		Label lblTailGutterLength_type6_2D = new Label(compositeShapeParameterChild_6_2D, SWT.NONE);
		lblTailGutterLength_type6_2D.setBounds(10, 91, 200, 20);
		lblTailGutterLength_type6_2D.setText(TextLabel_UI.lblTailGutterLength_type6_2D);
		
		type6_textTailGutterLength_2D = new Text(compositeShapeParameterChild_6_2D, SWT.BORDER);
		med.setType6_textTailGutterLength_2D(type6_textTailGutterLength_2D);
		type6_textTailGutterLength_2D.setBounds(250, 89, 120, 24);
		
		// Type7
		Composite compositeShapeParameterChild_7_2D = new Composite(compositeShapeParameterChild_2D, SWT.NONE);
		med.setCompositeShapeParameterChild_7_2D(compositeShapeParameterChild_7_2D);
		compositeShapeParameterChild_7_2D.setBounds(0, 0, 400, 130);
		
		Label lblHeadGutterHeight_type7_2D = new Label(compositeShapeParameterChild_7_2D, SWT.NONE);
		lblHeadGutterHeight_type7_2D.setBounds(10, 10, 250, 20);
		lblHeadGutterHeight_type7_2D.setText(TextLabel_UI.lblHeadGutterHeight_type7_2D);
		
		type7_textHeadGutterHeight_2D = new Text(compositeShapeParameterChild_7_2D, SWT.BORDER);
		med.setType7_textHeadGutterHeight_2D(type7_textHeadGutterHeight_2D);
		type7_textHeadGutterHeight_2D.setBounds(260, 8, 120, 24);
		
		Label lblGutterLength_type7_2D = new Label(compositeShapeParameterChild_7_2D, SWT.NONE);
		lblGutterLength_type7_2D.setBounds(10, 37, 250, 20);
		lblGutterLength_type7_2D.setText(TextLabel_UI.lblGutterLength_type7_2D);
		
		type7_textGutterLength_2D = new Text(compositeShapeParameterChild_7_2D, SWT.BORDER);
		med.setType7_textGutterLength_2D(type7_textGutterLength_2D);
		type7_textGutterLength_2D.setBounds(260, 35, 120, 24);
		
		Label lblGutterLengthLength_type7_2D = new Label(compositeShapeParameterChild_7_2D, SWT.NONE);
		lblGutterLengthLength_type7_2D.setBounds(10, 64, 250, 20);
		lblGutterLengthLength_type7_2D.setText(TextLabel_UI.lblGutterLengthLength_type7_2D);
		
		type7_textGutterLengthLength_2D = new Text(compositeShapeParameterChild_7_2D, SWT.BORDER);
		med.setType7_textGutterLengthLength_2D(type7_textGutterLengthLength_2D);
		type7_textGutterLengthLength_2D.setBounds(260, 62, 120, 24);
		
		Label lblGutterWidthLength_type7_2D = new Label(compositeShapeParameterChild_7_2D, SWT.NONE);
		lblGutterWidthLength_type7_2D.setBounds(10, 91, 250, 20);
		lblGutterWidthLength_type7_2D.setText(TextLabel_UI.lblGutterWidthLength_type7_2D);
		
		type7_textGutterWidthLength_2D = new Text(compositeShapeParameterChild_7_2D, SWT.BORDER);
		med.setType7_textGutterWidthLength_2D(type7_textGutterWidthLength_2D);
		type7_textGutterWidthLength_2D.setBounds(260, 89, 120, 24);
		//*/		
		compositeShapeParameterChild_1_2D.setVisible(false);
		compositeShapeParameterChild_2_2D.setVisible(false);
		compositeShapeParameterChild_3_2D.setVisible(false);
		/*
		compositeShapeParameterChild_4_2D.setVisible(false);
		compositeShapeParameterChild_5_2D.setVisible(false);
		compositeShapeParameterChild_6_2D.setVisible(false);
		compositeShapeParameterChild_7_2D.setVisible(false);
		//*/
		
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		
		Composite compositeMeshParameter_2D = new Composite(compositeParent2D, SWT.BORDER);
		med.setCompositeMeshParameter_2D(compositeMeshParameter_2D);
		compositeMeshParameter_2D.setLayout(new FormLayout());
		FormData fd_compositeMeshParameter_2D = new FormData();
		fd_compositeMeshParameter_2D.top = new FormAttachment(compositeShapeParameter_2D, 30);
		fd_compositeMeshParameter_2D.left = new FormAttachment(compositeShapeParameter_2D, 0, SWT.LEFT);
		fd_compositeMeshParameter_2D.right = new FormAttachment(compositeShapeParameter_2D, 0, SWT.RIGHT);
		fd_compositeMeshParameter_2D.bottom = new FormAttachment(compositeShapeParameter_2D, 260, SWT.BOTTOM);
		compositeMeshParameter_2D.setLayoutData(fd_compositeMeshParameter_2D);
		
		Label lblMeshParameter_2D = new Label(compositeMeshParameter_2D, SWT.NONE);
		lblMeshParameter_2D.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblMeshParameter_2D = new FormData();
		fd_lblMeshParameter_2D.top = new FormAttachment(0, 10);
		fd_lblMeshParameter_2D.left = new FormAttachment(0, 10);
		lblMeshParameter_2D.setLayoutData(fd_lblMeshParameter_2D);
		lblMeshParameter_2D.setText(TextLabel_UI.lblMeshParameter_2D);
		
		Label lblThicknessElementNum_2D = new Label(compositeMeshParameter_2D, SWT.NONE);
		FormData fd_lblThicknessElementNum_2D = new FormData();
		fd_lblThicknessElementNum_2D.top = new FormAttachment(lblMeshParameter_2D, 12);
		fd_lblThicknessElementNum_2D.left = new FormAttachment(lblMeshParameter_2D, 10, SWT.LEFT);
		lblThicknessElementNum_2D.setLayoutData(fd_lblThicknessElementNum_2D);
		lblThicknessElementNum_2D.setText(TextLabel_UI.lblThicknessElementNum_2D);
		
		textThicknessElementNum_2D = new Text(compositeMeshParameter_2D, SWT.BORDER);
		med.setTextThicknessElementNum_2D(textThicknessElementNum_2D);
		FormData fd_textThicknessElementNum_2D = new FormData();
		fd_textThicknessElementNum_2D.top = new FormAttachment(lblThicknessElementNum_2D, -2, SWT.TOP);
		fd_textThicknessElementNum_2D.left = new FormAttachment(lblThicknessElementNum_2D,20);
		fd_textThicknessElementNum_2D.right = new FormAttachment(100, -10);
		textThicknessElementNum_2D.setLayoutData(fd_textThicknessElementNum_2D);
		/*
		Label lblWidthAspectRatio_2D = new Label(compositeMeshParameter_2D, SWT.NONE);
		FormData fd_lblWidthAspectRatio_2D = new FormData();
		fd_lblWidthAspectRatio_2D.top = new FormAttachment(lblThicknessElementNum_2D, 12);
		fd_lblWidthAspectRatio_2D.left = new FormAttachment(lblThicknessElementNum_2D, 0, SWT.LEFT);
		lblWidthAspectRatio_2D.setLayoutData(fd_lblWidthAspectRatio_2D);
		lblWidthAspectRatio_2D.setText(TextLabel_UI.lblWidthAspectRatio_2D);
		
		textWidthAspectRatio_2D = new Text(compositeMeshParameter_2D, SWT.BORDER);
		med.setTextWidthAspectRatio_2D(textWidthAspectRatio_2D);
		FormData fd_textWidthAspectRatio_2D = new FormData();
		fd_textWidthAspectRatio_2D.top = new FormAttachment(lblWidthAspectRatio_2D, -2, SWT.TOP);
		fd_textWidthAspectRatio_2D.left = new FormAttachment(textThicknessElementNum_2D, 0, SWT.LEFT);
		fd_textWidthAspectRatio_2D.right = new FormAttachment(textThicknessElementNum_2D, 0, SWT.RIGHT);
		textWidthAspectRatio_2D.setLayoutData(fd_textWidthAspectRatio_2D);
		*/
		Label lblLengthAspectRatio_2D = new Label(compositeMeshParameter_2D, SWT.NONE);
		FormData fd_lblLengthAspectRatio_2D = new FormData();
		fd_lblLengthAspectRatio_2D.top = new FormAttachment(lblThicknessElementNum_2D, 12);
		fd_lblLengthAspectRatio_2D.left = new FormAttachment(lblThicknessElementNum_2D, 0, SWT.LEFT);
		lblLengthAspectRatio_2D.setLayoutData(fd_lblLengthAspectRatio_2D);
		lblLengthAspectRatio_2D.setText(TextLabel_UI.lblLengthAspectRatio_2D);
		
		textLengthAspectRatio_2D = new Text(compositeMeshParameter_2D, SWT.BORDER);
		med.setTextLengthAspectRatio_2D(textLengthAspectRatio_2D);
		FormData fd_textLengthAspectRatio_2D = new FormData();
		fd_textLengthAspectRatio_2D.top = new FormAttachment(lblLengthAspectRatio_2D, -2, SWT.TOP);
		fd_textLengthAspectRatio_2D.left = new FormAttachment(textThicknessElementNum_2D, 0, SWT.LEFT);
		fd_textLengthAspectRatio_2D.right = new FormAttachment(textThicknessElementNum_2D, 0, SWT.RIGHT);
		textLengthAspectRatio_2D.setLayoutData(fd_textLengthAspectRatio_2D);
		
		Label lblElementNumber_2D = new Label(compositeMeshParameter_2D, SWT.NONE);
		FormData fd_lblElementNumber_2D = new FormData();
		fd_lblElementNumber_2D.top = new FormAttachment(lblLengthAspectRatio_2D, 12);
		fd_lblElementNumber_2D.left = new FormAttachment(lblThicknessElementNum_2D, 0, SWT.LEFT);
		lblElementNumber_2D.setLayoutData(fd_lblElementNumber_2D);
		lblElementNumber_2D.setText(TextLabel_UI.lblElementNumber_2D);
		
		
		textElementNumber_2D = new Text(compositeMeshParameter_2D, SWT.BORDER);
		med.setTextElementNumber_2D(textElementNumber_2D);
		FormData fd_textElementNumber_2D = new FormData();
		fd_textElementNumber_2D.top = new FormAttachment(lblElementNumber_2D, -2, SWT.TOP);
		fd_textElementNumber_2D.left = new FormAttachment(textThicknessElementNum_2D, 0, SWT.LEFT);
		fd_textElementNumber_2D.right = new FormAttachment(textThicknessElementNum_2D, 0, SWT.RIGHT);
		textElementNumber_2D.setLayoutData(fd_textElementNumber_2D);
		textElementNumber_2D.setEnabled(false);
		
		
		Button btnCalcElementNum_2D = new Button(compositeMeshParameter_2D, SWT.NONE);
		med.setBtnCalcElementNum_2D(btnCalcElementNum_2D);
		CustomButton customBtnCalcElementNum_2D = new CustomButton(Mediator.BUTTON_CalcElementNum_2D,med);
		med.setCustomBtnCalcElementNum_2D(customBtnCalcElementNum_2D);
		customBtnCalcElementNum_2D.setCustomWidget_btnCalcElementNum_2D();
		FormData fd_btnCalcElementNum_2D = new FormData();
		fd_btnCalcElementNum_2D.top = new FormAttachment(textElementNumber_2D, 10);
		fd_btnCalcElementNum_2D.right = new FormAttachment(textThicknessElementNum_2D, 0, SWT.RIGHT);
		btnCalcElementNum_2D.setLayoutData(fd_btnCalcElementNum_2D);
		btnCalcElementNum_2D.setText(TextLabel_UI.btnCalcElementNum_2D);
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		
		Composite compositePlateInformation_2D = new Composite(compositeParent2D, SWT.BORDER);
		med.setCompositePlateInformation_2D(compositePlateInformation_2D);
		compositePlateInformation_2D.setLayout(new FormLayout());
		FormData fd_compositePlateInformation_2D = new FormData();
		fd_compositePlateInformation_2D.top = new FormAttachment(compositeMeshParameter_2D,30);
		fd_compositePlateInformation_2D.left = new FormAttachment(compositeShapeParameter_2D, 0, SWT.LEFT);
		fd_compositePlateInformation_2D.right = new FormAttachment(compositeShapeParameter_2D, 0, SWT.RIGHT);
		fd_compositePlateInformation_2D.bottom = new FormAttachment(compositeMeshParameter_2D,250,SWT.BOTTOM);
		compositePlateInformation_2D.setLayoutData(fd_compositePlateInformation_2D);
		
		Label lblPlateInformation_2D = new Label(compositePlateInformation_2D, SWT.NONE);
		lblPlateInformation_2D.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblPlateInformation_2D = new FormData();
		fd_lblPlateInformation_2D.top = new FormAttachment(0, 10);
		fd_lblPlateInformation_2D.left = new FormAttachment(0, 10);
		lblPlateInformation_2D.setLayoutData(fd_lblPlateInformation_2D);
		lblPlateInformation_2D.setText(TextLabel_UI.lblPlateInformation_2D);
		
		Label lblPlateVelocity_2D = new Label(compositePlateInformation_2D, SWT.NONE);
		FormData fd_lblPlateVelocity_2D = new FormData();
		fd_lblPlateVelocity_2D.top = new FormAttachment(lblPlateInformation_2D, 12);
		fd_lblPlateVelocity_2D.left = new FormAttachment(lblPlateInformation_2D,10,SWT.LEFT);
		lblPlateVelocity_2D.setLayoutData(fd_lblPlateVelocity_2D);
		lblPlateVelocity_2D.setText(TextLabel_UI.lblPlateVelocity);
		
		textPlateVelocity_2D = new Text(compositePlateInformation_2D, SWT.BORDER);
		med.setTextPlateVelocity_2D(textPlateVelocity_2D);
		FormData fd_textPlateVelocity_2D = new FormData();
		fd_textPlateVelocity_2D.top = new FormAttachment(lblPlateVelocity_2D, -2, SWT.TOP);
		fd_textPlateVelocity_2D.left = new FormAttachment(lblPlateVelocity_2D, 120);
		fd_textPlateVelocity_2D.right = new FormAttachment(100, -10);
		textPlateVelocity_2D.setLayoutData(fd_textPlateVelocity_2D);
		
		Label lblTemperatureStart_2D = new Label(compositePlateInformation_2D, SWT.NONE);
		FormData fd_lblTemperatureStart_2D = new FormData();
		fd_lblTemperatureStart_2D.top = new FormAttachment(lblPlateVelocity_2D, 12);
		fd_lblTemperatureStart_2D.left = new FormAttachment(lblPlateVelocity_2D, 0, SWT.LEFT);
		lblTemperatureStart_2D.setLayoutData(fd_lblTemperatureStart_2D);
		lblTemperatureStart_2D.setText(TextLabel_UI.lblTemperatureStart_2D);
		
		textTemperatureStart_2D = new Text(compositePlateInformation_2D, SWT.BORDER);
		med.setTextTemperatureStart_2D(textTemperatureStart_2D);
		FormData fd_textTemperatureStart_2D = new FormData();
		fd_textTemperatureStart_2D.top = new FormAttachment(lblTemperatureStart_2D, -2,SWT.TOP);
		fd_textTemperatureStart_2D.left = new FormAttachment(textPlateVelocity_2D,0, SWT.LEFT);
		fd_textTemperatureStart_2D.right = new FormAttachment(textPlateVelocity_2D, 0, SWT.RIGHT);
		textTemperatureStart_2D.setLayoutData(fd_textTemperatureStart_2D);
		
		Label lblTemperatureEnd_2D = new Label(compositePlateInformation_2D, SWT.NONE);
		FormData fd_lblTemperatureEnd_2D = new FormData();
		fd_lblTemperatureEnd_2D.top = new FormAttachment(lblTemperatureStart_2D, 12);
		fd_lblTemperatureEnd_2D.left = new FormAttachment(lblPlateVelocity_2D, 0, SWT.LEFT);
		lblTemperatureEnd_2D.setLayoutData(fd_lblTemperatureEnd_2D);
		lblTemperatureEnd_2D.setText(TextLabel_UI.lblTemperatureEnd_2D);
		
		textTemperatureEnd_2D = new Text(compositePlateInformation_2D, SWT.BORDER);
		med.setTextTemperatureEnd_2D(textTemperatureEnd_2D);
		FormData fd_textTemperatureEnd_2D = new FormData();
		fd_textTemperatureEnd_2D.top = new FormAttachment(lblTemperatureEnd_2D, -2, SWT.TOP);
		fd_textTemperatureEnd_2D.left = new FormAttachment(textPlateVelocity_2D, 0, SWT.LEFT);
		fd_textTemperatureEnd_2D.right = new FormAttachment(textPlateVelocity_2D, 0, SWT.RIGHT);
		textTemperatureEnd_2D.setLayoutData(fd_textTemperatureEnd_2D);
		
		
		Label lblPassLineOffset_2D = new Label(compositePlateInformation_2D, SWT.NONE);
		FormData fd_lblPassLineOffset_2D = new FormData();
		fd_lblPassLineOffset_2D.top = new FormAttachment(lblTemperatureEnd_2D, 12);
		fd_lblPassLineOffset_2D.left = new FormAttachment(lblPlateVelocity_2D, 0, SWT.LEFT);
		lblPassLineOffset_2D.setLayoutData(fd_lblPassLineOffset_2D);
		lblPassLineOffset_2D.setText(TextLabel_UI.lblPassLineOffset_2D);
		
		textPassLineOffset_2D = new Text(compositePlateInformation_2D, SWT.BORDER);
		med.setTextPassLineOffset_2D(textPassLineOffset_2D);
		FormData fd_textPassLineOffset_2D = new FormData();
		fd_textPassLineOffset_2D.top = new FormAttachment(lblPassLineOffset_2D, -2, SWT.TOP);
		fd_textPassLineOffset_2D.left = new FormAttachment(textPlateVelocity_2D, 0, SWT.LEFT);
		fd_textPassLineOffset_2D.right = new FormAttachment(textPlateVelocity_2D, 0, SWT.RIGHT);
		textPassLineOffset_2D.setLayoutData(fd_textPassLineOffset_2D);
		
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		
		
		Composite compositeRollParameter_2D = new Composite(compositeParent2D, SWT.BORDER);
		med.setCompositeRollParameter_2D(compositeRollParameter_2D);
		compositeRollParameter_2D.setLayout(new FormLayout());
		FormData fd_compositeRollParameter_2D = new FormData();
		fd_compositeRollParameter_2D.top = new FormAttachment(compositeShapeParameter_2D, 0, SWT.TOP);
		fd_compositeRollParameter_2D.left = new FormAttachment(compositeShapeParameter_2D, 10);
		fd_compositeRollParameter_2D.right = new FormAttachment(compositeShapeParameter_2D,500,SWT.RIGHT);
		fd_compositeRollParameter_2D.bottom = new FormAttachment(compositePlateInformation_2D,0,SWT.BOTTOM);
		compositeRollParameter_2D.setLayoutData(fd_compositeRollParameter_2D);
		
		
		Label lblRollParameter_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		lblRollParameter_2D.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblRollParameter_2D = new FormData();
		fd_lblRollParameter_2D.top = new FormAttachment(0, 10);
		fd_lblRollParameter_2D.left = new FormAttachment(0, 10);
		lblRollParameter_2D.setLayoutData(fd_lblRollParameter_2D);
		lblRollParameter_2D.setText(TextLabel_UI.lblRollParameter);
		
		
		
		Label lblUpperRollNumber_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblUpperRollNumber_2D = new FormData();
		fd_lblUpperRollNumber_2D.top = new FormAttachment(lblRollParameter_2D, 10);
		fd_lblUpperRollNumber_2D.left = new FormAttachment(lblRollParameter_2D, 10, SWT.LEFT);
		lblUpperRollNumber_2D.setLayoutData(fd_lblUpperRollNumber_2D);
		lblUpperRollNumber_2D.setText(TextLabel_UI.lblUpperRollNumber_2D);
		
		Spinner spinnerUpperRollNum_2D = new Spinner(compositeRollParameter_2D, SWT.BORDER);
		med.setSpinnerUpperRollNum_2D(spinnerUpperRollNum_2D);
		CustomSpinner customSpinnerUpperRollNum_2D = new CustomSpinner(Mediator.SPINNER_UpperRollNum_2D,med);
		med.setCustomSpinnerUpperRollNum_2D(customSpinnerUpperRollNum_2D);
		customSpinnerUpperRollNum_2D.setCustomWidget_spinnerUpperRollNum_2D();
		FormData fd_spinnerUpperRollNum_2D = new FormData();
		fd_spinnerUpperRollNum_2D.top = new FormAttachment(lblUpperRollNumber_2D, -2, SWT.TOP);
		fd_spinnerUpperRollNum_2D.left = new FormAttachment(lblUpperRollNumber_2D, 50,SWT.RIGHT);
		fd_spinnerUpperRollNum_2D.right = new FormAttachment(lblUpperRollNumber_2D, 110, SWT.RIGHT);
		spinnerUpperRollNum_2D.setLayoutData(fd_spinnerUpperRollNum_2D);
			
		Label lblLowerRollNumber_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblLowerRollNumber_2D = new FormData();
		fd_lblLowerRollNumber_2D.top = new FormAttachment(lblUpperRollNumber_2D, 0, SWT.TOP);
		fd_lblLowerRollNumber_2D.left = new FormAttachment(spinnerUpperRollNum_2D, 20, SWT.RIGHT);
		lblLowerRollNumber_2D.setLayoutData(fd_lblLowerRollNumber_2D);
		lblLowerRollNumber_2D.setText(TextLabel_UI.lblLowerRollNumber_2D);
		
		Spinner spinnerLowerRollNum_2D = new Spinner(compositeRollParameter_2D, SWT.BORDER);
		med.setSpinnerLowerRollNum_2D(spinnerLowerRollNum_2D);
		CustomSpinner customSpinnerLowerRollNum_2D = new CustomSpinner(Mediator.SPINNER_LowerRollNum_2D,med);
		med.setCustomSpinnerLowerRollNum_2D(customSpinnerLowerRollNum_2D);
		customSpinnerLowerRollNum_2D.setCustomWidget_spinnerLowerRollNum_2D();
		FormData fd_spinnerLowerRollNum_2D = new FormData();
		fd_spinnerLowerRollNum_2D.top = new FormAttachment(lblLowerRollNumber_2D, -2, SWT.TOP);
		fd_spinnerLowerRollNum_2D.left = new FormAttachment(lblLowerRollNumber_2D, 50, SWT.RIGHT);
		fd_spinnerLowerRollNum_2D.right = new FormAttachment(lblLowerRollNumber_2D, 110, SWT.RIGHT);
		spinnerLowerRollNum_2D.setLayoutData(fd_spinnerLowerRollNum_2D);
		
		Label lblRollPitch_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblRollPitch_2D = new FormData();
		fd_lblRollPitch_2D.top = new FormAttachment(lblUpperRollNumber_2D, 10);
		fd_lblRollPitch_2D.left = new FormAttachment(lblUpperRollNumber_2D, 0, SWT.LEFT);
		lblRollPitch_2D.setLayoutData(fd_lblRollPitch_2D);
		lblRollPitch_2D.setText(TextLabel_UI.lblRollPitch_2D);
		
		textRollPitch_2D = new Text(compositeRollParameter_2D, SWT.BORDER);
		med.setTextRollPitch_2D(textRollPitch_2D);
		FormData fd_textRollPitch_2D = new FormData();
		fd_textRollPitch_2D.top = new FormAttachment(lblRollPitch_2D, -2, SWT.TOP);
		fd_textRollPitch_2D.left = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.LEFT);
		fd_textRollPitch_2D.right = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.RIGHT);
		textRollPitch_2D.setLayoutData(fd_textRollPitch_2D);
		/*
		Label lblRollLength_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblRollLength_2D = new FormData();
		fd_lblRollLength_2D.top = new FormAttachment(lblRollPitch_2D, 0, SWT.TOP);
		fd_lblRollLength_2D.left = new FormAttachment(lblLowerRollNumber_2D, 0, SWT.LEFT);
		lblRollLength_2D.setLayoutData(fd_lblRollLength_2D);
		lblRollLength_2D.setText(TextLabel_UI.lblRollLength_2D);
		
		textRollLength_2D = new Text(compositeRollParameter_2D, SWT.BORDER);
		med.setTextRollLength_2D(textRollLength_2D);
		FormData fd_textRollLength_2D = new FormData();
		fd_textRollLength_2D.top = new FormAttachment(lblRollLength_2D, -2, SWT.TOP);
		fd_textRollLength_2D.left = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.LEFT);
		fd_textRollLength_2D.right = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.RIGHT);
		textRollLength_2D.setLayoutData(fd_textRollLength_2D);
		//textRollLength_2D.setEnabled(false);
		// */
		
		
		
		Label lblEntryRollTableDistance_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblEntryRollTableDistance_2D = new FormData();
		fd_lblEntryRollTableDistance_2D.top = new FormAttachment(lblRollPitch_2D, 10);
		fd_lblEntryRollTableDistance_2D.left = new FormAttachment(lblUpperRollNumber_2D, 0, SWT.LEFT);
		lblEntryRollTableDistance_2D.setLayoutData(fd_lblEntryRollTableDistance_2D);
		lblEntryRollTableDistance_2D.setText(TextLabel_UI.lblEntryRollTableDistance_2D);
		
		textEntryRollTableDistance_2D = new Text(compositeRollParameter_2D, SWT.BORDER);
		med.setTextEntryRollTableDistance_2D(textEntryRollTableDistance_2D);
		FormData fd_textEntryRollTableDistance_2D = new FormData();
		fd_textEntryRollTableDistance_2D.top = new FormAttachment(lblEntryRollTableDistance_2D, -2, SWT.TOP);
		fd_textEntryRollTableDistance_2D.left = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.LEFT);
		fd_textEntryRollTableDistance_2D.right = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.RIGHT);
		textEntryRollTableDistance_2D.setLayoutData(fd_textEntryRollTableDistance_2D);
		
		Label lblExitRollTableDistance_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblExitRollTableDistance_2D = new FormData();
		fd_lblExitRollTableDistance_2D.top = new FormAttachment(lblEntryRollTableDistance_2D, 0, SWT.TOP);
		fd_lblExitRollTableDistance_2D.left = new FormAttachment(lblLowerRollNumber_2D, 0, SWT.LEFT);
		lblExitRollTableDistance_2D.setLayoutData(fd_lblExitRollTableDistance_2D);
		lblExitRollTableDistance_2D.setText(TextLabel_UI.lblExitRollTableDistance_2D);
		
		textExitRollTableDistance_2D = new Text(compositeRollParameter_2D, SWT.BORDER);
		med.setTextExitRollTableDistance_2D(textExitRollTableDistance_2D);
		FormData fd_textExitRollTableDistance_2D = new FormData();
		fd_textExitRollTableDistance_2D.top = new FormAttachment(lblExitRollTableDistance_2D, -2, SWT.TOP);
		fd_textExitRollTableDistance_2D.left = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.LEFT);
		fd_textExitRollTableDistance_2D.right = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.RIGHT);
		textExitRollTableDistance_2D.setLayoutData(fd_textExitRollTableDistance_2D);
		
		
		
		
		
		Label lblEntryUpperRollGap_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblEntryUpperRollGap_2D = new FormData();
		fd_lblEntryUpperRollGap_2D.top = new FormAttachment(lblEntryRollTableDistance_2D, 10);
		fd_lblEntryUpperRollGap_2D.left = new FormAttachment(lblUpperRollNumber_2D, 0, SWT.LEFT);
		lblEntryUpperRollGap_2D.setLayoutData(fd_lblEntryUpperRollGap_2D);
		lblEntryUpperRollGap_2D.setText(TextLabel_UI.lblEntryUpperRollGap_2D);
		
		textEntryUpperRollGap_2D = new Text(compositeRollParameter_2D, SWT.BORDER);
		med.setTextEntryUpperRollGap_2D(textEntryUpperRollGap_2D);
		FormData fd_textEntryUpperRollGap_2D = new FormData();
		fd_textEntryUpperRollGap_2D.top = new FormAttachment(lblEntryUpperRollGap_2D, -2, SWT.TOP);
		fd_textEntryUpperRollGap_2D.left = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.LEFT);
		fd_textEntryUpperRollGap_2D.right = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.RIGHT);
		textEntryUpperRollGap_2D.setLayoutData(fd_textEntryUpperRollGap_2D);
		
		Label lblExitUpperRollGap_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblExitUpperRollGap_2D = new FormData();
		fd_lblExitUpperRollGap_2D.top = new FormAttachment(lblEntryUpperRollGap_2D, 0, SWT.TOP);
		fd_lblExitUpperRollGap_2D.left = new FormAttachment(lblLowerRollNumber_2D, 0, SWT.LEFT);
		lblExitUpperRollGap_2D.setLayoutData(fd_lblExitUpperRollGap_2D);
		lblExitUpperRollGap_2D.setText(TextLabel_UI.lblExitUpperRollGap_2D);
		
		textExitUpperRollGap_2D = new Text(compositeRollParameter_2D, SWT.BORDER);
		med.setTextExitUpperRollGap_2D(textExitUpperRollGap_2D);
		FormData fd_textExitUpperRollGap_2D = new FormData();
		fd_textExitUpperRollGap_2D.top = new FormAttachment(lblExitUpperRollGap_2D, -2, SWT.TOP);
		fd_textExitUpperRollGap_2D.left = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.LEFT);
		fd_textExitUpperRollGap_2D.right = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.RIGHT);
		textExitUpperRollGap_2D.setLayoutData(fd_textExitUpperRollGap_2D);
		
		
		
		Label lblEntryLowerRollGap_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblEntryLowerRollGap_2D = new FormData();
		fd_lblEntryLowerRollGap_2D.top = new FormAttachment(lblEntryUpperRollGap_2D, 10);
		fd_lblEntryLowerRollGap_2D.left = new FormAttachment(lblUpperRollNumber_2D, 0, SWT.LEFT);
		lblEntryLowerRollGap_2D.setLayoutData(fd_lblEntryLowerRollGap_2D);
		lblEntryLowerRollGap_2D.setText(TextLabel_UI.lblEntryLowerRollGap_2D);
		
		textEntryLowerRollGap_2D = new Text(compositeRollParameter_2D, SWT.BORDER);
		med.setTextEntryLowerRollGap_2D(textEntryLowerRollGap_2D);
		FormData fd_textEntryLowerRollGap_2D = new FormData();
		fd_textEntryLowerRollGap_2D.top = new FormAttachment(lblEntryLowerRollGap_2D, -2, SWT.TOP);
		fd_textEntryLowerRollGap_2D.left = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.LEFT);
		fd_textEntryLowerRollGap_2D.right = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.RIGHT);
		textEntryLowerRollGap_2D.setLayoutData(fd_textEntryLowerRollGap_2D);
		
		Label lblExitLowerRollGap_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblExitLowerRollGap_2D = new FormData();
		fd_lblExitLowerRollGap_2D.top = new FormAttachment(lblEntryLowerRollGap_2D, 0, SWT.TOP);
		fd_lblExitLowerRollGap_2D.left = new FormAttachment(lblLowerRollNumber_2D, 0, SWT.LEFT);
		lblExitLowerRollGap_2D.setLayoutData(fd_lblExitLowerRollGap_2D);
		lblExitLowerRollGap_2D.setText(TextLabel_UI.lblExitLowerRollGap_2D);
		
		textExitLowerRollGap_2D = new Text(compositeRollParameter_2D, SWT.BORDER);
		med.setTextExitLowerRollGap_2D(textExitLowerRollGap_2D);
		FormData fd_textExitLowerRollGap_2D = new FormData();
		fd_textExitLowerRollGap_2D.top = new FormAttachment(lblExitLowerRollGap_2D, -2, SWT.TOP);
		fd_textExitLowerRollGap_2D.left = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.LEFT);
		fd_textExitLowerRollGap_2D.right = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.RIGHT);
		textExitLowerRollGap_2D.setLayoutData(fd_textExitLowerRollGap_2D);
		
		
		
		
		Label lblRollFriction_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblRollFriction_2D = new FormData();
		fd_lblRollFriction_2D.top = new FormAttachment(lblEntryLowerRollGap_2D, 10);
		fd_lblRollFriction_2D.left = new FormAttachment(lblUpperRollNumber_2D, 0, SWT.LEFT);
		lblRollFriction_2D.setLayoutData(fd_lblRollFriction_2D);
		lblRollFriction_2D.setText(TextLabel_UI.lblRollFriction_2D);
		
		textRollFriction_2D = new Text(compositeRollParameter_2D, SWT.BORDER);
		med.setTextRollFriction_2D(textRollFriction_2D);
		FormData fd_textRollFriction_2D = new FormData();
		fd_textRollFriction_2D.top = new FormAttachment(lblRollFriction_2D, -2, SWT.TOP);
		fd_textRollFriction_2D.left = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.LEFT);
		fd_textRollFriction_2D.right = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.RIGHT);
		textRollFriction_2D.setLayoutData(fd_textRollFriction_2D);
	
		Label lblRollDiameter_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblRollDiameter_2D = new FormData();
		fd_lblRollDiameter_2D.top = new FormAttachment(lblRollFriction_2D, 0, SWT.TOP);
		fd_lblRollDiameter_2D.left = new FormAttachment(lblLowerRollNumber_2D, 0, SWT.LEFT);
		lblRollDiameter_2D.setLayoutData(fd_lblRollDiameter_2D);
		lblRollDiameter_2D.setText(TextLabel_UI.lblRollDiameter_2D);
		
		textRollDiameter_2D = new Text(compositeRollParameter_2D, SWT.BORDER);
		med.setTextRollDiameter_2D(textRollDiameter_2D);
		FormData fd_textRollDiameter_2D = new FormData();
		fd_textRollDiameter_2D.top = new FormAttachment(lblRollDiameter_2D, -2, SWT.TOP);
		fd_textRollDiameter_2D.left = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.LEFT);
		fd_textRollDiameter_2D.right = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.RIGHT);
		textRollDiameter_2D.setLayoutData(fd_textRollDiameter_2D);
		
		/*
		Label lblUpperRollCrown_2D = new Label(compositeRollParameter_2D,SWT.NONE);
		FormData fd_lblUpperRollCrown_2D = new FormData();
		fd_lblUpperRollCrown_2D.top = new FormAttachment(lblRollFriction_2D,10);
		fd_lblUpperRollCrown_2D.left = new FormAttachment(lblRollFriction_2D, 0, SWT.LEFT);
		lblUpperRollCrown_2D.setLayoutData(fd_lblUpperRollCrown_2D);
		lblUpperRollCrown_2D.setText(TextLabel_UI.lblUpperRollCrown_2D);
		
		textUpperRollCrown_2D = new Text(compositeRollParameter_2D, SWT.BORDER);
		med.setTextUpperRollCrown_2D(textUpperRollCrown_2D);
		FormData fd_textUpperRollCrown_2D = new FormData();
		fd_textUpperRollCrown_2D.top = new FormAttachment(lblUpperRollCrown_2D, -2, SWT.TOP);
		fd_textUpperRollCrown_2D.left = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.LEFT);
		fd_textUpperRollCrown_2D.right = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.RIGHT);
		textUpperRollCrown_2D.setLayoutData(fd_textUpperRollCrown_2D);
		// */
		/*
		Composite groupRollCrown_2D = new Composite(compositeRollParameter_2D, SWT.NONE);
		groupRollCrown_2D.setLayout(new FormLayout());
		FormData fd_groupRollCrown_2D = new FormData();
		fd_groupRollCrown_2D.top = new FormAttachment(lblUpperRollCrown_2D, 0,SWT.TOP);
		fd_groupRollCrown_2D.left = new FormAttachment(lblUpperRollCrown_2D, 15,SWT.RIGHT);
		fd_groupRollCrown_2D.right = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.RIGHT);
		groupRollCrown_2D.setLayoutData(fd_groupRollCrown_2D);
		
		Button btnRadioNone_RC_2D = new Button(groupRollCrown_2D, SWT.RADIO);
		med.setBtnRadioNone_RC_2D(btnRadioNone_RC_2D);
		CustomButton customBtnRadioNone_RC_2D = new CustomButton(Mediator.BUTTON_RadioNone_RC_2D,med);
		med.setCustomBtnRadioNone_RC_2D(customBtnRadioNone_RC_2D);
		customBtnRadioNone_RC_2D.setCustomWidget_btnRadioNone_RC_2D();
		btnRadioNone_RC_2D.setAlignment(SWT.CENTER);
		FormData fd_btnRadioNone_RC_2D = new FormData();
		fd_btnRadioNone_RC_2D.top = new FormAttachment(0, 0);
		fd_btnRadioNone_RC_2D.left = new FormAttachment(0, 0);
		btnRadioNone_RC_2D.setLayoutData(fd_btnRadioNone_RC_2D);
		btnRadioNone_RC_2D.setText(TextLabel_UI.btnRadioNone_2D);
		btnRadioNone_RC_2D.setSelection(false);
		
		Button btnRadioApply_RC_2D = new Button(groupRollCrown_2D, SWT.RADIO);
		med.setBtnRadioApply_RC_2D(btnRadioApply_RC_2D);
		CustomButton customBtnRadioApply_RC_2D = new CustomButton(Mediator.BUTTON_RadioApply_RC_2D, med);
		med.setCustomBtnRadioApply_RC_2D(customBtnRadioApply_RC_2D);
		customBtnRadioApply_RC_2D.setCustomWidget_btnRadioApply_RC_2D();
		FormData fd_btnRadioApply_RC_2D = new FormData();
		fd_btnRadioApply_RC_2D.top = new FormAttachment(btnRadioNone_RC_2D, 0, SWT.TOP);
		fd_btnRadioApply_RC_2D.left = new FormAttachment(btnRadioNone_RC_2D, 20);
		btnRadioApply_RC_2D.setLayoutData(fd_btnRadioApply_RC_2D);
		btnRadioApply_RC_2D.setText(TextLabel_UI.btnRadioApply_2D);
		btnRadioApply_RC_2D.setSelection(true);
		// */
		/*
		Label lblLowerRollCrown_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblLowerRollCrown_2D = new FormData();
		fd_lblLowerRollCrown_2D.top = new FormAttachment(lblUpperRollCrown_2D, 0,SWT.TOP);
		fd_lblLowerRollCrown_2D.left = new FormAttachment(lblLowerRollNumber_2D, 0, SWT.LEFT);
		lblLowerRollCrown_2D.setLayoutData(fd_lblLowerRollCrown_2D);
		lblLowerRollCrown_2D.setText(TextLabel_UI.lblLowerRollCrown_2D);
		
		textLowerRollCrown_2D = new Text(compositeRollParameter_2D, SWT.BORDER);
		med.setTextLowerRollCrown_2D(textLowerRollCrown_2D);
		FormData fd_textLowerRollCrown_2D = new FormData();
		fd_textLowerRollCrown_2D.top = new FormAttachment(lblLowerRollCrown_2D, -2, SWT.TOP);
		fd_textLowerRollCrown_2D.left = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.LEFT);
		fd_textLowerRollCrown_2D.right = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.RIGHT);
		textLowerRollCrown_2D.setLayoutData(fd_textLowerRollCrown_2D);
		// */
		Label lblMillStiffnessType_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblMillStiffnessType_2D = new FormData();
		fd_lblMillStiffnessType_2D.top = new FormAttachment(lblRollFriction_2D, 10);
		fd_lblMillStiffnessType_2D.left = new FormAttachment(lblRollFriction_2D, 0, SWT.LEFT);
		lblMillStiffnessType_2D.setLayoutData(fd_lblMillStiffnessType_2D);
		lblMillStiffnessType_2D.setText(TextLabel_UI.lblMillStiffnessType_2D);
		
		Composite groupMillStiffness_2D = new Composite(compositeRollParameter_2D,SWT.NONE);
		groupMillStiffness_2D.setLayout(new FormLayout());
		FormData fd_groupMillStiffness_2D = new FormData();
		fd_groupMillStiffness_2D.top = new FormAttachment(lblMillStiffnessType_2D, 0,SWT.TOP);
		fd_groupMillStiffness_2D.left = new FormAttachment(lblMillStiffnessType_2D, 15,SWT.RIGHT);
		fd_groupMillStiffness_2D.right = new FormAttachment(spinnerUpperRollNum_2D, 0, SWT.RIGHT);
		groupMillStiffness_2D.setLayoutData(fd_groupMillStiffness_2D);
		
		Button btnRadioRigid_MS_2D = new Button(groupMillStiffness_2D, SWT.RADIO);
		med.setBtnRadioRigid_MS_2D(btnRadioRigid_MS_2D);
		CustomButton customBtnRadioRigid_MS_2D = new CustomButton(Mediator.BUTTON_RadioRigid_MS_2D,med);
		med.setCustomBtnRadioRigid_MS_2D(customBtnRadioRigid_MS_2D);
		customBtnRadioRigid_MS_2D.setCustomWidget_btnRadioRigid_MS_2D();
		btnRadioRigid_MS_2D.setAlignment(SWT.CENTER);
		FormData fd_btnRadioRigid_MS_2D = new FormData();
		fd_btnRadioRigid_MS_2D.top = new FormAttachment(0, 0);
		fd_btnRadioRigid_MS_2D.left = new FormAttachment(0, 0);
		btnRadioRigid_MS_2D.setLayoutData(fd_btnRadioRigid_MS_2D);
		btnRadioRigid_MS_2D.setText(TextLabel_UI.btnRadioRigid_2D);
		btnRadioRigid_MS_2D.setSelection(false);
		
		Button btnRadioSpring_MS_2D = new Button(groupMillStiffness_2D, SWT.RADIO);
		med.setBtnRadioSpring_MS_2D(btnRadioSpring_MS_2D);
		CustomButton customBtnRadioSpring_MS_2D = new CustomButton(Mediator.BUTTON_RadioSpring_MS_2D, med);
		med.setCustomBtnRadioSpring_MS_2D(customBtnRadioSpring_MS_2D);
		customBtnRadioSpring_MS_2D.setCustomWidget_btnRadioSpring_MS_2D();
		FormData fd_btnRadioSpring_MS_2D = new FormData();
		fd_btnRadioSpring_MS_2D.top = new FormAttachment(btnRadioRigid_MS_2D, 0, SWT.TOP);
		fd_btnRadioSpring_MS_2D.left = new FormAttachment(btnRadioRigid_MS_2D, 20);
		btnRadioSpring_MS_2D.setLayoutData(fd_btnRadioSpring_MS_2D);
		btnRadioSpring_MS_2D.setText(TextLabel_UI.btnRadioSpring_2D);
		btnRadioSpring_MS_2D.setSelection(true);
		
		Label lblMillStiffness_2D = new Label(compositeRollParameter_2D, SWT.NONE);
		FormData fd_lblMillStiffness_2D = new FormData();
		fd_lblMillStiffness_2D.top = new FormAttachment(lblMillStiffnessType_2D, 0, SWT.TOP);
		fd_lblMillStiffness_2D.left = new FormAttachment(lblLowerRollNumber_2D, 0, SWT.LEFT);
		lblMillStiffness_2D.setLayoutData(fd_lblMillStiffness_2D);
		lblMillStiffness_2D.setText(TextLabel_UI.lblMillStiffness_2D);
		
		textMillStiffness_2D = new Text(compositeRollParameter_2D, SWT.BORDER);
		med.setTextMillStiffness_2D(textMillStiffness_2D);
		FormData fd_textMillStiffness_2D = new FormData();
		fd_textMillStiffness_2D.top = new FormAttachment(lblMillStiffness_2D, -2, SWT.TOP);
		fd_textMillStiffness_2D.left = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.LEFT);
		fd_textMillStiffness_2D.right = new FormAttachment(spinnerLowerRollNum_2D, 0, SWT.RIGHT);
		textMillStiffness_2D.setLayoutData(fd_textMillStiffness_2D);

		
		
		
		
		Group grpRollGapMovementInformation_2D = new Group(compositeRollParameter_2D, SWT.NONE);
		grpRollGapMovementInformation_2D.setText(TextLabel_UI.grpRollGapMovementInformation_2D);
		grpRollGapMovementInformation_2D.setLayout(new FormLayout());
		grpRollGapMovementInformation_2D.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_grpRollGapMovementInformation_2D = new FormData();
		fd_grpRollGapMovementInformation_2D.top = new FormAttachment(lblMillStiffnessType_2D, 10);
		fd_grpRollGapMovementInformation_2D.left = new FormAttachment(0, 10);
		fd_grpRollGapMovementInformation_2D.right = new FormAttachment(100,-10);
		grpRollGapMovementInformation_2D.setLayoutData(fd_grpRollGapMovementInformation_2D);
		
		Label lblUpperRollGap_2D = new Label(grpRollGapMovementInformation_2D, SWT.NONE);
		lblUpperRollGap_2D.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		FormData fd_lblUpperRollGap_2D = new FormData();
		fd_lblUpperRollGap_2D.top = new FormAttachment(0, 10);
		fd_lblUpperRollGap_2D.left = new FormAttachment(0, 5);
		lblUpperRollGap_2D.setLayoutData(fd_lblUpperRollGap_2D);
		lblUpperRollGap_2D.setText(TextLabel_UI.lblUpperRollGap_2D);
		
		Label lblUpperEntryRollGapMovement_2D = new Label(grpRollGapMovementInformation_2D, SWT.NONE);
		FormData fd_lblUpperEntryRollGapMovement_2D = new FormData();
		fd_lblUpperEntryRollGapMovement_2D.top = new FormAttachment(lblUpperRollGap_2D, 10);
		fd_lblUpperEntryRollGapMovement_2D.left = new FormAttachment(lblUpperRollGap_2D, 10, SWT.LEFT);
		lblUpperEntryRollGapMovement_2D.setLayoutData(fd_lblUpperEntryRollGapMovement_2D);
		lblUpperEntryRollGapMovement_2D.setText(TextLabel_UI.lblUpperEntryRollGapMovement_2D);
		
		textUpperEntryRollGapMovement_2D = new Text(grpRollGapMovementInformation_2D, SWT.BORDER);
		med.setTextUpperEntryRollGapMovement_2D(textUpperEntryRollGapMovement_2D);
		FormData fd_textUpperEntryRollGapMovement_2D = new FormData();
		fd_textUpperEntryRollGapMovement_2D.top = new FormAttachment(lblUpperEntryRollGapMovement_2D, -2, SWT.TOP);
		fd_textUpperEntryRollGapMovement_2D.left = new FormAttachment(lblUpperEntryRollGapMovement_2D, 50);
		fd_textUpperEntryRollGapMovement_2D.right = new FormAttachment(100,-10);
		textUpperEntryRollGapMovement_2D.setLayoutData(fd_textUpperEntryRollGapMovement_2D);
		
		Label lblUpperExitRollGapMovement_2D = new Label(grpRollGapMovementInformation_2D, SWT.NONE);
		FormData fd_lblUpperExitRollGapMovement_2D = new FormData();
		fd_lblUpperExitRollGapMovement_2D.top = new FormAttachment(lblUpperEntryRollGapMovement_2D, 10);
		fd_lblUpperExitRollGapMovement_2D.left = new FormAttachment(lblUpperEntryRollGapMovement_2D, 0, SWT.LEFT);
		lblUpperExitRollGapMovement_2D.setLayoutData(fd_lblUpperExitRollGapMovement_2D);
		lblUpperExitRollGapMovement_2D.setText(TextLabel_UI.lblUpperExitRollGapMovement_2D);
		
		textUpperExitRollGapMovement_2D = new Text(grpRollGapMovementInformation_2D, SWT.BORDER);
		med.setTextUpperExitRollGapMovement_2D(textUpperExitRollGapMovement_2D);
		FormData fd_textUpperExitRollGapMovement_2D = new FormData();
		fd_textUpperExitRollGapMovement_2D.top = new FormAttachment(lblUpperExitRollGapMovement_2D, -2, SWT.TOP);
		fd_textUpperExitRollGapMovement_2D.left = new FormAttachment(textUpperEntryRollGapMovement_2D, 0, SWT.LEFT);
		fd_textUpperExitRollGapMovement_2D.right = new FormAttachment(100,-10);
		textUpperExitRollGapMovement_2D.setLayoutData(fd_textUpperExitRollGapMovement_2D);
		
		Label lblUpperRollGapStayingTime_2D = new Label(grpRollGapMovementInformation_2D, SWT.NONE);
		FormData fd_lblUpperRollGapStayingTime_2D = new FormData();
		fd_lblUpperRollGapStayingTime_2D.top = new FormAttachment(lblUpperExitRollGapMovement_2D, 10);
		fd_lblUpperRollGapStayingTime_2D.left = new FormAttachment(lblUpperExitRollGapMovement_2D, 0, SWT.LEFT);
		lblUpperRollGapStayingTime_2D.setLayoutData(fd_lblUpperRollGapStayingTime_2D);
		lblUpperRollGapStayingTime_2D.setText(TextLabel_UI.lblUpperRollGapStayingTime_2D);
		
		textUpperRollGapStayingTime_2D = new Text(grpRollGapMovementInformation_2D, SWT.BORDER);
		med.setTextUpperRollGapStayingTime_2D(textUpperRollGapStayingTime_2D);
		FormData fd_textUpperRollGapStayingTime_2D = new FormData();
		fd_textUpperRollGapStayingTime_2D.top = new FormAttachment(lblUpperRollGapStayingTime_2D, -2, SWT.TOP);
		fd_textUpperRollGapStayingTime_2D.left = new FormAttachment(textUpperEntryRollGapMovement_2D, 0, SWT.LEFT);
		fd_textUpperRollGapStayingTime_2D.right = new FormAttachment(100,-10);
		textUpperRollGapStayingTime_2D.setLayoutData(fd_textUpperRollGapStayingTime_2D);
		
		Label lblUpperRollGapMovingTime_2D = new Label(grpRollGapMovementInformation_2D, SWT.NONE);
		FormData fd_lblUpperRollGapMovingTime_2D = new FormData();
		fd_lblUpperRollGapMovingTime_2D.top = new FormAttachment(lblUpperRollGapStayingTime_2D, 10);
		fd_lblUpperRollGapMovingTime_2D.left = new FormAttachment(lblUpperRollGapStayingTime_2D, 0, SWT.LEFT);
		lblUpperRollGapMovingTime_2D.setLayoutData(fd_lblUpperRollGapMovingTime_2D);
		lblUpperRollGapMovingTime_2D.setText(TextLabel_UI.lblUpperRollGapMovingTime_2D);
		
		textUpperRollGapMovingTime_2D = new Text(grpRollGapMovementInformation_2D, SWT.BORDER);
		med.setTextUpperRollGapMovingTime_2D(textUpperRollGapMovingTime_2D);
		FormData fd_textUpperRollGapMovingTime_2D = new FormData();
		fd_textUpperRollGapMovingTime_2D.top = new FormAttachment(lblUpperRollGapMovingTime_2D, -2, SWT.TOP);
		fd_textUpperRollGapMovingTime_2D.left = new FormAttachment(textUpperEntryRollGapMovement_2D, 0, SWT.LEFT);
		fd_textUpperRollGapMovingTime_2D.right = new FormAttachment(100,-10);
		textUpperRollGapMovingTime_2D.setLayoutData(fd_textUpperRollGapMovingTime_2D);
		

		Label lblLowerRollGap_2D = new Label(grpRollGapMovementInformation_2D, SWT.NONE);
		lblLowerRollGap_2D.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		FormData fd_lblLowerRollGap_2D = new FormData();
		fd_lblLowerRollGap_2D.top = new FormAttachment(lblUpperRollGapMovingTime_2D, 10);
		fd_lblLowerRollGap_2D.left = new FormAttachment(lblUpperRollGap_2D, 0,SWT.LEFT);
		lblLowerRollGap_2D.setLayoutData(fd_lblLowerRollGap_2D);
		lblLowerRollGap_2D.setText(TextLabel_UI.lblLowerRollGap_2D);

		Label lblLowerEntryRollGapMovement_2D = new Label(grpRollGapMovementInformation_2D, SWT.NONE);
		FormData fd_lblLowerEntryRollGapMovement_2D = new FormData();
		fd_lblLowerEntryRollGapMovement_2D.top = new FormAttachment(lblLowerRollGap_2D, 10);
		fd_lblLowerEntryRollGapMovement_2D.left = new FormAttachment(lblUpperRollGapStayingTime_2D, 0, SWT.LEFT);
		lblLowerEntryRollGapMovement_2D.setLayoutData(fd_lblLowerEntryRollGapMovement_2D);
		lblLowerEntryRollGapMovement_2D.setText(TextLabel_UI.lblLowerEntryRollGapMovement_2D);
		
		textLowerEntryRollGapMovement_2D = new Text(grpRollGapMovementInformation_2D, SWT.BORDER);
		med.setTextLowerEntryRollGapMovement_2D(textLowerEntryRollGapMovement_2D);
		FormData fd_textLowerEntryRollGapMovement_2D = new FormData();
		fd_textLowerEntryRollGapMovement_2D.top = new FormAttachment(lblLowerEntryRollGapMovement_2D, -2, SWT.TOP);
		fd_textLowerEntryRollGapMovement_2D.left = new FormAttachment(textUpperEntryRollGapMovement_2D, 0, SWT.LEFT);
		fd_textLowerEntryRollGapMovement_2D.right = new FormAttachment(100,-10);
		textLowerEntryRollGapMovement_2D.setLayoutData(fd_textLowerEntryRollGapMovement_2D);
		
		Label lblLowerExitRollGapMovement_2D = new Label(grpRollGapMovementInformation_2D, SWT.NONE);
		FormData fd_lblLowerExitRollGapMovement_2D = new FormData();
		fd_lblLowerExitRollGapMovement_2D.top = new FormAttachment(lblLowerEntryRollGapMovement_2D, 10);
		fd_lblLowerExitRollGapMovement_2D.left = new FormAttachment(lblLowerEntryRollGapMovement_2D, 0, SWT.LEFT);
		lblLowerExitRollGapMovement_2D.setLayoutData(fd_lblLowerExitRollGapMovement_2D);
		lblLowerExitRollGapMovement_2D.setText(TextLabel_UI.lblLowerExitRollGapMovement_2D);
		
		textLowerExitRollGapMovement_2D = new Text(grpRollGapMovementInformation_2D, SWT.BORDER);
		med.setTextLowerExitRollGapMovement_2D(textLowerExitRollGapMovement_2D);
		FormData fd_textLowerExitRollGapMovement_2D = new FormData();
		fd_textLowerExitRollGapMovement_2D.top = new FormAttachment(lblLowerExitRollGapMovement_2D, -2, SWT.TOP);
		fd_textLowerExitRollGapMovement_2D.left = new FormAttachment(textUpperEntryRollGapMovement_2D, 0, SWT.LEFT);
		fd_textLowerExitRollGapMovement_2D.right = new FormAttachment(100,-10);
		textLowerExitRollGapMovement_2D.setLayoutData(fd_textLowerExitRollGapMovement_2D);
		
		Label lblLowerRollGapStayingTime_2D = new Label(grpRollGapMovementInformation_2D, SWT.NONE);
		FormData fd_lblLowerRollGapStayingTime_2D = new FormData();
		fd_lblLowerRollGapStayingTime_2D.top = new FormAttachment(lblLowerExitRollGapMovement_2D, 10);
		fd_lblLowerRollGapStayingTime_2D.left = new FormAttachment(lblLowerExitRollGapMovement_2D, 0, SWT.LEFT);
		lblLowerRollGapStayingTime_2D.setLayoutData(fd_lblLowerRollGapStayingTime_2D);
		lblLowerRollGapStayingTime_2D.setText(TextLabel_UI.lblLowerRollGapStayingTime_2D);
		
		textLowerRollGapStayingTime_2D = new Text(grpRollGapMovementInformation_2D, SWT.BORDER);
		med.setTextLowerRollGapStayingTime_2D(textLowerRollGapStayingTime_2D);
		FormData fd_textLowerRollGapStayingTime_2D = new FormData();
		fd_textLowerRollGapStayingTime_2D.top = new FormAttachment(lblLowerRollGapStayingTime_2D, -2, SWT.TOP);
		fd_textLowerRollGapStayingTime_2D.left = new FormAttachment(textUpperEntryRollGapMovement_2D, 0, SWT.LEFT);
		fd_textLowerRollGapStayingTime_2D.right = new FormAttachment(100,-10);
		textLowerRollGapStayingTime_2D.setLayoutData(fd_textLowerRollGapStayingTime_2D);
		
		Label lblLowerRollGapMovingTime_2D = new Label(grpRollGapMovementInformation_2D, SWT.NONE);
		FormData fd_lblLowerRollGapMovingTime_2D = new FormData();
		fd_lblLowerRollGapMovingTime_2D.top = new FormAttachment(lblLowerRollGapStayingTime_2D, 10);
		fd_lblLowerRollGapMovingTime_2D.left = new FormAttachment(lblLowerRollGapStayingTime_2D, 0, SWT.LEFT);
		lblLowerRollGapMovingTime_2D.setLayoutData(fd_lblLowerRollGapMovingTime_2D);
		lblLowerRollGapMovingTime_2D.setText(TextLabel_UI.lblLowerRollGapMovingTime_2D);
		
		textLowerRollGapMovingTime_2D = new Text(grpRollGapMovementInformation_2D, SWT.BORDER);
		med.setTextLowerRollGapMovingTime_2D(textLowerRollGapMovingTime_2D);
		FormData fd_textLowerRollGapMovingTime_2D = new FormData();
		fd_textLowerRollGapMovingTime_2D.top = new FormAttachment(lblLowerRollGapMovingTime_2D, -2, SWT.TOP);
		fd_textLowerRollGapMovingTime_2D.left = new FormAttachment(textUpperEntryRollGapMovement_2D, 0, SWT.LEFT);
		fd_textLowerRollGapMovingTime_2D.right = new FormAttachment(100,-10);
		textLowerRollGapMovingTime_2D.setLayoutData(fd_textLowerRollGapMovingTime_2D);
		
		
		Group grpHoldDownRollInformation_2D = new Group(compositeRollParameter_2D, SWT.NONE);
		grpHoldDownRollInformation_2D.setText("Hold Down Roll Information");
		grpHoldDownRollInformation_2D.setLayout(new FormLayout());
		grpHoldDownRollInformation_2D.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_grpHoldDownRollInformation_2D = new FormData();
		fd_grpHoldDownRollInformation_2D.top = new FormAttachment(grpRollGapMovementInformation_2D, 10);
		fd_grpHoldDownRollInformation_2D.left = new FormAttachment(0, 10);
		fd_grpHoldDownRollInformation_2D.right = new FormAttachment(100,-10);
		grpHoldDownRollInformation_2D.setLayoutData(fd_grpHoldDownRollInformation_2D);
		
		Label lblHoldDownRollGeneration_2D = new Label(grpHoldDownRollInformation_2D, SWT.NONE);
		lblHoldDownRollGeneration_2D.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		FormData fd_lblHoldDownRollGeneration_2D = new FormData();
		fd_lblHoldDownRollGeneration_2D.top = new FormAttachment(0, 10);
		fd_lblHoldDownRollGeneration_2D.left = new FormAttachment(0, 5);
		lblHoldDownRollGeneration_2D.setLayoutData(fd_lblHoldDownRollGeneration_2D);
		lblHoldDownRollGeneration_2D.setText("Hold Down Roll Generation");
		
		Composite compositeHoldDownRollType_2D = new Composite(grpHoldDownRollInformation_2D, SWT.NONE);
		compositeHoldDownRollType_2D.setLayout(new FormLayout());
		FormData fd_compositeHoldDownRollType_2D = new FormData();
		fd_compositeHoldDownRollType_2D.top = new FormAttachment(lblHoldDownRollGeneration_2D, 5);
		fd_compositeHoldDownRollType_2D.left = new FormAttachment(lblHoldDownRollGeneration_2D, 0, SWT.LEFT);
		fd_compositeHoldDownRollType_2D.right = new FormAttachment(100,-10);
		compositeHoldDownRollType_2D.setLayoutData(fd_compositeHoldDownRollType_2D);
		
		Button btnNone_2D = new Button(compositeHoldDownRollType_2D, SWT.RADIO);
		med.setBtnNone_2D(btnNone_2D);
		CustomButton customBtnNone_2D = new CustomButton(Mediator.BUTTON_btnNone_2D,med);
		med.setCustomBtnNone_2D(customBtnNone_2D);
		customBtnNone_2D.setCustomWidget_btnNone_2D();
		FormData fd_btnNone_2D = new FormData();
		fd_btnNone_2D.top = new FormAttachment(0);
		fd_btnNone_2D.left = new FormAttachment(0,120);
		btnNone_2D.setLayoutData(fd_btnNone_2D);
		btnNone_2D.setText(TextLabel_UI.btnNone_2D);
		
		Button btnUpper_2D = new Button(compositeHoldDownRollType_2D, SWT.RADIO);
		med.setBtnUpper_2D(btnUpper_2D);
		CustomButton customBtnUpper_2D = new CustomButton(Mediator.BUTTON_btnUpper_2D,med);
		med.setCustomBtnUpper_2D(customBtnUpper_2D);
		customBtnUpper_2D.setCustomWidget_btnUpper_2D();
		FormData fd_btnUpper_2D = new FormData();
		fd_btnUpper_2D.top = new FormAttachment(0);
		fd_btnUpper_2D.left = new FormAttachment(btnNone_2D, 20,SWT.RIGHT);
		btnUpper_2D.setLayoutData(fd_btnUpper_2D);
		btnUpper_2D.setText(TextLabel_UI.btnUpper_2D);
		
		Button btnLower_2D = new Button(compositeHoldDownRollType_2D, SWT.RADIO);
		med.setBtnLower_2D(btnLower_2D);
		CustomButton customBtnLower_2D = new CustomButton(Mediator.BUTTON_btnLower_2D,med);
		med.setCustomBtnLower_2D(customBtnLower_2D);
		customBtnLower_2D.setCustomWidget_btnLower_2D();
		FormData fd_btnLower_2D = new FormData();
		fd_btnLower_2D.top = new FormAttachment(0);
		fd_btnLower_2D.left = new FormAttachment(btnUpper_2D, 20,SWT.RIGHT);
		btnLower_2D.setLayoutData(fd_btnLower_2D);
		btnLower_2D.setText(TextLabel_UI.btnLower_2D);
		
		Label lblFrontRollPosition_2D = new Label(grpHoldDownRollInformation_2D, SWT.NONE);
		lblFrontRollPosition_2D.setText(TextLabel_UI.lblFrontRollPosition_2D);
		lblFrontRollPosition_2D.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		FormData fd_lblFrontRollPosition_2D = new FormData();
		fd_lblFrontRollPosition_2D.top = new FormAttachment(compositeHoldDownRollType_2D, 5);
		fd_lblFrontRollPosition_2D.left = new FormAttachment(lblHoldDownRollGeneration_2D, 0, SWT.LEFT);
		lblFrontRollPosition_2D.setLayoutData(fd_lblFrontRollPosition_2D);
		
		Label lblFrontHDRollDia_2D = new Label(grpHoldDownRollInformation_2D, SWT.NONE);
		lblFrontHDRollDia_2D.setText(TextLabel_UI.lblFrontHDRollDia_2D);
		FormData fd_lblFrontHDRollDia_2D = new FormData();
		fd_lblFrontHDRollDia_2D.top = new FormAttachment(lblFrontRollPosition_2D, 5);
		fd_lblFrontHDRollDia_2D.left = new FormAttachment(lblFrontRollPosition_2D, 10, SWT.LEFT);
		lblFrontHDRollDia_2D.setLayoutData(fd_lblFrontHDRollDia_2D);
		
		textFrontHDRollDia_2D = new Text(grpHoldDownRollInformation_2D, SWT.BORDER);
		med.setTextFrontHDRollDia_2D(textFrontHDRollDia_2D);
		FormData fd_textFrontHDRollDia_2D = new FormData();
		fd_textFrontHDRollDia_2D.top = new FormAttachment(lblFrontHDRollDia_2D, -2, SWT.TOP);
		fd_textFrontHDRollDia_2D.left = new FormAttachment(lblFrontHDRollDia_2D, 100);
		fd_textFrontHDRollDia_2D.right = new FormAttachment(100,-20);
		textFrontHDRollDia_2D.setLayoutData(fd_textFrontHDRollDia_2D);	
		
		Label lblFrontHDRollPitch_2D = new Label(grpHoldDownRollInformation_2D, SWT.NONE);
		lblFrontHDRollPitch_2D.setText(TextLabel_UI.lblFrontHDRollPitch_2D);
		FormData fd_lblFrontHDRollPitch_2D = new FormData();
		fd_lblFrontHDRollPitch_2D.top = new FormAttachment(lblFrontHDRollDia_2D, 10);
		fd_lblFrontHDRollPitch_2D.left = new FormAttachment(lblFrontHDRollDia_2D, 0, SWT.LEFT);
		lblFrontHDRollPitch_2D.setLayoutData(fd_lblFrontHDRollPitch_2D);
		
		textFrontHDRollPitch_2D = new Text(grpHoldDownRollInformation_2D, SWT.BORDER);
		med.setTextFrontHDRollPitch_2D(textFrontHDRollPitch_2D);
		FormData fd_textFrontHDRollPitch_2D = new FormData();
		fd_textFrontHDRollPitch_2D.top = new FormAttachment(lblFrontHDRollPitch_2D, -2, SWT.TOP);
		fd_textFrontHDRollPitch_2D.left = new FormAttachment(textFrontHDRollDia_2D, 0,SWT.LEFT);
		fd_textFrontHDRollPitch_2D.right = new FormAttachment(textFrontHDRollDia_2D, 0,SWT.RIGHT);
		textFrontHDRollPitch_2D.setLayoutData(fd_textFrontHDRollPitch_2D);
		
		Label lblFrontHDRollVerticalPos_2D = new Label(grpHoldDownRollInformation_2D, SWT.NONE);
		lblFrontHDRollVerticalPos_2D.setText(TextLabel_UI.lblFrontHDRollVericalPos_2D);
		FormData fd_lblFrontHDRollVerticalPos_2D = new FormData();
		fd_lblFrontHDRollVerticalPos_2D.top = new FormAttachment(lblFrontHDRollPitch_2D, 10);
		fd_lblFrontHDRollVerticalPos_2D.left = new FormAttachment(lblFrontHDRollPitch_2D, 0, SWT.LEFT);
		lblFrontHDRollVerticalPos_2D.setLayoutData(fd_lblFrontHDRollVerticalPos_2D);
		
		textFrontHDRollVericalPos_2D = new Text(grpHoldDownRollInformation_2D, SWT.BORDER);
		med.setTextFrontHDRollVericalPos_2D(textFrontHDRollVericalPos_2D);
		FormData fd_textFrontHDRollVericalPos_2D = new FormData();
		fd_textFrontHDRollVericalPos_2D.top = new FormAttachment(lblFrontHDRollVerticalPos_2D, -2, SWT.TOP);
		fd_textFrontHDRollVericalPos_2D.left = new FormAttachment(textFrontHDRollDia_2D, 0,SWT.LEFT);
		fd_textFrontHDRollVericalPos_2D.right = new FormAttachment(textFrontHDRollDia_2D, 0,SWT.RIGHT);
		textFrontHDRollVericalPos_2D.setLayoutData(fd_textFrontHDRollVericalPos_2D);
		
		Label lblRearRollPosition_2D = new Label(grpHoldDownRollInformation_2D, SWT.NONE);
		lblRearRollPosition_2D.setText(TextLabel_UI.lblRearRollPosition_2D);
		lblRearRollPosition_2D.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		FormData fd_lblRearRollPosition_2D = new FormData();
		fd_lblRearRollPosition_2D.top = new FormAttachment(lblFrontHDRollVerticalPos_2D, 5);
		fd_lblRearRollPosition_2D.left = new FormAttachment(lblFrontRollPosition_2D, 0, SWT.LEFT);
		lblRearRollPosition_2D.setLayoutData(fd_lblRearRollPosition_2D);
		
		Label lblRearHDRollDia_2D = new Label(grpHoldDownRollInformation_2D, SWT.NONE);
		lblRearHDRollDia_2D.setText(TextLabel_UI.lblRearHDRollDia_2D);
		FormData fd_lblRearHDRollDia_2D = new FormData();
		fd_lblRearHDRollDia_2D.top = new FormAttachment(lblRearRollPosition_2D, 5);
		fd_lblRearHDRollDia_2D.left = new FormAttachment(lblFrontHDRollVerticalPos_2D, 0, SWT.LEFT);
		lblRearHDRollDia_2D.setLayoutData(fd_lblRearHDRollDia_2D);
		
		textRearHDRollDia_2D = new Text(grpHoldDownRollInformation_2D, SWT.BORDER);
		med.setTextRearHDRollDia_2D(textRearHDRollDia_2D);
		FormData fd_textRearHDRollDia_2D = new FormData();
		fd_textRearHDRollDia_2D.top = new FormAttachment(lblRearHDRollDia_2D, -2,SWT.TOP);
		fd_textRearHDRollDia_2D.left = new FormAttachment(textFrontHDRollVericalPos_2D, 0, SWT.LEFT);
		fd_textRearHDRollDia_2D.right = new FormAttachment(textFrontHDRollVericalPos_2D, 0, SWT.RIGHT);
		textRearHDRollDia_2D.setLayoutData(fd_textRearHDRollDia_2D);
		
		Label lblRearHDRollPitch_2D = new Label(grpHoldDownRollInformation_2D, SWT.NONE);
		lblRearHDRollPitch_2D.setText(TextLabel_UI.lblRearHDRollPitch_2D);
		FormData fd_lblRearHDRollPitch_2D = new FormData();
		fd_lblRearHDRollPitch_2D.top = new FormAttachment(lblRearHDRollDia_2D, 10);
		fd_lblRearHDRollPitch_2D.left = new FormAttachment(lblRearHDRollDia_2D, 0, SWT.LEFT);
		lblRearHDRollPitch_2D.setLayoutData(fd_lblRearHDRollPitch_2D);
		
		textRearHDRollPitch_2D = new Text(grpHoldDownRollInformation_2D, SWT.BORDER);
		med.setTextRearHDRollPitch_2D(textRearHDRollPitch_2D);
		FormData fd_textRearHDRollPitch_2D = new FormData();
		fd_textRearHDRollPitch_2D.top = new FormAttachment(lblRearHDRollPitch_2D, -2, SWT.TOP);
		fd_textRearHDRollPitch_2D.left = new FormAttachment(textRearHDRollDia_2D, 0, SWT.LEFT);
		fd_textRearHDRollPitch_2D.right = new FormAttachment(textRearHDRollDia_2D, 0, SWT.RIGHT);
		textRearHDRollPitch_2D.setLayoutData(fd_textRearHDRollPitch_2D);
		
		Label lblRearHDRollVerticalPos_2D = new Label(grpHoldDownRollInformation_2D, SWT.NONE);
		lblRearHDRollVerticalPos_2D.setText(TextLabel_UI.lblRearHDRollVerticalPos_2D);
		FormData fd_lblRearHDRollVerticalPos_2D = new FormData();
		fd_lblRearHDRollVerticalPos_2D.top = new FormAttachment(lblRearHDRollPitch_2D, 10);
		fd_lblRearHDRollVerticalPos_2D.left = new FormAttachment(lblRearHDRollPitch_2D, 0, SWT.LEFT);
		lblRearHDRollVerticalPos_2D.setLayoutData(fd_lblRearHDRollVerticalPos_2D);
		
		textRearHDRollVerticalPos_2D = new Text(grpHoldDownRollInformation_2D, SWT.BORDER);
		med.setTextRearHDRollVerticalPos_2D(textRearHDRollVerticalPos_2D);
		FormData fd_textRearHDRollVerticalPos_2D = new FormData();
		fd_textRearHDRollVerticalPos_2D.top = new FormAttachment(lblRearHDRollVerticalPos_2D, -2, SWT.TOP);
		fd_textRearHDRollVerticalPos_2D.left = new FormAttachment(textRearHDRollPitch_2D, 0, SWT.LEFT);
		fd_textRearHDRollVerticalPos_2D.right = new FormAttachment(textRearHDRollPitch_2D, 0, SWT.RIGHT);
		textRearHDRollVerticalPos_2D.setLayoutData(fd_textRearHDRollVerticalPos_2D);
		
		Button btnCreateRoll_2D = new Button(compositeRollParameter_2D, SWT.NONE);
		med.setBtnCreateRoll_2D(btnCreateRoll_2D);
		CustomButton customBtnCreateRoll_2D = new CustomButton(Mediator.BUTTON_CreateRoll_2D,med);
		med.setCustomBtnCreateRoll_2D(customBtnCreateRoll_2D);
		customBtnCreateRoll_2D.setCustomWidget_btnCreateRoll_2D();
		FormData fd_btnCreateRoll_2D  = new FormData();
		fd_btnCreateRoll_2D.top = new FormAttachment(grpHoldDownRollInformation_2D, 5);
		fd_btnCreateRoll_2D.left = new FormAttachment(grpHoldDownRollInformation_2D, 0, SWT.LEFT);
		fd_btnCreateRoll_2D.right = new FormAttachment(grpHoldDownRollInformation_2D, 0, SWT.RIGHT);
		btnCreateRoll_2D.setLayoutData(fd_btnCreateRoll_2D);
		btnCreateRoll_2D.setText(TextLabel_UI.btnCreateRoll_2D);
		
		Button btnShowRollTable_2D = new Button(compositeRollParameter_2D, SWT.NONE);
		med.setBtnShowRollTable_2D(btnShowRollTable_2D);
		CustomButton customBtnShowRollTable_2D = new CustomButton(Mediator.BUTTON_btnShowRollTable_2D,med);
		med.setCustomBtnShowRollTable_2D(customBtnShowRollTable_2D);
		customBtnShowRollTable_2D.setCustomWidget_btnShowRollTable_2D();
		btnShowRollTable_2D.setText("Show Roll Table");
		FormData fd_btnShowRollTable_2D = new FormData();
		fd_btnShowRollTable_2D.top = new FormAttachment(btnCreateRoll_2D, 10);
		fd_btnShowRollTable_2D.left = new FormAttachment(btnCreateRoll_2D, 0, SWT.LEFT);
		fd_btnShowRollTable_2D.right = new FormAttachment(btnCreateRoll_2D, 0, SWT.RIGHT);
		btnShowRollTable_2D.setLayoutData(fd_btnShowRollTable_2D);
			
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		
		Composite compositeMaterialParameter_2D = new Composite(compositeParent2D, SWT.BORDER);
		med.setCompositeMaterialParameter_2D(compositeMaterialParameter_2D);
		compositeMaterialParameter_2D.setLayout(new FormLayout());
		FormData fd_compositeMaterialParameter_2D = new FormData();
		fd_compositeMaterialParameter_2D.top = new FormAttachment(compositeShapeParameter_2D, 0, SWT.TOP);
		fd_compositeMaterialParameter_2D.left = new FormAttachment(compositeRollParameter_2D, 10);
		fd_compositeMaterialParameter_2D.right = new FormAttachment(compositeRollParameter_2D, 450,SWT.RIGHT);
		fd_compositeMaterialParameter_2D.bottom = new FormAttachment(lblModelName, 540, SWT.BOTTOM);
		compositeMaterialParameter_2D.setLayoutData(fd_compositeMaterialParameter_2D);
		/* */
		Label lblMaterialParameter_2D = new Label(compositeMaterialParameter_2D, SWT.NONE);
		lblMaterialParameter_2D.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblMaterialParameter_2D = new FormData();
		fd_lblMaterialParameter_2D.top = new FormAttachment(0, 10);
		fd_lblMaterialParameter_2D.left = new FormAttachment(0, 10);
		lblMaterialParameter_2D.setLayoutData(fd_lblMaterialParameter_2D);
		lblMaterialParameter_2D.setText(TextLabel_UI.lblMaterialParameter_2D);
		
		Label lblYoungsModulus_2D = new Label(compositeMaterialParameter_2D, SWT.NONE);
		FormData fd_lblYoungsModulus_2D = new FormData();
		fd_lblYoungsModulus_2D.top = new FormAttachment(lblMaterialParameter_2D, 12);
		fd_lblYoungsModulus_2D.left = new FormAttachment(lblMaterialParameter_2D, 10, SWT.LEFT);
		lblYoungsModulus_2D.setLayoutData(fd_lblYoungsModulus_2D);
		lblYoungsModulus_2D.setText(TextLabel_UI.lblYoungsModulus_2D);
		
		
		Composite group1_2D = new Composite(compositeMaterialParameter_2D, SWT.NONE);
		group1_2D.setLayout(new FormLayout());
		FormData fd_group1_2D = new FormData();
		fd_group1_2D.top = new FormAttachment(lblYoungsModulus_2D, 2);
		fd_group1_2D.left = new FormAttachment(lblYoungsModulus_2D, 10,SWT.LEFT);
		group1_2D.setLayoutData(fd_group1_2D);
		
		Button btnRadioConstant_YM_2D = new Button(group1_2D, SWT.RADIO);
		med.setBtnRadioConstant_YM_2D(btnRadioConstant_YM_2D);
		CustomButton customBtnRadioConstant_YM_2D = new CustomButton(Mediator.BUTTON_RadioConstant_YM_2D,med);
		med.setCustomBtnRadioConstant_YM_2D(customBtnRadioConstant_YM_2D);
		customBtnRadioConstant_YM_2D.setCustomWidget_btnRadioConstant_YM_2D();
		FormData fd_btnRadioConstant_YM_2D= new FormData();
		fd_btnRadioConstant_YM_2D.top = new FormAttachment(0, 5);
		fd_btnRadioConstant_YM_2D.left = new FormAttachment(0, 5);
		btnRadioConstant_YM_2D.setLayoutData(fd_btnRadioConstant_YM_2D);
		btnRadioConstant_YM_2D.setText(TextLabel_UI.btnRadioConstant_2D);
		btnRadioConstant_YM_2D.setSelection(false);
		
		Button btnRadioTable_YM_2D = new Button(group1_2D, SWT.RADIO);
		med.setBtnRadioTable_YM_2D(btnRadioTable_YM_2D);
		CustomButton customBtnRadioTable_YM_2D = new CustomButton(Mediator.BUTTON_RadioTable_YM_2D, med);
		med.setCustomBtnRadioTable_YM_2D(customBtnRadioTable_YM_2D);
		customBtnRadioTable_YM_2D.setCustomWidget_btnRadioTable_YM_2D();
		FormData fd_btnRadioTable_YM_2D = new FormData();
		fd_btnRadioTable_YM_2D.top = new FormAttachment(btnRadioConstant_YM_2D, 0, SWT.TOP);
		fd_btnRadioTable_YM_2D.left = new FormAttachment(btnRadioConstant_YM_2D, 20);
		btnRadioTable_YM_2D.setLayoutData(fd_btnRadioTable_YM_2D);
		btnRadioTable_YM_2D.setText(TextLabel_UI.btnRadioTable_2D);
		btnRadioTable_YM_2D.setSelection(true);
		
		textYoungsModulus_2D = new Text(compositeMaterialParameter_2D, SWT.BORDER);
		med.setTextYoungsModulus_2D(textYoungsModulus_2D);
		FormData fd_textYoungsModulus_2D = new FormData();
		fd_textYoungsModulus_2D.top = new FormAttachment(group1_2D, 5);
		fd_textYoungsModulus_2D.left = new FormAttachment(lblYoungsModulus_2D,20,SWT.LEFT);
		fd_textYoungsModulus_2D.right = new FormAttachment(100, -45);
		textYoungsModulus_2D.setLayoutData(fd_textYoungsModulus_2D);

		Button btnExplorerYoungsModulus_2D = new Button(compositeMaterialParameter_2D, SWT.NONE);
		med.setBtnExplorerYoungsModulus_2D(btnExplorerYoungsModulus_2D);
		CustomButton customBtnExplorerYoungsModulus_2D = new CustomButton(Mediator.BUTTON_ExplorerYoungsModulus_2D,med);
		med.setCustomBtnExplorerYoungsModulus_2D(customBtnExplorerYoungsModulus_2D);
		customBtnExplorerYoungsModulus_2D.setCustomWidget_btnExplorerYoungsModulus_2D();
		FormData fd_btnExplorerYoungsModulus_2D = new FormData();
		fd_btnExplorerYoungsModulus_2D.top = new FormAttachment(textYoungsModulus_2D, -2, SWT.TOP);
		fd_btnExplorerYoungsModulus_2D.right = new FormAttachment(100, -10);
		btnExplorerYoungsModulus_2D.setLayoutData(fd_btnExplorerYoungsModulus_2D);
		btnExplorerYoungsModulus_2D.setText("...");
		
		Label lblFlowStress_2D = new Label(compositeMaterialParameter_2D, SWT.NONE);
		FormData fd_lblFlowStress_2D = new FormData();
		fd_lblFlowStress_2D.top = new FormAttachment(textYoungsModulus_2D, 12);
		fd_lblFlowStress_2D.left = new FormAttachment(lblYoungsModulus_2D, 0, SWT.LEFT);
		lblFlowStress_2D.setLayoutData(fd_lblFlowStress_2D);
		lblFlowStress_2D.setText(TextLabel_UI.lblFlowStress_2D);
		
		Composite group2_2D = new Composite(compositeMaterialParameter_2D, SWT.NONE);
		group2_2D.setLayout(new FormLayout());
		FormData fd_group2_2D = new FormData();
		fd_group2_2D.top = new FormAttachment(lblFlowStress_2D, 2);
		fd_group2_2D.left = new FormAttachment(lblFlowStress_2D, 10,SWT.LEFT);
		group2_2D.setLayoutData(fd_group2_2D);
		
		Button btnRadioConstant_FS_2D = new Button(group2_2D, SWT.RADIO);
		med.setBtnRadioConstant_FS_2D(btnRadioConstant_FS_2D);
		CustomButton customBtnRadioConstant_FS_2D = new CustomButton(Mediator.BUTTON_RadioConstant_FS_2D,med);
		med.setCustomBtnRadioConstant_FS_2D(customBtnRadioConstant_FS_2D);
		customBtnRadioConstant_FS_2D.setCustomWidget_btnRadioConstant_FS_2D();
		FormData fd_btnRadioConstant_FS_2D = new FormData();
		fd_btnRadioConstant_FS_2D.top = new FormAttachment(0, 5);
		fd_btnRadioConstant_FS_2D.left = new FormAttachment(0, 5);
		btnRadioConstant_FS_2D.setLayoutData(fd_btnRadioConstant_FS_2D);
		btnRadioConstant_FS_2D.setText(TextLabel_UI.btnRadioConstant_2D);
		btnRadioConstant_FS_2D.setSelection(false);
		
		Button btnRadioTable_FS_2D = new Button(group2_2D, SWT.RADIO);
		med.setBtnRadioTable_FS_2D(btnRadioTable_FS_2D);
		CustomButton customBtnRadioTable_FS_2D = new CustomButton(Mediator.BUTTON_RadioTable_FS_2D, med);
		med.setCustomBtnRadioTable_FS_2D(customBtnRadioTable_FS_2D);
		customBtnRadioTable_FS_2D.setCustomWidget_btnRadioTable_FS_2D();
		FormData fd_btnRadioTable_FS_2D = new FormData();
		fd_btnRadioTable_FS_2D.top = new FormAttachment(btnRadioConstant_FS_2D, 0, SWT.TOP);
		fd_btnRadioTable_FS_2D.left = new FormAttachment(btnRadioConstant_FS_2D, 20);
		btnRadioTable_FS_2D.setLayoutData(fd_btnRadioTable_FS_2D);
		btnRadioTable_FS_2D.setText(TextLabel_UI.btnRadioTable_2D);
		btnRadioTable_FS_2D.setSelection(true);
		
		textFlowStress_2D = new Text(compositeMaterialParameter_2D, SWT.BORDER);
		med.setTextFlowStress_2D(textFlowStress_2D);
		FormData fd_textFlowStress_2D = new FormData();
		fd_textFlowStress_2D.top = new FormAttachment(group2_2D, 5);
		fd_textFlowStress_2D.left = new FormAttachment(lblFlowStress_2D,20,SWT.LEFT);
		fd_textFlowStress_2D.right = new FormAttachment(100, -45);
		textFlowStress_2D.setLayoutData(fd_textFlowStress_2D);
		
		Button btnExplorerFlowStress_2D = new Button(compositeMaterialParameter_2D, SWT.NONE);
		med.setBtnExplorerFlowStress_2D(btnExplorerFlowStress_2D);
		CustomButton customBtnExplorerFlowStress_2D = new CustomButton(Mediator.BUTTON_ExplorerFlowStress_2D,med);
		med.setCustomBtnExplorerFlowStress_2D(customBtnExplorerFlowStress_2D);
		customBtnExplorerFlowStress_2D.setCustomWidget_btnExplorerFlowStress_2D();
		FormData fd_btnExplorerFlowStress_2D = new FormData();
		fd_btnExplorerFlowStress_2D.top = new FormAttachment(textFlowStress_2D, -2, SWT.TOP);
		fd_btnExplorerFlowStress_2D.right = new FormAttachment(100, -10);
		btnExplorerFlowStress_2D.setLayoutData(fd_btnExplorerFlowStress_2D);
		btnExplorerFlowStress_2D.setText("...");
		
		//-------------------------------------------------------------------------------------
		
		Label lblYieldStrength_2D = new Label(compositeMaterialParameter_2D, SWT.NONE);
		FormData fd_lblYieldStrength_2D = new FormData();
		fd_lblYieldStrength_2D.top = new FormAttachment(textFlowStress_2D, 12);
		fd_lblYieldStrength_2D.left = new FormAttachment(lblYoungsModulus_2D,20, SWT.LEFT);
		lblYieldStrength_2D.setLayoutData(fd_lblYieldStrength_2D);
		lblYieldStrength_2D.setText(TextLabel_UI.lblYieldStrength_2D);
		
		textYieldStrength_2D = new Text(compositeMaterialParameter_2D, SWT.BORDER);
		med.setTextYieldStrength_2D(textYieldStrength_2D);
		FormData fd_textYieldStrength_2D = new FormData();
		fd_textYieldStrength_2D.top = new FormAttachment(lblYieldStrength_2D, -2,SWT.TOP);
		fd_textYieldStrength_2D.left = new FormAttachment(lblYieldStrength_2D,20,SWT.RIGHT);
		fd_textYieldStrength_2D.right = new FormAttachment(btnExplorerFlowStress_2D, 0,SWT.RIGHT);
		textYieldStrength_2D.setLayoutData(fd_textYieldStrength_2D);
		textYieldStrength_2D.setEnabled(false);
		
		Label lblTensileStrength_2D = new Label(compositeMaterialParameter_2D, SWT.NONE);
		FormData fd_lblTensileStrength_2D = new FormData();
		fd_lblTensileStrength_2D.top = new FormAttachment(lblYieldStrength_2D, 12);
		fd_lblTensileStrength_2D.left = new FormAttachment(lblYoungsModulus_2D, 20, SWT.LEFT);
		lblTensileStrength_2D.setLayoutData(fd_lblTensileStrength_2D);
		lblTensileStrength_2D.setText(TextLabel_UI.lblTensileStrength_2D);
		
		textTensileStrength_2D = new Text(compositeMaterialParameter_2D, SWT.BORDER);
		med.setTextTensileStrength_2D(textTensileStrength_2D);
		FormData fd_tensileStrength_2D = new FormData();
		fd_tensileStrength_2D.top = new FormAttachment(lblTensileStrength_2D,-2, SWT.TOP);
		fd_tensileStrength_2D.left = new FormAttachment(textYieldStrength_2D,0,SWT.LEFT);
		fd_tensileStrength_2D.right = new FormAttachment(textYieldStrength_2D, 0,SWT.RIGHT);
		textTensileStrength_2D.setLayoutData(fd_tensileStrength_2D);
		textTensileStrength_2D.setEnabled(false);
		
		Label lblElongation_2D = new Label(compositeMaterialParameter_2D, SWT.NONE);
		FormData fd_lblElongation_2D = new FormData();
		fd_lblElongation_2D.top = new FormAttachment(lblTensileStrength_2D, 12);
		fd_lblElongation_2D.left = new FormAttachment(lblYoungsModulus_2D, 20, SWT.LEFT);
		lblElongation_2D.setLayoutData(fd_lblElongation_2D);
		lblElongation_2D.setText(TextLabel_UI.lblElongation_2D);
		
		textElongation_2D = new Text(compositeMaterialParameter_2D, SWT.BORDER);
		med.setTextElongation_2D(textElongation_2D);
		FormData fd_textElongation_2D = new FormData();
		fd_textElongation_2D.top = new FormAttachment(lblElongation_2D,-2,SWT.TOP);
		fd_textElongation_2D.left = new FormAttachment(textYieldStrength_2D, 0, SWT.LEFT);
		fd_textElongation_2D.right = new FormAttachment(textYieldStrength_2D, 0, SWT.RIGHT);
		textElongation_2D.setLayoutData(fd_textElongation_2D);
		textElongation_2D.setEnabled(false);
		//-------------------------------------------------------------------------------------
		
		
		Label lblThermalExpansionCoefficient_2D = new Label(compositeMaterialParameter_2D, SWT.NONE);
		FormData fd_lblThermalExpansionCoefficient_2D = new FormData();
		fd_lblThermalExpansionCoefficient_2D.top = new FormAttachment(lblElongation_2D, 12);
		fd_lblThermalExpansionCoefficient_2D.left = new FormAttachment(lblYoungsModulus_2D, 0, SWT.LEFT);
		lblThermalExpansionCoefficient_2D.setLayoutData(fd_lblThermalExpansionCoefficient_2D);
		lblThermalExpansionCoefficient_2D.setText(TextLabel_UI.lblThermalExpansionCoefficient_2D);
		
		Composite group3_2D = new Composite(compositeMaterialParameter_2D, SWT.NONE);
		group3_2D.setLayout(new FormLayout());
		FormData fd_group3_2D = new FormData();
		fd_group3_2D.top = new FormAttachment(lblThermalExpansionCoefficient_2D, 2);
		fd_group3_2D.left = new FormAttachment(lblFlowStress_2D, 10,SWT.LEFT);
		group3_2D.setLayoutData(fd_group3_2D);
		
		Button btnRadioConstant_TEC_2D = new Button(group3_2D, SWT.RADIO);
		med.setBtnRadioConstant_TEC_2D(btnRadioConstant_TEC_2D);
		CustomButton customBtnRadioConstant_TEC_2D = new CustomButton(Mediator.BUTTON_RadioConstant_TEC_2D,med);
		med.setCustomBtnRadioConstant_TEC_2D(customBtnRadioConstant_TEC_2D);
		customBtnRadioConstant_TEC_2D.setCustomWidget_btnRadioConstant_TEC_2D();
		FormData fd_btnRadioConstant_TEC_2D = new FormData();
		fd_btnRadioConstant_TEC_2D.top = new FormAttachment(0, 5);
		fd_btnRadioConstant_TEC_2D.left = new FormAttachment(0, 5);
		btnRadioConstant_TEC_2D.setLayoutData(fd_btnRadioConstant_TEC_2D);
		btnRadioConstant_TEC_2D.setText(TextLabel_UI.btnRadioConstant_2D);
		btnRadioConstant_TEC_2D.setSelection(false);
		
		Button btnRadioTable_TEC_2D = new Button(group3_2D, SWT.RADIO);
		med.setBtnRadioTable_TEC_2D(btnRadioTable_TEC_2D);
		CustomButton customBtnRadioTable_TEC_2D = new CustomButton(Mediator.BUTTON_RadioTable_TEC_2D, med);
		med.setCustomBtnRadioTable_TEC_2D(customBtnRadioTable_TEC_2D);
		customBtnRadioTable_TEC_2D.setCustomWidget_btnRadioTable_TEC_2D();
		FormData fd_btnRadioTable_TEC_2D = new FormData();
		fd_btnRadioTable_TEC_2D.top = new FormAttachment(btnRadioConstant_TEC_2D, 0, SWT.TOP);
		fd_btnRadioTable_TEC_2D.left = new FormAttachment(btnRadioConstant_TEC_2D, 20);
		btnRadioTable_TEC_2D.setLayoutData(fd_btnRadioTable_TEC_2D);
		btnRadioTable_TEC_2D.setText(TextLabel_UI.btnRadioTable_2D);
		btnRadioTable_TEC_2D.setSelection(true);
		
		textThermalExpansionCoefficient_2D = new Text(compositeMaterialParameter_2D, SWT.BORDER);
		med.setTextThermalExpansionCoefficient_2D(textThermalExpansionCoefficient_2D);
		FormData fd_textThermalExpansionCoefficient_2D = new FormData();
		fd_textThermalExpansionCoefficient_2D.top = new FormAttachment(group3_2D, 5);
		fd_textThermalExpansionCoefficient_2D.left = new FormAttachment(lblThermalExpansionCoefficient_2D,20,SWT.LEFT);
		fd_textThermalExpansionCoefficient_2D.right = new FormAttachment(100, -45);
		textThermalExpansionCoefficient_2D.setLayoutData(fd_textThermalExpansionCoefficient_2D);
		
		Button btnExplorerThermalExpansionCoefficient_2D = new Button(compositeMaterialParameter_2D, SWT.NONE);
		med.setBtnExplorerThermalExpansionCoefficient_2D(btnExplorerThermalExpansionCoefficient_2D);
		CustomButton customBtnExplorerThermalExpansionCoefficient_2D = new CustomButton(Mediator.BUTTON_ExplorerThermalExpansionCoefficient_2D, med);
		med.setCustomBtnExplorerThermalExpansionCoefficient_2D(customBtnExplorerThermalExpansionCoefficient_2D);
		customBtnExplorerThermalExpansionCoefficient_2D.setCustomWidget_btnExplorerThermalExpansionCoefficient_2D();
		FormData fd_btnExplorerThermalExpansionCoefficient_2D = new FormData();
		fd_btnExplorerThermalExpansionCoefficient_2D.top = new FormAttachment(textThermalExpansionCoefficient_2D, -2, SWT.TOP);
		fd_btnExplorerThermalExpansionCoefficient_2D.right = new FormAttachment(100, -10);
		btnExplorerThermalExpansionCoefficient_2D.setLayoutData(fd_btnExplorerThermalExpansionCoefficient_2D);
		btnExplorerThermalExpansionCoefficient_2D.setText("...");
		
		Label lblPoissonsRatio_2D = new Label(compositeMaterialParameter_2D, SWT.NONE);
		FormData fd_lblPoissonsRatio_2D = new FormData();
		fd_lblPoissonsRatio_2D.top = new FormAttachment(textThermalExpansionCoefficient_2D, 12);
		fd_lblPoissonsRatio_2D.left = new FormAttachment(lblYoungsModulus_2D, 0, SWT.LEFT);
		lblPoissonsRatio_2D.setLayoutData(fd_lblPoissonsRatio_2D);
		lblPoissonsRatio_2D.setText(TextLabel_UI.lblPoissonsRatio_2D);
		
		Composite group4_2D = new Composite(compositeMaterialParameter_2D, SWT.NONE);
		group4_2D.setLayout(new FormLayout());
		FormData fd_group4_2D = new FormData();
		fd_group4_2D.top = new FormAttachment(lblPoissonsRatio_2D, 2);
		fd_group4_2D.left = new FormAttachment(lblFlowStress_2D, 10,SWT.LEFT);
		group4_2D.setLayoutData(fd_group4_2D);
		
		Button btnRadioConstant_PR_2D = new Button(group4_2D, SWT.RADIO);
		med.setBtnRadioConstant_PR_2D(btnRadioConstant_PR_2D);
		CustomButton customBtnRadioConstant_PR_2D = new CustomButton(Mediator.BUTTON_RadioConstant_PR_2D,med);
		med.setCustomBtnRadioConstant_PR_2D(customBtnRadioConstant_PR_2D);
		customBtnRadioConstant_PR_2D.setCustomWidget_btnRadioConstant_PR_2D();
		btnRadioConstant_PR_2D.setAlignment(SWT.CENTER);
		FormData fd_btnRadioConstant_PR_2D = new FormData();
		fd_btnRadioConstant_PR_2D.top = new FormAttachment(0, 5);
		fd_btnRadioConstant_PR_2D.left = new FormAttachment(0, 5);
		btnRadioConstant_PR_2D.setLayoutData(fd_btnRadioConstant_PR_2D);
		btnRadioConstant_PR_2D.setText(TextLabel_UI.btnRadioConstant_2D);
		btnRadioConstant_PR_2D.setSelection(false);
		
		Button btnRadioTable_PR_2D = new Button(group4_2D, SWT.RADIO);
		med.setBtnRadioTable_PR_2D(btnRadioTable_PR_2D);
		CustomButton customBtnRadioTable_PR_2D = new CustomButton(Mediator.BUTTON_RadioTable_PR_2D, med);
		med.setCustomBtnRadioTable_PR_2D(customBtnRadioTable_PR_2D);
		customBtnRadioTable_PR_2D.setCustomWidget_btnRadioTable_PR_2D();
		FormData fd_btnRadioTable_PR_2D = new FormData();
		fd_btnRadioTable_PR_2D.top = new FormAttachment(btnRadioConstant_PR_2D, 0, SWT.TOP);
		fd_btnRadioTable_PR_2D.left = new FormAttachment(btnRadioConstant_PR_2D, 20);
		btnRadioTable_PR_2D.setLayoutData(fd_btnRadioTable_PR_2D);
		btnRadioTable_PR_2D.setText(TextLabel_UI.btnRadioTable_2D);
		btnRadioTable_PR_2D.setSelection(true);
		
		textPoissonsRatio_2D = new Text(compositeMaterialParameter_2D, SWT.BORDER);
		med.setTextPoissonsRatio_2D(textPoissonsRatio_2D);
		FormData fd_textPoissonsRatio_2D = new FormData();
		fd_textPoissonsRatio_2D.top = new FormAttachment(group4_2D, 5);
		fd_textPoissonsRatio_2D.left = new FormAttachment(lblPoissonsRatio_2D,20,SWT.LEFT);
		fd_textPoissonsRatio_2D.right = new FormAttachment(100, -45);
		textPoissonsRatio_2D.setLayoutData(fd_textPoissonsRatio_2D);
		
		Button btnExplorerPoissonsRatio_2D = new Button(compositeMaterialParameter_2D, SWT.NONE);
		med.setBtnExplorerPoissonsRatio_2D(btnExplorerPoissonsRatio_2D);
		CustomButton customBtnExplorerPoissonsRatio_2D = new CustomButton(Mediator.BUTTON_ExplorerPoissonsRatio_2D,med);
		med.setCustomBtnExplorerPoissonsRatio_2D(customBtnExplorerPoissonsRatio_2D);
		customBtnExplorerPoissonsRatio_2D.setCustomWidget_btnExplorerPoissonsRatio_2D();
		FormData fd_btnExplorerPoissonsRatio_2D = new FormData();
		fd_btnExplorerPoissonsRatio_2D.top = new FormAttachment(textPoissonsRatio_2D, -2, SWT.TOP);
		fd_btnExplorerPoissonsRatio_2D.right = new FormAttachment(100, -10);
		btnExplorerPoissonsRatio_2D.setLayoutData(fd_btnExplorerPoissonsRatio_2D);
		btnExplorerPoissonsRatio_2D.setText("...");
		
		Label lblMassDensity_2D = new Label(compositeMaterialParameter_2D, SWT.NONE);
		FormData fd_lblMassDensity_2D = new FormData();
		fd_lblMassDensity_2D.top = new FormAttachment(textPoissonsRatio_2D, 12);
		fd_lblMassDensity_2D.left = new FormAttachment(lblYoungsModulus_2D, 0, SWT.LEFT);
		lblMassDensity_2D.setLayoutData(fd_lblMassDensity_2D);
		lblMassDensity_2D.setText(TextLabel_UI.lblMassDensity_2D);
		
		textMassDensity_2D = new Text(compositeMaterialParameter_2D, SWT.BORDER);
		med.setTextMassDensity_2D(textMassDensity_2D);
		FormData fd_textMassDensity_2D = new FormData();
		fd_textMassDensity_2D.top = new FormAttachment(lblMassDensity_2D, -2, SWT.TOP);
		fd_textMassDensity_2D.left = new FormAttachment(lblMassDensity_2D,100,SWT.RIGHT);
		fd_textMassDensity_2D.right = new FormAttachment(100, -10);
		textMassDensity_2D.setLayoutData(fd_textMassDensity_2D);
		// */
		
		// 
		//
		/////////////////////////////////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		/* */
		Composite compositeSolvingOption_2D = new Composite(compositeParent2D, SWT.BORDER);
		med.setCompositeSolvingOption_2D(compositeSolvingOption_2D);
		compositeSolvingOption_2D.setLayout(new FormLayout());
		FormData fd_compositeSolvingOption_2D = new FormData();
		fd_compositeSolvingOption_2D.top = new FormAttachment(compositeMaterialParameter_2D, 5);
		fd_compositeSolvingOption_2D.left = new FormAttachment(compositeMaterialParameter_2D, 0, SWT.LEFT);
		fd_compositeSolvingOption_2D.right = new FormAttachment(compositeMaterialParameter_2D, 0, SWT.RIGHT);
		fd_compositeSolvingOption_2D.bottom = new FormAttachment(compositeMaterialParameter_2D, 250,SWT.BOTTOM);
		compositeSolvingOption_2D.setLayoutData(fd_compositeSolvingOption_2D);
		
		Label lblSolvingOption_2D = new Label(compositeSolvingOption_2D, SWT.NONE);
		lblSolvingOption_2D.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblSolvingOption_2D = new FormData();
		fd_lblSolvingOption_2D.top = new FormAttachment(0, 10);
		fd_lblSolvingOption_2D.left = new FormAttachment(0, 10);
		lblSolvingOption_2D.setLayoutData(fd_lblSolvingOption_2D);
		lblSolvingOption_2D.setText(TextLabel_UI.lblSolvingOption_2D);
		
		Label lblSolvingTime_2D = new Label(compositeSolvingOption_2D, SWT.NONE);
		FormData fd_lblSolvingTime_2D = new FormData();
		fd_lblSolvingTime_2D.top = new FormAttachment(lblSolvingOption_2D, 12);
		fd_lblSolvingTime_2D.left = new FormAttachment(lblSolvingOption_2D, 10, SWT.LEFT);
		lblSolvingTime_2D.setLayoutData(fd_lblSolvingTime_2D);
		lblSolvingTime_2D.setText(TextLabel_UI.lblSolvingTime_2D);
		
		textSolvingTime_2D = new Text(compositeSolvingOption_2D, SWT.BORDER);
		med.setTextSolvingTime_2D(textSolvingTime_2D);
		FormData fd_textSolvingTime_2D = new FormData();
		fd_textSolvingTime_2D.top = new FormAttachment(lblSolvingTime_2D, -2, SWT.TOP);
		fd_textSolvingTime_2D.left = new FormAttachment(lblSolvingTime_2D,160,SWT.RIGHT);
		fd_textSolvingTime_2D.right = new FormAttachment(100, -10);
		textSolvingTime_2D.setLayoutData(fd_textSolvingTime_2D);
		
		Label lblIncrementTime_2D = new Label(compositeSolvingOption_2D, SWT.NONE);
		FormData fd_lblIncrementTime_2D = new FormData();
		fd_lblIncrementTime_2D.top = new FormAttachment(lblSolvingTime_2D, 12);
		fd_lblIncrementTime_2D.left = new FormAttachment(lblSolvingTime_2D, 0, SWT.LEFT);
		lblIncrementTime_2D.setLayoutData(fd_lblIncrementTime_2D);
		lblIncrementTime_2D.setText(TextLabel_UI.lblIncrementTime_2D);
		
		textIncrementTime_2D = new Text(compositeSolvingOption_2D, SWT.BORDER);
		med.setTextIncrementTime_2D(textIncrementTime_2D);
		FormData fd_textIncrementTime_2D = new FormData();
		fd_textIncrementTime_2D.top = new FormAttachment(lblIncrementTime_2D, -2, SWT.TOP);
		fd_textIncrementTime_2D.left = new FormAttachment(textSolvingTime_2D,0,SWT.LEFT);
		fd_textIncrementTime_2D.right = new FormAttachment(textSolvingTime_2D,0,SWT.RIGHT);
		textIncrementTime_2D.setLayoutData(fd_textIncrementTime_2D);
		
		Label lblParallelDDM_2D = new Label(compositeSolvingOption_2D, SWT.NONE);
		FormData fd_lblParallelDDM_2D = new FormData();
		fd_lblParallelDDM_2D.top = new FormAttachment(lblIncrementTime_2D, 12);
		fd_lblParallelDDM_2D.left = new FormAttachment(lblSolvingTime_2D, 0, SWT.LEFT);
		lblParallelDDM_2D.setLayoutData(fd_lblParallelDDM_2D);
		lblParallelDDM_2D.setText(TextLabel_UI.lblParallelDDM_2D);
		
		Button btnParallelDDMUse_2D = new Button(compositeSolvingOption_2D, SWT.CHECK);
		med.setBtnParallelDDMUse_2D(btnParallelDDMUse_2D);
		CustomButton customBtnParallelDDMUse_2D = new CustomButton(Mediator.BUTTON_ParallelDDMUse_2D,med);
		med.setCustomBtnParallelDDMUse_2D(customBtnParallelDDMUse_2D);
		customBtnParallelDDMUse_2D.setCustomWidget_btnParallelDDMUse_2D();
		FormData fd_btnParallelDDMUse_2D = new FormData();
		fd_btnParallelDDMUse_2D.top = new FormAttachment(lblParallelDDM_2D, -2, SWT.TOP);
		fd_btnParallelDDMUse_2D.left = new FormAttachment(textSolvingTime_2D, 0, SWT.LEFT);
		btnParallelDDMUse_2D.setLayoutData(fd_btnParallelDDMUse_2D);
		btnParallelDDMUse_2D.setText(TextLabel_UI.btnParallelDDMUse_2D);
		
		Label lblDomain_2D = new Label(compositeSolvingOption_2D, SWT.NONE);
		FormData fd_lblDomain_2D = new FormData();
		fd_lblDomain_2D.top = new FormAttachment(lblParallelDDM_2D, 12);
		fd_lblDomain_2D.left = new FormAttachment(lblSolvingTime_2D, 0, SWT.LEFT);
		lblDomain_2D.setLayoutData(fd_lblDomain_2D);
		lblDomain_2D.setText(TextLabel_UI.lblDomain_2D);
		
		Spinner spinnerDomain_2D = new Spinner(compositeSolvingOption_2D, SWT.BORDER);
		med.setSpinnerDomain_2D(spinnerDomain_2D);
		CustomSpinner customSpinnerDomain_2D = new CustomSpinner(Mediator.SPINNER_Domain_2D,med);
		med.setCustomSpinnerDomain_2D(customSpinnerDomain_2D);
		customSpinnerDomain_2D.setCustomWidget_spinnerDomain_2D();
		FormData fd_spinnerDomain_2D = new FormData();
		fd_spinnerDomain_2D.top = new FormAttachment(lblDomain_2D,-2,SWT.TOP);
		fd_spinnerDomain_2D.left = new FormAttachment(textSolvingTime_2D, 0, SWT.LEFT);
		fd_spinnerDomain_2D.right = new FormAttachment(textSolvingTime_2D, 0, SWT.RIGHT);
		spinnerDomain_2D.setLayoutData(fd_spinnerDomain_2D);
		spinnerDomain_2D.setEnabled(false);
		
		//-------------------------------------------
		Label lblParallelMultiThread_2D = new Label(compositeSolvingOption_2D, SWT.NONE);
		FormData fd_lblParallelMultiThread_2D = new FormData();
		fd_lblParallelMultiThread_2D.top = new FormAttachment(lblDomain_2D, 12);
		fd_lblParallelMultiThread_2D.left = new FormAttachment(lblSolvingTime_2D, 0, SWT.LEFT);
		lblParallelMultiThread_2D.setLayoutData(fd_lblParallelMultiThread_2D);
		lblParallelMultiThread_2D.setText(TextLabel_UI.lblParallelMultiThread_2D);
		
		Button btnParallelMultiThreadUse_2D = new Button(compositeSolvingOption_2D, SWT.CHECK);
		med.setBtnParallelMultiThreadUse_2D(btnParallelMultiThreadUse_2D);
		CustomButton customBtnParallelMultiThreadUse_2D = new CustomButton(Mediator.BUTTON_ParallelMultiThreadUse_2D,med);
		med.setCustomBtnParallelMultiThreadUse_2D(customBtnParallelMultiThreadUse_2D);
		customBtnParallelMultiThreadUse_2D.setCustomWidget_btnParallelMultiThreadUse_2D();
		FormData fd_btnParallelMultiThreadUse_2D = new FormData();
		fd_btnParallelMultiThreadUse_2D.top = new FormAttachment(lblParallelMultiThread_2D, -2, SWT.TOP);
		fd_btnParallelMultiThreadUse_2D.left = new FormAttachment(textSolvingTime_2D, 0, SWT.LEFT);
		btnParallelMultiThreadUse_2D.setLayoutData(fd_btnParallelMultiThreadUse_2D);
		btnParallelMultiThreadUse_2D.setText(TextLabel_UI.btnParallelMultiThreadUse_2D);
		
		Label lblThread_2D = new Label(compositeSolvingOption_2D, SWT.NONE);
		FormData fd_lblThread_2D = new FormData();
		fd_lblThread_2D.top = new FormAttachment(lblParallelMultiThread_2D, 12);
		fd_lblThread_2D.left = new FormAttachment(lblSolvingTime_2D, 0, SWT.LEFT);
		lblThread_2D.setLayoutData(fd_lblThread_2D);
		lblThread_2D.setText(TextLabel_UI.lblThread_2D);
		
		Spinner spinnerThread_2D = new Spinner(compositeSolvingOption_2D, SWT.BORDER);
		med.setSpinnerThread_2D(spinnerThread_2D);
		CustomSpinner customSpinnerThread_2D = new CustomSpinner(Mediator.SPINNER_Thread_2D,med);
		med.setCustomSpinnerThread_2D(customSpinnerThread_2D);
		customSpinnerThread_2D.setCustomWidget_spinnerDomain_2D();
		FormData fd_spinnerThread_2D = new FormData();
		fd_spinnerThread_2D.top = new FormAttachment(lblThread_2D,-2,SWT.TOP);
		fd_spinnerThread_2D.left = new FormAttachment(textSolvingTime_2D, 0, SWT.LEFT);
		fd_spinnerThread_2D.right = new FormAttachment(textSolvingTime_2D, 0, SWT.RIGHT);
		spinnerThread_2D.setLayoutData(fd_spinnerThread_2D);
		spinnerThread_2D.setEnabled(false);
		
		
		//--------------------------------------------
		
		Button btnApply_2D = new Button(compositeParent2D, SWT.NONE);
		med.setBtnApply_2D(btnApply_2D);
		CustomButton customBtnApply_2D = new CustomButton(Mediator.BUTTON_Apply_2D,med);
		med.setCustomBtnApply_2D(customBtnApply_2D);
		customBtnApply_2D.setCustomWidget_btnApply_2D();
		FormData fd_btnApply_2D = new FormData();
		fd_btnApply_2D.top = new FormAttachment(compositeSolvingOption_2D, 5);
		fd_btnApply_2D.left = new FormAttachment(compositeRollParameter_2D, 255);
		fd_btnApply_2D.right = new FormAttachment(compositeSolvingOption_2D, 0, SWT.RIGHT);
		fd_btnApply_2D.bottom = new FormAttachment(compositeRollParameter_2D, 0, SWT.BOTTOM);
		btnApply_2D.setLayoutData(fd_btnApply_2D);
		btnApply_2D.setText("APPLY");
			
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		TabItem tbtmd3D = new TabItem(tabFolder, SWT.NONE);
		tbtmd3D.setText("3D");
		
		Composite compositeParent3D = new Composite(tabFolder, SWT.NONE);
		compositeParent3D.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tbtmd3D.setControl(compositeParent3D);
		compositeParent3D.setLayout(new FormLayout());
		
		/* */
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		//
		
		Composite compositeShapeParameter = new Composite(compositeParent3D, SWT.BORDER);
		med.setCompositeShapeParameter(compositeShapeParameter);
		FormData fd_compositeShapeParameter = new FormData();
		fd_compositeShapeParameter.top = new FormAttachment(0, 10);
		fd_compositeShapeParameter.left = new FormAttachment(0,10);
		fd_compositeShapeParameter.right = new FormAttachment(0,450);
		fd_compositeShapeParameter.bottom = new FormAttachment(0, 360);
		compositeShapeParameter.setLayoutData(fd_compositeShapeParameter);
		compositeShapeParameter.setLayout(new FormLayout());
		
		Label lblShapeParameter = new Label(compositeShapeParameter, SWT.NONE);
		lblShapeParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblShapeParameter = new FormData();
		fd_lblShapeParameter.top = new FormAttachment(0, 10);
		fd_lblShapeParameter.left = new FormAttachment(0, 10);
		lblShapeParameter.setLayoutData(fd_lblShapeParameter);
		lblShapeParameter.setText(TextLabel_UI.lblShapeParameter);
		
		Label lblType = new Label(compositeShapeParameter, SWT.NONE);
		FormData fd_lblType = new FormData();
		fd_lblType.top = new FormAttachment(lblShapeParameter, 12);
		fd_lblType.left = new FormAttachment(lblShapeParameter,10,SWT.LEFT);
		lblType.setLayoutData(fd_lblType);
		lblType.setText(TextLabel_UI.lblType);
		
		Combo comboType = new Combo(compositeShapeParameter, SWT.READ_ONLY);
		med.setComboType(comboType);
		CustomCombo customComboType = new CustomCombo(Mediator.COMBO_Type,med);
		med.setCustomComboType(customComboType);
		customComboType.setCustomWidget_comboType();
		comboType.setItems(new String[] {	ComboLabel.TYPE1,
											ComboLabel.TYPE2,
											ComboLabel.TYPE3,
											ComboLabel.TYPE4,
											ComboLabel.TYPE5,
											ComboLabel.TYPE6,
											ComboLabel.TYPE7	});
		FormData fd_comboType = new FormData();
		fd_comboType.top = new FormAttachment(lblType, -2, SWT.TOP);
		fd_comboType.left = new FormAttachment(lblType, 50);
		fd_comboType.right = new FormAttachment(lblType, 200, SWT.RIGHT);
		comboType.setLayoutData(fd_comboType);

		
		Image img = ImageDescriptor.createFromFile(View.class,ImagePath.Type0).createImage();
		// Image Size => 121 X 101 
		Label lblPhoto = new Label(compositeShapeParameter, SWT.NONE);
		FormData fd_lblPhoto = new FormData();
		fd_lblPhoto.top = new FormAttachment(lblType,0,SWT.TOP);
		fd_lblPhoto.left = new FormAttachment(comboType, 10);
		fd_lblPhoto.right = new FormAttachment(comboType, 130,SWT.RIGHT);
		fd_lblPhoto.bottom = new FormAttachment(lblType, 85, SWT.BOTTOM);
		lblPhoto.setLayoutData(fd_lblPhoto);
		lblPhoto.setImage(img);
		lblPhoto.pack();
		
		Label lblWidth = new Label(compositeShapeParameter, SWT.NONE);
		med.setLblPhoto(lblPhoto);
		FormData fd_lblWidth = new FormData();
		fd_lblWidth.top = new FormAttachment(lblType, 12);
		fd_lblWidth.left = new FormAttachment(lblType, 0, SWT.LEFT);
		lblWidth.setLayoutData(fd_lblWidth);
		lblWidth.setText(TextLabel_UI.lblWidth);
		
		textWidth = new Text(compositeShapeParameter, SWT.BORDER);
		med.setTextWidth(textWidth);
		CustomText c_textWidth = new CustomText(Mediator.TEXT_textWidth,med);
		med.setC_textWidth(c_textWidth);
		c_textWidth.setCustomWidget_textWidth();
		FormData fd_textWidth = new FormData();
		fd_textWidth.top = new FormAttachment(lblWidth, -2, SWT.TOP);
		fd_textWidth.left = new FormAttachment(comboType, 0, SWT.LEFT);
		fd_textWidth.right = new FormAttachment(comboType, 0, SWT.RIGHT);
		textWidth.setLayoutData(fd_textWidth);
		
		Label lblLength = new Label(compositeShapeParameter, SWT.NONE);
		FormData fd_lblLength = new FormData();
		fd_lblLength.top = new FormAttachment(lblWidth, 12);
		fd_lblLength.left = new FormAttachment(lblType, 0, SWT.LEFT);
		lblLength.setLayoutData(fd_lblLength);
		lblLength.setText(TextLabel_UI.lblLength);
		
		textLength = new Text(compositeShapeParameter, SWT.BORDER);
		med.setTextLength(textLength);
		CustomText c_textLength = new CustomText(Mediator.Text_textLength,med);
		med.setC_textLength(c_textLength);
		c_textLength.setCustomWidget_textLength();
		FormData fd_textLength = new FormData();
		fd_textLength.top = new FormAttachment(lblLength, -2, SWT.TOP);
		fd_textLength.left = new FormAttachment(comboType, 0, SWT.LEFT);
		fd_textLength.right = new FormAttachment(comboType, 0, SWT.RIGHT);
		textLength.setLayoutData(fd_textLength);
		
		Label lblThickness = new Label(compositeShapeParameter, SWT.NONE);
		FormData fd_lblThickness = new FormData();
		fd_lblThickness.top = new FormAttachment(lblLength, 12);
		fd_lblThickness.left = new FormAttachment(lblType, 0, SWT.LEFT);
		lblThickness.setLayoutData(fd_lblThickness);
		lblThickness.setText(TextLabel_UI.lblThickness);
		
		textThickness = new Text(compositeShapeParameter, SWT.BORDER);
		med.setTextThickness(textThickness);
		CustomText c_textThickness = new CustomText(Mediator.TEXT_textThickness,med);
		med.setC_textThickness(c_textThickness);
		c_textThickness.setCustomWidget_textThickness();
		FormData fd_textThickness = new FormData();
		fd_textThickness.top = new FormAttachment(lblThickness, -2, SWT.TOP);
		fd_textThickness.left = new FormAttachment(comboType, 0, SWT.LEFT);
		fd_textThickness.right = new FormAttachment(comboType, 0, SWT.RIGHT);
		textThickness.setLayoutData(fd_textThickness);
		
		Composite compositeShapeParameterChild = new Composite(compositeShapeParameter, SWT.NONE);
		compositeShapeParameterChild.setLayout(null);
		FormData fd_compositeShapeParameterChild = new FormData();
		fd_compositeShapeParameterChild.top = new FormAttachment(lblThickness,2);
		fd_compositeShapeParameterChild.left = new FormAttachment(0, 10);
		fd_compositeShapeParameterChild.right = new FormAttachment(100,-10);
		fd_compositeShapeParameterChild.bottom = new FormAttachment(100,-10);
		compositeShapeParameterChild.setLayoutData(fd_compositeShapeParameterChild);
		
		Composite compositeShapeParameterChild_1 = new Composite(compositeShapeParameterChild, SWT.NONE);
		med.setCompositeShapeParameterChild_1(compositeShapeParameterChild_1);
		compositeShapeParameterChild_1.setBounds(0, 0, 400, 130);
		
		// Type2
		Composite compositeShapeParameterChild_2 = new Composite(compositeShapeParameterChild, SWT.NONE);
		med.setCompositeShapeParameterChild_2(compositeShapeParameterChild_2);
		compositeShapeParameterChild_2.setBounds(0, 0, 400, 184);
		
		Label lblLeftEdgeWavePitch_type2 = new Label(compositeShapeParameterChild_2, SWT.NONE);
		lblLeftEdgeWavePitch_type2.setBounds(10, 10, 200, 20);
		lblLeftEdgeWavePitch_type2.setText(TextLabel_UI.lblLeftEdgeWavePitch_type2);
		
		type2_textLeftEdgeWavePitch = new Text(compositeShapeParameterChild_2, SWT.BORDER);
		med.setType2_textLeftEdgeWavePitch(type2_textLeftEdgeWavePitch);
		type2_textLeftEdgeWavePitch.setBounds(250, 8, 120, 24);
		
		Label lblRightEdgeWavePitch_type2 = new Label(compositeShapeParameterChild_2, SWT.NONE);
		lblRightEdgeWavePitch_type2.setBounds(10, 37, 200, 20);
		lblRightEdgeWavePitch_type2.setText(TextLabel_UI.lblRightEdgeWavePitch_type2);
		
		type2_textRightEdgeWavePitch = new Text(compositeShapeParameterChild_2, SWT.BORDER);
		med.setType2_textRightEdgeWavePitch(type2_textRightEdgeWavePitch);
		type2_textRightEdgeWavePitch.setBounds(250, 35, 120, 24);
		
		Label lblLeftEdgeWaveHeight_type2 = new Label(compositeShapeParameterChild_2, SWT.NONE);
		lblLeftEdgeWaveHeight_type2.setBounds(10, 64, 200, 20);
		lblLeftEdgeWaveHeight_type2.setText(TextLabel_UI.lblLeftEdgeWaveHeight_type2);
		
		type2_textLeftEdgeWaveHeight = new Text(compositeShapeParameterChild_2, SWT.BORDER);
		med.setType2_textLeftEdgeWaveHeight(type2_textLeftEdgeWaveHeight);
		CustomText c_type2_textLeftEdgeWaveHeight = new CustomText(Mediator.TEXT_type2_textLeftEdgeWaveHeight,med);
		med.setC_type2_textLeftEdgeWaveHeight(c_type2_textLeftEdgeWaveHeight);
		c_type2_textLeftEdgeWaveHeight.setCustomWidget_type2_textLeftEdgeWaveHeight();
		type2_textLeftEdgeWaveHeight.setBounds(250, 62, 120, 24);
		
		Label lblRightEdgeWaveHeight_type2 = new Label(compositeShapeParameterChild_2, SWT.NONE);
		lblRightEdgeWaveHeight_type2.setBounds(10, 91, 200, 20);
		lblRightEdgeWaveHeight_type2.setText(TextLabel_UI.lblRightEdgeWaveHeight_type2);
		
		type2_textRightEdgeWaveHeight = new Text(compositeShapeParameterChild_2, SWT.BORDER);
		med.setType2_textRightEdgeWaveHeight(type2_textRightEdgeWaveHeight);
		CustomText c_type2_textRightEdgeWaveHeight = new CustomText(Mediator.TEXT_type2_textRightEdgeWaveHeight,med);
		med.setC_type2_textRightEdgeWaveHeight(c_type2_textRightEdgeWaveHeight);
		c_type2_textRightEdgeWaveHeight.setCustomWidget_type2_textRightEdgeWaveHeight();
		type2_textRightEdgeWaveHeight.setBounds(250, 89, 120, 24);
		
		// update -160315
		Label lblLeftEdgeWavePhase_type2 = new Label(compositeShapeParameterChild_2, SWT.NONE);
		lblLeftEdgeWavePhase_type2.setBounds(10, 118, 200, 20);
		lblLeftEdgeWavePhase_type2.setText(TextLabel_UI.lblLeftEdgeWavePhase_type2);
		
		type2_textLeftEdgeWavePhase = new Text(compositeShapeParameterChild_2, SWT.BORDER);
		med.setType2_textLeftEdgeWavePhase(type2_textLeftEdgeWavePhase);
		type2_textLeftEdgeWavePhase.setBounds(250, 116, 120, 24);
		
		Label lblRightEdgeWavePhase_type2 = new Label(compositeShapeParameterChild_2, SWT.NONE);
		lblRightEdgeWavePhase_type2.setBounds(10, 145, 200, 20);
		lblRightEdgeWavePhase_type2.setText(TextLabel_UI.lblRightEdgeWavePhase_type2);
		
		type2_textRightEdgeWavePhase = new Text(compositeShapeParameterChild_2, SWT.BORDER);
		med.setType2_textRightEdgeWavePhase(type2_textRightEdgeWavePhase);
		type2_textRightEdgeWavePhase.setBounds(250, 143, 120, 24);
		
		// Type3
		Composite compositeShapeParameterChild_3 = new Composite(compositeShapeParameterChild, SWT.NONE);
		med.setCompositeShapeParameterChild_3(compositeShapeParameterChild_3);
		compositeShapeParameterChild_3.setBounds(0, 0, 400, 130);
		
		Label lblWavePitch_type3 = new Label(compositeShapeParameterChild_3, SWT.NONE);
		lblWavePitch_type3.setBounds(10, 10, 170, 20);
		lblWavePitch_type3.setText(TextLabel_UI.lblWavePitch_type3);
		
		type3_textWavePitch = new Text(compositeShapeParameterChild_3, SWT.BORDER);
		med.setType3_textWavePitch(type3_textWavePitch);
		type3_textWavePitch.setBounds(210, 8, 120, 24);
		
		Label lblWaveHeight_type3 = new Label(compositeShapeParameterChild_3, SWT.NONE);
		lblWaveHeight_type3.setBounds(10, 37, 170, 20);
		lblWaveHeight_type3.setText(TextLabel_UI.lblWaveHeight_type3);
		
		type3_textWaveHeight = new Text(compositeShapeParameterChild_3, SWT.BORDER);
		med.setType3_textWaveHeight(type3_textWaveHeight);
		CustomText c_type3_textWaveHeight = new CustomText(Mediator.TEXT_type3_textWaveHeight,med);
		med.setC_type3_textWaveHeight(c_type3_textWaveHeight);
		c_type3_textWaveHeight.setCustomWidget_type3_textWaveHeight();
		type3_textWaveHeight.setBounds(210, 35, 120, 24);
		
		// Type4
		Composite compositeShapeParameterChild_4 = new Composite(compositeShapeParameterChild, SWT.NONE);
		med.setCompositeShapeParameterChild_4(compositeShapeParameterChild_4);
		compositeShapeParameterChild_4.setBounds(0, 0, 400, 130);
		
		Label lblGutterHeight_type4 = new Label(compositeShapeParameterChild_4, SWT.NONE);
		lblGutterHeight_type4.setBounds(10, 10, 170, 20);
		lblGutterHeight_type4.setText(TextLabel_UI.lblGutterHeight_type4);

		type4_textGutterHeight = new Text(compositeShapeParameterChild_4, SWT.BORDER);
		med.setType4_textGutterHeight(type4_textGutterHeight);
		CustomText c_type4_textGutterHeight = new CustomText(Mediator.TEXT_type4_textGutterHeight,med);
		med.setC_type4_textGutterHeight(c_type4_textGutterHeight);
		c_type4_textGutterHeight.setCustomWidget_type4_textGutterHeight();
		type4_textGutterHeight.setBounds(210, 8, 120, 24);

		// Type5		
		Composite compositeShapeParameterChild_5 = new Composite(compositeShapeParameterChild, SWT.NONE);
		med.setCompositeShapeParameterChild_5(compositeShapeParameterChild_5);
		compositeShapeParameterChild_5.setBounds(0, 0, 400, 130);
		
		Label lblGutterHeight_type5 = new Label(compositeShapeParameterChild_5, SWT.NONE);
		lblGutterHeight_type5.setBounds(10, 10, 170, 20);
		lblGutterHeight_type5.setText(TextLabel_UI.lblGutterHeight_type5);
		
		type5_textGutterHeight = new Text(compositeShapeParameterChild_5, SWT.BORDER);
		med.setType5_textGutterHeight(type5_textGutterHeight);
		CustomText c_type5_textGutterHeight = new CustomText(Mediator.TEXT_type5_textGutterHeight,med);
		med.setC_type5_textGutterHeight(c_type5_textGutterHeight);
		c_type5_textGutterHeight.setCustomWidget_type5_textGutterHeight();
		type5_textGutterHeight.setBounds(210, 8, 120, 24);
		
		Label lblGutterLength_type5 = new Label(compositeShapeParameterChild_5, SWT.NONE);
		lblGutterLength_type5.setBounds(10, 37, 170, 20);
		lblGutterLength_type5.setText(TextLabel_UI.lblGutterLength_type5);
		
		type5_textGutterLength = new Text(compositeShapeParameterChild_5, SWT.BORDER);
		med.setType5_textGutterLength(type5_textGutterLength);
		CustomText c_type5_textGutterLength = new CustomText(Mediator.TEXT_type5_textGutterLength,med);
		med.setC_type5_textGutterLength(c_type5_textGutterLength);
		c_type5_textGutterLength.setCustomWidget_type5_textGutterLength();
		type5_textGutterLength.setBounds(210, 35, 120, 24);
	
		// Type6
		Composite compositeShapeParameterChild_6 = new Composite(compositeShapeParameterChild, SWT.NONE);
		med.setCompositeShapeParameterChild_6(compositeShapeParameterChild_6);
		compositeShapeParameterChild_6.setBounds(0, 0, 400, 130);
		
		Label lblHeadGutterHeight_type6 = new Label(compositeShapeParameterChild_6, SWT.NONE);
		lblHeadGutterHeight_type6.setBounds(10, 10, 200, 20);
		lblHeadGutterHeight_type6.setText(TextLabel_UI.lblHeadGutterHeight_type6);
		
		type6_textHeadGutterHeight = new Text(compositeShapeParameterChild_6, SWT.BORDER);
		med.setType6_textHeadGutterHeight(type6_textHeadGutterHeight);
		CustomText c_type6_textHeadGutterHeight = new CustomText(Mediator.TEXT_type6_textHeadGutterHeight,med);
		med.setC_type6_textHeadGutterHeight(c_type6_textHeadGutterHeight);
		c_type6_textHeadGutterHeight.setCustomWidget_type6_textHeadGutterHeight();
		type6_textHeadGutterHeight.setBounds(250, 8, 120, 24);
		
		Label lblHeadGutterLength_type6 = new Label(compositeShapeParameterChild_6, SWT.NONE);
		lblHeadGutterLength_type6.setBounds(10, 37, 200, 20);
		lblHeadGutterLength_type6.setText(TextLabel_UI.lblHeadGutterLength_type6);
		
		type6_textHeadGutterLength = new Text(compositeShapeParameterChild_6, SWT.BORDER);
		med.setType6_textHeadGutterLength(type6_textHeadGutterLength);
		CustomText c_type6_textHeadGutterLength = new CustomText(Mediator.TEXT_type6_textHeadGutterLength,med);
		med.setC_type6_textHeadGutterLength(c_type6_textHeadGutterLength);
		c_type6_textHeadGutterLength.setCustomWidget_type6_textHeadGutterLength();
		type6_textHeadGutterLength.setBounds(250, 35, 120, 24);
		
		Label lblTailGutterHeight_type6 = new Label(compositeShapeParameterChild_6, SWT.NONE);
		lblTailGutterHeight_type6.setBounds(10, 64, 200, 20);
		lblTailGutterHeight_type6.setText(TextLabel_UI.lblTailGutterHeight_type6);
		
		type6_textTailGutterHeight = new Text(compositeShapeParameterChild_6, SWT.BORDER);
		med.setType6_textTailGutterHeight(type6_textTailGutterHeight);
		CustomText c_type6_textTailGutterHeight = new CustomText(Mediator.TEXT_type6_textTailGutterHeight,med);
		med.setC_type6_textTailGutterHeight(c_type6_textTailGutterHeight);
		c_type6_textTailGutterHeight.setCustomWidget_type6_textTailGutterHeight();
		type6_textTailGutterHeight.setBounds(250, 62, 120, 24);
		
		Label lblTailGutterLength_type6 = new Label(compositeShapeParameterChild_6, SWT.NONE);
		lblTailGutterLength_type6.setBounds(10, 91, 200, 20);
		lblTailGutterLength_type6.setText(TextLabel_UI.lblTailGutterLength_type6);
		
		type6_textTailGutterLength = new Text(compositeShapeParameterChild_6, SWT.BORDER);
		med.setType6_textTailGutterLength(type6_textTailGutterLength);
		CustomText c_type6_textTailGutterLength = new CustomText(Mediator.TEXT_type6_textTailGutterLength,med);
		med.setC_type6_textTailGutterLength(c_type6_textTailGutterLength);
		c_type6_textTailGutterLength.setCustomWidget_type6_textTailGutterLength();
		type6_textTailGutterLength.setBounds(250, 89, 120, 24);
		
		// Type7
		Composite compositeShapeParameterChild_7 = new Composite(compositeShapeParameterChild, SWT.NONE);
		med.setCompositeShapeParameterChild_7(compositeShapeParameterChild_7);
		compositeShapeParameterChild_7.setBounds(0, 0, 400, 130);
		
		Label lblHeadGutterHeight_type7 = new Label(compositeShapeParameterChild_7, SWT.NONE);
		lblHeadGutterHeight_type7.setBounds(10, 10, 250, 20);
		lblHeadGutterHeight_type7.setText(TextLabel_UI.lblHeadGutterHeight_type7);
		
		type7_textHeadGutterHeight = new Text(compositeShapeParameterChild_7, SWT.BORDER);
		med.setType7_textHeadGutterHeight(type7_textHeadGutterHeight);
		CustomText c_type7_textHeadGutterHeight = new CustomText(Mediator.TEXT_type7_textHeadGutterHeight,med);
		med.setC_type7_textHeadGutterHeight(c_type7_textHeadGutterHeight);
		c_type7_textHeadGutterHeight.setCustomWidget_type7_textHeadGutterHeight();
		type7_textHeadGutterHeight.setBounds(260, 8, 120, 24);
		
		Label lblGutterLength_type7 = new Label(compositeShapeParameterChild_7, SWT.NONE);
		lblGutterLength_type7.setBounds(10, 37, 250, 20);
		lblGutterLength_type7.setText(TextLabel_UI.lblGutterLength_type7);
		
		type7_textGutterLength = new Text(compositeShapeParameterChild_7, SWT.BORDER);
		med.setType7_textGutterLength(type7_textGutterLength);
		CustomText c_type7_textGutterLength = new CustomText(Mediator.TEXT_type7_textGutterLength,med);
		med.setC_type7_textGutterLength(c_type7_textGutterLength);
		c_type7_textGutterLength.setCustomWidget_type7_textGutterLength();
		type7_textGutterLength.setBounds(260, 35, 120, 24);
		
		Label lblGutterLengthLength_type7 = new Label(compositeShapeParameterChild_7, SWT.NONE);
		lblGutterLengthLength_type7.setBounds(10, 64, 250, 20);
		lblGutterLengthLength_type7.setText(TextLabel_UI.lblGutterLengthLength_type7);
		
		type7_textGutterLengthLength = new Text(compositeShapeParameterChild_7, SWT.BORDER);
		med.setType7_textGutterLengthLength(type7_textGutterLengthLength);
		CustomText c_type7_textGutterLengthLength = new CustomText(Mediator.TEXT_type7_textGutterLengthLength,med);
		med.setC_type7_textGutterLengthLength(c_type7_textGutterLengthLength);
		c_type7_textGutterLengthLength.setCustomWidget_type7_textGutterLengthLength();
		type7_textGutterLengthLength.setBounds(260, 62, 120, 24);
		
		Label lblGutterWidthLength_type7 = new Label(compositeShapeParameterChild_7, SWT.NONE);
		lblGutterWidthLength_type7.setBounds(10, 91, 250, 20);
		lblGutterWidthLength_type7.setText(TextLabel_UI.lblGutterWidthLength_type7);
		
		type7_textGutterWidthLength = new Text(compositeShapeParameterChild_7, SWT.BORDER);
		med.setType7_textGutterWidthLength(type7_textGutterWidthLength);
		CustomText c_type7_textGutterWidthLength = new CustomText(Mediator.TEXT_type7_textGutterWidthLength,med);
		med.setC_type7_textGutterWidthLength(c_type7_textGutterWidthLength);
		c_type7_textGutterWidthLength.setCustomWidget_type7_textGutterWidthLength();
		type7_textGutterWidthLength.setBounds(260, 89, 120, 24);
				
		compositeShapeParameterChild_1.setVisible(false);
		compositeShapeParameterChild_2.setVisible(false);
		compositeShapeParameterChild_3.setVisible(false);
		compositeShapeParameterChild_4.setVisible(false);
		compositeShapeParameterChild_5.setVisible(false);
		compositeShapeParameterChild_6.setVisible(false);
		compositeShapeParameterChild_7.setVisible(false);
		
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		Composite compositeMeshParameter = new Composite(compositeParent3D, SWT.BORDER);
		med.setCompositeMeshParameter(compositeMeshParameter);
		compositeMeshParameter.setLayout(new FormLayout());
		FormData fd_compositeMeshParameter = new FormData();
		fd_compositeMeshParameter.top = new FormAttachment(compositeShapeParameter, 30);
		fd_compositeMeshParameter.left = new FormAttachment(compositeShapeParameter, 0, SWT.LEFT);
		fd_compositeMeshParameter.right = new FormAttachment(compositeShapeParameter, 0, SWT.RIGHT);
		fd_compositeMeshParameter.bottom = new FormAttachment(compositeShapeParameter, 260, SWT.BOTTOM);
		compositeMeshParameter.setLayoutData(fd_compositeMeshParameter);
		
		Label lblMeshParameter = new Label(compositeMeshParameter, SWT.NONE);
		lblMeshParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblMeshParameter = new FormData();
		fd_lblMeshParameter.top = new FormAttachment(0, 10);
		fd_lblMeshParameter.left = new FormAttachment(0, 10);
		lblMeshParameter.setLayoutData(fd_lblMeshParameter);
		lblMeshParameter.setText(TextLabel_UI.lblMeshParameter);
		
		Label lblThicknessElementNum = new Label(compositeMeshParameter, SWT.NONE);
		FormData fd_lblThicknessElementNum = new FormData();
		fd_lblThicknessElementNum.top = new FormAttachment(lblMeshParameter, 12);
		fd_lblThicknessElementNum.left = new FormAttachment(lblMeshParameter, 10, SWT.LEFT);
		lblThicknessElementNum.setLayoutData(fd_lblThicknessElementNum);
		lblThicknessElementNum.setText(TextLabel_UI.lblThicknessElementNum);
		
		textThicknessElementNum = new Text(compositeMeshParameter, SWT.BORDER);
		med.setTextThicknessElementNum(textThicknessElementNum);
		FormData fd_textThicknessElementNum = new FormData();
		fd_textThicknessElementNum.top = new FormAttachment(lblThicknessElementNum, -2, SWT.TOP);
		fd_textThicknessElementNum.left = new FormAttachment(lblThicknessElementNum,20);
		fd_textThicknessElementNum.right = new FormAttachment(100, -10);
		textThicknessElementNum.setLayoutData(fd_textThicknessElementNum);
		
		Label lblWidthAspectRatio = new Label(compositeMeshParameter, SWT.NONE);
		FormData fd_lblWidthAspectRatio = new FormData();
		fd_lblWidthAspectRatio.top = new FormAttachment(lblThicknessElementNum, 12);
		fd_lblWidthAspectRatio.left = new FormAttachment(lblThicknessElementNum, 0, SWT.LEFT);
		lblWidthAspectRatio.setLayoutData(fd_lblWidthAspectRatio);
		lblWidthAspectRatio.setText(TextLabel_UI.lblWidthAspectRatio);
		
		textWidthAspectRatio = new Text(compositeMeshParameter, SWT.BORDER);
		med.setTextWidthAspectRatio(textWidthAspectRatio);
		FormData fd_textWidthAspectRatio = new FormData();
		fd_textWidthAspectRatio.top = new FormAttachment(lblWidthAspectRatio, -2, SWT.TOP);
		fd_textWidthAspectRatio.left = new FormAttachment(textThicknessElementNum, 0, SWT.LEFT);
		fd_textWidthAspectRatio.right = new FormAttachment(textThicknessElementNum, 0, SWT.RIGHT);
		textWidthAspectRatio.setLayoutData(fd_textWidthAspectRatio);
		
		Label lblLengthAspectRatio = new Label(compositeMeshParameter, SWT.NONE);
		FormData fd_lblLengthAspectRatio = new FormData();
		fd_lblLengthAspectRatio.top = new FormAttachment(lblWidthAspectRatio, 12);
		fd_lblLengthAspectRatio.left = new FormAttachment(lblThicknessElementNum, 0, SWT.LEFT);
		lblLengthAspectRatio.setLayoutData(fd_lblLengthAspectRatio);
		lblLengthAspectRatio.setText(TextLabel_UI.lblLengthAspectRatio);
		
		textLengthAspectRatio = new Text(compositeMeshParameter, SWT.BORDER);
		med.setTextLengthAspectRatio(textLengthAspectRatio);
		FormData fd_textLengthAspectRatio = new FormData();
		fd_textLengthAspectRatio.top = new FormAttachment(lblLengthAspectRatio, -2, SWT.TOP);
		fd_textLengthAspectRatio.left = new FormAttachment(textThicknessElementNum, 0, SWT.LEFT);
		fd_textLengthAspectRatio.right = new FormAttachment(textThicknessElementNum, 0, SWT.RIGHT);
		textLengthAspectRatio.setLayoutData(fd_textLengthAspectRatio);
		
		Label lblElementNumber = new Label(compositeMeshParameter, SWT.NONE);
		FormData fd_lblElementNumber = new FormData();
		fd_lblElementNumber.top = new FormAttachment(lblLengthAspectRatio, 12);
		fd_lblElementNumber.left = new FormAttachment(lblThicknessElementNum, 0, SWT.LEFT);
		lblElementNumber.setLayoutData(fd_lblElementNumber);
		lblElementNumber.setText(TextLabel_UI.lblElementNumber);
		
		
		textElementNumber = new Text(compositeMeshParameter, SWT.BORDER);
		med.setTextElementNumber(textElementNumber);
		FormData fd_textElementNumber = new FormData();
		fd_textElementNumber.top = new FormAttachment(lblElementNumber, -2, SWT.TOP);
		fd_textElementNumber.left = new FormAttachment(textThicknessElementNum, 0, SWT.LEFT);
		fd_textElementNumber.right = new FormAttachment(textThicknessElementNum, 0, SWT.RIGHT);
		textElementNumber.setLayoutData(fd_textElementNumber);
		textElementNumber.setEnabled(false);
		
		
		Button btnCalcElementNum = new Button(compositeMeshParameter, SWT.NONE);
		med.setBtnCalcElementNum(btnCalcElementNum);
		CustomButton customBtnCalcElementNum = new CustomButton(Mediator.BUTTON_CalcElementNum,med);
		med.setCustomBtnCalcElementNum(customBtnCalcElementNum);
		customBtnCalcElementNum.setCustomWidget_btnCalcElementNum();
		FormData fd_btnCalcElementNum = new FormData();
		fd_btnCalcElementNum.top = new FormAttachment(textElementNumber, 10);
		fd_btnCalcElementNum.right = new FormAttachment(textThicknessElementNum, 0, SWT.RIGHT);
		btnCalcElementNum.setLayoutData(fd_btnCalcElementNum);
		btnCalcElementNum.setText(TextLabel_UI.btnCalcElementNum);
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		
		Composite compositePlateInformation = new Composite(compositeParent3D, SWT.BORDER);
		med.setCompositePlateInformation(compositePlateInformation);
		compositePlateInformation.setLayout(new FormLayout());
		FormData fd_compositePlateInformation = new FormData();
		fd_compositePlateInformation.top = new FormAttachment(compositeMeshParameter,30);
		fd_compositePlateInformation.left = new FormAttachment(compositeShapeParameter, 0, SWT.LEFT);
		fd_compositePlateInformation.right = new FormAttachment(compositeShapeParameter, 0, SWT.RIGHT);
		fd_compositePlateInformation.bottom = new FormAttachment(compositeMeshParameter,250,SWT.BOTTOM);
		compositePlateInformation.setLayoutData(fd_compositePlateInformation);
		
		Label lblPlateInformation = new Label(compositePlateInformation, SWT.NONE);
		lblPlateInformation.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblPlateInformation = new FormData();
		fd_lblPlateInformation.top = new FormAttachment(0, 10);
		fd_lblPlateInformation.left = new FormAttachment(0, 10);
		lblPlateInformation.setLayoutData(fd_lblPlateInformation);
		lblPlateInformation.setText(TextLabel_UI.lblPlateInformation);
		
		Label lblPlateVelocity = new Label(compositePlateInformation, SWT.NONE);
		FormData fd_lblPlateVelocity = new FormData();
		fd_lblPlateVelocity.top = new FormAttachment(lblPlateInformation, 12);
		fd_lblPlateVelocity.left = new FormAttachment(lblPlateInformation,10,SWT.LEFT);
		lblPlateVelocity.setLayoutData(fd_lblPlateVelocity);
		lblPlateVelocity.setText(TextLabel_UI.lblPlateVelocity);
		
		textPlateVelocity = new Text(compositePlateInformation, SWT.BORDER);
		med.setTextPlateVelocity(textPlateVelocity);
		FormData fd_textPlateVelocity = new FormData();
		fd_textPlateVelocity.top = new FormAttachment(lblPlateVelocity, -2, SWT.TOP);
		fd_textPlateVelocity.left = new FormAttachment(lblPlateVelocity, 120);
		fd_textPlateVelocity.right = new FormAttachment(100, -10);
		textPlateVelocity.setLayoutData(fd_textPlateVelocity);
		
		Label lblTemperatureStart = new Label(compositePlateInformation, SWT.NONE);
		FormData fd_lblTemperatureStart = new FormData();
		fd_lblTemperatureStart.top = new FormAttachment(lblPlateVelocity, 12);
		fd_lblTemperatureStart.left = new FormAttachment(lblPlateVelocity, 0, SWT.LEFT);
		lblTemperatureStart.setLayoutData(fd_lblTemperatureStart);
		lblTemperatureStart.setText(TextLabel_UI.lblTemperatureStart);
		
		textTemperatureStart = new Text(compositePlateInformation, SWT.BORDER);
		med.setTextTemperatureStart(textTemperatureStart);
		FormData fd_textTemperatureStart = new FormData();
		fd_textTemperatureStart.top = new FormAttachment(lblTemperatureStart, -2,SWT.TOP);
		fd_textTemperatureStart.left = new FormAttachment(textPlateVelocity,0, SWT.LEFT);
		fd_textTemperatureStart.right = new FormAttachment(textPlateVelocity, 0, SWT.RIGHT);
		textTemperatureStart.setLayoutData(fd_textTemperatureStart);
		
		Label lblTemperatureEnd = new Label(compositePlateInformation, SWT.NONE);
		FormData fd_lblTemperatureEnd = new FormData();
		fd_lblTemperatureEnd.top = new FormAttachment(lblTemperatureStart, 12);
		fd_lblTemperatureEnd.left = new FormAttachment(lblPlateVelocity, 0, SWT.LEFT);
		lblTemperatureEnd.setLayoutData(fd_lblTemperatureEnd);
		lblTemperatureEnd.setText(TextLabel_UI.lblTemperatureEnd);
		
		textTemperatureEnd = new Text(compositePlateInformation, SWT.BORDER);
		med.setTextTemperatureEnd(textTemperatureEnd);
		FormData fd_textTemperatureEnd = new FormData();
		fd_textTemperatureEnd.top = new FormAttachment(lblTemperatureEnd, -2, SWT.TOP);
		fd_textTemperatureEnd.left = new FormAttachment(textPlateVelocity, 0, SWT.LEFT);
		fd_textTemperatureEnd.right = new FormAttachment(textPlateVelocity, 0, SWT.RIGHT);
		textTemperatureEnd.setLayoutData(fd_textTemperatureEnd);
		
		
		Label lblPassLineOffset = new Label(compositePlateInformation, SWT.NONE);
		FormData fd_lblPassLineOffset = new FormData();
		fd_lblPassLineOffset.top = new FormAttachment(lblTemperatureEnd, 12);
		fd_lblPassLineOffset.left = new FormAttachment(lblPlateVelocity, 0, SWT.LEFT);
		lblPassLineOffset.setLayoutData(fd_lblPassLineOffset);
		lblPassLineOffset.setText(TextLabel_UI.lblPassLineOffset);
		
		textPassLineOffset = new Text(compositePlateInformation, SWT.BORDER);
		med.setTextPassLineOffset(textPassLineOffset);
		FormData fd_textPassLineOffset = new FormData();
		fd_textPassLineOffset.top = new FormAttachment(lblPassLineOffset, -2, SWT.TOP);
		fd_textPassLineOffset.left = new FormAttachment(textPlateVelocity, 0, SWT.LEFT);
		fd_textPassLineOffset.right = new FormAttachment(textPlateVelocity, 0, SWT.RIGHT);
		textPassLineOffset.setLayoutData(fd_textPassLineOffset);
		
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		
		//expandJS
		
		final Composite compositeRollParameter = new Composite(compositeParent3D, SWT.BORDER);
		med.setCompositeRollParameter(compositeRollParameter);
		compositeRollParameter.setLayout(new FormLayout());
		FormData fd_compositeRollParameter = new FormData();
		fd_compositeRollParameter.top = new FormAttachment(compositeShapeParameter, 0, SWT.TOP);
		fd_compositeRollParameter.left = new FormAttachment(compositeShapeParameter, 10);
		fd_compositeRollParameter.right = new FormAttachment(compositeShapeParameter,500,SWT.RIGHT);
		fd_compositeRollParameter.bottom = new FormAttachment(compositePlateInformation,0,SWT.BOTTOM);
		compositeRollParameter.setLayoutData(fd_compositeRollParameter);
		
		
		Label lblRollParameter = new Label(compositeRollParameter, SWT.NONE);
		lblRollParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblRollParameter = new FormData();
		fd_lblRollParameter.top = new FormAttachment(0, 10);
		fd_lblRollParameter.left = new FormAttachment(0, 10);
		lblRollParameter.setLayoutData(fd_lblRollParameter);
		lblRollParameter.setText(TextLabel_UI.lblRollParameter);
		
		
		
		Label lblUpperRollNumber = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblUpperRollNumber = new FormData();
		fd_lblUpperRollNumber.top = new FormAttachment(lblRollParameter, 10);
		fd_lblUpperRollNumber.left = new FormAttachment(lblRollParameter, 10, SWT.LEFT);
		lblUpperRollNumber.setLayoutData(fd_lblUpperRollNumber);
		lblUpperRollNumber.setText(TextLabel_UI.lblUpperRollNumber);
		
		Spinner spinnerUpperRollNum = new Spinner(compositeRollParameter, SWT.BORDER);
		med.setSpinnerUpperRollNum(spinnerUpperRollNum);
		CustomSpinner customSpinnerUpperRollNum = new CustomSpinner(Mediator.SPINNER_UpperRollNum,med);
		med.setCustomSpinnerUpperRollNum(customSpinnerUpperRollNum);
		customSpinnerUpperRollNum.setCustomWidget_spinnerUpperRollNum();
		FormData fd_spinnerUpperRollNum = new FormData();
		fd_spinnerUpperRollNum.top = new FormAttachment(lblUpperRollNumber, -2, SWT.TOP);
		fd_spinnerUpperRollNum.left = new FormAttachment(lblUpperRollNumber, 50,SWT.RIGHT);
		fd_spinnerUpperRollNum.right = new FormAttachment(lblUpperRollNumber, 110, SWT.RIGHT);
		spinnerUpperRollNum.setLayoutData(fd_spinnerUpperRollNum);
			
		Label lblLowerRollNumber = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblLowerRollNumber = new FormData();
		fd_lblLowerRollNumber.top = new FormAttachment(lblUpperRollNumber, 0, SWT.TOP);
		fd_lblLowerRollNumber.left = new FormAttachment(spinnerUpperRollNum, 20, SWT.RIGHT);
		lblLowerRollNumber.setLayoutData(fd_lblLowerRollNumber);
		lblLowerRollNumber.setText(TextLabel_UI.lblLowerRollNumber);
		
		Spinner spinnerLowerRollNum = new Spinner(compositeRollParameter, SWT.BORDER);
		med.setSpinnerLowerRollNum(spinnerLowerRollNum);
		CustomSpinner customSpinnerLowerRollNum = new CustomSpinner(Mediator.SPINNER_LowerRollNum,med);
		med.setCustomSpinnerLowerRollNum(customSpinnerLowerRollNum);
		customSpinnerLowerRollNum.setCustomWidget_spinnerLowerRollNum();
		FormData fd_spinnerLowerRollNum = new FormData();
		fd_spinnerLowerRollNum.top = new FormAttachment(lblLowerRollNumber, -2, SWT.TOP);
		fd_spinnerLowerRollNum.left = new FormAttachment(lblLowerRollNumber, 50, SWT.RIGHT);
		fd_spinnerLowerRollNum.right = new FormAttachment(lblLowerRollNumber, 110, SWT.RIGHT);
		spinnerLowerRollNum.setLayoutData(fd_spinnerLowerRollNum);
		
		
		
		Label lblRollPitch = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblRollPitch = new FormData();
		fd_lblRollPitch.top = new FormAttachment(lblUpperRollNumber, 10);
		fd_lblRollPitch.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblRollPitch.setLayoutData(fd_lblRollPitch);
		lblRollPitch.setText(TextLabel_UI.lblRollPitch);
		
		textRollPitch = new Text(compositeRollParameter, SWT.BORDER);
		med.setTextRollPitch(textRollPitch);
		FormData fd_textRollPitch = new FormData();
		fd_textRollPitch.top = new FormAttachment(lblRollPitch, -2, SWT.TOP);
		fd_textRollPitch.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textRollPitch.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textRollPitch.setLayoutData(fd_textRollPitch);
		
		Label lblRollLength = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblRollLength = new FormData();
		fd_lblRollLength.top = new FormAttachment(lblRollPitch, 0, SWT.TOP);
		fd_lblRollLength.left = new FormAttachment(lblLowerRollNumber, 0, SWT.LEFT);
		lblRollLength.setLayoutData(fd_lblRollLength);
		lblRollLength.setText(TextLabel_UI.lblRollLength);
		
		textRollLength = new Text(compositeRollParameter, SWT.BORDER);
		med.setTextRollLength(textRollLength);
		FormData fd_textRollLength = new FormData();
		fd_textRollLength.top = new FormAttachment(lblRollLength, -2, SWT.TOP);
		fd_textRollLength.left = new FormAttachment(spinnerLowerRollNum, 0, SWT.LEFT);
		fd_textRollLength.right = new FormAttachment(spinnerLowerRollNum, 0, SWT.RIGHT);
		textRollLength.setLayoutData(fd_textRollLength);
		//textRollLength.setEnabled(false);
		
		Label lblEntryRollTableDistance = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblEntryRollTableDistance = new FormData();
		fd_lblEntryRollTableDistance.top = new FormAttachment(lblRollPitch, 10);
		fd_lblEntryRollTableDistance.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblEntryRollTableDistance.setLayoutData(fd_lblEntryRollTableDistance);
		lblEntryRollTableDistance.setText(TextLabel_UI.lblEntryRollTableDistance);
		
		textEntryRollTableDistance = new Text(compositeRollParameter, SWT.BORDER);
		med.setTextEntryRollTableDistance(textEntryRollTableDistance);
		FormData fd_textEntryRollTableDistance = new FormData();
		fd_textEntryRollTableDistance.top = new FormAttachment(lblEntryRollTableDistance, -2, SWT.TOP);
		fd_textEntryRollTableDistance.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textEntryRollTableDistance.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textEntryRollTableDistance.setLayoutData(fd_textEntryRollTableDistance);
		
		Label lblExitRollTableDistance = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblExitRollTableDistance = new FormData();
		fd_lblExitRollTableDistance.top = new FormAttachment(lblEntryRollTableDistance, 0, SWT.TOP);
		fd_lblExitRollTableDistance.left = new FormAttachment(lblLowerRollNumber, 0, SWT.LEFT);
		lblExitRollTableDistance.setLayoutData(fd_lblExitRollTableDistance);
		lblExitRollTableDistance.setText(TextLabel_UI.lblExitRollTableDistance);
		
		textExitRollTableDistance = new Text(compositeRollParameter, SWT.BORDER);
		med.setTextExitRollTableDistance(textExitRollTableDistance);
		FormData fd_textExitRollTableDistance = new FormData();
		fd_textExitRollTableDistance.top = new FormAttachment(lblExitRollTableDistance, -2, SWT.TOP);
		fd_textExitRollTableDistance.left = new FormAttachment(spinnerLowerRollNum, 0, SWT.LEFT);
		fd_textExitRollTableDistance.right = new FormAttachment(spinnerLowerRollNum, 0, SWT.RIGHT);
		textExitRollTableDistance.setLayoutData(fd_textExitRollTableDistance);
		
		
		
		
		
		
		
		Label lblEntryUpperRollGap = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblEntryUpperRollGap = new FormData();
		fd_lblEntryUpperRollGap.top = new FormAttachment(lblEntryRollTableDistance, 10);
		fd_lblEntryUpperRollGap.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblEntryUpperRollGap.setLayoutData(fd_lblEntryUpperRollGap);
		lblEntryUpperRollGap.setText(TextLabel_UI.lblEntryUpperRollGap);
		
		textEntryUpperRollGap = new Text(compositeRollParameter, SWT.BORDER);
		med.setTextEntryUpperRollGap(textEntryUpperRollGap);
		FormData fd_textEntryUpperRollGap = new FormData();
		fd_textEntryUpperRollGap.top = new FormAttachment(lblEntryUpperRollGap, -2, SWT.TOP);
		fd_textEntryUpperRollGap.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textEntryUpperRollGap.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textEntryUpperRollGap.setLayoutData(fd_textEntryUpperRollGap);
		
		Label lblExitUpperRollGap = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblExitUpperRollGap = new FormData();
		fd_lblExitUpperRollGap.top = new FormAttachment(lblEntryUpperRollGap, 0, SWT.TOP);
		fd_lblExitUpperRollGap.left = new FormAttachment(lblLowerRollNumber, 0, SWT.LEFT);
		lblExitUpperRollGap.setLayoutData(fd_lblExitUpperRollGap);
		lblExitUpperRollGap.setText(TextLabel_UI.lblExitUpperRollGap);
		
		textExitUpperRollGap = new Text(compositeRollParameter, SWT.BORDER);
		med.setTextExitUpperRollGap(textExitUpperRollGap);
		FormData fd_textExitUpperRollGap = new FormData();
		fd_textExitUpperRollGap.top = new FormAttachment(lblExitUpperRollGap, -2, SWT.TOP);
		fd_textExitUpperRollGap.left = new FormAttachment(spinnerLowerRollNum, 0, SWT.LEFT);
		fd_textExitUpperRollGap.right = new FormAttachment(spinnerLowerRollNum, 0, SWT.RIGHT);
		textExitUpperRollGap.setLayoutData(fd_textExitUpperRollGap);
		
		
		
		Label lblEntryLowerRollGap = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblEntryLowerRollGap = new FormData();
		fd_lblEntryLowerRollGap.top = new FormAttachment(lblEntryUpperRollGap, 10);
		fd_lblEntryLowerRollGap.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblEntryLowerRollGap.setLayoutData(fd_lblEntryLowerRollGap);
		lblEntryLowerRollGap.setText(TextLabel_UI.lblEntryLowerRollGap);
		
		textEntryLowerRollGap = new Text(compositeRollParameter, SWT.BORDER);
		med.setTextEntryLowerRollGap(textEntryLowerRollGap);
		FormData fd_textEntryLowerRollGap = new FormData();
		fd_textEntryLowerRollGap.top = new FormAttachment(lblEntryLowerRollGap, -2, SWT.TOP);
		fd_textEntryLowerRollGap.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textEntryLowerRollGap.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textEntryLowerRollGap.setLayoutData(fd_textEntryLowerRollGap);
		
		Label lblExitLowerRollGap = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblExitLowerRollGap = new FormData();
		fd_lblExitLowerRollGap.top = new FormAttachment(lblEntryLowerRollGap, 0, SWT.TOP);
		fd_lblExitLowerRollGap.left = new FormAttachment(lblLowerRollNumber, 0, SWT.LEFT);
		lblExitLowerRollGap.setLayoutData(fd_lblExitLowerRollGap);
		lblExitLowerRollGap.setText(TextLabel_UI.lblExitLowerRollGap);
		
		textExitLowerRollGap = new Text(compositeRollParameter, SWT.BORDER);
		med.setTextExitLowerRollGap(textExitLowerRollGap);
		FormData fd_textExitLowerRollGap = new FormData();
		fd_textExitLowerRollGap.top = new FormAttachment(lblExitLowerRollGap, -2, SWT.TOP);
		fd_textExitLowerRollGap.left = new FormAttachment(spinnerLowerRollNum, 0, SWT.LEFT);
		fd_textExitLowerRollGap.right = new FormAttachment(spinnerLowerRollNum, 0, SWT.RIGHT);
		textExitLowerRollGap.setLayoutData(fd_textExitLowerRollGap);
		
		
		
		
		Label lblRollFriction = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblRollFriction = new FormData();
		fd_lblRollFriction.top = new FormAttachment(lblEntryLowerRollGap, 10);
		fd_lblRollFriction.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblRollFriction.setLayoutData(fd_lblRollFriction);
		lblRollFriction.setText(TextLabel_UI.lblRollFriction);
		
		textRollFriction = new Text(compositeRollParameter, SWT.BORDER);
		med.setTextRollFriction(textRollFriction);
		FormData fd_textRollFriction = new FormData();
		fd_textRollFriction.top = new FormAttachment(lblRollFriction, -2, SWT.TOP);
		fd_textRollFriction.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textRollFriction.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textRollFriction.setLayoutData(fd_textRollFriction);
	
		Label lblRollDiameter = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblRollDiameter = new FormData();
		fd_lblRollDiameter.top = new FormAttachment(lblRollFriction, 0, SWT.TOP);
		fd_lblRollDiameter.left = new FormAttachment(lblLowerRollNumber, 0, SWT.LEFT);
		lblRollDiameter.setLayoutData(fd_lblRollDiameter);
		lblRollDiameter.setText(TextLabel_UI.lblRollDiameter);
		
		textRollDiameter = new Text(compositeRollParameter, SWT.BORDER);
		med.setTextRollDiameter(textRollDiameter);
		FormData fd_textRollDiameter = new FormData();
		fd_textRollDiameter.top = new FormAttachment(lblRollDiameter, -2, SWT.TOP);
		fd_textRollDiameter.left = new FormAttachment(spinnerLowerRollNum, 0, SWT.LEFT);
		fd_textRollDiameter.right = new FormAttachment(spinnerLowerRollNum, 0, SWT.RIGHT);
		textRollDiameter.setLayoutData(fd_textRollDiameter);
		
		
		Label lblUpperRollCrown = new Label(compositeRollParameter,SWT.NONE);
		FormData fd_lblUpperRollCrown = new FormData();
		fd_lblUpperRollCrown.top = new FormAttachment(lblRollFriction,10);
		fd_lblUpperRollCrown.left = new FormAttachment(lblRollFriction, 0, SWT.LEFT);
		lblUpperRollCrown.setLayoutData(fd_lblUpperRollCrown);
		lblUpperRollCrown.setText(TextLabel_UI.lblUpperRollCrown);
		
		textUpperRollCrown = new Text(compositeRollParameter, SWT.BORDER);
		med.setTextUpperRollCrown(textUpperRollCrown);
		FormData fd_textUpperRollCrown = new FormData();
		fd_textUpperRollCrown.top = new FormAttachment(lblUpperRollCrown, -2, SWT.TOP);
		fd_textUpperRollCrown.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textUpperRollCrown.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textUpperRollCrown.setLayoutData(fd_textUpperRollCrown);
		
		/*
		Composite groupRollCrown = new Composite(compositeRollParameter, SWT.NONE);
		groupRollCrown.setLayout(new FormLayout());
		FormData fd_groupRollCrown = new FormData();
		fd_groupRollCrown.top = new FormAttachment(lblUpperRollCrown, 0,SWT.TOP);
		fd_groupRollCrown.left = new FormAttachment(lblUpperRollCrown, 15,SWT.RIGHT);
		fd_groupRollCrown.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		groupRollCrown.setLayoutData(fd_groupRollCrown);
		
		Button btnRadioNone_RC = new Button(groupRollCrown, SWT.RADIO);
		med.setBtnRadioNone_RC(btnRadioNone_RC);
		CustomButton customBtnRadioNone_RC = new CustomButton(Mediator.BUTTON_RadioNone_RC,med);
		med.setCustomBtnRadioNone_RC(customBtnRadioNone_RC);
		customBtnRadioNone_RC.setCustomWidget_btnRadioNone_RC();
		btnRadioNone_RC.setAlignment(SWT.CENTER);
		FormData fd_btnRadioNone_RC = new FormData();
		fd_btnRadioNone_RC.top = new FormAttachment(0, 0);
		fd_btnRadioNone_RC.left = new FormAttachment(0, 0);
		btnRadioNone_RC.setLayoutData(fd_btnRadioNone_RC);
		btnRadioNone_RC.setText(TextLabel_UI.btnRadioNone);
		btnRadioNone_RC.setSelection(false);
		
		Button btnRadioApply_RC = new Button(groupRollCrown, SWT.RADIO);
		med.setBtnRadioApply_RC(btnRadioApply_RC);
		CustomButton customBtnRadioApply_RC = new CustomButton(Mediator.BUTTON_RadioApply_RC, med);
		med.setCustomBtnRadioApply_RC(customBtnRadioApply_RC);
		customBtnRadioApply_RC.setCustomWidget_btnRadioApply_RC();
		FormData fd_btnRadioApply_RC = new FormData();
		fd_btnRadioApply_RC.top = new FormAttachment(btnRadioNone_RC, 0, SWT.TOP);
		fd_btnRadioApply_RC.left = new FormAttachment(btnRadioNone_RC, 20);
		btnRadioApply_RC.setLayoutData(fd_btnRadioApply_RC);
		btnRadioApply_RC.setText(TextLabel_UI.btnRadioApply);
		btnRadioApply_RC.setSelection(true);
		// */
		Label lblLowerRollCrown = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblLowerRollCrown = new FormData();
		fd_lblLowerRollCrown.top = new FormAttachment(lblUpperRollCrown, 0,SWT.TOP);
		fd_lblLowerRollCrown.left = new FormAttachment(lblLowerRollNumber, 0, SWT.LEFT);
		lblLowerRollCrown.setLayoutData(fd_lblLowerRollCrown);
		lblLowerRollCrown.setText(TextLabel_UI.lblLowerRollCrown);
		
		textLowerRollCrown = new Text(compositeRollParameter, SWT.BORDER);
		med.setTextLowerRollCrown(textLowerRollCrown);
		FormData fd_textLowerRollCrown = new FormData();
		fd_textLowerRollCrown.top = new FormAttachment(lblLowerRollCrown, -2, SWT.TOP);
		fd_textLowerRollCrown.left = new FormAttachment(spinnerLowerRollNum, 0, SWT.LEFT);
		fd_textLowerRollCrown.right = new FormAttachment(spinnerLowerRollNum, 0, SWT.RIGHT);
		textLowerRollCrown.setLayoutData(fd_textLowerRollCrown);
		
		Label lblMillStiffnessType = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblMillStiffnessType = new FormData();
		fd_lblMillStiffnessType.top = new FormAttachment(lblUpperRollCrown, 10);
		fd_lblMillStiffnessType.left = new FormAttachment(lblUpperRollCrown, 0, SWT.LEFT);
		lblMillStiffnessType.setLayoutData(fd_lblMillStiffnessType);
		lblMillStiffnessType.setText(TextLabel_UI.lblMillStiffnessType);
		
		Composite groupMillStiffness = new Composite(compositeRollParameter,SWT.NONE);
		groupMillStiffness.setLayout(new FormLayout());
		FormData fd_groupMillStiffness = new FormData();
		fd_groupMillStiffness.top = new FormAttachment(lblMillStiffnessType, 0,SWT.TOP);
		fd_groupMillStiffness.left = new FormAttachment(lblMillStiffnessType, 15, SWT.RIGHT);
		fd_groupMillStiffness.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		groupMillStiffness.setLayoutData(fd_groupMillStiffness);
		
		Button btnRadioRigid_MS = new Button(groupMillStiffness, SWT.RADIO);
		med.setBtnRadioRigid_MS(btnRadioRigid_MS);
		CustomButton customBtnRadioRigid_MS = new CustomButton(Mediator.BUTTON_RadioRigid_MS,med);
		med.setCustomBtnRadioRigid_MS(customBtnRadioRigid_MS);
		customBtnRadioRigid_MS.setCustomWidget_btnRadioRigid_MS();
		btnRadioRigid_MS.setAlignment(SWT.CENTER);
		FormData fd_btnRadioRigid_MS = new FormData();
		fd_btnRadioRigid_MS.top = new FormAttachment(0, 0);
		fd_btnRadioRigid_MS.left = new FormAttachment(0, 0);
		btnRadioRigid_MS.setLayoutData(fd_btnRadioRigid_MS);
		btnRadioRigid_MS.setText(TextLabel_UI.btnRadioRigid);
		btnRadioRigid_MS.setSelection(false);
		
		Button btnRadioSpring_MS = new Button(groupMillStiffness, SWT.RADIO);
		med.setBtnRadioSpring_MS(btnRadioSpring_MS);
		CustomButton customBtnRadioSpring_MS = new CustomButton(Mediator.BUTTON_RadioSpring_MS, med);
		med.setCustomBtnRadioSpring_MS(customBtnRadioSpring_MS);
		customBtnRadioSpring_MS.setCustomWidget_btnRadioSpring_MS();
		FormData fd_btnRadioSpring_MS = new FormData();
		fd_btnRadioSpring_MS.top = new FormAttachment(btnRadioRigid_MS, 0, SWT.TOP);
		fd_btnRadioSpring_MS.left = new FormAttachment(btnRadioRigid_MS, 20);
		btnRadioSpring_MS.setLayoutData(fd_btnRadioSpring_MS);
		btnRadioSpring_MS.setText(TextLabel_UI.btnRadioSpring);
		btnRadioSpring_MS.setSelection(true);
		
		Label lblMillStiffness = new Label(compositeRollParameter, SWT.NONE);
		FormData fd_lblMillStiffness = new FormData();
		fd_lblMillStiffness.top = new FormAttachment(lblMillStiffnessType, 0, SWT.TOP);
		fd_lblMillStiffness.left = new FormAttachment(lblLowerRollNumber, 0, SWT.LEFT);
		lblMillStiffness.setLayoutData(fd_lblMillStiffness);
		lblMillStiffness.setText(TextLabel_UI.lblMillStiffness);
		
		textMillStiffness = new Text(compositeRollParameter, SWT.BORDER);
		med.setTextMillStiffness(textMillStiffness);
		FormData fd_textMillStiffness = new FormData();
		fd_textMillStiffness.top = new FormAttachment(lblMillStiffness, -2, SWT.TOP);
		fd_textMillStiffness.left = new FormAttachment(spinnerLowerRollNum, 0, SWT.LEFT);
		fd_textMillStiffness.right = new FormAttachment(spinnerLowerRollNum, 0, SWT.RIGHT);
		textMillStiffness.setLayoutData(fd_textMillStiffness);

		
		
		
		
		Group grpRollGapMovementInformation = new Group(compositeRollParameter, SWT.NONE);
		grpRollGapMovementInformation.setText(TextLabel_UI.grpRollGapMovementInformation);
		grpRollGapMovementInformation.setLayout(new FormLayout());
		grpRollGapMovementInformation.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_grpRollGapMovementInformation = new FormData();
		fd_grpRollGapMovementInformation.top = new FormAttachment(lblMillStiffnessType, 10);
		fd_grpRollGapMovementInformation.left = new FormAttachment(0, 10);
		fd_grpRollGapMovementInformation.right = new FormAttachment(100,-10);
		grpRollGapMovementInformation.setLayoutData(fd_grpRollGapMovementInformation);
		
		Label lblUpperRollGap = new Label(grpRollGapMovementInformation, SWT.NONE);
		lblUpperRollGap.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		FormData fd_lblUpperRollGap = new FormData();
		fd_lblUpperRollGap.top = new FormAttachment(0, 10);
		fd_lblUpperRollGap.left = new FormAttachment(0, 5);
		lblUpperRollGap.setLayoutData(fd_lblUpperRollGap);
		lblUpperRollGap.setText(TextLabel_UI.lblUpperRollGap);
		
		Label lblUpperEntryRollGapMovement = new Label(grpRollGapMovementInformation, SWT.NONE);
		FormData fd_lblUpperEntryRollGapMovement = new FormData();
		fd_lblUpperEntryRollGapMovement.top = new FormAttachment(lblUpperRollGap, 10);
		fd_lblUpperEntryRollGapMovement.left = new FormAttachment(lblUpperRollGap, 10, SWT.LEFT);
		lblUpperEntryRollGapMovement.setLayoutData(fd_lblUpperEntryRollGapMovement);
		lblUpperEntryRollGapMovement.setText(TextLabel_UI.lblUpperEntryRollGapMovement);
		
		textUpperEntryRollGapMovement = new Text(grpRollGapMovementInformation, SWT.BORDER);
		med.setTextUpperEntryRollGapMovement(textUpperEntryRollGapMovement);
		FormData fd_textUpperEntryRollGapMovement = new FormData();
		fd_textUpperEntryRollGapMovement.top = new FormAttachment(lblUpperEntryRollGapMovement, -2, SWT.TOP);
		fd_textUpperEntryRollGapMovement.left = new FormAttachment(lblUpperEntryRollGapMovement, 50);
		fd_textUpperEntryRollGapMovement.right = new FormAttachment(100,-10);
		textUpperEntryRollGapMovement.setLayoutData(fd_textUpperEntryRollGapMovement);
		
		Label lblUpperExitRollGapMovement = new Label(grpRollGapMovementInformation, SWT.NONE);
		FormData fd_lblUpperExitRollGapMovement = new FormData();
		fd_lblUpperExitRollGapMovement.top = new FormAttachment(lblUpperEntryRollGapMovement, 10);
		fd_lblUpperExitRollGapMovement.left = new FormAttachment(lblUpperEntryRollGapMovement, 0, SWT.LEFT);
		lblUpperExitRollGapMovement.setLayoutData(fd_lblUpperExitRollGapMovement);
		lblUpperExitRollGapMovement.setText(TextLabel_UI.lblUpperExitRollGapMovement);
		
		textUpperExitRollGapMovement = new Text(grpRollGapMovementInformation, SWT.BORDER);
		med.setTextUpperExitRollGapMovement(textUpperExitRollGapMovement);
		FormData fd_textUpperExitRollGapMovement = new FormData();
		fd_textUpperExitRollGapMovement.top = new FormAttachment(lblUpperExitRollGapMovement, -2, SWT.TOP);
		fd_textUpperExitRollGapMovement.left = new FormAttachment(textUpperEntryRollGapMovement, 0, SWT.LEFT);
		fd_textUpperExitRollGapMovement.right = new FormAttachment(100,-10);
		textUpperExitRollGapMovement.setLayoutData(fd_textUpperExitRollGapMovement);
		
		Label lblUpperRollGapStayingTime = new Label(grpRollGapMovementInformation, SWT.NONE);
		FormData fd_lblUpperRollGapStayingTime = new FormData();
		fd_lblUpperRollGapStayingTime.top = new FormAttachment(lblUpperExitRollGapMovement, 10);
		fd_lblUpperRollGapStayingTime.left = new FormAttachment(lblUpperExitRollGapMovement, 0, SWT.LEFT);
		lblUpperRollGapStayingTime.setLayoutData(fd_lblUpperRollGapStayingTime);
		lblUpperRollGapStayingTime.setText(TextLabel_UI.lblUpperRollGapStayingTime);
		
		textUpperRollGapStayingTime = new Text(grpRollGapMovementInformation, SWT.BORDER);
		med.setTextUpperRollGapStayingTime(textUpperRollGapStayingTime);
		FormData fd_textUpperRollGapStayingTime = new FormData();
		fd_textUpperRollGapStayingTime.top = new FormAttachment(lblUpperRollGapStayingTime, -2, SWT.TOP);
		fd_textUpperRollGapStayingTime.left = new FormAttachment(textUpperEntryRollGapMovement, 0, SWT.LEFT);
		fd_textUpperRollGapStayingTime.right = new FormAttachment(100,-10);
		textUpperRollGapStayingTime.setLayoutData(fd_textUpperRollGapStayingTime);
		
		Label lblUpperRollGapMovingTime = new Label(grpRollGapMovementInformation, SWT.NONE);
		FormData fd_lblUpperRollGapMovingTime = new FormData();
		fd_lblUpperRollGapMovingTime.top = new FormAttachment(lblUpperRollGapStayingTime, 10);
		fd_lblUpperRollGapMovingTime.left = new FormAttachment(lblUpperRollGapStayingTime, 0, SWT.LEFT);
		lblUpperRollGapMovingTime.setLayoutData(fd_lblUpperRollGapMovingTime);
		lblUpperRollGapMovingTime.setText(TextLabel_UI.lblUpperRollGapMovingTime);
		
		textUpperRollGapMovingTime = new Text(grpRollGapMovementInformation, SWT.BORDER);
		med.setTextUpperRollGapMovingTime(textUpperRollGapMovingTime);
		FormData fd_textUpperRollGapMovingTime = new FormData();
		fd_textUpperRollGapMovingTime.top = new FormAttachment(lblUpperRollGapMovingTime, -2, SWT.TOP);
		fd_textUpperRollGapMovingTime.left = new FormAttachment(textUpperEntryRollGapMovement, 0, SWT.LEFT);
		fd_textUpperRollGapMovingTime.right = new FormAttachment(100,-10);
		textUpperRollGapMovingTime.setLayoutData(fd_textUpperRollGapMovingTime);
		

		Label lblLowerRollGap = new Label(grpRollGapMovementInformation, SWT.NONE);
		lblLowerRollGap.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		FormData fd_lblLowerRollGap = new FormData();
		fd_lblLowerRollGap.top = new FormAttachment(lblUpperRollGapMovingTime, 10);
		fd_lblLowerRollGap.left = new FormAttachment(lblUpperRollGap, 0,SWT.LEFT);
		lblLowerRollGap.setLayoutData(fd_lblLowerRollGap);
		lblLowerRollGap.setText(TextLabel_UI.lblLowerRollGap);

		Label lblLowerEntryRollGapMovement = new Label(grpRollGapMovementInformation, SWT.NONE);
		FormData fd_lblLowerEntryRollGapMovement = new FormData();
		fd_lblLowerEntryRollGapMovement.top = new FormAttachment(lblLowerRollGap, 10);
		fd_lblLowerEntryRollGapMovement.left = new FormAttachment(lblUpperRollGapStayingTime, 0, SWT.LEFT);
		lblLowerEntryRollGapMovement.setLayoutData(fd_lblLowerEntryRollGapMovement);
		lblLowerEntryRollGapMovement.setText(TextLabel_UI.lblLowerEntryRollGapMovement);
		
		textLowerEntryRollGapMovement = new Text(grpRollGapMovementInformation, SWT.BORDER);
		med.setTextLowerEntryRollGapMovement(textLowerEntryRollGapMovement);
		FormData fd_textLowerEntryRollGapMovement = new FormData();
		fd_textLowerEntryRollGapMovement.top = new FormAttachment(lblLowerEntryRollGapMovement, -2, SWT.TOP);
		fd_textLowerEntryRollGapMovement.left = new FormAttachment(textUpperEntryRollGapMovement, 0, SWT.LEFT);
		fd_textLowerEntryRollGapMovement.right = new FormAttachment(100,-10);
		textLowerEntryRollGapMovement.setLayoutData(fd_textLowerEntryRollGapMovement);
		
		Label lblLowerExitRollGapMovement = new Label(grpRollGapMovementInformation, SWT.NONE);
		FormData fd_lblLowerExitRollGapMovement = new FormData();
		fd_lblLowerExitRollGapMovement.top = new FormAttachment(lblLowerEntryRollGapMovement, 10);
		fd_lblLowerExitRollGapMovement.left = new FormAttachment(lblLowerEntryRollGapMovement, 0, SWT.LEFT);
		lblLowerExitRollGapMovement.setLayoutData(fd_lblLowerExitRollGapMovement);
		lblLowerExitRollGapMovement.setText(TextLabel_UI.lblLowerExitRollGapMovement);
		
		textLowerExitRollGapMovement = new Text(grpRollGapMovementInformation, SWT.BORDER);
		med.setTextLowerExitRollGapMovement(textLowerExitRollGapMovement);
		FormData fd_textLowerExitRollGapMovement = new FormData();
		fd_textLowerExitRollGapMovement.top = new FormAttachment(lblLowerExitRollGapMovement, -2, SWT.TOP);
		fd_textLowerExitRollGapMovement.left = new FormAttachment(textUpperEntryRollGapMovement, 0, SWT.LEFT);
		fd_textLowerExitRollGapMovement.right = new FormAttachment(100,-10);
		textLowerExitRollGapMovement.setLayoutData(fd_textLowerExitRollGapMovement);
		
		Label lblLowerRollGapStayingTime = new Label(grpRollGapMovementInformation, SWT.NONE);
		FormData fd_lblLowerRollGapStayingTime = new FormData();
		fd_lblLowerRollGapStayingTime.top = new FormAttachment(lblLowerExitRollGapMovement, 10);
		fd_lblLowerRollGapStayingTime.left = new FormAttachment(lblLowerExitRollGapMovement, 0, SWT.LEFT);
		lblLowerRollGapStayingTime.setLayoutData(fd_lblLowerRollGapStayingTime);
		lblLowerRollGapStayingTime.setText(TextLabel_UI.lblLowerRollGapStayingTime);
		
		textLowerRollGapStayingTime = new Text(grpRollGapMovementInformation, SWT.BORDER);
		med.setTextLowerRollGapStayingTime(textLowerRollGapStayingTime);
		FormData fd_textLowerRollGapStayingTime = new FormData();
		fd_textLowerRollGapStayingTime.top = new FormAttachment(lblLowerRollGapStayingTime, -2, SWT.TOP);
		fd_textLowerRollGapStayingTime.left = new FormAttachment(textUpperEntryRollGapMovement, 0, SWT.LEFT);
		fd_textLowerRollGapStayingTime.right = new FormAttachment(100,-10);
		textLowerRollGapStayingTime.setLayoutData(fd_textLowerRollGapStayingTime);
		
		Label lblLowerRollGapMovingTime = new Label(grpRollGapMovementInformation, SWT.NONE);
		FormData fd_lblLowerRollGapMovingTime = new FormData();
		fd_lblLowerRollGapMovingTime.top = new FormAttachment(lblLowerRollGapStayingTime, 10);
		fd_lblLowerRollGapMovingTime.left = new FormAttachment(lblLowerRollGapStayingTime, 0, SWT.LEFT);
		lblLowerRollGapMovingTime.setLayoutData(fd_lblLowerRollGapMovingTime);
		lblLowerRollGapMovingTime.setText(TextLabel_UI.lblLowerRollGapMovingTime);
		
		textLowerRollGapMovingTime = new Text(grpRollGapMovementInformation, SWT.BORDER);
		med.setTextLowerRollGapMovingTime(textLowerRollGapMovingTime);
		FormData fd_textLowerRollGapMovingTime = new FormData();
		fd_textLowerRollGapMovingTime.top = new FormAttachment(lblLowerRollGapMovingTime, -2, SWT.TOP);
		fd_textLowerRollGapMovingTime.left = new FormAttachment(textUpperEntryRollGapMovement, 0, SWT.LEFT);
		fd_textLowerRollGapMovingTime.right = new FormAttachment(100,-10);
		textLowerRollGapMovingTime.setLayoutData(fd_textLowerRollGapMovingTime);
		
		
		Group grpHoldDownRollInformation = new Group(compositeRollParameter, SWT.NONE);
		grpHoldDownRollInformation.setText("Hold Down Roll Information");
		grpHoldDownRollInformation.setLayout(new FormLayout());
		grpHoldDownRollInformation.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_grpHoldDownRollInformation = new FormData();
		fd_grpHoldDownRollInformation.top = new FormAttachment(grpRollGapMovementInformation, 10);
		fd_grpHoldDownRollInformation.left = new FormAttachment(0, 10);
		fd_grpHoldDownRollInformation.right = new FormAttachment(100,-10);
		grpHoldDownRollInformation.setLayoutData(fd_grpHoldDownRollInformation);
		
		Label lblHoldDownRollGeneration = new Label(grpHoldDownRollInformation, SWT.NONE);
		lblHoldDownRollGeneration.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		FormData fd_lblHoldDownRollGeneration = new FormData();
		fd_lblHoldDownRollGeneration.top = new FormAttachment(0, 10);
		fd_lblHoldDownRollGeneration.left = new FormAttachment(0, 5);
		lblHoldDownRollGeneration.setLayoutData(fd_lblHoldDownRollGeneration);
		lblHoldDownRollGeneration.setText("Hold Down Roll Generation");
		
		Composite compositeHoldDownRollType = new Composite(grpHoldDownRollInformation, SWT.NONE);
		compositeHoldDownRollType.setLayout(new FormLayout());
		FormData fd_compositeHoldDownRollType = new FormData();
		fd_compositeHoldDownRollType.top = new FormAttachment(lblHoldDownRollGeneration, 5);
		fd_compositeHoldDownRollType.left = new FormAttachment(lblHoldDownRollGeneration, 0, SWT.LEFT);
		fd_compositeHoldDownRollType.right = new FormAttachment(100,-10);
		compositeHoldDownRollType.setLayoutData(fd_compositeHoldDownRollType);
		
		Button btnNone = new Button(compositeHoldDownRollType, SWT.RADIO);
		med.setBtnNone(btnNone);
		CustomButton customBtnNone = new CustomButton(Mediator.BUTTON_btnNone,med);
		med.setCustomBtnNone(customBtnNone);
		customBtnNone.setCustomWidget_btnNone();
		FormData fd_btnNone = new FormData();
		fd_btnNone.top = new FormAttachment(0);
		fd_btnNone.left = new FormAttachment(0,120);
		btnNone.setLayoutData(fd_btnNone);
		btnNone.setText(TextLabel_UI.btnNone);
		
		Button btnUpper = new Button(compositeHoldDownRollType, SWT.RADIO);
		med.setBtnUpper(btnUpper);
		CustomButton customBtnUpper = new CustomButton(Mediator.BUTTON_btnUpper,med);
		med.setCustomBtnUpper(customBtnUpper);
		customBtnUpper.setCustomWidget_btnUpper();
		FormData fd_btnUpper = new FormData();
		fd_btnUpper.top = new FormAttachment(0);
		fd_btnUpper.left = new FormAttachment(btnNone, 20,SWT.RIGHT);
		btnUpper.setLayoutData(fd_btnUpper);
		btnUpper.setText(TextLabel_UI.btnUpper);
		
		Button btnLower = new Button(compositeHoldDownRollType, SWT.RADIO);
		med.setBtnLower(btnLower);
		CustomButton customBtnLower = new CustomButton(Mediator.BUTTON_btnLower,med);
		med.setCustomBtnLower(customBtnLower);
		customBtnLower.setCustomWidget_btnLower();
		FormData fd_btnLower = new FormData();
		fd_btnLower.top = new FormAttachment(0);
		fd_btnLower.left = new FormAttachment(btnUpper, 20,SWT.RIGHT);
		btnLower.setLayoutData(fd_btnLower);
		btnLower.setText(TextLabel_UI.btnLower);
		
		Label lblFrontRollPosition = new Label(grpHoldDownRollInformation, SWT.NONE);
		lblFrontRollPosition.setText(TextLabel_UI.lblFrontRollPosition);
		lblFrontRollPosition.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		FormData fd_lblFrontRollPosition = new FormData();
		fd_lblFrontRollPosition.top = new FormAttachment(compositeHoldDownRollType, 5);
		fd_lblFrontRollPosition.left = new FormAttachment(lblHoldDownRollGeneration, 0, SWT.LEFT);
		lblFrontRollPosition.setLayoutData(fd_lblFrontRollPosition);
		
		Label lblFrontHDRollDia = new Label(grpHoldDownRollInformation, SWT.NONE);
		lblFrontHDRollDia.setText(TextLabel_UI.lblFrontHDRollDia);
		FormData fd_lblFrontHDRollDia = new FormData();
		fd_lblFrontHDRollDia.top = new FormAttachment(lblFrontRollPosition, 5);
		fd_lblFrontHDRollDia.left = new FormAttachment(lblFrontRollPosition, 10, SWT.LEFT);
		lblFrontHDRollDia.setLayoutData(fd_lblFrontHDRollDia);
		
		textFrontHDRollDia = new Text(grpHoldDownRollInformation, SWT.BORDER);
		med.setTextFrontHDRollDia(textFrontHDRollDia);
		FormData fd_textFrontHDRollDia = new FormData();
		fd_textFrontHDRollDia.top = new FormAttachment(lblFrontHDRollDia, -2, SWT.TOP);
		fd_textFrontHDRollDia.left = new FormAttachment(lblFrontHDRollDia, 100);
		fd_textFrontHDRollDia.right = new FormAttachment(100,-20);
		textFrontHDRollDia.setLayoutData(fd_textFrontHDRollDia);	
		
		Label lblFrontHDRollPitch = new Label(grpHoldDownRollInformation, SWT.NONE);
		lblFrontHDRollPitch.setText(TextLabel_UI.lblFrontHDRollPitch);
		FormData fd_lblFrontHDRollPitch = new FormData();
		fd_lblFrontHDRollPitch.top = new FormAttachment(lblFrontHDRollDia, 10);
		fd_lblFrontHDRollPitch.left = new FormAttachment(lblFrontHDRollDia, 0, SWT.LEFT);
		lblFrontHDRollPitch.setLayoutData(fd_lblFrontHDRollPitch);
		
		textFrontHDRollPitch = new Text(grpHoldDownRollInformation, SWT.BORDER);
		med.setTextFrontHDRollPitch(textFrontHDRollPitch);
		FormData fd_textFrontHDRollPitch = new FormData();
		fd_textFrontHDRollPitch.top = new FormAttachment(lblFrontHDRollPitch, -2, SWT.TOP);
		fd_textFrontHDRollPitch.left = new FormAttachment(textFrontHDRollDia, 0,SWT.LEFT);
		fd_textFrontHDRollPitch.right = new FormAttachment(textFrontHDRollDia, 0,SWT.RIGHT);
		textFrontHDRollPitch.setLayoutData(fd_textFrontHDRollPitch);
		
		Label lblFrontHDRollVerticalPos = new Label(grpHoldDownRollInformation, SWT.NONE);
		lblFrontHDRollVerticalPos.setText(TextLabel_UI.lblFrontHDRollVericalPos);
		FormData fd_lblFrontHDRollVerticalPos = new FormData();
		fd_lblFrontHDRollVerticalPos.top = new FormAttachment(lblFrontHDRollPitch, 10);
		fd_lblFrontHDRollVerticalPos.left = new FormAttachment(lblFrontHDRollPitch, 0, SWT.LEFT);
		lblFrontHDRollVerticalPos.setLayoutData(fd_lblFrontHDRollVerticalPos);
		
		textFrontHDRollVericalPos = new Text(grpHoldDownRollInformation, SWT.BORDER);
		med.setTextFrontHDRollVericalPos(textFrontHDRollVericalPos);
		FormData fd_textFrontHDRollVericalPos = new FormData();
		fd_textFrontHDRollVericalPos.top = new FormAttachment(lblFrontHDRollVerticalPos, -2, SWT.TOP);
		fd_textFrontHDRollVericalPos.left = new FormAttachment(textFrontHDRollDia, 0,SWT.LEFT);
		fd_textFrontHDRollVericalPos.right = new FormAttachment(textFrontHDRollDia, 0,SWT.RIGHT);
		textFrontHDRollVericalPos.setLayoutData(fd_textFrontHDRollVericalPos);
		
		Label lblRearRollPosition = new Label(grpHoldDownRollInformation, SWT.NONE);
		lblRearRollPosition.setText(TextLabel_UI.lblRearRollPosition);
		lblRearRollPosition.setFont(SWTResourceManager.getFont("Arial", 9, SWT.ITALIC));
		FormData fd_lblRearRollPosition = new FormData();
		fd_lblRearRollPosition.top = new FormAttachment(lblFrontHDRollVerticalPos, 5);
		fd_lblRearRollPosition.left = new FormAttachment(lblFrontRollPosition, 0, SWT.LEFT);
		lblRearRollPosition.setLayoutData(fd_lblRearRollPosition);
		
		Label lblRearHDRollDia = new Label(grpHoldDownRollInformation, SWT.NONE);
		lblRearHDRollDia.setText(TextLabel_UI.lblRearHDRollDia);
		FormData fd_lblRearHDRollDia = new FormData();
		fd_lblRearHDRollDia.top = new FormAttachment(lblRearRollPosition, 5);
		fd_lblRearHDRollDia.left = new FormAttachment(lblFrontHDRollVerticalPos, 0, SWT.LEFT);
		lblRearHDRollDia.setLayoutData(fd_lblRearHDRollDia);
		
		textRearHDRollDia = new Text(grpHoldDownRollInformation, SWT.BORDER);
		med.setTextRearHDRollDia(textRearHDRollDia);
		FormData fd_textRearHDRollDia = new FormData();
		fd_textRearHDRollDia.top = new FormAttachment(lblRearHDRollDia, -2,SWT.TOP);
		fd_textRearHDRollDia.left = new FormAttachment(textFrontHDRollVericalPos, 0, SWT.LEFT);
		fd_textRearHDRollDia.right = new FormAttachment(textFrontHDRollVericalPos, 0, SWT.RIGHT);
		textRearHDRollDia.setLayoutData(fd_textRearHDRollDia);
		
		Label lblRearHDRollPitch = new Label(grpHoldDownRollInformation, SWT.NONE);
		lblRearHDRollPitch.setText(TextLabel_UI.lblRearHDRollPitch);
		FormData fd_lblRearHDRollPitch = new FormData();
		fd_lblRearHDRollPitch.top = new FormAttachment(lblRearHDRollDia, 10);
		fd_lblRearHDRollPitch.left = new FormAttachment(lblRearHDRollDia, 0, SWT.LEFT);
		lblRearHDRollPitch.setLayoutData(fd_lblRearHDRollPitch);
		
		textRearHDRollPitch = new Text(grpHoldDownRollInformation, SWT.BORDER);
		med.setTextRearHDRollPitch(textRearHDRollPitch);
		FormData fd_textRearHDRollPitch = new FormData();
		fd_textRearHDRollPitch.top = new FormAttachment(lblRearHDRollPitch, -2, SWT.TOP);
		fd_textRearHDRollPitch.left = new FormAttachment(textRearHDRollDia, 0, SWT.LEFT);
		fd_textRearHDRollPitch.right = new FormAttachment(textRearHDRollDia, 0, SWT.RIGHT);
		textRearHDRollPitch.setLayoutData(fd_textRearHDRollPitch);
		
		Label lblRearHDRollVerticalPos = new Label(grpHoldDownRollInformation, SWT.NONE);
		lblRearHDRollVerticalPos.setText(TextLabel_UI.lblRearHDRollVerticalPos);
		FormData fd_lblRearHDRollVerticalPos = new FormData();
		fd_lblRearHDRollVerticalPos.top = new FormAttachment(lblRearHDRollPitch, 10);
		fd_lblRearHDRollVerticalPos.left = new FormAttachment(lblRearHDRollPitch, 0, SWT.LEFT);
		lblRearHDRollVerticalPos.setLayoutData(fd_lblRearHDRollVerticalPos);
		
		textRearHDRollVerticalPos = new Text(grpHoldDownRollInformation, SWT.BORDER);
		med.setTextRearHDRollVerticalPos(textRearHDRollVerticalPos);
		FormData fd_textRearHDRollVerticalPos = new FormData();
		fd_textRearHDRollVerticalPos.top = new FormAttachment(lblRearHDRollVerticalPos, -2, SWT.TOP);
		fd_textRearHDRollVerticalPos.left = new FormAttachment(textRearHDRollPitch, 0, SWT.LEFT);
		fd_textRearHDRollVerticalPos.right = new FormAttachment(textRearHDRollPitch, 0, SWT.RIGHT);
		textRearHDRollVerticalPos.setLayoutData(fd_textRearHDRollVerticalPos);
		
		Button btnCreateRoll = new Button(compositeRollParameter, SWT.NONE);
		med.setBtnCreateRoll(btnCreateRoll);
		CustomButton customBtnCreateRoll = new CustomButton(Mediator.BUTTON_CreateRoll,med);
		med.setCustomBtnCreateRoll(customBtnCreateRoll);
		customBtnCreateRoll.setCustomWidget_btnCreateRoll();
		FormData fd_btnCreateRoll  = new FormData();
		fd_btnCreateRoll.top = new FormAttachment(grpHoldDownRollInformation, 5);
		fd_btnCreateRoll.left = new FormAttachment(grpHoldDownRollInformation, 0, SWT.LEFT);
		fd_btnCreateRoll.right = new FormAttachment(grpHoldDownRollInformation, 0, SWT.RIGHT);
		btnCreateRoll.setLayoutData(fd_btnCreateRoll);
		btnCreateRoll.setText(TextLabel_UI.btnCreateRoll);
		
		Button btnShowRollTable = new Button(compositeRollParameter, SWT.NONE);
		med.setBtnShowRollTable(btnShowRollTable);
		CustomButton customBtnShowRollTable = new CustomButton(Mediator.BUTTON_btnShowRollTable,med);
		med.setCustomBtnShowRollTable(customBtnShowRollTable);
		customBtnShowRollTable.setCustomWidget_btnShowRollTable();
		btnShowRollTable.setText("Show Roll Table");
		FormData fd_btnShowRollTable = new FormData();
		fd_btnShowRollTable.top = new FormAttachment(btnCreateRoll, 10);
		fd_btnShowRollTable.left = new FormAttachment(btnCreateRoll, 0, SWT.LEFT);
		fd_btnShowRollTable.right = new FormAttachment(btnCreateRoll, 0, SWT.RIGHT);
		btnShowRollTable.setLayoutData(fd_btnShowRollTable);
			
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		Composite compositeMaterialParameter = new Composite(compositeParent3D, SWT.BORDER);
		med.setCompositeMaterialParameter(compositeMaterialParameter);
		compositeMaterialParameter.setLayout(new FormLayout());
		FormData fd_compositeMaterialParameter = new FormData();
		fd_compositeMaterialParameter.top = new FormAttachment(compositeShapeParameter, 0, SWT.TOP);
		fd_compositeMaterialParameter.left = new FormAttachment(compositeRollParameter, 10);
		fd_compositeMaterialParameter.right = new FormAttachment(compositeRollParameter, 450,SWT.RIGHT);
		fd_compositeMaterialParameter.bottom = new FormAttachment(lblModelName, 540, SWT.BOTTOM);
		compositeMaterialParameter.setLayoutData(fd_compositeMaterialParameter);
		
		Label lblMaterialParameter = new Label(compositeMaterialParameter, SWT.NONE);
		lblMaterialParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblMaterialParameter = new FormData();
		fd_lblMaterialParameter.top = new FormAttachment(0, 10);
		fd_lblMaterialParameter.left = new FormAttachment(0, 10);
		lblMaterialParameter.setLayoutData(fd_lblMaterialParameter);
		lblMaterialParameter.setText(TextLabel_UI.lblMaterialParameter);
		
		Label lblYoungsModulus = new Label(compositeMaterialParameter, SWT.NONE);
		FormData fd_lblYoungsModulus = new FormData();
		fd_lblYoungsModulus.top = new FormAttachment(lblMaterialParameter, 12);
		fd_lblYoungsModulus.left = new FormAttachment(lblMaterialParameter, 10, SWT.LEFT);
		lblYoungsModulus.setLayoutData(fd_lblYoungsModulus);
		lblYoungsModulus.setText(TextLabel_UI.lblYoungsModulus);
		
		
		Composite group1 = new Composite(compositeMaterialParameter, SWT.NONE);
		group1.setLayout(new FormLayout());
		FormData fd_group1 = new FormData();
		fd_group1.top = new FormAttachment(lblYoungsModulus, 2);
		fd_group1.left = new FormAttachment(lblYoungsModulus, 10,SWT.LEFT);
		group1.setLayoutData(fd_group1);
		
		Button btnRadioConstant_YM = new Button(group1, SWT.RADIO);
		med.setBtnRadioConstant_YM(btnRadioConstant_YM);
		CustomButton customBtnRadioConstant_YM = new CustomButton(Mediator.BUTTON_RadioConstant_YM,med);
		med.setCustomBtnRadioConstant_YM(customBtnRadioConstant_YM);
		customBtnRadioConstant_YM.setCustomWidget_btnRadioConstant_YM();
		FormData fd_btnRadioConstant_YM = new FormData();
		fd_btnRadioConstant_YM.top = new FormAttachment(0, 5);
		fd_btnRadioConstant_YM.left = new FormAttachment(0, 5);
		btnRadioConstant_YM.setLayoutData(fd_btnRadioConstant_YM);
		btnRadioConstant_YM.setText(TextLabel_UI.btnRadioConstant);
		btnRadioConstant_YM.setSelection(false);
		
		Button btnRadioTable_YM = new Button(group1, SWT.RADIO);
		med.setBtnRadioTable_YM(btnRadioTable_YM);
		CustomButton customBtnRadioTable_YM = new CustomButton(Mediator.BUTTON_RadioTable_YM, med);
		med.setCustomBtnRadioTable_YM(customBtnRadioTable_YM);
		customBtnRadioTable_YM.setCustomWidget_btnRadioTable_YM();
		FormData fd_btnRadioTable_YM = new FormData();
		fd_btnRadioTable_YM.top = new FormAttachment(btnRadioConstant_YM, 0, SWT.TOP);
		fd_btnRadioTable_YM.left = new FormAttachment(btnRadioConstant_YM, 20);
		btnRadioTable_YM.setLayoutData(fd_btnRadioTable_YM);
		btnRadioTable_YM.setText(TextLabel_UI.btnRadioTable);
		btnRadioTable_YM.setSelection(true);
		
		textYoungsModulus = new Text(compositeMaterialParameter, SWT.BORDER);
		med.setTextYoungsModulus(textYoungsModulus);
		FormData fd_textYoungsModulus = new FormData();
		fd_textYoungsModulus.top = new FormAttachment(group1, 5);
		fd_textYoungsModulus.left = new FormAttachment(lblYoungsModulus,20,SWT.LEFT);
		fd_textYoungsModulus.right = new FormAttachment(100, -45);
		textYoungsModulus.setLayoutData(fd_textYoungsModulus);

		Button btnExplorerYoungsModulus = new Button(compositeMaterialParameter, SWT.NONE);
		med.setBtnExplorerYoungsModulus(btnExplorerYoungsModulus);
		CustomButton customBtnExplorerYoungsModulus = new CustomButton(Mediator.BUTTON_ExplorerYoungsModulus,med);
		med.setCustomBtnExplorerYoungsModulus(customBtnExplorerYoungsModulus);
		customBtnExplorerYoungsModulus.setCustomWidget_btnExplorerYoungsModulus();
		FormData fd_btnExplorerYoungsModulus = new FormData();
		fd_btnExplorerYoungsModulus.top = new FormAttachment(textYoungsModulus, -2, SWT.TOP);
		fd_btnExplorerYoungsModulus.right = new FormAttachment(100, -10);
		btnExplorerYoungsModulus.setLayoutData(fd_btnExplorerYoungsModulus);
		btnExplorerYoungsModulus.setText("...");
		
		Label lblFlowStress = new Label(compositeMaterialParameter, SWT.NONE);
		FormData fd_lblFlowStress = new FormData();
		fd_lblFlowStress.top = new FormAttachment(textYoungsModulus, 12);
		fd_lblFlowStress.left = new FormAttachment(lblYoungsModulus, 0, SWT.LEFT);
		lblFlowStress.setLayoutData(fd_lblFlowStress);
		lblFlowStress.setText(TextLabel_UI.lblFlowStress);
		
		Composite group2 = new Composite(compositeMaterialParameter, SWT.NONE);
		group2.setLayout(new FormLayout());
		FormData fd_group2 = new FormData();
		fd_group2.top = new FormAttachment(lblFlowStress, 2);
		fd_group2.left = new FormAttachment(lblFlowStress, 10,SWT.LEFT);
		group2.setLayoutData(fd_group2);
		
		Button btnRadioConstant_FS = new Button(group2, SWT.RADIO);
		med.setBtnRadioConstant_FS(btnRadioConstant_FS);
		CustomButton customBtnRadioConstant_FS = new CustomButton(Mediator.BUTTON_RadioConstant_FS,med);
		med.setCustomBtnRadioConstant_FS(customBtnRadioConstant_FS);
		customBtnRadioConstant_FS.setCustomWidget_btnRadioConstant_FS();
		FormData fd_btnRadioConstant_FS = new FormData();
		fd_btnRadioConstant_FS.top = new FormAttachment(0, 5);
		fd_btnRadioConstant_FS.left = new FormAttachment(0, 5);
		btnRadioConstant_FS.setLayoutData(fd_btnRadioConstant_FS);
		btnRadioConstant_FS.setText(TextLabel_UI.btnRadioConstant);
		btnRadioConstant_FS.setSelection(false);
		
		Button btnRadioTable_FS = new Button(group2, SWT.RADIO);
		med.setBtnRadioTable_FS(btnRadioTable_FS);
		CustomButton customBtnRadioTable_FS = new CustomButton(Mediator.BUTTON_RadioTable_FS, med);
		med.setCustomBtnRadioTable_FS(customBtnRadioTable_FS);
		customBtnRadioTable_FS.setCustomWidget_btnRadioTable_FS();
		FormData fd_btnRadioTable_FS = new FormData();
		fd_btnRadioTable_FS.top = new FormAttachment(btnRadioConstant_FS, 0, SWT.TOP);
		fd_btnRadioTable_FS.left = new FormAttachment(btnRadioConstant_FS, 20);
		btnRadioTable_FS.setLayoutData(fd_btnRadioTable_FS);
		btnRadioTable_FS.setText(TextLabel_UI.btnRadioTable);
		btnRadioTable_FS.setSelection(true);
		
		textFlowStress = new Text(compositeMaterialParameter, SWT.BORDER);
		med.setTextFlowStress(textFlowStress);
		FormData fd_textFlowStress = new FormData();
		fd_textFlowStress.top = new FormAttachment(group2, 5);
		fd_textFlowStress.left = new FormAttachment(lblFlowStress,20,SWT.LEFT);
		fd_textFlowStress.right = new FormAttachment(100, -45);
		textFlowStress.setLayoutData(fd_textFlowStress);
		
		Button btnExplorerFlowStress = new Button(compositeMaterialParameter, SWT.NONE);
		med.setBtnExplorerFlowStress(btnExplorerFlowStress);
		CustomButton customBtnExplorerFlowStress = new CustomButton(Mediator.BUTTON_ExplorerFlowStress,med);
		med.setCustomBtnExplorerFlowStress(customBtnExplorerFlowStress);
		customBtnExplorerFlowStress.setCustomWidget_btnExplorerFlowStress();
		FormData fd_btnExplorerFlowStress = new FormData();
		fd_btnExplorerFlowStress.top = new FormAttachment(textFlowStress, -2, SWT.TOP);
		fd_btnExplorerFlowStress.right = new FormAttachment(100, -10);
		btnExplorerFlowStress.setLayoutData(fd_btnExplorerFlowStress);
		btnExplorerFlowStress.setText("...");
		
		//-------------------------------------------------------------------------------------
		
		Label lblYieldStrength = new Label(compositeMaterialParameter, SWT.NONE);
		FormData fd_lblYieldStrength = new FormData();
		fd_lblYieldStrength.top = new FormAttachment(textFlowStress, 12);
		fd_lblYieldStrength.left = new FormAttachment(lblYoungsModulus,20, SWT.LEFT);
		lblYieldStrength.setLayoutData(fd_lblYieldStrength);
		lblYieldStrength.setText(TextLabel_UI.lblYieldStrength);
		
		textYieldStrength = new Text(compositeMaterialParameter, SWT.BORDER);
		med.setTextYieldStrength(textYieldStrength);
		FormData fd_textYieldStrength = new FormData();
		fd_textYieldStrength.top = new FormAttachment(lblYieldStrength, -2,SWT.TOP);
		fd_textYieldStrength.left = new FormAttachment(lblYieldStrength,20,SWT.RIGHT);
		fd_textYieldStrength.right = new FormAttachment(btnExplorerFlowStress, 0,SWT.RIGHT);
		textYieldStrength.setLayoutData(fd_textYieldStrength);
		textYieldStrength.setEnabled(false);
		
		Label lblTensileStrength = new Label(compositeMaterialParameter, SWT.NONE);
		FormData fd_lblTensileStrength = new FormData();
		fd_lblTensileStrength.top = new FormAttachment(lblYieldStrength, 12);
		fd_lblTensileStrength.left = new FormAttachment(lblYoungsModulus, 20, SWT.LEFT);
		lblTensileStrength.setLayoutData(fd_lblTensileStrength);
		lblTensileStrength.setText(TextLabel_UI.lblTensileStrength);
		
		textTensileStrength = new Text(compositeMaterialParameter, SWT.BORDER);
		med.setTextTensileStrength(textTensileStrength);
		FormData fd_tensileStrength = new FormData();
		fd_tensileStrength.top = new FormAttachment(lblTensileStrength,-2, SWT.TOP);
		fd_tensileStrength.left = new FormAttachment(textYieldStrength,0,SWT.LEFT);
		fd_tensileStrength.right = new FormAttachment(textYieldStrength, 0,SWT.RIGHT);
		textTensileStrength.setLayoutData(fd_tensileStrength);
		textTensileStrength.setEnabled(false);
		
		Label lblElongation = new Label(compositeMaterialParameter, SWT.NONE);
		FormData fd_lblElongation = new FormData();
		fd_lblElongation.top = new FormAttachment(lblTensileStrength, 12);
		fd_lblElongation.left = new FormAttachment(lblYoungsModulus, 20, SWT.LEFT);
		lblElongation.setLayoutData(fd_lblElongation);
		lblElongation.setText(TextLabel_UI.lblElongation);
		
		textElongation = new Text(compositeMaterialParameter, SWT.BORDER);
		med.setTextElongation(textElongation);
		FormData fd_textElongation = new FormData();
		fd_textElongation.top = new FormAttachment(lblElongation,-2,SWT.TOP);
		fd_textElongation.left = new FormAttachment(textYieldStrength, 0, SWT.LEFT);
		fd_textElongation.right = new FormAttachment(textYieldStrength, 0, SWT.RIGHT);
		textElongation.setLayoutData(fd_textElongation);
		textElongation.setEnabled(false);
		//-------------------------------------------------------------------------------------
		
		
		Label lblThermalExpansionCoefficient = new Label(compositeMaterialParameter, SWT.NONE);
		FormData fd_lblThermalExpansionCoefficient = new FormData();
		fd_lblThermalExpansionCoefficient.top = new FormAttachment(lblElongation, 12);
		fd_lblThermalExpansionCoefficient.left = new FormAttachment(lblYoungsModulus, 0, SWT.LEFT);
		lblThermalExpansionCoefficient.setLayoutData(fd_lblThermalExpansionCoefficient);
		lblThermalExpansionCoefficient.setText(TextLabel_UI.lblThermalExpansionCoefficient);
		
		Composite group3 = new Composite(compositeMaterialParameter, SWT.NONE);
		group3.setLayout(new FormLayout());
		FormData fd_group3 = new FormData();
		fd_group3.top = new FormAttachment(lblThermalExpansionCoefficient, 2);
		fd_group3.left = new FormAttachment(lblFlowStress, 10,SWT.LEFT);
		group3.setLayoutData(fd_group3);
		
		Button btnRadioConstant_TEC = new Button(group3, SWT.RADIO);
		med.setBtnRadioConstant_TEC(btnRadioConstant_TEC);
		CustomButton customBtnRadioConstant_TEC = new CustomButton(Mediator.BUTTON_RadioConstant_TEC,med);
		med.setCustomBtnRadioConstant_TEC(customBtnRadioConstant_TEC);
		customBtnRadioConstant_TEC.setCustomWidget_btnRadioConstant_TEC();
		FormData fd_btnRadioConstant_TEC = new FormData();
		fd_btnRadioConstant_TEC.top = new FormAttachment(0, 5);
		fd_btnRadioConstant_TEC.left = new FormAttachment(0, 5);
		btnRadioConstant_TEC.setLayoutData(fd_btnRadioConstant_TEC);
		btnRadioConstant_TEC.setText(TextLabel_UI.btnRadioConstant);
		btnRadioConstant_TEC.setSelection(false);
		
		Button btnRadioTable_TEC = new Button(group3, SWT.RADIO);
		med.setBtnRadioTable_TEC(btnRadioTable_TEC);
		CustomButton customBtnRadioTable_TEC = new CustomButton(Mediator.BUTTON_RadioTable_TEC, med);
		med.setCustomBtnRadioTable_TEC(customBtnRadioTable_TEC);
		customBtnRadioTable_TEC.setCustomWidget_btnRadioTable_TEC();
		FormData fd_btnRadioTable_TEC = new FormData();
		fd_btnRadioTable_TEC.top = new FormAttachment(btnRadioConstant_TEC, 0, SWT.TOP);
		fd_btnRadioTable_TEC.left = new FormAttachment(btnRadioConstant_TEC, 20);
		btnRadioTable_TEC.setLayoutData(fd_btnRadioTable_TEC);
		btnRadioTable_TEC.setText(TextLabel_UI.btnRadioTable);
		btnRadioTable_TEC.setSelection(true);
		
		textThermalExpansionCoefficient = new Text(compositeMaterialParameter, SWT.BORDER);
		med.setTextThermalExpansionCoefficient(textThermalExpansionCoefficient);
		FormData fd_textThermalExpansionCoefficient = new FormData();
		fd_textThermalExpansionCoefficient.top = new FormAttachment(group3, 5);
		fd_textThermalExpansionCoefficient.left = new FormAttachment(lblThermalExpansionCoefficient,20,SWT.LEFT);
		fd_textThermalExpansionCoefficient.right = new FormAttachment(100, -45);
		textThermalExpansionCoefficient.setLayoutData(fd_textThermalExpansionCoefficient);
		
		Button btnExplorerThermalExpansionCoefficient = new Button(compositeMaterialParameter, SWT.NONE);
		med.setBtnExplorerThermalExpansionCoefficient(btnExplorerThermalExpansionCoefficient);
		CustomButton customBtnExplorerThermalExpansionCoefficient = new CustomButton(Mediator.BUTTON_ExplorerThermalExpansionCoefficient, med);
		med.setCustomBtnExplorerThermalExpansionCoefficient(customBtnExplorerThermalExpansionCoefficient);
		customBtnExplorerThermalExpansionCoefficient.setCustomWidget_btnExplorerThermalExpansionCoefficient();
		FormData fd_btnExplorerThermalExpansionCoefficient = new FormData();
		fd_btnExplorerThermalExpansionCoefficient.top = new FormAttachment(textThermalExpansionCoefficient, -2, SWT.TOP);
		fd_btnExplorerThermalExpansionCoefficient.right = new FormAttachment(100, -10);
		btnExplorerThermalExpansionCoefficient.setLayoutData(fd_btnExplorerThermalExpansionCoefficient);
		btnExplorerThermalExpansionCoefficient.setText("...");
		
		Label lblPoissonsRatio = new Label(compositeMaterialParameter, SWT.NONE);
		FormData fd_lblPoissonsRatio = new FormData();
		fd_lblPoissonsRatio.top = new FormAttachment(textThermalExpansionCoefficient, 12);
		fd_lblPoissonsRatio.left = new FormAttachment(lblYoungsModulus, 0, SWT.LEFT);
		lblPoissonsRatio.setLayoutData(fd_lblPoissonsRatio);
		lblPoissonsRatio.setText(TextLabel_UI.lblPoissonsRatio);
		
		Composite group4 = new Composite(compositeMaterialParameter, SWT.NONE);
		group4.setLayout(new FormLayout());
		FormData fd_group4 = new FormData();
		fd_group4.top = new FormAttachment(lblPoissonsRatio, 2);
		fd_group4.left = new FormAttachment(lblFlowStress, 10,SWT.LEFT);
		group4.setLayoutData(fd_group4);
		
		Button btnRadioConstant_PR = new Button(group4, SWT.RADIO);
		med.setBtnRadioConstant_PR(btnRadioConstant_PR);
		CustomButton customBtnRadioConstant_PR = new CustomButton(Mediator.BUTTON_RadioConstant_PR,med);
		med.setCustomBtnRadioConstant_PR(customBtnRadioConstant_PR);
		customBtnRadioConstant_PR.setCustomWidget_btnRadioConstant_PR();
		btnRadioConstant_PR.setAlignment(SWT.CENTER);
		FormData fd_btnRadioConstant_PR = new FormData();
		fd_btnRadioConstant_PR.top = new FormAttachment(0, 5);
		fd_btnRadioConstant_PR.left = new FormAttachment(0, 5);
		btnRadioConstant_PR.setLayoutData(fd_btnRadioConstant_PR);
		btnRadioConstant_PR.setText(TextLabel_UI.btnRadioConstant);
		btnRadioConstant_PR.setSelection(false);
		
		Button btnRadioTable_PR = new Button(group4, SWT.RADIO);
		med.setBtnRadioTable_PR(btnRadioTable_PR);
		CustomButton customBtnRadioTable_PR = new CustomButton(Mediator.BUTTON_RadioTable_PR, med);
		med.setCustomBtnRadioTable_PR(customBtnRadioTable_PR);
		customBtnRadioTable_PR.setCustomWidget_btnRadioTable_PR();
		FormData fd_btnRadioTable_PR = new FormData();
		fd_btnRadioTable_PR.top = new FormAttachment(btnRadioConstant_PR, 0, SWT.TOP);
		fd_btnRadioTable_PR.left = new FormAttachment(btnRadioConstant_PR, 20);
		btnRadioTable_PR.setLayoutData(fd_btnRadioTable_PR);
		btnRadioTable_PR.setText(TextLabel_UI.btnRadioTable);
		btnRadioTable_PR.setSelection(true);
		
		textPoissonsRatio = new Text(compositeMaterialParameter, SWT.BORDER);
		med.setTextPoissonsRatio(textPoissonsRatio);
		FormData fd_textPoissonsRatio = new FormData();
		fd_textPoissonsRatio.top = new FormAttachment(group4, 5);
		fd_textPoissonsRatio.left = new FormAttachment(lblPoissonsRatio,20,SWT.LEFT);
		fd_textPoissonsRatio.right = new FormAttachment(100, -45);
		textPoissonsRatio.setLayoutData(fd_textPoissonsRatio);
		
		Button btnExplorerPoissonsRatio = new Button(compositeMaterialParameter, SWT.NONE);
		med.setBtnExplorerPoissonsRatio(btnExplorerPoissonsRatio);
		CustomButton customBtnExplorerPoissonsRatio = new CustomButton(Mediator.BUTTON_ExplorerPoissonsRatio,med);
		med.setCustomBtnExplorerPoissonsRatio(customBtnExplorerPoissonsRatio);
		customBtnExplorerPoissonsRatio.setCustomWidget_btnExplorerPoissonsRatio();
		FormData fd_btnExplorerPoissonsRatio = new FormData();
		fd_btnExplorerPoissonsRatio.top = new FormAttachment(textPoissonsRatio, -2, SWT.TOP);
		fd_btnExplorerPoissonsRatio.right = new FormAttachment(100, -10);
		btnExplorerPoissonsRatio.setLayoutData(fd_btnExplorerPoissonsRatio);
		btnExplorerPoissonsRatio.setText("...");
		
		Label lblMassDensity = new Label(compositeMaterialParameter, SWT.NONE);
		FormData fd_lblMassDensity = new FormData();
		fd_lblMassDensity.top = new FormAttachment(textPoissonsRatio, 12);
		fd_lblMassDensity.left = new FormAttachment(lblYoungsModulus, 0, SWT.LEFT);
		lblMassDensity.setLayoutData(fd_lblMassDensity);
		lblMassDensity.setText(TextLabel_UI.lblMassDensity);
		
		textMassDensity = new Text(compositeMaterialParameter, SWT.BORDER);
		med.setTextMassDensity(textMassDensity);
		FormData fd_textMassDensity = new FormData();
		fd_textMassDensity.top = new FormAttachment(lblMassDensity, -2, SWT.TOP);
		fd_textMassDensity.left = new FormAttachment(lblMassDensity,100,SWT.RIGHT);
		fd_textMassDensity.right = new FormAttachment(100, -10);
		textMassDensity.setLayoutData(fd_textMassDensity);
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		Composite compositeSolvingOption = new Composite(compositeParent3D, SWT.BORDER);
		med.setCompositeSolvingOption(compositeSolvingOption);
		compositeSolvingOption.setLayout(new FormLayout());
		FormData fd_compositeSolvingOption = new FormData();
		fd_compositeSolvingOption.top = new FormAttachment(compositeMaterialParameter, 5);
		fd_compositeSolvingOption.left = new FormAttachment(compositeMaterialParameter, 0, SWT.LEFT);
		fd_compositeSolvingOption.right = new FormAttachment(compositeMaterialParameter, 0, SWT.RIGHT);
		fd_compositeSolvingOption.bottom = new FormAttachment(compositeMaterialParameter, 250,SWT.BOTTOM);
		compositeSolvingOption.setLayoutData(fd_compositeSolvingOption);
		
		Label lblSolvingOption = new Label(compositeSolvingOption, SWT.NONE);
		lblSolvingOption.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		FormData fd_lblSolvingOption = new FormData();
		fd_lblSolvingOption.top = new FormAttachment(0, 10);
		fd_lblSolvingOption.left = new FormAttachment(0, 10);
		lblSolvingOption.setLayoutData(fd_lblSolvingOption);
		lblSolvingOption.setText(TextLabel_UI.lblSolvingOption);
		
		Label lblSolvingTime = new Label(compositeSolvingOption, SWT.NONE);
		FormData fd_lblSolvingTime = new FormData();
		fd_lblSolvingTime.top = new FormAttachment(lblSolvingOption, 12);
		fd_lblSolvingTime.left = new FormAttachment(lblSolvingOption, 10, SWT.LEFT);
		lblSolvingTime.setLayoutData(fd_lblSolvingTime);
		lblSolvingTime.setText(TextLabel_UI.lblSolvingTime);
		
		textSolvingTime = new Text(compositeSolvingOption, SWT.BORDER);
		med.setTextSolvingTime(textSolvingTime);
		FormData fd_textSolvingTime = new FormData();
		fd_textSolvingTime.top = new FormAttachment(lblSolvingTime, -2, SWT.TOP);
		fd_textSolvingTime.left = new FormAttachment(lblSolvingTime,160,SWT.RIGHT);
		fd_textSolvingTime.right = new FormAttachment(100, -10);
		textSolvingTime.setLayoutData(fd_textSolvingTime);
		
		Label lblIncrementTime = new Label(compositeSolvingOption, SWT.NONE);
		FormData fd_lblIncrementTime = new FormData();
		fd_lblIncrementTime.top = new FormAttachment(lblSolvingTime, 12);
		fd_lblIncrementTime.left = new FormAttachment(lblSolvingTime, 0, SWT.LEFT);
		lblIncrementTime.setLayoutData(fd_lblIncrementTime);
		lblIncrementTime.setText(TextLabel_UI.lblIncrementTime);
		
		textIncrementTime = new Text(compositeSolvingOption, SWT.BORDER);
		med.setTextIncrementTime(textIncrementTime);
		FormData fd_textIncrementTime = new FormData();
		fd_textIncrementTime.top = new FormAttachment(lblIncrementTime, -2, SWT.TOP);
		fd_textIncrementTime.left = new FormAttachment(textSolvingTime,0,SWT.LEFT);
		fd_textIncrementTime.right = new FormAttachment(textSolvingTime,0,SWT.RIGHT);
		textIncrementTime.setLayoutData(fd_textIncrementTime);
		
		Label lblParallelDDM = new Label(compositeSolvingOption, SWT.NONE);
		FormData fd_lblParallelDDM = new FormData();
		fd_lblParallelDDM.top = new FormAttachment(lblIncrementTime, 12);
		fd_lblParallelDDM.left = new FormAttachment(lblSolvingTime, 0, SWT.LEFT);
		lblParallelDDM.setLayoutData(fd_lblParallelDDM);
		lblParallelDDM.setText(TextLabel_UI.lblParallelDDM);
		
		Button btnParallelDDMUse = new Button(compositeSolvingOption, SWT.CHECK);
		med.setBtnParallelDDMUse(btnParallelDDMUse);
		CustomButton customBtnParallelDDMUse = new CustomButton(Mediator.BUTTON_ParallelDDMUse,med);
		med.setCustomBtnParallelDDMUse(customBtnParallelDDMUse);
		customBtnParallelDDMUse.setCustomWidget_btnParallelDDMUse();
		FormData fd_btnParallelDDMUse = new FormData();
		fd_btnParallelDDMUse.top = new FormAttachment(lblParallelDDM, -2, SWT.TOP);
		fd_btnParallelDDMUse.left = new FormAttachment(textSolvingTime, 0, SWT.LEFT);
		btnParallelDDMUse.setLayoutData(fd_btnParallelDDMUse);
		btnParallelDDMUse.setText(TextLabel_UI.btnParallelDDMUse);
		
		Label lblDomain = new Label(compositeSolvingOption, SWT.NONE);
		FormData fd_lblDomain = new FormData();
		fd_lblDomain.top = new FormAttachment(lblParallelDDM, 12);
		fd_lblDomain.left = new FormAttachment(lblSolvingTime, 0, SWT.LEFT);
		lblDomain.setLayoutData(fd_lblDomain);
		lblDomain.setText(TextLabel_UI.lblDomain);
		
		Spinner spinnerDomain = new Spinner(compositeSolvingOption, SWT.BORDER);
		med.setSpinnerDomain(spinnerDomain);
		CustomSpinner customSpinnerDomain = new CustomSpinner(Mediator.SPINNER_Domain,med);
		med.setCustomSpinnerDomain(customSpinnerDomain);
		customSpinnerDomain.setCustomWidget_spinnerDomain();
		FormData fd_spinnerDomain = new FormData();
		fd_spinnerDomain.top = new FormAttachment(lblDomain,-2,SWT.TOP);
		fd_spinnerDomain.left = new FormAttachment(textSolvingTime, 0, SWT.LEFT);
		fd_spinnerDomain.right = new FormAttachment(textSolvingTime, 0, SWT.RIGHT);
		spinnerDomain.setLayoutData(fd_spinnerDomain);
		spinnerDomain.setEnabled(false);
		
		//-------------------------------------------
		Label lblParallelMultiThread = new Label(compositeSolvingOption, SWT.NONE);
		FormData fd_lblParallelMultiThread = new FormData();
		fd_lblParallelMultiThread.top = new FormAttachment(lblDomain, 12);
		fd_lblParallelMultiThread.left = new FormAttachment(lblSolvingTime, 0, SWT.LEFT);
		lblParallelMultiThread.setLayoutData(fd_lblParallelMultiThread);
		lblParallelMultiThread.setText(TextLabel_UI.lblParallelMultiThread);
		
		Button btnParallelMultiThreadUse = new Button(compositeSolvingOption, SWT.CHECK);
		med.setBtnParallelMultiThreadUse(btnParallelMultiThreadUse);
		CustomButton customBtnParallelMultiThreadUse = new CustomButton(Mediator.BUTTON_ParallelMultiThreadUse,med);
		med.setCustomBtnParallelMultiThreadUse(customBtnParallelMultiThreadUse);
		customBtnParallelMultiThreadUse.setCustomWidget_btnParallelMultiThreadUse();
		
		FormData fd_btnParallelMultiThreadUse = new FormData();
		fd_btnParallelMultiThreadUse.top = new FormAttachment(lblParallelMultiThread, -2, SWT.TOP);
		fd_btnParallelMultiThreadUse.left = new FormAttachment(textSolvingTime, 0, SWT.LEFT);
		btnParallelMultiThreadUse.setLayoutData(fd_btnParallelMultiThreadUse);
		btnParallelMultiThreadUse.setText(TextLabel_UI.btnParallelMultiThreadUse);
		
		Label lblThread = new Label(compositeSolvingOption, SWT.NONE);
		FormData fd_lblThread = new FormData();
		fd_lblThread.top = new FormAttachment(lblParallelMultiThread, 12);
		fd_lblThread.left = new FormAttachment(lblSolvingTime, 0, SWT.LEFT);
		lblThread.setLayoutData(fd_lblThread);
		lblThread.setText(TextLabel_UI.lblThread);
		
		Spinner spinnerThread = new Spinner(compositeSolvingOption, SWT.BORDER);
		med.setSpinnerThread(spinnerThread);
		CustomSpinner customSpinnerThread = new CustomSpinner(Mediator.SPINNER_Thread,med);
		med.setCustomSpinnerThread(customSpinnerThread);
		customSpinnerThread.setCustomWidget_spinnerDomain();
		FormData fd_spinnerThread = new FormData();
		fd_spinnerThread.top = new FormAttachment(lblThread,-2,SWT.TOP);
		fd_spinnerThread.left = new FormAttachment(textSolvingTime, 0, SWT.LEFT);
		fd_spinnerThread.right = new FormAttachment(textSolvingTime, 0, SWT.RIGHT);
		spinnerThread.setLayoutData(fd_spinnerThread);
		spinnerThread.setEnabled(false);
		
		
		//--------------------------------------------
		
		Button btnApply = new Button(compositeParent3D, SWT.NONE);
		med.setBtnApply(btnApply);
		CustomButton customBtnApply = new CustomButton(Mediator.BUTTON_Apply,med);
		med.setCustomBtnApply(customBtnApply);
		customBtnApply.setCustomWidget_btnApply();
		FormData fd_btnApply = new FormData();
		fd_btnApply.top = new FormAttachment(compositeSolvingOption, 5);
		fd_btnApply.left = new FormAttachment(compositeRollParameter, 255);
		fd_btnApply.right = new FormAttachment(compositeSolvingOption, 0, SWT.RIGHT);
		fd_btnApply.bottom = new FormAttachment(compositeRollParameter, 0, SWT.BOTTOM);
		btnApply.setLayoutData(fd_btnApply);
		btnApply.setText("APPLY");
			
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		init_ActionComponent();
		init_AllComponent();
		
		// */
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		
	}
	
	public void init_ActionComponent(){
		//TabFolder
		HandlerTabFolder handlerTabFolder = new HandlerTabFolder();
		med.getTabFolder().addListener(SWT.Selection, handlerTabFolder);
		
		// Button
		// 3D
		HandlerButton handlerButton = new HandlerButton();
		med.getBtnCalcElementNum().addListener(SWT.Selection, handlerButton);
		//med.getBtnRadioNone_RC().addListener(SWT.Selection, handlerButton);
		//med.getBtnRadioApply_RC().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioRigid_MS().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioSpring_MS().addListener(SWT.Selection, handlerButton);
		med.getBtnNone().addListener(SWT.Selection, handlerButton);
		med.getBtnUpper().addListener(SWT.Selection, handlerButton);
		med.getBtnLower().addListener(SWT.Selection, handlerButton);
		med.getBtnCreateRoll().addListener(SWT.Selection, handlerButton);
		med.getBtnShowRollTable().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorerYoungsModulus().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorerFlowStress().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorerThermalExpansionCoefficient().addListener(SWT.Selection, handlerButton);
		med.getBtnApply().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioConstant_YM().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioTable_YM().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioConstant_FS().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioTable_FS().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioConstant_TEC().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioTable_TEC().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioConstant_PR().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioTable_PR().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorerPoissonsRatio().addListener(SWT.Selection, handlerButton);
		med.getBtnParallelDDMUse().addListener(SWT.Selection, handlerButton);
		med.getBtnParallelMultiThreadUse().addListener(SWT.Selection, handlerButton);
		
		//HandlerButton handlerButton = new HandlerButton();
		med.getBtnCalcElementNum_2D().addListener(SWT.Selection, handlerButton);
		//med.getBtnRadioNone_RC_2D().addListener(SWT.Selection, handlerButton);
		//med.getBtnRadioApply_RC_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioRigid_MS_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioSpring_MS_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnNone_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnUpper_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnLower_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnCreateRoll_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnShowRollTable_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorerYoungsModulus_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorerFlowStress_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorerThermalExpansionCoefficient_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnApply_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioConstant_YM_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioTable_YM_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioConstant_FS_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioTable_FS_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioConstant_TEC_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioTable_TEC_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioConstant_PR_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioTable_PR_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorerPoissonsRatio_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnParallelDDMUse_2D().addListener(SWT.Selection, handlerButton);
		med.getBtnParallelMultiThreadUse_2D().addListener(SWT.Selection, handlerButton);
		
		
		
		
		HandlerCombo handlerCombo = new HandlerCombo();
		med.getComboType().addListener(SWT.Selection, handlerCombo);
		med.getComboType_2D().addListener(SWT.Selection, handlerCombo);
		
		HandlerSpinner handlerSpinner = new HandlerSpinner();
		med.getSpinnerUpperRollNum().addListener(SWT.Selection, handlerSpinner);
		med.getSpinnerLowerRollNum().addListener(SWT.Selection, handlerSpinner);
		med.getSpinnerDomain().addListener(SWT.Selection, handlerSpinner);
		med.getSpinnerThread().addListener(SWT.Selection, handlerSpinner);
		
		med.getSpinnerUpperRollNum_2D().addListener(SWT.Selection, handlerSpinner);
		med.getSpinnerLowerRollNum_2D().addListener(SWT.Selection, handlerSpinner);
		med.getSpinnerDomain_2D().addListener(SWT.Selection, handlerSpinner);
		med.getSpinnerThread_2D().addListener(SWT.Selection, handlerSpinner);
		
		//HandlerTableViewer handlerTableViewer = new HandlerTableViewer();
		//med.getTableViewerUpperRoll().addSelectionChangedListener(handlerTableViewer);
		//med.getTableViewerLowerRoll().addSelectionChangedListener(handlerTableViewer);
		
		HandlerText handlerText = new HandlerText();
		med.getTextLength_2D().addListener(SWT.CHANGED, handlerText);
		med.getTextThickness_2D().addListener(SWT.CHANGED, handlerText);
		med.getType2_textWaveHeight_2D().addListener(SWT.CHANGED, handlerText);
		med.getType3_textFrontCurlHeight_2D().addListener(SWT.CHANGED, handlerText);
		med.getType3_textFrontCurlLength_2D().addListener(SWT.CHANGED, handlerText);
		med.getType3_textRearCurlHeight_2D().addListener(SWT.CHANGED, handlerText);
		med.getType3_textRearCurlLength_2D().addListener(SWT.CHANGED, handlerText);
		
		med.getTextWidth().addListener(SWT.CHANGED, handlerText);
		med.getTextLength().addListener(SWT.CHANGED, handlerText);
		med.getTextThickness().addListener(SWT.CHANGED, handlerText);
		med.getType2_textLeftEdgeWaveHeight().addListener(SWT.CHANGED, handlerText);
		med.getType2_textRightEdgeWaveHeight().addListener(SWT.CHANGED, handlerText);
		med.getType3_textWaveHeight().addListener(SWT.CHANGED, handlerText);
		med.getType4_textGutterHeight().addListener(SWT.CHANGED, handlerText);
		med.getType5_textGutterHeight().addListener(SWT.CHANGED, handlerText);
		med.getType5_textGutterLength().addListener(SWT.CHANGED, handlerText);
		med.getType6_textHeadGutterHeight().addListener(SWT.CHANGED, handlerText);
		med.getType6_textHeadGutterLength().addListener(SWT.CHANGED, handlerText);
		med.getType6_textTailGutterHeight().addListener(SWT.CHANGED, handlerText);
		med.getType6_textTailGutterLength().addListener(SWT.CHANGED, handlerText);
		med.getType7_textHeadGutterHeight().addListener(SWT.CHANGED, handlerText);
		med.getType7_textGutterLength().addListener(SWT.CHANGED, handlerText);
		med.getType7_textGutterLengthLength().addListener(SWT.CHANGED, handlerText);
		med.getType7_textGutterWidthLength().addListener(SWT.CHANGED, handlerText);
		
	}
	
	public void init_AllComponent(){
		LMain.AllComponentDisable();
	}
	
	public void init_TableColunmn(){
		String [] ColumnName = new String[]{  
				TableColumnLabel.COL_0,TableColumnLabel.COL_1,TableColumnLabel.COL_2,TableColumnLabel.COL_3};
		int [] ColumnWidth = new int []{
				100,145,95,95	};
		int [] ColumnAligments = new int []{
				SWT.LEFT,SWT.RIGHT,SWT.RIGHT,SWT.RIGHT,};
		String [] ColumnProperty = {
				TableColumnLabel.COL_0,TableColumnLabel.COL_1,TableColumnLabel.COL_2,TableColumnLabel.COL_3};
		for(int i=0;i<ColumnName.length;i++){
			TableColumn tableColumn_up = new TableColumn(this.tableUpperRoll,ColumnAligments[i]);
			tableColumn_up.setText(ColumnName[i]);
			tableColumn_up.setWidth(ColumnWidth[i]);

			TableColumn tableColumn_down = new TableColumn(this.tableLowerRoll,ColumnAligments[i]);
			tableColumn_down.setText(ColumnName[i]);
			tableColumn_down.setWidth(ColumnWidth[i]);
		}
		//med.tableViewerUp.setColumnProperties(ColumnProperty);
		//med.tableViewerDown.setColumnProperties(ColumnProperty);
		med.getTableViewerUpperRoll().setColumnProperties(ColumnProperty);
		med.getTableViewerLowerRoll().setColumnProperties(ColumnProperty);
	}
	
	public void init_AllComponentValue(){
		LMain.init_AllComponentValue();
	}
}
