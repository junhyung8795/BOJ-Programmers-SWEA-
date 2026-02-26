import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static int M;
    static int[][] countGrid = new int[501][501];
    static int[][] grid = new int[501][501];
    static int[][] dirs = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};
    static class Node{
    	int x;
    	int y;
    	int life;
    	public Node(int x, int y, int life) {
    		this.x = x;
    		this.y = y;
    		this.life = life;
    	}
    	
    	@Override
    	public String toString() {
			return "x = " + x + "y = " + y + "life = " + life;
    		
    	}
    	
    	
    }
    static int answer = 0;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	//N input
    	N = Integer.parseInt(br.readLine());
    	for(int i = 0; i < N; i++) {
    		String[] s = br.readLine().split(" ");
    		int X1 = Integer.parseInt(s[0]);
    		int Y1 = Integer.parseInt(s[1]);
    		int X2 = Integer.parseInt(s[2]);
    		int Y2 = Integer.parseInt(s[3]);
    		
    		int maxX = Math.max(X2, X1);
    		int minX = Math.min(X2, X1);
    		int maxY = Math.max(Y2, Y1);
    		int minY = Math.min(Y2, Y1);
    		for(int y = minY ; y <= maxY; y++) {
    			for(int x = minX; x <= maxX; x++) {
    				grid[y][x] = 1; 
    			}
    		}
    	}
    	
    	//M input
    	M = Integer.parseInt(br.readLine());
    	for(int i = 0; i < M; i++) {
    		String[] s = br.readLine().split(" ");
    		int X1 = Integer.parseInt(s[0]);
    		int Y1 = Integer.parseInt(s[1]);
    		int X2 = Integer.parseInt(s[2]);
    		int Y2 = Integer.parseInt(s[3]);
    		
    		int maxX = Math.max(X2, X1);
    		int minX = Math.min(X2, X1);
    		int maxY = Math.max(Y2, Y1);
    		int minY = Math.min(Y2, Y1);
    		for(int y = minY ; y <= maxY; y++) {
    			for(int x = minX; x <= maxX; x++) {
    				grid[y][x] = 2; 
    			}
    		}
    	}
//    	
//    	for(int row = 0; row < 501; row ++) {
//    		System.out.println(Arrays.toString(grid[row]));
//    	}
    	
    	
    	//0,0 daikstra to 500,500
    	for(int row = 0; row < 501; row++) {
    		Arrays.fill(countGrid[row], Integer.MAX_VALUE);
    	}
    

    	daikstra();
    	
    	System.out.println(answer);
    	
    }
    
    public static void daikstra() {
    	
    	//힙큐
    	PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return o1.life - o2.life;
			}
    		
    	}); 
    	pq.add(new Node(0, 0, 0));
    	
    	while(!pq.isEmpty()) {
    		Node popped = pq.poll();
    		int curX = popped.x;
    		int curY = popped.y;
    		int curLife = popped.life;
//    		System.out.println(popped);
    		if(countGrid[curY][curX] <= curLife) {
//    			System.out.println("sadasd");
    			continue;
    		}
    		if(curX == 500 && curY == 500) {
    			answer = curLife;
    			return;
    		}
    		countGrid[curY][curX] = curLife;
    		
    		//연결된 지점으로 이동
    		//존재하는 격자이고 이동했을 때 칸의 countGrid가 더 적은 수면 이동 + 죽음의 칸이 아니라면!
    		for(int i = 0; i < 4; i++) {
    			int nextX = curX + dirs[i][1];
    			int nextY = curY + dirs[i][0];
//    			System.out.println("gogo");
    			if(nextX >= 0 && nextX < 501 && nextY >= 0 && nextY < 501) {
    				
    				if(grid[nextY][nextX] != 2) {
    					int nextLife = curLife;
    					if(grid[nextY][nextX] == 1) {
    						nextLife += 1;
    					}
    					if(countGrid[nextY][nextX] > nextLife) {
    						pq.add(new Node(nextX, nextY, nextLife));
    					}
    					
    				}
    				
    				
    				
    				
    			}
    		}
    		
    		
    		
    	}
    	
    	//루프 다돌아도 못찾앗다면 답은 -1
    	answer = -1;
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
}
