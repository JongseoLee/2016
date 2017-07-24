package com.msc.adams.automation.datas.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.msc.adams.automation.datas.Linkage;

public class LinkageFactory {
	
	private List AllData; 
	
	
	public LinkageFactory() {
		// TODO Auto-generated constructor stub
		this.AllData = new ArrayList();
	}
	
	public List getList(){
		return Collections.unmodifiableList(this.AllData);
	}
	
	public void addAllDataObj(Linkage obj){
		if(this.AllData.contains(obj)){
			this.AllData.add(obj);
		}
	}
	
	public void removeAllDataObj(Linkage obj){
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
