package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1162 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, K;
    private static List<Edge>[] edges;
    private static long[][] dist;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        edges = new List[N + 1];
        dist = new long[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[u].add(new Edge(v, cost, 0));
            edges[v].add(new Edge(u, cost, 0));
        }

        System.out.println(findShortPath());
    }

    private static long findShortPath() {
        Queue<Edge> pq = new PriorityQueue<>(((o1, o2) -> (int) (o1.cost - o2.cost)));
        pq.add(new Edge(1, 0, 0));
        dist[1][0] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.cost > dist[cur.v][cur.cnt]) continue;

            for (Edge next : edges[cur.v]) {
                if (cur.cnt + 1 <= K && dist[next.v][cur.cnt + 1] > cur.cost) {
                    dist[next.v][cur.cnt + 1] = cur.cost;
                    pq.add(new Edge(next.v, cur.cost, cur.cnt + 1));
                }
                if (dist[next.v][cur.cnt] > dist[cur.v][cur.cnt] + next.cost) {
                    dist[next.v][cur.cnt] = dist[cur.v][cur.cnt] + next.cost;
                    pq.add(new Edge(next.v, dist[next.v][cur.cnt], cur.cnt));
                }
            }
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            ans = Math.min(dist[N][i], ans);
        }
        return ans;
    }

    private static class Edge {
        int v, cnt;
        long cost;

        public Edge(int v, long cost, int cnt) {
            this.v = v;
            this.cost = cost;
            this.cnt = cnt;
        }
    }
}
