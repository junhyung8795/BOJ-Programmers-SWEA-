import java.util.*;
import java.io.*;

class Main{
    static int N;
    static int M;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        char[] s = br.readLine().toCharArray();
        
        int index = 0;
        for(int i = 0; i <= M - (2 * N + 1); i++){
            if(s[i] == 'I'){
            index = i;
            break;
            }
        }
        int answer = 0;
        boolean notCrack = false;
        
        while(index <= M - (2 * N + 1)){
            if(notCrack){
                int end = index + 2 * N;
                
                if(s[end] == 'I' && s[end - 1] == 'O'){
                    answer++;
                    index += 2;
                } else{
                    int candi = M - (2 * N + 1) + 1;
                    for(int i = end - 1 ; i <= M - (2 * N + 1); i++){
                        if(s[i] == 'I'){
                            candi = i;
                            break;
                        }
                    }
                    index = candi;
                    notCrack = false;
                }
            } else if(!notCrack){
                boolean check = true;
                int errorPoint = -1;
                for(int i = index ; i < index + 2 * N + 1; i++){
                    
                    if(!(((i - index) % 2 == 0 && s[i] == 'I') || ((i - index) % 2 == 1 && s[i] == 'O'))){
                        check = false;
                        errorPoint = i;
                        break;
                    }
                    
                }
                if(check){
                   notCrack = true; 
                }
                
                if(notCrack){
                                        

                    answer++;
                    index += 2;
                } else{
                    int rearI = M - (2 * N + 1) + 1;
                    for(int i = errorPoint; i <= M - (2 * N + 1); i++){
                        if(s[i] == 'I'){
                            rearI = i;
                            break;
                        }
                    }
                    index = rearI;
                }
                
                
                
                
                
            }
    }
         System.out.println(answer);
                       
   
}}