package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 외판원 순회 2
public class BOJ_10971 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ans = Integer.MAX_VALUE;
    private static int[][] map;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            dfs(i, i, 0, 0);
        }

        System.out.println(ans);
        br.close();
    }

    private static void dfs(int start, int i, int idx, int sum) {
        if (idx == N && start == i) {
            ans = Math.min(ans, sum);
            return;
        }

        for (int j = 0; j < N; j++) {
            if (map[i][j] == 0) continue;
            if (visited[i]) continue;

            visited[i] = true;
            sum += map[i][j];
            dfs(start, j, idx + 1, sum);
            visited[i] = false;
            sum -= map[i][j];
        }
    }
}
