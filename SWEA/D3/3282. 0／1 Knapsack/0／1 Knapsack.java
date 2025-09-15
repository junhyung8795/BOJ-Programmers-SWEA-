import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution {
	static int N;
	static int[] moneys = new int[] {50000, 10000, 5000, 1000, 500, 100, 50, 10};
	static int[] answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		
		
		
		for(int t = 1; t <= T; t++) {
			String[] inputNK = br.readLine().split(" ");
			int N = Integer.parseInt(inputNK[0]);
			int K = Integer.parseInt(inputNK[1]);
			
			int[][] knapsack = new int[N + 1][2];
			for(int i = 0; i < N; i++) {
				String[] inputVC = br.readLine().split(" ");
				int V = Integer.parseInt(inputVC[0]);
				int C = Integer.parseInt(inputVC[1]);
				knapsack[i][0] = V;
				knapsack[i][1] = C;
				
			}
			
			
			
			int[][] dp = new int[N][ K+ 1];
			
			for(int j = 0; j < K+ 1; j++) {
				if(knapsack[0][0] <= j) {//j 인덱스에 해당 하는 무게가 제한일때 제일 많은 가치를 담는 경우를 dp[i][j]에 담는다.
					dp[0][j] = knapsack[0][1];
				}
			}
			
			
			
			for(int i = 1; i < N; i++) {//각 넵색의 인덱스
				//넵색을 부피가 큰 순서부터 넣어보면서 dp 테이블을 갱신
				for(int j = 0; j < K+ 1; j++) {
					
					
					if(knapsack[i][0] <= j) {//j 인덱스에 해당 하는 무게가 제한일때 제일 많은 가치를 담는 경우를 dp[i][j]에 담는다.
						dp[i][j] = Math.max(dp[i - 1][j - knapsack[i][0]] +knapsack[i][1] , dp[i - 1][j]);
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
				
			}
			
			System.out.println("#" + t + " " + dp[N - 1][K]);
		}
		
	}
}
