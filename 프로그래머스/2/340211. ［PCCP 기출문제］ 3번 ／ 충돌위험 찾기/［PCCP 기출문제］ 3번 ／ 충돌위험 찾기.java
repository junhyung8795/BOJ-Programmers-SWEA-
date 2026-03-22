import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int maxTime = 0;
        int[][] timeArr = new int[20001][routes.length];
        for(int k = 0; k < routes.length; k++){
            //각 로봇에 대해 로직 수행
            int time = 0;
            for(int i = 1 ; i < routes[k].length; i++){
                //1부터 시작해서 k번 로봇의 이전 위치에서 i번째 포인트까지 1초씩 움직이면서 이동경로를 timeArr에 기록
                int prevPoint = routes[k][i - 1];
                int prevR = points[prevPoint - 1][0];
                int prevC = points[prevPoint - 1][1];
                
                //도착점은 i번째 포인트의 좌표를 알아내야함
                int nextPoint = routes[k][i];
                int nextR = points[nextPoint - 1][0];
                int nextC = points[nextPoint - 1][1];
                
                timeArr[time][k] = prevR * 101 + prevC;
                
                
                //둘의 r, C좌표중 둘중 하나라도 다르면 로직 실행
                while(prevR != nextR || prevC != nextC){
                    time += 1;
                    
                    
                    //만약 r이 다르면
                    if(prevR != nextR){
                        if(prevR > nextR){
                            prevR -= 1;
                        } else{
                            prevR += 1;
                        }
                    } else{
                        //r좌표가 다르면 그제서야 c좌표를 건든다.
                        if(prevC > nextC){
                            prevC -= 1;
                        } else{
                            prevC += 1;
                        }
                        
                    }
                    
                    //당대 시간에 k번째 로봇은 어느 위치에 있는지 저장.
                    timeArr[time][k] = prevR * 101 + prevC;

                }
                
                
            }
            
            //maxTime으로 탐색범위 좁히기
            maxTime = Math.max(maxTime, time);
            
            
            
        }
        
        
        //각 로봇이 몇초대 어디있는지를 파악했다면 그다음은 timeArr를 돌면서 Map을 생성하고 각 좌표당 로봇이 몇대있는지 확인
        
        for(int time = 0; time <= maxTime; time++){
            HashMap<Integer, Integer> map = new HashMap<>();
            
            for(int k = 0; k < timeArr[0].length; k++){
                //k번째 로봇은 어디에 있는지 파악후 Map에 넣는다.
                int coord = timeArr[time][k];
                if (coord > 0){
                map.put(coord, map.getOrDefault(coord, 0) + 1);    
                }
                
            }
            
            for(Map.Entry<Integer, Integer> entry: map.entrySet()){
                int coord = entry.getKey();
                int count = entry.getValue();
                if(count >= 2){
                    answer += 1;
                }
            }
        }
        
        
        
        // for(int i = 0; i < 30; i++){
        //     System.out.println(Arrays.toString(timeArr[i]));
        // }
        
        return answer;
    }
}