package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10211 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, max;
    private static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(in.readLine());
            arr = new int[N];
            dp = new int[N];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            bottomUp();
            System.out.println(max);
        }
    }

    private static void bottomUp() {
        dp[0] = arr[0];
        max = dp[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            max = Math.max(max, dp[i]);
        }
    }
}
