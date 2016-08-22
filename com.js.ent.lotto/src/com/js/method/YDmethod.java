package com.js.method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.js.ent.lotto.MyRound;
import com.js.io.Writer;
import com.js.util.myUtil;

public class YDmethod {
	private ArrayList<MyRound> gameList = new ArrayList<MyRound>();
	private ArrayList<MyRound> tempList = new ArrayList<MyRound>();
	private ArrayList<String> duplNumList = new ArrayList<String>();
	private Map <String,Integer> dupNumMap = new HashMap<String,Integer>();
	
	//private static final int TryNumber = 65000000;
	  private static final int TryNumber = 30000000;
	//private static final int TryNumber =  1000000;
	public YDmethod() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(){
		this.SimResult();
		//this.checkDuplicated();
		
		//myUtil.printMap(this.dupNumMap);
	
	}
	
	public void running2(){
		String file1 = "d:\\result1.csv";
		String file2 = "d:\\result2.csv";
		String file3 = "d:\\result3.csv";
		
	}
	
	public ArrayList<Integer> genNumberList(){
		ArrayList<Integer> numList = new ArrayList<Integer>();
		
		int arr[]= new int[6];
		int i,j,k;
		int x;
		//System.out.println("* ������ ��ȣ *");
		//System.out.println("----------------------------------");
		for(i=0;i<6;i++){   // ������ 6�� ����
			x=(int)((Math.random()*45)+1);  // �����Լ� ȣ�� (���� 1-45)
			arr[i]=x;
			for (j=0;j<i;j++){
				if(arr[i] == arr[j]){  // ������ ���� ������ ����� ���� ��
					x=(int)((Math.random()*45)+1);
					arr[i] = x;    // �ٽ� ���� ����
					i = i - 1;    // �ٽ� ÷���� ���� ���ڰ� �ִ°� ��
					break;
				}
			}
		}
		//* ������������ ���� */
		for(i=0; i<6; i++){        // ���� ����
			for(j=0; j<=i; j++){
				if(arr[i]<=arr[j]){
					k=arr[i];
					arr[i]=arr[j];
					arr[j]=k;
				}
			}
		}
		//* ���� ��� */
		for(i=0; i<6; i++){
				numList.add(arr[i]);
		}
		
		return numList;
    }
	
	public void SimResult(){
		System.out.println("��ȣ ���� ����");
		for(int i=0; i<this.TryNumber;i++){
			MyRound obj = new MyRound();
			obj.setAllData(this.genNumberList());
			this.gameList.add(obj);
			this.tempList.add(obj);
		}
		System.out.println("��ȣ ���� ��");
		// CSV ���Ϸ� ����
		this.saveGenResult();
	}
	
	public void saveGenResult(){
		System.out.println("���Ϸ����� ����");
		ArrayList<String> outputlist = new ArrayList<String>();
		for(MyRound mr : this.gameList){
			outputlist.add(mr.getStrResult());
		}
		
		Writer obj = new Writer(myUtil.setPath("d:\\", "result.csv"),outputlist);
		obj.running();
		System.out.println("���Ϸ����� ��");
	}
	
	public void checkDuplicated(){
		System.out.println("üũ ����");
		ArrayList<String> duplNumList = new ArrayList<String>();
		Map <String,Integer> dupNumMap = new HashMap<String,Integer>();
		
		for(MyRound mr : this.tempList){
			int duplicatedNumber = 0;
			String base = mr.getStrResult();
			
			
			for(int i=this.gameList.size()-1 ; i>=0 ; i--){
				if(base.equals(this.gameList.get(i).getStrResult())){
					duplicatedNumber++;
					
					if(duplNumList.contains(base)){
						
					}else{
						duplNumList.add(base);
					}
					
					
					if(dupNumMap.containsKey(base)){
						int num = dupNumMap.get(base)+1;
						dupNumMap.remove(base);
						dupNumMap.put(base, num);
					}else{
						dupNumMap.put(base, 1);
					}
					
					this.gameList.remove(i);
				}
			}
		}
		
		System.out.println("üũ ��");
	}
	
	
	
	public static void main(String [] args){
		/*
		ArrayList<String> outputlist = new ArrayList<String>();
		
		int num = 290000;
		for(int i=0;i<num;i++){
			String str = String.valueOf(i+1)+",";
			outputlist.add(str + genNumber());
		}
		
		Writer obj = new Writer(myUtil.setPath(System.getProperty("user.dir"), "result.csv"),outputlist);
		obj.running();
		*/
	}
}
