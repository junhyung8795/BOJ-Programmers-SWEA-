

import java.util.*;
import java.io.*;

class Solution {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		sc.nextLine();
		
		for(int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			int[] arrA = new int[N];
			for(int i = 0 ; i  < N; i++) {
				arrA[i] = sc.nextInt();
			}
			sc.nextLine();
			
			
			int[] arrB = new int[M];
			for(int i = 0 ; i  < M; i++) {
				arrB[i] = sc.nextInt();
			}
			sc.nextLine();
			
			
			int answer = 0;
			if(N <= M) {
				for(int i = 0 ; i <= M - N; i++) {
					int score = 0;
					for(int j = 0; j < N; j++) {
//						System.out.println((i + j) + " " + j);
						score += arrB[i + j] * arrA[j];
					}
					answer = Math.max(answer, score);
				}
				
			} else {
				for(int i = 0 ; i <= N - M; i++) {
					int score = 0;
					for(int j = 0; j < M; j++) {
						score += arrB[j] * arrA[i + j];
					}
					answer = Math.max(answer, score);
				}
				
			}
			
			System.out.println("#" + t + " " + answer);
			
			
			
			
			
			
			
		}
		
		
		
	}
	
}