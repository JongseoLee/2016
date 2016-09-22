package com.js.ens.license.encryption;

import com.js.ens.license.mac.MacAddress;

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
	}
	
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MainAES LApp = new MainAES();
		LApp.initAES();
		//LicenseKey.ens => 고유키 정보
		//MAC_Info.txt => 장비맥어드레스 정보
		
		
		
		
		String key = "aes256-test-key!!";       // key는 16자 이상
		//String key = ""
		//String key = "test";       // key는 16자 이상
		AES256 aes256 = new AES256(key);

		String text = "암호화되지 않은 문자입니다.";
		String encText = aes256.aesEncode(text);
		String decText = aes256.aesDecode(encText);

		System.out.println("암호화할 문자 : " + text);
		System.out.println("암호화된 문자 : " + encText);
		System.out.println("복호화된 문자 : " + decText);
	}
}
