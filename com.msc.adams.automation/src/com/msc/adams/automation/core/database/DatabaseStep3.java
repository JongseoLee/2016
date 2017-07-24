package com.msc.adams.automation.core.database;

import java.util.ArrayList;

import com.msc.adams.automation.datas.SwappingPart;
import com.msc.adams.automation.datas.factory.SwappingPartFactory;

public class DatabaseStep3 {
	
	private boolean isSkiped;
	
	private boolean isChanged = false;
	
	private ArrayList<SwappingPart> SwappingPartList;
	private SwappingPartFactory SwappingPartFactoryObjImpl;
	
	private SwappingPart currentSwappingPart;
	
	public DatabaseStep3() {
		// TODO Auto-generated constructor stub
		this.isSkiped = false;
		
		this.SwappingPartList = new ArrayList<SwappingPart>();
		this.SwappingPartFactoryObjImpl = new SwappingPartFactory();
	}

	public boolean isSwappingPart(String partName){
		boolean result = false;
		for(SwappingPart obj : this.SwappingPartList){
			//System.out.println("======> "+ obj.getPartName());
			//System.out.println("###===> "+ partName);
			if(obj.getPartName().equals(partName)){
				result = true;
				break;
			}
		}
		return result;
	}
	
	public SwappingPart SearchingSwappingPart(String partName){
		SwappingPart searchingObj = null;
		for(SwappingPart obj : this.SwappingPartList){
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
	
	
	public void addSwappingPartList(SwappingPart obj){
		this.SwappingPartList.add(obj);
	}
	public ArrayList<SwappingPart> getSwappingPartList() {
		return SwappingPartList;
	}
	public void setSwappingPartList(ArrayList<SwappingPart> swappingPartList) {
		SwappingPartList = swappingPartList;
	}
	
	
	public SwappingPartFactory getSwappingPartFactoryObjImpl() {
		return SwappingPartFactoryObjImpl;
	}
	public void setSwappingPartFactoryObjImpl(SwappingPartFactory swappingPartFactoryObjImpl) {
		SwappingPartFactoryObjImpl = swappingPartFactoryObjImpl;
	}


	public SwappingPart getCurrentSwappingPart() {
		return currentSwappingPart;
	}
	public void setCurrentSwappingPart(SwappingPart currentSwappingPart) {
		this.currentSwappingPart = currentSwappingPart;
	}



	public boolean isChanged() {
		return isChanged;
	}



	public void setChanged(boolean isChanged) {
		//System.out.println("before result : "+this.isChanged);
		//System.out.println("----> changed !!!");
		this.isChanged = isChanged;
		//System.out.println("after result : "+this.isChanged);
	}

}
