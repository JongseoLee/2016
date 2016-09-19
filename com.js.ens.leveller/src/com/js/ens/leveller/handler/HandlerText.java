package com.js.ens.leveller.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.js.ens.leveller.core.Mediator;


public class HandlerText implements Listener {
	private Mediator med = Mediator.getInstance();
	
	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		/*
		if(event.widget == med.textCommonData){
			med.getCustomTextCommonData().execute();
		}
		*/
		
		if(event.widget == med.getTextLength_2D()){
			med.getC_textLength_2D().execute();
		}else if(event.widget == med.getTextThickness_2D()){
			med.getC_textThickness_2D().execute();
		}else if(event.widget == med.getType2_textWaveHeight_2D()){
			med.getC_type2_textWaveHeight_2D().execute();
		}else if(event.widget == med.getType3_textFrontCurlHeight_2D()){
			med.getC_type3_textFrontCurlHeight_2D().execute();
		}else if(event.widget == med.getType3_textFrontCurlLength_2D()){
			med.getC_type3_textFrontCurlLength_2D().execute();
		}else if(event.widget == med.getType3_textRearCurlHeight_2D()){
			med.getC_type3_textRearCurlHeight_2D().execute();
		}else if(event.widget == med.getType3_textRearCurlLength_2D()){
			med.getC_type3_textRearCurlLength_2D().execute();
		}
		
		else if(event.widget == med.getTextWidth()){
			med.getC_textWidth().execute();
		}else if(event.widget == med.getTextLength()){
			med.getC_textLength().execute();
		}else if(event.widget == med.getTextThickness()){
			med.getC_textThickness().execute();
		}else if(event.widget == med.getType2_textLeftEdgeWaveHeight()){
			med.getC_type2_textLeftEdgeWaveHeight().execute();
		}else if(event.widget == med.getType2_textRightEdgeWaveHeight()){
			med.getC_type2_textRightEdgeWaveHeight().execute();
		}else if(event.widget == med.getType3_textWaveHeight()){
			med.getC_type3_textWaveHeight().execute();
		}else if(event.widget == med.getType4_textGutterHeight()){
			med.getC_type4_textGutterHeight().execute();
		}else if(event.widget == med.getType5_textGutterHeight()){
			med.getC_type5_textGutterHeight().execute();
		}else if(event.widget == med.getType5_textGutterLength()){
			med.getC_type5_textGutterLength().execute();
		}else if(event.widget == med.getType6_textHeadGutterHeight()){
			med.getC_type6_textHeadGutterHeight().execute();
		}else if(event.widget == med.getType6_textHeadGutterLength()){
			med.getC_type6_textHeadGutterLength().execute();
		}else if(event.widget == med.getType6_textTailGutterHeight()){
			med.getC_type6_textTailGutterHeight().execute();
		}else if(event.widget == med.getType6_textTailGutterLength()){
			med.getC_type6_textTailGutterLength().execute();
		}else if(event.widget == med.getType7_textHeadGutterHeight()){
			med.getC_type7_textHeadGutterHeight().execute();
		}else if(event.widget == med.getType7_textGutterLength()){
			med.getC_type7_textGutterLength().execute();
		}else if(event.widget == med.getType7_textGutterLengthLength()){
			med.getC_type7_textGutterLengthLength().execute();
		}else if(event.widget == med.getType7_textGutterWidthLength()){
			med.getC_type7_textGutterWidthLength().execute();
		}
		
	}

}
