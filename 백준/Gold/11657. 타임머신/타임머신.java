import java.io.*;
import java.util.*;

class Main{
    static int N;
    static int M;
    static ArrayList<Edge>[] graph;
    static long[] dist;
    static class Edge{
        int u;
        int v;
        int w;
        public Edge(int u, int v, int w){
            this.u = u ;
            this.v = v;
            this.w = w;
        }
        @Override
        public String toString(){
            return "u = " + u + " v = " + v + " w = " +w;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        
        //간선 입력받기
        graph = new ArrayList[N + 1];
        for(int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            //abc
            String[] s1 = br.readLine().split(" ");
            int u = Integer.parseInt(s1[0]);
            int v = Integer.parseInt(s1[1]);
            int w = Integer.parseInt(s1[2]);
            graph[u].add(new Edge(u, v, w));
        }
        
        //dist초기화
        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        //시작점은 1번 도시
        dist[1] = 0l;
        
        
        boolean result = bellmanford();
        //결과가 False면 음수사이클이므로 -1만 출력
        if(result == false){
            System.out.println(-1);
            return;
        }
        //아니면 dist배열의 요소들을 출력
        for(int i = 2; i < N + 1;i++){
            if(dist[i] == Long.MAX_VALUE){
                System.out.println(-1);
            } else{
                System.out.println(dist[i]);
            }
        }
        
        
    }
    
    
    public static boolean bellmanford(){
        //노드개수 - 1번 만큼 완화
        for(int i = 0; i < N - 1; i++){
            //간선 순회
           for(int j = 1; j <= N ; j++){
               for(Edge edge : graph[j]){
                   int u = edge.u;
                   int v = edge.v;
                   int w = edge.w;
                   if(dist[u] != Long.MAX_VALUE && dist[v] > dist[u] + w){
                       dist[v] = dist[u] + w;
                   }
               }
           }
        }
        
        //간선 순회
           for(int j = 1; j <= N ; j++){
               for(Edge edge : graph[j]){
                   int u = edge.u;
                   int v = edge.v;
                   int w = edge.w;
                   if(dist[u] != Long.MAX_VALUE && dist[v] > dist[u] + w){
                       //음수사이클 존재
                       return false;
                   }
               }
           }
        
        
        
        
        return true;
    }
    
}