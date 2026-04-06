import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        
        List<Integer> arr = new ArrayList<>();
        int total = 0;
        for(int i = 0 ; i < 9; i++){
            arr.add(Integer.parseInt(br.readLine()));
            total += arr.get(i);
        }
        
        int exceed = total - 100;
        int a = -1;
        int b = -1;
        label:
        for(int i = 0; i < 9; i++){
            for(int j = i + 1; j< 9; j++){
                if(arr.get(i) + arr.get(j) == exceed){
                    arr.remove(j);
                    arr.remove(i);
                    break label;
                }
            }
        }

        arr.sort((c,d) -> c - d);
        for(int i = 0; i < 7; i++){
            System.out.println(arr.get(i));
        }

        
    }
}
