package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11725 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static List<List<Integer>> list;
    private static boolean[] visited;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        parent = new int[N + 1];
        list = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            list.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(u).add(v);
            list.get(v).add(u);
        }

        dfs(1);

        for (int i = 2; i <= N; i++) {
            System.out.println(parent[i]);
        }

        br.close();
    }

    private static void dfs(int start) {
        if (visited[start]) return;

        for (Integer next : list.get(start)) {
            visited[start] = true;
            if (parent[next] == 0)
                parent[next] = start;
            dfs(next);
        }
    }
}
