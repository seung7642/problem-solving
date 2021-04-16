package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_5557 {

    private static int N;
    private static int[] arr;
    private static long[][] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        cache = new long[21][N];
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j < N; j++) {
                cache[i][j] = -1;
            }
        }

        System.out.println(recursive(arr[0], 0));
    }

    private static long recursive(int sum, int idx) {
        if (sum < 0 || sum > 20) {
            return 0;
        }

        if (idx == N - 2) {
            return sum == arr[N - 1] ? 1 : 0;
        }

        if (cache[sum][idx] != -1) {
            return cache[sum][idx];
        }

        cache[sum][idx] = 0;
        return cache[sum][idx] += recursive(sum + arr[idx + 1], idx + 1) + recursive(sum - arr[idx + 1], idx + 1);
    }
}
