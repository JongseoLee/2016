package com.msc.adams.automation.handler;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import com.msc.adams.automation.core.Mediator;

public class HandlerListDoubleClick implements MouseListener {
	private Mediator med = Mediator.getInstance();
	
	public HandlerListDoubleClick() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseDoubleClick(MouseEvent event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getListBladeDatabase()){
			med.getC_doubleClick_listBladeDatabase().execute();
		}else if(event.widget == med.getListLinkageDatabase()){
			med.getC_doubleClick_listLinkageDatabase().execute();
		}
	}

	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
