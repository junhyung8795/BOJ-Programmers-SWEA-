

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
	static int D;
	static int W;
	static int K;
	static int[][] table;
	static int answer;
	static HashSet<String> set;
	static int[] curArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			String[] inputNMK = br.readLine().split(" ");

			D = Integer.parseInt(inputNMK[0]);
			W = Integer.parseInt(inputNMK[1]);
			K = Integer.parseInt(inputNMK[2]);

			table = new int[D][W];
			for (int row = 0; row < D; row++) {
				String[] inputRow = br.readLine().split(" ");
//				System.out.println(Arrays.toString(inputRow));
				for (int col = 0; col < W; col++) {
					table[row][col] = Integer.parseInt(inputRow[col]);
				}
			}

			answer = D;
			set = new HashSet<>();
			curArr = new int[D];
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < D; i++) {
				curArr[i] = 3;
				sb.append(curArr[i]);
			}

			set.add(sb.toString());
			backtracking(0, 0);
//			
			System.out.println("#" + t + " " +answer);
		}
	}

	private static void backtracking(int cnt, int startIndex) {
		// TODO Auto-generated method stub
//		System.out.println(set);
//		System.out.println("asdas");

		if (answer <= cnt) {
			return;
		}
		
		boolean isOk = true;

		for (int col = 0; col < W; col++) {

			boolean isColOk = false;
			label: for (int row = 0; row <= D - K; row++) {
				int standard = table[row][col];
				boolean isStandardOk = true;
				for (int i = row + 1; i <= K - 1 + row; i++) {
					if (standard != table[i][col]) {
						isStandardOk = false;
						continue label;
					}
				}
				isColOk = true;
				break;
			}

			if (!isColOk) {
				isOk = false;
				break;
			}

		}

		if (!isOk) {
			if (startIndex == D)
				return;
			int[] prev = table[startIndex];
			int[] medi0 = new int[W];
			int[] medi1 = new int[W];

			for (int i = 0; i < W; i++) {
				medi0[i] = 0;
				medi1[i] = 1;
			}
			backtracking(cnt, startIndex + 1);

			table[startIndex] = medi0;
			backtracking(cnt + 1, startIndex + 1);

			table[startIndex] = medi1;
			backtracking(cnt + 1, startIndex + 1);
			table[startIndex] = prev;

		} else {
			answer = Math.min(answer, cnt);
			return;
		}

	}
}
