
import java.io.*;
import java.util.*;

class Solution {
	static int num;
	static int a, b, c, d, e;
	static int[] insuArr = { 2, 3, 5, 7, 11 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			// 숫자 입력
			num = Integer.parseInt(br.readLine());
			// 각 인수의 지수 초기
			a = 0;
			b = 0;
			c = 0;
			d = 0;
			e = 0;

			// insuArr에 들은 인수들을 차례로 divideBy에 넣어서 num이 1일때까지 반복한다.
			for (int i = 0; i < insuArr.length; i++) {
				divideBy(insuArr[i]);
				if (num == 1) {
					break;
				}
			}

			System.out.printf("#%d %d %d %d %d %d\n", (t + 1), a, b, c, d, e);

		}

	}

	private static void divideBy(int insu) {
		// TODO Auto-generated method stub
		// 인수로 나눈 나머지가 0이면 해당 인수에 해당하는 변수 abcde중 하나를 증
		while (true) {
//			System.out.println("num % insu ==  "+ (num % insu));
			if (num % insu == 0) {
				if (insu == 2) {
					a++;
				} else if (insu == 3) {
					b++;
				} else if (insu == 5) {
					c++;
				} else if (insu == 7) {
					d++;
				} else if (insu == 11) {
					e++;
				}

				num /= insu;

			} else {
				// 0으로 나누어떨어지지 않으면 루프 중단
				break;
			}
		}

	}
}