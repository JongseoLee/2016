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
		
		
		//String key = "aes256-test-key!!";       // key는 16자 이상
		String key = "ENS-license-key!";
		AES256 aes256 = new AES256(key);

		String t1 = "2016-09-01_2016-09-30";
		String t2 = "34E6D7083A75";

		String encText1 = aes256.aesEncode(t1);
		String decText1 = aes256.aesDecode(encText1);
		
		String encText2 = aes256.aesEncode(t2);
		String decText2 = aes256.aesDecode(encText2);
		
		
		System.out.println("암호화할 문자 : " + t1);
		System.out.println("암호화된 문자 : " + encText1);
		System.out.println("복호화된 문자 : " + decText1);
		System.out.println("암호화할 문자 : " + t2);
		System.out.println("암호화된 문자 : " + encText2);
		System.out.println("복호화된 문자 : " + decText2);
	}
}
