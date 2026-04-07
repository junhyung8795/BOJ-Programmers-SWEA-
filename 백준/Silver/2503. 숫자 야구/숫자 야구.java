import java.io.*;
import java.util.*;

class Main{
    static ArrayList<int[]> sbs = new ArrayList<>();
    static ArrayList<String> questions = new ArrayList<>(); 
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
            
        
        for(int i = 0; i < N; i++){
            String[] s = br.readLine().split(" ");

            sbs.add(new int[]{Integer.parseInt(s[1]), Integer.parseInt(s[2])});
            questions.add(s[0]);
            
        }


        //가능한한 모든 수들의 가지수에 대해 대입하면서 스트라이크 볼이 맞는지 판단해본다.
        for(int i = 1; i < 10; i++){
            for(int j = 1; j < 10; j++){
                for(int k = 1; k < 10; k++){
                    if(i != k  && k != j && i != j){
                        boolean result = find(i, j, k);
                        if(result)answer++;
                    }

                    
                }
            }
        }

        System.out.println(answer);
        
        
        
        
    }

    public static boolean find(int i, int j, int k){
        int target = 100 * i + 10 * j + k;
        HashSet<Integer> set = new HashSet<>();
        set.add(i);
        set.add(j);
        set.add(k);
        for(int h = 0; h < questions.size(); h++){
            //question과 각 자리 비교
            int strike = 0;
            int ball = 0;
            for(int t  = 0; t < 3; t++){
                if(set.contains(Integer.parseInt(questions.get(h).charAt(t) + ""))){
                    //자리수도 같으면 스트라이크 아니면 볼
                    if(t == 0){
                        if(i == Integer.parseInt(questions.get(h).charAt(t) + "")){
                            strike++;
                        } else ball++;
                    } else if(t == 1){
                        if(j == Integer.parseInt(questions.get(h).charAt(t) + "")){
                            strike++;
                        } else ball++;
                    } else if(t == 2){
                        if(k == Integer.parseInt(questions.get(h).charAt(t) + "")){
                            strike++;
                        } else ball++;
                    }
                }
            }

            if(sbs.get(h)[0] == strike && sbs.get(h)[1] == ball){
                
            } else{
                return false;
            }


            
        }
        // System.out.println("target = " + target);
        return true;










        
    }
}