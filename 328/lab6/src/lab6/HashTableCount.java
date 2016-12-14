package lab6;
import java.util.*;
import java.io.*;
public class HashTableCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] primeArr = new int[]{53,97,193,389,769,1543,3079,6151,12289,24593,49157,98317,196613,
									393241,786433,1572869};
		
		Scanner in = new Scanner(System.in);
		Scanner in1 = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		Scanner in3 = new Scanner(System.in);
		Scanner in4 = new Scanner(System.in);
		Scanner in5 = new Scanner(System.in);
		boolean check = true;
		boolean notprime = true;
		int userChoice;
		int userAction;
		int userNumber;
		int tablesize;
		int i = 0;
		int n = 0;
		Hash myHash =  new Hash(193);
		String listOfInt;
		
		myHash.fill();
		myHash.display();
		while(check){
			System.out.println("1) Create Counting Table");
			System.out.println("2) HashTable Operation");
			System.out.println("3) enter a comma delimited list");
			System.out.println("4) array of random size");
			System.out.println("5) quit");
			
			userChoice = in.nextInt();
			if(userChoice == 5){
				System.out.println("bye bye");
				check = false;
			}
			else if(userChoice == 1){
				System.out.println("Please enter the table size");
				tablesize = in1.nextInt();
				
				while(notprime){
					if(tablesize <= primeArr[i]){
						tablesize = primeArr[i];
						notprime = false;
					}
					i++;
					if(i >= primeArr.length){
						notprime = false;
					}
				}
				Hash newHash = new Hash(tablesize);
				newHash.fill();
				newHash.display();
				
				myHash = newHash;						
			}
			
			else if(userChoice == 2){
				System.out.println("1) Add to Table");
				System.out.println("2) subtract from Table");
				System.out.println("3) Count Obj");
				userAction = in2.nextInt();
				if(userAction == 1){
					System.out.println("please enter the number you want to add");
					userNumber = in3.nextInt();
					myHash.add(userNumber);
					myHash.display();
				}
				else if(userChoice == 2){
					System.out.println("please enter the number you want to subtract");
					userNumber = in3.nextInt();
					myHash.subtract(userNumber);
					myHash.display();
				}
				else if(userChoice == 3){
					System.out.println("please enter the number you want to see the count");
					userNumber = in3.nextInt();
					int value = myHash.count(userNumber);
					System.out.println("the count is " + value);
				}
			}
			
			else if(userChoice == 3){
				System.out.println("please enter a list of int with comma delimiter");
				listOfInt = in4.next();
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
				
				System.out.println("mode method the item that appear the most is " + mode(arrOfNumber, arrOfNumber.length));
				System.out.println("hashtable method the item that appear the most is " + myHash.modehash(arrOfNumber, arrOfNumber.length));
			}
			else if(userChoice == 4){
				System.out.println("please enter the size of the array");
				n = in5.nextInt();
				int[] array = new int[n];
				// random number for array size n
				Random generator = new Random();
				for(int v = 0; v < array.length; v++){
					int rand = (generator.nextInt(2001) - 1000);
					array[v] = rand;
				}
				
				double startTime = System.nanoTime();
				System.out.println("mode method result = " + mode(array, array.length));
				double estimatedTime = System.nanoTime() - startTime;
				double estimatedTimeInMilSec = estimatedTime / 1000000;
				if(estimatedTimeInMilSec < 1000){
					System.out.println("time used " + estimatedTimeInMilSec + " milliSecond");
				}
				else{
					System.out.println("time used " + (estimatedTimeInMilSec/1000) + " Second");
				}
				
				double startTime1 = System.nanoTime();
				System.out.println("hashtable method result " + myHash.modehash(array, array.length));
				double estimatedTime1 = System.nanoTime() - startTime1;
				double estimatedTimeInMilSec1 = estimatedTime1 / 1000000;
				if(estimatedTimeInMilSec1 < 1000){
					System.out.println("time used " + estimatedTimeInMilSec1 + " milliSecond");
				}
				else{
					System.out.println("time used " + (estimatedTimeInMilSec1/1000) + " Second");
				}
			}
			
			else{
				System.out.println("incorrect choice ");
				continue;
			}
		}
	}
	
	// go through the array and pull out the integer that appear the most.
	public static int mode(int a[], int n){
		int UD = (int) Math.pow(2, 30);
		int mode = UD;
		int max_count = 0;
		int i, candidate, count, j;
		for(i=0; i < n; i++){
			candidate = a[i];
			count = 1;
			for(j=i+1; j < n; j++){
				if(a[j] == candidate)
					count++;
			}
			if(count > max_count){
				max_count = count;
				mode = candidate;
			}
		}
	return mode;
	}
	
}
