import java.io.*;
import java.util.*;

public class Main{
    static int N;
    static int K;
    static int answer;

    static class Node{
        int moveCnt;
        int value;
        public Node(int value, int moveCnt){
            this.moveCnt = moveCnt;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);

        bfs();
        System.out.println(answer);
    }

    public static void bfs(){
        Queue<Node> q= new LinkedList<>();
        boolean[] visited = new boolean[100001];

        q.add(new Node(N, 0));
        visited[N] = true;
        while(!q.isEmpty()){
            Node popped = q.poll();
            int curValue = popped.value;
            int curMoveCnt = popped.moveCnt;

            if(curValue == K){
                answer = curMoveCnt;
                return;
            }

            //통과를 못했다면?
            //bfs탐색으로 로직수행
            //-1을 하는 노드를 넣을때는 만약에 -1을 해서 value값이 음수가 된다면 음.. 굳이 다시 넣을필요가없다.
            //그래도 *2를 하는거에도 사실 현재 CurValue가 0이면 다시 넣을필요가없다.
            //그렇다 curValue가 0인경우에는 +1을 더한 노드만 넣는걸로하자.
            
            //curValue가 0인 경우
            if(curValue == 0){
                //방문하지 않은 상태라면
                if(curValue + 1 <= 100000 && !visited[curValue + 1]){
                    q.add(new Node((curValue + 1), curMoveCnt + 1));
                    visited[curValue + 1] = true;
                }
            } else {
                //curValUe가 0이 아닌 경우 ==> 양수인 경우
                if(curValue + 1 <= 100000 && !visited[curValue + 1]){
                    q.add(new Node((curValue + 1), curMoveCnt + 1));
                    visited[curValue + 1] = true;
                }
                if(curValue - 1 <= 100000 && !visited[curValue - 1]){
                    q.add(new Node((curValue - 1), curMoveCnt + 1));
                    visited[curValue - 1] = true;
                }
                if( curValue * 2 <= 100000 && !visited[curValue * 2]){
                    q.add(new Node((curValue * 2), curMoveCnt + 1));
                    visited[curValue * 2] = true;
                }
            }



        }
    }
}