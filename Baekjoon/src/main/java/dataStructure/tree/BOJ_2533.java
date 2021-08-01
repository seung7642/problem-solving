package dataStructure.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2533 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static boolean[] visited;
    private static int[][] dp;
    private static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        dp = new int[N + 1][2];
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        int start = 1;
        dfs(start);
        int result = Math.min(dp[start][0], dp[start][1]);
        System.out.println(result);
    }

    public static void dfs(int idx) {
        visited[idx] = true;
        dp[idx][0] = 0;
        dp[idx][1] = 1;
        for (int to : list[idx]) {
            if (visited[to]) continue;
            dfs(to);
            dp[idx][0] += dp[to][1];
            dp[idx][1] += Math.min(dp[to][0], dp[to][1]);
        }
    }
}