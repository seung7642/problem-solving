package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9656 {

    private static int N;
    private static int[] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cache = new int[1001];
        cache[1] = 1; // 상근이 패
        cache[2] = 0; // 상근이 승
        cache[3] = 1;
        for (int i = 4; i < 1001; i++) {
            if (cache[i - 1] == 0 || cache[i - 3] == 0) {
                cache[i] = 1;
            } else {
                cache[i] = 0;
            }
        }

        System.out.println(cache[N] == 1 ? "CY" : "SK");
    }
}
