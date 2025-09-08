import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int[][] dirs = { {}, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static int N;
    static int M;
    static int R;
    static int C;
    static int L;
    static int[][] table;
    static boolean[][] visited;
    static int[][] possibleGoDir = { {}, { -1, 1, 1, 1, 1 }, { -1, 1, 1, 0, 0 }, { -1, 0, 0, 1, 1 }, { -1, 1, 0, 0, 1 },
            { -1, 0, 1, 0, 1 }, { -1, 0, 1, 1, 0 }, { -1, 1, 0, 1, 0 } };
    static int[][] possibleComeInDir = { {}, { -1, 1, 1, 1, 1 }, { -1, 1, 1, 0, 0 }, { -1, 0, 0, 1, 1 },
            { -1, 0, 1, 1, 0 }, { -1, 1, 0, 1, 0 }, { -1, 1, 0, 0, 1 }, { -1, 0, 1, 0, 1 } };
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            String[] inputNMRCL = br.readLine().split(" ");
 
            N = Integer.parseInt(inputNMRCL[0]);
            M = Integer.parseInt(inputNMRCL[1]);
            R = Integer.parseInt(inputNMRCL[2]);
            C = Integer.parseInt(inputNMRCL[3]);
            L = Integer.parseInt(inputNMRCL[4]);
 
            // 테이블 크기 초기화, 값 입력 받기
            table = new int[N][M];
            for (int row = 0; row < N; row++) {
                String[] inputTableRow = br.readLine().split(" ");
                for (int col = 0; col < M; col++) {
                    table[row][col] = Integer.parseInt(inputTableRow[col]);
                }
            }
            answer = 0;
            bfs();
            System.out.println("#" + t + " " +answer);
        }
 
    }
 
    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][M];
 
        // R,C에서 부터 시간이 1 지난상태에서 출발가능하다.
        q.add(new int[] { R, C, 1 });
         
        visited[R][C] = true;
        answer++;//1시간 지나면 일단 한칸은 도둑이 갈 수가 있다.
         
        while(!q.isEmpty()) {
             
            int[] popped = q.poll();
            int curRow = popped[0];
            int curCol = popped[1];
            int curTime = popped[2];
             
             
            for(int dir = 1; dir <= 4; dir++) {
                int dr = curRow + dirs[dir][0];
                int dc = curCol + dirs[dir][1];
                if(dr >= 0 && dr < N && dc >= 0 && dc < M) {//범위 유효성 검사
                    if(possibleGoDir[table[curRow][curCol]][dir] == 1 && !visited[dr][dc]) {//해당 터널에서 갈 수 있는 방향인지와 이미 방문했는지를 검사
                        if(table[dr][dc] != 0 && possibleComeInDir[table[dr][dc]][dir] == 1 && curTime + 1 <= L){// drdc에 적힌게 일단 터널이여야하며, 
                            //동시에 drdc의 터널이 해당 방향으로 들어오는걸 받을 수 잇어야한다. 또한 L을 시간초과하지 않아야한다.
                            q.add(new int[] {dr, dc, curTime + 1});
                            visited[dr][dc] = true;
                            answer++;// 위모든 조건을 통과하면 갈 수 있는 영역이 하나 늘어난 것이다.
 
                        }
                         
                    }
                }
                 
            }
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
        }
         
    }
 
}