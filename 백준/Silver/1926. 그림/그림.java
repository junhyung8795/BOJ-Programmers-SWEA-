import java.io.*;
import java.util.*;

class Main{
    static int n;
    static int m;
    static int[][] grid;
    static int count = 0;
    static int maxArea = 0;
    static boolean[][] visited;
    
    static class Node{
        int row;
        int col;
        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    
    static int[][] dirs = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        grid = new int[n][m];
        visited = new boolean[n][m];
        //격자 입력 받기
        for(int row = 0; row < n; row++){
            String[] s1 = br.readLine().split(" ");
            for(int col = 0; col < m; col++){
                grid[row][col] = Integer.parseInt(s1[col]);
            }
        }
        
        
        //1탐색하며 dfs
        for(int row = 0; row < n; row++){
            for(int col = 0; col < m ; col++){
                if(grid[row][col] == 1 && !visited[row][col]){
                    dfs(row, col);
                }
            }
        }
        
        System.out.println(count);
        System.out.println(maxArea);
        
    }
    
    public static void dfs(int row, int col){
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(row, col));
        int candi = 0;
        
        while(!stack.isEmpty()){
            Node popped = stack.pop();
            int curRow = popped.row;
            int curCol = popped.col;
            
            if(visited[curRow][curCol]){
                continue;
            }
            //방문처리 후 후보군이 되는 넓이 값을 +1해줌.
            visited[curRow][curCol] = true;
            candi += 1;
            
            //4방 탐색하면서 유효성검사 후 스택에 넣기
            for(int i = 0; i < 4; i++){
                int dr = curRow + dirs[i][0];
                int dc = curCol + dirs[i][1];
                
                if(dr >= 0 && dr < n && dc >= 0 && dc < m){
                    if(!visited[dr][dc] && grid[dr][dc] == 1){
                        stack.push(new Node(dr, dc));
                    }
                    
                    
                }
            }
            
        }
        
        maxArea = Math.max(maxArea, candi);
        count += 1;
        
    }
}