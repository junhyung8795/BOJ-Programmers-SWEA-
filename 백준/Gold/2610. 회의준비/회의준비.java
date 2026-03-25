import java.io.*;
import java.util.*;

class Main{
    static int N;
    static int M;
    static int[] parent;
    static int[][] dist;
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    static ArrayList<Edge> edges = new ArrayList<>();
    static ArrayList<Integer> answers = new ArrayList<>();
    static class Edge{
        int u;
        int v;
        public Edge(int u, int v){
            this.u = u;
            this.v = v;
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());    
        
        //M줄 동안간선 정보받기, dist초기화
        dist = new int[N + 1][N + 1];
        for(int i = 0; i < N + 1; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);    
            dist[i][i] = 0;
        }
        //간선정보받으면서 dist[][]를 최소로 유지
        for(int i = 0; i < M; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            dist[u][v] = Math.min(dist[u][v], 1);
            dist[v][u] = Math.min(dist[v][u], 1);
            edges.add(new Edge(u, v));
        }
        
        //플로이드 와샬
        floyd();
        
        makeSet();
        //간선 순회하면서 유니온파인드
        for(Edge edge: edges){
            unionSet(edge.u, edge.v);
        }
        
        //한번더 파인드셋 적용
        for(int i = 1; i <= N; i++){
            parent[i] = findSet(i);
            map.put(parent[i], new ArrayList<>());
        }
        //map의 각 키에서 나오는 ArrayList에다가 수들을 저장
        for(int i = 1; i <= N; i++){
            map.get(parent[i]).add(i);
        }
        
        //map에 각 키는 부모 자식은 자기자신을 포함한 자식들이 들어감
        //키가 위원회가 됨.먼저 위원회의 개수를 출력
        System.out.println(map.size());
        
        for(Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()){
            int top = -1;
            ArrayList<Integer> arr = entry.getValue();
            int minOfmax = Integer.MAX_VALUE;
            for(int i : arr){
                int cur = 0;
                for(int j : arr){
                    if(i != j){
                        cur = Math.max(cur, dist[i][j]);
                    }
                }
                if(cur < minOfmax){
                    minOfmax = cur;
                    top = i;
                }
            }
            answers.add(top);
        }
        
        answers.sort((a, b) ->a - b);
        for(int i = 0; i < answers.size(); i++){
            System.out.println(answers.get(i));
        }
        
        
        
    }
    
    public static void floyd(){
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];                                                
                    }
                }
            }
        }
    }
    
    public static void makeSet(){
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++){
            parent[i] = i;
        }
    }
    
    public static int findSet(int x){
        if(parent[x] != x){
            parent[x] = findSet(parent[x]);
        }
        return parent[x];
    }
    
    public static void unionSet(int a, int b){
        int pa = findSet(a);
        int pb = findSet(b);
        if(pa == pb){
            return;
        }
        if(pa <= pb){
            parent[pa] = pb;    
        } else{
            parent[pb] = pa;
        }
        
        
    }
    
}