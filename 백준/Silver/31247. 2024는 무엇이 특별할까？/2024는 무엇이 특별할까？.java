import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        //테케수만큼 반복
        for(int t = 0; t < T; t++){
            String[] s = br.readLine().split(" ");
            long N = Long.parseLong(s[0]);
            long K = Long.parseLong(s[1]);
            if(K >= 64){
                //이미 64를 넘기면 2^64 * 홀수는 무조건 홀수가 1이라해도 10의 18승을 넘김
                System.out.println(0);
                continue;
            }

            //K=0이면 N이하의 모든 홀수를 출력
            if(K == 0){
                System.out.println((N + 1) / 2);
                continue;
            }

            //K가 64이하라면?
            //로직 수행
            //N이하에 2^K * 홀수형태인 수들의 개수를 맞춰보자.
            //단 이걸 매번 1로 시작해서 +2인수가 N보다 작은지를 찾는건 너무 번거로움
            // a * b + c = d라는식이 있을 때 a가 2의 제곱수라면 b는 몫, c는 나머지
            //N을 2^K로 나눠보면 b가 나온다.
            Double doubleValue = Math.pow(2, K);
            long longValue = doubleValue.longValue();
            long a = N / longValue + 1;
            System.out.println(a / 2);
            




            
        }
    }
}
