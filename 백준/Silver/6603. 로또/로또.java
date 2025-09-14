
import java.io.*;
import java.util.*;

public class Main {
	static int[] nums;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String[] inputStr = br.readLine().split(" ");
			if(inputStr.length == 1 && inputStr[0].equals("0")) {
				return;
			}
			int k = Integer.parseInt(inputStr[0]);
			
			nums = new int[inputStr.length - 1];
			for(int i = 1; i <inputStr.length; i++) {
				nums[i - 1] = Integer.parseInt(inputStr[i]);
			}
//			System.out.println(Arrays.toString(nums));
			Arrays.sort(nums);//nums배열 오름차 정렬, 출력할 때 백트래킹 결과를 오름차정렬로 출력하기 위해 
			
			backtracking(new ArrayList<>(), 0, 0);
			System.out.println();
			
		}
	}

	private static void backtracking(List<Integer> arrayList, int cnt, int startIndex) {
		if(cnt == 6) {//6개의 수를 뽑았다는 뜻이므로 arrayList의 요소들을 띄어쓰기로 출력 
			for(int num: arrayList) {
				System.out.print(num +" ");
			}
			System.out.println();
			return;
		}
		
		for(int i = startIndex; i < nums.length; i++) {
			arrayList.add(nums[i]);//해당 요소를 arrayLsit에 추
			backtracking(arrayList, cnt + 1 , i +1);
			arrayList.remove(arrayList.size() - 1);//다음 탐색을 위해 원상태로 복구 
		}
		
	}
}
