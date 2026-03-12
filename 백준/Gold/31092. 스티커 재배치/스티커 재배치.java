import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int K;

    static char[] s;   // 스티커에 적힌 문자
    static int[] d;    // 떼는 비용
    static int[] a;    // 구매 비용

    static int[] Nnums; // 현재 보드에 붙은 스티커 종류 번호

    // 문자별 최소 구매 비용
    static HashMap<Character, Integer> store = new HashMap<>();

    // 전체 보드에서 스티커 종류 번호별 개수
    static HashMap<Integer, Integer> totalTypeCnt = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N M K 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        s = new char[M + 1];
        d = new int[M + 1];
        a = new int[M + 1];

        // 스티커 종류 정보 입력
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            s[i] = st.nextToken().charAt(0);
            d[i] = Integer.parseInt(st.nextToken());
            a[i] = Integer.parseInt(st.nextToken());

            store.put(s[i], Math.min(store.getOrDefault(s[i], Integer.MAX_VALUE), a[i]));
        }

        // 현재 스티커 상태 입력
        Nnums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            Nnums[i] = Integer.parseInt(st.nextToken());
            totalTypeCnt.put(Nnums[i], totalTypeCnt.getOrDefault(Nnums[i], 0) + 1);
        }

        // 목표 문자열
        char[] target = br.readLine().toCharArray();

        int answer = Integer.MAX_VALUE;

        // 목표 문자열이 들어갈 시작 위치를 모두 시도
        for (int start = 0; start <= N - K; start++) {
            int cost = 0;

            // 현재 윈도우 내부의 스티커 종류별 개수
            HashMap<Integer, Integer> windowTypeCnt = new HashMap<>();

            // 문자별 재활용 가능 비용 후보
            HashMap<Character, PriorityQueue<Integer>> reusable = new HashMap<>();

            // 목표 문자열에 필요한 문자 집합
            HashSet<Character> needCharSet = new HashSet<>();
            for (char c : target) {
                needCharSet.add(c);
            }

            // 1. 윈도우 내부에서 틀린 스티커는 제거
            // 제거된 스티커는 같은 문자로 재활용 가능(추가 제거비용 없음)
            for (int i = start; i < start + K; i++) {
                int type = Nnums[i];
                windowTypeCnt.put(type, windowTypeCnt.getOrDefault(type, 0) + 1);

                char currentChar = s[type];
                char wantChar = target[i - start];

                if (currentChar != wantChar) {
                    cost += d[type];

                    if (needCharSet.contains(currentChar)) {
                        reusable.computeIfAbsent(currentChar, k -> new PriorityQueue<>()).offer(0);
                    }
                }
            }

            // 2. 윈도우 밖의 스티커는 떼어오면 재활용 가능
            // 이 경우 비용은 d[type]
            for (Map.Entry<Integer, Integer> entry : totalTypeCnt.entrySet()) {
                int type = entry.getKey();
                int totalCnt = entry.getValue();
                int insideCnt = windowTypeCnt.getOrDefault(type, 0);
                int outsideCnt = totalCnt - insideCnt;

                if (outsideCnt <= 0) continue;

                char ch = s[type];
                if (!needCharSet.contains(ch)) continue;

                PriorityQueue<Integer> pq = reusable.computeIfAbsent(ch, k -> new PriorityQueue<>());
                for (int c = 0; c < outsideCnt; c++) {
                    pq.offer(d[type]);
                }
            }

            // 3. 틀린 자리를 목표 문자열로 맞추기
            boolean possible = true;

            for (int i = start; i < start + K; i++) {
                int type = Nnums[i];
                char currentChar = s[type];
                char wantChar = target[i - start];

                // 이미 맞으면 그대로 둠
                if (currentChar == wantChar) continue;

                PriorityQueue<Integer> pq = reusable.get(wantChar);
                int reuseCost = (pq != null && !pq.isEmpty()) ? pq.peek() : Integer.MAX_VALUE;
                int buyCost = store.getOrDefault(wantChar, Integer.MAX_VALUE);

                if (reuseCost == Integer.MAX_VALUE && buyCost == Integer.MAX_VALUE) {
                    possible = false;
                    break;
                }

                if (reuseCost < buyCost) {
                    cost += pq.poll();
                } else {
                    cost += buyCost;
                }
            }

            if (possible) {
                answer = Math.min(answer, cost);
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}