import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                if((a[1] - a[0]) != (b[1] - b[0])){
                    return (a[1] - a[0]) - (b[1] - b[0]);
                } else{
                    return a[0] - b[0];
                }
            }
        });
        
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        
        //dq에 [value, index]값을 넣는다. For문을 돌면서
        //dq내부의 총합을 total로 관리
        int total = 0;
        for(int i = 0; i < sequence.length; i++){
            if(sequence[i] > k){
                break;
            }
            
            if(total + sequence[i] <= k){
                dq.add(new int[]{sequence[i], i});
                total += sequence[i];
            } else{
                //더했을 때 초과라면 앞에서부터 차례대로 빼면서 K보다 작거나 같아질때까지 이과정 반복
                while(total + sequence[i] > k){
                    int[] peeked = dq.peekFirst();
                    dq.removeFirst();
                    total -= peeked[0];
                }
                dq.add(new int[]{sequence[i], i});
                total += sequence[i];
            }
            
            //합계가 K와 같은 순간은 pq에 넣는다.
            if(total == k){
                //앞쪽 인덱스
                int front = dq.peekFirst()[1];
                int back = dq.peekLast()[1];
                pq.add(new int[]{front, back});
                
            }
            
            
            
            
        }
        
        
        // System.out.println(Arrays.toString(pq.peek()));
        answer = pq.peek();
        return answer;
    }
}