import java.io.*;
import java.util.*;

class Main{
    static int N;
    static Parent[] parent;
    static Child[] child;
    static ArrayList<ArrayList<Integer>> level = new ArrayList<>();
    static int root;
    static int[] myCol;
    static int colNum;
    static class Parent{
        int left;
        int right;
        public Parent(int left, int right){
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString(){
            return "parent : " + "left = " + left + " right = " + right;
        }
    }
    static class Child{
        int left;
        int right;
        public Child(int left, int right){
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString(){
            return "child : " + "left = " + left + " right = " + right;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        parent = new Parent[N + 1];
        child = new Child[N + 1];
        myCol = new int[N + 1];

        
        for(int i = 0; i < N; i++){
            String[] s = br.readLine().split(" ");
            int p = Integer.parseInt(s[0]);
            int leftChild = Integer.parseInt(s[1]);
            int rightChild = Integer.parseInt(s[2]);
            child[p] = new Child(leftChild, rightChild);
            if(leftChild != -1)parent[leftChild] = new Parent(-1 ,p);

            if(rightChild != -1)parent[rightChild] = new Parent(p, -1);
        }

        //루트결정
        for(int i = 1; i < N + 1; i++){
            if(parent[i] == null){
                //양쪽 부모가 없으므로 루트
                root = i;
                break;
            }
        }

        //bfs로 각 노드의 레벨 구하기
        bfs();

        // System.out.println(level);
        midOrder(root);
        //중위순회
        // System.out.println(Arrays.toString(myCol));

        int answerWidth = -1;
        int answerLevel = -1;
        for(int i = 1;  i < level.size(); i++){
            level.get(i).sort((a,b) -> Integer.compare(myCol[a], myCol[b]));
            int l = level.get(i).get(0);
            int m = level.get(i).get(level.get(i).size() - 1);
            if(answerWidth < (myCol[m] - myCol[l]) + 1){
                answerWidth = myCol[m] - myCol[l] + 1;
                answerLevel = i;
            }

        }

        System.out.println(answerLevel + " " + answerWidth);
    }

    public static void bfs(){
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        visited[root] = true;
        dq.add(root);
        //넣으면서 방문처리
        level.add(new ArrayList<>());

        int curLevel = 0;
        while(!dq.isEmpty()){
            // System.out.println("dq = " + dq);
            int s = dq.size();
            curLevel += 1;
            level.add(new ArrayList<>());
            for(int i = 0 ; i < s; i++){
                int popped = dq.poll();    
                level.get(curLevel).add(popped);
                if(child[popped]!= null && child[popped].left != -1)dq.add(child[popped].left);
                if(child[popped] != null && child[popped].right != -1)dq.add(child[popped].right);
            }
            

            
        }
        
    }

    public static void midOrder(int start){
        if(child[start].left != -1){
            midOrder(child[start].left);
        }
        colNum++;
        myCol[start] = colNum;
        if(child[start].right != -1){
            midOrder(child[start].right);
        }
    }









    
}