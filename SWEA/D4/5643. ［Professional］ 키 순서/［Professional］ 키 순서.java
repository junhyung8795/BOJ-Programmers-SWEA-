import java.io.*;
import java.util.*;
 
public class Solution {
    static int N;
    static int M;
    static int[][] table;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
             
             
            table = new int[N][N];
            int[] people = new int[N];
            for(int i = 0; i < M; i++) {
                String[] inputSE = br.readLine().split(" ");
                int start = Integer.parseInt(inputSE[0]);
                int end = Integer.parseInt(inputSE[1]);
                table[start - 1][end - 1] = 1;
                table[end - 1][start - 1] = -1;
                people[start - 1] += 1;
                people[end - 1] += 1;
                //start인 사람의 키가 end인 사람의 키보다 작다.
            }
             
            answer = 0;
             
            //자신의 키 순위를 알려면? ->자기보다 키가 큰 사람, 자기보다 키가 작은 사람의 키가 자기 제외한 N-1명만큼 있어야 자기 순위를 알 수 있다.
            //플로이드 워셜로 자신이 시작점이 됐을 때 도달할 수 있는 모든 정점들(자기보다 키큰 사람들)을 알아내고 + 자기가 도착점이 됐을 때 시작점이 되는 모든 정점들(자기보다 키가 작은 사람들)을 알 수 있다.
            for(int k = 0; k < N; k++) {//경유점
                for(int i = 0; i < N; i++) {//시작점
                    for(int j = 0; j < N; j++) {//종료점
                        if(table[i][k] == 1 && table[k][j] == 1 && table[i][j] == 0){//i가 k보다 작은데 k 도 j보다 작으면 i는 j보다도 작은것.
                            //i도 자기보다 큰 사람 j를 발견했지만 j도 자기보다 작은 사람 i를 발견한다.
                            table[i][j] = 1;
                            table[j][i] = -1;
                            people[i]+= 1;
                            people[j] += 1;
                        }
                        if(table[i][k] == -1 && table[k][j] == -1 && table[i][j] == 0) {
                            table[i][j] = -1;
                            table[j][i] = 1;
                            people[i]+= 1;
                            people[j] += 1;
                        }
                    }
                }
            }
//          System.out.println(Arrays.toString(people));
            for(int i = 0; i < N; i++) {
                if(people[i] == N - 1 ) {
                    answer += 1;
                }
            }
             
            System.out.println("#" +t + " " +answer);
             
             
             
             
             
             
             
             
        }   
 
    }
 
}