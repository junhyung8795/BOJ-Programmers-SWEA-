

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static char[][] table;
	static int destRow;
	static int destCol;
	static int redStartRow;
	static int redStartCol;
	static int blueStartRow;
	static int blueStartCol;
	static int answer;
	static int callNum = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputNM = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputNM[0]);
		M = Integer.parseInt(inputNM[1]);

		table = new char[N][M];
		for (int row = 0; row < N; row++) {
			char[] inputRow = br.readLine().toCharArray();
			for (int col = 0; col < M; col++) {
				table[row][col] = inputRow[col];

				if (table[row][col] == 'O') {
					destRow = row;
					destCol = col;
				} else if (table[row][col] == 'R') {
					redStartRow = row;
					redStartCol = col;
				} else if (table[row][col] == 'B') {
					blueStartCol = col;
					blueStartRow = row;
				}
			}
		}

		answer = Integer.MAX_VALUE;
//		bfs();
		backtracking(redStartRow, redStartCol, blueStartRow, blueStartCol, 0);
//		System.out.println("callNum = " + callNum);

		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);

		}else {
			System.out.println(answer);
		}
		
	}



	private static void backtracking(int redRow, int redCol, int blueRow, int blueCol, int cnt) {
		if (cnt >= 10) {
			return;
		}
		if (cnt >= answer) {
			return;
		}

		for (int dir = 0; dir < 4; dir++) {
			int redDr = redRow + dirs[dir][0];
			int redDc = redCol + dirs[dir][1];
			int blueDr = blueRow + dirs[dir][0];
			int blueDc = blueCol + dirs[dir][1];
			boolean arriveR = false;
			boolean arriveB = false;

			while (redDr >= 0 && redDr < N && redDc >= 0 && redDc < M && table[redDr][redDc] != '#') {
				
				if (redDr == destRow && redDc == destCol) {
					// 도착가능함.
					arriveR = true;
				}
				redDr += dirs[dir][0];
				redDc += dirs[dir][1];
			}
			redDr -= dirs[dir][0];
			redDc -= dirs[dir][1];
			
			
			while (blueDr >= 0 && blueDr < N && blueDc >= 0 && blueDc < M && table[blueDr][blueDc] != '#') {
				
				if (blueDr == destRow && blueDc == destCol) {
					// 도착가능함.
					arriveB = true;
				}
				blueDr += dirs[dir][0];
				blueDc += dirs[dir][1];
			}
			blueDr -= dirs[dir][0];
			blueDc -= dirs[dir][1];
			
			
			
			int arriveOffsetBetweenR = Math.abs(redRow - redDr) + Math.abs(redCol - redDc);
			int arriveOffsetBetweenB = Math.abs(blueRow - blueDr) + Math.abs(blueCol - blueDc);
			if (arriveR && arriveB) {

				continue;
			} else if (arriveR) {
				answer = Math.min(answer, cnt + 1);
				continue;
			} else if (arriveB) {
				continue;
			} else if (redDr == blueDr && redDc == blueDc) {
				if (arriveOffsetBetweenR < arriveOffsetBetweenB) {
					blueDr -= dirs[dir][0];
					blueDc -= dirs[dir][1];
				} else if (arriveOffsetBetweenR > arriveOffsetBetweenB) {
					redDr -= dirs[dir][0];
					redDc -= dirs[dir][1];
				}
			}
			callNum++;
//			System.out.println("redDr  = " + redDr + " redDc = " + redDc + " blueDr = " + blueDr +  " blueDc = " + blueDc);
			backtracking(redDr, redDc, blueDr, blueDc, cnt + 1);

		}

	}
}
