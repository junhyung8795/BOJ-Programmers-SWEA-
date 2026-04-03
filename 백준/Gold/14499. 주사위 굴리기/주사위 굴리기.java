import java.io.*;
import java.util.*;

class Main{
    static int[] dice = {0,0,0,0,0,0,0};
    static int N;
    static int M;
    static int[][] graph;
    static int K;
    static int startRow;
    static int startCol;
    static int[] order;
    static int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
    static int[] diceStatus = {1,6,5,2,3,4};
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
            row = dr;
            col = dc;

            diceRoll(order[i]);

            
            
            if(graph[row][col] == 0){
                graph[row][col] = dice[diceStatus[1]];
                System.out.println(dice[diceStatus[0]]);
            } else{
                dice[diceStatus[1]] = graph[row][col];
                graph[row][col] = 0;
                System.out.println(dice[diceStatus[0]]);
            }
        }
        
    }
    public static void diceRoll(int orderNum){
            int tempR = diceStatus[4];
            int tempL = diceStatus[5];
            int tempT = diceStatus[0];
            int tempBt = diceStatus[1];
            int tempF = diceStatus[2];
            int tempBa = diceStatus[3];
            if(orderNum == 0){
                //동
                diceStatus[1] = tempR;
                diceStatus[0] = tempL;
                diceStatus[4] = tempT;
                diceStatus[5] = tempBt;
            } else if(orderNum == 1){
                diceStatus[5] = tempT;
                diceStatus[4] = tempBt;
                diceStatus[0] = tempR;
                diceStatus[1] = tempL;
            } else if(orderNum == 2){
                diceStatus[1] = tempBa;
                diceStatus[0] = tempF;
                diceStatus[3] = tempT;
                diceStatus[2] = tempBt;
            } else if(orderNum == 3){
                diceStatus[1] = tempF;
                diceStatus[3] = tempBt;
                diceStatus[0] = tempBa;
                diceStatus[2] = tempT;
            }

            
        }
}