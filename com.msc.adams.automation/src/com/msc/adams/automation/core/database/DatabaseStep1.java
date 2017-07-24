package com.msc.adams.automation.core.database;

import java.util.ArrayList;

import com.msc.adams.automation.datas.Blade;
import com.msc.adams.automation.datas.Linkage;
import com.msc.adams.automation.datas.Windshield;
import com.msc.adams.automation.datas.factory.BladeFactory;
import com.msc.adams.automation.datas.factory.LinkageFactory;
import com.msc.adams.automation.datas.factory.WindshieldFactory;

public class DatabaseStep1 {

	private ArrayList<Blade> SymmetricSameBasicList;
	private ArrayList<Blade> SymmetricDifferentBasicList;
	private ArrayList<Blade> NonSymmetricOuterBasicList;
	private ArrayList<Blade> NonSymmetricInnerBasicList;
	/*
	private ArrayList<Blade> SymmetricList;
	private ArrayList<Blade> NonSymmetricInnerList;
	private ArrayList<Blade> NonSymmetricOuterList;
	// */
	//private ArrayList<Blade> BladeType3List;
	//private ArrayList<Blade> BladeType4List;
	
	private ArrayList<Linkage> SerialParallelList;
	private ArrayList<Linkage> SerialOpposedList;
	private ArrayList<Linkage> ModuleTandemList;
	private ArrayList<Linkage> SerialTandemList;
	
	private ArrayList<Windshield> WindshieldList;

	private BladeFactory BladeFactorySymmetricSameBasicObjImpl;
	private BladeFactory BladeFactorySymmetricDifferentBasicObjImpl;
	private BladeFactory BladeFactoryNonSymmetricOuterBasicObjImpl;
	private BladeFactory BladeFactoryNonSymmetricInnerBasicObjImpl;
	/*
	private BladeFactory BladeFactorySymmetricObjImpl;
	private BladeFactory BladeFactoryNonSymmetricInnerObjImpl;
	private BladeFactory BladeFactoryNonSymmetricOuterObjImpl;
	// */
	private LinkageFactory LinkageFactorySerialParallelObjImpl;
	private LinkageFactory LinkageFactorySerialOpposedObjImpl;
	private LinkageFactory LinkageFactoryModuleTandemObjImpl;
	private LinkageFactory LinkageFactorySerialTandemObjImpl;
	
	private WindshieldFactory WindshieldFactoryObjImpl;
	
	//DB µ•¿Ã≈Õ
	private String BladeType;
	private String SelectedBladeDBName;
	private String SelectedBladeDBPath;
	private Blade SelectedBladeObj = null;
	
	private String LinkageType;
	private String SelectedLinkageDBName;
	private String SelectedLinkageDBPath;
	private Linkage SelectedLinkageObj = null;
	// update for mid seminar(17.01.18)
	private String SelectedLinkageDBModelPath;

	private String WindshieldType;
	private String SelectedWindshieldDBName;
	private String SelectedWindshieldDBPath;
	private Windshield SelectedWindshieldObj = null;
	private String Radius;
	private String X;
	private String Y;
	private String Z;
	
	public DatabaseStep1() {
		// TODO Auto-generated constructor stub
		
		this.SymmetricSameBasicList = new ArrayList<Blade>();
		this.SymmetricDifferentBasicList = new ArrayList<Blade>();
		this.NonSymmetricOuterBasicList = new ArrayList<Blade>();
		this.NonSymmetricInnerBasicList = new ArrayList<Blade>();
		
		/*
		this.SymmetricList = new ArrayList<Blade>();
		this.NonSymmetricInnerList = new ArrayList<Blade>();
		this.NonSymmetricOuterList = new ArrayList<Blade>();
		// */
		
		this.SerialParallelList = new ArrayList<Linkage>();
		this.SerialOpposedList = new ArrayList<Linkage>();
		this.ModuleTandemList = new ArrayList<Linkage>();
		this.SerialTandemList = new ArrayList<Linkage>();
		
		this.WindshieldList = new ArrayList<Windshield>();
		
		this.BladeFactorySymmetricSameBasicObjImpl = new BladeFactory(); 
		this.BladeFactorySymmetricDifferentBasicObjImpl = new BladeFactory();
		this.BladeFactoryNonSymmetricOuterBasicObjImpl = new BladeFactory();
		this.BladeFactoryNonSymmetricInnerBasicObjImpl = new BladeFactory();
		/*
		this.BladeFactorySymmetricObjImpl = new BladeFactory();
		this.BladeFactoryNonSymmetricInnerObjImpl = new BladeFactory();
		this.BladeFactoryNonSymmetricOuterObjImpl = new BladeFactory();
		//*/
		
		this.LinkageFactorySerialParallelObjImpl = new LinkageFactory();
		this.LinkageFactorySerialOpposedObjImpl = new LinkageFactory();
		this.LinkageFactoryModuleTandemObjImpl = new LinkageFactory();
		this.LinkageFactorySerialTandemObjImpl = new LinkageFactory();
		
		this.WindshieldFactoryObjImpl = new WindshieldFactory();
	}

	
	
	
	
	/*
	public ArrayList<Blade> getSymmetricList() {
		return SymmetricList;
	}

	public void setSymmetricList(ArrayList<Blade> SymmetricList) {
		this.SymmetricList = SymmetricList;
	}

	public ArrayList<Blade> getNonSymmetricInnerList() {
		return NonSymmetricInnerList;
	}

	public void setNonSymmetricInnerList(ArrayList<Blade> NonSymmetricInnerList) {
		this.NonSymmetricInnerList = NonSymmetricInnerList;
	}
	
	public ArrayList<Blade> getNonSymmetricOuterList() {
		return NonSymmetricOuterList;
	}

	public void setNonSymmetricOuterList(ArrayList<Blade> NonSymmetricOuterList) {
		this.NonSymmetricOuterList = NonSymmetricOuterList;
	}
	// */
	public ArrayList<Blade> getSymmetricSameBasicList() {
		return SymmetricSameBasicList;
	}

	public void setSymmetricSameBasicList(ArrayList<Blade> symmetricSameBasicList) {
		SymmetricSameBasicList = symmetricSameBasicList;
	}

	public ArrayList<Blade> getSymmetricDifferentBasicList() {
		return SymmetricDifferentBasicList;
	}

	public void setSymmetricDifferentBasicList(ArrayList<Blade> symmetricDifferentBasicList) {
		SymmetricDifferentBasicList = symmetricDifferentBasicList;
	}
 
	public ArrayList<Blade> getNonSymmetricOuterBasicList() {
		return NonSymmetricOuterBasicList;
	}
 
	public void setNonSymmetricOuterBasicList(ArrayList<Blade> nonSymmetricOuterBasicList) {
		NonSymmetricOuterBasicList = nonSymmetricOuterBasicList;
	}
 
	public ArrayList<Blade> getNonSymmetricInnerBasicList() {
		return NonSymmetricInnerBasicList;
	}
 
	public void setNonSymmetricInnerBasicList(ArrayList<Blade> nonSymmetricInnerBasicList) {
		NonSymmetricInnerBasicList = nonSymmetricInnerBasicList;
	}
	
	public ArrayList<Linkage> getSerialParallelList() {
		return SerialParallelList;
	}

	public void setSerialParallelList(ArrayList<Linkage> SerialParallelList) {
		this.SerialParallelList = SerialParallelList;
	}

	public ArrayList<Linkage> getSerialOpposedList() {
		return SerialOpposedList;
	}

	public void setSerialOpposedList(ArrayList<Linkage> SerialOpposedList) {
		this.SerialOpposedList = SerialOpposedList;
	}

	public ArrayList<Linkage> getModuleTandemList() {
		return ModuleTandemList;
	}

	public void setModuleTandemList(ArrayList<Linkage> ModuleTandemList) {
		this.ModuleTandemList = ModuleTandemList;
	}

	public ArrayList<Linkage> getSerialTandemList() {
		return SerialTandemList;
	}

	public void setSerialTandemList(ArrayList<Linkage> SerialTandemList) {
		this.SerialTandemList = SerialTandemList;
	}
	
	/*
	public BladeFactory getBladeFactorySymmetricObjImpl() {
		return BladeFactorySymmetricObjImpl;
	}

	public void setBladeFactorySymmetricObjImpl(BladeFactory BladeFactorySymmetricObjImpl) {
		this.BladeFactorySymmetricObjImpl = BladeFactorySymmetricObjImpl;
	}

	public BladeFactory getBladeFactoryNonSymmetricInnerObjImpl() {
		return BladeFactoryNonSymmetricInnerObjImpl;
	}

	public void setBladeFactoryNonSymmetricInnerObjImpl(BladeFactory BladeFactoryNonSymmetricInnerObjImpl) {
		this.BladeFactoryNonSymmetricInnerObjImpl = BladeFactoryNonSymmetricInnerObjImpl;
	}

	public BladeFactory getBladeFactoryNonSymmetricOuterObjImpl() {
		return BladeFactoryNonSymmetricOuterObjImpl;
	}

	public void setBladeFactoryNonSymmetricOuterObjImpl(BladeFactory BladeFactoryNonSymmetricOuterObjImpl) {
		this.BladeFactoryNonSymmetricOuterObjImpl = BladeFactoryNonSymmetricOuterObjImpl;
	}
	// */
	public BladeFactory getBladeFactorySymmetricSameBasicObjImpl() {
		return BladeFactorySymmetricSameBasicObjImpl;
	}
 
	public void setBladeFactorySymmetricSameBasicObjImpl(BladeFactory bladeFactorySymmetricSameBasicObjImpl) {
		BladeFactorySymmetricSameBasicObjImpl = bladeFactorySymmetricSameBasicObjImpl;
	}
 
	public BladeFactory getBladeFactorySymmetricDifferentBasicObjImpl() {
		return BladeFactorySymmetricDifferentBasicObjImpl;
	}
 
	public void setBladeFactorySymmetricDifferentBasicObjImpl(BladeFactory bladeFactorySymmetricDifferentBasicObjImpl) {
		BladeFactorySymmetricDifferentBasicObjImpl = bladeFactorySymmetricDifferentBasicObjImpl;
	}
 
	public BladeFactory getBladeFactoryNonSymmetricOuterBasicObjImpl() {
		return BladeFactoryNonSymmetricOuterBasicObjImpl;
	}
 
	public void setBladeFactoryNonSymmetricOuterBasicObjImpl(BladeFactory bladeFactoryNonSymmetricOuterBasicObjImpl) {
		BladeFactoryNonSymmetricOuterBasicObjImpl = bladeFactoryNonSymmetricOuterBasicObjImpl;
	}
 
	public BladeFactory getBladeFactoryNonSymmetricInnerBasicObjImpl() {
		return BladeFactoryNonSymmetricInnerBasicObjImpl;
	}
 
	public void setBladeFactoryNonSymmetricInnerBasicObjImpl(BladeFactory bladeFactoryNonSymmetricInnerBasicObjImpl) {
		BladeFactoryNonSymmetricInnerBasicObjImpl = bladeFactoryNonSymmetricInnerBasicObjImpl;
	}

	public LinkageFactory getLinkageFactorySerialParallelObjImpl() {
		return LinkageFactorySerialParallelObjImpl;
	}

	public void setLinkageFactorySerialParallelObjImpl(LinkageFactory LinkageFactorySerialParallelObjImpl) {
		this.LinkageFactorySerialParallelObjImpl = LinkageFactorySerialParallelObjImpl;
	}

	public LinkageFactory getLinkageFactorySerialOpposedObjImpl() {
		return LinkageFactorySerialOpposedObjImpl;
	}

	public void setLinkageFactorySerialOpposedObjImpl(LinkageFactory LinkageFactorySerialOpposedObjImpl) {
		this.LinkageFactorySerialOpposedObjImpl = LinkageFactorySerialOpposedObjImpl;
	}

	public LinkageFactory getLinkageFactoryModuleTandemObjImpl() {
		return LinkageFactoryModuleTandemObjImpl;
	}

	public void setLinkageFactoryModuleTandemObjImpl(LinkageFactory LinkageFactoryModuleTandemObjImpl) {
		this.LinkageFactoryModuleTandemObjImpl = LinkageFactoryModuleTandemObjImpl;
	}

	public LinkageFactory getLinkageFactorySerialTandemObjImpl() {
		return LinkageFactorySerialTandemObjImpl;
	}

	public void setLinkageFactorySerialTandemObjImpl(LinkageFactory LinkageFactorySerialTandemObjImpl) {
		this.LinkageFactorySerialTandemObjImpl = LinkageFactorySerialTandemObjImpl;
	}
	
	public String getSelectedLinkageDBModelPath() {
		return SelectedLinkageDBModelPath;
	}

	public void setSelectedLinkageDBModelPath(String selectedLinkageDBModelPath) {
		SelectedLinkageDBModelPath = selectedLinkageDBModelPath;
	}
	
	public String getBladeType() {
		return BladeType;
	}

	public void setBladeType(String bladeType) {
		this.BladeType = bladeType;
	}

	public String getSelectedBladeDBName() {
		return SelectedBladeDBName;
	}

	public void setSelectedBladeDBName(String selectedBladeDBName) {
		this.SelectedBladeDBName = selectedBladeDBName;
	}
	
	public Blade getSelectedBladeObj() {
		return SelectedBladeObj;
	}

	public void setSelectedBladeObj(Blade selectedBladeObj) {
		this.SelectedBladeObj = selectedBladeObj;
	}
	
	public String getLinkageType() {
		return LinkageType;
	}

	public void setLinkageType(String linkageType) {
		this.LinkageType = linkageType;
	}

	public String getSelectedLinkageDBName() {
		return SelectedLinkageDBName;
	}

	public void setSelectedLinkageDBName(String selectedLinkageDBName) {
		this.SelectedLinkageDBName = selectedLinkageDBName;
	}
	
	public Linkage getSelectedLinkageObj() {
		return SelectedLinkageObj;
	}

	public void setSelectedLinkageObj(Linkage selectedLinkageObj) {
		this.SelectedLinkageObj = selectedLinkageObj;
	}

	public String getSelectedBladeDBPath() {
		return SelectedBladeDBPath;
	}

	public void setSelectedBladeDBPath(String selectedBladeDBPath) {
		this.SelectedBladeDBPath = selectedBladeDBPath;
	}

	public String getSelectedLinkageDBPath() {
		return SelectedLinkageDBPath;
	}

	public void setSelectedLinkageDBPath(String selectedLinkageDBPath) {
		this.SelectedLinkageDBPath = selectedLinkageDBPath;
	}
	
	public String getWindshieldType() {
		return WindshieldType;
	}

	public void setWindshieldType(String windshieldType) {
		this.WindshieldType = windshieldType;
	}

	public String getSelectedWindshieldDBName() {
		return SelectedWindshieldDBName;
	}
	
	public void setSelectedWindshieldDBName(String selectedWindshieldDBName) {
		this.SelectedWindshieldDBName = selectedWindshieldDBName;
	}

	public String getSelectedWindshieldDBPath() {
		return SelectedWindshieldDBPath;
	}

	public void setSelectedWindshieldDBPath(String selectedWindshieldDBPath) {
		this.SelectedWindshieldDBPath = selectedWindshieldDBPath;
	}

	public Windshield getSelectedWindshieldObj() {
		return SelectedWindshieldObj;
	}

	public void setSelectedWindshieldObj(Windshield selectedWindshieldObj) {
		this.SelectedWindshieldObj = selectedWindshieldObj;
	}
	
	public String getRadius() {
		return Radius;
	}

	public void setRadius(String radius) {
		this.Radius = radius;
	}

	public String getX() {
		return X;
	}

	public void setX(String x) {
		this.X = x;
	}

	public String getY() {
		return Y;
	}

	public void setY(String y) {
		this.Y = y;
	}

	public String getZ() {
		return Z;
	}

	public void setZ(String z) {
		this.Z = z;
	}

	public ArrayList<Windshield> getWindshieldList() {
		return WindshieldList;
	}

	public void setWindshieldList(ArrayList<Windshield> windshieldList) {
		this.WindshieldList = windshieldList;
	}

	public WindshieldFactory getWindshieldFactoryObjImpl() {
		return WindshieldFactoryObjImpl;
	}

	public void setWindshieldFactoryObjImpl(WindshieldFactory windshieldFactoryObjImpl) {
		this.WindshieldFactoryObjImpl = windshieldFactoryObjImpl;
	}

}
