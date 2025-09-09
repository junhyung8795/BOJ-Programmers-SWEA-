
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputNK = br.readLine().split(" ");
		int N = Integer.parseInt(inputNK[0]);
		int K = Integer.parseInt(inputNK[1]);
		
		//1~N 까지의 수를 데크에 넣고 K가 3이면 K-1번인 2번만 dq에서 pollFirst, addLast를 해주고 맨앞에 있는 수 pop하면 3번째 있는 수가 빠지게 된다.
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		
		//데크에 1~N까지의 수 넣기
		for(int i = 1; i <= N; i++) {
			dq.addLast(i);
		}
		
		List<Integer> answerList = new ArrayList<>();
		while(!dq.isEmpty()) {
			//K-1번 dq에서 pollFirst addLast를 한다음 dq에서 pollFirst한 것을 answerList에 넣는다.
			// 가장 먼저 들어온게 가장 먼저 빠저나오므로 큐를 써도 동일하게 풀 수 있다.
			for(int i = 0; i < K - 1;i++) {
				dq.addLast(dq.pollFirst());
			}
			answerList.add(dq.pollFirst());
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		for(int i = 0; i < answerList.size(); i++) {
			sb.append(answerList.get(i) + "");
			if(i != answerList.size() - 1) {
				sb.append(", ");//마지막 요소를 넣을 때는 ", "를 넣지 않는다.
			}
			
		}
		sb.append(">");
		System.out.println(sb.toString());
		
		
	}

}