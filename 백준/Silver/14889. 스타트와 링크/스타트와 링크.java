import java.util.*;
import java.io.*;

class Main {
	static List<Integer> start;
	static List<Integer> link;
	static int answer;
	static int N;
	static int[][] table;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		table = new int[N][N];

		for (int row = 0; row < N; row++) {

			String[] inputRow = br.readLine().split(" ");

			// 테이블 초기화
			for (int col = 0; col < N; col++) {
				table[row][col] = Integer.parseInt(inputRow[col]);
			}

		}

		// 두 팀 시너지들의 차이가 최소가 되면 그 최솟값은 얼마인가?
		// a-b나 b-a나 차이는 절댓값으로 똑같다.
		// 20명에서 10명을 뽑는 가짓수는 너무 많다. 20C10
		// 스타트와 링크 팀의 조합이 a,b조합이 뽑힌다면 b,a조합으로 봅히기도하는데 이걸 없애야한다.
		start = new LinkedList<>();
		link = new LinkedList<>();
		// 스타트 그룹에 0을 먼저 넣어주고 시작하면
		// 스타트그룹은 무조건 0을 가지고 시작하는 그룹이 되어
		// a,b조합이 b,a조합이 되는 걸 방지한다.
		start.add(0);
		answer = Integer.MAX_VALUE;
		backtracking(1);
		System.out.println(answer);
	}

	private static void backtracking(int curIndex) {
		// TODO Auto-generated method stub
		// 기저조건 각 팀의 인원수가 N / 2 가되는 시점
		if (start.size() == N / 2 && link.size() == N / 2) {
			
			//스타트팀 총합 구하기
			int totalStart = 0;
			for (int a: start) {
				for (int b: start) {
					totalStart += table[a][b];
				}
			}
			
			
			//링크팀 총합 구하기
			int totalLink = 0;
			for (int a: link) {
				for (int b: link) {
					totalLink += table[a][b];
				}
			}
			
			
			answer = Math.min(answer, Math.abs(totalLink - totalStart));
		}
		
		
		
		//현재 인덱스에서 각 팀의 사이즈가 N / 2보다 작으면 해당 인덱스의 사람을 팀에 넣을 수 있다.
		//스타트팀에 넣거나 혹은? 링크팀에 넣을 수 있다.
		if(start.size() < N / 2) {
			start.add(curIndex);
			backtracking(curIndex + 1);
			start.remove(start.size() - 1);
		}
		
		if(link.size() < N / 2) {
			link.add(curIndex);
			backtracking(curIndex + 1);
			link.remove(link.size() - 1);
		}
		

	}
}
