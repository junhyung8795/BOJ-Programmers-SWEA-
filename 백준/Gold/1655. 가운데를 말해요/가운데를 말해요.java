

import java.util.*;
import java.io.*;
import java.math.BigInteger;

//매회차 중간값을 얻어내는 문제 
//매번 숫자를 추가하면서 중간값을 얻기 위해
//1. 입력을 받으면서 우선순위큐에 (현재숫자들의 개수 - 1) / 2번 만큼 수들을 빼고
//우선순위큐 가장 위에 있는 수가 중간값이 되는데/
//시간복잡도를 계산하면 (0log0 + 0log0 +1log1 +1log1 +2log2 +2log2....(10^5 - 1) / 2 * log((10^5 - 1) / 2)+ (10^5 - 1) / 2 * log((10^5 - 1) / 2))이된다.
//시간복잡도 터짐.

//2. 우선순위큐를 두개 만들어서
// 최대힙 ---- 최소힙이 있어서 최대힙안의 숫자개수가 최소힙안의 숫자개수와 같으면 최대힙에
// 아니라면 최소힙에 숫자를 넣는다.
//그 후 항상 최대힙에 peek을 하면 번거롭게 넣고 빼는 작업이 아예 없어진다. 시간복잡도 -> 10^5log(10^5) ~ 10^5 * 16와 유


class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq1 = new PriorityQueue<>((a, b) -> b - a);
		PriorityQueue<Integer> pq2 = new PriorityQueue<>((a, b) -> a - b);

		for(int i = 1; i <= N; i++) {
			int target = Integer.parseInt(br.readLine());
			if(pq1.size() == pq2.size()) {
				pq1.add(target);
			} else {
				pq2.add(target);
			}
			
			
			//단 주의점.
			// 4 4 5 6 7 이렇게 넣으면
			//5  4  7    -----    4   6이렇게 들어간다.
			//들어가는 숫자의 대소에 따라서 조건이 필요한데
			//최대힙에 들어가는 숫자들은 모두 최소힙에 들어가는 숫자보다 작아야 말이된다!
			//숫자를 넣는 매회차마다 최대힙의 peek와 최소힙의 peek을 확인해서 최소힙의 peek이 더 작은 상태라면 peek된 두 수들을 반대편 우선순위큐에 넘겨줘야한다.
			if(!pq1.isEmpty() && !pq2.isEmpty()) {
				if(pq1.peek() > pq2.peek()) {
					int pq1Popped = pq1.remove();
					int pq2Popped = pq2.remove();
					pq1.add(pq2Popped);
					pq2.add(pq1Popped);
				}
			}
			System.out.println(pq1.peek());
			
			
			
		}			

	}

}
