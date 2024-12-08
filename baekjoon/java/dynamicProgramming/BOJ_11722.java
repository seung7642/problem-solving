package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 가장 긴 감소하는 부분 수열
public class BOJ_11722 {

    private static int N, ans;
    private static int[] arr, cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        cache = new int[N];
        cache[0] = 1;
        for (int i = 1; i < N; i++) {
            cache[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    cache[i] = Math.max(cache[i], cache[j] + 1);
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
