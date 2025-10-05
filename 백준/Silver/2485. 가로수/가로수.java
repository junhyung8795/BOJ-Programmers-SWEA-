
import java.util.*;
import java.io.*;
import java.math.BigInteger;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		List<Integer> numList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			numList.add(Integer.parseInt(br.readLine()));
		}
		
		
		//각 가로수의 위치 정보를 받은 후 간격에 대한 정보들을 따로 저장한다.
		List<Integer> distanceList = new ArrayList<>(); 
		for(int i = 1; i < N; i++) {
			distanceList.add(numList.get(i) - numList.get(i - 1));
		}
		
		
		//현재 간격관 바로다음 간격을 비교해서 최대 공약수를 구한 것을 a라하면  
		//다다음 간격과도 최대공약수를 구해야하는데, 비교대상을 a로 할 것이다.
		//이렇게 하면 각 결과들은 여태까지의 모든 수들을 고려한 최대 공약수가 될 것이다.
		//디폴트로 0번째 간격, 0번째 간격을 고려한 최대 공약수 = distanceList.get(0)라고 놓는다.
		int curGCD = distanceList.get(0);
		
		for(int i = 1; i < distanceList.size(); i++) {
			//유클리드 호제법
			int a = curGCD;
			int b = distanceList.get(i);
			
			while(true){
				if(a % b == 0) {
					curGCD = b;
					break;
				} else {
					int temp = b;
					b = a % b;
					a = temp;
				}
			}
			
		}
//		현재까지 모든 간격을 동일한 간격으로 나눌 수 있는 간격 중 최대 간격 = curGCD
//		System.out.println(curGCD);
		
		//각 간격들을 순회하면서 curGCD로 나눈 몫 - 1을 다 더하면 가로수를 설치해야하는 총 개수가 나옴.
		int total = 0;
		for(int i = 0; i <distanceList.size(); i++) {
			
			total += (distanceList.get(i) / curGCD) - 1;
			
			
		}
		System.out.println(total);
	}

}
