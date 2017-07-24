package com.msc.adams.automation.datas.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.msc.adams.automation.datas.Blade;
import com.msc.adams.automation.datas.Part;
import com.msc.io.Reader;
import com.msc.util.myUtil;


// LoadPratData ����
public class PartFactory {
	
	private List AllData;
	
	
	public PartFactory() {
		// TODO Auto-generated constructor stub
		this.AllData = new ArrayList();
	}

	// ����Ʈ�� �ִ� �����͸� ��������
	public List getList(){
		return Collections.unmodifiableList(this.AllData);
	}
	
	public void addAllDataObj(Part obj){
		this.AllData.add(obj);
	}
	
	public void removeAllDataObj(Part obj){
		if(this.AllData.contains(obj)){
			this.AllData.remove(obj);
		}
	}

	public List getAllData() {
		return AllData;
	}

	public void setAllData(List allData) {
		AllData = allData;
	}
	
	
}
