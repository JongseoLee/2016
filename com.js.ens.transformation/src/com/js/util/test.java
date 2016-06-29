package com.js.util;

import java.io.File;
import java.util.ArrayList;

import com.js.io.Writer;
import com.js.parser.ParserDefault;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}
	
	public void setFileList(){
	
		System.out.println("path : "+System.getProperty("user.dir"));
		String f1 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f1";
		String f1rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f1\\rolls";
		String f2 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f2";
		String f2rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f2\\rolls";
		String f3 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f3";
		String f3rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f3\\rolls";
		String f4 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f4";
		String f4rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f4\\rolls";
		String f5 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f5";
		String f5rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f5\\rolls";
		String f6 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f6";
		String f6rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f6\\rolls";
		String f7 = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f7";
		String f7rolls="D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f7\\rolls";
		
		String f1Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f1.filelist";
		String f2Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f2.filelist";
		String f3Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f3.filelist";
		String f4Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f4.filelist";
		String f5Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f5.filelist";
		String f6Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f6.filelist";
		String f7Name = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\userModule\\f7.filelist";
		
		ArrayList<String> fList = new ArrayList<String>();
		
		for(File f : myUtil.getDirFileList(f1)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f1rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj = new Writer(f1Name,fList);
		obj.running();
		fList.clear();
		
		for(File f : myUtil.getDirFileList(f2)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f2rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj2 = new Writer(f2Name,fList);
		obj2.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f3)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f3rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj3 = new Writer(f3Name,fList);
		obj3.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f4)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f4rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj4 = new Writer(f4Name,fList);
		obj4.running();
		fList.clear();
		
		
		
		for(File f : myUtil.getDirFileList(f5)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f5rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj5 = new Writer(f5Name,fList);
		obj5.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f6)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f6rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj6 = new Writer(f6Name,fList);
		obj6.running();
		fList.clear();
		
		
		for(File f : myUtil.getDirFileList(f7)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add(f.getName());
			}
		}
		for(File f : myUtil.getDirFileList(f7rolls)){
			if(f.isFile()){
				System.out.println(f.getName());
				fList.add("rolls\\"+f.getName());
			}
		}
		
		Writer obj7 = new Writer(f7Name,fList);
		obj7.running();
		fList.clear();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "|\t\t\t|";
		System.out.println(str);
		String newStr = str.replace("\t", "a");
		System.out.println(newStr);

	}
	
}
