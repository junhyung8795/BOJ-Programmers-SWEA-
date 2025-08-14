import java.util.*;
import java.io.*;
 
class Solution {
    static int[][] tree;
    static int[] parentTree;
    public static void main(String[] args) throws IOException {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        //입력 T받기
        int T = sc.nextInt();
        sc.nextLine();
         
        for(int t = 1;t <= T; t++) {
            //V, E, A, B(A,B는 공통 조상 찾고자 하는 두 노드)
            int V = sc.nextInt();
            int E = sc.nextInt();
            int A = sc.nextInt();
            int B = sc.nextInt();
            sc.nextLine();
            tree = new int[V + 1][2];//트리의 정점들의 개수와 왼쪽 오른쪽 자식 담는 칸 초기화
            parentTree = new int[V + 1];//각 노드들의 직계부모를 담는 배열
             
            //E 간선개수만큼 부모 자식 순서로 노드 입력
            for(int  i = 0 ; i < E; i++) {
                int parent = sc.nextInt();
                int child = sc.nextInt();
                //자식의 부모를 담는 배열
                parentTree[child] = parent;
                 
                //부모의 자식을 담는 배열
                //해당 부모의 왼쪽 자식이 있으면 왼쪽에 넣고
                //아니라면 오른쪽 자식에 넣는다.
                if(tree[parent][0] == 0) {
                    tree[parent][0] = child;
                }else{
                    tree[parent][1] = child;
                }
            }
            sc.nextLine();
             
            //A의 조상들을 담는 List
            List<Integer> parentsArrOfA = new ArrayList<>();
            //현재 A의 직계부모
            int vertexA =A;
            while(vertexA != 0) {
                parentsArrOfA.add(vertexA);
                vertexA = parentTree[vertexA];
            } 
             
             
            //B의 조상들을 담는 List
            List<Integer> parentsArrOfB = new ArrayList<>();
            //현재 A의 직계부모
            int vertexB = B;
            while(vertexB != 0) {
                parentsArrOfB.add(vertexB);
                vertexB = parentTree[vertexB];
            } 
//          System.out.println("parentsArrOfA = "  + parentsArrOfA);
//          System.out.println("parentsArrOfB = "  + parentsArrOfB);
//          
            //A의 조상들을 기준으로 B의 조상들을 순회하면서
            //공통조상을 찾으면 바로 빠져나오기
            int commonParent = -1;//디폴트로 항상 출발 정점이 1이니 1을 넣는다.
            label:
            for(int a = 0; a < parentsArrOfA.size(); a++) {
                for(int b = 0; b < parentsArrOfB.size(); b++) {
                    if(parentsArrOfA.get(a).equals(parentsArrOfB.get(b)) ) {
                        //두 요소가 최초로 같은 시점이 공통조상 찾은 시점.
                        commonParent = parentsArrOfA.get(a);
                        break label;
                    }
                }
            }
             
            //서브트리의 크기는?
            //commonParent를 기준으로 전위순회
            int result = preOrder(commonParent);
             
            System.out.println("#" + t + " " + commonParent + " " + result);
             
             
        }
         
         
         
    }
    private static int preOrder(int startV) {
        // TODO Auto-generated method stub
        int result = 1;//자기 자신의 개수
        //System.out.println(startV);
        if(tree[startV][0] != 0){
            result += preOrder(tree[startV][0]);
        }
        if(tree[startV][1] != 0){
            result += preOrder(tree[startV][1]);
        }
         
         
        return result;
    }
}
