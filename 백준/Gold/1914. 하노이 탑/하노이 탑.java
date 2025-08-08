

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	static BigInteger answer ;
	static ArrayList<Integer> dq = new ArrayList<>();

	public static void move(int quantity, int depart, int arrive) {
		if(quantity == 1) {
			
//			System.out.println(depart + " " + arrive);	
			dq.add(depart);
			dq.add(arrive);
			return;
		}
		
		move(quantity - 1, depart, 6 - arrive - depart);
		dq.add(depart);
		dq.add(arrive);
		move(quantity - 1, 6 - arrive - depart, arrive);
		return;
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		
		
		
		answer = new BigInteger("2");
		answer = answer.pow(N).subtract(new BigInteger("1"));
		System.out.println(answer);
	
		if(N <= 20) {
			move(N, 1, 3);
			
			for(int i = 0; i < dq.size() - 1; i += 2) {
				int depart = dq.get(i);
				int arrive = dq.get(i + 1);
				System.out.println(depart + " " + arrive);	
			}	
		}
		
		
	}
}
