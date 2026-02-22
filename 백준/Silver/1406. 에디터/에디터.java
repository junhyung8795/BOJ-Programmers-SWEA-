import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String init = br.readLine();
        int M = Integer.parseInt(br.readLine());

        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < init.length(); i++) list.add(init.charAt(i));

        // 커서를 가리키는 iterator (초기 커서는 맨 끝)
        ListIterator<Character> it = list.listIterator(list.size());

        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            char cmd = line.charAt(0);

            if (cmd == 'L') {
                if (it.hasPrevious()) it.previous();
            } else if (cmd == 'D') {
                if (it.hasNext()) it.next();
            } else if (cmd == 'B') {
                // 커서 왼쪽 문자 삭제: 이전으로 한 칸 가서 remove
                if (it.hasPrevious()) {
                    it.previous();
                    it.remove();
                }
            } else if (cmd == 'P') {
                char ch = line.charAt(2); // "P x"
                it.add(ch);               // 커서 왼쪽에 삽입, 커서는 삽입 뒤에 위치
            }
        }

        StringBuilder sb = new StringBuilder(list.size());
        for (char c : list) sb.append(c);
        System.out.print(sb.toString());
    }
}