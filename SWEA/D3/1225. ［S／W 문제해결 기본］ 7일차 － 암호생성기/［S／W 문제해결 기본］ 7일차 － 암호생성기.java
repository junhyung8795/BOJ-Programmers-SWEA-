
import java.util.*;
import java.io.*;

class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1; t <= 10; t++) {
			//테케 번호와 숫자열 입력 받기
			int a = Integer.parseInt(br.readLine());
			String[] s  = br.readLine().split(" ");
			
			//Queue에 s에 해당하는 숫자를 저장
			Queue<Integer> q = new LinkedList<>();
			for(int i = 0; i < s.length; i++) {
				q.add(Integer.parseInt(s[i]));
			}
			
			
			//로직중 0이 나올 때까지 무한루프
			//1사이클만으로 0이 나오리라는 보장이 없으니
			//한사이클을 단위로 무한루프를 돌린다.
			label:
			while(true) {
				for(int i = 1; i <= 5; i++) {
					int num = q.peek();
					num -= i;//i 가 1,2,3,4,5까지 증가하면서 빼는 수가 커진다.
					//num이 만약 음수가 되면 0으로 초기화한다.
					if(num <= 0) {
						num = 0;
					}
					q.poll();
					q.add(num);
					
					//위에서 num을 넣었을 때 num이 0인상태라면
					//루프를 끊어야한다.
					if(num == 0) {
						break label;	
					}
					
				}
			}
			
			StringBuilder sb = new StringBuilder();
			int sizeOfQ = q.size();
			for(int i = 0; i < sizeOfQ; i++) {
				sb.append(q.poll() + " ");
			}
			System.out.println("#" + t +" " +  sb.toString());
			
			
			
			
			
			
			
			
			
			
			
			
		}
	}
}