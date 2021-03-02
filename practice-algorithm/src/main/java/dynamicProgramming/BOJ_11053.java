package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11053 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] dp = new int[N];
            dp[0] = 1;
            for (int i = 1; i < N; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (arr[i] > arr[j] && dp[i] <= dp[j]) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }

            int max = 0;
            for (int i : dp) {
                max = Math.max(max, i);
            }
            System.out.println(max);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
