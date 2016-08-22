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
		//System.out.println("* 금주의 번호 *");
		//System.out.println("----------------------------------");
		for(i=0;i<6;i++){   // 랜덤수 6개 생성
			x=(int)((Math.random()*45)+1);  // 랜덤함수 호출 (범위 1-45)
			arr[i]=x;
			for (j=0;j<i;j++){
				if(arr[i] == arr[j]){  // 생성된 수와 이전에 저장된 수를 비교
					x=(int)((Math.random()*45)+1);
					arr[i] = x;    // 다시 수를 생성
					i = i - 1;    // 다시 첨부터 같은 숫자가 있는가 비교
					break;
				}
			}
		}
		//* 오름차순으로 정렬 */
		for(i=0; i<6; i++){        // 정렬 조건
			for(j=0; j<=i; j++){
				if(arr[i]<=arr[j]){
					k=arr[i];
					arr[i]=arr[j];
					arr[j]=k;
				}
			}
		}
		//* 최종 출력 */
		for(i=0; i<6; i++){
				numList.add(arr[i]);
		}
		
		return numList;
    }
	
	public void SimResult(){
		System.out.println("번호 추출 시작");
		for(int i=0; i<this.TryNumber;i++){
			MyRound obj = new MyRound();
			obj.setAllData(this.genNumberList());
			this.gameList.add(obj);
			this.tempList.add(obj);
		}
		System.out.println("번호 추출 끝");
		// CSV 파일로 저장
		this.saveGenResult();
	}
	
	public void saveGenResult(){
		System.out.println("파일로저장 시작");
		ArrayList<String> outputlist = new ArrayList<String>();
		for(MyRound mr : this.gameList){
			outputlist.add(mr.getStrResult());
		}
		
		Writer obj = new Writer(myUtil.setPath("d:\\", "result.csv"),outputlist);
		obj.running();
		System.out.println("파일로저장 끝");
	}
	
	public void checkDuplicated(){
		System.out.println("체크 시작");
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
		
		System.out.println("체크 끝");
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
