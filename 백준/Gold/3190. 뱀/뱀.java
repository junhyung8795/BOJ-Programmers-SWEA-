import java.io.*;
import java.util.*;

class Main{
    static int N;
    static int K;
    static int L;
    static int[][] grid;
    static int[][] dirs = { {0,1}, {0,-1}, {1,0}, {-1,0}};
    static int[][] changeDir = {{3,2}, {2,3}, {0,1}, {1,0}};
    static ArrayList<Integer> second = new ArrayList<>();
    static ArrayList<String> order = new ArrayList<>();
    static int time = 0;
    static HashSet<Integer> hsSet = new HashSet<>();
    static class Node{
        int row;
        int col;
        int dir;
        public Node(int row, int col, int dir){
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
        
        @Override
        public String toString(){
            return "row = " + row + " col = " + col;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        
        
        for(int i = 0; i < K; i++){
            //사과 위치
            String[] s = br.readLine().split(" ");
            int row = Integer.parseInt(s[0]) - 1;
            int col = Integer.parseInt(s[1]) - 1;
            grid[row][col] = 1;
        }
        
        L = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < L ;i++){
            String[] s = br.readLine().split(" ");
            second.add(Integer.parseInt(s[0]));
            order.add(s[1]);
        }
        
        startGame();
        
        System.out.println(time);
        
        
        
        
    }
    
    
    public static void startGame(){
        ArrayDeque<Node> dq= new ArrayDeque<>();
        dq.addLast(new Node(0, 0, 0));//맨처음은 오른쪽
        hsSet.add(0);
        int curOrderIndex = 0;
        while(true){
            time++;
            //새지점.
            Node curNode = dq.peekLast();
            int row = curNode.row;
            int col = curNode.col;
            int dir = curNode.dir;
            int newRow = row + dirs[dir][0];
            int newCol = col + dirs[dir][1];
            
            //벽
            if(newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) break;
            //자기자신 부딪힘.
            if(hsSet.contains(newRow * 101 + newCol)) break;
            
            if(grid[newRow][newCol] == 1){
                grid[newRow][newCol] = 0;
                dq.addLast(new Node(newRow, newCol, dir));
                hsSet.add(newRow * 101 + newCol);
            } else{
                Node removed = dq.removeFirst();
                hsSet.remove(removed.row * 101 + removed.col);
                dq.addLast(new Node(newRow, newCol, dir));
                hsSet.add(newRow * 101 + newCol);
            }
            
            
            if(curOrderIndex < L && second.get(curOrderIndex) == time){
                //명령대로 머리를 돌릴시간.
                if(order.get(curOrderIndex).equals("L")){
                    dq.peekLast().dir = changeDir[dq.peekLast().dir][0];    
                }else if(order.get(curOrderIndex).equals("D")){
                    dq.peekLast().dir = changeDir[dq.peekLast().dir][1];
                }
                curOrderIndex++;
            }
            
            
        }
        
        
        
        
        
        
        
        
       
    }
    
    
    
    
    
    
    
    
    
}