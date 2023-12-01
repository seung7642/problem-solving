package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11060 {

    private static final int INF = 1_000_000_000;
    private static int N, ans;
    private static int[] arr, cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cache = new int[N + 1];
        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            cache[i] = -1;
        }


        int result = dfs(1);
        System.out.println(result == INF ? "-1" : result);
    }

    private static void loop() {
        for (int i = 1; i <= N; i++) {
            int jump = arr[i];
            for (int j = 1; j <= jump; j++) {
                if (i + j > N) continue;
                cache[i + j] = Math.min(cache[i] + 1, cache[i + j]);
            }
        }
    }

    private static int dfs(int pos) {
        if (pos > N) {
            return INF;
        }

        if (pos == N) { // 오른쪽 끝에 도달했다면
            return 0;
        }

        if (cache[pos] != -1) {
            return cache[pos];
        }

        cache[pos] = INF;
        for (int i = 1; i <= arr[pos]; i++) {
            int result = dfs(pos + i) + 1;
            cache[pos] = Math.min(cache[pos], result);
        }
        return cache[pos];
    }
}
