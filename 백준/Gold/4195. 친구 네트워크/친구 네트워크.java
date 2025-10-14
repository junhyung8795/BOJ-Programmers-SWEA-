import java.util.*;
//import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
	static int[] parent;
	static HashMap<Integer, Integer> network;
	static HashMap<String, Integer> memberID;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 0; t < T; t++) {
        	//친구관계의 수
        	int f = Integer.parseInt(br.readLine());
        	
        	//친구관계가 최대 100000이 나온다면 사람들은 최대 100001명 나올 수 있다. 사람을 1부터 세기 위해 100002크기의 배열 선언
        	parent = new int[f * 2 + 1];
        	for(int i = 0; i < parent.length; i++) {
        		parent[i] = i;
        	}
        	
        	
        	network = new HashMap<>();
        	memberID = new HashMap<>();
        	for(int i = 0; i < f ; i++) {
        		String[] inputAB = br.readLine().split(" ");
        		String a = inputAB[0];
        		String b = inputAB[1];
        		if(!memberID.containsKey(a)) {
        			memberID.put(a,memberID.size() + 1);
        			network.put(memberID.get(a), 1);
        		}
        		if(!memberID.containsKey(b)) {
        			memberID.put(b,memberID.size() + 1);
        			network.put(memberID.get(b), 1);
        		}
        		
        		int parentA = find(memberID.get(a));
        		int parentB = find(memberID.get(b));
        		
        		
        		union(parentA, parentB);
        		
        		System.out.println(network.get(parentB));
//            	System.out.println(network);

        	}
        	
        	
//        	System.out.println(memberID);
        	
        }
        
        
        
        
    }
	private static void union(int a, int b) {
		// TODO Auto-generated method stub
		if(a != b) {//a와 b가 다른 경우에만 유니온
			parent[a] = b;
			network.replace(b, network.get(b) + network.get(a));
			network.remove(Integer.valueOf(a));
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