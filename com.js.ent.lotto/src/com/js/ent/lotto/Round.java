package com.js.ent.lotto;

import java.util.ArrayList;
import java.util.Collections;

import com.js.parser.ParserDefault;

public class Round {
	private int No = 0;
	private ArrayList<Integer> numList = new ArrayList<Integer>();
	private int	bonusNum = 0;
	
	public Round() {
		// TODO Auto-generated constructor stub
	}
	
	public void setAllData(String line){
		ArrayList<String> tokens = new ArrayList<String>();
		tokens = ParserDefault.splitLineData(line, ",");
		this.No = Integer.parseInt(tokens.get(0));
		this.bonusNum = Integer.parseInt(tokens.get(tokens.size()-1));
		numList.add(Integer.parseInt(tokens.get(1)));
		numList.add(Integer.parseInt(tokens.get(2)));
		numList.add(Integer.parseInt(tokens.get(3)));
		numList.add(Integer.parseInt(tokens.get(4)));
		numList.add(Integer.parseInt(tokens.get(5)));
		numList.add(Integer.parseInt(tokens.get(6)));
		Collections.sort(numList);
	}

	public int getNo() {
		return No;
	}

	public void setNo(int no) {
		No = no;
	}

	public ArrayList<Integer> getNumList() {
		return numList;
	}

	public void setNumList(ArrayList<Integer> numList) {
		this.numList = numList;
	}

	public int getBonusNum() {
		return bonusNum;
	}

	public void setBonusNum(int bonusNum) {
		this.bonusNum = bonusNum;
	}
	
	
}
