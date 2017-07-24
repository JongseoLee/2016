package com.msc.adams.automation.core;

import java.util.ArrayList;


public class MessageWindow {
	private ArrayList<String> MessageLog;
	public static final String Info =     "[Info]      ";
	public static final String Error =    "[Error]    ";
	public static final String Warnning = "[Warnnig] ";
	
	
	public MessageWindow() {
		// TODO Auto-generated constructor stub
		this.MessageLog = new ArrayList<String>();
		//this.textbox = med.getTextMessageWindow();
	}
	
	private String PrintMessage(){
		String allMsg = "";
		for(String line : this.MessageLog){
			allMsg += line+"\n";
		}
		return allMsg;
	}
	
	public String addMessage(String msg,String type){
		String msgType = "";
		if(type.equals(this.Info)){
			msgType = this.Info;
		}else if(type.equals(this.Error)){
			msgType = this.Error;
		}else if(type.equals(this.Warnning)){
			msgType = this.Warnning;
		}
		String fullMessage = msgType + msg;
		this.MessageLog.add(fullMessage);
		return this.PrintMessage();
	}
	
}
