package com.js.ent.lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.js.io.Reader;
import com.js.method.YDmethod;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class MainTool {
	
	private static MainTool instance = new MainTool();
	public static MainTool getInstance(){
		return instance;
	}
	
	private String dbPath = null;
	private ArrayList<String> dbFileDataList = new ArrayList<String>();
	private ArrayList<Round> RoundList = new ArrayList<Round>();
	private ArrayList<Round> SearchingResultList = new ArrayList<Round>();
	private Map<Integer,Integer> StatisticsTotalMap = new HashMap<Integer,Integer>();
	private int latestNo = 0;
	
	private Searching SearchingObj = new Searching();
	//private CountingBoard CountingObj = new CountingBoard();
	
	public MainTool() {
		// TODO Auto-generated constructor stub
	}
	
	public MainTool(String dbPath) {
		// TODO Auto-generated constructor stub
		this.dbPath = dbPath;
	}
	
	public void LoadingDB(){
		Reader obj = new Reader(this.dbPath);
		obj.running();
		
		this.dbFileDataList = obj.getFileDataList();
		this.dbFileDataList.remove(0);	// column �� ����
		this.latestNo = Integer.parseInt(ParserDefault.splitLineData(this.dbFileDataList.get(0), ",").get(0));
		
		for(String line : this.dbFileDataList){
			//System.out.println(line);
			Round Robj = new Round();
			Robj.setAllData(line);
			RoundList.add(Robj);
		}
	}
	
	private void getSearchingRoundList(int start, int end){
		// start ���� ȸ�� , end ������ ȸ��
		// end value : 0 => latest round
		this.SearchingResultList.clear();
		
		if(end == 0){
			this.SearchingResultList = SearchingObj.searching_round(start, this.latestNo, this.RoundList);
		}else{
			this.SearchingResultList = SearchingObj.searching_round(start, end, this.RoundList);
		}
	}
	
	private void getSearchingRoundList(int period){
		// �ֱ� ��ȸ�� ��ȸ 
		this.SearchingResultList.clear();
		int start = this.latestNo - period;
		int end = this.latestNo;
		this.SearchingResultList = SearchingObj.searching_round(start, end, this.RoundList);
	}
	
	// statistics
	public void getSortingResult(int start, int end){
		CountingBoard CountingObj = new CountingBoard();
		this.StatisticsTotalMap.clear();
		this.getSearchingRoundList(start,end);
		this.StatisticsTotalMap = CountingObj.getCountingBoardData(this.SearchingResultList);
		
		int newEnd = 0;
		if(end == 0){
			newEnd = this.latestNo;
		}else{
			newEnd = end;
		}
		System.out.println("========================================");
		System.out.println("[ Sorting Data("+start+"~"+newEnd+"ȸ) ]");
		this.DisplaySortingData();
	}
	
	public void getSortingResult(int period){
		CountingBoard CountingObj = new CountingBoard();
		this.StatisticsTotalMap.clear();
		this.getSearchingRoundList(period);
		this.StatisticsTotalMap = CountingObj.getCountingBoardData(this.SearchingResultList);
		
		System.out.println("========================================");
		System.out.println("[ Sorting Data(�ֱ� "+period+"ȸ) ]");
		this.DisplaySortingData();
	}
	
	private void DisplaySortingData(){
		String result = "";
		for(int i=1;i<=45;i++){
			result += i+"\t: "+this.StatisticsTotalMap.get(i)+"\t"+this.printBar(this.StatisticsTotalMap.get(i))+"\n";
		}
		System.out.println(result);
		System.out.println("========================================\n\n");
	}
	
	private String printBar(int num){
		String result = "";
		String bar ="��";
		int n = num/2;
		for(int i=0;i<n;i++){
			result += bar;
		}
		return result;
	}
	
	private void YDMethod(){
		YDmethod obj = new YDmethod();
		obj.running();
	}
	
	
	
	
	public ArrayList<Round> getRoundList() {
		return RoundList;
	}

	public void setRoundList(ArrayList<Round> roundList) {
		RoundList = roundList;
	}

	public ArrayList<Round> getSearchingResultList() {
		return SearchingResultList;
	}

	public void setSearchingResultList(ArrayList<Round> searchingResultList) {
		SearchingResultList = searchingResultList;
	}
	
	public int getLatestNo() {
		return latestNo;
	}

	public void setLatestNo(int latestNo) {
		this.latestNo = latestNo;
	}
	
	
	public int DispMenu(){
		int menu = 0;
		System.out.println("++++++++++++++++++++++");
		System.out.println("         �޴�");
		System.out.println("0.����");
		System.out.println("1.�ֱ� ��ȸ�� ��� ����");
		System.out.println("2.���ϴ� �Ⱓ ��� ����");
		System.out.println("3.�ùķ��̼� ��� ����(YD method)");
		System.out.println("++++++++++++++++++++++");
		Scanner scan = new Scanner(System.in);
		menu = scan.nextInt();
		return menu;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dbPath = myUtil.setPath(System.getProperty("user.dir"), "db.csv");
		if(myUtil.checkPath(dbPath)){
			System.out.println("DB Loading completed...");
			//System.out.println(dbPath);	
		}else {
			System.out.println("DB Loading failed...");
		}
		
		MainTool mt = new MainTool(dbPath);
		mt.LoadingDB();
		// �ֱ� ��ȸ��
		mt.getSortingResult(60);
		// n1 ȸ�� ~ n2 ȸ��
		mt.getSortingResult(1, 0);
		
		Scanner scan = new Scanner(System.in);
		int menu = 0;
		while(true){
			menu = mt.DispMenu();
			if(menu == 0){
				System.out.println("����");
				System.exit(0);
			}else if(menu == 1){
				System.out.println("�ֱ� �� ȸ �з� ������ ?");
				int n = scan.nextInt();
				mt.getSortingResult(n);
			}else if(menu == 2){
				System.out.println("���� ȸ�� ?");
				int start = scan.nextInt();
				System.out.println("������ ȸ�� ?( ���� 0 �̸� �ֱ� ȸ�� �ڵ��Է�)");
				int end = scan.nextInt();
				mt.getSortingResult(start,end);
			}else if(menu == 3){
				System.out.println("Start~");
				mt.YDMethod();
				System.out.println("End~");
			}
		}
	
		
		
		
		
	}

}
