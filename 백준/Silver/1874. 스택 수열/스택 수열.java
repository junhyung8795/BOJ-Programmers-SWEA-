

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		int curNum = 1;
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		boolean isOk = true;
		for(int num: nums) {
			if(num >= curNum) {
				while(num >= curNum) {
					stack.push(curNum++);
					sb.append("+ \n");
				}
				stack.pop();
				sb.append("- \n");
			} else {
				if(!stack.isEmpty() && stack.peek() == num) {
					stack.pop();
					sb.append("- \n");
				} else {
					isOk = false;
					break;
				}
				
				
				
				
			}
		}
		
		if(isOk) {
			System.out.println(sb.toString());
		}else {
			System.out.println("NO");
		}
		
	}

}