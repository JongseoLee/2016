package com.js.engine.license.licensegen;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class LicenseGen {
	private String userLicensePeriod = "";
	private String startDate = "";
	private String endDate = "";
	private String userMacAddress = "";
	
	private String encPeriod = "";
	private String encMac = "";
	
	public LicenseGen() {
		// TODO Auto-generated constructor stub
	}
	
	public void enc_running(String key){
		this.readMACInfo();
		AES256 aes256 = null;
		try {
			aes256 = new AES256(key);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			encPeriod = aes256.aesEncode(this.userLicensePeriod);
		} catch (InvalidKeyException | UnsupportedEncodingException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException
				| IllegalBlockSizeException | BadPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			encMac = aes256.aesEncode(this.userMacAddress);
		} catch (InvalidKeyException | UnsupportedEncodingException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.writeLicense();
	}
	
	public void readMACInfo(){
		String progPath = System.getProperty("user.dir");
		String MacInfoPath = myUtil.setPath(progPath, "MAC_Info.txt");
		Reader reader = new Reader(MacInfoPath);
		reader.running();
		this.userLicensePeriod = reader.getFileDataList().get(0);
		this.userMacAddress = reader.getFileDataList().get(1);
	}
	
	public void writeLicense(){
		String progPath = System.getProperty("user.dir");
		String licenseFilePath = myUtil.setPath(progPath, "Licenes.ens");
		ArrayList<String> outDataList = new ArrayList<String>();
		outDataList.add(this.encPeriod);
		outDataList.add(this.encMac);
		Writer writer = new Writer(licenseFilePath,outDataList);
		writer.running();
		
		System.out.println("#=> License file path : "+licenseFilePath);
	}
	
	public void readLicense(){
		String progPath = System.getProperty("user.dir");
		String licenseFilePath = myUtil.setPath(progPath, "Licenes.ens");
		Reader reader = new Reader(licenseFilePath);
		reader.running();
		encPeriod = reader.getFileDataList().get(0);
		encMac = reader.getFileDataList().get(1);
	}
	
	public void dec_runngin(String key){
		this.readLicense();
		AES256 aes256 = null;
		try {
			aes256 = new AES256(key);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.userLicensePeriod = aes256.aesDecode(this.encPeriod);
		} catch (InvalidKeyException | UnsupportedEncodingException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.userMacAddress = aes256.aesDecode(this.encMac);
		} catch (InvalidKeyException | UnsupportedEncodingException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("#=> Period : "+ this.userLicensePeriod);
		System.out.println("#=> Mac Address : "+ this.userMacAddress);
	}
	
}
