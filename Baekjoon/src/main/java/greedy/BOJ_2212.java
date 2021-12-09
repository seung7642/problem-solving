package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_2212 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K, ans;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        K = Integer.parseInt(in.readLine());

        int[] arr = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(arr);
        Integer[] diff = new Integer[N - 1];
        for (int i = 0; i < N - 1; i++) {
            diff[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(diff, Collections.reverseOrder());

        for (int i = K - 1; i < N - 1; i++) {
            ans += diff[i];
        }
        System.out.println(ans);
    }
}
