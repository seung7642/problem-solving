package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9655 {

    private static int N;
    private static int[] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cache = new int[N + 1];

        cache[1] = 1;
        for (int i = 1; i <= N; i++) {
            cache[i] = 0;
            if (i - 1 >= 0 && cache[i - 1] == 0) {
                cache[i] = 1;
            } else if (i - 3 >= 0 && cache[i - 3] == 0) {
                cache[i] = 1;
            }
        }

        System.out.println(cache[N] == 1 ? "SK" : "CY");
    }
}
