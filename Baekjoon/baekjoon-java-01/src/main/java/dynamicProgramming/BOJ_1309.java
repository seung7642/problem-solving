package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1309 {

    private static final int MOD = 9901;
    private static int N;
    private static int[][] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cache = new int[N + 1][3];
        cache[1][0] = cache[1][1] = cache[1][2] = 1;

        for (int i = 2; i <= N; i++) {
            cache[i][0] = (cache[i - 1][0] + cache[i - 1][1] + cache[i - 1][2]) % MOD;
            cache[i][1] = (cache[i - 1][0] + cache[i - 1][2]) % MOD;
            cache[i][2] = (cache[i - 1][0] + cache[i - 1][1]) % MOD;
        }

        int result = (cache[N][0] + cache[N][1] + cache[N][2]) % MOD;
        System.out.println(result);
    }
}
