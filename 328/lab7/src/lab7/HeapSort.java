package lab7;
import java.util.*;
import java.io.*;
import java.util.Arrays;
public class HeapSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Scanner in1 = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		Scanner in3 = new Scanner(System.in);
		Scanner in4 = new Scanner(System.in);
		Scanner in5 = new Scanner(System.in);
		Scanner in6 = new Scanner(System.in);
		Scanner in7 = new Scanner(System.in);
		Scanner in8 = new Scanner(System.in);
		
		Heap heap1 = new Heap(10);
		Heap heap2 = new Heap(10);
		Heap heap3 = new Heap(10);
		
		String listOfInt;
		int userChoice;
		int userInput;
		boolean check = true;
		
		while(check){
			System.out.println("1) Create An empty Min Heap");
			System.out.println("2) enter a comma delimited list of integer use build heap"
								+ " to create min heap with those int");
			System.out.println("3) insert an single element into heap 1 or heap 2");
			System.out.println("4) pop element from heap 1 or heap 2");
			System.out.println("5) display heap using heap sort");
			System.out.println("6) provide length for an array ");
			System.out.println("7) quit");
			
			userChoice = in.nextInt();
			
			if(userChoice == 7){
				System.out.println("bye bye");
				check = false;
			}
			else if(userChoice == 1){
				System.out.println("please enter heap size");
				userInput = in2.nextInt();
				Heap myheap1 = new Heap(userInput);
				heap1 = myheap1;
			}
			else if(userChoice == 2){
				System.out.println("please enter a list of int with comma delimiter");
				listOfInt = in3.next();
				// split the string using comma into an array 
				String[] s = listOfInt.split(",");
				// create an array of integer with the same size as s array
				int[] arrOfNumber = new int[s.length];
				try{
					for(int q = 0; q < s.length; q++){
						arrOfNumber[q] = Integer.parseInt(s[q]);
					}
				}catch(Exception e){
					System.out.println("you need to enter a list of integer" +
							" seperate by comma");
					continue;
				}	
				
				Heap myheap2 = new Heap(arrOfNumber.length);
				heap2 = myheap2;
				heap2.Buildheap(arrOfNumber);
				heap2.Display();
			}
			else if(userChoice == 3){
				System.out.println("please enter the number you want to insert");
				int userint = in4.nextInt();
				System.out.println("1) insert into heap 1");
				System.out.println("2) insert into heap 2");
				int userHeapChoice = in5.nextInt();
				if(userHeapChoice == 1){
					heap1.insert(userint);
					heap1.Display();
				}
				else if(userHeapChoice == 2){
					heap2.insert(userint);
					heap2.Display();
				}
				else{
					System.out.println("invalid selection");
					continue;
				}
			}
			else if(userChoice == 4){
				System.out.println("please enter the heap you want to pop");
				System.out.println("1) pop into heap 1");
				System.out.println("2) pop into heap 2");
				int userPopChoice = in6.nextInt();
				if(userPopChoice == 1){
					heap1.pop();
					heap1.Display();
				}
				else if(userPopChoice == 2){
					heap2.pop();
					heap2.Display();
				}
				else{
					System.out.println("invalid selection");
					continue;
				}
			}
			else if(userChoice == 5){
				System.out.println("please enter the heap you want to sort and display");
				System.out.println("1) heap 1");
				System.out.println("2) heap 2");
				int userSortChoice = in7.nextInt();
				if(userSortChoice == 1){
					heap1.HeapSort();
				}
				else if(userSortChoice == 2){
					heap2.HeapSort();
				}
				else{
					System.out.println("invalid selection");
					continue;
				}
			}
			else if(userChoice == 6){
				System.out.println("please enter the size of the array");
				int n = in8.nextInt();
				int[] array = new int[n];
				// random number for array size n
				Random generator = new Random();
				for(int v = 0; v < array.length; v++){
					int rand = (generator.nextInt(2001) - 1000);
					array[v] = rand;
				}
				
				//Heap myheap3 = new Heap(array.length);
				//heap3 = myheap3;
				//heap3.Buildheap(array);
				

				Heap myheap3 = new Heap(array.length);
				heap3 = myheap3;
				double startTime = System.nanoTime();
				heap3.Buildheap(array);
				heap3.HeapSort();
				double estimatedTime = System.nanoTime() - startTime;
				double estimatedTimeInMilSec = estimatedTime / 1000000;
				if(estimatedTimeInMilSec < 1000){
					System.out.println("heap sort time used " + estimatedTimeInMilSec + " milliSecond");
				}
				else{
					System.out.println("heap sort time used " + (estimatedTimeInMilSec/1000) + " Second");
				}
				
				double startTime1 = System.nanoTime();
				Arrays.sort(array);
				double estimatedTime1 = System.nanoTime() - startTime1;
				double estimatedTimeInMilSec1 = estimatedTime1 / 1000000;
				for(int yj = 0; yj < array.length; yj++){
					System.out.print(array[yj]+" ");
				}
				System.out.println();
				
				if(estimatedTimeInMilSec1 < 1000){
					System.out.println("lib sort time used " + estimatedTimeInMilSec1 + " milliSecond");
				}
				else{
					System.out.println("lib sort time used " + (estimatedTimeInMilSec1/1000) + " Second");
				}
			}
		}
	}
		
}
