package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_13301 {

    private static int N;
    private static long[] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cache = new long[N + 1];
        cache[1] = 1;
        for (int i = 2; i <= N; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }

        long result = cache[N] * 2 + (cache[N] + cache[N - 1]) * 2;
        System.out.println(result);
    }
}
