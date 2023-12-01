package slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2096 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, max, min;
    private static int[][] arr, dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        dp = new int[N][3];
        arr = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        for (int i = 1; i < N; i++) {
            dp[i][0] = arr[i][0] + Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = arr[i][1] + Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
            dp[i][2] = arr[i][2] + Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        max = Math.max(dp[N - 1][0], Math.max(dp[N - 1][1], dp[N - 1][2]));

        for (int i = 1; i < N; i++) {
            dp[i][0] = arr[i][0] + Math.min(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = arr[i][1] + Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2]));
            dp[i][2] = arr[i][2] + Math.min(dp[i - 1][1], dp[i - 1][2]);
        }
        min = Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2]));

        System.out.println(max + " " + min);
        br.close();
    }
}
