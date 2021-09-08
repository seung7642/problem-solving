package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 0-1 배낭 문제
public class BOJ_1535 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] L, J;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        L = new int[N + 1];
        J = new int[N + 1];
        dp = new int[N + 1][101];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= N; i++) {
            L[i] = Integer.parseInt(st.nextToken()); // 무게
        }
        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= N; i++) {
            J[i] = Integer.parseInt(st.nextToken()); // 가치
        }

        bottomUp();
        System.out.println(dp[N][1]);
    }

    private static void bottomUp() {
        for (int i = 1; i <= N; i++) {
            int weight = L[i]; // i번째 사람의 무게
            int value = J[i];  // i번째 사람의 가치
            for (int j = 100; j >= 0; j--) {
                if (j + weight <= 100) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j + weight] + value);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
    }
}
