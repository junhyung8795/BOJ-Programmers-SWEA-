import java.io.*;
import java.util.*;

class Main{
    static int N;
    static int M;
    static int[][] graph;
    static ArrayList<int[]> posOf2 = new ArrayList<>();
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int answer = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s= br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        graph = new int[N][M];
        
        for(int row = 0; row < N; row++){
            String[] inp = br.readLine().split(" ");
            for(int col = 0; col < M; col++){
                graph[row][col] = Integer.parseInt(inp[col]);
                if(graph[row][col] == 2)posOf2.add(new int[]{row, col});
            }
        }


        //3개의 벽을 세울 좌표를 정한다.
        backtracking(0, 0, new ArrayList<>());

        System.out.println(answer);
        
    }

    public static void backtracking(int row, int col, ArrayList<int[]> list){
        if(list.size() == 3){
            for(int[] arr:  list){
                graph[arr[0]][arr[1]] = 1;
            }
            bfs();
            //2를 기점으로 bfs완료 후 총 안전지대개수 세기
            for(int[] arr:  list){
                graph[arr[0]][arr[1]] = 0;
            }
            return;
        }
        if(row < 0 || row >= N || col < 0 || col >= M){
            return;
        }
        // System.out.println("row = " + row + " col = " + col);
        if(graph[row][col] == 0){
            list.add(new int[]{row, col});
            if(col + 1 >= M){
                backtracking(row + 1, 0, list);    
                list.remove(list.size() - 1);
                backtracking(row + 1, 0, list);
            } else if(col + 1 < M){
                backtracking(row, col + 1, list);    
                list.remove(list.size() - 1);
                backtracking(row, col + 1, list);
            }
        } else{
            if(col + 1 >= M) backtracking(row + 1, 0, list);
            else if(col + 1 < M) backtracking(row, col + 1, list);
        }
        
    }


    public static void bfs(){
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        for(int[] posOf2each: posOf2){
            dq.addLast(posOf2each);
            visited[posOf2each[0]][posOf2each[1]] = true;
        }
        int[][] graphCopy = new int[N][M];
        for(int row = 0;row < N; row++){
            for(int col = 0; col < M; col++){
                graphCopy[row][col] = graph[row][col];
            }
        }
        while(!dq.isEmpty()){
            int[] popped = dq.removeFirst();
            int row = popped[0];
            int col = popped[1];

            for(int[] dir: dirs){
                // System.out.println(Arrays.toString(dir));
                int dr = row + dir[0];
                int dc = col + dir[1];
                if(dr < 0 || dr >= N || dc < 0 || dc >= M || graphCopy[dr][dc] == 1 || visited[dr][dc]) continue;
                dq.addLast(new int[]{dr, dc});
                graphCopy[dr][dc] = 2;
                visited[dr][dc] = true;
            }
            
        }

        int candi = 0;
        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                if(graphCopy[row][col] == 0){
                    candi++;
                }
            }
        }
        answer = Math.max(answer, candi);
    }







    
}