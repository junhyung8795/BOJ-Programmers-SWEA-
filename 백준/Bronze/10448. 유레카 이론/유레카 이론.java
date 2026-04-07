import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        //n= 45까지면 k=1000까지 커버가능
        int[] arr = new int[45 + 1];
        for(int i = 1; i <= 45; i++){
            arr[i] = i * (i + 1) / 2;
        }
        label:
        for(int t = 0; t < T; t++){
            
            int target = Integer.parseInt(br.readLine());
            
            
            
            for(int i = 1; i <= 45; i++){
                for(int j = i ; j <= 45 ;j++){
                    for(int k = j ; k <= 45; k++){
                        if(arr[i] + arr[j] + arr[k] == target){
                            System.out.println(1);
                            continue label;
                        }
                    }
                }
            }
            System.out.println(0);
        }
        
        
        
        
    }
}