package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17626 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        dp = new int[N + 1];

        bottomUp();
        System.out.println(dp[N]);
    }

    private static void bottomUp() {
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = 0x7fffffff;
            for (int j = 1; j * j <= i; j++) {
                int idx = i - j * j;
                dp[i] = Math.min(dp[i], dp[idx] + 1);
            }
        }
    }
}
