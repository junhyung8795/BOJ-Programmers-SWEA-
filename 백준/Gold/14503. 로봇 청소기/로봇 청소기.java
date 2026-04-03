import java.io.*;
import java.util.*;

class Main{
    static int N;
    static int M;
    static int[][] graph;
    static int startRow;
    static int startCol;
    static int startDir;
    static int[][] dirs = {{-1, 0},{0, 1},{1, 0},{0, -1}};
    static int[] changeDir = {3, 0, 1, 2};
    static int answer = 0;
    static class Robot{
        int row;
        int col;
        int direction;
        public Robot(int row, int col, int direction){
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        @Override
        public String toString(){
            return "row = " + row + " col = " + col + " dir = " + direction;
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        String[] s1 = br.readLine().split(" ");
        startRow = Integer.parseInt(s1[0]);
        startCol = Integer.parseInt(s1[1]);
        startDir = Integer.parseInt(s1[2]);

        graph = new int[N][M];
        for(int row = 0; row < N; row++){
            String[] inp = br.readLine().split(" ");
            for(int col = 0; col < M; col++){
                graph[row][col] = Integer.parseInt(inp[col]);
            }
        }

        

        cleaning(new Robot(startRow, startCol, startDir));




        System.out.println(answer);







        
    }


    public static void cleaning(Robot robot){
        int row = robot.row;
        int col = robot.col;
        int direction = robot.direction;
        while(true){
            // System.out.println("row = " + row + " col = " + col + " direction = " + direction);
            
            if(graph[row][col] == 0){
                graph[row][col] = -1;
                answer++;
            }   
            //본래방향 확인
            int dr;
            int dc;
            int candiD = direction;
            boolean ok = false;
            for(int i = 0; i < 4; i++){
                
                
                    candiD = changeDir[candiD];
                    dr = row + dirs[candiD][0];
                    dc = col + dirs[candiD][1];
                    if(dr < 0 || dr >= N || dc < 0 || dc >= M) continue;
                    if(graph[dr][dc] == 0){
                        row = dr;
                        col = dc;
                        direction = candiD;
                        ok = true;
                        break;
                    }
                
                
            }
            if(!ok){
                // System.out.println("not ok");
                dr = row - dirs[direction][0];
                dc = col - dirs[direction][1];
                if(dr < 0 || dr >= N || dc < 0 || dc >= M || graph[dr][dc] == 1) {
                    break;   
                } else{
                    row = dr;
                    col = dc;
                }
            }
            // if(row == 9 && col == 3 && direction == 1){
            //     for(int r = 0; r < N; r++){
            //         System.out.println(Arrays.toString(graph[r]));
            //     }
            //     break;
            // }

            
        }
    }










    
}