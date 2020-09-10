package graph.lowestCommonAncestor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11437 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static List<List<Integer>> tree;
    private static int[] depth;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        for (int i = 0; i < N + 1; i++)
            tree.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        depth = new int[N + 1];
        parents = new int[N + 1];

        dfs(1, 1);
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int lca = solve(a, depth[a], b, depth[b]);
            bw.write(lca + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int solve(int a, int aDepth, int b, int bDepth) {
        while (aDepth != bDepth) { // 둘의 depth가 같아질 때까지 위로 올린다.
            if (aDepth > bDepth) {
                aDepth--;
                a = parents[a];
            } else {
                bDepth--;
                b = parents[b];
            }
        }

        while (a != b) {
            a = parents[a];
            b = parents[b];
        }

        return a;
    }

    private static void dfs(int from, int cnt) {
        depth[from] = cnt++;
        for (Integer next : tree.get(from)) {
            if (depth[next] == 0) {
                dfs(next, cnt);
                parents[next] = from;
            }
        }
    }
}
