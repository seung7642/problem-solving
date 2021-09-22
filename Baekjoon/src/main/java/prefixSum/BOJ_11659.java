package prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11659 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, i, j;
    private static int[] arr, prefixSum;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        prefixSum = new int[N + 1];
        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = arr[i] + prefixSum[i - 1];
        }

        while (M-- > 0) {
            st = new StringTokenizer(in.readLine());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            int result = prefixSum[j] - prefixSum[i - 1];
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}
