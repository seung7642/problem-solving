package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_1234 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    private static Stack<Integer> st;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (T++ < 10) {
            st = new Stack<>();
            String[] sArr = br.readLine().split(" ");
            for (char c : sArr[1].toCharArray()) {
                if (!st.isEmpty() && st.peek() == Character.digit(c, 10)) {
                    st.pop();
                } else {
                    st.push(Character.digit(c, 10));
                }
            }

            sb.append("#").append(T).append(" ");
            Stack<Integer> tmp = new Stack<>();
            while (!st.isEmpty()) tmp.push(st.pop());
            while (!tmp.isEmpty()) sb.append(tmp.pop());
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
