package com.js.ens.transformation.customWidget;

import org.eclipse.swt.widgets.Text;

import com.js.ens.transformation.core.MainController;
import com.js.ens.transformation.core.Mediator;

public class CustomText implements ICommand {
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private Text text;
	
	public CustomText(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			//MC.메서드 호출()
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			
		}else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			
		}else if(widgetName.equals(Mediator.TEXT_textThickness)){
			
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			
		}else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			
		}else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			
		}else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_time)){
			
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement_dt)){
			
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			
		}else if(widgetName.equals(Mediator.TEXT_textIncrementTime)){
			
		}
	}
	
	public void setCustomWidget_textTopWRDiameter(){
		this.text = med.getTextTopWRDiameter();
	}
	public void setCustomWidget_textBottomWRDiameter(){
		this.text = med.getTextBottomWRDiameter();
	}
	public void setCustomWidget_textWRCrown(){
		this.text = med.getTextWRCrown();
	}
	public void setCustomWidget_textWRLength(){
		this.text = med.getTextWRLength();
	}
	public void setCustomWidget_textWRMeshAngle(){
		this.text = med.getTextWRMeshAngle();
	}
	public void setCustomWidget_textTopBURDiameter(){
		this.text = med.getTextTopBURDiameter();
	}
	public void setCustomWidget_textBottomBURDiameter(){
		this.text = med.getTextBottomBURDiameter();
	}
	public void setCustomWidget_textBURLength(){
		this.text = med.getTextBURLength();
	}
	public void setCustomWidget_textBURMeshAngle(){
		this.text = med.getTextBURMeshAngle();
	}
	public void setCustomWidget_textThickness(){
		this.text = med.getTextThickness();
	}
	public void setCustomWidget_textWidth(){
		this.text = med.getTextWidth();
	}
	public void setCustomWidget_textLength(){
		this.text = med.getTextLength();
	}
	public void setCustomWidget_textEntryTemperature(){
		this.text = med.getTextEntryTemperature();
	}
	public void setCustomWidget_textExitTemperature(){
		this.text = med.getTextExitTemperature(); 
	}
	public void setCustomWidget_textInitialPosition(){
		this.text = med.getTextInitialPosition();
	}
	public void setCustomWidget_textMeshLength(){
		this.text = med.getTextMeshLength();
	}
	public void setCustomWidget_textThicknessMeshDivisions(){
		this.text = med.getTextThicknessMeshDivisions();
	}
	public void setCustomWidget_textVelocity(){
		this.text = med.getTextVelocity();
	}
	public void setCustomWidget_textRollGap(){
		this.text = med.getTextRollGap();
	}
	public void setCustomWidget_textPassLine(){
		this.text = med.getTextPassLine();
	}
	public void setCustomWidget_textPairCrossAngle(){
		this.text = med.getTextPairCrossAngle();
	}
	public void setCustomWidget_textBenderForce(){
		this.text = med.getTextBenderForce();
	}
	public void setCustomWidget_textRollTorque(){
		this.text = med.getTextRollTorque();
	}
	public void setCustomWidget_textTensionStress(){
		this.text = med.getTextTensionStress(); 
	}
	public void setCustomWidget_textRollToPlateFrictCoef(){
		this.text = med.getTextRollToPlateFrictCoef();
	}
	public void setCustomWidget_textRollToRollFrictCoef(){
		this.text = med.getTextRollToRollFrictCoef();
	}
	public void setCustomWidget_textYoungsModulus(){
		this.text = med.getTextYoungsModulus();
	}
	public void setCustomWidget_textThermalExpansionCoefficient(){
		this.text = med.getTextThermalExpansionCoefficient();
	}
	public void setCustomWidget_textPoissonsRatio(){
		this.text = med.getTextPoissonsRatio();
	}
	public void setCustomWidget_textMassDensity(){
		this.text = med.getTextMassDensity();
	}
	public void setCustomWidget_textTimeIncrement_time(){
		this.text = med.getTextTimeIncrement_time();
	}
	public void setCustomWidget_textTimeIncrement_dt(){
		this.text = med.getTextTimeIncrement_dt();
	}
	public void setCustomWidget_textPostWritingFrequency(){
		this.text = med.getTextPostWritingFrequency();
	}
	public void setCustomWidget_textIncrementTime(){
		this.text = med.getTextIncrementTime();
	}
	

}
