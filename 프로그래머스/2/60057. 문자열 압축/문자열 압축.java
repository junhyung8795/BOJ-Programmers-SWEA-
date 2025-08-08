import java.util.Scanner;

class Solution {
    public int solution(String s) {
        // 문자열 길이가 1이면 압축 불가능하므로 1을 반환
        if (s.length() == 1) {
            return 1;
        }

        // 압축이 전혀 안 되는 경우가 최댓값이므로 초기값으로 설정
        int minLength = s.length();

        // 1. 자르는 단위를 1부터 s.length()/2 까지 늘려가며 반복
        for (int unit = 1; unit <= s.length() / 2; unit++) {
            StringBuilder compressed = new StringBuilder();
            
            // 2. 단위(unit)만큼 문자열을 잘라 압축
            for (int pos = 0; pos < s.length(); ) {
                // 기준이 되는 패턴 (pos에서 unit만큼 자름)
                // 마지막 부분의 경계를 넘지 않도록 처리
                String pattern = s.substring(pos, Math.min(pos + unit, s.length()));
                int count = 1;

                // 다음 위치부터 패턴이 몇 번 반복되는지 확인
                int nextPos = pos + unit;
                while (nextPos < s.length()) {
                    String nextPattern = s.substring(nextPos, Math.min(nextPos + unit, s.length()));
                    if (pattern.equals(nextPattern)) {
                        count++;
                        nextPos += unit; // 다음 비교 위치로 이동
                    } else {
                        break; // 패턴이 다르면 반복 중단
                    }
                }

                // 3. 압축된 결과를 StringBuilder에 추가
                if (count > 1) {
                    compressed.append(count); // 반복 횟수 추가
                }
                compressed.append(pattern); // 패턴 추가

                // 4. 다음 기준 패턴을 찾기 위해 위치(pos)를 업데이트
                pos = nextPos;
            }

            // 5. 현재 단위로 압축된 길이를 기존 최솟값과 비교하여 갱신
            minLength = Math.min(minLength, compressed.length());
        }

        return minLength;
    }

    // 테스트를 위한 main 메소드 (프로그래머스 제출 시에는 solution 메소드만 필요)
    public static void main(String[] args) {
        Solution sol = new Solution();
        Scanner sc = new Scanner(System.in);
        System.out.print("문자열을 입력하세요: ");
        String s = sc.nextLine(); // 예: "aabbaccc"
        int result = sol.solution(s);
        System.out.println("가장 짧은 압축 문자열의 길이: " + result); // 결과: 7
    }
}