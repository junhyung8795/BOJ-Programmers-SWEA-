import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(br.readLine());
            if(num == 0){
                dq.removeLast();
            } else{
                dq.addLast(num);    
            }
            
        }
        
        int answer = 0;
        int s = dq.size();
        for(int i = 0; i < s; i++){
            answer += dq.removeLast();
        }
        System.out.println(answer);
        
    }
}