import java.io.*;
import java.util.*;

class Main{
    static int n;
    static int k;
    static ArrayList<Integer> arrA = new ArrayList<>();
    static ArrayList<Integer> arrB = new ArrayList<>();
    static int maxK = 0;
    static int minK = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //n,k입력받기
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        k = Integer.parseInt(s[1]);
        
        //Ai, Bi입력받기
        for(int i = 0 ; i < n; i++){
            String[] s1 = br.readLine().split(" ");
            int A = Integer.valueOf(s1[0]);
            int B = Integer.valueOf(s1[1]);
            arrA.add(A);
            arrB.add(B);
            maxK = Math.max(B - A, maxK);
            
        }
        
        
        
        if(maxK <= 0){
            System.out.println(0);
            return;
        }

        int answer = 0;
        //이분탐색으로 최적의 K찾기
        while(minK <= maxK){
            int mid = (maxK + minK) / 2;

            if(search(mid) >= k){
                answer = mid;
                // System.out.println(answer);
                maxK = mid -1;
            }
            else{
                minK = mid + 1;
            }
        }


        System.out.println(answer);


        
        
    }


    public static int search(int X){
        int total = 0;
        for(int i = 0 ; i < n; i++){
            if(arrA.get(i) + X >= arrB.get(i)){
                total += 1;
            }
        }





        return total;


        
    }
}