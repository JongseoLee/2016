package com.msc.adams.automation.handler;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Text;

public class TextFocusListener implements FocusListener {

	public TextFocusListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		Text text = (Text)e.widget;
		text.selectAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}

}
