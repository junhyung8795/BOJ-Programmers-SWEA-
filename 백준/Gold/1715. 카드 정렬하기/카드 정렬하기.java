
import java.util.*;
import java.io.*;


class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 우선순위큐 정의
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<>() {
			@Override
			public int compare(Integer a, Integer b) {
				return a - b;
			}
		});

		// 입력
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}

		// 두 카드 뭉치를 묶을 때마다 최대한 작은 개수의 카드 뭉치가 중복되게 더해지게끔해야함
		// 입력을 받자마자 최소힙에 넣고
		// 1. 두번씩 꺼낸 후 두 카드뭉치를 합한다. 그러면 제일 작은 두 카드 뭉치가 더해짐.
		// 2. 합한것을 total에 저장.
		// 3-1. 이후 최소힙에 아무것도 없다면 이미 다 합친 것!
		// 3-2. 만약 최소힙에 카드뭉치(숫자)가 남아있으면 합한 값을 다시 최소힙에 넣고 반복.
		int total = 0;
        //만약 카드 뭉치가 애초에 한개면? 비교할 필요가 없으니 0이 답이된다.
        if(N == 1){
            System.out.println(0);
            return ;
        }
		while (!pq.isEmpty()) {
			int a = 0;
			int b = 0;
			a = pq.remove();
			b = pq.remove();
			

			total += a + b;
			if (!pq.isEmpty()) {	
				pq.add(a + b);
			} else {
				break;
			}
		}
		
		
		
		System.out.println(total);

	}

}
