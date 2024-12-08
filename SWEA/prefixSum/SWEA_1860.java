package prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1860 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, M, K;
    private static int[] arr, prefixSum;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Arrays.sort(arr);
            prefixSum = new int[11112];
            for (int i = M; i <= 11111; i += M) { prefixSum[i] = K; }
            for (int i = 1; i <= 11111; i++) { prefixSum[i] += prefixSum[i - 1]; }

            boolean isPossible = true;
            for (int i = 0; i < N; i++) {
                if (prefixSum[arr[i]] - i <= 0) {
                    isPossible = false;
                    break;
                }
            }

            sb.append("#").append(tc).append(" ");
            if (isPossible) sb.append("Possible");
            else sb.append("Impossible");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
