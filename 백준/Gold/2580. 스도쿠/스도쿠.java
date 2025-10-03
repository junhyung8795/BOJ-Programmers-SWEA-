

import java.util.*;
import java.io.*;

class Main {
	static int[][] table;
	static HashMap<Integer, HashSet<Integer>> rowMap;
	static HashMap<Integer, HashSet<Integer>> colMap;
	static HashMap<String, HashSet<Integer>> areaMap;
	static List<Node> blankList;
	static boolean alreadyFound;
	static class Node{
		int row;
		int col;
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		@Override
		public String toString() {
			return "row = " +row +" col = " +col;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		table = new int[9][9];
		rowMap = new HashMap<>();
		colMap = new HashMap<>();
		areaMap = new HashMap<>();
		blankList = new ArrayList<>();
		alreadyFound = false;
		for(int i = 0; i < 9; i++) {
			//각 행에는무슨 숫자가 들어있는지 저장
			rowMap.put(i, new HashSet<>());
			
			//각 열에는 무슨 숫자가 들어있는지 저장 
			colMap.put(i, new HashSet<>());
		}
		
		for(int i = 0; i < 3;i++) {
			for(int j = 0; j < 3; j++) {
				//각 정사각형 구역에는 무슨 숫자들이 들어있는지 저장 
				areaMap.put(String.valueOf(i +", " +j), new HashSet<>());
			}
		}
		// 스도쿠 테이블 초기화
		for (int row = 0; row < 9; row++) {
			String[] inputRow = br.readLine().split(" ");
			for (int col = 0; col < 9; col++) {
				table[row][col] = Integer.parseInt(inputRow[col]);
				
				//해당 칸이 채워져있을 때 로직 
				if(table[row][col] != 0) {
					//테이블을 초기화하면서 각 행과 열에 table[row][col]라는 숫자가 있음을 추가 
					rowMap.get(row).add(table[row][col]);
					colMap.get(col).add(table[row][col]);
					
					//해당 구역에도 table[row][col]가 있음을 추가 
					areaMap.get(String.valueOf((row / 3) +", " + (col / 3))).add(table[row][col]);
				} else {
					//해당 칸이 비워져있을 때, 백트래킹으로 순회할 빈칸의 좌표들을 리스트에 넣는다.
					blankList.add(new Node(row, col));
				}
				
			}
		}
		
		
		backtracking(0);
		
		
		
		
		
		
	}
	private static void backtracking(int curIndex) {
		// TODO Auto-generated method stub
		if(alreadyFound) {
			//이미 경우의 수를 찾았으면 더 탐색할 필요가 없다. 
			return;
		}
		if(curIndex == blankList.size() && !alreadyFound) {
			//모든 빈칸들이 다 채워진 경우 그리고 아직 완벽히 테이블을 채운 경우를 못찾은 상태라면?
			// 완벽히 채운 경우의 수가 있음을 표시하고 테이블을 프린트하고 함수 종료
			alreadyFound = true;
			StringBuilder sb = new StringBuilder();
			
			for(int row = 0; row < 9; row++) {
				
				for(int col = 0; col <9; col++) {
					sb.append(table[row][col] + "");
					if(col != 8) {
						sb.append(" ");
					}else if(col == 8) {
						sb.append("\n");
					}
				}
				
				
			}
			System.out.println(sb.toString());
			return;
			
		}
		
		//현재 검사하고 있는 빈칸의 행 열 정보 가져오기
		Node curRowCol = blankList.get(curIndex);
		int curRow = curRowCol.row;
		int curCol = curRowCol.col;
		
		
		//1부터 9까지 수를 검사
		for(int num = 1; num <= 9; num++) {
			boolean rowResult = rowTest(curRow, num);
			boolean colResult = colTest(curCol, num);
			boolean areaResult = areaTest(curRow, curCol, num);
			
			//모든 검사 통과시 다음 빈칸 좌표를 탐색하도록 백트래킹
			if(rowResult && colResult && areaResult) {
				table[curRow][curCol] = num;
				rowMap.get(curRow).add(num);
				colMap.get(curCol).add(num);
				areaMap.get(String.valueOf((curRow / 3) + ", " +(curCol / 3))).add(num);
				backtracking(curIndex + 1);
				table[curRow][curCol] = 0;
				rowMap.get(curRow).remove(Integer.valueOf(num));
				colMap.get(curCol).remove(Integer.valueOf(num));
				areaMap.get(String.valueOf((curRow / 3) + ", " +(curCol / 3))).remove(Integer.valueOf(num));
			}
			
			
			
			
			
		}
		
		
		
		
		
		
		
	}
	private static boolean rowTest(int curRow, int num) {
		
		return !rowMap.get(curRow).contains(num);
	}
	private static boolean colTest(int curCol, int num) {
		// TODO Auto-generated method stub
		return !colMap.get(curCol).contains(num);
	}
	private static boolean areaTest(int curRow, int curCol, int num) {
		// TODO Auto-generated method stub
		String targetKey = String.valueOf((curRow / 3) + ", " +(curCol / 3));
		return !areaMap.get(targetKey).contains(num);
	}
}
