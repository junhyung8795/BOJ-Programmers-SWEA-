import java.util.*;
import java.io.*;

class Main {
	static int N;
	static int[][] table;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력받기
		N = Integer.parseInt(br.readLine());
		table = new int[N][N];

		for (int row = 0; row < N; row++) {
			String[] s = br.readLine().split(" ");
			for (int col = 0; col < N; col++) {
				table[row][col] = Integer.parseInt(s[col]);
			}
		}

		// 핵심 로직/
		// 각 칸까지 도달하는 경우의수는 목표 칸보다 왼 위에 있는 칸들로부터 목표칸에 도달하는 모든 경우의 수들의 합이다,
		// 따라서 해당 칸까지 도달하는 경우의 수를 구하려면 경로 수 테이블(visited)을 만든 후 해당 칸까지 오는 경로들의 수를 저장한다.
		// for문을 이중으로 돌면서 맨처음에 맨위왼쪽칸은 1의 경우의 수가 있으므로 이를 시작으로
		// 맨위왼쪽에 적힌 수만큼 오른쪽 아래쪽으로 이동하고 도착한 칸에 적힌 경로들의 수에 + 1을 해주면서
		// 맨아래 오른쪽에 도달할때까지 반복해준다.
		// 단 이러한 경로들을 뻗어나가며 업데이트하는 것은 visited배열의 값이 1이상안 경우에만 한정한다.
		// 해당칸까지오는 경로가 0개인데 거기서 더 뻗어나가는 경우는 없기 때문
		long[][] visited = new long[N][N];
		visited[0][0] = 1;// 첫칸은 경로1개가 있다고 취급
		for (int row = 0; row < N; row++) {
			
		
			for (int col = 0; col < N; col++) {
				if(table[row][col] == 0) {
					//0이면 이하 로직을 생략하고 다음칸 탐색
					continue;
				}
				
				//1이상인 경우의 로직
				//변화량 델타
				int delta = table[row][col];
				//아래 범위가 유효할때
				if(row + delta < N) {
					visited[row + delta][col] += visited[row][col];
				}
				//오른쪽 범위가 유효할때
				if(col + delta < N) {
					visited[row][col + delta]+= visited[row][col];
				}
				
				
				

			}
		}
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}
		System.out.println(visited[N - 1][N - 1]);

	}

}
