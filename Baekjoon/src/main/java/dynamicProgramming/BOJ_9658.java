package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9658 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static boolean[] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        dp = new boolean[N + 1];
        dp[1] = true;
        if (N >= 3) {
            dp[3] = true;
        }
        for (int i = 5; i <= N; i++) {
            if (dp[i - 1] || dp[i - 3] || dp[i - 4]) {
                dp[i] = false;
            } else {
                dp[i] = true;
            }
        }

        System.out.println(dp[N] ? "CY" : "SK");
    }
}
