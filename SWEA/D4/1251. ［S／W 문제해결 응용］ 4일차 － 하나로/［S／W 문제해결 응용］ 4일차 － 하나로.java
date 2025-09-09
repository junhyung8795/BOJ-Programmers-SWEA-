

import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static int[][] nodes;
	static List<Edge> edges;
	static double E;

	static class Edge implements Comparable<Edge> {

		int startNodeNum;

		int endNodeNum;
		double distance;

		public Edge(int startNodeNum, int endNodeNum) {
			super();

			this.startNodeNum = startNodeNum;
			this.endNodeNum = endNodeNum;
			int startX = nodes[startNodeNum][0];
			int startY = nodes[startNodeNum][1];
			int endX = nodes[endNodeNum][0];
			int endY = nodes[endNodeNum][1];
			this.distance = Math.sqrt(Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2));
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			if (this.distance < o.distance) {
				return -1;
			} else if(this.distance > o.distance) {
				return 1;
			} else {
				return this.startNodeNum - o.startNodeNum;
			}
		}

		@Override
		public String toString() {
			return "Edge [startNodeNum=" + startNodeNum + ", endNodeNum=" + endNodeNum + ", distance=" + distance + "]";
		}
		
		
	}

	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			nodes = new int[N][2];
			String[] inputNodesX = br.readLine().split(" ");
			String[] inputNodesY = br.readLine().split(" ");

			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(inputNodesX[i]);
				int y = Integer.parseInt(inputNodesY[i]);
				nodes[i] = new int[] { x, y };
//				System.out.println(Arrays.toString(nodes[i]));
			}

			E = Double.parseDouble(br.readLine());

			// 각 정점들을 기준으로 연결할 수 있는 모든 간선들을 고려하여
			// 간선 리스트에 넣고 정렬한다.
			edges = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					edges.add(new Edge(i, j));
					
				}
			}
			
			Collections.sort(edges);
			parent = new int[N];
			for (int i = 0; i < N; i++) {
				parent[i] = i;
			} // makeSet

			int count = 0;
			double dist = 0;
			
			
			for (Edge edge : edges) {
				
				if (findSet(edge.startNodeNum) != findSet(edge.endNodeNum)) {
					union(edge.startNodeNum, edge.endNodeNum);
					count++;
					dist += Math.pow(edge.distance, 2);
//					System.out.println(edge);
				}

				if (count == N - 1) {
					break;
				}
			}
//			System.out.println("E = " + E);
			System.out.println("#" + t + " " + Math.round(E * dist));
		}

	}

	private static void union(int x, int y) {
		x = findSet(x);
		y = findSet(y);

		if (parent[x] != parent[y]) {
			parent[x] = y;
		}

		return;
	}

	private static int findSet(int x) {
		if (x != parent[x]) {
			return parent[x] = findSet(parent[x]);
		}
		return x;
	}
}
