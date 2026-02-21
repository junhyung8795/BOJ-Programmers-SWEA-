import java.io.*;
import java.util.*;

public class Main{
    static int N;
    static int X;
    static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        X = Integer.parseInt(br.readLine());

        int[] arr = new int[2000001];
        for(int i = 0; i < s.length; i++){
            int num = Integer.valueOf(s[i]);
            arr[num] += 1;
        }

        for(int i = 0 ; i < s.length; i++){
            int num = Integer.valueOf(s[i]);
            if (num <= X/2 && X - num > 0 && (X - num != num) && arr[X - num] == 1) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}