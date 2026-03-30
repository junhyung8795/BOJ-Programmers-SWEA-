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

        int[] first = bfs(1);       // 1번에서 가장 먼 노드 A
        int[] second = bfs(first[0]); // A에서 가장 먼 노드 B
        System.out.println(second[1]); // A-B 거리 = 트리의 지름
    }

    // 반환: {가장 먼 노드 번호, 거리}
    static int[] bfs(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(start);
        dist[start] = 0;
        int farthest = start;

        while (!dq.isEmpty()) {
            int cur = dq.poll();
            for (int[] edge : graph[cur]) {
                if (dist[edge[0]] == -1) {
                    dist[edge[0]] = dist[cur] + edge[1];
                    if (dist[edge[0]] > dist[farthest]) farthest = edge[0];
                    dq.add(edge[0]);
                }
            }
        }
        return new int[]{farthest, dist[farthest]};
    }
}