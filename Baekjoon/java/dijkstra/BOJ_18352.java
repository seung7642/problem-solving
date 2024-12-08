package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18352 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, K, X;
    private static List<Integer>[] path;
    private static int[] dist;
    private static StringBuffer sb = new StringBuffer();

    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        path = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            path[i] = new ArrayList<>();
        }

        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            path[u].add(v);
        }

        dijkstra();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                sb.append(i).append(" ");
            }
        }
        if ("".equals(sb.toString())) {
            sb.append("-1");
        }
        System.out.println(sb);
    }

    private static void dijkstra() {
        Queue<Integer> pq = new PriorityQueue<>();
        pq.add(X);
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Integer cur = pq.poll();

            for (int i : path[cur]) {
                if (dist[i] > dist[cur] + 1) {
                    dist[i] = dist[cur] + 1;
                    pq.add(i);
                }
            }
        }
    }
}
