import java.util.*;
class Solution {
    static int[] discountRate ;
    static int[] kindOfDiscount = {10, 20, 30, 40};
    static int[][] staticUsers;
    static int[] staticEmoticons;
    static int[] answer;
    public int[] solution(int[][] users, int[] emoticons) {
        
        answer = new int[2];
        staticUsers = users;
        staticEmoticons = emoticons;
        discountRate = new int[emoticons.length];
        
        //각 이모티콘의 할인율 저장
        //백트래킹으로 각 이모티콘의 할인율을 결정
        backtracking(0);
       
            
        
        return answer;
    }
    
    public static void backtracking(int curEmoticonIndex){
        //현재 이모티콘 인덱스가 이모티콘스의 길이랑 같으면 users 순회로직 발동
        if(curEmoticonIndex == staticEmoticons.length){
            
            // System.out.println(Arrays.toString(discountRate));
            List<Integer> result = userLogic();
            // System.out.println(result);
            if(answer[0] < result.get(0)){
                answer[0] = result.get(0);
                answer[1] = result.get(1);
            } else if (answer[0] == result.get(0) && answer[1] < result.get(1)){
                answer[1] = result.get(1);
            }
            return;
        }
        
        //더 찾아야하면 백트래킹으로 할인율을 결정해
        for(int i = 0; i < 4; i++){
            discountRate[curEmoticonIndex] = kindOfDiscount[i];
            backtracking(curEmoticonIndex + 1);
            discountRate[curEmoticonIndex] = 0;
        }
        
        
        
        
        
        
        
        
    }
    
    
    public static List<Integer> userLogic(){
        int candidatePlusPeople = 0;
        int candidateTotalCost = 0;
        for(int i = 0; i < staticUsers.length; i++){
            //각 유저들은 기준 할인율과 기준 가격을 가짐
            
            int standardDiscountRate = staticUsers[i][0];
            int standardCost = staticUsers[i][1];
            
            //모든 이모티콘들을 순회
            int comparedCost = 0;
            
            for(int emoIndex = 0; emoIndex < staticEmoticons.length; emoIndex++){
                //기준 할인율을 넘으면 산다.
                if(discountRate[emoIndex] >= standardDiscountRate){
                    comparedCost += staticEmoticons[emoIndex] * (1 - ((float)discountRate[emoIndex] / 100));
                    // System.out.println((1 - (discountRate[emoIndex] / 100)));
                }
                
            }

            //만약 compareCost가 기준치를 넘기면 안사고 플러스 가입!
            if(comparedCost >= standardCost){
                comparedCost = 0;
                candidatePlusPeople += 1;
            } else{
                candidateTotalCost += comparedCost;
                                // System.out.println("comparedCost = " + comparedCost);

            }
            
            
        }
        // if(discountRate[0] == 30 && discountRate[1] == 40){
        //     System.out.println("candidatePlusPeople  = " + candidatePlusPeople);
        //     System.out.println("candidateTotalCost  = " + candidateTotalCost);
        // }
        List<Integer> resultList = new ArrayList<>();
        resultList.add(candidatePlusPeople);
                resultList.add(candidateTotalCost);

        
        
        
        
        return resultList;
        
        
        
    }
}