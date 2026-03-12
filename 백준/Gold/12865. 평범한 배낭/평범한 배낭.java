import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);
        
        //W, V입력 받아서 ArrayList넣기
        ArrayList<int[]> arrWV = new ArrayList<>();
        for(int i = 0; i < N; i++){
            String[] s1 = br.readLine().split(" ");
            int W = Integer.parseInt(s1[0]);
            int V = Integer.parseInt(s1[1]);
            arrWV.add(new int[]{W, V});
        }
        //arrWV무게가 작은 순으로 나열
        arrWV.sort((a, b) -> a[0] - b[0]);
        
        //dp테이블 선언
        int[][] dp = new int[N + 1][100000 + 1];
        
        //dp테이블 채우기
        for(int i = 1; i < N + 1; i++){
            //arrWV순회하면서 1개, 2개, 3개 고려를 시작
            int curW = arrWV.get(i - 1)[0];
            int curV = arrWV.get(i - 1)[1];
            
            for(int j = 0; j < curW; j++){
                dp[i][j] = dp[i - 1][j];
            }
            for(int j = curW; j < dp[0].length; j++){
                dp[i][j] = Math.max(dp[i - 1][j - curW] + curV , dp[i - 1][j]);
            }
            
        }
        
        System.out.println(dp[N][K]);
        
        
        
    }
}