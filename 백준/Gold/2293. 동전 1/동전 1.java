import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");        
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);
        for(int i = 1; i <= n; i++){
            arr.add(Integer.parseInt(br.readLine()));
        }
        arr.sort((a,b) -> Integer.compare(a,b));

        int[][] dp = new int[n + 1][k + 1];

        for(int row = 0; row < n + 1; row++){
            dp[row][0] = 1;
        }
        for(int row = 1; row <= n; row++){
            int nThNum = arr.get(row);
            if(nThNum > k) {
                 for(int col = 0; col < k + 1; col++){
                    dp[row][col] = dp[row - 1][col];
                }
            }else{
                for(int col = 0; col < nThNum; col++){
                    dp[row][col] = dp[row - 1][col];
                }
                for(int col = nThNum ; col < k + 1; col++){
                    dp[row][col] = dp[row - 1][col] + dp[row][col - nThNum];
                }    
            }
            
        }

        // for(int i = 0; i < n + 1; i++){
        //     System.out.println(Arrays.toString(dp[i]));
        // }

        System.out.println(dp[n][k]);
    }
}