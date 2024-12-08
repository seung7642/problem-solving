package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2745 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            int B = Integer.parseInt(st.nextToken());

            int ans = 0, length = input.length();
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                int val;
                if (Character.isDigit(c)) {
                    val = Character.digit(c, 10);
                } else {
                    val = input.charAt(i) - 'A' + 10;
                }

                int result = 1, cnt = length - i - 1;
                for (int j = 0; j < cnt; j++) {
                    result *= B;
                }

                result *= val;
                ans += result;
            }

            System.out.println(ans);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
