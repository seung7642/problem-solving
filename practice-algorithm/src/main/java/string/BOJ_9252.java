package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;

public class BOJ_9252 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String s1 = br.readLine();
        String s2 = br.readLine();
        int len1 = s1.length();
        int len2 = s2.length();

        int[][] dp = new int[len2 + 1][len1 + 1];

        for (int i = 1; i < len2 + 1; i++) {
            for (int j = 1; j < len1 + 1; j++) {
                if (s2.charAt(i - 1) != s1.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j] > dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
                else
                    dp[i][j] = dp[i - 1][j - 1] + 1;
            }
        }

        int ans = dp[len2][len1];
        System.out.println(ans);

        int i = len2, j = len1;
        Stack<Character> stack = new Stack<>();
        while (i >= 1 && j >= 1) {
            if (dp[i][j] == dp[i - 1][j]) // 위와 같은 경우 위로 이동
                i--;
            else if (dp[i][j] == dp[i][j - 1]) // 왼쪽과 같은 경우 왼쪽으로 이동
                j--;
            else { // 대각선으로 같다면
                stack.push(s2.charAt(i - 1));
                i--; j--;
            }
        }

        while (!stack.isEmpty())
            System.out.print(stack.pop());

        br.close();
    }
}
