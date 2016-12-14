package lab3;


import java.util.*;
public class lab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Scanner in1 = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		boolean check = true;
		String userInput;
		int k = 0;
		int n = 0;
		
		// time begin
		
		while(check){
			System.out.println("Enter Q to Quit");
			System.out.println("Enter K to change k value");
			System.out.println("Enter N to change N value");
			System.out.println("Enter C for calculation of time");
			System.out.println("Enter P for print k");
			userInput = in.next();
			if(userInput.charAt(0) == 'q' || userInput.charAt(0) == 'Q'){
				check = false;
			}
			else if(userInput.charAt(0) == 'k' || userInput.charAt(0) == 'K'){
				System.out.println("Please enter K value");
				k = in1.nextInt();
				continue;
			}
			else if(userInput.charAt(0) == 'n' || userInput.charAt(0) == 'N'){
				System.out.println("Please enter N value");
				n = in2.nextInt();
				continue;
			}
			else if(userInput.charAt(0) == 'c' || userInput.charAt(0) == 'C'){
				double startTime = System.nanoTime();
				loop(n,k);
				double estimatedTime = System.nanoTime() - startTime;
				double estimatedTimeInMilSec = estimatedTime / 1000000;
				if(estimatedTimeInMilSec < 1000){
					System.out.println("time used " + estimatedTimeInMilSec + " milliSecond");
				}
				else{
					System.out.println("time used " + (estimatedTimeInMilSec/1000) + " Second");
				}
			}
			else if(userInput.charAt(0) == 'p' || userInput.charAt(0) == 'P'){
				loop1(n,k);
			}
			else{
				System.out.println("invalid input");
				continue;
			}					
		}
	}
	public static void loop(int n, int k){
		int counter = 0;
		// create an int array of size k
		// k is the number of loop
		int[] a = new int[k]; 
		// zero out the array
		for(int j = 0; j < a.length; j++){
			a[j] = 0;
		}
		// loop index keep track of which loop currently being iterated.
		int i = 0;
		//each loop is to iterate n times
		while(counter < Math.pow(n, k)){
			a[i] = a[i] + 1;
			if(a[i] == n){
				a[i] = 0;
				i++;
			}
			else if (a[i] < n){
				i = 0;
			}
			counter++;
		}
	}
	public static void loop1(int n, int k){
		int counter = 0;
		// create an int array of size k
		// k is the number of loop
		int[] a = new int[k]; 
		// zero out the array
		for(int j = 0; j < a.length; j++){
			a[j] = 0;
		}
		// loop index keep track of which loop currently being iterated.
		int i = 0;
		//each loop is to iterate n times
		while(counter < Math.pow(n+1, k)){			
			a[i] = a[i] + 1;
			printArr(a);
			if(a[i] == n && i == k - 1){				
				i = 0;				
			}			
			else if(a[i] == n){
				a[i] = 0;
				i++;				
			}			
			else if (a[i] < n){
				i = 0;
			}
			counter++;
		}
	}
	public static void printArr(int[] arr){
		for(int g = 0; g < arr.length; g++){
			System.out.print(arr[g]);
		}
		System.out.print(" \n");
	}
}
