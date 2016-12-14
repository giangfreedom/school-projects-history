package labfinal;

public class lab11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] w = new int[6];
		
		w[0] = 0;
		w[1] = 3;
		w[2] = 5;
		w[3] = 5;
		w[4] = 1;
		w[5] = 4;
		
		int[][] dp = new int[6][11];
		
		for(int i = 0; i < 11; i++){
			dp[0][i] = 0;
		}
		for(int j = 0; j < 6; j++){
			dp[j][0] = 0;
		}
		dp[1][1] = 0;
		dp[1][2] = 0;
		dp[1][3] = 40;
		dp[1][4] = 40;
		dp[1][5] = 40;
		dp[1][6] = 40;
		dp[1][7] = 40;
		dp[1][8] = 40;
		dp[1][9] = 40;
		dp[1][10] = 40;
		
		dp[2][1] = 0;
		dp[2][2] = 0;
		dp[2][3] = 40;
		dp[2][4] = 40;
		dp[2][5] = 60;
		dp[2][6] = 60;
		dp[2][7] = 60;
		dp[2][8] = 100;
		dp[2][9] = 100;
		dp[2][10] = 100;
		
		dp[3][1] = 0;
		dp[3][2] = 0;
		dp[3][3] = 40;
		dp[3][4] = 40;
		dp[3][5] = 60;
		dp[3][6] = 60;
		dp[3][7] = 60;
		dp[3][8] = 100;
		dp[3][9] = 100;
		dp[3][10] = 100;
		
		dp[4][1] = 30;
		dp[4][2] = 30;
		dp[4][3] = 40;
		dp[4][4] = 70;
		dp[4][5] = 70;
		dp[4][6] = 90;
		dp[4][7] = 90;
		dp[4][8] = 100;
		dp[4][9] = 130;
		dp[4][10] = 130;
		
		dp[5][1] = 30;
		dp[5][2] = 30;
		dp[5][3] = 40;
		dp[5][4] = 70;
		dp[5][5] = 80;
		dp[5][6] = 90;
		dp[5][7] = 90;
		dp[5][8] = 120;
		dp[5][9] = 130;
		dp[5][10] = 140;
		
		print_used_items(dp,w,5,10);
	}
	
	public static void print_used_items(int[][] p, int[] w, int i, int c){
		if(i == 0 || c == 0){
			return;
		}
		
		if(p[i][c] > p[i-1][c]){
			print_used_items(p,w, i-1,c-w[i]);
			System.out.println("used" + i);
		}
		else{
			print_used_items(p,w, i-1,c);
		}		
	}

}
