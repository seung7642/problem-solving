package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_5567 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n, m, ans;
    private static List<List<Integer>> list;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            list.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list.get(u).add(v);
            list.get(v).add(u);
        }

        dfs(1, 1);
        for (int i = 2; i <= n; i++)
            if (visited[i]) ans++;

        System.out.println(ans);
        br.close();
    }

    private static void dfs(int depth, int start) {
        if (depth == 3) return;

        for (Integer next : list.get(start)) {
            visited[next] = true;
            dfs(depth + 1, next);
        }
    }
}
