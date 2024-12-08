package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15486 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N, max;
    private static int[] T, P;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        T = new int[N + 2];
        P = new int[N + 2];
        dp = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        bottomUp();

        System.out.println(max);
    }

    private static void bottomUp() {
        for (int i = 1; i <= N + 1; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }

            int day = i + T[i];
            if (day <= N + 1) {
                dp[day] = Math.max(dp[day], max + P[i]);
            }
        }
    }
}
