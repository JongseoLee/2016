package com.js.ens.transformation.customWidget;

import org.eclipse.swt.widgets.Text;

import com.js.ens.transformation.core.Mediator;

public class CustomText implements ICommand {
	private Mediator med;
	private String widgetName;
	private Text text;
	
	public CustomText(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
