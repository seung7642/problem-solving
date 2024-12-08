package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_15988 {

    private static final int MOD = 1_000_000_009;
    private static int T, N;
    private static long[] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        cache = new long[1_000_001];
        cache[1] = 1;
        cache[2] = 2;
        cache[3] = 4;
        for (int i = 4; i <= 1_000_000; i++) {
            cache[i] = (cache[i - 1] + cache[i - 2] + cache[i - 3]) % MOD;
        }

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            sb.append(cache[N]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
