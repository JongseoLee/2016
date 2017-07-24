package com.msc.adams.automation.customTable;

import java.util.ArrayList;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.msc.adams.automation.datas.Part;

public class TableEditorInfo {

	private Table parent;
	private ArrayList<TableEditor> listEditor;
	private Part PartObj;
	private int row;
	
	public TableEditorInfo(Table parent, int row){
		this.parent = parent;
		this.listEditor = new ArrayList<TableEditor>();
		this.row = row;
		this.PartObj = null;
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
	
	
	
	
	public void setPartObj(Part obj){
		this.PartObj = obj;
	}
	
	public Part getPartObj(){
		return this.PartObj;
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
		if(type.equals(Part.Type_MNF)){
			((CCombo)listEditor.get(2).getEditor()).select(1);
		}else if(type.equals(Part.Type_NAS)){
			((CCombo)listEditor.get(2).getEditor()).select(0);
		}else if(type.equals(Part.Type_OPT)){
			((CCombo)listEditor.get(2).getEditor()).select(2);
		}
	}
	
	
}
