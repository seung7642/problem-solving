package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_10217 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, M, K;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            List<Edge>[] adj = new ArrayList[N + 1];
            int[][] dp = new int[N + 1][M + 1]; // 출발 지점인 1번 공항에서 i번 공항까지 j원의 비용을 들여 도달했을 때의 최소 시간

            for (int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < K; i++) {
                int u, v, c, d;
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());

                adj[u].add(new Edge(v, c, d));
            }

            Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
            dp[1][0] = 0;
            pq.add(new Edge(1, 0, 0));
            int answer = Integer.MAX_VALUE;

            while (!pq.isEmpty()) {
                Edge now = pq.poll();

                if (now.vertex == N) {
                    answer = now.time;
                    break;
                }

                for (Edge next : adj[now.vertex]) {
                    if (now.cost + next.cost > M) continue;

                    if (dp[next.vertex][now.cost + next.cost] > now.time + next.time) {
                        dp[next.vertex][now.cost + next.cost] = now.time + next.time;
                        pq.add(new Edge(next.vertex, now.cost + next.cost, dp[next.vertex][now.cost + next.cost]));
                    }
                }
            }
            sb.append(answer == Integer.MAX_VALUE ? "Poor KCM" : answer).append("\n");
        }
        System.out.println(sb);
    }

    private static class Edge {
        int vertex, cost, time;

        public Edge(int vertex, int cost, int time) {
            this.vertex = vertex;
            this.cost = cost;
            this.time = time;
        }
    }
}
