import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
class Solution {
    static int N;
    static int M;
    static boolean[] visited;
    static int[][] graph;
    static int answer;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            String[] inputNM = br.readLine().split(" ");
            N = Integer.parseInt(inputNM[0]);
            M = Integer.parseInt(inputNM[1]);
 
            // 그랲 저장
            graph = new int[N + 1][N + 1];
            for(int i = 0; i < M; i++) {
                String[] inputNodes = br.readLine().split(" ");
                int first = Integer.parseInt(inputNodes[0]);
                int second = Integer.parseInt(inputNodes[1]);
                graph[first][second] = 1;
                graph[second][first] = 1;
            }
             
            //answer, visited초기화 및 bfs로직 실행 
            answer = 0;
            visited = new boolean[N +1];
            for(int n = 1; n < N +1; n++) {
                if(!visited[n]) {
                    bfs(n);
                }
                 
            }
             
             
            System.out.println("#" +t +" " + answer);
             
             
             
             
             
             
             
             
             
        }
    }
 
    private static void bfs(int startNode) {
        // TODO Auto-generated method stub
        Queue<Integer> q= new LinkedList<>();
        q.add(startNode);
        visited[startNode] = true;
         
        while(!q.isEmpty()) {
            int popped = q.poll();
             
            for(int i = 1; i <= N; i++) {
                if(graph[popped][i] == 1) {
                    if(!visited[i]) {
                        q.add(i);
                        visited[i] = true;
                    }
                }
            }           
        }
         
        answer++;
    }
}