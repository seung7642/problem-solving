package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11497 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, ans;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine());
            int[] arr = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Arrays.sort(arr);
            int[] resultArr = new int[N];
            for (int i = 0, cnt = 0; i < N; i += 2, cnt++) {
                resultArr[cnt] = arr[i];
                if (i + 1 < N) {
                    resultArr[N - 1 - cnt] = arr[i + 1];
                }
            }

            ans = Math.abs(resultArr[0] - resultArr[N - 1]);
            for (int i = 1; i < N; i++) {
                int diff = Math.abs(resultArr[i] - resultArr[i - 1]);
                ans = Math.max(ans, diff);
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
