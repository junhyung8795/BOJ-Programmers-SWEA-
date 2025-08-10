

import java.io.*;
import java.util.*;

class Solution {
	static int N;
	static int[][] dirs = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
	static char[][] table;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		label:
		for (int t = 0; t < T; t++) {
			// NxN의 N입
			N = Integer.parseInt(br.readLine());
			
			// 테이블 초기화
			table = new char[N][N];
			for (int i = 0; i < N; i++) {
				table[i] = br.readLine().toCharArray();
			}

			// 행열의 각 점을 순회
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					// 돌이 놓인 각 점을 시작으로 십자, x자 방향으로 탐색해서
					// 한번이라도 돌이 5개가 세지면 true를 반환
					boolean isOk = false;
					if (table[row][col] == 'o') {
						isOk = countRock(row, col);
					}
					if (isOk) {// 5개 세졌으므로 출력하고 break후 다음테케로 넘어감
//						System.out.println("from row col" + row + " " +col);
						System.out.println("#" + (t + 1) + " YES");
						continue label;
					}

				}
			}
			// 모든 지점을탐색해도 못찾았으니 NO 출력
			System.out.println("#" + (t + 1) + " NO");

		}

	}

	private static boolean countRock(int row, int col) {
		// TODO Auto-generated method stub
		
		for (int[] dir : dirs) {
			int cnt = 1;// 현재 돌이 놓인 위치니까 1개로 초기화
			
			for (int i = 1; i <= 4; i++) {
				int dr = row + dir[0] * i;
				int dc = col + dir[1] * i; 
				if (dr >= 0 && dr < N && dc >= 0 && dc < N) {
					if(table[dr][dc] == 'o') {
						cnt++;//돌있으면 cnt++
					}else {
						//없으면 다른 방향으로 바로 틀기 
						break;
					}
					
				} else {
					//못가는 방향이 다른 방향으로 바로 틀기 
					break;
				}
				
			}
			//한방향 탐색끝나고 cnt가 5면 return true;
			if(cnt == 5) {
				return true;
			}
				
		}
		//모든 방향 다 찾아도 없다면 그땨 false 리
		return false;
	}

}