
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
	static int N;
	static int M;
	static int K;

	static class Node {
		int row;
		int col;
		int life;
		int dir;

		public Node(int row, int col, int life, int dir) {
			super();
			this.row = row;
			this.col = col;
			this.life = life;
			this.dir = dir;
		}

	}

	static Queue<Node> q;
	static int answer;
	static int[][] dirs = { {}, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[] changeDir = { 0, 2, 1, 4, 3 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			String[] inputNMK = br.readLine().trim().split(" ");
			N = Integer.parseInt(inputNMK[0]);
			M = Integer.parseInt(inputNMK[1]);
			K = Integer.parseInt(inputNMK[2]);
			q = new LinkedList<>();
			for (int i = 0; i < K; i++) {
				String[] inputNode = br.readLine().trim().split(" ");
				int row = Integer.parseInt(inputNode[0]);
				int col = Integer.parseInt(inputNode[1]);
				int life = Integer.parseInt(inputNode[2]);
				int dir = Integer.parseInt(inputNode[3]);
				q.offer(new Node(row, col, life, dir));
			}

			answer = 0;
			simulation();
			System.out.println("#" +t + " " +answer);
		}

	}

	private static void simulation() {
		// TODO Auto-generated method stub
		int time = 0;
		while (time < M) {
			time++;
			HashMap<String, LinkedList<Node>> map = new HashMap<>();

			while (!q.isEmpty()) {
				Node curNode = q.poll();
				curNode.row = curNode.row + dirs[curNode.dir][0];
				curNode.col = curNode.col + dirs[curNode.dir][1];

				// 벽
				if (curNode.row == 0 || curNode.row == (N - 1) || curNode.col == 0 || curNode.col == (N - 1)) {
					curNode.life /= 2;
					curNode.dir = changeDir[curNode.dir];
				}
				if(curNode.life != 0) {
					StringBuilder sb = new StringBuilder();
					sb.append(curNode.row + "," + curNode.col);
					String coordKey = sb.toString();
					if (!map.containsKey(coordKey)) {
						map.put(coordKey, new LinkedList<>());
					}
					map.get(coordKey).add(curNode);
				}
				

			}

			// 충돌하는애확인
			for (Map.Entry<String, LinkedList<Node>> entry : map.entrySet()) {
				LinkedList<Node> list = entry.getValue();
				if (list.size() >= 2) {
					int finalDir = 0;
					int maxLife = 0;
					int newRow = 0;
					int newCol = 0;
					int sum = 0;
					for (int i = 0; i < list.size(); i++) {
						if (maxLife < list.get(i).life) {
							maxLife = list.get(i).life;
							finalDir = list.get(i).dir;
						}
						sum += list.get(i).life;
						newRow = list.get(i).row;
						newCol = list.get(i).col;
						//System.out.println("newRow = "+newRow+ " newCol = " + newCol +" finalDir =" +finalDir);
					}

					q.offer(new Node(newRow, newCol, sum, finalDir));

				} else {
					q.offer(list.get(0));
				}
			}

		}
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			answer += node.life;
			//System.out.println("answer = " +answer);
		}
		
	}

}
