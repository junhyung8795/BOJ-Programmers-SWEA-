import java.io.*;
import java.util.*;
 
class Solution {
    static int[][] table;
    static int N;
    static int W;
    static int H;
    static int answer;
    static int rockRemain;
    static int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            // NWH입력받기
            String[] inputNWH = br.readLine().split(" ");
            N = Integer.parseInt(inputNWH[0]);
            W = Integer.parseInt(inputNWH[1]);
            H = Integer.parseInt(inputNWH[2]);
            answer = Integer.MAX_VALUE;
            rockRemain = 0;
             
            // H줄동안 table의 입력 받고 초기화
            table = new int[H][W];
            for (int row = 0; row < H; row++) {
                String[] inputRow = br.readLine().split(" ");
 
                for (int col = 0; col < W; col++) {
                    table[row][col] = Integer.parseInt(inputRow[col]);
                    if(table[row][col] != 0) {
                        rockRemain++;
                    }
                }
            }
 
            // 열마다 구슬을 떨어뜨리는 모든 경우를 고려해본다.
            // 왜냐하면 구슬로 한번 떨어뜨렸을 때 제일 많이 부쉈다고 생각되는 위치일지라
            // 테이블이 구슬로 인해서 변형되기 때문에 두번째, 세번째, 네번째에서
            // 부술 수 있는 벽돌의 양이 시시각각 달라지기 때문이다.
            // N이 3이면 for문을 3중, N이 4면 for문을 4중으로 돌아야한다.
            backtracking(N, table, 0);
            System.out.println("#"+ t +" " +answer);
        }
 
    }
 
    private static void backtracking(int loopCnt, int[][] table, int brokenRockCnt) {
        // TODO Auto-generated method stub
        if (loopCnt == 0 ||brokenRockCnt == rockRemain) {
            // N이 0이면 해당 횟수만큼 for문을 돌렸다는 뜻이다.
            //혹은 N번의 구슬을 떨어뜨리기도 전에 모든 벽돌을 다 부순다면 게임이 끝난다.
            // 최대한 많이 부쉈을 때  이를 전체 벽돌 수 rockRemain에서 뺀 것이 최소한으로 남은 벽돌 수이다.
            answer = Math.min(answer, rockRemain - brokenRockCnt);
            return;
        }
 
        // 이제 각 열을 순회하면서 해당 열에 구슬을 떨어뜨려
        // dfs로 벽돌을 부수고
        // 벽돌이 부서진 테이블에서 공중에 떠 있는 벽돌들을아래로 내리고
        // 다시 다음 backtracking으로 넘어가야한다.
        for (int col = 0; col < W; col++) {
            int[][] copyOfTable = new int[H][W];
            for(int row = 0; row < H; row++) {
                copyOfTable[row] = table[row].clone();
            }
            // 백트래킹을 하기 위해 매 열마다 테이블의 복사본을 만들어
            // dfs로 벽돌을 부순다.
            // dfs로 원본 테이블을 보내면 다음 열 순회때 이미 부숴진 테이블이 보내져서
            // 백트래킹에서 독립적인로직수행을 할 수 없다.
            int startRow = -1;
            for (int row = 0; row < H; row++) {
                if (table[row][col] != 0) {
                    startRow = row;
                    break;
                }
            }
            if(startRow == -1) {
                continue;
            }
            // dfs의 결과물로 부순 벽돌 수를 반환하
            int result = dfs(startRow, col, copyOfTable);
            // 이 result를 brokenRockCnt에 넣는다.
            brokenRockCnt += result;
 
            // 그리고 이를 반영한 백트래킹을 더 진행
            backtracking(loopCnt - 1, copyOfTable, brokenRockCnt);
            // 다음 열에서 로직을 수행할 때 brokenRockCnt이 증가돼있는 상태로 안들어가게
            // 다시 원상복구
            brokenRockCnt -= result;
        }
    }
 
    private static int dfs(int startRow, int col, int[][] copyOfTable) {
        // TODO Auto-generated method stub
        //startRow와 col을 시작으로 dfs시작.
        boolean[][] visited = new boolean[H][W];
        int result = 0;
        int[] node = { startRow, col };
        Stack<int[]> stack = new Stack<>();
        stack.push(node);
 
        while (!stack.isEmpty()) {
            int[] popped = stack.pop();
            int curRow = popped[0];
            int curCol = popped[1];
            if (!visited[curRow][curCol]) {
                visited[curRow][curCol] = true;
                //방문하지 않았다면 방문 처리를 하고 
                //해당 칸을 부쉈으니 result++ 
                result++;
                //해당 칸에 적힌 수 M을 기준으로 M-1 칸 만큼 추가로 십자모양으로 터뜨리니 칸에 적힌 수를 저장할 M
                int M = copyOfTable[curRow][curCol];
                //그리고 해당 칸은 터졌으니 0으로 만든다.
                copyOfTable[curRow][curCol] = 0;
                for (int[] dir : dirs) {
                    for (int i = 1; i <= M - 1; i++) {
                        //dir의 방향으로1칸 부터 M-1칸까지 퍼져나가 터뜨린다.
                        int dr = curRow + dir[0] * i;
                        int dc = curCol + dir[1] * i;
 
                        //유효성 검사 + 칸에 적힌 수가 0이 아닐때만 스택에푸쉬한다.
                        if (dr >= 0 && dr < H && dc >= 0 && dc < W && copyOfTable[dr][dc] != 0) {
                            if(!visited[dr][dc]) {
                                int[] pushed = {dr, dc};
                                stack.push(pushed);
                            }
                        }
                    }
                }
 
            }
        }
         
 
        //공중에 뜬 벽돌들을 다시 바닥으로 내려준다.
        //내려주는 메서드 rockGoDown
        rockGoDown(copyOfTable);
         
 
        return result;
 
    }
 
    private static void rockGoDown(int[][] copyOfTable) {
        // TODO Auto-generated method stub
        //각 열을 돌면서 벽돌들을 만나면 needToGoDownIndex로 내려준다.
        for(int col = 0; col < W; col++) {
            int needToGoDownIndex =  copyOfTable.length - 1;
            for(int row = copyOfTable.length - 1; row >=0 ; row--) {
                if(copyOfTable[row][col] == 0) {
                    //0인 칸 위에 0을 또 만난다고 해서 벽돌이 떨어질 위치가 변하진 않는다.
                    //벽돌을 만난 경우에만 변하도록 needToGoDownIndex = Math.max(needToGoDownIndex, row);
                    needToGoDownIndex = Math.max(needToGoDownIndex, row);
                } else {
                     
                    // needToGoDownIndex에 벽돌을 내려주고 
                    copyOfTable[needToGoDownIndex][col] = copyOfTable[row][col];
                    //원래있던 칸은 벽돌이 사라졌으니 0을 넣어준다.
                    //단 row와 needToGoDownIndex칸이 같은 경우도 있을 수 있다. -> 내려와져야하는 공간에 이미 벽돌이 있는 경우 
                    //맨아래에 벽돌이 있고  needToGoDownIndex이 0이면 그냥 needToGoDownIndex--만 해야지
                    //이미 있는 벽돌을 지워버리면 안된다.
                    if(row != needToGoDownIndex) {
                        copyOfTable[row][col] = 0;
                    }
                    //그리고 needToGoDownIndex은 한칸 위로 올라간다.
                    needToGoDownIndex--;
                }
            }
        }
    }
}