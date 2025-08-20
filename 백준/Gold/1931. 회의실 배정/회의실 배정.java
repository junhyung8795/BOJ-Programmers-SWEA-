

import java.io.*;
import java.util.*;

class Main {
	static int[][] conference;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		//각 회의의 시작시간과 종료시간을 받는다.
		conference = new int[N][2];
		for(int i = 0; i < N; i++) {
			String[] inputStartEnd = br.readLine().split(" ");
			
			conference[i][0] = Integer.parseInt(inputStartEnd[0]);
			conference[i][1] = Integer.parseInt(inputStartEnd[1]);
		}
		
		
		//각 회의들은 최대한 촘촘하게
		//최대한 서로를 방해하지 않게 뽑아야한다.
		//먼저 최대한 서로를 방해하지 않으려면 끝나는 시간이 가장 빠른 순으로 먼저 정렬을 해야한다.
		//처음에는 시작시간이 가장 빠른 회의를 뽑아야한다고 생각했지만
		//예시 입력의 2 13같은 입력을 보고 시작시간이 빠르다고 해서 만사가 아니다.
		//그러면 시작시간이 빠른 순으로 먼저 정렬하고 그 중에서 종료시간이 빠른 순서로 정렬을 하려고했다.
		//그러나 06 14 44 44 44 44 44 같은 극단적인 상황이 나타나면 
		//0으로 시작해도 6에 끝나기에 중간에 4시에 끝나는 극단적인 회의들을 진행할 수 없다.
		//따라서 아무리 빨리 시작한다고 해도 끝나는 시간이 늦으면 해당 시간 뒤에 올 수 있는 회의들의 가능성들을 해치기 때문에
		//회의를 선정할때 종료시간이 가장빠른 회의부터 뽑기로했다.
		//그래서 14 35 06순으로 쭉 일단 놓아봤는데,같은 종료시간이라면, 시작시간이 빠른게 더 유리하다는 결론이 낫다.
		//왜냐하면 위 예시처럼 14 44순으로 회의를 진행하는게, 44 14순으로 회의를 진행하는거보다 더 유리하다.
		//종료시간이 같다면 14 (49, 59, 69, 79,89) 중 무거나 한개가 와도 상관이 없다.
		//49를 고른다고해서 59를 고를 수 없고, 89를 고른다고해서 49를 고를 수도 없다.
		//하지만 예외가 있는데, 위와 같은 14 44 44 44같은 케이스는 
		//시작시간이 종료시간과같아지면 시작하자마자 끝나기에 회의수가 복사가된다.
		//따라서 종료시간이 빠른 순으로 정렬을하고 2번째 기준으로 시작시간이 빠른 순으로 정렬을 해서
		//각 회의의 종료시간보다 크거나 같은 시작시간을 가진 회의를 추가로 카운팅하면서 순회한다.
		
		Arrays.sort(conference, (a,b) -> {
			if(a[1] != b[1]) {
				return a[1] - b[1];
			} else {
				return a[0] - b[0];
			}
		});
		
		int endTime = 0;
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			//endTime < 이 아닌 이유는 끝나는 시간에맞춰 다음회의가 바로 시작할 수 있기때문이다.
			if(endTime <= conference[i][0]) {
				//현재호의의 종료시간보다 다음회의의 시작시간이 뒤에 있다면 
				//해당 회의를 선정한다!
				cnt++;
				//그리고 선정한 해당 회의의 종료시간으로 endTime업데이트
				endTime = conference[i][1];
			}
		}
		System.out.println(cnt);
		
	}

}