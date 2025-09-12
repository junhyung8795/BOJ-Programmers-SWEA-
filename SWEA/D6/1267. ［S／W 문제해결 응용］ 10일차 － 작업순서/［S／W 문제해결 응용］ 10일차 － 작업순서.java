

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		// 테스트케이스 수 10
		int T = 10;
		for (int t = 1; t <= T; t++) {
			// 입력

			// 정점의 갯수 V, 간선의 갯수 E
			String[] temp = sc.nextLine().split(" ");
			int V = Integer.parseInt(temp[0]);
			int E = Integer.parseInt(temp[1]);

			// 간선의 정보 입력받기
			// 인접 리스트
			List<List<Integer>> graph = new ArrayList<>();
			for (int i = 0; i < V + 1; i++) {
				graph.add(new ArrayList<>());
			}

			// 진입차수 배열
			int[] inDegree = new int[V + 1];

			// 간선의 정보 입력 받기
			temp = sc.nextLine().split(" ");
			for (int i = 0; i < E; i++) {
				int u = Integer.parseInt(temp[i * 2]);
				int v = Integer.parseInt(temp[i * 2 + 1]);

				// u -> v 단방향 간선 기록
				graph.get(u).add(v);

				// v의 진입차수 증가
				inDegree[v]++;
			}

			// 로직 : 위상 정렬을 위한 칸 알고리즘 실행
			List<Integer> result = topologySort(graph, V, E, inDegree);

			// 출력
			StringBuilder sb = new StringBuilder();
			sb.append("#" + t);
			for(int i = 0; i < result.size(); i++) {
				sb.append(" " + result.get(i));
			}
			System.out.println(sb.toString());
		}
	}

	private static List<Integer> topologySort(List<List<Integer>> graph, int V, int E, int[] inDegree) {
		List<Integer> result = new ArrayList<>();
		// 큐 선언
		Queue<Integer> q = new LinkedList<>();

		// 1. 진입차수가 0인 정점을 큐에 추가하고
		for(int  i = 1; i < V + 1 ; i++) {
			if(inDegree[i] == 0) {
				q.offer(i);
			}
		}
		
		//큐가 빌 때 까지
		while(!q.isEmpty()) {
			// 2 큐에서 정점꺼내고 result에 담고. 
			int current = q.poll();
			result.add(current);
			for(int next: graph.get(current)){
				//2-1.해당 정점고 연결된 정점의 진입차수 1 낮춘다.
				inDegree[next]--;
				
				// 3. 0이된다면 큐에 추가
				if(inDegree[next] == 0) {
					q.add(next);
				}
			}
			
			
		}
		
		
		return result;
	}

}