package com.js.util;

import java.io.File;
import java.util.ArrayList;

import com.js.io.Writer;

public class CreateFileList {

	public CreateFileList() {
		// TODO Auto-generated constructor stub
	}
	
	public void setLeveller(){
		
	}
	
	public void setHRolling3d(){
		String OfficePath_c = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\consquent";
		String OfficePath_i = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\individual";
		String HomePath_c = "G:\\06.ENS2_2016\\eclipse\\userModule\\consquent";
		String HomePath_i = "G:\\06.ENS2_2016\\eclipse\\userModule\\individual";
		
		//String s_path_c = OfficePath_c;
		//String s_path_i = OfficePath_i;
		String s_path_c = HomePath_c;
		String s_path_i = HomePath_i;
		
		
		String f1_c = myUtil.setPath(s_path_c,"f1");
		String f2_c = myUtil.setPath(s_path_c,"f2");
		String f3_c = myUtil.setPath(s_path_c,"f3");
		String f4_c = myUtil.setPath(s_path_c,"f4");
		String f5_c = myUtil.setPath(s_path_c,"f5");
		String f6_c = myUtil.setPath(s_path_c,"f6");
		String f7_c = myUtil.setPath(s_path_c,"f7");

		String f1rolls_c = myUtil.setPath(f1_c, "rolls");
		String f2rolls_c = myUtil.setPath(f2_c, "rolls");
		String f3rolls_c = myUtil.setPath(f3_c, "rolls");
		String f4rolls_c = myUtil.setPath(f4_c, "rolls");
		String f5rolls_c = myUtil.setPath(f5_c, "rolls");
		String f6rolls_c = myUtil.setPath(f6_c, "rolls");
		String f7rolls_c = myUtil.setPath(f7_c, "rolls");
		
		String f1_i = myUtil.setPath(s_path_i,"f1");
		String f2_i = myUtil.setPath(s_path_i,"f2");
		String f3_i = myUtil.setPath(s_path_i,"f3");
		String f4_i = myUtil.setPath(s_path_i,"f4");
		String f5_i = myUtil.setPath(s_path_i,"f5");
		String f6_i = myUtil.setPath(s_path_i,"f6");
		String f7_i = myUtil.setPath(s_path_i,"f7");

		String f1rolls_i = myUtil.setPath(f1_i, "rolls");
		String f2rolls_i = myUtil.setPath(f2_i, "rolls");
		String f3rolls_i = myUtil.setPath(f3_i, "rolls");
		String f4rolls_i = myUtil.setPath(f4_i, "rolls");
		String f5rolls_i = myUtil.setPath(f5_i, "rolls");
		String f6rolls_i = myUtil.setPath(f6_i, "rolls");
		String f7rolls_i = myUtil.setPath(f7_i, "rolls");
		
		String f1Name_c = myUtil.setPath(s_path_c,"f1.filelist");
		String f2Name_c = myUtil.setPath(s_path_c,"f2.filelist");
		String f3Name_c = myUtil.setPath(s_path_c,"f3.filelist");
		String f4Name_c = myUtil.setPath(s_path_c,"f4.filelist");
		String f5Name_c = myUtil.setPath(s_path_c,"f5.filelist");
		String f6Name_c = myUtil.setPath(s_path_c,"f6.filelist");
		String f7Name_c = myUtil.setPath(s_path_c,"f7.filelist");
		
		String f1Name_i = myUtil.setPath(s_path_i,"f1.filelist");
		String f2Name_i = myUtil.setPath(s_path_i,"f2.filelist");
		String f3Name_i = myUtil.setPath(s_path_i,"f3.filelist");
		String f4Name_i = myUtil.setPath(s_path_i,"f4.filelist");
		String f5Name_i = myUtil.setPath(s_path_i,"f5.filelist");
		String f6Name_i = myUtil.setPath(s_path_i,"f6.filelist");
		String f7Name_i = myUtil.setPath(s_path_i,"f7.filelist");
		
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		ArrayList<String> fList = new ArrayList<String>();
		
		// consquent
		for(File f : myUtil.getDirFileList(f1_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f1rolls_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj = new Writer(f1Name_c,fList);
		obj.running();
		fList.clear();
		
		for(File f : myUtil.getDirFileList(f2_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f2rolls_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj2 = new Writer(f2Name_c,fList);
		obj2.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f3_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f3rolls_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj3 = new Writer(f3Name_c,fList);
		obj3.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f4_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f4rolls_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj4 = new Writer(f4Name_c,fList);
		obj4.running();
		fList.clear();
		
		
		
		for(File f : myUtil.getDirFileList(f5_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f5rolls_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj5 = new Writer(f5Name_c,fList);
		obj5.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f6_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f6rolls_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj6 = new Writer(f6Name_c,fList);
		obj6.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f7_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f7rolls_c)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj7 = new Writer(f7Name_c,fList);
		obj7.running();
		fList.clear();
		

		// individual
		for(File f : myUtil.getDirFileList(f1_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f1rolls_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj_i = new Writer(f1Name_i,fList);
		obj_i.running();
		fList.clear();
		
		for(File f : myUtil.getDirFileList(f2_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f2rolls_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj2_i = new Writer(f2Name_i,fList);
		obj2_i.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f3_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f3rolls_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj3_i = new Writer(f3Name_i,fList);
		obj3_i.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f4_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f4rolls_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj4_i = new Writer(f4Name_i,fList);
		obj4_i.running();
		fList.clear();
		
		
		
		for(File f : myUtil.getDirFileList(f5_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f5rolls_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj5_i = new Writer(f5Name_i,fList);
		obj5_i.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f6_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f6rolls_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj6_i = new Writer(f6Name_i,fList);
		obj6_i.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f7_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f7rolls_i)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj7_i = new Writer(f7Name_i,fList);
		obj7_i.running();
		fList.clear();
		
	}
	
	public void setFileList(String folder,String path){
		ArrayList<String> fList = new ArrayList<String>();
		
		for(File f : myUtil.getDirFileList(folder)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		
		Writer obj = new Writer(path,fList);
		obj.running();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateFileList obj = new CreateFileList();
		obj.setHRolling3d();
	}

}
