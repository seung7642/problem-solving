package prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2435 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K, ans = Integer.MIN_VALUE;
    private static int[] arr, prefixSum;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        prefixSum = new int[N + 1];
        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = arr[i] + prefixSum[i - 1];
        }

        for (int i = 1; i + K - 1 <= N; i++) {
            int result = prefixSum[i + K - 1] - prefixSum[i - 1];
            ans = Math.max(ans, result);
        }
        System.out.println(ans);
    }
}
