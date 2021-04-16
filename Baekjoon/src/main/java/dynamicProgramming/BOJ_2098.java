package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2098 {

    private static final int INF = 10_000_000;
    private static int N;
    private static int[][] map, cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        cache = new int[N][(1 << N) - 1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], INF);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = dfs(0, 1);
        System.out.println(result);
    }

    private static int dfs(int node, int visitd) {
        if (visitd == (1 << N) - 1) {
            return map[node][0] == 0 ? INF : map[node][0];
        }

        if (cache[node][visitd] != INF) {
            return cache[node][visitd];
        }

        for (int i = 0; i < N; i++) {
            // i번 노드에 대한 길이 없거나, 이미 방문한 경우
            if (map[node][i] == 0 || (visitd & (1 << i)) != 0) continue;

            // dfs 파라미터로 다음에 방문할 i번 노드, i번 노드를 방문했다는 표시를 넘긴다.
            int cost = dfs(i, visitd | (1 << i)) + map[node][i];
            cache[node][visitd] = Math.min(cache[node][visitd], cost);
        }
        return cache[node][visitd];
    }
}
