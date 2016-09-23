package com.js.ens.license.encryption;

import java.util.ArrayList;

import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.ParserDefault;
import com.js.util.myUtil;

public class MakeASCII {
	
	private ArrayList<String> keyList_16 = new ArrayList<String>();
	private String key = "";
	
	public MakeASCII() {
		// TODO Auto-generated constructor stub
	}
	
	public void readLicenseKey(){
		ArrayList<String> fileDataList = new ArrayList<String>();
		String progPath = System.getProperty("user.dir");
		String licenseKeyPath = myUtil.setPath(myUtil.setPath(progPath, "configuration"),"Key.ens");
		Reader reader = new Reader(licenseKeyPath);
		reader.running();
		fileDataList = reader.getFileDataList();
		this.splitLicenseKey(fileDataList);
	}
	
	public void splitLicenseKey(ArrayList<String> fileDataList){
		ParserDefault obj = new ParserDefault();
		obj.setFileDataList(fileDataList);
		keyList_16 = obj.splitLineData(0, 4, 0);
	}
	
	public void trans_Key(){
		key = "";
		for(int i = 0;i<keyList_16.size();i++){
			int intVal = Integer.parseInt(keyList_16.get(i), 16);
			char charVal = (char) intVal;
			key += charVal;
			//System.out.println("The character is: "+charVal);
		}
		
		System.out.println(key);
	}
	
	public void trans_Key16(String inputKey){
		String progPath = System.getProperty("user.dir");
		String licenseKeyPath = myUtil.setPath(progPath, "LicenseKey.ens");
		String keyPath = myUtil.setPath(progPath, "Key.ens");
		Reader reader = new Reader(licenseKeyPath);
		reader.running();
		String licenseKey = reader.getFileDataList().get(0);
		
		String outKey = "";
		for(int i = 0; i<licenseKey.length(); i++){
			//System.out.println();
			int ch=(int)licenseKey.charAt( i );
			String s4="00"+Integer.toHexString( ch );
			outKey += s4.trim();
			//System.out.println(i + "output->"+s4); // String to Hex
		}
		
		ArrayList<String> outKeyList = new ArrayList<String>();
		outKeyList.add(outKey);
		
		Writer writer = new Writer(keyPath,outKeyList);
		writer.running();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MakeASCII obj = new MakeASCII();
		obj.readLicenseKey();
		obj.trans_Key();
		obj.trans_Key16("ENS-license-key!");
		
		
		
	}

}
