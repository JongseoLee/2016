package com.msc.adams.commandServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.msc.adams.automation.core.MainController;
import com.msc.adams.automation.core.Mediator;
import com.msc.adams.automation.core.MessageWindow;
import com.msc.adams.automation.core.UILabel;
import com.msc.adams.automation.core.database.DatabaseFolderName;
import com.msc.io.Reader;
import com.msc.io.Writer;
import com.msc.parser.ParserDefault;
import com.msc.util.myUtil;

public class ClientBackup implements Runnable {
	private static ClientBackup instance = new ClientBackup();
	public static ClientBackup getInstance(){
		return instance;
	}
	
	private MainController MC = MainController.getInstance();
	//private UILabel LabelDatas = UILabel.getInstatnce();
	private Mediator med = Mediator.getInstance();
	private Job jobObj = Job.getInstance();
	
	
	public boolean isRunning = false;
	
	public static String PORT			= "port";
	public static String IP 			= "ip";
	public static String CMD_readFile 	= "readFile";
	// "C://MSC.Software//Adams//2016//common//mdi.bat aview ru-st i"; 처럼 시작할때만 옵션 필요
	// 현재는 lnk 로실행
	public static String AViewOption	= "AViewOption";	//" aview ru-st i";
	public static String ExeLinkFileName= "ExeLinkFileName";
	
	public static String Step1 = "STEP1-Select Model Process-Blade and Linkage";
	public static String Step1_Geo = "STEP1-Select Model Process-Geo";
	public static String Step1_Sph = "STEP1-Select Model Process-Sph";
	public static String Step3 = "STEP3-Swapping MNF Process";
	public static String Step3_Back = "STEP3-Back";
	public static String Step4_Simulation = "STEP4-Dynamic Analysis Solving";
	public static String Step4_Animation = "STEP4-Dynamic Analysis Animation";
	public static String Step4_Assem = "STEP4_ASSEM-Dynamic Analysis";
	public static String Step4_AssemBin = "STEP4_ASSEMBIN-Dynamic Analysis";
	public static String Step4_DAC = "STEP4_DAC-Dynamic Analysis";
	public static String Step4_Force = "STEP4_FORCE-Dynamic Analysis";
	public static String Step4_NodeInformation = "STEP4_NodeInformation-Dynamic Anlysis";
	public static String Step4_Abort = "STEP4_ABORT-Dynamic Analysis";
	public static String RESTART = "RESTART";
	
	
	private String port;
	private String ip;
	private String cmd_readFile;
	private String AdamsViewOption;
	
	private String AdamsPath;
	private String AdamsExePath;
	private String exeLinkFileName;
	
	private Map<String,String> ClientMap;
	private String ClientPath;
	
	// 소켓을 만들기 위한 인스턴스들
	private Socket socket = null;
	// 소켓에서 스트림을 얻어오기 위한 인스턴스들
	private OutputStream os = null;
	private InputStream is = null;
	private OutputStreamWriter writer = null;
	private BufferedReader br = null;
	
	private String response = "";
	
	private String cmdPath = "";
	private String step = "";

	public ClientBackup() {
		// TODO Auto-generated constructor stub
		this.ClientMap = new HashMap<String,String>();
		this.ClientPath = myUtil.setPath(myUtil.setPath(MC.getAppPath(),DatabaseFolderName.msck_Config),"Client.ini");
		
		this.readClientInfoFile();
		
		this.AdamsPath = MC.getAdamsPath();
		this.AdamsExePath = myUtil.setPath(myUtil.setPath(this.AdamsPath, "common"),"mdi.bat");
		/*
		System.out.println("##############################################");
		System.out.println("Port : "+this.port);
		System.out.println("IP : "+this.ip);
		System.out.println("cmd_readFile : "+this.cmd_readFile);
		System.out.println("AdamsViewOption : "+this.AdamsViewOption);
		System.out.println("exeLinkFileName : "+this.exeLinkFileName);
		System.out.println("Adams Path : "+this.AdamsPath);
		System.out.println("AdamsExepath : "+this.AdamsExePath);
		System.out.println("##############################################");
		// */
	}
	
	
	public void StartAdams(){
		this.port = this.getVar(PORT);
		this.ip = this.getVar(IP);
		this.cmd_readFile = this.getVar(CMD_readFile);
		this.AdamsViewOption = this.getVar(AViewOption);
		this.exeLinkFileName = this.getVar(ExeLinkFileName); 
		// Adams 실행
		this.copyAdamsLib();
		String jobSpace = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.JobSpace);
		String dest_exeLinkPath = myUtil.setPath(jobSpace, this.exeLinkFileName);
		
		try {
			String BatFile = myUtil.setPath(jobSpace, "RunAdamsView.bat");
			String batPath = myUtil.setPath(jobSpace, "RunAdamsView.bat");
			ArrayList<String> ScriptList = new ArrayList<String>();
			ScriptList.add("cd "+jobSpace);
			ScriptList.add(jobSpace.charAt(0)+":");
			// http://www.yunsobi.com/blog/411
			ScriptList.add("rundll32 url.dll,FileProtocolHandler "+dest_exeLinkPath );
			Writer writer = new Writer(batPath); 
			writer.running(ScriptList);  
			
			Process p = Runtime.getRuntime().exec(BatFile);
			
			MC.addMsgWindow("Adams is running", MessageWindow.Info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MC.addMsgWindow("Adams is not running.", MessageWindow.Error);
			System.out.println("Error - StartAdams");
			
		} 
	}
	
	private void copyAdamsLib(){
		String msck_TemplatePath = myUtil.setPath(MC.getAppPath(), DatabaseFolderName.msck_Template);
		String jobSpace = myUtil.setPath(MC.getWorkspace(), DatabaseFolderName.JobSpace);
		String adamsLibPath = myUtil.setPath(msck_TemplatePath, DatabaseFolderName.AdamsLib);
		String ori_exeLinkPath = myUtil.setPath(adamsLibPath, this.exeLinkFileName);
		String dest_exeLinkPath = myUtil.setPath(jobSpace, this.exeLinkFileName);
		String ori_aviewBSPath = myUtil.setPath(adamsLibPath, "aviewBS.cmd");
		String dest_aviewBSPath = myUtil.setPath(jobSpace, "aviewBS.cmd");
		
		myUtil.fileCopy(ori_aviewBSPath, dest_aviewBSPath);
		myUtil.fileCopy(ori_exeLinkPath, dest_exeLinkPath);
		
	}
	
	public void SendCMD(String cmdPath,String step){
		// CMD 전송 
		this.cmdPath = cmdPath;
		this.step = step;
		if(this.step.equals(this.Step1)){
			jobObj.setJobStatus_step1(Job.start);
		}else if(this.step.equals(this.Step4_Simulation)){
			jobObj.setJobStatus_step4(Job.end);
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean requestResult = false;
		
		this.SendtoServer(cmdPath);
		/////////////////////////////////////////////////////	
		//Success
		if(this.response.equals("cmd: 0")){
			requestResult = true;
			final String msg = "Success SendCMD - "+ myUtil.getFileName(this.step);
			
			//JOptionPane.showMessageDialog(null, msg,"Success - Adams CommandServer",JOptionPane.INFORMATION_MESSAGE);
			
			if(this.step.equals(this.Step1)){
				jobObj.setJobStatus_step1(Job.end);
				med.getParentView().getDisplay().asyncExec(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						med.getBtnSaveAllData().setEnabled(true);
						med.getBtnStartSolving().setEnabled(true);
					}
				});
			}
			else if(this.step.equals(this.Step4_Simulation)){
				jobObj.setJobStatus_step4(Job.end);
				med.getParentView().getDisplay().asyncExec(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						UILabel LabelDatas = UILabel.getInstatnce();
						med.getLblStatusValue().setText(LabelDatas.getLabel(UILabel.DONE));
						med.getLblStatusValue().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
						med.getLblStatusValue().setFont(SWTResourceManager.getFont("Arial", 11, SWT.NONE));
						med.getBtnStartSolving().setEnabled(true);
						med.getBtnStartAnimation().setEnabled(true);
						med.getBtnExportResult().setEnabled(true);
						med.getBtnSaveAllData().setEnabled(true);
						
					}
				});
			}else if(this.step.equals(this.Step4_Abort)){
				jobObj.setJobStatus_step4(Job.stop);
				med.getParentView().getDisplay().asyncExec(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						UILabel LabelDatas = UILabel.getInstatnce();
						med.getLblStatusValue().setText(LabelDatas.getLabel(UILabel.STOP));
						med.getLblStatusValue().setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
						med.getLblStatusValue().setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
					}
				});

			}
			med.getParentView().getDisplay().asyncExec(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					String allMsg = MC.getMsgWindow().addMessage(msg, MessageWindow.Info);
					med.getTextMessageWindow().setText(allMsg);
					med.getTextMessageWindow().setSelection(allMsg.length());
					System.out.println(msg);
					
				}
				
			});
			
		
			
			
			
			
		/////////////////////////////////////////////////////	
		//Fail 	
		}else if(this.response.equals("cmd: 1")){
			requestResult = false;
			
			final String msg = "Fail SendCMD - "+myUtil.getFileName(this.step);
			JOptionPane.showMessageDialog(null, msg,"Error - Adams CommandServer",JOptionPane.ERROR_MESSAGE);
			
			if(this.step.equals(this.Step1)){
				jobObj.setJobStatus_step1(Job.fail);
			}
			else if(this.step.equals(this.Step4_Simulation)){
				jobObj.setJobStatus_step4(Job.fail);
				med.getParentView().getDisplay().asyncExec(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						UILabel LabelDatas = UILabel.getInstatnce();
						med.getLblStatusValue().setText(LabelDatas.getLabel(UILabel.FAIL));
						med.getLblStatusValue().setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
						med.getLblStatusValue().setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
						med.getBtnStartSolving().setEnabled(true);
						med.getBtnStartAnimation().setEnabled(false);
						med.getBtnSaveAllData().setEnabled(false);
						med.getBtnExportResult().setEnabled(false);
					}
				});
			}else if(this.step.equals(this.Step4_Abort)){
				jobObj.setJobStatus_step4(Job.fail);
				med.getParentView().getDisplay().asyncExec(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						UILabel LabelDatas = UILabel.getInstatnce();
						med.getLblStatusValue().setText(LabelDatas.getLabel(UILabel.FAIL));
						med.getLblStatusValue().setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
						med.getLblStatusValue().setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
					}
				});

			}
			
			med.getParentView().getDisplay().asyncExec(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					//MC.addMsgWindow(msg, MessageWindow.Info);
					String allMsg = MC.getMsgWindow().addMessage(msg, MessageWindow.Error);
					med.getTextMessageWindow().setText(allMsg);
					med.getTextMessageWindow().setSelection(allMsg.length());
					System.out.println(msg);
				}
				
			});
		}
	}
	
	private void SendtoServer(String cmdPath){
		String commandLine = this.cmd_readFile +"='"+cmdPath+"'";
		System.out.println("CMD : "+commandLine);
		
		try {
			// 서버 소켓을 생성하고, 클라이언트에서 스트림을 받아드릴 소켓을 하나 더 생성합니다.
			socket = new Socket(this.ip,Integer.parseInt(this.port));
			//System.out.println("소켓을 생성하여 서버와 연결하였습니다.");
		} catch (IOException e) {
			System.out.println("소켓 생성 실패");
		}
		/*
		while(true){
			try {
				// 소켓에 스트림을 연결하고, 스트림을 보낼 Writer를 만듭니다.
				os = socket.getOutputStream();
				is = socket.getInputStream();
				writer = new OutputStreamWriter(os);
				br = new BufferedReader(new InputStreamReader(is));
				break;
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("소켓 연결에 실패했습니다.");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		}
		//*/
		
		try {
			// 소켓에 스트림을 연결하고, 스트림을 보낼 Writer를 만듭니다.
			os = socket.getOutputStream();
			is = socket.getInputStream();
			writer = new OutputStreamWriter(os);
			br = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("소켓 연결에 실패했습니다.");
		}
		
		try {
			writer.write(commandLine);
			writer.flush();
			response = br.readLine();
		} catch (IOException e) {
			//e.printStackTrace();
			//System.out.println("데이터 전송에 실패했습니다.");
		}
		
		try {
			socket.close();
			writer.close();
			os.close();
		} catch (IOException e) {
			System.out.println("소켓 닫기에 실패했습니다");
		}
	}
	
	
	private void readClientInfoFile(){
		Reader reader = new Reader(this.ClientPath);
		reader.running();
		for(String line : reader.getFileDataList()){
			String data = line.trim();
			if(data.contains("=")){
				ArrayList<String> tokens = new ArrayList<String>();
				tokens = ParserDefault.splitLineData(data, "=");
				this.ClientMap.put(tokens.get(0), tokens.get(1));
			}
		}
	}
	
	public String getVar(String key){
		if(this.ClientMap.containsKey(key)){
			return this.ClientMap.get(key);
		}else{
			return "no-data";
		}
	}

	
	// get set method
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCmd_readFile() {
		return cmd_readFile;
	}

	public void setCmd_readFile(String cmd_readFile) {
		this.cmd_readFile = cmd_readFile;
	}
	
	public String getAdamsExePath() {
		return AdamsExePath;
	}

	public void setAdamsExePath(String adamsExePath) {
		AdamsExePath = adamsExePath;
	}

	public String getAdamsViewOption() {
		return AdamsViewOption;
	}

	public void setAdamsViewOption(String adamsViewOption) {
		AdamsViewOption = adamsViewOption;
	}
	
	public String getResponse() {
		return response;
	}


	public boolean isRunning() {
		return isRunning;
	}


	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	

	/*
	public void setResponse(String response) {
		this.response = response;
	}
	// */

}
