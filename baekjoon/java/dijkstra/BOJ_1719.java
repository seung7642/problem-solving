package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1719 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static List<Edge>[] edges;
    private static int[] path, dist;
    private static boolean[] visited;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[u].add(new Edge(v, cost));
            edges[v].add(new Edge(u, cost));
        }

        for (int i = 1; i <= N; i++) {
            path = new int[N + 1];
            visited = new boolean[N + 1];
            dist = new int[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dijkstra(i);
        }
        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        dist[start] = 0;
        Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            // 간선이 양방향인 것에 대한 처리
            if (visited[cur.v]) continue;
            visited[cur.v] = true;

            for (Edge next : edges[cur.v]) {
                if (dist[next.v] > dist[cur.v] + next.cost) {
                    dist[next.v] = dist[cur.v] + next.cost;
                    path[next.v] = cur.v;
                    pq.add(new Edge(next.v, dist[next.v]));
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (i == start) {
                sb.append("- ");
            } else {
                int ans = find(i, start);
                sb.append(ans).append(" ");
            }
        }
        sb.append("\n");
    }

    private static int find(int i, int start) {
        if (path[i] == start) {
            return i;
        }
        return find(path[i], start);
    }

    private static class Edge {
        int v, cost;

        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}
