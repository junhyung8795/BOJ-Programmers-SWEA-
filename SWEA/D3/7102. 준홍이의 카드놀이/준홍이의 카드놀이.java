

import java.util.*;
import java.io.*;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테케 수 입력
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// N, M입력
			// N이 항상 더 크거나 같도록 만들 예정
			String[] cardSetInput = br.readLine().split(" ");
			int first = Integer.parseInt(cardSetInput[0]);
			int second = Integer.parseInt(cardSetInput[1]);
			int N = (first >= second) ? first : second;
			int M = (first >= second) ? second : first;

			// N과 M의 카드들을 담은 카드세트 배열 초기화
			int[] arrN = new int[N];
			int[] arrM = new int[M];
			for (int i = 0; i < N; i++) {
				arrN[i] = i + 1;
			}
			for (int i = 0; i < M; i++) {
				arrM[i] = i + 1;
			}
			
			
			//출력에 사용할 스트링빌더
			StringBuilder sb = new StringBuilder();
			
			
			// N과 M의 차이 + 1만큼 루프를 실행
			for (int i = 0; i < N - M + 1; i++) {
				//M배열의 시작점을 N배열의 i지점에 가져다대고 
				//N배열의 i지점 + M이 가장 나올 확률이 높다.
				sb.append((M + arrN[i]) + " ");
			}
			
			
			
			System.out.println("#" + t + " " + sb.toString());
		}
	}
}