package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2491 {

    private static int N;
    private static int[] arr, cache, cache1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        cache = new int[N];
        cache1 = new int[N];

        int result = cache[0] = cache1[0] = 1;
        for (int i = 1; i < N; i++) {
            cache[i] = cache1[i] = 1;

            if (arr[i - 1] <= arr[i]) {
                cache[i] = cache[i - 1] + 1;
            }

            if (arr[i - 1] >= arr[i]) {
                cache1[i] = cache1[i - 1] + 1;
            }

            result = Math.max(result, Math.max(cache[i], cache1[i]));
        }

        System.out.println(result);
    }
}
