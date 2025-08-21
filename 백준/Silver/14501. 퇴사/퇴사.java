
import java.io.*;
import java.util.*;

class Main {
	static int[][] table;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		table = new int[N][2];
		for (int i = 0; i < N; i++) {
			String[] inputTimeAndPrice = br.readLine().split(" ");
			table[i][0] = Integer.parseInt(inputTimeAndPrice[0]);
			table[i][1] = Integer.parseInt(inputTimeAndPrice[1]);
		}

		// 1번인덱스면 1번째날에 벌 수 있는 최대액수를 담은 테이블
		int[] dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			// 테이블 순회하면서 각 요소가 기여할 수 있는 날짜와 동일한
			// 인덱스 번호를 보고 해당 날짜에 벌 수 있는 최대 액수를 계속 개산해본다.
			// 만약 1에서 3 10이고 3에서 1 50이면?
			// 그냥 3번째 날 당일에 상담하고 50벌면 그만이다.
			// 3 10 아니고 3 60이면 당일에 일하는게 아니라 1번째날에 상담해서 3일에 돈받는게 맞는거다ㅣ.
			// 그러나 두번째 날에서 2 100이나 50아면?
			// 1번째날 : 3 60 2번째날: 2 100 3번째날 1 50
			// 이면 1번째날만 고려했을 땐, 3번째날에 60받는게 맞다
			// 하지만?2번째날도 3번째날에 기여할 수 있다.
			// -> 100의 이득이 3번째날에 받을 수 잇는 기존의 이득(60)보다 많아진 것.
			// 이로써 3번째 날에 받을 수 있는 이득은 100이다.
			// 만약 4번째 날에 받을 수 있는 이득은?
			// 각 날짜는 사실 당일날에 상담하고 돈을 받을 수 있다.
			// 4번째날에 1 50이면 4번째날 받을 수 있는 최대액수는? 150
			// 전날에 받을 수 있는 최대액수 + 당일날 받을 수 있는 액수와 현재 본인의 최대액수, 그리고 이전날을 a라고 하면
			// dp[a - 1] + table[a][0]을 더한 값이 크냐를 비교해야한다.
			int time = table[i - 1][0];
			int price = table[i - 1][1];
			// 타임이 1이면 해당날짜에 바로 일해서 돈을 얻을 수 있다.
			// 그래서 이전날짜까지 얻은 수익 +당일에 번 수익을 더한다.
			if (time == 1) {
                //전날에 받은 수익에 현재 Price를 더하는 것보다 이전의 다른 날에 의해 엄청 난 수익을 받았으면 이미 dp[i]가 엄청 큰 상태일테니 max비교
				dp[i] = Math.max(dp[i - 1] + price, dp[i]);
			} else {
				// 당일에 돈을 벌 수 없을 때.
				//위와 마찬가지로 dp[i] = dp[i - 1]을 안하는 이유는 i보다 이전에 어느 한 날에 의해서 엄청난 수익을 받은 상태여서
				//i-1번째까지의 수익을 그대로 옮기는 것보다 더 많이 받을 수도 있는 상황이 생길 수 있기 때문
				dp[i] = Math.max(dp[i - 1], dp[i]);
				if (i + time - 1 <= N) {
					dp[i + time - 1] = Math.max(dp[i + time - 1], dp[i - 1] + price);
				}
			}
			

		}
		
	System.out.println(dp[N]);
	}

}