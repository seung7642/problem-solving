package dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_11066 {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int K = sc.nextInt();
            int[] arr = new int[K + 1];
            int[] sum = new int[K + 1];
            int[][] dp = new int[502][502];

            for (int i = 1; i <= K; i++) {
                arr[i] = sc.nextInt();
                sum[i] = sum[i - 1] + arr[i];
            }

            // j에서 i까지의 최소 비용. (j < k < i)
            for (int i = 2; i <= K; i++) {
                for (int j = i - 1; j > 0; j--) {
                    dp[j][i] = Integer.MAX_VALUE;
                    for (int k = j; k <= i; k++) {
                        dp[j][i] = Math.min(dp[j][i], dp[j][k] + dp[k + 1][i]);
                    }
                    dp[j][i] += sum[i] - sum[j - 1]; // 마지막에 전체 합을 한 번 더해준다.
                }
            }

            sb.append(dp[1][K]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
