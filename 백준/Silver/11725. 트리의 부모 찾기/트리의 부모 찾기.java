import java.io.*;
import java.util.*;

class Main{
    static ArrayList<Integer>[] graph;
    static int N;
    static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        parent = new int[N + 1];
        for(int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < N - 1; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            graph[u].add(v);
            graph[v].add(u);
        }


        bfs();
        
        for(int i = 2; i < N + 1; i++){
            System.out.println(parent[i]);
        }
    }

    static void bfs(){
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;
        dq.add(1);

        while(!dq.isEmpty()){
            int curNode = dq.poll();

            for(int next : graph[curNode]){
                if(!visited[next]){
                    visited[next] = true;
                    dq.add(next);
                    parent[next] = curNode;
                }
                
            }
        }
    }
}