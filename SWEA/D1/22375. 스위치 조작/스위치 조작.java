

import java.io.*;
import java.util.*;

class Solution {
	static int[] A;
	static int[] B;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			// 입력, A, B배열 초기화
			int N = Integer.parseInt(br.readLine());
			A = new int[N];
			B = new int[N];
			String[] strArrOfA = br.readLine().split(" ");
			String[] strArrOfB = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(strArrOfA[i]);
				B[i] = Integer.parseInt(strArrOfB[i]);
			}

			// 스위치 누른 횟수
			int cnt = 0;
			// 각 배열의 인덱스 순회
			for (int i = 0; i < N; i++) {
				// 만약 i번째의 수가 서로 같으면 스위치 안눌러도된다.
				if (A[i] == B[i]) {
					continue;
				} else {// 다르면 스위치를 눌러야한다.
					cnt++;
					// 스위치를 눌렀으니 A를 i부터 N-1인덱스에 존재하는 수들을 전부 반대로 바꿔야한다.
					for (int j = i; j < N; j++) {
						A[j] = (A[j] == 1) ? 0 : 1;
					}

				}
			}
			
			System.out.println("#" + (t + 1) +" " +cnt);

		}

	}

}