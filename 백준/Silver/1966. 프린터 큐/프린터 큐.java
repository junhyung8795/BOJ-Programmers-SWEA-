

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			String[] inputNM = br.readLine().split(" ");
			// N, M초기화
			N = Integer.parseInt(inputNM[0]);
			M = Integer.parseInt(inputNM[1]);

			// 프린터큐에 들어간 숫자들 초기화
			String[] inputNumbers = br.readLine().split(" ");
			int[][] nums = new int[N][2];
			Integer[] standardDescendant = new Integer[N];// 요소들의내림차 정렬을 저장할 배열

			// 프린터에 들은 숫자들이 중요도가 같은 애들을 구분하려면 각 요소가 몇번째로 들어갔는지 알아야 하기 때문에 i번째에 들어간 수!
			// 라는 것을 저장하기 위해 nums[i][1]에 i를 저장
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(inputNumbers[i]);
				nums[i][0] = num;
				nums[i][1] = i;
				standardDescendant[i] = num;
			}

			// 내림차 정렬
			Arrays.sort(standardDescendant, (a, b) -> b - a);

			// 내림차 정렬을 하면 중요도가 가장 높은 녀석이 제일 앞에 온다.
			// 중요도가 제일 높은 값과 q에 있는 요소[0]이 같은 경우에 뽑고
			// 뽑았을 때, 요소[1]과 M이 같으면? answer(얘는 몇번째에 뽑혔는지 저장)를 반환한다.
			// 요소[1]과 M이 다르면? 그다음으로 큰수(idx++한다.)와 요소[0]이 일치할때까지 큐에서 뽑고 맨뒤로 보내고를 반복한다.
			int idx = 0;
			int answer = 0;
			Queue<int[]> q = new LinkedList<>();
			for(int i = 0; i < N; i++) {
				q.add(nums[i]);
			}
			while (!q.isEmpty()) {
				if (q.peek()[0] == standardDescendant[idx]) {//중요도가 제일 높은 값과 q에 있는 요소[0]이 같은 경우
					answer += 1; 
					if (q.poll()[1] == M) {//근데 M과도 같으면 그게 답이 된다. -> 루프종료
						break;
					}
					idx += 1;//그 다음으로 큰 요소를 가리키기 위해 idx++
				}else {
					q.offer(q.poll());//중요도가 제일 높은 값과 q에 있는 요소[0]이 다른 경우 큐의 맨 뒤로 이동시킨다.
				}
			}
			
			
			System.out.println(answer);

		}

	}

}
