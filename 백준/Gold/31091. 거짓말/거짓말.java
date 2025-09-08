
import java.io.*;
import java.util.*;

public class Main {

    static int upperBound(List<Integer> a, int key) {
        int left = 0, right = a.size() - 1, ans = a.size();
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (a.get(mid) > key) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    static int lowerBound(List<Integer> a, int key) {
        int left = 0, right = a.size() - 1, ans = a.size();
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (a.get(mid) >= key) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] inputStr = br.readLine().split(" ");

        List<Integer> arrA = new ArrayList<>();
        List<Integer> arrB = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(inputStr[i]);
            if (num > 0) arrA.add(num);
            else arrB.add(-num); // 음수는 절댓값으로
        }

        arrA.sort(Comparator.naturalOrder());
        arrB.sort(Comparator.naturalOrder());

        List<Integer> answerList = new ArrayList<>();

        for (int K = 0; K <= N; K++) {
            // arrA: > K 개수 = size - upper_bound(K)
            int idxA = upperBound(arrA, K);
            int resultA = arrA.size() - idxA;

            // arrB: < K 개수 = lower_bound(K)
            int idxB = lowerBound(arrB, K);
            int resultB = idxB;

            if (resultA + resultB == K) {
                answerList.add(K);
            }
        }

        
        System.out.println(answerList.size());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < answerList.size(); i++){
            sb.append(answerList.get(i)+" ");
        }
        System.out.println(sb.toString());
    }
}
