package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5719 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, S, D;
    private static List<Edge>[] edges;
    private static List<Integer>[] parent;
    private static boolean[][] isShortest;
    private static int[] dist;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }

            isShortest = new boolean[N][N];
            edges = new List[N];
            parent = new List[N];
            for (int i = 0; i < N; i++) {
                edges[i] = new ArrayList<>();
                parent[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            int u, v, p;
            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                p = Integer.parseInt(st.nextToken());
                edges[u].add(new Edge(v, p));
            }

            dijkstra();
            backTracking(D);
            dijkstra();

            if (dist[D] == Integer.MAX_VALUE) {
                sb.append("-1\n");
            } else {
                sb.append(dist[D]).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static void dijkstra() {
        dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Edge(S, 0));
        dist[S] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.cost > dist[cur.v]) continue;

            for (Edge next : edges[cur.v]) {
                if (isShortest[cur.v][next.v]) continue;
                if (dist[next.v] == cur.cost + next.cost) {
                    parent[next.v].add(cur.v);
                } else if (dist[next.v] > cur.cost + next.cost) {
                    dist[next.v] = cur.cost + next.cost;
                    parent[next.v].clear();
                    parent[next.v].add(cur.v);
                    pq.add(new Edge(next.v, dist[next.v]));
                }
            }
        }
    }

    private static void backTracking(int cur) {
        if (S == cur) return;
        for (Integer prev : parent[cur]) {
            if (!isShortest[prev][cur]) {
                isShortest[prev][cur] = true;
                backTracking(prev);
            }
        }
    }

    private static class Edge {
        int v, cost;

        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}
