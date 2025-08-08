import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 0; t < 10; t++) {
			// 입
			int lengthOfInput = sc.nextInt();
			sc.nextLine();
			char[] chArr = sc.nextLine().toCharArray();

			// chArr를 순회하면서 +를 만나면 numArrList에 잠깐 빼놓고
			// 그다음에 숫자가 오면 숫자를 또 넣고
			// 숫자 뒤의연산자를 만나면? 기존에 큐에 있던 연산자를 numArrList에 넣고
			// 방금 chArr에서 꺼낸 연산자를 operDq에 넣는다.
			// 이후는 반복
			ArrayList<String> numArrList = new ArrayList<>();
			Deque<String> operDq = new ArrayDeque<>();
			for (int i = 0; i < chArr.length; i++) {
				String currentString = chArr[i] + "";
				if (!currentString.equals("+")) {
					numArrList.add(currentString);
				} else {
					if (!operDq.isEmpty()) {
						numArrList.add(operDq.pop());
					}
					operDq.push(currentString);
				}
			}
			while (!operDq.isEmpty()) {
				numArrList.add(operDq.pop());
			}
//			System.out.println(numArrList);

			// numList의 각 수를 순회하면서 스택에 넣는다.
			// 단 연산자를 만날때에는 해당 연산자에 해당하는 연산을
			// 스택에서 두개의 수를 빼면서 연산한다.
			// 만약 123+*이면 123이 스택에 들어가고
			// +를 먼저 만 2+3이 행해진 후
			// 나중에 * 1이 행해져서
			// 원래 식은 1 * (2 + 3)인걸 알 수 있다.
			// 괄호없이도 컴퓨터가 연산자의 우선순위를 알 수 있다.
			Deque<Integer> dq = new ArrayDeque<>();
			for (int i = 0; i < numArrList.size(); i++) {

				if ("+".equals(numArrList.get(i))) {
//					System.out.println(dq);
					Integer first = Integer.valueOf(dq.pop());
					Integer second = Integer.valueOf(dq.pop());

					dq.push(first + second);
				} else {
					dq.push(Integer.valueOf(numArrList.get(i)));
				}
			}
			System.out.println("#" + (t + 1) + " " + dq.pop());
		}
	}
}
