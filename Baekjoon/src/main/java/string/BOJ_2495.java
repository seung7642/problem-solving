package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2495 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 3;
            StringBuilder sb = new StringBuilder();
            while (T-- > 0) {
                String str = br.readLine();

                int max = 1, cnt = 1;
                for (int left = 0, right = 1; right < str.length(); right++) {
                    if (str.charAt(left) == str.charAt(right)) {
                        cnt++;
                    } else {
                        left = right;
                        max = Math.max(max, cnt);
                        cnt = 1;
                    }
                }

                max = Math.max(max, cnt);
                sb.append(max).append("\n");
            }

            System.out.print(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
