import java.io.*;
import java.util.*;

class Main{
    //테케수
    static int T;
    //각 테케안의 지점수, 도로수, 웜홀 수
    static int N;
    static int M;
    static int W;
    static int start;
    //간선을 담는 그래프
    static ArrayList<Edge> graph;
    static long[] dist;
    //간선을 나타내는 클래스 
    static class Edge{
        int u;
        int v;
        int w;
        public Edge(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
        
        @Override
        public String toString(){
            return "u = " + u + " v = " + v + " w = " + w; 
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            //각 테케안에서 로직 실행
            String[] inp = br.readLine().split(" ");  
            N = Integer.parseInt(inp[0]);
            M = Integer.parseInt(inp[1]);
            W = Integer.parseInt(inp[2]);
            
            //그래프 초기화, Edge들이 들어감.
            graph = new ArrayList<>();
            
            //도로라는 간선 정보 입력
            for(int i = 0; i < M; i++){
                String[] eInp = br.readLine().split(" ");
                int u = Integer.parseInt(eInp[0]);
                int v = Integer.parseInt(eInp[1]);
                int w = Integer.parseInt(eInp[2]);
                //도로는 양방향 간선
                graph.add(new Edge(u,v,w));
                graph.add(new Edge(v,u,w));
            }
            
            //웜홀이라는 간선 정보입력
            for(int i = 0 ; i < W; i++){
                String[] eInp = br.readLine().split(" ");
                int u = Integer.parseInt(eInp[0]);
                int v = Integer.parseInt(eInp[1]);
                int w = Integer.parseInt(eInp[2]);
                //웜홀은 단방향 간선
                graph.add(new Edge(u,v,-w));
                
            }
            
            //dist배열 초기화
            //1번 지점을 출발지점으로 놓고. 만약 음의 사이클이 생긴다면 YES 아니면 NO출력
            dist = new long[N + 1];
            //일단 다른 지점들 전부 못간다고 가정
            Arrays.fill(dist, Integer.MAX_VALUE);
            //시작점은 0의 거리를 가짐
            dist[1] = 0l;
            
            String result = BF();
                
            
            
            System.out.println(result);
            
        }
        
        
        
    }
    
    public static String BF(){
        //벨먼포드
        for(int i = 0; i < N - 1;i++){
            for(Edge edge: graph){
                int u = edge.u;
                int v = edge.v;
                int w = edge.w;
                
                if(dist[u] + w < dist[v]){
                    dist[v] = dist[u] + w;
                }
            }
        }
        
        for(Edge edge: graph){
                int u = edge.u;
                int v = edge.v;
                int w = edge.w;
                
                if( dist[u] + w < dist[v]){
                    return "YES";
                }
            }
        
        return "NO";
    }
}