import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static ArrayList<Edge>[] graph;
    static class Edge{
        int v;
        int w;
        public Edge(int v, int w){
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString(){
            return "v = " +v + " w = " + w;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for(int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 1; i <= N; i++){
            String[] s = br.readLine().split(" ");
            int node = Integer.parseInt(s[0]);
            for(int j = 1; j < s.length; j += 2){
                
                int v = Integer.parseInt(s[j]);
                 if(v == -1)continue;
                int w = Integer.parseInt(s[j + 1]);
               
                graph[node].add(new Edge(v, w));
            }
        }

        //임의의 점 1로부터 제일 먼 노드는 지름의 양끝점 중 하나이다.
        int[] result1 = bfs(1);
        int[] result2 = bfs(result1[0]);
        System.out.println(result2[1]);
        
    }

    public static int[] bfs(int start){
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{start, 0});
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        dist[start] = 0;
        //임의의 시작점에서 다른 점으로 이어지는 경로는 하나다. 트리니까
        //사이클이 없음
        int farthestNode = -1;
        int farthestDist = -1;
        while(!dq.isEmpty()){
            int[] popped = dq.poll();
            int curNode = popped[0];
            int curDist = popped[1];
            if(dist[curNode] == -1){
                dist[curNode] = curDist;
            }

            if(dist[curNode] > farthestDist){
                farthestDist = dist[curNode];
                farthestNode = curNode;
            }

            for(Edge edge: graph[curNode]){
                int v = edge.v;
                int w = edge.w;
                if(dist[v] != -1){
                    continue;
                }
                dq.add(new int[]{v, w + curDist});
                
            }
        }


        return new int[]{farthestNode, dist[farthestNode]};


        
    }
}