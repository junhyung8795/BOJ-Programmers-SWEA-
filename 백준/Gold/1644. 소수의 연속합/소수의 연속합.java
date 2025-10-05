

import java.util.*;
import java.io.*;
import java.math.BigInteger;

class Main {
	static int MAXLENGTH = 4 * 1_000_000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		//4*10^6이하의 모든 소수 구하기 -> 에라토스테네스의 체 
		boolean[] isPrime = new boolean[MAXLENGTH + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;//0과1은 소수가 아님.
		
		List<Integer> primeList = new ArrayList<>();
		for(int i = 2; i <= MAXLENGTH; i++) {
			if(isPrime[i]) {
				primeList.add(i);
				
				for(int j = i * i ; j <= MAXLENGTH && j >= 0; j+= i) {
					isPrime[j] = false;
				}
			}
			
		}
		
		
		//primeList의 기준점들을 순회하면서/
		//각 기준점에서 부터 다음 소수들을 더해갔을 때, sum = N이면 answer += 1;
		//sum < N이면 계속 더하기 sum > N이면 다음 기준점으로 넘어간다.
		//이때, 기준점들은 최대 N과 같을 때까지만 실행하도록 한다.
		int answer = 0;
//		System.out.println("primeList.size() = " + primeList.size());
		
		for(int i = 0;  i < primeList.size() && primeList.get(i) <= N; i++) {
			int curSum = 0;
			for(int j = i; j < primeList.size() && primeList.get(j) <= N  ; j++) {
//				System.out.println("j = " +j);
				curSum += primeList.get(j);
				if(curSum == N) {
					answer++;
					break;
				} else if(curSum > N) {
					break;
				} else if (curSum < N) {
					continue;
				}
			}
			
			
			
			
			
			
			
			
			
		}
		
		System.out.println(answer);
		
		
	}

}
