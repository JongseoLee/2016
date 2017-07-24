package com.msc.adams.automation.core.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.custom.CCombo;

import com.msc.adams.automation.customTable.TableEditorInfo_FatSolving;
import com.msc.adams.automation.datas.Part_FatSolving;

public class DatabaseStep5 {
	
	private ArrayList<Part_FatSolving> PartList;
	private ArrayList<TableEditorInfo_FatSolving> TableRowList;
	private Map<CCombo,TableEditorInfo_FatSolving> TableComboTextMap;
	
	private String CycleNumberValue;
	
	

	public DatabaseStep5() {
		// TODO Auto-generated constructor stub
		this.PartList = new ArrayList<Part_FatSolving>();
		this.TableRowList = new ArrayList<TableEditorInfo_FatSolving>();
		this.TableComboTextMap = new HashMap<CCombo,TableEditorInfo_FatSolving>();
		this.CycleNumberValue = "1.50e+05";
	}
	
	public Part_FatSolving SearchingPart_FatSolvingObj(String partName){
		Part_FatSolving searchingObj = null;
		for(Part_FatSolving obj : this.PartList){
			if(obj.getPartName().equals(partName)){
				searchingObj = obj;
			}
		}
		return searchingObj;
	}
	
	
	
	
	
	
	
	public ArrayList<Part_FatSolving> getPartList() {
		return PartList;
	}

	public void setPartList(ArrayList<Part_FatSolving> partList) {
		PartList = partList;
	}

	
	
	public void addTableRowList(TableEditorInfo_FatSolving obj){
		this.TableRowList.add(obj);
	}
	public void removeTableRowList(TableEditorInfo_FatSolving obj){
		if(this.TableRowList.contains(obj)){
			this.TableRowList.remove(obj);
		}
	}
	
	public ArrayList<TableEditorInfo_FatSolving> getTableRowList() {
		return TableRowList;
	}

	public void setTableRowList(ArrayList<TableEditorInfo_FatSolving> tableRowList) {
		TableRowList = tableRowList;
	}

	public void putTableComoTextMap(CCombo combo, TableEditorInfo_FatSolving editorInfo){
		this.TableComboTextMap.put(combo, editorInfo);
	}
	
	public Map<CCombo, TableEditorInfo_FatSolving> getTableComboTextMap() {
		return TableComboTextMap;
	}

	public void setTableComboTextMap(Map<CCombo, TableEditorInfo_FatSolving> tableComboTextMap) {
		TableComboTextMap = tableComboTextMap;
	}
	
	public String getCycleNumberValue() {
		return CycleNumberValue;
	}

	public void setCycleNumberValue(String cycleNumberValue) {
		CycleNumberValue = cycleNumberValue;
	}
	
	

}
