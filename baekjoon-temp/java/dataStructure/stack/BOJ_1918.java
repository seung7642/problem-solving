package dataStructure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 중위 표기법 -> 후위 표기법으로 변환하기.
public class BOJ_1918 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Stack<Character> st = new Stack<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String str = br.readLine();

        for (char c : str.toCharArray()) {
            if ('A' <= c && c <= 'Z') { // 피연산자
                sb.append(c);
            } else if (c == '(') {
                st.push(c);
            } else if (c == ')') {
                while (!st.isEmpty()) {
                    char operator = st.pop();
                    if (operator == '(') break;
                    sb.append(operator);
                }
            } else {
                while (!st.isEmpty() && precedence(st.peek()) >= precedence(c)) {
                    sb.append(st.pop());
                }
                st.push(c);
            }
        }
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }

        System.out.println(sb);
    }

    private static int precedence(char c) {
        if (c == '(') return 0;
        if (c == '+' || c == '-') return 1;
        return 2; // '*' 또는 '/'
    }
}
