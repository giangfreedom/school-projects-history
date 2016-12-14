package lab6;

public class Hash {
	public int[] HashTable;
	public int[] CountTable;
	
	public Hash(int size){
		HashTable = new int[size];
		CountTable = new int[size];
	}
	
	public void fill(){
		for(int t = 0; t < HashTable.length; t++){
			HashTable[t] = (int) Math.pow(2, 30);
		}
		for(int t = 0; t < CountTable.length; t++){
			CountTable[t] = (int) Math.pow(2, 30);
		}
	}
	public void display(){
		System.out.println("Hash ");
		for(int t1 = 0; t1 < HashTable.length; t1++){
			System.out.print(HashTable[t1] + " |");
		}
		System.out.println();
		System.out.println("Count ");
		for(int t2 = 0; t2 < CountTable.length; t2++){
			System.out.print(CountTable[t2] + " |");
		}
		System.out.println();
		System.out.println("index ");
		for(int t3 = 0; t3 < HashTable.length; t3++){
			System.out.print(t3 + "         ");
		}
		System.out.println();
	}
	
	public double hash(int x){
		double value = 0;
		String s = ""+x;
		s.trim();
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt((s.length()-1) - i);
			value = value + (c * Math.pow(37, i));
		}
		return value;
	}
	
	public int add(int x){
		int p = HashTable.length;
		int size = p;
		int UD = (int) Math.pow(2, 30);
		
		int index = (int)hash(x) % p; //note p is the size of table
		int i = 0; //i is the current probe index
		int next_index, new_count;
		while(true){
			next_index = ((index + i*i)) % size;
			
			if(CountTable[next_index] == UD){ //this cell is unoccupied
				HashTable[next_index] = x;
				CountTable[next_index] = 1;
				return 1;
			}
			if(x == HashTable[next_index]){
				new_count = CountTable[next_index] + 1;
				CountTable[next_index] = new_count;
				return new_count;
			}
			
			if(i > 1000){
				break;
			}
			i++;
		}
	return 0;
	}
	
	public int subtract(int x){
		int p = HashTable.length;
		int size = p;
		int UD = (int) Math.pow(2, 30);
		
		int index = (int)hash(x) % p; //note p is the size of table
		int i = 0; //i is the current probe index
		int next_index, new_count;
		while(true){
			next_index = ((index + i*i)) % size;
			
			if(CountTable[next_index] == UD){ //this cell is unoccupied the item is not in the table
				return -1;
			}
			if(x == HashTable[next_index]){
				new_count = CountTable[next_index] - 1;
				CountTable[next_index] = new_count;
				return new_count;
			}
			
			if(i > 1000){
				break;
			}
			i++;
		}
	return 0;
	}
	
	public int count(int x){
		int p = HashTable.length;
		int size = p;
		int UD = (int) Math.pow(2, 30);
		
		int index = (int)hash(x) % p; //note p is the size of table
		int i = 0; //i is the current probe index
		int next_index, new_count;
		while(true){
			next_index = ((index + i*i)) % size;
			
			if(CountTable[next_index] == UD){ //this cell is unoccupied			
				return -1;
			}
			if(x == HashTable[next_index]){				
				return CountTable[next_index];
			}
			
			if(i > 1000){
				break;
			}
			i++;
		}
	return 0;
	}
	
	public int modehash(int a[], int n){
		int UD = (int) Math.pow(2, 30);
		int mode = UD;
		int max_count = 0;
		for(int i = 0; i < n; i++){
			add(a[i]);
		}
		for(int j = 0; j < CountTable.length; j++){
			if(CountTable[j] != UD && CountTable[j] > max_count){
				max_count = CountTable[j];
				mode = HashTable[j];
			}
		}
		return mode;
	}
}
