package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1357 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String X = st.nextToken();
            String Y = st.nextToken();

            int val = Integer.parseInt(reverse(X)) + Integer.parseInt(reverse(Y));
            String ans = reverse(String.valueOf(val));

            System.out.println(Integer.parseInt(ans));
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }

        return sb.toString();
    }
}
