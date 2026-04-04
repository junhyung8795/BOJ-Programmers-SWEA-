import java.io.*;
import java.util.*;

class Main{
    static int R;
    static int C;
    static int T;
    static int[][] graph;
    static int time = 0;
    static int answer = 0;
    static int up = -1;
    static int down = -1;
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        R = Integer.parseInt(s[0]);
        C = Integer.parseInt(s[1]);
        T = Integer.parseInt(s[2]);
        graph = new int[R][C];



        for(int row = 0; row < R; row++){
            String[] inp = br.readLine().split(" ");
            for(int col = 0; col < C; col++){
                graph[row][col] = Integer.parseInt(inp[col]);
                if(graph[row][col] == -1 && up == -1){
                    up = row;
                } else if(graph[row][col] == -1){
                    down = row;
                }
            }
        }
        

        simulation();

        for(int row = 0; row< R; row++){
            for(int col = 0; col < C ;col++){
                answer+= graph[row][col];
            }
        }

        System.out.println(answer + 2);



        
    }
    public static void simulation(){
        while(time < T){
            time++;
            //미세먼지확산
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int row = 0; row < R; row++){
                for(int col = 0; col < C; col++){
                    if(graph[row][col] != 0 &&  graph[row][col] != -1){
                        //미세먼지잇음
                        map.put(row * 51 + col, map.getOrDefault(row * 51 + col, graph[row][col]));
                        graph[row][col] = 0;
                    }
                }
            }

            //맵 순회하면서 확산
            for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                int row = entry.getKey() / 51;
                int col = entry.getKey() % 51;
                int munji = entry.getValue();

                int cntOfDirs = 0;
                int amountPerDir = munji / 5;
                for(int[] dir: dirs){
                    int dr = row + dir[0];
                    int dc = col + dir[1];

                    if(dr < 0 || dr >= R || dc < 0 || dc >= C || graph[dr][dc] == -1) continue;

                    graph[dr][dc] += amountPerDir;//각 방향으로 확산
                    cntOfDirs++;
                }
                //본인자리는 munji - cntOfDirs * amountPerDir;
                graph[row][col] +=  (munji - cntOfDirs * amountPerDir);
            }





            //바람이동
            //up부분 부터
            for(int row = up; row >= 1; row--){
                if(row == up){
                    continue;
                }
                graph[row][0] = graph[row - 1][0];
            }
            for(int col = 0 ; col < C - 1; col++){
                graph[0][col] = graph[0][col + 1];
            }
            for(int row = 0; row <= up - 1; row++){
                graph[row][C - 1] = graph[row + 1][C - 1];
            }
            for(int col = C - 1; col >=1; col--){
                graph[up][col] = graph[up][col - 1];
                if(graph[up][col - 1] == -1){
                    graph[up][col] = 0;
                }
            }
            

            //down
            for(int row = down; row < R - 1; row++){
                if(row == down){
                    continue;
                }
                graph[row][0] = graph[row + 1][0];
            }
            for(int col = 0; col < C - 1; col++){
                graph[R - 1][col] =graph[R - 1][col + 1];
            }
            for(int row = R - 1; row >= down + 1; row--){
                graph[row][C - 1] = graph[row - 1][C - 1];
            }
            for(int col = C - 1; col >=1; col--){
                graph[down][col] = graph[down][col - 1];
                if(graph[down][col - 1] == -1){
                    graph[down][col] = 0;
                }
            }

            
            
            
        }










        
    }
}