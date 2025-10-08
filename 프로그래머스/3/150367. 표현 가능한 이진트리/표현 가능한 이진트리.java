class Solution {
    static String binaryStrForTest;
    static boolean isOk;
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        // System.out.println(Long.toBinaryString(42L));
        
        
        //이진트리는 이미 0이 맨 뒤에있던 1이 있던 10진수로 변환할 수가 확정된상태
        //그런데 10진수를 이진트리로 변홯할때는 이진수문자열 뒤에 0을 붙이면 수가 불어남.
        //0을 붙여도 앞에 붙여야하는데.
        //이는 더미노드는 맨 앞에서만 붙일 수 있다는 말이다.
        for(int t = 0; t< numbers.length; t++){
            isOk = true;
            String binaryStr = Long.toBinaryString(numbers[t]);
            
            //해당 이진수가 층수로 따지면 몇층인지 알아내야한다.
            //바이너리스트링의 길이를 뛰어넘는 첫 2의 제곱수가 층이다.
            int level = 0;
            while(true){
                if((int)Math.pow(2, level) > binaryStr.length()){
                    break;
                };
                level++;
            }
            // System.out.println(level);
            
            
            //층수를 알아냈으니 층수가 꽉차도록 0을 앞에서부터 채워넣는다. 부족한 분만큼
            int needtoAddedZeros = (int)Math.pow(2, level) - 1 - binaryStr.length();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < needtoAddedZeros; i++ ){
                sb.append("0");
            }
            sb.append(binaryStr);
            
            // System.out.println(sb.toString());
            binaryStrForTest = sb.toString();
            findZero(0, binaryStrForTest.length() - 1, false);
            
            if(isOk){
                answer[t] = 1;
            } else{
                answer[t] = 0;
            }
            
        }
        
        return answer;
    }
    
    public static void findZero(int startIndex, int endIndex, boolean isZeroMid){
        int mid = (startIndex + endIndex) / 2;

        if(isZeroMid && binaryStrForTest.charAt(mid) == '1'){
            isOk = false;
            return;
        }
        if(startIndex == endIndex){//기저조건
            return;
        }
        
        
       if(!isZeroMid && binaryStrForTest.charAt(mid) == '1'){
            findZero(startIndex, mid - 1, isZeroMid);
            findZero(mid + 1, endIndex, isZeroMid);
        } else if(isZeroMid && binaryStrForTest.charAt(mid) == '0'){
            findZero(startIndex, mid - 1, isZeroMid);
            findZero(mid + 1, endIndex, isZeroMid);
        } else if(!isZeroMid && binaryStrForTest.charAt(mid) == '0'){
            findZero(startIndex, mid - 1, !isZeroMid);
            findZero(mid + 1, endIndex, !isZeroMid);
        }
        
        
        
    }
}