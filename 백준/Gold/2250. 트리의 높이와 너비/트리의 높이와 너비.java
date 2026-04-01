import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static Child[] graph;
    static Parent[] parents;
    static int root;
    static ArrayList<Integer>[] level;
    static int[][] cntLeftRight;
    static int[] myCol;
    static class Child{
        int left;
        int right;
        public Child(int left, int right){
            this.left = left;
            this.right = right;
        }
        @Override
        public String toString(){
            return "Child " +"left = " + left + " right = " + right;
        }
    }
    static class Parent{
        int left;
        int right;
        public Parent(int left, int right){
            this.left = left;
            this.right = right;
        }
        @Override
        public String toString(){
            return "Parent " + "left = " + left + " right = " + right;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new Child[N + 1];
        parents = new Parent[N + 1];
        for(int i = 1; i <= N;i++){
            graph[i] = new Child(-1, -1);
            parents[i] = new Parent(-1,-1);
        }
        level = new ArrayList[N + 1];
        cntLeftRight = new int[N + 1][2];
        myCol = new int[N + 1];
        
        for(int i = 1; i <= N; i++){
            String[] s = br.readLine().split(" ");
            int parent = Integer.parseInt(s[0]);
            int left = Integer.parseInt(s[1]);
            int right = Integer.parseInt(s[2]);
            graph[parent] = new Child(left, right);
            if(left != -1)parents[left] = new Parent(-1, parent);
            if(right != -1)parents[right] = new Parent(parent, -1);
            level[i] = new ArrayList<>();
        }

        //1. bfs로 레벨 알기
        //루트노드 찾기
        for(int i = 1; i <= N; i++){
            //왼, 오 부모가 없으면 그 노드가 루트
            if(parents[i].left == -1 && parents[i].right == -1){
                root = i;
                break;
            }
        }
        bfs();
        cntChild(root);
        //본인 좌표 채우기
        for(int i = 1; i <= N; i++){
            //자신의 오른쪽 부모가 있으면
            int rightParent = parents[i].right;
            int leftParent = parents[i].left;
            if(rightParent != -1){
                
                myCol[i] = N - (returnAllRight(rightParent, 1) + cntLeftRight[i][1]);
            } else if(leftParent != -1){//자신의 왼쪽 부모가 있으면
                myCol[i] = returnAllLeft(leftParent, 0) + cntLeftRight[i][0] + 1;
            }
            
        }


        //각 레벨에서 제일 col이 큰 수와 작은 수를 찾는다.
        int answerLevel = 1;
        int answerWidth = 1;
        for(int i = 2; i<=N; i++){
            if(level[i].size() == 0) break;
            level[i].sort((a,b) -> Integer.compare(myCol[a], myCol[b]));
            // System.out.println("front = " + myCol[level[i].get(level[i].size() - 1)] + " back = " + myCol[level[i].get(0)]);
            int candiWidth = myCol[level[i].get(level[i].size() - 1)] - myCol[level[i].get(0)] + 1;
            if(candiWidth > answerWidth ){
                answerWidth = candiWidth;
                answerLevel = i;
            }
        }
        System.out.println(answerLevel +" " + answerWidth);
        
    }

    public static void bfs(){
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(root);
        boolean[] visited = new boolean[N + 1];
        visited[root] = true;
        int levelCnt = 1;
        while(!dq.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            int a = dq.size();
            for(int i = 0; i < a; i++){
                int curNode = dq.poll();
                list.add(curNode);
                level[levelCnt].add(curNode);
            }
            for(int i = 0; i < list.size(); i++){
                //list안의 요소들의 왼오 자식들을 넣는다.
                if(graph[list.get(i)].left != -1){
                    dq.add(graph[list.get(i)].left);
                }
                if(graph[list.get(i)].right != -1){
                    dq.add(graph[list.get(i)].right);
                }
            }
            levelCnt++;
        }
    }

    public static int cntChild(int start){
        int left = 0;
        int right = 0;
        if(graph[start].left != -1){
            left = cntChild(graph[start].left);
        }
        if(graph[start].right != -1){
            right = cntChild(graph[start].right);
        }
        cntLeftRight[start][0] = left;
        cntLeftRight[start][1] = right;
        
        return 1 + left + right;
    }

    public static int returnAllRight(int start, int from){
        int total = 0;
        if(from == 1){
            total += 1;
            //from 1이면 올라오는 방향 오른쪽. 
            //0이면 올라오는 방향 왼쪽.
            total += cntLeftRight[start][1]; 
        }
        if(parents[start].right != -1){
            total += returnAllRight(parents[start].right, 1);
        }else if(parents[start].left != -1){
            total += returnAllRight(parents[start].left, 0);
        }   
        
        
        return total;
    }

    public static int returnAllLeft(int start, int from){
        int total = 0;
        if(from == 0){
            total += 1;
            //from 1이면 올라오는 방향 오른쪽. 
            //0이면 올라오는 방향 왼쪽.
            total += cntLeftRight[start][0]; 
        }
        if(parents[start].right != -1){
            total += returnAllLeft(parents[start].right, 1);
        }else if(parents[start].left != -1){
            total += returnAllLeft(parents[start].left, 0);
        }   
        
        return total;
    }
  
}