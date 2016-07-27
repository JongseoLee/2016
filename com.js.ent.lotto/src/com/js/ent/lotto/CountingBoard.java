package com.js.ent.lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CountingBoard {
	public Map<Integer,Integer> boardMap = new HashMap<Integer,Integer>();
	
	public CountingBoard() {
		// TODO Auto-generated constructor stub
		this.initBoardMap();
	}
	
	public void initBoardMap(){
		for(int i=1;i<=45;i++){
			this.boardMap.put(i, 0);
		}
	}

	public void updateCountingBoard(int key){
		if(this.boardMap.containsKey(key)){
			int value = this.boardMap.get(key);
			value = value + 1;
			this.boardMap.put(key, value);
		}
	}
	
	public Map<Integer,Integer> getCountingBoardData(ArrayList<Round> roundList){
		for(Round obj : roundList){
			for(int key : obj.getNumList()){
				this.updateCountingBoard(key);
			}
			this.updateCountingBoard(obj.getBonusNum());
		}
		return this.boardMap;
	}
	
	public Map<Integer, Integer> getBoardMap() {
		return boardMap;
	}

	public void setBoardMap(Map<Integer, Integer> boardMap) {
		this.boardMap = boardMap;
	}
	
}
