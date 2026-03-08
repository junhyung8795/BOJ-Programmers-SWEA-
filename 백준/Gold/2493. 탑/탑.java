import java.io.*;
import java.util.*;

class Main{
    static int N;
    static int[] arr;
    static int[] answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        
        //입력들 정수로 파싱
        arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        
        //dq선언, 기둥높이와 기둥의 index+1값.
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        
        
        //dq에 초항값 넣기
        answer = new int[N];
        dq.addLast(new int[]{arr[0], 1});
        answer[0] = 0;
        
        //2번째 기둥부터 로직 실행.
        for(int i = 1; i < N; i++){
            if(dq.peekLast()[0] < arr[i]){
                while(!dq.isEmpty()){
                    if(dq.peekLast()[0] >= arr[i]){
                        break;
                    } else{
                        dq.removeLast();
                    }
                }
                
                //다빼버렸으면 0이 자기 수신기둥(수신 못함)
                if(dq.isEmpty()){
                    answer[i] = 0;
                    dq.addLast(new int[]{arr[i], i + 1});
                } else{
                    //다 안빼고 남았다면 자기보다 크거나 같은 기둥이 있다는 뜻
                    answer[i] = dq.peekLast()[1];
                    dq.addLast(new int[]{arr[i], i + 1});
                }
            } else{
                //이미 바로앞에가 자기보다 크거나 같은 높이의 기둥임!
                answer[i] = dq.peekLast()[1];
                dq.addLast(new int[]{arr[i], i + 1});
            }
        }
        

        for(int i = 0; i < N; i++){
            System.out.print(answer[i] + " ");
        }
        
        
        
        
        
        
    }
}