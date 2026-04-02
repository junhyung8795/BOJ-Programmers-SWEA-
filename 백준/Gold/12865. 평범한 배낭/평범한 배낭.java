import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));

        //n,k        
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        //w,v
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            list.add(new ArrayList<>());
            String[] s1 = br.readLine().split(" ");
            int w = Integer.parseInt(s1[0]);
            int v = Integer.parseInt(s1[1]);
            list.get(i).add(w);
            list.get(i).add(v);
        }
        list.sort((a,b) -> Integer.compare(a.get(0), b.get(0)));
        
        int[] dp = new int[k + 1];
        for(int i =0; i < n; i++){
            int w = list.get(i).get(0);
            int v = list.get(i).get(1);
            if(w <= k){
                for(int col = k; col >= w; col--){
                    dp[col] = Math.max(dp[col], dp[col - w] + v);
                }    
            }
            
        }

        System.out.println(dp[k]);
        
        
    }
}