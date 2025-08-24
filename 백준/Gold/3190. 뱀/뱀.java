import java.io.*;
import java.util.*;

class Main {
	static int N;
	static int K;
	static int[][] table;
	static int L;
	static Queue<int[]> q;
	static int time = 0;
	static int[][] dirs = { {}, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[][] changeDirection = { {}, { 3, 4 }, { 4, 3 }, { 2, 1 }, { 1, 2 } };
	static boolean[][] visited;
	static int[][] tailDirection;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		// table visited tailDirection초기화 
		table = new int[N][N];
		visited = new boolean[N][N];
		tailDirection = new int[N][N];
		for (int i = 0; i < K; i++) {
			String[] appleInput = br.readLine().split(" ");
			int row = Integer.parseInt(appleInput[0]) - 1;
			int col = Integer.parseInt(appleInput[1]) - 1;
			table[row][col] = 1;
			// 사과가 있으면 1로 저장
		}

		// 뱀이동경로 저장
		L = Integer.parseInt(br.readLine());
		q = new LinkedList<>();
		for (int i = 0; i < L; i++) {
			String[] snakeInput = br.readLine().split(" ");
			int second = Integer.parseInt(snakeInput[0]);
			int leftRight = (snakeInput[1].equals("L")) ? 0 : 1;
			q.offer(new int[] { second, leftRight });
			// 왼쪽이면 0으로 오른쪽이면 1로저장
		}
		
		
		snakeMove();
		System.out.println(time);
	}
	private static void snakeMove() {
		// TODO Auto-generated method stub
		int dir = 4;//초기이동방향오른쪽 4
		int curRow = 0;
		int curCol = 0;//초기위치 0, 0
		int curTailRow = 0;
		int curTailCol = 0;//꼬리의 초기위치 
		tailDirection[0][0] = 4;
		visited[curRow][curCol] = true;//
		while(true) {
			time++;
			curRow += dirs[dir][0];
			curCol += dirs[dir][1];
			//System.out.println("curRow = " + curRow  +" curCol = " + curCol );
			//System.out.println("time = " + time );

			//1. 벽이나 자기자신에게 부딪혔는지 확인
			
			if(curRow < 0 || curRow >= N || curCol < 0 || curCol >= N) {
				return;
			}
			if(visited[curRow][curCol] == true) {
				return;
			}
			
			
			//위 조건이 아니라면 visited업데이트 
			visited[curRow][curCol] = true;
			
			//2. 사과 o
			if(table[curRow][curCol] == 1) {
				table[curRow][curCol] = 0;
			} else if(table[curRow][curCol] == 0) {
				//사과가 없는 경우 
				visited[curTailRow][curTailCol] = false;
				int tailDir = tailDirection[curTailRow][curTailCol];
				curTailRow += dirs[tailDir][0];
				curTailCol += dirs[tailDir][1];
			}
			
			
			
			//3.q에 들어가있는 놈의 첫인덱스 = 방향변동시간 
			if(!q.isEmpty() && q.peek()[0] == time) {
				dir = changeDirection[dir][q.peek()[1]];
				
				q.poll();
			}
			tailDirection[curRow][curCol] = dir;
			
		}
	}
}
