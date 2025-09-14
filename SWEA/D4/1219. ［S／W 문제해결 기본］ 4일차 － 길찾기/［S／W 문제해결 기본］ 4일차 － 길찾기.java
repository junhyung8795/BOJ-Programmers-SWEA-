
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
	static int[] nextNode1;
	static int[] nextNode2;
	static int T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		for (int l = 1; l <= 10; l++) {
			String[] inputTE = br.readLine().split(" ");
			T = Integer.parseInt(inputTE[0]);
			int E = Integer.parseInt(inputTE[1]);
			
			//nextNode1, nextNode2 초기화 
			nextNode1 = new int[100];
			nextNode2 = new int[100];
			Arrays.fill(nextNode1, -1);
			Arrays.fill(nextNode2, -1);//간선 연결 안돼있으면 -1로표현 
			
			//간선정보받기 
			String[] inputEs = br.readLine().split(" ");
			for(int i = 0; i < inputEs.length; i+=2) {
				int start = Integer.parseInt(inputEs[i]);
				int end = Integer.parseInt(inputEs[i +1]);
				
				if(nextNode1[start] == -1) {
					nextNode1[start] = end;//연결시킴.
				}else {
					nextNode2[start] = end;//첫번째가 이미 연결돼있으면 두번째에 연결 
				}	
			}
			
			bfs();//시작정점 0번을 기준으로 bfs
			
			
			
		}

	}

	private static void bfs() {
		Queue<Integer> q= new LinkedList<>();
		q.add(0);
		boolean[] visited = new boolean[100];
		visited[0] = true;
		
		while(!q.isEmpty()) {
			int curNode = q.poll();
			if(curNode == 99) {
				System.out.println("#" + T +" " + 1);
				return;
			}
			
			//99에 도착 못했다면 해당 정점에서 다른 정점으로 간선을 통해 이동 
			if(nextNode1[curNode] != -1) {
				q.add(nextNode1[curNode]);
			}
			if(nextNode2[curNode] != -1) {
				q.add(nextNode2[curNode]);
			}
			
			
		}
		System.out.println("#" + T +" " + 0);

	}

}