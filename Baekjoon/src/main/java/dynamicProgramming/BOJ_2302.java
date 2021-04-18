package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2302 {

    private static int N, M;
    private static int[] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        cache = new int[N + 1];
        cache[0] = 1;
        cache[1] = 1;
        cache[2] = 2;
        for (int i = 3; i <= N; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }

        int result = 1, prev = 0;
        for (int i = 0; i < M; i++) {
            int temp = Integer.parseInt(br.readLine());
            result *= cache[temp - prev - 1];
            prev = temp;
        }
        result *= cache[N - prev]; // 마지막 vip 좌석 다음 좌석에서 끝 좌석까지의 경우의 수.

        System.out.println(result);
    }

}