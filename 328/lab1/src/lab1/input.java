package lab1;
import java.util.*;
import java.text.NumberFormat;

public class input {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Scanner in1 = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		boolean check = true;
		String userInput;
		String listOfInt;
		String listOfDouble;
		double average = 0;
		double squadAverage = 0;
		double sum = 0;
		double sumSquad = 0;
		double SD = 0;
		int x = 0;
		int counter = 0;
		int temp = 0;
		int display = 0;
		while(check){
			// check to see if the user want to quit
			System.out.println("Enter Q to Quit and any other character to continue");
			System.out.println("Enter D to change decimal place");
			System.out.println("Enter I to input integer list");
			System.out.println("Enter R to input a real number list");
			userInput = in.next();
			if(userInput.charAt(0) == 'q' || userInput.charAt(0) == 'Q'){
				break;
			}
			else if(userInput.charAt(0) == 'd' || userInput.charAt(0) == 'D'){
				// aks the user for the amount of precision
				System.out.println("Please enter the number of decimal place");
				try{
					x = in.nextInt();
				}catch(InputMismatchException e){
					System.out.println("enter a number for decimal place");
					continue;
				}
				// set the precision
				if(x < 0){	
					System.out.println("invalid decimal place");
				}				
			}
			else if(userInput.charAt(0) == 'i' || userInput.charAt(0) == 'I'){
				// get a list of int from user
				// seperate by comma
				System.out.println("Please enter a series of whole number" +
						" seperate by comma");
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
				//for(int i = 0; i < s.length; i++){
				//	arrOfNumber[i] = Integer.parseInt(s[i]);
				//}
				//check for the number that appear the most
				for(int j = 0; j < arrOfNumber.length; j++){
					for(int q = 0; q < arrOfNumber.length; q++){
						if(arrOfNumber[j] == arrOfNumber [q]){
							temp++;
						}
					}
					if(counter < temp){
						counter = temp;
						display = arrOfNumber[j];
					}
					temp = 0;
				}
				System.out.println("the number that appear the most is " + 
						display);
			}
			else if(userInput.charAt(0) == 'r' || userInput.charAt(0) == 'R'){
				System.out.println("Please enter a series of real number" +
						" seperate by comma");
						listOfDouble = in2.next();
						System.out.println(listOfDouble);
						// split the string using comma into an array 
						String[] s1 = listOfDouble.split(",");
						// create an array of double with the same size as s1 array
						double[] arrOfDouble = new double[s1.length];
						try{
							for(int k = 0; k < s1.length; k++){
								arrOfDouble[k] = Double.parseDouble(s1[k]);
								System.out.println(arrOfDouble[k]);
							}
						}catch(Exception e){
							System.out.println("you need to enter a list of double" +
									" seperate by comma");
							continue;
						}
						//for(int k = 0; k < s1.length; k++){
						//	arrOfDouble[k] = Double.parseDouble(s1[k]);
						//	System.out.println(arrOfDouble[k]);
						//}
						
						NumberFormat boundprecision = NumberFormat.getNumberInstance();
						boundprecision.setMaximumFractionDigits(x);
						boundprecision.setMinimumFractionDigits(x);
						// take the average Mx = 1/n (X1+X2+...+Xn)
						for(int l = 0; l < arrOfDouble.length; l++){
							sum = sum + arrOfDouble[l];
						}
						int n = arrOfDouble.length;
						average = sum / n;
						System.out.println("the average is");
						System.out.println(boundprecision.format(average));
						
						// squad of average Mx2 = 1/n (X1Squad + X2 Squad + ...+ Xn Squad)
						for(int v = 0; v < arrOfDouble.length; v++){
							sumSquad = sumSquad + (arrOfDouble[v] * arrOfDouble[v]);
						}
						squadAverage = sumSquad / (arrOfDouble.length);
						
						SD = Math.sqrt(squadAverage - (average * average));
						
						System.out.println("the standard deviation is");
						System.out.println(boundprecision.format(SD));
			}
			else{
				continue;
			}			
			average = 0;
			squadAverage = 0;
			sum = 0;
			sumSquad = 0;
			SD = 0;
			counter = 0;
			temp = 0;
			display = 0;
		}
		System.out.println("bye bye");
	}
		
	public static int MostFrequenceInt(int[] arrOfNumber){
		int x1 = 0;
		int counter1 = 0;
		int temp1 = 0;
		int display1 = 0;
		for(int j = 0; j < arrOfNumber.length; j++){
			for(int q = 0; q < arrOfNumber.length; q++){
				if(arrOfNumber[j] == arrOfNumber [q]){
					temp1++;
				}
			}
			if(counter1 < temp1){
				counter1 = temp1;
				display1 = arrOfNumber[j];
			}
			temp1 = 0;
		}
		return display1;
	}
}
