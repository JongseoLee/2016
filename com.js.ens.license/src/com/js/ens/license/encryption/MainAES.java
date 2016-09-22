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
		//LicenseKey.ens => ����Ű ����
		//MAC_Info.txt => ���ƾ�巹�� ����
		
		
		
		
		String key = "aes256-test-key!!";       // key�� 16�� �̻�
		//String key = ""
		//String key = "test";       // key�� 16�� �̻�
		AES256 aes256 = new AES256(key);

		String text = "��ȣȭ���� ���� �����Դϴ�.";
		String encText = aes256.aesEncode(text);
		String decText = aes256.aesDecode(encText);

		System.out.println("��ȣȭ�� ���� : " + text);
		System.out.println("��ȣȭ�� ���� : " + encText);
		System.out.println("��ȣȭ�� ���� : " + decText);
	}
}
