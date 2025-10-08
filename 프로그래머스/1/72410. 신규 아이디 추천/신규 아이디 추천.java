import java.util.*;
class Solution {
    static HashSet<Character> alphabetSet = new HashSet<>();
    static HashSet<Integer> numberSet = new HashSet<>();
    public String solution(String new_id) {
        String answer = "ASDS.~!@#$%^&*()=+[{]}:?,<>/";
        String a = answer.toLowerCase();
        
        
        
        int N = new_id.length();
        for(int i = 97 ; i <= 122; i++){
            alphabetSet.add((char) i);
        }
        alphabetSet.add('-');
        alphabetSet.add('_');
        alphabetSet.add('.');
        alphabetSet.add('0');
        alphabetSet.add('1');
        alphabetSet.add('2');
        alphabetSet.add('3');
        alphabetSet.add('4');
        alphabetSet.add('5');
        alphabetSet.add('6');
        alphabetSet.add('7');
        alphabetSet.add('8');
        alphabetSet.add('9');
        
        
        
        
        ArrayDeque<Character> dq = new ArrayDeque<>();
        char[] lower_new_id = new_id.toLowerCase().toCharArray();
        //2.
        for(int i = 0; i < lower_new_id.length; i++){
            if(alphabetSet.contains(lower_new_id[i]) ){
                dq.addLast(lower_new_id[i]);
            }
        }
        
        // System.out.println(dq);
        
        //3. 모든 연속된 .을 . 하나로 치환
        ArrayDeque<Character> dq2 = new ArrayDeque<>();
        while(!dq.isEmpty()){
            Character popped = dq.removeFirst();
            if(popped == '.'){
                //이 경우 연속으로 Dq2에 넣을 가능성이 있으니
                //peekLast했을 때 .이면 안됨.
                if(!dq2.isEmpty() && dq2.peekLast() == '.'){
                    continue;
                }
            }
            
            dq2.addLast(popped);
        }
        // System.out.println(dq2);

        
        //4.가 처음이나 끝에 있으면 제거
        if(!dq2.isEmpty()){
            if(dq2.peekFirst() == '.'){
                dq2.removeFirst();
            }
        }
        if(!dq2.isEmpty()){
            if(dq2.peekLast() == '.'){
                dq2.removeLast();
            }
        }
       
        // System.out.println(dq2);
        //5.new_id가 빈문자열이면 "a"를대입
        if(dq2.isEmpty()){
            dq2.addLast('a');
        }
        // System.out.println(dq2);
        
        //6.16자 이상이면 15자가 될때까지 자르고
        //맨 뒤가 .이면 .도 제거
        while(dq2.size() > 15){
            dq2.removeLast();
        }
        
        if(!dq2.isEmpty() && dq2.peekLast() == '.'){
            dq2.removeLast();
        }
        // System.out.println(dq2);
        //7.길이가 2 이하면 마지막 문자를 길이가 3 될때까지 끝에 붙임.
        if(dq2.size() <= 2){
            Character lastChar = dq2.peekLast();
            // System.out.println("lastChar = " + lastChar);

            while(dq2.size() <3){
                dq2.addLast(lastChar);
            }
        }
        // System.out.println(dq2);
        StringBuilder sb = new StringBuilder();
        while(!dq2.isEmpty()){
            char popped = dq2.removeFirst();
            sb.append(popped + "");
        }
        
        
        return sb.toString();
        
    }
}