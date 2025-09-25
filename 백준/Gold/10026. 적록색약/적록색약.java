import java.util.*;
import java.io.*;
class Main {

	static int N;
	static char[][] table;
	static boolean[][][] visited;
	static int answer0;
	static int answer1;
	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		table = new char[N][N];

		for (int row = 0; row < N; row++) {
			char[] inputRow = br.readLine().toCharArray();
			table[row] = inputRow;
		}

		visited = new boolean[N][N][2];
		answer0 = 0;
		answer1 = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (!visited[row][col][0]) {
					bfs0(row, col);
				}
				if (!visited[row][col][1]) {
					bfs1(row, col);
				}

			}
		}
		System.out.println(answer0 + " " + answer1);
	
		
	}

	private static void bfs1(int startRow, int startCol) {
		// TODO Auto-generated method stub
		Queue<Integer> q = new LinkedList<>();
		q.add(startRow * N + startCol);
		visited[startRow][startCol][1] = true;
		while (!q.isEmpty()) {
			int popped = q.poll();
			int curRow = popped / N;
			int curCol = popped % N;
			for (int dir = 0; dir < 4; dir++) {
				int dr = curRow + dirs[dir][0];
				int dc = curCol + dirs[dir][1];
				if (dr >= 0 && dr < N && dc >= 0 && dc < N && !visited[dr][dc][1]
						&& (table[dr][dc] == table[startRow][startCol]
								|| (table[dr][dc] == 'R' && table[startRow][startCol] == 'G')
								|| (table[dr][dc] == 'G' && table[startRow][startCol] == 'R'))) {
					visited[dr][dc][1] = true;
					q.add(dr * N + dc);
				}

			}
		}
		answer1++;

	}

	private static void bfs0(int startRow, int startCol) {
		// TODO Auto-generated method stub
		Queue<Integer> q = new LinkedList<>();
		q.add(startRow * N + startCol);
		visited[startRow][startCol][0] = true;
		while (!q.isEmpty()) {
			int popped = q.poll();
			int curRow = popped / N;
			int curCol = popped % N;
			for (int dir = 0; dir < 4; dir++) {
				int dr = curRow + dirs[dir][0];
				int dc = curCol + dirs[dir][1];
				if (dr >= 0 && dr < N && dc >= 0 && dc < N && !visited[dr][dc][0]
						&& table[dr][dc] == table[startRow][startCol]) {
					visited[dr][dc][0] = true;
					q.add(dr * N + dc);
				}

			}
		}
		answer0++;

	}

}
