import java.io.*;
import java.util.*;


public class Main {
	static int R;
	static int C;
	static boolean[][] visited;
	static char[][] table;
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static HashSet<Character> set;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//R, C인풋 받기
		String[] inputRC = br.readLine().split(" ");
		R = Integer.parseInt(inputRC[0]);
		C = Integer.parseInt(inputRC[1]);
		
		
		//R번 반복하면서 테이블 초기화
		table = new char[R][C];
		for(int row = 0; row < R; row++) {
			char[] inputRow = br.readLine().toCharArray();
			table[row] = inputRow;
		}
		
		set = new HashSet<>();
		visited = new boolean[R][C];
		//0,0에서 부터 백트래킹 시작
		//0,0에서 부터 각 지점들을 델타탐색하는데, 여태까지 다녀온 길에 있던 문자들이 가려고하는 길에 있다면 갈 수 없다.
		//그래서 만약에 4방 탐색을 다했는데 이미 다녀온 곳이거나, 아니면 다녀오진 않았지만 내가 이미 왔던 길에 있던 문자를 가진 곳만 있다면
		//그 지점에서 백트래킹을 멈추고 여태까지의 문자들의 갯수를 세서 answer랑 비교한다.
		
		set.add(table[0][0]);//0,0에 해당하는 문자는 이미 지니고 있고, answer는 시작점 1칸을 포함하기에 1로 초기화한다.
		answer = 1;
		backtracking(0, 0);
		
		
		
		
		System.out.println(answer);
		
		
	}
	private static void backtracking(int curRow, int curCol) {
		
		boolean backtracked = false;
		for(int dir = 0; dir < 4; dir++){//델타탐색
			int dr = curRow + dirs[dir][0];
			int dc = curCol + dirs[dir][1];
			if(dr >= 0 && dr < R && dc >= 0 && dc < C && !visited[dr][dc] && !set.contains(table[dr][dc])) {
				//방문 하지 않음과 동시에 여태까지 지나온 문자들을 포함하지 않는 발판이면 갈 수 있다.
				backtracked = true;
				set.add(table[dr][dc]);
				visited[dr][dc] = true;
				backtracking(dr, dc);
				set.remove(table[dr][dc]);//원복
				visited[dr][dc] = false;

				
			}
		}
		
		if(!backtracked) {
			answer = Math.max(answer, set.size());
		}
	}

	
}
