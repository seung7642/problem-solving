package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1699 {

    private static int N;
    private static int[] cache = new int[100_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cache[1] = 1;
        for (int i = 2; i <= N; i++) {
            cache[i] = i; // 자연수 k가 가질 수 있는 항의 최대개수는 k개다.

            for (int j = 1; j * j <= i; j++) {
                cache[i] = Math.min(cache[i], cache[i - j * j] + 1);
            }
        }

        System.out.println(cache[N]);
    }
}
