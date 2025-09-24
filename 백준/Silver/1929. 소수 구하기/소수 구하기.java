import java.util.*;
import java.io.*;
 class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputMN = br.readLine().split(" ");
		int M = Integer.parseInt(inputMN[0]);
		int N = Integer.parseInt(inputMN[1]);
		
		//에라토스테네스의 체를 이용하면 M이하의 소수는 for문을 돌려서 소수가 아닌 수를 제외하는 루프를 돌릴때, M의 루트까지의 수만 보면 된다.
		int lastIndex = (int)Math.sqrt(N);
		int[] numbers = new int[N + 1];
		numbers[0] = 1;//0과 1은 소수가 아니다.
		numbers[1] = 1;
		for(int i = 2; i <= lastIndex; i++) {//M부터 N의 루트이하의 정수까지 탐색
			if(numbers[i] == 0) {
				//만약 0이라면 해당 수를 제외하고 해당 수의 배수들을 전부 1로 변경
				for(int j = 2; j * i <= N; j++) {
					numbers[i * j] = 1;//i의 배수들은 모두 1로 만들어 소수가 아님을 표시 
				}
			}
			
		}
		
		
		for(int j = M; j<= N; j++) {
			if(numbers[j] == 0) {
				System.out.println(j);
			}
		}		
	}
}
