import java.util.*;

class Solution {
    static int N;
    static int minIntensity = Integer.MAX_VALUE;
    static int minOfMountain = Integer.MAX_VALUE;
    static HashMap<Integer, ArrayList<Edge>> edgeMap = new HashMap<>();
    static HashSet<Integer> gateSet = new HashSet<>();
    static HashSet<Integer> mountainSet = new HashSet<>();
    static class Edge{
        int to;
        int weight;
        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public String toString(){
            return "to = " + to + " weight = " + weight;
        }
    }
    static class Node{
      
        int current;
        int maxWeight;
        public Node(int current, int maxWeight){
        
            this.current = current;
            this.maxWeight = maxWeight;
        }
        @Override
        public String toString(){
            return "current = " + current + " maxWeight = " + maxWeight;
        }
    }
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        N = n;
        //path탐색하면서 간선정보를 edgeMap에 저장
        for(int i = 0; i < n; i++){
            edgeMap.put(i + 1, new ArrayList<>());
        }
        
        for(int k = 0; k < paths.length; k++){
            int i = paths[k][0];
            int j = paths[k][1];
            int w = paths[k][2];
            
            edgeMap.get(i).add(new Edge(j, w));
            edgeMap.get(j).add(new Edge(i, w));
        }
        
        
        
        
        for(int gate :  gates){
            gateSet.add(gate);
        }
        for(int summit: summits){
            mountainSet.add(summit);
        }
        
        //특정노드에 도달할 때, 여태까지 가중치의 합들 중 최소로 오는게 아닌
        //여태까지 가중치들의 가짓수들 중 최대를 각 노드가 끌고 다니다가
        //dist[]배열에 출입구에서 각 지점까지오는 각 경로들의 최대 가중치들중 최솟값을 저장한다.
        daikstra();
        
        
        answer[0] = minOfMountain;
        answer[1] = minIntensity;
        return answer;
    }
    
    
    
    public static void daikstra(){
        //dist배열 정의
        //출입구에서 각 지점으로 가는 모든 경로들이 가지는 최댓값들 중 최솟값을 저장.
        //만약 이 최솟값보다 큰 값으로 해당 지점을 방문하려한다면 그냥 continue로 로직을 건너뛰기
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(Node a, Node b){
                return a.maxWeight - b.maxWeight;
            }   
        });
        for(int gate : gateSet){
            //각 시작점들을 모두 pq에 넣어준다.
            pq.add(new Node(gate, 0));
            // dist[gate] = 0;
        }
        
        
        //가장 작은 maxWeight를 가진 애들을 우선으로 뽑았을 때,
        //봉우리에 가장 먼저 도달하는 케이스가 가장 intensity가 작은 경우일 것이고 이때의 intensity와
        //산봉우리 번호를 저장한디.
        while(!pq.isEmpty()){
            Node popped = pq.remove();
            int curPos = popped.current;
            int curMaxWeight = popped.maxWeight;
           
            if(dist[curPos] <= curMaxWeight){
                continue;
            }
            dist[curPos] = curMaxWeight;
            
            //봉우리를 찾았을 때 로직
            if(mountainSet.contains(curPos)){
                //만약 minIntensity가 Integer.MAX_VALUE라면 갱신
                if(minIntensity == Integer.MAX_VALUE){
                    minIntensity = Math.min(minIntensity, dist[curPos]);    
                    minOfMountain = Math.min(minOfMountain, curPos);
                    continue;
                } else {
                    //만약 minIntensity가 한번 이상 갱신된 상태라면
                    
                    //더 작은 봉우리번호가 나오면 봉우리 번호를 갱신하고
                    if(minIntensity == curMaxWeight){
                        if(curPos < minOfMountain){
                            minOfMountain = curPos;    
                        }
                        continue;
                    } else{
                        //이미 한번 갱신된 상태라면 이 조건에서는 무조건 minIntensity < curMaxWeight인 상황
                        return;
                        
                    }
                    //minIntensity보다 큰 수가 curMaxWeight로 나오면 그때 리턴
                    
                }
                
                
            }
                
            for(Edge edge: edgeMap.get(curPos)){
                
                if(Math.max(dist[curPos], edge.weight) >= dist[edge.to] ||gateSet.contains(edge.to) ){
                    continue;
                } else{
                    pq.add(new Node(edge.to, Math.max(dist[curPos], edge.weight)));
                }
            }
            
            
            
        }
        
        
        
        
    }
}