package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2935 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String A = br.readLine();
            char operator = br.readLine().charAt(0);
            String B = br.readLine();

            String ans = operate(A, B, operator);
            System.out.println(ans);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static String operate(String a, String b, char operator) {
        StringBuilder sb = new StringBuilder();
        if (operator == '*') {
            int cnt = a.length() + b.length() - 2;
            sb.append("1");
            for (int i = 0; i < cnt; i++) {
                sb.append("0");
            }
        } else if (operator == '+') {
            String str = a.length() >= b.length() ? a : b;
            sb.append(str);

            int idx = Math.abs(a.length() - b.length());
            int val = sb.charAt(idx) - '0';
            sb.setCharAt(idx, Character.forDigit(val + 1, 10));
        }

        return sb.toString();
    }
}
