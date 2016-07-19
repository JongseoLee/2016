package com.js.ens.leveller;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;
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
import com.js.ens.leveller.customWidget.CustomTableViewer;
import com.js.ens.leveller.handler.HandlerButton;
import com.js.ens.leveller.handler.HandlerCombo;
import com.js.ens.leveller.handler.HandlerSpinner;
import com.js.ens.leveller.handler.HandlerTableViewer;
import com.js.ens.leveller.img.ImagePath;

import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;

public class View extends ViewPart {
	private Mediator med = Mediator.getInstance();
	private LevellerMain LMain = LevellerMain.getInstatnce();
	
	
	public static final String ID = "com.js.ens.leveller.view";
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
	private Text textEntryUpperRollGap;
	private Text textEntryLowerRollGap;
	private Text textExitUpperRollGap;
	private Text textExitLowerRollGap;
	private Text textRollFriction;
	private Text textRollDiameter;
	private Text textRollCrown;
	private Text textMillStiffness;
	private Table tableUpperRoll;
	private Table tableLowerRoll;
	//--------------------------------
	//private Text textModulusElasticity;
	//private Text textYieldStrength;
	//private Text textPoissonRatio;
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
		sc.setMinSize(1420, 800);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		
		/*
		sc.addControlListener(new ControlAdapter(){
			public void controlResized(ControlEvent e) {
		        Rectangle r = sc.getClientArea();
		        sc.setMinSize(compositeParent.computeSize(r.width, SWT.DEFAULT));
		        //sc.setMinSize(r.width, r.height);
		    }
		});
		*/
		
		Composite compositeParent = new Composite(sc, SWT.NONE);
		FormLayout fl_compositeParent = new FormLayout();
		fl_compositeParent.spacing = 10;
		fl_compositeParent.marginWidth = 10;
		fl_compositeParent.marginTop = 10;
		fl_compositeParent.marginRight = 10;
		fl_compositeParent.marginLeft = 10;
		fl_compositeParent.marginHeight = 10;
		fl_compositeParent.marginBottom = 10;
		compositeParent.setLayout(fl_compositeParent);
		
		sc.setContent(compositeParent);
		
		Label lblModelName = new Label(compositeParent, SWT.NONE);
		FormData fd_lblModelName = new FormData();
		fd_lblModelName.top = new FormAttachment(0, 10);
		fd_lblModelName.left = new FormAttachment(0, 10);
		lblModelName.setLayoutData(fd_lblModelName);
		lblModelName.setText(TextLabel_UI.lblModelName);
		
		textModelName = new Text(compositeParent, SWT.BORDER);
		med.setTextModelName(textModelName);
		FormData fd_textModelName = new FormData();
		fd_textModelName.top = new FormAttachment(lblModelName, -2,SWT.TOP);
		fd_textModelName.left = new FormAttachment(lblModelName, 30, SWT.RIGHT);
		fd_textModelName.right = new FormAttachment(lblModelName, 150, SWT.RIGHT);
		textModelName.setLayoutData(fd_textModelName);
		textModelName.setEnabled(false);
		
		Label lblWorkspacePath = new Label(compositeParent, SWT.NONE);
		med.setlblworkspacePath(lblWorkspacePath);
		FormData fd_lblWorkspacePath = new FormData();
		fd_lblWorkspacePath.top = new FormAttachment(lblModelName, 0, SWT.TOP);
		fd_lblWorkspacePath.left = new FormAttachment(textModelName, 5);
		fd_lblWorkspacePath.right = new FormAttachment(100,-10);
		lblWorkspacePath.setLayoutData(fd_lblWorkspacePath);
		lblWorkspacePath.setText("Workspace : ");
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		//
		Composite compositeShapeParameter = new Composite(compositeParent, SWT.BORDER);
		med.setCompositeShapeParameter(compositeShapeParameter);
		FormData fd_compositeShapeParameter = new FormData();
		fd_compositeShapeParameter.top = new FormAttachment(lblModelName, 15);
		fd_compositeShapeParameter.left = new FormAttachment(lblModelName, 0,SWT.LEFT);
		fd_compositeShapeParameter.right = new FormAttachment(0,450);
		fd_compositeShapeParameter.bottom = new FormAttachment(lblModelName, 355, SWT.BOTTOM);
		compositeShapeParameter.setLayoutData(fd_compositeShapeParameter);
		compositeShapeParameter.setLayout(new FormLayout());
		
		Label lblShapeParameter = new Label(compositeShapeParameter, SWT.NONE);
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
		
		/* */
		Composite compositeShapeParameterChild_1 = new Composite(compositeShapeParameterChild, SWT.NONE);
		med.setCompositeShapeParameterChild_1(compositeShapeParameterChild_1);
		compositeShapeParameterChild_1.setBounds(0, 0, 400, 130);
		//*/
		
		/* */
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
		type2_textLeftEdgeWaveHeight.setBounds(250, 62, 120, 24);
		
		Label lblRightEdgeWaveHeight_type2 = new Label(compositeShapeParameterChild_2, SWT.NONE);
		lblRightEdgeWaveHeight_type2.setBounds(10, 91, 200, 20);
		lblRightEdgeWaveHeight_type2.setText(TextLabel_UI.lblRightEdgeWaveHeight_type2);
		
		type2_textRightEdgeWaveHeight = new Text(compositeShapeParameterChild_2, SWT.BORDER);
		med.setType2_textRightEdgeWaveHeight(type2_textRightEdgeWaveHeight);
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
		
		//*/
		/* */
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
		type3_textWaveHeight.setBounds(210, 35, 120, 24);
		//*/
		/* */
		// Type4
		Composite compositeShapeParameterChild_4 = new Composite(compositeShapeParameterChild, SWT.NONE);
		med.setCompositeShapeParameterChild_4(compositeShapeParameterChild_4);
		compositeShapeParameterChild_4.setBounds(0, 0, 400, 130);
		
		Label lblGutterHeight_type4 = new Label(compositeShapeParameterChild_4, SWT.NONE);
		lblGutterHeight_type4.setBounds(10, 10, 170, 20);
		lblGutterHeight_type4.setText(TextLabel_UI.lblGutterHeight_type4);

		type4_textGutterHeight = new Text(compositeShapeParameterChild_4, SWT.BORDER);
		med.setType4_textGutterHeight(type4_textGutterHeight);
		type4_textGutterHeight.setBounds(210, 8, 120, 24);
		//*/
		/* */
		// Type5
		Composite compositeShapeParameterChild_5 = new Composite(compositeShapeParameterChild, SWT.NONE);
		med.setCompositeShapeParameterChild_5(compositeShapeParameterChild_5);
		compositeShapeParameterChild_5.setBounds(0, 0, 400, 130);
		
		Label lblGutterHeight_type5 = new Label(compositeShapeParameterChild_5, SWT.NONE);
		lblGutterHeight_type5.setBounds(10, 10, 170, 20);
		lblGutterHeight_type5.setText(TextLabel_UI.lblGutterHeight_type5);
		
		type5_textGutterHeight = new Text(compositeShapeParameterChild_5, SWT.BORDER);
		med.setType5_textGutterHeight(type5_textGutterHeight);
		type5_textGutterHeight.setBounds(210, 8, 120, 24);
		
		Label lblGutterLength_type5 = new Label(compositeShapeParameterChild_5, SWT.NONE);
		lblGutterLength_type5.setBounds(10, 37, 170, 20);
		lblGutterLength_type5.setText(TextLabel_UI.lblGutterLength_type5);
		
		type5_textGutterLength = new Text(compositeShapeParameterChild_5, SWT.BORDER);
		med.setType5_textGutterLength(type5_textGutterLength);
		type5_textGutterLength.setBounds(210, 35, 120, 24);
		//*/
		/* */
		// Type6
		Composite compositeShapeParameterChild_6 = new Composite(compositeShapeParameterChild, SWT.NONE);
		med.setCompositeShapeParameterChild_6(compositeShapeParameterChild_6);
		compositeShapeParameterChild_6.setBounds(0, 0, 400, 130);
		
		Label lblHeadGutterHeight_type6 = new Label(compositeShapeParameterChild_6, SWT.NONE);
		lblHeadGutterHeight_type6.setBounds(10, 10, 200, 20);
		lblHeadGutterHeight_type6.setText(TextLabel_UI.lblHeadGutterHeight_type6);
		
		type6_textHeadGutterHeight = new Text(compositeShapeParameterChild_6, SWT.BORDER);
		med.setType6_textHeadGutterHeight(type6_textHeadGutterHeight);
		type6_textHeadGutterHeight.setBounds(250, 8, 120, 24);
		
		Label lblHeadGutterLength_type6 = new Label(compositeShapeParameterChild_6, SWT.NONE);
		lblHeadGutterLength_type6.setBounds(10, 37, 200, 20);
		lblHeadGutterLength_type6.setText(TextLabel_UI.lblHeadGutterLength_type6);
		
		type6_textHeadGutterLength = new Text(compositeShapeParameterChild_6, SWT.BORDER);
		med.setType6_textHeadGutterLength(type6_textHeadGutterLength);
		type6_textHeadGutterLength.setBounds(250, 35, 120, 24);
		
		Label lblTailGutterHeight_type6 = new Label(compositeShapeParameterChild_6, SWT.NONE);
		lblTailGutterHeight_type6.setBounds(10, 64, 200, 20);
		lblTailGutterHeight_type6.setText(TextLabel_UI.lblTailGutterHeight_type6);
		
		type6_textTailGutterHeight = new Text(compositeShapeParameterChild_6, SWT.BORDER);
		med.setType6_textTailGutterHeight(type6_textTailGutterHeight);
		type6_textTailGutterHeight.setBounds(250, 62, 120, 24);
		
		Label lblTailGutterLength_type6 = new Label(compositeShapeParameterChild_6, SWT.NONE);
		lblTailGutterLength_type6.setBounds(10, 91, 200, 20);
		lblTailGutterLength_type6.setText(TextLabel_UI.lblTailGutterLength_type6);
		
		type6_textTailGutterLength = new Text(compositeShapeParameterChild_6, SWT.BORDER);
		med.setType6_textTailGutterLength(type6_textTailGutterLength);
		type6_textTailGutterLength.setBounds(250, 89, 120, 24);
		
		//*/
		/* */
		// Type7
		Composite compositeShapeParameterChild_7 = new Composite(compositeShapeParameterChild, SWT.NONE);
		med.setCompositeShapeParameterChild_7(compositeShapeParameterChild_7);
		compositeShapeParameterChild_7.setBounds(0, 0, 400, 130);
		
		Label lblHeadGutterHeight_type7 = new Label(compositeShapeParameterChild_7, SWT.NONE);
		lblHeadGutterHeight_type7.setBounds(10, 10, 250, 20);
		lblHeadGutterHeight_type7.setText(TextLabel_UI.lblHeadGutterHeight_type7);
		
		type7_textHeadGutterHeight = new Text(compositeShapeParameterChild_7, SWT.BORDER);
		med.setType7_textHeadGutterHeight(type7_textHeadGutterHeight);
		type7_textHeadGutterHeight.setBounds(260, 8, 120, 24);
		
		Label lblGutterLength_type7 = new Label(compositeShapeParameterChild_7, SWT.NONE);
		lblGutterLength_type7.setBounds(10, 37, 250, 20);
		lblGutterLength_type7.setText(TextLabel_UI.lblGutterLength_type7);
		
		type7_textGutterLength = new Text(compositeShapeParameterChild_7, SWT.BORDER);
		med.setType7_textGutterLength(type7_textGutterLength);
		type7_textGutterLength.setBounds(260, 35, 120, 24);
		
		Label lblGutterLengthLength_type7 = new Label(compositeShapeParameterChild_7, SWT.NONE);
		lblGutterLengthLength_type7.setBounds(10, 64, 250, 20);
		lblGutterLengthLength_type7.setText(TextLabel_UI.lblGutterLengthLength_type7);
		
		type7_textGutterLengthLength = new Text(compositeShapeParameterChild_7, SWT.BORDER);
		med.setType7_textGutterLengthLength(type7_textGutterLengthLength);
		type7_textGutterLengthLength.setBounds(260, 62, 120, 24);
		
		Label lblGutterWidthLength_type7 = new Label(compositeShapeParameterChild_7, SWT.NONE);
		lblGutterWidthLength_type7.setBounds(10, 91, 250, 20);
		lblGutterWidthLength_type7.setText(TextLabel_UI.lblGutterWidthLength_type7);
		
		type7_textGutterWidthLength = new Text(compositeShapeParameterChild_7, SWT.BORDER);
		med.setType7_textGutterWidthLength(type7_textGutterWidthLength);
		type7_textGutterWidthLength.setBounds(260, 89, 120, 24);
		
		//*/
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
		Composite compositeMeshParameter = new Composite(compositeParent, SWT.BORDER);
		med.setCompositeMeshParameter(compositeMeshParameter);
		compositeMeshParameter.setLayout(new FormLayout());
		FormData fd_compositeMeshParameter = new FormData();
		fd_compositeMeshParameter.top = new FormAttachment(compositeShapeParameter, 30);
		fd_compositeMeshParameter.left = new FormAttachment(compositeShapeParameter, 0, SWT.LEFT);
		fd_compositeMeshParameter.right = new FormAttachment(compositeShapeParameter, 0, SWT.RIGHT);
		fd_compositeMeshParameter.bottom = new FormAttachment(compositeShapeParameter, 255, SWT.BOTTOM);
		compositeMeshParameter.setLayoutData(fd_compositeMeshParameter);
		
		Label lblMeshParameter = new Label(compositeMeshParameter, SWT.NONE);
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
		
		Composite compositePlateInformation = new Composite(compositeParent, SWT.BORDER);
		med.setCompositePlateInformation(compositePlateInformation);
		compositePlateInformation.setLayout(new FormLayout());
		FormData fd_compositePlateInformation = new FormData();
		fd_compositePlateInformation.top = new FormAttachment(compositeMeshParameter,30);
		fd_compositePlateInformation.left = new FormAttachment(compositeShapeParameter, 0, SWT.LEFT);
		fd_compositePlateInformation.right = new FormAttachment(compositeShapeParameter, 0, SWT.RIGHT);
		fd_compositePlateInformation.bottom = new FormAttachment(compositeMeshParameter,225,SWT.BOTTOM);
		compositePlateInformation.setLayoutData(fd_compositePlateInformation);
		
		Label lblPlateInformation = new Label(compositePlateInformation, SWT.NONE);
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
		Composite compositeRollParameter = new Composite(compositeParent, SWT.BORDER);
		med.setCompositeRollParameter(compositeRollParameter);
		compositeRollParameter.setLayout(new FormLayout());
		FormData fd_compositeRollParameter = new FormData();
		fd_compositeRollParameter.top = new FormAttachment(compositeShapeParameter, 0, SWT.TOP);
		fd_compositeRollParameter.left = new FormAttachment(compositeShapeParameter, 10);
		fd_compositeRollParameter.right = new FormAttachment(compositeShapeParameter,450,SWT.RIGHT);
		fd_compositeRollParameter.bottom = new FormAttachment(compositePlateInformation,0,SWT.BOTTOM);
		compositeRollParameter.setLayoutData(fd_compositeRollParameter);
		
		ScrolledComposite sc_RollParam = new ScrolledComposite(compositeRollParameter, SWT.H_SCROLL|SWT.V_SCROLL | SWT.NONE);
		sc_RollParam.setLayout(new FillLayout());
		sc_RollParam.setMinSize(300, 300);
		sc_RollParam.setExpandHorizontal(true);
		sc_RollParam.setExpandVertical(true);
		
		Composite compositeRollParameter_in = new Composite(sc_RollParam, SWT.NONE);
		compositeRollParameter_in.setLayout(new FormLayout());
		FormData fd_compositeRollParameter_in = new FormData();
		fd_compositeRollParameter_in.top = new FormAttachment(0,0);
		fd_compositeRollParameter_in.left = new FormAttachment(0,0);
		fd_compositeRollParameter_in.right = new FormAttachment(100,0);
		fd_compositeRollParameter_in.bottom = new FormAttachment(100,0);
		compositeRollParameter_in.setLayoutData(fd_compositeRollParameter_in);
		
		sc_RollParam.setContent(compositeRollParameter_in);
		/*
		compositeRollParameter_scroll.setMinSize(100,100);
		compositeRollParameter_scroll.setExpandHorizontal(true);
		compositeRollParameter_scroll.setExpandVertical(true);
		
		compositeRollParameter_scroll.setContent(compositeRollParameter);
		*/
		Label lblRollParameter = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblRollParameter = new FormData();
		fd_lblRollParameter.top = new FormAttachment(0, 10);
		fd_lblRollParameter.left = new FormAttachment(0, 10);
		lblRollParameter.setLayoutData(fd_lblRollParameter);
		lblRollParameter.setText(TextLabel_UI.lblRollParameter);
		
		Label lblUpperRollNumber = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblUpperRollNumber = new FormData();
		fd_lblUpperRollNumber.top = new FormAttachment(lblRollParameter, 12);
		fd_lblUpperRollNumber.left = new FormAttachment(lblRollParameter, 10, SWT.LEFT);
		lblUpperRollNumber.setLayoutData(fd_lblUpperRollNumber);
		lblUpperRollNumber.setText(TextLabel_UI.lblUpperRollNumber);
		
		Spinner spinnerUpperRollNum = new Spinner(compositeRollParameter_in, SWT.BORDER);
		med.setSpinnerUpperRollNum(spinnerUpperRollNum);
		CustomSpinner customSpinnerUpperRollNum = new CustomSpinner(Mediator.SPINNER_UpperRollNum,med);
		med.setCustomSpinnerUpperRollNum(customSpinnerUpperRollNum);
		customSpinnerUpperRollNum.setCustomWidget_spinnerUpperRollNum();
		FormData fd_spinnerUpperRollNum = new FormData();
		fd_spinnerUpperRollNum.top = new FormAttachment(lblUpperRollNumber, -2, SWT.TOP);
		fd_spinnerUpperRollNum.left = new FormAttachment(lblUpperRollNumber, 150);
		fd_spinnerUpperRollNum.right = new FormAttachment(100,-10);
		spinnerUpperRollNum.setLayoutData(fd_spinnerUpperRollNum);
		
		Label lblLowerRollNumber = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblLowerRollNumber = new FormData();
		fd_lblLowerRollNumber.top = new FormAttachment(lblUpperRollNumber, 12);
		fd_lblLowerRollNumber.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblLowerRollNumber.setLayoutData(fd_lblLowerRollNumber);
		lblLowerRollNumber.setText(TextLabel_UI.lblLowerRollNumber);
		
		Spinner spinnerLowerRollNum = new Spinner(compositeRollParameter_in, SWT.BORDER);
		med.setSpinnerLowerRollNum(spinnerLowerRollNum);
		CustomSpinner customSpinnerLowerRollNum = new CustomSpinner(Mediator.SPINNER_LowerRollNum,med);
		med.setCustomSpinnerLowerRollNum(customSpinnerLowerRollNum);
		customSpinnerLowerRollNum.setCustomWidget_spinnerLowerRollNum();
		FormData fd_spinnerLowerRollNum = new FormData();
		fd_spinnerLowerRollNum.top = new FormAttachment(lblLowerRollNumber, -2, SWT.TOP);
		fd_spinnerLowerRollNum.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_spinnerLowerRollNum.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		spinnerLowerRollNum.setLayoutData(fd_spinnerLowerRollNum);
		
		Label lblRollPitch = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblRollPitch = new FormData();
		fd_lblRollPitch.top = new FormAttachment(lblLowerRollNumber, 12);
		fd_lblRollPitch.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblRollPitch.setLayoutData(fd_lblRollPitch);
		lblRollPitch.setText(TextLabel_UI.lblRollPitch);
		
		textRollPitch = new Text(compositeRollParameter_in, SWT.BORDER);
		med.setTextRollPitch(textRollPitch);
		FormData fd_textRollPitch = new FormData();
		fd_textRollPitch.top = new FormAttachment(lblRollPitch, -2, SWT.TOP);
		fd_textRollPitch.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textRollPitch.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textRollPitch.setLayoutData(fd_textRollPitch);
		
		Label lblRollLength = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblRollLength = new FormData();
		fd_lblRollLength.top = new FormAttachment(lblRollPitch, 12);
		fd_lblRollLength.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblRollLength.setLayoutData(fd_lblRollLength);
		lblRollLength.setText(TextLabel_UI.lblRollLength);
		
		textRollLength = new Text(compositeRollParameter_in, SWT.BORDER);
		med.setTextRollLength(textRollLength);
		FormData fd_textRollLength = new FormData();
		fd_textRollLength.top = new FormAttachment(lblRollLength, -2, SWT.TOP);
		fd_textRollLength.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textRollLength.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textRollLength.setLayoutData(fd_textRollLength);
		textRollLength.setEnabled(false);
		
		Label lblEntryUpperRollGap = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblEntryUpperRollGap = new FormData();
		fd_lblEntryUpperRollGap.top = new FormAttachment(lblRollLength, 12);
		fd_lblEntryUpperRollGap.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblEntryUpperRollGap.setLayoutData(fd_lblEntryUpperRollGap);
		lblEntryUpperRollGap.setText(TextLabel_UI.lblEntryUpperRollGap);
		
		textEntryUpperRollGap = new Text(compositeRollParameter_in, SWT.BORDER);
		med.setTextEntryUpperRollGap(textEntryUpperRollGap);
		FormData fd_textEntryUpperRollGap = new FormData();
		fd_textEntryUpperRollGap.top = new FormAttachment(lblEntryUpperRollGap, -2, SWT.TOP);
		fd_textEntryUpperRollGap.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textEntryUpperRollGap.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textEntryUpperRollGap.setLayoutData(fd_textEntryUpperRollGap);
		
		Label lblExitUpperRollGap = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblExitUpperRollGap = new FormData();
		fd_lblExitUpperRollGap.top = new FormAttachment(lblEntryUpperRollGap, 12);
		fd_lblExitUpperRollGap.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblExitUpperRollGap.setLayoutData(fd_lblExitUpperRollGap);
		lblExitUpperRollGap.setText(TextLabel_UI.lblExitUpperRollGap);
		
		textExitUpperRollGap = new Text(compositeRollParameter_in, SWT.BORDER);
		med.setTextExitUpperRollGap(textExitUpperRollGap);
		FormData fd_textExitUpperRollGap = new FormData();
		fd_textExitUpperRollGap.top = new FormAttachment(lblExitUpperRollGap, -2, SWT.TOP);
		fd_textExitUpperRollGap.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textExitUpperRollGap.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textExitUpperRollGap.setLayoutData(fd_textExitUpperRollGap);
		
		Label lblEntryLowerRollGap = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblEntryLowerRollGap = new FormData();
		fd_lblEntryLowerRollGap.top = new FormAttachment(lblExitUpperRollGap, 12);
		fd_lblEntryLowerRollGap.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblEntryLowerRollGap.setLayoutData(fd_lblEntryLowerRollGap);
		lblEntryLowerRollGap.setText(TextLabel_UI.lblEntryLowerRollGap);
		
		textEntryLowerRollGap = new Text(compositeRollParameter_in, SWT.BORDER);
		med.setTextEntryLowerRollGap(textEntryLowerRollGap);
		FormData fd_textEntryLowerRollGap = new FormData();
		fd_textEntryLowerRollGap.top = new FormAttachment(lblEntryLowerRollGap, -2, SWT.TOP);
		fd_textEntryLowerRollGap.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textEntryLowerRollGap.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textEntryLowerRollGap.setLayoutData(fd_textEntryLowerRollGap);
		
		Label lblExitLowerRollGap = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblExitLowerRollGap = new FormData();
		fd_lblExitLowerRollGap.top = new FormAttachment(lblEntryLowerRollGap, 12);
		fd_lblExitLowerRollGap.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblExitLowerRollGap.setLayoutData(fd_lblExitLowerRollGap);
		lblExitLowerRollGap.setText(TextLabel_UI.lblExitLowerRollGap);
		
		textExitLowerRollGap = new Text(compositeRollParameter_in, SWT.BORDER);
		med.setTextExitLowerRollGap(textExitLowerRollGap);
		FormData fd_textExitLowerRollGap = new FormData();
		fd_textExitLowerRollGap.top = new FormAttachment(lblExitLowerRollGap, -2, SWT.TOP);
		fd_textExitLowerRollGap.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textExitLowerRollGap.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textExitLowerRollGap.setLayoutData(fd_textExitLowerRollGap);
		
		Label lblRollFriction = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblRollFriction = new FormData();
		fd_lblRollFriction.top = new FormAttachment(lblExitLowerRollGap, 12);
		fd_lblRollFriction.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblRollFriction.setLayoutData(fd_lblRollFriction);
		lblRollFriction.setText("Roll friction");
		
		textRollFriction = new Text(compositeRollParameter_in, SWT.BORDER);
		med.setTextRollFriction(textRollFriction);
		FormData fd_textRollFriction = new FormData();
		fd_textRollFriction.top = new FormAttachment(lblRollFriction, -2, SWT.TOP);
		fd_textRollFriction.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textRollFriction.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textRollFriction.setLayoutData(fd_textRollFriction);
		
		Label lblRollDiameter = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblRollDiameter = new FormData();
		fd_lblRollDiameter.top = new FormAttachment(lblRollFriction, 12);
		fd_lblRollDiameter.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblRollDiameter.setLayoutData(fd_lblRollDiameter);
		lblRollDiameter.setText(TextLabel_UI.lblRollDiameter);
		
		textRollDiameter = new Text(compositeRollParameter_in, SWT.BORDER);
		med.setTextRollDiameter(textRollDiameter);
		FormData fd_textRollDiameter = new FormData();
		fd_textRollDiameter.top = new FormAttachment(lblRollDiameter, -2, SWT.TOP);
		fd_textRollDiameter.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textRollDiameter.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textRollDiameter.setLayoutData(fd_textRollDiameter);
		
		ExpandBar expandBar_HoldDownInfo = new ExpandBar(compositeRollParameter_in, SWT.NONE);
		expandBar_HoldDownInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		FormData fd_expandBar_HoldDownInfo = new FormData();
		fd_expandBar_HoldDownInfo.top = new FormAttachment(textRollDiameter,12);
		fd_expandBar_HoldDownInfo.left = new FormAttachment(0);
		fd_expandBar_HoldDownInfo.right = new FormAttachment(100,0);
		//fd_expandBar_HoldDownInfo.bottom = new FormAttachment(textRollDiameter, 300);
		expandBar_HoldDownInfo.setLayoutData(fd_expandBar_HoldDownInfo);
		
		ExpandItem xpndtmHoldDownInfo = new ExpandItem(expandBar_HoldDownInfo, SWT.NONE);
		xpndtmHoldDownInfo.setExpanded(false);
		xpndtmHoldDownInfo.setText("Hold Down Roll Information");
		
		Composite composite_HoldDownInfo = new Composite(expandBar_HoldDownInfo, SWT.NONE);
		composite_HoldDownInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		xpndtmHoldDownInfo.setControl(composite_HoldDownInfo);
		xpndtmHoldDownInfo.setHeight(320);
		composite_HoldDownInfo.setLayout(new FormLayout());
		
		Label lblUpperRoll2 = new Label(composite_HoldDownInfo, SWT.NONE);
		FormData fd_lblUpperRoll2 = new FormData();
		fd_lblUpperRoll2.bottom = new FormAttachment(100, -137);
		fd_lblUpperRoll2.left = new FormAttachment(0, 99);
		lblUpperRoll2.setLayoutData(fd_lblUpperRoll2);
		lblUpperRoll2.setText(TextLabel_UI.lblUpperRoll);
		
		
		
		Label lblRollCrownType = new Label(compositeRollParameter_in,SWT.NONE);
		FormData fd_lblRollCrownType = new FormData();
		fd_lblRollCrownType.top = new FormAttachment(expandBar_HoldDownInfo,12);
		fd_lblRollCrownType.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblRollCrownType.setLayoutData(fd_lblRollCrownType);
		lblRollCrownType.setText(TextLabel_UI.lblRollCrownType);
		
		Composite groupRollCrown = new Composite(compositeRollParameter, SWT.NONE);
		groupRollCrown.setLayout(new FormLayout());
		FormData fd_groupRollCrown = new FormData();
		fd_groupRollCrown.top = new FormAttachment(lblRollCrownType, -2,SWT.TOP);
		fd_groupRollCrown.left = new FormAttachment(spinnerUpperRollNum, 0,SWT.LEFT);
		fd_groupRollCrown.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		groupRollCrown.setLayoutData(fd_groupRollCrown);
		
		Button btnRadioNone_RC = new Button(groupRollCrown, SWT.RADIO);
		med.setBtnRadioNone_RC(btnRadioNone_RC);
		CustomButton customBtnRadioNone_RC = new CustomButton(Mediator.BUTTON_RadioNone_RC,med);
		med.setCustomBtnRadioNone_RC(customBtnRadioNone_RC);
		customBtnRadioNone_RC.setCustomWidget_btnRadioNone_RC();
		btnRadioNone_RC.setAlignment(SWT.CENTER);
		FormData fd_btnRadioNone_RC = new FormData();
		fd_btnRadioNone_RC.top = new FormAttachment(0, 5);
		fd_btnRadioNone_RC.left = new FormAttachment(0, 5);
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
		
		Label lblRollCrown = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblRollCrown = new FormData();
		fd_lblRollCrown.top = new FormAttachment(lblRollCrownType, 12);
		fd_lblRollCrown.left = new FormAttachment(lblUpperRollNumber, 10, SWT.LEFT);
		lblRollCrown.setLayoutData(fd_lblRollCrown);
		lblRollCrown.setText(TextLabel_UI.lblRollCrown);
		
		textRollCrown = new Text(compositeRollParameter_in, SWT.BORDER);
		med.setTextRollCrown(textRollCrown);
		FormData fd_textRollCrown = new FormData();
		fd_textRollCrown.top = new FormAttachment(lblRollCrown, -2, SWT.TOP);
		fd_textRollCrown.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textRollCrown.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textRollCrown.setLayoutData(fd_textRollCrown);
		
		Label lblMillStiffnessType = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblMillStiffnessType = new FormData();
		fd_lblMillStiffnessType.top = new FormAttachment(lblRollCrown, 12);
		fd_lblMillStiffnessType.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		lblMillStiffnessType.setLayoutData(fd_lblMillStiffnessType);
		lblMillStiffnessType.setText(TextLabel_UI.lblMillStiffnessType);
		
		Composite groupMillStiffness = new Composite(compositeRollParameter_in,SWT.NONE);
		groupMillStiffness.setLayout(new FormLayout());
		FormData fd_groupMillStiffness = new FormData();
		fd_groupMillStiffness.top = new FormAttachment(lblMillStiffnessType, -2,SWT.TOP);
		fd_groupMillStiffness.left = new FormAttachment(spinnerUpperRollNum, 0,SWT.LEFT);
		fd_groupMillStiffness.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		groupMillStiffness.setLayoutData(fd_groupMillStiffness);
		
		Button btnRadioRigid_MS = new Button(groupMillStiffness, SWT.RADIO);
		med.setBtnRadioRigid_MS(btnRadioRigid_MS);
		CustomButton customBtnRadioRigid_MS = new CustomButton(Mediator.BUTTON_RadioRigid_MS,med);
		med.setCustomBtnRadioRigid_MS(customBtnRadioRigid_MS);
		customBtnRadioRigid_MS.setCustomWidget_btnRadioRigid_MS();
		btnRadioRigid_MS.setAlignment(SWT.CENTER);
		FormData fd_btnRadioRigid_MS = new FormData();
		fd_btnRadioRigid_MS.top = new FormAttachment(0, 5);
		fd_btnRadioRigid_MS.left = new FormAttachment(0, 5);
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
		
		Label lblMillStiffness = new Label(compositeRollParameter_in, SWT.NONE);
		FormData fd_lblMillStiffness = new FormData();
		fd_lblMillStiffness.top = new FormAttachment(lblMillStiffnessType, 12);
		fd_lblMillStiffness.left = new FormAttachment(lblUpperRollNumber, 10, SWT.LEFT);
		lblMillStiffness.setLayoutData(fd_lblMillStiffness);
		lblMillStiffness.setText(TextLabel_UI.lblMillStiffness);
		
		textMillStiffness = new Text(compositeRollParameter_in, SWT.BORDER);
		med.setTextMillStiffness(textMillStiffness);
		FormData fd_textMillStiffness = new FormData();
		fd_textMillStiffness.top = new FormAttachment(lblMillStiffness, -2, SWT.TOP);
		fd_textMillStiffness.left = new FormAttachment(spinnerUpperRollNum, 0, SWT.LEFT);
		fd_textMillStiffness.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		textMillStiffness.setLayoutData(fd_textMillStiffness);

		
		Button btnCreateRoll = new Button(compositeRollParameter_in, SWT.CENTER);
		med.setBtnCreateRoll(btnCreateRoll);
		CustomButton customBtnCreateRoll = new CustomButton(Mediator.BUTTON_CreateRoll,med);
		med.setCustomBtnCreateRoll(customBtnCreateRoll);
		customBtnCreateRoll.setCustomWidget_btnCreateRoll();
		FormData fd_btnCreateRoll = new FormData();
		fd_btnCreateRoll.top = new FormAttachment(lblMillStiffness, 12);
		fd_btnCreateRoll.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		fd_btnCreateRoll.right = new FormAttachment(spinnerUpperRollNum, 0, SWT.RIGHT);
		btnCreateRoll.setLayoutData(fd_btnCreateRoll);
		btnCreateRoll.setText(TextLabel_UI.btnCreateRoll);
		
		ExpandBar expandBar_rollInfo = new ExpandBar(compositeRollParameter_in, SWT.NONE);
		expandBar_rollInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		FormData fd_expandBar_rollInfo = new FormData();
		fd_expandBar_rollInfo.top = new FormAttachment(btnCreateRoll, 5);
		fd_expandBar_rollInfo.left = new FormAttachment(0);
		fd_expandBar_rollInfo.right = new FormAttachment(100,0);
		fd_expandBar_rollInfo.bottom = new FormAttachment(100,0);
		expandBar_rollInfo.setLayoutData(fd_expandBar_rollInfo);
		
		ExpandItem xpndtmRollInformation = new ExpandItem(expandBar_rollInfo, SWT.NONE);
		xpndtmRollInformation.setExpanded(true);
		xpndtmRollInformation.setText("Roll Table");
		
		ScrolledComposite sc2 = new ScrolledComposite(expandBar_rollInfo, SWT.H_SCROLL|SWT.V_SCROLL | SWT.BORDER);
		sc2.setLayout(new FillLayout());
		sc2.setMinSize(300, 300);
		sc2.setExpandHorizontal(true);
		sc2.setExpandVertical(true);
		
		Composite composite_rollInfo = new Composite(sc2, SWT.NONE);
		composite_rollInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		xpndtmRollInformation.setControl(sc2);
		xpndtmRollInformation.setHeight(100);
		composite_rollInfo.setLayout(new FormLayout());
		
		sc2.setContent(composite_rollInfo);
		
		Label lblUpperRoll = new Label(composite_rollInfo, SWT.NONE);
		FormData fd_lblUpperRoll = new FormData();
		fd_lblUpperRoll.top = new FormAttachment(0, 5);
		fd_lblUpperRoll.left = new FormAttachment(0, 5);
		lblUpperRoll.setLayoutData(fd_lblUpperRoll);
		lblUpperRoll.setText(TextLabel_UI.lblUpperRoll);
		
		TableViewer tableViewerUpperRoll = new TableViewer(composite_rollInfo, SWT.BORDER | SWT.FULL_SELECTION);
		med.setTableViewerUpperRoll(tableViewerUpperRoll);
		CustomTableViewer customTableViewerUpperRoll = new CustomTableViewer(Mediator.TABLEVIEWER_UpperRoll,med);
		med.setCustomTableViewerUpperRoll(customTableViewerUpperRoll);
		customTableViewerUpperRoll.setCustomWidget_tableViewerUpperRoll();
		tableUpperRoll = tableViewerUpperRoll.getTable();
		tableUpperRoll.setLinesVisible(true);
		tableUpperRoll.setHeaderVisible(true);
		FormData fd_tableUpperRoll = new FormData();
		fd_tableUpperRoll.top = new FormAttachment(lblUpperRoll, 5);
		fd_tableUpperRoll.left = new FormAttachment(lblUpperRoll, 0, SWT.LEFT);
		fd_tableUpperRoll.right = new FormAttachment(100,-5);
		fd_tableUpperRoll.bottom = new FormAttachment(lblUpperRoll, 100, SWT.BOTTOM);
		tableUpperRoll.setLayoutData(fd_tableUpperRoll);
		//tableUpperRoll.setEnabled(false);
		
		Label lblLowerRoll = new Label(composite_rollInfo, SWT.NONE);
		FormData fd_lblLowerRoll = new FormData();
		fd_lblLowerRoll.top = new FormAttachment(tableUpperRoll, 5);
		fd_lblLowerRoll.left = new FormAttachment(lblUpperRoll, 0, SWT.LEFT);
		lblLowerRoll.setLayoutData(fd_lblLowerRoll);
		lblLowerRoll.setText(TextLabel_UI.lblLowerRoll);
		
		TableViewer tableViewerLowerRoll = new TableViewer(composite_rollInfo, SWT.BORDER | SWT.FULL_SELECTION);
		med.setTableViewerLowerRoll(tableViewerLowerRoll);
		CustomTableViewer customTableViewerLowerRoll = new CustomTableViewer(Mediator.TABLEVIEWER_LowerRoll,med);
		med.setCustomTableViewerLowerRoll(customTableViewerLowerRoll);
		customTableViewerLowerRoll.setCustomWidget_tableViewerLowerRoll();
		tableLowerRoll = tableViewerLowerRoll.getTable();
		tableLowerRoll.setLinesVisible(true);
		tableLowerRoll.setHeaderVisible(true);
		FormData fd_tableLowerRoll = new FormData();
		fd_tableLowerRoll.top = new FormAttachment(lblLowerRoll, 5);
		fd_tableLowerRoll.left = new FormAttachment(lblUpperRoll, 0, SWT.LEFT);
		fd_tableLowerRoll.right = new FormAttachment(100,-5);
		fd_tableLowerRoll.bottom = new FormAttachment(lblLowerRoll, 100, SWT.BOTTOM);
		tableLowerRoll.setLayoutData(fd_tableLowerRoll);
		//tableLowerRoll.setEnabled(false);

		init_TableColunmn();
		
		Button btnSaveRoll = new Button(composite_rollInfo, SWT.NONE);
		med.setBtnSaveRoll(btnSaveRoll);
		CustomButton customBtnSaveRoll = new CustomButton(Mediator.BUTTON_SaveRoll,med);
		med.setcustomBtnSaveRoll(customBtnSaveRoll);
		customBtnSaveRoll.setCustomWidget_btnSaveRoll();
		FormData fd_btnSaveRoll = new FormData();
		fd_btnSaveRoll.top = new FormAttachment(tableLowerRoll, 5);
		fd_btnSaveRoll.left = new FormAttachment(lblUpperRollNumber, 0, SWT.LEFT);
		fd_btnSaveRoll.right = new FormAttachment(tableLowerRoll, 0, SWT.RIGHT);
		btnSaveRoll.setLayoutData(fd_btnSaveRoll);
		btnSaveRoll.setText("Save Roll");
		// */
		
		
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		Composite compositeMaterialParameter = new Composite(compositeParent, SWT.BORDER);
		med.setCompositeMaterialParameter(compositeMaterialParameter);
		compositeMaterialParameter.setLayout(new FormLayout());
		FormData fd_compositeMaterialParameter = new FormData();
		fd_compositeMaterialParameter.top = new FormAttachment(compositeShapeParameter, 0, SWT.TOP);
		fd_compositeMaterialParameter.left = new FormAttachment(compositeRollParameter, 10);
		fd_compositeMaterialParameter.right = new FormAttachment(compositeRollParameter, 450,SWT.RIGHT);
		fd_compositeMaterialParameter.bottom = new FormAttachment(lblModelName, 540, SWT.BOTTOM);
		compositeMaterialParameter.setLayoutData(fd_compositeMaterialParameter);
		
		Label lblMaterialParameter = new Label(compositeMaterialParameter, SWT.NONE);
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
		//fd_btnRadioConstant_YM.top = new FormAttachment(lblYoungsModulus, 5);
		fd_btnRadioConstant_YM.top = new FormAttachment(0, 5);
		//fd_btnRadioConstant_YM.left = new FormAttachment(lblYoungsModulus, 10, SWT.LEFT);
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
		//fd_btnRadioConstant_FS.top = new FormAttachment(lblFlowStress, 5);
		fd_btnRadioConstant_FS.top = new FormAttachment(0, 5);
		//fd_btnRadioConstant_FS.left = new FormAttachment(lblFlowStress, 10, SWT.LEFT);
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
		//fd_btnRadioConstant_TEC.top = new FormAttachment(lblThermalExpansionCoefficient, 5);
		//fd_btnRadioConstant_TEC.left = new FormAttachment(lblThermalExpansionCoefficient, 10, SWT.LEFT);
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
		//fd_btnRadioConstant_PR.top = new FormAttachment(lblPoissonsRatio, 5);
		//fd_btnRadioConstant_PR.left = new FormAttachment(lblPoissonsRatio, 10,SWT.LEFT);
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
		
		
		
		/*
		private Text textYoungsModulus;
		private Text textFlowStress;
		private Text textThermalExpansionCoefficient;
		private Text textMassDensity;
		private Text textPoissonsRatio;
		 * 
		Label lblModulusElasticity = new Label(compositeMaterialParameter, SWT.NONE);
		FormData fd_lblModulusElasticity = new FormData();
		fd_lblModulusElasticity.top = new FormAttachment(lblMaterialParameter, 12);
		fd_lblModulusElasticity.left = new FormAttachment(lblMaterialParameter, 10, SWT.LEFT);
		lblModulusElasticity.setLayoutData(fd_lblModulusElasticity);
		lblModulusElasticity.setText(TextLabel_UI.lblModulusElasticity);
		
		textModulusElasticity = new Text(compositeMaterialParameter, SWT.BORDER);
		med.setTextModulusElasticity(textModulusElasticity);
		FormData fd_textModulusElasticity = new FormData();
		fd_textModulusElasticity.top = new FormAttachment(lblModulusElasticity, 5);
		fd_textModulusElasticity.left = new FormAttachment(lblModulusElasticity, 0, SWT.LEFT);
		fd_textModulusElasticity.right = new FormAttachment(lblModulusElasticity,330,SWT.LEFT);
		textModulusElasticity.setLayoutData(fd_textModulusElasticity);
		
		Button btnExplorer = new Button(compositeMaterialParameter, SWT.NONE);
		med.setBtnExplorer(btnExplorer);
		CustomButton customBtnExplorer = new CustomButton(Mediator.BUTTON_Explorer,med);
		med.setCustomBtnExplorer(customBtnExplorer);
		customBtnExplorer.setCustomWidget_btnExplorer();
		FormData fd_btnExplorer = new FormData();
		fd_btnExplorer.top = new FormAttachment(textModulusElasticity, -2,SWT.TOP);
		fd_btnExplorer.left = new FormAttachment(textModulusElasticity, 5);
		fd_btnExplorer.right = new FormAttachment(100,-10);
		btnExplorer.setLayoutData(fd_btnExplorer);
		btnExplorer.setText("...");
		
		Label lblYieldStrength = new Label(compositeMaterialParameter, SWT.NONE);
		FormData fd_lblYieldStrength = new FormData();
		fd_lblYieldStrength.top = new FormAttachment(textModulusElasticity, 12);
		fd_lblYieldStrength.left = new FormAttachment(lblModulusElasticity, 0, SWT.LEFT);
		lblYieldStrength.setLayoutData(fd_lblYieldStrength);
		lblYieldStrength.setText(TextLabel_UI.lblYieldStrength);
		
		textYieldStrength = new Text(compositeMaterialParameter, SWT.BORDER);
		med.setTextYieldStrength(textYieldStrength);
		FormData fd_textYieldStrength = new FormData();
		fd_textYieldStrength.top = new FormAttachment(lblYieldStrength, -2, SWT.TOP);
		fd_textYieldStrength.left = new FormAttachment(lblYieldStrength, 150);
		fd_textYieldStrength.right = new FormAttachment(btnExplorer,0,SWT.RIGHT);
		textYieldStrength.setLayoutData(fd_textYieldStrength);
		
		Label lblPoissonRatio = new Label(compositeMaterialParameter, SWT.NONE);
		FormData fd_lblPoissonRatio = new FormData();
		fd_lblPoissonRatio.top = new FormAttachment(lblYieldStrength, 12);
		fd_lblPoissonRatio.left = new FormAttachment(lblModulusElasticity ,0, SWT.LEFT);
		lblPoissonRatio.setLayoutData(fd_lblPoissonRatio);
		lblPoissonRatio.setText(TextLabel_UI.lblPoissonRatio);
		
		textPoissonRatio = new Text(compositeMaterialParameter, SWT.BORDER);
		med.setTextPoissonRatio(textPoissonRatio);
		FormData fd_textPoissonRatio = new FormData();
		fd_textPoissonRatio.top = new FormAttachment(lblPoissonRatio, -2, SWT.TOP);
		fd_textPoissonRatio.left = new FormAttachment(textYieldStrength, 0, SWT.LEFT);
		fd_textPoissonRatio.right = new FormAttachment(btnExplorer, 0, SWT.RIGHT);
		textPoissonRatio.setLayoutData(fd_textPoissonRatio);
		*/
		
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		/*
		Composite compositeContactParameter = new Composite(compositeParent, SWT.BORDER);
		med.setCompositeContactParameter(compositeContactParameter);
		compositeContactParameter.setLayout(new FormLayout());
		FormData fd_compositeContactParameter = new FormData();
		fd_compositeContactParameter.top = new FormAttachment(compositeMaterialParameter, 15);
		fd_compositeContactParameter.left = new FormAttachment(compositeMaterialParameter, 0, SWT.LEFT);
		fd_compositeContactParameter.right = new FormAttachment(compositeMaterialParameter,0, SWT.RIGHT);
		fd_compositeContactParameter.bottom = new FormAttachment(compositeMaterialParameter, 110,SWT.BOTTOM);
		compositeContactParameter.setLayoutData(fd_compositeContactParameter);
		
		Label lblContactParameter = new Label(compositeContactParameter, SWT.NONE);
		FormData fd_lblContactParameter = new FormData();
		fd_lblContactParameter.top = new FormAttachment(0, 10);
		fd_lblContactParameter.left = new FormAttachment(0, 10);
		lblContactParameter.setLayoutData(fd_lblContactParameter);
		lblContactParameter.setText(TextLabel_UI.lblContactParameter);
		
		Label lblFriction = new Label(compositeContactParameter, SWT.NONE);
		FormData fd_lblFriction = new FormData();
		fd_lblFriction.top = new FormAttachment(lblContactParameter, 12);
		fd_lblFriction.left = new FormAttachment(lblContactParameter, 10, SWT.LEFT);
		lblFriction.setLayoutData(fd_lblFriction);
		lblFriction.setText(TextLabel_UI.lblFriction);
		
		textFriction = new Text(compositeContactParameter, SWT.BORDER);
		med.setTextFriction(textFriction);
		FormData fd_textFriction = new FormData();
		fd_textFriction.top = new FormAttachment(lblFriction, -2, SWT.TOP);
		fd_textFriction.left = new FormAttachment(lblFriction , 195,SWT.RIGHT);
		fd_textFriction.right = new FormAttachment(100, -10);
		textFriction.setLayoutData(fd_textFriction);
		
		*/
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		//
		//
		Composite compositeSolvingOption = new Composite(compositeParent, SWT.BORDER);
		med.setCompositeSolvingOption(compositeSolvingOption);
		compositeSolvingOption.setLayout(new FormLayout());
		FormData fd_compositeSolvingOption = new FormData();
		fd_compositeSolvingOption.top = new FormAttachment(compositeMaterialParameter, 5);
		fd_compositeSolvingOption.left = new FormAttachment(compositeMaterialParameter, 0, SWT.LEFT);
		fd_compositeSolvingOption.right = new FormAttachment(compositeMaterialParameter, 0, SWT.RIGHT);
		fd_compositeSolvingOption.bottom = new FormAttachment(compositeMaterialParameter, 250,SWT.BOTTOM);
		//compositeMeshParameter,220,SWT.BOTTOM
		compositeSolvingOption.setLayoutData(fd_compositeSolvingOption);
		
		Label lblSolvingOption = new Label(compositeSolvingOption, SWT.NONE);
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
		
		
		
		
		Button btnApply = new Button(compositeParent, SWT.NONE);
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
		

		
		
		/*
		Label lblDeformedCoordinate = new Label(compositeSolvingOption, SWT.NONE);
		FormData fd_lblDeformedCoordinate = new FormData();
		fd_lblDeformedCoordinate.top = new FormAttachment(lblDomain, 12);
		fd_lblDeformedCoordinate.left = new FormAttachment(lblSolvingTime, 0, SWT.LEFT);
		lblDeformedCoordinate.setLayoutData(fd_lblDeformedCoordinate);
		lblDeformedCoordinate.setText(TextLabel_UI.lblDeformedCoordinate);
		
		textDeformedCoordinate = new Text(compositeSolvingOption, SWT.BORDER);
		med.setTextDeformedCoordinate(textDeformedCoordinate);
		FormData fd_textDeformedCoordinate = new FormData();
		fd_textDeformedCoordinate.top = new FormAttachment(lblDeformedCoordinate, -2,SWT.TOP);
		fd_textDeformedCoordinate.left = new FormAttachment(textSolvingTime, 0, SWT.LEFT);
		fd_textDeformedCoordinate.right = new FormAttachment(textSolvingTime, 0, SWT.RIGHT);
		textDeformedCoordinate.setLayoutData(fd_textDeformedCoordinate);
		*/
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		init_ActionComponent();
		init_AllComponent();
		//init_AllComponentValue();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		
	}
	
	public void init_ActionComponent(){
		HandlerButton handlerButton = new HandlerButton();
		med.getBtnCalcElementNum().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioNone_RC().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioApply_RC().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioRigid_MS().addListener(SWT.Selection, handlerButton);
		med.getBtnRadioSpring_MS().addListener(SWT.Selection, handlerButton);
		med.getBtnCreateRoll().addListener(SWT.Selection, handlerButton);
		//med.getBtnUpperEdit().addListener(SWT.Selection, handlerButton);
		//med.getBtnUpperSave().addListener(SWT.Selection, handlerButton);
		//med.getBtnLowerEdit().addListener(SWT.Selection, handlerButton);
		//med.getBtnLowerSave().addListener(SWT.Selection, handlerButton);
		med.getBtnSaveRoll().addListener(SWT.Selection, handlerButton);
		//med.getBtnExplorer().addListener(SWT.Selection, handlerButton);
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
		
		HandlerCombo handlerCombo = new HandlerCombo();
		med.getComboType().addListener(SWT.Selection, handlerCombo);
		
		HandlerSpinner handlerSpinner = new HandlerSpinner();
		med.getSpinnerUpperRollNum().addListener(SWT.Selection, handlerSpinner);
		med.getSpinnerLowerRollNum().addListener(SWT.Selection, handlerSpinner);
		med.getSpinnerDomain().addListener(SWT.Selection, handlerSpinner);
		med.getSpinnerThread().addListener(SWT.Selection, handlerSpinner);
		
		HandlerTableViewer handlerTableViewer = new HandlerTableViewer();
		med.getTableViewerUpperRoll().addSelectionChangedListener(handlerTableViewer);
		med.getTableViewerLowerRoll().addSelectionChangedListener(handlerTableViewer);
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
