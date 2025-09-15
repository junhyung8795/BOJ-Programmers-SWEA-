import java.io.*;
import java.util.*;


public class Main {
	static int N;
	static int S;
	static int answer;
	static int[] nums;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputNS = br.readLine().split(" ");
		N = Integer.parseInt(inputNS[0]);
		S = Integer.parseInt(inputNS[1]);
		
		nums = new int[N];
		String[] inputNums = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(inputNums[i]);
		}
		
		
		List<Integer> list = new ArrayList<>();
		answer = 0;
		for(int i = 0 ; i <N ;i++) {
			list.add(nums[i]);
			backtracking(list, i + 1, nums[i]);
			list.remove(list.size() - 1);
		}
		
		System.out.println(answer);
		
	}

	private static void backtracking(List<Integer> list, int startIndex, int sum) {
		if(startIndex == N) {
			
			if(sum == S) {
				answer += 1;
			}
			return;
		}
		
		
		list.add(nums[startIndex]);
		backtracking(list, startIndex + 1 ,sum + nums[startIndex]);
		list.remove(list.size() - 1);
		backtracking(list, startIndex + 1, sum);

		
		
	}
}
