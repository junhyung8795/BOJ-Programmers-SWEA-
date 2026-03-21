import java.util.*;
class Solution {
    public long solution(int n) {
        long answer = 0;
        
        //dp배열 생성 10의 5제곱 +1크기
        long[] dp = new long[100000 + 1];
        dp[0] = 0l;
        dp[1] = 1l;
        for(int i = 2; i < 100001; i++){
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
        }
        answer = (dp[n]);
        // System.out.println((int)(dp[100000] % 1234567));
        return answer;
    }
}