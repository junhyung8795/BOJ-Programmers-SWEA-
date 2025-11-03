import java.util.*;

class Solution {
    static HashMap<String, Integer> exchangeMap = new HashMap<>();    
    static HashMap<String, Integer> giftScoreMap = new HashMap<>();
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        
        //0. friends를 돌면서 각 사람들의 선물지수를 0으로 초기화
        for(int i = 0; i < friends.length; i++){
            giftScoreMap.put(friends[i], 0);
        }
        
        
        //1. gifts를 순회하면서 선물 교환기록을 확인하고 각 선물교환
        //기록의 횟수와 각 사람의 선물지수를 갱신해준다.
        for(int i = 0; i < gifts.length; i++){
            //선물교환기록 업뎃
            if(!exchangeMap.containsKey(gifts[i])){
                exchangeMap.put(gifts[i], 0);
            }
            exchangeMap.replace(gifts[i], exchangeMap.get(gifts[i]) + 1);
            
            
            //선물 지수 업데이트
            //받으면 감소하고 주면 증가한다.
            //받는 사람과 주는 사람 정의
            String[] people = gifts[i].split(" ");

            String giver = people[0];
            String accepter = people[1];
            

            giftScoreMap.replace(giver, giftScoreMap.get(giver) + 1);
            giftScoreMap.replace(accepter, giftScoreMap.get(accepter) - 1);

            
        }
        // System.out.println("exchangeMap =  " + exchangeMap);
        // System.out.println("giftScoreMap = " +giftScoreMap);
        
        //한 사람이 받을 선물의 수는 다른 모든 친구들과의 선물교환 기록과 선물지수들을 비교해서 찾아야한다.
        //그러면 최소 한 사람을 계산할 때는 다른 모든 친구들을 순회하므로 모두 고려하면 50^2
        for(int i = 0; i < friends.length; i++){
            String curPerson = friends[i];//현재 고려하는 사람
            int totalAccept = 0;//curPerson이 받을 총 선물 수
            
            
            
            //curPerson을 기준으로 다른 모든 사람들을 보고 누가 나에게 선물을 줄지 검사
            //1. 일단 교환기록이 있으면 교환기록을 본다.
            for(int j = 0; j < friends.length; j++){
                String comparedPerson = friends[j];
                if(curPerson.equals(comparedPerson)){
                    continue;//같은 사람은 비교안하고 넘긴다.
                }
                
                
                
                //1.해당 사람과 선물교환 기록이 있는지 검사.
                StringBuilder sb1 = new StringBuilder();
                sb1.append(curPerson + " " + comparedPerson);
                String SB1 = sb1.toString();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(comparedPerson + " " + curPerson);
                String SB2 = sb2.toString();
                
                
                int giveCnt = 0; // curPerson이 comparedPerson에게 준 횟수
                int acceptCnt = 0; // 받은 횟수
                if(exchangeMap.containsKey(SB1)){
                    giveCnt += exchangeMap.get(SB1);
                }
                if(exchangeMap.containsKey(SB2)){
                    acceptCnt += exchangeMap.get(SB2);
                }
                
                //그런데 giveCnt acceptCnt 둘다 0이라면 교환기록이 없는것
                //그리고 둘이 같은 경우도 있는데, 이경우는 선물지수를 비교해서 
                //누가 누구에게 선물을 줄지 결정
                //이름이 있기만 하고 선물 교환에 아예 관여하지 않을 수도 있나..?
                if((giveCnt == 0 && acceptCnt == 0) ||  (giveCnt == acceptCnt)){    
                    if(giftScoreMap.containsKey(curPerson) && giftScoreMap.containsKey(comparedPerson) && (giftScoreMap.get(curPerson) > giftScoreMap.get(comparedPerson))){
                        //이 경우엔 curPerson이 받음.
                        //giftScoreMap은 gifts를 돌면서 초기화했으니 아예 선물기록이 없으면
                        //선물지수가0인데, 이대로면 0인 상대에게 선물을 못받음
                        totalAccept += 1;
                    }
                } else {
                    //그게 아닌 경우엔 giveCnt가 acceptCnt보다 크면 하나 받는다.
                    if(giveCnt > acceptCnt){
                        totalAccept += 1;
                    }
                }
                
                
                
            }
            
            
            //모든 루프를 다 돌았으면 해당 사람이 받는 모든 선물의 수가 고려된 것.
            //이 선물의 수를 answer와 비교해서 최댓값으로 갱신
            answer = Math.max(answer, totalAccept);
            
        }
        
        
        
        
        
        
        
        return answer;
    }
}