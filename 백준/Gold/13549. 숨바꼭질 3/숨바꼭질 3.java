import java.util.*;
import java.io.*;

class Main{
    static int N;
    static int K;
    static class Node{
        int time;
        int pos;
        public Node(int time, int pos){
            this.time = time;
            this.pos = pos;
        }
        @Override
        public String toString(){
            return "time = " + time + " pos = " + pos;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //N과 K입력 바딕
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);
        
        daikstra();
        
        
    }
    public static void daikstra(){
        //시작점은 N으로 주어진다.
        //시작점을 기점으로 걸어가든 순간이동을 할 때, 같은 지점을 최대한 적은 시간으로 가야한다.
        //우선순위 큐에 Node(time, pos)를 넣고 빼면서 탐색할 때, 같은 지점은 더 큰 time으로 가는건 이미가 없으므로 
        //이때는 그냥 흘려보낸다.
        int[] dist = new int[200001];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node a, Node b){
                return a.time - b.time;
            }
        });
        pq.offer(new Node(0, N));//N이라는 지점에 0초부터 시작
        Arrays.fill(dist, Integer.MAX_VALUE);
        while(!pq.isEmpty()){
            //System.out.println(pq);
            Node cur = pq.poll();
            //System.out.println("cur = " + cur);
            int curTime = cur.time;
            int curPos = cur.pos;
            //현재 위치가 K라면 답을 출력하고 return
            if(curPos == K){
                System.out.println(curTime);
                break;
            }
            //만약 이미 더 짧은 시간으로 접근이 가능하다면 continue;
            if(dist[curPos] < curTime){
                continue;
            }
            //더 짧은 시간으로 업데이트
            dist[curPos] = curTime;
            
            //현재 지점에서 갈 수 있는 3가지 가짓수를 모두 반영
            int minus = curPos - 1;
            int plus = curPos + 1;
            int mult = curPos * 2;
            //음수 좌표로는 이동하면 무조건 손해다.
            if(minus >= 0){//0초과일때만 한다.
                if(dist[minus] > curTime + 1){
                    pq.offer(new Node(curTime + 1, minus));    
                } 
            }
            //+는 언제해야하나. K보다 작을 때만 해야한다.
            if(plus <= K){
                if(dist[plus] > curTime + 1){
                    pq.offer(new Node(curTime + 1, plus)); 
                }     
            }
            //곱하기는 K보다 작을때만 해야한다.
            if(mult <= 200000 && mult != 0){
                if(dist[mult] > curTime){
                    pq.offer(new Node(curTime, mult)); 
                }
            }
           
            
        }
        
    }
}