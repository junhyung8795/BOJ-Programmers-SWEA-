import java.io.*;
import java.util.*;

class Main{
    static int M;
    static int N;
    static int[][] graph;
    static int endRow;
    static int endCol;
    static int startRow;
    static int startCol;
    static int answer;
    static int[][] visited;
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static class Node{
        int row;
        int col;
        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] a = br.readLine().split(" ");
        M = Integer.parseInt(a[0]);
        N = Integer.parseInt(a[1]);
        visited = new int[M][N];
        graph = new int[M][N];
        for(int row = 0; row < M; row++){
            String[] s = br.readLine().split(" ");
            for(int col = 0; col < N; col++){
                graph[row][col] = Integer.parseInt(s[col]);
            }
        }

        startRow = 0;
        startCol = 0;
        endRow = M - 1;
        endCol = N - 1;
        visited = new int[M][N];
        for(int[] row : visited) Arrays.fill(row, -1);
        dfs(0, 0);
        
        System.out.println(visited[0][0]);
                
    }
    public static int dfs(int row, int col){
        if((row == endRow && col == endCol)){
            return 1;
        }
        if(visited[row][col] != -1){
            return visited[row][col];
        }
        visited[row][col] = 0;
        for(int[] dir: dirs){
            int dr = row + dir[0];
            int dc = col + dir[1];
            //범위벗어나면 컨티뉴
            if(dr < 0 || dr >= M || dc < 0 || dc >= N){
                continue;
            }

            //자기보다 낮은곳으로만 점프하므로 큰곳은 스킵
            if(graph[dr][dc] >= graph[row][col]){
                continue;
            }
            
            visited[row][col] += dfs(dr, dc);         
        }
        
        return visited[row][col];
    }






    

    
}