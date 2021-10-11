package blindRecruitment_2020;

import java.util.Stack;

public class B {

    public static void main(String[] args) {
        String p = "()))((()";
        String result = solution(p);
        System.out.println(result);
    }

    public static String solution(String p) {
        return recursive(p);
    }

    private static String recursive(String s) {
        int i, start, end, N = s.length();
        start = end = 0;
        for (i = 0; i < N; i++) {
            char ch = s.charAt(i);
            if (ch == '(') start++;
            else end++;
            if (i != 0 && start == end) break;
        }
        if ("".equals(s)) return "";
        String u = s.substring(0, i + 1);
        if (isValidBracket(u)) {
            return u + recursive(s.substring(i + 1));
        } else {
            return "(" + recursive(s.substring(i + 1)) + ")" + reverse(u);
        }
    }

    private static String reverse(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < s.length() - 1; i++) {
            char ch = s.charAt(i);
            if (ch == '(') sb.append(')');
            else sb.append('(');
        }
        return sb.toString();
    }

    private static boolean isValidBracket(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
                else return false;
            }
        }
        return true;
    }
}
