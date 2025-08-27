import java.io.*;
import java.util.*;
 
public class Solution {
    static int N;
    static int M;
    static int[][] table;
    static int answer;
    static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            String[] inputNM = br.readLine().trim().split(" ");
            N = Integer.parseInt(inputNM[0]);
            M = Integer.parseInt(inputNM[1]);
            // 테이블 초기화
            table = new int[N][N];
            int numberOfHouse =0;
            for (int row = 0; row < N; row++) {
                String[] inputTableRow = br.readLine().trim().split(" ");
                for (int col = 0; col < N; col++) {
                    table[row][col] = Integer.parseInt(inputTableRow[col]);
                    if(table[row][col] == 1){
                        numberOfHouse++;
                    }
                }
//              System.out.println(Arrays.toString(table[row]));
            }
             
            answer = 0;
            // K는 1부터 여러가지수
            if(numberOfHouse == 1){
            answer = 1;
                return;
            }
            for (int k = 1; k <= 3 * N / 2; k++) {
                int operatingCost = numberOfHouse * M - (k * k + (k - 1) * (k - 1));
                 if (numberOfHouse * M < operatingCost) {
                    break;
                }
                for (int row = 0; row < N; row++) {
                    for (int col = 0; col < N; col++) {
                        bfs(row, col, k);
                    }
                }
            }
             
            System.out.println("#" + t + " " + answer);
        }
 
         
    }
 
    private static void bfs(int row, int col, int k) {
        // TODO Auto-generated method stub
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.offer(new int[] { row, col, k });
        visited[row][col] = true;
        int quantityHouse = 0;
//      System.out.println(row + "  " + col + "  " + k);
        while (!q.isEmpty()) {
            int[] popped = q.poll();
            int curRow = popped[0];
            int curCol = popped[1];
            int curK = popped[2];
//          System.out.println("curRow = " + curRow + "curCol " + curCol + " curK " + curK);
//          System.out.println("table[curRow][curCol] = " + table[curRow][curCol]);
            if (table[curRow][curCol] == 1) {
                quantityHouse += 1;
//              System.out.println("pluse!");
            }
             
            for (int i = 0; i < 4; i++) {
                int dr = curRow + dirs[i][0];
                int dc = curCol + dirs[i][1];
                if(dr >= 0 && dr < N && dc >=0 && dc < N) {
                    int diff = Math.abs(dr - row) + Math.abs(dc - col);
                    if(visited[dr][dc] == false && diff <= k-1) {
                        visited[dr][dc] = true;
//                      System.out.println("asdasd");
                        q.offer(new int[] {dr, dc, curK + 1});
                    }
                }
            }
        }
         
//      System.out.println("quantityHouse =  " + quantityHouse);
 
        if(quantityHouse * M - (k * k + (k - 1) * (k - 1)) >= 0) {
            answer = Math.max(quantityHouse, answer);
        }
 
    }
}