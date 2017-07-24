package com.msc.adams.automation.customTable;

import java.util.ArrayList;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.msc.adams.automation.datas.Part_FatSolving;

public class TableEditorInfo_FatSolving {

	private Table parent;
	private ArrayList<TableEditor> listEditor;
	private Part_FatSolving Part_FatSolvingObj;
	private int row;
	
	public TableEditorInfo_FatSolving(Table parent, int row){
		this.parent = parent;
		this.listEditor = new ArrayList<TableEditor>();
		this.row = row;
		this.Part_FatSolvingObj = null;
	}
	
	public void setColtrol(Control control, int col, TableItem item){
		if(listEditor.size() <= col){
			int nAddedCnt = col-listEditor.size()+1;
			for(int i=0;i<nAddedCnt;i++){
				listEditor.add(null);
			}
			
			TableEditor editor = new TableEditor(parent);
			editor.grabHorizontal = true;
			editor.grabVertical = true;
			editor.setEditor(control, item, col);	
			listEditor.set(col, editor);
		}
	}
	
	public String [] getControlDatas(){
		String [] datas = new String[listEditor.size()];
		for(int i=0;i<listEditor.size();i++){
			if(listEditor.get(i) != null){
				String data = null;
				if(listEditor.get(i).getEditor() instanceof Text){
					Text text = (Text)listEditor.get(i).getEditor();
					data = text.getText();
				}else if(listEditor.get(i).getEditor() instanceof CCombo){
					CCombo combo = (CCombo)listEditor.get(i).getEditor();
					data = combo.getText();
				}
				
				datas[i] = data;
			}
		}
		return datas;
	}
	
	public void disposeControls(){
		for(int i=0; i<listEditor.size(); i++){
			if( listEditor.get(i) != null){
				listEditor.get(i).getEditor().dispose();
				listEditor.get(i).dispose();
			}
		}
	}
	
	
	
	
	public void setPart_FatSolvingObj(Part_FatSolving obj){
		this.Part_FatSolvingObj = obj;
	}
	
	public Part_FatSolving getPart_FatSolvingObj(){
		return this.Part_FatSolvingObj;
	}
	
	public Text getTextPathTableEditor(){
		return (Text)listEditor.get(3).getEditor();
	}
	public void setTextPath(String path){
		((Text)listEditor.get(3).getEditor()).setText(path);
	}
	
	public Combo getComboTableEditor(){
		return (Combo)listEditor.get(2).getEditor();
	}
	public void setComboType(String type){
		if(type.equals(Part_FatSolving.Type_MSR_MANUAL)){
			((CCombo)listEditor.get(2).getEditor()).select(0);
		}else if(type.equals(Part_FatSolvingObj.Type_MSR_AUTO)){
			((CCombo)listEditor.get(2).getEditor()).select(1);
		}else if(type.equals(Part_FatSolving.Type_MSM)){
			((CCombo)listEditor.get(2).getEditor()).select(2);
		}
	}
	
	
}
