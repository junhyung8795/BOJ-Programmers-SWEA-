import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

	static char[][] table;
	static int N;
	static int M;
	static int startRow;
	static int startCol;
	static int endRow;
	static int endCol;
	static int answer;

	static class Node {
		int curRow;
		int curCol;
		int curDist;
		int breakFlag;

		public Node(int curRow, int curCol, int curDist, int breakFlag) {
			super();
			this.curRow = curRow;
			this.curCol = curCol;
			this.curDist = curDist;
			this.breakFlag = breakFlag;
		}

		@Override
		public String toString() {
			return "Node [curRow=" + curRow + ", curCol=" + curCol + ", curDist=" + curDist + ", breakFlag=" + breakFlag
					+ "]";
		}

	}

	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] inputNM = br.readLine().split(" ");
		N = Integer.parseInt(inputNM[0]);
		M = Integer.parseInt(inputNM[1]);
		table = new char[N][M];
		visited = new boolean[N][M][2];
		for (int i = 0; i < N; i++) {
			char[] inputRow = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				table[i][j] = inputRow[j];
			}
		}

		startRow = 0;
		startCol = 0;
		endRow = N - 1;
		endCol = M - 1;
		answer = Integer.MAX_VALUE;
//		visited[startRow][startCol][0] = true;
//		backtracking(1, startRow, startCol ,false);
//		
		bfs();
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}

	}

	private static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(startRow, startCol, 1, 0));
		visited[startRow][startCol][0] = true;

		while (!q.isEmpty()) {
			Node popped = q.poll();
			int curRow = popped.curRow;
			int curCol = popped.curCol;
			int curDist = popped.curDist;
			int breakFlag = popped.breakFlag;

			if (curRow == endRow && curCol == endCol) {
				answer = curDist;
				return;
			}

			for (int dir = 0; dir < 4; dir++) {
				int dr = curRow + dirs[dir][0];
				int dc = curCol + dirs[dir][1];

				if (dr >= 0 && dr < N && dc >= 0 && dc < M && !visited[dr][dc][breakFlag]) {
					if (table[dr][dc] == '0') {
						visited[dr][dc][breakFlag] = true;
						q.add(new Node(dr, dc, curDist + 1, breakFlag));
					} else {
						// 벽이라면 breakFlag가 false면 뚫고 지나갈 수도 있다.1
						if (breakFlag == 0) {
							visited[dr][dc][1] = true;
							q.add(new Node(dr, dc, curDist + 1, breakFlag + 1));
						}
					}

				}

			}

		}

	}
}

// 백트래킹은 각 점을 기점으로 4방향으로 뻗어나가는데, 각 방향에서 최대 두가지가 또 생기므로 시간초과 발생함.
// NM이 1000, 1000이 최대이므로 각 정점을 모두 찾는 10의 6승 이외에도 벽이냐 아니냐에 따라서도 가지가 더 생김.
// 각 가지에서 최대 2^4^1000^1000 -> 1억 6천만번이라 시간초과가능