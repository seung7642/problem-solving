package disjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_7465 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, M;
    private static int[] parent;
    private static Set<Integer> groups;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // Make_Set
            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(in.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                union(v1, v2);
            }

            groups = new HashSet<>();
            for (int i = 1; i <= N; i++) {
                find(i);
                groups.add(parent[i]);
            }

            sb.append("#").append(tc).append(" ").append(groups.size()).append("\n");
        }
        System.out.println(sb);
    }

    private static void union(int v1, int v2) {
        v1 = find(v1);
        v2 = find(v2);
        if (v1 < v2) {
            parent[v2] = v1;
        } else {
            parent[v1] = v2;
        }
    }

    private static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]); // Path Compression
    }
}
