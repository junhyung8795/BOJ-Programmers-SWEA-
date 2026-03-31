import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int S = Integer.parseInt(s[1]);
        int answer = 0;
        int[] nums = new int[N];
        String[] ip = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(ip[i]);
        }
        // System.out.println(Arrays.toString(nums));

        for(int mask = 1;  mask < (1 << N) ; mask++){
            int candi = 0;
            for(int j = N - 1 ; j >= 0; j--){
                if((mask & (1 << j)) != 0){
                    candi += nums[j];
                    
                }
            }
            if(candi == S){
                answer += 1;
            }
        }

        System.out.println(answer);
    }
}