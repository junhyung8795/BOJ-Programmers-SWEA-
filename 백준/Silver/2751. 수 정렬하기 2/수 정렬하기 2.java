

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		int[] nArr = new int[T];
		for(int i = 0; i < T; i++) {
			nArr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(nArr);
		for(int i = 0; i < nArr.length; i++) {
			System.out.println(nArr[i]);
		}
					
	}
}
