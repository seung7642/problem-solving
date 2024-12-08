package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ_10826 {

    private static int N;
    private static BigInteger[] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cache = new BigInteger[10001];
        cache[0] = BigInteger.ZERO;
        cache[1] = cache[2] = BigInteger.ONE;
        for (int i = 3; i <= 10000; i++) {
            cache[i] = cache[i - 1].add(cache[i - 2]);
        }

        System.out.println(cache[N]);
    }
}
