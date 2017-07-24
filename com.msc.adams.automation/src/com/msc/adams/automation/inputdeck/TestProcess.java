package com.msc.adams.automation.inputdeck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.msc.io.Reader;
import com.msc.parser.ParserDefault;
import com.msc.util.myUtil;

public class TestProcess {

	public TestProcess() {
		// TODO Auto-generated constructor stub
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String cmd = "'C:\\blank test\\CAESoft\\Altair\\13.0\\hwsolvers\\scripts\\optistruct.bat' -fem D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\170420\\Result\\1_InputdeckForMNF\\PA_ROD\\PA_ROD_mnf_creation.fem";
		
		Map<String,String> RunScriptMap = new HashMap<String,String>();
		
		String msck_config ="D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Config";
		String solvingScriptPath = myUtil.setPath(msck_config, "SolvingScript.ini");
		Reader reader = new Reader(solvingScriptPath);
		reader.running();
		for(String line : reader.getFileDataList()){
			String data = line.trim();
			if(data.contains("=")){
				ArrayList<String> tokens = new ArrayList<String>();
				tokens = ParserDefault.splitLineData(data, "=");
				RunScriptMap.put(tokens.get(0), tokens.get(1));
				/*
				System.out.println("------------");
				System.out.println("key : "+tokens.get(0));
				System.out.println("Value : "+tokens.get(1));
				// */
			}
		}
		
		
		String exePath = RunScriptMap.get("OPT");
		String reExePath = exePath.replace("%HyperWorksPath%", "C:\\blank test\\CAESoft\\Altair\\13.0");
		String fullCMD = reExePath+" "+"-fem D:\\6____Tool_RCP_Eclipse\\eclipse_mars_forMSC\\msck_Workspace\\170420\\Result\\1_InputdeckForMNF\\PA_ROD\\PA_ROD_mnf_creation.fem";
		System.out.println(fullCMD);
		try{
			Process p = null;
			p = Runtime.getRuntime().exec(fullCMD);	
			p.getErrorStream().close();
			p.getInputStream().close();
			p.getOutputStream().close();
			//p.waitFor();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		/*
		try{
		    Process p = Runtime.getRuntime().exec(cmd);
		    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line = null;
		   
		    while((line = br.readLine()) != null){
		        System.out.println(line);
		    }
		}catch(Exception e){
		    System.out.println(e);
		}
		// */
	}

}
