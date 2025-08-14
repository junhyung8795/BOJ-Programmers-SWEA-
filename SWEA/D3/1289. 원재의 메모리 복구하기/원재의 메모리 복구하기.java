
import java.util.*;
import java.io.*;

class Solution {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		sc.nextLine();
		for(int t = 1; t <= T; t++) {
			String memoryStr = sc.nextLine();
			int[] memoryBit = new int[memoryStr.length()];
			for(int  i  = 0; i < memoryStr.length(); i++) {
				memoryBit[i] = Integer.parseInt(memoryStr.charAt(i) + "");
			}
			
			
			//초기화된 문자열
//			System.out.println("memoryBit = " + Arrays.toString(memoryBit));
			int[] zeroBit = new int[memoryBit.length];
			//각 문자를 순회하면서 만약에 원본 문자열의 문자와 다르면 
			//현재값에 반대값으로 설정하고 해당 인덱스 뒤에 있는 것도 전부 바꾼다.
			int answer = 0;
			for(int i = 0; i <  memoryBit.length; i++) {
				if(zeroBit[i] != memoryBit[i]) {
					for(int  j =i; j <  memoryBit.length; j++) {
						zeroBit[j] ^= 1;
						
					}
					answer+= 1;
//					System.out.println(Arrays.toString(zeroBit));
				}
			}
			
			System.out.println("#" + t + " " +answer);
			
			
			
			
			
			
		}
		
	}
	
}