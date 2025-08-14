

import java.util.*;
import java.io.*;

class Solution {
	static class BatteryCharge implements Comparable<BatteryCharge> {
		int x;
		int y;
		int power;
		int coverage;

		public BatteryCharge(int x, int y, int power, int coverage) {
			super();
			this.x = x;
			this.y = y;
			this.power = power;
			this.coverage = coverage;
		}

		@Override
		public int compareTo(BatteryCharge o) {

			return o.power - this.power;
		}

	}

	static class Person {
		int x;
		int y;

		public Person(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static ArrayList<BatteryCharge> BCArr;
	static int[][] dirs = { { 0, 0 }, { 0, -1 },  { 1, 0 }, { 0, 1 }, { -1,0 } };
	static ArrayList<Integer> moveOfA;
	static ArrayList<Integer> moveOfB;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			String[] inputTimeAndQuantityOfBC = br.readLine().split(" ");
			int time = Integer.parseInt(inputTimeAndQuantityOfBC[0]);
			int quantityOfBC = Integer.parseInt(inputTimeAndQuantityOfBC[1]);

			String[] inputMoveOfA = br.readLine().split(" ");
			moveOfA = new ArrayList<>();
			moveOfA.add(0);
			for (int i = 0; i < time; i++) {
				moveOfA.add(Integer.parseInt(inputMoveOfA[i]));
			}

			String[] inputMoveOfB = br.readLine().split(" ");
			moveOfB = new ArrayList<>();
			moveOfB.add(0);
			for (int i = 0; i < time; i++) {
				moveOfB.add(Integer.parseInt(inputMoveOfB[i]));
			}
			
			BCArr = new ArrayList<>();
			for (int i = 0; i < quantityOfBC; i++) {
				String[] s = br.readLine().split(" ");
				int x = Integer.parseInt(s[0]);
				int y = Integer.parseInt(s[1]);
				int coverage = Integer.parseInt(s[2]);
				int power = Integer.parseInt(s[3]);
				BCArr.add(new BatteryCharge(x, y, power, coverage));
			}

			int totalCharged = 0;

			Person A = new Person(1, 1);
			Person B = new Person(10, 10);
			int current = 0;
			while (time >= current) {
				A.x = A.x + dirs[moveOfA.get(current)][0];
				A.y = A.y + dirs[moveOfA.get(current)][1];

				B.x = B.x + dirs[moveOfB.get(current)][0];
				B.y = B.y + dirs[moveOfB.get(current)][1];

				PriorityQueue<BatteryCharge> BCforA = new PriorityQueue<>();
				PriorityQueue<BatteryCharge> BCforB = new PriorityQueue<>();
				for (int i = 0; i < BCArr.size(); i++) {
//					BCArr.get(i);에서 A,B가 접근가능한지. 넣기.
					BatteryCharge targetBC = BCArr.get(i);
					// 접근이 가능하다면 각 Bc의 좌표와 A,B의 좌표의 xy값 각각의 차이의 절댓값의 합이 BC.coverage보다 작아야한다.
					if (targetBC.coverage >= (Math.abs(A.x - targetBC.x) + Math.abs(A.y - targetBC.y))) {
						BCforA.add(targetBC);
					}

					if (targetBC.coverage >= (Math.abs(B.x - targetBC.x) + Math.abs(B.y - targetBC.y))) {
						BCforB.add(targetBC);
					}
				}

				// BCforA, BCforB의 맨 앞에 있는 충전소가 A,B가 받을 수 있는 최대파워의 충전소다.
				// 만약 맨앞 충전소가 서로 다르면 그냥 각 BC의 power를 totalCharged에 더한다,.
				// 아니라면 경우의 수를 나눠야한다.

				if (BCforA.peek() != BCforB.peek() || BCforA.peek() == null || BCforB.peek() == null) {
					totalCharged += (BCforA.peek() != null) ? BCforA.peek().power : 0;
					totalCharged += (BCforB.peek() != null) ? BCforB.peek().power : 0;

				} else {
					int commonBCPower = BCforA.peek().power;

					BCforA.poll();
					BCforB.poll();
					int candi1 = commonBCPower / 2 * 2;
					int powerOfA = (BCforA.peek() != null) ? BCforA.peek().power : 0;
					int powerOfB = (BCforB.peek() != null) ? BCforB.peek().power : 0;
					int candi2 = commonBCPower + powerOfA;
					int candi3 = commonBCPower + powerOfB;

					int candi4 = 0;
					candi4 = Math.max(candi4, candi1);
					candi4 = Math.max(candi4, candi2);
					candi4 = Math.max(candi4, candi3);

					totalCharged += candi4;
				}
				
				current++;
				
				

			}
			
			
			System.out.println("#" + t + " " + totalCharged);

		}
	}

}