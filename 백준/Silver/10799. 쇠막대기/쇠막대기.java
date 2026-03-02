import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] inputArr = br.readLine().toCharArray();
        
        ArrayDeque<Character> dq = new ArrayDeque();
        
        int nowC = 0;
        int recent = 0;
        int index = 0;
        int answer = 0;
        while(index < inputArr.length){
            if(inputArr[index] == '('){
                dq.addLast(inputArr[index]);
                nowC += 1;
                recent = index;
            }
            else {
                if((index - recent) == 1){
                    dq.removeLast();
                    nowC -= 1;
                    answer += nowC;
                }
                else{
                    dq.removeLast();
                    nowC -= 1;
                    answer += 1;
                }
            }
            index++;
            
            
            
            
        }
        System.out.println(answer);
        
        
    }
}