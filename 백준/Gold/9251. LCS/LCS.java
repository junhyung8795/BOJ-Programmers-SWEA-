import java.util.*;
import java.io.*;

class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] a = br.readLine().toCharArray();
		char[] b = br.readLine().toCharArray();
		
		if(a.length == 0 || b.length == 0){
            System.out.println(0);
        }
		//음..규칙성을 정말 브루트포스하듯이 하나 하나 했더니 규칙성을 발견함
		//다만 Dp문제를 풀 때는 현재의 답이 몇가지 이전단계의 몇가지 요소로 인해서 영향을 받느냐에 따라
		//dp테이블의 차원수를 정한다. 
		//여기서는 테이블이 a b 가 주어지면 b가 가만히 있어도 a의 고려하는 길이를 늘리면 답이 늘어날 수도 있고
		//반대의 경우도 가능하니. 각 테이블이 공개하는 숫자들의 개수를 그 요인으로 보았고 
		//따라서 테이블이 두개니까 이차원 dp테이블을 만들었다.
		
		//dp테이블 초기화
		int[][] dp = new int[b.length + 1][a.length + 1];
		for(int row = 0 ; row < dp.length; row++) {
			dp[row][0] = 0;
		}
		for(int col = 0 ; col < dp[0].length; col++) {
			dp[0][col] = 0;
		}
		
		
		int answer = 0;
	
		for(int row = 1; row < dp.length; row++) {
			for(int col = 1; col < dp[0].length; col++) {
				if(a[col - 1] == b[row - 1]) {
					
					dp[row][col] = dp[row - 1][col - 1] + 1;
					answer = Math.max(answer, dp[row][col]);
				}
				else {
					dp[row][col] = Math.max(dp[row][col - 1], dp[row - 1][col]);
				}
				
				
			}
		}
		System.out.println(answer);
		
	}


	
}