import java.util.*;
 
 
 
import java.io.*;
 
public class Solution {
    static HashMap<Integer, ArrayList<Integer>> edges;
    static int L;
    static int startNode;
    static int maxDist;
    static int maxNumber;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        for (int t = 1; t <= 10; t++) {
            String[] inputLStart = br.readLine().split(" ");
            L = Integer.parseInt(inputLStart[0]);
            startNode = Integer.parseInt(inputLStart[1]);
 
            edges = new HashMap<>();
            String[] inputEdges = br.readLine().split(" ");
            for (int i = 0; i < L; i += 2) {
                int start = Integer.parseInt(inputEdges[i]);
                int end = Integer.parseInt(inputEdges[i + 1]);
                if (!edges.containsKey(start)) {
                    edges.put(start, new ArrayList<>());
                }
                edges.get(start).add(end);
            }
            maxDist = 0;
            maxNumber = 0;
            bfs();
            System.out.println("#" + t + " " +maxNumber);
 
        }
 
    }
 
    private static void bfs() {
        // TODO Auto-generated method stub
        Queue<int[]> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        for (int i = 0; i < edges.get(startNode).size(); i++) {
            q.add(new int[] { edges.get(startNode).get(i), 1});
            visited.add(edges.get(startNode).get(i));
        }
 
        while (!q.isEmpty()) {
            int[] popped = q.poll();
            int curNodeNum = popped[0];
            int curDistance = popped[1];
            if (curDistance > maxDist) {
                maxDist = curDistance;
                maxNumber = curNodeNum;
            } else if (curDistance == maxDist) {
                if (maxNumber < curNodeNum) {
                    maxNumber = curNodeNum;
                }
            }
             
             
            for(int i = 0; edges.get(curNodeNum) != null && i < edges.get(curNodeNum).size(); i++) {
                if(!visited.contains(edges.get(curNodeNum).get(i))) {
                    q.add(new int[] { edges.get(curNodeNum).get(i), curDistance + 1 });
                    visited.add(edges.get(curNodeNum).get(i));
                }
            }
        }
 
    }
 
}