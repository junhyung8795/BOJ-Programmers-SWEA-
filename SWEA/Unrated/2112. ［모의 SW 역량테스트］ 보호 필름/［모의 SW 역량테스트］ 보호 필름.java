import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
class Solution {
    static int D;
    static int W;
    static int K;
    static int[][] table;
    static int answer;

 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            String[] inputNMK = br.readLine().split(" ");
 
            D = Integer.parseInt(inputNMK[0]);
            W = Integer.parseInt(inputNMK[1]);
            K = Integer.parseInt(inputNMK[2]);
 
            table = new int[D][W];
            for (int row = 0; row < D; row++) {
                String[] inputRow = br.readLine().split(" ");
//              System.out.println(Arrays.toString(inputRow));
                for (int col = 0; col < W; col++) {
                    table[row][col] = Integer.parseInt(inputRow[col]);
                }
            }
 
            answer = D;

           
 

            backtracking(0, 0);
//          
            System.out.println("#" + t + " " +answer);
        }
    }
 
    private static void backtracking(int cnt, int startIndex) {
        // TODO Auto-generated method stub	
        //최대 상금 문제와 헷갈려서 많이 애먹은 문제다.
        //최대 상금처럼 프루닝을 해야하는줄 알고 set에 문자열을 담는다 치고 각 행에 A약을 줬는지, B약을 줬는지를 문자열로 바꿔서
        //문자열이 이미 set에 포함돼있으면 backtracking을 못하게 했는데, 애초에 매 backtracking마다 
        //row를 0부터 D - 1까지 돌리면서 조건식으로 set에 문자열이 포함돼있냐 안돼있냐를 계속 따지기때문에 시간복잡도가 터져버렸다.
        
 		//이 문제는 각 행을 안고를 수도 있고, A약, B약을 바를 수도 있다.
        //각 행에서 3가지 갈래가 뻗어 나가고 최대 깊이는 startIndex가 D가 되는 순간이 최대이다.
        // 주의 할점은 startIndex가 D가 된다고 바로 빠져나가는게 아니라, startIndex = D-1행에 약을 바르고 startIndex + 1을 해서 온거기 때문에
        //D-1행까지의 검사는 안돼있어서 검사를 한 후에 startIndex==D면 리턴해야한다.
        //이렇게하면 부분집합문제가 되는데,
        //최대상금 문제와의 차이는 최대상금 문제는 1과 2번 카드를 처음에 뒤집는 거랑 나중에 뒤집는거랑 카드에 적힌 숫자들의 순서가 다를 수 있어서 depth를 파악해서 depth도 같은데 숫자열 상태도 같으면 프루닝을 해야하지만
        //하지만 이 문제는 각 행마다 3가지의 경우의 수(안바르기, A바르기, B바르기)를 고려하면 된다.
        //그 이유는 만약 0행에 A를 바르고 1행에 B를 바르나, 1행에 B를 바르고 0행에 A를 바르나 같은 경우의 수이다.
        //그럴거면 그냥 0행부터 순서대로 아래로 내려가면서 모든 경우의 수를 고려하는 부분집합처럼 문제를 풀면된다.
        
        if (answer <= cnt) {
            return;
        }
        
        //아래는 현재 각 열에서 연속으로 같은 AB가 K개있는지 확인하는 코드인데, 너무 길어서 메서드로 바꾸는 것도 좋다.
        boolean isOk = true;
 
        for (int col = 0; col < W; col++) {
 
            boolean isColOk = false;
            label: for (int row = 0; row <= D - K; row++) {
                int standard = table[row][col];
                boolean isStandardOk = true;
                for (int i = row + 1; i <= K - 1 + row; i++) {
                    if (standard != table[i][col]) {
                        isStandardOk = false;
                        continue label;
                    }
                }
                isColOk = true;
                break;
            }
 
            if (!isColOk) {
                isOk = false;
                break;
            }
 
        }
 	
        //만약 연속K개가 안나오는 열이 하나라도 있으면 아래 로직 실행.
        if (!isOk) {
            //테이블을 체크한 후 startIndex == D라면 그냥 리턴
            if (startIndex == D)
                return;
            //아니라면 현재 행을 0으로 쭉채운 행 혹은 
            //1로 쭉 채운 행으로 교체할 준비
            int[] prev = table[startIndex];
            int[] medi0 = new int[W];//0으로 쭉 채운 행
            int[] medi1 = new int[W];//1로 쭉 채운 행
 
            for (int i = 0; i < W; i++) {
                medi0[i] = 0;
                medi1[i] = 1;
            }
            //cnt가 그대로인건 약을 안바른 것. 그상태에서 다음 행으로 넘어감.
            backtracking(cnt, startIndex + 1);
 
            table[startIndex] = medi0;
            //0으로 채우고 다음 행
            backtracking(cnt + 1, startIndex + 1);
            //여기서 원복을 안해준 건. 아래에서 어차피 medi1로 덮어씌어지기 때문
            // table[startIndex] = prev;이렇게 써도 결과는 똑같다.
 
            table[startIndex] = medi1;
            //1로 채우고 다음 행
            backtracking(cnt + 1, startIndex + 1);
            //이제는 원복을 해줘야만 한다.
            table[startIndex] = prev;
 
        } else {
            //만약 모든 열이 AB가 연속으로 K개 있다면 그때는 answer
            answer = Math.min(answer, cnt);
            return;
        }
 
    }
}








//이전에 틀리게 풀었던 나의 풀이. 왜 틀렸는지 대조용
//package sfPractice;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//class Solution {
//	static int D;
//	static int W;
//	static int K;
//	static int[][] table;
//	static int answer;
//	static HashSet<String> set;
//	static int[] curArr;
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(br.readLine());
//		for (int t = 1; t <= T; t++) {
//			String[] inputNMK = br.readLine().split(" ");
//			D = Integer.parseInt(inputNMK[0]);
//			W = Integer.parseInt(inputNMK[1]);
//			K = Integer.parseInt(inputNMK[2]);
//			table = new int[D][W];
//			for (int row = 0; row < D; row++) {
//				String[] inputRow = br.readLine().split(" ");
//				for (int col = 0; col < W; col++) {
//					table[row][col] = Integer.parseInt(inputRow[col]);
//				}
//			}
//			answer = Integer.MAX_VALUE;
//			set = new HashSet<>();
//			curArr = new int[D];
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < D; i++) {
//				curArr[i] = 3;
//				sb.append(curArr[i]);
//			}
//			set.add(sb.toString());
//			backtracking(0);
//			System.out.println(answer);
//		}
//	}
//
//	private static void backtracking(int cnt) {
//		
//		if (answer <= cnt) {
//			return;
//		}
//		
//		boolean isOk = true;
//		for (int col = 0; col < W; col++) {
//			boolean isColOk = false;
//			label: for (int row = 0; row <= D - K; row++) {
//				int standard = table[row][col];
//			
//				for (int i = row + 1; i <= K - 1 + row; i++) {
//					if (standard != table[i][col]) {
//					
//						continue label;
//					}
//				}
//				isColOk = true;
//				break;
//			}
//			if (!isColOk) {
//				isOk = false;
//				break;
//			}
//		}
//		if (!isOk) {
//			for (int row = 0; row < D; row++) {
//				if (curArr[row] == 3) {
//					for (int i = 0; i <= 1; i++) {
//						curArr[row] = i;
//						boolean isAllI = true;
//						for (int col = 0; col < W; col++) {
//							if (table[row][col] != i) {
//								isAllI = false;
//							}
//						}
//						if (isAllI) {
//							continue;
//						}
//						StringBuilder sb2 = new StringBuilder();
//						for (int l = 0; l < D; l++) {
//							sb2.append(curArr[l] + "");
//						}
//						if (!set.contains(sb2.toString())) {
//							int[] prev = table[row];
//							int[] medi = new int[W];
//							for (int j = 0; j < W; j++) {
//								medi[j] = i;
//							}
//							table[row] = medi;
//							set.add(sb2.toString());
//							backtracking(cnt + 1);
//							table[row] = prev;
//						}
//						curArr[row] = 3;
//					}
//				}
//			}
//		} else {
//			answer = Math.min(answer, cnt);
//			return;
//		}
//	}
//}