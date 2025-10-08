import java.util.*;

class Solution {
    static HashSet<Character> charSet = new HashSet<>(); 
    static HashMap<String, Integer> map =  new LinkedHashMap<>();
    
    static String[] staticOrders;
    static int[] staticCourse;
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        staticOrders = orders;
        staticCourse = course;
        
        for(int j = 0; j < orders.length; j++){
            char[] chrArrOrder = orders[j].toCharArray();
            Arrays.sort(chrArrOrder);
            orders[j] = String.valueOf(chrArrOrder);

        }
        for(int i = 0; i < course.length; i++){
            for(int j = 0; j < orders.length; j++){
                backtracking(0, new ArrayList<Character>(), course[i], orders[j]);
            }
        }
        
        // System.out.println(map);
        
        
        List<String> answerList = new ArrayList<>();
        
        for(int i = 0; i < course.length; i++){
            int standardLength = course[i];
            //각 길이별 가장 많이 겹치는 횟수
            int maxCnt = 2;
            //그리고 그 겹치는 횟수를 가지는 문자열을 저장할 리스트
            List<String> duplicatedStrList = new ArrayList<>();
            
            
            for(Map.Entry<String, Integer> entry: map.entrySet()){
                if(entry.getKey().length() == standardLength) {
                    //기준 길이랑 같은 경우 maxCnt보다 entry.getValue()가 더 높으면
                    //duplicatedStrList clear하고 maxCnt = entry.getValue(); duplicatedStrList.add(entry.getKey())
                    if(maxCnt < entry.getValue()){
                        duplicatedStrList.clear();
                        maxCnt = entry.getValue();
                        duplicatedStrList.add(entry.getKey());
                    } else if(maxCnt == entry.getValue()){
                        duplicatedStrList.add(entry.getKey());
                    }
                }
            }
            
            for(int k = 0; k < duplicatedStrList.size(); k++){
                answerList.add(duplicatedStrList.get(k));
            }
            
        }
        // System.out.println(answerList);
        
        answer = new String[answerList.size()];
        answerList.sort((a, b) -> a.compareTo(b));
        
        for(int i = 0; i < answerList.size(); i++){
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    
    public static void backtracking(int curIndex, List<Character> current, int limit, String curString){
        if(current.size() == limit){
            StringBuilder sb = new StringBuilder();
            for(int idx = 0; idx < current.size(); idx++){
                sb.append(current.get(idx) + "");
            }
            String keyStr = sb.toString(); 
            if(!map.containsKey(keyStr)){
                map.put(keyStr, 0);
            }
            // System.out.println("limit = " + limit);
            // System.out.println("curString = " + curString);
            // System.out.println("keyStr = " + keyStr);
            map.replace(keyStr, map.get(keyStr) + 1);
        }
//         && sortedCharList.size() - i >= limit
        for(int i = curIndex; i < curString.length() && curString.length() - i >= limit - current.size(); i++){
            current.add(curString.charAt(i));
            backtracking(i + 1, current, limit, curString);
            current.remove(current.size() - 1);
        }
        
        
        
        
    }
}