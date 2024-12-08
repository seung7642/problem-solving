package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_10451 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;
    private static List<Integer> list;
    private static boolean[] visited;

    private static class Edge {
        int u, v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        while (T-- != 0) {
            N = Integer.parseInt(br.readLine());

            visited = new boolean[N];
            list = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                int v = Integer.parseInt(st.nextToken());
                list.add(v - 1);
            }

            int cycle = 0;
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    dfs(i);
                    cycle++;
                }
            }

            System.out.println(cycle);
        }

        br.close();
    }

    private static void dfs(int start) {
        if (!visited[start]) {
            visited[start] = true;
            int nextNode = list.get(start);
            dfs(nextNode);
        }
    }
}
