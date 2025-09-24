import java.util.*;
import java.io.*;
class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputMN = br.readLine().split(" ");
		int M = Integer.parseInt(inputMN[0]);
		int N = Integer.parseInt(inputMN[1]);
		
		//유클리드 호제법
		//먼저 최대공약수를 구해준다.
		int bigger = (M >= N) ? M: N;
		int smaller = (M >= N) ? N: M;
		int BDN = 0;
		int SMN = Integer.MAX_VALUE;
		int mok = bigger / smaller;
		while(true) {
			
			int namuji= bigger % smaller;
			
			if(namuji == 0 ) {
				BDN = smaller;
				break;
			}else {
				bigger = smaller;
				smaller = namuji;
			}
		}
		
		SMN = M * N / BDN;
		System.out.println(BDN);
		System.out.println(SMN);
	}
}
