package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1965 {

    private static int N, ans;
    private static int[] arr, cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        cache = new int[N];
        for (int i = 0; i < arr.length; i++) {
            cache[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && cache[i] <= cache[j]) {
                    cache[i] = cache[j] + 1;
                }
            }
            ans = Math.max(ans, cache[i]);
        }

        System.out.println(ans);
    }
}
