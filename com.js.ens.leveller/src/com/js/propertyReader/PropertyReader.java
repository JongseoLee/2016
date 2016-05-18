package com.js.propertyReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import com.js.util.myUtil;

public class PropertyReader implements Serializable {
	private Properties prop;
	private static PropertyReader p_reader = new PropertyReader();
	private String path;
		
	private String InitValuePath = myUtil.setPath(System.getProperty("user.dir"),"InitValue.txt");

	
	public PropertyReader (){
		prop = new Properties();
		try{
			load(System.getProperty("prop.properties"));
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static PropertyReader getInstance(){
		return p_reader;
	}
	
	public InputStream init(String path){
		return super.getClass().getResourceAsStream(path);
	}
	
	public void load(String path) throws IOException{
		this.path = path;
		File file = new File(path);
		prop.load(new FileInputStream(file));
	}
	
	public void clear(){
		prop.clear();
	}
	
	public String getProperty(String key){
		return prop.getProperty(key);
	}
	
	public void setProperty(String key, String value){
		prop.setProperty(key, value);
	}
	
	public void store() throws IOException{
		FileOutputStream fout = new FileOutputStream(new File(path));
		prop.store(fout, null);
		fout.close();
	}

	public String[] getProperties()
	{
		Enumeration _enum = prop.keys();
		String arr[] = null;
		Vector v = new Vector();
		while(_enum.hasMoreElements())
		{
			v.addElement((String)_enum.nextElement());
		}
		arr = new String[v.size()];
		v.copyInto(arr);
		return arr;
	}
	
	/*
	 *
	 *
	==========================================================================
	# Call PropertyReader.
	=>	public PropertyReader p_reader = PropertyReader.getInstance();
	
	# Get Property. 
	=> p_reader.getProperty(KEY);
	
	# PropertyFile( file name : prop.properties || file formay : Key = Value)
	=> procFolder=D:\\RTL_Eclipse\\workspace-linux\\com.msc.posco.rtl\\proc
	# vm Argument
	-Dprop.properties=PATH( D:\\path\\path\\prop.properties )
	==========================================================================
	*  
	*  
	*/
}
