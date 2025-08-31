

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
	static int N;
	static int[][] table;
	static boolean[][] visited;
	static int startRow;
	static int startCol;
	static int endRow;
	static int endCol;
	static int time;
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			// 테이블 초기화
			table = new int[N][N];
			for (int row = 0; row < N; row++) {
				String[] inputRow = br.readLine().split(" ");
				for (int col = 0; col < N; col++) {
					table[row][col] = Integer.parseInt(inputRow[col]);
				}
			}

			// 도착 시작 초기화
			String[] inputStart = br.readLine().split(" ");
			startRow = Integer.parseInt(inputStart[0]);
			startCol = Integer.parseInt(inputStart[1]);

			String[] inputEnd = br.readLine().split(" ");
			endRow = Integer.parseInt(inputEnd[0]);
			endCol = Integer.parseInt(inputEnd[1]);

			// 시뮬레이션 로직 시
			time = 0;
			visited = new boolean[N][N];
			simulation();
			System.out.println("#" +t +" " +time);
		}
	}

	private static void simulation() {
		// TODO Auto-generated method stub

		Queue<Integer> q = new LinkedList<>();
		q.add(startRow * N + startCol);
		visited[startRow][startCol] = true;
		Queue<Integer> q2 = new LinkedList<>();

		int Qsize = q.size();

		while (!q.isEmpty() || !q2.isEmpty()) {
//			System.out.println("q = " + q + " q2 = " + q2 + " time = " + time);
			for (int j = 0; j < Qsize; j++) {
				int popped = q.poll();
				int curRow = popped / N;
				int curCol = popped % N;

				if (curRow == endRow && curCol == endCol) {
					return;
				}
				for (int i = 0; i < 4; i++) {
					int dr = curRow + dirs[i][0];
					int dc = curCol + dirs[i][1];
//					System.out.println("dr = " + dr + "dc = " + dc);
//					System.out.println();
					if (dr >= 0 && dr < N && dc >= 0 && dc < N && table[dr][dc] != 1 && !visited[dr][dc]) {
						if (table[dr][dc] == 0) {
							q.offer(dr * N + dc);
							visited[dr][dc] = true;
						} else if (table[dr][dc] == 2) {
							if (time % 3 == 1 || time % 3 == 0) {
								q2.offer(dr * N + dc);
								visited[dr][dc] = true;
							} else {// time % 3 == 2인경우 그냥 탐색 가능
								q.offer(dr * N + dc);
								visited[dr][dc] = true;
							}
						}
					}
				}
				
				
//				System.out.println("after");
//
//				System.out.println("q = " + q + " q2 = " + q2 + " time = " + time);
//				System.out.println();
				
				
			}
			if (time % 3 == 2) {
				while (!q2.isEmpty()) {
					q.offer(q2.poll());
				}
			}
			Qsize = q.size();

			time++;
		}

		// 다 탐색했는데도 리턴안되면 그냥 못감
		time = -1;
	}

}
