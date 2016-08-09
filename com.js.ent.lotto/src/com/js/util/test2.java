package com.js.util;

import java.util.ArrayList;

public class test2 {
	public ArrayList<location> locObjList = new ArrayList<location>();
	public ArrayList<location> dist_0_List;
	public ArrayList<location> dist_1_List;
	public ArrayList<location> dist_2_List;
	
	  
	
	public test2() {
		// TODO Auto-generated constructor stub
		int name = 1;
		for(int i=0; i<5;i++){
			for(int j=0; j<5; j++){
				location obj = new location(name,i,j);
				locObjList.add(obj);
				name++;
			}
		}
	}
	
	public location findLoc(int name){
		location loc = null;
		for(location o : this.locObjList){
			if(o.getName() == name){
				loc = o;
				break;
			}
		}
		return loc;
	}
	
	public void showLoc(int curLoc){
		
		dist_0_List = new ArrayList<location>();
		dist_1_List = new ArrayList<location>();
		dist_2_List = new ArrayList<location>();
		
		int curX = this.findLoc(curLoc).getX();
		int curY = this.findLoc(curLoc).getY();
		
		for(location o : this.locObjList){
			int destX = o.getX();
			int destY = o.getY();
			
			double distance = Math.pow(Math.abs(curX-destX), 2) + Math.pow(Math.abs(curY-destY), 2);
			//System.out.println(o.getName()+ " || "+distance);
			
			if(distance == 0.0){
				this.dist_0_List.add(o);
			}else if(distance == 1.0){
				this.dist_1_List.add(o);
			}else if(distance == 2.0){
				this.dist_2_List.add(o);
			}
		}
	}
	
	public void printResult(){
		System.out.println("distance = 0");
		for(location o : this.dist_0_List){
			System.out.println("=>" +o.getName());
		}
		System.out.println("distance = 1");
		for(location o : this.dist_1_List){
			System.out.println("=>" +o.getName());
		}
		System.out.println("distance = 2");
		for(location o : this.dist_2_List){
			System.out.println("=>" +o.getName());
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int name = 7;
		
		test2 runTest = new test2();
		runTest.showLoc(name);
		runTest.printResult();
		
		
	}

	
	class location{
		public int name;
		public int x;
		public int y;
		
		public location(int name, int x, int y){
			this.name = name;
			this.x = x;
			this.y = y;
		}

		public int getName() {
			return name;
		}

		public void setName(int name) {
			this.name = name;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}
}
