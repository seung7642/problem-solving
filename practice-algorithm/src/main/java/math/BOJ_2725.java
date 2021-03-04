package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2725 {

    private static StringBuilder sb = new StringBuilder();
    private static int[] ans = new int[1001];

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            ans[1] = 2;
            for (int i = 2; i <= 1000; i++) {
                int cnt = 0;

                for (int j = 1; j <= i; j++) {
                    if (gcd(i, j) == 1) cnt++;
                }
                ans[i] = ans[i - 1] + cnt;
            }

            int T = Integer.parseInt(br.readLine());
            while (T-- > 0) {
                int N = Integer.parseInt(br.readLine());
                sb.append(ans[N] * 2 - 1).append("\n");
            }

            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
