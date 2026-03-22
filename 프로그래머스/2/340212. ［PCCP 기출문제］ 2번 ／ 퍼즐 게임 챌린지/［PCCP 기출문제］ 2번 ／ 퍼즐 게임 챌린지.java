import java.util.*;

class Solution {
    static int[] diffsSt;
    static int[] timesSt;
    static long limitSt;
    static int n;
    public int solution(int[] diffs, int[] times, long limit) {
        diffsSt = diffs;
        timesSt = times;
        limitSt = limit;
        n = diffs.length;
        int answer = 100000;
        
        int left = 1;
        int right = 100000;
        
        while(left <= right){
            int mid = (left + right) / 2;
            long result = bs(mid);
            
            if(result <= limit){
                answer = mid;
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }            
        }
        
        return answer;
    }
    
    public static long bs(int level){
        //일단 첫항은 diffs[0] = 1 이니까 무조건 통과라서 디폴트는 times[0]이 추가
        long total = timesSt[0];
        
        
        //그다음 1번째 항부터 n-1번째 항가지 돌면서 문제조건에따라 total을 업데이트
        for(int i = 1; i <= n - 1; i++){
            if(level >= diffsSt[i]){
                total += timesSt[i];
            }else{
                total += (diffsSt[i] - level) * (timesSt[i] + timesSt[i - 1]);
                total += timesSt[i];
            }
        }
        return total;
        
        
    }
}