import java.util.*;
class Solution {
    static int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    static String[] dirStrings = {"d", "l", "r", "u"};
    static String answer;
    static class Node{
        int row;
        int col;
        int moveCnt;
        List<String> sb;
        public Node(int row, int col, int moveCnt, List<String> sb){
            this.row = row;
            this.col = col;
            this.moveCnt = moveCnt;
            this.sb = sb;
        }
        @Override
        public String toString(){
            return "row = " + row + " col = " + col + " moveCnt = " +moveCnt + " sb = " + sb;
        }
    }
    static int endRow;
    static int endCol;
    static int maxMoveCnt;
    static int maxRow;
    static int maxCol;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        endRow = r;
        endCol = c;
        maxMoveCnt = k;
        maxRow = n;
        maxCol = m;
        answer = "";
        int totalNeedDistance = Math.abs(x - r) + Math.abs(y - c);
        if(totalNeedDistance > k){
            answer = "impossible";
        } else if(totalNeedDistance == k){
            backtracking(x, y, 0, new ArrayList<String>());
        } else if(totalNeedDistance < k){
            if((k - totalNeedDistance) % 2 == 0){
                backtracking(x, y, 0, new ArrayList<String>());
            } else if((k - totalNeedDistance) % 2 != 0){
                answer = "impossible";
            }
        }
        
        return answer;
    }
    
//     public static StringBuilder dfs(int startRow, int startCol){
        
//         ArrayDeque<Node> dq = new ArrayDeque<>();
//         dq.addFirst(new Node(startRow, startCol, 0, new StringBuilder()));
        
//         while(!dq.isEmpty()){
//             Node popped = dq.removeFirst();
//             // System.out.println(dq);
//             int curRow = popped.row;
//             int curCol = popped.col;
//             int curMoveCnt = popped.moveCnt;
//             List<String> curSb = popped.sb;
//             // System.out.println("curSb = " + curSb);
//             if(curRow == endRow && curCol == endCol && curMoveCnt == maxMoveCnt){
//                 //이동횟수 모두 소진 후 도착지점에 있다면 현재 스트링을 리턴해야함.
//                 StringBuilder nsb = new StringBuilder();
//                 for(int i = 0; i < curSb.size(); i++){
//                     nsb.append(curSb.get(i));
//                 }
//                 return curSb;
//             }
            
//             int needDistanceFromNow = Math.abs(curRow - endRow) + Math.abs(curCol - endCol);
//             if(curMoveCnt + needDistanceFromNow > maxMoveCnt){
//                 continue;
//             } 
//             else if(curMoveCnt + needDistanceFromNow == maxMoveCnt){
                
//             } else if(curMoveCnt + needDistanceFromNow < maxMoveCnt){
//                 if((maxMoveCnt - (curMoveCnt + needDistanceFromNow)) % 2 == 0){
                    
//                 } else if((maxMoveCnt - (curMoveCnt + needDistanceFromNow)) % 2 != 0){
//                     continue;
//                 }
//             }
            
            
//             //그게아니라면 dlru순으로 방향을 순회해야함
//             for(int dir = 0; dir < 4; dir++){
//                 int dr = curRow + dirs[dir][0];
//                 int dc = curCol + dirs[dir][1];
//                 // System.out.println(dirStrings[dir]);
//                 //유효성 검사
//                 if(dr >= 1 && dr <= maxRow && dc >= 1 && dc <= maxCol){
//                     //갈 수 있는 횟수를 넘지 않는 조건도 있다.
//                     if(curMoveCnt < maxMoveCnt){
                        
//                         dq.addFirst(new Node(dr, dc, curMoveCnt + 1, new ArrayList<>());
//                         System.out.println("curSb = " + curSb);
//                         // curSb.deleteCharAt(curSb.length() -1);
//                         System.out.println(" after curSb = " + curSb);

//                     }
                    
//                 }
//             }
            
            
            
            
//         }
        
        
        
        
        
//         return new StringBuilder();
        
        
        
//     }
    
    public static void backtracking(int curRow, int curCol, int curMoveCnt, List<String> currentPath){
        
        if(curRow == endRow && curCol == endCol && curMoveCnt == maxMoveCnt){
                //이동횟수 모두 소진 후 도착지점에 있다면 현재 스트링을 리턴해야함.
            StringBuilder nsb = new StringBuilder();
            for(int i = 0; i < currentPath.size(); i++){
                nsb.append(currentPath.get(i));
            }
            answer = nsb.toString();
            return ;
        }
        
        if(answer.length() > 0){
            return;
        }
        
        int needDistanceFromNow = Math.abs(curRow - endRow) + Math.abs(curCol - endCol);
        if(curMoveCnt + needDistanceFromNow > maxMoveCnt){
            return;
        }
        else if(curMoveCnt + needDistanceFromNow == maxMoveCnt){
            
        } else if(curMoveCnt + needDistanceFromNow < maxMoveCnt){
            if((maxMoveCnt - (curMoveCnt + needDistanceFromNow)) % 2 == 0){
                    
            } else if((maxMoveCnt - (curMoveCnt + needDistanceFromNow)) % 2 != 0){
                return;
            }
        }
        
        
        // 그게아니라면 dlru순으로 방향을 순회해야함
        for(int dir = 0; dir < 4; dir++){
            int dr = curRow + dirs[dir][0];
            int dc = curCol + dirs[dir][1];
            // System.out.println(dirStrings[dir]);
            //유효성 검사
            if(dr >= 1 && dr <= maxRow && dc >= 1 && dc <= maxCol){
                //갈 수 있는 횟수를 넘지 않는 조건도 있다.
                if(curMoveCnt < maxMoveCnt){
                    currentPath.add(dirStrings[dir]);
                    backtracking(dr, dc, curMoveCnt + 1,currentPath);
                    currentPath.remove(currentPath.size() - 1);
                                
                }
            }
        }
        
        
        
    }
}