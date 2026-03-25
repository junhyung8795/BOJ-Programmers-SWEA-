import java.io.*;
import java.util.*;

class Main{
    static int n;
    static int m;
    static int[][] dist;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        
        //Dist를 초기회
        dist = new int[n + 1][n + 1];
        for(int i = 0; i < n + 1; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);

        }
        for(int i = 0 ; i < n + 1; i++){
            //i에서 i로 가는건 0거리가 걸린다.
            dist[i][i] = 0;
        }
        
        //간선정보입력. 단방향임
        for(int i = 0 ; i < m; i++){
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);
            //같은 시작도시와 출발도시여도 다른 비용을 내는 간선이 있어서 최솟값만 넣음.
            dist[u][v] = Math.min(dist[u][v], w);
        }
        
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < n + 1; j++){
                if(dist[i][j] == Integer.MAX_VALUE){
                    System.out.print(0 + " ");
                } else{
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
        
        
        
    }
    
    
    
    
    
    
}