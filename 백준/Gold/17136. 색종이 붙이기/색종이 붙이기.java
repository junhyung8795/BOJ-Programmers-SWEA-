

import java.io.*;
import java.util.*;

class Main {
	static int answer = 26;// 각 장수 다 더한것 5x5 + 1
	static int[][] table;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		table = new int[10][10];

		for (int row = 0; row < 10; row++) {
			// 테이블 초기화
			String[] inputTableRow = br.readLine().split(" ");
			for (int col = 0; col < 10; col++) {
				table[row][col] = Integer.parseInt(inputTableRow[col]);
			}
		}

		// 각 테이블에 담긴 색종이의 수 배열
		int[] cntOfPaper = new int[] { 0, 5, 5, 5, 5, 5 };

//		//각 점을 기준으로 얼마나 큰 정사각형을 그릴 수 있는가 파악하기 위해
//		//dp를 이용해 테이블 다시 초기화
//		for(int row = 1; row <10; row++) {
//			for(int col = 1; col < 10; col++) {
//				if(table[row][col] != 0) {
//					//0이 아닌 칸들 기준으로 왼쪽 위 왼위의 칸들에 적힌 숫자들 중 최솟값 +1이 
//					//해당 칸에서 놓을 수 있는 최대 색종이의 한변 길이
//					table[row][col] = Math.min(table[row - 1][col - 1], Math.min(table[row - 1][col], table[row][col - 1])) + 1;
//				}
//			}
//		}
//		
//		
//		
//		//각 칸을 기준으로 백트래킹 -> 붙이냐 안붙이냐가 아니라
//		//0이 아닌 숫자가 붙은 칸들은 모두 색종이로 덮여야한다.
//		//붙이는 가짓수중 1 2 3 4 5가 모두 가능한데, 정사각형 오른 아래에 적힌 숫자 기준으로 붙이니
//		//오른쪽 가장 아래 부터 순회하면서 최대한 많이 붙이도록 해당 칸에 숫자보다 작거나 같은 변을 가진 색종이
//		//를예를들어 5가 적혀있으면 5 4 3 2 1순으로 붙인데. 붙이면? 해당 칸들을 전부 0으로만든다.
//		
		// dp써도 백트래킹 과정에서 0으로 지웠다가 다시 1로 초기화해야해서 의미가 없어짐
		// 그냥 각 칸을 기준으로 5 4 3 2 1로 줄여가며 하는게 효율적이다.
		int startRow = -1;
		int startCol = -1;
		label: for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				if (table[row][col] != 0) {
					startRow = row;
					startCol = col;
					break label;
				}
			}
		}
		if (startRow == -1 || startCol == -1) {
			// 시작점을 찾지도 못하면 그 케이스는 그냥 -1을 반환
			System.out.println(0);
			return;
		}
		backtracking(startRow, startCol, cntOfPaper, 0);

		if (answer == 26) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}

	}

	private static void backtracking(int startRow, int startCol, int[] cntOfPaper, int cnt) {
		// TODO Auto-generated method stub
		// 기저조건
		// startRow, startCol 범위를 벗어나거나 혹은 cnt가 기존 answer보다 크거나 같으면 리턴
		if (cnt >= answer) {
			return;
		}
		if (startRow == 10) {
			answer = Math.min(answer, cnt);
			return;
		}

		// 먼저 해당 칸을 기준으로 table[row][col]에 적힌 숫자부터 1까지
		// 각 변만큼 정사각형을 붙일 수 있는지를 봐야한다.
		// 근데 만약 이 for문을 통과했는데 한번도 백트래킹이 다 컨티뉴 된다면?
		if (table[startRow][startCol] == 1) {
			for (int i = 5; i >= 1; i--) {
				// i는 색종이의 한 변길이
				// i의 길이 기준으로색종이 덮기가가능한지 검사
				boolean isOk = isAttachable(startRow, startCol, i);
				if (isOk && cntOfPaper[i] > 0) {// 덮을 수도 있어야하지만? i길이의 변을 가진 색종이도 있어야함.
					// 해당 색종이를 붙이는로직 -> 0을 저정하면 붙이기 1을 저장하면 붙이기
					attach(startRow, startCol, i, 0);
					// 백트래킹 이어주는 로직
					// 1. 시작점 찾기
					int newRow = startRow;
					int newCol = startCol;
					cntOfPaper[i] -= 1;
					if(startCol + 1 <= 9) {
						backtracking(newRow, newCol + 1, cntOfPaper, cnt + 1);
					} else {
						backtracking(newRow +1, 0, cntOfPaper, cnt + 1);

					}
					// 붙인 색종이를 다시 때는 로직
					cntOfPaper[i] += 1;
					attach(startRow, startCol, i, 1);
					
					
					
				}

			}
		}else {
            //만약 해당 칸이 1이 아니라 0이면 다음 1이 나오는 칸을 찾으로 가야함.
            //else를 꼭 위에 붙여야 그냥 로직이 실행되는일이 없음. (실수한 부분)
			if(startCol + 1 <= 9) {
				backtracking(startRow, startCol +1, cntOfPaper, cnt);
			} else {
				backtracking(startRow +1, 0, cntOfPaper, cnt);

			}
		}
		

	}

	private static void attach(int startRow, int startCol, int i, int flag) {
		// TODO Auto-generated method stub
		for (int row = startRow; row < startRow + i; row++) {
			for (int col = startCol; col < startCol + i; col++) {
				// 만약 해당 칸의 유효성검사를 불통과하거나
				// 0인 칸이 있으면 i길이의 색종이는 못붙인다.
				table[row][col] = flag;
			}
		}

	}

	private static boolean isAttachable(int startRow, int startCol, int i) {
		// TODO Auto-generated method stub
		for (int row = startRow; row < startRow + i; row++) {
			for (int col = startCol; col < startCol + i; col++) {
				// 만약 해당 칸의 유효성검사를 불통과하거나
				// 0인 칸이 있으면 i길이의 색종이는 못붙인다.
				if (row >= 0 && row < 10 && col >= 0 && col < 10) {
					if (table[row][col] == 0) {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		return true;
	}

}



