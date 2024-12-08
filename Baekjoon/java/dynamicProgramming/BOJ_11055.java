package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11055 {

    private static int N, ans;
    private static int[] arr, cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        cache = new int[N];
        cache[0] = arr[0];
        for (int i = 1; i < N; i++) {
            cache[i] = arr[i];

            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    cache[i] = Math.max(cache[i], cache[j] + arr[i]);
                }
            }
        }

        for (int x : cache) {
            if (ans < x) {
                ans = x;
            }
        }

        System.out.println(ans);
    }
}
