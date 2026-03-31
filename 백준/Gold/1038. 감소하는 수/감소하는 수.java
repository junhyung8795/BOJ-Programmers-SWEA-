import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        List<Long> list = new ArrayList<>();
        
        for(int mask = 1 ; mask < (1 << 10) ; mask++){
            StringBuilder sb = new StringBuilder();
            for(int i = 9; i >= 0; i--){
                if((mask & (1 << i) )!= 0){
                    //0이 아니라는 뜻은 해당 비트 위치에 1이 있다라는 것.
                    //한자리씩 확인하면서 비트가 있는지 확인하려면 and연산했을 때 0이 아니면 된다.
                    sb.append(i);
                }
            }
            list.add(Long.parseLong(sb.toString()));
        }
        list.sort((a,b) -> Long.compare(a,b));
        if(N >= list.size()){
            System.out.println(-1);
        }else{
            System.out.println(list.get(N));
        }    
                

        
        
    }
}