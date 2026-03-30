import java.io.*;
import java.util.*;

class Main{
    static int N;
    static int M;
    static int[][] grid;
    static boolean[][] visited;
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static class Node{
        int row;
        int col;
        int cnt;
        public Node(int row, int col, int cnt){
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
        
        @Override
        public String toString(){
            return "row = " + row + " col = " + col + " cnt = " + cnt;
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        grid = new int[N][M];
        for(int row = 0; row < N; row++){
            char[] inpStr = br.readLine().toCharArray();
            for(int col = 0; col < M; col++){
                grid[row][col] = Integer.parseInt(inpStr[col] + "");
            }
        }
        
        bfs();

        
    }
    
    public static void bfs(){
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.addLast(new Node(0,0,1));
        visited = new boolean[N][M];
        visited[0][0] = true;

        while(!q.isEmpty()){

            Node popped = q.removeFirst();
            int row = popped.row;
            int col = popped.col;
            int cnt = popped.cnt;


            if(row == N - 1 && col == M - 1){
                System.out.println(cnt);
                return;
            }
            
            for(int[] dir: dirs){
                int dr = row + dir[0];
                int dc = col + dir[1];

                if(dr < 0 || dr >= N || dc < 0 || dc >= M)
                    continue;
                if(visited[dr][dc])
                    continue;
                if(grid[dr][dc] == 0){
                    continue;
                }
                visited[dr][dc] = true;
                q.addLast(new Node(dr, dc ,cnt + 1));

            }
        }
    }
}