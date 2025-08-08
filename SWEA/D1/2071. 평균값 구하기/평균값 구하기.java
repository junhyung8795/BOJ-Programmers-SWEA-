

import java.io.*;
import java.util.*;

class Solution{
	public static void main(String[] args){
    	Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for(int t = 0 ; t < N; t++){
        	float total = 0f;
            for(int i = 0; i <10; i++){
            	int num = sc.nextInt();
                total += num;
            }
            float avg = total / 10;
            avg += 0.5;
            
        	System.out.printf( "#" + (t + 1) + " " + (int) avg);
            System.out.println();
        }
        
        
        
    }	
}