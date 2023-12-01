package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10819 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ans;
    private static int[] arr, tmp;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        tmp = new int[N];
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        dfs(0);

        System.out.println(ans);
        br.close();
    }

    private static void dfs(int idx) {
        if (idx == N) {
            int sum = 0;
            for (int i = 1; i < N; i++)
                sum += Math.abs(tmp[i] - tmp[i - 1]);

            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            tmp[idx] = arr[i]; // 새로운 배열 만들기
            dfs(idx + 1);
            visited[i] = false;
        }
    }
}
