import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			// 테이블 크기 N입력, answer초기화
			N = Integer.parseInt(br.readLine());
			answer = 0;

			backtracking(0, new LinkedList<Integer>());
			System.out.println("#" + t + " " +answer);
		}
	}

	private static void backtracking(int currentRow, LinkedList<Integer> current) {
		// TODO Auto-generated method stub
		if (currentRow == N) {// N번째 행에 도달하면 다 배치했으므로 리턴
			answer += 1;
			return;
		}
		
		for(int col = 0; col < N; col++){
			boolean colOk = true;//해당열에 퀸을 배치해도좋은지 체크
			for(int num: current) {
				int rowInArr = num / N;
				int colInArr = num % N;
				if((colInArr == col) || (Math.abs(colInArr- col) == Math.abs(rowInArr - currentRow))) {
					colOk = false;//이전에 놓은 퀸들의 위치의 열이 겹치거나 열과 행차이가 같아지면 못놓는다.
					break;//하나라도 공격범위에 있다는 뜻이므로 다른 퀸들의 조건을 볼필요x
				}
			}
			
			if(colOk){//배치가능한 열이라면 current에 "현재행 * N + 열"을 넣고 백트래킹 
				current.add(currentRow * N + col);
				backtracking(currentRow + 1, current);
				current.remove(current.size() - 1);//원복
			}
		}
		
		
		
	}

}
