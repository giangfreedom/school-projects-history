package newtest;

import java.util.LinkedList;

public class Room {
	private int[] neighbor;
	private int value;
	private int x_cord;
	private int y_cord;
	
	public Room (int valueinput, int [] arrneighbor, int x, int y){
		neighbor = arrneighbor;
		value = valueinput;	
		x_cord = x;
		y_cord = y;
	}
	
	public int getvalue(){
		return value;
	}
	public int[] GetNeighbor(){
		return neighbor;
	}
	public int getx_cord(){
		return x_cord;
	}
	public int gety_cord(){
		return y_cord;
	}
	
	//public void setvalue(int roomNumber){
	//	value = roomNumber;
	//}
	
}
