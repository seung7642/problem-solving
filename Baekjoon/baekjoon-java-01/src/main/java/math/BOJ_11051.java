package math;

import java.io.*;
import java.util.Arrays;

public class BOJ_11051 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int N = input[0];
            int K = input[1];
            int[][] dp = new int[N + 1][N + 1];

            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j <= i; j++) {
                    if (i == j || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
                    }
                }
            }

            System.out.println(dp[N][K]);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
