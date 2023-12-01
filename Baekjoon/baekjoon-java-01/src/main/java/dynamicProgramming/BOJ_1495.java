package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1495 {

    private static int N, S, M;
    private static int[] arr;
    private static int[][] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        cache = new int[N][M + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], -2);
        }

        int result = dfs(S, 0);
        System.out.println(result);
    }

    private static int dfs(int sum, int depth) {
        if (sum < 0 || sum > M) {
            return -1;
        }

        if (depth == N) {
            return sum;
        }

        if (cache[depth][sum] != -2) {
            return cache[depth][sum];
        }

        int result1 = dfs(sum + arr[depth], depth + 1);
        int result2 = dfs(sum - arr[depth], depth + 1);
        return cache[depth][sum] = Math.max(result1, result2);
    }
}
