package com.msc.adams.automation.image;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.parser.ParserDefault;
import com.msc.util.myUtil;

public class ImagePath {
	/*
	BT_1 = Sysmmetric=Sysmmetric
	BT_2 = NonSysmmetric=NonSysmmetric
	LT_1 = SerialParaller=SerialParaller
	LT_2 = SerialOpposed=SerialOpposed
	LT_3 = ModuleTandem=ModuleTandem
	LT_4 = SerialTandem=SerialTandem
	 */
	private MainController MC = MainController.getInstance();
	private static ImagePath instance = new ImagePath();
	public static ImagePath getInstatnce(){
		return instance;
	}
	
	private ArrayList<String> ImagePathList_Symmetric_Same_Basic;
	private Map<String,String> ImagePathMap_Symmetric_Same_Basic;	
	private ArrayList<String> ImagePathList_Symmetric_Different_Basic;
	private Map<String,String> ImagePathMap_Symmetric_Different_Basic;
	private ArrayList<String> ImagePathList_NonSymmetric_Outer_Basic;
	private Map<String,String> ImagePathMap_NonSymmetric_Outer_Basic;
	private ArrayList<String> ImagePathList_NonSymmetric_Inner_Basic;
	private Map<String,String> ImagePathMap_NonSymmetric_Inner_Basic;
	
	/*
	private ArrayList<String> ImagePathList_Symmetric;
	private Map<String,String> ImagePathMap_Symmetric;
	private ArrayList<String> ImagePathList_NonSymmetric_inner;
	private Map<String,String> ImagePathMap_NonSymmetric_inner;
	private ArrayList<String> ImagePathList_NonSymmetric_outer;
	private Map<String,String> ImagePathMap_NonSymmetric_outer;
	// */
	/*
	private ArrayList<String> ImagePathList_BT3;
	private Map<String,String> ImagePathMap_BT3;
	private ArrayList<String> ImagePathList_BT4;
	private Map<String,String> ImagePathMap_BT4;
	//*/
	private ArrayList<String> ImagePathList_SerialParallel;
	private Map<String,String> ImagePathMap_SerialParallel;
	private ArrayList<String> ImagePathList_SerialOpposed;
	private Map<String,String> ImagePathMap_SerialOpposed;
	private ArrayList<String> ImagePathList_ModuleTandem;
	private Map<String,String> ImagePathMap_ModuleTandem;
	private ArrayList<String> ImagePathList_SerialTandem;
	private Map<String,String> ImagePathMap_SerialTandem;
	
	private ArrayList<String> ImagePathList_PartSerialParallel;
	private Map<String,String> ImagePathMap_PartSerialParallel;
	private ArrayList<String> ImagePathList_PartSerialOpposed;
	private Map<String,String> ImagePathMap_PartSerialOpposed;
	private ArrayList<String> ImagePathList_PartModuleTandem;
	private Map<String,String> ImagePathMap_PartModuleTandem;
	private ArrayList<String> ImagePathList_PartSerialTandem;
	private Map<String,String> ImagePathMap_PartSerialTandem;
	
	
	public static final String BladeNonSymmetric_Inner_Basic = DatabaseFolderName.BladeNonSymmetric_Inner_Basic;
	private String NonSymmetric_Inner_Basic_path;
	public static final String BladeNonSymmetric_Outer_Basic = DatabaseFolderName.BladeNonSymmetric_Outer_Basic;
	private String NonSymmetric_Outer_Basic_path;
	public static final String BladeSymmetric_Different_Basic = DatabaseFolderName.BladeSymmetric_Different_Basic;
	private String Symmetric_Different_Basic_path;
	public static final String BladeSymmetric_Same_Basic = DatabaseFolderName.BladeSymmetric_Same_Basic;
	private String Symmetric_Same_Basic_path;
	
	/*
	public static final String BladeSymmetric = DatabaseFolderName.BladeSymmetric;
	private String Symmetric_path;
	public static final String BladeNonSymmetric_inner = DatabaseFolderName.BladeNonSymmetric_inner;
	private String NonSymmetric_inner_path;
	public static final String BladeNonSymmetric_outer = DatabaseFolderName.BladeNonSymmetric_outer;
	private String NonSymmetric_outer_path;
	//*/
	/*
	public static final String Blade_type3 = "BladeType3";
	private String BT3_path;
	public static final String Blade_type4 = "BladeType4";
	private String BT4_path;
	// */
	public static final String LinkageSerialParallel = DatabaseFolderName.LinkageSerialParallel;
	private String SerialParallel_path;
	public static final String LinkageSerialOpposed = DatabaseFolderName.LinkageSerialOpposed;
	private String SerialOpposed_path;
	public static final String LinkageModuleTandem = DatabaseFolderName.LinkageModuleTandem;
	private String ModuleTandem_path;
	public static final String LinkageSerialTandem = DatabaseFolderName.LinkageSerialTandem;
	private String SerialTandem_path;
	
	public static final String PartSerialParallel = "PartSerialParallel";
	private String PartSerialParallel_path;
	public static final String PartSerialOpposed = "PartSerialOpposed";
	private String PartSerialOpposed_path;
	public static final String PartModuleTandem = "PartModuleTandem";
	private String PartModuleTandem_path;
	public static final String PartSerialTandem = "PartSerialTandem";
	private String PartSerialTandem_path;
	
	
	private String ImageFolderPath;
	public ImagePath() {
		// TODO Auto-generated constructor stub
		//this.ImageFolderPath = myUtil.setPath(System.getProperty("user.dir"), DatabaseFolderName.msck_Image);
		this.ImageFolderPath = myUtil.setPath(MC.getAppPath(), DatabaseFolderName.msck_Image);
		this.NonSymmetric_Inner_Basic_path = myUtil.setPath(this.ImageFolderPath, this.BladeNonSymmetric_Inner_Basic);
		this.NonSymmetric_Outer_Basic_path = myUtil.setPath(this.ImageFolderPath, this.BladeNonSymmetric_Outer_Basic);
		this.Symmetric_Different_Basic_path = myUtil.setPath(this.ImageFolderPath, this.BladeSymmetric_Different_Basic);
		this.Symmetric_Same_Basic_path = myUtil.setPath(this.ImageFolderPath, this.BladeSymmetric_Same_Basic);
		/* 
		this.Symmetric_path = myUtil.setPath(this.ImageFolderPath, this.BladeSymmetric);
		this.NonSymmetric_inner_path = myUtil.setPath(this.ImageFolderPath, this.BladeNonSymmetric_inner);
		this.NonSymmetric_outer_path = myUtil.setPath(this.ImageFolderPath, this.BladeNonSymmetric_outer);
		// */
		//this.BT3_path = myUtil.setPath(this.ImageFolderPath, this.Blade_type3);
		//this.BT4_path = myUtil.setPath(this.ImageFolderPath, this.Blade_type4);
		
		this.SerialParallel_path = myUtil.setPath(this.ImageFolderPath, this.LinkageSerialParallel);
		this.SerialOpposed_path = myUtil.setPath(this.ImageFolderPath, this.LinkageSerialOpposed);
		this.ModuleTandem_path = myUtil.setPath(this.ImageFolderPath, this.LinkageModuleTandem);
		this.SerialTandem_path = myUtil.setPath(this.ImageFolderPath, this.LinkageSerialTandem);
		
		this.PartSerialParallel_path = myUtil.setPath(this.ImageFolderPath, this.PartSerialParallel);
		this.PartSerialOpposed_path = myUtil.setPath(this.ImageFolderPath, this.PartSerialOpposed);
		this.PartModuleTandem_path = myUtil.setPath(this.ImageFolderPath, this.PartModuleTandem);
		this.PartSerialTandem_path = myUtil.setPath(this.ImageFolderPath, this.PartSerialTandem);
			
		this.ImagePathList_Symmetric_Same_Basic = new ArrayList<String>();
		this.ImagePathMap_Symmetric_Same_Basic = new HashMap<String,String>();
		this.ImagePathList_Symmetric_Different_Basic = new ArrayList<String>();
		this.ImagePathMap_Symmetric_Different_Basic = new HashMap<String,String>();
		this.ImagePathList_NonSymmetric_Outer_Basic = new ArrayList<String>();
		this.ImagePathMap_NonSymmetric_Outer_Basic = new HashMap<String,String>();
		this.ImagePathList_NonSymmetric_Inner_Basic = new ArrayList<String>();
		this.ImagePathMap_NonSymmetric_Inner_Basic = new HashMap<String,String>();
		/*
		this.ImagePathList_Symmetric = new ArrayList<String>();
		this.ImagePathMap_Symmetric = new HashMap<String,String>();
		this.ImagePathList_NonSymmetric_inner = new ArrayList<String>();
		this.ImagePathMap_NonSymmetric_inner = new HashMap<String,String>();
		this.ImagePathList_NonSymmetric_outer = new ArrayList<String>();
		this.ImagePathMap_NonSymmetric_outer = new HashMap<String,String>();
		// */
		this.ImagePathList_SerialParallel = new ArrayList<String>();
		this.ImagePathMap_SerialParallel = new HashMap<String,String>();
		this.ImagePathList_SerialOpposed = new ArrayList<String>();
		this.ImagePathMap_SerialOpposed = new HashMap<String,String>();
		this.ImagePathList_ModuleTandem = new ArrayList<String>();
		this.ImagePathMap_ModuleTandem = new HashMap<String,String>();
		this.ImagePathList_SerialTandem = new ArrayList<String>();
		this.ImagePathMap_SerialTandem = new HashMap<String,String>();
		
		this.ImagePathList_PartSerialParallel = new ArrayList<String>();
		this.ImagePathMap_PartSerialParallel = new HashMap<String,String>();
		this.ImagePathList_PartSerialOpposed = new ArrayList<String>();
		this.ImagePathMap_PartSerialOpposed = new HashMap<String,String>();
		this.ImagePathList_PartModuleTandem = new ArrayList<String>();
		this.ImagePathMap_PartModuleTandem = new HashMap<String,String>();
		this.ImagePathList_PartSerialTandem = new ArrayList<String>();
		this.ImagePathMap_PartSerialTandem = new HashMap<String,String>();
		
		this.InitPath();
	}
	
	private void InitPath(){
		for(File f : myUtil.getDirFileList(this.Symmetric_Different_Basic_path)){
			if(f.isFile()){
				String fPath = f.getAbsolutePath();
				String fName = ParserDefault.splitLineData(f.getName(),"\\.").get(0);
				this.ImagePathList_Symmetric_Different_Basic.add(f.getAbsolutePath());
				this.ImagePathMap_Symmetric_Different_Basic.put(fName, fPath);
			}
		}
		for(File f : myUtil.getDirFileList(this.Symmetric_Same_Basic_path)){
			if(f.isFile()){
				String fPath = f.getAbsolutePath();
				String fName = ParserDefault.splitLineData(f.getName(),"\\.").get(0);
				this.ImagePathList_Symmetric_Same_Basic.add(f.getAbsolutePath());
				this.ImagePathMap_Symmetric_Same_Basic.put(fName, fPath);
			}
		}
		for(File f : myUtil.getDirFileList(this.NonSymmetric_Inner_Basic_path)){
			if(f.isFile()){
				String fPath = f.getAbsolutePath();
				String fName = ParserDefault.splitLineData(f.getName(),"\\.").get(0);
				this.ImagePathList_NonSymmetric_Inner_Basic.add(f.getAbsolutePath());
				this.ImagePathMap_NonSymmetric_Inner_Basic.put(fName, fPath);
			}
		}
		for(File f : myUtil.getDirFileList(this.NonSymmetric_Outer_Basic_path)){
			if(f.isFile()){
				String fPath = f.getAbsolutePath();
				String fName = ParserDefault.splitLineData(f.getName(),"\\.").get(0);
				this.ImagePathList_NonSymmetric_Outer_Basic.add(f.getAbsolutePath());
				this.ImagePathMap_NonSymmetric_Outer_Basic.put(fName, fPath);
			}
		}
		
		for(File f : myUtil.getDirFileList(this.SerialParallel_path)){
			if(f.isFile()){
				String fPath = f.getAbsolutePath();
				String fName = ParserDefault.splitLineData(f.getName(),"\\.").get(0);
				this.ImagePathList_SerialParallel.add(f.getAbsolutePath());
				this.ImagePathMap_SerialParallel.put(fName, fPath);
			}
		}
		for(File f : myUtil.getDirFileList(this.SerialOpposed_path)){
			if(f.isFile()){
				String fPath = f.getAbsolutePath();
				String fName = ParserDefault.splitLineData(f.getName(),"\\.").get(0);
				this.ImagePathList_SerialOpposed.add(f.getAbsolutePath());
				this.ImagePathMap_SerialOpposed.put(fName, fPath);
			}
		}
		for(File f : myUtil.getDirFileList(this.ModuleTandem_path)){
			if(f.isFile()){
				String fPath = f.getAbsolutePath();
				String fName = ParserDefault.splitLineData(f.getName(),"\\.").get(0);
				this.ImagePathList_ModuleTandem.add(f.getAbsolutePath());
				this.ImagePathMap_ModuleTandem.put(fName, fPath);
			}
		}
		for(File f : myUtil.getDirFileList(this.SerialTandem_path)){
			if(f.isFile()){
				String fPath = f.getAbsolutePath();
				String fName = ParserDefault.splitLineData(f.getName(),"\\.").get(0);
				this.ImagePathList_SerialTandem.add(f.getAbsolutePath());
				this.ImagePathMap_SerialTandem.put(fName, fPath);
			}
		}
		
		for(File f : myUtil.getDirFileList(this.PartSerialParallel_path)){
			if(f.isFile()){
				String fPath = f.getAbsolutePath();
				String fName = ParserDefault.splitLineData(f.getName(),"\\.").get(0);
				this.ImagePathList_PartSerialParallel.add(f.getAbsolutePath());
				this.ImagePathMap_PartSerialParallel.put(fName, fPath);
			}
		}
		for(File f : myUtil.getDirFileList(this.PartSerialOpposed_path)){
			if(f.isFile()){
				String fPath = f.getAbsolutePath();
				String fName = ParserDefault.splitLineData(f.getName(),"\\.").get(0);
				this.ImagePathList_PartSerialOpposed.add(f.getAbsolutePath());
				this.ImagePathMap_PartSerialOpposed.put(fName, fPath);
			}
		}
		for(File f : myUtil.getDirFileList(this.PartModuleTandem_path)){
			if(f.isFile()){
				String fPath = f.getAbsolutePath();
				String fName = ParserDefault.splitLineData(f.getName(),"\\.").get(0);
				this.ImagePathList_PartModuleTandem.add(f.getAbsolutePath());
				this.ImagePathMap_PartModuleTandem.put(fName, fPath);
			}
		}
		for(File f : myUtil.getDirFileList(this.PartSerialTandem_path)){
			if(f.isFile()){
				String fPath = f.getAbsolutePath();
				String fName = ParserDefault.splitLineData(f.getName(),"\\.").get(0);
				this.ImagePathList_PartSerialTandem.add(f.getAbsolutePath());
				this.ImagePathMap_PartSerialTandem.put(fName, fPath);
			}
		}
		
	}
	
	public String getImagePath(String type, String fileName){
		String path = "";
		if(type.equals(ImagePath.BladeSymmetric_Different_Basic)){
			if(this.ImagePathMap_Symmetric_Different_Basic.containsKey(fileName)){
				return this.ImagePathMap_Symmetric_Different_Basic.get(fileName);
			}
		}else if(type.equals(ImagePath.BladeSymmetric_Same_Basic)){
			if(this.ImagePathMap_Symmetric_Same_Basic.containsKey(fileName)){
				return this.ImagePathMap_Symmetric_Same_Basic.get(fileName);
			}
		}else if(type.equals(ImagePath.BladeNonSymmetric_Inner_Basic)){
			if(this.ImagePathMap_NonSymmetric_Inner_Basic.containsKey(fileName)){
				return this.ImagePathMap_NonSymmetric_Inner_Basic.get(fileName);
			}
		}
		else if(type.equals(ImagePath.BladeNonSymmetric_Outer_Basic)){
			if(this.ImagePathMap_NonSymmetric_Outer_Basic.containsKey(fileName)){
				return this.ImagePathMap_NonSymmetric_Outer_Basic.get(fileName);
			}
		}
		/*
		if(type.equals(ImagePath.BladeSymmetric)){
			if(this.ImagePathMap_Symmetric.containsKey(fileName)){
				return this.ImagePathMap_Symmetric.get(fileName);
			}
		}else if(type.equals(ImagePath.BladeNonSymmetric_inner)){
			if(this.ImagePathMap_NonSymmetric_inner.containsKey(fileName)){
				return this.ImagePathMap_NonSymmetric_inner.get(fileName);
			}
		}
		else if(type.equals(ImagePath.BladeNonSymmetric_outer)){
			if(this.ImagePathMap_NonSymmetric_outer.containsKey(fileName)){
				return this.ImagePathMap_NonSymmetric_outer.get(fileName);
			}
		}
		// */
		
		else if(type.equals(ImagePath.LinkageSerialParallel)){
			if(this.ImagePathMap_SerialParallel.containsKey(fileName)){
				return this.ImagePathMap_SerialParallel.get(fileName);
			}
		}else if(type.equals(ImagePath.LinkageSerialOpposed)){
			if(this.ImagePathMap_SerialOpposed.containsKey(fileName)){
				return this.ImagePathMap_SerialOpposed.get(fileName);
			}
		}else if(type.equals(ImagePath.LinkageModuleTandem)){
			if(this.ImagePathMap_ModuleTandem.containsKey(fileName)){
				return this.ImagePathMap_ModuleTandem.get(fileName);
			}
		}else if(type.equals(ImagePath.LinkageSerialTandem)){
			if(this.ImagePathMap_SerialTandem.containsKey(fileName)){
				return this.ImagePathMap_SerialTandem.get(fileName);
			}
		}
		
		else if(type.equals(ImagePath.PartSerialParallel)){
			if(this.ImagePathMap_PartSerialParallel.containsKey(fileName)){
				return this.ImagePathMap_PartSerialParallel.get(fileName);
			}
		}else if(type.equals(ImagePath.PartSerialOpposed)){
			if(this.ImagePathMap_PartSerialOpposed.containsKey(fileName)){
				return this.ImagePathMap_PartSerialOpposed.get(fileName);
			}
		}else if(type.equals(ImagePath.PartModuleTandem)){
			if(this.ImagePathMap_PartModuleTandem.containsKey(fileName)){
				return this.ImagePathMap_PartModuleTandem.get(fileName);
			}
		}else if(type.equals(ImagePath.PartSerialTandem)){
			if(this.ImagePathMap_PartSerialTandem.containsKey(fileName)){
				return this.ImagePathMap_PartSerialTandem.get(fileName);
			}
		}
		
		
		return path;
	}

	
	public static void main(String [] args){
		
		ImagePath obj = ImagePath.getInstatnce();
		//System.out.println(obj.getImagePath(ImagePath.BladeSymmetric,"0"));
	}
}
