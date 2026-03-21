import java.util.*;
import java.io.*;


class Solution
{
    public int solution(String s)
    {
        int answer = -1;
        char[] charArray = s.toCharArray();
        
        //하나씩 스택에 넣는다.
        ArrayDeque<Character> dq = new ArrayDeque<>();
        
        //charArray길이저장
        int charArraylen =  charArray.length;
        
        //루프돌면서 dq의 peeklast와 i번째 문자와 비교
        //같으면 dq에서 removeLast하고 그다음 i번째 문자와 peekLast 계속 비교
        //이랬는데, dq에 문자가 남아있다면 짝짓기가 불가능함므로 0반환
        for(int i = 0; i < charArraylen; i++){
            // System.out.println(dq);
            if(dq.isEmpty()){
                dq.addLast(charArray[i]);
                continue;
            }
            
            
            if(dq.peekLast() == charArray[i]){
                dq.removeLast();
            } else {
                dq.addLast(charArray[i]);
            }
        }
        
        if(!dq.isEmpty()){
            answer = 0;
        } else{
            answer = 1;
        }
        
        
        return answer;
    }
}