package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BOJ_17413 {

    private static Queue<Character> q = new LinkedList<>(); // 태그 문자열을 담을 변수
    private static Stack<Character> st = new Stack<>(); // 단어를 담을 변수 (리버스 용도)
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();
            solution(str);
            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static void solution(String str) {
        boolean isTagRange = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            // 단어에 대한 리버스 처리는 총 세 가지 경우에 필요하다.
            //   1. '<' 문자를 만났을 때 스택에 단어가 들어가 있는 경우
            //   2. 공백 문자를 만났을 때 스택에 단어가 들어가 있는 경우
            //   3. 최종적으로 출력하기 전 스택에 단어가 들어가 있는 경우

            if (c == '<' || c == '>' || isTagRange) {
                if (c == '<' && !st.isEmpty()) {
                    reverse();
                }
                isTagRange = processTagString(c);
            } else if (Character.isSpaceChar(c)) {
                reverse();
                sb.append(c);
            } else {
                st.push(c);
            }
        }

        if (!st.isEmpty()) {
            reverse();
        }
    }

    private static void reverse() {
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
    }

    private static boolean processTagString(char c) {
        if (c == '>') {
            while (!q.isEmpty()) {
                sb.append(q.poll());
            }
            sb.append(c);
            return false;
        } else if (c == '<') {
            q.add(c);
            return true;
        } else {
            q.add(c);
            return true;
        }
    }
}
