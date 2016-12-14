package lab10;

import java.util.Scanner;

public class lab10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Scanner in1 = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		
		String s1;
		String s2;
		
		int userChoice;
		
		boolean check = true;
		while(check){
			System.out.println("1) enter 2 string");
			System.out.println("2) Quit");
			userChoice = in.nextInt();
			
			if(userChoice == 1){
				System.out.println("please enter string 1");
				s1 = in1.next();
				
				System.out.println("please enter string 2");
				s2 = in2.next();
				
				System.out.println("the distance is " + minDistance(s1,s2));
			}
			else if(userChoice == 2){
				check = false;
			}
			else{
				System.out.println("invalid input");
			}
			
		}		
		
	}
	
	public static int minDistance(String s1, String s2) {
		int length1 = s1.length();
		int length2 = s2.length();
	 
		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[length1 + 1][length2 + 1];
	 
		for (int i = 0; i <= length1; i++) {
			dp[i][0] = i;
		}
	 
		for (int j = 0; j <= length2; j++) {
			dp[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < length1; i++) {
			char c1 = s1.charAt(i);
			for (int j = 0; j < length2; j++) {
				char c2 = s2.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) {
					//update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
					
					if(min == replace){
						System.out.println("replace is used");
						System.out.println();
					}
					else if(min == insert){
						System.out.println("insert is used");
						System.out.println();
					}
					else{
						System.out.println("delete is used");
						System.out.println();
					}
				}
			}
		}
	 
		return dp[length1][length2];
	}

}
