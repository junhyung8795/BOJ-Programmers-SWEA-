
import java.io.*;
import java.util.*;

class Solution {
	static int K;
	static int[][] mgntArr;
	static int[][] revolve;
	static int[] pickIndex;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine());
			mgntArr = new int[5][16];
			for (int i = 1; i <= 4; i++) {
				String[] inputMgnt = br.readLine().trim().split(" ");
				for (int j = 0; j < 8; j++) {
					mgntArr[i][j] = Integer.parseInt(inputMgnt[j]);
				}
				for (int j = 8; j < 16; j++) {
					mgntArr[i][j] = Integer.parseInt(inputMgnt[j % 8]);
				}
				// 자석은 두배복사해서 사이클을 만듬
			}

			pickIndex = new int[5];
			for (int i = 0; i < K; i++) {
				String[] inputMove = br.readLine().trim().split(" ");
				int orderMgnt = Integer.parseInt(inputMove[0]);
				int orderDir = Integer.parseInt(inputMove[1]);
				revolve = new int[5][2];
				search(orderMgnt, orderDir, 0);
				for (int numOfMgnt = 1; numOfMgnt <= 4; numOfMgnt++) {
					if (revolve[numOfMgnt][0] == 1) {
						if (revolve[numOfMgnt][1] == 1) {
							pickIndex[numOfMgnt] += (-1);// 시계방향이므로 -1만큼 이동
							pickIndex[numOfMgnt] = (pickIndex[numOfMgnt] < 0) ? 7 : pickIndex[numOfMgnt];
						} else if (revolve[numOfMgnt][1] == -1) {
							pickIndex[numOfMgnt] += 1;// 반 시계방향이므로 1만큼 이동
							pickIndex[numOfMgnt] %= 8;
						}
					}
				}
//				System.out.println("pickIndex = " + Arrays.toString(pickIndex));
//				System.out.print("revolve = ");
				for (int l = 1; l <= 4; l++) {
//					System.out.print(Arrays.toString(revolve[l]));	
				}

			}

			// 모든 입력 처리 완료 후 출력
			answer = 0;
			for (int i = 1; i <= 4; i++) {
				int curJasung = mgntArr[i][pickIndex[i]];// i번째 자석의 극을 따진다.
				if (curJasung == 1) {
//					System.out.println("i번재 자석 = " );
//					System.out.println(1<<(i - 1));
					answer += 1 << (i - 1);
				}
			}
//			System.out.println(Arrays.toString(pickIndex));
			System.out.println("#" + t + " " + answer);
		}

	}

	private static void search(int orderMgnt, int orderDir, int toGo) {
		// TODO Auto-generated method stub
		// 마냑 오른쪽이 존재하면 오른쪽 자석이 자신과 다른지 확인
		if (orderMgnt <= 3 && orderMgnt > 0 && (toGo == 1 || toGo == 0)) {
			// 오른쪽이 존재
			
//			System.out.println("orderMgnt = " + orderMgnt);
			int curRight = pickIndex[orderMgnt] + 2;
			int nextLeft = pickIndex[orderMgnt + 1] + 6;

			if (mgntArr[orderMgnt][curRight] != mgntArr[orderMgnt + 1][nextLeft]) {
				search(orderMgnt + 1, -orderDir, 1);
			}
		}

		// 만약 왼쪽이 존재하면 왼쪽 자석이 자신과 다른지 확인
		if (orderMgnt > 1 && (toGo == -1 || toGo == 0)) {
			// 왼쪽이 존재
			int curLeft = pickIndex[orderMgnt] + 6;
			int prevRight = pickIndex[orderMgnt - 1] + 2;

			if (mgntArr[orderMgnt][curLeft] != mgntArr[orderMgnt - 1][prevRight]) {

				search(orderMgnt - 1, -orderDir, -1);
			}
		}

		// 본인 회전
		revolve[orderMgnt][0] = 1;
		revolve[orderMgnt][1] = orderDir;
	}
}
