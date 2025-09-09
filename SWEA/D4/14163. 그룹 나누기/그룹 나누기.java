

import java.io.*;
import java.util.*;

public class Solution {
	private static int findSet(int[] parent, int x) {
		// 재귀적으로 우리가 대표자를 계속해서 찾는다
		// TODO Auto-generated method stub
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = findSet(parent, parent[x]);

	}

	private static void union(int[] parent, int x, int y) {
		// TODO Auto-generated method stub
		int root_x = findSet(parent, x);
		int root_y = findSet(parent, y);
		
		if(root_x != root_y) {
			parent[root_x] = root_y;
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		// 테케 수
		int T = Integer.parseInt(sc.nextLine());

		for (int tc = 1; tc <= T; tc++) {
			// 입력
			String[] temp = sc.nextLine().split(" ");
			// 출석번호 (1번~N번 까지 인원)
			int N = Integer.parseInt(temp[0]);
			// 신청서 갯수 M
			int M = Integer.parseInt(temp[1]);

			// 신청서 한줄에 대한 입력
			int[] data = new int[M * 2];
			String[] temp2 = sc.nextLine().split(" ");
			for (int i = 0; i < temp2.length; i++) {
				data[i] = Integer.parseInt(temp2[i]);
			}

			// 로직: 서로소 집합 union-find알고리즘 구현
			// init 초기화 make-set
			int[] parent = new int[N + 1];
			for (int i = 0; i < parent.length; i++) {
				parent[i] = i;
			}

			// 데이터를 통해서 x,y 원소를 하나의 그룹으로 union
			for (int i = 0; i < M * 2; i += 2) {
				int x = data[i];
				int y = data[i + 1];
				union(parent, x, y);
			}
			
			//그룹의 갯수가 몇개인가
			int count = 0;
			Set<Integer> groups = new HashSet<>();
			for(int i = 1; i <= N; i++) {
				groups.add(findSet(parent, i));
				//parent[i]를 넣으면 절대 안된다.
				//각 노드의 부모노드가 루트노드로 전부 바뀌지 않은 경우가 있을 수 있기 때문에.
				//항상 findSet을 호출해서 넣어야한다.
			}
			
			count = groups.size();
			// 출력
			
			System.out.println("#" + tc + " " + count);
		}
	}

}
