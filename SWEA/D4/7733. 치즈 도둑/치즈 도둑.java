import java.io.*;
import java.util.*;
 
public class Solution {
    static int[][] table;
    static int maxTaste;
    static int answer;
    static int N;
    static int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            // N입력과 테이블 크기 초기화
            N = Integer.parseInt(br.readLine());
            table = new int[N][N];
 
            // 테이블 입력 받으면서 최대 맛이 몇인지 측정
            maxTaste = 0;
            for (int row = 0; row < N; row++) {
                String[] inputTableRow = br.readLine().split(" ");
                for (int col = 0; col < N; col++) {
                    table[row][col] = Integer.parseInt(inputTableRow[col]);
                    maxTaste = Math.max(maxTaste, table[row][col]);
                }
            }
 
            // 각 taste마다 방문 배열 초기화 answer초가화
            answer = 1;//전체가 x인 맛으로 통일된 경우. x번재 날에 x인맛을 다 먹으니 bfs를 x를 초과한 경우에만 하니 0덩이가된다.
            //하지만 상식적으로 맨처음 상태에는 최소 1덩이가 있다. answer는 0이 아닌 1로 초기화해야한다.
            for (int taste = 1; taste <= maxTaste; taste++) {
                // 맛을 1부터 시작해서 한단계씩 올리면서
                // 테이블의 각 점이 taste보다 크고 !visited면[row][col]이면 bfs를 호출,
 
                boolean[][] visited = new boolean[N][N];
                int result = 0;
                for (int row = 0; row < N; row++) {
                    for (int col = 0; col < N; col++) {
                        if (!visited[row][col] && table[row][col] > taste) {
                            bfs(row, col, visited, taste);
                            result++;// bfs를 하면 해당 점에서 부터 방문처리를 하니 만약 다음 row, col을 돌았는데 방문처리가 안돼있고 taste보다 값이 크면?
                            // 해당 지점은 또 다른 덩어리가 된다는 뜻이다. 그러므로 result++;
                        }
                    }
                }
                 
                answer = Math.max(answer, result);
 
            }
             
            System.out.println("#" + t + " "+ answer);
 
        }
 
    }
 
    private static void bfs(int row, int col, boolean[][] visited, int taste) {
        Queue<Integer> q = new LinkedList<>();
 
        q.add(row * N + col);
        visited[row][col] = true;
        while (!q.isEmpty()) {
            int popped = q.poll();
            int curRow = popped / N;
            int curCol = popped % N;
 
            for (int i = 0; i < 4; i++) {
                int dr = curRow + dirs[i][0];
                int dc = curCol + dirs[i][1];
                if (dr >= 0 && dr < N && dc >= 0 && dc < N && !visited[dr][dc] && table[dr][dc] > taste) {
                    // 갈수 잇는 범위라면? 그리고 방문한 적이 없는 곳이고 taste보다 큰 값을 가지는 칸이라면?
                    visited[dr][dc] = true;
                    q.add(dr * N + dc);
 
                }
            }
        }
 
    }
}