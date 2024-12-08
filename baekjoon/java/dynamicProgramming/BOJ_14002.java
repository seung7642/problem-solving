package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BOJ_14002 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            int N = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] dp = new int[N];
            dp[0] = 1;
            int result = 1;
            for (int i = 1; i < N; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (arr[i] > arr[j] && dp[i] <= dp[j]) {
                        dp[i] = dp[j] + 1;
                        result = Math.max(result, dp[i]);
                    }
                }
            }
            sb.append(result).append("\n");

            Stack<Integer> stack = new Stack<>();
            int len = result;
            for (int i = N - 1; i >= 0; i--) {
                if (len == dp[i]) {
                    stack.push(arr[i]);
                    len--;
                }
            }

            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(" ");
            }

            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
