
import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] table;

	static int time;
	static int[][] dirs = { { -1, 0 }, { 0, -1 },  { 0, 1 }, { 1, 0 } };
	static int sizeOfShark;
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N초기화
		N = Integer.parseInt(br.readLine());
		int startRow = 0;
		int startCol = 0;
		// 테이블 초기화
		table = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] inputFish = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				table[i][j] = Integer.parseInt(inputFish[j]);
				if (table[i][j] == 9) {
					startRow = i;
					startCol = j;
				}
			}
		}

		// 시작 상어의 크기
		sizeOfShark = 2;
		cnt = 0;
		time = 0;
		// 먼저 시작지점에서 부터 bfs를 하면서 가장 가까우면서
		// 자신의 몸집보다 작은 물고기들을 찾는다.
		// 찾는 즉시 자신의 cnt를 1증가시키고 table[row][col] =0 으로만들고 cnt가 자신의 사이즈와 같으면 cnt = 0으로하고
		// sizeOfShark += 1;
		// 그리고 매번 물고기를 먹는 상황이 올때마다 bfs를 멈추고
		// 다시 해당 지점에서부터 bfs를 하면서 먹을 수 있는 물고기가 있는 지 찾는다.
		// 먹을 수 있는 물고기가 있으면 걸어온 칸수를 기억해 놨다가. 해당 칸수만큼 time을 증가시킨다.
		// 만약에 먹을 수 있는 물고기가 없다 == 갈 수 있는 곳이 없다 == 사방이 장외범위 + visited = true라면 갈 수 있는 곳이
		// 없다는 뜻.
		// 그러면 저장돼 있던 time을 그대로 반환
		bfs(startRow, startCol, 0);
		
		System.out.println(time);
	}

	private static void bfs(int startRow, int startCol, int startDist) {
		// TODO Auto-generated method stub
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new int[]{startRow, startCol , startDist});
		visited[startRow][startCol] = true;
		table[startRow][startCol] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
			@Override
			public int compare(int[] a, int[] b) {
				if(a[2] != b[2]) {
					return a[2] - b[2];
				} else if(a[0] != b[0]) {
					return a[0] - b[0];
				}else  {
					return a[1] - b[1];
				}
			}
		});
		while(!q.isEmpty()) {
			int[] popped = q.poll();
			int curRow = popped[0];
			int curCol = popped[1];
			int curDist = popped[2];
			//pop해서 나온 칸의 물고기가 상어의 사이즈보다 작으면 바로 break후 curRow, curCol지점에서 새로운 bfs
			if(table[curRow][curCol] < sizeOfShark && table[curRow][curCol] > 0) {
//				table[curRow][curCol] = 0;
//				//해당 지점까지 걸어온 거리만큼 시간이 추가
//				time += curDist;
//				curDist = 0;
//				
//				//cnt를 올리면서 만약 cnt가 상어 몸집만해졌다면 몸집을 1 키우고 0으로 초기화
//				cnt += 1;
//				if(cnt == sizeOfShark) {
//					sizeOfShark += 1;
//					cnt = 0;
//				}
//				//새로 bfs를 출발할 newRow, new Col
//				newRow = curRow;
//				newCol = curCol;
//				break;
//				table[curRow][curCol] = 0;
				pq.add(new int[] {curRow, curCol, curDist});
			}
			for(int i = 0; i < 4; i++) {
				int dr = curRow + dirs[i][0];
				int dc = curCol + dirs[i][1];
				
				//유효성검사
				if(dr >=0 && dr < N && dc >=0 && dc < N) {
					//이미 방문한 곳인지와 해당 지점이 상어의 몸집보다 작거나 같은지 검사
					if(visited[dr][dc] == false && table[dr][dc] <= sizeOfShark) {
						visited[dr][dc] = true;
						q.offer(new int[]{dr, dc , curDist + 1});
					}
				}
			}
		}
		
		if(pq.peek() == null) {
			return;
		}
		int[] nearestPoint = pq.poll();
		int newRow = nearestPoint[0];
		int newCol = nearestPoint[1];
		int addedDist = nearestPoint[2];
		time += addedDist;
		cnt += 1;
		if(cnt == sizeOfShark) {
			sizeOfShark += 1;
			cnt = 0;
		}
		bfs(newRow, newCol, 0);
	}
}
