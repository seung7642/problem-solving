package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1446 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, D;
    private static List<Edge>[] edges;
    private static int[] dist;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        dist = new int[10001];
        edges = new List[10001];
        for (int i = 0; i <= 10000; i++) {
            dist[i] = i;
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[u].add(new Edge(v, cost));
        }

        dijkstra(0);
        System.out.println(dist[D]);
    }

    private static void dijkstra(int start) {
        if (start > D) return;
        if (dist[start + 1] > dist[start] + 1) {
            dist[start + 1] = dist[start] + 1;
        }
        for (Edge next : edges[start]) {
            if (dist[next.v] > dist[start] + next.cost) {
                dist[next.v] = dist[start] + next.cost;
            }
        }
        dijkstra(start + 1);
    }

    private static class Edge {
        int v, cost;

        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}
