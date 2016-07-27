package com.js.ent.lotto;

import java.util.ArrayList;

public class Searching {
	
	public Searching() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Round> searching_round(int start, int end, ArrayList<Round> roundList){
		ArrayList<Round> result = new ArrayList<Round>();
		
		for(Round obj : roundList){
			int no = obj.getNo();
			if(no >= start && no <= end){
				result.add(obj);
			}
		}
		return result;
	}

}
