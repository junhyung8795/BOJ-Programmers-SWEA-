import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        
        int cnt = 0;
        int N = queue1.length;
        
        long sum1 = 0;
        long sum2 = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for(int i = 0; i < queue1.length; i++){
            sum1 += queue1[i];
            sum2 += queue2[i];
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }
        
        long total = sum1 + sum2;
        if(total % 2 == 1) {
            return -1;
        }
        while(cnt < 4 * N){
            //종료조건: 두 큐의 합이 같을 때
            if(sum1 == sum2){
                break;
            }
            
            if(sum1 > sum2){
                sum1 -= q1.peek();
                sum2 += q1.peek();
                
                q2.offer(q1.poll());
            } else if(sum2 > sum1){
                sum1 += q2.peek();
                sum2 -= q2.peek();
                
                q1.offer(q2.poll());
            }
            cnt++;
            
            
        }
        
        if(cnt == 4 * N){
            cnt = -1;
        }
        return cnt;
    }
}