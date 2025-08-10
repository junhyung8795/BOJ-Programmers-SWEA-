
import java.io.*;
import java.util.*;

class Main {
	static StringBuilder answer = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

//		System.out.println((int) Math.pow(2, N) - 1);
		answer.append(((int) Math.pow(2, N) - 1)+"\n");
		hanoi(N, 1, 3);
		System.out.println(answer);
	}

	private static void hanoi(int n, int from, int to) {
		// TODO Auto-generated method stub
		if (n == 1) {
			// 1개면 맨 밑 원판을 옮기는 것.
			// 바로 한개를 from에서 to로 옮긴다.
//			System.out.println(from + " " + to);
			answer.append(from + " " + to +"\n");
			return;
		}
		
		//1개가 아니면
		//맨밑 원판을 제외한 나머지는 중간지점에 옮기고
		hanoi(n - 1, from ,6 - from - to);
		
		//맨 밑 원판을 목적지로 옮긴다음
		answer.append(from + " " + to +"\n");
		
		//중간에지점으로 옮겨놨던 원판들을 다시 목적지로 옮긴다.
		hanoi(n - 1, 6 - from - to, to);

	}
}