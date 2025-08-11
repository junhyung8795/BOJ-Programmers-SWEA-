import java.util.*;
import java.io.*;
 
class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        // 테케 수 입력
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
             
            int[][] table = new int[N][N];
            for(int i = 0 ;i < N; i++) {
                String s = br.readLine();
                for(int j =0 ;j < N; j++) {
                    table[i][j] = Integer.parseInt(s.charAt(j) + "");
                }
            }
             
            //0에서 부터 N / 2행가지 계산
            int sum = 0;
            for(int row = 0; row <= N / 2; row++) {
                for(int col = N / 2 - row; col <= N / 2 + row; col++) {
                    sum += table[row][col];
                }
            }
             
            //N /2 + 1행부터 N-1행까지
            for(int row = N /2  + 1; row < N; row++) {
                for(int col = row - N / 2; col <= 3 * N / 2 - 1 - row; col++) {
                    sum += table[row][col];
                }
            }
             
             
            System.out.println("#" + t + " " +sum);
             
             
             
             
             
             
             
             
        }
    }
}