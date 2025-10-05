

import java.util.*;
import java.io.*;
import java.math.BigInteger;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			String[] inputAB = br.readLine().split(" ");
			
			
			long A = Long.parseLong(inputAB[0]);
			long B = Long.parseLong(inputAB[1]);
			
			
			long sumOfAB = A +B;
			
			//문제 조건상 A,B는 1이상이므로 sumOfAB는 최소 2이상이다.
			//골드바흐의 추측으로 2를 초과하는 모든 짝수들은 두 소수의 합으로 표현가능하다.
			//만약 2초과의 짝수라면 YEs를 출력하고/
			//만약 sumOFAB가 홀수라면?
			//홀수 + 짝수여야 홀수가 나오는데, 홀수인 소수와 짝수인 소수의 조합인지 생각해보면 된다.
			//짝수인 소수는 2 혼자 뿐이다.
			//S-2가 소수인지를 확인하면 풀 수 있다.
			//4x10^12승인 수가 소수인지는 BigInteger함수를쓰자.
			//에라토스테네스의 체를 이용할 수도 있지만 4x10^12승이하의 모든 소수를 찾으려면
			//배열의 메모리가 너무 커져서 사용할 수 없는 케이스이기도 하다.
			
			if(sumOfAB == 2) {
				sb.append("NO\n");
			}//sumOfAB == 2가 아니라면 무조건 2를 초과한다. 
			else if(sumOfAB % 2 == 0) {
				sb.append("YES\n");
			} else if ( sumOfAB % 2 == 1) {
				//합이 홀수 인 경우 sumOfAB - 2가 소수여야한다.
				if(BigInteger.valueOf(sumOfAB - 2).isProbablePrime(20)) {
					sb.append("YES\n");
				}else {
					sb.append("NO\n");
				}
				
				
				
			}
			
		}
		
		System.out.println(sb.toString());
	
	}

}
