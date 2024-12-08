package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1871 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String input = br.readLine();
            String str1 = input.split("-")[0];
            String str2 = input.split("-")[1];

            int sum = 0;
            for (int i = str1.length() - 1, j = 0; i >= 0; i--, j++) {
                int val = str1.charAt(i) - 'A';
                sum += (Math.pow(26, j) * val);
            }

            int result = Math.abs(sum - Integer.parseInt(str2));
            if (result <= 100) sb.append("nice").append("\n");
            else sb.append("not nice").append("\n");
        }

        System.out.println(sb.toString());
    }
}
