import java.util.LinkedList;

public class Room {
	private int[] neighbor;
	private String[] neighborString;
	private int value;
	private int x_cord;
	private int y_cord;
	private String room_name;
	
	public Room (int valueinput, int [] arrneighbor, int x, int y, String name, String[] neiString){
		neighbor = arrneighbor;
		value = valueinput;	
		x_cord = x;
		y_cord = y;
		room_name = name;
		neighborString = neiString;
	}
	
	public String[] getneighborString(){
		return neighborString;
	}
	public String getroom_name(){
		return room_name;
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
