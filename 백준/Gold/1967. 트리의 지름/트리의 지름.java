import java.io.*;
import java.util.*;

class Main {
    static int n;
    static ArrayList<int[]>[] graph; // {인접노드, 가중치}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w}); // 양방향!
        }
        
        int[] result1 = bfs(1);
        int[] result2 = bfs(result1[0]);
        System.out.println(result2[1]);
       
    }

    // 반환: {가장 먼 노드 번호, 거리}
    static int[] bfs(int start) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{start, 0});
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[start] = 0;
        int farthest = start;
        
        while(!dq.isEmpty()){
            int[] popped = dq.poll();
            int curNode = popped[0];
            int curDist = popped[1];
            
            for(int[] edge : graph[curNode]){
                int v = edge[0];
                int w = edge[1];
                if(dist[v] == -1){
                    dist[v] = curDist + w;
                    if(dist[farthest] < dist[v]){
                        farthest = v;
                    }
                    dq.add(new int[]{v, w + curDist});
                }
            }
        }
        return new int[]{farthest, dist[farthest]};
        
        
    }
}