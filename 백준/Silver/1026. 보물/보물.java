

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		int[] arr1 = new int[N];
		int[] arr2 = new int[N];
		for (int i = 0; i < N; i++) {
			arr1[i] = sc.nextInt();
		}
		sc.nextLine();
		for (int i = 0; i < N; i++) {
			arr2[i] = sc.nextInt();
		}

		Arrays.sort(arr1);
		Arrays.sort(arr2);
		int total = 0;
		for (int i = 0; i < N; i++) {
			total += arr1[i] * arr2[N - 1 - i];
		}
		System.out.println(total);
	}
}
