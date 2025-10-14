import java.util.*;
//import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
	static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputNM = br.readLine().split(" ");
        int n = Integer.parseInt(inputNM[0]);
        int m = Integer.parseInt(inputNM[1]);
        
        parent = new int[n + 1];
        
        //makeset
        for(int i = 1; i < parent.length; i++) {
        	parent[i] = i;
        }
        
        //명령어는 m개만큼 주어지므로 루프를 m번
        for(int i = 0; i < m; i++) {
        	String[] command = br.readLine().split(" ");
        	int com = Integer.parseInt(command[0]);
        	int a = Integer.parseInt(command[1]);
        	int b = Integer.parseInt(command[2]);
        	
        	int parentA = find(a);
        	int parentB = find(b);
        	
        	//합집합 연산 -> 유니온파인드
        	if(com == 0) {
        		union(parentA, parentB);
        	} else {
        		//두 원소가 같은 집합에 포함됐는지 확인하는 로직
        		if(parent[a] == parent[b]) {
        			System.out.println("YES");
        		} else {
        			System.out.println("NO");
        		}
        	}
        	
        }
        
        
        
    }

	private static void union(int a, int b) {
		if(a != b) {
			parent[a] = b;
		}
		
	}

	private static int find(int x) {
		// TODO Auto-generated method stub
		if(parent[x] != x){
			return parent[x] = find(parent[x]);
		}
		return x;
	}
}