import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String[] s1 = br.readLine().split(" ");
        Integer[] arrN = new Integer[N];
        for (int i = 0; i < N; i++){
            arrN[i] = Integer.parseInt(s1[i]);
        }
        
        int M = Integer.parseInt(br.readLine());
        String[] s2 = br.readLine().split(" ");
        Integer[] arrM = new Integer[M];
        for (int i = 0; i < M; i++){
            arrM[i] = Integer.parseInt(s2[i]);
        }
        
        Arrays.sort(arrN);
        for(int i = 0; i < M; i++){
            int start = 0;
            int end = N - 1;
            int mid = (start + end) / 2;
            int target = arrM[i];
            boolean flag = false;
            while (start <= end){

                if (arrN[mid] == target){
                    flag = true;
                    break;
                } else if(arrN[mid] > target){
                    end = mid - 1;
                    mid = (start + end) / 2;
                } else if(arrN[mid] < target){
                    start = mid + 1;
                    mid = (start + end) / 2;
                }
            }
            if (flag){
                bw.write(1 + "\n");
            } else{
                bw.write(0 + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
        
    }
}