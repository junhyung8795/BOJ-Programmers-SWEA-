import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int maxNum = (1 << 21) - 1;
        

        int original = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N ; i++){
            String[] s = br.readLine().split(" ");
            String order = s[0];

            if(order.equals("add")){
                            int x = Integer.parseInt(s[1]);

                original = (1 << x) | original;
            } else if(order.equals("remove")){
                            int x = Integer.parseInt(s[1]);

                if((original & (1 << x)) != 0){
                    original -= (1 << x);
                }
            } else if(order.equals("check")){
                            int x = Integer.parseInt(s[1]);

                if((original & (1 << x)) != 0){
                    sb.append(1 + "\n");
                } else{
                    sb.append(0 + "\n");
                }
            }else if(order.equals("toggle")){
                            int x = Integer.parseInt(s[1]);

                original = (1 << x) ^ original;
            }else if(order.equals("all")){
                original = maxNum;
            } else{
                original = 0;
            }
        }
        
        System.out.println(sb.toString());
    }
}