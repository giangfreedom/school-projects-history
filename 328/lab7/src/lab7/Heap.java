package lab7;

public class Heap {
	/*
	class Node{
		private Node child;
		private Node parents;
		private int Data;
	}
	private Node root;
	private int count;
	private int height;
	*/
	private int load;
	private int[] array;
	private int UD = (int) Math.pow(2, 30);
	
	public Heap(int capacity){
		load = 0;
		array = new int[capacity + 1];
		for(int i = 0; i < array.length; i++){
			array[i] = UD;
		}
	}
	
	public void insert (int x){
		// check for heap is full
		if(load == array.length - 1){
			int [] newArray = new int [array.length * 2];
			for(int hx = 0; hx < newArray.length; hx++){
				newArray[hx] = UD;
			}
			for(int h = 0; h < array.length; h++){
				newArray[h] = array[h];
			}
			array = newArray;
		}
		load++;
		int hole = load;
		for(; hole > 1 && array[hole/2] > x; hole/= 2){
			array[hole] = array[hole/2];
		}
		array[hole] = x;
	}
	
	public int pop (){
		// check for empty heap
		if(load == 0){
			System.out.println("heap is empty return 0");
			return 0;
		}
		int top = array[1];
		array[1] = array[load];
		percolate_down(1);
		load--;
		return top;
	}
	
	public void percolate_down(int index){
		int child;
		int tmp = array[index];
		for(;(child = 2*index) <= load; index = child){
			
			if(child != load && array[child + 1] < array[child] && array[child + 1] != UD){
				child++;
			}
			
			if(array[child] < tmp && array[child] != UD){
				array[index] = array[child];
				array[child] = tmp;
			}			
		}
	}
	
	public void Buildheap(int [] arr){
		int i;
		int mycounter = arr.length;
		for(int x = 0; x < mycounter; x++){
			array[x+1] = arr[x];
			load++;
		}
		
		for(i = load/2; i > 0; i--){
			percolate_down(i);
		}		
	}
	
	public void Display(){
		for(int f = 1; f < array.length; f++){
			System.out.print(array[f]+" ");
		}
		System.out.println();
	}
	
	public void HeapSort(){
		int counter = load;
		int [] myArr = new int[load];
		int counter1 = myArr.length;
		
		for(int j = 0; j < counter; j++){
			myArr[j] = pop();
		}
		for(int jx = 0; jx < counter1; jx++){
			System.out.print(myArr[jx] +" ");
		}
		System.out.println();
	}
	
}
