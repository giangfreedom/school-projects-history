import java.util.*;
import java.io.*;
public class MinSS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Scanner in1 = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		Scanner in4 = new Scanner(System.in);
		boolean check = true;
		
		String s1;
		String listOfInt;
		int userChoice;
		int n;
		while(check){
			System.out.println("1) Quit");
			System.out.println("2) enter an array with comma delimiter");
			System.out.println("3) random size array");
			userChoice = in.nextInt();
			if(userChoice == 1){
				System.out.println("bye bye");
				check = false;
			}
			else if(userChoice == 2){
				System.out.println("please enter a list of int with comma delimiter");
				listOfInt = in1.next();
				// split the string using comma into an array 
				String[] s = listOfInt.split(",");
				// create an array of integer with the same size as s array
				int[] arrOfNumber = new int[s.length];
				try{
					for(int i = 0; i < s.length; i++){
						arrOfNumber[i] = Integer.parseInt(s[i]);
					}
				}catch(Exception e){
					System.out.println("you need to enter a list of integer" +
							" seperate by comma");
					continue;
				}				
				System.out.println("sophomore Mss is " + Sophomore(arrOfNumber));
				System.out.println("junior Mss is " + mss_Junior(arrOfNumber,0,arrOfNumber.length - 1));
			}
			
			// random array
			else if(userChoice == 3){
				System.out.println("please enter the size of the array");
				n = in2.nextInt();
				int[] array = new int[n];
				// random number for array size n
				Random generator = new Random();
				for(int i = 0; i < array.length; i++){
					int rand = (generator.nextInt(101)-50);
					array[i] = rand;
				}
				System.out.println("please enter a two digits string between 1 to 2" +
						" to select the algorithm you want to run");
				String userInput = in4.next();
				userInput.trim();
				String[] stringUser = userInput.split("");
				String[] temArr = new String[stringUser.length - 1];

				for(int i = 0; i < temArr.length; i++){
					temArr[i] =	stringUser[i+1];		
				}
				//for(int i = 0; i < temArr.length; i++){
				//	System.out.println(temArr[i]);	
				//}
				// create an array of integer with the same size as s array
				int[] arrStringUser = new int[temArr.length];

				try{
					for(int i = 0; i < arrStringUser.length; i++){
						arrStringUser[i] = Integer.parseInt(temArr[i]);
						System.out.println(arrStringUser[i]);
					}
				}catch(Exception e){
					System.out.println("you need to enter a list of integerx1");
					continue;
				}
				
				for(int i = 0; i < arrStringUser.length; i++){					
					if(arrStringUser[i] == 1){
						double startTime = System.nanoTime();
						System.out.println("sophomore Mss is " + Sophomore(array));
						double estimatedTime = System.nanoTime() - startTime;
						double estimatedTimeInMilSec = estimatedTime / 1000000;
						if(estimatedTimeInMilSec < 1000){
							System.out.println("time used " + estimatedTimeInMilSec + " milliSecond");
						}
						else{
							System.out.println("time used " + (estimatedTimeInMilSec/1000) + " Second");
						}
					}
					else if(arrStringUser[i] == 2){
						double startTime = System.nanoTime();
						System.out.println("junior Mss is " + mss_Junior(array,0,array.length - 1));
						double estimatedTime = System.nanoTime() - startTime;
						double estimatedTimeInMilSec = estimatedTime / 1000000;
						if(estimatedTimeInMilSec < 1000){
							System.out.println("time used " + estimatedTimeInMilSec + " milliSecond");
						}
						else{
							System.out.println("time used " + (estimatedTimeInMilSec/1000) + " Second");
						}
					}
					
					else{
						System.out.println("you need to enter a list of integerx2" +
								" between 1 to 4");
						continue;
					}
				}
			}
			else{
				System.out.println("incorrect choice ");
				continue;
			}
		}

	};
		
	
	public static int Sophomore(int [] arr){
		int max_sum = 0;
		int n = arr.length;
		int this_sum = 0;
		// choose start index
		for(int i = 0; i < n; i++){
			this_sum = 0;
			for(int j = i; j < n; j++){
				this_sum += arr[j];				
				if(this_sum > 0 && max_sum == 0){
					max_sum = this_sum;
				}
				else if(this_sum > 0 && this_sum < max_sum){
					max_sum = this_sum;
				}
				
			}
		}
		return max_sum;
	}
	
	public static int mss_Junior(int [] arr, int left, int right){
		//base case arr have 1 or 2 element left
		if((right - left) <= 1){
			return mss_base_case(arr,left,right);
		}
		
		int mid = (left + right) / 2;
		int mssL = mss_Junior(arr,left,mid);
		int mssR = mss_Junior(arr,mid+1,right);
		int mssM = mss_middle(arr,left,mid,right);
		
		//return min(mssL, mssM, mssR);
		System.out.println("mid = " + mssM);
		System.out.println("left = " + mssL);
		System.out.println("right = " + mssR);
		return mssM;
	}
	
	public static int mss_base_case(int [] arr, int left, int right){
		if(arr.length <= 1){
			return arr[0];
		}
		else{
			if(arr[0] >= 0 && arr[1] >= 0){
				if(arr[0] < arr[1]){
					return arr[0];
				}
				else{
					return arr[1];
				}
			}
			else if(arr[0] < 0 && arr[1] < 0){
				if(arr[0] < arr[1]){
					return arr[1];
				}
				else{
					return arr[0];
				}
			}			
			else{
				if(arr[1] > arr[0]){
					return arr[1];
				}
				else{
					return arr[0];
				}
			}
		}
	}
	public static int mss_middle(int [] arr1, int left,int mid, int right){
		//scan for largest value of each sub array return the sum of the 2 max
		int [] rightArr = new int [mid+1];
		int [] leftArr = new int [mid+1];
		int r = 0;
		int this_sum = 0;
		int minpossum = 0;
		int currentSum = 0;
		// right array
		for(int i = mid+1; i < right+1; i++){
			this_sum = this_sum + arr1[i];
			rightArr[r] = this_sum;
			r++;
		}
		r = 0;
		this_sum = 0;
		for(int i = mid; i >= left; i--){
			this_sum = this_sum + arr1[i];
			leftArr[r] = this_sum;
			r++;
		}
		rightArr = QuickSort(rightArr);
		leftArr = QuickSort(leftArr);
		
		int leftmarker = 0;
		int rightmarker = rightArr.length - 1;
		
		for(int i = 0; i < rightArr.length; i++){
			System.out.print(rightArr[i]+ " / ");
		}
		System.out.println();
		for(int i = 0; i < leftArr.length; i++){
			System.out.print(leftArr[i]+ " / ");
		}
		System.out.println();
		while((leftmarker <= rightArr.length - 1) && rightmarker >= 0){
			currentSum = leftArr[leftmarker] + rightArr[rightmarker];

			if(currentSum <= 0){
				leftmarker++;
			}
			else{
				rightmarker--;
			}
			
			if(currentSum > 0 && minpossum == 0){
				minpossum = currentSum;
			}
			else if(currentSum > 0 && currentSum < minpossum){
				minpossum = currentSum;
			}
		}				
		return minpossum;
	}
	public static int[] QuickSort(int [] arr){
		int left = 0;
		int right = arr.length - 1;
		//int mid = arr.length / 2;
		int mid = (left + right) / 2;
		int pivot = median(arr, left, right, mid);
		int temp = arr[right];
		
		arr[right] = arr[pivot];
		arr[pivot] = temp;
		
		int i = 0;
		int j = right - 1;
		while (i <= j) {
	           while (i <= j && arr[i] < arr[pivot]){
	        	   i++; 
	           }	               
	           while (i <= j && arr[j] > arr[pivot]){
	        	   j--;
	           }
	           if(j < 0){
	        	   j = 0;
	           }
	           if (i <= j) {
	                 temp = arr[i];
	                 arr[i] = arr[j];
	                 arr[j] = temp;
	                 i++;
	                 j--;
	           }
	    };		
	    
	    temp = arr[pivot];
	    arr[pivot] = arr[i];
	    arr[i] = temp;
	    return arr;
	}
	public static int median(int [] arr,int left,int right,int mid){
		if(arr[left] <= arr[right] && arr[left] >= arr[mid]){
			return left;
		}
		else if(arr[left] >= arr[right] && arr[left] <= arr[mid]){
			return left;
		}
		else if(arr[right] <= arr[left] && arr[right] >= arr[mid]){
			return right;
		}
		else if(arr[right] >= arr[left] && arr[right] <= arr[mid]){
			return right;
		}
		else if(arr[mid] <= arr[left] && arr[mid] >= arr[right]){
			return mid;
		}
		else if(arr[mid] >= arr[left] && arr[mid] <= arr[right]){
			return mid;
		}
		else{
			return mid;
		}
	}
	
	public static int min(int left,int mid, int right){
		if(left <= mid && left <= right){
			return left;
		}
		else if(right <= mid && right <= left){
			return right;
		}
		else if(mid <= right && mid <= left){
			return mid;
		}
		else{
			return mid;
		}
	}
	
}
