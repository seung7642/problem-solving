package dataStructure.stack;

import java.io.*;
import java.util.*;

public class BOJ_2504 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Stack<String> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        String str = br.readLine();

        boolean isAble = true;
        for (int i = 0; i < str.length(); i++) {
            String c = str.substring(i, i + 1);

            if ("(".equals(c)) {
                stack.push(")");
                continue;
            } else if ("[".equals(c)) {
                stack.push("]");
                continue;
            }

            int num = 0;
            while (true) {
                if (stack.isEmpty()) {
                    isAble = false;
                    break;
                }

                if (isNumber(stack.peek())) {
                    num += Integer.parseInt(stack.pop());
                } else {
                    if (isVPS(c, stack.peek())) {
                        stack.pop();
                        int t = (")".equals(c)) ? 2 : 3;

                        if (num == 0) stack.push(String.valueOf(t));
                        else stack.push(String.valueOf(t * num));
                        break;
                    } else {
                        isAble = false;
                        break;
                    }
                }
            }
            if (!isAble) break;
        }

        int result = 0;
        while (!stack.isEmpty()) { // 정상적인 괄호 문자열이라면 스택에는 숫자만 들어 있어야 한다.
            if (isNumber(stack.peek())) {
                result += Integer.parseInt(stack.pop());
            } else {
                isAble = false;
                break;
            }
        }

        if (isAble) System.out.println(result);
        else System.out.println(0);
    }

    public static boolean isVPS(String c, String target) {
        if (c.equals(target)) return true;
        return false;
    }

    // 두 괄호가 아니면 무조건 숫자이다.
    public static boolean isNumber(String str) {
        if (str.equals(")") || str.equals("]")) return false;
        return true;
    }
}