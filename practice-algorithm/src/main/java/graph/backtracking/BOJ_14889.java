package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ans = Integer.MAX_VALUE;
    private static int[][] map;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 0);
        System.out.println(ans);
        br.close();
    }

    private static void dfs(int start, int depth) {
        if (depth == N / 2) {
            ans = Math.min(ans, getAbilityDifference());
            return;
        }

        for (int i = start; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    static int getAbilityDifference() {
        int sumStart = 0;
        int sumLink = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // true 면 스타트팀
                if (visited[i] && visited[j])
                    sumStart += map[i][j];

                // false 면 링크팀
                if (!visited[i] && !visited[j])
                    sumLink += map[i][j];
            }
        }

        return Math.abs(sumStart - sumLink);
    }
}
