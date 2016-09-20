package com.js.ens.leveller.customWidget;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Text;

import com.js.ens.leveller.core.Mediator;
import com.js.ens.leveller.handler.ICommand;


public class CustomText implements ICommand {
	private Mediator med;
	private String widgetName;
	private Text text;
	
	public CustomText(String widgetName, Mediator med){
		this.widgetName = widgetName;
		this.med = med;
	}
	
	public boolean checkValue(String value){
		
		if(value.isEmpty()){
			//System.out.println("null : " + value);
		}else {
			if(value.equals("0.")){
				//System.out.println("입력 : 0. ");
			}else if(value.equals("-")){
				//System.out.println("입력 : - ");
				//JOptionPane.showMessageDialog(null, "-", "Input Error", JOptionPane.INFORMATION_MESSAGE);
			}else{
				try{
					double d = Double.parseDouble(value);
					
					if(d<0.0){
						JOptionPane.showMessageDialog(null, "Input value must be greater than zero.", "Input Error", JOptionPane.ERROR_MESSAGE);
						return true;
					}else if(d == 0.0){
						JOptionPane.showMessageDialog(null, "Input value must be greater than zero.", "Input Error", JOptionPane.ERROR_MESSAGE);
						return false;
					}
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Input value must be greater than zero.(2)", "Input Error", JOptionPane.ERROR_MESSAGE);
					//System.out.println("입력 : 잘못입력  ");
					return true;
				}	
			}
		}
		return false;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		/*
		if(widgetName.equals(Mediator.TEXT_COMMON_DATA)){
			
		}
		*/
		//System.out.println(widgetName);
		boolean result = false;
		if(widgetName.equals(Mediator.TEXT_textLength_2D)){
			result = this.checkValue(med.getTextLength_2D().getText().trim());
			if(result)
				med.getTextLength_2D().setText("");
		}else if(widgetName.equals(Mediator.TEXT_textThickness_2D)){
			result = this.checkValue(med.getTextThickness_2D().getText().trim());
			if(result)
				med.getTextThickness_2D().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type2_textWaveHeight_2D)){
			result = this.checkValue(med.getType2_textWaveHeight_2D().getText().trim());
			if(result)
				med.getType2_textWaveHeight_2D().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type3_textFrontCurlHeight_2D)){
			result = this.checkValue(med.getType3_textFrontCurlHeight_2D().getText().trim());
			if(result)
				med.getType3_textFrontCurlHeight_2D().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type3_textFrontCurlLength_2D)){
			result = this.checkValue(med.getType3_textFrontCurlLength_2D().getText().trim());
			
			if(result)
				med.getType3_textFrontCurlLength_2D().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type3_textRearCurlHeight_2D)){
			result = this.checkValue(med.getType3_textRearCurlHeight_2D().getText().trim());
			if(result)
				med.getType3_textRearCurlHeight_2D().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type3_textRearCurlLength_2D)){
			result = this.checkValue(med.getType3_textRearCurlLength_2D().getText().trim());
			if(result)
				med.getType3_textRearCurlLength_2D().setText("");
		}
		
		else if(widgetName.equals(Mediator.TEXT_textWidth)){
			result = this.checkValue(med.getTextWidth().getText().trim());
			if(result)
				med.getTextWidth().setText("");
		}else if(widgetName.equals(Mediator.Text_textLength)){
			result = this.checkValue(med.getTextLength().getText().trim());
			if(result)
				med.getTextLength().setText("");
		}else if(widgetName.equals(Mediator.TEXT_textThickness)){
			result = this.checkValue(med.getTextThickness().getText().trim());
			if(result)
				med.getTextThickness().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type2_textLeftEdgeWaveHeight)){
			result = this.checkValue(med.getType2_textLeftEdgeWaveHeight().getText().trim());
			if(result)
				med.getType2_textLeftEdgeWaveHeight().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type2_textRightEdgeWaveHeight)){
			result = this.checkValue(med.getType2_textRightEdgeWaveHeight().getText().trim());
			if(result)
				med.getType2_textRightEdgeWaveHeight().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type3_textWaveHeight)){
			result = this.checkValue(med.getType3_textWaveHeight().getText().trim());
			if(result)
				med.getType3_textWaveHeight().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type4_textGutterHeight)){
			result = this.checkValue(med.getType4_textGutterHeight().getText().trim());
			if(result)
				med.getType4_textGutterHeight().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type5_textGutterHeight)){
			result = this.checkValue(med.getType5_textGutterHeight().getText().trim());
			if(result)
				med.getType5_textGutterHeight().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type5_textGutterLength)){
			result = this.checkValue(med.getType5_textGutterLength().getText().trim());
			if(result)
				med.getType5_textGutterLength().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type6_textHeadGutterHeight)){
			result = this.checkValue(med.getType6_textHeadGutterHeight().getText().trim());
			if(result)
				med.getType6_textHeadGutterHeight().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type6_textHeadGutterLength)){
			result = this.checkValue(med.getType6_textHeadGutterLength().getText().trim());
			if(result)
				med.getType6_textHeadGutterLength().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type6_textTailGutterHeight)){
			result = this.checkValue(med.getType6_textTailGutterHeight().getText().trim());
			if(result)
				med.getType6_textTailGutterHeight().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type6_textTailGutterLength)){
			result = this.checkValue(med.getType6_textTailGutterLength().getText().trim());
			if(result)
				med.getType6_textTailGutterLength().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type7_textHeadGutterHeight)){
			result = this.checkValue(med.getType7_textHeadGutterHeight().getText().trim());
			if(result)
				med.getType7_textHeadGutterHeight().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type7_textGutterLength)){
			result = this.checkValue(med.getType7_textGutterLength().getText().trim());
			if(result)
				med.getType7_textGutterLength().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type7_textGutterLengthLength)){
			result = this.checkValue(med.getType7_textGutterLengthLength().getText().trim());
			if(result)
				med.getType7_textGutterLengthLength().setText("");
		}else if(widgetName.equals(Mediator.TEXT_type7_textGutterWidthLength)){
			result = this.checkValue(med.getType7_textGutterWidthLength().getText().trim());
			if(result)
				med.getType7_textGutterWidthLength().setText("");
		}
	}
	
	/*
	public void setCustomWidget_textCommonData(){
		this.text = med.getTextCommonData(); 
	}
	*/
	//2D 
	
	public void setCustomWidget_textLength_2D(){
		this.text = med.getTextLength_2D();
	}
	
	public void setCustomWidget_textThickness_2D(){
		this.text =med.getTextThickness_2D();
	}
	
	public void setCustomWidget_type2_textWaveHeight_2D(){
		this.text = med.getType2_textWaveHeight_2D();
	}
	
	public void setCustomWidget_type3_textFrontCurlHeight_2D(){
		this.text = med.getType3_textFrontCurlHeight_2D();
	}
	
	public void setCustomWidget_type3_textFrontCurlLength_2D(){
		this.text = med.getType3_textFrontCurlLength_2D();
	}
	
	public void setCustomWidget_type3_textRearCurlHeight_2D(){
		this.text = med.getType3_textRearCurlHeight_2D();
	}
	
	public void setCustomWidget_type3_textRearCurlLength_2D(){
		this.text = med.getType3_textRearCurlLength_2D();
	}
	
	
	
	// 3D
	
	public void setCustomWidget_textWidth(){
		this.text = med.getTextWidth();
	}
	
	public void setCustomWidget_textLength(){
		this.text = med.getTextLength();
	}
	
	public void setCustomWidget_textThickness(){
		this.text = med.getTextThickness();
	}
	
	public void setCustomWidget_type2_textLeftEdgeWaveHeight(){
		this.text = med.getType2_textLeftEdgeWaveHeight();
	}
	
	public void setCustomWidget_type2_textRightEdgeWaveHeight(){
		this.text = med.getType2_textRightEdgeWaveHeight();
	}

	public void setCustomWidget_type3_textWaveHeight(){
		this.text = med.getType3_textWaveHeight();
	}
	
	public void setCustomWidget_type4_textGutterHeight(){
		this.text = med.getType4_textGutterHeight();
	}
	
	public void setCustomWidget_type5_textGutterHeight(){
		this.text = med.getType5_textGutterHeight();
	}
	
	public void setCustomWidget_type5_textGutterLength(){
		this.text = med.getType5_textGutterLength();
	}
	
	public void setCustomWidget_type6_textHeadGutterHeight(){
		this.text = med.getType6_textHeadGutterHeight();
	}
	
	public void setCustomWidget_type6_textHeadGutterLength(){
		this.text = med.getType6_textHeadGutterLength();
	}
	
	public void setCustomWidget_type6_textTailGutterHeight(){
		this.text = med.getType6_textTailGutterHeight();
	}
	
	public void setCustomWidget_type6_textTailGutterLength(){
		this.text = med.getType6_textTailGutterLength();
	}
	
	public void setCustomWidget_type7_textHeadGutterHeight(){
		this.text = med.getType7_textHeadGutterHeight();
	}
	
	public void setCustomWidget_type7_textGutterLength(){
		this.text = med.getType7_textGutterLength();
	}
	
	public void setCustomWidget_type7_textGutterLengthLength(){
		this.text = med.getType7_textGutterLengthLength();
	}
	
	public void setCustomWidget_type7_textGutterWidthLength(){
		this.text = med.getType7_textGutterWidthLength();
	}
}
