

import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int startRow;
	static int startCol;
	static int endRow;
	static int endCol;
	static int[][] table;
	static int answer;
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			//테이블 초기화
			table = new int[N][N];
			for(int row = 0; row < N; row++) {
				char[] inputRow = br.readLine().toCharArray();
				for(int col = 0; col < N; col++) {
					table[row][col] = inputRow[col] - '0';
				}
			}
			
			
			startRow = 0;
			startCol = 0;
			endRow = N - 1;
			endCol = N - 1;
			answer = 0;
			daikstra();
			System.out.println("#" + t + " "  +answer);
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
	}
	private static void daikstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				
				return o1[2] - o2[2];
			}
		});
		
		boolean[][] visited = new boolean[N][N];
		pq.add(new int[] {startRow, startCol, 0});
		visited[startRow][startCol] = true;
		
		while(!pq.isEmpty()) {
//			System.out.println("sd");
			int[] popped = pq.poll();
			int curRow = popped[0];
			int curCol = popped[1];
			int curDepth = popped[2];
			if(curRow == endRow && curCol == endCol) {
				answer = curDepth;
				return;
			}
			
			for(int dir = 0; dir < 4; dir++) {
				int dr =  curRow +  dirs[dir][0];
				int dc =  curCol +  dirs[dir][1];
				
				if(dr >= 0 && dr < N && dc >= 0 && dc < N && !visited[dr][dc]) {
					pq.add(new int[] {dr, dc, table[dr][dc] + curDepth});
					visited[dr][dc] = true; 
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			
		}
	}
}
