

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 함수들 모임p입력받기
			char[] inputP = br.readLine().toCharArray();

			// 숫자 배열 입력받기
			int N = Integer.parseInt(br.readLine());
			String inputStr = br.readLine().trim();
			String[] inputArr = inputStr.substring(1, inputStr.length() - 1).split(",");
			
			ArrayDeque<String> dq = new ArrayDeque<>();
			for (int i = 0; i <= inputArr.length -1; i ++) {
				dq.add(inputArr[i]);// 숫자만 골라넣는 로직
			}
			
			//[]이면 공백문자가 1개 들어가는 걸로 판정돼서 N == 0이면 그냥 dq에서 요소 하나 빼준다.
			if( N == 0){
				dq.removeFirst();
			}
			
			int isR = 0;// 뒤집기 기능 비활성
			boolean isOk = true;
			for (int i = 0; i < inputP.length; i++) {// p에있는 문자들을 하나씩 순회
				if (inputP[i] == 'R') {
					// 뒤집기라면 나중에 'D'가 나올때 뒤에서 제거함.
					isR ^= 1;// 현재상태에서 반전
				} else if (inputP[i] == 'D') {
//					System.out.println(dq.size() + " " + dq.peekFirst());
					if (!dq.isEmpty()) {// dq가 비어있는지 확인
						if (isR == 0) {
							dq.removeFirst();// 안뒤집었으면 앞에서빼고
						} else {
							dq.removeLast();// 뒤집었으면 뒤에서 뺀다.
						}
					} else {
						// 비어있으면 error를 출력

						isOk = false;
						break;
					}

				}

			}
			
		
			if (!isOk) {
				// error가 난 경우
				System.out.println("error");
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append("[");

				while (!dq.isEmpty()) {
					if (isR == 0) {
						// 앞에서 부터배면서 sb.append
						sb.append(dq.removeFirst());
						if (dq.size() != 0) {
							sb.append(",");
						}
					} else {
						if (isR == 1) {
							// 앞에서 부터배면서 sb.append
							sb.append(dq.removeLast());
							if (dq.size() != 0) {
								sb.append(",");
							}
						}
					}
				}
				
				
				sb.append("]");
				
				System.out.println(sb.toString());
			}

		}

	}

}
