


import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] s = br.readLine().split(" ");
		
		int[] arr = new int[s.length];
		for(int i = 0 ; i <s.length; i++) {
			arr[i] = Integer.parseInt(s[i]);
		}
		//index=1이면 1인 길이를 가지는 수열 중 마지막 값 중 가장 작은 값을 dp[1]에 저장 왜냐하면 
		//10 20 1 4가 주어졌을 때 4 뒤에 5가 오면 1 4 5로 3길이의 가장 긴 증가수열이 완성되기때문
		//가장 큰값인 20을 저장하면 5가 뒤에 와도 갱신 X 최대한 작은 숫자가 해당 길이의 마지막 값이 되도록하는게
		//새로운 숫자가 들어왓을 때 수열의 길이가 증가될 확률이 높다.
		int[] dp = new int[s.length + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = arr[0];
		for(int i = 1; i <s.length; i++) {
			
			//새로 들어온 수보다 작은 수가 들어간 인덱스 중 가장 큰 인덱스를 찾음.
			//이진탐색
			int left = 0;
			int right = dp.length - 1;
			int targetIdx = -1;//이후에 +1인자리에 들어갔을 때 최소 0이되게 하려고 -1로 초기화
			while(left <= right) {
				int mid = (left + right) / 2;
				
				if(dp[mid] < arr[i]) {
					 left = mid +1;
					 targetIdx = mid;
				} else {
					right = mid - 1;
				}
			}
			//찾은 자리보다 길이가 1추가된다면?
			//Mid +1자리의 수를 없데이트 해야한다.
			dp[targetIdx + 1] = arr[i];
		}
//		System.out.println(Arrays.toString(dp));
		
		int cnt = 0;
		for(int i = 0; i < dp.length; i++) {
			if(dp[i] != Integer.MAX_VALUE) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}


	
}