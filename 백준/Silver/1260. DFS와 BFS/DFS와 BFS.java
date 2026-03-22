import java.util.*;
import java.io.*;

class Main{
    static int N;
    static int M;
    static int start;

    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        //0부터 N까지 인덱스를 가지는 그래프
        graph = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i++){
            graph[i] = new ArrayList<Integer>();
        }
        M = Integer.parseInt(s[1]);
        start = Integer.parseInt(s[2]);
        
        //양방향 간선.
        //M개의 줄로 입력을 받는다.
        //인접 리스트로 그래프 구현
        for(int i = 0; i < M; i++){
            String[] edgeInput = br.readLine().split(" ");
            int u = Integer.parseInt(edgeInput[0]);
            int v = Integer.parseInt(edgeInput[1]);
            graph[u].add(v);
            graph[v].add(u);
        }
        
        //그래프에서 각 노드와 인접한 다른 노드들을 방문할 때 번호가 작은 순으로 들어가야하므로 정렬을 함.
        for(int i = 0; i < N + 1; i++){
            graph[i].sort((a, b) -> a - b);
        }
        
        dfs();
        bfs();
        
            
    }
    
    public static void dfs(){
        //stack visited
        //stack에서 요소를 빼면서 방문처리
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        dq.addLast(start);
        StringBuilder sb = new StringBuilder();
        
        while(!dq.isEmpty()){
            //안비면 빼면서 Popped보고 여기에 붙어있는 다른 노드들 탐색 단 이미 방문 처리된 노드는 dq에 넣으면 안됨
            int curNode = dq.removeLast();
            if(visited[curNode]){
                continue;
            }
            //방문안햇으면 방문처리
            visited[curNode] = true;
            sb.append(curNode + " ");
            
            //현재 노드 기준 연결된 다른 노드들을 탐색하고 방문이 안된애들을 dq에 넣기.
            //그런데 오름차 정렬로 넣었으니 스택에서 뽑을 때 작은 거부터 뽑고 싶다면 for문을 역으로 탐색.
            for(int i = graph[curNode].size() - 1; i >= 0; i--){
                int next = graph[curNode].get(i);
                if(!visited[next]){
                    dq.addLast(next);    
                }
            }
            
        }
        
        System.out.println(sb.toString());
        
    }
    
    public static void bfs(){
        //q visited
        //stack에서 요소를 넣으면서 방문처리
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        dq.addFirst(start);
        visited[start] = true;
        StringBuilder sb = new StringBuilder();
        
        while(!dq.isEmpty()){
            //안비면 빼면서 Popped보고 여기에 붙어있는 다른 노드들 탐색 단 이미 방문 처리된 노드는 dq에 넣으면 안됨
            int curNode = dq.removeFirst();
            sb.append(curNode + " ");
            //현재 노드 기준 연결된 다른 노드들을 탐색하고 방문이 안된애들을 dq에 넣기.
            //그런데 오름차 정렬로 넣었으니 스택에서 뽑을 때 작은 거부터 뽑고 싶다면 for문을 역으로 탐색.
            for(int i = 0; i < graph[curNode].size(); i++){
                int next = graph[curNode].get(i);
                if(!visited[next]){
                    visited[next] = true;
                    dq.addLast(next);    
                }
            }
            
        }
        
        System.out.println(sb.toString());
        
    }
    
}