package com.msc.adams.automation.datas.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.msc.adams.automation.datas.Blade;

public class BladeFactory {
	
	private List AllData;
	
	
	public BladeFactory() {
		// TODO Auto-generated constructor stub
		this.AllData = new ArrayList();
	}
	
	public List getList(){
		return Collections.unmodifiableList(this.AllData);
	}
	
	public void addAllDataObj(Blade obj){
		if(this.AllData.contains(obj)){
			this.AllData.add(obj);
		}
	}
	
	public void removeAllDataObj(Blade obj){
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
