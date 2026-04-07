import java.io.*;
import java.util.*;

class Main{
   
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        int N = Integer.parseInt(s[0]);
        int L = Integer.parseInt(s[1]);

        String[] inp = br.readLine().split(" ");
        int[] arr = new int[N];
        for(int i = 0; i < inp.length; i++){
            arr[i] = Integer.parseInt(inp[i]);
        }
        
        int index = 0;
        int answer = 0;
        Arrays.sort(arr);
        while(index < N){
            int start = arr[index];
            int end = arr[index] + L - 1;
            // System.out.println("index = " + index);

            for(int i = index; i < N; i++){
                if(arr[i] >= start && arr[i] <= end){
                    if(i == N - 1){
                        answer++;
                        index = N;
                        break;
                    }
                    continue;
                } else {
                    answer++;
                    index = i;
                    break;
                }
            }
        }

        System.out.println(answer);
        
    }

   






        
    
}