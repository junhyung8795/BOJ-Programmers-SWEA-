import java.io.*;
import java.util.*;
class Main{
    static int[] arr;
    static int M;
    static int answer;
    static int N;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        String[] s1 = br.readLine().split(" ");
        arr = new int[N];
        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(s1[i]);
        }


        backtracking(new ArrayList<>(), 0);
        System.out.println(answer);
    }

    static void backtracking(ArrayList<Integer> list, int index){
        if(list.size() == 3){
            int candi = 0;
            for(int i = 0; i < list.size(); i++){
                candi += arr[list.get(i)];
            }
            if(candi <= M && answer < candi){
                answer = candi;
            }
            return;
        }

        if(index < N){
             list.add(index);
            backtracking(list, index + 1);
            list.remove(list.size() - 1);
            backtracking(list, index + 1);
        }
           
    }
}