package com.js.engine.license;

import java.util.Scanner;

import com.js.engine.license.keygen.KeyGen;
import com.js.engine.license.licensegen.LicenseGen;

public class MainController {

	public MainController() {
		// TODO Auto-generated constructor stub
	}

	public static void ShowTitle(){
		System.out.println("===================================");
		System.out.println("*                                 *");
		System.out.println("*         License Generator       *");
		System.out.println("*               ver.1             *");
		System.out.println("*                                 *");
		System.out.println("*                         2016.09 *");
		System.out.println("===================================");
	}
	
	public static void ShowMenu(){
		System.out.println(" [Menu List] ");
		System.out.println("1. Create private Key");
		System.out.println("2. Read private Key");
		System.out.println("3. Create License File");
		System.out.println("4. Read License File");
		System.out.println("5. Exit");
		System.out.println("Menu : ");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String userInput = "";
		Scanner scan = new Scanner(System.in);
		
		String key = "";
		ShowTitle();
		while(true){
			ShowMenu();
			userInput = scan.nextLine();
			if(userInput.trim().equals("1")){
				KeyGen obj = new KeyGen();
				obj.trans_Key16();
			}else if(userInput.trim().equals("2")){
				KeyGen obj = new KeyGen();
				obj.readLicenseKey();
				obj.trans_Key();
			}else if(userInput.trim().equals("3")){
				
				KeyGen obj = new KeyGen();
				obj.readLicenseKey();
				obj.trans_Key();
				key = obj.getKey();
				
				LicenseGen obj2 = new LicenseGen();
				obj2.enc_running(key);
				
			}else if(userInput.trim().equals("4")){
				KeyGen obj = new KeyGen();
				obj.readLicenseKey();
				obj.trans_Key();
				key = obj.getKey();
				
				LicenseGen obj2 = new LicenseGen();
				obj2.dec_runngin(key);
			}else if(userInput.trim().equals("5")){
				System.out.println("BYE BYE !!!!");
				break;
			}else {
				System.out.println("Error - Please, Enter the menu nubmer!");
			}
			System.out.println("\n");
		}
	}
}

















