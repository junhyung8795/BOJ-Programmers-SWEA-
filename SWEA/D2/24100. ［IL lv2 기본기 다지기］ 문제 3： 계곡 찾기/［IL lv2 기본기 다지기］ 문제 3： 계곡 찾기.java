import java.util.*;
 
 
class Solution {
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, - 1}};
    public static void main (String[] args) {
         
        Scanner sc = new Scanner(System.in);
         
        int T = sc.nextInt();
         
        for(int t = 0; t < T; t++) {
            int answer = 0;
            int N = sc.nextInt();
            sc.nextLine();
            int[][] table = new int[N][N];
             
            for(int i =0 ;i < N; i++) {
                String[] s = sc.nextLine().split(" ");
                for(int j = 0; j < s.length; j++) {
                    table[i][j] = Integer.parseInt(s[j]);
                }
                 
            }
             
             
            for(int row = 0; row < N; row++) {
                for(int col = 0; col < N; col++) {
                    int cnt = 0;
                    for(int i = 0; i < 4; i++) {
                        int dr = row + dirs[i][0];
                        int dc = col + dirs[i][1];
                         
                         
                        //갈수있는지검사
                        //못가는 곳이면 그냥 사방 카운트 + 1
                        //가는 곳인데 만약 현재셀보다 값이 크다면 계곡이 아니다.
                         
                        if(dr >= 0 && dr < N && dc >= 0 && dc < N) {
                            if(table[dr][dc] > table[row][col]) {
                                cnt++;
                            }else {
                                cnt = 0;
                                break;
                            }
                        }
                    }
                    if(cnt == 4) {
                        answer++;
                    }
                }
            }
             
             
             
        System.out.println("#" + (t + 1) + " " +answer);    
             
             
             
             
        }
         
    }
}