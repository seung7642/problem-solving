package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1748 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static long N, result;

    public static void main(String[] args) throws IOException {
        String str = in.readLine();
        for (int i = 0; i < str.length(); i++) {
            N++;
        }

        // 1 * 9
        // 2 * 90
        // 3 * 900
        // 4 * 9000
        for (int i = 1; i < N; i++) {
            result += i * (9 * Math.pow(10, i - 1));
        }

        long num;
        if (N == 1) {
            num = Long.parseLong(str);
            result += N * (num);
        } else {
            StringBuilder sb = new StringBuilder(str.substring(1));
            char c = Character.forDigit(
                    Character.digit(str.charAt(0), 10) - 1,
                    10);
            sb.insert(0, c);
            num = Long.parseLong(sb.toString());
            result += N * (num + 1);
        }
        System.out.println(result);
    }
}
