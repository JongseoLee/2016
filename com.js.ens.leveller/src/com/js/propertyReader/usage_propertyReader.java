package com.js.propertyReader;

public class usage_propertyReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertyReader p_reader = PropertyReader.getInstance();
		System.out.println(p_reader.getProperty("key1"));
		System.out.println(p_reader.getProperty("key2"));
		System.out.println(p_reader.getProperty("key3"));
	}

}
