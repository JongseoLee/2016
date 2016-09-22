package com.js.ens.license.mac;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MacAddress {
	private InetAddress ip = null;
	private NetworkInterface netif = null;
	private String IPAddress = "";
	private String AdapterName = "";
	private String MacAddress = "";
	
	public MacAddress() {
		// TODO Auto-generated constructor stub
	}
	
	public void init(){
		// 로컬 IP취득
		
		//InetAddress ip = InetAddress.getLocalHost();
		
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.IPAddress = ip.getHostAddress();
		//System.out.println("IP : " + ip.getHostAddress());

		
		// 네트워크 인터페이스 취득
		try {
			netif = NetworkInterface.getByInetAddress(ip);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 네트워크 인터페이스가 NULL이 아니면
		if (netif != null) {
			// 네트워크 인터페이스 표시명 출력
			this.AdapterName = netif.getDisplayName();
			//System.out.print(netif.getDisplayName() + " : ");

			// 맥어드레스 취득
			byte[] mac = null;
			try {
				mac = netif.getHardwareAddress();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 맥어드레스 출력
			for (byte b : mac) {
				this.MacAddress = this.MacAddress + String.format("%02X", b);
				//System.out.printf(" %02X ", b);
			}
			//System.out.println();
		}
	}
	
	

	public String getMacAddress() {
		return MacAddress;
	}

	public String getAdapterName() {
		return AdapterName;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MacAddress obj = new MacAddress();
		obj.init();
		
		System.out.println(obj.getAdapterName());
		System.out.println(obj.getMacAddress());
	}

}
