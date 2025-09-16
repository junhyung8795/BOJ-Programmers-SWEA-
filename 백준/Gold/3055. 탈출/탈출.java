import java.io.*;

import java.util.*;

public class Main {

	
	static int R;
	static int C;
	static char[][] table;
	static int time;
	static boolean[][] waterVisited;
	static boolean[][] hedgehogVisited;
	static Queue<int[]> waterQ;
	static Queue<int[]> hedgehogQ;
	static int endRow;
	static int endCol;
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputRC = br.readLine().split(" ");
		R = Integer.parseInt(inputRC[0]);
		C = Integer.parseInt(inputRC[1]);
		table = new char[R][C];
		waterVisited = new boolean[R][C];
		hedgehogVisited = new boolean[R][C];
		waterQ = new LinkedList<>();
		hedgehogQ = new LinkedList<>();
		
		for(int row = 0 ; row < R; row++){
			char[] inputRow = br.readLine().toCharArray();
			table[row] = inputRow;
			for(int col = 0; col < C; col++) {
				//만약 돌인 경우
				//물도 지날 수 없고 고슴도치도 지날 수 없다.
				if(table[row][col] == 'X') {
					waterVisited[row][col] = true;
					hedgehogVisited[row][col] = true;
				} else if(table[row][col] == '*') {//물인 좌표들을 워터 큐에 넣는다.
					waterQ.add(new int[] {row, col});
					waterVisited[row][col] = true;
				} else if(table[row][col] == 'S') {//고슴도치의 시작좌표를 헤지호그 큐에 넣는다.
					hedgehogQ.add(new int[] {row, col});
					hedgehogVisited[row][col] = true;
				} else if(table[row][col] == 'D') {
					endRow = row;
					endCol = col;//비버굴은 도착지점이니 좌표 저장
					
					
				}
			}
		}
		
		
		time = 0;
		simulation();
		if(time == -1) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(time);
		}
		
		
		
	}
	private static void simulation() {
		//
		
		while(!waterQ.isEmpty() || !hedgehogQ.isEmpty()) {
			
			
			
			int wQSize = waterQ.size();
//			System.out.println("waterQ is " + waterQ);
			for(int  i = 0 ; i < wQSize; i++) {
				int[] popped = waterQ.poll();
				int curRow = popped[0];
				int curCol = popped[1];
				
				for(int dir = 0; dir < 4; dir++) {
					int dr = curRow + dirs[dir][0];
					int dc = curCol + dirs[dir][1];
					
					
					if(dr >= 0 && dr < R && dc >= 0 && dc < C && !waterVisited[dr][dc] && !(dr == endRow && dc == endCol)) {
						waterVisited[dr][dc] = true;
						waterQ.add(new int[] {dr, dc});
					}
				}
				
				
			}
//			System.out.println(waterQ);

//			System.out.println();
//			System.out.println("hQ is  = " +hedgehogQ);
			int hQSize = hedgehogQ.size();
			for(int  i = 0 ; i < hQSize; i++) {
				int[] popped = hedgehogQ.poll();
				int curRow = popped[0];
				int curCol = popped[1];
				
				
				if(curRow == endRow && curCol == endCol){
					return;
				}
				
				
				
				for(int dir = 0; dir < 4; dir++) {
					int dr = curRow + dirs[dir][0];
					int dc = curCol + dirs[dir][1];
					
					
					if(dr >= 0 && dr < R && dc >= 0 && dc < C && !hedgehogVisited[dr][dc] && !waterVisited[dr][dc]) {//이미 지나온 길과 현재 물의 위치에도 가면 안된다.
						hedgehogVisited[dr][dc] = true;
						hedgehogQ.add(new int[] {dr, dc});
					}
				}
				
				
			}
			
			
//			System.out.println(hedgehogQ);

			time++; //시간 증가 후 물과 고슴도치 이동
			
			
			
			
			
		}
		
		time = -1;
		return;
		
		
		
		
		
		
		
		
	}

	
}

