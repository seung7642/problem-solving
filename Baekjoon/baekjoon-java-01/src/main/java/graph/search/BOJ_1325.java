package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1325 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, ans = Integer.MIN_VALUE;
    private static List<List<Integer>> list;
    private static boolean[] visited;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];
        visited = new boolean[N + 1];
        list = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            list.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list.get(u).add(v);
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            bfs(i);
        }

        for (int i = 1; i <= N; i++)
            ans = Math.max(ans, dp[i]);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (dp[i] == ans) {
                sb.append(i + " ");
            }
        }

        System.out.println(sb.toString());
        br.close();
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 0; i < list.get(now).size(); i++) {
                int next = list.get(now).get(i);

                if (visited[next]) continue;

                visited[next] = true;
                dp[next]++;
                q.add(next);
            }
        }
    }

    private static void dfs(int start) {
        visited[start] = true;

        for (Integer next : list.get(start)) {
            if (visited[next]) continue;

            dp[next]++;
            dfs(next);
        }
    }
}
