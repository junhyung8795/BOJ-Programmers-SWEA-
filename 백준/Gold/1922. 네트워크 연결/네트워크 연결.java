import java.io.*;
import java.util.*;

class Main{
    static int N;
    static int M;
    static int[] parent;
    static ArrayList<Edge> edges;
    static class Edge{
        int u;
        int v;
        int w;
        public Edge(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //nm입력받기
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        //M개의 줄동안 간선정보 입력
        edges = new ArrayList<>();
        for(int i = 0; i < M; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);
            edges.add(new Edge(u, v, w));
        }
        //간선을 w오름차순으로 정렬
        edges.sort((a,b) -> a.w - b.w);
        
        makeSet();
        
        //오름차순으로 간선을 살피면서 해당 간선을 포함시킬지 말지 결정
        //unionSet이 false면 total과 cnt 갱신 X 
        //true면 갱신
        //마지막에는 Cnt가 N - 1인지 확인하고 같다면 브레이크
        int total = 0;
        int cnt = 0;
        for(Edge edge: edges){
            int u = edge.u;
            int v = edge.v;
            int w = edge.w;
            
            //유니온의 결과확인
            boolean result = unionSet(u, v);
            //거짓이면
            if(!result){
                //합치지 않는다. total, cnt갱신 X
                continue;
            }
            //참이면
            total += w;
            cnt++;
            
            //마지막으로 cnt가 N-1인지 검사
            if(cnt == N - 1){
                break;
            }
        }
        
        System.out.println(total);
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
    
    public static boolean unionSet(int a, int b){
        int pa = findSet(a);
        int pb = findSet(b);
        
        if(pa == pb){//사이클이 생기므로 더이상 합치지 않음.
            return false;
        }
        parent[pa] = pb;
        return true;
    }
}