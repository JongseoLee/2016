package com.msc.adams.automation.core.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.custom.CCombo;

import com.msc.adams.automation.customTable.TableEditorInfo;
import com.msc.adams.automation.datas.Part;
import com.msc.adams.automation.datas.factory.PartFactory;

public class DatabaseStep2 {
	
	private boolean isSkiped;
	
	private String PartDataPath;
	private ArrayList<Part> PartList;				// File���� ���� ��ü Part
	private ArrayList<Part> AllPartList;
	
	private PartFactory PartFactoryObjImpl;			// ���� ����� Part
	
	private ArrayList<Part> tempSelectedPartList;	//list���� ���õ� Part �ӽ� ���� ����Ʈ
	private ArrayList<Part> SelectedPartList;		//table�� �߰��� Part
	
	private ArrayList<TableEditorInfo> TableRowList;	//Table �÷� �� ����Ʈ
	
	private Map<CCombo,TableEditorInfo> TableComboTextMap;


	public DatabaseStep2() {
		// TODO Auto-generated constructor stub
		this.isSkiped = false;
		
		this.PartList = new ArrayList<Part>();
		this.AllPartList = new ArrayList<Part>();
		
		this.tempSelectedPartList = new ArrayList<Part>();
		this.SelectedPartList = new ArrayList<Part>();
		this.PartFactoryObjImpl = new PartFactory();
		
		this.TableRowList = new ArrayList<TableEditorInfo>();
		this.TableComboTextMap = new HashMap<CCombo,TableEditorInfo>();
	}
	
	public Part SearchingPartObj(String partName) {
		// TODO Auto-generated method stub
		// step3 ���� �Էµ� �����Ϳ� step2 ������ ����ȭ �Ҷ� ȣ��
		Part searchingObj = null;
		for(Part obj : this.SelectedPartList){
			if(obj.getPartName().equals(partName)){
				searchingObj = obj;
			}
		}
		return searchingObj;
	}
	
	
	
	
	


	public boolean isSkiped() {
		return isSkiped;
	}
	public void setSkiped(boolean isSkiped) {
		this.isSkiped = isSkiped;
	}



	public void addPartList(Part obj){
		this.PartList.add(obj);
		this.AllPartList.add(obj);
	}
	public void removePartList(Part obj){
		if(this.PartList.contains(obj)){
			this.PartList.remove(obj);
		}
	}
	public ArrayList<Part> getPartList() {
		return PartList;
	}
	public ArrayList<Part> getAllPartList() {
		return AllPartList;
	}

	public void setAllPartList(ArrayList<Part> allPartList) {
		AllPartList = allPartList;
	}

	public void setPartList(ArrayList<Part> partList) {
		PartList = partList;
	}
	

	
	
	
	
	public void addTempSelectedPartList(Part obj){
		this.tempSelectedPartList.add(obj);
	}
	public void removeTempSelectedPartList(Part obj){
		if(this.tempSelectedPartList.contains(obj)){
			this.tempSelectedPartList.remove(obj);
		}
	}
	public ArrayList<Part> getTempSelectedPartList() {
		return tempSelectedPartList;
	}
	public void setTempSelectedPartList(ArrayList<Part> tempSelectedPartList) {
		this.tempSelectedPartList = tempSelectedPartList;
	}
	
	
	
	
	
	
	public void addSelectedPartList(Part obj){
		this.SelectedPartList.add(obj);
	}
	public void removeSelectedPartList(Part obj){
		if(this.SelectedPartList.contains(obj)){
			this.SelectedPartList.remove(obj);
		}
	}
	public ArrayList<Part> getSelectedPartList() {
		return SelectedPartList;
	}
	public void setSelectedPartList(ArrayList<Part> selectedPartList) {
		SelectedPartList = selectedPartList;
	}
	
	
	
	

	public void addTableRowList(TableEditorInfo obj){
		this.TableRowList.add(obj);
	}
	public void removeTableRowList(TableEditorInfo obj){
		if(this.TableRowList.contains(obj)){
			this.TableRowList.remove(obj);
		}
	}
	public ArrayList<TableEditorInfo> getTableRowList() {
		return TableRowList;
	}
	public void setTableRowList(ArrayList<TableEditorInfo> tableRowList) {
		TableRowList = tableRowList;
	}
	
	
	
	
	

	public Map<CCombo, TableEditorInfo> getTableComboTextMap() {
		return TableComboTextMap;
	}
	public void setTableComboTextMap(Map<CCombo, TableEditorInfo> tableComboTextMap) {
		TableComboTextMap = tableComboTextMap;
	}
	public void putTableComoTextMap(CCombo combo,TableEditorInfo editorInfo){
		this.TableComboTextMap.put(combo, editorInfo);
	}
	public void removeTableComboTextMap(CCombo combo){
		if(this.TableComboTextMap.containsKey(combo)){
			this.TableComboTextMap.remove(combo);
		}
	}

	
	
	

	public PartFactory getPartFactoryObjImpl() {
		return PartFactoryObjImpl;
	}
	public void setPartFactoryObjImpl(PartFactory partFactoryObjImpl) {
		PartFactoryObjImpl = partFactoryObjImpl;
	}




	public String getPartDataPath() {
		return PartDataPath;
	}

	public void setPartDataPath(String partDataPath) {
		PartDataPath = partDataPath;
	}







	
	
}
