import java.io.*;
import java.util.*;

class Main{
   
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = 0;
        while(true){
            caseNum++;
            String[] s = br.readLine().split(" ");
            int L = Integer.parseInt(s[0]);
            int P = Integer.parseInt(s[1]);
            int V = Integer.parseInt(s[2]);
            if(L == 0 && P == 0 && V == 0) break;
            int result = 0;
            int fullDay = V / P;
            result = fullDay * L + Math.min(V - fullDay * P, L);
            System.out.println("Case "+caseNum + ": " + result);
        }
        
        
    }

   






        
    
}