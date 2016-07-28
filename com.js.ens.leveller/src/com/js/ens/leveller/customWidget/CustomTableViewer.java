package com.js.ens.leveller.customWidget;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Table;

import com.js.ens.leveller.core.Mediator;
import com.js.ens.leveller.core.TableViewerModifier;
import com.js.ens.leveller.handler.ICommand;


public class CustomTableViewer implements ICommand {
	private Mediator med;
	private String widgetName;
	private TableViewer tableViewer;
	private Table table;
	
	public CustomTableViewer(String widgetName, Mediator med){
		this.widgetName = widgetName;
		this.med = med;
	}
	@Override
	public void execute() {
		
		// TODO Auto-generated method stub
		if(widgetName.equals(Mediator.TABLEVIEWER_UpperRoll)){
			//System.out.println("=>Upper Roll Table event");
			UpTableViewerEdit();
		} 
		if(widgetName.equals(Mediator.TABLEVIEWER_LowerRoll)){
			//System.out.println("=>Lower Roll Table event");
			DownTableViewerEdit();
		}
		
		
		if(widgetName.equals(Mediator.TABLEVIEWER_UpperRoll_2D)){
			//System.out.println("=>Upper Roll Table event");
			UpTableViewerEdit_2D();
		} 
		if(widgetName.equals(Mediator.TABLEVIEWER_LowerRoll_2D)){
			//System.out.println("=>Lower Roll Table event");
			DownTableViewerEdit_2D();
		}
		
	}
	
	
	public void setCustomWidget_tableViewerUpperRoll(){
		this.tableViewer = med.getTableViewerUpperRoll();
		this.table = this.tableViewer.getTable();
	}
	
	public void setCustomWidget_tableViewerLowerRoll(){
		this.tableViewer = med.getTableViewerLowerRoll();
		this.table = this.tableViewer.getTable();
	}
	
	
	public void setCustomWidget_tableViewerUpperRoll_2D(){
		this.tableViewer = med.getTableViewerUpperRoll_2D();
		this.table = this.tableViewer.getTable();
	}
	
	public void setCustomWidget_tableViewerLowerRoll_2D(){
		this.tableViewer = med.getTableViewerLowerRoll_2D();
		this.table = this.tableViewer.getTable();
	}
	
	public void UpTableViewerEdit(){
		CellEditor [] editor = new CellEditor[4];
		editor[0] = null;
		editor[1] = new TextCellEditor(this.table);
		editor[2] = new TextCellEditor(this.table);
		editor[3] = new TextCellEditor(this.table);
		
		this.tableViewer.setCellModifier(new TableViewerModifier(this.tableViewer));
		this.tableViewer.setCellEditors(editor);
	}
	
	public void DownTableViewerEdit(){
		CellEditor [] editor = new CellEditor[4];
		editor[0] = null;
		editor[1] = new TextCellEditor(this.table);
		editor[2] = new TextCellEditor(this.table);
		editor[3] = new TextCellEditor(this.table);
		
		this.tableViewer.setCellModifier(new TableViewerModifier(this.tableViewer));
		this.tableViewer.setCellEditors(editor);
	}
	
	
	
	public void UpTableViewerEdit_2D(){
		CellEditor [] editor = new CellEditor[4];
		editor[0] = null;
		editor[1] = new TextCellEditor(this.table);
		editor[2] = new TextCellEditor(this.table);
		editor[3] = new TextCellEditor(this.table);
		
		this.tableViewer.setCellModifier(new TableViewerModifier(this.tableViewer));
		this.tableViewer.setCellEditors(editor);
	}
	
	public void DownTableViewerEdit_2D(){
		CellEditor [] editor = new CellEditor[4];
		editor[0] = null;
		editor[1] = new TextCellEditor(this.table);
		editor[2] = new TextCellEditor(this.table);
		editor[3] = new TextCellEditor(this.table);
		
		this.tableViewer.setCellModifier(new TableViewerModifier(this.tableViewer));
		this.tableViewer.setCellEditors(editor);
	}
	
}


