import java.io.*;
import java.util.*;

class Main{
    static int N;
    static int[][] grid;
    static int[][] dirs = {{0,1}, {-1, 0}, {0, -1}, {1, 0}};
    static int[] changeDir = {1,2,3,0};
    static int[][] curves;
    static int answer = 0;
    static class Arrow{
        int dir;
        public Arrow(int dir){
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        grid = new int[101][101];
        N = Integer.parseInt(br.readLine());
        curves = new int[N][4];
        for(int i = 0; i < N; i++){
            String[] s = br.readLine().split(" ");
            curves[i][0] = Integer.parseInt(s[0]);
            curves[i][1] = Integer.parseInt(s[1]);
            curves[i][2] = Integer.parseInt(s[2]);
            curves[i][3] = Integer.parseInt(s[3]);
        }
        
        for(int i = 0; i < N; i++){
            draw(i);
        }
        
        for(int row = 0; row < 100; row++){
            for(int col = 0; col < 100; col++){
                if(grid[row][col] == 1 && grid[row + 1][col] == 1 && grid[row][col + 1] == 1 && grid[row + 1][col + 1] == 1){
                    answer++;
                }   
            }
        }

        System.out.println(answer);
        
        
        
        

        
        
    }
    
    
    public static void draw(int curveIndex){
        int row = curves[curveIndex][1];
        int col = curves[curveIndex][0];
        int curveDir = curves[curveIndex][2];
        int curveGen = curves[curveIndex][3];
        grid[row][col] = 1;
        int curGen = 0;
        ArrayDeque<Arrow> dq= new ArrayDeque<>();
        dq.addLast(new Arrow(curveDir));
        row += dirs[curveDir][0];
        col += dirs[curveDir][1];
        grid[row][col] = 1;
        
        for(int i = 0; i < curveGen; i++){
            int s = dq.size();
            ArrayDeque<Arrow> dq2 = new ArrayDeque<>();
            for(int j = 0; j < s; j++){
                Arrow popped = dq.removeLast();
                int dir = popped.dir;
                int newDir = changeDir[dir];
                row += dirs[newDir][0];
                col+= dirs[newDir][1];
                grid[row][col] = 1;
                dq.addFirst(popped);
                dq2.addLast(new Arrow(newDir));
            }
            
             
            while(!dq2.isEmpty()){
                dq.addLast(dq2.removeFirst());
            }
        }
        
    }
    
}