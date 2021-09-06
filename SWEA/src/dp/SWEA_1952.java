package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1952 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    private static int[] fee, plan, dp;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            fee = new int[4];
            plan = new int[13];
            dp = new int[13];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < 4; i++) {
                fee[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(in.readLine());
            for (int i = 1; i <= 12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }

            bottomUp();
            dp[12] = Math.min(dp[12], fee[3]);
            sb.append("#").append(tc).append(" ").append(dp[12]).append("\n");
        }
        System.out.println(sb);
    }

    private static void bottomUp() {
        dp[1] = Math.min(plan[1] * fee[0], fee[1]);
        dp[2] = dp[1] + Math.min(plan[2] * fee[0], fee[1]);
        dp[3] = Math.min(dp[0] + fee[2], dp[2] + Math.min(plan[3] * fee[0], fee[1]));
        dp[4] = Math.min(dp[1] + fee[2], dp[3] + Math.min(plan[4] * fee[0], fee[1]));
        dp[5] = Math.min(dp[2] + fee[2], dp[4] + Math.min(plan[5] * fee[0], fee[1]));
        dp[6] = Math.min(dp[3] + fee[2], dp[5] + Math.min(plan[6] * fee[0], fee[1]));
        for (int i = 7; i <= 12; i++) {
            dp[i] = Math.min(dp[i - 3] + fee[2], dp[i - 1] + Math.min(plan[i] * fee[0], fee[1]));
        }
    }
}
