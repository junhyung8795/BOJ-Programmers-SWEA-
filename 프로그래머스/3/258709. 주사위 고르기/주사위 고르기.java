import java.util.*;
class Solution {
    static List<Integer> groupA = new ArrayList<>();
    static List<Integer> groupB = new ArrayList<>();
    static int N;
    static int[][] Sdice;
    //답을 담을 리스트.
    static List<Integer> answer;
    static int mostWin = 0;
    
    static List<Integer> groupANumbers;
    static List<Integer> groupBNumbers;
    
    
    public int[] solution(int[][] dice) {
        // int[] answer = new int[3];
        Sdice = dice;
        N = dice.length;
        //주사위를 최대 10C5로 뽑음
        //->212
        //A가 가지는 주사위들로 만들 수 있는 수들의 가짓수 6의 5제곱
        //B가 가지는 주사위들로 만들 수 있는 수들의 가짓수 6의 5제곱
        //각각의 가짓수별로 다 곱해버리면 시간초과
        
        //A와 B가 가지는 숫자들을 모두 구하고
        //정렬한 후 이진탐색을 통해, A의 각 수를 순회하며 A를 넘지못하는 B의 수들의 개수를 구하자.
        backtracking(0);
        
        
        
        
        
        
        int[] returned = new int[N / 2];
        for(int i = 0; i < answer.size(); i++){
            returned[i] = answer.get(i);
        }        
        return returned;
    }

    
    //A, B주사위 정하기 10C5
    public static void backtracking(int startIndex){
        if(groupA.size() == N / 2 && groupB.size() == N / 2){
            //A와 B의 모든 수들의 조합을 구하고 정렬한 후 이진탐색하는 로직
            
            //각 그룹의 모든 숫자 가짓수들을 담는 리스트 초기화
            groupANumbers = new ArrayList<>();
            groupBNumbers = new ArrayList<>();
            //로직 시작
            findNumbersA(0,0);
            findNumbersB(0,0);
            
            // System.out.println(groupANumbers);
            // System.out.println(groupBNumbers);
            groupANumbers.sort((a, b) -> a - b);
            groupBNumbers.sort((a, b) -> a - b);
            int candi = 0;
            for(int i = 0; i < groupANumbers.size(); i++){
                //groupANumbers의 각 수들을 기준으로 이를 넘지않는 groupBNumbers원소들의 갯수를 더해간다.
                candi += binarySearch(i);
            }
            
            if(candi > mostWin){
                mostWin = candi;
                answer = new ArrayList<>();
                for(Integer diceNum: groupA){
                    answer.add(diceNum + 1);
                }
            }
            // System.out.println("answer  = " + answer);
            
            return;
        }
        
        if(groupA.size() < N / 2){
            groupA.add(startIndex);
            backtracking(startIndex + 1);
            groupA.remove(groupA.size() - 1);
        }
        
        if(groupB.size() < N / 2){
            groupB.add(startIndex);
            backtracking(startIndex + 1);
            groupB.remove(groupB.size() - 1);
        }
        
        
        
    }
    
    
    
    public static void findNumbersA(int startIndex, int current){
        if(startIndex == N / 2){
            groupANumbers.add(current);
            return;
        }
        int idx = groupA.get(startIndex);
        
        for(int i = 0; i < 6; i++){
            //현재 주사위에서 가질 수 있는 수들
            //현재 주사위의 인덱스 번호 ->groupANumbers.get(startIndex);
            //Sdice[idx][i]는 각각 현재 주사위가 가지는 숫자들을 의미
            findNumbersA(startIndex + 1, current + Sdice[idx][i]);
        }
        
    }
    
    public static void findNumbersB(int startIndex, int current){
        if(startIndex == N / 2){
            groupBNumbers.add(current);
            return;
        }
        int idx = groupB.get(startIndex);
        for(int i = 0; i < 6; i++){
            //현재 주사위에서 가질 수 있는 수들
            //현재 주사위의 인덱스 번호 ->groupANumbers.get(startIndex);
            //Sdice[idx][i]는 각각 현재 주사위가 가지는 숫자들을 의미
            findNumbersB(startIndex + 1, current + Sdice[idx][i]);
        }
        
    }
    
    public static int binarySearch(int i){
        int standard = groupANumbers.get(i);
        
        int start = 0;
        int end = groupANumbers.size() - 1;
        int possibleIdx = -1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(standard > groupBNumbers.get(mid)){
                possibleIdx = mid;
                start = mid + 1;
                
            } else{
                end = mid - 1;
            }          
            
            
            
            
            
        }
        // System.out.println(groupANumbers);
        // System.out.println(groupBNumbers);
        
        if(possibleIdx == -1){
            return 0;
        } else{
            return possibleIdx + 1;
        }
        
        
        
        
    }
}