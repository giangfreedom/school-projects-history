package lab9;

import java.util.LinkedList;

public class MyNode {
	private int[] neighbor;
	private boolean mark;
	private int value;
	private MyNode parents;
	
	public MyNode(int valueinput, int [] arrneighbor){
		neighbor = arrneighbor;
		value = valueinput;	
		mark = false;
		parents = null;
	}
	public int getvalue(){
		return value;
	}
	
	public boolean getmark(){
		return mark;
	}
	
	public void setmark(boolean mybool){
		mark = mybool;
	}
	
	public int[] GetNeighbor(){
		return neighbor;
	}
	
	public void setparents(MyNode parent){
		parents = parent;
	}
	
	public MyNode getparents(){
		return parents;
	}
}
