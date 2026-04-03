import java.io.*;
import java.util.*;

class Main{
    static int[][] nextPos = {
        {7,12,20,16},
        {6,14,21, 18},
        {5,13,22,19},
        {4,15,23,17},
        {8,3,6,5},
        {10,2,4,7},
        {9,1,7,4},
        {11,0,5,6},
        {15,4,16,20},
        {14,6,19,22},
        {13,5,18,21},
        {12,7,17,23},
        {0,11,14,13},
        {2,10,12,15},
        {1,9,15,12},
        {3,8,13,14},
        {19,18,0,8},
        {18,19,3,11},
        {16,17,1,10},
        {17,16,2,9},
        {21,22,8,0},
        {23, 20, 10, 1},
        {20,23,9,2},
        {22,21,11,3}
    };
    static int[] floorForDir = {6,6,6,6,4,2,5,3,1,1,1,1,4,5,2,3,5,2,4,3,2,3,4,5};
    static int[] dice = {0,0,0,0,0,0,0};
    static int N;
    static int M;
    static int[][] graph;
    static int curPos = 0;
    static int K;
    static int startRow;
    static int startCol;
    static int[] order;
    static int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        startRow = Integer.parseInt(s[2]);
        startCol = Integer.parseInt(s[3]);
        K = Integer.parseInt(s[4]);
        order = new int[K];
        graph = new int[N][M];
        for(int row = 0; row < N ;row++){
            String[] a = br.readLine().split(" ");
            for(int col = 0; col < M; col++){
                graph[row][col] = Integer.parseInt(a[col]);
            }
        }

        String[] b = br.readLine().split(" ");
        for(int i = 0; i < K ; i++){
            order[i] = Integer.parseInt(b[i]) - 1;
        }

        int row = startRow;
        int col = startCol;
        for(int i = 0; i < K; i++){
            //명령대로 움직인다.
            
            int dr = row + dirs[order[i]][0];
            int dc = col + dirs[order[i]][1];
            if(dr < 0 || dr >= N || dc < 0 || dc >= M)continue;
            curPos = nextPos[curPos][order[i]];
            row = dr;
            col = dc;
            // System.out.println("dr = " + dr + " dc  = " + dc);
            if(graph[row][col] == 0){
                //바닥면은?floorForDir[curPos]
                graph[row][col] = dice[floorForDir[curPos]];
                System.out.println(dice[7-floorForDir[curPos]]);
            } else{
                dice[floorForDir[curPos]] = graph[row][col];
                graph[row][col] = 0;
                System.out.println(dice[7-floorForDir[curPos]]);
            }
        }










        
    }
}