package com.js.ens.license.encryption;

import com.js.ens.license.mac.MacAddress;
import com.js.util.myUtil;

public class MainAES {

	
	
	public MainAES() {
		// TODO Auto-generated constructor stub
	}
	
	public void initAES(){
		MacAddress obj = new MacAddress();
		obj.init();
		String macName = obj.getAdapterName();
		String macAddress = obj.getMacAddress();
		System.out.println(macName.length());
		System.out.println(macAddress.length());
		System.out.println(macAddress);
	}
	
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MainAES LApp = new MainAES();
		LApp.initAES();
		
		
		//String key = "aes256-test-key!!";       // key�� 16�� �̻�
		String key = "ENS-license-key!";
		AES256 aes256 = new AES256(key);

		String t1 = "2016-09-01_2016-09-30";
		String t2 = "34E6D7083A75";

		String encText1 = aes256.aesEncode(t1);
		String decText1 = aes256.aesDecode(encText1);
		
		String encText2 = aes256.aesEncode(t2);
		String decText2 = aes256.aesDecode(encText2);
		
		
		System.out.println("��ȣȭ�� ���� : " + t1);
		System.out.println("��ȣȭ�� ���� : " + encText1);
		System.out.println("��ȣȭ�� ���� : " + decText1);
		System.out.println("��ȣȭ�� ���� : " + t2);
		System.out.println("��ȣȭ�� ���� : " + encText2);
		System.out.println("��ȣȭ�� ���� : " + decText2);
	}
}
