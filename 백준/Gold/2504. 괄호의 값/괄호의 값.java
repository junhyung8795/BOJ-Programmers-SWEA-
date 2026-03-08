import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        Stack<Character> stack = new Stack<>();

        int result = 0;
        int temp = 1;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c == '(') {
                stack.push(c);
                temp *= 2;
            }

            else if (c == '[') {
                stack.push(c);
                temp *= 3;
            }

            else if (c == ')') {

                if (stack.isEmpty() || stack.peek() != '(') {
                    System.out.println(0);
                    return;
                }

                if (s.charAt(i - 1) == '(') {
                    result += temp;
                }

                stack.pop();
                temp /= 2;
            }

            else if (c == ']') {

                if (stack.isEmpty() || stack.peek() != '[') {
                    System.out.println(0);
                    return;
                }

                if (s.charAt(i - 1) == '[') {
                    result += temp;
                }

                stack.pop();
                temp /= 3;
            }
        }

        if (!stack.isEmpty()) System.out.println(0);
        else System.out.println(result);
    }
}